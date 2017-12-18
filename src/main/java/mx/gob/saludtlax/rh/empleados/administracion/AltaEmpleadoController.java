/**
 * 
 */

package mx.gob.saludtlax.rh.empleados.administracion;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mx.gob.saludtlax.rh.catalogos.Catalogo;
import mx.gob.saludtlax.rh.empleados.datolaboral.ConfiguracionPresupuestal;
import mx.gob.saludtlax.rh.empleados.datolaboral.DetalleConfiguracionPresupuestoDTO;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.puestosautorizados.EnumFiltroConsultaVacante;
import mx.gob.saludtlax.rh.puestosautorizados.EnumTipoContratacion;
import mx.gob.saludtlax.rh.puestosautorizados.EnumTipoNombramiento;
import mx.gob.saludtlax.rh.puestosautorizados.FiltroVacanteDTO;
import mx.gob.saludtlax.rh.puestosautorizados.PuestosAutorizadosEmpleados;
import mx.gob.saludtlax.rh.seguridad.ConfiguracionConst;
import mx.gob.saludtlax.rh.seguridad.usuario.UsuarioDTO;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.SelectItemsUtil;
import mx.gob.saludtlax.rh.vacantes.postulacion.InfoCandidatoDTO;
import mx.gob.saludtlax.rh.vacantes.postulacion.Postulacion;
import mx.gob.saludtlax.rh.vacantes.seleccion.EnumTipoCandidato;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 26/08/2016 05:13:42
 */
