package mx.gob.saludtlax.rh.nomina.pensionalimenticia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BeneficiarioPensionAlimienticiaDTO implements Serializable {
	private static final long serialVersionUID = -7362563092503803724L;

	private int idPensionAlimenticia;
	private String rfc;
	private String beneficiario;
	private String oficio;
	private String numeroExpediente;
	private String numeroJuzgado;
	private Date fechaAlta;
	private Date fechaBaja;
	private String claveCouta;
	private String estatus;
	private BigDecimal valor;
	private Integer idTipoCoutasPension;

	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public String getBeneficiario() {
		return beneficiario;
	}
	public void setBeneficiario(String beneficiario) {
		this.beneficiario = beneficiario;
	}
	public String getOficio() {
		return oficio;
	}
	public void setOficio(String oficio) {
		this.oficio = oficio;
	}
	public String getNumeroExpediente() {
		return numeroExpediente;
	}
	public void setNumeroExpediente(String numeroExpediente) {
		this.numeroExpediente = numeroExpediente;
	}
	public String getNumeroJuzgado() {
		return numeroJuzgado;
	}
	public void setNumeroJuzgado(String numeroJuzgado) {
		this.numeroJuzgado = numeroJuzgado;
	}
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public Date getFechaBaja() {
		return fechaBaja;
	}
	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	public String getClaveCouta() {
		return claveCouta;
	}
	public void setClaveCouta(String claveCouta) {
		this.claveCouta = claveCouta;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public int getIdPensionAlimenticia() {
		return idPensionAlimenticia;
	}
	public void setIdPensionAlimenticia(int idPensionAlimenticia) {
		this.idPensionAlimenticia = idPensionAlimenticia;
	}
    public Integer getIdTipoCoutasPension() {
        return idTipoCoutasPension;
    }
    public void setIdTipoCoutasPension(Integer idTipoCoutasPension) {
        this.idTipoCoutasPension = idTipoCoutasPension;
    }
}