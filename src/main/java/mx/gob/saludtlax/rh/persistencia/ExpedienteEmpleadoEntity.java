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
 * @since 13/06/2016 11:13:48
 * 
 */
@Entity
@Table(name = "expedientes_empleados")
public class ExpedienteEmpleadoEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4449808413299106135L;
	@Id
	@Column(name = "id_expediente_empleado")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idExpedienteEmpleado;

	@Column(name = "id_empleado")
	private Integer idEmpleado;

	@Column(name = "numero_expediente")
	private String numeroExpediente;

	public Integer getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getNumeroExpediente() {
		return numeroExpediente;
	}

	public void setNumeroExpediente(String numeroExpediente) {
		this.numeroExpediente = numeroExpediente;
	}

	public Integer getIdExpedienteEmpleado() {
		return idExpedienteEmpleado;
	}
}
