/*
 * Copyright ® 2016
 */

package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 * 
 * @version 1.0
 * @since 25/05/2016 13:50:30
 */
@Entity
@Table(name = "movimientos_empleado_nomina")
public class MovimientoFijoEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -764307789460118188L;

    @Id
    @Column(name = "id_movimiento_fijo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMovimientoFijo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tercero_institucional")
    private TerceroInstitucionalEntity terceroInstitucional;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empleado")
    private EmpleadoEntity empleado;

    @Column(name = "rfc")
    private String rfc;

    @Column(name = "quincena_operacion")
    private Integer quincenaOperacion;

    @Column(name = "anyo_operacion")
    private Integer anyoOperacion;

    @Column(name = "importe_descontado")
    private BigDecimal importeDescontado;

    @Column(name = "quincena_inicial")
    private Integer quincenaInicial;

    @Column(name = "quincena_final")
    private Integer quincenaFinal;

    @Column(name = "anio_final")
    private Integer anioFinal;

    @Column(name = "anio_inicial")
    private Integer anioInicial;

    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    @Column(name = "folio_documento")
    private String folioDocumento;

    @Column(name = "fecha_documento")
    private Date fechaDocumento;

    @Column(name = "id_tipo_movimiento")
    private Integer idTipoMovimiento;

    @Column(name = "dias")
    private Integer dias;

    @Column(name = "clave")
    private String clave;

    @Column(name = "estatus")
    private Boolean estatus;

    public Integer getIdMovimientoFijo() {
        return idMovimientoFijo;
    }

    public void setIdMovimientoFijo(Integer idMovimientoFijo) {
        this.idMovimientoFijo = idMovimientoFijo;
    }

    public TerceroInstitucionalEntity getTerceroInstitucional() {
        return terceroInstitucional;
    }

    public void setTerceroInstitucional(
            TerceroInstitucionalEntity terceroInstitucional) {
        this.terceroInstitucional = terceroInstitucional;
    }

    public EmpleadoEntity getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoEntity empleado) {
        this.empleado = empleado;
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

    public Integer getAnioFinal() {
        return anioFinal;
    }

    public void setAnioFinal(Integer anioFinal) {
        this.anioFinal = anioFinal;
    }

    public Integer getAnioInicial() {
        return anioInicial;
    }

    public void setAnioInicial(Integer anioInicial) {
        this.anioInicial = anioInicial;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getFolioDocumento() {
        return folioDocumento;
    }

    public void setFolioDocumento(String folioDocumento) {
        this.folioDocumento = folioDocumento;
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

}
