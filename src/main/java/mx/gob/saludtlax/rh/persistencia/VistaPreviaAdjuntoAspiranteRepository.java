/*
 * VistaPreviaAdjuntoAspiranteRepository.java
 * Creado el Sep 2, 2016 11:22:12 AM
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class VistaPreviaAdjuntoAspiranteRepository extends GenericRepository<VistaPreviaAdjuntoAspiranteEntity, Integer> {
    /**
     *
     */
    private static final long serialVersionUID = -3495148646225107176L;
    private static final String OBTENER_VISTA_PREVIA_POR_ID_ADJUNTO = "select vistaPrevia" + " from VistaPreviaAdjuntoAspiranteEntity as vistaPrevia"
            + " where vistaPrevia.informacionAdjuntoAspirante.idInformacionAdjuntoAspirante = :idAdjunto";
    private static final String ELIMINAR_POR_ID_ADJUNTO = "delete from VistaPreviaAdjuntoAspiranteEntity as vistaPrevia"
            + " where vistaPrevia.informacionAdjuntoAspirante.idInformacionAdjuntoAspirante = :idAdjunto";

    public VistaPreviaAdjuntoAspiranteEntity obtenerPorIdAdjunto(Integer idAdjunto) {
        TypedQuery<VistaPreviaAdjuntoAspiranteEntity> query = em.createQuery(OBTENER_VISTA_PREVIA_POR_ID_ADJUNTO, classType);
        query.setParameter("idAdjunto", idAdjunto);

        return query.getSingleResult();
    }

    public void eliminarPorIdAdjunto(int idAdjunto) {
        Query query = em.createQuery(ELIMINAR_POR_ID_ADJUNTO);
        query.setParameter("idAdjunto", idAdjunto);

        query.executeUpdate();
    }
}
