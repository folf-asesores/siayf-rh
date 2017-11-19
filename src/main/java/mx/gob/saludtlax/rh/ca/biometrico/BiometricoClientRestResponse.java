
package mx.gob.saludtlax.rh.ca.biometrico;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class BiometricoClientRestResponse implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2882324330125144332L;
    @SerializedName("Exitoso")
    private boolean exitoso;

    @SerializedName("Mensaje")
    private String mensaje;

    public boolean isExitoso() {
        return exitoso;
    }

    public void setExitoso(boolean exitoso) {
        this.exitoso = exitoso;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
