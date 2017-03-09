/**
 * 
 */
package mx.gob.saludtlax.rh.contrato;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author eduardo Mex
 *
 */
public class ContratoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer idContrato;

	private Integer tipoContrato;

	private String nombreTipoContrato;

	private Date fechaInicio;

	private Date fechaFin;

	private String numeroContrato;

	private Integer idEmpleado;

	private Integer idVacante;

	private boolean activo;

	private boolean impreso;

	private BigDecimal sueldoMensual;

	private String descripcionSueldo;

	private String nombreCompletoEmpleado;

	private String nombrePuestoGeneral;

	private String domicilioServicio;

	/**
	 * 
	 */
	public ContratoDTO() {
		super();

	}

	/**
	 * 
	 */
	public ContratoDTO(Integer idContrato, String nombreTipoContrato,
			String nombreCompletoEmpleado, Date fechaInicio, Date fechaFin) {

		this.idContrato = idContrato;
		this.nombreTipoContrato = nombreTipoContrato;
		this.nombreCompletoEmpleado = nombreCompletoEmpleado;
		this.fechaInicio = fechaInicio;
		this.fechaInicio = fechaFin;

	}

	public boolean isImpreso() {
		return impreso;
	}

	public void setImpreso(boolean impreso) {
		this.impreso = impreso;
	}

	/**
	 * @return the idContrato
	 */
	public Integer getIdContrato() {
		return idContrato;
	}

	/**
	 * @param idContrato
	 *            the idContrato to set
	 */
	public void setIdContrato(Integer idContrato) {
		this.idContrato = idContrato;
	}

	/**
	 * @return the tipoContrato
	 */
	public Integer getTipoContrato() {
		return tipoContrato;
	}

	/**
	 * @param tipoContrato
	 *            the tipoContrato to set
	 */
	public void setTipoContrato(Integer tipoContrato) {
		this.tipoContrato = tipoContrato;
	}

	/**
	 * @return the fechaInicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param fechaInicio
	 *            the fechaInicio to set
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * @return the fechaFin
	 */
	public Date getFechaFin() {
		return fechaFin;
	}

	/**
	 * @param fechaFin
	 *            the fechaFin to set
	 */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * @return the numeroContrato
	 */
	public String getNumeroContrato() {
		return numeroContrato;
	}

	/**
	 * @param numeroContrato
	 *            the numeroContrato to set
	 */
	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	/**
	 * @return the idEmpleado
	 */
	public Integer getIdEmpleado() {
		return idEmpleado;
	}

	/**
	 * @param idEmpleado
	 *            the idEmpleado to set
	 */
	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	/**
	 * @return the idVacante
	 */
	public Integer getIdVacante() {
		return idVacante;
	}

	/**
	 * @param idVacante
	 *            the idVacante to set
	 */
	public void setIdVacante(Integer idVacante) {
		this.idVacante = idVacante;
	}

	/**
	 * @return the activo
	 */
	public boolean isActivo() {
		return activo;
	}

	/**
	 * @param activo
	 *            the activo to set
	 */
	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	/**
	 * @return the sueldoMensual
	 */
	public BigDecimal getSueldoMensual() {
		return sueldoMensual;
	}

	/**
	 * @param sueldoMensual
	 *            the sueldoMensual to set
	 */
	public void setSueldoMensual(BigDecimal sueldoMensual) {
		this.sueldoMensual = sueldoMensual;
	}

	/**
	 * @return the descripcionSueldo
	 */
	public String getDescripcionSueldo() {
		return descripcionSueldo;
	}

	/**
	 * @param descripcionSueldo
	 *            the descripcionSueldo to set
	 */
	public void setDescripcionSueldo(String descripcionSueldo) {
		this.descripcionSueldo = descripcionSueldo;
	}

	/**
	 * @return the nombreCompletoEmpleado
	 */
	public String getNombreCompletoEmpleado() {
		return nombreCompletoEmpleado;
	}

	/**
	 * @param nombreCompletoEmpleado
	 *            the nombreCompletoEmpleado to set
	 */
	public void setNombreCompletoEmpleado(String nombreCompletoEmpleado) {
		this.nombreCompletoEmpleado = nombreCompletoEmpleado;
	}

	/**
	 * @return the nombrePuestoGeneral
	 */
	public String getNombrePuestoGeneral() {
		return nombrePuestoGeneral;
	}

	/**
	 * @param nombrePuestoGeneral
	 *            the nombrePuestoGeneral to set
	 */
	public void setNombrePuestoGeneral(String nombrePuestoGeneral) {
		this.nombrePuestoGeneral = nombrePuestoGeneral;
	}

	/**
	 * @return the domicilioServicio
	 */
	public String getDomicilioServicio() {
		return domicilioServicio;
	}

	/**
	 * @param domicilioServicio
	 *            the domicilioServicio to set
	 */
	public void setDomicilioServicio(String domicilioServicio) {
		this.domicilioServicio = domicilioServicio;
	}

	/**
	 * @return the nombreTipoContrato
	 */
	public String getNombreTipoContrato() {
		return nombreTipoContrato;
	}

	/**
	 * @param nombreTipoContrato
	 *            the nombreTipoContrato to set
	 */
	public void setNombreTipoContrato(String nombreTipoContrato) {
		this.nombreTipoContrato = nombreTipoContrato;
	}

}
