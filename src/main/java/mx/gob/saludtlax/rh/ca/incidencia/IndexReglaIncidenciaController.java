
package mx.gob.saludtlax.rh.ca.incidencia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;

import mx.gob.saludtlax.rh.configuracion.serviciosweb.ServicioWebException;
import mx.gob.saludtlax.rh.configuracion.serviciosweb.ServiciosWebEJB;
import mx.gob.saludtlax.rh.excepciones.RESTClientException;
import mx.gob.saludtlax.rh.persistencia.ServiciosRSEntity;
import mx.gob.saludtlax.rh.persistencia.TipoContratacionEntity;
import mx.gob.saludtlax.rh.persistencia.TipoContratacionRepository;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.ListadoMensajesSistema;
import mx.gob.saludtlax.rh.util.ServicioWebEnum;

@ManagedBean(name = "indexReglaIncidenciaController")
@ViewScoped
public class IndexReglaIncidenciaController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5254827465513586438L;

    @Inject
    TipoContratacionRepository tipoContratacionRepository;
    @Inject
    ReglaIncidenciaClienteRest reglaIncidenciaClienteRest;
    @Inject
    IncidenciaClienteRest incidenciaClientRest;

    @Inject
    ServiciosWebEJB serviocWebEJB;

    private List<SelectItem> listadoTiposContrataciones;
    private Integer tipoContratacionSeleccionada = 0;
    private List<ReglaIncidenciaViewModel> listadoReglaIncidenciaViewModel;
    private Integer idIncidencia;
    private IncidenciaModelView incidenciaViewModel = null;
    private ReglaIncidenciaFormModel reglaIncidenciaFormModel = new ReglaIncidenciaFormModel();

    public void init() {

        if (FacesContext.getCurrentInstance().isPostback()) {
            return;
        }

        try {
            ServiciosRSEntity servicioRSEntity = serviocWebEJB
                    .getServicioActivo(ServicioWebEnum.CONTROL_ASISTENCIA_RS);
            if (!servicioRSEntity.isProduccion()) {
                HttpServletRequest req = (HttpServletRequest) FacesContext
                        .getCurrentInstance().getExternalContext().getRequest();
                String url = req.getContextPath().toString();
                FacesMessage facesMessage = new FacesMessage(
                        FacesMessage.SEVERITY_WARN, "Servicio en Modo Prueba",
                        "El servcio configurado como activo para este modulo es de pruebas consulte la <a href='"
                                + url
                                + "/contenido/configuracion/serviciosweb/index.xhtml'>configuracion</a>");
                FacesContext.getCurrentInstance().addMessage(null,
                        facesMessage);

            }

        } catch (ServicioWebException e1) {
            FacesMessage facesMessage = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, e1.getMessage(),
                    e1.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            return;
        }

        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> params = context.getExternalContext()
                .getRequestParameterMap();
        String id = params.get("i");

        if (id == null) {
            HttpServletRequest req = (HttpServletRequest) FacesContext
                    .getCurrentInstance().getExternalContext().getRequest();
            String url = req.getContextPath().toString();
            FacesMessage facesMessage = new FacesMessage(
                    FacesMessage.SEVERITY_WARN, "Error Acceso",
                    "Ocurrio un error al intentar buscar la incidencia seleccionada por favor regresa al  <a href='"
                            + url
                            + "/contenido/controlasistencia/catalogo/incidencia/index.xhtml'>catalogo de incidencias</a>");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            return;
        }
        idIncidencia = new Integer(id);

        llenarTiposContratacion();
        try {
            llenarReglasIncidencias();
            incidenciaViewModel = incidenciaClientRest
                    .buscarIncidenciaPorId(idIncidencia);
        } catch (RESTClientException e) {
            e.printStackTrace();
            JSFUtils.errorMessage(ListadoMensajesSistema.E002.getMensaje(),
                    e.getMessage());
        }

    }

    public void filtrarPorTipoContratacion() {
        try {
            llenarReglasIncidencias();
        } catch (RESTClientException e) {
            e.printStackTrace();
            JSFUtils.errorMessage(ListadoMensajesSistema.E002.getMensaje(),
                    e.getMessage());
        }

    }

    public void nuevaReglaIncidencia() {

        reglaIncidenciaFormModel = new ReglaIncidenciaFormModel();

        RequestContext.getCurrentInstance()
                .execute("PF('dglNuevaReglaIncidencia').show()");

    }

    public void eliminarReglaIncidencia(
            ReglaIncidenciaViewModel reglaIncidenciaElminar) {

        try {
            reglaIncidenciaClienteRest.elminarReglaIncidencia(
                    reglaIncidenciaElminar.getIdReglaIncidencia());
            llenarReglasIncidencias();
        } catch (RESTClientException e) {
            JSFUtils.errorMessage("Error", e.getMessage());
            e.printStackTrace();
        }

    }

    public void crearNuevaReglaIncidencia() {
        reglaIncidenciaFormModel.setIdIncidencia(idIncidencia);
        try {
            reglaIncidenciaClienteRest
                    .crearNuevaReglaIncidencia(reglaIncidenciaFormModel);
            RequestContext.getCurrentInstance()
                    .execute("PF('dglNuevaReglaIncidencia').hide()");
            llenarReglasIncidencias();
        } catch (RESTClientException e) {
            JSFUtils.errorMessage("Error", e.getMessage());
            e.printStackTrace();
        }

    }

    private void llenarReglasIncidencias() throws RESTClientException {

        listadoReglaIncidenciaViewModel = reglaIncidenciaClienteRest
                .listadoReglasIncidencia(idIncidencia,
                        tipoContratacionSeleccionada);

    }

    private void llenarTiposContratacion() {

        List<TipoContratacionEntity> listadoTipoContratacionEntity = tipoContratacionRepository
                .consultarTodos();
        listadoTiposContrataciones = new ArrayList<>();
        SelectItem selectItemTipoContratacion = new SelectItem();
        for (TipoContratacionEntity tipoContratacionEntity : listadoTipoContratacionEntity) {

            selectItemTipoContratacion = new SelectItem();

            selectItemTipoContratacion
                    .setLabel(tipoContratacionEntity.getTipoContratacion());
            selectItemTipoContratacion.setValue(tipoContratacionEntity.getId());
            listadoTiposContrataciones.add(selectItemTipoContratacion);

        }

    }

    public List<SelectItem> getListadoTiposContrataciones() {
        return listadoTiposContrataciones;
    }

    public void setListadoTiposContrataciones(
            List<SelectItem> listadoTiposContrataciones) {
        this.listadoTiposContrataciones = listadoTiposContrataciones;
    }

    public Integer getTipoContratacionSeleccionada() {
        return tipoContratacionSeleccionada;
    }

    public void setTipoContratacionSeleccionada(
            Integer tipoContratacionSeleccionada) {
        this.tipoContratacionSeleccionada = tipoContratacionSeleccionada;
    }

    public List<ReglaIncidenciaViewModel> getListadoReglaIncidenciaViewModel() {
        return listadoReglaIncidenciaViewModel;
    }

    public void setListadoReglaIncidenciaViewModel(
            List<ReglaIncidenciaViewModel> listadoReglaIncidenciaViewModel) {
        this.listadoReglaIncidenciaViewModel = listadoReglaIncidenciaViewModel;
    }

    public IncidenciaModelView getIncidenciaViewModel() {
        return incidenciaViewModel;
    }

    public void setIncidenciaViewModel(
            IncidenciaModelView incidenciaViewModel) {
        this.incidenciaViewModel = incidenciaViewModel;
    }

    public ReglaIncidenciaFormModel getReglaIncidenciaFormModel() {
        return reglaIncidenciaFormModel;
    }

    public void setReglaIncidenciaFormModel(
            ReglaIncidenciaFormModel reglaIncidenciaFormModel) {
        this.reglaIncidenciaFormModel = reglaIncidenciaFormModel;
    }

}
