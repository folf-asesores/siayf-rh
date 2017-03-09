/**
 * 
 */
package mx.gob.saludtlax.rh.empleados.suplencia;

import java.util.Date;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @Since 29/12/2016 14:00:48
 */
public class QuincenaActivaDTO {
	private String usuario;
	private Date fecha;
	private int numeroQuincenaActiva;
	private int ejercicioFiscal;

	public int getEjercicioFiscal() {
		return ejercicioFiscal;
	}

	public void setEjercicioFiscal(int ejercicioFiscal) {
		this.ejercicioFiscal = ejercicioFiscal;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getNumeroQuincenaActiva() {
		return numeroQuincenaActiva;
	}

	public void setNumeroQuincenaActiva(int numeroQuincenaActiva) {
		this.numeroQuincenaActiva = numeroQuincenaActiva;
	}

}
