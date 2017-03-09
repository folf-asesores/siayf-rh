package mx.gob.saludtlax.rh.modulos;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.areas.AreaDTO;

@Stateless
public class ModulosEJB implements Modulos {
	
	@Inject
	private ModulosService service;

	@Override
	public void crearModulo(ModuloDTO dto) {
		service.crear(dto);
	}

	@Override
	public void editarModulo(ModuloDTO dto) {
		service.editar(dto);
	}

	@Override
	public Boolean eliminarModulo(Integer idModulo) {
     return service.eliminar(idModulo);
	}

	@Override
	public List<ModuloDTO> listaModulos() {
		return service.listaModulos();
	}
	public List<AreaDTO> listaArea(){
		return service.listaArea();
	}
	
}
