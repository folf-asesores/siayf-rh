/**
 * 
 */
package mx.gob.saludtlax.rh.empleados.suplencia;

import java.math.BigDecimal;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @Since 28/11/2016 12:08:48
 */
public class SuplenciasQuincenaDTO {
	private Integer idEmpleado;
	private String suplente;
	private BigDecimal sueldo;

	public Integer getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getSuplente() {
		return suplente;
	}

	public void setSuplente(String suplente) {
		this.suplente = suplente;
	}

	public BigDecimal getSueldo() {
		return sueldo;
	}

	public void setSueldo(BigDecimal sueldo) {
		this.sueldo = sueldo;
	}

}
