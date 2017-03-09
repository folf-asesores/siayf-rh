/*
 * DocumentoAdjuntableDTO.java
 * Creado el May 12, 2016 1:03:42 PM
 * 
 */
package mx.gob.saludtlax.rh.expediente;

import java.io.Serializable;

/**
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 *
 */
public class DocumentoAdjuntableDTO implements Serializable {

    /**  */
    private static final long serialVersionUID = 1298793496986963950L;

    private Integer idDocumentoAdjuntable;
    private String descripcion;
    private boolean confidencial;
    private boolean original;
    private boolean requerido;
    private boolean unico;

    public DocumentoAdjuntableDTO() {
        this(0);
    }

    public DocumentoAdjuntableDTO(Integer idDocumentoAdjuntable) {
        this(idDocumentoAdjuntable, "");
    }
    
    public DocumentoAdjuntableDTO(Integer idDocumentoAdjuntable, String nombre) {
        this(idDocumentoAdjuntable, nombre, false, false, false, false);
    }

    public DocumentoAdjuntableDTO(Integer idDocumentoAdjuntable, String descripcion, boolean confidencial, boolean original, boolean requerido, boolean unico) {
        this.idDocumentoAdjuntable = idDocumentoAdjuntable;
        this.descripcion = descripcion;
        this.confidencial = confidencial;
        this.original = original;
        this.requerido = requerido;
        this.unico = unico;
    }

    /**
     * @return the idDocumentoAdjuntable
     */
    public Integer getIdDocumentoAdjuntable() {
        return idDocumentoAdjuntable;
    }

    /**
     * @param idDocumentoAdjuntable the idDocumentoAdjuntable to set
     */
    public void setIdDocumentoAdjuntable(Integer idDocumentoAdjuntable) {
        this.idDocumentoAdjuntable = idDocumentoAdjuntable;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isConfidencial() {
        return confidencial;
    }

    public void setConfidencial(boolean confidencial) {
        this.confidencial = confidencial;
    }

    public boolean isOriginal() {
        return original;
    }

    public void setOriginal(boolean original) {
        this.original = original;
    }

    public boolean isRequerido() {
        return requerido;
    }

    public void setRequerido(boolean requerido) {
        this.requerido = requerido;
    }

    public boolean isUnico() {
        return unico;
    }

    public void setUnico(boolean unico) {
        this.unico = unico;
    }
}
