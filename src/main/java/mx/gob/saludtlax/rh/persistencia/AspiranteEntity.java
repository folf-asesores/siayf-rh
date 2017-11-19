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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 18/04/2016-14:15:32
 */
@Entity
@Table(name = "aspirantes")
public class AspiranteEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5800524688665363370L;

	@Id
	@Column(name = "id_aspirante")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idAspirante;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pais_nacionalidad")
	private PaisEntity paisNacionalidad;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cargo")
	private PuestoGeneralEntity cargo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_empleado")
	private EmpleadoEntity empleado;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "apellido_materno")
	private String apellidoMaterno;

	@Column(name = "apellido_paterno")
	private String apellidoPaterno;

	@Column(name = "nombre_completo")
	private String nombreCompleto;

	@Column(name = "rfc")
	private String rfc;

	@Column(name = "curp")
	private String curp;

	@Column(name = "correo_electronico")
	private String correoElectronico;

	@Column(name = "fecha_nacimiento")
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;

	@Column(name = "telefono")
	private String telefono;

	@Column(name = "id_nacionalidad")
	private String nacionalidad;

	@Column(name = "lugar_nacimiento")
	private String lugarNacimiento;

	@Column(name = "direccion_completa")
	private String direccionCompleta;

	@Column(name = "tipo_sangre")
	private String tipoSangre;

	@Column(name = "peso")
	private float peso;

	@Column(name = "estatura")
	private float estatura;

	@Column(name = "id_sexo")
	private String idSexo;

	@Column(name = "id_estado_civil")
	private String estadoCivil;

	@Column(name = "vive_con")
	private String viveCon;

	@Column(name = "tiene_persona_dependiente")
	private Boolean tienePersonasDependientes;

	@Column(name = "numero_hijo")
	private int numeroHijos;

	@Column(name = "numero_conyuge")
	private Integer numeroConyuges;

	@Column(name = "numero_padre")
	private Integer numeroPadres;

	@Column(name = "numero_otro")
	private Integer numeroOtros;

	@Column(name = "id_estatus")
	private String idEstatus;

	@Column(name = "en_proceso")
	private boolean enProceso;

	@Column(name = "tiene_licencia")
	private boolean tieneLicencia;

	@Column(name = "fecha_alta")
	@Temporal(TemporalType.DATE)
	private Date fechaAlta;

	@Column(name = "autorizado")
	private Boolean autorizado;

	@Column(name = "id_foto")
	private Integer idFoto;

	@Column(name = "perfil_academico")
	private String perfilAcademico;

	

	public void toUpperCase() {
		this.nombre = this.nombre.toUpperCase();
		this.apellidoMaterno = this.apellidoMaterno.toUpperCase();
		if (this.apellidoPaterno != null) {
			this.apellidoPaterno = this.apellidoPaterno.toUpperCase();
		}
		this.curp = this.curp.toUpperCase();
		this.lugarNacimiento = this.lugarNacimiento.toUpperCase();
	}

	public String nombreCompleto() {
		String nombreCompleto = this.nombre + " ";
		if (this.apellidoPaterno != null || this.apellidoPaterno.trim().isEmpty()) {
			this.nombreCompleto = this.nombreCompleto + this.apellidoPaterno + " ";
		}
		this.nombreCompleto = this.nombreCompleto + this.apellidoMaterno;
		return nombreCompleto;
	}

	public String getPerfilAcademico() {
		return perfilAcademico;
	}

	public void setPerfilAcademico(String perfilAcademico) {
		this.perfilAcademico = perfilAcademico;
	}

	public Boolean getAutorizado() {
		return autorizado;
	}

	public void setAutorizado(Boolean autorizado) {
		this.autorizado = autorizado;
	}

	public PuestoGeneralEntity getCargo() {
		return cargo;
	}

	public void setCargo(PuestoGeneralEntity cargo) {
		this.cargo = cargo;
	}

	public Integer getIdAspirante() {
		return idAspirante;
	}

	public void setIdAspirante(Integer idAspirante) {
		this.idAspirante = idAspirante;
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

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
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

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public PaisEntity getPaisNacionalidad() {
		return paisNacionalidad;
	}

	public void setPaisNacionalidad(PaisEntity paisNacionalidad) {
		this.paisNacionalidad = paisNacionalidad;
	}

	public String getLugarNacimiento() {
		return lugarNacimiento;
	}

	public void setLugarNacimiento(String lugarNacimiento) {
		this.lugarNacimiento = lugarNacimiento;
	}

	public String getDireccionCompleta() {
		return direccionCompleta;
	}

	public void setDireccionCompleta(String direccionCompleta) {
		this.direccionCompleta = direccionCompleta;
	}

	public String getTipoSangre() {
		return tipoSangre;
	}

	public void setTipoSangre(String tipoSangre) {
		this.tipoSangre = tipoSangre;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public float getEstatura() {
		return estatura;
	}

	public void setEstatura(float estatura) {
		this.estatura = estatura;
	}

	public String getIdSexo() {
		return idSexo;
	}

	public void setIdSexo(String idSexo) {
		this.idSexo = idSexo;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getViveCon() {
		return viveCon;
	}

	public void setViveCon(String viveCon) {
		this.viveCon = viveCon;
	}

	public Boolean getTienePersonasDependientes() {
		return tienePersonasDependientes;
	}

	public void setTienePersonasDependientes(Boolean tienePersonasDependientes) {
		this.tienePersonasDependientes = tienePersonasDependientes;
	}

	public int getNumeroHijos() {
		return numeroHijos;
	}

	public void setNumeroHijos(int numeroHijos) {
		this.numeroHijos = numeroHijos;
	}

	public Integer getNumeroConyuges() {
		return numeroConyuges;
	}

	public void setNumeroConyuges(Integer numeroConyuges) {
		this.numeroConyuges = numeroConyuges;
	}

	public Integer getNumeroPadres() {
		return numeroPadres;
	}

	public void setNumeroPadres(Integer numeroPadres) {
		this.numeroPadres = numeroPadres;
	}

	public Integer getNumeroOtros() {
		return numeroOtros;
	}

	public void setNumeroOtros(Integer numeroOtros) {
		this.numeroOtros = numeroOtros;
	}

	public String getIdEstatus() {
		return idEstatus;
	}

	public void setIdEstatus(String idEstatus) {
		this.idEstatus = idEstatus;
	}

	public boolean isEnProceso() {
		return enProceso;
	}

	public void setEnProceso(boolean enProceso) {
		this.enProceso = enProceso;
	}

	public boolean isTieneLicencia() {
		return tieneLicencia;
	}

	public void setTieneLicencia(boolean tieneLicencia) {
		this.tieneLicencia = tieneLicencia;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	/**
	 * @return the empleado
	 */
	public EmpleadoEntity getEmpleado() {
		return empleado;
	}

	/**
	 * @param empleado
	 *            the empleado to set
	 */
	public void setEmpleado(EmpleadoEntity empleado) {
		this.empleado = empleado;
	}

	/**
	 * @return the idFoto
	 */
	public Integer getIdFoto() {
		return idFoto;
	}

	/**
	 * @param idFoto
	 *            the idFoto to set
	 */
	public void setIdFoto(Integer idFoto) {
		this.idFoto = idFoto;
	}

}
