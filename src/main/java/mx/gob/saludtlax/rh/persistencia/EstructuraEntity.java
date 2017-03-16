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
 * @since 13/03/2017
 */
@Entity
@Table(name = "estructura")
public class EstructuraEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5223854657573222775L;
	@Id
	@Column(name = "id_estructura")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idEstructura;

	@Column(name = "id_inventario_vacante")
	private Integer idPuesto;

	@Column(name = "id_subfuncion_trabajador")
	private String subfuncion;

	@Column(name = "tabulador_puesto")
	private String tabuladorPuesto;

	@Column(name = "pagaduria")
	private String pagaduria;

	@Column(name = "indicador_mando")
	private String indicadorMando;

	@Column(name = "tipo_unidad")
	private String tipoUnidad;

	@Column(name = "tipo_pago")
	private String tipoPago;

	@Column(name = "financiamiento_federal")
	private String financiamientoFederal;

	@Column(name = "jornada_trabajo")
	private String jornadaTrabajo;

	public String lccEstructuraNomina() {
		return "Estructura Nomina [idEstructura=" + idEstructura + ", subfuncion=" + subfuncion + ", tabuladorPuesto="
				+ tabuladorPuesto + ", pagaduria=" + pagaduria + ", indicadorMando=" + indicadorMando + ", tipoUnidad="
				+ tipoUnidad + ", tipoPago=" + tipoPago + ", financiamientoFederal=" + financiamientoFederal
				+ ", jornadaTrabajo=" + jornadaTrabajo + "]";
	}

	public Integer getIdPuesto() {
		return idPuesto;
	}

	public void setIdPuesto(Integer idPuesto) {
		this.idPuesto = idPuesto;
	}

	public String getSubfuncion() {
		return subfuncion;
	}

	public void setSubfuncion(String subfuncion) {
		this.subfuncion = subfuncion;
	}

	public String getTabuladorPuesto() {
		return tabuladorPuesto;
	}

	public void setTabuladorPuesto(String tabuladorPuesto) {
		this.tabuladorPuesto = tabuladorPuesto;
	}

	public String getPagaduria() {
		return pagaduria;
	}

	public void setPagaduria(String pagaduria) {
		this.pagaduria = pagaduria;
	}

	public String getIndicadorMando() {
		return indicadorMando;
	}

	public void setIndicadorMando(String indicadorMando) {
		this.indicadorMando = indicadorMando;
	}

	public String getTipoUnidad() {
		return tipoUnidad;
	}

	public void setTipoUnidad(String tipoUnidad) {
		this.tipoUnidad = tipoUnidad;
	}

	public String getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}

	public String getFinanciamientoFederal() {
		return financiamientoFederal;
	}

	public void setFinanciamientoFederal(String financiamientoFederal) {
		this.financiamientoFederal = financiamientoFederal;
	}

	public String getJornadaTrabajo() {
		return jornadaTrabajo;
	}

	public void setJornadaTrabajo(String jornadaTrabajo) {
		this.jornadaTrabajo = jornadaTrabajo;
	}

	public Integer getIdEstructura() {
		return idEstructura;
	}

}
