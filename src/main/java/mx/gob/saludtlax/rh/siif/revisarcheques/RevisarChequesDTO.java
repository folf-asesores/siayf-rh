
package mx.gob.saludtlax.rh.siif.revisarcheques;

import java.io.Serializable;

public class RevisarChequesDTO implements Serializable {

    private static final long serialVersionUID = 906841269353612971L;

    private String numeroCuenta;
    private String rfc;
    private String numCheq;
    private String nombramientoDescripcion;
    private String periodo;
    private Integer anio;

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getNumCheq() {
        return numCheq;
    }

    public void setNumCheq(String numCheq) {
        this.numCheq = numCheq;
    }

    public String getNombramientoDescripcion() {
        return nombramientoDescripcion;
    }

    public void setNombramientoDescripcion(String nombramientoDescripcion) {
        this.nombramientoDescripcion = nombramientoDescripcion;
    }

}
