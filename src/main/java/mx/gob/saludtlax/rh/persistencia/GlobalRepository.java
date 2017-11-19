
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import javax.persistence.NoResultException;

public class GlobalRepository extends GenericRepository<GlobalEntity, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = 5068604875664104251L;

    public List<GlobalEntity> consultarGlobales() {
        return em.createQuery("SELECT g FROM GlobalEntity AS g WHERE g.duplicado = false", GlobalEntity.class).getResultList();
    }

    public GlobalEntity obtenerGlobalRfc(String rfc) {
        try {
            return em.createQuery("SELECT g FROM GlobalEntity AS g WHERE g.rfc =:rfc AND g.duplicado =0", GlobalEntity.class).setParameter("rfc", rfc)
                    .getSingleResult();
        } catch (NoResultException exception) {
            return null;
        }
    }

    public List<GlobalEntity> obtenerGlobalPorContratacion(Integer tipoContratacion) {

        return em.createQuery("SELECT g FROM GlobalEntity AS g WHERE g.tipoContratacion.id =:tipoContratacion AND g.duplicado =0", GlobalEntity.class)
                .setParameter("tipoContratacion", tipoContratacion).getResultList();
    }

}
