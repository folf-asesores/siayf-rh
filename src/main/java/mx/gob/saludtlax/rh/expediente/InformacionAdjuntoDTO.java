/*
 * InformacionAdjuntoDTO.java
 * Creado el May 12, 2016 12:57:19 PM
 * 
 */
package mx.gob.saludtlax.rh.expediente;

import java.io.Serializable;

import mx.gob.saludtlax.rh.expediente.aspirante.AdjuntoAspirante;
import mx.gob.saludtlax.rh.expediente.empleado.AdjuntoEmpleado;
import mx.gob.saludtlax.rh.util.TipoArchivo;

/**
 * Esta clase tiene por objetivo solo describir un adjunto; para poder obtener 
 * el adjunto se debe emplear el EJB corespondiente, en caso de un empleado 
 * {@link AdjuntoEmpleado#obtenerAdjuntoPorIdAdjunto(int) } y en caso de un 
 * aspirante {@link AdjuntoAspirante#obtenerAdjuntoPorIdAdjunto(int)}.
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class InformacionAdjuntoDTO implements Serializable {

    /** Para permitir que el objecto sea serializable. */
    private static final long serialVersionUID = 5704852507441211938L;

    private Integer idAdjunto;
    private EntidadContexto entidadContexto;
    private Integer idEntidadContexto;
    private String nombreAdjunto;
    private TipoArchivo extension;
    private DocumentoAdjuntableDTO documentoAdjuntable;
    private Integer idEmpleado;
    private Integer idAspirante;
    private String detalle;
    private Integer idExpediente;

    /**
     * Permite crear una nueva instancia de la clase.
     */
    public InformacionAdjuntoDTO() {

    }

    /**
     * Permite crear un instancia inicializando los campos esenciales del DTO.
     * 
     * @param idAdjunto el ID del adjunto.
     * @param entidadContexto el contexto del adjunto.
     * @param idEntidadContexto el id del contexto.
     * @param nombreAdjunto el nombre del archivo adjunto sin extensión.
     * @param extension la extensión del adjunto.
     * @param documentoAdjuntable el tipo de documento adjuntable que es.
     * @param idExpediente el ID del expedinte.
     * @param idEmpleado el ID del empleado si se trata de un adjunto del empleado.
     * @param idAspirante el ID del aspirante si se trata de un adjunto del aspirante.
     * @param detalle
     */
    public InformacionAdjuntoDTO(Integer idAdjunto, 
            EntidadContexto entidadContexto, Integer idEntidadContexto,
            String nombreAdjunto, TipoArchivo extension, 
            DocumentoAdjuntableDTO documentoAdjuntable, Integer idExpediente, 
            Integer idEmpleado,
            Integer idAspirante, String detalle) {

        this.idAdjunto = idAdjunto;
        this.entidadContexto = entidadContexto;
        this.idEntidadContexto = idEntidadContexto;
        this.nombreAdjunto = nombreAdjunto;
        this.extension = extension;
        this.documentoAdjuntable = documentoAdjuntable;
        this.idExpediente = idExpediente;
        this.idEmpleado = idEmpleado;
        this.idAspirante = idAspirante;
        this.detalle = detalle;
    }

    /**
     *
     * @return
     */
    public Integer getIdExpediente() {
        return idExpediente;
    }

    /**
     *
     * @param idExpediente
     */
    public void setIdExpediente(Integer idExpediente) {
        this.idExpediente = idExpediente;
    }

    /**
     * @return the idAdjunto
     */
    public Integer getIdAdjunto() {
        return idAdjunto;
    }

    /**
     * @param idAdjunto the idAdjunto to set
     */
    public void setIdAdjunto(Integer idAdjunto) {
        this.idAdjunto = idAdjunto;
    }

    /**
     * @return the entidadContexto
     */
    public EntidadContexto getEntidadContexto() {
        return entidadContexto;
    }

    /**
     * @param entidadContexto the entidadContexto to set
     */
    public void setEntidadContexto(EntidadContexto entidadContexto) {
        this.entidadContexto = entidadContexto;
    }

    /**
     * @return the idEntidadContexto
     */
    public Integer getIdEntidadContexto() {
        return idEntidadContexto;
    }

    /**
     * @param idEntidadContexto the idEntidadContexto to set
     */
    public void setIdEntidadContexto(Integer idEntidadContexto) {
        this.idEntidadContexto = idEntidadContexto;
    }

    /**
     * @return the nombreAdjunto
     */
    public String getNombreAdjunto() {
        return nombreAdjunto;
    }

    /**
     * @param nombreAdjunto the nombreAdjunto to set
     */
    public void setNombreAdjunto(String nombreAdjunto) {
        this.nombreAdjunto = nombreAdjunto;
    }

    /**
     * @return the extension
     */
    public TipoArchivo getExtension() {
        return extension;
    }

    /**
     * @param extension the extension to set
     */
    public void setExtension(TipoArchivo extension) {
        this.extension = extension;
    }

    /**
     * @return the documentoAdjuntable
     */
    public DocumentoAdjuntableDTO getDocumentoAdjuntable() {
        return documentoAdjuntable;
    }

    /**
     * @param documentoAdjuntable the documentoAdjuntable to set
     */
    public void setDocumentoAdjuntable(DocumentoAdjuntableDTO documentoAdjuntable) {
        this.documentoAdjuntable = documentoAdjuntable;
    }

    /**
     * @return the idEmpleado
     */
    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    /**
     * @param idEmpleado the idEmpleado to set
     */
    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    /**
     * @return the idAspirante
     */
    public Integer getIdAspirante() {
        return idAspirante;
    }

    /**
     * @param idAspirante the idAspirante to set
     */
    public void setIdAspirante(Integer idAspirante) {
        this.idAspirante = idAspirante;
    }

    /**
     * @return the detalle
     */
    public String getDetalle() {
        return detalle;
    }

    /**
     * @param detalle the detalle to set
     */
    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
}
