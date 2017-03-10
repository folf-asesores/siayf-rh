package mx.gob.saludtlax.rh.quinquenios;

import java.util.List;

import mx.gob.saludtlax.rh.vacantes.seleccion.InfoEmpleadoVacanteDTO;

public interface ConfiguracionQuinquenioService {

	public List<ConfiguracionQuinquenioDTO> obtenerConfiguraciones();
	
	public ConfiguracionQuinquenioDTO obtenerConfiguracionPorEmpleado(Integer idempleado);
	

	public ConfiguracionQuinquenioDTO obtenerConfiguracionPorConfiguracionPresup(Integer idConfiguracion);
	
	public List<InfoEmpleadoVacanteDTO> buscarEmpleadosPorTipoNombramiento(Integer idNombramiento);
	
	public void crearConfiguracion(ConfiguracionQuinquenioDTO dto);
	public void actualizarConfiguracion(ConfiguracionQuinquenioDTO dto);
	
	
}
