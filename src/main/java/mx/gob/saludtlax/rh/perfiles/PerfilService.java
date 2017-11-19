
package mx.gob.saludtlax.rh.perfiles;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import mx.gob.saludtlax.rh.persistencia.ConfiguracionPerfilModuloEntity;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionPerfilModuloRepository;
import mx.gob.saludtlax.rh.persistencia.PerfilEntity;
import mx.gob.saludtlax.rh.persistencia.PerfilRepository;
import mx.gob.saludtlax.rh.util.Configuracion;

@Stateless
public class PerfilService {

    @PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
    private EntityManager entityManager;

    @Inject
    private PerfilRepository perfilDAO;

    @Inject
    private ConfiguracionPerfilModuloRepository configuracionPerfilModuloDAO;

    public void crearPerfil(PerfilDTO perfilDTO) {
        PerfilEntity entity = new PerfilEntity();

        entity.setNombre(perfilDTO.getNombre());
        entity.setDescripcion(perfilDTO.getDescripcion());

        perfilDAO.crear(entity);
    }

    public List<PerfilDTO> listaPerfil() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT id_perfil AS idPerfil, "
                + "nombre, descripcion FROM perfiles");
        query.setResultTransformer(Transformers.aliasToBean(PerfilDTO.class));
        @SuppressWarnings("unchecked")
        List<PerfilDTO> result = query.list();
        return result;
    }

    public void editarPerfil(PerfilDTO perfilDTO) {

        PerfilEntity entity = perfilDAO.obtenerPorId(perfilDTO.getIdPerfil());

        entity.setNombre(perfilDTO.getNombre());
        entity.setDescripcion(perfilDTO.getDescripcion());

        perfilDAO.actualizar(entity);

    }

    public void eliminarPerfil(Integer idPerfil) {
        List<ConfiguracionPerfilModuloEntity> perfilModuloEntity = configuracionPerfilModuloDAO
                .obtenerListaPorIdPerfil(idPerfil);

        if (perfilModuloEntity != null && perfilModuloEntity.size() > 0) {
            for (ConfiguracionPerfilModuloEntity cpm : perfilModuloEntity) {
                configuracionPerfilModuloDAO.eliminar(cpm);
            }
        }

        perfilDAO.eliminarPorId(idPerfil);

    }

}