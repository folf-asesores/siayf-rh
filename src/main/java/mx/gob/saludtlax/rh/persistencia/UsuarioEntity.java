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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "usuarios")
public class UsuarioEntity implements Serializable {

	private static final long serialVersionUID = 7769033685876224319L;

	@Id
	@Column(name = "id_usuario")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idUsuario;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "password")
	private String password;

	@Column(name = "activo")
	private boolean activo;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "apellido_paterno")
	private String apellidoPaterno;

	@Column(name = "apellido_materno")
	private String apellidoMaterno;

	@Column(name = "correo")
	private String correo;

	@Column(name = "cargo")
	private String cargo;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_perfil")
	private PerfilUsuarioEntity perfil;

	@Column(name = "fecha_alta")
	@Temporal(TemporalType.DATE)
	private Date fechaAlta;
	
	@Column(name="telefono")
	private String telefono;
	
	@Column(name="celular")
	private String celular;
	
	@Column(name="id_area_adscripcion")
	private Integer id_area_adscripcion;
	
	@Column(name="id_lugar_adscripcion")
	private Integer id_lugar_adscripcion;
	
	@Column(name="id_adscripcion")
	private Integer id_adscripcion;

	public String nombreCompleto() {
		String nombreCompleto = nombre + " " + apellidoPaterno + " "
				+ apellidoMaterno;
		return nombreCompleto;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
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

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public PerfilUsuarioEntity getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilUsuarioEntity idPerfil) {
		perfil = idPerfil;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getId_area_adscripcion() {
		return id_area_adscripcion;
	}

	public void setId_area_adscripcion(Integer id_area_adscripcion) {
		this.id_area_adscripcion = id_area_adscripcion;
	}

	public Integer getId_lugar_adscripcion() {
		return id_lugar_adscripcion;
	}

	public void setId_lugar_adscripcion(Integer id_lugar_adscripcion) {
		this.id_lugar_adscripcion = id_lugar_adscripcion;
	}

	public Integer getId_adscripcion() {
		return id_adscripcion;
	}

	public void setId_adscripcion(Integer id_adscripcion) {
		this.id_adscripcion = id_adscripcion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}
	
	
}
