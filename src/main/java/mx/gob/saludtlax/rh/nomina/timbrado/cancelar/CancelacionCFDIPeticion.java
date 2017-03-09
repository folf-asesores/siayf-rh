package mx.gob.saludtlax.rh.nomina.timbrado.cancelar;

import java.io.Serializable;

public class CancelacionCFDIPeticion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 254077835744665826L;

	private String user;
	private String password;
	private String rfc;
	private String uuid;
	private String certificado;
	private String llave;
	private String password_llave;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getCertificado() {
		return certificado;
	}

	public void setCertificado(String certificado) {
		this.certificado = certificado;
	}

	public String getLlave() {
		return llave;
	}

	public void setLlave(String llave) {
		this.llave = llave;
	}

	public String getPassword_llave() {
		return password_llave;
	}

	public void setPassword_llave(String password_llave) {
		this.password_llave = password_llave;
	}

}
