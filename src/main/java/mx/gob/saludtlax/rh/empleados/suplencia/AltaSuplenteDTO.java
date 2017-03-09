/**
 * 
 */
package mx.gob.saludtlax.rh.empleados.suplencia;

import java.util.Date;

import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.DireccionDTO;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @Since 02/12/2016 20:05:48
 */
public class AltaSuplenteDTO {
	private String rfc;
	private String curp;
	private String nombre;
	private String apellidoMaterno;
	private String apellidoPaterno;
	private Date fechaNacimiento;
	private String sexo;
	private Integer numeroPersonal;
	private String telefono;
	private DireccionDTO direccion = new DireccionDTO();

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public DireccionDTO getDireccion() {
		return direccion;
	}

	public void setDireccion(DireccionDTO direccion) {
		this.direccion = direccion;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
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

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
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

	public Integer getNumeroPersonal() {
		return numeroPersonal;
	}

	public void setNumeroPersonal(Integer numeroPersonal) {
		this.numeroPersonal = numeroPersonal;
	}

}
