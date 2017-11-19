/*
 * SIIFEncabezadoDTO.java
 * Creado el 4/07/2016 05:21:07 PM
 *
 */

package mx.gob.saludtlax.rh.siif.layout;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class SIIFEncabezadoDTO {
    private Integer idSIIFEncabezado;
    private Integer idNomina;
    private Character idPoder;
    private Integer idTipoNomina;
    private Date fechaFinQuincena;
    private String fechaFinQuincenaStr;
    private Integer idCuentaBancaria;
    private Integer claveCuentaBancaria;
    private String idTipoEmisionNomina;
    private BigDecimal percepciones;
    private BigDecimal deducciones;
    private BigDecimal neto;
    private Character idEstadoNomina;
    private Integer idNombramiento;
    private String nombramiento;
    private Integer idSIIFBitacora;
    private String subPrograma;
    private BigInteger inconsistenciaPersonal;
    private BigInteger inconsistenciaLaboral;

    public SIIFEncabezadoDTO() {
    }

    //    public SIIFEncabezadoDTO(Integer idNomina, Character idPoder, Integer idTipoNomina, Date fechaFinQuincena, Integer idCuentaBancaria, String idTipoEmisionNomina, BigDecimal percepciones, BigDecimal deducciones, BigDecimal neto, Character idEstadoNomina) {
    //        this.idNomina = idNomina;
    //        this.idPoder = idPoder;
    //        this.idTipoNomina = idTipoNomina;
    //        this.fechaFinQuincena = fechaFinQuincena;
    //        this.idCuentaBancaria = idCuentaBancaria;
    //        this.idTipoEmisionNomina = idTipoEmisionNomina;
    //        this.percepciones = percepciones;
    //        this.deducciones = deducciones;
    //        this.neto = neto;
    //        this.idEstadoNomina = idEstadoNomina;
    //    }

    public SIIFEncabezadoDTO(Integer idNomina, Character idPoder,
            Integer idTipoNomina, Date fechaFinQuincena,
            Integer claveCuentaBancaria, String idTipoEmisionNomina,
            BigDecimal percepciones, BigDecimal deducciones, BigDecimal neto,
            Character idEstadoNomina) {
        this.idNomina = idNomina;
        this.idPoder = idPoder;
        this.idTipoNomina = idTipoNomina;
        this.fechaFinQuincena = fechaFinQuincena;
        this.claveCuentaBancaria = claveCuentaBancaria;
        this.idTipoEmisionNomina = idTipoEmisionNomina;
        this.percepciones = percepciones;
        this.deducciones = deducciones;
        this.neto = neto;
        this.idEstadoNomina = idEstadoNomina;
    }

    public SIIFEncabezadoDTO(Integer idSIIFEncabezado, Integer idNomina,
            Character idPoder, Integer idTipoNomina, Date fechaFinQuincena,
            String idTipoEmisionNomina, Integer idCuentaBancaria,
            Integer claveCuentaBancaria, BigDecimal percepciones,
            BigDecimal deducciones, BigDecimal neto, Character idEstadoNomina,
            Integer idSIIFBitacora) {
        this.idSIIFEncabezado = idSIIFEncabezado;
        this.idNomina = idNomina;
        this.idPoder = idPoder;
        this.idTipoNomina = idTipoNomina;
        this.fechaFinQuincena = fechaFinQuincena;
        this.idTipoEmisionNomina = idTipoEmisionNomina;
        this.idCuentaBancaria = idCuentaBancaria;
        this.claveCuentaBancaria = claveCuentaBancaria;
        this.percepciones = percepciones;
        this.deducciones = deducciones;
        this.neto = neto;
        this.idEstadoNomina = idEstadoNomina;
        this.idSIIFBitacora = idSIIFBitacora;
    }

    public Integer getIdSIIFEncabezado() {
        return idSIIFEncabezado;
    }

    public void setIdSIIFEncabezado(Integer idSIIFEncabezado) {
        this.idSIIFEncabezado = idSIIFEncabezado;
    }

    public Integer getIdNomina() {
        return idNomina;
    }

    public void setIdNomina(Integer idNomina) {
        this.idNomina = idNomina;
    }

    public Character getIdPoder() {
        return idPoder;
    }

    public void setIdPoder(Character idPoder) {
        this.idPoder = idPoder;
    }

    public Integer getIdTipoNomina() {
        return idTipoNomina;
    }

    public void setIdTipoNomina(Integer idTipoNomina) {
        this.idTipoNomina = idTipoNomina;
    }

    public Date getFechaFinQuincena() {
        return fechaFinQuincena;
    }

    public void setFechaFinQuincena(Date fechaFinQuincena) {
        this.fechaFinQuincena = fechaFinQuincena;
    }

    public String getFechaFinQuincenaStr() {
        return fechaFinQuincenaStr;
    }

    public void setFechaFinQuincenaStr(String fechaFinQuincenaStr) {
        this.fechaFinQuincenaStr = fechaFinQuincenaStr;
    }

    public Integer getIdCuentaBancaria() {
        return idCuentaBancaria;
    }

    public void setIdCuentaBancaria(Integer idCuentaBancaria) {
        this.idCuentaBancaria = idCuentaBancaria;
    }

    public String getIdTipoEmisionNomina() {
        return idTipoEmisionNomina;
    }

    public void setIdTipoEmisionNomina(String idTipoEmisionNomina) {
        this.idTipoEmisionNomina = idTipoEmisionNomina;
    }

    public BigDecimal getPercepciones() {
        return percepciones;
    }

    public void setPercepciones(BigDecimal percepciones) {
        this.percepciones = percepciones;
    }

    public BigDecimal getDeducciones() {
        return deducciones;
    }

    public void setDeducciones(BigDecimal deducciones) {
        this.deducciones = deducciones;
    }

    public BigDecimal getNeto() {
        return neto;
    }

    public void setNeto(BigDecimal neto) {
        this.neto = neto;
    }

    public Character getIdEstadoNomina() {
        return idEstadoNomina;
    }

    public void setIdEstadoNomina(Character idEstadoNomina) {
        this.idEstadoNomina = idEstadoNomina;
    }

    public Integer getIdNombramiento() {
        return idNombramiento;
    }

    public void setIdNombramiento(Integer idNombramiento) {
        this.idNombramiento = idNombramiento;
    }

    public Integer getIdSIIFBitacora() {
        return idSIIFBitacora;
    }

    public void setIdSIIFBitacora(Integer idSIIFBitacora) {
        this.idSIIFBitacora = idSIIFBitacora;
    }

    public Integer getClaveCuentaBancaria() {
        return claveCuentaBancaria;
    }

    public void setClaveCuentaBancaria(Integer claveCuentaBancaria) {
        this.claveCuentaBancaria = claveCuentaBancaria;
    }

    public String getNombramiento() {
        return nombramiento;
    }

    public void setNombramiento(String nombramiento) {
        this.nombramiento = nombramiento;
    }

    public String getSubPrograma() {
        return subPrograma;
    }

    public void setSubPrograma(String subPrograma) {
        this.subPrograma = subPrograma;
    }

    public BigInteger getInconsistenciaPersonal() {
        return inconsistenciaPersonal;
    }

    public void setInconsistenciaPersonal(BigInteger inconsistenciaPersonal) {
        this.inconsistenciaPersonal = inconsistenciaPersonal;
    }

    public BigInteger getInconsistenciaLaboral() {
        return inconsistenciaLaboral;
    }

    public void setInconsistenciaLaboral(BigInteger inconsistenciaLaboral) {
        this.inconsistenciaLaboral = inconsistenciaLaboral;
    }
}