package mx.gob.saludtlax.rh.configuracion.isr;

import java.math.BigDecimal;

public class TablaSubsidioDTO {
	private Integer idTablaSubsidio;
	private Integer ejercicioFiscal;
	private BigDecimal limiteSuperior;
	private BigDecimal limiteInferior;
	private BigDecimal subsidio;
	private Integer idTipoPeriodo;
	
	
//	<Getters & Setters>
	
	public Integer getIdTablaSubsidio() {
		return idTablaSubsidio;
	}
	public void setIdTablaSubsidio(Integer idTablaSubsidio) {
		this.idTablaSubsidio = idTablaSubsidio;
	}
	public Integer getEjercicioFiscal() {
		return ejercicioFiscal;
	}
	public void setEjercicioFiscal(Integer ejercicioFiscal) {
		this.ejercicioFiscal = ejercicioFiscal;
	}
	public BigDecimal getLimiteSuperior() {
		return limiteSuperior;
	}
	public void setLimiteSuperior(BigDecimal limiteSuperior) {
		this.limiteSuperior = limiteSuperior;
	}
	public BigDecimal getLimiteInferior() {
		return limiteInferior;
	}
	public void setLimiteInferior(BigDecimal limiteInferior) {
		this.limiteInferior = limiteInferior;
	}
	public BigDecimal getSubsidio() {
		return subsidio;
	}
	public void setSubsidio(BigDecimal subsidio) {
		this.subsidio = subsidio;
	}
	public Integer getIdTipoPeriodo() {
		return idTipoPeriodo;
	}
	public void setIdTipoPeriodo(Integer idTipoPeriodo) {
		this.idTipoPeriodo = idTipoPeriodo;
	}
	
}
