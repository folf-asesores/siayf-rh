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
@Table(name = "Retencion")
public class RetencionEntity implements Serializable {
	private static final long serialVersionUID = 3872560620716751355L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="IdRetencion")
	private Integer idRetencion;
	@Column(name = "impuesto")
    private String impuesto;
    @Column(name = "importe")
    private BigDecimal importe;

    public Integer getIdRetencion() {
		return idRetencion;
	}
	public void setIdRetencion(Integer idRetencion) {
		this.idRetencion = idRetencion;
	}
	public String getImpuesto() {
		return impuesto;
	}
	public void setImpuesto(String impuesto) {
		this.impuesto = impuesto;
	}
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
}