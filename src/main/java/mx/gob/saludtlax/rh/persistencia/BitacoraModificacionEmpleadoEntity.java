/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.sql.Time;
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
 * @since 07/03/2016-12:35:16
 */
@Entity
@Table(name = "bitacoras_modificaciones_empleados")
public class BitacoraModificacionEmpleadoEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9008811045195525131L;

	@Id
	@Column(name = "id_bitacora_movimiento")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idBitacoraMovimiento;

	@Column(name = "fecha")
	private Date fecha;

	@Column(name = "hora")
	private Time hora;

	@Column(name = "comentario")
	private String comentarios;

	@Column(name = "lcc_anterior")
	private String lccAnterior;

	@Column(name = "lcc_actual")
	private String lccActual;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo_modificacion")
	private TipoModificacionEmpleadoEntity tipoMovimiento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario")
	private UsuarioEntity usuario;

	@Column(name = "id_empleado")
	private Integer idEmpleado;

	public Integer getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Time getHora() {
		return hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public String getLccAnterior() {
		return lccAnterior;
	}

	public void setLccAnterior(String lccAnterior) {
		this.lccAnterior = lccAnterior;
	}

	public String getLccActual() {
		return lccActual;
	}

	public void setLccActual(String lccActual) {
		this.lccActual = lccActual;
	}

	public TipoModificacionEmpleadoEntity getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(TipoModificacionEmpleadoEntity tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public UsuarioEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}

	public Integer getIdBitacoraMovimiento() {
		return idBitacoraMovimiento;
	}

}
