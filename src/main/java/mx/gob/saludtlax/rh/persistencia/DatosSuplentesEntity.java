/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;

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
 * @Since 05/01/2017 20:57:58
 */
@Entity
@Table(name = "datos_suplentes")
public class DatosSuplentesEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 540809741701712854L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "rfc")
	private String rfc;

	@Column(name = "curp")
	private String curp;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "centro_responsabilidad")
	private String nombreCentroResponsabilidad;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_centro_responsabilidad")
	private CentroResponsabilidadEntity centroResponsabilidad;

	@Column(name = "funcion")
	private String funcion;

	@Column(name = "id_funcion")
	private Integer idFuncion;

	@Column(name = "domicilio")
	private String domicilio;

	@Column(name = "telefono")
	private String telefono;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public String getNombreCentroResponsabilidad() {
		return nombreCentroResponsabilidad;
	}

	public void setNombreCentroResponsabilidad(
			String nombreCentroResponsabilidad) {
		this.nombreCentroResponsabilidad = nombreCentroResponsabilidad;
	}

	public CentroResponsabilidadEntity getCentroResponsabilidad() {
		return centroResponsabilidad;
	}

	public void setCentroResponsabilidad(
			CentroResponsabilidadEntity centroResponsabilidad) {
		this.centroResponsabilidad = centroResponsabilidad;
	}

	public String getFuncion() {
		return funcion;
	}

	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}

	public Integer getIdFuncion() {
		return idFuncion;
	}

	public void setIdFuncion(Integer idFuncion) {
		this.idFuncion = idFuncion;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Integer getId() {
		return id;
	}

}
