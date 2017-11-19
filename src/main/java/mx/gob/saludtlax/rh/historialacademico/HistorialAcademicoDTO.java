/*
 *
 */

package mx.gob.saludtlax.rh.historialacademico;

import java.util.Date;

public class HistorialAcademicoDTO {

    private Integer idHistorialAcademico;
    private Integer idEmpleado;
    private String nombreEscolaridad;
    private Integer escolaridad = 0;
    private Integer documentoComprobatorio = 0;
    private String nombreDocumentoComprobatorio;
    private String estatusComprobatorio;
    private String nombreInstitucion;
    private Date fechaInicial;
    private Date FechaFinal;
    private String duracion;
    private String nombreCurso;
    private Boolean cursando = false;
    private Boolean tieneDocumentacion;
    private Boolean esMaximoEstudio;
    private Date fechaExpedicionCedula;
    private Integer numeroCedula;

    private String comentarioMovimiento = "";
    private Integer idUsuarioEnSesion;

    public String lccHistorial() {
        return "HistorialAcademico[idHistorialAcademico=" + idHistorialAcademico
                + ", escolaridad=" + escolaridad + ", idEmpleado=" + idEmpleado
                + ", comprobanteEstudio=" + nombreDocumentoComprobatorio
                + ", nombreInstitucion=" + nombreInstitucion + ", fechaInicial="
                + fechaInicial + ", FechaFinal=" + FechaFinal + ", duracion="
                + duracion + ", cursando=" + cursando + ", nombreCurso="
                + nombreCurso + ", tieneDocumentacion=" + tieneDocumentacion
                + ", maximoEstudio=" + esMaximoEstudio
                + ", fechaExpedicionCedula=" + fechaExpedicionCedula
                + ", numeroCedula=" + numeroCedula + "]";
    }

    public Integer getIdHistorialAcademico() {
        return idHistorialAcademico;
    }

    public Boolean getEsMaximoEstudio() {
        return esMaximoEstudio;
    }

    public void setEsMaximoEstudio(Boolean esMaximoEstudio) {
        this.esMaximoEstudio = esMaximoEstudio;
    }

    public Boolean getTieneDocumentacion() {
        return tieneDocumentacion;
    }

    public void setTieneDocumentacion(Boolean tieneDocumentacion) {
        this.tieneDocumentacion = tieneDocumentacion;
    }

    public String getNombreDocumentoComprobatorio() {
        return nombreDocumentoComprobatorio;
    }

    public void setNombreDocumentoComprobatorio(
            String nombreDocumentoComprobatorio) {
        this.nombreDocumentoComprobatorio = nombreDocumentoComprobatorio;
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

    public Integer getDocumentoComprobatorio() {
        return documentoComprobatorio;
    }

    public void setDocumentoComprobatorio(Integer documentoComprobatorio) {
        this.documentoComprobatorio = documentoComprobatorio;
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

    public String getEstatusComprobatorio() {
        return estatusComprobatorio;
    }

    public void setEstatusComprobatorio(String estatusComprobatorio) {
        this.estatusComprobatorio = estatusComprobatorio;
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

    /**
     * @return the idEmpleado
     */
    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    /**
     * @param idEmpleado
     *            the idEmpleado to set
     */
    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    /**
     * @return the comentarioMovimiento
     */
    public String getComentarioMovimiento() {
        return comentarioMovimiento;
    }

    /**
     * @param comentarioMovimiento
     *            the comentarioMovimiento to set
     */
    public void setComentarioMovimiento(String comentarioMovimiento) {
        this.comentarioMovimiento = comentarioMovimiento;
    }

    /**
     * @return the idUsuarioEnSesion
     */
    public Integer getIdUsuarioEnSesion() {
        return idUsuarioEnSesion;
    }

    /**
     * @param idUsuarioEnSesion
     *            the idUsuarioEnSesion to set
     */
    public void setIdUsuarioEnSesion(Integer idUsuarioEnSesion) {
        this.idUsuarioEnSesion = idUsuarioEnSesion;
    }

}
