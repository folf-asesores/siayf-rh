
package mx.gob.saludtlax.rh.ca.empleado;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;

import mx.gob.saludtlax.rh.ca.incidencia.IncidenciaClienteRest;
import mx.gob.saludtlax.rh.ca.incidencia.IncidenciaModelView;
import mx.gob.saludtlax.rh.configuracion.serviciosweb.ServicioWebException;
import mx.gob.saludtlax.rh.configuracion.serviciosweb.ServiciosWebEJB;
import mx.gob.saludtlax.rh.empleados.administracion.Empleado;
import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;
import mx.gob.saludtlax.rh.excepciones.RESTClientException;
import mx.gob.saludtlax.rh.excepciones.ValidacionIncidenciaException;
import mx.gob.saludtlax.rh.persistencia.ServiciosRSEntity;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.ListadoMensajesSistema;
import mx.gob.saludtlax.rh.util.ServicioWebEnum;

@ManagedBean(name = "agregarIncidenciaEmpleadoController")
@ViewScoped
public class AgregarIncidencaEmpleadoController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6928922807105556010L;

    @Inject
    Empleado empleadoService;

    @Inject
    IncidenciaClienteRest incidenciaClienteRest;

    @Inject
    EmpleadoClientRest empleadoClienteRest;

    @Inject
    ServiciosWebEJB serviocWebEJB;

    IncidenciaEmpleadoFormModel incidenciaEmpleadoFormModel;

    InfoEmpleadoDTO empleadoDTO;

    String mensajeErrorValidacion;

    public void init() {

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
        }
        incidenciaEmpleadoFormModel = new IncidenciaEmpleadoFormModel();

    }

    public List<InfoEmpleadoDTO> buscarEmpleadoAutoComplete(String query) {

        List<InfoEmpleadoDTO> listadoEmpleadoDTO = null;

        if (query == "") {
            query = ".";
        }

        if (query.length() > 4) {
            listadoEmpleadoDTO = empleadoService.consultaPorCriterio(query);
        }

        return listadoEmpleadoDTO;

    }

    public List<IncidenciaModelView> incidenciasAutoacompletar(String query) {

        List<IncidenciaModelView> listadoIncidencias = null;

        try {
            listadoIncidencias = incidenciaClienteRest.buscarIncidenciaPorDescripcion(query);
        } catch (RESTClientException e) {
            JSFUtils.errorMessage(ListadoMensajesSistema.E002.getMensaje(), e.getMessage());
            e.printStackTrace();
        }
        return listadoIncidencias;
    }

    public String guardar() {

        try {
            empleadoClienteRest.agregarNuevaIncidenciaEmpleado(incidenciaEmpleadoFormModel);
            return "index.xhtml?faces-redirect=true&exito=1 ";
        } catch (RESTClientException e) {
            JSFUtils.errorMessage(e.getMessage(), e.getMessage());
        } catch (ValidacionIncidenciaException e) {
            mensajeErrorValidacion = e.getMessage();
            RequestContext.getCurrentInstance().execute("PF('dlgAdevertencia').show()");

        }
        return "";

    }

    public String guardarSinValidacion() {
        RequestContext.getCurrentInstance().execute("PF('dlgAdevertencia').hide()");
        incidenciaEmpleadoFormModel.setIgnorarValidacionReglaIncidencia(true);
        try {
            empleadoClienteRest.agregarNuevaIncidenciaEmpleado(incidenciaEmpleadoFormModel);
            return "index.xhtml?faces-redirect=true&exito=1 ";
        } catch (RESTClientException e) {
            JSFUtils.errorMessage(e.getMessage(), e.getMessage());
        } catch (ValidacionIncidenciaException e) {
            mensajeErrorValidacion = e.getMessage();
            RequestContext.getCurrentInstance().execute("PF('dlgAdevertencia').show()");

        }
        return "";
    }

    public IncidenciaEmpleadoFormModel getIncidenciaEmpleadoFormModel() {
        return incidenciaEmpleadoFormModel;
    }

    public void setIncidenciaEmpleadoFormModel(IncidenciaEmpleadoFormModel incidenciaEmpleadoFormModel) {
        this.incidenciaEmpleadoFormModel = incidenciaEmpleadoFormModel;
    }

    public InfoEmpleadoDTO getEmpleadoDTO() {
        return empleadoDTO;
    }

    public void setEmpleadoDTO(InfoEmpleadoDTO empleadoDTO) {
        this.empleadoDTO = empleadoDTO;
    }

    public String getMensajeErrorValidacion() {
        return mensajeErrorValidacion;
    }

    public void setMensajeErrorValidacion(String mensajeErrorValidacion) {
        this.mensajeErrorValidacion = mensajeErrorValidacion;
    }

}
