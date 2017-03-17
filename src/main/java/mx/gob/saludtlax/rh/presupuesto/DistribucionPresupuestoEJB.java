package mx.gob.saludtlax.rh.presupuesto;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.configuracion.dependencia.DependenciaDTO;
import mx.gob.saludtlax.rh.configuracion.dependencia.DependenciaService;
import mx.gob.saludtlax.rh.configuracion.fuenteFinanciamiento.SubfuenteFinanciamientoDTO;
import mx.gob.saludtlax.rh.configuracion.nombramiento.TipoNombramientoDTO;
import mx.gob.saludtlax.rh.configuracion.nombramiento.TipoNombramientoService;
import mx.gob.saludtlax.rh.siif.SiifLaboralesSubfuentesService;

@Stateless
public class DistribucionPresupuestoEJB {
	
	@Inject
    private TipoNombramientoService tipoNombramientoService;
	@Inject
	private DependenciaService dependenciaService;
	@Inject
	private SiifLaboralesSubfuentesService subfuentesService;
    @Inject
    private DistribucionPresupuestoService distribucionPresupuestoService;
    
    public List<TipoNombramientoDTO> getListaTipoNombramiento() {
        return tipoNombramientoService.listaNombramiento();
    }
    
    public List<DependenciaDTO> getListaDependencia(){
    	return dependenciaService.listaDependencia();
    }
    
    public List<SubfuenteFinanciamientoDTO> getListaSubfuente(){
    	return subfuentesService.listaSubfuenteFinanciamiento();
    }
        
    public List<DistribucionPresupuestoDTO> distribucionPresupuesto(Integer anioPresupuesto,
            Integer idTipoNombramiento, Integer idDependencia, Integer idSubfuenteFinanciamiento) {
        return distribucionPresupuestoService.obtenerProyeccionesMensuales(anioPresupuesto,idTipoNombramiento,idDependencia, idSubfuenteFinanciamiento);
    }

//    public class reporteExcel{
//    	public byte [] getReporte(int idDistribucionPresupuestal){
//    		List<DistribucionPresupuestoDTO> lista = distribucionPresupuestoService.obtenerProyeccionesMensuales(idDistribucionPresupuestal);
//    		Excel ex = new Excel();
//    		return ex.getBytes(lista);
//    	}
//    }

}
