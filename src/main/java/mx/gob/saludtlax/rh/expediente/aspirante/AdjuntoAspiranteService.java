/*
 * AdjuntoAspiranteService.java
 * Creado el May 18, 2016 4:55:40 PM
 */

package mx.gob.saludtlax.rh.expediente.aspirante;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.jboss.logging.Logger;

import mx.gob.saludtlax.rh.configuracion.app.ConfiguracionAplicacion;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.expediente.DocumentoAdjuntableDTO;
import mx.gob.saludtlax.rh.expediente.EntidadContexto;
import mx.gob.saludtlax.rh.expediente.InformacionAdjuntoDTO;
import mx.gob.saludtlax.rh.persistencia.DocumentoAdjuntableEntity;
import mx.gob.saludtlax.rh.persistencia.DocumentoAdjuntableRepository;
import mx.gob.saludtlax.rh.persistencia.EscolaridadEntity;
import mx.gob.saludtlax.rh.persistencia.EscolaridadRepository;
import mx.gob.saludtlax.rh.persistencia.ExpedienteAspiranteEntity;
import mx.gob.saludtlax.rh.persistencia.ExpedienteAspiranteRepository;
import mx.gob.saludtlax.rh.persistencia.HistorialAcademicoRepository;
import mx.gob.saludtlax.rh.persistencia.InformacionAdjuntoAspiranteEntity;
import mx.gob.saludtlax.rh.persistencia.InformacionAdjuntoAspiranteRepository;
import mx.gob.saludtlax.rh.persistencia.VistaPreviaAdjuntoAspiranteEntity;
import mx.gob.saludtlax.rh.persistencia.VistaPreviaAdjuntoAspiranteRepository;
import mx.gob.saludtlax.rh.util.ArchivoUtil;
import mx.gob.saludtlax.rh.util.TipoArchivo;

/**
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 *
 */
public class AdjuntoAspiranteService implements Serializable {

    private static final long serialVersionUID = 997851947400123632L;

    @Inject
    private ConfiguracionAplicacion configuracionAplicacionEJB;
    @Inject
    private DocumentoAdjuntableRepository documentoAdjuntableRepository;
    @Inject
    private EscolaridadRepository escolaridadRepository;
    @Inject
    private ExpedienteAspiranteRepository expedienteAspiranteRepository;
    @Inject
    private HistorialAcademicoRepository historialAcademicoRepository;
    @Inject
    private InformacionAdjuntoAspiranteRepository informacionAdjuntoRepository;
    @Inject
    private VistaPreviaAdjuntoAspiranteRepository vistaPreviaAdjuntoAspiranteRepository;

    private static final Logger LOGGER = Logger.getLogger(AdjuntoAspiranteService.class.getName());

    /**
     *
     * @param informacionDelAdjunto
     * @param bytesAdjunto
     * @return
     */
    protected int crear(InformacionAdjuntoDTO informacionDelAdjunto, byte[] bytesAdjunto) {
        try {
            DocumentoAdjuntableEntity documentoAdjuntableEntity = documentoAdjuntableRepository
                    .obtenerPorId(informacionDelAdjunto.getDocumentoAdjuntable().getIdDocumentoAdjuntable());

            if (documentoAdjuntableEntity == null) {
                throw new ValidacionException("El documento adjuntable que indico no es valido.", ValidacionCodigoError.VALOR_REQUERIDO);
            }

            if (documentoAdjuntableEntity.isUnico() && (informacionAdjuntoRepository.tieneAdjuntoUnico(informacionDelAdjunto.getIdAspirante(),
                    documentoAdjuntableEntity.getIdDocumentoAdjuntable()))) {

                throw new ValidacionException("El empleado ya ha adjuntado una foto.", ValidacionCodigoError.VALOR_REQUERIDO);
            }

            InformacionAdjuntoAspiranteEntity informacionAdjuntoEntity = convertirDTOAEntidad(informacionDelAdjunto);

            String nombreArchivo = informacionDelAdjunto.getNombreAdjunto();
            TipoArchivo ext = informacionDelAdjunto.getExtension();
            Map<String, Object> map = ArchivoUtil.validarArchivo(nombreArchivo, ext, bytesAdjunto);
            nombreArchivo = (String) map.get("NOMBRE_DE_ARCHIVO");
            ext = (TipoArchivo) map.get("EXTENSION");

            informacionAdjuntoEntity.setNombreAdjunto(nombreArchivo);
            informacionAdjuntoEntity.setExtension(ext);

            informacionAdjuntoRepository.crear(informacionAdjuntoEntity);

            ArchivoUtil.guardarArchivo(generarRuta(informacionAdjuntoEntity.getExpedienteAspirante().getIdExpedienteAspirante()),
                    informacionAdjuntoEntity.getIdInformacionAdjuntoAspirante() + ext.getExtension(true), bytesAdjunto, usarCarpetaUsuario());

            VistaPreviaAdjuntoAspiranteEntity vistaPrevia = new VistaPreviaAdjuntoAspiranteEntity();
            vistaPrevia.setIdVistaPreviaAdjuntoAspirante(null);
            vistaPrevia.setVistaPrevia(ArchivoUtil.crearVistaPrevia(informacionAdjuntoEntity.getExtension(), bytesAdjunto));
            vistaPrevia.setInformacionAdjuntoAspirante(informacionAdjuntoEntity);

            vistaPreviaAdjuntoAspiranteRepository.crear(vistaPrevia);

            ArchivoUtil.crearVistaPrevia(informacionAdjuntoEntity.getExtension(), bytesAdjunto);

            return informacionAdjuntoEntity.getIdInformacionAdjuntoAspirante();
        } catch (IOException ex) {
            LOGGER.error("Error al guardar el archivo en disco.", ex);
            return -1;
        }
    }

