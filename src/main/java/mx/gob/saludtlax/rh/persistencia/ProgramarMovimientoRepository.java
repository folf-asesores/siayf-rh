
package mx.gob.saludtlax.rh.persistencia;

import java.util.ArrayList;
import java.util.List;

public class ProgramarMovimientoRepository
        extends GenericRepository<ProgramarMovimientoEntity, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = -4572950615123023809L;

    public List<ProgramarMovimientoEntity> obtenerRegistrosPorTipoMovimiento(
            Integer idtipoMovimiento) {
        List<ProgramarMovimientoEntity> lista = new ArrayList<>();
        lista = em.createQuery(
                "Select p From ProgramarMovimientoEntity as p where p.idTipoMovimiento.idMovimientoNomina=:idTipoMovimiento",
                ProgramarMovimientoEntity.class)
                .setParameter("idTipoMovimiento", idtipoMovimiento)
                .getResultList();
        return lista;
    }

}
