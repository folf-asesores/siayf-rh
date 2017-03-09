package mx.gob.saludtlax.rh.siif;

import java.util.Date;

public class DatosPersonalesDTO {

	private Integer idDatoPersonal;
	private Integer idEmpleadoDatosPersonales;
	private String rfc;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String nombre;
	private Date fechaNacimiento;
	private String sexo;
	private Integer idLocalidad;
	private Integer idColonia;
	private String calle;
	private String numeroExterior;
	private String numeroInterior;
	private String codigoPostal;
	private String telefono;
	private String idEstadoEmpleado;

	@Override
	public String toString() {
		return "DatosPersonalesDTO [idDatoPersonal=" + idDatoPersonal + ", idEmpleadoDatosPersonales="
				+ idEmpleadoDatosPersonales + ", rfc=" + rfc + ", apellidoPaterno=" + apellidoPaterno
				+ ", apellidoMaterno=" + apellidoMaterno + ", nombre=" + nombre + ", fechaNacimiento=" + fechaNacimiento
				+ ", sexo=" + sexo + ", idLocalidad=" + idLocalidad + ", idColonia=" + idColonia + ", calle=" + calle
				+ ", numeroExterior=" + numeroExterior + ", numeroInterior=" + numeroInterior + ", codigoPostal="
				+ codigoPostal + ", telefono=" + telefono + ", idEstadoEmpleado=" + idEstadoEmpleado + "]";
	}

	public Integer getIdDatoPersonal() {
		return idDatoPersonal;
	}

	public void setIdDatoPersonal(Integer idDatoPersonal) {
		this.idDatoPersonal = idDatoPersonal;
	}

	public Integer getIdEmpleadoDatosPersonales() {
		return idEmpleadoDatosPersonales;
	}

	public void setIdEmpleadoDatosPersonales(Integer idEmpleadoDatosPersonales) {
		this.idEmpleadoDatosPersonales = idEmpleadoDatosPersonales;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Integer getIdLocalidad() {
		return idLocalidad;
	}

	public void setIdLocalidad(Integer idLocalidad) {
		this.idLocalidad = idLocalidad;
	}

	public Integer getIdColonia() {
		return idColonia;
	}

	public void setIdColonia(Integer idColonia) {
		this.idColonia = idColonia;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNumeroExterior() {
		return numeroExterior;
	}

	public void setNumeroExterior(String numeroExterior) {
		this.numeroExterior = numeroExterior;
	}

	public String getNumeroInterior() {
		return numeroInterior;
	}

	public void setNumeroInterior(String numeroInterior) {
		this.numeroInterior = numeroInterior;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getIdEstadoEmpleado() {
		return idEstadoEmpleado;
	}

	public void setIdEstadoEmpleado(String idEstadoEmpleado) {
		this.idEstadoEmpleado = idEstadoEmpleado;
	}

}
