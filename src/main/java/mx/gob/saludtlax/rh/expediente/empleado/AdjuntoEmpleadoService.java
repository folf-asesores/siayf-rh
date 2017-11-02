/*
 * AdjuntoEmpleadoService.java
 * Creado el May 18, 2016 4:55:40 PM 
 */
package mx.gob.saludtlax.rh.expediente.empleado;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.configuracion.app.ConfiguracionAplicacion;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.expediente.DocumentoAdjuntableDTO;
import mx.gob.saludtlax.rh.expediente.EntidadContexto;
import mx.gob.saludtlax.rh.expediente.InformacionAdjuntoDTO;
import mx.gob.saludtlax.rh.persistencia.DependienteEconomicoRepository;
import mx.gob.saludtlax.rh.persistencia.DocumentoAdjuntableEntity;
import mx.gob.saludtlax.rh.persistencia.DocumentoAdjuntableRepository;
import mx.gob.saludtlax.rh.persistencia.EscolaridadEntity;
import mx.gob.saludtlax.rh.persistencia.EscolaridadRepository;
import mx.gob.saludtlax.rh.persistencia.ExpedienteAspiranteEntity;
import mx.gob.saludtlax.rh.persistencia.ExpedienteAspiranteRepository;
import mx.gob.saludtlax.rh.persistencia.ExpedienteEmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.ExpedienteEmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.HistorialAcademicoRepository;
import mx.gob.saludtlax.rh.persistencia.InformacionAdjuntoAspiranteEntity;
import mx.gob.saludtlax.rh.persistencia.InformacionAdjuntoAspiranteRepository;
import mx.gob.saludtlax.rh.persistencia.InformacionAdjuntoEmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.InformacionAdjuntoEmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.VistaPreviaAdjuntoAspiranteRepository;
import mx.gob.saludtlax.rh.persistencia.VistaPreviaAdjuntoEmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.VistaPreviaAdjuntoEmpleadoRepository;
import mx.gob.saludtlax.rh.util.ArchivoUtil;
import mx.gob.saludtlax.rh.util.TipoArchivo;

import org.jboss.logging.Logger;

/**
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 *
 */
public class AdjuntoEmpleadoService implements Serializable {

    private static final long serialVersionUID = -1383110600369840704L;

    @Inject private ConfiguracionAplicacion configuracionAplicacionEJB;
    @Inject private DependienteEconomicoRepository dependienteEconomicoRepository;
    @Inject private DocumentoAdjuntableRepository documentoAdjuntableRepository;
    @Inject private EscolaridadRepository escolaridadRepository;
    @Inject private ExpedienteAspiranteRepository aspiranteRepository;
    @Inject private ExpedienteEmpleadoRepository expedienteEmpleadoRepository;
    @Inject private HistorialAcademicoRepository historialAcademicoRepository;
    @Inject private InformacionAdjuntoEmpleadoRepository informacionAdjuntoEmpleadoRepository;
    @Inject private InformacionAdjuntoAspiranteRepository informacionAdjuntoAspiranteRepository;
    @Inject private  VistaPreviaAdjuntoAspiranteRepository vistaPreviaAdjuntoAspiranteRepository;
    @Inject private VistaPreviaAdjuntoEmpleadoRepository vistaPreviaAdjuntoEmpleadoRepository;

    private static final Logger LOGGER = Logger.getLogger(AdjuntoEmpleadoService.class.getName());

