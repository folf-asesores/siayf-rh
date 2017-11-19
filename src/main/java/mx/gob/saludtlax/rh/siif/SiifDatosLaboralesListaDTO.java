
package mx.gob.saludtlax.rh.siif;

import java.util.Date;

public class SiifDatosLaboralesListaDTO {

    private Integer idDatoLaboral;
    private String rfc;
    private String idPlaza;
    private Integer idProyecto;
    private String idDependencia;
    private String idUnidadResponsable;
    private String nombramiento;
    private String idPuesto;
    private Integer idSindicato;
    private Integer idHabilitado;
    private Date fechaIngreso;
    private String noCuenta;
    private Integer idTipoRecurso;

    //  < < < Getters & Setters > > >

    public Integer getIdDatoLaboral() {
        return idDatoLaboral;
    }

    public void setIdDatoLaboral(Integer idDatoLaboral) {
        this.idDatoLaboral = idDatoLaboral;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getIdPlaza() {
        return idPlaza;
    }

    public void setIdPlaza(String idPlaza) {
        this.idPlaza = idPlaza;
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getIdDependencia() {
        return idDependencia;
    }

    public void setIdDependencia(String idDependencia) {
        this.idDependencia = idDependencia;
    }

    public String getIdUnidadResponsable() {
        return idUnidadResponsable;
    }

    public void setIdUnidadResponsable(String idUnidadResponsable) {
        this.idUnidadResponsable = idUnidadResponsable;
    }

    public String getNombramiento() {
        return nombramiento;
    }

    public void setNombramiento(String nombramiento) {
        this.nombramiento = nombramiento;
    }

    public String getIdPuesto() {
        return idPuesto;
    }

    public void setIdPuesto(String idPuesto) {
        this.idPuesto = idPuesto;
    }

    public Integer getIdSindicato() {
        return idSindicato;
    }

    public void setIdSindicato(Integer idSindicato) {
        this.idSindicato = idSindicato;
    }

    public Integer getIdHabilitado() {
        return idHabilitado;
    }

    public void setIdHabilitado(Integer idHabilitado) {
        this.idHabilitado = idHabilitado;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getNoCuenta() {
        return noCuenta;
    }

    public void setNoCuenta(String noCuenta) {
        this.noCuenta = noCuenta;
    }

    public Integer getIdTipoRecurso() {
        return idTipoRecurso;
    }

    public void setIdTipoRecurso(Integer idTipoRecurso) {
        this.idTipoRecurso = idTipoRecurso;
    }

}