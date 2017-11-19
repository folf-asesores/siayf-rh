package mx.gob.saludtlax.rh.reporteslaborales.comision;

import java.io.Serializable;

/**
 * @author Daniela
 *
 */

public class ComisionDetalleDTO implements Serializable{

	/**
	 * 
	 */
	
	
	private Integer idMovimiento;
	private String empleado;
	private String rfc;
	private String curp;
	private String motivo;
	private String url;
	
	public Integer getIdMovimiento() {
		return idMovimiento;
	}
	public void setIdMovimiento(Integer idMovimiento) {
		this.idMovimiento = idMovimiento;
	}
	public String getEmpleado() {
		return empleado;
	}
	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public String getCurp() {
		return curp;
	}
	public void setCurp(String curp) {
		this.curp = curp;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

}
