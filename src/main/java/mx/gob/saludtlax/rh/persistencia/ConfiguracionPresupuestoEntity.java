
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

@Entity
@Table(name = "configuraciones_presupuestales_empleados")
public class ConfiguracionPresupuestoEntity implements Serializable {
    private static final long serialVersionUID = -4152763684655562288L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_configuracion_presupuestal")
    private Integer id;

    @Column(name = "numero_empleado")
    private Integer numeroEmpleado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_proyecto")
    private ProyectoTempEntity proyecto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_dependencia")
    private DependenciaTempEntity dependencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_unidad_responsable")
    private UnidadResponsableEntity unidadResponsable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_contratacion")
    private TipoContratacionEntity tipoContratacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_nombramiento")
    private TiposNombramientosEntity nombramiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_puesto_general")
    private PuestoGeneralEntity puesto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_fuente_financiamiento")
    private FuenteFinanciamientoEntity fuenteFinanciamiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_subfuente_financiamiento")
    private SubFuenteFinanciamientoTempEntity subfuenteFinanciamiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_recurso")
    private TipoRecursoTempEntity tipoRecurso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empleado")
    private EmpleadoEntity empleado;

    @Column(name = "fecha_alta_configuracion")
    private Date fechaAltaConfiguracion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estatus")
    private EstatusConfiguracionesEntity estatus;

    @Column(name = "sueldo")
    private BigDecimal sueldo;

    @Column(name = "sueldo_01")
    private BigDecimal sueldo01;

    @Column(name = "sueldo_14")
    private BigDecimal sueldo14;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cuenta")
    private CuentasBancariasEntity cuenta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tabulador")
    private TabuladorEntity tabulador;

    @Column(name = "id_plaza")
    private String idPlaza;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_centro_responsabilidad")
    private CentroResponsabilidadEntity centroResponsabilidad;

    @Column(name = "fecha_inicio_labores")
    private Date fechaInicioLabores;

    @Column(name = "aplica_primer_pago")
    private Boolean aplicaPrimerPago;

    @Column(name = "id_jornada")
    private Integer idJornada;

    @Column(name = "id_riesgo")
    private Integer idRiesgo;

    @Column(name = "tipo_pago")//1:honorarios 2:salario
    private Integer tipoPago;

    public String lccDatosLaborales() {
        String p = " SIN PROYECTO ";
        if (proyecto != null) {
            p = proyecto.getDescripcion();
        }
        String d = " SIN DEPENDENCIA ";
        if (dependencia != null) {
            d = dependencia.getDescripcion();
        }

        String u = " SIN UNIDAD ";
        if (unidadResponsable != null) {
            u = unidadResponsable.getDescripcion();
        }
        String f = "SIN FUENTE";
        if (fuenteFinanciamiento != null) {
            f = fuenteFinanciamiento.getDescripcion();
        }

        String sub = " SIN SUBFUENTE";
        if (subfuenteFinanciamiento != null) {
            sub = subfuenteFinanciamiento.getDescripcion();
        }
        String ta = " SIN TABULADOR ";
        if (tabulador != null) {
            ta = tabulador.getPuestoGeneral().getCodigo() + " "
                    + tabulador.getPuestoGeneral().getPuesto();
        }
        return "Datos Laborales [numeroEmpleado=" + numeroEmpleado
                + ", tipoContratacion=" + tipoContratacion.getTipoContratacion()
                + ", proyecto=" + p + ", dependencia=" + d
                + ", unidadResponsable=" + u + ", puesto=" + puesto.getCodigo()
                + "-" + puesto.getPuesto() + ", fuenteFinanciamiento=" + f
                + ", subfuenteFinanciamiento=" + sub + ", sueldo=" + sueldo
                + ", sueldo01=" + sueldo01 + ", sueldo14=" + sueldo14
                + ", tabulador=" + ta + ", fechaInicioLabores="
                + fechaInicioLabores + "]";
    }

    public String lccSueldo() {
        return "Sueldo [sueldo=" + sueldo + ", sueldo01=" + sueldo01
                + ", sueldo14=" + sueldo14 + "]";
    }

    public Integer getIdJornada() {
        return idJornada;
    }

    public void setIdJornada(Integer idJornada) {
        this.idJornada = idJornada;
    }

    public Integer getIdRiesgo() {
        return idRiesgo;
    }

