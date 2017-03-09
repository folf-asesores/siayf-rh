/**
 * 
 */
package mx.gob.saludtlax.rh.historialacademico;

import java.util.Date;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 13/06/2016 21:50:30
 */
public class NuevoHistorialDTO {

	private Integer idEscolaridad;
	private Integer idEmpleado;
	private Integer idComprobanteEstudio;
	private boolean maximoGradoEstudios;
	private String nombreInstitucion;
	private Date fechaInicial;
	private Date fechaFinal;
	private String duracion;
	private boolean cursando;
	private String nombreCurso;
	private boolean maximoEstudios;
	private Date fechaExpedicionCedula;
	private Integer numeroCedula;

	public boolean isMaximoEstudios() {
		return maximoEstudios;
	}

	public void setMaximoEstudios(boolean maximoEstudios) {
		this.maximoEstudios = maximoEstudios;
	}

	public Integer getIdEscolaridad() {
		return idEscolaridad;
	}

	public void setIdEscolaridad(Integer idEscolaridad) {
		this.idEscolaridad = idEscolaridad;
	}

	public Integer getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public Integer getIdComprobanteEstudio() {
		return idComprobanteEstudio;
	}

	public void setIdComprobanteEstudio(Integer idComprobanteEstudio) {
		this.idComprobanteEstudio = idComprobanteEstudio;
	}

	public boolean isMaximoGradoEstudios() {
		return maximoGradoEstudios;
	}

	public void setMaximoGradoEstudios(boolean maximoGradoEstudios) {
		this.maximoGradoEstudios = maximoGradoEstudios;
	}

	public String getNombreInstitucion() {
		return nombreInstitucion;
	}

	public void setNombreInstitucion(String nombreInstitucion) {
		this.nombreInstitucion = nombreInstitucion;
	}

	public Date getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	public boolean isCursando() {
		return cursando;
	}

	public void setCursando(boolean cursando) {
		this.cursando = cursando;
	}

	public String getNombreCurso() {
		return nombreCurso;
	}

	public void setNombreCurso(String nombreCurso) {
		this.nombreCurso = nombreCurso;
	}

	/**
	 * @return the fechaExpedicionCedula
	 */
	public Date getFechaExpedicionCedula() {
		return fechaExpedicionCedula;
	}

	/**
	 * @param fechaExpedicionCedula the fechaExpedicionCedula to set
	 */
	public void setFechaExpedicionCedula(Date fechaExpedicionCedula) {
		this.fechaExpedicionCedula = fechaExpedicionCedula;
	}

	/**
	 * @return the numeroCedula
	 */
	public Integer getNumeroCedula() {
		return numeroCedula;
	}

	/**
	 * @param numeroCedula the numeroCedula to set
	 */
	public void setNumeroCedula(Integer numeroCedula) {
		this.numeroCedula = numeroCedula;
	}

}
