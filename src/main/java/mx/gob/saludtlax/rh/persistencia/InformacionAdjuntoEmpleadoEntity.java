/*
 * InformacionAdjuntoEmpleadoEntity.java
 * Creado el May 12, 2016 10:13:45 AM
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import mx.gob.saludtlax.rh.expediente.EntidadContexto;
import mx.gob.saludtlax.rh.util.TipoArchivo;

/**
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 *
 */
@Entity
@Table(name = "informacion_adjuntos_empleados")
public class InformacionAdjuntoEmpleadoEntity implements Serializable {

    private static final long serialVersionUID = -1951502154671090648L;

    @Id
    @Column(name = "id_informacion_adjunto_empleado")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idInformacionAdjuntoEmpleado;

    @Basic(optional = false)
    @Column(name = "nombre_adjunto")
    private String nombreAdjunto;

    @Basic(optional = false)
    @Enumerated(EnumType.STRING)
    @Column(name = "extension")
    private TipoArchivo extension;

    @Basic(optional = false)
    @Enumerated(EnumType.STRING)
    @Column(name = "entidad_contexto")
    private EntidadContexto entidadContexto;

    @Basic(optional = false)
    @Column(name = "id_entidad_contexto")
    private Integer idEntidadContexto;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_documento_adjuntable")
    private DocumentoAdjuntableEntity documentoAdjuntable;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_expediente")
    private ExpedienteEmpleadoEntity expedienteEmpleado;

    public String getNombreAdjunto() {
        return nombreAdjunto;
    }

    public ExpedienteEmpleadoEntity getExpedienteEmpleado() {
        return expedienteEmpleado;
    }

    public void setExpedienteEmpleado(
            ExpedienteEmpleadoEntity expedienteEmpleado) {
        this.expedienteEmpleado = expedienteEmpleado;
    }

    /**
     * @return the idInformacionAdjuntoEmpleado
     */
    public Integer getIdInformacionAdjuntoEmpleado() {
        return idInformacionAdjuntoEmpleado;
    }

    /**
     * @param idInformacionAdjuntoEmpleado
     *            the idInformacionAdjuntoEmpleado to
     *            set
     */
    public void setIdInformacionAdjuntoEmpleado(
            Integer idInformacionAdjuntoEmpleado) {
        this.idInformacionAdjuntoEmpleado = idInformacionAdjuntoEmpleado;
    }

    /**
     * @return the entidadContexto
     */
    public EntidadContexto getEntidadContexto() {
        return entidadContexto;
    }

    /**
     * @param entidadContexto
     *            the entidadContexto to set
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
     * @param idEntidadContexto
     *            the idEntidadContexto to set
     */
    public void setIdEntidadContexto(Integer idEntidadContexto) {
        this.idEntidadContexto = idEntidadContexto;
    }

    /**
     * @param nombreAdjunto
     *            the nombreAdjunto to set
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
     * @param extension
     *            the extension to set
     */
    public void setExtension(TipoArchivo extension) {
        this.extension = extension;
    }

    /**
     * @return the documentoAdjuntable
     */
    public DocumentoAdjuntableEntity getDocumentoAdjuntable() {
        return documentoAdjuntable;
    }

    /**
     * @param documentoAdjuntable
     *            the documentoAdjuntable to set
     */
    public void setDocumentoAdjuntable(
            DocumentoAdjuntableEntity documentoAdjuntable) {
        this.documentoAdjuntable = documentoAdjuntable;
    }
}
