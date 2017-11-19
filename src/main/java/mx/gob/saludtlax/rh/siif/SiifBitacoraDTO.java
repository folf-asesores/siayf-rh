
package mx.gob.saludtlax.rh.siif;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.gob.saludtlax.rh.siif.layout.SIIFEncabezadoDTO;

public class SiifBitacoraDTO {
    private Integer idSiifBitacora;
    private Integer idNombramiento;
    private String nombramiento;
    private String status;
    private String periodo;
    private Integer anio;
    private Date fechaImportado;
    private Date fechaEnvio;
    private Integer idCuentaBancaria;
    private Integer idTipoNomina;
    private String cuentaBancaria;
    private String tipoNomina;

    private Integer totalNomina;
    private BigDecimal totalPercepciones;
    private BigDecimal totalDeducciones;
    private BigDecimal totalNeto;
    private String periodoAfectacion;
    private Integer anioAfectacion;

    private Integer tipoArchivo;

    private List<SIIFEncabezadoDTO> siifEncabezadoList;

    public Integer getIdSiifBitacora() {
        return idSiifBitacora;
    }

    public void setIdSiifBitacora(Integer idSiifBitacora) {
        this.idSiifBitacora = idSiifBitacora;
    }

    public Integer getIdNombramiento() {
        return idNombramiento;
    }

    public void setIdNombramiento(Integer idNombramiento) {
        this.idNombramiento = idNombramiento;
    }

    public String getNombramiento() {
        return nombramiento;
    }

    public void setNombramiento(String nombramiento) {
        this.nombramiento = nombramiento;
    }

    public Integer getTotalNomina() {
        return totalNomina;
    }

    public void setTotalNomina(Integer totalNomina) {
        this.totalNomina = totalNomina;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Date getFechaImportado() {
        return fechaImportado;
    }

    public void setFechaImportado(Date fechaImportado) {
        this.fechaImportado = fechaImportado;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public BigDecimal getTotalPercepciones() {
        return totalPercepciones;
    }

    public void setTotalPercepciones(BigDecimal totalPercepciones) {
        this.totalPercepciones = totalPercepciones;
    }

    public BigDecimal getTotalDeducciones() {
        return totalDeducciones;
    }

    public void setTotalDeducciones(BigDecimal totalDeducciones) {
        this.totalDeducciones = totalDeducciones;
    }

    public BigDecimal getTotalNeto() {
        return totalNeto;
    }

    public void setTotalNeto(BigDecimal totalNeto) {
        this.totalNeto = totalNeto;
    }

    public Integer getIdCuentaBancaria() {
        return idCuentaBancaria;
    }

    public void setIdCuentaBancaria(Integer idCuentaBancaria) {
        this.idCuentaBancaria = idCuentaBancaria;
    }

    public Integer getIdTipoNomina() {
        return idTipoNomina;
    }

    public void setIdTipoNomina(Integer idTipoNomina) {
        this.idTipoNomina = idTipoNomina;
    }

    public String getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(String cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    public String getTipoNomina() {
        return tipoNomina;
    }

    public void setTipoNomina(String tipoNomina) {
        this.tipoNomina = tipoNomina;
    }

    public List<SIIFEncabezadoDTO> getSiifEncabezadoList() {
        return siifEncabezadoList;
    }

    public void setSiifEncabezadoList(List<SIIFEncabezadoDTO> siifEncabezadoList) {
        this.siifEncabezadoList = siifEncabezadoList;
    }

    public String getPeriodoAfectacion() {
        return periodoAfectacion;
    }

    public void setPeriodoAfectacion(String periodoAfectacion) {
        this.periodoAfectacion = periodoAfectacion;
    }

    public Integer getAnioAfectacion() {
        return anioAfectacion;
    }

    public void setAnioAfectacion(Integer anioAfectacion) {
        this.anioAfectacion = anioAfectacion;
    }

    public Integer getTipoArchivo() {
        return tipoArchivo;
    }

    public void setTipoArchivo(Integer tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }

}
