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
 * @since 27/09/2016 10:55:37
 * 
 */
@Entity
@Table(name = "interinatos")
public class InterinatoEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6574825991188817838L;
	@Id
	@Column(name = "id_interinato")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idInterinato;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_puesto_propietario")
	private InventarioVacanteEntity puestoPropietario;

	@Column(name = "id_puesto_interino")
	private Integer idPuestoInterino;

	@Column(name = "id_empleado_propietario")
	private Integer idEmpleadoPropietario;

	@Column(name = "estatus")
	private String estatus;

	@Column(name = "id_usuario")
	private Integer idUsuario;

	@Temporal(javax.persistence.TemporalType.DATE)
	@Column(name = "fecha")
	private Date fecha;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "hora")
	private Date hora;

	@Column(name = "id_contexto")
	private Integer idContexto;

	@Column(name = "tipo_candidato")
	private Integer tipoCandidato;

	@Column(name = "fecha_ingreso")
	private Date fechaIngreso;

	@Column(name = "numero_cuenta")
	private String numeroCuenta;

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public Integer getTipoCandidato() {
		return tipoCandidato;
	}

	public void setTipoCandidato(Integer tipoCandidato) {
		this.tipoCandidato = tipoCandidato;
	}

	public Integer getIdContexto() {
		return idContexto;
	}

	public void setIdContexto(Integer idContexto) {
		this.idContexto = idContexto;
	}

	public InventarioVacanteEntity getPuestoPropietario() {
		return puestoPropietario;
	}

	public void setPuestoPropietario(InventarioVacanteEntity puestoPropietario) {
		this.puestoPropietario = puestoPropietario;
	}

	public Integer getIdPuestoInterino() {
		return idPuestoInterino;
	}

	public void setIdPuestoInterino(Integer idPuestoInterino) {
		this.idPuestoInterino = idPuestoInterino;
	}

	public Integer getIdEmpleadoPropietario() {
		return idEmpleadoPropietario;
	}

	public void setIdEmpleadoPropietario(Integer idEmpleadoPropietario) {
		this.idEmpleadoPropietario = idEmpleadoPropietario;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public Integer getIdInterinato() {
		return idInterinato;
	}

}
