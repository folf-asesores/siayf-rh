/*
 *
 */

package mx.gob.saludtlax.rh.acumulados;

import java.io.Serializable;

import mx.gob.saludtlax.rh.siif.PaqueteEntradaFederalDTO;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 * 
 * @version 1.0
 * @since 18:25:33 24/09/2016
 */
public class ImportarNominaView implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -8514677325219897956L;

    private PaqueteEntradaFederalDTO paqueteEntrada = new PaqueteEntradaFederalDTO();

    private boolean mostrarDatTra = false;
    private boolean mostrarExcel = false;

    public boolean isMostrarDatTra() {
        return mostrarDatTra;
    }

    public void setMostrarDatTra(boolean mostrarDatTra) {
        this.mostrarDatTra = mostrarDatTra;
    }

    public boolean isMostrarExcel() {
        return mostrarExcel;
    }

    public void setMostrarExcel(boolean mostrarExcel) {
        this.mostrarExcel = mostrarExcel;
    }

    /**
     * @return the paqueteEntrada
     */
    public PaqueteEntradaFederalDTO getPaqueteEntrada() {
        return paqueteEntrada;
    }

    /**
     * @param paqueteEntrada
     *            the paqueteEntrada to set
     */
    public void setPaqueteEntrada(PaqueteEntradaFederalDTO paqueteEntrada) {
        this.paqueteEntrada = paqueteEntrada;
    }

}
