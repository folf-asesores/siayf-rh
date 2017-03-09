package mx.gob.saludtlax.rh.nomina.pensionalimenticia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BeneficiarioPensionAlimenticiaForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5788040582457479788L;
	
	public int idPensionAlimenticia;
	public int idEmpleado;
	public String rfc;
	public String beneficiario;
	public String oficio;
	public String numeroExpediente;
	public String numeroJuzgado;
	public Date fechaAlta;
	public int idTipoCoutaPension;
	public int estatus;
	public String numeroCuentaBancaria;
	public int idBanco;
	public BigDecimal valor;

	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

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

	public int getIdTipoCoutaPension() {
		return idTipoCoutaPension;
	}

	public void setIdTipoCoutaPension(int idTipoCoutaPension) {
		this.idTipoCoutaPension = idTipoCoutaPension;
	}

	public int getEstatus() {
		return estatus;
	}

	public int getIdPensionAlimenticia() {
		return idPensionAlimenticia;
	}

	public void setIdPensionAlimenticia(int idPensionAlimenticia) {
		this.idPensionAlimenticia = idPensionAlimenticia;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getNumeroCuentaBancaria() {
		return numeroCuentaBancaria;
	}

	public void setNumeroCuentaBancaria(String numeroCuentaBancaria) {
		this.numeroCuentaBancaria = numeroCuentaBancaria;
	}

	public int getIdBanco() {
		return idBanco;
	}

	public void setIdBanco(int idBanco) {
		this.idBanco = idBanco;
	}
	
	

}
