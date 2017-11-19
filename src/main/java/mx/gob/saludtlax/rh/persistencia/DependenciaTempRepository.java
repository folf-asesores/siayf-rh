/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import javax.persistence.TypedQuery;

import org.jboss.logging.Logger;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 14/08/2016 22:50:50
 */
public class DependenciaTempRepository extends GenericRepository<DependenciaTempEntity, Integer> {
    private static final Logger LOGGER = Logger.getLogger(DependenciaTempRepository.class.getName());
    /**
     *
     */
    private static final long serialVersionUID = 5053724808417971804L;

    private static final String CONSULTAR_DESCRIPCION_DEPENDENCIAS_POR_CRITERIO = "select dependencia.descripcion"
            + " from DependenciaTempEntity as dependencia" + " where dependencia.descripcion like :descripcion";

    private static final String CONSULTAR_ID_DEPENDENCIA_POR_DESCRIPCION = "select dependencia.idDependencia" + " from DependenciaTempEntity as dependencia"
            + " where dependencia.descripcion = :descripcion";

    public List<DependenciaTempEntity> consultarDependencias() {
        return em.createQuery("SELECT d FROM DependenciaTempEntity AS d", DependenciaTempEntity.class).getResultList();
    }

    public List<String> consultarDescripcionDependenciasPorCriterio(String consulta) {
        StringBuilder sb = new StringBuilder();
        sb.append('%');
        sb.append(consulta);
        sb.append('%');

        TypedQuery<String> query = em.createQuery(CONSULTAR_DESCRIPCION_DEPENDENCIAS_POR_CRITERIO, String.class);
        query.setParameter("descripcion", sb.toString());
        query.setMaxResults(10);

        return query.getResultList();
    }

    public Integer consultarIdDependenciaPorDescripcion(String descripcion) {
        TypedQuery<Integer> query = em.createQuery(CONSULTAR_ID_DEPENDENCIA_POR_DESCRIPCION, Integer.class);
        query.setParameter("descripcion", descripcion);

        List<Integer> resultList = query.getResultList();

        if (resultList != null && !resultList.isEmpty()) {
            if (resultList.size() > 1) {
                LOGGER.warnv("Se encontro m\u00e1s de una dependecia con la descripcion: {0}", descripcion);
            }

            return resultList.get(0);
        } else {
            return null;
        }
    }
}
