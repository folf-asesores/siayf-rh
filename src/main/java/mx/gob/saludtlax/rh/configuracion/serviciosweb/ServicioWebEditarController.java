
package mx.gob.saludtlax.rh.configuracion.serviciosweb;

import java.io.Serializable;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.persistencia.ServiciosRSEntity;
import mx.gob.saludtlax.rh.util.ServicioWebEnum;

@ManagedBean(name = "servicioWebEditarController")
@ViewScoped
public class ServicioWebEditarController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -5208958454726821102L;

    @Inject
    ServiciosWebEJB serviciosWebEJB;

    private ServiciosRSEntity servicioRSEntity = new ServiciosRSEntity();

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> params = context.getExternalContext()
                .getRequestParameterMap();
        String id = params.get("id");

        if (!id.equals(null)) {
            servicioRSEntity = serviciosWebEJB
                    .getServicioRSEntity(new Integer(id));
        }

    }

    public ServiciosRSEntity getServicioRSEntity() {
        return servicioRSEntity;
    }

    public void setServicioRSEntity(ServiciosRSEntity servicioRSEntity) {
        this.servicioRSEntity = servicioRSEntity;
    }

    public String guardarServicio() {
        try {
            serviciosWebEJB.actualizarInformacionServicio(servicioRSEntity);
        } catch (ServicioWebException e) {

            FacesMessage facesMessage = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, e.getMessage(),
                    e.getMessage());
            FacesContext.getCurrentInstance().addMessage("info", facesMessage);
            return "";
        }
        return "index.xhtml?faces-redirect=true";

    }

    public ServicioWebEnum[] getServicios() {
        return ServicioWebEnum.values();
    }

    public void validarFormulario(FacesContext context, UIComponent component,
            Object value) {

        String nombreComponete = component.getId();
        switch (nombreComponete) {
            case "url":
                Pattern pat = Pattern.compile(
                        "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
                String url = (String) value;
                if (url == null) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "El campo url es obligatorio");
                    context.addMessage(component.getId(), facesMessage);
                    throw new ValidatorException(facesMessage);

                }
                Matcher mat = pat.matcher(url);
                if (!mat.matches()) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Ingrese una url valida http:// o https://.");
                    context.addMessage(component.getId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }
                break;
        }

    }

}
