package mx.gob.saludtlax.rh.presupuesto;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.configuracion.dependencia.DependenciaDTO;
import mx.gob.saludtlax.rh.configuracion.dependencia.DependenciaService;
import mx.gob.saludtlax.rh.configuracion.nombramiento.TipoNombramientoDTO;
import mx.gob.saludtlax.rh.configuracion.nombramiento.TipoNombramientoService;

@Stateless
public class DistribucionPresupuestoEJB {
	
	@Inject
    private TipoNombramientoService tipoNombramientoService;
	@Inject
	private DependenciaService dependenciaService;
    @Inject
    private DistribucionPresupuestoService distribucionPresupuestoService;
    
    public List<TipoNombramientoDTO> getListaTipoNombramiento() {
        return tipoNombramientoService.listaNombramiento();
    }
    
    public List<DependenciaDTO> getListaDependencia(){
    	return dependenciaService.listaDependencia();
    }
    
//    public List<DistribucionPresupuestoDTO> distribucionPresupuesto(Integer anio) 
//    		throws ReglaNegocioException {
//    	 // TODO Auto-generated method stub
//        return distribucionPresupuestoService.distribucionPresupuesto (anio);
//    }
//    
//    public List<DistribucionPresupuestoDTO> consultarPartidasPorTipoNombramiento(Integer tipoNombramiento)
//            throws ReglaNegocioException {
//        // TODO Auto-generated method stub
//        return distribucionPresupuestoService.consultarPartidasPorTipoNombramiento(tipoNombramiento);
//    }

    public List<DistribucionPresupuestoDTO> distribucionPresupuesto(Integer anioPresupuesto,
            Integer idTipoNombramiento, Integer idDependencia) {
        return distribucionPresupuestoService.obtenerProyeccionesMensuales(anioPresupuesto,idTipoNombramiento,idDependencia);
    }
}
