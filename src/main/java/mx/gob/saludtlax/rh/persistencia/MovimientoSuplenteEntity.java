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
 * @Since 12/01/2017 13:20:22
 */
@Entity
@Table(name = "movimientos_suplentes")
public class MovimientoSuplenteEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6692648678591557621L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_suplente")
	private SuplenteAutorizadoEntity suplente;

	@Column(name = "movimiento")
	private String movimiento;

	@Column(name = "fecha_inicio")
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date fechaInicio;

	@Column(name = "fecha_fin")
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date fechaFin;

	@Column(name = "fecha_solicitud")
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date fechaRegistro;

	@Column(name = "hora_solicitud")
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaRegistro;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario_solicitud")
	private UsuarioEntity usuarioSolicitud;

	@Column(name = "ejercicio_fiscal_periodo")
	private int ejercicioFiscalPeriodo;

	@Column(name = "total_dias")
	private int totalDias;

	@Column(name = "observaciones")
	private String observaciones;

	@Column(name = "estatus")
	private String estatus;

	@Column(name = "fecha_validacion")
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date fechaValidacion;

	@Column(name = "hora_validacion")
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaValidacion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario_validacion")
	private UsuarioEntity usuarioValidacion;

	public SuplenteAutorizadoEntity getSuplente() {
		return suplente;
	}

	public void setSuplente(SuplenteAutorizadoEntity suplente) {
		this.suplente = suplente;
	}

	public String getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(String movimiento) {
		this.movimiento = movimiento;
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

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Date getHoraRegistro() {
		return horaRegistro;
	}

	public void setHoraRegistro(Date horaRegistro) {
		this.horaRegistro = horaRegistro;
	}

	public int getEjercicioFiscalPeriodo() {
		return ejercicioFiscalPeriodo;
	}

	public void setEjercicioFiscalPeriodo(int ejercicioFiscalPeriodo) {
		this.ejercicioFiscalPeriodo = ejercicioFiscalPeriodo;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Integer getId() {
		return id;
	}

	public UsuarioEntity getUsuarioSolicitud() {
		return usuarioSolicitud;
	}

	public void setUsuarioSolicitud(UsuarioEntity usuarioSolicitud) {
		this.usuarioSolicitud = usuarioSolicitud;
	}

	public int getTotalDias() {
		return totalDias;
	}

	public void setTotalDias(int totalDias) {
		this.totalDias = totalDias;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public Date getFechaValidacion() {
		return fechaValidacion;
	}

	public void setFechaValidacion(Date fechaValidacion) {
		this.fechaValidacion = fechaValidacion;
	}

	public Date getHoraValidacion() {
		return horaValidacion;
	}

	public void setHoraValidacion(Date horaValidacion) {
		this.horaValidacion = horaValidacion;
	}

	public UsuarioEntity getUsuarioValidacion() {
		return usuarioValidacion;
	}

	public void setUsuarioValidacion(UsuarioEntity usuarioValidacion) {
		this.usuarioValidacion = usuarioValidacion;
	}

}
