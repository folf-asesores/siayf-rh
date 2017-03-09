package mx.gob.saludtlax.rh.siif.reportarcontratos;

import java.io.Serializable;

public class SiifSeguroPopularDTO implements Serializable {


	private static final long serialVersionUID = -4226990086634174773L;

	private String idSeguroPopularFederal;	
	private String rfc;
	private Integer quincena;
	
	/**************** Getters and Setters ***********************/
	
	public String getIdSeguroPopularFederal() {
		return idSeguroPopularFederal;
	}
	public void setIdSeguroPopularFederal(String idSeguroPopularFederal) {
		this.idSeguroPopularFederal = idSeguroPopularFederal;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public Integer getQuincena() {
		return quincena;
	}
	public void setQuincena(Integer quincena) {
		this.quincena = quincena;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}