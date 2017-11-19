
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
@Table(name = "estructuras_contratos_trailers")
public class EstructuraContratoTrailersEntity implements Serializable {

    private static final long serialVersionUID = 3111008823681449423L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estructuras_contratos_trailers")
    private Integer idEstructurasContratosTrailers;

    @Column(name = "num_emp")
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

    @Column(name = "id_estructuras_contratos")
    private Integer idEstructurasContratos;
    @Column(name = "id_siif_bitacoras")
    private Integer idSiifBitacoras;
    @Column(name = "id_siif_encabezados")
    private Integer idSiifEncabezados;
    @Column(name = "id_concepto")
    private Integer idConcepto;
    @Column(name = "concepto_siif")
    private String conceptosSiif;

    /**
     * **************Getters and Setters**********
     */
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

    public Integer gettConcep() {
        return tConcep;
    }

    public void setTConcep(Integer tConcep) {
        this.tConcep = tConcep;
    }

    public String geTConcep() {
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

    public Integer getIdEstructurasContratosTrailers() {
        return idEstructurasContratosTrailers;
    }

    public void setIdEstructurasContratosTrailers(Integer idEstructurasContratosTrailers) {
        this.idEstructurasContratosTrailers = idEstructurasContratosTrailers;
    }

    public Integer getIdEstructurasContratos() {
        return idEstructurasContratos;
    }

    public void setIdEstructurasContratos(Integer idEstructurasContratos) {
        this.idEstructurasContratos = idEstructurasContratos;
    }

    public Integer getIdSiifBitacoras() {
        return idSiifBitacoras;
    }

    public void setIdSiifBitacoras(Integer idSiifBitacoras) {
        this.idSiifBitacoras = idSiifBitacoras;
    }

    public Integer getIdSiifEncabezados() {
        return idSiifEncabezados;
    }

    public void setIdSiifEncabezados(Integer idSiifEncabezados) {
        this.idSiifEncabezados = idSiifEncabezados;
    }

    public Integer getIdConcepto() {
        return idConcepto;
    }

    public void setIdConcepto(Integer idConcepto) {
        this.idConcepto = idConcepto;
    }

    public String getConceptosSiif() {
        return conceptosSiif;
    }

    public void setConceptosSiif(String conceptosSiif) {
        this.conceptosSiif = conceptosSiif;
    }

    public String getConcep() {
        return concep;
    }

    public void settConcep(Integer tConcep) {
        this.tConcep = tConcep;
    }
}
