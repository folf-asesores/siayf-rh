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
@Table(name = "estructuras_nominas_trailers")
public class EstructuraNominaTrailersEntity implements Serializable {
	private static final long serialVersionUID = 3111008823681449423L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_estructuras_nominas_trailers")
	private Integer idEstructurasNominasTrailers;

	@Column(name="num_emp")
	private String numEmp;
	@Column(name = "rfc")
	private String rfc;
	@Column(name = "num_cheq")
	private String numCheq;
	@Column(name = "t_concep")
	private Integer tConcep;
	@Column(name = "concep")
	private String concep;
	@Column(name = "importe")
	private BigDecimal importe;
	@Column(name = "anio")
	private String anio;
	@Column(name = "qna")
	private String qna;
	@Column(name = "pta_ant")
	private String ptaAnt;
	@Column(name = "tot_pagos")
	private String totPAgos;
	@Column(name = "pago_efec")
	private String pagoEfec;	
	@Column(name = "nom_prod")
	private String nomProd;
	@Column(name = "num_ctrol")
	private Integer numCtrol;
	@Column(name = "id_estructuras_nominas")
	private Integer idEstructurasNominas;
	@Column(name = "sub_cheque")
	private Integer subCheque;
	@Column(name = "id_concepto")
	private Integer idConcepto;
	@Column(name = "concepto_siif")
	private String conceptoSiif;
	@Column(name = "id_siif_bitacoras")
	private Integer idSiifBitacora;
	

	public Integer getIdEstructurasNominasTrailers() {
		return idEstructurasNominasTrailers;
	}
	public void setIdEstructurasNominasTrailers(Integer idEstructurasNominasTrailers) {
		this.idEstructurasNominasTrailers = idEstructurasNominasTrailers;
	}
	public String getNumEmp() {
		return numEmp;
	}
	public void setNumEmp(String numEmp) {
		this.numEmp = numEmp;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public String getNumCheq() {
		return numCheq;
	}
	public void setNumCheq(String numCheq) {
		this.numCheq = numCheq;
	}
	public Integer getTConcep() {
		return tConcep;
	}
	public void setTConcep(Integer tConcep) {
		this.tConcep = tConcep;
	}
	public String getConcep() {
		return concep;
	}
	public void setConcep(String concep) {
		this.concep = concep;
	}
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	public String getAnio() {
		return anio;
	}
	public void setAnio(String anio) {
		this.anio = anio;
	}
	public String getQna() {
		return qna;
	}
	public void setQna(String qna) {
		this.qna = qna;
	}
	public String getPtaAnt() {
		return ptaAnt;
	}
	public void setPtaAnt(String ptaAnt) {
		this.ptaAnt = ptaAnt;
	}
	public String getTotPAgos() {
		return totPAgos;
	}
	public void setTotPAgos(String totPAgos) {
		this.totPAgos = totPAgos;
	}
	public String getPagoEfec() {
		return pagoEfec;
	}
	public void setPagoEfec(String pagoEfec) {
		this.pagoEfec = pagoEfec;
	}
	public String getNomProd() {
		return nomProd;
	}
	public void setNomProd(String nomProd) {
		this.nomProd = nomProd;
	}
	public Integer getNumCtrol() {
		return numCtrol;
	}
	public void setNumCtrol(Integer numCtrol) {
		this.numCtrol = numCtrol;
	}
	public Integer getIdEstructurasNominas() {
		return idEstructurasNominas;
	}
	public void setIdEstructurasNominas(Integer idEstructurasNominas) {
		this.idEstructurasNominas = idEstructurasNominas;
	}
	public Integer getIdSiifBitacora() {
		return idSiifBitacora;
	}
	public void setIdSiifBitacora(Integer idSiifBitacora) {
		this.idSiifBitacora = idSiifBitacora;
	}
	public Integer getSubCheque() {
		return subCheque;
	}
	public void setSubCheque(Integer subCheque) {
		this.subCheque = subCheque;
	}
	public Integer getIdConcepto() {
		return idConcepto;
	}
	public void setIdConcepto(Integer idConcepto) {
		this.idConcepto = idConcepto;
	}
	public String getConceptoSiif() {
		return conceptoSiif;
	}
	public void setConceptoSiif(String conceptoSiif) {
		this.conceptoSiif = conceptoSiif;
	}
}