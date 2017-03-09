/*
 *  RegimenContratacionTrabajadorEJB.java
 *  Creado el May 25, 2016 1:59:59 PM
 * 
 */
package mx.gob.saludtlax.rh.sat.catalogos;

import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
public class RegimenContratacionTrabajadorEJB implements RegimenContratacionTrabajador{

    @Inject private RegimenContratacionTrabajadorService regimenContratacionTrabajadorService;
    
    @Override
    public int crear(RegimenContratacionTrabajadorDTO regimenContratacionTrabajadorDTO) {
        return regimenContratacionTrabajadorService.crear(regimenContratacionTrabajadorDTO);
    }

    @Override
    public void actualizar(RegimenContratacionTrabajadorDTO regimenContratacionTrabajadorDTO) {
        regimenContratacionTrabajadorService.actualizar(regimenContratacionTrabajadorDTO);
    }

    @Override
    public RegimenContratacionTrabajadorDTO obtenerPorId(int idRegimenContratacionTrabajador) {
        return regimenContratacionTrabajadorService.obtenerPorId(idRegimenContratacionTrabajador);
    }

    @Override
    public List<RegimenContratacionTrabajadorDTO> obtenerRegimenContratacionTrabajadores() {
        return regimenContratacionTrabajadorService.obtenerRegimenContratacionTrabajadores();
    }

    @Override
    public void eliminar(int idRegimenContratacionTrabajador) {
        regimenContratacionTrabajadorService.eliminar(idRegimenContratacionTrabajador);
    }
    
}
