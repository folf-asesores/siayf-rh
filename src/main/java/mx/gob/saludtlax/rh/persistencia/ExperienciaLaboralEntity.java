/**
 * 
 */
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 14/03/2016-20:30:33
 */
@Entity
@Table(name = "experiencias_laborales")
public class ExperienciaLaboralEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2832302808071614942L;

	@Id
	@Column(name = "id_experiencia_laboral")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idExperienciaLaboral;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_aspirante")
	private AspiranteEntity aspirante;

	@Column(name = "nombre_empresa")
	private String nombreEmpresa;

	@Column(name = "puesto")
	private String puesto;

    @Temporal(TemporalType.DATE)
	@Column(name = "fecha_inicial")
	private Date fechaInicial;

    @Temporal(TemporalType.DATE)
	@Column(name = "fecha_final")
	private Date fechaFinal;

	@Column(name = "direccion_empresa")
	private String direccionEmpresa;

	@Column(name = "motivo_separacion")
	private String motivoSeparacion;

	@Column(name = "sueldo_mensual")
	private BigDecimal sueldoMensual;

	@Column(name = "comentario")
	private String comentarios;

	@Column(name = "telefono")
	private String telefono;

	@Column(name = "correo_contacto")
	private String correoContacto;

	@Column(name = "nombre_jefe")
	private String nombreJefe;

	@Column(name = "puesto_jefe")
	private String puestoJefe;

	@Column(name = "solicitar_informacion")
	private Boolean solicitarInformacion;

	@Column(name = "razon_no_solicitar")
	private String razonNoSolicitar;

	@Column(name = "id_empleado")
	private Integer idEmpleado;

	
	
	
	public Integer getIdEmpleado() {
		return idEmpleado;
	}

	@Override
	public String toString() {
		return "ExperienciaLaboralEntity [idExperienciaLaboral="
				+ idExperienciaLaboral + ", aspirante=" + aspirante
				+ ", nombreEmpresa=" + nombreEmpresa + ", puesto=" + puesto
				+ ", fechaInicial=" + fechaInicial + ", fechaFinal="
				+ fechaFinal + ", direccionEmpresa=" + direccionEmpresa
				+ ", motivoSeparacion=" + motivoSeparacion + ", sueldoMensual="
				+ sueldoMensual + ", comentarios=" + comentarios
				+ ", telefono=" + telefono + ", correoContacto="
				+ correoContacto + ", nombreJefe=" + nombreJefe
				+ ", puestoJefe=" + puestoJefe + ", solicitarInformacion="
				+ solicitarInformacion + ", razonNoSolicitar="
				+ razonNoSolicitar + ", idEmpleado=" + idEmpleado + "]";
	}
	
	public String lccExperiencia() {
		return "ExperienciaLaboral[idExperienciaLaboral="
				+ idExperienciaLaboral + ", aspirante=" + aspirante
				+ ", nombreEmpresa=" + nombreEmpresa + ", puesto=" + puesto
				+ ", fechaInicial=" + fechaInicial + ", fechaFinal="
				+ fechaFinal + ", direccionEmpresa=" + direccionEmpresa
				+ ", motivoSeparacion=" + motivoSeparacion + ", sueldoMensual="
				+ sueldoMensual + ", comentarios=" + comentarios
				+ ", telefono=" + telefono + ", correoContacto="
				+ correoContacto + ", nombreJefe=" + nombreJefe
				+ ", puestoJefe=" + puestoJefe + ", solicitarInformacion="
				+ solicitarInformacion + ", razonNoSolicitar="
				+ razonNoSolicitar + ", idEmpleado=" + idEmpleado + "]";
	}

	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public Date getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public String getDireccionEmpresa() {
		return direccionEmpresa;
	}

	public void setDireccionEmpresa(String direccionEmpresa) {
		this.direccionEmpresa = direccionEmpresa;
	}

	public String getCorreoContacto() {
		return correoContacto;
	}

	public void setCorreoContacto(String correoContacto) {
		this.correoContacto = correoContacto;
	}

	public void setIdExperienciaLaboral(Integer idExperienciaLaboral) {
		this.idExperienciaLaboral = idExperienciaLaboral;
	}

	public String getMotivoSeparacion() {
		return motivoSeparacion;
	}

	public void setMotivoSeparacion(String motivoSeparacion) {
		this.motivoSeparacion = motivoSeparacion;
	}

	public BigDecimal getSueldoMensual() {
		return sueldoMensual;
	}

	public void setSueldoMensual(BigDecimal sueldoMensual) {
		this.sueldoMensual = sueldoMensual;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}


	/**
	 * @return the aspirante
	 */
	public AspiranteEntity getAspirante() {
		return aspirante;
	}

	/**
	 * @param aspirante the aspirante to set
	 */
	public void setAspirante(AspiranteEntity aspirante) {
		this.aspirante = aspirante;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getNombreJefe() {
		return nombreJefe;
	}

	public void setNombreJefe(String nombreJefe) {
		this.nombreJefe = nombreJefe;
	}

	public String getPuestoJefe() {
		return puestoJefe;
	}

	public void setPuestoJefe(String puestoJefe) {
		this.puestoJefe = puestoJefe;
	}

	public Boolean getSolicitarInformacion() {
		return solicitarInformacion;
	}

	public void setSolicitarInformacion(Boolean solicitarInformacion) {
		this.solicitarInformacion = solicitarInformacion;
	}

	public String getRazonNoSolicitar() {
		return razonNoSolicitar;
	}

	public void setRazonNoSolicitar(String razonNoSolicitar) {
		this.razonNoSolicitar = razonNoSolicitar;
	}

	public Integer getIdExperienciaLaboral() {
		return idExperienciaLaboral;
	}

}
