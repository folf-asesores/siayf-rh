package mx.gob.saludtlax.rh.configuracion.centroresponsabilidad;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import mx.gob.saludtlax.rh.persistencia.CentroResponsabilidadEntity;
import mx.gob.saludtlax.rh.persistencia.CentroResponsabilidadRepository;

/**
 * 
 * @author José Pablo
 *
 */
@Stateless
public class CentroResponsabilidadService {
	@PersistenceContext(name = "siayfrhPU")
	private EntityManager entityManager;

	@Inject
	private CentroResponsabilidadRepository centroResponsabilidadRepository;

	public List<CentroResponsabilidadDTO> listaCentroResponsabilidad() {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("SELECT id_centro_responsabilidad AS idCentroResponsabilidad, "
				+ "clave AS clave, " + "descripcion AS descripcion " + "FROM centro_responsabilidad");
		query.setResultTransformer(Transformers.aliasToBean(CentroResponsabilidadDTO.class));
		@SuppressWarnings("unchecked")
		List<CentroResponsabilidadDTO> result = (List<CentroResponsabilidadDTO>) query.list();
		return result;
	}

	public CentroResponsabilidadDTO nuevoCentroresponsabilidad() {
		CentroResponsabilidadDTO dto = new CentroResponsabilidadDTO();
		dto.setClave(null);
		dto.setDescripcion(null);
		return dto;
	}

	public CentroResponsabilidadDTO crearCentroResponsabilidad(CentroResponsabilidadDTO dto) {
		CentroResponsabilidadEntity entity = new CentroResponsabilidadEntity();
		entity.setClave(dto.getClave());
		entity.setDescripcion(dto.getDescripcion());
		centroResponsabilidadRepository.crear(entity);
		return obtenerCentroResponsabilidadPorId(entity.getIdCentroResponsabilidad());
	}

	public CentroResponsabilidadDTO obtenerCentroResponsabilidadPorId(Integer idCentroResponsabilidad) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session
				.createSQLQuery("SELECT id_centro_responsabilidad AS idCentroResponsabilidad, " + "clave AS clave, "
						+ "descripcion AS descripcion "
						+ "FROM centro_responsabilidad WHERE id_centro_responsabilidad = :idCentroResponsabilidad")
				.setParameter("idCentroResponsabilidad", idCentroResponsabilidad);
		query.setResultTransformer(Transformers.aliasToBean(CentroResponsabilidadDTO.class));
		CentroResponsabilidadDTO result = (CentroResponsabilidadDTO) query.list().get(0);
		return result;
	}

	public CentroResponsabilidadDTO actualizarCentroResponsabilidad(CentroResponsabilidadDTO dto) {
		CentroResponsabilidadEntity entity = centroResponsabilidadRepository
				.obtenerPorId(dto.getIdCentroResponsabilidad());
		entity.setClave(dto.getClave());
		entity.setDescripcion(dto.getDescripcion());
		centroResponsabilidadRepository.crear(entity);
		return obtenerCentroResponsabilidadPorId(entity.getIdCentroResponsabilidad());
	}

	public void eliminarCentroResponsabilidad(Integer idCentroResponsabilidad) {
		CentroResponsabilidadEntity entity = entityManager.find(CentroResponsabilidadEntity.class,
				idCentroResponsabilidad);
		entityManager.remove(entity);
	}

	public List<SelectItem> obtenerCentroResponsabilidadItems() {
		List<SelectItem> selectItems = new ArrayList<>();
		List<CentroResponsabilidadEntity> listaCentroResponsabilidadEntities = centroResponsabilidadRepository
				.obtenerListaPuestoGeneral();
		for (CentroResponsabilidadEntity centroResponsabilidadEntity : listaCentroResponsabilidadEntities) {
			SelectItem item = new SelectItem(centroResponsabilidadEntity.getIdCentroResponsabilidad(),
					centroResponsabilidadEntity.getDescripcion());
			selectItems.add(item);
		}
		return selectItems;
	}

}
