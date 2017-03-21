package mx.gob.saludtlax.rh.modulos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import mx.gob.saludtlax.rh.acciones.AccionDTO;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.persistencia.AccionesRepository;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionModuloAccionRepository;
import mx.gob.saludtlax.rh.persistencia.DetalleConfiguracionModuloAccionEntity;
import mx.gob.saludtlax.rh.persistencia.DetalleConfiguracionModuloAccionRepository;
import mx.gob.saludtlax.rh.persistencia.ModuloEntity;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionModuloAccionEntity;
import mx.gob.saludtlax.rh.persistencia.ModuloRepository;
import mx.gob.saludtlax.rh.util.Configuracion;

public class ConfiguracionModuloAccionService implements Serializable {

	private static final long serialVersionUID = -1800073664586345602L;
	private static final Logger LOGGER = Logger.getLogger(ConfiguracionModuloAccionService.class);

		@PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
	private EntityManager entityManager;

	@Inject
	private ConfiguracionModuloAccionRepository dao;

	@Inject
	private DetalleConfiguracionModuloAccionRepository detalleConfiguracionDao;

	@Inject
	private ModuloRepository moduloDAO;

	@Inject
	private AccionesRepository accionesRepository;

	public void crear(ConfiguracionModuloAccionDTO dto) {
		ConfiguracionModuloAccionEntity entity = new ConfiguracionModuloAccionEntity();

		entity.setDescripcion(dto.getNombreConfiguracion());
		entity.setId_Modulo(moduloDAO.obtenerPorId(dto.getModulo().getId_modulo()));

		ConfiguracionModuloAccionEntity conf = dao.crear(entity);

		guardarDetalleConfiguracion(conf.getId_configuracion_modulo_accion(), dto.getAcciones());
	}

	private void guardarDetalleConfiguracion(Integer idConfiguracion, List<AccionDTO> acciones) {

		// primero buscara si ya hay detalles de la configuracion de ser asi
		// los borrara y guardara la nueva lista de acciones.
		List<DetalleConfiguracionModuloAccionEntity> detallesExistentes = new ArrayList<>();

		detallesExistentes = detalleConfiguracionDao.obtenerDetallesPorIdConfiguracion(idConfiguracion);
		if (detallesExistentes != null) {
			for (DetalleConfiguracionModuloAccionEntity detalleDelet : detallesExistentes) {
				detalleConfiguracionDao.eliminarPorId(detalleDelet.getId_detalle_configuracion_modulo_accion());
			}
		}
		if (!acciones.isEmpty()) { // Valida si hay acciones por registrar
			for (AccionDTO dto : acciones) {
				LOGGER.debug("Acción registrando: " + dto.toString());
				DetalleConfiguracionModuloAccionEntity detalle = new DetalleConfiguracionModuloAccionEntity();
				detalle.setId_accion(accionesRepository.obtenerPorId(dto.getId_accion()));
				detalle.setIdConfiguracionModuloAccion(idConfiguracion);
				// guarda los detalles de las acciones
				detalleConfiguracionDao.crear(detalle);
			}
		}

	}

	public void editar(ConfiguracionModuloAccionDTO dto) {
		ConfiguracionModuloAccionEntity entity = dao.obtenerPorId(dto.getId_configuracion_modulo_accion());
		entity.setDescripcion(dto.getNombreConfiguracion());
		entity.setId_Modulo(moduloDAO.obtenerPorId(dto.getModulo().getId_modulo()));

		ConfiguracionModuloAccionEntity conf = dao.actualizar(entity);

		guardarDetalleConfiguracion(conf.getId_configuracion_modulo_accion(), dto.getAcciones());
	}

	public void eliminar(Integer id) {
		ConfiguracionModuloAccionEntity entity = dao.obtenerPorId(id);

		// primero buscara si ya hay detalles de la configuracion de ser asi los
		// borrara y guardara la nueva lista de acciones.
		List<DetalleConfiguracionModuloAccionEntity> detallesExistentes = new ArrayList<>();

		detallesExistentes = detalleConfiguracionDao
				.obtenerDetallesPorIdConfiguracion(entity.getId_configuracion_modulo_accion());
		if (detallesExistentes != null) {
			for (DetalleConfiguracionModuloAccionEntity detalleDelet : detallesExistentes) {
				detalleConfiguracionDao.eliminarPorId(detalleDelet.getId_detalle_configuracion_modulo_accion());
			}
		}

		dao.eliminar(entity);
	}

