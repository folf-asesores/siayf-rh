
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
@Table(name = "productos_nomina")
public class ProductoNominaEntity implements Serializable {

    private static final long serialVersionUID = -8963701888867064010L;

    @Id
    @Column(name = "id_producto_nomina")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProductoNomina;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_periodo")
    private TipoPeriodoEntity tipoPeriodo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_periodo_calendario")
    private PeriodoCalendariosEntity periodoCalendario;

    @JoinColumn(name = "id_usuario", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private UsuarioEntity usuario;

    @JoinColumn(name = "id_ejercicio_fiscal", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private EjercicioFiscalEntity ejercicioFiscal;

    @JoinColumn(name = "id_estatus_producto_nomina", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private EstatusProductoNominaEntity estatusProductoNomina;

    @JoinColumn(name = "id_banco")
    @ManyToOne(fetch = FetchType.LAZY)
    private BancoSatEntity banco;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cuenta_bancaria")
    private CuentasBancariasEntity cuentaBancaria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_contratacion")
    private TipoContratacionEntity tipoContratacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_fuente_financiamiento")
    private FuenteFinanciamientoEntity fuenteFinanciamiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_subfuente_financiamiento")
    private SubFuenteFinanciamientoTempEntity subfuenteFinanciamiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_nomina")
    private TipoNominaEntity tipoNomina;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_recurso_sat")
    private TiposRecursosSatEntity tipoRecursoSat;

    @Column(name = "cambiar_subfuente_financiamiento")
    private Boolean cambiarSubfuenteFinanciamiento;

    @Column(name = "ejercicio_fiscal")
    private Integer anyoEjercicioFiscal;

    @Column(name = "inicio_periodo")
    @Temporal(TemporalType.DATE)
    private Date inicioPeriodo;

    @Column(name = "fin_periodo")
    @Temporal(TemporalType.DATE)
    private Date finPeriodo;

    @Column(name = "numero_periodo")
    private Integer numeroPeriodo;

    @Column(name = "fecha_pago")
    @Temporal(TemporalType.DATE)
    private Date fechaPago;

    @Column(name = "nombre_producto")
    private String nombreProducto;

    @Column(name = "numero_inicio_cheques")
    private Integer numeroInicioCheques;

    @Column(name = "total_percepciones")
    private BigDecimal totalPercepciones;

    @Column(name = "total_deducciones")
    private BigDecimal totalDeducciones;

    @Column(name = "total_neto")
    private BigDecimal totalNeto;

    @Column(name = "total_subsidio")
    private BigDecimal totalSubsidio;

    @Column(name = "total_isr")
    private BigDecimal totalIsr;

    @Column(name = "inicio_rango_faltas")
    @Temporal(TemporalType.DATE)
    private Date inicioRangoFaltas;

    @Column(name = "fin_rango_faltas")
    @Temporal(TemporalType.DATE)
    private Date finRangoFaltas;
    @Column(name = "dias_prima_vacasional")
    private BigDecimal diasPrimaVacasional;
    @Column(name = "dias_aguinaldo")
    private BigDecimal diasAguinaldo;
    @Column(name = "dias_exento_prima_vacasional")
    private BigDecimal diasExentoPrimaVacasional;
    @Column(name = "dias_exento_aguinaldo")
    private BigDecimal diasExentoAguinaldo;

    public Integer getIdProductoNomina() {
        return idProductoNomina;
    }

    public void setIdProductoNomina(Integer idProductoNomina) {
        this.idProductoNomina = idProductoNomina;
    }

    public TipoPeriodoEntity getTipoPeriodo() {
        return tipoPeriodo;
    }

    public void setTipoPeriodo(TipoPeriodoEntity tipoPeriodo) {
        this.tipoPeriodo = tipoPeriodo;
    }

    public PeriodoCalendariosEntity getPeriodoCalendario() {
        return periodoCalendario;
    }

    public void setPeriodoCalendario(
            PeriodoCalendariosEntity periodoCalendario) {
        this.periodoCalendario = periodoCalendario;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public EjercicioFiscalEntity getEjercicioFiscal() {
        return ejercicioFiscal;
    }

    public void setEjercicioFiscal(EjercicioFiscalEntity ejercicioFiscal) {
        this.ejercicioFiscal = ejercicioFiscal;
    }

    public EstatusProductoNominaEntity getEstatusProductoNomina() {
        return estatusProductoNomina;
    }

    public void setEstatusProductoNomina(
            EstatusProductoNominaEntity estatusProductoNomina) {
        this.estatusProductoNomina = estatusProductoNomina;
    }

    public BancoSatEntity getBanco() {
        return banco;
    }

    public void setBanco(BancoSatEntity banco) {
        this.banco = banco;
    }

    public CuentasBancariasEntity getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(CuentasBancariasEntity cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    public TipoContratacionEntity getTipoContratacion() {
        return tipoContratacion;
    }

    public void setTipoContratacion(TipoContratacionEntity tipoContratacion) {
        this.tipoContratacion = tipoContratacion;
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

    public TipoNominaEntity getTipoNomina() {
        return tipoNomina;
    }

    public void setTipoNomina(TipoNominaEntity tipoNomina) {
        this.tipoNomina = tipoNomina;
    }

    public Boolean getCambiarSubfuenteFinanciamiento() {
        return cambiarSubfuenteFinanciamiento;
    }

    public void setCambiarSubfuenteFinanciamiento(
            Boolean cambiarSubfuenteFinanciamiento) {
        this.cambiarSubfuenteFinanciamiento = cambiarSubfuenteFinanciamiento;
    }

    public Integer getAnyoEjercicioFiscal() {
        return anyoEjercicioFiscal;
    }

    public void setAnyoEjercicioFiscal(Integer anyoEjercicioFiscal) {
        this.anyoEjercicioFiscal = anyoEjercicioFiscal;
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

    public Integer getNumeroPeriodo() {
        return numeroPeriodo;
    }

    public void setNumeroPeriodo(Integer numeroPeriodo) {
        this.numeroPeriodo = numeroPeriodo;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Integer getNumeroInicioCheques() {
        return numeroInicioCheques;
    }

    public void setNumeroInicioCheques(Integer numeroInicioCheques) {
        this.numeroInicioCheques = numeroInicioCheques;
    }

    public BigDecimal getTotalPercepciones() {
        return totalPercepciones;
    }

    public void setTotalPercepciones(BigDecimal totalPercepciones) {
        this.totalPercepciones = totalPercepciones;
    }

    public BigDecimal getTotalDeducciones() {
        return totalDeducciones;
    }

    public void setTotalDeducciones(BigDecimal totalDeducciones) {
        this.totalDeducciones = totalDeducciones;
    }

    public BigDecimal getTotalNeto() {
        return totalNeto;
    }

    public void setTotalNeto(BigDecimal totalNeto) {
        this.totalNeto = totalNeto;
    }

    public BigDecimal getTotalSubsidio() {
        return totalSubsidio;
    }

    public void setTotalSubsidio(BigDecimal totalSubsidio) {
        this.totalSubsidio = totalSubsidio;
    }

    public BigDecimal getTotalIsr() {
        return totalIsr;
    }

    public void setTotalIsr(BigDecimal totalIsr) {
        this.totalIsr = totalIsr;
    }

    public TiposRecursosSatEntity getTipoRecursoSat() {
        return tipoRecursoSat;
    }

    public void setTipoRecursoSat(TiposRecursosSatEntity tipoRecursoSat) {
        this.tipoRecursoSat = tipoRecursoSat;
    }

    public Date getInicioRangoFaltas() {
        return inicioRangoFaltas;
    }

    public void setInicioRangoFaltas(Date inicioRangoFaltas) {
        this.inicioRangoFaltas = inicioRangoFaltas;
    }

    public Date getFinRangoFaltas() {
        return finRangoFaltas;
    }

    public void setFinRangoFaltas(Date finRangoFaltas) {
        this.finRangoFaltas = finRangoFaltas;
    }

    public BigDecimal getDiasPrimaVacasional() {
        return diasPrimaVacasional;
    }

    public void setDiasPrimaVacasional(BigDecimal diasPrimaVacasional) {
        this.diasPrimaVacasional = diasPrimaVacasional;
    }

    public BigDecimal getDiasAguinaldo() {
        return diasAguinaldo;
    }

    public void setDiasAguinaldo(BigDecimal diasAguinaldo) {
        this.diasAguinaldo = diasAguinaldo;
    }

    public BigDecimal getDiasExentoPrimaVacasional() {
        return diasExentoPrimaVacasional;
    }

    public void setDiasExentoPrimaVacasional(
            BigDecimal diasExentoPrimaVacasional) {
        this.diasExentoPrimaVacasional = diasExentoPrimaVacasional;
    }

    public BigDecimal getDiasExentoAguinaldo() {
        return diasExentoAguinaldo;
    }

    public void setDiasExentoAguinaldo(BigDecimal diasExentoAguinaldo) {
        this.diasExentoAguinaldo = diasExentoAguinaldo;
    }
}