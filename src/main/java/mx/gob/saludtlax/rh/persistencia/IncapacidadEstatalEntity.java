package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "incapacidades_comprobantes")
public class IncapacidadEstatalEntity implements Serializable {
	private static final long serialVersionUID = -1515628325730682997L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="IdIncapacidad")
	private Integer idIncapacidad;
	@Column(name = "DiasIncapacidad")
    private BigDecimal diasIncapacidad;
    @Column(name = "TipoIncapacidad")
    private int tipoIncapacidad;
    @Column(name = "Descuento")
    private BigDecimal descuento;

    public Integer getIdIncapacidad() {
		return idIncapacidad;
	}
	public void setIdIncapacidad(Integer idIncapacidad) {
		this.idIncapacidad = idIncapacidad;
	}
	public BigDecimal getDiasIncapacidad() {
		return diasIncapacidad;
	}
	public void setDiasIncapacidad(BigDecimal diasIncapacidad) {
		this.diasIncapacidad = diasIncapacidad;
	}
	public int getTipoIncapacidad() {
		return tipoIncapacidad;
	}
	public void setTipoIncapacidad(int tipoIncapacidad) {
		this.tipoIncapacidad = tipoIncapacidad;
	}
	public BigDecimal getDescuento() {
		return descuento;
	}
	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
	}
}