
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import javax.persistence.NoResultException;

public class ConceptosNominaEspecialRepository
        extends GenericRepository<ConceptosNominaEspecialEntity, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = 2863446284096218937L;

    public List<ConceptosNominaEspecialEntity> listaRegistros() {
        return em.createQuery("Select c From ConceptosNominaEspecialEntiy as c",
                ConceptosNominaEspecialEntity.class).getResultList();
    }

    public ConceptosNominaEspecialEntity obtenerConcepto(
            Integer idConceptoBase) {
        ConceptosNominaEspecialEntity entity = new ConceptosNominaEspecialEntity();
        try {
            entity = em.createQuery(
                    "Select c from ConceptosNominaEspecialEntity as c where c.idConceptobase.idConceptoNomina=:idConceptoBase",
                    ConceptosNominaEspecialEntity.class)
                    .setParameter("idConceptoBase", idConceptoBase)
                    .getSingleResult();

            return entity;
        } catch (NoResultException e) {
            return null;
        }
    }

}
