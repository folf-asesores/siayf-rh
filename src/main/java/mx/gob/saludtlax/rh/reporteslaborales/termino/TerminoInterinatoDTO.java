package mx.gob.saludtlax.rh.reporteslaborales.termino;

import java.io.Serializable;

public class TerminoInterinatoDTO extends TerminoDTO implements Serializable {

    private static final long serialVersionUID = 983714864555200191L;

    private String posicionUno;
    private String reincorporacionTitular;
    private String fechaTermino;
    private String funcion;
    private String secretarioSalud;

    public String getPosicionUno() {
        return posicionUno;
    }

    public void setPosicionUno(String posicionUno) {
        this.posicionUno = posicionUno;
    }

    public String getReincorporacionTitular() {
        return reincorporacionTitular;
    }

    public void setReincorporacionTitular(String reincorporacionTitular) {
        this.reincorporacionTitular = reincorporacionTitular;
    }

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
