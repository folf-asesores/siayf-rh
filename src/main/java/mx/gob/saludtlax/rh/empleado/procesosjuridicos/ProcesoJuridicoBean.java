package mx.gob.saludtlax.rh.empleado.procesosjuridicos;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;

@Stateless
public class ProcesoJuridicoBean implements ProcesoJuridico{

	 @Inject
	 ProcesoJuridicoService procesoJuridicoService;
	
	@Override
	public void crearProceso(ProcesoDTO procesoDTO) {
		procesoJuridicoService.crearProceso(procesoDTO);		
	}

	@Override
	public ProcesoDTO nuevoProceso(InfoEmpleadoDTO empleado) {
		return procesoJuridicoService.nuevoProceso(empleado);
	}

	@Override
	public void actualizarProceso(ProcesoDTO proceso) {
		procesoJuridicoService.actualizarProceso(proceso);
	}

	@Override
	public List<ProcesoDTO> obtenerProcesoLista() {
		return procesoJuridicoService.obtenerProcesoLista();
	}

	@Override
	public ProcesoDTO gestionarProceso(ProcesoDTO proceso) {
		ProcesoDTO dto = procesoJuridicoService.gestionarProceso(proceso);
		return dto;
	}

	@Override
	public void eliminarProceso(ProcesoDTO proceso) {
		procesoJuridicoService.eliminarProceso(proceso);
		
	}

}
