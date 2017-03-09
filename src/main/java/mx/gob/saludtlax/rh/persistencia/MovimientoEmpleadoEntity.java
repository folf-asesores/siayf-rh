/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
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
 * @since 03/05/2016 03/05/2016
 */
@Entity
@Table(name = "movimientos_empleado")
public class MovimientoEmpleadoEntity implements Serializable {

	private static final long serialVersionUID = -8692279906405660081L;

	@Id
	@Column(name = "id_movimiento_empleado")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idMovimientoEmpleado;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_empleado")
	private EmpleadoEntity empleado;

	@Column(name = "fecha_ingreso")
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date fechaIngreso;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "hora_ingreso")
	private Date horaIngreso;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario")
	private UsuarioEntity usuarioEntity;

	@Column(name = "numero_oficio")
	private String numeroOficio;

	@JoinColumn(name = "id_movimiento_autorizado")
	@ManyToOne(fetch = FetchType.LAZY)
	private TipoMovimientoEmpleadoEntity movimiento;

	@Column(name = "observaciones")
	private String observaciones;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_inventario_vacante")
	private InventarioVacanteEntity inventarioVacante;

	@Column(name = "fecha_inicio_permiso")
	private Date fechaInicioPermiso;

	@Column(name = "fecha_fin_permiso")
	private Date fechaFinPermiso;

	@Column(name = "tipo_suplencia")
	private Integer tipoSuplencia;

	@Column(name = "id_estatus_movimiento")
	private String estatusMovimiento;

	@Column(name = "fecha_autorizacion")
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date fechaAutorizacion;
	
	@Column(name = "motivo_permiso")
	private String motivoPermiso;
	
	@Column(name = "id_puesto")
	private Integer idPuestoGeneral;
	
	

	public Integer getIdPuestoGeneral() {
		return idPuestoGeneral;
	}

	public void setIdPuestoGeneral(Integer idPuestoGeneral) {
		this.idPuestoGeneral = idPuestoGeneral;
	}

	public Date getFechaAutorizacion() {
		return fechaAutorizacion;
	}

	public void setFechaAutorizacion(Date fechaAutorizacion) {
		this.fechaAutorizacion = fechaAutorizacion;
	}

	public String getEstatusMovimiento() {
		return estatusMovimiento;
	}

	public void setEstatusMovimiento(String estatusMovimiento) {
		this.estatusMovimiento = estatusMovimiento;
	}

	public int getTipoSuplencia() {
		return tipoSuplencia;
	}

	public void setTipoSuplencia(int tipoSuplencia) {
		this.tipoSuplencia = tipoSuplencia;
	}

	public Date getFechaInicioPermiso() {
		return fechaInicioPermiso;
	}

	public void setFechaInicioPermiso(Date fechaInicioPermiso) {
		this.fechaInicioPermiso = fechaInicioPermiso;
	}

	public Date getFechaFinPermiso() {
		return fechaFinPermiso;
	}

	public void setFechaFinPermiso(Date fechaFinPermiso) {
		this.fechaFinPermiso = fechaFinPermiso;
	}

	public UsuarioEntity getUsuarioEntity() {
		return usuarioEntity;
	}

	public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
		this.usuarioEntity = usuarioEntity;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public EmpleadoEntity getEmpleado() {
		return empleado;
	}

	public void setEmpleado(EmpleadoEntity empleado) {
		this.empleado = empleado;
	}

	public String getNumeroOficio() {
		return numeroOficio;
	}

	public void setNumeroOficio(String numeroOficio) {
		this.numeroOficio = numeroOficio;
	}

	public TipoMovimientoEmpleadoEntity getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(TipoMovimientoEmpleadoEntity movimiento) {
		this.movimiento = movimiento;
	}

	public Integer getIdMovimientoEmpleado() {
		return idMovimientoEmpleado;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Date getHoraIngreso() {
		return horaIngreso;
	}

	public void setHoraIngreso(Date horaIngreso) {
		this.horaIngreso = horaIngreso;
	}

	public InventarioVacanteEntity getInventarioVacante() {
		return inventarioVacante;
	}

	public void setInventarioVacante(InventarioVacanteEntity inventarioVacante) {
		this.inventarioVacante = inventarioVacante;
	}

	public String getMotivoPermiso() {
		return motivoPermiso;
	}

	public void setMotivoPermiso(String motivoPermiso) {
		this.motivoPermiso = motivoPermiso;
	}

	
}
