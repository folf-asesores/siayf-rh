/*
 *
 */

package mx.gob.saludtlax.rh.bolsatrabajo.aspirantes;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Eduardo Mex
 * @version 21/03/2016 14:49:38
 * @email Lic.Eduardo_Mex@hotmail.com
 */
public class ExperienciaLaboralAspiranteDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5485027721896666049L;

    private Integer idExperienciaLaboralAspirante = 0;

    private Integer idEstado = 0;

    private Integer idAspirante = 0;

    private String nombreEmpresa;

    private String puesto;

    private Date fechaInicial;

    private Date fechaFinal;

    private String direccionEmpresa;

    private String motivoSeparacion;

    private BigDecimal sueldoMensual;

    private String comentarios;

    private String telefono;

    private String correoContacto;

    private String nombreJefe;

    private String puestoJefe;

    private Boolean solicitarInformacion = true;

    private String razonNoSolicitar;

    /**
     * @return the idExperienciaLaboralAspirante
     */
    public Integer getIdExperienciaLaboralAspirante() {
        return idExperienciaLaboralAspirante;
    }

    /**
     * @param idExperienciaLaboralAspirante
     *            the idExperienciaLaboralAspirante to set
     */
    public void setIdExperienciaLaboralAspirante(Integer idExperienciaLaboralAspirante) {
        this.idExperienciaLaboralAspirante = idExperienciaLaboralAspirante;
    }

    /**
     * @return the idEstado
     */
    public Integer getIdEstado() {
        return idEstado;
    }

    /**
     * @param idEstado
     *            the idEstado to set
     */
    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    /**
     * @return the idAspirante
     */
    public Integer getIdAspirante() {
        return idAspirante;
    }

    /**
     * @param idAspirante
     *            the idAspirante to set
     */
    public void setIdAspirante(Integer idAspirante) {
        this.idAspirante = idAspirante;
    }

    /**
     * @return the nombreEmpresa
     */
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    /**
     * @param nombreEmpresa
     *            the nombreEmpresa to set
     */
    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    /**
     * @return the puesto
     */
    public String getPuesto() {
        return puesto;
    }

    /**
     * @param puesto
     *            the puesto to set
     */
    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    /**
     * @return the motivoSeparacion
     */
    public String getMotivoSeparacion() {
        return motivoSeparacion;
    }

    /**
     * @param motivoSeparacion
     *            the motivoSeparacion to set
     */
    public void setMotivoSeparacion(String motivoSeparacion) {
        this.motivoSeparacion = motivoSeparacion;
    }

    /**
     * @return the sueldoMensual
     */
    public BigDecimal getSueldoMensual() {
        return sueldoMensual;
    }

    /**
     * @param sueldoMensual
     *            the sueldoMensual to set
     */
    public void setSueldoMensual(BigDecimal sueldoMensual) {
        this.sueldoMensual = sueldoMensual;
    }

    /**
     * @return the comentarios
     */
    public String getComentarios() {
        return comentarios;
    }

    /**
     * @param comentarios
     *            the comentarios to set
     */
    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono
     *            the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the nombreJefe
     */
    public String getNombreJefe() {
        return nombreJefe;
    }

    /**
     * @param nombreJefe
     *            the nombreJefe to set
     */
    public void setNombreJefe(String nombreJefe) {
        this.nombreJefe = nombreJefe;
    }

    /**
     * @return the puestoJefe
     */
    public String getPuestoJefe() {
        return puestoJefe;
    }

    /**
     * @param puestoJefe
     *            the puestoJefe to set
     */
    public void setPuestoJefe(String puestoJefe) {
        this.puestoJefe = puestoJefe;
    }

    /**
     * @return the solicitarInformacion
     */
    public Boolean getSolicitarInformacion() {
        return solicitarInformacion;
    }

    /**
     * @param solicitarInformacion
     *            the solicitarInformacion to set
     */
    public void setSolicitarInformacion(Boolean solicitarInformacion) {
        this.solicitarInformacion = solicitarInformacion;
    }

    /**
     * @return the razonNoSolicitar
     */
    public String getRazonNoSolicitar() {
        return razonNoSolicitar;
    }

    /**
     * @param razonNoSolicitar
     *            the razonNoSolicitar to set
     */
    public void setRazonNoSolicitar(String razonNoSolicitar) {
        this.razonNoSolicitar = razonNoSolicitar;
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

    public String getDireccionEmpresa() {
        return direccionEmpresa;
    }

    public void setDireccionEmpresa(String direccionEmpresa) {
        this.direccionEmpresa = direccionEmpresa;
    }

    public String getCorreoContacto() {
        return correoContacto;
    }

    public void setCorreoContacto(String correoContacto) {
        this.correoContacto = correoContacto;
    }

}
