/**
 * 
 */
package mx.gob.saludtlax.rh.vacantes.seleccion;

import java.io.Serializable;

/**
 * 
 * @author L.I. Eduardo B. C. Mex
 * @email lic.eduardo_mex@hotmail.com
 * @version 1.0
 * @since 17/08/2016 17:27:38
 */
public class CandidatoVacanteDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1280483538275730617L;

	private Integer tipoCandidato;
	
	private Integer idContexto;

	/************* Constructors *************/

	/**
	 * 
	 */
	public CandidatoVacanteDTO() {
		super();
	}

	



	/**
	 * @return the tipoCandidato
	 */
	public Integer getTipoCandidato() {
		return tipoCandidato;
	}

	/**
	 * @param tipoCandidato
	 *            the tipoCandidato to set
	 */
	public void setTipoCandidato(Integer tipoCandidato) {
		this.tipoCandidato = tipoCandidato;
	}

	/**
	 * @return the idContexto
	 */
	public Integer getIdContexto() {
		return idContexto;
	}

	/**
	 * @param idContexto the idContexto to set
	 */
	public void setIdContexto(Integer idContexto) {
		this.idContexto = idContexto;
	}

	

}