    /**
     * <p>Guarda un adjunto en el almacén de datos y retorna el ID. En caso de 
     * que el haya ocurrido un error retorna un ID negativo.</p>
     *
     * @param informacionDelAdjunto la información sobre el adjunto.
     * @param bytesAdjunto los bytes del archivo
     * @return el ID del adjunto.
     * @throws NullPointerException si la información del adjunto es nula o los 
     * bytes del archivo son nulos.
     * @throws ValidacionException si no tiene el ID del empleado, la entidad 
     * contexto, ID del documento adjuntable y si se trata de adjuntar un 
     * documento unico. 
     */
    protected int crear(InformacionAdjuntoDTO informacionDelAdjunto, byte[] bytesAdjunto) {

        try {
            DocumentoAdjuntableEntity documentoAdjuntableEntity = documentoAdjuntableRepository.obtenerPorId(informacionDelAdjunto.getDocumentoAdjuntable().getIdDocumentoAdjuntable());
            
            if (documentoAdjuntableEntity == null) {
                throw new ValidacionException("El documento adjuntable que indico no es valido.", ValidacionCodigoError.VALOR_REQUERIDO);
            }
            
            if(documentoAdjuntableEntity.isUnico()
                    && informacionAdjuntoEmpleadoRepository.tieneAdjuntoUnico(informacionDelAdjunto.getIdEmpleado(), documentoAdjuntableEntity.getIdDocumentoAdjuntable())
                    && !informacionAdjuntoEmpleadoRepository.consultarInformacionAdjuntosPorEntidadContextoIdEntidadContexto(informacionDelAdjunto.getEntidadContexto(), informacionDelAdjunto.getIdEntidadContexto()).isEmpty()) {
                throw new ValidacionException("El empleado tiene ya tiene un adjunto del tipo: " + documentoAdjuntableEntity.getDescripcion(), ValidacionCodigoError.VALOR_DUPLICADO);
            }
            
            InformacionAdjuntoEmpleadoEntity informacionAdjuntoEntity = convertirDTOAEntidad(informacionDelAdjunto);
            
            String nombreArchivo = informacionDelAdjunto.getNombreAdjunto();
            TipoArchivo ext = informacionDelAdjunto.getExtension();
            Map<String, Object> map = ArchivoUtil.validarArchivo(nombreArchivo, ext,
                    bytesAdjunto);
            nombreArchivo = (String) map.get("NOMBRE_DE_ARCHIVO");
            ext = (TipoArchivo) map.get("EXTENSION");
            
            informacionAdjuntoEntity.setNombreAdjunto(nombreArchivo);
            informacionAdjuntoEntity.setExtension(ext);
            
            informacionAdjuntoEmpleadoRepository.crear(informacionAdjuntoEntity);
            
            ArchivoUtil.guardarArchivo(
                    generarRuta(informacionAdjuntoEntity
                            .getExpedienteEmpleado()
                            .getIdExpedienteEmpleado()), 
                    informacionAdjuntoEntity.getIdInformacionAdjuntoEmpleado() 
                            + ext.getExtension(true), bytesAdjunto, usarCarpetaUsuario());

            VistaPreviaAdjuntoEmpleadoEntity vistaPrevia = new VistaPreviaAdjuntoEmpleadoEntity();
            vistaPrevia.setIdVistaPreviaAdjuntoEmpleado(null);
            vistaPrevia.setVistaPrevia(ArchivoUtil.crearVistaPrevia(informacionAdjuntoEntity.getExtension(), bytesAdjunto));
            vistaPrevia.setInformacionAdjuntoEmpleado(informacionAdjuntoEntity);
            
            vistaPreviaAdjuntoEmpleadoRepository.crear(vistaPrevia);
            
            return informacionAdjuntoEntity.getIdInformacionAdjuntoEmpleado();
        } catch (IOException ex) {
            LOGGER.error("Error al guardar el archivo en disco.", ex);
            return -1;
        }
    }    

    protected InformacionAdjuntoDTO obtenerInformacionAdjuntoPorIdAdjunto(int idAdjunto) {
        InformacionAdjuntoEmpleadoEntity entidad = informacionAdjuntoEmpleadoRepository.obtenerPorId(idAdjunto);

        return convertirEntidadADTO(entidad);
    }

