
package mx.gob.saludtlax.rh.ca.biometrico;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import mx.gob.saludtlax.rh.ca.cliente.biometrico.ClienteBiometricoFormModel;
import mx.gob.saludtlax.rh.ca.cliente.biometrico.ClienteBiometricoREST;
import mx.gob.saludtlax.rh.configuracion.serviciosweb.ServicioWebException;
import mx.gob.saludtlax.rh.configuracion.serviciosweb.ServiciosWebEJB;
import mx.gob.saludtlax.rh.excepciones.RESTClientException;
import mx.gob.saludtlax.rh.persistencia.ServiciosRSEntity;
import mx.gob.saludtlax.rh.util.ServicioWebEnum;

@ManagedBean(name = "biometricoNuevoController")
@ViewScoped
public class BiometricoControllerNuevo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4568331639024118035L;

    @Inject
    BiometricoClientRest biometricoClientRest;
    @Inject
    ClienteBiometricoREST clienteBiometricoREST;

    @Inject
    ServiciosWebEJB servicioWebEJB;

    List<ClienteBiometricoFormModel> listadoBiometricos;

    BiometricoFormModel biometricoFormModel = new BiometricoFormModel();

    public void init() {

        try {
            ServiciosRSEntity servicioRSEntity = servicioWebEJB
                    .getServicioActivo(ServicioWebEnum.RELOJ_CHECADOR);
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

            listadoBiometricos = clienteBiometricoREST
                    .listadoClientesBiometricos();

        } catch (ServicioWebException e1) {
            FacesMessage facesMessage = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, e1.getMessage(),
                    e1.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        } catch (RESTClientException e) {
            FacesMessage facesMessage = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, e.getMessage(),
                    e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }

    }

    public String guardarBiometrico() {

        try {

            ClienteBiometricoFormModel clienteBiometricoFormModel = clienteBiometricoREST
                    .buscarClienteBiometrico(
                            biometricoFormModel.getIdClienteBiometrico());

            String urlServicio = "http://"
                    + clienteBiometricoFormModel.getDireccionIP();

            if (clienteBiometricoFormModel.getPuerto() != 0) {
                urlServicio = urlServicio + ":"
                        + clienteBiometricoFormModel.getPuerto();
            }
            urlServicio = urlServicio + "/ca/api";

            BiometricoClientRestResponse biometricoClientRestResponse = biometricoClientRest
                    .guardarBiometrico(biometricoFormModel, urlServicio);
            if (!biometricoClientRestResponse.isExitoso()) {
                FacesMessage facesMessage = new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, "Guardar",
                        biometricoClientRestResponse.getMensaje());
                FacesContext.getCurrentInstance().addMessage(null,
                        facesMessage);
                return "";
            }
            return "index.xhml?faces-redirect=true";
        } catch (RESTClientException e) {
            FacesMessage facesMessage = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Guardar", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
        return "";
    }

    public BiometricoFormModel getBiometricoFormModel() {
        return biometricoFormModel;
    }

    public void setBiometricoFormModel(
            BiometricoFormModel biometricoFormModel) {
        this.biometricoFormModel = biometricoFormModel;
    }

    public List<ClienteBiometricoFormModel> getListadoBiometricos() {
        return listadoBiometricos;
    }

    public void setListadoBiometricos(
            List<ClienteBiometricoFormModel> listadoBiometricos) {
        this.listadoBiometricos = listadoBiometricos;
    }

}
