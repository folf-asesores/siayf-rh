/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.gob.saludtlax.rh.util.Configuracion;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 05/04/2016-11:51:35
 */
public class DocumentacionRepository
        extends GenericRepository<DocumentacionEntity, Integer> {
    /**
    *
    */
    private static final long serialVersionUID = -561502263345073146L;
    @PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
    private EntityManager entityManager;

    /**
     * Actualiza o registrar los documentos que presenta el aspirante.
     *
     * @param documentacionEntity
     */
    public void registrarActualizarDocumento(
            DocumentacionEntity documentacionEntity) {
        entityManager.persist(documentacionEntity);
    }

    /**
     * Cosulta las documentaciones registradas por el aspirante
     *
     * @param idAspirante
     * @return
     */
    public List<DocumentacionEntity> documentacionesPorIdAspirante(
            Integer idAspirante) {

        List<DocumentacionEntity> listaDocumentacion = entityManager
                .createQuery(
                        "SELECT d FROM DocumentacionEntity AS d WHERE d.idAspirante.idAspirante =:idAspirante",
                        DocumentacionEntity.class)
                .setParameter("idAspirante", idAspirante).getResultList();

        return listaDocumentacion;
    }

}