    protected List<InformacionAdjuntoDTO> obtenerInformacionAduntosPorContextoEntidadIdContextoEntidadIdDocumentoAdjuntable(
            EntidadContexto entidadContexto, int idEntidadContexto, int idDocumentoAdjuntable) {
        List<InformacionAdjuntoEmpleadoEntity> entidades = informacionAdjuntoEmpleadoRepository
                .consultarInformacionAduntosPorEntidadContextoIdEntidadContextoIdDocumentoAdjuntable(entidadContexto, idEntidadContexto,
                        idDocumentoAdjuntable);

        return convertirEntidadesADTOs(entidades);
    }    

    protected byte[] obtenerAdjuntoPorIdAdjunto(int idAdjunto) {
        try {
            InformacionAdjuntoEmpleadoEntity informacionAdjuntoEmpleado = informacionAdjuntoEmpleadoRepository.obtenerPorId(idAdjunto);
            String path = generarRuta(informacionAdjuntoEmpleado.getExpedienteEmpleado().getIdExpedienteEmpleado());
            
            return ArchivoUtil.leerArchivo(path, 
                    informacionAdjuntoEmpleado.getIdInformacionAdjuntoEmpleado()
                            + informacionAdjuntoEmpleado.getExtension()
                                    .getExtension(true),
                    usarCarpetaUsuario());

        } catch (IOException ex) {
            LOGGER.error("Imposible leer el archivo", ex);
            return null;
        }
    }

    protected byte[] obtenerVistaPreviaPorIdAdjunto(int idAdjunto) {
        return vistaPreviaAdjuntoEmpleadoRepository
                .obtenerPorIdAdjunto(idAdjunto).getVistaPrevia();
    }    
    
    protected List<InformacionAdjuntoDTO> consultarInformacionAdjuntosPorEntidadContextoIdEntidadContexto(
            EntidadContexto entidadContexto, int idEntidadContexto) {
        List<InformacionAdjuntoEmpleadoEntity> entidades = informacionAdjuntoEmpleadoRepository
                .consultarInformacionAdjuntosPorEntidadContextoIdEntidadContexto(entidadContexto, idEntidadContexto);

        return convertirEntidadesADTOs(entidades);
    }

    protected List<InformacionAdjuntoDTO> consultarInformacionAdjuntosPorIdEmpleado(int idEmpleado) {
        List<InformacionAdjuntoEmpleadoEntity> entidades = informacionAdjuntoEmpleadoRepository
                .consultarInformacionAdjuntosDelEmpleado(idEmpleado);

        return convertirEntidadesADTOs(entidades);
    }
    
    protected List<String> documentosAdjuntosPorEntidadContexto(
            EntidadContexto entidadContexto,
            Integer idEntidadContexto) {

        return informacionAdjuntoEmpleadoRepository
                .consultarDocumentosAdjuntosPorEntidadContexto(entidadContexto, 
                        idEntidadContexto);
    }    

