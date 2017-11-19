/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import javax.persistence.NoResultException;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 *
 */
public class FormatoFiliacionRepository
        extends GenericRepository<FormatoFiliacionEntity, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = -3392273217048584211L;

    public List<FormatoFiliacionEntity> listaFormatoFiliacionPorIdEmpleado(
            Integer idEmpleado) {
        try {
            return em.createQuery(
                    "SELECT f FROM FormatoFiliacionEntity AS f WHERE f.idEmpleado =:idEmpleado AND f.estatus = 'ACTIVO'",
                    FormatoFiliacionEntity.class)
                    .setParameter("idEmpleado", idEmpleado).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

}
