
package mx.gob.saludtlax.rh.ca.cliente.biometrico;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.ca.biometrico.BiometricoClientRest;
import mx.gob.saludtlax.rh.ca.biometrico.BiometricoClientRestResponse;
import mx.gob.saludtlax.rh.ca.biometrico.BiometricoFormModel;
import mx.gob.saludtlax.rh.excepciones.RESTClientException;

@Stateless
public class ClienteBiometricoServices {

    @Inject
    BiometricoClientRest biometricoClientRest;

    public BiometricoClientRestResponse guardar(BiometricoFormModel biometricoFormModel) throws RESTClientException {
        return biometricoClientRest.guardarBiometrico(biometricoFormModel);

    }
}
