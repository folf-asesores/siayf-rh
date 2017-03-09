package mx.gob.saludtlax.rh.nomina.timbrado;

import java.io.Serializable;
import java.math.BigDecimal;

public class PercepcionCFDI implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1618521031003588408L;

	private String tipoPercepcion;
	private String clave;
	private String concepto;
	private BigDecimal ImporteGravado;
	private BigDecimal ImporteExcento;



	public String getTipoPercepcion() {
		return tipoPercepcion;
	}

	public void setTipoPercepcion(String tipoPercepcion) {
		this.tipoPercepcion = tipoPercepcion;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public BigDecimal getImporteGravado() {
		return ImporteGravado;
	}

	public void setImporteGravado(BigDecimal importeGravado) {
		ImporteGravado = importeGravado;
	}

	public BigDecimal getImporteExcento() {
		return ImporteExcento;
	}

	public void setImporteExcento(BigDecimal importeExcento) {
		ImporteExcento = importeExcento;
	}

}
