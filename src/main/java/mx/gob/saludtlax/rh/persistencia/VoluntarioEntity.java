/*
 * VoluntarioEntity.java
 * Creado el 24/Nov/2016 5:50:24 PM
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
@Entity
@Table(name = "voluntarios")
public class VoluntarioEntity implements Serializable {

	private static final long serialVersionUID = -2843098376964028352L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id_voluntario")
	private Integer idVoluntario;
	@Size(max = 45)
	@Column(name = "curp")
	private String curp;

	@Column(name = "rfc")
	private String rfc;
	@Size(max = 45)
	@Column(name = "nombre")
	private String nombre;
	@Size(max = 45)
	@Column(name = "apellido_paterno")
	private String apellidoPaterno;
	@Size(max = 45)
	@Column(name = "apellido_materno")
	private String apellidoMaterno;
	@Size(max = 45)
	@Column(name = "nombre_completo")
	private String nombreCompleto;
	@Column(name = "fecha_ingreso")
	@Temporal(TemporalType.DATE)
	private Date fechaIngreso;
	@Column(name = "fecha_inicio_contratacion")
	@Temporal(TemporalType.DATE)
	private Date fechaInicioContratacion;
	@Column(name = "fecha_fin_contratacion")
	@Temporal(TemporalType.DATE)
	private Date fechaFinContratacion;
	@Column(name = "sueldo")
	private BigDecimal sueldo;

	@Column(name = "numero_cuenta")
	private String numeroCuenta;

	@Column(name = "estatus")
	private String estatus;

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public VoluntarioEntity() {
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public VoluntarioEntity(Integer idVoluntario) {
		this.idVoluntario = idVoluntario;
	}

	public Integer getIdVoluntario() {
		return idVoluntario;
	}

	public void setIdVoluntario(Integer idVoluntario) {
		this.idVoluntario = idVoluntario;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Date getFechaInicioContratacion() {
		return fechaInicioContratacion;
	}

	public void setFechaInicioContratacion(Date fechaInicioContratacion) {
		this.fechaInicioContratacion = fechaInicioContratacion;
	}

	public Date getFechaFinContratacion() {
		return fechaFinContratacion;
	}

	public void setFechaFinContratacion(Date fechaFinContratacion) {
		this.fechaFinContratacion = fechaFinContratacion;
	}

	public BigDecimal getSueldo() {
		return sueldo;
	}

	public void setSueldo(BigDecimal sueldo) {
		this.sueldo = sueldo;
	}

}
