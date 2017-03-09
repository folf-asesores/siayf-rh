package mx.gob.saludtlax.rh.persistencia;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.TypedQuery;

import org.jboss.logging.Logger;

public class SectorRepository extends GenericRepository<SectorEntity, Integer>{

    private static final Logger LOGGER = Logger.getLogger(SectorRepository.class.getName());
    private static final String CONSULTAR_SECTORES = "from SectorEntity as s";
    private static final String CONSULTAR_DESCRIPCION_SECTOR_POR_CRITERIO = 
            "select sector.sector "
            + "  from SectorEntity as sector"
            + "  where sector.sector like :descripcion";
    private static final String CONSULTAR_ID_SECTOR_POR_DESCRIPCION = 
            "select sector.idSector "
            + "  from SectorEntity as sector"
            + "  where sector.sector = :descripcion";
	
    public List<SectorEntity> consultarSectores() {
        TypedQuery<SectorEntity> query = em.createQuery(CONSULTAR_SECTORES, SectorEntity.class);
        List<SectorEntity> sectores = query.getResultList();
        return sectores;
    }

    public List<String> consultarDescripcionSectorPorCriterio(String consulta) {
        StringBuilder sb = new StringBuilder();
        sb.append('%');
        sb.append(consulta);
        sb.append('%');

        TypedQuery<String> query = em.createQuery(CONSULTAR_DESCRIPCION_SECTOR_POR_CRITERIO, String.class);
        query.setParameter("descripcion", sb.toString());
        query.setMaxResults(10);
        
        return query.getResultList();
    }

    public Integer consultarIdSectorPorDescripcion(String descripcion) {
        TypedQuery<Integer> query = em.createQuery(CONSULTAR_ID_SECTOR_POR_DESCRIPCION, Integer.class);
        query.setParameter("descripcion", descripcion);
        List<Integer> resultList = query.getResultList();
        
        if(resultList != null && !resultList.isEmpty()) {
            if (resultList.size() > 1) {
                LOGGER.warnv("Se han encontrado m\u00e1s de un sector con la misma descripci\u00f3n: {0}", descripcion);
            }
            
            return resultList.get(0);
        } else {
            return null;
        }
    }
}