
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class ConfiguracionPerfilModuloRepository extends GenericRepository<ConfiguracionPerfilModuloEntity, Integer> {

    private static final long serialVersionUID = 5134584308364700753L;
    private static final String OBTENER_LISTADO_POR_ID_PERFIL = "select c" + "  from ConfiguracionPerfilModuloEntity as c" + "  join c.perfil as perfil"
            + " where perfil.idPerfil = :idPerfil";
    private static final String ELIMINAR_POR_ID_PERFIL = "delete" + "  from ConfiguracionPerfilModuloEntity as c" + " where c.perfil.idPerfil = :idPerfil";

    public List<ConfiguracionPerfilModuloEntity> obtenerListaPorIdPerfil(Integer idPerfil) {
        TypedQuery<ConfiguracionPerfilModuloEntity> query = em.createQuery(OBTENER_LISTADO_POR_ID_PERFIL, ConfiguracionPerfilModuloEntity.class);
        query.setParameter("idPerfil", idPerfil);
        return query.getResultList();
    }

    public void eliminarPorIdPerfil(Integer idPerfil) {
        Query query = em.createQuery(ELIMINAR_POR_ID_PERFIL);
        query.setParameter("idPerfil", idPerfil);
        query.executeUpdate();
    }
}
