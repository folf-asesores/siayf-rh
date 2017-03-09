package mx.gob.saludtlax.rh.presupuesto;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.configuracion.nombramiento.TipoNombramientoDTO;
import mx.gob.saludtlax.rh.configuracion.nombramiento.TipoNombramientoService;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;


@Stateless
public class ProyeccionesPresupuestalesEJB {
	@Inject
    private TipoNombramientoService tipoNombramientoService;
    @Inject
    private ProyeccionesPresupuestalesService proyeccionesPresupuestalesService;
    
    public List<TipoNombramientoDTO> getListaTipoNombramiento() {
        return tipoNombramientoService.listaNombramiento();
    }
    
    public List<ProyeccionesPresupuestalesDTO> proyeccionesPresupuestales(Integer anio) 
    		throws ReglaNegocioException {
    	 // TODO Auto-generated method stub
        return proyeccionesPresupuestalesService.proyeccionesPresupuestales(anio);
    }
    
    public List<ProyeccionesPresupuestalesDTO> consultarPartidasPorTipoNombramiento(Integer tipoNombramiento)
            throws ReglaNegocioException {
        // TODO Auto-generated method stub
        return proyeccionesPresupuestalesService.consultarPartidasPorTipoNombramiento(tipoNombramiento);
    }

    public List<ProyeccionesPresupuestalesDTO> proyeccionesPresupuestales(Integer anioPresupuesto,
            Integer idTipoNombramiento) {
        return proyeccionesPresupuestalesService.obtenerProyeccionesMensuales(anioPresupuesto,idTipoNombramiento);
    }

    public void guardarProyeccion(ProyeccionesPresupuestalesDTO proyeccionPresupuestal) {
        proyeccionPresupuestal = proyeccionesPresupuestalesService.proyectar(proyeccionPresupuestal);
        proyeccionesPresupuestalesService.guardarProyeccion(proyeccionPresupuestal);
    }
}
