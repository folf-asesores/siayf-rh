package mx.gob.saludtlax.rh.areas;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import mx.gob.saludtlax.rh.persistencia.AreaEntity;
import mx.gob.saludtlax.rh.persistencia.AreasRepository;
import mx.gob.saludtlax.rh.siif.reportarcontratos.BusinessException;
import mx.gob.saludtlax.rh.util.Configuracion;

public class AreasService {

	@PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
	private EntityManager entityManager;
	
	@Inject
	private AreasRepository areasDAO;
	
	
	protected void crearArea(AreaDTO areaDTO){
		AreaEntity areaEntity = new AreaEntity();
		
		areaEntity.setNombreArea(areaDTO.getNombreArea());
		areaEntity.setAreaPadre(areaDTO.getAreaPadre());
		areaEntity.setDescripcion(areaDTO.getDescripcion());
		areaEntity.setTitular(areaDTO.getTitular());
		try{
		areasDAO.crear(areaEntity);
		}catch(PersistenceException e){
			throw new BusinessException();
		}
	}
	
	protected void editarArea(AreaDTO areaDTO){
		AreaEntity areaEntity = areasDAO.obtenerPorId(areaDTO.getIdArea());
		
		
		areaEntity.setNombreArea(areaDTO.getNombreArea());
		areaEntity.setAreaPadre(areaDTO.getAreaPadre());
		areaEntity.setDescripcion(areaDTO.getDescripcion());
		areaEntity.setTitular(areaDTO.getTitular());
		try{
		areasDAO.actualizar(areaEntity);
		}catch(PersistenceException e){
			throw new BusinessException();
		}
	}
	
	protected void eliminar(Integer IdArea){
		areasDAO.eliminarPorId(IdArea);
	}
	
	protected List<AreaDTO> obtenerAreas(){
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery(
				"SELECT id_area AS idArea, "
				+ "nombre_area AS nombreArea, "
				+ "area_padre As areaPadre, "
				+ "descripcion AS descripcion, "
				+ "titular AS titular "
				+ "FROM areas");
		query.setResultTransformer(Transformers.aliasToBean(AreaDTO.class));
		@SuppressWarnings("unchecked")
		List<AreaDTO> result = (List<AreaDTO>) query.list();
		return result;
		
	}
	
	
}
