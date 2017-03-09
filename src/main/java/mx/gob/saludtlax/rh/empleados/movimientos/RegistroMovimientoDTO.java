/**
 * 
 */
package mx.gob.saludtlax.rh.empleados.movimientos;

import java.util.Date;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 03/05/2016 03/05/2016
 */
public class RegistroMovimientoDTO {
	private Integer idUsuario;
	private Integer idVacante;
	private Integer idMovimiento;
	private Integer idPuestoGeneral;
	private String numeroOficio;
	private String observaciones;
	private IncapacidadDTO incapacidad;
	private Date fechaInicio;
	private Date fechaFin;

	public Integer getIdPuestoGeneral() {
		return idPuestoGeneral;
	}

	public void setIdPuestoGeneral(Integer idPuestoGeneral) {
		this.idPuestoGeneral = idPuestoGeneral;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getIdVacante() {
		return idVacante;
	}

	public void setIdVacante(Integer idVacante) {
		this.idVacante = idVacante;
	}

	public Integer getIdMovimiento() {
		return idMovimiento;
	}

	public void setIdMovimiento(Integer idMovimiento) {
		this.idMovimiento = idMovimiento;
	}

	public String getNumeroOficio() {
		return numeroOficio;
	}

	public void setNumeroOficio(String numeroOficio) {
		this.numeroOficio = numeroOficio;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public IncapacidadDTO getIncapacidad() {
		return incapacidad;
	}

	public void setIncapacidad(IncapacidadDTO incapacidad) {
		this.incapacidad = incapacidad;
	}

}
