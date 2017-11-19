/*
 *
 */

package mx.gob.saludtlax.rh.puestosautorizados.programas;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 25/08/2016 20:23:28
 */
public class ProgramaDTO {
    private Integer idPrograma;
    private String programa;
    private String descripcion;
    private Integer tipoConfiguracion;
    private Integer idProyecto;
    private Integer idDependencia;
    private Integer idUnidadResponsable;
    private Integer idFuenteFinanciamiento;
    private Integer idSubfuentefinanciamiento;
    private Integer idTipoRecurso;

    public Integer getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(Integer idPrograma) {
        this.idPrograma = idPrograma;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public Integer getTipoConfiguracion() {
        return tipoConfiguracion;
    }

    public void setTipoConfiguracion(Integer tipoConfiguracion) {
        this.tipoConfiguracion = tipoConfiguracion;
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Integer getIdDependencia() {
        return idDependencia;
    }

    public void setIdDependencia(Integer idDependencia) {
        this.idDependencia = idDependencia;
    }

    public Integer getIdUnidadResponsable() {
        return idUnidadResponsable;
    }

    public void setIdUnidadResponsable(Integer idUnidadResponsable) {
        this.idUnidadResponsable = idUnidadResponsable;
    }

    public Integer getIdFuenteFinanciamiento() {
        return idFuenteFinanciamiento;
    }

    public void setIdFuenteFinanciamiento(Integer idFuenteFinanciamiento) {
        this.idFuenteFinanciamiento = idFuenteFinanciamiento;
    }

    public Integer getIdSubfuentefinanciamiento() {
        return idSubfuentefinanciamiento;
    }

    public void setIdSubfuentefinanciamiento(
            Integer idSubfuentefinanciamiento) {
        this.idSubfuentefinanciamiento = idSubfuentefinanciamiento;
    }

    public Integer getIdTipoRecurso() {
        return idTipoRecurso;
    }

    public void setIdTipoRecurso(Integer idTipoRecurso) {
        this.idTipoRecurso = idTipoRecurso;
    }

}
