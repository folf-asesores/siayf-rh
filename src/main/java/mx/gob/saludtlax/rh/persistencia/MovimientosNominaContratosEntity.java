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
 *
 * @author José Pablo
 *
 */
@Entity
@Table(name = "movimientos_nomina_contratos")
public class MovimientosNominaContratosEntity implements Serializable {

    private static final long serialVersionUID = 6364820582747446934L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimiento_fijo")
    private Integer idMovimiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_concepto_contratos")
    private ConceptoNominaContratosEntity conceptoNominaContrato;

    @Column(name = "descripcion_concepto")
    private String decripcionConcepto;

    @Column(name = "id_empleado")
    private Integer idEmpleado;

    @Column(name = "rfc")
    private String rfc;

    @Column(name = "quincena_operacion")
    private Integer quincenaOperacion;

    @Column(name = "anyo_operacion")
    private Integer anyoOperacion;

    @Column(name = "numero_pagos")
    private Integer numeroPagos;

    @Column(name = "monto")
    private BigDecimal monto;

    @Column(name = "abonado")
    private BigDecimal abonado;

    @Column(name = "saldo")
    private BigDecimal saldo;

    @Column(name = "descuento")
    private BigDecimal descuento;

    @Column(name = "periodo_inicial")
    private Integer periodoInicial;

    @Column(name = "anio_inicial")
    private Integer anioInicial;

    @Column(name = "periodo_final")
    private Integer periodoFinal;

    @Column(name = "anio_final")
    private Integer anioFinal;

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

    @Column(name = "id_tipo_periodo")
    private Integer idTipoPeriodo;

    @Column(name = "dias")
    private Integer dias;

    @Column(name = "id_estatus")
    private Integer idEstatus;

    @Column(name = "id_nomina_empleado")
    private Integer idNominaEmpleado;

    public Integer getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(Integer idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public ConceptoNominaContratosEntity getConceptoNominaContrato() {
        return conceptoNominaContrato;
    }

    public void setConceptoNominaContrato(ConceptoNominaContratosEntity conceptoNominaContrato) {
        this.conceptoNominaContrato = conceptoNominaContrato;
    }

    public String getDecripcionConcepto() {
        return decripcionConcepto;
    }

    public void setDecripcionConcepto(String decripcionConcepto) {
        this.decripcionConcepto = decripcionConcepto;
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

    public Integer getNumeroPagos() {
        return numeroPagos;
    }

    public void setNumeroPagos(Integer numeroPagos) {
        this.numeroPagos = numeroPagos;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public BigDecimal getAbonado() {
        return abonado;
    }

    public void setAbonado(BigDecimal abonado) {
        this.abonado = abonado;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public Integer getPeriodoInicial() {
        return periodoInicial;
    }

    public void setPeriodoInicial(Integer periodoInicial) {
        this.periodoInicial = periodoInicial;
    }

    public Integer getAnioInicial() {
        return anioInicial;
    }

    public void setAnioInicial(Integer anioInicial) {
        this.anioInicial = anioInicial;
    }

    public Integer getPeriodoFinal() {
        return periodoFinal;
    }

    public void setPeriodoFinal(Integer periodoFinal) {
        this.periodoFinal = periodoFinal;
    }

    public Integer getAnioFinal() {
        return anioFinal;
    }

    public void setAnioFinal(Integer anioFinal) {
        this.anioFinal = anioFinal;
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

    public Integer getIdTipoPeriodo() {
        return idTipoPeriodo;
    }

    public void setIdTipoPeriodo(Integer idTipoPeriodo) {
        this.idTipoPeriodo = idTipoPeriodo;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public Integer getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(Integer idEstatus) {
        this.idEstatus = idEstatus;
    }

    public Integer getIdNominaEmpleado() {
        return idNominaEmpleado;
    }

    public void setIdNominaEmpleado(Integer idNominaEmpleado) {
        this.idNominaEmpleado = idNominaEmpleado;
    }
}
