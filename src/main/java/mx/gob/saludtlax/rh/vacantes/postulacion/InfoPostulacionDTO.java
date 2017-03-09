/**
 * 
 */
package mx.gob.saludtlax.rh.vacantes.postulacion;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @Since 24/10/2016 10:45:23
 */
public class InfoPostulacionDTO {

	private Integer idPostulacion;
	private String tipoContratacion;
	private Date fechaPostulacion;
	private String tipoPostulacion;
	private String puesto;
	private BigDecimal sueldo;
	private String ultimoEmpleado;

	public String getUltimoEmpleado() {
		return ultimoEmpleado;
	}

	public void setUltimoEmpleado(String ultimoEmpleado) {
		this.ultimoEmpleado = ultimoEmpleado;
	}

	public Integer getIdPostulacion() {
		return idPostulacion;
	}

	public void setIdPostulacion(Integer idPostulacion) {
		this.idPostulacion = idPostulacion;
	}

	public String getTipoContratacion() {
		return tipoContratacion;
	}

	public void setTipoContratacion(String tipoContratacion) {
		this.tipoContratacion = tipoContratacion;
	}

	public Date getFechaPostulacion() {
		return fechaPostulacion;
	}

	public void setFechaPostulacion(Date fechaPostulacion) {
		this.fechaPostulacion = fechaPostulacion;
	}

	public String getTipoPostulacion() {
		return tipoPostulacion;
	}

	public void setTipoPostulacion(String tipoPostulacion) {
		this.tipoPostulacion = tipoPostulacion;
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

}
