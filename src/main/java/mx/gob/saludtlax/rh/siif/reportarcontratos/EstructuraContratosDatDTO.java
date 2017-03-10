package mx.gob.saludtlax.rh.siif.reportarcontratos;

import java.io.Serializable;
import java.math.BigDecimal;

public class EstructuraContratosDatDTO implements Serializable {


	private static final long serialVersionUID = -4226990086634174773L;

	private String idEstructura;
	private Integer idEstructurasContratos;
	private String numEmp;
	private String rfc;
	private String curp;
	private String nombre;
	private String sar;
	private String bancoA;
	private String bancoN;
	private String numCta;
	private String clabe;
	private String funcion;
	private String cp;
	private String calle;
	private String puesto;
	private String desPuesto;
	private String ur;
	private String gf;
	private String fn;
	private String sf;
	private String pg;
	private String ai;
	private String pp;
	private String partida;
	private String puestoTab;
	private String numPto;
	private String edo;
	private String mpio;
	private String cr;
	private String ci;
	private String pagaD;
	private String financiamiento;
	private String tabPto;
	private String nivel;
	private String rango;
	private String indMando;
	private String horas;
	private String porcent;
	private String tipoTrab;
	private String nivelPto;
	private String indEmp;
	private String fecInicial;
	private String fecFinal;
	private String fecIngreso;
	private String tipoMov;
	private String fPago;
	private String pPagoI;
	private String pPagoF;
	private String pQnaI;
	private String pQnaF;
	private String qnaReal;
	private String anioReal;
	private Integer tipoPago;
	private String instruA;
	private String instruN;
	private BigDecimal per;
	private BigDecimal ded;
	private BigDecimal neto;
	private Integer noTrail;
	private Integer diasLab;
	private String nomProd;
	private Integer numCtrol;
	private String numCheq;
	private String digVer;
	private String jornada;
	private String diasP;
	private String cicloF;
	private String numAport;
	private BigDecimal acumF;
	private Integer faltas;
	private String clues;
	private Integer porPen01;
	private Integer porPen02;
	private Integer porPen03;
	private Integer porPen04;
	private Integer porPen05;
	private Integer issste;
	private Integer tipoUni;
	private String crespDes;
	private Integer idNombramiento;
	private Integer idSiifEncabezados;
	private String tipoEmisionNomina;
	private Integer idSiifBitacoras;
	private Integer idSubfuentaFinanciamiento;
	private Integer idProductoNomina;
	private Integer idPrograma;
	
	// **************** Getters and Setters *********************** //
	