    /**
     *
     * @param idAdjunto
     * @return
     */
    protected InformacionAdjuntoDTO obtenerInformacionAdjuntoPorIdAdjunto(int idAdjunto) {
        InformacionAdjuntoAspiranteEntity entidad = informacionAdjuntoRepository.obtenerPorId(idAdjunto);

        return convertirEntidadADTO(entidad);
    }

    /**
     *
     * @param entidadContexto
     * @param idEntidadContexto
     * @return
     */
    protected List<InformacionAdjuntoDTO> consultarInformacionAdjuntosPorTipoDocumentoIdTipoDocumento(EntidadContexto entidadContexto, int idEntidadContexto) {
        List<InformacionAdjuntoAspiranteEntity> entidades = informacionAdjuntoRepository
                .consultarInformacionAdjuntosPorEntidadContextoIdEntidadContexto(entidadContexto, idEntidadContexto);

        return convertirEntidadesADTOs(entidades);
    }

    /**
     *
     * @param entidadContexto
     * @param idEntidadContexto
     * @param idDocumentoAdjuntable
     * @return
     */
    protected List<InformacionAdjuntoDTO> obtenerInformacionAduntosPorTipoDocumentoIdTipoDocumentoIdDocumentoAdjuntable(EntidadContexto entidadContexto,
            int idEntidadContexto, int idDocumentoAdjuntable) {
        List<InformacionAdjuntoAspiranteEntity> entidades = informacionAdjuntoRepository
                .consultarInformacionAduntosPorEntidadContextoIdEntidadContextoIdDocumentoAdjuntable(entidadContexto, idEntidadContexto, idDocumentoAdjuntable);

        return convertirEntidadesADTOs(entidades);
    }

    /**
     *
     * @param idAspirante
     * @return
     */
    protected List<InformacionAdjuntoDTO> consultarInformacionAdjuntosPorIdAspirante(int idAspirante) {
        List<InformacionAdjuntoAspiranteEntity> entidades = informacionAdjuntoRepository.consultarInformacionAdjuntosDelAspirante(idAspirante);

        return convertirEntidadesADTOs(entidades);
    }

    /**
     *
     * @param idAdjunto
     * @return
     */
    protected byte[] obtenerAdjuntoPorIdAdjunto(int idAdjunto) {
        try {
            InformacionAdjuntoAspiranteEntity informacionAdjuntoAspirante = informacionAdjuntoRepository.obtenerPorId(idAdjunto);
            String path = generarRuta(informacionAdjuntoAspirante.getExpedienteAspirante().getIdExpedienteAspirante());

            return ArchivoUtil.leerArchivo(path,
                    informacionAdjuntoAspirante.getIdInformacionAdjuntoAspirante() + informacionAdjuntoAspirante.getExtension().getExtension(true),
                    usarCarpetaUsuario());
        } catch (IOException ex) {
            LOGGER.error("Imposible leer el archivo", ex);
            return null;
        }
    }

    protected byte[] obtenerVistaPreviaPorIDAdjunto(int idAdjunto) {
        return vistaPreviaAdjuntoAspiranteRepository.obtenerPorIdAdjunto(idAdjunto).getVistaPrevia();
    }

