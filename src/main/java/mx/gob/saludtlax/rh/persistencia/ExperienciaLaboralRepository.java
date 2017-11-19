/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.util.List;

/**
 * @author Leila Schiaffini Ehuan
 * @since 09/06/2016 12:41:50
 *
 */
public class ExperienciaLaboralRepository extends GenericRepository<ExperienciaLaboralEntity, Integer> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 2839001731081792873L;

    /**
     * Lista de Experiencia Laboral por id Aspirante
     *
     * @param idAspirante
     * @return
     */
    public List<ExperienciaLaboralEntity> consultarExperienciasLaboralesAspirante(Integer idAspirante) {
        return em.createQuery("SELECT e FROM ExperienciaLaboralEntity AS e WHERE e.aspirante.idAspirante =:idAspirante", ExperienciaLaboralEntity.class)
                .setParameter("idAspirante", idAspirante).getResultList();
    }

    public List<ExperienciaLaboralEntity> consultarExperienciasEmpleado(Integer idEmpleado) {
        return em.createQuery("SELECT e FROM ExperienciaLaboralEntity AS e WHERE e.idEmpleado =:idEmpleado", ExperienciaLaboralEntity.class)
                .setParameter("idEmpleado", idEmpleado).getResultList();
    }

}
