/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import mx.gob.saludtlax.rh.excepciones.BusinessException;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;

/**
 * @author Leila Schiaffini Ehuan
 * @since 13/06/2016 11:17:18
 *
 */
public class ExpedienteEmpleadoRepository extends GenericRepository<ExpedienteEmpleadoEntity, Integer> {

    private static final long serialVersionUID = -5862760775058476152L;

    public ExpedienteEmpleadoEntity obtenerPorIdEmpleado(Integer idEmpleado) {
        try {
            return em.createQuery("SELECT e FROM ExpedienteEmpleadoEntity AS e WHERE e.idEmpleado =:idEmpleado", classType)
                    .setParameter("idEmpleado", idEmpleado).getSingleResult();
        } catch (NoResultException exception) {
            return null;
        } catch (NonUniqueResultException exception) {
            throw new ReglaNegocioException("Se ha encontrado más de un expediente asignado al empleado.", ReglaNegocioCodigoError.EXISTE_MAS_DE_UN_EXPEDIENTE);
        }
    }

    /**
     * Consulta si el empleado tiene aperturado un expediente.
     *
     * @param idEmpleado
     */
    public boolean existeExpedienteAsignadoEmpleado(Integer idEmpleado) {
        try {
            em.createQuery("SELECT e.idExpedienteEmpleado FROM ExpedienteEmpleadoEntity AS e WHERE e.idEmpleado =:idEmpleado", Integer.class)
                    .setParameter("idEmpleado", idEmpleado).getSingleResult();
            return true;
        } catch (NoResultException exception) {
            return false;
        } catch (NonUniqueResultException exception) {
            throw new BusinessException("Se ha encontrado mas de un expediente asignado al empleado.");
        }
    }

    /**
     * Consulta si el numero de expediente ya está asignado.
     *
     * @param numeroExpediente
     */
    public boolean existeNumeroExpediente(String numeroExpediente) {
        try {
            em.createQuery("SELECT e.idExpedienteEmpleado FROM ExpedienteEmpleadoEntity AS e WHERE e.numeroExpediente =:numeroExpediente", Integer.class)
                    .setParameter("numeroExpediente", numeroExpediente.trim()).getSingleResult();
            return true;
        } catch (NoResultException exception) {
            return false;
        }
    }

    /**
     * Consulta el numero de expediente del empleado
     *
     * @param numeroExpediente
     */
    public String numeroExpedienteEmpleado(Integer idEmpleado) {

        try {
            return em.createQuery("SELECT e.numeroExpediente FROM ExpedienteEmpleadoEntity AS e WHERE e.idEmpleado =:idEmpleado", String.class)
                    .setParameter("idEmpleado", idEmpleado).getSingleResult();

        } catch (NoResultException exception) {
            throw new BusinessException("El empleado no tiene un expediente aperturado.");
        }
    }

    public Integer obtenerIdExpedienteEmpleado(Integer idEmpleado) {

        try {
            return em.createQuery("SELECT e.idExpedienteEmpleado FROM ExpedienteEmpleadoEntity AS e WHERE e.idEmpleado =:idEmpleado", Integer.class)
                    .setParameter("idEmpleado", idEmpleado).getSingleResult();

        } catch (NoResultException exception) {
            throw new BusinessException("El empleado no tiene un expediente aperturado.");
        }
    }

}
