/*
 *  RegimenContratacionTrabajador.java
 *  Creado el May 25, 2016 1:58:31 PM
 *
 */

package mx.gob.saludtlax.rh.sat.catalogos;

import java.util.List;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public interface RegimenContratacionTrabajador {
    int crear(RegimenContratacionTrabajadorDTO regimenContratacionTrabajadorDTO);

    void actualizar(RegimenContratacionTrabajadorDTO regimenContratacionTrabajadorDTO);

    RegimenContratacionTrabajadorDTO obtenerPorId(int idRegimenContratacionTrabajador);

    List<RegimenContratacionTrabajadorDTO> obtenerRegimenContratacionTrabajadores();

    void eliminar(int idRegimenContratacionTrabajador);
}
