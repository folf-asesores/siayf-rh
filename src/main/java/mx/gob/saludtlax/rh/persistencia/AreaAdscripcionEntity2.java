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
@Entity
@Table(name = "areas_adscripcion")
public class AreaAdscripcionEntity2 implements Serializable{
private static final long serialVersionUID = 8530750267523022132L;

	@Id
	@Column(name="id_area_adscripcion")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idAreaAdscripcion;

	@Column(name="clave")
	private String clave;

	@Column(name="area_adscripcion")
	private String areaAdscripcion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_clues")
	private CluesEntity clues;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_centro_pago")
	private CentroPagoEntity centros_pago;

	public Integer getIdAreaAdscripcion() {
		return idAreaAdscripcion;
	}

	public void setIdAreaAdscripcion(Integer idAreaAdscripcion) {
		this.idAreaAdscripcion = idAreaAdscripcion;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getAreaAdscripcion() {
		return areaAdscripcion;
	}

	public void setAreaAdscripcion(String areaAdscripcion) {
		this.areaAdscripcion = areaAdscripcion;
	}

	public CluesEntity getClues() {
		return clues;
	}

	public void setClues(CluesEntity clues) {
		this.clues = clues;
	}

	public CentroPagoEntity getCentros_pago() {
		return centros_pago;
	}

	public void setCentros_pago(CentroPagoEntity centros_pago) {
		this.centros_pago = centros_pago;
	}

}
