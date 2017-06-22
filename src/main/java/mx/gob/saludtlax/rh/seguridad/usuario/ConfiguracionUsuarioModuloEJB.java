package mx.gob.saludtlax.rh.seguridad.usuario;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.acciones.AccionDTO;
import mx.gob.saludtlax.rh.modulos.ConfiguracionModuloAccionDTO;
import mx.gob.saludtlax.rh.modulos.ModuloDTO;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionModuloAccionRepository;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionUsuarioModuloEntity;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionUsuarioModuloRepository;
import mx.gob.saludtlax.rh.persistencia.DetalleConfiguracionModuloAccionEntity;
import mx.gob.saludtlax.rh.persistencia.DetalleConfiguracionModuloAccionRepository;
import mx.gob.saludtlax.rh.persistencia.UsuarioRepository;

@Stateless
public class ConfiguracionUsuarioModuloEJB implements ConfiguracionUsuarioModulo {

	@Inject
	private ConfiguracionUsuarioModuloRepository dao;
	
		
	@Inject
	private UsuarioRepository usuarioDao;
	
	@Inject
	private ConfiguracionModuloAccionRepository configuracionModuloAccionRepository;
	
	@Inject
	private DetalleConfiguracionModuloAccionRepository detalleconfiguracionModuloAccionRepository;

	
	@Override
	public void crear(ConfiguracionUsuarioModuloDTO dto) {
		ConfiguracionUsuarioModuloEntity entity = new ConfiguracionUsuarioModuloEntity();
		entity.setFechaCreacion(new Date());
		entity.setConfiguracionModuloAccion(configuracionModuloAccionRepository.obtenerPorId(dto.getConfiguracionModulo().getId_configuracion_modulo_accion()));
		entity.setUsuario(usuarioDao.obtenerPorId(dto.getUsuario().getIdUsuario()));
		
		dao.crear(entity);
	}

	@Override
	public void editar(ConfiguracionUsuarioModuloDTO dto) {
		ConfiguracionUsuarioModuloEntity entity = dao.obtenerPorId(dto.getId_configuracion_usuario_modulo());
		entity.setFechaCreacion(new Date());
		entity.setConfiguracionModuloAccion(configuracionModuloAccionRepository.obtenerPorId(dto.getConfiguracionModulo().getId_configuracion_modulo_accion()));
		entity.setUsuario(usuarioDao.obtenerPorId(dto.getUsuario().getIdUsuario()));
		
		dao.actualizar(entity);
	}

	@Override
	public void eliminar(Integer id){
		dao.eliminarPorId(id);		
	}

	@Override
	public List<ConfiguracionUsuarioModuloDTO> obtenerLista() {
		List<ConfiguracionUsuarioModuloEntity> listEntity = dao.obtenerRegistros();
		List<ConfiguracionUsuarioModuloDTO> listDto = new ArrayList<>();
		for(ConfiguracionUsuarioModuloEntity ent:listEntity){
			ConfiguracionUsuarioModuloDTO dto = new ConfiguracionUsuarioModuloDTO();
			
			dto.setId_configuracion_usuario_modulo(ent.getId_configuracion_usuario_modulo());
			UsuarioDTO usrDto = new UsuarioDTO();
			usrDto.setIdUsuario(ent.getUsuario().getIdUsuario());
			usrDto.setUserName(ent.getUsuario().getUserName());
			dto.setUsuario(usrDto);
			
			
			ConfiguracionModuloAccionDTO cfn = new ConfiguracionModuloAccionDTO();
			cfn.setId_configuracion_modulo_accion(ent.getConfiguracionModuloAccion().getId_configuracion_modulo_accion());
			ModuloDTO mdl = new ModuloDTO();
			mdl.setId_modulo(ent.getConfiguracionModuloAccion().getId_Modulo().getId_modulo());
			mdl.setNombre(ent.getConfiguracionModuloAccion().getId_Modulo().getNombre());
			cfn.setModulo(mdl);
			cfn.setNombreConfiguracion(ent.getConfiguracionModuloAccion().getDescripcion());
List<AccionDTO> listaAcciones = new ArrayList<>();
			
			List<DetalleConfiguracionModuloAccionEntity> detalles = new ArrayList<>();
			detalles = detalleconfiguracionModuloAccionRepository.obtenerDetallesPorIdConfiguracion(
					ent.getConfiguracionModuloAccion().getId_configuracion_modulo_accion());
			for(DetalleConfiguracionModuloAccionEntity detalle : detalles){
				listaAcciones.add(new AccionDTO(detalle.getId_accion().getId_accion(), detalle.getId_accion().getClave(), detalle.getId_accion().getDescripcion(), detalle.getId_accion().getArea().getIdArea(), detalle.getId_accion().getModulo().getId_modulo(), detalle.getId_accion().getArea().getNombreArea()));
			}
			cfn.setAcciones(listaAcciones);
			dto.setConfiguracionModulo(cfn);

			listDto.add(dto);
		
		}
		return listDto;
	}

