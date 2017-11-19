
package mx.gob.saludtlax.rh.ca.biometrico;

import java.io.Serializable;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import mx.gob.saludtlax.rh.configuracion.serviciosweb.ServicioWebException;
import mx.gob.saludtlax.rh.configuracion.serviciosweb.ServiciosWebEJB;
import mx.gob.saludtlax.rh.excepciones.RESTClientException;
import mx.gob.saludtlax.rh.persistencia.ServiciosRSEntity;
import mx.gob.saludtlax.rh.util.ServicioWebEnum;

@ManagedBean(name = "biometricoEditarController")
@ViewScoped
public class BiometricoControllerEditar implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 8013287909881216876L;

    @Inject
    BiometricoClientRest biometricoClientRest;
    @Inject
    ServiciosWebEJB servicioWebEJB;

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

        } catch (ServicioWebException e1) {
            FacesMessage facesMessage = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, e1.getMessage(),
                    e1.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }

        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> params = context.getExternalContext()
                .getRequestParameterMap();
        String id = params.get("id");

        if (id != null) {

            Integer idBiometrico = new Integer(id);
            try {
                biometricoFormModel = biometricoClientRest
                        .buscarBiometrico(idBiometrico);
            } catch (RESTClientException e) {
                FacesMessage facesMessage = new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, e.getMessage(),
                        e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null,
                        facesMessage);
            }

        }

    }

    public String guardar() {
        try {
            BiometricoClientRestResponse biometricoClienteRestResponse = biometricoClientRest
                    .actualizarBiometrico(biometricoFormModel);

            if (!biometricoClienteRestResponse.isExitoso()) {
                FacesMessage facesMessage = new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, "Guardar",
                        biometricoClienteRestResponse.getMensaje());
                FacesContext.getCurrentInstance().addMessage(null,
                        facesMessage);
            }
            return "index.xhml?faces-redirect=true";

        } catch (RESTClientException e) {
            FacesMessage facesMessage = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, e.getMessage(),
                    e.getMessage());
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

}
