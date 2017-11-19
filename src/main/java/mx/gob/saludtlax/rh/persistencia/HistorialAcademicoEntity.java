/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 07/03/2016-11:25:13
 */
@Entity
@Table(name = "historiales_academicos")
public class HistorialAcademicoEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2641807708396124556L;
	@Id
	@Column(name = "id_historia_academico")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idHistorialAcademico;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_escolaridad")
	private EscolaridadEntity escolaridad;

	@Column(name = "id_empleado")
	private Integer idEmpleado;

	@Column(name = "id_aspirante")
	private Integer idAspirante;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_comprobante_estudio")
	private ComprobanteEstudioEntity comprobanteEstudio;

	@Column(name = "nombre_institucion")
	private String nombreInstitucion;

	@Column(name = "fecha_inicial")
	private Date fechaInicial;

	@Column(name = "fecha_final")
	private Date FechaFinal;

	@Column(name = "duracion")
	private String duracion;

	@Column(name = "cursando")
	private Boolean cursando;

	@Column(name = "nombre_curso")
	private String nombreCurso;

	@Column(name = "tiene_documentacion")
	private Boolean tieneDocumentacion;

	@Column(name = "maximo_estudio")
	private Boolean maximoEstudio;

	@Column(name = "fecha_expedicion_cedula")
	private Date fechaExpedicionCedula;

	@Column(name = "numero_cedula")
	private Integer numeroCedula;

	


	@Override
	public String toString() {
		return "HistorialAcademicoEntity [idHistorialAcademico=" + idHistorialAcademico + ", escolaridad=" + escolaridad
				+ ", idEmpleado=" + idEmpleado + ", idAspirante=" + idAspirante + ", comprobanteEstudio="
				+ comprobanteEstudio + ", nombreInstitucion=" + nombreInstitucion + ", fechaInicial=" + fechaInicial
				+ ", FechaFinal=" + FechaFinal + ", duracion=" + duracion + ", cursando=" + cursando + ", nombreCurso="
				+ nombreCurso + ", tieneDocumentacion=" + tieneDocumentacion + ", maximoEstudio=" + maximoEstudio
				+ ", fechaExpedicionCedula=" + fechaExpedicionCedula + ", numeroCedula=" + numeroCedula + "]";
	}
	

	public String lccHistorial() {
		return "HistorialAcademico[idHistorialAcademico=" + idHistorialAcademico + ", escolaridad=" + escolaridad.getEscolaridad()
				+ ", idEmpleado=" + idEmpleado + ", idAspirante=" + idAspirante + ", comprobanteEstudio="
				+ comprobanteEstudio + ", nombreInstitucion=" + nombreInstitucion + ", fechaInicial=" + fechaInicial
				+ ", FechaFinal=" + FechaFinal + ", duracion=" + duracion + ", cursando=" + cursando + ", nombreCurso="
				+ nombreCurso + ", tieneDocumentacion=" + tieneDocumentacion + ", maximoEstudio=" + maximoEstudio
				+ ", fechaExpedicionCedula=" + fechaExpedicionCedula + ", numeroCedula=" + numeroCedula + "]";
	}
	
	public Integer getIdHistorialAcademico() {
		return idHistorialAcademico;
	}

	public Boolean getMaximoEstudio() {
		return maximoEstudio;
	}

	public void setMaximoEstudio(Boolean maximoEstudio) {
		this.maximoEstudio = maximoEstudio;
	}

	public Boolean getTieneDocumentacion() {
		return tieneDocumentacion;
	}

	public void setTieneDocumentacion(Boolean tieneDocumentacion) {
		this.tieneDocumentacion = tieneDocumentacion;
	}

	public Integer getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public Integer getIdAspirante() {
		return idAspirante;
	}

	public void setIdAspirante(Integer idAspirante) {
		this.idAspirante = idAspirante;
	}

	public void setIdHistorialAcademico(Integer idHistorialAcademico) {
		this.idHistorialAcademico = idHistorialAcademico;
	}

	/**
	 * @return the comprobanteEstudio
	 */
	public ComprobanteEstudioEntity getComprobanteEstudio() {
		return comprobanteEstudio;
	}

	/**
	 * @param comprobanteEstudio
	 *            the comprobanteEstudio to set
	 */
	public void setComprobanteEstudio(ComprobanteEstudioEntity comprobanteEstudio) {
		this.comprobanteEstudio = comprobanteEstudio;
	}

	public EscolaridadEntity getEscolaridad() {
		return escolaridad;
	}

	public void setEscolaridad(EscolaridadEntity escolaridad) {
		this.escolaridad = escolaridad;
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
		return FechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		FechaFinal = fechaFinal;
	}

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	public String getNombreCurso() {
		return nombreCurso;
	}

	public void setNombreCurso(String nombreCurso) {
		this.nombreCurso = nombreCurso;
	}

	public Boolean getCursando() {
		return cursando;
	}

	public void setCursando(Boolean cursando) {
		this.cursando = cursando;
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
