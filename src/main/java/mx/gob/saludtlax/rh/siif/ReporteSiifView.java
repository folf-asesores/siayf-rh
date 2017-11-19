
package mx.gob.saludtlax.rh.siif;

import java.io.Serializable;
import java.util.List;

import org.primefaces.model.StreamedContent;

import mx.gob.saludtlax.rh.configuracion.cuentabancaria.CuentaBancariaDTO;
import mx.gob.saludtlax.rh.configuracion.tiponomina.TipoNominaDTO;
import mx.gob.saludtlax.rh.siif.layout.SIIFEncabezadoDTO;

public class ReporteSiifView implements Serializable {

    private static final long serialVersionUID = -1444579854501658125L;

    private PaqueteEntradaFederalDTO paqueteEntrada;
    private String periodoCriterio;
    private Integer anioCriterio;

    private SiifBitacoraDTO reporteSiifSelect;
    private SIIFEncabezadoDTO siifEncabezadoSelect;
    private List<SiifBitacoraDTO> listReporteSiif;
    private List<CuentaBancariaDTO> cuentaBancariaList;
    private List<TipoNominaDTO> tipoNominaList;

    private List<SubfuenteFinanciamiento> subfuenteFinanciamientoList;
    private List<EstructuraNominaTrailersDTO> trailersLista;
    private SiifBitacoraDTO siifBitacoraProcesada;
    private SiifBitacoraDTO siifDeudores;
    private List<SIIFEncabezadoDTO> encabezadoList;
    private SIIFEncabezadoDTO encabezadoDTO;
    private List<SIIFEncabezadoDTO> encabezadoListSiif;

    private Boolean disabledImportar;
    private Boolean panelPrincipal;
    private Boolean panelResul;
    private Boolean panelDownload;
    private Boolean panelUpload;
    private Boolean panelResume;
    private Boolean panelDeudores;
    private Boolean panelEncabezado;
    private Boolean panelElimina;
    private Boolean panelUploadCont;
    private Boolean panelContrato;
    private Boolean panelNomina;

    private StreamedContent file;

    public void panelPrincipal() {
        panelPrincipal = Boolean.TRUE;
        panelResul = Boolean.FALSE;
        panelDownload = Boolean.FALSE;
        panelDeudores = Boolean.FALSE;
        panelElimina = Boolean.TRUE;
        disabledImportar = Boolean.TRUE;
        panelUploadCont = Boolean.TRUE;
    }

    public void panelDeudores() {
        panelPrincipal = Boolean.TRUE;
        panelResul = Boolean.FALSE;
        panelDownload = Boolean.FALSE;
        panelDeudores = Boolean.TRUE;
        disabledImportar = Boolean.TRUE;
    }

    public void panelDatosEncabezado() {
        panelPrincipal = Boolean.TRUE;
        panelEncabezado = Boolean.TRUE;
    }

    public String getPeriodoCriterio() {
        return periodoCriterio;
    }

    public void setPeriodoCriterio(String periodoCriterio) {
        this.periodoCriterio = periodoCriterio;
    }

    public SiifBitacoraDTO getReporteSiifSelect() {
        return reporteSiifSelect;
    }

    public void setReporteSiifSelect(SiifBitacoraDTO reporteSiifSelect) {
        this.reporteSiifSelect = reporteSiifSelect;
    }

    public Integer getAnioCriterio() {
        return anioCriterio;
    }

    public void setAnioCriterio(Integer anioCriterio) {
        this.anioCriterio = anioCriterio;
    }

    public List<SiifBitacoraDTO> getListReporteSiif() {
        return listReporteSiif;
    }

    public void setListReporteSiif(List<SiifBitacoraDTO> listReporteSiif) {
        this.listReporteSiif = listReporteSiif;
    }

    public Boolean getDisabledImportar() {
        return disabledImportar;
    }

    public void setDisabledImportar(Boolean disabledImportar) {
        this.disabledImportar = disabledImportar;
    }

