/*
 *
 */

package mx.gob.saludtlax.rh.empleados.suplencia.relacionpersonal;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 *
 */
public class RelacionPersonalSuplenteDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -835357618620996776L;

    private String numeroFolio;

    private String rfc;

    private String nombreEmpleado;

    private String funcion;

    private String areaSuple;

    private String periodoSuplencia;

    private BigDecimal importe;

    private String observaciones;

    private String centroResponsabilidad;

    @Override
    public String toString() {
        return "RelacionPersonalSuplenteDTO [numeroFolio=" + numeroFolio
                + ", rfc=" + rfc + ", nombreEmpleado=" + nombreEmpleado
                + ", funcion=" + funcion + ", areaSuple=" + areaSuple
                + ", periodoSuplencia=" + periodoSuplencia + ", importe="
                + importe + ", observaciones=" + observaciones
                + ", centroResponsabilidad=" + centroResponsabilidad + "]";
    }

    public String getNumeroFolio() {
        return numeroFolio;
    }

    public void setNumeroFolio(String numeroFolio) {
        this.numeroFolio = numeroFolio;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public String getAreaSuple() {
        return areaSuple;
    }

    public void setAreaSuple(String areaSuple) {
        this.areaSuple = areaSuple;
    }

    public String getPeriodoSuplencia() {
        return periodoSuplencia;
    }

    public void setPeriodoSuplencia(String periodoSuplencia) {
        this.periodoSuplencia = periodoSuplencia;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * @return the centroResponsabilidad
     */
    public String getCentroResponsabilidad() {
        return centroResponsabilidad;
    }

    /**
     * @param centroResponsabilidad
     *            the centroResponsabilidad to set
     */
    public void setCentroResponsabilidad(String centroResponsabilidad) {
        this.centroResponsabilidad = centroResponsabilidad;
    }

}
