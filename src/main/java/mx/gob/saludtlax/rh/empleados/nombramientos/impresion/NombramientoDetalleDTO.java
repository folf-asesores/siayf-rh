/**
 * Copyright © 2016
 */
package mx.gob.saludtlax.rh.empleados.nombramientos.impresion;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Eduardo Mex
 * @email lic.eduardo_mex@hotmail.com
 * @version 1.0
 * @since 18:20:00 12/09/2016
 */
public class NombramientoDetalleDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3294323635219943794L;

	private String textoPosicionUno;
	private String nombreTipoNombramiento;
	private String nombreEmpleado;
	private String rfc;
	private String curp;
	private String edad;
	private String nacionalidad;
	private String sexo;
	private String estadoCivil;
	private String domicilioEmpleado;
	private String clavePresupuestal;
	private String funcion;
	private String tipoNombramiento;
	private String jornadaTrabajo;
	private String horarioTrabajo;
	private BigDecimal sueldo;
	private String lugarAdscripcion;
	private Date vigenciaFechaIngresoEmpleado;
	private String sustituye;
	private String textoPosicionDos;
	private String nombreSecretario;

	private String rama;
	private String propietarioPlaza;
	private String tipoRecurso;
	private Date fechaPosicionUno;
	

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
	 * @return the rfc
	 */
	public String getRfc() {
		return rfc;
	}

	/**
	 * @param rfc
	 *            the rfc to set
	 */
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	/**
	 * @return the curp
	 */
	public String getCurp() {
		return curp;
	}

	/**
	 * @param curp
	 *            the curp to set
	 */
	public void setCurp(String curp) {
		this.curp = curp;
	}

	/**
	 * @return the edad
	 */
	public String getEdad() {
		return edad;
	}

	/**
	 * @param edad
	 *            the edad to set
	 */
	public void setEdad(String edad) {
		this.edad = edad;
	}

	/**
	 * @return the nacionalidad
	 */
	public String getNacionalidad() {
		return nacionalidad;
	}

	/**
	 * @param nacionalidad
	 *            the nacionalidad to set
	 */
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	/**
	 * @return the sexo
	 */
	public String getSexo() {
		return sexo;
	}

	/**
	 * @param sexo
	 *            the sexo to set
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	/**
	 * @return the estadoCivil
	 */
	public String getEstadoCivil() {
		return estadoCivil;
	}

	/**
	 * @param estadoCivil
	 *            the estadoCivil to set
	 */
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	/**
	 * @return the domicilioEmpleado
	 */
	public String getDomicilioEmpleado() {
		return domicilioEmpleado;
	}

	/**
	 * @param domicilioEmpleado
	 *            the domicilioEmpleado to set
	 */
	public void setDomicilioEmpleado(String domicilioEmpleado) {
		this.domicilioEmpleado = domicilioEmpleado;
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
	 * @return the jornadaTrabajo
	 */
	public String getJornadaTrabajo() {
		return jornadaTrabajo;
	}

	/**
	 * @param jornadaTrabajo
	 *            the jornadaTrabajo to set
	 */
	public void setJornadaTrabajo(String jornadaTrabajo) {
		this.jornadaTrabajo = jornadaTrabajo;
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
	 * @return the lugarAdscripcion
	 */
	public String getLugarAdscripcion() {
		return lugarAdscripcion;
	}

	/**
	 * @param lugarAdscripcion
	 *            the lugarAdscripcion to set
	 */
	public void setLugarAdscripcion(String lugarAdscripcion) {
		this.lugarAdscripcion = lugarAdscripcion;
	}

	/**
	 * @return the vigenciaFechaIngresoEmpleado
	 */
	public Date getVigenciaFechaIngresoEmpleado() {
		return vigenciaFechaIngresoEmpleado;
	}

	public String getRama() {
		return rama;
	}

	public void setRama(String rama) {
		this.rama = rama;
	}

	public String getPropietarioPlaza() {
		return propietarioPlaza;
	}

	public void setPropietarioPlaza(String propietarioPlaza) {
		this.propietarioPlaza = propietarioPlaza;
	}

	public String getTipoRecurso() {
		return tipoRecurso;
	}

	public void setTipoRecurso(String tipoRecurso) {
		this.tipoRecurso = tipoRecurso;
	}

	public String getTextoPosicionUno() {
		return textoPosicionUno;
	}

	public void setTextoPosicionUno(String textoPosicionUno) {
		this.textoPosicionUno = textoPosicionUno;
	}

	public String getNombreTipoNombramiento() {
		return nombreTipoNombramiento;
	}

	public void setNombreTipoNombramiento(String nombreTipoNombramiento) {
		this.nombreTipoNombramiento = nombreTipoNombramiento;
	}

	public String getHorarioTrabajo() {
		return horarioTrabajo;
	}

	public void setHorarioTrabajo(String horarioTrabajo) {
		this.horarioTrabajo = horarioTrabajo;
	}

	public String getSustituye() {
		return sustituye;
	}

	public void setSustituye(String sustituye) {
		this.sustituye = sustituye;
	}

	public String getNombreSecretario() {
		return nombreSecretario;
	}

	public void setNombreSecretario(String nombreSecretario) {
		this.nombreSecretario = nombreSecretario;
	}

	/**
	 * @param vigenciaFechaIngresoEmpleado
	 *            the vigenciaFechaIngresoEmpleado to set
	 */
	public void setVigenciaFechaIngresoEmpleado(Date vigenciaFechaIngresoEmpleado) {
		this.vigenciaFechaIngresoEmpleado = vigenciaFechaIngresoEmpleado;
	}

	/**
	 * @return the textoPosicionDos
	 */
	public String getTextoPosicionDos() {
		return textoPosicionDos;
	}

	/**
	 * @param textoPosicionDos
	 *            the textoPosicionDos to set
	 */
	public void setTextoPosicionDos(String textoPosicionDos) {
		this.textoPosicionDos = textoPosicionDos;
	}

	public Date getFechaPosicionUno() {
		return fechaPosicionUno;
	}

	public void setFechaPosicionUno(Date fechaPosicionUno) {
		this.fechaPosicionUno = fechaPosicionUno;
	}

}
