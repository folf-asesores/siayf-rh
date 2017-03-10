/**
 * 
 */
package mx.gob.saludtlax.rh.empleados.administracion;

import java.math.BigDecimal;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 04/05/2016 04/05/2016
 */
public class InfoEmpleadoDTO {
	private Integer idEmpleado;
	private String nombre;
	private String curp;
	private String rfc;
	private String direccion;
	private Integer numeroEmpleado;
	private String estatus;
	private String profesionEspecialidad;
	private String tipoContratacion;
	private String folioVacante;
	private Integer idVacante;
	private String codigoPuesto;
	private String puesto;
	private String tipoEmpleado;

	private String nombramiento;
	private BigDecimal sueldoActualEmpleado;
	private String claveCobro;

	public InfoEmpleadoDTO() {
		super();

	}
	public InfoEmpleadoDTO(Integer idEmpleado, String nombre) {
		this.idEmpleado = idEmpleado;
		this.nombre = nombre;
	}
	
	public InfoEmpleadoDTO(Integer idEmpleado, String nombre, String curp,
			String rfc, String direccion, Integer numeroEmpleado,
			String estatus, String tipoContratacion, String folio,
			Integer idVacante) {

		this.idEmpleado = idEmpleado;
		this.nombre = nombre;
		this.curp = curp;
		this.rfc = rfc;
		this.direccion = direccion;
		this.numeroEmpleado = numeroEmpleado;
		this.estatus = estatus;
		this.tipoContratacion = tipoContratacion;
		this.folioVacante = folio;
		this.idVacante = idVacante;
	}

	public InfoEmpleadoDTO(Integer idEmpleado, String nombre, String curp,
			String rfc, String direccion, Integer numeroEmpleado,
			String estatus, String tipoEmpleado) {

		this.idEmpleado = idEmpleado;
		this.nombre = nombre;
		this.curp = curp;
		this.rfc = rfc;
		this.direccion = direccion;
		this.numeroEmpleado = numeroEmpleado;
		this.estatus = estatus;
		this.tipoEmpleado = tipoEmpleado;

	}

	public InfoEmpleadoDTO(Integer idEmpleado, String nombre, String curp,
			String rfc, String direccion, Integer numeroEmpleado,
			String estatus, String tipoContratacion, String folio,
			Integer idVacante, String codigoPuesto, String puesto,
			String nombramiento, BigDecimal sueldoActualEmpleado,
			String claveCobro) {

		this.idEmpleado = idEmpleado;
		this.nombre = nombre;
		this.curp = curp;
		this.rfc = rfc;
		this.direccion = direccion;
		this.numeroEmpleado = numeroEmpleado;
		this.estatus = estatus;
		this.tipoContratacion = tipoContratacion;
		this.folioVacante = folio;
		this.idVacante = idVacante;
		this.codigoPuesto = codigoPuesto;
		this.puesto = puesto;
		this.nombramiento = nombramiento;
		this.sueldoActualEmpleado = sueldoActualEmpleado;
		this.claveCobro = claveCobro;

	}

	@Override
	public String toString() {
		return "InfoEmpleadoDTO [idEmpleado=" + idEmpleado + ", nombre="
				+ nombre + ", curp=" + curp + ", rfc=" + rfc + ", direccion="
				+ direccion + ", numeroEmpleado=" + numeroEmpleado
				+ ", estatus=" + estatus + "]";
	}

	public String getTipoEmpleado() {
		return tipoEmpleado;
	}

	public void setTipoEmpleado(String tipoEmpleado) {
		this.tipoEmpleado = tipoEmpleado;
	}

	public String getCodigoPuesto() {
		return codigoPuesto;
	}

	public void setCodigoPuesto(String codigoPuesto) {
		this.codigoPuesto = codigoPuesto;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public Integer getIdVacante() {
		return idVacante;
	}

	public void setIdVacante(Integer idVacante) {
		this.idVacante = idVacante;
	}

	public String getTipoContratacion() {
		return tipoContratacion;
	}

	public void setTipoContratacion(String tipoContratacion) {
		this.tipoContratacion = tipoContratacion;
	}

	public String getFolioVacante() {
		return folioVacante;
	}

	public void setFolioVacante(String folioVacante) {
		this.folioVacante = folioVacante;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public Integer getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Integer getNumeroEmpleado() {
		return numeroEmpleado;
	}

	public void setNumeroEmpleado(Integer numeroEmpleado) {
		this.numeroEmpleado = numeroEmpleado;
	}

	/**
	 * @return the profesionEspecialidad
	 */
	public String getProfesionEspecialidad() {
		return profesionEspecialidad;
	}

	/**
	 * @param profesionEspecialidad
	 *            the profesionEspecialidad to set
	 */
	public void setProfesionEspecialidad(String profesionEspecialidad) {
		this.profesionEspecialidad = profesionEspecialidad;
	}

	public String getNombramiento() {
		return nombramiento;
	}

	public void setNombramiento(String nombramiento) {
		this.nombramiento = nombramiento;
	}

	public BigDecimal getSueldoActualEmpleado() {
		return sueldoActualEmpleado;
	}

	public void setSueldoActualEmpleado(BigDecimal sueldoActualEmpleado) {
		this.sueldoActualEmpleado = sueldoActualEmpleado;
	}

	public String getClaveCobro() {
		return claveCobro;
	}

	public void setClaveCobro(String claveCobro) {
		this.claveCobro = claveCobro;
	}

}
