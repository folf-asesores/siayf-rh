
package mx.gob.saludtlax.rh.retenciones;

import java.io.Serializable;

public class DatosCertificado implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4241440470510394709L;
    public String informacionBase64;
    public String numeroCertficiado;

    public String getInformacionBase64() {
        return informacionBase64;
    }

    public void setInformacionBase64(String informacionBase64) {
        this.informacionBase64 = informacionBase64;
    }

    public String getNumeroCertficiado() {
        return numeroCertficiado;
    }

    public void setNumeroCertficiado(String numeroCertficiado) {
        this.numeroCertficiado = numeroCertficiado;
    }
}
