/**
 * 
 */
package mx.gob.saludtlax.rh.vacantes.postulacion;

import java.math.BigDecimal;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @Since 24/10/2016 23:37:44
 */
public class PuestoDisponibleDTO {

	private Integer idPuesto;
	private String tipoContratacion;
	private String tipoNombramiento;
	private String folioVacante;
	private String puesto;
	private BigDecimal sueldo;
	private String empleadoAnterior;
	private String tipoVacante;

	public String getTipoVacante() {
		return tipoVacante;
	}

	public void setTipoVacante(String tipoVacante) {
		this.tipoVacante = tipoVacante;
	}

	public Integer getIdPuesto() {
		return idPuesto;
	}

	public void setIdPuesto(Integer idPuesto) {
		this.idPuesto = idPuesto;
	}

	public String getTipoContratacion() {
		return tipoContratacion;
	}

	public void setTipoContratacion(String tipoContratacion) {
		this.tipoContratacion = tipoContratacion;
	}

	public String getTipoNombramiento() {
		return tipoNombramiento;
	}

	public void setTipoNombramiento(String tipoNombramiento) {
		this.tipoNombramiento = tipoNombramiento;
	}

	public String getFolioVacante() {
		return folioVacante;
	}

	public void setFolioVacante(String folioVacante) {
		this.folioVacante = folioVacante;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public BigDecimal getSueldo() {
		return sueldo;
	}

	public void setSueldo(BigDecimal sueldo) {
		this.sueldo = sueldo;
	}

	public String getEmpleadoAnterior() {
		return empleadoAnterior;
	}

	public void setEmpleadoAnterior(String empleadoAnterior) {
		this.empleadoAnterior = empleadoAnterior;
	}

}
