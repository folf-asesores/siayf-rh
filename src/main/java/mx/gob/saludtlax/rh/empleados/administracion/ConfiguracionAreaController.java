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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mx.gob.saludtlax.rh.catalogos.Catalogo;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.puestosautorizados.EnumFiltroConsultaVacante;
import mx.gob.saludtlax.rh.puestosautorizados.FiltroVacanteDTO;
import mx.gob.saludtlax.rh.puestosautorizados.PuestosAutorizadosEmpleados;
import mx.gob.saludtlax.rh.seguridad.ConfiguracionConst;
import mx.gob.saludtlax.rh.seguridad.usuario.UsuarioDTO;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.SelectItemsUtil;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 13/09/2016 01:42:00
 */
@ManagedBean(name = "configuracionArea")
@ViewScoped
public class ConfiguracionAreaController implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 75529045788980696L;

    @Inject
    private Catalogo catalogo;
    @Inject
    private PuestosAutorizadosEmpleados puestosEmpleados;

    private UbicacionEmpleadoView view;

    @PostConstruct
    public void inicio() {
        this.view = new UbicacionEmpleadoView();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        UsuarioDTO usuario = (UsuarioDTO) httpSession.getAttribute(ConfiguracionConst.SESSION_ATRIBUTE_LOGGED_USER);

        this.view.setListaAdscripciones(SelectItemsUtil.listaCatalogos(this.catalogo.consultarAdscripciones()));
        this.view.setListaSubadscripcion(SelectItemsUtil.listaCatalogos(this.catalogo.consultarSubadscripciones()));
        this.view.setListaServicio(SelectItemsUtil.listaCatalogos(this.catalogo.consultarServicios()));
        this.view.setListaFuncion(SelectItemsUtil.listaCatalogos(this.catalogo.consultarFunciones()));
        this.view.setListaClues(SelectItemsUtil.listaCatalogos(this.catalogo.consultarClues()));
        this.view.setListaCentrosResponsabilidad(SelectItemsUtil.listaCatalogos(this.catalogo.consultarCentrosResponsabilidades()));

        this.view.setListaUbicaciones(SelectItemsUtil.listaFiltrosConsultaUbicaciones());
        this.view.setListaDependencias(SelectItemsUtil.listaCatalogos(this.catalogo.listaDependencias()));
        this.view.setMostrarBusqueda(true);
        this.view.setCriterio(" ");
        this.view.getUbicacion().setIdUsuario(usuario.getIdUsuario());
    }

    public void seleccionarFiltro() {
        try {
            this.view.getPuestosEmpleados().clear();
            this.view.setMostrarUnidades(false);
            this.view.setMostrarCriterio(false);
            if (this.view.getIdFiltro() == EnumFiltroConsultaVacante.UNIDAD_RESPONSABLE) {
                this.view.setMostrarUnidades(true);
            } else if (this.view.getIdFiltro() == EnumFiltroConsultaVacante.NOMBRE_RFC) {
                this.view.setMostrarCriterio(true);
            } else {

                FiltroVacanteDTO filtroVacanteDTO = new FiltroVacanteDTO();
                filtroVacanteDTO.setTipoBusqueda(this.view.getIdFiltro());
                this.view.setPuestosEmpleados(this.puestosEmpleados.consultaVacantesPorCriterio(filtroVacanteDTO));
                if (this.view.getPuestosEmpleados().isEmpty()) {
                    JSFUtils.warningMessage("", "Sin resultados.");
                }
            }

        } catch (ReglaNegocioException exception) {
            throw new ReglaNegocioException(exception.getMessage(), exception.getCodigoError());
        }
    }

    public void buscarEmpleados() {
        try {
            this.view.getPuestosEmpleados().clear();
            FiltroVacanteDTO filtroVacanteDTO = new FiltroVacanteDTO();
            filtroVacanteDTO.setTipoBusqueda(this.view.getIdFiltro());
            filtroVacanteDTO.setIdentificador(this.view.getIdContexto());
            this.view.setPuestosEmpleados(this.puestosEmpleados.consultaVacantesPorCriterio(filtroVacanteDTO));
            if (this.view.getPuestosEmpleados().isEmpty()) {
                JSFUtils.warningMessage("", "Sin resultados.");
            }

        } catch (ReglaNegocioException exception) {
            throw new ReglaNegocioException(exception.getMessage(), exception.getCodigoError());
        }
    }

    public void obtenerUnidadesResponsables() {
        this.view.getListaUnidadesResponsables().clear();
        if (this.view.getIdDependencia() != 0) {
            this.view.setListaUnidadesResponsables(
                    SelectItemsUtil.listaCatalogos(this.catalogo.listaUnidadesResponsablesPorDependencia(this.view.getIdDependencia())));
        }

    }

    public void buscarEmpleadoCriterio() {
        try {
            if (this.view.getCriterio().length() > 3) {
                this.view.getPuestosEmpleados().clear();
                FiltroVacanteDTO filtroVacanteDTO = new FiltroVacanteDTO();
                filtroVacanteDTO.setTipoBusqueda(this.view.getIdFiltro());
                filtroVacanteDTO.setCriterio(this.view.getCriterio().trim());
                this.view.setPuestosEmpleados(this.puestosEmpleados.consultaVacantesPorCriterio(filtroVacanteDTO));
                if (this.view.getPuestosEmpleados().isEmpty()) {
                    JSFUtils.warningMessage("", "Sin resultados.");
                }
            }
        } catch (ReglaNegocioException | ValidacionException exception) {
            JSFUtils.errorMessage("", exception.getMessage());
        }
    }

    public void obtenerDetalle(Integer idInventario) {
        try {
            this.view.getUbicacion().setIdInventarioVacante(idInventario);
            this.view.setMostrarBusqueda(false);
            this.view.setMostrarDetalle(true);
            this.view.setPuesto(this.puestosEmpleados.obtenerInformacionPuesto(idInventario));

            this.view.getUbicacion().setIdAdscripcion(this.view.getPuesto().getIdAdscripcion());
            this.view.getUbicacion().setIdSubadscripcion(this.view.getPuesto().getIdSubadscripcion());
            this.view.getUbicacion().setIdServicio(this.view.getPuesto().getIdServicio());
            this.view.getUbicacion().setIdFuncion(this.view.getPuesto().getIdFuncion());
            this.view.getUbicacion().setIdClue(this.view.getPuesto().getIdClue());
            this.view.getUbicacion().setIdCentroResponsabilidad(this.view.getPuesto().getIdCentroResponsabilidad());

        } catch (ReglaNegocioException exception) {
            JSFUtils.errorMessage("", exception.getMessage());
        }

    }

    public void ubicarEmpleado() {
        try {
            this.puestosEmpleados.ubicarEmpleado(this.view.getUbicacion());
            inicio();
            JSFUtils.infoMessage("", "¡La ubicación ha sido registrada con éxito!");

        } catch (ReglaNegocioException exception) {
            JSFUtils.errorMessage("", exception.getMessage());
        }
    }

    public UbicacionEmpleadoView getView() {
        return this.view;
    }

    public void setView(UbicacionEmpleadoView view) {
        this.view = view;
    }

}
