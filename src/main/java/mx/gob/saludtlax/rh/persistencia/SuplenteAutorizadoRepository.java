/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import javax.persistence.NoResultException;

import mx.gob.saludtlax.rh.empleados.suplencia.SuplenteDTO;

/**
 * @author Leila Schiaffini Ehuan
 * @since 2016-10-19
 *
 */
public class SuplenteAutorizadoRepository
        extends GenericRepository<SuplenteAutorizadoEntity, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = -4670486071594867630L;

    public boolean esEmpleadoActivoComoSuplente(Integer idEmpleado) {
        try {
            em.createQuery(
                    "SELECT s.idSuplenteAutorizado FROM SuplenteAutorizadoEntity AS s WHERE s.empleado.idEmpleado =:idEmpleado",
                    Integer.class).setParameter("idEmpleado", idEmpleado)
                    .getSingleResult();
            return true;
        } catch (NoResultException exception) {
            return false;
        }

    }

    public SuplenteAutorizadoEntity obtenerEmpleadoSuplente(
            Integer idEmpleado) {
        try {
            return em.createQuery(
                    "SELECT s FROM SuplenteAutorizadoEntity AS s WHERE s.empleado.idEmpleado =:idEmpleado",
                    SuplenteAutorizadoEntity.class)
                    .setParameter("idEmpleado", idEmpleado).getSingleResult();

        } catch (NoResultException exception) {
            return null;
        }

    }

    public List<SuplenteDTO> consultarSuplentesPorCriterio(String criterio) {
        return em.createQuery(
                "SELECT NEW mx.gob.saludtlax.rh.empleados.suplencia.SuplenteDTO (s.idSuplenteAutorizado, s.empleado.rfc, s.empleado.nombreCompleto, "
                        + "s.estatus, s.empleado.tipoEmpleado.tipoEmpleado, s.empleado.idEmpleado) FROM SuplenteAutorizadoEntity AS s "
                        + "WHERE s.empleado.rfc LIKE :criterio OR s.empleado.nombreCompleto LIKE :criterio ORDER BY s.empleado.rfc ASC",
                SuplenteDTO.class)
                .setParameter("criterio", "%" + criterio + "%").getResultList();
    }

    public List<SuplenteDTO> consultarSuplentesPorIdEstatus(String estatus) {
        return em.createQuery(
                "SELECT NEW mx.gob.saludtlax.rh.empleados.suplencia.SuplenteDTO (s.idSuplenteAutorizado, s.empleado.rfc,"
                        + " s.empleado.nombreCompleto, s.estatus, s.empleado.tipoEmpleado.tipoEmpleado, s.empleado.idEmpleado)"
                        + " FROM SuplenteAutorizadoEntity AS s WHERE s.estatus =:estatus ORDER BY s.empleado.rfc ASC",
                SuplenteDTO.class).setParameter("estatus", estatus)
                .getResultList();
    }

    public boolean estaHabilitadoEmpleadoComoSuplente(Integer idEmpleado) {
        try {
            em.createQuery(
                    "SELECT s.idSuplenteAutorizado FROM SuplenteAutorizadoEntity AS s WHERE s.empleado.idEmpleado =:idEmpleado",
                    Integer.class).getResultList();
            return true;
        } catch (NoResultException exception) {
            return false;
        }

    }

}
