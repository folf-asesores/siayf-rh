/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "siif_bitacoras")
public class SiifBitacoraEntity implements Serializable {

    private static final long serialVersionUID = -3491218769714297031L;

    @Id
    @Column(name = "id_siif_bitacoras")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReporteSiif;
    @Column(name = "id_nombramiento")
    private Integer idNombramiento;
    @Column(name = "anio")
    private Integer anio;
    @Column(name = "nombramiento_descripcion")
    private String nombramiento;
    @Column(name = "total_nomina")
    private Integer totalNomina;
    @Column(name = "status")
    private String status;
    @Column(name = "periodo")
    private String periodo;
    @Column(name = "fecha_importado")
    private Date fechaImportado;
    @Column(name = "fecha_envio")
    private Date fechaEnvio;
    @Column(name = "total_percepciones")
    private BigDecimal totalPercepciones;
    @Column(name = "total_deducciones")
    private BigDecimal totalDeducciones;
    @Column(name = "total_neto")
    private BigDecimal totalNeto;
    @Column(name = "id_cuenta_bancaria")
    private Integer idCuentaBancaria;
    @Column(name = "id_tipo_nomina")
    private Integer idTipoNomina;
    @Column(name = "periodo_reporte")
    private String periodoAfectacion;
    @Column(name = "anio_reporte")
    private Integer anioAfectacion;
    @Column(name = "tipo_archivo")
    private Integer tipoArchivo;

    public Integer getIdReporteSiif() {
        return idReporteSiif;
    }

    public void setIdReporteSiif(Integer idReporteSiif) {
        this.idReporteSiif = idReporteSiif;
    }

    public Integer getIdNombramiento() {
        return idNombramiento;
    }

    public void setIdNombramiento(Integer idNombramiento) {
        this.idNombramiento = idNombramiento;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
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
