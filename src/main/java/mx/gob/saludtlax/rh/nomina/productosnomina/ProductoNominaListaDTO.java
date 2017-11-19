
package mx.gob.saludtlax.rh.nomina.productosnomina;

import java.io.Serializable;
import java.util.Date;

public class ProductoNominaListaDTO implements Serializable {
    private static final long serialVersionUID = -3705259295830199202L;

    private Integer idProductoNomina;
    private String ejercicioFiscal;
    private Integer idEjercicioFiscal;
    private String periodoCalendario;
    private Integer idPeriodoCalendario;
    private String tipoNombramiento;
    private Integer idTipoNombramiento;
    private String tipoContratacion;
    private Integer idTipoContratacion;
    private String fuenteFinanciamiento;
    private Integer idFuenteFinanciamiento;
    private String subfuenteFinanciamiento;
    private Integer idSubfuenteFinanciamiento;
    private String tipoNomina;
    private Integer idTipoNomina;
    private Date fechaPago;
    private Integer idEstatus;
    private String estatus;
    private String nombreProducto;

    public Integer getIdProductoNomina() {
        return idProductoNomina;
    }

    public void setIdProductoNomina(Integer idProductoNomina) {
        this.idProductoNomina = idProductoNomina;
    }

    public String getEjercicioFiscal() {
        return ejercicioFiscal;
    }

    public void setEjercicioFiscal(String ejercicioFiscal) {
        this.ejercicioFiscal = ejercicioFiscal;
    }

    public Integer getIdEjercicioFiscal() {
        return idEjercicioFiscal;
    }

    public void setIdEjercicioFiscal(Integer idEjercicioFiscal) {
        this.idEjercicioFiscal = idEjercicioFiscal;
    }

    public String getPeriodoCalendario() {
        return periodoCalendario;
    }

    public void setPeriodoCalendario(String periodoCalendario) {
        this.periodoCalendario = periodoCalendario;
    }

    public Integer getIdPeriodoCalendario() {
        return idPeriodoCalendario;
    }

    public void setIdPeriodoCalendario(Integer idPeriodoCalendario) {
        this.idPeriodoCalendario = idPeriodoCalendario;
    }

    public String getTipoNombramiento() {
        return tipoNombramiento;
    }

    public void setTipoNombramiento(String tipoNombramiento) {
        this.tipoNombramiento = tipoNombramiento;
    }

    public Integer getIdTipoNombramiento() {
        return idTipoNombramiento;
    }

    public void setIdTipoNombramiento(Integer idTipoNombramiento) {
        this.idTipoNombramiento = idTipoNombramiento;
    }

    public String getTipoContratacion() {
        return tipoContratacion;
    }

    public void setTipoContratacion(String tipoContratacion) {
        this.tipoContratacion = tipoContratacion;
    }

    public Integer getIdTipoContratacion() {
        return idTipoContratacion;
    }

    public void setIdTipoContratacion(Integer idTipoContratacion) {
        this.idTipoContratacion = idTipoContratacion;
    }

    public String getFuenteFinanciamiento() {
        return fuenteFinanciamiento;
    }

    public void setFuenteFinanciamiento(String fuenteFinanciamiento) {
        this.fuenteFinanciamiento = fuenteFinanciamiento;
    }

    public Integer getIdFuenteFinanciamiento() {
        return idFuenteFinanciamiento;
    }

    public void setIdFuenteFinanciamiento(Integer idFuenteFinanciamiento) {
        this.idFuenteFinanciamiento = idFuenteFinanciamiento;
    }

    public String getSubfuenteFinanciamiento() {
        return subfuenteFinanciamiento;
    }

    public void setSubfuenteFinanciamiento(String subfuenteFinanciamiento) {
        this.subfuenteFinanciamiento = subfuenteFinanciamiento;
    }

    public Integer getIdSubfuenteFinanciamiento() {
        return idSubfuenteFinanciamiento;
    }

    public void setIdSubfuenteFinanciamiento(
            Integer idSubfuenteFinanciamiento) {
        this.idSubfuenteFinanciamiento = idSubfuenteFinanciamiento;
    }

    public String getTipoNomina() {
        return tipoNomina;
    }

    public void setTipoNomina(String tipoNomina) {
        this.tipoNomina = tipoNomina;
    }

    public Integer getIdTipoNomina() {
        return idTipoNomina;
    }

    public void setIdTipoNomina(Integer idTipoNomina) {
        this.idTipoNomina = idTipoNomina;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Integer getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(Integer idEstatus) {
        this.idEstatus = idEstatus;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
}