
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
import org.primefaces.event.SelectEvent;

import mx.gob.saludtlax.rh.ca.jornada.JornadaClienteRest;
import mx.gob.saludtlax.rh.ca.jornada.JornadaFormModel;
import mx.gob.saludtlax.rh.configuracion.serviciosweb.ServicioWebException;
import mx.gob.saludtlax.rh.configuracion.serviciosweb.ServiciosWebEJB;
import mx.gob.saludtlax.rh.empleados.administracion.Empleado;
import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;
import mx.gob.saludtlax.rh.excepciones.RESTClientException;
import mx.gob.saludtlax.rh.persistencia.ServiciosRSEntity;
import mx.gob.saludtlax.rh.util.ServicioWebEnum;

@ManagedBean(name = "administrarHorarioEmpleadoController")
@ViewScoped
public class AdministrarHorarioEmpleadoController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -745081723601941023L;

    @Inject
    Empleado empleadoService;

    @Inject
    EmpleadoClientRest empleadoClienteRest;

    @Inject
    ServiciosWebEJB serviocWebEJB;

    @Inject
    JornadaClienteRest jornadaClienteRest;

    InfoEmpleadoDTO empleadoDTO;

    HorarioEmpleadoFormModel horarioEmpleadoFormModel;

    List<HorarioEmpleadoViewModel> listadoHorarioEmpleadoViewModel;

    List<JornadaFormModel> listadoJornada;

    public void init() {

        if (!FacesContext.getCurrentInstance().isPostback()) {
            horarioEmpleadoFormModel = new HorarioEmpleadoFormModel();

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
                listadoJornada = jornadaClienteRest.listadoJornada();

            } catch (ServicioWebException e1) {
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, e1.getMessage(), e1.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            } catch (RESTClientException e) {
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            }
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

    public void onItemSelect(SelectEvent event) {
        horarioEmpleadoFormModel.setIdEmpleado(new Integer(event.getObject().toString()));

        try {
            llenarTablaJornada();
        } catch (RESTClientException e) {

        }
    }

    public void nuevaJornadaEmpleado() {

        if (horarioEmpleadoFormModel.getIdEmpleado() == null) {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Agregar Jornada", "Debes Selecionar primero a un empleado");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            return;
        }
        if (horarioEmpleadoFormModel.getIdEmpleado() != 0) {

            RequestContext.getCurrentInstance().execute("PF('dlgNuevoHorario').show()");
        } else {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Agregar Jornada", "Debes Selecionar primero a un empleado");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }

    }

    private void llenarTablaJornada() throws RESTClientException {

        listadoHorarioEmpleadoViewModel = empleadoClienteRest.buscarHorarioPorEmpleado(horarioEmpleadoFormModel.getIdEmpleado());

    }

    public void eliminarJornada(HorarioEmpleadoViewModel horarioEmpleado) {
        try {

            empleadoClienteRest.eliminarJornadaEmpleado(horarioEmpleado.getIdHorarioEmpleado());
            llenarTablaJornada();
        } catch (RESTClientException e) {

            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Agregar Jornada", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }

    }

    public void guardarJordana() {

        try {
            empleadoClienteRest.agregarHorarioEmpleado(horarioEmpleadoFormModel);

            llenarTablaJornada();

            RequestContext.getCurrentInstance().execute("PF('dlgNuevoHorario').hide()");
        } catch (RESTClientException e) {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Agregar Jornada", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }

    public InfoEmpleadoDTO getEmpleadoDTO() {
        return empleadoDTO;
    }

    public void setEmpleadoDTO(InfoEmpleadoDTO empleadoDTO) {
        this.empleadoDTO = empleadoDTO;
    }

    public HorarioEmpleadoFormModel getHorarioEmpleadoFormModel() {
        return horarioEmpleadoFormModel;
    }

    public void setHorarioEmpleadoFormModel(HorarioEmpleadoFormModel horarioEmpleadoFormModel) {
        this.horarioEmpleadoFormModel = horarioEmpleadoFormModel;
    }

    public List<HorarioEmpleadoViewModel> getListadoHorarioEmpleadoViewModel() {
        return listadoHorarioEmpleadoViewModel;
    }

    public void setListadoHorarioEmpleadoViewModel(List<HorarioEmpleadoViewModel> listadoHorarioEmpleadoViewModel) {
        this.listadoHorarioEmpleadoViewModel = listadoHorarioEmpleadoViewModel;
    }

    public List<JornadaFormModel> getListadoJornada() {
        return listadoJornada;
    }

    public void setListadoJornada(List<JornadaFormModel> listadoJornada) {
        this.listadoJornada = listadoJornada;
    }

}
