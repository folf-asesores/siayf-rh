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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Leila Schiaffini Ehuan
 * @since 2016-10-19
 * 
 */
@Entity
@Table(name = "suplentes_autorizados")
public class SuplenteAutorizadoEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6076064225297480061L;

	@Id
	@Column(name = "id_suplente_autorizado")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idSuplenteAutorizado;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_empleado")
	private EmpleadoEntity empleado;

	@Column(name = "estatus")
	private String estatus;

	@Column(name = "fecha_alta")
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date fechaAlta;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "hora_alta")
	private Date horaAlta;

	@Column(name = "fecha_baja")
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date fechaBaja;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "hora_baja")
	private Date horaBaja;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_puesto_general")
	private PuestoGeneralEntity puesto;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_centro_responsabilidad")
	private CentroResponsabilidadEntity centroResponsabilidad;

	@Column(name = "id_metodo_pago")
	private int metodoPago;

	@Column(name = "numero")
	private String numero;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_funcion")
	private FuncionEntity funcion;

	@Column(name = "numero_laboral")
	private Integer numeroLaboral;

	@Column(name = "numero_personal")
	private Integer numeroPersonal;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_proyecto")
	private ProyectoTempEntity proyecto;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_dependencia")
	private DependenciaTempEntity dependencia;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_unidad_responsable")
	private UnidadResponsableEntity unidadResponsable;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_ingreso")
	private Date fechaIngreso;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "primera_quincena")
	private Date primeraQuincena;
	
	
	
	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Date getPrimeraQuincena() {
		return primeraQuincena;
	}

	public void setPrimeraQuincena(Date primeraQuincena) {
		this.primeraQuincena = primeraQuincena;
	}

	public Integer getNumeroPersonal() {
		return numeroPersonal;
	}

	public void setNumeroPersonal(Integer numeroPersonal) {
		this.numeroPersonal = numeroPersonal;
	}

	public void setNumeroLaboral(Integer numeroLaboral) {
		this.numeroLaboral = numeroLaboral;
	}

	public ProyectoTempEntity getProyecto() {
		return proyecto;
	}

	public void setProyecto(ProyectoTempEntity proyecto) {
		this.proyecto = proyecto;
	}

	public DependenciaTempEntity getDependencia() {
		return dependencia;
	}

	public void setDependencia(DependenciaTempEntity dependencia) {
		this.dependencia = dependencia;
	}

	public UnidadResponsableEntity getUnidadResponsable() {
		return unidadResponsable;
	}

	public void setUnidadResponsable(UnidadResponsableEntity unidadResponsable) {
		this.unidadResponsable = unidadResponsable;
	}

	public CentroResponsabilidadEntity getCentroResponsabilidad() {
		return centroResponsabilidad;
	}

	public void setCentroResponsabilidad(CentroResponsabilidadEntity centroResponsabilidad) {
		this.centroResponsabilidad = centroResponsabilidad;
	}

	public FuncionEntity getFuncion() {
		return funcion;
	}

	public void setFuncion(FuncionEntity funcion) {
		this.funcion = funcion;
	}

	public int getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(int metodoPago) {
		this.metodoPago = metodoPago;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public int getNumeroLaboral() {
		return numeroLaboral;
	}

	public void setNumeroLaboral(int numeroLaboral) {
		this.numeroLaboral = numeroLaboral;
	}

	public PuestoGeneralEntity getPuesto() {
		return puesto;
	}

	public void setPuesto(PuestoGeneralEntity puesto) {
		this.puesto = puesto;
	}

	public EmpleadoEntity getEmpleado() {
		return empleado;
	}

	public void setEmpleado(EmpleadoEntity empleado) {
		this.empleado = empleado;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
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

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public Date getHoraBaja() {
		return horaBaja;
	}

	public void setHoraBaja(Date horaBaja) {
		this.horaBaja = horaBaja;
	}

	public Integer getIdSuplenteAutorizado() {
		return idSuplenteAutorizado;
	}

}
