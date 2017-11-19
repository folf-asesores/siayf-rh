/*
 *
 */

package mx.gob.saludtlax.rh.puestosautorizados.programas;

import java.math.BigDecimal;

/**
 * @author Leila Schiaffini Ehuan
 * @since 2016-10-21
 *
 */
public class InfoDetallePrograma {

    private String estatus;
    private String clave;
    private String descripcion;
    private int numeroPersonas;
    private int mesesContratacion;
    private BigDecimal precioUnitario;
    private BigDecimal totalGlobal;
    private String tipoDetalle;

    public String getTipoDetalle() {
        return tipoDetalle;
    }

    public void setTipoDetalle(String tipoDetalle) {
        this.tipoDetalle = tipoDetalle;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getNumeroPersonas() {
        return numeroPersonas;
    }

    public void setNumeroPersonas(int numeroPersonas) {
        this.numeroPersonas = numeroPersonas;
    }

    public int getMesesContratacion() {
        return mesesContratacion;
    }

    public void setMesesContratacion(int mesesContratacion) {
        this.mesesContratacion = mesesContratacion;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getTotalGlobal() {
        return totalGlobal;
    }

    public void setTotalGlobal(BigDecimal totalGlobal) {
        this.totalGlobal = totalGlobal;
    }

}
