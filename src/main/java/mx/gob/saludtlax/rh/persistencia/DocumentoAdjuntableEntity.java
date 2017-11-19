/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Entity
@Table(name = "documentos_adjuntables")
public class DocumentoAdjuntableEntity implements Serializable {

    private static final long serialVersionUID = 6589084464076232301L;

    @Id
    @Column(name = "id_documento_adjuntable", nullable = false)
    private Integer idDocumentoAdjuntable;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Basic(optional = false)
    @Column(name = "confidencial")
    private boolean confidencial;

    @Basic(optional = false)
    @Column(name = "original")
    private boolean original;

    @Basic(optional = false)
    @Column(name = "requerido")
    private boolean requerido;

    @Basic(optional = false)
    @Column(name = "unico")
    private boolean unico;

    @Basic(optional = false)
    @Column(name = "contexto_documento")
    private Integer contextoDocumento;

    /**
     *
     */
    public DocumentoAdjuntableEntity() {
    }

    /**
     *
     * @return
     */
    public Integer getIdDocumentoAdjuntable() {
        return idDocumentoAdjuntable;
    }

    /**
     *
     * @param idDocumentoAdjuntable
     */
    public void setIdDocumentoAdjuntable(Integer idDocumentoAdjuntable) {
        this.idDocumentoAdjuntable = idDocumentoAdjuntable;
    }

    /**
     *
     * @return
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     *
     * @param descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     *
     * @return
     */
    public boolean isConfidencial() {
        return confidencial;
    }

    /**
     *
     * @param confidencial
     */
    public void setConfidencial(boolean confidencial) {
        this.confidencial = confidencial;
    }

    /**
     *
     * @return
     */
    public boolean isOriginal() {
        return original;
    }

    /**
     *
     * @param original
     */
    public void setOriginal(boolean original) {
        this.original = original;
    }

    /**
     *
     * @return
     */
    public boolean isRequerido() {
        return requerido;
    }

    /**
     *
     * @param requerido
     */
    public void setRequerido(boolean requerido) {
        this.requerido = requerido;
    }

    /**
     *
     * @return
     */
    public boolean isUnico() {
        return unico;
    }

    /**
     *
     * @param unico
     */
    public void setUnico(boolean unico) {
        this.unico = unico;
    }

    /**
     *
     * @return
     */
    public Integer getContextoDocumento() {
        return contextoDocumento;
    }

    /**
     *
     * @param contextoDocumento
     */
    public void setContextoDocumento(Integer contextoDocumento) {
        this.contextoDocumento = contextoDocumento;
    }
}
