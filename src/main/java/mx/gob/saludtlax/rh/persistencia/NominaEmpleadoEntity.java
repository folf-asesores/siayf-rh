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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author José Pablo
 *
 */
@Entity
@Table(name = "nomina_empleado")
public class NominaEmpleadoEntity implements Serializable {

    private static final long serialVersionUID = 1922121718009688924L;

    @Id
    @Column(name = "id_nomina_empleado")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idNominaEmpleado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto_nomina")
    private ProductoNominaEntity idProductoNomina;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empleado")
    private EmpleadoEntity idEmpleado;

    @Column(name = "percepciones")
    private BigDecimal percepciones;

    @Column(name = "deducciones")
    private BigDecimal deducciones;

    @Column(name = "neto")
    private BigDecimal neto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_proyecto")
    private ProyectoTempEntity idProyecto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_dependencia")
    private DependenciaTempEntity idDependencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_unidad_responsable")
    private UnidadResponsableEntity idUnidadResponsable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_contratacion")
    private TipoContratacionEntity idTipoContratacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_nombramiento")
    private TiposNombramientosEntity idTipoNombramiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_puesto_general")
    private PuestoGeneralEntity idPuestoGeneral;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_fuente_financiamiento")
    private FuenteFinanciamientoEntity idFuenteFinanciamiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_subfuente_financiamiento")
    private SubfuenteFinanciamientoEntity idSubfuenteFinanciamiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_recurso")
    private TipoRecursoTempEntity idTipoRecurso;

    @Column(name = "numero_empleado")
    private Integer numeroEmpleado;

    @Column(name = "fecha_alta_configuracion")
    @Temporal(TemporalType.DATE)
    private Date fechaAltaConfiguracion;

    @Column(name = "sueldo")
    private BigDecimal sueldo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tabulador")
    private TabuladorEntity idTabulador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_configuracion_presupuestal")
    private ConfiguracionPresupuestoEntity idConfiguracionPresupuestal;

    @Column(name = "numero_cuenta")
    private String numeroCuenta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cuenta_bancaria")
    private CuentasBancariasEntity idCuentaBancaria;

    @Column(name = "id_metodo_pago")
    private Integer idMetodoPago;

    @Column(name = "inicio_periodo")
    @Temporal(TemporalType.DATE)
    private Date inicioPeriodo;

    @Column(name = "fin_periodo")
    @Temporal(TemporalType.DATE)
    private Date finPeriodo;

    @Column(name = "fecha_pago")
    @Temporal(TemporalType.DATE)
    private Date fechaPago;

    @Column(name = "numero_dias_pagados")
    private Integer numeroDiasPagados;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_centro_responsabilidad")
    private CentroResponsabilidadEntity idCentroResponsabilidad;

    @Column(name = "consecutivo")
    private Integer consecutivo;

    @Column(name = "numero_cheque")
    private Integer numeroCheque;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estatus_nomina_empleado")
    private EstatusNominasEmpleadoEntity idEstatusNominaEmpleado;

    @Column(name = "numero_id_personal")
    private Integer numeroIdPersonal;

    @Column(name = "numero_id_laboral")
    private Integer numeroIdLaboral;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_funcion")
    private FuncionEntity idFuncion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_programa")
    private ProgramaEntity programa;

    @Column(name = "calculado")
    private boolean calculado;

    @Column(name = "motivo")
    private String motivo;

    @Column(name = "id_comprobante")
    private Integer idComprobante;

    public Integer getIdComprobante() {
        return idComprobante;
    }

