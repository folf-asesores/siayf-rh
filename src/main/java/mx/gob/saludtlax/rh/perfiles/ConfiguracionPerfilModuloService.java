package mx.gob.saludtlax.rh.perfiles;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.gob.saludtlax.rh.acciones.AccionDTO;
import mx.gob.saludtlax.rh.modulos.ConfiguracionModuloAccionDTO;
import mx.gob.saludtlax.rh.modulos.ModuloDTO;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionModuloAccionRepository;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionPerfilModuloRepository;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionPerfilModuloEntity;
import mx.gob.saludtlax.rh.persistencia.PerfilRepository;
import mx.gob.saludtlax.rh.util.Configuracion;

public class ConfiguracionPerfilModuloService {

		@PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
	private EntityManager entityManager;

	@Inject
	private PerfilRepository perfilDAO;
	
	@Inject
	private ConfiguracionPerfilModuloRepository configuracionPerfilModuloDAO;
	
	@Inject
	private ConfiguracionModuloAccionRepository configuracionModuloAccionDAO;
	
	
	public void crearConfiguracion(ConfiguracionPerfilModuloDTO configuracionPerfilModuloDTO){
		ConfiguracionPerfilModuloEntity entity = new ConfiguracionPerfilModuloEntity();
		entity.setConfiguracionModuloAccion(configuracionModuloAccionDAO.obtenerPorId(configuracionPerfilModuloDTO.getConfiguracionModuloAccionDTO().getId_configuracion_modulo_accion()));
		entity.setFechaCreacion(new Date());
		entity.setPerfil(perfilDAO.obtenerPorId(configuracionPerfilModuloDTO.getPerfil().getIdPerfil()));
		
		configuracionPerfilModuloDAO.crear(entity);
	}
	
	public void editarConfiguracion(ConfiguracionPerfilModuloDTO configuracionPerfilModuloDTO){
		ConfiguracionPerfilModuloEntity entity = configuracionPerfilModuloDAO.obtenerPorId(configuracionPerfilModuloDTO.getId_perfil_modulo());
		entity.setConfiguracionModuloAccion(configuracionModuloAccionDAO.obtenerPorId(configuracionPerfilModuloDTO.getConfiguracionModuloAccionDTO().getId_configuracion_modulo_accion()));
		entity.setFechaCreacion(new Date());
		entity.setPerfil(perfilDAO.obtenerPorId(configuracionPerfilModuloDTO.getPerfil().getIdPerfil()));
		
		configuracionPerfilModuloDAO.crear(entity);
	}
	
	public void eliminarConfiguracion(Integer id){
		configuracionPerfilModuloDAO.eliminarPorIdPerfil(id);
	}
	
	
	public List<ConfiguracionPerfilModuloDTO> obtenerRegistros(){
		List<ConfiguracionPerfilModuloDTO> listDto = new ArrayList<>();
		List<ConfiguracionPerfilModuloEntity> listEntity = new ArrayList<>();
		
		listEntity = configuracionPerfilModuloDAO.obtenerLista();
		
		for(ConfiguracionPerfilModuloEntity cE: listEntity){
			ConfiguracionPerfilModuloDTO dto = new ConfiguracionPerfilModuloDTO();
			
			dto.setId_perfil_modulo(cE.getId_perfil_modulo());
			dto.setFechaCreacion(new Date());
			
			ConfiguracionModuloAccionDTO configuracionModuloAccionDTO = new ConfiguracionModuloAccionDTO();
			configuracionModuloAccionDTO.setId_configuracion_modulo_accion(cE.getConfiguracionModuloAccion().getId_configuracion_modulo_accion());
			AccionDTO accion = new AccionDTO();
//			accion.setClave(cE.getConfiguracionModuloAccion().getId_accion().getClave());
//			accion.setDescripcion(cE.getConfiguracionModuloAccion().getId_accion().getDescripcion());
//			accion.setId_accion(cE.getConfiguracionModuloAccion().getId_accion().getId_accion());
//			accion.setIdArea(cE.getConfiguracionModuloAccion().getId_accion().getArea().getIdArea());
//			accion.setNombreArea(cE.getConfiguracionModuloAccion().getId_accion().getArea().getNombreArea());
//			configuracionModuloAccionDTO.setAccion(accion);
			
			ModuloDTO modulo = new ModuloDTO();
			modulo.setHabilitado(cE.getConfiguracionModuloAccion().getId_Modulo().getHabilitado());
			modulo.setId_modulo(cE.getConfiguracionModuloAccion().getId_Modulo().getId_modulo());
			modulo.setIdArea(cE.getConfiguracionModuloAccion().getId_Modulo().getArea().getIdArea());
			modulo.setNombre(cE.getConfiguracionModuloAccion().getId_Modulo().getNombre());
			modulo.setNombreArea(cE.getConfiguracionModuloAccion().getId_Modulo().getArea().getNombreArea());
			modulo.setUrl(cE.getConfiguracionModuloAccion().getId_Modulo().getUrl());
			
			configuracionModuloAccionDTO.setModulo(modulo);
			
			
			dto.setConfiguracionModuloAccionDTO(configuracionModuloAccionDTO);
			
			listDto.add(dto);
		}
		
		return listDto;
		
	}
	
