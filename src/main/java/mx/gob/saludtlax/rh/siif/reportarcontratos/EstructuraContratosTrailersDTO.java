
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
    private Integer idSubfuenteFinanciamiento;
    private Integer idProductoNomina;
    private Integer idPrograma;

    // **************** Getters and Setters *********************** //

    public String getIdEstructura() {
        return this.idEstructura;
    }

    public void setIdEstructura(String idEstructura) {
        this.idEstructura = idEstructura;
    }

    public Integer getIdEstructurasContratosTrailers() {
        return this.idEstructurasContratosTrailers;
    }

    public void setIdEstructurasContratosTrailers(Integer idEstructurasContratosTrailers) {
        this.idEstructurasContratosTrailers = idEstructurasContratosTrailers;
    }

    public String getRfc() {
        return this.rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getNumEmp() {
        return this.numEmp;
    }

    public void setNumEmp(String numEmp) {
        this.numEmp = numEmp;
    }

    public String getNumCheq() {
        return this.numCheq;
    }

    public void setNumCheq(String numCheq) {
        this.numCheq = numCheq;
    }

    public Integer gettConcep() {
        return this.tConcep;
    }

    public void settConcep(Integer tConcep) {
        this.tConcep = tConcep;
    }

    public String getConcep() {
        return this.concep;
    }

    public void setConcep(String concep) {
        this.concep = concep;
    }

    public BigDecimal getImporte() {
        return this.importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public String getAnio() {
        return this.anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getQna() {
        return this.qna;
    }

    public void setQna(String qna) {
        this.qna = qna;
    }

    public String getPtaAnt() {
        return this.ptaAnt;
    }

    public void setPtaAnt(String ptaAnt) {
        this.ptaAnt = ptaAnt;
    }

    public String getTotPagos() {
        return this.totPagos;
    }

    public void setTotPagos(String totPagos) {
        this.totPagos = totPagos;
    }

    public String getPagoEfec() {
        return this.pagoEfec;
    }

    public void setPagoEfec(String pagoEfec) {
        this.pagoEfec = pagoEfec;
    }

    public String getNomProd() {
        return this.nomProd;
    }

    public void setNomProd(String nomProd) {
        this.nomProd = nomProd;
    }

    public Integer getNumControl() {
        return this.numControl;
    }

    public void setNumControl(Integer numControl) {
        this.numControl = numControl;
    }

    public Integer getIdEstructurasContratos() {
        return this.idEstructurasContratos;
    }

    public void setIdEstructurasContratos(Integer idEstructurasContratos) {
        this.idEstructurasContratos = idEstructurasContratos;
    }

    public Integer getIdSiifBitacoras() {
        return this.idSiifBitacoras;
    }

    public void setIdSiifBitacoras(Integer idSiifBitacoras) {
        this.idSiifBitacoras = idSiifBitacoras;
    }

    public Integer getIdSiifEncabezados() {
        return this.idSiifEncabezados;
    }

    public void setIdSiifEncabezados(Integer idSiifEncabezados) {
        this.idSiifEncabezados = idSiifEncabezados;
    }

    public Integer getIdConcepto() {
        return this.idConcepto;
    }

    public void setIdConcepto(Integer idConcepto) {
        this.idConcepto = idConcepto;
    }

    public String getConceptosSiif() {
        return this.conceptosSiif;
    }

    public void setConceptosSiif(String conceptosSiif) {
        this.conceptosSiif = conceptosSiif;
    }

    public Integer getIdSubfuenteFinanciamiento() {
        return this.idSubfuenteFinanciamiento;
    }

    public void setIdSubfuenteFinanciamiento(Integer idSubfuenteFinanciamiento) {
        this.idSubfuenteFinanciamiento = idSubfuenteFinanciamiento;
    }

    public Integer getIdProductoNomina() {
        return this.idProductoNomina;
    }

    public void setIdProductoNomina(Integer idProductoNomina) {
        this.idProductoNomina = idProductoNomina;
    }

    public Integer getIdPrograma() {
        return this.idPrograma;
    }

    public void setIdPrograma(Integer idPrograma) {
        this.idPrograma = idPrograma;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "EstructuraContratosTrailersDTO [idEstructura=" + this.idEstructura + ", idEstructurasContratosTrailers=" + this.idEstructurasContratosTrailers
                + ", rfc=" + this.rfc + ", numEmp=" + this.numEmp + ", numCheq=" + this.numCheq + ", tConcep=" + this.tConcep + ", concep=" + this.concep
                + ", importe=" + this.importe + ", anio=" + this.anio + ", qna=" + this.qna + ", ptaAnt=" + this.ptaAnt + ", totPagos=" + this.totPagos
                + ", pagoEfec=" + this.pagoEfec + ", nomProd=" + this.nomProd + ", numControl=" + this.numControl + ", idEstructurasContratos="
                + this.idEstructurasContratos + ", idSiifBitacoras=" + this.idSiifBitacoras + ", idSiifEncabezados=" + this.idSiifEncabezados + ", idConcepto="
                + this.idConcepto + ", conceptosSiif=" + this.conceptosSiif + "]";
    }

}