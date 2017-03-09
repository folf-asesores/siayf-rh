package mx.gob.saludtlax.rh.puestosautorizados;

import java.math.BigDecimal;

public class InfoSalarioDTO {

	private String contratacion;
	private String rfc;
	private String nombreCompleto;
	private String clavePuesto;
	private String puesto;
	private BigDecimal sueldoMensual;
	private BigDecimal sueldoQuincenal;

	public InfoSalarioDTO() {

	}

	public InfoSalarioDTO(String contratacion, String rfc,
			String nombreCompleto, String clavePuesto, String puesto,
			BigDecimal sueldoMensual, BigDecimal sueldoQuincenal) {
		super();
		this.contratacion = contratacion;
		this.rfc = rfc;
		this.nombreCompleto = nombreCompleto;
		this.clavePuesto = clavePuesto;
		this.puesto = puesto;
		this.sueldoMensual = sueldoMensual;
		this.sueldoQuincenal = sueldoQuincenal;
	}

	public String getClavePuesto() {
		return clavePuesto;
	}

	public void setClavePuesto(String clavePuesto) {
		this.clavePuesto = clavePuesto;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public BigDecimal getSueldoQuincenal() {
		return sueldoQuincenal;
	}

	public void setSueldoQuincenal(BigDecimal sueldoQuincenal) {
		this.sueldoQuincenal = sueldoQuincenal;
	}

	public String getContratacion() {
		return contratacion;
	}

	public void setContratacion(String contratacion) {
		this.contratacion = contratacion;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public BigDecimal getSueldoMensual() {
		return sueldoMensual;
	}

	public void setSueldoMensual(BigDecimal sueldoMensual) {
		this.sueldoMensual = sueldoMensual;
	}

}
