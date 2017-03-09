package mx.gob.saludtlax.rh.nomina.timbrado;

import java.io.Serializable;

/**
*
*@author Juan Carlos Ivan Ganzo Dominguez
*
*/
public class CFDIRespuesta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7834466898561391170L;
	
	private String codigo;
	
	private String validate;
	
	private String mensaje;
	
	private String xml;
	
	private String uuid;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	

}
