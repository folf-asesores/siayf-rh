package mx.gob.saludtlax.rh.nomina.timbrado;

import java.io.Serializable;
import java.math.BigDecimal;

public class OtroPagoCFDI implements Serializable{
	
	public String clave;
	public String concepto;
	public BigDecimal importe;
	public String tipo;
	
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
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
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	
	
	

}
