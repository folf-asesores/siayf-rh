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

@Entity
@Table(name = "estatus_movimientos_nomina")
public class EstatusMovimientoNominaEntity implements Serializable {

	private static final long serialVersionUID = 211187133101081733L;

	@Id
	@Column(name = "id_estatus_movimiento_nomina")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idEstatusMovimientoNomina;

	@Column(name = "estatus")
	private String estatus;

	public Integer getIdEstatusMovimientoNomina() {
		return idEstatusMovimientoNomina;
	}
	public void setIdEstatusMovimientoNomina(Integer idEstatusMovimientoNomina) {
		this.idEstatusMovimientoNomina = idEstatusMovimientoNomina;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
}