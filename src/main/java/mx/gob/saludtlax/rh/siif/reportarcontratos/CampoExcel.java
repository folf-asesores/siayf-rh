/*
 * CampoVacio.java
 * Creado: Apr 24, 2015, 10:18:43 AM
 */

package mx.gob.saludtlax.rh.siif.reportarcontratos;

import java.io.Serializable;

/**
 * @author Freddy Barrera
 *
 */
public class CampoExcel implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 8293412931664537176L;

    public static int VACIO = 1, ERROR = 2;
    private int status;

    /**
     * @param status
     */
    public CampoExcel() {
        status = VACIO;
    }

    /**
     * @param status
     */
    public CampoExcel(int status) {
        this.status = status;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

}