    protected void actualizar(Map<String, Object> parametros) {
        Integer idAdjunto = (Integer) parametros.get("idAdjunto");

        if ((idAdjunto == null) || (idAdjunto < 1)) {
            throw new ValidacionException(
                    "El ID del adjunto no puede ser nulo, cero o negativo",
                    ValidacionCodigoError.VALOR_REQUERIDO
            );
        }

        if (parametros.containsKey("adjunto")) {
            if (!parametros.containsKey("nombreAdjunto")
                    && !(parametros.containsKey("extension"))) {
                throw new ValidacionException(
                        "Debe incluir el nombre del archivo y la extensión.",
                        ValidacionCodigoError.VALOR_REQUERIDO
                );
            } else {
                byte[] bytes = (byte[]) parametros.get("adjunto");
                String nombreArchivo = (String) parametros.get("nombreAdjunto");
                TipoArchivo ext = (TipoArchivo) parametros.get("extension");
                Map<String, Object> map = ArchivoUtil.validarArchivo(nombreArchivo, ext,
                        bytes);
                nombreArchivo = (String) map.get("NOMBRE_DE_ARCHIVO");
                ext = (TipoArchivo) map.get("EXTENSION");

                parametros.put("nombreAdjunto", nombreArchivo);
                parametros.put("extension", ext);
            }
        } else if (parametros.containsKey("nombreAdjunto")) {
            String nombreArchivo = ArchivoUtil.obtenerNombreSinExtension((String) parametros
                    .get("nombreAdjunto"));
            parametros.put("nombreAdjunto", nombreArchivo);
        }

        InformacionAdjuntoEmpleadoEntity infoAdjunto = informacionAdjuntoEmpleadoRepository.obtenerPorId(idAdjunto);

        for (Map.Entry<String, Object> parametro : parametros.entrySet()) {

            String clave = parametro.getKey();
            Object valor = parametro.getValue();

            switch (clave) {
                case "entidadContexto":
                    EntidadContexto entidadContexto = (EntidadContexto) valor;
                    infoAdjunto.setEntidadContexto(entidadContexto);
                    break;
                case "idEntidadContexto":
                    Integer idEntidadContexto = (Integer) valor;
                    infoAdjunto.setIdEntidadContexto(idEntidadContexto);
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

        informacionAdjuntoEmpleadoRepository.actualizar(infoAdjunto);

        if (parametros.containsKey("adjunto")) {
            byte[] bytes = (byte[]) parametros.get("adjunto");
            
            VistaPreviaAdjuntoEmpleadoEntity vistaPrevia = vistaPreviaAdjuntoEmpleadoRepository.obtenerPorIdAdjunto(idAdjunto);
            vistaPrevia.setVistaPrevia(ArchivoUtil.crearVistaPrevia(infoAdjunto.getExtension(), bytes));
            vistaPreviaAdjuntoEmpleadoRepository.actualizar(vistaPrevia);

            informacionAdjuntoEmpleadoRepository.actualizar(infoAdjunto);
            
            try {
                String ruta = generarRuta(infoAdjunto.getExpedienteEmpleado()
                        .getIdExpedienteEmpleado());

                ArchivoUtil.eliminarArchivoSoloConNombre(ruta, 
                        infoAdjunto.getIdInformacionAdjuntoEmpleado()
                                .toString(), usarCarpetaUsuario());
                ArchivoUtil.guardarArchivo(
                        ruta,
                        infoAdjunto.getIdInformacionAdjuntoEmpleado() 
                                + infoAdjunto.getExtension().getExtension(true),
                        bytes, usarCarpetaUsuario());
            } catch (IOException ex) {
                LOGGER.error("Error al modificar el archivo adjunto.", ex);
            }
        }
    }

    protected void importarExpedienteAspirante(Integer idAspirantente, 
            Integer idEmpleado) {
        List<InformacionAdjuntoAspiranteEntity> 
                iaas = informacionAdjuntoAspiranteRepository
                        .consultarInformacionAdjuntosDelAspirante(idAspirantente);
        ExpedienteEmpleadoEntity expedienteEmpleado = expedienteEmpleadoRepository.obtenerPorIdEmpleado(idEmpleado);
        
        for(InformacionAdjuntoAspiranteEntity iaa : iaas) {
            InformacionAdjuntoEmpleadoEntity iae = new InformacionAdjuntoEmpleadoEntity();
            
            iae.setIdInformacionAdjuntoEmpleado(null);
            iae.setNombreAdjunto(iaa.getNombreAdjunto());
            iae.setExtension(iaa.getExtension());
            iae.setDocumentoAdjuntable(iaa.getDocumentoAdjuntable());
            iae.setEntidadContexto(iaa.getEntidadContexto());
            iae.setIdEntidadContexto(iaa.getIdEntidadContexto());
            iae.setExpedienteEmpleado(expedienteEmpleado);
            
            informacionAdjuntoEmpleadoRepository.crear(iae);
            byte[] vistaPrevia = vistaPreviaAdjuntoAspiranteRepository.obtenerPorIdAdjunto(iaa.getIdInformacionAdjuntoAspirante()).getVistaPrevia();
            
            VistaPreviaAdjuntoEmpleadoEntity vistaPreviaAdjuntoEmpleado = new VistaPreviaAdjuntoEmpleadoEntity();

            vistaPreviaAdjuntoEmpleado.setIdVistaPreviaAdjuntoEmpleado(null);
            vistaPreviaAdjuntoEmpleado.setInformacionAdjuntoEmpleado(iae);
            vistaPreviaAdjuntoEmpleado.setVistaPrevia(vistaPrevia);

            vistaPreviaAdjuntoEmpleadoRepository.crear(vistaPreviaAdjuntoEmpleado);
        }
        
        ExpedienteAspiranteEntity expedienteAspirante = iaas.get(0).getExpedienteAspirante();
        String origen = generarRuta(expedienteAspirante.getIdExpedienteAspirante(), false);
        String destino = generarRuta(expedienteEmpleado.getIdExpedienteEmpleado());
        
        ArchivoUtil.moverArchivo(origen, destino, usarCarpetaUsuario());
        
        for(InformacionAdjuntoAspiranteEntity iaa : iaas) {
            vistaPreviaAdjuntoAspiranteRepository.eliminarPorIdAdjunto(iaa.getIdInformacionAdjuntoAspirante());
            informacionAdjuntoAspiranteRepository.eliminar(iaa);
        }
        
        aspiranteRepository.eliminar(expedienteAspirante);
    }

    protected void eliminar(int idAdjunto) {
        try {
            vistaPreviaAdjuntoEmpleadoRepository.eliminarPorIdAdjunto(idAdjunto);
            
            InformacionAdjuntoEmpleadoEntity informacionAdjuntoEmpleado 
                    = informacionAdjuntoEmpleadoRepository.obtenerPorId(idAdjunto);

            int idEntidadContexto = informacionAdjuntoEmpleado.getIdEntidadContexto();
            EntidadContexto entidadContexto = informacionAdjuntoEmpleado.getEntidadContexto();
            
            if(EntidadContexto.HISTORIAL_ACADEMICO.equals(entidadContexto)) {
                List<InformacionAdjuntoEmpleadoEntity> historiales = informacionAdjuntoEmpleadoRepository.consultarInformacionAdjuntosPorEntidadContextoIdEntidadContexto(entidadContexto, idEntidadContexto);
                
                historialAcademicoRepository.actualizarTieneDocumentos(idEntidadContexto, (historiales != null && historiales.size() > 1));
            }
            
            ArchivoUtil.eliminarArchivoSoloConNombre(
                    generarRuta(informacionAdjuntoEmpleado
                            .getExpedienteEmpleado()
                            .getIdExpedienteEmpleado()), 
                    informacionAdjuntoEmpleado
                            .getIdInformacionAdjuntoEmpleado().toString(), 
                    usarCarpetaUsuario());
            
            informacionAdjuntoEmpleadoRepository.eliminar(informacionAdjuntoEmpleado);
        } catch (IOException ex) {
            LOGGER.error("Error al leer el archivo a eliminar.", ex);
        }
    }

    private InformacionAdjuntoDTO convertirEntidadADTO(InformacionAdjuntoEmpleadoEntity entidad) {
        if (entidad == null) {
            throw new NullPointerException("No se puede convertir una entidad que es nula");
        }

        DocumentoAdjuntableDTO documentoAdjuntable = new DocumentoAdjuntableDTO(entidad.getDocumentoAdjuntable().getIdDocumentoAdjuntable(), entidad.getDocumentoAdjuntable().getDescripcion());

        String detalle = "";

        if (entidad.getEntidadContexto() == EntidadContexto.HISTORIAL_ACADEMICO) {
            LOGGER.debugv("ID de la entidad contexto: {0}", entidad.getIdEntidadContexto());

            String nombreCurso = historialAcademicoRepository.nombreEscolaridadCursadaPorIdHistorial(entidad.getIdEntidadContexto());
            EscolaridadEntity escolaridad = escolaridadRepository.escolaridadPorIdHistorialAcademico(entidad.getIdEntidadContexto());
            detalle = escolaridad.getGrupoAcademico() + "-" + nombreCurso;
        } else if (entidad.getEntidadContexto() == EntidadContexto.DEPENDIENTE_ECONOMICO) {
            LOGGER.debugv("ID de la entidad contexto: {0}", entidad.getIdEntidadContexto());

            detalle = dependienteEconomicoRepository.obtenerNombreDependientePorId(entidad.getIdEntidadContexto());          
        }
        
        InformacionAdjuntoDTO dto = new InformacionAdjuntoDTO(
                entidad.getIdInformacionAdjuntoEmpleado(),
                entidad.getEntidadContexto(),
                entidad.getIdEntidadContexto(),
                entidad.getNombreAdjunto(),
                entidad.getExtension(),
                documentoAdjuntable,
                (entidad.getExpedienteEmpleado() == null) ? null : entidad.getExpedienteEmpleado().getIdExpedienteEmpleado(),
                (entidad.getExpedienteEmpleado() == null) ? null : entidad.getExpedienteEmpleado().getIdEmpleado(),
                null,
                detalle);

        return dto;
    }

    private List<InformacionAdjuntoDTO> convertirEntidadesADTOs(
            List<InformacionAdjuntoEmpleadoEntity> listaEntidades) {
        List<InformacionAdjuntoDTO> listaDTOs = new ArrayList<>();

        for (InformacionAdjuntoEmpleadoEntity entidad : listaEntidades) {
            InformacionAdjuntoDTO dto = convertirEntidadADTO(entidad);
            listaDTOs.add(dto);
        }

        return listaDTOs;
    }

    private InformacionAdjuntoEmpleadoEntity convertirDTOAEntidad(InformacionAdjuntoDTO dto) {
        if (dto == null) {
            throw new NullPointerException("No se puede convertir una DTO que es nulo");
        }

        DocumentoAdjuntableEntity documentoAdjuntable = documentoAdjuntableRepository.obtenerPorId(dto.getDocumentoAdjuntable().getIdDocumentoAdjuntable());
        ExpedienteEmpleadoEntity expedienteEmpleado = expedienteEmpleadoRepository.obtenerPorId(dto.getIdExpediente());

        InformacionAdjuntoEmpleadoEntity entidad = new InformacionAdjuntoEmpleadoEntity();

        entidad.setIdInformacionAdjuntoEmpleado(null);
        entidad.setEntidadContexto(dto.getEntidadContexto());
        entidad.setIdEntidadContexto(dto.getIdEntidadContexto());
        entidad.setNombreAdjunto(dto.getNombreAdjunto());
        entidad.setExtension(dto.getExtension());
        entidad.setDocumentoAdjuntable(documentoAdjuntable);
        entidad.setExpedienteEmpleado(expedienteEmpleado);

        return entidad;
    }
    
    private String generarRuta(Integer idExpediente) {
        return generarRuta(idExpediente, true);
    }

    private String generarRuta(Integer idExpediente, boolean empleado) {
        
        StringBuilder path = new StringBuilder(configuracionAplicacionEJB.getConfiguracion("adjuntos.basepath"));
            path.append(empleado ? "/empleados/" : "/aspirantes/");
            path.append(idExpediente);

        return path.toString();
    }
    
    private boolean usarCarpetaUsuario() {
        return Boolean.parseBoolean(configuracionAplicacionEJB
                        .getConfiguracion("adjuntos.usar-home"));
    }
}