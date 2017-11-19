
package mx.gob.saludtlax.rh.ca.biometrico;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class AsignarEmpleadoRegistroBiometricoForm implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6442870631662492620L;

    @SerializedName("IdEmpleado")
    private Integer idEmpleado;

    @SerializedName("IdRegistroBiometrico")
    private Integer idRegistroBiometrico;

    @SerializedName("IdBiometrico")
    private Integer idBiometrico;

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Integer getIdRegistroBiometrico() {
        return idRegistroBiometrico;
    }

    public void setIdRegistroBiometrico(Integer idRegistroBiometrico) {
        this.idRegistroBiometrico = idRegistroBiometrico;
    }

    public Integer getIdBiometrico() {
        return idBiometrico;
    }

    public void setIdBiometrico(Integer idBiometrico) {
        this.idBiometrico = idBiometrico;
    }

}
