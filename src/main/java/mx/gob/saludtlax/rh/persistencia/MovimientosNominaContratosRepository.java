
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.util.List;

import javax.persistence.TypedQuery;

/**
 *
 * @author José Pablo
 *
 */
public class MovimientosNominaContratosRepository
        extends GenericRepository<MovimientosNominaContratosEntity, Integer>
        implements Serializable {
    private static final long serialVersionUID = -1195379333527080588L;

    public List<MovimientosNominaContratosEntity> obtenerPorNominaEmpleado(
            NominaEmpleadoEntity nominaEmpleadoEntity) {
        TypedQuery<MovimientosNominaContratosEntity> query = em
                .createQuery(" FROM MovimientosNominaContratosEntity "
                        + " WHERE idNominaEmpleado = :idNominaEmpleado ",
                        MovimientosNominaContratosEntity.class)
                .setParameter("idNominaEmpleado",
                        nominaEmpleadoEntity.getIdNominaEmpleado());
        List<MovimientosNominaContratosEntity> result = query.getResultList();
        return result;
    }

    public List<MovimientosNominaContratosEntity> obtenerMovimientosNominaContratosPorIdNominaEmpleado(
            Integer idNominaEmpleado) {
        TypedQuery<MovimientosNominaContratosEntity> query = em.createQuery(
                "" + " FROM MovimientosNominaContratosEntity AS m " + " WHERE "
                        + " m.idNominaEmpleado = :idNominaEmpleado ",
                MovimientosNominaContratosEntity.class)
                .setParameter("idNominaEmpleado", idNominaEmpleado);
        List<MovimientosNominaContratosEntity> result = query.getResultList();
        return result;
    }

}
