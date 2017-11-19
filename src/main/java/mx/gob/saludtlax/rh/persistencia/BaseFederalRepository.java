
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

public class BaseFederalRepository
        extends GenericRepository<BaseFederalEntity, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = 8271367817594151104L;

    public List<BaseFederalEntity> consultarPlantillaPendiente() {
        return em.createQuery(
                "SELECT b FROM BaseFederalEntity AS b WHERE b.idInventario IS NULL",
                BaseFederalEntity.class).getResultList();
    }

}
