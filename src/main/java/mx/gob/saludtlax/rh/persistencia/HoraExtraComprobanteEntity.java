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
@Table(name = "horas_extras_comprobantes")
public class HoraExtraComprobanteEntity implements Serializable {
	private static final long serialVersionUID = 3111008823681449423L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_hora_extra")
	private Integer idHoraExtra;
	@Column(name = "dias")
	private Integer dia;
	@Column(name = "tipo_hora")
	private String tipoHora;
	@Column(name = "hora_extra")
	private Integer horaExtra;
	@Column(name = "importe_pagado")
	private BigDecimal importePagado;
	@Column(name = "id_comprobante")
	private Integer idComprobante;

	public Integer getIdHoraExtra() {
		return idHoraExtra;
	}

	public void setIdHoraExtra(Integer idHoraExtra) {
		this.idHoraExtra = idHoraExtra;
	}

	public Integer getDia() {
		return dia;
	}

	public void setDia(Integer dia) {
		this.dia = dia;
	}

	public String getTipoHora() {
		return tipoHora;
	}

	public void setTipoHora(String tipoHora) {
		this.tipoHora = tipoHora;
	}

	public Integer getHoraExtra() {
		return horaExtra;
	}

	public void setHoraExtra(Integer horaExtra) {
		this.horaExtra = horaExtra;
	}

	public BigDecimal getImportePagado() {
		return importePagado;
	}

	public void setImportePagado(BigDecimal importePagado) {
		this.importePagado = importePagado;
	}

	public Integer getIdComprobante() {
		return idComprobante;
	}

	public void setIdComprobante(Integer idComprobante) {
		this.idComprobante = idComprobante;
	}

}