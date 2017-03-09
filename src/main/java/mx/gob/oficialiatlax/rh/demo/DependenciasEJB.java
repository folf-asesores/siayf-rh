package mx.gob.oficialiatlax.rh.demo;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.persistencia.TiposDependenciasRepository;

@Stateless
public class DependenciasEJB {

	@Inject
	private TiposDependenciasRepository dependenciasRepository;
	
	
	public List<TipoDependenciasDTO> obtenerTiposDependencias(){
	return dependenciasRepository.obtenerTipoDependencias();	
	}
	
	public List<DependenciasDTO> obtenerDependencias(Integer idtipo){
		return dependenciasRepository.obtenerDependencias(idtipo);
	}
	
}
