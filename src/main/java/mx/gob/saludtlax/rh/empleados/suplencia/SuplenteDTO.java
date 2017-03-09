/**
 * 
 */
package mx.gob.saludtlax.rh.empleados.suplencia;

import java.util.Date;

/**
 * @author Leila Schiaffini Ehuan
 * @since 2016-10-19
 * 
 */
public class SuplenteDTO {
	private Integer idSuplente;
	private Integer idEmpleado;
	private String rfc;
	private String nombre;
	private String estatus;
	private String tipoSuplente;
	private Date fechaIngreso;
	private Date fechaPrimeraQuincena;
	private String centroResponsabilidad;
	private String funcion;
	private String metodoPago;
	private String numeroCuenta;
	private String proyecto;
	private String dependencia;
	private String unidadResponsable;
	private int numeroLaboral;
	private int numeroPersonal;

	public SuplenteDTO() {

	}

	public SuplenteDTO(Integer idSuplente, String rfc, String nombre, String estatus, String tipoSuplente,
			Integer idEmpleado) {
		super();
		this.idSuplente = idSuplente;
		this.rfc = rfc;
		this.nombre = nombre;
		this.estatus = estatus;
		this.tipoSuplente = tipoSuplente;
		this.idEmpleado = idEmpleado;
	}

	public Date getFechaPrimeraQuincena() {
		return fechaPrimeraQuincena;
	}

	public void setFechaPrimeraQuincena(Date fechaPrimeraQuincena) {
		this.fechaPrimeraQuincena = fechaPrimeraQuincena;
	}

	public Integer getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getTipoSuplente() {
		return tipoSuplente;
	}

	public void setTipoSuplente(String tipoSuplente) {
		this.tipoSuplente = tipoSuplente;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public Integer getIdSuplente() {
		return idSuplente;
	}

	public void setIdSuplente(Integer idSuplente) {
		this.idSuplente = idSuplente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getCentroResponsabilidad() {
		return centroResponsabilidad;
	}

	public void setCentroResponsabilidad(String centroResponsabilidad) {
		this.centroResponsabilidad = centroResponsabilidad;
	}

	public String getFuncion() {
		return funcion;
	}

	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getProyecto() {
		return proyecto;
	}

	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}

	public String getDependencia() {
		return dependencia;
	}

	public void setDependencia(String dependencia) {
		this.dependencia = dependencia;
	}

	public String getUnidadResponsable() {
		return unidadResponsable;
	}

	public void setUnidadResponsable(String unidadResponsable) {
		this.unidadResponsable = unidadResponsable;
	}

	public int getNumeroLaboral() {
		return numeroLaboral;
	}

	public void setNumeroLaboral(int numeroLaboral) {
		this.numeroLaboral = numeroLaboral;
	}

	public int getNumeroPersonal() {
		return numeroPersonal;
	}

	public void setNumeroPersonal(int numeroPersonal) {
		this.numeroPersonal = numeroPersonal;
	}

}
