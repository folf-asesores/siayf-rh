/**
 * Copyright © 2016
 */
package mx.gob.saludtlax.rh.expediente.aspirante;

import java.io.Serializable;

/**
 * @author Eduardo Mex
 * @since  30/06/2016 15:07:56
 * @version 1.0
 * @email lic.eduardo_mex@hotmail.com
 */
public class ExpedienteAspiranteDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1557770379844162307L;
	
	private Integer idAspirante;
	
	private String numeroExpediente;
	
	
	/*******************Getters and Setters****************/

	/**
	 * @return the idAspirante
	 */
	public Integer getIdAspirante() {
		return idAspirante;
	}

	/**
	 * @param idAspirante the idAspirante to set
	 */
	public void setIdAspirante(Integer idAspirante) {
		this.idAspirante = idAspirante;
	}

	/**
	 * @return the numeroExpediente
	 */
	public String getNumeroExpediente() {
		return numeroExpediente;
	}

	/**
	 * @param numeroExpediente the numeroExpediente to set
	 */
	public void setNumeroExpediente(String numeroExpediente) {
		this.numeroExpediente = numeroExpediente;
	}
	
	
	

}
