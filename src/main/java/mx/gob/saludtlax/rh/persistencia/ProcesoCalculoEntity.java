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
 *
 * @Since 01/12/2016 16:49:40
 */
@Entity
@Table(name = "proceso")
public class ProcesoCalculoEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3827643241148484795L;
	@Id
	@Column(name = "id_proceso")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProceso;
	@Column(name = "en_proceso")
	private boolean enProceso;
	@Column(name = "numero_procesado")
	private int numeroProcesado;
	@Column(name = "tipo_proceso")
	private int tipoProceso;

	public Integer getIdProceso() {
		return idProceso;
	}
	public void setIdProceso(Integer idProceso) {
		this.idProceso = idProceso;
	}
	public boolean isEnProceso() {
		return enProceso;
	}
	public void setEnProceso(boolean enProceso) {
		this.enProceso = enProceso;
	}
	public int getNumeroProcesado() {
		return numeroProcesado;
	}
	public void setNumeroProcesado(int numeroProcesado) {
		this.numeroProcesado = numeroProcesado;
	}
	public int getTipoProceso() {
		return tipoProceso;
	}
	public void setTipoProceso(int tipoProceso) {
		this.tipoProceso = tipoProceso;
	}
}