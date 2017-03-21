package mx.gob.saludtlax.rh.modulos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import mx.gob.saludtlax.rh.acciones.AccionDTO;
import mx.gob.saludtlax.rh.acciones.AccionService;
import mx.gob.saludtlax.rh.areas.AreaDTO;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.persistencia.AccionesEntity;
import mx.gob.saludtlax.rh.persistencia.AccionesRepository;
import mx.gob.saludtlax.rh.persistencia.AreasRepository;
import mx.gob.saludtlax.rh.persistencia.ModuloRepository;
import mx.gob.saludtlax.rh.util.Configuracion;
import mx.gob.saludtlax.rh.util.ValidacionUtil;
import mx.gob.saludtlax.rh.persistencia.ModuloEntity;

public class ModulosService implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4071097046814566204L;

		@PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
	private EntityManager entityManager;

	@Inject
	private ModuloRepository moduloDAO;
	@Inject
	private AreasRepository areasDAO;

	@Inject
	private AccionService accionesService;

	@Inject
	private AccionesRepository accionesRepository;

	@Inject
	private ConfiguracionModuloAccion configuracionModuloAccion;

	public void crear(ModuloDTO dto) {

		String contexto = "Registrar Modulo: ";

		if (dto == null) {
			throw new ValidacionException(contexto + "Ingrese los valores requeridos",
					ValidacionCodigoError.VALOR_REQUERIDO);
		}

		if (ValidacionUtil.esCadenaVacia(dto.getNombre())) {
			throw new ValidacionException(contexto + "Por favor Ingrese en nombre del modulo.",
					ValidacionCodigoError.VALOR_REQUERIDO);
		}

		if (ValidacionUtil.esCadenaVacia(dto.getUrl())) {
			throw new ValidacionException(contexto + "Por favor Ingrese la url.",
					ValidacionCodigoError.VALOR_REQUERIDO);
		}

		if (!ValidacionUtil.esNumeroPositivoInt(dto.getIdArea())) {
			throw new ValidacionException(contexto + "Por favor seleccione el area",
					ValidacionCodigoError.VALOR_REQUERIDO);
		}

		if (dto.getAcciones().isEmpty()) {
			throw new ValidacionException(contexto + "Por favor ingrese una acción como minimo.",
					ValidacionCodigoError.VALOR_REQUERIDO);
		}

		ModuloEntity entity = new ModuloEntity();
		entity.setArea(areasDAO.obtenerPorId(dto.getIdArea()));
		entity.setHabilitado(dto.getHabilitado());
		entity.setNombre(dto.getNombre());
		entity.setUrl(dto.getUrl());
		moduloDAO.crear(entity);

		for (AccionDTO accion : dto.getAcciones()) {

			AccionesEntity accionesEntity = new AccionesEntity();

			accionesEntity.setArea(areasDAO.obtenerPorId(accion.getId_area()));
			accionesEntity.setClave(accion.getClave());
			accionesEntity.setDescripcion(accion.getDescripcion());
			accionesEntity.setModulo(entity);

			accionesRepository.crear(accionesEntity);
		}

	}

	public void editar(ModuloDTO dto) {

		String contexto = "Actualizar Modulo: ";

		if (dto == null) {
			throw new ValidacionException(contexto + "Ingrese los valores requeridos",
					ValidacionCodigoError.VALOR_REQUERIDO);
		}

		if (ValidacionUtil.esCadenaVacia(dto.getNombre())) {
			throw new ValidacionException(contexto + "Por favor Ingrese en nombre del modulo.",
					ValidacionCodigoError.VALOR_REQUERIDO);
		}

		if (ValidacionUtil.esCadenaVacia(dto.getUrl())) {
			throw new ValidacionException(contexto + "Por favor Ingrese la url.",
					ValidacionCodigoError.VALOR_REQUERIDO);
		}

		if (!ValidacionUtil.esNumeroPositivoInt(dto.getIdArea())) {
			throw new ValidacionException(contexto + "Por favor seleccione el area",
					ValidacionCodigoError.VALOR_REQUERIDO);
		}

		if (dto.getAcciones().isEmpty()) {
			throw new ValidacionException(contexto + "Por favor ingrese una acción como minimo.",
					ValidacionCodigoError.VALOR_REQUERIDO);
		}

		ModuloEntity entity = moduloDAO.obtenerPorId(dto.getId_modulo());

		entity.setArea(areasDAO.obtenerPorId(dto.getIdArea()));
		entity.setHabilitado(dto.getHabilitado());
		entity.setNombre(dto.getNombre());
		entity.setUrl(dto.getUrl());
		moduloDAO.actualizar(entity);

		for (AccionDTO accion : dto.getAcciones()) {

			AccionesEntity accionesEntity = accionesRepository.obtenerPorId(accion.getId_accion());
			// registra las acciones nuevas.
			if (accionesEntity == null) {
				accionesEntity = new AccionesEntity();

				accionesEntity.setArea(areasDAO.obtenerPorId(accion.getId_area()));
				accionesEntity.setClave(accion.getClave());
				accionesEntity.setDescripcion(accion.getDescripcion());
				accionesEntity.setModulo(entity);

				accionesRepository.crear(accionesEntity);
			}

		}

	}

	public Boolean eliminar(Integer id) {

		Boolean res = true;
		List<ConfiguracionModuloAccionDTO> listConf = new ArrayList<>();
		listConf = configuracionModuloAccion.obtenerListaConfiguracionModuloAccionDTOPorModulo(id);
		if (listConf == null || listConf.isEmpty()) {
			moduloDAO.eliminarPorId(id);
			res = true;
		} else {
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
		Query query = session.createSQLQuery("SELECT " + "id_area AS id, " + "nombre_area AS nombre " + "FROM areas");
		query.setResultTransformer(Transformers.aliasToBean(AreaDTO.class));
		@SuppressWarnings("unchecked")
		List<AreaDTO> result = (List<AreaDTO>) query.list();
		return result;
	}

	protected ModuloDTO obtenerModuloPorId(Integer idModulo) {

		ModuloEntity entity = moduloDAO.obtenerPorId(idModulo);

		ModuloDTO dto = new ModuloDTO();

		dto.setHabilitado(entity.getHabilitado());
		dto.setId_modulo(entity.getId_modulo());
		dto.setIdArea(entity.getArea().getIdArea());
		dto.setNombre(entity.getNombre());
		dto.setNombreArea(entity.getArea().getNombreArea());
		dto.setUrl(entity.getUrl());

		List<AccionDTO> listaAccion = accionesService.obtenerAccionesPorModulo(idModulo);

		if (!listaAccion.isEmpty()) {
			dto.setAcciones(listaAccion);
		}

		return dto;
	}

}
