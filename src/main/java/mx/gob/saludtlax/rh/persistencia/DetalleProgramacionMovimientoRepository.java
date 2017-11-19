
package mx.gob.saludtlax.rh.persistencia;

import java.util.ArrayList;
import java.util.List;

public class DetalleProgramacionMovimientoRepository extends
        GenericRepository<DetalleProgramacionMovimientoEntity, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = 6470484868515281106L;

    public List<DetalleProgramacionMovimientoEntity> obtenerRegistrosPorProgramacionMovimimento(
            Integer idprogramaMovimiento) {

        List<DetalleProgramacionMovimientoEntity> list = new ArrayList<>();
        list = em.createQuery(
                "Select dp From DetalleProgramacionMovimientoEntity as dp where dp.idProgramacionMovimiento.idProgramacionMovimiento=:idprogramaMovimiento",
                DetalleProgramacionMovimientoEntity.class)
                .setParameter("idprogramaMovimiento", idprogramaMovimiento)
                .getResultList();
        return list;
    }

}
