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

/**
 * @author Leila Schiaffini Ehuan
 *
 * @Since 12/10/2016 17:44:08
 */
@Entity
@Table(name = "global_octubre")
public class GlobalEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3153008209907803653L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_global")
	private Integer idGlobal;

	@Column(name = "rfc")
	private String rfc;

	@Column(name = "curp")
	private String curp;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "paterno")
	private String apellidoPaterno;

	@Column(name = "materno")
	private String apellidoMaterno;

	@Column(name = "nacionalidad")
	private String nacionalidad;

	@Column(name = "sexo")
	private String sexo;

	@Column(name = "domicilio")
	private String direccion;

	@Column(name = "estado_civil")
	private String estadoCivil;

	@Column(name = "telefono")
	private String telefono;

	@Column(name = "correo")
	private String correo;

	@Column(name = "fecha_ingreso")
	private Date fechaIngreso;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo_contratacion")
	private TipoContratacionEntity tipoContratacion;

	@Column(name = "clave_presupuestal")
	private String clavePresupuestal;

	@Column(name = "adscripcion")
	private String adscripcion;

	@Column(name = "subadscripcion_homologada")
	private String subadscripcion;

	@Column(name = "servicio_homologado")
	private String servicioLabora;

	@Column(name = "funcion")
	private String funcion;

	@Column(name = "clue_real")
	private String clueReal;

	@Column(name = "procesado")
	private Boolean procesado;

	@Column(name = "duplicados")
	private boolean duplicado;

	@Column(name = "id_empleado")
	private Integer idEmpleado;

	@Column(name = "id_inventario_vacante")
	private Integer idInventarioVacante;

	@Column(name = "nuevo")
	private Boolean nuevo;

	@Column(name = "duplicadosSiif")
	private boolean duplicadosSiif;

	@Column(name = "doble_laboral")
	private boolean dobleLaboral;

	@Column(name = "id_laboral_padre")
	private Integer idLaboralPadre;

	public boolean isDobleLaboral() {
		return dobleLaboral;
	}

	public void setDobleLaboral(boolean dobleLaboral) {
		this.dobleLaboral = dobleLaboral;
	}

	public Integer getIdLaboralPadre() {
		return idLaboralPadre;
	}

	public void setIdLaboralPadre(Integer idLaboralPadre) {
		this.idLaboralPadre = idLaboralPadre;
	}

	public boolean isDuplicadosSiif() {
		return duplicadosSiif;
	}

	public void setDuplicadosSiif(boolean duplicadosSiif) {
		this.duplicadosSiif = duplicadosSiif;
	}

	public Boolean getNuevo() {
		return nuevo;
	}

	public void setNuevo(Boolean nuevo) {
		this.nuevo = nuevo;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getClavePresupuestal() {
		return clavePresupuestal;
	}

	public void setClavePresupuestal(String clavePresupuestal) {
		this.clavePresupuestal = clavePresupuestal;
	}

	public String getAdscripcion() {
		return adscripcion;
	}

	public void setAdscripcion(String adscripcion) {
		this.adscripcion = adscripcion;
	}

	public String getSubadscripcion() {
		return subadscripcion;
	}

	public void setSubadscripcion(String subadscripcion) {
		this.subadscripcion = subadscripcion;
	}

	public String getServicioLabora() {
		return servicioLabora;
	}

	public void setServicioLabora(String servicioLabora) {
		this.servicioLabora = servicioLabora;
	}

	public String getFuncion() {
		return funcion;
	}

	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}

	public String getClueReal() {
		return clueReal;
	}

	public void setClueReal(String clueReal) {
		this.clueReal = clueReal;
	}

	public Boolean getProcesado() {
		return procesado;
	}

	public void setProcesado(Boolean procesado) {
		this.procesado = procesado;
	}

	public Integer getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public Integer getIdInventarioVacante() {
		return idInventarioVacante;
	}

	public void setIdInventarioVacante(Integer idInventarioVacante) {
		this.idInventarioVacante = idInventarioVacante;
	}

	public boolean isDuplicado() {
		return duplicado;
	}

	public void setDuplicado(boolean duplicado) {
		this.duplicado = duplicado;
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

	public Integer getIdGlobal() {
		return idGlobal;
	}

	public void setIdGlobal(Integer idGlobal) {
		this.idGlobal = idGlobal;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public TipoContratacionEntity getTipoContratacion() {
		return tipoContratacion;
	}

	public void setTipoContratacion(TipoContratacionEntity tipoContratacion) {
		this.tipoContratacion = tipoContratacion;
	}

}
