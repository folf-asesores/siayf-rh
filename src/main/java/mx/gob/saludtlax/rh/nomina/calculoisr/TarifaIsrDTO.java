
package mx.gob.saludtlax.rh.nomina.calculoisr;

import java.math.BigDecimal;

public class TarifaIsrDTO {
    private BigDecimal limiteInferior;
    private BigDecimal porcentajeAplicable;
    private BigDecimal cuotaFija;

    public BigDecimal getLimiteInferior() {
        return limiteInferior;
    }

    public void setLimiteInferior(BigDecimal limiteInferior) {
        this.limiteInferior = limiteInferior;
    }

    public BigDecimal getPorcentajeAplicable() {
        return porcentajeAplicable;
    }

    public void setPorcentajeAplicable(BigDecimal porcentajeAplicable) {
        this.porcentajeAplicable = porcentajeAplicable;
    }

    public BigDecimal getCuotaFija() {
        return cuotaFija;
    }

    public void setCuotaFija(BigDecimal cuotaFija) {
        this.cuotaFija = cuotaFija;
    }
}