	public ConfiguracionModuloAccionDTO obtenerConfAccModPorId(Integer IdConfAccMod) {

		ConfiguracionModuloAccionEntity entity = dao.obtenerPorId(IdConfAccMod);

		ConfiguracionModuloAccionDTO dto = new ConfiguracionModuloAccionDTO();

		dto.setId_configuracion_modulo_accion(entity.getId_configuracion_modulo_accion());
		dto.setNombreConfiguracion(entity.getDescripcion());

		ModuloDTO moduloDto = new ModuloDTO();
		moduloDto.setHabilitado(entity.getId_Modulo().getHabilitado());
		moduloDto.setId_modulo(entity.getId_Modulo().getId_modulo());
		moduloDto.setIdArea(entity.getId_Modulo().getArea().getIdArea());
		moduloDto.setNombre(entity.getId_Modulo().getNombre());
		moduloDto.setNombreArea(entity.getId_Modulo().getArea().getNombreArea());
		moduloDto.setUrl(entity.getId_Modulo().getUrl());

		dto.setModulo(moduloDto);

		List<AccionDTO> listAcciones = new ArrayList<AccionDTO>();

		// primero buscara si ya hay detalles de la configuracion de ser asi
		List<DetalleConfiguracionModuloAccionEntity> detallesExistentes = new ArrayList<>();

		detallesExistentes = detalleConfiguracionDao.obtenerDetallesPorIdConfiguracion(IdConfAccMod);
		if (!detallesExistentes.isEmpty()) {
			for (DetalleConfiguracionModuloAccionEntity detalleConfiguracionModuloAccionEntity : detallesExistentes) {

				AccionDTO accion = new AccionDTO();

				accion.setId_accion(detalleConfiguracionModuloAccionEntity.getId_accion().getId_accion());
				accion.setId_area(detalleConfiguracionModuloAccionEntity.getId_accion().getArea().getIdArea());
				accion.setClave(detalleConfiguracionModuloAccionEntity.getId_accion().getClave());
				accion.setDescripcion(detalleConfiguracionModuloAccionEntity.getId_accion().getDescripcion());
				accion.setNombreArea(detalleConfiguracionModuloAccionEntity.getId_accion().getArea().getNombreArea());

				Integer idModulo = entity.getId_Modulo().getId_modulo();

				ModuloEntity moduloEntity = moduloDAO.obtenerPorId(idModulo);

				if (moduloEntity == null) {
					throw new ValidacionException(
							"Obtener Configuración Modulo Acción: no se encontrarón resultados con el identificador del modulo "
									+ detalleConfiguracionModuloAccionEntity.getId_accion().getModulo().getId_modulo()
											.toString(),
							ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);
				} else {
					accion.setId_modulo(moduloEntity.getId_modulo());
				}

				listAcciones.add(accion);

			}
		}

		dto.setAcciones(listAcciones);

		return dto;
	}

	public List<ConfiguracionModuloAccionDTO> obtenerRegistros() {
		List<ConfiguracionModuloAccionEntity> listEntity = new ArrayList<>();

		listEntity = dao.obtenerRegistros();

		List<ConfiguracionModuloAccionDTO> listDto = new ArrayList<>();

		for (ConfiguracionModuloAccionEntity cE : listEntity) {
			ConfiguracionModuloAccionDTO dto = new ConfiguracionModuloAccionDTO();

			dto.setId_configuracion_modulo_accion(cE.getId_configuracion_modulo_accion());
			dto.setNombreConfiguracion(cE.getDescripcion());

			ModuloDTO moduloDto = new ModuloDTO();
			moduloDto.setHabilitado(cE.getId_Modulo().getHabilitado());
			moduloDto.setId_modulo(cE.getId_Modulo().getId_modulo());
			moduloDto.setIdArea(cE.getId_Modulo().getArea().getIdArea());
			moduloDto.setNombre(cE.getId_Modulo().getNombre());
			moduloDto.setNombreArea(cE.getId_Modulo().getArea().getNombreArea());
			moduloDto.setUrl(cE.getId_Modulo().getUrl());

			dto.setModulo(moduloDto);

			List<AccionDTO> acciones = obtenerAccionesPorConfiguracion(cE.getId_configuracion_modulo_accion());
			dto.setAcciones(acciones);
			listDto.add(dto);
		}

		return listDto;

	}

