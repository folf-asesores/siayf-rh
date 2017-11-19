/*
 *
 * InformacionAdjuntoAspiranteRepository.java
 * Creado el Jun 11, 2016 3:25:42 PM
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import mx.gob.saludtlax.rh.expediente.EntidadContexto;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class InformacionAdjuntoAspiranteRepository extends GenericRepository<InformacionAdjuntoAspiranteEntity, Integer> {

    private static final long serialVersionUID = 2034132595376058794L;

    private static final String OBTENER_INFORMACION_ADJUNTO_POR_TIPO_DOCUMENTO_ID_TIPO_DOCUMENTO = "from  InformacionAdjuntoAspiranteEntity as informacionAdjunto"
            + " where informacionAdjunto.entidadContexto = :entidadContexto" + " and informacionAdjunto.idEntidadContexto = :idEntidadContexto";

    private static final String OBTENER_INFORMACION_ADJUNTO_POR_TIPO_DOCUMENTO_ID_TIPO_DOCUMENTO_ID_DOCUMENTO_ADJUNTABLE = "from  InformacionAdjuntoAspiranteEntity as informacionAdjunto"
            + " where informacionAdjunto.entidadContexto = :entidadContexto" + " and informacionAdjunto.idEntidadContexto = :idEntidadContexto"
            + " and informacionAdjunto.documentoAdjuntable.idDocumentoAdjuntable = :idDocumentoAdjuntable";

    private static final String OBTENER_INFORMACION_ADJUNTOS_DEL_ASPIRANTE = "from  InformacionAdjuntoAspiranteEntity as informacionAdjunto"
            + " where informacionAdjunto.expedienteAspirante.idAspirante = :idAspirante" + " order by informacionAdjunto.entidadContexto";

    private static final String TIENE_ADJUNTO_UNICO = "select" + " case count(info.idInformacionAdjuntoAspirante)" + " when 0 then false" + " else true"
            + " end" + " from InformacionAdjuntoAspiranteEntity as info" + " where info.expedienteAspirante.idAspirante = :idAspirante"
            + " and info.documentoAdjuntable.idDocumentoAdjuntable = :idDocumentoAdjuntable";

    /**
     *
     * @param idAspirante
     * @param idDocumentoAdjuntable
     * @return
     */
    public boolean tieneAdjuntoUnico(Integer idAspirante, Integer idDocumentoAdjuntable) {
        Boolean tieneAdjunto = em.createQuery(TIENE_ADJUNTO_UNICO, Boolean.class).setParameter("idAspirante", idAspirante)
                .setParameter("idDocumentoAdjuntable", idDocumentoAdjuntable).getSingleResult();
        return tieneAdjunto;
    }

    /**
     *
     * @param idAspirante
     * @return
     */
    public List<InformacionAdjuntoAspiranteEntity> consultarInformacionAdjuntosDelAspirante(int idAspirante) {
        List<InformacionAdjuntoAspiranteEntity> listaInformacionAdjuntos = em
                .createQuery(OBTENER_INFORMACION_ADJUNTOS_DEL_ASPIRANTE, InformacionAdjuntoAspiranteEntity.class).setParameter("idAspirante", idAspirante)
                .getResultList();

        return listaInformacionAdjuntos;
    }

    /**
     *
     * @param entidadContexto
     * @param idEntidadContexto
     * @param idDocumentoAdjuntable
     * @return
     */
    public List<InformacionAdjuntoAspiranteEntity> consultarInformacionAduntosPorEntidadContextoIdEntidadContextoIdDocumentoAdjuntable(
            EntidadContexto entidadContexto, int idEntidadContexto, int idDocumentoAdjuntable) {
        List<InformacionAdjuntoAspiranteEntity> listaInformacionAdjuntos = em
                .createQuery(OBTENER_INFORMACION_ADJUNTO_POR_TIPO_DOCUMENTO_ID_TIPO_DOCUMENTO_ID_DOCUMENTO_ADJUNTABLE, InformacionAdjuntoAspiranteEntity.class)
                .setParameter("entidadContexto", entidadContexto).setParameter("idEntidadContexto", idEntidadContexto)
                .setParameter("idDocumentoAdjuntable", idDocumentoAdjuntable).getResultList();

        return listaInformacionAdjuntos;
    }

    /**
     *
     * @param entidadContexto
     * @param idEntidadContexto
     * @return
     */
    public List<InformacionAdjuntoAspiranteEntity> consultarInformacionAdjuntosPorEntidadContextoIdEntidadContexto(EntidadContexto entidadContexto,
            int idEntidadContexto) {
        List<InformacionAdjuntoAspiranteEntity> listaInformacionAdjuntos = em
                .createQuery(OBTENER_INFORMACION_ADJUNTO_POR_TIPO_DOCUMENTO_ID_TIPO_DOCUMENTO, InformacionAdjuntoAspiranteEntity.class)
                .setParameter("entidadContexto", entidadContexto).setParameter("idEntidadContexto", idEntidadContexto).getResultList();
        return listaInformacionAdjuntos;
    }
}
