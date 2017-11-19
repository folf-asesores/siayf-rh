
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

@ManagedBean(name = "indexPeriodoEsperaIncidenciaController")
@ViewScoped
public class IndexPeriodoEsperaIncidenciaController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5664295416470706630L;
    @Inject
    private PeriodoEsperaClienteRest periodoEsperaClienteRest;
    @Inject
    ServiciosWebEJB serviocWebEJB;
    @Inject
    TipoContratacionRepository tipoContratacionRepository;
    @Inject
    IncidenciaClienteRest incidenciaClientRest;

    private Integer idIncidencia;
    private Integer idTipoContatacion = 0;
    private List<SelectItem> listadoTiposContrataciones;
    private IncidenciaModelView incidenciaViewModel;
    private List<PeriodoEsperaViewModel> listadoPeriodoEsperaViewModel;
    private PeriodoEsperaFormModel periodoEsperaFormModel = new PeriodoEsperaFormModel();;

    public void init() {

        if (FacesContext.getCurrentInstance().isPostback()) {
            return;
        }

        try {
            ServiciosRSEntity servicioRSEntity = serviocWebEJB.getServicioActivo(ServicioWebEnum.CONTROL_ASISTENCIA_RS);
            if (!servicioRSEntity.isProduccion()) {
                HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                String url = req.getContextPath().toString();
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, "Servicio en Modo Prueba",
                        "El servcio configurado como activo para este modulo es de pruebas consulte la <a href='" + url
                                + "/contenido/configuracion/serviciosweb/index.xhtml'>configuracion</a>");
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);

            }

        } catch (ServicioWebException e1) {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, e1.getMessage(), e1.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            return;
        }

        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> params = context.getExternalContext().getRequestParameterMap();
        String id = params.get("i");

        if (id == null) {
            HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String url = req.getContextPath().toString();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error Acceso",
                    "Ocurrio un error al intentar buscar la incidencia seleccionada por favor regresa al  <a href='" + url
                            + "/contenido/controlasistencia/catalogo/incidencia/index.xhtml'>catalogo de incidencias</a>");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            return;
        }
        idIncidencia = new Integer(id);

        llenarTiposContratacion();
        try {
            llenarPeriodosEspera();

            incidenciaViewModel = incidenciaClientRest.buscarIncidenciaPorId(idIncidencia);
        } catch (RESTClientException e) {
            e.printStackTrace();
            JSFUtils.errorMessage(ListadoMensajesSistema.E002.getMensaje(), e.getMessage());
        }

    }

    public void filtrarPorTipoContratacion() {
        try {
            llenarPeriodosEspera();
        } catch (RESTClientException e) {
            e.printStackTrace();
            JSFUtils.errorMessage(ListadoMensajesSistema.E002.getMensaje(), e.getMessage());
        }

    }

    public void nuevoPeriodoEsperaIncidencia() {

        periodoEsperaFormModel = new PeriodoEsperaFormModel();
        RequestContext.getCurrentInstance().execute("PF('dglNuevoPeriodoEsperaIncidencia').show()");

    }

    public List<IncidenciaModelView> incidenciasAutoacompletar(String query) {

        List<IncidenciaModelView> listadoIncidencias = null;

        try {
            listadoIncidencias = incidenciaClientRest.buscarIncidenciaPorDescripcion(query);
        } catch (RESTClientException e) {
            JSFUtils.errorMessage(ListadoMensajesSistema.E002.getMensaje(), e.getMessage());
            e.printStackTrace();
        }
        return listadoIncidencias;
    }

    public void guardarPeriodoEsperaIncidencia() {

        try {
            periodoEsperaFormModel.setIdIncidenciaOrigen(idIncidencia);
            periodoEsperaFormModel.setDiasNaturales(true);
            periodoEsperaClienteRest.crearNuevoPeriodoEsperaIncidencia(periodoEsperaFormModel);
            llenarPeriodosEspera();
            RequestContext.getCurrentInstance().execute("PF('dglNuevoPeriodoEsperaIncidencia').hide()");
        } catch (RESTClientException e) {
            e.printStackTrace();
            JSFUtils.errorMessage(ListadoMensajesSistema.E002.getMensaje(), e.getMessage());
        }
    }

    public void eliminarPeriodoEsperaIncidencia(PeriodoEsperaViewModel periodoEsperaViewModel) {

        try {
            periodoEsperaClienteRest.elminarPeriodoEsperaIncidencia(periodoEsperaViewModel.getIdPeriodoEsperaIncidencias());
            llenarPeriodosEspera();
        } catch (RESTClientException e) {

            e.printStackTrace();
            JSFUtils.errorMessage(ListadoMensajesSistema.E002.getMensaje(), e.getMessage());
        }

    }

    private void llenarPeriodosEspera() throws RESTClientException {

        listadoPeriodoEsperaViewModel = periodoEsperaClienteRest.listadoPeriodoEsperaIncidencia(idIncidencia, idTipoContatacion);

    }

    private void llenarTiposContratacion() {

        List<TipoContratacionEntity> listadoTipoContratacionEntity = tipoContratacionRepository.consultarTodos();
        listadoTiposContrataciones = new ArrayList<>();
        SelectItem selectItemTipoContratacion = new SelectItem();
        for (TipoContratacionEntity tipoContratacionEntity : listadoTipoContratacionEntity) {

            selectItemTipoContratacion = new SelectItem();

            selectItemTipoContratacion.setLabel(tipoContratacionEntity.getTipoContratacion());
            selectItemTipoContratacion.setValue(tipoContratacionEntity.getId());
            listadoTiposContrataciones.add(selectItemTipoContratacion);

        }

    }

    public Integer getIdIncidencia() {
        return idIncidencia;
    }

    public void setIdIncidencia(Integer idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    public Integer getIdTipoContatacion() {
        return idTipoContatacion;
    }

    public void setIdTipoContatacion(Integer idTipoContatacion) {
        this.idTipoContatacion = idTipoContatacion;
    }

    public List<SelectItem> getListadoTiposContrataciones() {
        return listadoTiposContrataciones;
    }

    public void setListadoTiposContrataciones(List<SelectItem> listadoTiposContrataciones) {
        this.listadoTiposContrataciones = listadoTiposContrataciones;
    }

    public IncidenciaModelView getIncidenciaViewModel() {
        return incidenciaViewModel;
    }

    public void setIncidenciaViewModel(IncidenciaModelView incidenciaViewModel) {
        this.incidenciaViewModel = incidenciaViewModel;
    }

    public List<PeriodoEsperaViewModel> getListadoPeriodoEsperaViewModel() {
        return listadoPeriodoEsperaViewModel;
    }

    public void setListadoPeriodoEsperaViewModel(List<PeriodoEsperaViewModel> listadoPeriodoEsperaViewModel) {
        this.listadoPeriodoEsperaViewModel = listadoPeriodoEsperaViewModel;
    }

    public PeriodoEsperaFormModel getPeriodoEsperaFormModel() {
        return periodoEsperaFormModel;
    }

    public void setPeriodoEsperaFormModel(PeriodoEsperaFormModel periodoEsperaFormModel) {
        this.periodoEsperaFormModel = periodoEsperaFormModel;
    }

}
