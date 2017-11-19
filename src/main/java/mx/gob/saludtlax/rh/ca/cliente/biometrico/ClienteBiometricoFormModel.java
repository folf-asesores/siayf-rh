
package mx.gob.saludtlax.rh.ca.cliente.biometrico;

import java.io.Serializable;

public class ClienteBiometricoFormModel implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 2802944317053658936L;

    private int idClienteBiometricoFormModel;

    private String direccionIP;

    private int puerto;

    private String unidad;

    public int getIdClienteBiometricoFormModel() {
        return idClienteBiometricoFormModel;
    }

    public void setIdClienteBiometricoFormModel(int idClienteBiometricoFormModel) {
        this.idClienteBiometricoFormModel = idClienteBiometricoFormModel;
    }

    public String getDireccionIP() {
        return direccionIP;
    }

    public void setDireccionIP(String direccionIP) {
        this.direccionIP = direccionIP;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

}
