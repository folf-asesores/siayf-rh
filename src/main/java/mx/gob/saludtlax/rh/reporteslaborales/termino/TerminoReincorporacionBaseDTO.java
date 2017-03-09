package mx.gob.saludtlax.rh.reporteslaborales.termino;

import java.io.Serializable;

public class TerminoReincorporacionBaseDTO extends TerminoDTO implements Serializable {

    private static final long serialVersionUID = -3650089257483024488L;

    private String fechaTermino;
    private String fechaPlaza;
    private String nuevaClave;
    private String jefe;
    private String secretarioSalud;

    public String getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(String fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    public String getFechaPlaza() {
        return fechaPlaza;
    }

    public void setFechaPlaza(String fechaPlaza) {
        this.fechaPlaza = fechaPlaza;
    }

    public String getNuevaClave() {
        return nuevaClave;
    }

    public void setNuevaClave(String nuevaClave) {
        this.nuevaClave = nuevaClave;
    }

    public String getJefe() {
        return jefe;
    }

    public void setJefe(String jefe) {
        this.jefe = jefe;
    }

    public String getSecretarioSalud() {
        return secretarioSalud;
    }

    public void setSecretarioSalud(String secretarioSalud) {
        this.secretarioSalud = secretarioSalud;
    }

}
