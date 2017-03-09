package mx.gob.saludtlax.rh.acciones;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import mx.gob.saludtlax.rh.persistencia.AccionesRepository;
import mx.gob.saludtlax.rh.areas.AreaDTO;
import mx.gob.saludtlax.rh.modulos.ConfiguracionModuloAccion;
import mx.gob.saludtlax.rh.modulos.ConfiguracionModuloAccionDTO;
import mx.gob.saludtlax.rh.persistencia.AccionesEntity;
import mx.gob.saludtlax.rh.persistencia.AreasRepository;

public class AccionService {
	@PersistenceContext(name = "siayfrhPU")
	private EntityManager entityManager;
	
	@Inject
	private AccionesRepository accionesDAO;
	
	@Inject
	private AreasRepository areasDAO;
	
	@Inject
	private ConfiguracionModuloAccion configuracionModuloAccion;
	
	protected void crearAccion(AccionDTO accionDTO){
		AccionesEntity accionesEntity = new AccionesEntity();
		
		accionesEntity.setArea(areasDAO.obtenerPorId(accionDTO.getId_area()));
		accionesEntity.setClave(accionDTO.getClave());
		accionesEntity.setDescripcion(accionDTO.getDescripcion());
		
		accionesDAO.crear(accionesEntity);		
	}
	
	
	protected void editarAccion(AccionDTO accionDTO){
		AccionesEntity accionesEntity = accionesDAO.obtenerPorId(accionDTO.getId_accion());
		
		accionesEntity.setArea(areasDAO.obtenerPorId(accionDTO.getId_area()));
		accionesEntity.setClave(accionDTO.getClave());
		accionesEntity.setDescripcion(accionDTO.getDescripcion());
		
		accionesDAO.actualizar(accionesEntity);		
	}

	
	protected List<AccionDTO> obtenerAcciones(){
		List<AccionDTO> listDto = new ArrayList<>();
		
		List<AccionesEntity> listEntity = new ArrayList<>();
		listEntity = accionesDAO.obtenerListaAcciones();
		
		for(AccionesEntity a:listEntity){
			AccionDTO aDto = new AccionDTO();
			aDto.setClave(a.getClave());
			aDto.setDescripcion(a.getDescripcion());
			aDto.setId_accion(a.getId_accion());
			aDto.setId_area(a.getArea().getIdArea());
			aDto.setNombreArea(a.getArea().getNombreArea());
			
			listDto.add(aDto);
		} 
		
		return listDto;
		
	}
	
	public List<AccionDTO> obtenerAccionesPorArea(Integer idArea){
		List<AccionDTO> listDto = new ArrayList<>();
		try{
		List<AccionesEntity> listEntity = new ArrayList<>();
		listEntity = accionesDAO.obtenerListaAccionesPorIdArea(idArea);
		
		for(AccionesEntity a:listEntity){
			AccionDTO aDto = new AccionDTO();
			aDto.setClave(a.getClave());
			aDto.setDescripcion(a.getDescripcion());
			aDto.setId_accion(a.getId_accion());
			aDto.setId_area(a.getArea().getIdArea());
			aDto.setNombreArea(a.getArea().getNombreArea());
			
			listDto.add(aDto);
		} 
		
		return listDto;
		}catch(NoResultException e){
			return null;
		}
		
	}
	
	
	public boolean eliminarAccion(Integer idAccion){
			Boolean res = true;	
		List<ConfiguracionModuloAccionDTO> listConf = new ArrayList<>();
		listConf = configuracionModuloAccion.obtenerListaConfiguracionModuloAccionDTOPorAccion(idAccion);
		if(listConf==null||listConf.isEmpty()){
			accionesDAO.eliminarPorId(idAccion);
			res = true;
		}else{
			res = false;
		}
		return res;
		
	}
	
	public List<AreaDTO> listaArea() {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("SELECT "
				+ "id_area AS idArea, "
				+ "nombre_area AS nombreArea "
				+ "FROM areas");
		query.setResultTransformer(Transformers.aliasToBean(AreaDTO.class));
		@SuppressWarnings("unchecked")
		List<AreaDTO> result = (List<AreaDTO>) query.list();
		return result;
	}
}
