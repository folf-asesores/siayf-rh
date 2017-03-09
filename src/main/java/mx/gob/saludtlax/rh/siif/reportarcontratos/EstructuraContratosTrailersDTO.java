package mx.gob.saludtlax.rh.siif.reportarcontratos;

import java.io.Serializable;
import java.math.BigDecimal;

public class EstructuraContratosTrailersDTO implements Serializable {


	private static final long serialVersionUID = -4226990086634174773L;

	private String idEstructura;
	private Integer idEstructurasContratosTrailers;
	private String rfc;
	private String numEmp;
	private String numCheq;
	private Integer tConcep;
	private String concep;
	private BigDecimal importe;
	private String anio;
	private String qna;
	private String ptaAnt;
	private String totPagos;
	private String pagoEfec;
	private String nomProd;
	private Integer numControl;
	
	private Integer idEstructurasContratos;
	private Integer idSiifBitacoras;
	private Integer idSiifEncabezados;
	private Integer idConcepto;
	private String conceptosSiif;
	private Integer idProductoNomina;
	
	// **************** Getters and Setters *********************** //
			
	
	public String getIdEstructura() {
		return idEstructura;
	}
	public void setIdEstructura(String idEstructura) {
		this.idEstructura = idEstructura;
	}
	
	public Integer getIdEstructurasContratosTrailers() {
		return idEstructurasContratosTrailers;
	}
	public void setIdEstructurasContratosTrailers(Integer idEstructurasContratosTrailers) {
		this.idEstructurasContratosTrailers = idEstructurasContratosTrailers;
	}
	public String getRfc() {
		return rfc;
	}
	
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public String getNumEmp() {
		return numEmp;
	}
	public void setNumEmp(String numEmp) {
		this.numEmp = numEmp;
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
	public void settConcep(Integer tConcep) {
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
	public String getTotPagos() {
		return totPagos;
	}
	public void setTotPagos(String totPagos) {
		this.totPagos = totPagos;
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
	public Integer getNumControl() {
		return numControl;
	}
	public void setNumControl(Integer numControl) {
		this.numControl = numControl;
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
	public Integer getIdProductoNomina() {
		return idProductoNomina;
	}
	public void setIdProductoNomina(Integer idProductoNomina) {
		this.idProductoNomina = idProductoNomina;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "EstructuraContratosTrailersDTO [idEstructura=" + idEstructura + ", idEstructurasContratosTrailers="
				+ idEstructurasContratosTrailers + ", rfc=" + rfc + ", numEmp=" + numEmp + ", numCheq=" + numCheq
				+ ", tConcep=" + tConcep + ", concep=" + concep + ", importe=" + importe + ", anio=" + anio + ", qna="
				+ qna + ", ptaAnt=" + ptaAnt + ", totPagos=" + totPagos + ", pagoEfec=" + pagoEfec + ", nomProd="
				+ nomProd + ", numControl=" + numControl + ", idEstructurasContratos=" + idEstructurasContratos
				+ ", idSiifBitacoras=" + idSiifBitacoras + ", idSiifEncabezados=" + idSiifEncabezados + ", idConcepto="
				+ idConcepto + ", conceptosSiif=" + conceptosSiif + "]";
	}

		

}