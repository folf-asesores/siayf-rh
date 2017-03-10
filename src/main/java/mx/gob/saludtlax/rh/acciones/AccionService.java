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
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.modulos.ConfiguracionModuloAccion;
import mx.gob.saludtlax.rh.modulos.ConfiguracionModuloAccionDTO;
import mx.gob.saludtlax.rh.persistencia.AccionesEntity;
import mx.gob.saludtlax.rh.persistencia.AreasRepository;
import mx.gob.saludtlax.rh.persistencia.ModuloRepository;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

public class AccionService {
	@PersistenceContext(name = "siayfrhPU")
	private EntityManager entityManager;

	@Inject
	private AccionesRepository accionesDAO;

	@Inject
	private ModuloRepository moduloRepository;

	@Inject
	private AreasRepository areasDAO;

	@Inject
	private ConfiguracionModuloAccion configuracionModuloAccion;

	public void crearAccion(AccionDTO accionDTO) {
		String contexto = "Registrar Acción: ";

		AccionesEntity accionesEntity = new AccionesEntity();

		accionesEntity.setClave(accionDTO.getClave());
		accionesEntity.setDescripcion(accionDTO.getDescripcion());

		if (!ValidacionUtil.esNumeroPositivoInt(accionDTO.getId_area())) {
			throw new ValidacionException(contexto + "Selecione el area.", ValidacionCodigoError.VALOR_REQUERIDO);
		}

		if (ValidacionUtil.esNumeroPositivoInt(accionDTO.getId_modulo())) {

			accionesEntity.setModulo(moduloRepository.obtenerPorId(accionDTO.getId_modulo()));

		}

		accionesEntity.setArea(areasDAO.obtenerPorId(accionDTO.getId_area()));

		accionesDAO.crear(accionesEntity);
	}

	public void editarAccion(AccionDTO accionDTO) {
		AccionesEntity accionesEntity = accionesDAO.obtenerPorId(accionDTO.getId_accion());

		accionesEntity.setArea(areasDAO.obtenerPorId(accionDTO.getId_area()));
		accionesEntity.setClave(accionDTO.getClave());
		accionesEntity.setDescripcion(accionDTO.getDescripcion());

		accionesDAO.actualizar(accionesEntity);
	}

	protected List<AccionDTO> obtenerAcciones() {
		List<AccionDTO> listDto = new ArrayList<>();

		List<AccionesEntity> listEntity = new ArrayList<>();
		listEntity = accionesDAO.obtenerListaAcciones();

		for (AccionesEntity a : listEntity) {
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

	public List<AccionDTO> obtenerAccionesPorArea(Integer idArea) {
		List<AccionDTO> listDto = new ArrayList<>();
		try {
			List<AccionesEntity> listEntity = new ArrayList<>();
			listEntity = accionesDAO.obtenerListaAccionesPorIdArea(idArea);

			for (AccionesEntity a : listEntity) {
				AccionDTO aDto = new AccionDTO();
				aDto.setClave(a.getClave());
				aDto.setDescripcion(a.getDescripcion());
				aDto.setId_accion(a.getId_accion());
				aDto.setId_area(a.getArea().getIdArea());
				aDto.setNombreArea(a.getArea().getNombreArea());

				listDto.add(aDto);
			}

			return listDto;
		} catch (NoResultException e) {
			return null;
		}

	}

	public List<AccionDTO> obtenerAccionesPorModulo(Integer idModulo) {
		List<AccionDTO> listDto = new ArrayList<>();
		try {
			List<AccionesEntity> listEntity = new ArrayList<>();
			listEntity = accionesDAO.obtenerListaAccionesPorIdModulo(idModulo);

			for (AccionesEntity a : listEntity) {
				AccionDTO aDto = new AccionDTO();
				aDto.setClave(a.getClave());
				aDto.setDescripcion(a.getDescripcion());
				aDto.setId_accion(a.getId_accion());
				aDto.setId_area(a.getArea().getIdArea());
				aDto.setNombreArea(a.getArea().getNombreArea());

				if (a.getModulo() != null) {
					aDto.setId_modulo(a.getModulo().getId_modulo());
				}

				listDto.add(aDto);
			}

			return listDto;
		} catch (NoResultException e) {
			return null;
		}

	}

	public boolean eliminarAccion(Integer idAccion) {
		Boolean res = true;
		List<ConfiguracionModuloAccionDTO> listConf = new ArrayList<>();
		listConf = configuracionModuloAccion.obtenerListaConfiguracionModuloAccionDTOPorAccion(idAccion);
		if (listConf == null || listConf.isEmpty()) {
			accionesDAO.eliminarPorId(idAccion);
			res = true;
		} else {
			res = false;
		}
		return res;

	}

	public List<AreaDTO> listaArea() {
		Session session = entityManager.unwrap(Session.class);
		Query query = session
				.createSQLQuery("SELECT " + "id_area AS idArea, " + "nombre_area AS nombreArea " + "FROM areas");
		query.setResultTransformer(Transformers.aliasToBean(AreaDTO.class));
		@SuppressWarnings("unchecked")
		List<AreaDTO> result = (List<AreaDTO>) query.list();
		return result;
	}

	public List<AccionDTO> obtenerAccionesFiltradas(Integer idModulo, List<Integer> idAccionFiltro) {

		List<AccionDTO> listDto = new ArrayList<>();
		try {

			List<AccionesEntity> listEntity = new ArrayList<>();
			listEntity = accionesDAO.obtenerListaAccionesPorIdModulo(idModulo);

			for (Integer idAcc : idAccionFiltro) {
				for (int i = 0; i < listEntity.size(); i++) {
					if (idAcc.compareTo(listEntity.get(i).getId_accion()) == 0) {
						listEntity.remove(i);
					}

				}
			}

			for (AccionesEntity a : listEntity) {

				AccionDTO aDto = new AccionDTO();
				aDto.setClave(a.getClave());
				aDto.setDescripcion(a.getDescripcion());
				aDto.setId_accion(a.getId_accion());
				aDto.setId_area(a.getArea().getIdArea());
				aDto.setNombreArea(a.getArea().getNombreArea());

				listDto.add(aDto);

			}

			return listDto;
		} catch (NoResultException e) {
			return null;
		}

	}
}
