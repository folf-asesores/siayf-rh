package mx.gob.saludtlax.rh.nomina.timbrado;

import java.io.Serializable;

import mx.gob.saludtlax.rh.sat.xml.cfdi.Comprobante;

/**
 *
 * @author Juan Carlos Ivan Ganzo Dominguez
 *
 */
public class DatosCFDITimbrado implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String cadenaOriginal;
	private String sello;
	private String uuid;
	private Comprobante comprobante;
	private String XMLSellado;
	private Integer IdComprobante;

	public Integer getIdComprobante() {
		return IdComprobante;
	}

	public void setIdComprobante(Integer idComprobante) {
		IdComprobante = idComprobante;
	}

	public String getCadenaOriginal() {
		return cadenaOriginal;
	}

	public void setCadenaOriginal(String cadenaOriginal) {
		this.cadenaOriginal = cadenaOriginal;
	}

	public String getSello() {
		return sello;
	}

	public void setSello(String sello) {
		this.sello = sello;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Comprobante getComprobante() {
		return comprobante;
	}

	public void setComprobante(Comprobante comprobante) {
		this.comprobante = comprobante;
	}

	public String getXMLSellado() {
		return XMLSellado;
	}

	public void setXMLSellado(String xMLSellado) {
		XMLSellado = xMLSellado;
	}

}
