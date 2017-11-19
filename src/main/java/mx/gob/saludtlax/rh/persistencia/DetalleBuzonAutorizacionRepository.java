/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 10/08/2016 23:42:48
 */
public class DetalleBuzonAutorizacionRepository
        extends GenericRepository<DetalleBuzonAutorizacionEntity, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = -7434892771064361170L;

    public List<DetalleBuzonAutorizacionEntity> procesoAutorizacionAccion(
            Integer idBuzonAutorizacion) {
        List<DetalleBuzonAutorizacionEntity> proceso = em.createQuery(
                "SELECT b FROM DetalleBuzonAutorizacionEntity AS b WHERE b.buzon.idBuzon =:idBuzonAutorizacion",
                DetalleBuzonAutorizacionEntity.class)
                .setParameter("idBuzonAutorizacion", idBuzonAutorizacion)
                .getResultList();
        return proceso;
    }

    public List<BuzonAutorizacionesEntity> autorizacionesUsuarioEstatus(
            Integer idUsuario, boolean autorizado) {
        List<BuzonAutorizacionesEntity> proceso = em.createQuery(
                "SELECT b.buzon FROM DetalleBuzonAutorizacionEntity AS b WHERE b.usuarioEntity.idUsuario =:idUsuario "
                        + "AND b.autorizado =:autorizado ORDER BY b.buzon.accion.operacion ASC",
                BuzonAutorizacionesEntity.class)
                .setParameter("idUsuario", idUsuario)
                .setParameter("autorizado", autorizado).getResultList();
        return proceso;

    }

    public List<BuzonAutorizacionesEntity> autorizacionesUsuarioOperacionEstatus(
            Integer idUsuario, boolean autorizado, Integer idOperacion) {
        List<BuzonAutorizacionesEntity> proceso = em.createQuery(
                "SELECT b.buzon FROM DetalleBuzonAutorizacionEntity AS b WHERE b.usuarioEntity.idUsuario =:idUsuario "
                        + "AND b.autorizado =:autorizado AND b.buzon.accion.idOperacion =:idOperacion",
                BuzonAutorizacionesEntity.class)
                .setParameter("idUsuario", idUsuario)
                .setParameter("autorizado", autorizado)
                .setParameter("idOperacion", idOperacion).getResultList();
        return proceso;

    }

    public DetalleBuzonAutorizacionEntity detalleBuzonPorBuzonUsuario(
            Integer idBuzon, Integer idUsuario) {
        try {
            return em.createQuery(
                    "SELECT d FROM DetalleBuzonAutorizacionEntity AS d WHERE d.buzon.idBuzon =:idBuzon AND d.usuarioEntity.idUsuario =:idUsuario",
                    DetalleBuzonAutorizacionEntity.class)
                    .setParameter("idBuzon", idBuzon)
                    .setParameter("idUsuario", idUsuario).getSingleResult();
        } catch (NoResultException exception) {
            return null;
        }
    }

    /**
     * Consulta los detalles del buzón por estatus
     *
     * @param idBuzon
     * @param autorizado
     */
    public List<DetalleBuzonAutorizacionEntity> detallesBuzonPorEstatus(
            Integer idBuzon, boolean estatus) {

        return em.createQuery(
                "SELECT d FROM DetalleBuzonAutorizacionEntity AS d WHERE d.autorizado =:estatus AND d.buzon.idBuzon =:idBuzon",
                DetalleBuzonAutorizacionEntity.class)
                .setParameter("estatus", estatus)
                .setParameter("idBuzon", idBuzon).getResultList();
    }

    public List<Integer> consultarReceptores(Integer idBuzon) {
        TypedQuery<Integer> query = em.createQuery(
                "select detalle.usuarioEntity.idUsuario from DetalleBuzonAutorizacionEntity as detalle where detalle.idBuzon = :idBuzon",
                Integer.class);
        query.setParameter("idBuzon", idBuzon);

        return query.getResultList();
    }

}
