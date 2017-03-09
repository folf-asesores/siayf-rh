package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="tipos_coutas_pensiones_alimenticias")
public class TipoCoutaPensionAlimenticiaEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1024581829501910223L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_tipo_couta_pension_alimenticia")
	private int idTipoCoutaPensionAlimenticia;

	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "clave")
	private String clave;

	public int getIdTipoCoutaPensionAlimenticia() {
		return idTipoCoutaPensionAlimenticia;
	}

	public void setIdTipoCoutaPensionAlimenticia(int idTipoCoutaPensionAlimenticia) {
		this.idTipoCoutaPensionAlimenticia = idTipoCoutaPensionAlimenticia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

}
