
package mx.gob.saludtlax.rh.nomina.movimientoscontrato;

import java.io.Serializable;
import java.math.BigDecimal;

public class InfoMovimientoDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7882596122159333059L;
    private Integer idEmpleado;
    private BigDecimal montoPeriodo;
    private Integer numeroPago;
    private Integer idConcepto;
    private Integer idMovimiento;
    private Integer idNominaEmpleado;
    private Integer tipoConcepto;

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public BigDecimal getMontoPeriodo() {
        return montoPeriodo;
    }

    public void setMontoPeriodo(BigDecimal montoPeriodo) {
        this.montoPeriodo = montoPeriodo;
    }

    public Integer getIdConcepto() {
        return idConcepto;
    }

    public void setIdConcepto(Integer idConcepto) {
        this.idConcepto = idConcepto;
    }

    public Integer getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(Integer idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public Integer getIdNominaEmpleado() {
        return idNominaEmpleado;
    }

    public void setIdNominaEmpleado(Integer idNominaEmpleado) {
        this.idNominaEmpleado = idNominaEmpleado;
    }

    public Integer getNumeroPago() {
        return numeroPago;
    }

    public void setNumeroPago(Integer numeroPago) {
        this.numeroPago = numeroPago;
    }

    public Integer getTipoConcepto() {
        return tipoConcepto;
    }

    public void setTipoConcepto(Integer tipoConcepto) {
        this.tipoConcepto = tipoConcepto;
    }
}