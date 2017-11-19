/*
 *
 */

package mx.gob.saludtlax.rh.empleado.issste;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Eduardo Mex
 *
 */
public class InfoMovimientoIsssteDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4092225817773080363L;

    private Integer idMovimientoIsssteEmpleado;

    private String rfc;

    private String curp;

    private String claveCobro;

    private String nombramiento;

    private String nivelSalarial;

    private String domicilio;

    private String nombreEmpleado;

    private String tipoMovimientoIssste;

    private Integer idTipoMovimientoIssste;

    private String numeroSeguroSocial;

    private BigDecimal sueldoIssste;

    private BigDecimal sueldoSar;

    private BigDecimal totalRemuneracion;

    private Integer idCausaBaja;

    public Integer getIdMovimientoIsssteEmpleado() {
        return idMovimientoIsssteEmpleado;
    }

    public void setIdMovimientoIsssteEmpleado(Integer idMovimientoIsssteEmpleado) {
        this.idMovimientoIsssteEmpleado = idMovimientoIsssteEmpleado;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getTipoMovimientoIssste() {
        return tipoMovimientoIssste;
    }

    public void setTipoMovimientoIssste(String tipoMovimientoIssste) {
        this.tipoMovimientoIssste = tipoMovimientoIssste;
    }

    public String getNumeroSeguroSocial() {
        return numeroSeguroSocial;
    }

    public void setNumeroSeguroSocial(String numeroSeguroSocial) {
        this.numeroSeguroSocial = numeroSeguroSocial;
    }

    public BigDecimal getSueldoIssste() {
        return sueldoIssste;
    }

    public void setSueldoIssste(BigDecimal sueldoIssste) {
        this.sueldoIssste = sueldoIssste;
    }

    public BigDecimal getSueldoSar() {
        return sueldoSar;
    }

    public void setSueldoSar(BigDecimal sueldoSar) {
        this.sueldoSar = sueldoSar;
    }

    public BigDecimal getTotalRemuneracion() {
        return totalRemuneracion;
    }

    public void setTotalRemuneracion(BigDecimal totalRemuneracion) {
        this.totalRemuneracion = totalRemuneracion;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getNivelSalarial() {
        return nivelSalarial;
    }

    public void setNivelSalarial(String nivelSalarial) {
        this.nivelSalarial = nivelSalarial;
    }

    public String getNombramiento() {
        return nombramiento;
    }

    public void setNombramiento(String nombramiento) {
        this.nombramiento = nombramiento;
    }

    public String getClaveCobro() {
        return claveCobro;
    }

    public void setClaveCobro(String claveCobro) {
        this.claveCobro = claveCobro;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public Integer getIdCausaBaja() {
        return idCausaBaja;
    }

    public void setIdCausaBaja(Integer idCausaBaja) {
        this.idCausaBaja = idCausaBaja;
    }

    public Integer getIdTipoMovimientoIssste() {
        return idTipoMovimientoIssste;
    }

    public void setIdTipoMovimientoIssste(Integer idTipoMovimientoIssste) {
        this.idTipoMovimientoIssste = idTipoMovimientoIssste;
    }

}
