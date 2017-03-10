package mx.gob.saludtlax.rh.modulos;

import java.util.List;

public interface Modulos {
	
	void crearModulo(ModuloDTO dto);
	
	void editarModulo(ModuloDTO dto);
	
	Boolean eliminarModulo(Integer idModulo);
	
	List<ModuloDTO> listaModulos();
	
	ModuloDTO obtenerModuloPorId(Integer idModulo); 
	
	
	
}