    public void setIdComprobante(Integer idComprobante) {
        this.idComprobante = idComprobante;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public boolean isCalculado() {
        return calculado;
    }

    public void setCalculado(boolean calculado) {
        this.calculado = calculado;
    }

    public Integer getIdNominaEmpleado() {
        return idNominaEmpleado;
    }

    public void setIdNominaEmpleado(Integer idNominaEmpleado) {
        this.idNominaEmpleado = idNominaEmpleado;
    }

    public ProductoNominaEntity getIdProductoNomina() {
        return idProductoNomina;
    }

    public void setIdProductoNomina(ProductoNominaEntity idProductoNomina) {
        this.idProductoNomina = idProductoNomina;
    }

    public EmpleadoEntity getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(EmpleadoEntity idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public BigDecimal getPercepciones() {
        return percepciones;
    }

    public void setPercepciones(BigDecimal percepciones) {
        this.percepciones = percepciones;
    }

    public BigDecimal getDeducciones() {
        return deducciones;
    }

    public void setDeducciones(BigDecimal deducciones) {
        this.deducciones = deducciones;
    }

    public BigDecimal getNeto() {
        return neto;
    }

    public void setNeto(BigDecimal neto) {
        this.neto = neto;
    }

    public ProyectoTempEntity getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(ProyectoTempEntity idProyecto) {
        this.idProyecto = idProyecto;
    }

    public DependenciaTempEntity getIdDependencia() {
        return idDependencia;
    }

    public void setIdDependencia(DependenciaTempEntity idDependencia) {
        this.idDependencia = idDependencia;
    }

    public UnidadResponsableEntity getIdUnidadResponsable() {
        return idUnidadResponsable;
    }

    public void setIdUnidadResponsable(UnidadResponsableEntity idUnidadResponsable) {
        this.idUnidadResponsable = idUnidadResponsable;
    }

    public TipoContratacionEntity getIdTipoContratacion() {
        return idTipoContratacion;
    }

    public void setIdTipoContratacion(TipoContratacionEntity idTipoContratacion) {
        this.idTipoContratacion = idTipoContratacion;
    }

    public TiposNombramientosEntity getIdTipoNombramiento() {
        return idTipoNombramiento;
    }

    public void setIdTipoNombramiento(TiposNombramientosEntity idTipoNombramiento) {
        this.idTipoNombramiento = idTipoNombramiento;
    }

    public PuestoGeneralEntity getIdPuestoGeneral() {
        return idPuestoGeneral;
    }

    public void setIdPuestoGeneral(PuestoGeneralEntity idPuestoGeneral) {
        this.idPuestoGeneral = idPuestoGeneral;
    }

    public FuenteFinanciamientoEntity getIdFuenteFinanciamiento() {
        return idFuenteFinanciamiento;
    }

    public void setIdFuenteFinanciamiento(FuenteFinanciamientoEntity idFuenteFinanciamiento) {
        this.idFuenteFinanciamiento = idFuenteFinanciamiento;
    }

    public SubfuenteFinanciamientoEntity getIdSubfuenteFinanciamiento() {
        return idSubfuenteFinanciamiento;
    }

    public void setIdSubfuenteFinanciamiento(SubfuenteFinanciamientoEntity idSubfuenteFinanciamiento) {
        this.idSubfuenteFinanciamiento = idSubfuenteFinanciamiento;
    }

    public TipoRecursoTempEntity getIdTipoRecurso() {
        return idTipoRecurso;
    }

    public void setIdTipoRecurso(TipoRecursoTempEntity idTipoRecurso) {
        this.idTipoRecurso = idTipoRecurso;
    }

    public Integer getNumeroEmpleado() {
        return numeroEmpleado;
    }

    public void setNumeroEmpleado(Integer numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }

    public Date getFechaAltaConfiguracion() {
        return fechaAltaConfiguracion;
    }

    public void setFechaAltaConfiguracion(Date fechaAltaConfiguracion) {
        this.fechaAltaConfiguracion = fechaAltaConfiguracion;
    }

    public BigDecimal getSueldo() {
        return sueldo;
    }

    public void setSueldo(BigDecimal sueldo) {
        this.sueldo = sueldo;
    }

    public TabuladorEntity getIdTabulador() {
        return idTabulador;
    }

    public void setIdTabulador(TabuladorEntity idTabulador) {
        this.idTabulador = idTabulador;
    }

    public ConfiguracionPresupuestoEntity getIdConfiguracionPresupuestal() {
        return idConfiguracionPresupuestal;
    }

    public void setIdConfiguracionPresupuestal(ConfiguracionPresupuestoEntity idConfiguracionPresupuestal) {
        this.idConfiguracionPresupuestal = idConfiguracionPresupuestal;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public CuentasBancariasEntity getIdCuentaBancaria() {
        return idCuentaBancaria;
    }

    public void setIdCuentaBancaria(CuentasBancariasEntity idCuentaBancaria) {
        this.idCuentaBancaria = idCuentaBancaria;
    }

    public Integer getIdMetodoPago() {
        return idMetodoPago;
    }

    public void setIdMetodoPago(Integer idMetodoPago) {
        this.idMetodoPago = idMetodoPago;
    }

    public Date getInicioPeriodo() {
        return inicioPeriodo;
    }

    public void setInicioPeriodo(Date inicioPeriodo) {
        this.inicioPeriodo = inicioPeriodo;
    }

    public Date getFinPeriodo() {
        return finPeriodo;
    }

    public void setFinPeriodo(Date finPeriodo) {
        this.finPeriodo = finPeriodo;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Integer getNumeroDiasPagados() {
        return numeroDiasPagados;
    }

    public void setNumeroDiasPagados(Integer numeroDiasPagados) {
        this.numeroDiasPagados = numeroDiasPagados;
    }

    public CentroResponsabilidadEntity getIdCentroResponsabilidad() {
        return idCentroResponsabilidad;
    }

    public void setIdCentroResponsabilidad(CentroResponsabilidadEntity idCentroResponsabilidad) {
        this.idCentroResponsabilidad = idCentroResponsabilidad;
    }

    public Integer getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(Integer consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Integer getNumeroCheque() {
        return numeroCheque;
    }

    public void setNumeroCheque(Integer numeroCheque) {
        this.numeroCheque = numeroCheque;
    }

    public EstatusNominasEmpleadoEntity getIdEstatusNominaEmpleado() {
        return idEstatusNominaEmpleado;
    }

    public void setIdEstatusNominaEmpleado(EstatusNominasEmpleadoEntity idEstatusNominaEmpleado) {
        this.idEstatusNominaEmpleado = idEstatusNominaEmpleado;
    }

    public Integer getNumeroIdPersonal() {
        return numeroIdPersonal;
    }

    public void setNumeroIdPersonal(Integer numeroIdPersonal) {
        this.numeroIdPersonal = numeroIdPersonal;
    }

    public Integer getNumeroIdLaboral() {
        return numeroIdLaboral;
    }

    public void setNumeroIdLaboral(Integer numeroIdLaboral) {
        this.numeroIdLaboral = numeroIdLaboral;
    }

    public FuncionEntity getIdFuncion() {
        return idFuncion;
    }

    public void setIdFuncion(FuncionEntity idFuncion) {
        this.idFuncion = idFuncion;
    }

    public ProgramaEntity getPrograma() {
        return programa;
    }

    public void setPrograma(ProgramaEntity programa) {
        this.programa = programa;
    }
}
