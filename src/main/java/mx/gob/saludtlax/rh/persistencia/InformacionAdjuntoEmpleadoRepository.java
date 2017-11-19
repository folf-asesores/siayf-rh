/*
 * InformacionAdjuntoEmpleadoRepository.java
 * Creado el May 18, 2016 5:00:44 PM
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import mx.gob.saludtlax.rh.expediente.EntidadContexto;

/**
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 *
 */
public class InformacionAdjuntoEmpleadoRepository extends GenericRepository<InformacionAdjuntoEmpleadoEntity, Integer> {

    private static final long serialVersionUID = -6250247397117260779L;

    private static final String OBTENER_INFORMACION_ADJUNTO_POR_ENTIDAD_CONTEXTO_ID_ENTIDAD_CONTEXTO = "from  InformacionAdjuntoEmpleadoEntity as informacionAdjunto"
            + " where informacionAdjunto.entidadContexto = :entidadContexto" + " and informacionAdjunto.idEntidadContexto = :idEntidadContexto";

    private static final String OBTENER_INFORMACION_ADJUNTO_POR_ENTIDAD_CONTEXTO_ID_ENTIDAD_CONTEXTO_ID_DOCUMENTO_ADJUNTABLE = "from  InformacionAdjuntoEmpleadoEntity as informacionAdjunto"
            + " where informacionAdjunto.entidadContexto = :entidadContexto" + " and informacionAdjunto.idEntidadContexto = :idEntidadContexto"
            + " and informacionAdjunto.documentoAdjuntable.idDocumentoAdjuntable = :idDocumentoAdjuntable";

    private static final String OBTENER_INFORMACION_ADJUNTOS_DEL_EMPLEADO = "from  InformacionAdjuntoEmpleadoEntity as informacionAdjunto"
            + " where informacionAdjunto.expedienteEmpleado.idEmpleado = :idEmpleado" + " order by informacionAdjunto.entidadContexto";

    private static final String TIENE_ADJUNTO_UNICO = "select" + " case count(info.idInformacionAdjuntoEmpleado)" + " when 0 then false" + " else true" + " end"
            + " from  InformacionAdjuntoEmpleadoEntity as info" + " where info.expedienteEmpleado.idEmpleado = :idEmpleado"
            + " and info.documentoAdjuntable.idDocumentoAdjuntable = :idDocumentoAdjuntable";

    private static final String CONSULTAR_DOCUMENTOS_ADJUNTOS_POR_TIPO_DOCUMENTO = "select i.documentoAdjuntable.descripcion"
            + " from InformacionAdjuntoEmpleadoEntity as i" + " where i.entidadContexto = :entidadContexto" + " and i.idEntidadContexto = :idEntidadContexto";

    /**
     *
     * @param entidadContexto
     * @param idEntidadContexto
     * @return
     */
    public List<InformacionAdjuntoEmpleadoEntity> consultarInformacionAdjuntosPorEntidadContextoIdEntidadContexto(EntidadContexto entidadContexto,
            int idEntidadContexto) {
        List<InformacionAdjuntoEmpleadoEntity> listaInformacionAdjuntos = em
                .createQuery(OBTENER_INFORMACION_ADJUNTO_POR_ENTIDAD_CONTEXTO_ID_ENTIDAD_CONTEXTO, InformacionAdjuntoEmpleadoEntity.class)
                .setParameter("entidadContexto", entidadContexto).setParameter("idEntidadContexto", idEntidadContexto).getResultList();

        return listaInformacionAdjuntos;
    }

    /**
     *
     * @param entidadContexto
     * @param idEntidadContexto
     * @param idDocumentoAdjuntable
     * @return the java.util.List<mx.gob.saludtlax.rh.persistencia.InformacionAdjuntoEmpleadoEntity>
     */
    public List<InformacionAdjuntoEmpleadoEntity> consultarInformacionAduntosPorEntidadContextoIdEntidadContextoIdDocumentoAdjuntable(
            EntidadContexto entidadContexto, int idEntidadContexto, int idDocumentoAdjuntable) {

        List<InformacionAdjuntoEmpleadoEntity> listaInformacionAdjuntos = em
                .createQuery(OBTENER_INFORMACION_ADJUNTO_POR_ENTIDAD_CONTEXTO_ID_ENTIDAD_CONTEXTO_ID_DOCUMENTO_ADJUNTABLE,
                        InformacionAdjuntoEmpleadoEntity.class)
                .setParameter("entidadContexto", entidadContexto).setParameter("idEntidadContexto", idEntidadContexto)
                .setParameter("idDocumentoAdjuntable", idDocumentoAdjuntable).getResultList();

        return listaInformacionAdjuntos;
    }

    /**
     *
     * @param idEmpleado
     * @param idDocumentoAdjuntable
     * @return
     */
    public boolean tieneAdjuntoUnico(Integer idEmpleado, Integer idDocumentoAdjuntable) {
        Boolean tieneAdjunto = em.createQuery(TIENE_ADJUNTO_UNICO, Boolean.class).setParameter("idEmpleado", idEmpleado)
                .setParameter("idDocumentoAdjuntable", idDocumentoAdjuntable).getSingleResult();
        return tieneAdjunto;
    }

    /**
     *
     * @param idEmpleado
     * @return
     */
    public List<InformacionAdjuntoEmpleadoEntity> consultarInformacionAdjuntosDelEmpleado(int idEmpleado) {
        List<InformacionAdjuntoEmpleadoEntity> listaInformacionAdjuntos = em
                .createQuery(OBTENER_INFORMACION_ADJUNTOS_DEL_EMPLEADO, InformacionAdjuntoEmpleadoEntity.class).setParameter("idEmpleado", idEmpleado)
                .getResultList();

        return listaInformacionAdjuntos;
    }

    /**
     * Retorna la lista de los documentos adjuntos por tipo de documento.
     *
     * @param entidadContexto
     * @param idEntidadContexto
     * @return una lista de los documentos adjuntos.
     *
     * @author Leila Schiaffini Ehuan
     */
    public List<String> consultarDocumentosAdjuntosPorEntidadContexto(EntidadContexto entidadContexto, Integer idEntidadContexto) {

        List<String> documentos = em.createQuery(CONSULTAR_DOCUMENTOS_ADJUNTOS_POR_TIPO_DOCUMENTO, String.class)
                .setParameter("entidadContexto", entidadContexto).setParameter("idEntidadContexto", idEntidadContexto).getResultList();
        return documentos;
    }
}
