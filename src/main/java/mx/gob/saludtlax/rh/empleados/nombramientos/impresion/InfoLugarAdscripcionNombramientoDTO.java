/*
 *
 */

package mx.gob.saludtlax.rh.empleados.nombramientos.impresion;

import java.io.Serializable;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 * 
 * @version 1.0
 * @since 15:15:17 14/09/2016
 */
public class InfoLugarAdscripcionNombramientoDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1941734232972793372L;

    private String adscripcion;
    private String areaAdscripcion;
    private String lugarAdscripcion;

    /**
     * @return the adscripcion
     */
    public String getAdscripcion() {
        return adscripcion;
    }

    /**
     * @param adscripcion
     *            the adscripcion to set
     */
    public void setAdscripcion(String adscripcion) {
        this.adscripcion = adscripcion;
    }

    /**
     * @return the lugarAdscripcion
     */
    public String getLugarAdscripcion() {
        return lugarAdscripcion;
    }

    /**
     * @param lugarAdscripcion
     *            the lugarAdscripcion to set
     */
    public void setLugarAdscripcion(String lugarAdscripcion) {
        this.lugarAdscripcion = lugarAdscripcion;
    }

    /**
     * @return the areaAdscripcion
     */
    public String getAreaAdscripcion() {
        return areaAdscripcion;
    }

    /**
     * @param areaAdscripcion
     *            the areaAdscripcion to set
     */
    public void setAreaAdscripcion(String areaAdscripcion) {
        this.areaAdscripcion = areaAdscripcion;
    }

}
