
package mx.gob.saludtlax.rh.ca.cliente.biometrico;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.excepciones.RESTClientException;

@ManagedBean(name = "nuevoCliente")
@ViewScoped
public class NuevoClienteBiometricoController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 8214348519199306365L;

    @Inject
    ClienteBiometricoREST clienteBiometricoREST;

    private ClienteBiometricoFormModel clienteBiometricoFormModel = new ClienteBiometricoFormModel();

    // @PostConstruct
    public void init() throws RESTClientException {
        List<ClienteBiometricoFormModel> listadecliente = clienteBiometricoREST.listadoClientesBiometricos();

        for (ClienteBiometricoFormModel ite : listadecliente) {
            System.out.println(ite.getDireccionIP() + " -- " + ite.getUnidad());
        }

    }

    public String guardarClienteBiometrico() {

        try {
            System.out.println("llama al metodo para guardar ");
            clienteBiometricoREST.guardarBiometrico(clienteBiometricoFormModel);

            return "clientesBiometricos.xhml?faces-redirect=true";

        } catch (RESTClientException e) {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Guardar", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            return "";
        }

    }

    public ClienteBiometricoFormModel getClienteBiometricoFormModel() {
        return clienteBiometricoFormModel;
    }

    public void setClienteBiometricoFormModel(ClienteBiometricoFormModel clienteBiometricoFormModel) {
        this.clienteBiometricoFormModel = clienteBiometricoFormModel;
    }

}
