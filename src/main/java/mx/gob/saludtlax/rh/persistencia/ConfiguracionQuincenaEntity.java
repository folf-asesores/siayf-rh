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
 * @Since 30/10/2016 14:27:10
 */
@Entity
@Table(name = "configuracion_quincenas")
public class ConfiguracionQuincenaEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3741128872752583649L;
	@Id
	@Column(name = "id_configuracion")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idConfiguracion;

	@Column(name = "id_mes")
	private int mes;

	@Column(name = "limite_inferior")
	private int limiteInferior;

	@Column(name = "limite_superior")
	private int limiteSuperior;

	@Column(name = "numero_quincena")
	private int numeroQuincena;

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getLimiteInferior() {
		return limiteInferior;
	}

	public void setLimiteInferior(int limiteInferior) {
		this.limiteInferior = limiteInferior;
	}

	public int getLimiteSuperior() {
		return limiteSuperior;
	}

	public void setLimiteSuperior(int limiteSuperior) {
		this.limiteSuperior = limiteSuperior;
	}

	public int getNumeroQuincena() {
		return numeroQuincena;
	}

	public void setNumeroQuincena(int numeroQuincena) {
		this.numeroQuincena = numeroQuincena;
	}

	public Integer getIdConfiguracion() {
		return idConfiguracion;
	}

}
