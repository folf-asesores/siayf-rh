
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
@Table(name = "estructuras_contratos")
public class EstructuraContratoEntity implements Serializable {

    private static final long serialVersionUID = 3111008823681449423L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estructuras_contratos")
    private Integer idEstructurasContratos;
    @Column(name = "num_emp")
    private String numEmp;
    @Column(name = "rfc")
    private String rfc;
    @Column(name = "curp")
    private String curp;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "sar")
    private String sar;
    @Column(name = "banco_a")
    private String bancoA;
    @Column(name = "banco_n")
    private String bancoN;
    @Column(name = "num_cta")
    private String numCta;
    @Column(name = "clabe")
    private String clabe;
    @Column(name = "funcion")
    private String funcion;
    @Column(name = "cp")
    private String cp;
    @Column(name = "calle")
    private String calle;
    @Column(name = "puesto")
    private String puesto;
    @Column(name = "des_puesto")
    private String desPuesto;
    @Column(name = "ur")
    private String ur;
    @Column(name = "gf")
    private String gf;
    @Column(name = "fn")
    private String fn;
    @Column(name = "sf")
    private String sf;
    @Column(name = "pg")
    private String pg;
    @Column(name = "ai")
    private String ai;
    @Column(name = "pp")
    private String pp;
    @Column(name = "partida")
    private String partida;
    @Column(name = "puesto_tab")
    private String puestoTab;
    @Column(name = "num_pto")
    private String numPto;
    @Column(name = "edo")
    private String edo;
    @Column(name = "mpio")
    private String mpio;
    @Column(name = "cr")
    private String cr;
    @Column(name = "ci")
    private String ci;
    @Column(name = "paga_d")
    private String pagaD;
    @Column(name = "financiamiento")
    private String financiamiento;
    @Column(name = "tab_pto")
    private String tabPto;
    @Column(name = "nivel")
    private String nivel;
    @Column(name = "rango")
    private String rango;
    @Column(name = "ind_mando")
    private String indMando;
    @Column(name = "horas")
    private String horas;
    @Column(name = "porcent")
    private String porcent;
    @Column(name = "tipo_trab")
    private String tipoTrab;
    @Column(name = "nivel_pto")
    private String nivelPto;
    @Column(name = "ind_emp")
    private String indEmp;
    @Column(name = "fec_inicial")
    private String fecInicial;
    @Column(name = "fec_final")
    private String fecFinal;
    @Column(name = "fec_ingreso")
    private String fecIngreso;
    @Column(name = "tipo_mov")
    private String tipoMov;
    @Column(name = "f_pago")
    private String fPago;
    @Column(name = "p_pago_i")
    private String pPagoI;
    @Column(name = "p_pago_f")
    private String pPagoF;
    @Column(name = "p_qna_i")
    private String pQnaI;
    @Column(name = "p_qna_f")
    private String pQnaF;
    @Column(name = "qna_real")
    private String qnaReal;
    @Column(name = "anio_real")
    private String anioReal;
    @Column(name = "tipo_pago")
    private Integer tipoPago;
    @Column(name = "instru_a")
    private String instruA;
    @Column(name = "instru_n")
    private String instruN;
    @Column(name = "per")
    private BigDecimal per;
    @Column(name = "ded")
    private BigDecimal ded;
    @Column(name = "neto")
    private BigDecimal neto;
    @Column(name = "no_trail")
    private Integer noTrail;
    @Column(name = "dias_lab")
    private Integer diasLab;
    @Column(name = "nom_prod")
    private String nomProd;
    @Column(name = "num_ctrol")
    private Integer numCtrol;
    @Column(name = "num_cheq")
    private String numCheq;
    @Column(name = "dig_ver")
    private String digVer;
    @Column(name = "jornada")
    private String jornada;
    @Column(name = "dias_p")
    private String diasP;
    @Column(name = "ciclo_f")
    private String cicloF;
    @Column(name = "num_aport")
    private String numAport;
    @Column(name = "acum_f")
    private BigDecimal acumF;
    @Column(name = "faltas")
    private Integer faltas;
    @Column(name = "clues")
    private String clues;
    @Column(name = "por_pen_01")
    private Integer porPen01;
    @Column(name = "por_pen_02")
    private Integer porPen02;
    @Column(name = "por_pen_03")
    private Integer porPen03;
    @Column(name = "por_pen_04")
    private Integer porPen04;
    @Column(name = "por_pen_05")
    private Integer porPen05;
    @Column(name = "issste")
    private Integer issste;
    @Column(name = "tipo_uni")
    private Integer tipoUni;
    @Column(name = "cresp_des")
    private String crespDes;

    @Column(name = "id_nombramiento")
    private Integer idNombramiento;
    @Column(name = "id_siif_encabezados")
    private Integer idSiifEncabezados;
    @Column(name = "tipo_emision_nomina")
    private String tipoEmisionNomina;
    @Column(name = "id_siif_bitacoras")
    private Integer idSiifBitacoras;
    @Column(name = "id_subfuente_financiamiento")
    private Integer idSubfuenteFinanciamiento;

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

    public Integer getIdEstructurasContratos() {
        return idEstructurasContratos;
    }

    public void setIdEstructurasContratos(Integer idEstructurasContratos) {
        this.idEstructurasContratos = idEstructurasContratos;
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

    public Integer getIdSubfuenteFinanciamiento() {
        return idSubfuenteFinanciamiento;
    }

    public void setIdSubfuenteFinanciamiento(Integer idSubfuenteFinanciamiento) {
        this.idSubfuenteFinanciamiento = idSubfuenteFinanciamiento;
    }

}
