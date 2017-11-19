/*
 *
 */

package mx.gob.saludtlax.rh.empleados.datolaboral;

import java.math.BigDecimal;

/**
 * @author Leila Schiaffini Ehuan
 * @since 15/08/2016 13:08:52
 *
 */
public class DetalleConfiguracionPresupuestoDTO {
    private Integer idDetalle;
    private Integer numeroEmpleado;
    private String proyecto;
    private String dependencia;
    private String unidadResponsable;
    private String tipoContratacion;
    private Integer idTipoContratacion;
    private String nombramiento;
    private Integer idTipoNombramiento;
    private String codigoPuesto;
    private String puesto;
    private String fuenteFinanciamiento;
    private String subfuenteFinanciamiento;
    private String tipoRecurso;
    private String nombreEmpleado;
    private String estatus;
    private String cuentaFinanciamiento;
    private String tabulador;
    private BigDecimal sueldoAutorizado;
    private BigDecimal sueldoTabulador;

    public Integer getIdTipoNombramiento() {
        return idTipoNombramiento;
    }

    public void setIdTipoNombramiento(Integer idTipoNombramiento) {
        this.idTipoNombramiento = idTipoNombramiento;
    }

    public Integer getIdTipoContratacion() {
        return idTipoContratacion;
    }

    public void setIdTipoContratacion(Integer idTipoContratacion) {
        this.idTipoContratacion = idTipoContratacion;
    }

    public Integer getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public BigDecimal getSueldoAutorizado() {
        return sueldoAutorizado;
    }

    public BigDecimal getSueldoTabulador() {
        return sueldoTabulador;
    }

    public void setSueldoAutorizado(BigDecimal sueldoAutorizado) {
        this.sueldoAutorizado = sueldoAutorizado;
    }

    public void setSueldoTabulador(BigDecimal sueldoTabulador) {
        this.sueldoTabulador = sueldoTabulador;
    }

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

    public String getTipoContratacion() {
        return tipoContratacion;
    }

    public void setTipoContratacion(String tipoContratacion) {
        this.tipoContratacion = tipoContratacion;
    }

    public String getNombramiento() {
        return nombramiento;
    }

    public void setNombramiento(String nombramiento) {
        this.nombramiento = nombramiento;
    }

    public String getCodigoPuesto() {
        return codigoPuesto;
    }

    public void setCodigoPuesto(String codigoPuesto) {
        this.codigoPuesto = codigoPuesto;
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

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getCuentaFinanciamiento() {
        return cuentaFinanciamiento;
    }

    public void setCuentaFinanciamiento(String cuentaFinanciamiento) {
        this.cuentaFinanciamiento = cuentaFinanciamiento;
    }

    public String getTabulador() {
        return tabulador;
    }

    public void setTabulador(String tabulador) {
        this.tabulador = tabulador;
    }

}
