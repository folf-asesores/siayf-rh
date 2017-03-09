package mx.gob.saludtlax.rh.modulos;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import mx.gob.saludtlax.rh.areas.AreaDTO;
import mx.gob.saludtlax.rh.persistencia.AreasRepository;
import mx.gob.saludtlax.rh.persistencia.ModuloRepository;
import mx.gob.saludtlax.rh.persistencia.ModuloEntity;

public class ModulosService {
	@PersistenceContext(name="siayfrhPU")
	private EntityManager entityManager;
	
	@Inject
	private ModuloRepository moduloDAO;
	@Inject
	private AreasRepository areasDAO;

	@Inject
	private ConfiguracionModuloAccion configuracionModuloAccion;
	public void crear(ModuloDTO dto) {
		ModuloEntity entity = new ModuloEntity();
		entity.setArea(areasDAO.obtenerPorId(dto.getIdArea()));
		entity.setHabilitado(dto.getHabilitado());
		entity.setNombre(dto.getNombre());
		entity.setUrl(dto.getUrl());
		moduloDAO.crear(entity);

	}

	public void editar(ModuloDTO dto) {
		ModuloEntity entity = moduloDAO.obtenerPorId(dto.getId_modulo());

		entity.setArea(areasDAO.obtenerPorId(dto.getIdArea()));
		entity.setHabilitado(dto.getHabilitado());
		entity.setNombre(dto.getNombre());
		entity.setUrl(dto.getUrl());
		moduloDAO.actualizar(entity);

	}

	public Boolean  eliminar(Integer id) {
	   
		Boolean res = true;	
		List<ConfiguracionModuloAccionDTO> listConf = new ArrayList<>();
		listConf = configuracionModuloAccion.obtenerListaConfiguracionModuloAccionDTOPorModulo(id);
		if(listConf==null||listConf.isEmpty()){
			moduloDAO.eliminarPorId(id);
			res = true;
		}else{
			res = false;
		}
		return res;
		
		
	}

	public List<ModuloDTO> listaModulos() {
		List<ModuloEntity> listEntity = new ArrayList<>();
		listEntity = moduloDAO.obtenerListaModulos();
		List<ModuloDTO> listDTO = new ArrayList<>();

		for (ModuloEntity entity : listEntity) {
			ModuloDTO dto = new ModuloDTO();

			dto.setHabilitado(entity.getHabilitado());
			dto.setId_modulo(entity.getId_modulo());
			dto.setIdArea(entity.getArea().getIdArea());
			dto.setNombre(entity.getNombre());
			dto.setNombreArea(entity.getArea().getNombreArea());
			dto.setUrl(entity.getUrl());

			listDTO.add(dto);
		}
		return listDTO;
	}

	public List<ModuloDTO> listaModulosPorArea(Integer idArea) {
		try {
			List<ModuloEntity> listEntity = new ArrayList<>();
			listEntity = moduloDAO.obtenerModulosPorIdArea(idArea);
			List<ModuloDTO> listDTO = new ArrayList<>();

			for (ModuloEntity entity : listEntity) {
				ModuloDTO dto = new ModuloDTO();

				dto.setHabilitado(entity.getHabilitado());
				dto.setId_modulo(entity.getId_modulo());
				dto.setIdArea(entity.getArea().getIdArea());
				dto.setNombre(entity.getNombre());
				dto.setNombreArea(entity.getArea().getNombreArea());
				dto.setUrl(entity.getUrl());

				listDTO.add(dto);
			}
			return listDTO;
		} catch (NoResultException e) {
			return null;
		}
	}
	public List<AreaDTO> listaArea() {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("SELECT "
				+ "id_area AS id, "
				+ "nombre_area AS nombre "
				+ "FROM areas");
		query.setResultTransformer(Transformers.aliasToBean(AreaDTO.class));
		@SuppressWarnings("unchecked")
		List<AreaDTO> result = (List<AreaDTO>) query.list();
		return result;
	}
	
}
