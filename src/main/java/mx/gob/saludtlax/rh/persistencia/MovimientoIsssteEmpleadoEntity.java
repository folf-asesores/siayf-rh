/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

import javax.persistence.Basic;
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
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 *
 */
@Entity
@Table(name = "movimientos_issste_empleado")
public class MovimientoIsssteEmpleadoEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5390513634318430753L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimiento_issste")
    private Integer idMovimientoIsssteEmpleado;

    @JoinColumn(name = "id_empleado", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private EmpleadoEntity empleado;

    @JoinColumn(name = "id_causa_baja", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private CausaBajaIsssteEntity causaBaja;

    @JoinColumn(name = "id_usuario", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private UsuarioEntity usuario;

    @JoinColumn(name = "id_tipo_movimiento_issste", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoMovimientoIsssteEntity tipoMovimientoIssste;

    @Column(name = "sueldo_issste")
    private BigDecimal sueldoIssste;

    @Column(name = "sueldo_sar")
    private BigDecimal sueldoSar;

    @Column(name = "total_remuneracion")
    private BigDecimal totalRemuneracion;

    @Column(name = "nivel_salarial")
    private String nivelSalario;

    @Column(name = "nombramiento")
    private String nombramiento;

    @Basic(optional = false)
    @Column(name = "fecha_movimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaMovimiento;

    @Basic(optional = false)
    @Column(name = "hora_movimiento")
    private Time horaMovimiento;

    public Integer getIdMovimientoIsssteEmpleado() {
        return idMovimientoIsssteEmpleado;
    }

    public void setIdMovimientoIsssteEmpleado(
            Integer idMovimientoIsssteEmpleado) {
        this.idMovimientoIsssteEmpleado = idMovimientoIsssteEmpleado;
    }

    public BigDecimal getSueldoIssste() {
        return sueldoIssste;
    }

    public void setSueldoIssste(BigDecimal sueldoIssste) {
        this.sueldoIssste = sueldoIssste;
    }

    public BigDecimal getSueldoSar() {
        return sueldoSar;
    }

    public void setSueldoSar(BigDecimal sueldoSar) {
        this.sueldoSar = sueldoSar;
    }

    public BigDecimal getTotalRemuneracion() {
        return totalRemuneracion;
    }

    public void setTotalRemuneracion(BigDecimal totalRemuneracion) {
        this.totalRemuneracion = totalRemuneracion;
    }

    public String getNivelSalario() {
        return nivelSalario;
    }

    public void setNivelSalario(String nivelSalario) {
        this.nivelSalario = nivelSalario;
    }

    public String getNombramiento() {
        return nombramiento;
    }

    public void setNombramiento(String nombramiento) {
        this.nombramiento = nombramiento;
    }

    public Date getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public Time getHoraMovimiento() {
        return horaMovimiento;
    }

    public void setHoraMovimiento(Time horaMovimiento) {
        this.horaMovimiento = horaMovimiento;
    }

    public EmpleadoEntity getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoEntity empleado) {
        this.empleado = empleado;
    }

    public CausaBajaIsssteEntity getCausaBaja() {
        return causaBaja;
    }

    public void setCausaBaja(CausaBajaIsssteEntity causaBaja) {
        this.causaBaja = causaBaja;
    }

    public TipoMovimientoIsssteEntity getTipoMovimientoIssste() {
        return tipoMovimientoIssste;
    }

    public void setTipoMovimientoIssste(
            TipoMovimientoIsssteEntity tipoMovimientoIssste) {
        this.tipoMovimientoIssste = tipoMovimientoIssste;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

}
