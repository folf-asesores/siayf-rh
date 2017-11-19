
package mx.gob.saludtlax.rh.util;

import java.io.Serializable;

public class MensajeSistema implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -337083138289795838L;
    private String mensaje;
    private String numero;

    public MensajeSistema(String numero, String mensaje) {
        this.mensaje = mensaje;
        this.numero = numero;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    };

}
