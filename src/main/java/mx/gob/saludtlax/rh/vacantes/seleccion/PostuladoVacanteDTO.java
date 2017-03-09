/**
 * Copyright © 2016
 */
package mx.gob.saludtlax.rh.vacantes.seleccion;

import java.io.Serializable;
import java.util.List;

/**
 * @author L.I. Eduardo B. C. Mex
 * @email lic.eduardo_mex@hotmail.com
 * @version 1.0
 * @since 17/08/2016 17:23:27
 */
public class PostuladoVacanteDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2970579847581172276L;

	private String usuario;

	private Integer idInventarioVacante;

	private List<CandidatoVacanteDTO> listaCandidatoVacante;

	/**
	 * 
	 */
	public PostuladoVacanteDTO() {

	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the idInventarioVacante
	 */
	public Integer getIdInventarioVacante() {
		return idInventarioVacante;
	}

	/**
	 * @param idInventarioVacante the idInventarioVacante to set
	 */
	public void setIdInventarioVacante(Integer idInventarioVacante) {
		this.idInventarioVacante = idInventarioVacante;
	}

	/**
	 * @return the listaCandidatoVacante
	 */
	public List<CandidatoVacanteDTO> getListaCandidatoVacante() {
		return listaCandidatoVacante;
	}

	/**
	 * @param listaCandidatoVacante the listaCandidatoVacante to set
	 */
	public void setListaCandidatoVacante(List<CandidatoVacanteDTO> listaCandidatoVacante) {
		this.listaCandidatoVacante = listaCandidatoVacante;
	}

}