	@Override
	public List<ConfiguracionUsuarioModuloDTO> obtenerListaPorUsuario(Integer idUsuario) {
		List<ConfiguracionUsuarioModuloEntity> listEntity = dao.obtenerRegistrosPorUsuario(idUsuario);
		List<ConfiguracionUsuarioModuloDTO> listDto = new ArrayList<>();
		for(ConfiguracionUsuarioModuloEntity ent:listEntity){
			ConfiguracionUsuarioModuloDTO dto = toDto(ent);
			listDto.add(dto);
		}
		return listDto;
	}
	
	
	public Boolean tienePermiso(String permiso, Integer idUsuario){
		List<String> permisosUsuario = new ArrayList<>();
		permisosUsuario = obtenerAccionesdeUsuario(idUsuario);
		return permisosUsuario.contains(permiso);
	}
	
	// obtiene la lista de claves de acciones que tiene asignadas un usuario
		public List<String> obtenerAccionesdeUsuario(Integer idUsuario) {
			List<ConfiguracionUsuarioModuloDTO> confDtos = new ArrayList<>();
			confDtos = obtenerListaPorUsuario(idUsuario);

				List<String> accionesDeUsuario = new ArrayList<>();
			for (ConfiguracionUsuarioModuloDTO dto : confDtos) {

				List<DetalleConfiguracionModuloAccionEntity> detalles = new ArrayList<>();
				detalles = detalleconfiguracionModuloAccionRepository.obtenerDetallesPorIdConfiguracion(
						dto.getConfiguracionModulo().getId_configuracion_modulo_accion());
				for(DetalleConfiguracionModuloAccionEntity detalle : detalles){
					accionesDeUsuario.add(detalle.getId_accion().getClave());
				}
			}
			return accionesDeUsuario;
		}

	@Override
	public List<ConfiguracionUsuarioModuloDTO> obtenerListaRestantePorUsuario(Integer idUsuario) {
		List<ConfiguracionUsuarioModuloEntity> listEntity = dao.obtenerRegistrosPorUsuario(idUsuario);
		List<ConfiguracionUsuarioModuloEntity> listCompletaEntity = dao.consultarTodos();
		List<ConfiguracionUsuarioModuloEntity> listRestanteEntity =new ArrayList<>();
		
		for (ConfiguracionUsuarioModuloEntity ent : listCompletaEntity) {
			if(!listEntity.contains(ent)) {
				listRestanteEntity.add(ent);
			}
		}
		List<ConfiguracionUsuarioModuloDTO> listDto = new ArrayList<>();
		for (ConfiguracionUsuarioModuloEntity ent : listRestanteEntity) {
			ConfiguracionUsuarioModuloDTO dto = toDto(ent);
			listDto.add(dto);
		}
		return listDto;
	}

	private ConfiguracionUsuarioModuloDTO toDto(ConfiguracionUsuarioModuloEntity ent) {

		ConfiguracionUsuarioModuloDTO dto = new ConfiguracionUsuarioModuloDTO();

		dto.setId_configuracion_usuario_modulo(ent.getId_configuracion_usuario_modulo());
		UsuarioDTO usrDto = new UsuarioDTO();
		usrDto.setIdUsuario(ent.getUsuario().getIdUsuario());
		usrDto.setUserName(ent.getUsuario().getUserName());
		dto.setUsuario(usrDto);

		ConfiguracionModuloAccionDTO cfn = new ConfiguracionModuloAccionDTO();
		cfn.setId_configuracion_modulo_accion(ent.getConfiguracionModuloAccion().getId_configuracion_modulo_accion());
		ModuloDTO mdl = new ModuloDTO();
		mdl.setId_modulo(ent.getConfiguracionModuloAccion().getId_Modulo().getId_modulo());
		mdl.setNombre(ent.getConfiguracionModuloAccion().getId_Modulo().getNombre());
		cfn.setModulo(mdl);
		cfn.setNombreConfiguracion(ent.getConfiguracionModuloAccion().getDescripcion());
		List<AccionDTO> listaAcciones = new ArrayList<>();

		List<DetalleConfiguracionModuloAccionEntity> detalles = new ArrayList<>();
		detalles = detalleconfiguracionModuloAccionRepository.obtenerDetallesPorIdConfiguracion(
				ent.getConfiguracionModuloAccion().getId_configuracion_modulo_accion());
		if (!detalles.isEmpty()) {
			for (DetalleConfiguracionModuloAccionEntity detalle : detalles) {
				listaAcciones.add(new AccionDTO(detalle.getId_accion().getId_accion(),
						detalle.getId_accion().getClave(), detalle.getId_accion().getDescripcion(),
						detalle.getId_accion().getArea().getIdArea(), detalle.getId_accion().getModulo().getId_modulo(),
						detalle.getId_accion().getArea().getNombreArea()));
			}
		}
		cfn.setAcciones(listaAcciones);
		dto.setConfiguracionModulo(cfn);
		return dto;
	}
}
