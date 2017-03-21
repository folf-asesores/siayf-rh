package mx.gob.saludtlax.rh.configuracion.clasificacionnomina;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import mx.gob.saludtlax.rh.persistencia.ClasificacionNominaEntity;
import mx.gob.saludtlax.rh.persistencia.ClasificacionNominaRepository;
import mx.gob.saludtlax.rh.util.Configuracion;


@Stateless
public class ClasificacionNominaService {
		@PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
	private EntityManager entityManager;
	
	@Inject
	private ClasificacionNominaRepository clasificacionNominaDAO;

	public List<ClasificacionNominaDTO> listaClasificacionNomina() {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery(
				"SELECT id_clasificacion_nomina AS idClasificacionNomina, "
				+ "clasificacion_nomina AS clasificacionNomina, "
				+ "descripcion AS descripcion "
				+ "FROM clasificaciones_nominas");
		query.setResultTransformer(Transformers.aliasToBean(ClasificacionNominaDTO.class));
		@SuppressWarnings("unchecked")
		List<ClasificacionNominaDTO> result = (List<ClasificacionNominaDTO>) query.list();
		return result;
	}
	
	public ClasificacionNominaDTO nuevoClasificacionNomina() {
		ClasificacionNominaDTO  dto = new ClasificacionNominaDTO ();
		dto.setDescripcion(null);
		dto.setClasificacionNomina(null);
		return dto;
	}
	
	public ClasificacionNominaDTO crearClasificacionNomina(ClasificacionNominaDTO dto) {
		ClasificacionNominaEntity entity = new ClasificacionNominaEntity();
		entity.setDescripcion(dto.getDescripcion());
		entity.setClasificacionNomina(dto.getClasificacionNomina());
		clasificacionNominaDAO.crear(entity);
		return obtenerClasificacionNominaPorId(entity.getIdClasificacionNomina());
	}
	
	public ClasificacionNominaDTO obtenerClasificacionNominaPorId(Integer idClasificacionNomina) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery(
				"SELECT id_clasificacion_nomina AS idClasificacionNomina, "
				+ "clasificacion_nomina AS clasificacionNomina, "
				+ "descripcion AS descripcion "
				+ "FROM clasificaciones_nominas WHERE id_clasificacion_nomina= :idClasificacionNomina")
				.setParameter("idClasificacionNomina", idClasificacionNomina);
		query.setResultTransformer(Transformers.aliasToBean(ClasificacionNominaDTO.class));
		ClasificacionNominaDTO result = (ClasificacionNominaDTO) query.list().get(0);
		return result;
	}

	public ClasificacionNominaDTO actualizarClasificacionNomina(ClasificacionNominaDTO dto) {
		ClasificacionNominaEntity entity = clasificacionNominaDAO.obtenerPorId(dto.getIdClasificacionNomina());
		entity.setDescripcion(dto.getDescripcion());
		entity.setClasificacionNomina(dto.getClasificacionNomina());
		clasificacionNominaDAO.crear(entity);
		return obtenerClasificacionNominaPorId(entity.getIdClasificacionNomina());
	}

	public void eliminarClasificacionNomina(ClasificacionNominaDTO dto) {
		ClasificacionNominaEntity entity = entityManager.find
				(ClasificacionNominaEntity.class, dto.getIdClasificacionNomina());
		entityManager.remove(entity);
		}
	
}