	public List<ConfiguracionPerfilModuloDTO> obtenerRegistrosPorIdPerfil(Integer idPerfil){
		List<ConfiguracionPerfilModuloDTO> listDto = new ArrayList<>();
		List<ConfiguracionPerfilModuloEntity> listEntity = new ArrayList<>();
		
		listEntity = configuracionPerfilModuloDAO.obtenerListaPorIdPerfil(idPerfil);
		
		for(ConfiguracionPerfilModuloEntity cE: listEntity){
			ConfiguracionPerfilModuloDTO dto = new ConfiguracionPerfilModuloDTO();
			
			dto.setId_perfil_modulo(cE.getId_perfil_modulo());
			dto.setFechaCreacion(new Date());
			
			ConfiguracionModuloAccionDTO configuracionModuloAccionDTO = new ConfiguracionModuloAccionDTO();
			configuracionModuloAccionDTO.setId_configuracion_modulo_accion(cE.getConfiguracionModuloAccion().getId_configuracion_modulo_accion());
			AccionDTO accion = new AccionDTO();
//			accion.setClave(cE.getConfiguracionModuloAccion().getId_accion().getClave());
//			accion.setDescripcion(cE.getConfiguracionModuloAccion().getId_accion().getDescripcion());
//			accion.setId_accion(cE.getConfiguracionModuloAccion().getId_accion().getId_accion());
//			accion.setIdArea(cE.getConfiguracionModuloAccion().getId_accion().getArea().getIdArea());
//			accion.setNombreArea(cE.getConfiguracionModuloAccion().getId_accion().getArea().getNombreArea());
//			configuracionModuloAccionDTO.setAccion(accion);
			
			ModuloDTO modulo = new ModuloDTO();
			modulo.setHabilitado(cE.getConfiguracionModuloAccion().getId_Modulo().getHabilitado());
			modulo.setId_modulo(cE.getConfiguracionModuloAccion().getId_Modulo().getId_modulo());
			modulo.setIdArea(cE.getConfiguracionModuloAccion().getId_Modulo().getArea().getIdArea());
			modulo.setNombre(cE.getConfiguracionModuloAccion().getId_Modulo().getNombre());
			modulo.setNombreArea(cE.getConfiguracionModuloAccion().getId_Modulo().getArea().getNombreArea());
			modulo.setUrl(cE.getConfiguracionModuloAccion().getId_Modulo().getUrl());
			
			configuracionModuloAccionDTO.setModulo(modulo);
			
			
			dto.setConfiguracionModuloAccionDTO(configuracionModuloAccionDTO);
			
			listDto.add(dto);
		}
		
		return listDto;
		
	}
	
}
