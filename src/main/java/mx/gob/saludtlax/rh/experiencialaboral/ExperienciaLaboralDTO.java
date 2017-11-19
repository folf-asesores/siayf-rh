/*
 *
 */

package mx.gob.saludtlax.rh.experiencialaboral;

import java.math.BigDecimal;
import java.util.Date;

public class ExperienciaLaboralDTO {

    private Integer idExperienciaLaboral;

    private String nombreEmpresa;

    private String direccionEmpresa;

    private String correoEmpresa;

    private String telefonoEmpresa;

    private Boolean solicitarInformacion;

    private String razonNoSolicitar;

    private String nombreJefe;

    private String puestoJefe;

    private String puestoAspirante;

    private BigDecimal sueldoMensual;

    private Date fechaInicial;

    private Date fechaFinal;

    private String motivoSeparacion;

    private String comentarios;

    private String comentarioMovimiento = "";
    private Integer idUsuarioEnSesion;

    public String lccExperiencia() {
        return "ExperienciaLaboral[idExperienciaLaboral=" + idExperienciaLaboral
                + ", nombreEmpresa=" + nombreEmpresa + ", puesto="
                + puestoAspirante + ", fechaInicial=" + fechaInicial
                + ", fechaFinal=" + fechaFinal + ", direccionEmpresa="
                + direccionEmpresa + ", motivoSeparacion=" + motivoSeparacion
                + ", sueldoMensual=" + sueldoMensual + ", comentarios="
                + comentarios + ", telefono=" + telefonoEmpresa
                + ", correoContacto=" + correoEmpresa + ", nombreJefe="
                + nombreJefe + ", puestoJefe=" + puestoJefe
                + ", solicitarInformacion=" + solicitarInformacion
                + ", razonNoSolicitar=" + razonNoSolicitar + "]";
    }

    public Integer getIdExperienciaLaboral() {
        return idExperienciaLaboral;
    }

    public void setIdExperienciaLaboral(Integer idExperienciaLaboral) {
        this.idExperienciaLaboral = idExperienciaLaboral;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getDireccionEmpresa() {
        return direccionEmpresa;
    }

    public void setDireccionEmpresa(String direccionEmpresa) {
        this.direccionEmpresa = direccionEmpresa;
    }

    public String getCorreoEmpresa() {
        return correoEmpresa;
    }

    public void setCorreoEmpresa(String correoEmpresa) {
        this.correoEmpresa = correoEmpresa;
    }

    public String getTelefonoEmpresa() {
        return telefonoEmpresa;
    }

    public void setTelefonoEmpresa(String telefonoEmpresa) {
        this.telefonoEmpresa = telefonoEmpresa;
    }

    public Boolean getSolicitarInformacion() {
        return solicitarInformacion;
    }

    public void setSolicitarInformacion(Boolean solicitarInformacion) {
        this.solicitarInformacion = solicitarInformacion;
    }

    public String getRazonNoSolicitar() {
        return razonNoSolicitar;
    }

    public void setRazonNoSolicitar(String razonNoSolicitar) {
        this.razonNoSolicitar = razonNoSolicitar;
    }

    public String getNombreJefe() {
        return nombreJefe;
    }

    public void setNombreJefe(String nombreJefe) {
        this.nombreJefe = nombreJefe;
    }

    public String getPuestoJefe() {
        return puestoJefe;
    }

    public void setPuestoJefe(String puestoJefe) {
        this.puestoJefe = puestoJefe;
    }

    public String getPuestoAspirante() {
        return puestoAspirante;
    }

    public void setPuestoAspirante(String puestoAspirante) {
        this.puestoAspirante = puestoAspirante;
    }

    public BigDecimal getSueldoMensual() {
        return sueldoMensual;
    }

    public void setSueldoMensual(BigDecimal sueldoMensual) {
        this.sueldoMensual = sueldoMensual;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getMotivoSeparacion() {
        return motivoSeparacion;
    }

    public void setMotivoSeparacion(String motivoSeparacion) {
        this.motivoSeparacion = motivoSeparacion;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
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

}
