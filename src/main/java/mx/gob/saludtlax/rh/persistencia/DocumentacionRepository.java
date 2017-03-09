/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 05/04/2016-11:51:35
 */
public class DocumentacionRepository extends GenericRepository<DocumentacionEntity, Integer>{
	@PersistenceContext(name = "siayfrhPU")
	private EntityManager entityManager;

	/**
	 * Actualiza o registrar los documentos que presenta el aspirante.
	 * 
	 * @param documentacionEntity
	 */
	public void registrarActualizarDocumento(DocumentacionEntity documentacionEntity) {
		entityManager.persist(documentacionEntity);
	}

	/**
	 * Cosulta las documentaciones registradas por el aspirante
	 * 
	 * @param idAspirante
	 * @return
	 */
	public List<DocumentacionEntity> documentacionesPorIdAspirante(Integer idAspirante) {

		List<DocumentacionEntity> listaDocumentacion = entityManager
				.createQuery("SELECT d FROM DocumentacionEntity AS d WHERE d.idAspirante.idAspirante =:idAspirante",
						DocumentacionEntity.class)
				.setParameter("idAspirante", idAspirante).getResultList();

		return listaDocumentacion;
	}

}
