/*
 * FirmaPOJO.java
 * Creado el 08/sep/2017 4:32:12 PM
 * 
 */

package mx.gob.saludtlax.rh.nomina.reportes.firma;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class FirmaPOJO {

    private Integer idProductoNomina;
    private Integer idPrograma;
    private String programa;
    private String clave;
    private String descripcion;
    private Date fechaPago;
    private String filiacion;
    private String nombre;
    private Date inicioPeriodo;
    private Date finPeriodo;
    private String numeroCheque;
    private BigDecimal neto;
    private Integer consecutivo;
    private String jefe1Nombre;
    private String jefe1Cargo;
    private String jefe2Nombre;
    private String jefe2Cargo;
    private String jefe3Nombre;
    private String jefe3Cargo;

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

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getFiliacion() {
        return filiacion;
    }

    public void setFiliacion(String filiacion) {
        this.filiacion = filiacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getNumeroCheque() {
        return numeroCheque;
    }

    public void setNumeroCheque(String numeroCheque) {
        this.numeroCheque = numeroCheque;
    }

    public BigDecimal getNeto() {
        return neto;
    }

    public void setNeto(BigDecimal neto) {
        this.neto = neto;
    }

    public Integer getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(Integer consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getJefe1Nombre() {
        return jefe1Nombre;
    }

    public void setJefe1Nombre(String jefe1Nombre) {
        this.jefe1Nombre = jefe1Nombre;
    }

    public String getJefe1Cargo() {
        return jefe1Cargo;
    }

    public void setJefe1Cargo(String jefe1Cargo) {
        this.jefe1Cargo = jefe1Cargo;
    }

    public String getJefe2Nombre() {
        return jefe2Nombre;
    }

    public void setJefe2Nombre(String jefe2Nombre) {
        this.jefe2Nombre = jefe2Nombre;
    }

    public String getJefe2Cargo() {
        return jefe2Cargo;
    }

    public void setJefe2Cargo(String jefe2Cargo) {
        this.jefe2Cargo = jefe2Cargo;
    }

    public String getJefe3Nombre() {
        return jefe3Nombre;
    }

    public void setJefe3Nombre(String jefe3Nombre) {
        this.jefe3Nombre = jefe3Nombre;
    }

    public String getJefe3Cargo() {
        return jefe3Cargo;
    }

    public void setJefe3Cargo(String jefe3Cargo) {
        this.jefe3Cargo = jefe3Cargo;
    }

}
