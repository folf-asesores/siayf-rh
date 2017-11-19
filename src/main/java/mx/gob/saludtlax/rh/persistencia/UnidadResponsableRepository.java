
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import javax.persistence.TypedQuery;

import org.jboss.logging.Logger;

public class UnidadResponsableRepository extends GenericRepository<UnidadResponsableEntity, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = 7955665164066909467L;
    private static final String CONSULTAR_UNIDADES_RESPONSABLES = "from UnidadResponsableEntity as unidadResponsable";
    private static final String CONSULTAR_DESCRIPCION_UNIDADES_RESPONSABLES_POR_CRITERIO = "select unidadResponsable.descripcion"
            + " from UnidadResponsableEntity as unidadResponsable" + " where unidadResponsable.descripcion like :descripcion";
    private static final String CONSULTAR_ID_UNIDAD_RESPONSABLE_POR_DESCRIPCION = "select unidadResponsable.idUnidadResponsable"
            + " from UnidadResponsableEntity as unidadResponsable" + " where unidadResponsable.descripcion = :descripcion";

    private static final Logger LOGGER = Logger.getLogger(UnidadResponsableRepository.class.getName());

    public List<UnidadResponsableEntity> consultarUnidadesResponsables() {
        TypedQuery<UnidadResponsableEntity> query = em.createQuery(CONSULTAR_UNIDADES_RESPONSABLES, classType);
        return query.getResultList();
    }

    public List<String> consultarDescripcionUnidadesResponsablesPorCriterio(String consulta) {
        StringBuilder sb = new StringBuilder();
        sb.append('%');
        sb.append(consulta);
        sb.append('%');

        TypedQuery<String> query = em.createQuery(CONSULTAR_DESCRIPCION_UNIDADES_RESPONSABLES_POR_CRITERIO, String.class);
        query.setParameter("descripcion", sb.toString());
        query.setMaxResults(10);

        return query.getResultList();
    }

    public Integer consultarIdUnidadResponsablePorDescripcion(String descripcion) {
        TypedQuery<Integer> query;

        query = em.createQuery(CONSULTAR_ID_UNIDAD_RESPONSABLE_POR_DESCRIPCION, Integer.class);
        query.setParameter("descripcion", descripcion);

        List<Integer> resultList = query.getResultList();

        if (resultList != null && !resultList.isEmpty()) {
            if (resultList.size() > 1) {
                LOGGER.warnv("Existen m\u00e1s entidades que contienen la descripci\u00f3n: {0}", descripcion);
            }

            return resultList.get(0);
        } else {
            return null;
        }
    }

    public List<UnidadResponsableEntity> consultarUnidadResponsable() {
        List<UnidadResponsableEntity> unidades_responsables = em.createQuery("select u from UnidadResponsableEntity as u", UnidadResponsableEntity.class)
                .getResultList();
        return unidades_responsables;
    }

    public List<UnidadResponsableEntity> consultarUnidadResponsablePorDependencia(Integer idDependencia) {
        List<UnidadResponsableEntity> unidades_responsables = em
                .createQuery("SELECT u FROM UnidadResponsableEntity AS u WHERE u.idDependencia =:idDependencia", UnidadResponsableEntity.class)
                .setParameter("idDependencia", idDependencia).getResultList();
        return unidades_responsables;
    }
}
