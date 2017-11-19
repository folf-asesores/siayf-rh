/*
 *
 */

package mx.gob.saludtlax.rh.empleados.datolaboral;

import mx.gob.saludtlax.rh.configuracion.tabulador.InfoSueldoDTO;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 21/11/2016 11:51:59
 */
public class InfoDatosLaboralesDTO {

    private Integer numeroEmpleado;
    private String proyecto;
    private String dependencia;
    private String unidadResponsable;
    private String nombramiento;
    private String puesto;
    private String fuenteFinanciamiento;
    private String subfuenteFinanciamiento;
    private String tipoRecurso;
    private InfoSueldoDTO sueldo;

    public Integer getNumeroEmpleado() {
        return numeroEmpleado;
    }

    public void setNumeroEmpleado(Integer numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

    public String getUnidadResponsable() {
        return unidadResponsable;
    }

    public void setUnidadResponsable(String unidadResponsable) {
        this.unidadResponsable = unidadResponsable;
    }

    public String getNombramiento() {
        return nombramiento;
    }

    public void setNombramiento(String nombramiento) {
        this.nombramiento = nombramiento;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getFuenteFinanciamiento() {
        return fuenteFinanciamiento;
    }

    public void setFuenteFinanciamiento(String fuenteFinanciamiento) {
        this.fuenteFinanciamiento = fuenteFinanciamiento;
    }

    public String getSubfuenteFinanciamiento() {
        return subfuenteFinanciamiento;
    }

    public void setSubfuenteFinanciamiento(String subfuenteFinanciamiento) {
        this.subfuenteFinanciamiento = subfuenteFinanciamiento;
    }

    public String getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(String tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    public InfoSueldoDTO getSueldo() {
        return sueldo;
    }

    public void setSueldo(InfoSueldoDTO sueldo) {
        this.sueldo = sueldo;
    }

}
