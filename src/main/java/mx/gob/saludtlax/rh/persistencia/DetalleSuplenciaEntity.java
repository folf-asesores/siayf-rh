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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 30/10/2016 15:17:12
 */
@Entity
@Table(name = "detalles_suplencias")
public class DetalleSuplenciaEntity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 2428012349285200117L;
    @Id
    @Column(name = "id_detalle_suplencia")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDetalleSuplencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empleado")
    private EmpleadoEntity empleado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_causa_suplencia")
    private TipoSuplenciaEntity tipoSuplencia;

    @Column(name = "fecha_inicio")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaInicio;

    @Column(name = "fecha_fin")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaFin;

    @Column(name = "dias")
    private int dias;

    @Column(name = "cantidad_diaria")
    private BigDecimal cantidadDiaria;

    @Column(name = "total")
    private BigDecimal total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_movimiento_empleado")
    private MovimientoEmpleadoEntity movimientoEmpleado;

    @Column(name = "estatus")
    private String estatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_quincena_suplencia")
    private QuincenasSuplenciasEntity quincena;

    @Column(name = "modificada")
    private boolean modificada;

    @Column(name = "fecha_modificacion")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaModifacion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "hora_modificacion")
    private Date horaModificacion;

    @Column(name = "fecha_alta")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaAlta;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "hora_alta")
    private Date horaAlta;

    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "total_anterior")
    private BigDecimal totalAnterior;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario_alta")
    private UsuarioEntity usuarioAlta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario_modificacion")
    private UsuarioEntity usuarioModificacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_jornada")
    private TipoJornadaSuplenciaEntity jornada;

    @Column(name = "id_tabulador")
    private Integer idTabulador;

    public Integer getIdTabulador() {
        return idTabulador;
    }

    public void setIdTabulador(Integer idTabulador) {
        this.idTabulador = idTabulador;
    }

    public TipoJornadaSuplenciaEntity getJornada() {
        return jornada;
    }

    public void setJornada(TipoJornadaSuplenciaEntity jornada) {
        this.jornada = jornada;
    }

    public boolean isModificada() {
        return modificada;
    }

    public void setModificada(boolean modificada) {
        this.modificada = modificada;
    }

    public Date getFechaModifacion() {
        return fechaModifacion;
    }

    public void setFechaModifacion(Date fechaModifacion) {
        this.fechaModifacion = fechaModifacion;
    }

    public Date getHoraModificacion() {
        return horaModificacion;
    }

    public void setHoraModificacion(Date horaModificacion) {
        this.horaModificacion = horaModificacion;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getHoraAlta() {
        return horaAlta;
    }

    public void setHoraAlta(Date horaAlta) {
        this.horaAlta = horaAlta;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public BigDecimal getTotalAnterior() {
        return totalAnterior;
    }

    public void setTotalAnterior(BigDecimal totalAnterior) {
        this.totalAnterior = totalAnterior;
    }

    public UsuarioEntity getUsuarioAlta() {
        return usuarioAlta;
    }

    public void setUsuarioAlta(UsuarioEntity usuarioAlta) {
        this.usuarioAlta = usuarioAlta;
    }

    public UsuarioEntity getUsuarioModificacion() {
        return usuarioModificacion;
    }

    public void setUsuarioModificacion(UsuarioEntity usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

    public QuincenasSuplenciasEntity getQuincena() {
        return quincena;
    }

    public void setQuincena(QuincenasSuplenciasEntity quincena) {
        this.quincena = quincena;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public void setIdDetalleSuplencia(Integer idDetalleSuplencia) {
        this.idDetalleSuplencia = idDetalleSuplencia;
    }

    public EmpleadoEntity getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoEntity empleado) {
        this.empleado = empleado;
    }

    public TipoSuplenciaEntity getTipoSuplencia() {
        return tipoSuplencia;
    }

    public void setTipoSuplencia(TipoSuplenciaEntity tipoSuplencia) {
        this.tipoSuplencia = tipoSuplencia;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public BigDecimal getCantidadDiaria() {
        return cantidadDiaria;
    }

    public void setCantidadDiaria(BigDecimal cantidadDiaria) {
        this.cantidadDiaria = cantidadDiaria;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public MovimientoEmpleadoEntity getMovimientoEmpleado() {
        return movimientoEmpleado;
    }

    public void setMovimientoEmpleado(MovimientoEmpleadoEntity movimientoEmpleado) {
        this.movimientoEmpleado = movimientoEmpleado;
    }

    public Integer getIdDetalleSuplencia() {
        return idDetalleSuplencia;
    }

}
