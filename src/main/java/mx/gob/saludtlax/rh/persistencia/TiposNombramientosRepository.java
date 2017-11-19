
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

public class TiposNombramientosRepository
        extends GenericRepository<TiposNombramientosEntity, Integer>
        implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5301679043467897979L;

    public List<TiposNombramientosEntity> nombramientos() {
        List<TiposNombramientosEntity> nombramientos = em
                .createQuery("SELECT n FROM TiposNombramientosEntity AS n",
                        TiposNombramientosEntity.class)
                .getResultList();
        return nombramientos;
    }

    public TiposNombramientosEntity nombramientoPorId(Integer idNombramiento) {
        return em.find(TiposNombramientosEntity.class, idNombramiento);
    }

    public TiposNombramientosEntity nombramientoPorClave(String clave) {
        try {
            return em.createQuery(
                    "SELECT n FROM TiposNombramientosEntity AS n WHERE n.nombramiento =:clave",
                    TiposNombramientosEntity.class).setParameter("clave", clave)
                    .getSingleResult();

        } catch (NoResultException exception) {
            return null;
        }
    }

    public Integer obtenerIdNombramientoPorClave(String clave) {
        try {
            return em.createQuery(
                    "SELECT n.idTipoNombramiento FROM TiposNombramientosEntity AS n WHERE n.clave =:clave",
                    Integer.class).setParameter("clave", clave)
                    .getSingleResult();

        } catch (NoResultException exception) {
            return null;
        } catch (NonUniqueResultException e) {
            return null;
        }
    }

    public List<TiposNombramientosEntity> nombramientosConSubfuente() {
        List<TiposNombramientosEntity> nombramientos = em.createQuery(
                "SELECT n FROM TiposNombramientosEntity AS n WHERE n.idSubfuenteFinanciamiento IS NOT NULL ",
                TiposNombramientosEntity.class).getResultList();
        return nombramientos;
    }
}