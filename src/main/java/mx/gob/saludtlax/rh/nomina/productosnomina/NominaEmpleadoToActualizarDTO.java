package mx.gob.saludtlax.rh.nomina.productosnomina;

import mx.gob.saludtlax.rh.persistencia.NominaEmpleadoEntity;

public class NominaEmpleadoToActualizarDTO {
	private NominaEmpleadoEntity nominaEmpleadoEntity;
	private ActualizarNominaEmpleadoDTO actualizarNominaEmpleado;

	public NominaEmpleadoToActualizarDTO(NominaEmpleadoEntity nominaEmpleadoEntity,
			ActualizarNominaEmpleadoDTO actualizarNominaEmpleado) {
		this.nominaEmpleadoEntity = nominaEmpleadoEntity;
		this.actualizarNominaEmpleado = actualizarNominaEmpleado;
	}

	public NominaEmpleadoEntity getNominaEmpleadoEntity() {
		return nominaEmpleadoEntity;
	}
	public void setNominaEmpleadoEntity(NominaEmpleadoEntity nominaEmpleadoEntity) {
		this.nominaEmpleadoEntity = nominaEmpleadoEntity;
	}
	public ActualizarNominaEmpleadoDTO getActualizarNominaEmpleado() {
		return actualizarNominaEmpleado;
	}
	public void setActualizarNominaEmpleado(ActualizarNominaEmpleadoDTO actualizarNominaEmpleado) {
		this.actualizarNominaEmpleado = actualizarNominaEmpleado;
	}
}