@ViewScoped
@ManagedBean(name = "altaEmpleado")
public class AltaEmpleadoController implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -6918001437877890399L;
    @Inject
    private Catalogo catalogo;
    @Inject
    private ConfiguracionPresupuestal configuracionPresupuestal;
    @Inject
    private Empleado empleado;
    @Inject
    private PuestosAutorizadosEmpleados vacantes;
    @Inject
    private Postulacion postulacion;

    private AltaEmpleadoView view = new AltaEmpleadoView();

    @PostConstruct
    public void inicio() {
        this.view.setListaTiposContratacion(SelectItemsUtil.listaTiposContratacion());
        this.view.setListaTiposJornadas(SelectItemsUtil.listaCatalogos(this.catalogo.listaTiposJornadas()));
        this.view.setListaBancos(SelectItemsUtil.listaCatalogos(this.catalogo.listaBancos()));
        this.view.setListaMetodosPago(SelectItemsUtil.listaCatalogos(this.catalogo.consultarMetodosPago()));
        this.view.setMostrarTablaResultado(true);
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        UsuarioDTO usuario = (UsuarioDTO) httpSession.getAttribute(ConfiguracionConst.SESSION_ATRIBUTE_LOGGED_USER);
        this.view.getAltaEmpleado().setIdUsuario(usuario.getIdUsuario());
    }

    public void vacantesDisponiblesPorContratacion() {
        try {

            this.view.getVacantesDisponibles().clear();
            FiltroVacanteDTO filtroVacanteDTO = new FiltroVacanteDTO();
            filtroVacanteDTO.setIdentificador(this.view.getIdTipoContratacion());
            filtroVacanteDTO.setTipoBusqueda(EnumFiltroConsultaVacante.DISPONIBLES_POSTULADAS);

            this.view.setVacantesDisponibles(this.vacantes.consultaVacantesPorCriterio(filtroVacanteDTO));
            if (this.view.getVacantesDisponibles().isEmpty()) {
                JSFUtils.warningMessage("", "No se encontró candidatos aprobados, seleccione otro tipo de contratación");
            }

        } catch (ReglaNegocioException exception) {
            throw new ReglaNegocioException(exception.getMessage(), exception.getCodigoError());
        }
    }

    public void obtenerDetalleConfiguracion(Integer idConfiguracion, Integer idVacante, String tipoContratacion) {

        try {
            this.view.getAltaEmpleado().setIdVacante(idVacante);
            this.view.setMostrarTablaResultado(false);
            this.view.setMostrarPanelAlta(true);
            this.view.setMostrarCamposFederales(false);
            this.view.setMostrarCamposEventuales(false);
            DetalleConfiguracionPresupuestoDTO detalle = this.configuracionPresupuestal.obtenerDetalleConfiguracionId(idConfiguracion);
            this.view.setDetalleVacante(detalle);

            InfoCandidatoDTO candidato = this.postulacion.obtenerInformacionCandidatoAprobado(idVacante);

            this.view.setCandidato(candidato);

            if (candidato.getIdTipoCandidato() == EnumTipoCandidato.EMPLEADO) {
                ValidacionEmpleadoDTO validacion = this.empleado.validarDatosObligatoriosEmpleado(candidato.getIdContexto());
                if (!validacion.isEsValido()) {
                    JSFUtils.warningMessage("", validacion.getMensaje());
                }
            }

            if (tipoContratacion.equals("BASE")) {
                this.view.setMostrarCamposFederales(true);
            } else if (tipoContratacion.equals("CONTRATO ESTATAL") || tipoContratacion.equals("CONTRATO FEDERAL")) {
                this.view.setMostrarCamposEventuales(true);
            }

            this.view.getAltaEmpleado().getNombramiento().setClavePresupuestal(
                    generarClavePresupuestal(detalle.getCodigoPuesto(), detalle.getIdTipoContratacion(), detalle.getIdTipoNombramiento()));

        } catch (NoResultException exception) {
            JSFUtils.errorMessage("", exception.getMessage());
        }
    }

    public void registrarEmpleado() {
        try {
            boolean validacion = false;
            if (this.view.getAltaEmpleado().getFechaInicioLabores() == null) {
                validacion = true;
                JSFUtils.errorMessage("", "La fecha de inicio de labores es requerida.");
            }
            if (this.view.getAltaEmpleado().getFechaIngreso() == null) {
                validacion = true;
                JSFUtils.errorMessage("", "La fecha de ingreso es requerida.");
            }

            if (!validacion) {
                this.empleado.crearEmpleado(this.view.getAltaEmpleado());
                AltaEmpleadoView view = new AltaEmpleadoView();
                setView(view);
                inicio();
                view.setMostrarTablaResultado(true);
                JSFUtils.infoMessage("", "¡El empleado ha sido registrado con éxito!");
            }
        } catch (ReglaNegocioException exception) {
            throw new ReglaNegocioException(exception.getMessage(), exception.getCodigoError());
        }
    }

    private String generarClavePresupuestal(String codigoPuesto, Integer tipoContratacion, Integer idTipoNombramiento) {
        String clavePresupuestal = "";
        if (tipoContratacion == EnumTipoContratacion.BASE) {
            clavePresupuestal = "IO02 " + "4161103 " + codigoPuesto + " 29004 0001";
        } else if (tipoContratacion == EnumTipoContratacion.HOMOLOGADOS) {
            clavePresupuestal = "IO02 " + "HOM1103 " + codigoPuesto + " 29004 0001";
        } else if (tipoContratacion == EnumTipoContratacion.REGULARIZADOS) {
            clavePresupuestal = "U004 " + "REG1103 " + codigoPuesto + " 0002 20001";
        } else if (tipoContratacion == EnumTipoContratacion.FORMALIZADOS) {
            if (idTipoNombramiento == EnumTipoNombramiento.FORMALIZADOS) {
                clavePresupuestal = "U005" + "FOR1103 " + codigoPuesto + " 00022 0001";
            } else if (idTipoNombramiento == EnumTipoNombramiento.FORMALIZADOS_2) {
                clavePresupuestal = "U005" + "FO21103 " + codigoPuesto + " 00022 0001";
            }
        }

        return clavePresupuestal;
    }

    public AltaEmpleadoView getView() {
        return this.view;
    }

    public void setView(AltaEmpleadoView view) {
        this.view = view;
    }

}
