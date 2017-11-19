/*
 *
 */

package mx.gob.saludtlax.rh.reportes;

import java.io.Serializable;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 *
 */
public class ReporteParamDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4539140416282250689L;

    private String[] parametros;

    private byte[] bytes;

    private String tituloReporte;
    private String subtituloReporte;
    private String nombreReporte;

    /**
     * @return the parametros
     */
    public String[] getParametros() {
        return parametros;
    }

    /**
     * @param parametros
     *            the parametros to set
     */
    public void setParametros(String[] parametros) {
        this.parametros = parametros;
    }

    /**
     * @return the tituloReporte
     */
    public String getTituloReporte() {
        return tituloReporte;
    }

    /**
     * @param tituloReporte
     *            the tituloReporte to set
     */
    public void setTituloReporte(String tituloReporte) {
        this.tituloReporte = tituloReporte;
    }

    /**
     * @return the subtituloReporte
     */
    public String getSubtituloReporte() {
        return subtituloReporte;
    }

    /**
     * @param subtituloReporte
     *            the subtituloReporte to set
     */
    public void setSubtituloReporte(String subtituloReporte) {
        this.subtituloReporte = subtituloReporte;
    }

    /**
     * @return the nombreReporte
     */
    public String getNombreReporte() {
        return nombreReporte;
    }

    /**
     * @param nombreReporte
     *            the nombreReporte to set
     */
    public void setNombreReporte(String nombreReporte) {
        this.nombreReporte = nombreReporte;
    }

    /**
     * @return the bytes
     */
    public byte[] getBytes() {
        return bytes;
    }

    /**
     * @param bytes
     *            the bytes to set
     */
    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

}
