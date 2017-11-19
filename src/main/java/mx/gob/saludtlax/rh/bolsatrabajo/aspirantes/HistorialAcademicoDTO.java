/*
 *
 */

package mx.gob.saludtlax.rh.bolsatrabajo.aspirantes;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 07/03/2016-14:46:00
 */
public class HistorialAcademicoDTO implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -8995069172844451028L;

    private Integer idHistorialAcademico = 0;

    private String nombreEscolaridad;

    private Integer escolaridad = 0;

    private Integer aspirante = 0;

    private Integer comprobanteEstudio = 0;

    private String nombreComprobanteEstudio;

    private String estatusComprobanteEstudio;

    private String nombreInstitucion;

    private Date fechaInicial;

    private Date FechaFinal;

    private String duracion;

    private String nombreCurso;

    private Boolean cursando = false;

    private Boolean tieneDocumentacion = false;

    private Boolean esMaximoEstudio;

    private Date fechaExpedicionCedula;

    private Integer numeroCedula;

    public Integer getIdHistorialAcademico() {
        return idHistorialAcademico;
    }

    public String getNombreEscolaridad() {
        return nombreEscolaridad;
    }

    public void setNombreEscolaridad(String nombreEscolaridad) {
        this.nombreEscolaridad = nombreEscolaridad;
    }

    public void setIdHistorialAcademico(Integer idHistorialAcademico) {
        this.idHistorialAcademico = idHistorialAcademico;
    }

    public Integer getEscolaridad() {
        return escolaridad;
    }

    public void setEscolaridad(Integer escolaridad) {
        this.escolaridad = escolaridad;
    }

    public Integer getAspirante() {
        return aspirante;
    }

    public void setAspirante(Integer aspirante) {
        this.aspirante = aspirante;
    }

    public String getNombreInstitucion() {
        return nombreInstitucion;
    }

    public void setNombreInstitucion(String nombreInstitucion) {
        this.nombreInstitucion = nombreInstitucion;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return FechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        FechaFinal = fechaFinal;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public Boolean getCursando() {
        return cursando;
    }

    public void setCursando(Boolean cursando) {
        this.cursando = cursando;
    }

    /**
     * @return the comprobanteEstudio
     */
    public Integer getComprobanteEstudio() {
        return comprobanteEstudio;
    }

    /**
     * @param comprobanteEstudio
     *            the comprobanteEstudio to set
     */
    public void setComprobanteEstudio(Integer comprobanteEstudio) {
        this.comprobanteEstudio = comprobanteEstudio;
    }

    /**
     * @return the estatusComprobanteEstudio
     */
    public String getEstatusComprobanteEstudio() {
        return estatusComprobanteEstudio;
    }

    /**
     * @param estatusComprobanteEstudio
     *            the estatusComprobanteEstudio to set
     */
    public void setEstatusComprobanteEstudio(String estatusComprobanteEstudio) {
        this.estatusComprobanteEstudio = estatusComprobanteEstudio;
    }

    /**
     * @return the nombreComprobanteEstudio
     */
    public String getNombreComprobanteEstudio() {
        return nombreComprobanteEstudio;
    }

    /**
     * @param nombreComprobanteEstudio
     *            the nombreComprobanteEstudio to set
     */
    public void setNombreComprobanteEstudio(String nombreComprobanteEstudio) {
        this.nombreComprobanteEstudio = nombreComprobanteEstudio;
    }

    /**
     * @return the tieneDocumentacion
     */
    public Boolean getTieneDocumentacion() {
        return tieneDocumentacion;
    }

    /**
     * @param tieneDocumentacion
     *            the tieneDocumentacion to set
     */
    public void setTieneDocumentacion(Boolean tieneDocumentacion) {
        this.tieneDocumentacion = tieneDocumentacion;
    }

    /**
     * @return the esMaximoEstudio
     */
    public Boolean getEsMaximoEstudio() {
        return esMaximoEstudio;
    }

    /**
     * @param esMaximoEstudio
     *            the esMaximoEstudio to set
     */
    public void setEsMaximoEstudio(Boolean esMaximoEstudio) {
        this.esMaximoEstudio = esMaximoEstudio;
    }

    /**
     * @return the fechaExpedicionCedula
     */
    public Date getFechaExpedicionCedula() {
        return fechaExpedicionCedula;
    }

    /**
     * @param fechaExpedicionCedula
     *            the fechaExpedicionCedula to set
     */
    public void setFechaExpedicionCedula(Date fechaExpedicionCedula) {
        this.fechaExpedicionCedula = fechaExpedicionCedula;
    }

    /**
     * @return the numeroCedula
     */
    public Integer getNumeroCedula() {
        return numeroCedula;
    }

    /**
     * @param numeroCedula
     *            the numeroCedula to set
     */
    public void setNumeroCedula(Integer numeroCedula) {
        this.numeroCedula = numeroCedula;
    }

}
