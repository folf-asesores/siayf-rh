
package mx.gob.saludtlax.rh.persistencia;

import java.util.ArrayList;
import java.util.List;

public class ConfiguracionTipoMovimientoNominaReporsitory
        extends GenericRepository<ConfiguracionTipoMovimientoEntity, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = 50536756442272018L;

    public List<ConfiguracionTipoMovimientoEntity> obenerConceptosPorTipoMovimiento(
            Integer idTipoMovimiento) {
        List<ConfiguracionTipoMovimientoEntity> list = new ArrayList<>();
        list = em.createQuery(
                "Select c From ConfiguracionTipoMovimientoEntity as c where c.tipoMovimiento.idMovimientoNomina=:idTipoMovimiento",
                ConfiguracionTipoMovimientoEntity.class)
                .setParameter("idTipoMovimiento", idTipoMovimiento)
                .getResultList();
        return list;
    }

}
