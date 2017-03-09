package mx.gob.saludtlax.rh.nomina.timbrado;

import java.io.Serializable;

/**
*
*@author Juan Carlos Ivan Ganzo Dominguez
*
*/
public class CFDIPeticion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5042425864056373526L;
	
	private String user;
	private String password;
	private String xml;
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
	public String getXml() {
		return xml;
	}
	public void setXml(String xml) {
		this.xml = xml;
	}
	
	

}
