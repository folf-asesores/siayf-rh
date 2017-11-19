
package mx.gob.saludtlax.rh.reporteslaborales.termino;

import java.io.Serializable;

public class TerminoConfianzaDTO extends TerminoDTO implements Serializable {

    private static final long serialVersionUID = 580089956254366158L;

    private String fechaTermino;
    private String funcion;
    private String secretarioSalud;

    @Override
    public String getFechaTermino() {
        return fechaTermino;
    }

    @Override
    public void setFechaTermino(String fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    @Override
    public String getFuncion() {
        return funcion;
    }

    @Override
    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    @Override
    public String getSecretarioSalud() {
        return secretarioSalud;
    }

    @Override
    public void setSecretarioSalud(String secretarioSalud) {
        this.secretarioSalud = secretarioSalud;
    }

}
