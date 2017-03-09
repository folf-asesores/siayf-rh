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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Plazas")
public class PlazaEntity implements Serializable {

	private static final long serialVersionUID = 3683137414743126189L;

	@Id
	@Column(name = "IdPlaza")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPlaza;

	@Column(name = "Clave")
	private String clave;

	@Column(name = "NombrePlaza")
	private String nombrePlaza;

	@Column(name = "Adscripcion")
	private String adscripcion;

	@Column(name = "Tipo")
	private String tipo;

	@Column(name = "CondicionLaboral")
	private String condicionLaboral;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IdNombramiento")
	private TiposNombramientosEntity nombramiento;

	@Column(name = "IdNivel")
	private Integer idNivel;

	@Column(name = "ImporteMensual")
	private BigDecimal importeMensual;

	@Column(name = "Estatus")
	private String estatus;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IdEmpleado")
	private EmpleadoEntity empleado;

	@Column(name = "FechaInicio")
	private Date fechaInicio;

	@Column(name = "FechaFin")
	private Date fechaFin;

	public EmpleadoEntity getEmpleado() {
		return empleado;
	}

	public void setEmpleado(EmpleadoEntity empleado) {
		this.empleado = empleado;
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

	public TiposNombramientosEntity getNombramiento() {
		return nombramiento;
	}

	public void setNombramiento(TiposNombramientosEntity nombramiento) {
		this.nombramiento = nombramiento;
	}

	public Integer getIdNivel() {
		return idNivel;
	}

	public void setIdNivel(Integer idNivel) {
		this.idNivel = idNivel;
	}

	public BigDecimal getImporteMensual() {
		return importeMensual;
	}

	public void setImporteMensual(BigDecimal importeMensual) {
		this.importeMensual = importeMensual;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	/******** Getters and Setters ********/

	public Integer getIdPlaza() {
		return idPlaza;
	}

	public void setIdPlaza(Integer idPlaza) {
		this.idPlaza = idPlaza;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getNombrePlaza() {
		return nombrePlaza;
	}

	public void setNombrePlaza(String nombrePlaza) {
		this.nombrePlaza = nombrePlaza;
	}

	public String getAdscripcion() {
		return adscripcion;
	}

	public void setAdscripcion(String adscripcion) {
		this.adscripcion = adscripcion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCondicionLaboral() {
		return condicionLaboral;
	}

	public void setCondicionLaboral(String condicionLaboral) {
		this.condicionLaboral = condicionLaboral;
	}
}