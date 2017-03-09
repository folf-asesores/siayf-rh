/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Leila Schiaffini Ehuan
 * @since 16/08/2016 11:30:58
 * 
 */
@Entity
@Table(name = "temporal_padron_contratos_federales")
public class TempContratosFederalesEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9169525085157280002L;
	@Id
	@Column(name = "id_temporal")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "rfc")
	private String rfc;

	@Column(name = "sueldo_mensual")
	private BigDecimal sueldoMensual;

	@Column(name = "id_temporal_configuracion_empleado")
	private Integer idTemporalConfiguracionEmpleado;

	@Column(name = "duplicados")
	private Boolean duplicados;

	@Column(name = "programa")
	private String programa;

	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

	public Boolean getDuplicados() {
		return duplicados;
	}

	public void setDuplicados(Boolean duplicados) {
		this.duplicados = duplicados;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public BigDecimal getSueldoMensual() {
		return sueldoMensual;
	}

	public void setSueldoMensual(BigDecimal sueldoMensual) {
		this.sueldoMensual = sueldoMensual;
	}

	public Integer getIdTemporalConfiguracionEmpleado() {
		return idTemporalConfiguracionEmpleado;
	}

	public void setIdTemporalConfiguracionEmpleado(
			Integer idTemporalConfiguracionEmpleado) {
		this.idTemporalConfiguracionEmpleado = idTemporalConfiguracionEmpleado;
	}

	public Integer getId() {
		return id;
	}

}
