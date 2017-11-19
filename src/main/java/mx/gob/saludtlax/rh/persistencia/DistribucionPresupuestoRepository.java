
package mx.gob.saludtlax.rh.persistencia;

import java.util.ArrayList;
import java.util.List;

public class DistribucionPresupuestoRepository
        extends GenericRepository<DistribucionPresupuestoEntity, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = 3964306228815768265L;

    public List<DistribucionPresupuestoEntity> obtenerRegistrosPorTipoMovimiento(
            Integer idtipoMovimiento) {
        List<DistribucionPresupuestoEntity> lista = new ArrayList<>();
        lista = em.createQuery(
                "Select p From DistribucionPresupuestoEntity as p where p.idTipoMovimiento.idMovimientoNomina=:idTipoMovimiento",
                DistribucionPresupuestoEntity.class)
                .setParameter("idTipoMovimiento", idtipoMovimiento)
                .getResultList();
        return lista;
    }

}
