/**
 * Copyright © 2016
 */
package mx.gob.saludtlax.rh.empleados.nombramientos.impresion;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Eduardo Mex
 * @email lic.eduardo_mex@hotmail.com
 * @version 1.0
 * @since 12:56:16 13/09/2016
 */
public class InfoNombramientoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7257909365820116952L;

	private Integer idNombramiento;

	private String tipoNombramiento;

	private String nombreEmpleado;

	private String clavePresupuestal;

	private String funcion;

	private BigDecimal sueldo;

	private String domicilioNombramiento;
	
	private Integer idClasificacionNombramiento;

	public InfoNombramientoDTO() {
		super();
	}

	public InfoNombramientoDTO(Integer idNombramiento, String tipoNombramiento, String nombreEmpleado,
			String clavePresupuestal, String funcion, BigDecimal sueldo, String domicilioNombramiento, Integer idClasificacionNombramiento) {

		this.idNombramiento = idNombramiento;
		this.tipoNombramiento = tipoNombramiento;
		this.nombreEmpleado = nombreEmpleado;
		this.clavePresupuestal = clavePresupuestal;
		this.funcion = funcion;
		this.sueldo = sueldo;
		this.domicilioNombramiento = domicilioNombramiento;
		this.idClasificacionNombramiento = idClasificacionNombramiento;


	}

	/**
	 * @return the idNombramiento
	 */
	public Integer getIdNombramiento() {
		return idNombramiento;
	}

	/**
	 * @param idNombramiento
	 *            the idNombramiento to set
	 */
	public void setIdNombramiento(Integer idNombramiento) {
		this.idNombramiento = idNombramiento;
	}

	/**
	 * @return the tipoNombramiento
	 */
	public String getTipoNombramiento() {
		return tipoNombramiento;
	}

	/**
	 * @param tipoNombramiento
	 *            the tipoNombramiento to set
	 */
	public void setTipoNombramiento(String tipoNombramiento) {
		this.tipoNombramiento = tipoNombramiento;
	}

	/**
	 * @return the nombreEmpleado
	 */
	public String getNombreEmpleado() {
		return nombreEmpleado;
	}

	/**
	 * @param nombreEmpleado
	 *            the nombreEmpleado to set
	 */
	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}

	/**
	 * @return the clavePresupuestal
	 */
	public String getClavePresupuestal() {
		return clavePresupuestal;
	}

	/**
	 * @param clavePresupuestal
	 *            the clavePresupuestal to set
	 */
	public void setClavePresupuestal(String clavePresupuestal) {
		this.clavePresupuestal = clavePresupuestal;
	}

	/**
	 * @return the funcion
	 */
	public String getFuncion() {
		return funcion;
	}

	/**
	 * @param funcion
	 *            the funcion to set
	 */
	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}

	/**
	 * @return the sueldo
	 */
	public BigDecimal getSueldo() {
		return sueldo;
	}

	/**
	 * @param sueldo
	 *            the sueldo to set
	 */
	public void setSueldo(BigDecimal sueldo) {
		this.sueldo = sueldo;
	}

	/**
	 * @return the domicilioNombramiento
	 */
	public String getDomicilioNombramiento() {
		return domicilioNombramiento;
	}

	/**
	 * @param domicilioNombramiento
	 *            the domicilioNombramiento to set
	 */
	public void setDomicilioNombramiento(String domicilioNombramiento) {
		this.domicilioNombramiento = domicilioNombramiento;
	}

	public Integer getIdClasificacionNombramiento() {
		return idClasificacionNombramiento;
	}

	public void setIdClasificacionNombramiento(Integer idClasificacionNombramiento) {
		this.idClasificacionNombramiento = idClasificacionNombramiento;
	}


}
