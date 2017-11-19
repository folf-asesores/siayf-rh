/**
 * Copyright ® 2016
 */
package mx.gob.saludtlax.rh.configuracion.terceroinstitucional;

import java.io.Serializable;

/**
 * @author Eduardo Mex
 * @email lic.eduardo_mex@hotmail.com
 * @version 1.0
 * @since 24/05/2016 10:55:31
 */
public class TerceroInstitucionalDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1540117907642893857L;

	private Integer idTerceroInstitucional;

	private String numero;

	private String nombreEmpresa;

	private String conceptoDeduccion;

	private String contrapartidaIdentificadora;

	private String giro;

	

	/**
	 * @return the idTerceroInstitucional
	 */
	public Integer getIdTerceroInstitucional() {
		return idTerceroInstitucional;
	}

	/**
	 * @param idTerceroInstitucional
	 *            the idTerceroInstitucional to set
	 */
	public void setIdTerceroInstitucional(Integer idTerceroInstitucional) {
		this.idTerceroInstitucional = idTerceroInstitucional;
	}

	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero
	 *            the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * @return the nombreEmpresa
	 */
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	/**
	 * @param nombreEmpresa
	 *            the nombreEmpresa to set
	 */
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	/**
	 * @return the giro
	 */
	public String getGiro() {
		return giro;
	}

	/**
	 * @param giro
	 *            the giro to set
	 */
	public void setGiro(String giro) {
		this.giro = giro;
	}

	public String getConceptoDeduccion() {
		return conceptoDeduccion;
	}

	public void setConceptoDeduccion(String conceptoDeduccion) {
		this.conceptoDeduccion = conceptoDeduccion;
	}

	public String getContrapartidaIdentificadora() {
		return contrapartidaIdentificadora;
	}

	public void setContrapartidaIdentificadora(String contrapartidaIdentificadora) {
		this.contrapartidaIdentificadora = contrapartidaIdentificadora;
	}

}
