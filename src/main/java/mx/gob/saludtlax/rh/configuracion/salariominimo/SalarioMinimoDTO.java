/*
 *
 */

package mx.gob.saludtlax.rh.configuracion.salariominimo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Eduardo Mex
 *
 */
public class SalarioMinimoDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5990525716387988698L;

    private Integer idSalarioMinimo;;

    private Date fecha;

    private BigDecimal salarioMinimo;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getSalarioMinimo() {
        return salarioMinimo;
    }

    public void setSalarioMinimo(BigDecimal salarioMinimo) {
        this.salarioMinimo = salarioMinimo;
    }

    public Integer getIdSalarioMinimo() {
        return idSalarioMinimo;
    }

    public void setIdSalarioMinimo(Integer idSalarioMinimo) {
        this.idSalarioMinimo = idSalarioMinimo;
    }

}