    public void setIdRiesgo(Integer idRiesgo) {
        this.idRiesgo = idRiesgo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumeroEmpleado() {
        return numeroEmpleado;
    }

    public void setNumeroEmpleado(Integer numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }

    public ProyectoTempEntity getProyecto() {
        return proyecto;
    }

    public void setProyecto(ProyectoTempEntity proyecto) {
        this.proyecto = proyecto;
    }

    public DependenciaTempEntity getDependencia() {
        return dependencia;
    }

    public void setDependencia(DependenciaTempEntity dependencia) {
        this.dependencia = dependencia;
    }

    public UnidadResponsableEntity getUnidadResponsable() {
        return unidadResponsable;
    }

    public void setUnidadResponsable(
            UnidadResponsableEntity unidadResponsable) {
        this.unidadResponsable = unidadResponsable;
    }

    public TipoContratacionEntity getTipoContratacion() {
        return tipoContratacion;
    }

    public void setTipoContratacion(TipoContratacionEntity tipoContratacion) {
        this.tipoContratacion = tipoContratacion;
    }

    public TiposNombramientosEntity getNombramiento() {
        return nombramiento;
    }

    public void setNombramiento(TiposNombramientosEntity nombramiento) {
        this.nombramiento = nombramiento;
    }

    public PuestoGeneralEntity getPuesto() {
        return puesto;
    }

    public void setPuesto(PuestoGeneralEntity puesto) {
        this.puesto = puesto;
    }

    public FuenteFinanciamientoEntity getFuenteFinanciamiento() {
        return fuenteFinanciamiento;
    }

    public void setFuenteFinanciamiento(
            FuenteFinanciamientoEntity fuenteFinanciamiento) {
        this.fuenteFinanciamiento = fuenteFinanciamiento;
    }

    public SubFuenteFinanciamientoTempEntity getSubfuenteFinanciamiento() {
        return subfuenteFinanciamiento;
    }

    public void setSubfuenteFinanciamiento(
            SubFuenteFinanciamientoTempEntity subfuenteFinanciamiento) {
        this.subfuenteFinanciamiento = subfuenteFinanciamiento;
    }

    public TipoRecursoTempEntity getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(TipoRecursoTempEntity tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    public EmpleadoEntity getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoEntity empleado) {
        this.empleado = empleado;
    }

    public Date getFechaAltaConfiguracion() {
        return fechaAltaConfiguracion;
    }

    public void setFechaAltaConfiguracion(Date fechaAltaConfiguracion) {
        this.fechaAltaConfiguracion = fechaAltaConfiguracion;
    }

    public EstatusConfiguracionesEntity getEstatus() {
        return estatus;
    }

    public void setEstatus(EstatusConfiguracionesEntity estatus) {
        this.estatus = estatus;
    }

    public BigDecimal getSueldo() {
        return sueldo;
    }

    public void setSueldo(BigDecimal sueldo) {
        this.sueldo = sueldo;
    }

    public BigDecimal getSueldo01() {
        return sueldo01;
    }

    public void setSueldo01(BigDecimal sueldo01) {
        this.sueldo01 = sueldo01;
    }

    public BigDecimal getSueldo14() {
        return sueldo14;
    }

    public void setSueldo14(BigDecimal sueldo14) {
        this.sueldo14 = sueldo14;
    }

    public CuentasBancariasEntity getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentasBancariasEntity cuenta) {
        this.cuenta = cuenta;
    }

    public TabuladorEntity getTabulador() {
        return tabulador;
    }

    public void setTabulador(TabuladorEntity tabulador) {
        this.tabulador = tabulador;
    }

    public String getIdPlaza() {
        return idPlaza;
    }

    public void setIdPlaza(String idPlaza) {
        this.idPlaza = idPlaza;
    }

    public CentroResponsabilidadEntity getCentroResponsabilidad() {
        return centroResponsabilidad;
    }

    public void setCentroResponsabilidad(
            CentroResponsabilidadEntity centroResponsabilidad) {
        this.centroResponsabilidad = centroResponsabilidad;
    }

    public Date getFechaInicioLabores() {
        return fechaInicioLabores;
    }

    public void setFechaInicioLabores(Date fechaInicioLabores) {
        this.fechaInicioLabores = fechaInicioLabores;
    }

    public Boolean getAplicaPrimerPago() {
        return aplicaPrimerPago;
    }

    public void setAplicaPrimerPago(Boolean aplicaPrimerPago) {
        this.aplicaPrimerPago = aplicaPrimerPago;
    }

    public Integer getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(Integer tipoPago) {
        this.tipoPago = tipoPago;
    }

}