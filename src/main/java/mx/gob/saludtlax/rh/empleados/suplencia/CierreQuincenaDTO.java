/**
 * 
 */
package mx.gob.saludtlax.rh.empleados.suplencia;

import java.math.BigDecimal;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @Since 08/11/2016 18:33:29
 */
public class CierreQuincenaDTO {
	private Integer idQuincena;
	private Integer idProyecto;
	private Integer idDependencia;
	private Integer idUnidadResponsable;
	private Integer idFuenteFinanciamiento;
	private Integer idSubfuenteFinanciamiento;
	private Integer idTipoRecurso;
	private Integer idPuesto;
	private Integer idCuenta;
	private BigDecimal sueldo;
	private int totalDias;

	public int getTotalDias() {
		return totalDias;
	}

	public void setTotalDias(int totalDias) {
		this.totalDias = totalDias;
	}

	public Integer getIdQuincena() {
		return idQuincena;
	}

	public void setIdQuincena(Integer idQuincena) {
		this.idQuincena = idQuincena;
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

	public Integer getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(Integer idCuenta) {
		this.idCuenta = idCuenta;
	}

	public BigDecimal getSueldo() {
		return sueldo;
	}

	public void setSueldo(BigDecimal sueldo) {
		this.sueldo = sueldo;
	}

}
