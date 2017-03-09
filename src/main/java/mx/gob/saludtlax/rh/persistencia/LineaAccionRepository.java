/*
 * 
 * LineaAccionRepository.java
 * Creado el Jul 27, 2016 1:37:21 PM
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import javax.persistence.TypedQuery;

import org.jboss.logging.Logger;

/**
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
public class LineaAccionRepository extends GenericRepository<LineaAccionEntity, Integer> {

    private static final Logger LOGGER = Logger.getLogger(LineaAccionRepository.class.getName());
    
    private static final String CONSULTAR_DESCRIPCION_LINEA_ACCION_POR_DESCRIPCION = 
            "select lineaAccion.lineaAccion"
            + "  from LineaAccionEntity as lineaAccion"
            + "  where lineaAccion.lineaAccion like :lineaAccion";
    private static final String CONSULTAR_ID_LINEA_ACCION_POR_DESCRIPCION = 
            "select lineaAccion.idLineaAccion"
            + "  from LineaAccionEntity as lineaAccion"
            + "  where lineaAccion.lineaAccion = :descripcion"; 
    
    public List<String> consultarDescripcionLineaAccionPorCriterio(String consulta) {
        StringBuilder sb = new StringBuilder();
        sb.append('%');
        sb.append(consulta);
        sb.append('%');
        
        TypedQuery<String> query = em.createQuery(CONSULTAR_DESCRIPCION_LINEA_ACCION_POR_DESCRIPCION, String.class);
        query.setParameter("lineaAccion", sb.toString());
        query.setMaxResults(10);
        
        return query.getResultList();
    }

    public Integer consultarIdLineaAccionPorDescripcion(String descripcion) {
        TypedQuery<Integer> query = em.createQuery(CONSULTAR_ID_LINEA_ACCION_POR_DESCRIPCION, Integer.class);
        query.setParameter("descripcion", descripcion);
        
        List<Integer> resultList = query.getResultList();
        
        if(resultList != null && !resultList.isEmpty()) {
            if(resultList.size() > 1) {
                LOGGER.warn("Se han encontrado más de una linea de acción"
                        + " con la misma descripción. Se tomara la primera como"
                        + " valor por defecto.");
            }
            
            return resultList.get(0);
        } else {
            return null;
        }
    }
}
