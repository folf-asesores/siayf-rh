/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Leila Schiaffini Ehuan
 * @since 05/10/2016 13:23:53
 * 
 */
@Entity
@Table(name = "estatus_puestos")
public class EstatusPuestosEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4506799067711110944L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_estatus")
	private Integer idEstatus;

	@Column(name = "estatus")
	private String estatus;

	@Column(name = "autorizado")
	private boolean autorizado;

	@Column(name = "disponible")
	private boolean disponible;

	@Column(name = "puesto_activo")
	private boolean puestoActivo;

	public boolean isPuestoActivo() {
		return puestoActivo;
	}

	public void setPuestoActivo(boolean puestoActivo) {
		this.puestoActivo = puestoActivo;
	}

	public boolean isAutorizado() {
		return autorizado;
	}

	public void setAutorizado(boolean autorizado) {
		this.autorizado = autorizado;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public Integer getIdEstatus() {
		return idEstatus;
	}

	public void setIdEstatus(Integer idEstatus) {
		this.idEstatus = idEstatus;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

}
