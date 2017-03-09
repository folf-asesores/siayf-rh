/**
 * 
 */
package mx.gob.saludtlax.rh.empleados.movimientos.reportes;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Eduardo Mex
 *
 */
public class ConsentradoAltaBajaExcelDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1166849908567865911L;
	
	private String rfc;
	private Date fecha;
	private String nombreCompleto;
	private String clavePresupuestal;
	private String adscripcion;
	private String tipoNombramiento;
	private String movimiento;
	private String observaciones;
	

	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getClavePresupuestal() {
		return clavePresupuestal;
	}
	public void setClavePresupuestal(String clavePresupuestal) {
		this.clavePresupuestal = clavePresupuestal;
	}
	public String getAdscripcion() {
		return adscripcion;
	}
	public void setAdscripcion(String adscripcion) {
		this.adscripcion = adscripcion;
	}
	public String getTipoNombramiento() {
		return tipoNombramiento;
	}
	public void setTipoNombramiento(String tipoNombramiento) {
		this.tipoNombramiento = tipoNombramiento;
	}
	public String getMovimiento() {
		return movimiento;
	}
	public void setMovimiento(String movimiento) {
		this.movimiento = movimiento;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	
}
