
package mx.gob.saludtlax.rh.persistencia;

import java.util.ArrayList;
import java.util.List;

public class ConfiguracionConceptoPuestoRepository
        extends GenericRepository<ConfiguracionConceptoPuestoEntity, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = -2773130441343002821L;

    public List<ConfiguracionConceptoPuestoEntity> obtenerListaConfiguracionesPorConcepto(
            Integer idConcepto) {
        List<ConfiguracionConceptoPuestoEntity> listEntitys = new ArrayList<>();
        listEntitys = em.createQuery(
                "Select c From ConfiguracionConceptoPuestoEntity as c where c.conceptoNomina.idConceptoNomina=:idConcepto",
                ConfiguracionConceptoPuestoEntity.class)
                .setParameter("idConcepto", idConcepto).getResultList();
        return listEntitys;
    }

    public List<ConfiguracionConceptoPuestoEntity> obtenerListaConfiguracionesPorPuesto(
            Integer idPuesto) {
        List<ConfiguracionConceptoPuestoEntity> listEntitys = new ArrayList<>();
        listEntitys = em.createQuery(
                "Select c From ConfiguracionConceptoPuestoEntity as c where c.puestoGeneral.idPuestoGeneral=:idPuesto",
                ConfiguracionConceptoPuestoEntity.class)
                .setParameter("idPuesto", idPuesto).getResultList();
        return listEntitys;
    }

    public ConfiguracionConceptoPuestoEntity obtenerPorConceptoPuesto(
            Integer idConcepto, Integer idPuesto) {
        ConfiguracionConceptoPuestoEntity entity = new ConfiguracionConceptoPuestoEntity();
        entity = em.createQuery(
                "Select c From ConfiguracionConceptoPuestoEntity as c where  c.conceptoNomina.idConceptoNomina=:idConcepto and c.puestoGeneral.idPuestoGeneral=:idPuesto",
                ConfiguracionConceptoPuestoEntity.class)
                .setParameter("idConcepto", idConcepto)
                .setParameter("idPuesto", idPuesto).getSingleResult();
        return entity;
    }
}
