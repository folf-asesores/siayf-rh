/*
 *
 */

package mx.gob.saludtlax.rh.empleados.suplencia;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.EnumTipoFiltro;
import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.FiltroDTO;
import mx.gob.saludtlax.rh.catalogos.Catalogo;
import mx.gob.saludtlax.rh.empleados.administracion.Empleado;
import mx.gob.saludtlax.rh.empleados.administracion.EnumEstatusEmpleado;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.seguridad.ConfiguracionConst;
import mx.gob.saludtlax.rh.seguridad.usuario.UsuarioDTO;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.SelectItemsUtil;
import mx.gob.saludtlax.rh.vacantes.seleccion.EnumTipoCandidato;

/**
 * @author Leila Schiaffini Ehuan
 * @since 2016-10-19
 *
 */
@ManagedBean(name = "habilitacionSuplente")
@ViewScoped
public class HabilitacionSuplenteController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4541679753011523872L;
    @Inject
    private Catalogo catalogos;
    @Inject
    private Empleado empleado;
    @Inject
    private Suplencia suplencia;

    private HabilitacionSuplenteView view = new HabilitacionSuplenteView();

    @PostConstruct
    public void inicio() {
        view.setTiposCandidatos(SelectItemsUtil.listaTipoCandidato());
        view.setMostrarBusqueda(true);
        view.setListaTiposSexo(SelectItemsUtil.listaTiposSexo());
        view.setListaEstados(
                SelectItemsUtil.listaCatalogos(catalogos.listaEstados()));
        view.setListaBancos(
                SelectItemsUtil.listaCatalogos(catalogos.listaBancos()));
        view.setListaDependencias(
                SelectItemsUtil.listaCatalogos(catalogos.listaDependencias()));
        view.setListaProyectos(
                SelectItemsUtil.listaCatalogos(catalogos.consultarProyectos()));
        view.setListaCentrosResponsabilidades(SelectItemsUtil
                .listaCatalogos(catalogos.consultarCentrosResponsabilidades()));
        view.setListaTiposBusqueda(
                SelectItemsUtil.listaTiposBusquedaSuplentes());
        view.setListaPuestos(
                SelectItemsUtil.listaCatalogos(catalogos.listaPuestos()));

        HttpServletRequest request = (HttpServletRequest) FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        UsuarioDTO usuario = (UsuarioDTO) httpSession
                .getAttribute(ConfiguracionConst.SESSION_ATRIBUTE_LOGGED_USER);
        view.setIdUsuarioLogeado(usuario.getIdUsuario());

    }

    public void obtenerUnidadesResponsables() {
        view.getListaUnidadesResponsables().clear();
        if (view.getRegistroSuplente().getIdDependencia() != 0) {
            view.setListaUnidadesResponsables(SelectItemsUtil.listaCatalogos(
                    catalogos.listaUnidadesResponsablesPorDependencia(
                            view.getRegistroSuplente().getIdDependencia())));
        }

    }

    public void seleccionarEmpleado(Integer idEmpleado) {
        view.setMostrarDetalleLaboral(false);
        view.getRegistroSuplente().setIdEmpleado(idEmpleado);
        view.setEmpleado(empleado.obtenerInformacionEmpleado(idEmpleado));
        view.setMostrarDetalleEmpleado(true);
        view.getRegistroSuplente().setIdEmpleado(idEmpleado);
        view.getRegistroSuplente()
                .setIdTipoCandidato(EnumTipoCandidato.EMPLEADO);
        if (view.getEmpleado().getEstatus().equals(EnumEstatusEmpleado.ACTIVO)
                && view.getEmpleado().getTipoEmpleado().equals("EMPLEADO")) {
            view.setMostrarDetalleLaboral(true);
        }

    }

    public void ocultarDetalleEmpleado() {
        view.setMostrarDetalleEmpleado(false);
    }

    public void consultarMunicipioPorIdEstado() {
        if (view.getRegistroSuplente().getSuplente().getDireccion()
                .getIdEstado() != 0) {
            view.setListaMunicipios(SelectItemsUtil.listaCatalogos(catalogos
                    .consultarMunicipiosPorEstado(view.getRegistroSuplente()
                            .getSuplente().getDireccion().getIdEstado())));
        }
    }

    public void consultarAsentamientosPorIdMunicipio() {
        if (view.getRegistroSuplente().getSuplente().getDireccion()
                .getIdMunicipio() != 0) {
            view.setListaAsentamientos(SelectItemsUtil.listaCatalogos(
                    catalogos.consultarAsantamientosPorMunicipios(
                            view.getRegistroSuplente().getSuplente()
                                    .getDireccion().getIdMunicipio())));

        }

    }

    public void consultarEmpleado() {
        try {
            FiltroDTO filtro = new FiltroDTO();
            filtro.setCriterio(view.getCriterio());
            filtro.setTipoFiltro(EnumTipoFiltro.NOMBRE_RFC_CURP);
            view.setEmpleados(empleado.consultaEmpleado(filtro));
            view.setCriterio("");
        } catch (ValidacionException exception) {
            JSFUtils.warningMessage("", exception.getMessage());
        }

    }

    public void seleccionarTipoCandidato() {
        view.setMostrarEmpleado(false);
        view.setMostrarAltaSuplente(false);
        RegistroSuplenteDTO registro = new RegistroSuplenteDTO();
        view.setRegistroSuplente(registro);

        if (view.getIdTipoCandidato() == EnumTipoCandidato.ASPIRANTE) {
            view.setMostrarAltaSuplente(true);
            view.getRegistroSuplente()
                    .setIdTipoCandidato(EnumTipoCandidato.ASPIRANTE);
        } else if (view.getIdTipoCandidato() == EnumTipoCandidato.EMPLEADO) {
            view.setMostrarEmpleado(true);
            view.getRegistroSuplente()
                    .setIdTipoCandidato(EnumTipoCandidato.EMPLEADO);
        }

    }

    public void registrarSuplente() {
        try {
            view.getRegistroSuplente().setIdUsuario(view.getIdUsuarioLogeado());
            suplencia.habilitarSuplente(view.getRegistroSuplente());
            view.setMostrarHabilitacionSuplente(false);
            view.setMostrarDetalleEmpleado(false);
            view.setMostrarBusqueda(true);
            RegistroSuplenteDTO registro = new RegistroSuplenteDTO();
            view.setRegistroSuplente(registro);
            JSFUtils.infoMessage("",
                    "¡El suplente ha sido habilitado con éxito!");
        } catch (ReglaNegocioException | ValidacionException exception) {

            if (view.getRegistroSuplente()
                    .getIdTipoCandidato() == EnumTipoCandidato.ASPIRANTE) {

                JSFUtils.errorMessageEspecifico("errorCandidato", "",
                        exception.getMessage());
            } else if (view.getRegistroSuplente()
                    .getIdTipoCandidato() == EnumTipoCandidato.EMPLEADO) {

                JSFUtils.errorMessageEspecifico("errorEmpleado", "",
                        exception.getMessage());
            }

        }
    }

    public void consultarSuplentes() {
        view.getSuplentes().clear();
        view.setSuplentes(
                suplencia.consultarSuplentesPorCriterio(view.getFiltro()));
        if (view.getSuplentes().isEmpty()) {
            JSFUtils.warningMessage("",
                    "No se encontrarón suplentes con el criterio "
                            + view.getFiltro().getCriterio());
        }

    }

    public void seleccionarTipoBusqueda() {
        view.setMostrarCriterio(false);
        view.getSuplentes().clear();
        if (view.getFiltro()
                .getTipoConsulta() == EnumTipoConsultaSuplencia.NOMBRE) {
            view.setMostrarCriterio(true);
        } else if (view.getFiltro()
                .getTipoConsulta() == EnumTipoConsultaSuplencia.ACTIVO) {
            view.setSuplentes(
                    suplencia.consultarSuplentesPorCriterio(view.getFiltro()));
            if (view.getSuplentes().isEmpty()) {
                JSFUtils.warningMessage("",
                        "No se encontrarón suplentes activos registrados");
            }
        } else if (view.getFiltro()
                .getTipoConsulta() == EnumTipoConsultaSuplencia.INACTIVO) {
            view.setSuplentes(
                    suplencia.consultarSuplentesPorCriterio(view.getFiltro()));
            if (view.getSuplentes().isEmpty()) {
                JSFUtils.warningMessage("",
                        "No se encontrarón suplentes inactivos registrados");
            }
        }
    }

    public void mostrarHabilitacion() {
        view.setMostrarHabilitacionSuplente(true);
        view.setMostrarBusqueda(false);
        RegistroSuplenteDTO registroSuplente = new RegistroSuplenteDTO();
        view.setRegistroSuplente(registroSuplente);
    }

    public void ocultarHabilitacion() {
        view.setMostrarHabilitacionSuplente(false);
        view.setMostrarBusqueda(true);
    }

    public void mostrarActivacion(Integer idSuplente) {
        view.setIdSuplente(idSuplente);
        view.setMostrarActivacion(true);
        view.setMostrarBusqueda(false);
    }

    public void ocultarActivacion() {
        view.setMostrarActivacion(false);
        view.setMostrarBusqueda(true);
    }

    public void actualizarEstatus(String estatus) {
        try {
            suplencia.actualizarEstatusSuplente(view.getIdSuplente(), estatus);
            ocultarActivacion();
        } catch (ReglaNegocioException | ValidacionException exception) {
            JSFUtils.errorMessage("", exception.getMessage());
        }
    }

    public HabilitacionSuplenteView getView() {
        return view;
    }

    public void setView(HabilitacionSuplenteView view) {
        this.view = view;
    }

}
