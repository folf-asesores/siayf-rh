/*
 *
 * InformacionAdjuntoAspiranteEntity.java
 * Creado el Jun 11, 2016 3:11:25 PM
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
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Entity
@Table(name = "informacion_adjuntos_aspirantes")
public class InformacionAdjuntoAspiranteEntity implements Serializable {

    private static final long serialVersionUID = -4301921087395030578L;

    @Id
    @Column(name = "id_informacion_adjunto_aspirante")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idInformacionAdjuntoAspirante;

    @Basic(optional = false)
    @Column(name = "nombre_adjunto")
    private String nombreAdjunto;

    @Basic(optional = false)
    @Enumerated(EnumType.STRING)
    @Column(name = "extension")
    private TipoArchivo extension;

    @Enumerated(EnumType.STRING)
    @Column(name = "entidad_contexto")
    private EntidadContexto entidadContexto;

    @Basic(optional = false)
    @Column(name = "id_entidad_contexto")
    private int idEntidadContexto;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_documento_adjuntable")
    private DocumentoAdjuntableEntity documentoAdjuntable;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_expediente", referencedColumnName = "id_expediente_aspirante")
    private ExpedienteAspiranteEntity expedienteAspirante;

    /**
     *
     */
    public InformacionAdjuntoAspiranteEntity() {
    }

    /**
     *
     * @return
     */
    public Integer getIdInformacionAdjuntoAspirante() {
        return idInformacionAdjuntoAspirante;
    }

    /**
     *
     * @param idInformacionAdjuntoAspirante
     */
    public void setIdInformacionAdjuntoAspirante(Integer idInformacionAdjuntoAspirante) {
        this.idInformacionAdjuntoAspirante = idInformacionAdjuntoAspirante;
    }

    /**
     *
     * @return
     */
    public String getNombreAdjunto() {
        return nombreAdjunto;
    }

    /**
     *
     * @param nombreAdjunto
     */
    public void setNombreAdjunto(String nombreAdjunto) {
        this.nombreAdjunto = nombreAdjunto;
    }

    /**
     *
     * @return
     */
    public TipoArchivo getExtension() {
        return extension;
    }

    /**
     *
     * @param extension
     */
    public void setExtension(TipoArchivo extension) {
        this.extension = extension;
    }

    /**
     *
     * @return
     */
    public EntidadContexto getEntidadContexto() {
        return entidadContexto;
    }

    /**
     *
     * @param entidadContexto
     */
    public void setEntidadContexto(EntidadContexto entidadContexto) {
        this.entidadContexto = entidadContexto;
    }

    /**
     *
     * @return
     */
    public int getIdEntidadContexto() {
        return idEntidadContexto;
    }

    /**
     *
     * @param idEntidadContexto
     */
    public void setIdEntidadContexto(int idEntidadContexto) {
        this.idEntidadContexto = idEntidadContexto;
    }

    /**
     *
     * @return
     */
    public DocumentoAdjuntableEntity getDocumentoAdjuntable() {
        return documentoAdjuntable;
    }

    /**
     *
     * @param documentoAdjuntable
     */
    public void setDocumentoAdjuntable(DocumentoAdjuntableEntity documentoAdjuntable) {
        this.documentoAdjuntable = documentoAdjuntable;
    }

    /**
     *
     * @return
     */
    public ExpedienteAspiranteEntity getExpedienteAspirante() {
        return expedienteAspirante;
    }

    /**
     *
     * @param expedienteAspirante
     */
    public void setExpedienteAspirante(ExpedienteAspiranteEntity expedienteAspirante) {
        this.expedienteAspirante = expedienteAspirante;
    }
}
