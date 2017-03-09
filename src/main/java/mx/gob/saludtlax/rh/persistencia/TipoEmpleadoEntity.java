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
 * @since 2016-10-18
 * 
 */
@Table(name = "tipos_empleados")
@Entity
public class TipoEmpleadoEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5220290263590061060L;
	@Id
	@Column(name = "id_tipo_empleado")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTipoEmpleado;

	@Column(name = "tipo_empleado")
	private String tipoEmpleado;

	public Integer getIdTipoEmpleado() {
		return idTipoEmpleado;
	}

	public void setIdTipoEmpleado(Integer idTipoEmpleado) {
		this.idTipoEmpleado = idTipoEmpleado;
	}

	public String getTipoEmpleado() {
		return tipoEmpleado;
	}

}
