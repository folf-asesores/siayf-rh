package mx.gob.saludtlax.rh.configuracion.conceptosnomina;

import java.io.Serializable;
import java.math.BigDecimal;

public class ConfiguracionConceptoPuestoDTO implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = 5172641171733087326L;

	private Integer id_configuracion_concepto;

	private Integer id_concepto_nomina;
	private String claveConcepto;
	private String decripcionConcepto;

	private Integer id_tabulador;
	private Integer ejercicioFiscalTabulador;

	private BigDecimal importe_concepto=new BigDecimal(0);

	private Integer id_puesto_general;
	private String codigoPuesto;
	private String descripcionPuesto;
	
	private String formula;
	
	private Integer tipoPuesto;

	public Integer getId_configuracion_concepto() {
		return id_configuracion_concepto;
	}

	public void setId_configuracion_concepto(Integer id_configuracion_concepto) {
		this.id_configuracion_concepto = id_configuracion_concepto;
	}

	public Integer getId_concepto_nomina() {
		return id_concepto_nomina;
	}

	public void setId_concepto_nomina(Integer id_concepto_nomina) {
		this.id_concepto_nomina = id_concepto_nomina;
	}

	public String getClaveConcepto() {
		return claveConcepto;
	}

	public void setClaveConcepto(String claveConcepto) {
		this.claveConcepto = claveConcepto;
	}

	public String getDecripcionConcepto() {
		return decripcionConcepto;
	}

	public void setDecripcionConcepto(String decripcionConcepto) {
		this.decripcionConcepto = decripcionConcepto;
	}

	public Integer getId_tabulador() {
		return id_tabulador;
	}

	public void setId_tabulador(Integer id_tabulador) {
		this.id_tabulador = id_tabulador;
	}

	public Integer getEjercicioFiscalTabulador() {
		return ejercicioFiscalTabulador;
	}

	public void setEjercicioFiscalTabulador(Integer ejercicioFiscalTabulador) {
		this.ejercicioFiscalTabulador = ejercicioFiscalTabulador;
	}

	public BigDecimal getImporte_concepto() {
		return importe_concepto;
	}

	public void setImporte_concepto(BigDecimal importe_concepto) {
		this.importe_concepto = importe_concepto;
	}

	public Integer getId_puesto_general() {
		return id_puesto_general;
	}

	public void setId_puesto_general(Integer id_puesto_general) {
		this.id_puesto_general = id_puesto_general;
	}

	public String getCodigoPuesto() {
		return codigoPuesto;
	}

	public void setCodigoPuesto(String codigoPuesto) {
		this.codigoPuesto = codigoPuesto;
	}

	public String getDescripcionPuesto() {
		return descripcionPuesto;
	}

	public void setDescripcionPuesto(String descripcionPuesto) {
		this.descripcionPuesto = descripcionPuesto;
	}

	public Integer getTipoPuesto() {
		return tipoPuesto;
	}

	public void setTipoPuesto(Integer tipoPuesto) {
		this.tipoPuesto = tipoPuesto;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

}
