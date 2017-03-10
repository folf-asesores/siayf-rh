/**
 * 
 */
package mx.gob.saludtlax.rh.empleados.datolaboral;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Leila Schiaffini Ehuan
 * @since 10/08/2016 12:24:58
 * 
 */
public class DatoLaboralDTO {
	private Integer numeroEmpleado;
	private Integer idProyecto;
	private Integer idDependencia;
	private Integer idUnidadResponsable;
	private Integer tipoContratacion;
	private Integer idFuenteFinanciamiento;
	private Integer idSubfuenteFinanciamiento;
	private Integer idTipoRecurso;
	private Integer idPuesto;
	private Integer idNombramiento;
	private Integer idTabulador;
	private BigDecimal sueldo;
	private BigDecimal sueldo14;
	private BigDecimal sueldo01;
	private Integer idPrograma;
	private Integer idDetallePrograma;
	private Date fechaInicioLabores;
	private boolean seguroPopular;

	public boolean isSeguroPopular() {
		return seguroPopular;
	}

	public void setSeguroPopular(boolean seguroPopular) {
		this.seguroPopular = seguroPopular;
	}

	public Integer getNumeroEmpleado() {
		return numeroEmpleado;
	}

	public void setNumeroEmpleado(Integer numeroEmpleado) {
		this.numeroEmpleado = numeroEmpleado;
	}

	public Date getFechaInicioLabores() {
		return fechaInicioLabores;
	}

	public void setFechaInicioLabores(Date fechaInicioLabores) {
		this.fechaInicioLabores = fechaInicioLabores;
	}

	public BigDecimal getSueldo14() {
		return sueldo14;
	}

	public void setSueldo14(BigDecimal sueldo14) {
		this.sueldo14 = sueldo14;
	}

	public BigDecimal getSueldo01() {
		return sueldo01;
	}

	public void setSueldo01(BigDecimal sueldo01) {
		this.sueldo01 = sueldo01;
	}

	public Integer getIdDetallePrograma() {
		return idDetallePrograma;
	}

	public void setIdDetallePrograma(Integer idDetallePrograma) {
		this.idDetallePrograma = idDetallePrograma;
	}

	public Integer getIdPrograma() {
		return idPrograma;
	}

	public void setIdPrograma(Integer idPrograma) {
		this.idPrograma = idPrograma;
	}

	public Integer getIdTabulador() {
		return idTabulador;
	}

	public void setIdTabulador(Integer idTabulador) {
		this.idTabulador = idTabulador;
	}

	public BigDecimal getSueldo() {
		return sueldo;
	}

	public void setSueldo(BigDecimal sueldo) {
		this.sueldo = sueldo;
	}

	public Integer getIdNombramiento() {
		return idNombramiento;
	}

	public void setIdNombramiento(Integer idNombramiento) {
		this.idNombramiento = idNombramiento;
	}

	public Integer getTipoContratacion() {
		return tipoContratacion;
	}

	public void setTipoContratacion(Integer tipoContratacion) {
		this.tipoContratacion = tipoContratacion;
	}

	public Integer getIdProyecto() {
		return idProyecto;
	}

	public void setIdProyecto(Integer idProyecto) {
		this.idProyecto = idProyecto;
	}

	public Integer getIdDependencia() {
		return idDependencia;
	}

	public void setIdDependencia(Integer idDependencia) {
		this.idDependencia = idDependencia;
	}

	public Integer getIdUnidadResponsable() {
		return idUnidadResponsable;
	}

	public void setIdUnidadResponsable(Integer idUnidadResponsable) {
		this.idUnidadResponsable = idUnidadResponsable;
	}

	public Integer getIdFuenteFinanciamiento() {
		return idFuenteFinanciamiento;
	}

	public void setIdFuenteFinanciamiento(Integer idFuenteFinanciamiento) {
		this.idFuenteFinanciamiento = idFuenteFinanciamiento;
	}

	public Integer getIdSubfuenteFinanciamiento() {
		return idSubfuenteFinanciamiento;
	}

	public void setIdSubfuenteFinanciamiento(Integer idSubfuenteFinanciamiento) {
		this.idSubfuenteFinanciamiento = idSubfuenteFinanciamiento;
	}

	public Integer getIdTipoRecurso() {
		return idTipoRecurso;
	}

	public void setIdTipoRecurso(Integer idTipoRecurso) {
		this.idTipoRecurso = idTipoRecurso;
	}

	public Integer getIdPuesto() {
		return idPuesto;
	}

	public void setIdPuesto(Integer idPuesto) {
		this.idPuesto = idPuesto;
	}

}
