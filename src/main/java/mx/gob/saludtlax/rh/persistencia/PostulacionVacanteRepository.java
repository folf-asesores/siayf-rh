/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import mx.gob.saludtlax.rh.excepciones.SistemaCodigoError;
import mx.gob.saludtlax.rh.excepciones.SistemaException;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 *
 */
public class PostulacionVacanteRepository
        extends GenericRepository<PostulacionVacanteEntity, Integer>
        implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2301969114733212635L;

    public List<PostulacionVacanteEntity> obtenerListaPostuladoVacanteDisponibles() {
        return em.createQuery(
                "SELECT pv FROM PostulacionVacanteEntity AS pv WHERE pv.disponible = 'SI'",
                PostulacionVacanteEntity.class).getResultList();
    }

    public PostulacionVacanteEntity tienePuestoPostulacionActiva(
            Integer idPuesto) {
        try {
            return em.createQuery(
                    "SELECT p FROM PostulacionVacanteEntity AS p WHERE p.inventarioVacante.idVacante =:idPuesto AND p.disponible = 'SI'",
                    PostulacionVacanteEntity.class)
                    .setParameter("idPuesto", idPuesto).getSingleResult();
        } catch (NoResultException exception) {
            return null;
        } catch (NonUniqueResultException exception) {
            throw new SistemaException(
                    "Se ha detectado mas de una postulación para el puesto activo.",
                    SistemaCodigoError.ERROR_MULTIPLES_RESULTADOS);
        }
    }

    public Integer obtenerIdPostulacionActiva(Integer idPuesto) {
        try {
            return em.createQuery(
                    "SELECT p.idPostuladoVacante FROM PostulacionVacanteEntity AS p WHERE p.inventarioVacante.idVacante =:idPuesto AND p.disponible = 'SI'",
                    Integer.class).setParameter("idPuesto", idPuesto)
                    .getSingleResult();
        } catch (NoResultException exception) {
            return null;
        } catch (NonUniqueResultException exception) {
            throw new SistemaException(
                    "Se ha detectado mas de una postulación para el puesto activo.",
                    SistemaCodigoError.ERROR_MULTIPLES_RESULTADOS);
        }
    }

}
