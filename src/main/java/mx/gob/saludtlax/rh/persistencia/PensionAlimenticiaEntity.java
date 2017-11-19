
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

@Entity
@Table(name = "pensiones_alimenticias")
public class PensionAlimenticiaEntity implements Serializable {
    private static final long serialVersionUID = -7990337607707111792L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pension_alimenticia")
    private int idPensionAlimenticia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empleado")
    private EmpleadoEntity empleado;

    @Column(name = "rfc")
    private String rfc;

    @Column(name = "beneficiario")
    private String beneficiario;

    @Column(name = "oficio")
    private String oficio;

    @Column(name = "numero_expediente")
    private String numeroExpediente;

    @Column(name = "numero_juzgado")
    private String numeroJuzgado;

    @Column(name = "fecha_alta")
    @Temporal(TemporalType.DATE)
    private Date fechaAlta;

    @Column(name = "fecha_baja")
    @Temporal(TemporalType.DATE)
    private Date fechaBaja;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_coutas_pension_alimenticia")
    private TipoCoutaPensionAlimenticiaEntity tipoCoutaAlimenticia;

    @Column(name = "estatus")
    private int estatus;

    @Column(name = "numero_cuenta_bancaria")
    private String numeroCuentaBancaria;

    @Column(name = "valor")
    private BigDecimal valor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_banco")
    private BancoSatEntity banco;

    public int getIdPensionAlimenticia() {
        return idPensionAlimenticia;
    }

    public void setIdPensionAlimenticia(int idPensionAlimenticia) {
        this.idPensionAlimenticia = idPensionAlimenticia;
    }

    public EmpleadoEntity getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoEntity empleado) {
        this.empleado = empleado;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(String beneficiario) {
        this.beneficiario = beneficiario;
    }

    public String getOficio() {
        return oficio;
    }

    public void setOficio(String oficio) {
        this.oficio = oficio;
    }

    public String getNumeroExpediente() {
        return numeroExpediente;
    }

    public void setNumeroExpediente(String numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

    public String getNumeroJuzgado() {
        return numeroJuzgado;
    }

    public void setNumeroJuzgado(String numeroJuzgado) {
        this.numeroJuzgado = numeroJuzgado;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public TipoCoutaPensionAlimenticiaEntity getTipoCoutaAlimenticia() {
        return tipoCoutaAlimenticia;
    }

    public void setTipoCoutaAlimenticia(TipoCoutaPensionAlimenticiaEntity tipoCoutaAlimenticia) {
        this.tipoCoutaAlimenticia = tipoCoutaAlimenticia;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getNumeroCuentaBancaria() {
        return numeroCuentaBancaria;
    }

    public void setNumeroCuentaBancaria(String numeroCuentaBancaria) {
        this.numeroCuentaBancaria = numeroCuentaBancaria;
    }

    public BancoSatEntity getBanco() {
        return banco;
    }

    public void setBanco(BancoSatEntity banco) {
        this.banco = banco;
    }
}