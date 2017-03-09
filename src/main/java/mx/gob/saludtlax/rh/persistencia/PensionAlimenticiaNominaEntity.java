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
@Table(name="pensiones_alimenticias_nominas")
public class PensionAlimenticiaNominaEntity implements Serializable{
	private static final long serialVersionUID = -7990337607707111792L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_pension_alimenticia_nomina")
	private int idPensionAlimenticiaNomina;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pension_alimenticia")
    private PensionAlimenticiaEntity pensionAlimenticia;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name="id_nomina_empleado")
    private NominaEmpleadoEntity nominaEmpleado;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name="id_producto_nomina")
    private ProductoNominaEntity productoNomina;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name="id_empleado")
    private EmpleadoEntity empleado;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name="id_configuracion_presupuestal")
    private ConfiguracionPresupuestoEntity configuracionPresupuesto;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name="id_banco")
    private BancoSatEntity banco;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name="id_metodo_pago")
    private MetodoPagoEntity metodoPago;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_centro_responsabilidad")
    private CentroResponsabilidadEntity centroResponsabilidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estatus_nomina_empleado")
    private EstatusNominasEmpleadoEntity estatusNominaEmpleado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_programa")
    private ProgramaEntity programa;

    @Column(name="monto")
    private BigDecimal monto;

	@Column (name = "inicio_periodo")
    private Date inicioPeriodo;

    @Column (name = "fin_periodo")
    private Date finPeriodo; 

    @Column (name = "fecha_pago")
    private Date fechaPago;

    @Column (name = "consecutivo")
    private Integer consecutivo;

    @Column (name = "numero_cheque")
    private Integer numeroCheque;

    @Column (name = "numero_cuenta")
    private String numeroCuenta;

    public int getIdPensionAlimenticiaNomina() {
        return idPensionAlimenticiaNomina;
    }
    public void setIdPensionAlimenticiaNomina(int idPensionAlimenticiaNomina) {
        this.idPensionAlimenticiaNomina = idPensionAlimenticiaNomina;
    }
    public PensionAlimenticiaEntity getPensionAlimenticia() {
        return pensionAlimenticia;
    }
    public void setPensionAlimenticia(PensionAlimenticiaEntity pensionAlimenticia) {
        this.pensionAlimenticia = pensionAlimenticia;
    }
    public NominaEmpleadoEntity getNominaEmpleado() {
        return nominaEmpleado;
    }
    public void setNominaEmpleado(NominaEmpleadoEntity nominaEmpleado) {
        this.nominaEmpleado = nominaEmpleado;
    }
    public ProductoNominaEntity getProductoNomina() {
        return productoNomina;
    }
    public void setProductoNomina(ProductoNominaEntity productoNomina) {
        this.productoNomina = productoNomina;
    }
    public EmpleadoEntity getEmpleado() {
        return empleado;
    }
    public void setEmpleado(EmpleadoEntity empleado) {
        this.empleado = empleado;
    }
    public ConfiguracionPresupuestoEntity getConfiguracionPresupuesto() {
        return configuracionPresupuesto;
    }
    public void setConfiguracionPresupuesto(ConfiguracionPresupuestoEntity configuracionPresupuesto) {
        this.configuracionPresupuesto = configuracionPresupuesto;
    }
    public BancoSatEntity getBanco() {
        return banco;
    }
    public void setBanco(BancoSatEntity banco) {
        this.banco = banco;
    }
    public MetodoPagoEntity getMetodoPago() {
        return metodoPago;
    }
    public void setMetodoPago(MetodoPagoEntity metodoPago) {
        this.metodoPago = metodoPago;
    }
    public CentroResponsabilidadEntity getCentroResponsabilidad() {
        return centroResponsabilidad;
    }
    public void setCentroResponsabilidad(CentroResponsabilidadEntity centroResponsabilidad) {
        this.centroResponsabilidad = centroResponsabilidad;
    }
    public EstatusNominasEmpleadoEntity getEstatusNominaEmpleado() {
        return estatusNominaEmpleado;
    }
    public void setEstatusNominaEmpleado(EstatusNominasEmpleadoEntity estatusNominaEmpleado) {
        this.estatusNominaEmpleado = estatusNominaEmpleado;
    }
    public ProgramaEntity getPrograma() {
        return programa;
    }
    public void setPrograma(ProgramaEntity programa) {
        this.programa = programa;
    }
    public BigDecimal getMonto() {
        return monto;
    }
    public void setMonto(BigDecimal monto) {
        this.monto = monto;
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
    public String getNumeroCuenta() {
        return numeroCuenta;
    }
    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }
}