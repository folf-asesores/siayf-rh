/*
 *
 */

package mx.gob.saludtlax.rh.bolsatrabajo.aspirantes;

import java.io.Serializable;

/**
 * @author eduardo
 *
 */
public class DocumentoComprobatorioDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2458378581846741209L;

    private Integer idDocumentoComprobatorio;

    private String estatus;

    /**
     * @return the estatus
     */
    public String getEstatus() {
        return estatus;
    }

    /**
     * @param estatus
     *            the estatus to set
     */
    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Integer getIdDocumentoComprobatorio() {
        return idDocumentoComprobatorio;
    }

    public void setIdDocumentoComprobatorio(Integer idDocumentoComprobatorio) {
        this.idDocumentoComprobatorio = idDocumentoComprobatorio;
    }

}
