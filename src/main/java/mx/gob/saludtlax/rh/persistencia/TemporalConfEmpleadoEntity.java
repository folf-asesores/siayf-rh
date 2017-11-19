/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 25/07/2016 14:24:22
 */
@Entity
@Table(name = "temporal_configuracion_empleado")
public class TemporalConfEmpleadoEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -529580609898083826L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_temporal")
    private Integer id;

    @Column(name = "rfc")
    private String rfc;

    @Column(name = "id_proyecto")
    private Integer idProyecto;

    @Column(name = "id_dependencia")
    private Integer idDependencia;

    @Column(name = "id_unidad_responsable")
    private Integer idUnidadResponsable;

    @Column(name = "nombramiento")
    private String nombramiento;

    @Column(name = "clave_puesto")
    private String clavePuesto;

    @Column(name = "id_fuente_financiamiento")
    private Integer idFuenteFinanciamiento;

    @Column(name = "id_subfuente_financiamiento")
    private Integer subfuenteFinanciamiento;

    @Column(name = "id_tipo_recurso")
    private Integer tipoRecurso;

    @Column(name = "padron")
    private Integer tipoContratacion;

    @Column(name = "fecha_ingreso")
    private Date fechaIngreso;

    @Column(name = "sueldo_mensual")
    private BigDecimal sueldoMensual;

    @Column(name = "id_configuracion_empleado")
    private Integer idConfiguracionPresupuestal;

    @Column(name = "id_programa")
    private Integer idPrograma;

    @Column(name = "id_empleado_datos_laborales")
    private Integer numero_empleado;

    public Integer getNumero_empleado() {
        return numero_empleado;
    }

    public void setNumero_empleado(Integer numero_empleado) {
        this.numero_empleado = numero_empleado;
    }

    public Integer getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(Integer idPrograma) {
        this.idPrograma = idPrograma;
    }

    public Integer getIdConfiguracionPresupuestal() {
        return idConfiguracionPresupuestal;
    }

    public void setIdConfiguracionPresupuestal(
            Integer idConfiguracionPresupuestal) {
        this.idConfiguracionPresupuestal = idConfiguracionPresupuestal;
    }

    public BigDecimal getSueldoMensual() {
        return sueldoMensual;
    }

    public void setSueldoMensual(BigDecimal sueldoMensual) {
        this.sueldoMensual = sueldoMensual;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
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

    public String getNombramiento() {
        return nombramiento;
    }

    public void setNombramiento(String nombramiento) {
        this.nombramiento = nombramiento;
    }

    public String getClavePuesto() {
        return clavePuesto;
    }

    public void setClavePuesto(String clavePuesto) {
        this.clavePuesto = clavePuesto;
    }

    public Integer getIdFuenteFinanciamiento() {
        return idFuenteFinanciamiento;
    }

    public void setIdFuenteFinanciamiento(Integer idFuenteFinanciamiento) {
        this.idFuenteFinanciamiento = idFuenteFinanciamiento;
    }

    public Integer getSubfuenteFinanciamiento() {
        return subfuenteFinanciamiento;
    }

    public void setSubfuenteFinanciamiento(Integer subfuenteFinanciamiento) {
        this.subfuenteFinanciamiento = subfuenteFinanciamiento;
    }

    public Integer getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(Integer tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    public Integer getTipoContratacion() {
        return tipoContratacion;
    }

    public void setTipoContratacion(Integer tipoContratacion) {
        this.tipoContratacion = tipoContratacion;
    }

    public Integer getId() {
        return id;
    }

}
