/*
 * VistaPreviaAdjuntoEmpleadoRepository.java
 * Creado el Aug 31, 2016 1:52:22 PM
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class VistaPreviaAdjuntoEmpleadoRepository
        extends GenericRepository<VistaPreviaAdjuntoEmpleadoEntity, Integer> {
    /**
     *
     */
    private static final long serialVersionUID = 936235235517375099L;
    private static final String OBTENER_VISTA_PREVIA_POR_ID_ADJUNTO = "select vistaPrevia"
            + " from VistaPreviaAdjuntoEmpleadoEntity as vistaPrevia"
            + " where vistaPrevia.informacionAdjuntoEmpleado.idInformacionAdjuntoEmpleado = :idAdjunto";
    private static final String ELIMINAR_POR_ID_ADJUNTO = "delete from VistaPreviaAdjuntoEmpleadoEntity as vistaPrevia"
            + " where vistaPrevia.informacionAdjuntoEmpleado.idInformacionAdjuntoEmpleado = :idAdjunto";

    public VistaPreviaAdjuntoEmpleadoEntity obtenerPorIdAdjunto(
            Integer idAdjunto) {
        TypedQuery<VistaPreviaAdjuntoEmpleadoEntity> query = em
                .createQuery(OBTENER_VISTA_PREVIA_POR_ID_ADJUNTO, classType);
        query.setParameter("idAdjunto", idAdjunto);

        return query.getSingleResult();
    }

    public void eliminarPorIdAdjunto(int idAdjunto) {
        Query query = em.createQuery(ELIMINAR_POR_ID_ADJUNTO);
        query.setParameter("idAdjunto", idAdjunto);

        query.executeUpdate();
    }

}
