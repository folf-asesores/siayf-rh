
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

    @Override
    public String getReincorporacionTitular() {
        return reincorporacionTitular;
    }

    @Override
    public void setReincorporacionTitular(String reincorporacionTitular) {
        this.reincorporacionTitular = reincorporacionTitular;
    }

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
