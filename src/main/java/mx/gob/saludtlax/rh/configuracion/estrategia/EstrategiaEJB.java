/*
 * 
 * EstrategiaEJB.java
 * Creado el Jul 12, 2016 10:43:19 AM
 * 
 */
package mx.gob.saludtlax.rh.configuracion.estrategia;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
@Stateless
public class EstrategiaEJB implements Estrategia {
    
    @Inject private EstrategiaService estrategiaService;

    @Override
    public int crear(EstrategiaDTO dto) {
        return estrategiaService.crear(dto);
    }

    @Override
    public void actualizar(EstrategiaDTO dto) {
        estrategiaService.actualizar(dto);
    }

    @Override
    public EstrategiaDTO obtenerPorId(int idEstrategia) {
        return estrategiaService.obtenerPorId(idEstrategia);
    }

    @Override
    public List<EstrategiaDTO> consultarEstrategias() {
        return estrategiaService.consultarEstrategias();
    }
    
    @Override
    public List<String> consultarDescripcionEstrategiaPorCriterio(String criterio) {
        return estrategiaService.consultarDescripcionEstrategiaPorCriterio(criterio);
    }

    @Override
    public Integer consultarIdEstrategiaPorDescripcion(String descripcion) {
        return estrategiaService.consultarIdEstrategiaPorDescripcion(descripcion);
    }

    @Override
    public void eliminar(int idEstrategia) {
        estrategiaService.eliminar(idEstrategia);
    }

    @Override
    public boolean existeCodigoEstrategia(int codigoEstrategia) {
        return estrategiaService.existeCodigoEstrategia(codigoEstrategia);
    }
}
