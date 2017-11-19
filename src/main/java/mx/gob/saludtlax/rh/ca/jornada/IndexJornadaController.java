
package mx.gob.saludtlax.rh.ca.jornada;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import mx.gob.saludtlax.rh.ca.incidencia.IncidenciaClienteRest;
import mx.gob.saludtlax.rh.configuracion.serviciosweb.ServicioWebException;
import mx.gob.saludtlax.rh.configuracion.serviciosweb.ServiciosWebEJB;
import mx.gob.saludtlax.rh.excepciones.RESTClientException;
import mx.gob.saludtlax.rh.persistencia.ServiciosRSEntity;
import mx.gob.saludtlax.rh.util.ServicioWebEnum;

@ManagedBean(name = "indexJornadaController")
@ViewScoped
public class IndexJornadaController {

    private List<JornadaFormModel> listadoFormModel;

    private List<JornadaFormModel> listadoFormModelFiltrado;

    @Inject
    IncidenciaClienteRest incidenciaClienteRest;

    @Inject
    JornadaClienteRest jornadaClientRest;

    @Inject
    ServiciosWebEJB serviocWebEJB;

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

        try {
            listadoFormModel = jornadaClientRest.listadoJornada();
        } catch (RESTClientException e) {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }

    }

    public List<JornadaFormModel> getListadoFormModel() {
        return listadoFormModel;
    }

    public void setListadoFormModel(List<JornadaFormModel> listadoFormModel) {
        this.listadoFormModel = listadoFormModel;
    }

    public List<JornadaFormModel> getListadoFormModelFiltrado() {
        return listadoFormModelFiltrado;
    }

    public void setListadoFormModelFiltrado(List<JornadaFormModel> listadoFormModelFiltrado) {
        this.listadoFormModelFiltrado = listadoFormModelFiltrado;
    }

    public String editar(Integer id) {

        return "editar.xhtml?faces-redirect=true&includeViewParams=true&id=" + id;
    }

}