	public String getIdEstructura() {
		return idEstructura;
	}
	public void setIdEstructura(String idEstructura) {
		this.idEstructura = idEstructura;
	}
	public Integer getIdEstructurasContratos() {
		return idEstructurasContratos;
	}
	public void setIdEstructurasContratos(Integer idEstructurasContratos) {
		this.idEstructurasContratos = idEstructurasContratos;
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
	public String getCurp() {
		return curp;
	}
	public void setCurp(String curp) {
		this.curp = curp;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getSar() {
		return sar;
	}
	public void setSar(String sar) {
		this.sar = sar;
	}
	public String getBancoA() {
		return bancoA;
	}
	public void setBancoA(String bancoA) {
		this.bancoA = bancoA;
	}
	public String getBancoN() {
		return bancoN;
	}
	public void setBancoN(String bancoN) {
		this.bancoN = bancoN;
	}
	public String getNumCta() {
		return numCta;
	}
	public void setNumCta(String numCta) {
		this.numCta = numCta;
	}
	public String getClabe() {
		return clabe;
	}
	public void setClabe(String clabe) {
		this.clabe = clabe;
	}
	public String getFuncion() {
		return funcion;
	}
	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getPuesto() {
		return puesto;
	}
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	public String getDesPuesto() {
		return desPuesto;
	}
	public void setDesPuesto(String desPuesto) {
		this.desPuesto = desPuesto;
	}
	public String getUr() {
		return ur;
	}
	public void setUr(String ur) {
		this.ur = ur;
	}
	public String getGf() {
		return gf;
	}
	public void setGf(String gf) {
		this.gf = gf;
	}
	public String getFn() {
		return fn;
	}
	public void setFn(String fn) {
		this.fn = fn;
	}
	public String getSf() {
		return sf;
	}
	public void setSf(String sf) {
		this.sf = sf;
	}
	public String getPg() {
		return pg;
	}
	public void setPg(String pg) {
		this.pg = pg;
	}
	public String getAi() {
		return ai;
	}
	public void setAi(String ai) {
		this.ai = ai;
	}
	public String getPp() {
		return pp;
	}
	public void setPp(String pp) {
		this.pp = pp;
	}
	public String getPartida() {
		return partida;
	}
	public void setPartida(String partida) {
		this.partida = partida;
	}
	public String getPuestoTab() {
		return puestoTab;
	}
	public void setPuestoTab(String puestoTab) {
		this.puestoTab = puestoTab;
	}
	public String getNumPto() {
		return numPto;
	}
	public void setNumPto(String numPto) {
		this.numPto = numPto;
	}
	public String getEdo() {
		return edo;
	}
	public void setEdo(String edo) {
		this.edo = edo;
	}
	public String getMpio() {
		return mpio;
	}
	public void setMpio(String mpio) {
		this.mpio = mpio;
	}
	public String getCr() {
		return cr;
	}
	public void setCr(String cr) {
		this.cr = cr;
	}
	public String getCi() {
		return ci;
	}
	public void setCi(String ci) {
		this.ci = ci;
	}
	public String getPagaD() {
		return pagaD;
	}
	public void setPagaD(String pagaD) {
		this.pagaD = pagaD;
	}
	public String getFinanciamiento() {
		return financiamiento;
	}
	public void setFinanciamiento(String financiamiento) {
		this.financiamiento = financiamiento;
	}
	public String getTabPto() {
		return tabPto;
	}
	public void setTabPto(String tabPto) {
		this.tabPto = tabPto;
	}
	public String getNivel() {
		return nivel;
	}
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	public String getRango() {
		return rango;
	}
	public void setRango(String rango) {
		this.rango = rango;
	}
	public String getIndMando() {
		return indMando;
	}
	public void setIndMando(String indMando) {
		this.indMando = indMando;
	}
	public String getHoras() {
		return horas;
	}
	public void setHoras(String horas) {
		this.horas = horas;
	}
	public String getPorcent() {
		return porcent;
	}
	public void setPorcent(String porcent) {
		this.porcent = porcent;
	}
	public String getTipoTrab() {
		return tipoTrab;
	}
	public void setTipoTrab(String tipoTrab) {
		this.tipoTrab = tipoTrab;
	}
	public String getNivelPto() {
		return nivelPto;
	}
	public void setNivelPto(String nivelPto) {
		this.nivelPto = nivelPto;
	}
	public String getIndEmp() {
		return indEmp;
	}
	public void setIndEmp(String indEmp) {
		this.indEmp = indEmp;
	}
	public String getFecInicial() {
		return fecInicial;
	}
	public void setFecInicial(String fecInicial) {
		this.fecInicial = fecInicial;
	}
	public String getFecFinal() {
		return fecFinal;
	}
	public void setFecFinal(String fecFinal) {
		this.fecFinal = fecFinal;
	}
	public String getFecIngreso() {
		return fecIngreso;
	}
	public void setFecIngreso(String fecIngreso) {
		this.fecIngreso = fecIngreso;
	}
	public String getTipoMov() {
		return tipoMov;
	}
	public void setTipoMov(String tipoMov) {
		this.tipoMov = tipoMov;
	}
	public String getfPago() {
		return fPago;
	}
	public void setfPago(String fPago) {
		this.fPago = fPago;
	}
	public String getpPagoI() {
		return pPagoI;
	}
	public void setpPagoI(String pPagoI) {
		this.pPagoI = pPagoI;
	}
	public String getpPagoF() {
		return pPagoF;
	}
	public void setpPagoF(String pPagoF) {
		this.pPagoF = pPagoF;
	}
	public String getpQnaI() {
		return pQnaI;
	}
	public void setpQnaI(String pQnaI) {
		this.pQnaI = pQnaI;
	}
	public String getpQnaF() {
		return pQnaF;
	}
	public void setpQnaF(String pQnaF) {
		this.pQnaF = pQnaF;
	}
	public String getQnaReal() {
		return qnaReal;
	}
	public void setQnaReal(String qnaReal) {
		this.qnaReal = qnaReal;
	}
	public String getAnioReal() {
		return anioReal;
	}
	public void setAnioReal(String anioReal) {
		this.anioReal = anioReal;
	}
	public Integer getTipoPago() {
		return tipoPago;
	}
	public void setTipoPago(Integer tipoPago) {
		this.tipoPago = tipoPago;
	}
	public String getInstruA() {
		return instruA;
	}
	public void setInstruA(String instruA) {
		this.instruA = instruA;
	}
	public String getInstruN() {
		return instruN;
	}
	public void setInstruN(String instruN) {
		this.instruN = instruN;
	}
	public BigDecimal getPer() {
		return per;
	}
	public void setPer(BigDecimal per) {
		this.per = per;
	}
	public BigDecimal getDed() {
		return ded;
	}
	public void setDed(BigDecimal ded) {
		this.ded = ded;
	}
	public BigDecimal getNeto() {
		return neto;
	}
	public void setNeto(BigDecimal neto) {
		this.neto = neto;
	}
	public Integer getNoTrail() {
		return noTrail;
	}
	public void setNoTrail(Integer noTrail) {
		this.noTrail = noTrail;
	}
	public Integer getDiasLab() {
		return diasLab;
	}
	public void setDiasLab(Integer diasLab) {
		this.diasLab = diasLab;
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
	public String getNumCheq() {
		return numCheq;
	}
	public void setNumCheq(String numCheq) {
		this.numCheq = numCheq;
	}
	public String getDigVer() {
		return digVer;
	}
	public void setDigVer(String digVer) {
		this.digVer = digVer;
	}
	public String getJornada() {
		return jornada;
	}
	public void setJornada(String jornada) {
		this.jornada = jornada;
	}
	public String getDiasP() {
		return diasP;
	}
	public void setDiasP(String diasP) {
		this.diasP = diasP;
	}
	public String getCicloF() {
		return cicloF;
	}
	public void setCicloF(String cicloF) {
		this.cicloF = cicloF;
	}
	public String getNumAport() {
		return numAport;
	}
	public void setNumAport(String numAport) {
		this.numAport = numAport;
	}
	public BigDecimal getAcumF() {
		return acumF;
	}
	public void setAcumF(BigDecimal acumF) {
		this.acumF = acumF;
	}
	public Integer getFaltas() {
		return faltas;
	}
	public void setFaltas(Integer faltas) {
		this.faltas = faltas;
	}
	public String getClues() {
		return clues;
	}
	public void setClues(String clues) {
		this.clues = clues;
	}
	public Integer getPorPen01() {
		return porPen01;
	}
	public void setPorPen01(Integer porPen01) {
		this.porPen01 = porPen01;
	}
	public Integer getPorPen02() {
		return porPen02;
	}
	public void setPorPen02(Integer porPen02) {
		this.porPen02 = porPen02;
	}
	public Integer getPorPen03() {
		return porPen03;
	}
	public void setPorPen03(Integer porPen03) {
		this.porPen03 = porPen03;
	}
	public Integer getPorPen04() {
		return porPen04;
	}
	public void setPorPen04(Integer porPen04) {
		this.porPen04 = porPen04;
	}
	public Integer getPorPen05() {
		return porPen05;
	}
	public void setPorPen05(Integer porPen05) {
		this.porPen05 = porPen05;
	}
	public Integer getIssste() {
		return issste;
	}
	public void setIssste(Integer issste) {
		this.issste = issste;
	}
	public Integer getTipoUni() {
		return tipoUni;
	}
	public void setTipoUni(Integer tipoUni) {
		this.tipoUni = tipoUni;
	}
	public String getCrespDes() {
		return crespDes;
	}
	public void setCrespDes(String crespDes) {
		this.crespDes = crespDes;
	}
	public Integer getIdNombramiento() {
		return idNombramiento;
	}
	public void setIdNombramiento(Integer idNombramiento) {
		this.idNombramiento = idNombramiento;
	}
	public Integer getIdSiifEncabezados() {
		return idSiifEncabezados;
	}
	public void setIdSiifEncabezados(Integer idSiifEncabezados) {
		this.idSiifEncabezados = idSiifEncabezados;
	}
	public String getTipoEmisionNomina() {
		return tipoEmisionNomina;
	}
	public void setTipoEmisionNomina(String tipoEmisionNomina) {
		this.tipoEmisionNomina = tipoEmisionNomina;
	}
	public Integer getIdSiifBitacoras() {
		return idSiifBitacoras;
	}
	public void setIdSiifBitacoras(Integer idSiifBitacoras) {
		this.idSiifBitacoras = idSiifBitacoras;
	}
	public Integer getIdSubfuentaFinanciamiento() {
		return idSubfuentaFinanciamiento;
	}
	public void setIdSubfuentaFinanciamiento(Integer idSubfuentaFinanciamiento) {
		this.idSubfuentaFinanciamiento = idSubfuentaFinanciamiento;
	}
			
	public Integer getIdProductoNomina() {
		return idProductoNomina;
	}
	public void setIdProductoNomina(Integer idProductoNomina) {
		this.idProductoNomina = idProductoNomina;
	}
			
	public Integer getIdPrograma() {
		return idPrograma;
	}
	public void setIdPrograma(Integer idPrograma) {
		this.idPrograma = idPrograma;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}