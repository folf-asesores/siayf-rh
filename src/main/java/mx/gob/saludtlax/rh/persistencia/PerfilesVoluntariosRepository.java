
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import javax.persistence.NoResultException;

public class PerfilesVoluntariosRepository
        extends GenericRepository<PerfilesVoluntariosRepository, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = 4067534164478836054L;

    public PerfilesVoluntariosEntity perfilPorId(Integer idPerfilVoluntario) {
        try {
            return em.createQuery(
                    "SELECT pv FROM PerfilesVoluntariosEntity AS pv WHERE pv.id_perfil_voluntario =:idPerfilVoluntario",
                    PerfilesVoluntariosEntity.class)
                    .setParameter("idPerfilVoluntario", idPerfilVoluntario)
                    .getSingleResult();
        } catch (NoResultException exception) {
            return null;
        }

    }

    public List<PerfilesVoluntariosEntity> consultarVoluntarios() {
        return em.createQuery("SELECT p FROM PerfilesVoluntariosEntity AS p ",
                PerfilesVoluntariosEntity.class).getResultList();
    }

}