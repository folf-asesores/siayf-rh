
package mx.gob.saludtlax.rh.reporteslaborales.termino;

import java.io.Serializable;

public class TerminoReincorporacionBaseDTO extends TerminoDTO implements Serializable {

    private static final long serialVersionUID = -3650089257483024488L;

    private String fechaTermino;
    private String fechaPlaza;
    private String nuevaClave;
    private String jefe;
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
    public String getFechaPlaza() {
        return fechaPlaza;
    }

    @Override
    public void setFechaPlaza(String fechaPlaza) {
        this.fechaPlaza = fechaPlaza;
    }

    @Override
    public String getNuevaClave() {
        return nuevaClave;
    }

    @Override
    public void setNuevaClave(String nuevaClave) {
        this.nuevaClave = nuevaClave;
    }

    @Override
    public String getJefe() {
        return jefe;
    }

    @Override
    public void setJefe(String jefe) {
        this.jefe = jefe;
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
