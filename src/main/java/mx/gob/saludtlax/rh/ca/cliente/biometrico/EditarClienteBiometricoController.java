
package mx.gob.saludtlax.rh.ca.cliente.biometrico;

import java.io.Serializable;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.excepciones.RESTClientException;

@ManagedBean(name = "editarClienteBiometrico")
@ViewScoped
public class EditarClienteBiometricoController implements Serializable {

    /**
    	 *
    	 */
    private static final long serialVersionUID = 5758037650862997624L;

    @Inject
    ClienteBiometricoREST clienteBiometricoREST;

    private ClienteBiometricoFormModel clienteBiometricoFormModel = new ClienteBiometricoFormModel();

    //@PostConstruct
    public void init() {

        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> params = context.getExternalContext()
                .getRequestParameterMap();
        String id = params.get("id");

        if (id != null) {

            Integer idBiometrico = new Integer(id);
            try {
                clienteBiometricoFormModel = clienteBiometricoREST
                        .buscarClienteBiometrico(idBiometrico);
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
            clienteBiometricoREST
                    .actualizarBiometrico(clienteBiometricoFormModel);

            /*
             * if (!biometricoClienteRestResponse.isExitoso()) {
             * FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Guardar",
             * biometricoClienteRestResponse.getMensaje());
             * FacesContext.getCurrentInstance().addMessage(null, facesMessage);
             * }
             */
            return "clientesBiometricos.xhml?faces-redirect=true";

        } catch (RESTClientException e) {
            FacesMessage facesMessage = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, e.getMessage(),
                    e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }

        return "";

    }

    public ClienteBiometricoFormModel getClienteBiometricoFormModel() {
        return clienteBiometricoFormModel;
    }

    public void setClienteBiometricoFormModel(
            ClienteBiometricoFormModel clienteBiometricoFormModel) {
        this.clienteBiometricoFormModel = clienteBiometricoFormModel;
    }

}