    /**
     *
     * @param parametros
     */
    protected void actualizar(Map<String, Object> parametros) {
        Integer idAdjunto = (Integer) parametros.get("idAdjunto");

        if ((idAdjunto == null) || (idAdjunto < 1)) {
            throw new ValidacionException("El ID del adjunto no puede ser nulo, cero o negativo", ValidacionCodigoError.NUMERO_NEGATIVO);
        }

        if (parametros.containsKey("adjunto")) {
            if (!parametros.containsKey("nombreAdjunto") && !(parametros.containsKey("extension"))) {
                throw new ValidacionException("Debe incluir el nombre del archivo y la extensión.", ValidacionCodigoError.VALOR_REQUERIDO);
            } else {
                byte[] bytes = (byte[]) parametros.get("adjunto");
                String nombreArchivo = (String) parametros.get("nombreAdjunto");
                TipoArchivo ext = (TipoArchivo) parametros.get("extension");
                Map<String, Object> map = ArchivoUtil.validarArchivo(nombreArchivo, ext, bytes);
                nombreArchivo = (String) map.get("NOMBRE_DE_ARCHIVO");
                ext = (TipoArchivo) map.get("EXTENSION");

                parametros.put("nombreAdjunto", nombreArchivo);
                parametros.put("extension", ext);
            }
        } else if (parametros.containsKey("nombreAdjunto")) {
            String nombreArchivo = ArchivoUtil.obtenerNombreSinExtension((String) parametros.get("nombreAdjunto"));
            parametros.put("nombreAdjunto", nombreArchivo);
        }

        InformacionAdjuntoAspiranteEntity infoAdjunto = informacionAdjuntoRepository.obtenerPorId(idAdjunto);

        for (Map.Entry<String, Object> parametro : parametros.entrySet()) {

            String clave = parametro.getKey();
            Object valor = parametro.getValue();

            switch (clave) {
                case "tipoDocumento":
                    EntidadContexto tipoDocumento = (EntidadContexto) valor;
                    infoAdjunto.setEntidadContexto(tipoDocumento);
                    break;
                case "idTipoDocumento":
                    Integer idTipoDocumento = (Integer) valor;
                    infoAdjunto.setIdEntidadContexto(idTipoDocumento);
                    break;
                case "nombreAdjunto":
                    String nombreAdjunto = (String) valor;
                    infoAdjunto.setNombreAdjunto(nombreAdjunto);
                    break;
                case "extension":
                    TipoArchivo extension = (TipoArchivo) valor;
                    infoAdjunto.setExtension(extension);
                    break;
                case "documentoAdjuntable":
                    Integer idDocumentoAdjuntable = (Integer) valor;
                    DocumentoAdjuntableEntity documentoAdjuntableEntity = documentoAdjuntableRepository.obtenerPorId(idDocumentoAdjuntable);
                    infoAdjunto.setDocumentoAdjuntable(documentoAdjuntableEntity);
                    break;
            }
        }

        informacionAdjuntoRepository.actualizar(infoAdjunto);

        if (parametros.containsKey("adjunto")) {
            byte[] bytes = (byte[]) parametros.get("adjunto");

            VistaPreviaAdjuntoAspiranteEntity vistaPrevia = vistaPreviaAdjuntoAspiranteRepository.obtenerPorIdAdjunto(idAdjunto);
            vistaPrevia.setVistaPrevia(ArchivoUtil.crearVistaPrevia(infoAdjunto.getExtension(), bytes));
            vistaPreviaAdjuntoAspiranteRepository.actualizar(vistaPrevia);

            informacionAdjuntoRepository.actualizar(infoAdjunto);

            try {
                String ruta = generarRuta(infoAdjunto.getExpedienteAspirante().getIdExpedienteAspirante());

                ArchivoUtil.eliminarArchivoSoloConNombre(ruta, infoAdjunto.getIdInformacionAdjuntoAspirante().toString(), usarCarpetaUsuario());
                ArchivoUtil.guardarArchivo(ruta, infoAdjunto.getIdInformacionAdjuntoAspirante() + infoAdjunto.getExtension().getExtension(true), bytes,
                        usarCarpetaUsuario());
            } catch (IOException ex) {
                LOGGER.error("Error al modificar el archivo adjunto.", ex);
            }
        }
    }

    /**
     *
     * @param idAdjunto
     */
    protected void elimnar(int idAdjunto) {
        try {
            vistaPreviaAdjuntoAspiranteRepository.eliminarPorIdAdjunto(idAdjunto);

            InformacionAdjuntoAspiranteEntity informacionAdjuntoAspirante = informacionAdjuntoRepository.obtenerPorId(idAdjunto);

            int idEntidadContexto = informacionAdjuntoAspirante.getIdEntidadContexto();
            EntidadContexto entidadContexto = informacionAdjuntoAspirante.getEntidadContexto();

            if (EntidadContexto.HISTORIAL_ACADEMICO.equals(entidadContexto)) {
                List<InformacionAdjuntoAspiranteEntity> historiales = informacionAdjuntoRepository
                        .consultarInformacionAdjuntosPorEntidadContextoIdEntidadContexto(entidadContexto, idEntidadContexto);

                historialAcademicoRepository.actualizarTieneDocumentos(idEntidadContexto, (historiales != null && historiales.size() > 1));
            }

            ArchivoUtil.eliminarArchivoSoloConNombre(generarRuta(informacionAdjuntoAspirante.getExpedienteAspirante().getIdExpedienteAspirante()),
                    informacionAdjuntoAspirante.getIdInformacionAdjuntoAspirante().toString(), usarCarpetaUsuario());

            informacionAdjuntoRepository.eliminar(informacionAdjuntoAspirante);
        } catch (IOException ex) {
            LOGGER.error("Error al leer el archivo a eliminar.", ex);
        }
    }

