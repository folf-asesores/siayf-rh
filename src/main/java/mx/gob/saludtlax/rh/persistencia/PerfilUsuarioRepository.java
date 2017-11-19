
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.gob.saludtlax.rh.util.Configuracion;

public class PerfilUsuarioRepository extends GenericRepository<PerfilUsuarioEntity, Integer> implements Serializable {

    private static final long serialVersionUID = 7897606793185133307L;

    @PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
    EntityManager entityManager;

    /**
     * Devuelve el perfil solicitado por medio de su nombre
     */
    public PerfilUsuarioEntity perfil(Integer idPerfil) {
        return entityManager.find(PerfilUsuarioEntity.class, idPerfil);
    }

    /**
     * Devuelve la lista de perfiles registrados en el sistema
     */
    public List<PerfilUsuarioEntity> perfiles() {
        List<PerfilUsuarioEntity> listaPerfiles = entityManager.createQuery("SELECT a FROM PerfilUsuarioEntity a", PerfilUsuarioEntity.class).getResultList();
        return listaPerfiles;
    }

}