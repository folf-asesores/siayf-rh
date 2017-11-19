/*
 *
 */

package mx.gob.saludtlax.rh.vacantes.seleccion;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 * 
 * @version 1.0
 * @since 13:49:05 12/08/2016
 */
public class InfoPuestoDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6554960522098912394L;

    private Integer idInventarioVacante;

    private String tipoContratacion;

    private String nombramiento;

    private String folioVacante;

    private String codigoPuesto;

    private String puesto;

    private BigDecimal sueldo;

    private String nombreEmpleado;

    private Integer idConfiguracionPresupuesto;

    private String unidadResponsable;

    /**
     *
     */
    public InfoPuestoDTO() {
        super();
    }

    public InfoPuestoDTO(Integer idInventarioVacante, String tipoContratacion,
            String nombramiento, String folioVacante, String codigoPuesto,
            String puesto, String nombreEmpleado,
            Integer idConfiguracionPresupuesto, String unidadResponsable) {
        this.idInventarioVacante = idInventarioVacante;
        this.tipoContratacion = tipoContratacion;
        this.nombramiento = nombramiento;
        this.folioVacante = folioVacante;
        this.codigoPuesto = codigoPuesto;
        this.puesto = puesto;
        this.nombreEmpleado = nombreEmpleado;
        this.idConfiguracionPresupuesto = idConfiguracionPresupuesto;
        this.unidadResponsable = unidadResponsable;
    }

    public InfoPuestoDTO(Integer idInventarioVacante, String tipoContratacion,
            String nombramiento, String folioVacante, String codigoPuesto,
            String puesto, BigDecimal sueldo, String nombreEmpleado,
            Integer idConfiguracionPresupuesto) {

        this.idInventarioVacante = idInventarioVacante;
        this.tipoContratacion = tipoContratacion;
        this.nombramiento = nombramiento;
        this.folioVacante = folioVacante;
        this.codigoPuesto = codigoPuesto;
        this.puesto = puesto;
        this.sueldo = sueldo;
        this.nombreEmpleado = nombreEmpleado;
        this.idConfiguracionPresupuesto = idConfiguracionPresupuesto;
    }

    public InfoPuestoDTO(Integer idInventarioVacante, String tipoContratacion,
            String nombramiento, String folioVacante, String codigoPuesto,
            String puesto, BigDecimal sueldo,
            Integer idConfiguracionPresupuesto) {

        this.idInventarioVacante = idInventarioVacante;
        this.tipoContratacion = tipoContratacion;
        this.nombramiento = nombramiento;
        this.folioVacante = folioVacante;
        this.codigoPuesto = codigoPuesto;
        this.puesto = puesto;
        this.sueldo = sueldo;
        this.idConfiguracionPresupuesto = idConfiguracionPresupuesto;
    }

    public InfoPuestoDTO(Integer idInventarioVacante, String tipoContratacion,
            String nombramiento, String folioVacante, String codigoPuesto,
            String puesto, BigDecimal sueldo) {

        this.idInventarioVacante = idInventarioVacante;
        this.tipoContratacion = tipoContratacion;
        this.nombramiento = nombramiento;
        this.folioVacante = folioVacante;
        this.codigoPuesto = codigoPuesto;
        this.puesto = puesto;
        this.sueldo = sueldo;

    }

    public String getUnidadResponsable() {
        return unidadResponsable;
    }

    public void setUnidadResponsable(String unidadResponsable) {
        this.unidadResponsable = unidadResponsable;
    }

    public Integer getIdConfiguracionPresupuesto() {
        return idConfiguracionPresupuesto;
    }

    public void setIdConfiguracionPresupuesto(
            Integer idConfiguracionPresupuesto) {
        this.idConfiguracionPresupuesto = idConfiguracionPresupuesto;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    /**
     * @return the idInventarioVacante
     */
    public Integer getIdInventarioVacante() {
        return idInventarioVacante;
    }

    /**
     * @param idInventarioVacante
     *            the idInventarioVacante to set
     */
    public void setIdInventarioVacante(Integer idInventarioVacante) {
        this.idInventarioVacante = idInventarioVacante;
    }

    /**
     * @return the tipoContratacion
     */
    public String getTipoContratacion() {
        return tipoContratacion;
    }

    /**
     * @param tipoContratacion
     *            the tipoContratacion to set
     */
    public void setTipoContratacion(String tipoContratacion) {
        this.tipoContratacion = tipoContratacion;
    }

    /**
     * @return the nombramiento
     */
    public String getNombramiento() {
        return nombramiento;
    }

    /**
     * @param nombramiento
     *            the nombramiento to set
     */
    public void setNombramiento(String nombramiento) {
        this.nombramiento = nombramiento;
    }

    /**
     * @return the folioVacante
     */
    public String getFolioVacante() {
        return folioVacante;
    }

    /**
     * @param folioVacante
     *            the folioVacante to set
     */
    public void setFolioVacante(String folioVacante) {
        this.folioVacante = folioVacante;
    }

    /**
     * @return the codigoPuesto
     */
    public String getCodigoPuesto() {
        return codigoPuesto;
    }

    /**
     * @param codigoPuesto
     *            the codigoPuesto to set
     */
    public void setCodigoPuesto(String codigoPuesto) {
        this.codigoPuesto = codigoPuesto;
    }

    /**
     * @return the puesto
     */
    public String getPuesto() {
        return puesto;
    }

    /**
     * @param puesto
     *            the puesto to set
     */
    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    /**
     * @return the sueldo
     */
    public BigDecimal getSueldo() {
        return sueldo;
    }

    /**
     * @param sueldo
     *            the sueldo to set
     */
    public void setSueldo(BigDecimal sueldo) {
        this.sueldo = sueldo;
    }

}
