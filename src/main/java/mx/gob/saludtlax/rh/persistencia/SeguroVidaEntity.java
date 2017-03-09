/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Eduardo Mex
 *
 */
@Entity
@Table(name = "seguros_vidas")
public class SeguroVidaEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6314843348106873924L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_seguro_vida")
	private Integer idSeguroVida;

	@Column(name = "id_empleado")
	private Integer idEmpleado;

	@Column(name = "estatus")
	private boolean estatus;

	@Column(name = "fecha_alta")
	private Date fechaAlta;

	@Column(name = "hora_alta")
	private Time horaAlta;

	@Column(name = "fecha_fin_dia")
	private Date fechaFinDia;

	@Column(name = "hora_fin_dia")
	private Time horaFinDia;

	@Column(name = "numero_expediente")
	private String numeroExpediente;

	public Integer getIdSeguroVida() {
		return idSeguroVida;
	}

	public void setIdSeguroVida(Integer idSeguroVida) {
		this.idSeguroVida = idSeguroVida;
	}

	public Integer getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public boolean isEstatus() {
		return estatus;
	}

	public void setEstatus(boolean estatus) {
		this.estatus = estatus;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Time getHoraAlta() {
		return horaAlta;
	}

	public void setHoraAlta(Time horaAlta) {
		this.horaAlta = horaAlta;
	}

	public Date getFechaFinDia() {
		return fechaFinDia;
	}

	public void setFechaFinDia(Date fechaFinDia) {
		this.fechaFinDia = fechaFinDia;
	}

	public Time getHoraFinDia() {
		return horaFinDia;
	}

	public void setHoraFinDia(Time horaFinDia) {
		this.horaFinDia = horaFinDia;
	}

	public String getNumeroExpediente() {
		return numeroExpediente;
	}

	public void setNumeroExpediente(String numeroExpediente) {
		this.numeroExpediente = numeroExpediente;
	}

}
