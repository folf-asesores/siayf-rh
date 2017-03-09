/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "siif_datos_personales")
public class DatosEncabezadoEntity implements Serializable {

	private static final long serialVersionUID = -3491218769714297031L;
	
//	private String rfc;
//	private String nombre;
//	private Integer idNomina;
//	private Integer id_empleado_datos_laborales;
//	private String noChequeCuenta;
//	private String ur;
//	private String fn;
//	private String cr;
//	private String id_puesto;
//	private BigDecimal percepciones;
//	private BigDecimal deducciones;
//	private BigDecimal neto;
//	private String rfc1;
//	private Integer num;

	@Id
	@Column(name = "id_dato_personal")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idDatoPersonal;
	
	@Column(name = "id_empleado_datos_personales")
	private Integer idEmpleadoDatosPersonales;
	
	@Column(name = "rfc")
	private String rfc;
	
	
	@Column(name = "apellido_paterno")
	private String apellidoPaterno;
	
	@Column(name = "apellido_materno")
	private String apellidoMaterno;
	
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "fecha_nacimiento")
	private Date fechaNacimiento;
	
	@Column(name = "sexo")
	private String sexo;
	
	@Column(name = "id_localidad")	
	private Integer idLocalidad;
	
	@Column(name = "id_colonia")
	private Integer idColonia;
	
	@Column(name = "calle")
	private String calle;
	
	@Column(name = "numero_exterior")	
	private String numeroExterior;
	
	@Column(name = "numeroInterior")	
	private String numeroInterior;
	
	@Column(name = "codigo_postal")	
	
	private String codigoPostal;
	@Column(name = "telefono")	
	private String telefono;
	
	@Column(name = "id_estado_empleado")	
	private String idEstadoEmpleado;

	
//		<<<<<Getters & Setters>>>>>


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


	public void setNumeroInterior(String numerointerior) {
		this.numeroInterior = numerointerior;
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
	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

		
}