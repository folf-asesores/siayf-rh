/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 * 
 * @version 1.0
 * @since 21/07/2016 10:15:08
 */
public class PuestoGeneralRepository
        extends GenericRepository<PuestoGeneralEntity, Integer>
        implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5158353831818803939L;

    public PuestoGeneralEntity puestoPorClave(String clave) {
        try {
            return em.createQuery(
                    "SELECT p FROM PuestoGeneralEntity AS p WHERE p.codigo =:clave",
                    PuestoGeneralEntity.class).setParameter("clave", clave)
                    .getSingleResult();
        } catch (NoResultException exception) {
            return null;

        }
    }

    public List<PuestoGeneralEntity> obtenerListaPuestoGeneral() {
        try {
            return em.createQuery("SELECT p FROM PuestoGeneralEntity AS p",
                    PuestoGeneralEntity.class).getResultList();
        } catch (NoResultException exception) {
            return null;
        }
    }

    public Boolean existePuestoPorCodigo(String codigo) {

        try {
            em.createQuery(
                    "SELECT p.idPuestoGeneral FROM PuestoGeneralEntity AS p WHERE p.codigo =:codigo",
                    Integer.class).setParameter("codigo", codigo)
                    .getSingleResult();
            return true;
        } catch (NoResultException exception) {
            return false;

        }

    }

    public Boolean existePuestoPorCodigoIdPuesto(Integer idPuesto,
            String codigo) {

        try {
            em.createQuery(
                    "SELECT p.idPuestoGeneral FROM PuestoGeneralEntity AS p WHERE p.idPuestoGeneral =:idPuesto AND p.codigo =:codigo",
                    Integer.class).setParameter("idPuesto", idPuesto)
                    .setParameter("codigo", codigo).getSingleResult();
            return true;

        } catch (NoResultException exception) {
            return false;

        }

    }

    public List<PuestoGeneralEntity> consultarPuestosPorRama(Integer idRama) {
        List<PuestoGeneralEntity> puestos = em.createQuery(
                "SELECT p FROM PuestoGeneralEntity AS p WHERE p.idRama.idRamaPuesto =:idRama",
                PuestoGeneralEntity.class).getResultList();
        return puestos;

    }
}
