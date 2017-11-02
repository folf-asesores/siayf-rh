/*
 * NotificacionRepository.java
 * Creado el Aug 10, 2016 11:44:31 AM
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * Esta clase tiene todas las consultas relacionadas con las notificaciones.
 * 
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class NotificacionRepository extends GenericRepository<NotificacionEntity, Integer>{

    private static final String OBTENER_POR_TOKEN = 
            "select nde.notificacion"
            + " from NotificacionDestinatarioEntity as nde"
            + " where nde.token = :token";
    private static final String EXISTE_TOKEN = 
            "select"
            + "   case count(notificacion.idNotificacionDestinatario)" 
            + "      when 0 then false"
            + "      else true" 
            + "   end" 
            + " from NotificacionDestinatarioEntity as notificacion"
            + "    where notificacion.token = :token";
    private static final String CONSULTAR_NOTIFICACIONES_DEL_USUARIO = 
            "select destinatario.notificacion"
            + " from NotificacionDestinatarioEntity as destinatario"
            + " where destinatario.destinatario.idUsuario = :idUsuario"
            + " order by destinatario.notificacion.fechaPublicacion desc,"
            + " destinatario.notificacion.horaPublicacion desc";
    private static final String CONSULTAR_NOTIFICACIONES_DEL_USUARIO_VISTO = 
            "select destinatario.notificacion"
            + " from NotificacionDestinatarioEntity as destinatario"
            + " where destinatario.destinatario.idUsuario = :idUsuario and destinatario.visto = :visto"
            + " order by destinatario.notificacion.fechaPublicacion desc,"
            + " destinatario.notificacion.horaPublicacion desc";
    private static final String MARCAR_NOTIFICACION_COMO_VISTA =
            "update NotificacionDestinatarioEntity as nde"
            + " set nde.visto = true"
            + " where nde.token = :token";
    
    public NotificacionEntity obtenerPorToken(String token) {
        TypedQuery<NotificacionEntity> query = em.createQuery(OBTENER_POR_TOKEN, classType);
        query.setParameter("token", token);

        return query.getSingleResult();
    }

    public boolean existeToken(String token) {
        TypedQuery<Boolean> query = em.createQuery(EXISTE_TOKEN, Boolean.class);
        query.setParameter("token", token);
        return query.getSingleResult();
    }

    public List<NotificacionEntity> consutarNotificacionesPorIdUsuario(Integer idUsuario) {
        TypedQuery<NotificacionEntity> query = em
                .createQuery(CONSULTAR_NOTIFICACIONES_DEL_USUARIO, classType);
        query.setParameter("idUsuario", idUsuario);
        
        return query.getResultList();
    }
    
    public List<NotificacionEntity> consutarNotificacionesPorIdUsuarioVisto(Integer idUsuario, Boolean visto) {
        TypedQuery<NotificacionEntity> query = em
                .createQuery(CONSULTAR_NOTIFICACIONES_DEL_USUARIO_VISTO, classType);
        
        query.setParameter("idUsuario", idUsuario);
        query.setParameter("visto", visto);
        
        return query.getResultList();
    }

    public void marcarComoVista(String token) {
        Query query = em.createQuery(MARCAR_NOTIFICACION_COMO_VISTA);
        query.setParameter("token", token);
        query.executeUpdate();
    }
    
    @Override
    public void eliminar(NotificacionEntity entity) {
        // Las notificaciones no se eliminan se marcan como vistas
    }

    @Override
    public void eliminarPorId(Integer key) {
        // Las notificaciones no se eliminan se marcan como vistas
    }    
}
