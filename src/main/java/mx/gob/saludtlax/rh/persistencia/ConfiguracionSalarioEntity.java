/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Leila Schiaffini Ehuan
 * @since 27/07/2016 17:27:31
 * 
 */
@Entity
@Table(name = "configuracion_salario")
public class ConfiguracionSalarioEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3053674008344661561L;
	@Id
	@Column(name = "id_configuracion_nomina")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idConfiguracionNomina;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_configuracion_presupuestal")
	private ConfiguracionPresupuestoEntity configuracionPresupuestal;

	@Column(name = "sueldo_mensual")
	private BigDecimal sueldoMensual;

	@Column(name = "sueldo_quincenal")
	private BigDecimal sueldoQuincenal;

	@Column(name = "id_tabulador_salario")
	private Integer idTabuladorSalario;

	public BigDecimal getSueldoQuincenal() {
		return sueldoQuincenal;
	}

	public void setSueldoQuincenal(BigDecimal sueldoQuincenal) {
		this.sueldoQuincenal = sueldoQuincenal;
	}

	public Integer getIdTabuladorSalario() {
		return idTabuladorSalario;
	}

	public void setIdTabuladorSalario(Integer idTabuladorSalario) {
		this.idTabuladorSalario = idTabuladorSalario;
	}

	public BigDecimal getSueldoMensual() {
		return sueldoMensual;
	}

	public void setSueldoMensual(BigDecimal sueldoMensual) {
		this.sueldoMensual = sueldoMensual;
	}

	public Integer getIdConfiguracionNomina() {
		return idConfiguracionNomina;
	}

}