    /**
     *
     * @param entidad
     * @return
     */
    private InformacionAdjuntoDTO convertirEntidadADTO(InformacionAdjuntoAspiranteEntity entidad) {
        if (entidad == null) {
            throw new NullPointerException("No se puede convertir una entidad que es nula");
        }

        DocumentoAdjuntableDTO documentoAdjuntable = new DocumentoAdjuntableDTO(entidad.getDocumentoAdjuntable().getIdDocumentoAdjuntable(),
                entidad.getDocumentoAdjuntable().getDescripcion());

        String detalle = "";

        if (entidad.getEntidadContexto() == EntidadContexto.HISTORIAL_ACADEMICO) {
            LOGGER.debugv("ID del tipo documento. {0}", entidad.getIdEntidadContexto());

            EscolaridadEntity escolaridad = escolaridadRepository.escolaridadPorIdHistorialAcademico(entidad.getIdEntidadContexto());
            detalle = escolaridad.getEscolaridad() + "-" + escolaridad.getGrupoAcademico();
        }

        InformacionAdjuntoDTO dto = new InformacionAdjuntoDTO(entidad.getIdInformacionAdjuntoAspirante(), entidad.getEntidadContexto(),
                entidad.getIdEntidadContexto(), entidad.getNombreAdjunto(), entidad.getExtension(), documentoAdjuntable,
                (entidad.getExpedienteAspirante() == null) ? null : entidad.getExpedienteAspirante().getIdExpedienteAspirante(), null, // El ID del empleado
                (entidad.getExpedienteAspirante() == null) ? null : entidad.getExpedienteAspirante().getIdAspirante(), detalle);

        return dto;
    }

    /**
     *
     * @param listaEntidades
     * @return
     */
    private List<InformacionAdjuntoDTO> convertirEntidadesADTOs(List<InformacionAdjuntoAspiranteEntity> listaEntidades) {
        List<InformacionAdjuntoDTO> listaDTOs = new ArrayList<>();

        for (InformacionAdjuntoAspiranteEntity entidad : listaEntidades) {
            InformacionAdjuntoDTO dto = convertirEntidadADTO(entidad);
            listaDTOs.add(dto);
        }

        return listaDTOs;
    }

    /**
     *
     * @param dto
     * @return
     */
    private InformacionAdjuntoAspiranteEntity convertirDTOAEntidad(InformacionAdjuntoDTO dto) {
        if (dto == null) {
            throw new NullPointerException("No se puede convertir una DTO que es nulo");
        }

        DocumentoAdjuntableEntity documentoAdjuntable = documentoAdjuntableRepository.obtenerPorId(dto.getDocumentoAdjuntable().getIdDocumentoAdjuntable());
        ExpedienteAspiranteEntity expedienteAspirante = expedienteAspiranteRepository.obtenerPorId(dto.getIdExpediente());

        InformacionAdjuntoAspiranteEntity entidad = new InformacionAdjuntoAspiranteEntity();

        entidad.setIdInformacionAdjuntoAspirante(null);
        entidad.setEntidadContexto(dto.getEntidadContexto());
        entidad.setIdEntidadContexto(dto.getIdEntidadContexto());
        entidad.setNombreAdjunto(dto.getNombreAdjunto());
        entidad.setExtension(dto.getExtension());
        entidad.setDocumentoAdjuntable(documentoAdjuntable);
        entidad.setExpedienteAspirante(expedienteAspirante);

        return entidad;
    }

    private String generarRuta(Integer idExpedienteEmpleado) {
        StringBuilder path = new StringBuilder(configuracionAplicacionEJB.getConfiguracion("adjuntos.basepath"));
        path.append("/aspirantes/");
        path.append(idExpedienteEmpleado);

        return path.toString();
    }

    private boolean usarCarpetaUsuario() {
        return Boolean.parseBoolean(configuracionAplicacionEJB.getConfiguracion("adjuntos.usar-home"));
    }
}