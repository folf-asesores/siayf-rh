package mx.gob.saludtlax.rh.siif.reportarcontratos;

import java.util.List;

import org.primefaces.model.UploadedFile;


import mx.gob.saludtlax.rh.configuracion.cuentabancaria.CuentaBancariaDTO;
import mx.gob.saludtlax.rh.configuracion.tiponomina.TipoNominaDTO;
import mx.gob.saludtlax.rh.siif.EstructuraNominaTrailersDTO;
import mx.gob.saludtlax.rh.siif.PaqueteEntradaFederalDTO;
import mx.gob.saludtlax.rh.siif.SiifBitacoraDTO;
import mx.gob.saludtlax.rh.siif.SubfuenteFinanciamiento;
import mx.gob.saludtlax.rh.siif.layout.SIIFEncabezadoDTO;


import mx.gob.saludtlax.rh.configuracion.cuentabancaria.CuentaBancariaDTO;
import mx.gob.saludtlax.rh.configuracion.tiponomina.TipoNominaDTO;
import mx.gob.saludtlax.rh.siif.PaqueteEntradaContratoDTO;
import mx.gob.saludtlax.rh.siif.SiifBitacoraDTO;


public class ReportarSiifContratosView {

	private PaqueteEntradaFederalDTO paqueteEntrada;
	private String periodoCriterio;
	private Integer anioCriterio;

	private SiifBitacoraDTO reporteSiifSelect;
	private SIIFEncabezadoDTO siifEncabezadoSelect;
	private List<SiifBitacoraDTO> listReporteSiif;
	//private List<CuentaBancariaDTO> cuentaBancariaList;
	//private List<TipoNominaDTO> tipoNominaList;
	private List<SubfuenteFinanciamiento> subfuenteFinanciamientoList;
	private List<EstructuraNominaTrailersDTO> trailersLista;
	//private SiifBitacoraDTO siifBitacoraProcesada;	
	
	private Boolean panelPrincipal;
	
	private UploadedFile dat;
	private UploadedFile tra;
	private UploadedFile cont;
	
	private PaqueteEntradaContratoDTO paqueteEntradaContra = new PaqueteEntradaContratoDTO();
	private List<CuentaBancariaDTO> cuentaBancariaList;
	private List<TipoNominaDTO> tipoNominaList;
	private SiifBitacoraDTO siifBitacoraProcesada;


	public void panelPrincipal() {
		panelPrincipal = Boolean.TRUE;
	}

	public PaqueteEntradaContratoDTO getPaqueteEntradaContra() {
		return paqueteEntradaContra;
	}

	public void setPaqueteEntradaContra(PaqueteEntradaContratoDTO paqueteEntrada) {
		this.paqueteEntradaContra = paqueteEntrada;
	}

	public List<CuentaBancariaDTO> getCuentaBancariaList() {
		return cuentaBancariaList;
	}

	public void setCuentaBancariaList(List<CuentaBancariaDTO> cuentaBancariaList) {
		this.cuentaBancariaList = cuentaBancariaList;
	}


	public UploadedFile getCont() {
		return cont;
	}

	public List<TipoNominaDTO> getTipoNominaList() {
		return tipoNominaList;

	}

	public void setTipoNominaList(List<TipoNominaDTO> tipoNominaList) {
		this.tipoNominaList = tipoNominaList;
	}


	public Boolean getPanelPrincipal() {
		return panelPrincipal;
	}

	public void setPanelPrincipal(Boolean panelPrincipal) {
		this.panelPrincipal = panelPrincipal;
	}

	public PaqueteEntradaFederalDTO getPaqueteEntrada() {
		return paqueteEntrada;
	}

	public void setPaqueteEntrada(PaqueteEntradaFederalDTO paqueteEntrada) {
		this.paqueteEntrada = paqueteEntrada;
	}

	public String getPeriodoCriterio() {
		return periodoCriterio;
	}

	public void setPeriodoCriterio(String periodoCriterio) {
		this.periodoCriterio = periodoCriterio;
	}

	public Integer getAnioCriterio() {
		return anioCriterio;
	}

	public void setAnioCriterio(Integer anioCriterio) {
		this.anioCriterio = anioCriterio;
	}

	public SiifBitacoraDTO getReporteSiifSelect() {
		return reporteSiifSelect;
	}

	public void setReporteSiifSelect(SiifBitacoraDTO reporteSiifSelect) {
		this.reporteSiifSelect = reporteSiifSelect;
	}

	public SIIFEncabezadoDTO getSiifEncabezadoSelect() {
		return siifEncabezadoSelect;
	}

	public void setSiifEncabezadoSelect(SIIFEncabezadoDTO siifEncabezadoSelect) {
		this.siifEncabezadoSelect = siifEncabezadoSelect;
	}

	public List<SiifBitacoraDTO> getListReporteSiif() {
		return listReporteSiif;
	}

	public void setListReporteSiif(List<SiifBitacoraDTO> listReporteSiif) {
		this.listReporteSiif = listReporteSiif;
	}

//	public List<CuentaBancariaDTO> getCuentaBancariaList() {
//		return cuentaBancariaList;
//	}
//
//	public void setCuentaBancariaList(List<CuentaBancariaDTO> cuentaBancariaList) {
//		this.cuentaBancariaList = cuentaBancariaList;
//	}
//
//	public List<TipoNominaDTO> getTipoNominaList() {
//		return tipoNominaList;
//	}
//
//	public void setTipoNominaList(List<TipoNominaDTO> tipoNominaList) {
//		this.tipoNominaList = tipoNominaList;
//	}

	public List<SubfuenteFinanciamiento> getSubfuenteFinanciamientoList() {
		return subfuenteFinanciamientoList;
	}

	public void setSubfuenteFinanciamientoList(List<SubfuenteFinanciamiento> subfuenteFinanciamientoList) {
		this.subfuenteFinanciamientoList = subfuenteFinanciamientoList;
	}

	public List<EstructuraNominaTrailersDTO> getTrailersLista() {
		return trailersLista;
	}

	public void setTrailersLista(List<EstructuraNominaTrailersDTO> trailersLista) {
		this.trailersLista = trailersLista;
	}

	public SiifBitacoraDTO getSiifBitacoraProcesada() {
		return siifBitacoraProcesada;
	}

	public void setSiifBitacoraProcesada(SiifBitacoraDTO siifBitacoraProcesada) {
		this.siifBitacoraProcesada = siifBitacoraProcesada;
	}	

	
	
	
	
}

	
	

