
package mx.gob.saludtlax.rh.ca.cliente.biometrico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.excepciones.RESTClientException;

@ManagedBean(name = "clienteBiometricos")
@ViewScoped
public class ClientesBiometricosController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1635826164215598597L;

    @Inject
    ClienteBiometricoREST clienteBiometricoREST;

    private List<ClienteBiometricoFormModel> listaClientes = new ArrayList<>();

    @PostConstruct
    public void init() throws RESTClientException {
        listaClientes = clienteBiometricoREST.listadoClientesBiometricos();

        for (ClienteBiometricoFormModel ite : listaClientes) {
            System.out.println(ite.getDireccionIP() + " -- " + ite.getUnidad());
        }

    }

    public List<ClienteBiometricoFormModel> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(
            List<ClienteBiometricoFormModel> listaClientes) {
        this.listaClientes = listaClientes;
    }

}
