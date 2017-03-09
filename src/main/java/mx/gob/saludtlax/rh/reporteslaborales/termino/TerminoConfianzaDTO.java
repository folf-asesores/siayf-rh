package mx.gob.saludtlax.rh.reporteslaborales.termino;

import java.io.Serializable;

public class TerminoConfianzaDTO extends TerminoDTO implements Serializable {

    private static final long serialVersionUID = 580089956254366158L;

    private String fechaTermino;
    private String funcion;
    private String secretarioSalud;

    public String getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(String fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public String getSecretarioSalud() {
        return secretarioSalud;
    }

    public void setSecretarioSalud(String secretarioSalud) {
        this.secretarioSalud = secretarioSalud;
    }

}
