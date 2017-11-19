/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

/**
 * @author Leila Schiaffini Ehuan
 * @since 17/05/2016 17:10:34
 *
 */
public class DocumentoAdjuntableRepository extends GenericRepository<DocumentoAdjuntableEntity, Integer> {

    private static final long serialVersionUID = -5799539290256795571L;

    /**
     * Consulta la lista de los documentos que pueden ser adjuntados segun su
     * clasificacion
     *
     * @param clasificacion
     *            clasifiacion del documento que va a ser adjuntado.
     * @return
     */
    public List<DocumentoAdjuntableEntity> documentosAdjuntablesPorClasificacion(String clasificacion) {
        List<DocumentoAdjuntableEntity> documentos = em
                .createQuery("SELECT d FROM DocumentoAdjuntableEntity AS d WHERE d.clasificacion =:clasificacion", DocumentoAdjuntableEntity.class)
                .setParameter("clasificacion", clasificacion).getResultList();
        return documentos;
    }

    public List<DocumentoAdjuntableEntity> consultarDocumentosPorContexto(int contexto) {
        return em.createQuery("SELECT d FROM DocumentoAdjuntableEntity AS d WHERE d.contextoDocumento =:contextoDocumento", DocumentoAdjuntableEntity.class)
                .setParameter("contextoDocumento", contexto).getResultList();
    }
}
