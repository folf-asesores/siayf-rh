
package mx.gob.saludtlax.rh.nomina.movimientoscontrato;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.gob.saludtlax.rh.nomina.productosnomina.FaltaContadaDTO;

/**
 *
 * @author José Pablo
 *
 */
public class MovimientoContratosDTO {
    private Integer idMovimientoContratos;
    private Integer idConceptoContratos;// +
    private Integer idEmpleado;
    private Integer idNominaEmpleado;
    private Date fechaRegistro;//?
    private Date fechaModificacion;//?
    private String rfc;
    private Date fechaDocumento;// +
    private String folioDocumento;// +
    private BigDecimal monto;// +
    private BigDecimal abonado;// +
    private BigDecimal saldo;// +
    private BigDecimal descuento;// +
    private Integer numeroAbonos;// +
    private Integer anioInicial;// +
    private Integer periodoInicial;// +
    private Integer anioFinal;// +
    private Integer periodoFinal;// +
    private Integer dias;// +
    private Integer idEstatus;// +
    private Integer idTipoMovimiento;
    private Integer idTipoPeriodo;
    private Integer quincenaOperacion;
    private String descripcion_concepto;
    private List<FaltaContadaDTO> faltaContadaList;
    private List<DetalleMovimientoContratoDTO> listaDetalles;

    private String nomina;
    private String estatus;

    public Integer getIdMovimientoContratos() {
        return idMovimientoContratos;
    }

    public void setIdMovimientoContratos(Integer idMovimientoContratos) {
        this.idMovimientoContratos = idMovimientoContratos;
    }

    public Integer getIdConceptoContratos() {
        return idConceptoContratos;
    }

    public void setIdConceptoContratos(Integer idConceptoContratos) {
        this.idConceptoContratos = idConceptoContratos;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
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

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public Date getFechaDocumento() {
        return fechaDocumento;
    }

    public void setFechaDocumento(Date fechaDocumento) {
        this.fechaDocumento = fechaDocumento;
    }

    public String getFolioDocumento() {
        return folioDocumento;
    }

    public void setFolioDocumento(String folioDocumento) {
        this.folioDocumento = folioDocumento;
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

    public Integer getNumeroAbonos() {
        return numeroAbonos;
    }

    public void setNumeroAbonos(Integer numeroAbonos) {
        this.numeroAbonos = numeroAbonos;
    }

    public Integer getAnioInicial() {
        return anioInicial;
    }

    public void setAnioInicial(Integer anioInicial) {
        this.anioInicial = anioInicial;
    }

    public Integer getPeriodoInicial() {
        return periodoInicial;
    }

    public void setPeriodoInicial(Integer periodoInicial) {
        this.periodoInicial = periodoInicial;
    }

    public Integer getAnioFinal() {
        return anioFinal;
    }

    public void setAnioFinal(Integer anioFinal) {
        this.anioFinal = anioFinal;
    }

    public Integer getPeriodoFinal() {
        return periodoFinal;
    }

    public void setPeriodoFinal(Integer periodoFinal) {
        this.periodoFinal = periodoFinal;
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

    public Integer getQuincenaOperacion() {
        return quincenaOperacion;
    }

    public void setQuincenaOperacion(Integer quincenaOperacion) {
        this.quincenaOperacion = quincenaOperacion;
    }

    public Integer getIdNominaEmpleado() {
        return idNominaEmpleado;
    }

    public void setIdNominaEmpleado(Integer idNominaEmpleado) {
        this.idNominaEmpleado = idNominaEmpleado;
    }

    public String getDescripcion_concepto() {
        return descripcion_concepto;
    }

    public void setDescripcion_concepto(String descripcion_concepto) {
        this.descripcion_concepto = descripcion_concepto;
    }

    public List<DetalleMovimientoContratoDTO> getListaDetalles() {
        return listaDetalles;
    }

    public void setListaDetalles(
            List<DetalleMovimientoContratoDTO> listaDetalles) {
        this.listaDetalles = listaDetalles;
    }

    public List<FaltaContadaDTO> getFaltaContadaList() {
        return faltaContadaList;
    }

    public void setFaltaContadaList(List<FaltaContadaDTO> faltaContadaList) {
        this.faltaContadaList = faltaContadaList;
    }

    public String getNomina() {
        return nomina;
    }

    public void setNomina(String nomina) {
        this.nomina = nomina;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
}