    public Boolean getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(Boolean panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public Boolean getPanelResul() {
        return panelResul;
    }

    public void setPanelResul(Boolean panelResul) {
        this.panelResul = panelResul;
    }

    public Boolean getPanelDownload() {
        return panelDownload;
    }

    public void setPanelDownload(Boolean panelDownload) {
        this.panelDownload = panelDownload;
    }

    public PaqueteEntradaFederalDTO getPaqueteEntrada() {
        return paqueteEntrada;
    }

    public void setPaqueteEntrada(PaqueteEntradaFederalDTO paqueteEntrada) {
        this.paqueteEntrada = paqueteEntrada;
    }

    public StreamedContent getFile() {
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }

    public List<CuentaBancariaDTO> getCuentaBancariaList() {
        return cuentaBancariaList;
    }

    public void setCuentaBancariaList(List<CuentaBancariaDTO> cuentaBancariaList) {
        this.cuentaBancariaList = cuentaBancariaList;
    }

    public List<TipoNominaDTO> getTipoNominaList() {
        return tipoNominaList;
    }

    public void setTipoNominaList(List<TipoNominaDTO> tipoNominaList) {
        this.tipoNominaList = tipoNominaList;
    }

    public SiifBitacoraDTO getSiifBitacoraProcesada() {
        return siifBitacoraProcesada;
    }

    public void setSiifBitacoraProcesada(SiifBitacoraDTO siifBitacoraProcesada) {
        this.siifBitacoraProcesada = siifBitacoraProcesada;
    }

    public SiifBitacoraDTO getSiifDeudores() {
        return siifDeudores;
    }

    public void setSiifDeudores(SiifBitacoraDTO siifDeudores) {
        this.siifDeudores = siifDeudores;
    }

    public SIIFEncabezadoDTO getSiifEncabezadoSelect() {
        return siifEncabezadoSelect;
    }

    public void setSiifEncabezadoSelect(SIIFEncabezadoDTO siifEncabezadoSelect) {
        this.siifEncabezadoSelect = siifEncabezadoSelect;
    }

    public Boolean getPanelUpload() {
        return panelUpload;
    }

    public void setPanelUpload(Boolean panelUpload) {
        this.panelUpload = panelUpload;
    }

    public Boolean getPanelResume() {
        return panelResume;
    }

    public void setPanelResume(Boolean panelResume) {
        this.panelResume = panelResume;
    }

    public List<SubfuenteFinanciamiento> getSubfuenteFinanciamientoList() {
        return subfuenteFinanciamientoList;
    }

    public void setSubfuenteFinanciamientoList(List<SubfuenteFinanciamiento> subfuenteFinanciamientoList) {
        this.subfuenteFinanciamientoList = subfuenteFinanciamientoList;
    }

    public Boolean getPanelDeudores() {
        return panelDeudores;
    }

    public void setPanelDeudores(Boolean panelDeudores) {
        this.panelDeudores = panelDeudores;
    }

    public List<SIIFEncabezadoDTO> getEncabezadoList() {
        return encabezadoList;
    }

    public void setEncabezadoList(List<SIIFEncabezadoDTO> encabezadoList) {
        this.encabezadoList = encabezadoList;
    }

    public List<EstructuraNominaTrailersDTO> getTrailersLista() {
        return trailersLista;
    }

    public void setTrailersLista(List<EstructuraNominaTrailersDTO> trailersLista) {
        this.trailersLista = trailersLista;
    }

    public SIIFEncabezadoDTO getEncabezadoDTO() {
        return encabezadoDTO;
    }

    public void setEncabezadoDTO(SIIFEncabezadoDTO encabezadoDTO) {
        this.encabezadoDTO = encabezadoDTO;
    }

    public Boolean getPanelEncabezado() {
        return panelEncabezado;
    }

    public void setPanelEncabezado(Boolean panelEncabezado) {
        this.panelEncabezado = panelEncabezado;
    }

    public Boolean getPanelElimina() {
        return panelElimina;
    }

    public void setPanelElimina(Boolean panelElimina) {
        this.panelElimina = panelElimina;
    }

    public Boolean getPanelUploadCont() {
        return panelUploadCont;
    }

    public void setPanelUploadCont(Boolean panelUploadCont) {
        this.panelUploadCont = panelUploadCont;
    }

    public Boolean getPanelContrato() {
        return panelContrato;
    }

    public void setPanelContrato(Boolean panelContrato) {
        this.panelContrato = panelContrato;
    }

    public Boolean getPanelNomina() {
        return panelNomina;
    }

    public void setPanelNomina(Boolean panelNomina) {
        this.panelNomina = panelNomina;
    }

    public List<SIIFEncabezadoDTO> getEncabezadoListSiif() {
        return encabezadoListSiif;
    }

    public void setEncabezadoListSiif(List<SIIFEncabezadoDTO> encabezadoListSiif) {
        this.encabezadoListSiif = encabezadoListSiif;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
