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
public class IncapacidadComprobanteEntity implements Serializable {
	private static final long serialVersionUID = -1515628325730682997L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_incapacidad")
	private Integer idIncapacidad;
	@Column(name="dia_incapacidad")
	private BigDecimal diaIncapacidad;
	@Column(name="tipo_incapacidad")
	private Integer tipoIncapacidad;
	@Column(name="descuento")
	private BigDecimal descuento;
	@Column(name="id_comprobante")
	private Integer idComprobante;
	public Integer getIdIncapacidad() {
		return idIncapacidad;
	}
	public void setIdIncapacidad(Integer idIncapacidad) {
		this.idIncapacidad = idIncapacidad;
	}
	public BigDecimal getDiaIncapacidad() {
		return diaIncapacidad;
	}
	public void setDiaIncapacidad(BigDecimal diaIncapacidad) {
		this.diaIncapacidad = diaIncapacidad;
	}
	public Integer getTipoIncapacidad() {
		return tipoIncapacidad;
	}
	public void setTipoIncapacidad(Integer tipoIncapacidad) {
		this.tipoIncapacidad = tipoIncapacidad;
	}
	public BigDecimal getDescuento() {
		return descuento;
	}
	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
	}
	public Integer getIdComprobante() {
		return idComprobante;
	}
	public void setIdComprobante(Integer idComprobante) {
		this.idComprobante = idComprobante;
	}
	
	
	
}