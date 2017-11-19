
package mx.gob.saludtlax.rh.nomina.calculoisr;

import java.math.BigDecimal;

public class IsrDTO {
    private BigDecimal ingresoGravable;
    private BigDecimal subsidiosEntregar;
    private BigDecimal impuestoRetener;
    private BigDecimal percepcionEfectiva;

    public BigDecimal getIngresoGravable() {
        return ingresoGravable;
    }

    public void setIngresoGravable(BigDecimal ingresoGravable) {
        this.ingresoGravable = ingresoGravable;
    }

    public BigDecimal getSubsidiosEntregar() {
        return subsidiosEntregar;
    }

    public void setSubsidiosEntregar(BigDecimal subsidiosEntregar) {
        this.subsidiosEntregar = subsidiosEntregar;
    }

    public BigDecimal getImpuestoRetener() {
        return impuestoRetener;
    }

    public void setImpuestoRetener(BigDecimal impuestoRetener) {
        this.impuestoRetener = impuestoRetener;
    }

    public BigDecimal getPercepcionEfectiva() {
        return percepcionEfectiva;
    }

    public void setPercepcionEfectiva(BigDecimal percepcionEfectiva) {
        this.percepcionEfectiva = percepcionEfectiva;
    }

    @Override
    public String toString() {
        return "IsrDTO [ingresoGravable=" + ingresoGravable
                + ", subsidiosEntregar=" + subsidiosEntregar
                + ", impuestoRetener=" + impuestoRetener
                + ", percepcionEfectiva=" + percepcionEfectiva + "]";
    }
}