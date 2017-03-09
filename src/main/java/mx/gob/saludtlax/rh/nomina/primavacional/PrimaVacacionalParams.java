package mx.gob.saludtlax.rh.nomina.primavacional;

import java.math.BigDecimal;

import mx.gob.saludtlax.rh.persistencia.ConfiguracionPresupuestoEntity;

public class PrimaVacacionalParams {
    private ConfiguracionPresupuestoEntity configuracionPresupuesto;
    private BigDecimal basePrima;
    private TipoCalculoPrimaVacacionalEnum tipoCalculo;
    private BigDecimal diasPagar;
    private BigDecimal diasExentoPagar;

    public ConfiguracionPresupuestoEntity getConfiguracionPresupuesto() {
        return configuracionPresupuesto;
    }
    public void setConfiguracionPresupuesto(ConfiguracionPresupuestoEntity configuracionPresupuesto) {
        this.configuracionPresupuesto = configuracionPresupuesto;
    }
    public BigDecimal getBasePrima() {
        return basePrima;
    }
    public void setBasePrima(BigDecimal basePrima) {
        this.basePrima = basePrima;
    }
    public TipoCalculoPrimaVacacionalEnum getTipoCalculo() {
        return tipoCalculo;
    }
    public void setTipoCalculo(TipoCalculoPrimaVacacionalEnum tipoCalculo) {
        this.tipoCalculo = tipoCalculo;
    }
    public BigDecimal getDiasPagar() {
        return diasPagar;
    }
    public void setDiasPagar(BigDecimal diasPagar) {
        this.diasPagar = diasPagar;
    }
    public BigDecimal getDiasExentoPagar() {
        return diasExentoPagar;
    }
    public void setDiasExentoPagar(BigDecimal diasExentoPagar) {
        this.diasExentoPagar = diasExentoPagar;
    }
}