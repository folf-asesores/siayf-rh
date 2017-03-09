/**
 * 
 */
package mx.gob.saludtlax.rh.nomina.productosnomina;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "estatus_productos_nomina")
public class EstatusProductoNominaDTO implements Serializable {

	private static final long serialVersionUID = 213087133101081752L;

	@Id
	@Column(name = "id_estatus_producto_nomina")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idEstatusConceptoNomina;

	@Column(name = "estatus")
	private String estatus;

	public Integer getIdEstatusConceptoNomina() {
		return idEstatusConceptoNomina;
	}
	public void setIdEstatusConceptoNomina(Integer idEstatusConceptoNomina) {
		this.idEstatusConceptoNomina = idEstatusConceptoNomina;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
}