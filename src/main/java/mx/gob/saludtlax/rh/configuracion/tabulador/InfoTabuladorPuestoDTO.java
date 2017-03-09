/**
 * 
 */
package mx.gob.saludtlax.rh.configuracion.tabulador;

import java.io.Serializable;

/**
 * @author eduardo
 *
 */
public class InfoTabuladorPuestoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoPuestoGeneral;

	private String nivelTipoPuesto;

	private String nombreRamaPuesto;
	
	/************Getters and Setters*********/

	/**
	 * @return the codigoPuestoGeneral
	 */
	public String getCodigoPuestoGeneral() {
		return codigoPuestoGeneral;
	}

	/**
	 * @param codigoPuestoGeneral the codigoPuestoGeneral to set
	 */
	public void setCodigoPuestoGeneral(String codigoPuestoGeneral) {
		this.codigoPuestoGeneral = codigoPuestoGeneral;
	}

	/**
	 * @return the nivelTipoPuesto
	 */
	public String getNivelTipoPuesto() {
		return nivelTipoPuesto;
	}

	/**
	 * @param nivelTipoPuesto the nivelTipoPuesto to set
	 */
	public void setNivelTipoPuesto(String nivelTipoPuesto) {
		this.nivelTipoPuesto = nivelTipoPuesto;
	}

	/**
	 * @return the nombreRamaPuesto
	 */
	public String getNombreRamaPuesto() {
		return nombreRamaPuesto;
	}

	/**
	 * @param nombreRamaPuesto the nombreRamaPuesto to set
	 */
	public void setNombreRamaPuesto(String nombreRamaPuesto) {
		this.nombreRamaPuesto = nombreRamaPuesto;
	}
	
	

}