	private List<AccionDTO> obtenerAccionesPorConfiguracion(Integer idConfiguracion) {
		List<AccionDTO> listAcciones = new ArrayList<>();

		List<DetalleConfiguracionModuloAccionEntity> detalles = detalleConfiguracionDao
				.obtenerDetallesPorIdConfiguracion(idConfiguracion);

		for (DetalleConfiguracionModuloAccionEntity ent : detalles) {
			AccionDTO accionDto = new AccionDTO();
			accionDto.setId_accion(ent.getId_accion().getId_accion());
			accionDto.setClave(ent.getId_accion().getClave());
			accionDto.setDescripcion(ent.getId_accion().getDescripcion());
			accionDto.setId_area(ent.getId_accion().getArea().getIdArea());
			accionDto.setNombreArea(ent.getId_accion().getArea().getNombreArea());

			listAcciones.add(accionDto);
		}

		return listAcciones;
	}

	public List<ConfiguracionModuloAccionDTO> obtenerRegistrosPorModulo(Integer idModulo) {
		List<ConfiguracionModuloAccionEntity> listEntity = new ArrayList<>();

		listEntity = dao.obtenerRegistrosPorModulo(idModulo);

		List<ConfiguracionModuloAccionDTO> listDto = new ArrayList<>();
		for (ConfiguracionModuloAccionEntity cE : listEntity) {
			ConfiguracionModuloAccionDTO dto = new ConfiguracionModuloAccionDTO();

			dto.setId_configuracion_modulo_accion(cE.getId_configuracion_modulo_accion());

			ModuloDTO moduloDto = new ModuloDTO();
			moduloDto.setHabilitado(cE.getId_Modulo().getHabilitado());
			moduloDto.setId_modulo(cE.getId_Modulo().getId_modulo());
			moduloDto.setIdArea(cE.getId_Modulo().getArea().getIdArea());
			moduloDto.setNombre(cE.getId_Modulo().getNombre());
			moduloDto.setNombreArea(cE.getId_Modulo().getArea().getNombreArea());
			moduloDto.setUrl(cE.getId_Modulo().getUrl());

			dto.setModulo(moduloDto);

			listDto.add(dto);
		}

		return listDto;

	}

	public List<ConfiguracionModuloAccionDTO> obtenerRegistrosPorAccion(Integer idModulo) {
		List<ConfiguracionModuloAccionEntity> listEntity = new ArrayList<>();

		listEntity = dao.obtenerRegistrosPorModulo(idModulo);

		List<ConfiguracionModuloAccionDTO> listDto = new ArrayList<>();
		for (ConfiguracionModuloAccionEntity cE : listEntity) {
			ConfiguracionModuloAccionDTO dto = new ConfiguracionModuloAccionDTO();

			dto.setId_configuracion_modulo_accion(cE.getId_configuracion_modulo_accion());

			ModuloDTO moduloDto = new ModuloDTO();
			moduloDto.setHabilitado(cE.getId_Modulo().getHabilitado());
			moduloDto.setId_modulo(cE.getId_Modulo().getId_modulo());
			moduloDto.setIdArea(cE.getId_Modulo().getArea().getIdArea());
			moduloDto.setNombre(cE.getId_Modulo().getNombre());
			moduloDto.setNombreArea(cE.getId_Modulo().getArea().getNombreArea());
			moduloDto.setUrl(cE.getId_Modulo().getUrl());

			dto.setModulo(moduloDto);

			listDto.add(dto);
		}

		return listDto;

	}
	
	public AccionDTO obtenerAccionesNoRegistradasEnConfg(Integer idModulo, Integer idAccionFiltro) {
		
		
		
		
		
		
		return null;
	}

}
