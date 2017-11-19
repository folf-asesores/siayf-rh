package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import javax.persistence.TypedQuery;

import org.jboss.logging.Logger;

public class AreasAdscripcionRepository extends GenericRepository<AreaAdscripcionEntity, Integer> {

    /**
	 * 
	 */
	
	private static final Logger LOGGER = Logger.getLogger(AreasAdscripcionRepository.class.getName());

	public List<AreaAdscripcionEntity> listaAreas() {
		List<AreaAdscripcionEntity> areas_adscripcion = em
				.createQuery("select c from AreaAdscripcionEntity as c", AreaAdscripcionEntity.class).getResultList();
		return areas_adscripcion;
	}

	public List<AreaAdscripcionEntity> consultar_areas() {
		List<AreaAdscripcionEntity> areas_adscripcion = em
				.createQuery("select c from AreaAdscripcionEntity as c", AreaAdscripcionEntity.class).getResultList();
		return areas_adscripcion;
	}
	public List<AreaAdscripcionEntity> consultarAreaPorClave() {
		List<AreaAdscripcionEntity> areas_adscripcion = em
				.createQuery("select clave from AreaAdscripcionEntity  ", AreaAdscripcionEntity.class).getResultList();
		return areas_adscripcion;
	}
	
	public  boolean  existeClave(String clave) {
		boolean existe = true;
		String resultado = em.createQuery(
				"select clave from AreaAdscripcionEntity where clave=:clave",
						String.class).setParameter("clave", clave).getSingleResult();
		if (resultado==null){
			existe = false;
		}else {
			existe =true;
		}
		return existe;
	}

    public List<String> consultarAreaAdscripcionPorCriterio(String consulta) {
            StringBuilder sb = new StringBuilder();
            sb.append('%');
            sb.append(consulta);
            sb.append('%');
            TypedQuery<String> query = em.createQuery(
                    "select area.areaAdscripcion"
                    + "  from AreaAdscripcionEntity as area"
                    + "  where area.areaAdscripcion like :areaAdscripcion", String.class);
            query.setParameter("areaAdscripcion", sb.toString());
            query.setMaxResults(10);
            
            return query.getResultList();
    }

    public Integer consultarIdAreaAdscripcionPorDescripcion(String descripcion) {
        TypedQuery<Integer> query = em.createQuery(
                "select area.idAreaAdscripcion"
                + "  from AreaAdscripcionEntity as area"
                + "  where area.areaAdscripcion = :descripcion", Integer.class);
        query.setParameter("descripcion", descripcion);
        List<Integer> resultList = query.getResultList();
        
        if(resultList != null && !resultList.isEmpty()) {
            if(resultList.size() > 1) {
                LOGGER.warn("Se han econtrado más de un área de adscripción"
                        + " con la misma descripción. Se tomara el primero"
                        + " como valor por defecto.");
            }
            return resultList.get(0);
        } else {
            return null;
        }
    }
    
}
