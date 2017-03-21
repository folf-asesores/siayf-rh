package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import mx.gob.saludtlax.rh.configuracion.plazas.EnumEstatusPlaza;
import mx.gob.saludtlax.rh.util.Configuracion;

public class PlazaRepository implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5028515099960320052L;

	@PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
	EntityManager entityManager;

	/**
	 * Registra una plaza
	 */
	public void registrarPlaza(PlazaEntity plaza) {
		entityManager.persist(plaza);
	}

	/**
	 * Actualiza la información de una plaza registrada
	 */
	public void actualizarPlaza(PlazaEntity plaza) {

		entityManager.merge(plaza);
	}

	/**
	 * Eliminar una plaza registrada
	 */
	public void eliminarPlazaPorId(Integer idPlaza) {
		PlazaEntity plaza = entityManager.find(PlazaEntity.class, idPlaza);
		entityManager.remove(plaza);
	}

	/**
	 * Recupera todas las plazas registradas
	 */
	public List<PlazaEntity> obtenerListaPlaza(String criterio) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<PlazaEntity> q = cb.createQuery(PlazaEntity.class);
		Root<PlazaEntity> root = q.from(PlazaEntity.class);
		final Predicate disjunction = cb.disjunction();

		if (!StringUtils.isBlank(criterio)) {
			disjunction.getExpressions().add(
					cb.like(root.get("clave").as(String.class), "%" + criterio
							+ "%"));
			disjunction.getExpressions().add(
					cb.like(root.get("nombrePlaza").as(String.class), "%"
							+ criterio + "%"));
			disjunction.getExpressions().add(
					cb.like(root.get("adscripcion").as(String.class), "%"
							+ criterio + "%"));
			disjunction.getExpressions().add(
					cb.like(root.get("tipo").as(String.class), "%" + criterio
							+ "%"));
		}
		q.select(root).where(disjunction);
		List<PlazaEntity> plaza = entityManager.createQuery(q).getResultList();
		return plaza;
	}

	/**
	 * Obtener plaza por id
	 */

	public PlazaEntity obtenerPlazaPorId(Integer idPlaza) {
		return entityManager.find(PlazaEntity.class, idPlaza);
	}

	public String buscarClave(String clave) {
		List<PlazaEntity> resultado = entityManager
				.createQuery(
						"SELECT a FROM PlazaEntity AS a WHERE a.clave=:clave",
						PlazaEntity.class).setParameter("clave", clave)
				.getResultList();
		PlazaEntity plazaEntity = null;
		if (!resultado.isEmpty()) {
			plazaEntity = resultado.get(0);
		}
		return plazaEntity.getClave();
	}

	/**
	 * Consulta las plazas vacantes por nombramiento y nivel
	 * 
	 * @param idNombramiento
	 * @param idNivel
	 * 
	 * */
	public List<PlazaEntity> plazasDisponiblesPorNombramientoNivel(
			String idNombramiento, Integer idNivel) {
		List<PlazaEntity> plazas = entityManager
				.createQuery(
						"SELECT p FROM PlazaEntity AS p WHERE p.nombramiento.nombramiento =:idNombramiento AND p.idNivel =:idNivel AND p.estatus =:estatus",
						PlazaEntity.class)
				.setParameter("idNombramiento", idNombramiento)
				.setParameter("idNivel", idNivel)
				.setParameter("estatus", EnumEstatusPlaza.VACANTE)
				.getResultList();
		return plazas;
	}

}