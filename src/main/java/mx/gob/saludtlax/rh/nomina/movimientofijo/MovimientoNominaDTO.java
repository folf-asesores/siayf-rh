/*
 * Copyright ® 2016
 */

package mx.gob.saludtlax.rh.nomina.movimientofijo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Eduardo Mex

 * @version 1.0
 * @since 25/05/2016 13:45:06
 */
public class MovimientoNominaDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -232336985105177960L;

    private Integer idMovimientoFijo;

    private Integer idTerceroInstitucional;

    private String terceroInstitucional;

    private Date fechaDocumento;

    private String folio;

    private Integer idEmpleado;

    private String rfc;

    private Integer quincenaOperacion;

    private Integer anyoOperacion;

    private BigDecimal importeQuincenas;

    private BigDecimal importeDescontado = BigDecimal.ZERO;

    private Integer quincenaInicial;

    private Integer quincenaFinal;

    private Integer anioFinal;

    private Integer anioInicial;

    private BigDecimal importeMensual;

    private BigDecimal importeQuincenal;

    private Date fechaRegistro;

    private Date fechaModificacion;

    private Integer idTipoMovimiento;

    private Integer dias;

    //bandera para saber que tipo de accion se realizara con el dto
    private String tipoMovimiento;

    private String statusRegistro;

    private String clave;

    //valida la aplicacion del movimiento
    private Boolean estatus;

    public Integer getIdMovimientoFijo() {
        return idMovimientoFijo;
    }

    public void setIdMovimientoFijo(Integer idMovimientoFijo) {
        this.idMovimientoFijo = idMovimientoFijo;
    }

    public Integer getIdTerceroInstitucional() {
        return idTerceroInstitucional;
    }

    public void setIdTerceroInstitucional(Integer idTerceroInstitucional) {
        this.idTerceroInstitucional = idTerceroInstitucional;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public Integer getQuincenaOperacion() {
        return quincenaOperacion;
    }

    public void setQuincenaOperacion(Integer quincenaOperacion) {
        this.quincenaOperacion = quincenaOperacion;
    }

    public Integer getAnyoOperacion() {
        return anyoOperacion;
    }

    public void setAnyoOperacion(Integer anyoOperacion) {
        this.anyoOperacion = anyoOperacion;
    }

    public BigDecimal getImporteQuincenas() {
        return importeQuincenas;
    }

    public void setImporteQuincenas(BigDecimal importeQuincenas) {
        this.importeQuincenas = importeQuincenas;
    }

    public BigDecimal getImporteDescontado() {
        return importeDescontado;
    }

    public void setImporteDescontado(BigDecimal importeDescontado) {
        this.importeDescontado = importeDescontado;
    }

    public Integer getQuincenaInicial() {
        return quincenaInicial;
    }

    public void setQuincenaInicial(Integer quincenaInicial) {
        this.quincenaInicial = quincenaInicial;
    }

    public Integer getQuincenaFinal() {
        return quincenaFinal;
    }

    public void setQuincenaFinal(Integer quincenaFinal) {
        this.quincenaFinal = quincenaFinal;
    }

    public BigDecimal getImporteMensual() {
        return importeMensual;
    }

    public void setImporteMensual(BigDecimal importeMensual) {
        this.importeMensual = importeMensual;
    }

    public BigDecimal getImporteQuincenal() {
        return importeQuincenal;
    }

    public void setImporteQuincenal(BigDecimal importeQuincenal) {
        this.importeQuincenal = importeQuincenal;
    }

    public Integer getAnioFinal() {
        return anioFinal;
    }

    public void setAnioFinal(Integer anioFinal) {
        this.anioFinal = anioFinal;
    }

    public String getTerceroInstitucional() {
        return terceroInstitucional;
    }

    public void setTerceroInstitucional(String terceroInstitucional) {
        this.terceroInstitucional = terceroInstitucional;
    }

    public Integer getAnioInicial() {
        return anioInicial;
    }

    public void setAnioInicial(Integer anioInicial) {
        this.anioInicial = anioInicial;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public Date getFechaDocumento() {
        return fechaDocumento;
    }

    public void setFechaDocumento(Date fechaDocumento) {
        this.fechaDocumento = fechaDocumento;
    }

    public Integer getIdTipoMovimiento() {
        return idTipoMovimiento;
    }

    public void setIdTipoMovimiento(Integer idTipoMovimiento) {
        this.idTipoMovimiento = idTipoMovimiento;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public String getStatusRegistro() {
        return statusRegistro;
    }

    public void setStatusRegistro(String statusRegistro) {
        this.statusRegistro = statusRegistro;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Boolean getEstatus() {
        return estatus;
    }

    public void setEstatus(Boolean estatus) {
        this.estatus = estatus;
    }

    @Override
    public String toString() {
        return "MovimientoNominaDTO [idMovimientoFijo=" + idMovimientoFijo + ", idTerceroInstitucional=" + idTerceroInstitucional + ", terceroInstitucional="
                + terceroInstitucional + ", fechaDocumento=" + fechaDocumento + ", folio=" + folio + ", idEmpleado=" + idEmpleado + ", rfc=" + rfc
                + ", quincenaOperacion=" + quincenaOperacion + ", anyoOperacion=" + anyoOperacion + ", importeQuincenas=" + importeQuincenas
                + ", importeDescontado=" + importeDescontado + ", quincenaInicial=" + quincenaInicial + ", quincenaFinal=" + quincenaFinal + ", anioFinal="
                + anioFinal + ", anioInicial=" + anioInicial + ", importeMensual=" + importeMensual + ", importeQuincenal=" + importeQuincenal
                + ", fechaRegistro=" + fechaRegistro + ", fechaModificacion=" + fechaModificacion + ", idTipoMovimiento=" + idTipoMovimiento + ", dias=" + dias
                + ", tipoMovimiento=" + tipoMovimiento + ", statusRegistro=" + statusRegistro + ", clave=" + clave + ", estatus=" + estatus + "]";
    }

}
