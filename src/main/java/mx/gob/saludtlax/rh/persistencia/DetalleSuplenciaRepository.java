/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import mx.gob.saludtlax.rh.empleados.suplencia.EnumEstatusSuplencia;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 30/10/2016 18:32:40
 */
public class DetalleSuplenciaRepository extends GenericRepository<DetalleSuplenciaEntity, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = 1182039061434321674L;

    public List<DetalleSuplenciaEntity> consultarDetallesSuplenciaIdQuincena(Integer idQuincena) {

        return em.createQuery("SELECT d FROM DetalleSuplenciaEntity AS d WHERE d.quincena.idQuincenaSuplencia =:idQuincena ORDER BY d.fechaInicio DESC",
                DetalleSuplenciaEntity.class).setParameter("idQuincena", idQuincena).getResultList();
    }

    public List<DetalleSuplenciaEntity> consultarDetallesQuincenaPorEstatus(Integer idQuincena, String estatus) {

        return em.createQuery(
                "SELECT d FROM DetalleSuplenciaEntity AS d WHERE d.quincena.idQuincenaSuplencia =:idQuincena  AND d.estatus =:estatus ORDER BY d.fechaInicio DESC",
                DetalleSuplenciaEntity.class).setParameter("idQuincena", idQuincena).setParameter("estatus", estatus).getResultList();
    }

    public List<DetalleSuplenciaEntity> consultarDetalleSuplentePorEstatus(Integer idSuplente, String estatus, Integer idQuincena) {
        return em.createQuery("SELECT d FROM DetalleSuplenciaEntity AS d WHERE d.quincena.suplente.idSuplenteAutorizado =:idSuplente "
                + "AND d.estatus =:estatus AND d.quincena.idQuincenaSuplencia !=:id AND d.quincena.estatus =:estatusQuincena ORDER BY d.fechaInicio DESC",
                DetalleSuplenciaEntity.class).setParameter("idSuplente", idSuplente).setParameter("estatus", estatus).setParameter("id", idQuincena)
                .setParameter("estatusQuincena", EnumEstatusSuplencia.CERRADA).getResultList();
    }

    public boolean haSuplidoFecha(Date fecha, Integer idSuplente, Integer idJornada) {
        try {
            em.createQuery("SELECT d.idDetalleSuplencia FROM DetalleSuplenciaEntity AS d" + " WHERE d.quincena.suplente.idSuplenteAutorizado =:idSuplente AND "
                    + " :fecha BETWEEN d.fechaInicio AND d.fechaFin AND d.jornada.id =:idJornada", Integer.class).setParameter("idSuplente", idSuplente)
                    .setParameter("fecha", fecha).setParameter("idJornada", idJornada).getSingleResult();
            return true;
        } catch (NoResultException exception) {
            return false;
        }
    }

    public List<Integer> tieneDetallesAprobados(Integer idQuincena, String estatus) {
        return em.createQuery(
                "SELECT d.idDetalleSuplencia FROM DetalleSuplenciaEntity AS d WHERE d.quincena.idQuincenaSuplencia =:idQuincena  AND d.estatus =:estatus",
                Integer.class).setParameter("idQuincena", idQuincena).setParameter("estatus", estatus).getResultList();
    }

}
