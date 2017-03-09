/**
 * Copyright © 2016
 */
package mx.gob.saludtlax.rh.configuracion.puestogeneral;

import java.io.Serializable;

/**
 * @author Eduardo Mex
 * @email Lic.Eduardo_Mex@hotmail.com
 * @version 1.0
 * @since 21/07/2016 13:36:58
 */
public class PuestoGeneralDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6683844987788458941L;

	private Integer idPuestoGeneral;

	private String codigo;	

	private String puesto;

	private Integer idTipoPuesto = 0;

	private String descripcionTipoPuesto;

	private Integer idRama = 0;

	private String descripcionRama;

	/*********** Getters and Setter *******/

	/**
	 * @return the idPuestoGeneral
	 */
	public Integer getIdPuestoGeneral() {
		return idPuestoGeneral;
	}

	/**
	 * @param idPuestoGeneral
	 *            the idPuestoGeneral to set
	 */
	public void setIdPuestoGeneral(Integer idPuestoGeneral) {
		this.idPuestoGeneral = idPuestoGeneral;
	}

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the puesto
	 */
	public String getPuesto() {
		return puesto;
	}

	/**
	 * @param puesto
	 *            the puesto to set
	 */
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	/**
	 * @return the idRama
	 */
	public Integer getIdRama() {
		return idRama;
	}

	/**
	 * @param idRama
	 *            the idRama to set
	 */
	public void setIdRama(Integer idRama) {
		this.idRama = idRama;
	}

	/**
	 * @return the descripcionRama
	 */
	public String getDescripcionRama() {
		return descripcionRama;
	}

	/**
	 * @param descripcionRama
	 *            the descripcionRama to set
	 */
	public void setDescripcionRama(String descripcionRama) {
		this.descripcionRama = descripcionRama;
	}

	public Integer getIdTipoPuesto() {
		return idTipoPuesto;
	}

	public void setIdTipoPuesto(Integer idTipoPuesto) {
		this.idTipoPuesto = idTipoPuesto;
	}

	public String getDescripcionTipoPuesto() {
		return descripcionTipoPuesto;
	}

	public void setDescripcionTipoPuesto(String descripcionTipoPuesto) {
		this.descripcionTipoPuesto = descripcionTipoPuesto;
	}

}
