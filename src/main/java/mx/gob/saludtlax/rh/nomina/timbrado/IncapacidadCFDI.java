package mx.gob.saludtlax.rh.nomina.timbrado;

import java.io.Serializable;
import java.math.BigDecimal;

public class IncapacidadCFDI implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2301211381424138404L;
	private BigDecimal diasIncapacidad;
	private int tipoIncapacidad;
	private BigDecimal descuento;
	public BigDecimal getDiasIncapacidad() {
		return diasIncapacidad;
	}
	public void setDiasIncapacidad(BigDecimal diasIncapacidad) {
		this.diasIncapacidad = diasIncapacidad;
	}
	public int getTipoIncapacidad() {
		return tipoIncapacidad;
	}
	public void setTipoIncapacidad(int tipoIncapacidad) {
		this.tipoIncapacidad = tipoIncapacidad;
	}
	public BigDecimal getDescuento() {
		return descuento;
	}
	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
	}
	
	

}
