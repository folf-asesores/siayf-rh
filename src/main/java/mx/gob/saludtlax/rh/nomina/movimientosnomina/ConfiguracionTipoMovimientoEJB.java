package mx.gob.saludtlax.rh.nomina.movimientosnomina;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ConfiguracionTipoMovimientoEJB {

	
	@Inject
	ConfiguracionTipoMovimientoService configuracionTipoMovimientoService;
	
	
	public void crear(ConfiguracionTipoMovimientoDTO dto){
		configuracionTipoMovimientoService.guardarConfiguracion(dto);
	}
	
	public void editar(ConfiguracionTipoMovimientoDTO dto){
		configuracionTipoMovimientoService.editarConfiguracion(dto);
	}
	
	public ConfiguracionTipoMovimientoDTO configuracionPorTipoMovimiento(Integer idTipoMovimiento){
		return configuracionTipoMovimientoService.obtenerConfiguracionesPorTipoMovimiento(idTipoMovimiento);
	}
}
