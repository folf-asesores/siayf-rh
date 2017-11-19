/*
 *
 */

package mx.gob.saludtlax.rh.puestosautorizados.programas;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.event.RowEditEvent;

import mx.gob.saludtlax.rh.catalogos.Catalogo;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.seguridad.ConfiguracionConst;
import mx.gob.saludtlax.rh.seguridad.usuario.UsuarioDTO;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.SelectItemsUtil;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author Leila Schiaffini Ehuan
 * @since 2016-10-21
 *
 */
@ManagedBean(name = "programasFederales")
@ViewScoped
public class ProgramasFederalesController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4238994823797744483L;

    @Inject
    private Catalogo catalogo;
    @Inject
    private Programa programa;

    private ProgramaFederealView view = new ProgramaFederealView();

    private List<ProgramaDTO> programasFilter = new ArrayList<>();

    @PostConstruct
    public void inicio() {

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        UsuarioDTO usuario = (UsuarioDTO) httpSession.getAttribute(ConfiguracionConst.SESSION_ATRIBUTE_LOGGED_USER);
        view.setIdUsuarioLogeado(usuario.getIdUsuario());

        view.setProgramas(programa.consultarProgramas());
        view.setListaPuestos(SelectItemsUtil.listaCatalogos(catalogo.listaPuestos()));
        view.setListaDependencias(SelectItemsUtil.listaCatalogos(catalogo.listaDependencias()));
        view.setListaFuentesFinanciamiento(SelectItemsUtil.listaCatalogos(catalogo.listaFuentesFinanciamientos()));
        view.setListaTiposRecursos(SelectItemsUtil.listaCatalogos(catalogo.listaTiposRecursos()));
        view.setListaCuentaFinanciamiento(SelectItemsUtil.listaCatalogos(catalogo.consultarCuentasBancariasActuales()));
        view.setListaProyectos(SelectItemsUtil.listaCatalogos(catalogo.consultarProyectos()));

    }

    public void obtenerUnidadesResponsablesPrograma() {
        view.getListaUnidadesResponsables().clear();
        if (view.getNuevoPrograma().getIdDependencia() != 0) {
            view.setListaUnidadesResponsables(
                    SelectItemsUtil.listaCatalogos(catalogo.listaUnidadesResponsablesPorDependencia(view.getNuevoPrograma().getIdDependencia())));
        }

    }

    public void obtenerUnidadesResponsablesEdicionPrograma() {
        view.getListaUnidadesResponsables().clear();
        if (view.getEdicionPrograma().getIdDependencia() != null && view.getEdicionPrograma().getIdDependencia() != 0) {
            view.setListaUnidadesResponsables(
                    SelectItemsUtil.listaCatalogos(catalogo.listaUnidadesResponsablesPorDependencia(view.getEdicionPrograma().getIdDependencia())));
        }

    }

    public void obtenerSubfuentesFinanciamientoProgramas() {

        view.getListaSubfuentesFinanciamiento().clear();
        if (view.getNuevoPrograma().getIdFuenteFinanciamiento() != null && view.getNuevoPrograma().getIdFuenteFinanciamiento() != 0) {
            view.setListaSubfuentesFinanciamiento(SelectItemsUtil
                    .listaCatalogos(catalogo.listaSubfuentesFinanciamientosPorFinanciamiento(view.getNuevoPrograma().getIdFuenteFinanciamiento())));
        }

    }

    public void obtenerSubfuentesFinanciamientoEdicionPrograma() {

        view.getListaSubfuentesFinanciamiento().clear();
        if (view.getEdicionPrograma().getIdFuenteFinanciamiento() != null && view.getEdicionPrograma().getIdFuenteFinanciamiento() != 0) {
            view.setListaSubfuentesFinanciamiento(SelectItemsUtil
                    .listaCatalogos(catalogo.listaSubfuentesFinanciamientosPorFinanciamiento(view.getEdicionPrograma().getIdFuenteFinanciamiento())));
        }

    }

    public void registrarPrograma() {
        try {
            programa.crearPrograma(view.getNuevoPrograma(), view.getIdUsuarioLogeado());
            view.setNuevoPrograma(new ProgramaDTO());
            view.setProgramas(programa.consultarProgramas());
            view.setMostrarRegistroPrograma(false);
            JSFUtils.infoMessage("", "El programa se ha registrado con éxito.");
        } catch (ReglaNegocioException exception) {
            JSFUtils.errorMessageEspecifico("error", "", exception.getMessage());
        }
    }

    public void registrarDetallePrograma() {
        try {
            view.getDetallePrograma().setIdUsuario(view.getIdUsuarioLogeado());
            view.getDetallePrograma().setIdTipoDetalle(EnumTipoDetallePrograma.PARTIDA);
            programa.crearDetallePrograma(view.getDetallePrograma());
            view.setDetalles(programa.consultarDetallesProgramas(view.getDetallePrograma().getIdPrograma()));
            Integer programa = view.getDetallePrograma().getIdPrograma();

            view.setDetallePrograma(new DetalleProgramaDTO());
            view.getDetallePrograma().setIdPrograma(programa);
            view.getDetallePrograma().setIdTipoDetalle(EnumTipoDetallePrograma.PARTIDA);

        } catch (ReglaNegocioException exception) {
            JSFUtils.errorMessageEspecifico("errorDetalle", "", exception.getMessage());
        }
    }

    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Car Edited", ((DetalleProgramaDTO) event.getObject()).getIdDetalle() + "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void visualizarRegistroPrograma() {
        view.setMostrarRegistroPrograma(true);
    }

    public void ocultarRegistroPrograma() {
        view.setMostrarRegistroPrograma(false);
    }

    public void visualizarDetallePrograma(Integer idPrograma) {
        view.setMostrarRegistroDetalle(true);
        view.getDetalles().clear();
        view.setDetalles(programa.consultarDetallesProgramas(idPrograma));
        view.getDetallePrograma().setIdPrograma(idPrograma);
        view.getDetallePrograma().setIdTipoDetalle(EnumTipoDetallePrograma.PARTIDA);
        view.setMostrarDetallePartida(true);
    }

    public void ocultarDetallePrograma() {
        view.setMostrarRegistroDetalle(false);
    }

    public void seleccionarTipoDetalle() {
        view.setMostrarDetallePartida(false);
        if (view.getDetallePrograma().getIdTipoDetalle().equals(EnumTipoDetallePrograma.PARTIDA)) {
            view.setMostrarDetallePartida(true);
        }
    }

    public void calcularTotal() {

        view.getDetallePrograma().setTotalGlobal(BigDecimal.ZERO);
        if (ValidacionUtil.esNumeroPositivo(view.getDetallePrograma().getNumeroPersonas())
                && ValidacionUtil.esNumeroPositivo(view.getDetallePrograma().getCostoUnitario())) {

            BigDecimal numero = new BigDecimal(view.getDetallePrograma().getNumeroPersonas());
            BigDecimal total = numero.multiply(view.getDetallePrograma().getCostoUnitario());
            view.getDetallePrograma().setTotalGlobal(total);

        }
    }

    public void seleccionarEdicionPrograma(Integer idPrograma) {
        view.setMostrarEdicion(true);
        view.setEdicionPrograma(programa.obtenerDetalleProgramaPorId(idPrograma));
        obtenerUnidadesResponsablesEdicionPrograma();
        obtenerSubfuentesFinanciamientoEdicionPrograma();
    }

    public void editarPrograma() {
        try {
            programa.editarPrograma(view.getEdicionPrograma());
            view.setMostrarEdicion(false);
            JSFUtils.infoMessage("", "El programa ha sido editada con éxito.");
        } catch (ValidacionException exception) {
            JSFUtils.errorMessageEspecifico("errorEdicion", "", exception.getMessage());
        }
    }

    public void ocultarEdicion() {
        view.setMostrarEdicion(false);
    }

    public ProgramaFederealView getView() {
        return view;
    }

    public void setView(ProgramaFederealView view) {
        this.view = view;
    }

    public List<ProgramaDTO> getProgramasFilter() {
        return programasFilter;
    }

    public void setProgramasFilter(List<ProgramaDTO> programasFilter) {
        this.programasFilter = programasFilter;
    }

}
