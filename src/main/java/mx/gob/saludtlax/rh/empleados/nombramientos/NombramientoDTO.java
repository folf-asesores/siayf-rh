/**
 * 
 */
package mx.gob.saludtlax.rh.empleados.nombramientos;

import java.math.BigDecimal;

import mx.gob.saludtlax.rh.persistencia.EmpleadoEntity;

/**
 * @author Leila Schiaffini Ehuan
 * @since 12/09/2016 12:02:23
 * 
 */
public class NombramientoDTO {
	private EmpleadoEntity empleado;
	private String clavePresupuestal;
	private BigDecimal sueldo;
	private int idClasificacionNombramiento;

	public EmpleadoEntity getEmpleado() {
		return empleado;
	}

	public void setEmpleado(EmpleadoEntity empleado) {
		this.empleado = empleado;
	}

	public String getClavePresupuestal() {
		return clavePresupuestal;
	}

	public void setClavePresupuestal(String clavePresupuestal) {
		this.clavePresupuestal = clavePresupuestal;
	}

	public BigDecimal getSueldo() {
		return sueldo;
	}

	public void setSueldo(BigDecimal sueldo) {
		this.sueldo = sueldo;
	}

	public int getIdClasificacionNombramiento() {
		return idClasificacionNombramiento;
	}

	public void setIdClasificacionNombramiento(int idClasificacionNombramiento) {
		this.idClasificacionNombramiento = idClasificacionNombramiento;
	}

}
