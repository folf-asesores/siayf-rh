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
 * @Since 26/01/2017 14:28:44
 */
@Entity
@Table(name = "base_final_federal")
public class BaseFederalEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 611076323400948827L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo_contratacion")
	private TipoContratacionEntity tipoContratacion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo_nombramiento")
	private TiposNombramientosEntity nombramiento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_puesto")
	private PuestoGeneralEntity puesto;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_empleado")
	private EmpleadoEntity empleado;

	@Column(name = "nuevo_puesto")
	private boolean nuevoPuesto;

	@Column(name = "doble_laboral")
	private boolean dobleLaboral;

	@Column(name = "sin_laboral")
	private boolean sinLaboral;

	@Column(name = "numero_puesto")
	private String numeroPuesto;

	@Column(name = "id_inventario")
	private Integer idInventario;

	public Integer getIdInventario() {
		return idInventario;
	}

	public void setIdInventario(Integer idInventario) {
		this.idInventario = idInventario;
	}

	public boolean isSinLaboral() {
		return sinLaboral;
	}

	public void setSinLaboral(boolean sinLaboral) {
		this.sinLaboral = sinLaboral;
	}

	public String getNumeroPuesto() {
		return numeroPuesto;
	}

	public void setNumeroPuesto(String numeroPuesto) {
		this.numeroPuesto = numeroPuesto;
	}

	public boolean isDobleLaboral() {
		return dobleLaboral;
	}

	public void setDobleLaboral(boolean dobleLaboral) {
		this.dobleLaboral = dobleLaboral;
	}

	public boolean isNuevoPuesto() {
		return nuevoPuesto;
	}

	public void setNuevoPuesto(boolean nuevoPuesto) {
		this.nuevoPuesto = nuevoPuesto;
	}

	public TipoContratacionEntity getTipoContratacion() {
		return tipoContratacion;
	}

	public void setTipoContratacion(TipoContratacionEntity tipoContratacion) {
		this.tipoContratacion = tipoContratacion;
	}

	public TiposNombramientosEntity getNombramiento() {
		return nombramiento;
	}

	public void setNombramiento(TiposNombramientosEntity nombramiento) {
		this.nombramiento = nombramiento;
	}

	public PuestoGeneralEntity getPuesto() {
		return puesto;
	}

	public void setPuesto(PuestoGeneralEntity puesto) {
		this.puesto = puesto;
	}

	public EmpleadoEntity getEmpleado() {
		return empleado;
	}

	public void setEmpleado(EmpleadoEntity empleado) {
		this.empleado = empleado;
	}

}
