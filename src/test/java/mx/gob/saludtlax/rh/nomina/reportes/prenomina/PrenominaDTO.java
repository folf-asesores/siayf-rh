/*
 * PrenominaDTO.java
 * Creado el 09/Jul/2017 4:45:23 PM
 *
 */
package mx.gob.saludtlax.rh.nomina.reportes.prenomina;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class PrenominaDTO {

    private String elaboroNombre;
    private String elaboroCargo;
    private String revisoNombre;
    private String revisoCargo;
    private String autorizoNombre;
    private String autorizoCargo;
    private Date fechaPago;
    private Integer idProductoNomina;
    private Integer idPrograma;
    private String programa;
    private Date inicioPeriodo;
    private Date finPeriodo;
    private String claveCentroResponsabilidad;
    private String descripcionCentroResponsabilidad;
    private String rfc;
    private String nombre;
    private String claveConcepto;
    private String descripcionConcepto;
    private BigDecimal importe;
    private BigDecimal total;
    private String tipo;

    public String getElaboroNombre() {
        return elaboroNombre;
    }

    public void setElaboroNombre(String elaboroNombre) {
        this.elaboroNombre = elaboroNombre;
    }

    public String getElaboroCargo() {
        return elaboroCargo;
    }

    public void setElaboroCargo(String elaboroCargo) {
        this.elaboroCargo = elaboroCargo;
    }

    public String getRevisoNombre() {
        return revisoNombre;
    }

    public void setRevisoNombre(String revisoNombre) {
        this.revisoNombre = revisoNombre;
    }

    public String getRevisoCargo() {
        return revisoCargo;
    }

    public void setRevisoCargo(String revisoCargo) {
        this.revisoCargo = revisoCargo;
    }

    public String getAutorizoNombre() {
        return autorizoNombre;
    }

    public void setAutorizoNombre(String autorizoNombre) {
        this.autorizoNombre = autorizoNombre;
    }

    public String getAutorizoCargo() {
        return autorizoCargo;
    }

    public void setAutorizoCargo(String autorizoCargo) {
        this.autorizoCargo = autorizoCargo;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Integer getIdProductoNomina() {
        return idProductoNomina;
    }

    public void setIdProductoNomina(Integer idProductoNomina) {
        this.idProductoNomina = idProductoNomina;
    }

    public Integer getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(Integer idPrograma) {
        this.idPrograma = idPrograma;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public Date getInicioPeriodo() {
        return inicioPeriodo;
    }

    public void setInicioPeriodo(Date inicioPeriodo) {
        this.inicioPeriodo = inicioPeriodo;
    }

    public Date getFinPeriodo() {
        return finPeriodo;
    }

    public void setFinPeriodo(Date finPeriodo) {
        this.finPeriodo = finPeriodo;
    }

    public String getClaveCentroResponsabilidad() {
        return claveCentroResponsabilidad;
    }

    public void setClaveCentroResponsabilidad(String claveCentroResponsabilidad) {
        this.claveCentroResponsabilidad = claveCentroResponsabilidad;
    }

    public String getDescripcionCentroResponsabilidad() {
        return descripcionCentroResponsabilidad;
    }

    public void setDescripcionCentroResponsabilidad(String descripcionCentroResponsabilidad) {
        this.descripcionCentroResponsabilidad = descripcionCentroResponsabilidad;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClaveConcepto() {
        return claveConcepto;
    }

    public void setClaveConcepto(String claveConcepto) {
        this.claveConcepto = claveConcepto;
    }

    public String getDescripcionConcepto() {
        return descripcionConcepto;
    }

    public void setDescripcionConcepto(String descripcionConcepto) {
        this.descripcionConcepto = descripcionConcepto;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "PrenominaDTO{" + "elaboroNombre=" + elaboroNombre + ", elaboroCargo=" + elaboroCargo + ", revisoNombre=" + revisoNombre + ", revisoCargo=" + revisoCargo + ", autorizoNombre=" + autorizoNombre + ", autorizoCargo=" + autorizoCargo + ", fechaPago=" + fechaPago + ", idProductoNomina=" + idProductoNomina + ", idPrograma=" + idPrograma + ", programa=" + programa + ", inicioPeriodo=" + inicioPeriodo + ", finPeriodo=" + finPeriodo + ", claveCentroResponsabilidad=" + claveCentroResponsabilidad + ", descripcionCentroResponsabilidad=" + descripcionCentroResponsabilidad + ", rfc=" + rfc + ", nombre=" + nombre + ", claveConcepto=" + claveConcepto + ", descripcionConcepto=" + descripcionConcepto + ", importe=" + importe + ", total=" + total + ", tipo=" + tipo + '}';
    }
}
