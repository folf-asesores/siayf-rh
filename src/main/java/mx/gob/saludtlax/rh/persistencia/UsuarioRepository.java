
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.jboss.logging.Logger;

public class UsuarioRepository extends GenericRepository<UsuarioEntity, Integer>
        implements Serializable {

    private static final long serialVersionUID = 2519772084802925292L;
    private static final Logger LOGGER = Logger
            .getLogger(UsuarioRepository.class.getName());

    private static final String EXISTE_USUARIO = "select"
            + "   case count(usuario.userName)" + "      when 0 then false"
            + "      else true" + "   end" + " from UsuarioEntity as usuario"
            + " where usuario.userName = :nombreUsuario";

    private static final String OBTENER_USUARIO_POR_HASH_TOKEN = "select token.usuario"
            + " from TokenEntity as token" + " where token.token = :hashToken";

    /**
     * Permite guardar un usuario en el almacen de datos.
     *
     * @param usuario
     *            el usuario a guardar.
     * @return el usuario guardado.
     */
    @Override
    public UsuarioEntity crear(UsuarioEntity usuario) {
        usuario.setFechaAlta(new Date());
        return super.crear(usuario);
    }

    /**
     * Consulta la existencia de un usuario por medio de su username
     *
     * @param nombreUsuario
     *            el nombre del usuario a validar.
     * @return verdad sí y sólo si el usuario existe en el almacen de datos.
     */
    public boolean existeUsuario(String nombreUsuario) {
        TypedQuery<Boolean> query = em.createQuery(EXISTE_USUARIO,
                Boolean.class);
        query.setParameter("nombreUsuario", nombreUsuario);

        return query.getSingleResult();
    }

    /**
     * Devuelve un usuario por medio de su user name
     *
     * @param nombreUsuario
     *            el nombre de usuario a buscar.
     * @return un usuario si existe en el almacen de datos.
     */
    public UsuarioEntity obtenerUsuarioPorNombreUsuario(String nombreUsuario) {
        List<UsuarioEntity> resultado = em.createQuery(
                "SELECT a FROM UsuarioEntity AS a WHERE a.userName = :userName",
                UsuarioEntity.class).setParameter("userName", nombreUsuario)
                .getResultList();
        UsuarioEntity usuario = null;

        if (!resultado.isEmpty()) {
            usuario = resultado.get(0);
        }

        return usuario;
    }

    public UsuarioEntity obtenerUsuarioPorHashToken(String hashToken) {
        TypedQuery<UsuarioEntity> query = em
                .createQuery(OBTENER_USUARIO_POR_HASH_TOKEN, classType);
        query.setParameter("hashToken", hashToken);

        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            LOGGER.warnv("Hash token {0} no se ha encontrado", hashToken);
            return null;
        } catch (NonUniqueResultException ex) {
            LOGGER.warnv("Hash token {0} duplicado", hashToken);
            return null;
        }
    }

    /**
     * Obtiene los usuarios que se encuentra en el almacen de datos.
     *
     * @return una lista con los usuarios del almacen de datos.
     */
    public List<UsuarioEntity> consultarTodosUsuarios() {
        List<UsuarioEntity> lista = em
                .createQuery("from UsuarioEntity as usuario",
                        UsuarioEntity.class)
                .getResultList();

        return lista;
    }

    /**
     * Consulta todos los usuarios activos en el sistema.
     *
     * @return una lista con los usuarios que están activos.
     */
    public List<UsuarioEntity> consultarUsuariosActivos() {
        return consultarUsuarios(true);
    }

    /**
     * Consulta todos los usuarios inactivos en el sistema
     *
     * @return una lista con los usuarios que no están activos.
     */
    public List<UsuarioEntity> consultarUsuariosInactivos() {
        return consultarUsuarios(false);
    }

    private List<UsuarioEntity> consultarUsuarios(boolean activo) {
        List<UsuarioEntity> lista = em.createQuery(
                "SELECT a FROM UsuarioEntity AS a WHERE a.activo = :activo",
                UsuarioEntity.class).setParameter("activo", activo)
                .getResultList();
        return lista;
    }

    /**
     * Consulta un usuario en base a un criterio de busqueda, siendo este su
     * username o su correo electrónico.
     *
     * @param criterioBusqueda
     *            un criterio de busqueda.
     * @return el usuario que coincide con el criterio de busqueda.
     */
    public UsuarioEntity buscarUsuario(String criterioBusqueda) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<UsuarioEntity> q = cb.createQuery(UsuarioEntity.class);
        Root<UsuarioEntity> root = q.from(UsuarioEntity.class);
        final Predicate disjunction = cb.disjunction();

        if (!StringUtils.isBlank(criterioBusqueda)) {
            disjunction.getExpressions()
                    .add(cb.like(root.get("userName").as(String.class),
                            "%" + criterioBusqueda + "%"));
            disjunction.getExpressions()
                    .add(cb.like(root.get("correo").as(String.class),
                            "%" + criterioBusqueda + "%"));
        }
        q.select(root).where(disjunction);
        UsuarioEntity usuario = null;
        List<UsuarioEntity> resultado = em.createQuery(q).getResultList();
        if (!resultado.isEmpty()) {
            usuario = resultado.get(0);
        }
        return usuario;
    }

    public List<String> recursosPorUsuario(Integer idUsuario) {
        Session session = em.unwrap(Session.class);
        Query query = session
                .createSQLQuery("CALL usp_usuario_modulos(:idUsuario)")
                .setParameter("idUsuario", idUsuario);
        //query.setResultTransformer(Transformers.aliasToBean(String.class));
        @SuppressWarnings("unchecked")
        List<String> result = query.list();
        return result;
    }

    //Regresa la lista de acciones a todos los modulos que tenga asignado el usuario.
    public List<String> accionesPorUsuario(Integer idUsuario) {
        Session session = em.unwrap(Session.class);
        Query query = session
                .createSQLQuery("CALL usp_acciones_de_usuario(:idUsuario)")
                .setParameter("idUsuario", idUsuario);
        //query.setResultTransformer(Transformers.aliasToBean(String.class));
        @SuppressWarnings("unchecked")
        List<String> result = query.list();
        return result;
    }

    public Boolean tieneClaveAccion(Integer idUsuario, String claveAccion) {
        List<String> acciones = new ArrayList<>();
        acciones = accionesPorUsuario(idUsuario);
        Boolean res = false;

        if (acciones.contains(claveAccion)) {
            res = true;
        }
        return res;
    }
}
