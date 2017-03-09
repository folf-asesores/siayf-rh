/**
 * 
 */
package mx.gob.saludtlax.rh.empleados.interinatos;

import java.util.Date;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @Since 18/11/2016 18:46:38
 */
public class InterinatoDTO {
	private Integer idPuesto;
	private Integer idContexto;
	private Integer idUsuario;
	private Integer tipoCandidato;
	private String numeroCuenta;
	private Date fechaIngreso;

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Integer getIdContexto() {
		return idContexto;
	}

	public void setIdContexto(Integer idContexto) {
		this.idContexto = idContexto;
	}

	public Integer getTipoCandidato() {
		return tipoCandidato;
	}

	public void setTipoCandidato(Integer tipoCandidato) {
		this.tipoCandidato = tipoCandidato;
	}

	public Integer getIdPuesto() {
		return idPuesto;
	}

	public void setIdPuesto(Integer idPuesto) {
		this.idPuesto = idPuesto;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

}
