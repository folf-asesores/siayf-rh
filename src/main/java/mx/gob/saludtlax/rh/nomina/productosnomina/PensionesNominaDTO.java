
package mx.gob.saludtlax.rh.nomina.productosnomina;

import java.math.BigDecimal;
import java.util.Date;

public class PensionesNominaDTO {
    private Integer idPensionNomina;
    private String rfcBeneficiario;
    private String beneficiario;
    private String rfcEmpleado;
    private String empleado;
    private BigDecimal pago;
    private Date fechaPago;

    public Integer getIdPensionNomina() {
        return idPensionNomina;
    }

    public void setIdPensionNomina(Integer idPensionNomina) {
        this.idPensionNomina = idPensionNomina;
    }

    public String getRfcBeneficiario() {
        return rfcBeneficiario;
    }

    public void setRfcBeneficiario(String rfcBeneficiario) {
        this.rfcBeneficiario = rfcBeneficiario;
    }

    public String getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(String beneficiario) {
        this.beneficiario = beneficiario;
    }

    public String getRfcEmpleado() {
        return rfcEmpleado;
    }

    public void setRfcEmpleado(String rfcEmpleado) {
        this.rfcEmpleado = rfcEmpleado;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public BigDecimal getPago() {
        return pago;
    }

    public void setPago(BigDecimal pago) {
        this.pago = pago;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }
}