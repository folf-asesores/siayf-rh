
package mx.gob.saludtlax.rh.ca.empleado;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import mx.gob.saludtlax.rh.configuracion.serviciosweb.ServicioWebException;
import mx.gob.saludtlax.rh.configuracion.serviciosweb.ServiciosWebEJB;
import mx.gob.saludtlax.rh.empleados.administracion.Empleado;
import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;
import mx.gob.saludtlax.rh.excepciones.RESTClientException;
import mx.gob.saludtlax.rh.persistencia.ServiciosRSEntity;
import mx.gob.saludtlax.rh.util.ServicioWebEnum;

@ManagedBean(name = "indexIncidenciaEmpleadoController")
@ViewScoped
public class IndexIncidenciaEmpleadoController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 280980765909030913L;

    @Inject
    ServiciosWebEJB serviocWebEJB;

    @Inject
    Empleado empleadoService;

    @Inject
    EmpleadoClientRest empleadoClienteRest;

    InfoEmpleadoDTO empleadoDTO;

    ConsultarIncidenciasEmpleadoModel model = new ConsultarIncidenciasEmpleadoModel();

    private List<IncidenciaEmpleadoView> listadoIncidenciaEmpleado;

    private boolean mostrarDetalle = false;

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

        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> params = context.getExternalContext().getRequestParameterMap();
        String id = params.get("exito");

        if (id != null) {

            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Agregar", "Se agrego la incidencia con exito");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);

        }

    }

    public void eliminarIncidencia(IncidenciaEmpleadoView incidenciaViewModel) {

        try {
            empleadoClienteRest.elminarIncidenciaEmpleado(incidenciaViewModel.getIdIncidenciaEmpleado());
            listadoIncidenciaEmpleado = empleadoClienteRest.buscarIncidenciaEmpleado(model);
        } catch (RESTClientException e) {

            e.printStackTrace();

            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminar", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
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

    public void buscarIncidenciaEmpleado() {

        try {
            listadoIncidenciaEmpleado = empleadoClienteRest.buscarIncidenciaEmpleado(model);
            mostrarDetalle = true;
        } catch (RESTClientException e) {
            mostrarDetalle = false;
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Buscar", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }

    public boolean isMostrarDetalle() {
        return mostrarDetalle;
    }

    public void setMostrarDetalle(boolean mostrarDetalle) {
        this.mostrarDetalle = mostrarDetalle;
    }

    public List<IncidenciaEmpleadoView> getListadoIncidenciaEmpleado() {
        return listadoIncidenciaEmpleado;
    }

    public void setListadoIncidenciaEmpleado(List<IncidenciaEmpleadoView> listadoIncidenciaEmpleado) {
        this.listadoIncidenciaEmpleado = listadoIncidenciaEmpleado;
    }

    public InfoEmpleadoDTO getEmpleadoDTO() {
        return empleadoDTO;
    }

    public void setEmpleadoDTO(InfoEmpleadoDTO empleadoDTO) {
        this.empleadoDTO = empleadoDTO;
    }

    public ConsultarIncidenciasEmpleadoModel getModel() {
        return model;
    }

    public void setModel(ConsultarIncidenciasEmpleadoModel model) {
        this.model = model;
    }

}
