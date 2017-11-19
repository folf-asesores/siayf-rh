/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 04/09/2016 17:20:04
 */
@Entity
@Table(name = "nombramientos")
public class NombramientoEntity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 247572429208578684L;

    @Id
    @Column(name = "id_nombramiento")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idNombramiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empleado")
    private EmpleadoEntity empleado;

    @Column(name = "clave_presupuestal")
    private String clavePresupuestal;

    @Column(name = "funcion")
    private String funcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_jornada_trabajo")
    private TiposDuracionJornadaEntity tipoJornada;

    @Column(name = "sueldo")
    private BigDecimal sueldo;

    @Column(name = "adscripcion")
    private String adscripcion;

    @Column(name = "edad")
    private Integer edad;

    @Column(name = "estado_civil")
    private String estadoCivil;

    @Column(name = "domicilio")
    private String domicilio;

    @Column(name = "numero_oficio")
    private String numeroOficio;

    @ManyToOne
    @JoinColumn(name = "id_clasificacion_nombramiento")
    private ClasificacionNombramientoEntity clasificacionNombramiento;

    @Column(name = "fecha_recepcion")
    private Date fechaRecepcion;

    @Column(name = "estatus")
    private String estatus;

    @Column(name = "fecha_impreso")
    private Date fechaImpreso;

    @ManyToOne
    @JoinColumn(name = "id_tipo_nombramiento")
    private TiposNombramientosEntity tiposNombramientos;

    /**
     * @return the tiposNombramientos
     */
    public TiposNombramientosEntity getTiposNombramientos() {
        return tiposNombramientos;
    }

    /**
     * @param tiposNombramientos
     *            the tiposNombramientos to set
     */
    public void setTiposNombramientos(TiposNombramientosEntity tiposNombramientos) {
        this.tiposNombramientos = tiposNombramientos;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public String getAdscripcion() {
        return adscripcion;
    }

    public void setAdscripcion(String adscripcion) {
        this.adscripcion = adscripcion;
    }

    public Integer getIdNombramiento() {
        return idNombramiento;
    }

    public void setIdNombramiento(Integer idNombramiento) {
        this.idNombramiento = idNombramiento;
    }

    public EmpleadoEntity getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoEntity empleado) {
        this.empleado = empleado;
    }

    public String getClavePresupuestal() {
        return clavePresupuestal;
    }

    public void setClavePresupuestal(String clavePresupuestal) {
        this.clavePresupuestal = clavePresupuestal;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public TiposDuracionJornadaEntity getTipoJornada() {
        return tipoJornada;
    }

    public void setTipoJornada(TiposDuracionJornadaEntity tipoJornada) {
        this.tipoJornada = tipoJornada;
    }

    /**
     * @return the estatus
     */
    public String getEstatus() {
        return estatus;
    }

    /**
     * @param estatus
     *            the estatus to set
     */
    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    /**
     * @return the fechaImpreso
     */
    public Date getFechaImpreso() {
        return fechaImpreso;
    }

    /**
     * @param fechaImpreso
     *            the fechaImpreso to set
     */
    public void setFechaImpreso(Date fechaImpreso) {
        this.fechaImpreso = fechaImpreso;
    }

    public BigDecimal getSueldo() {
        return sueldo;
    }

    public void setSueldo(BigDecimal sueldo) {
        this.sueldo = sueldo;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getNumeroOficio() {
        return numeroOficio;
    }

    public void setNumeroOficio(String numeroOficio) {
        this.numeroOficio = numeroOficio;
    }

    public ClasificacionNombramientoEntity getClasificacionNombramiento() {
        return clasificacionNombramiento;
    }

    public void setClasificacionNombramiento(ClasificacionNombramientoEntity clasificacionNombramiento) {
        this.clasificacionNombramiento = clasificacionNombramiento;
    }

}
