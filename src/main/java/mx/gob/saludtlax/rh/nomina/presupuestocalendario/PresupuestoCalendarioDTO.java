/*
 *
 */

package mx.gob.saludtlax.rh.nomina.presupuestocalendario;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Eduardo Mex
 *
 */
public class PresupuestoCalendarioDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3381768728024253346L;

    private Integer idPresupuestoCalendario;

    private Integer ur;

    private Integer fin;

    private Integer fn;

    private Integer sf;

    private Integer rg;

    private Integer ai;

    private String mpp;

    private Integer pp;

    private Integer ptda;

    private Integer tg;

    private Integer ff;

    private Integer ef;

    private Integer ppii;

    private Integer anio;

    private BigDecimal proyectoAnual;

    private BigDecimal enero;

    private BigDecimal febrero;

    private BigDecimal marzo;

    private BigDecimal abril;

    private BigDecimal mayo;

    private BigDecimal junio;

    private BigDecimal julio;

    private BigDecimal agosto;

    private BigDecimal septimbre;

    private BigDecimal octubre;

    private BigDecimal noviembre;

    private BigDecimal diciembre;

    /**
     *
     */
    public PresupuestoCalendarioDTO() {
        enero = BigDecimal.ZERO;

        febrero = BigDecimal.ZERO;

        marzo = BigDecimal.ZERO;

        abril = BigDecimal.ZERO;

        mayo = BigDecimal.ZERO;

        junio = BigDecimal.ZERO;

        julio = BigDecimal.ZERO;

        agosto = BigDecimal.ZERO;

        septimbre = BigDecimal.ZERO;

        octubre = BigDecimal.ZERO;

        noviembre = BigDecimal.ZERO;

        diciembre = BigDecimal.ZERO;

    }

    /**
     * @param idPresupuestoCalendario
     * @param ur
     * @param fin
     * @param fn
     * @param sf
     * @param rg
     * @param ai
     * @param mpp
     * @param pp
     * @param ptda
     * @param tg
     * @param ff
     * @param ef
     * @param ppi
     * @param proyectoAnual
     * @param enero
     * @param febrero
     * @param marzo
     * @param abril
     * @param mayo
     * @param junio
     * @param julio
     * @param agosto
     * @param septimbre
     * @param octubre
     * @param noviembre
     * @param diciembre
     */
    public PresupuestoCalendarioDTO(Integer idPresupuestoCalendario, Integer ur, Integer fin, Integer fn, Integer sf, Integer rg, Integer ai, String mpp,
            Integer pp, Integer ptda, Integer tg, Integer ff, Integer ef, Integer ppii, Integer anio, BigDecimal proyectoAnual, BigDecimal enero,
            BigDecimal febrero, BigDecimal marzo, BigDecimal abril, BigDecimal mayo, BigDecimal junio, BigDecimal julio, BigDecimal agosto,
            BigDecimal septimbre, BigDecimal octubre, BigDecimal noviembre, BigDecimal diciembre) {

        this.idPresupuestoCalendario = idPresupuestoCalendario;
        this.ur = ur;
        this.fin = fin;
        this.fn = fn;
        this.sf = sf;
        this.rg = rg;
        this.ai = ai;
        this.mpp = mpp;
        this.pp = pp;
        this.ptda = ptda;
        this.tg = tg;
        this.ff = ff;
        this.ef = ef;
        this.ppii = ppii;
        this.anio = anio;
        this.proyectoAnual = proyectoAnual;
        this.enero = enero;
        this.febrero = febrero;
        this.marzo = marzo;
        this.abril = abril;
        this.mayo = mayo;
        this.junio = junio;
        this.julio = julio;
        this.agosto = agosto;
        this.septimbre = septimbre;
        this.octubre = octubre;
        this.noviembre = noviembre;
        this.diciembre = diciembre;
    }

    public Integer getIdPresupuestoCalendario() {
        return idPresupuestoCalendario;
    }

    public void setIdPresupuestoCalendario(Integer idPresupuestoCalendario) {
        this.idPresupuestoCalendario = idPresupuestoCalendario;
    }

    public Integer getUr() {
        return ur;
    }

    public void setUr(Integer ur) {
        this.ur = ur;
    }

    public Integer getFin() {
        return fin;
    }

    public void setFin(Integer fin) {
        this.fin = fin;
    }

    public Integer getFn() {
        return fn;
    }

    public void setFn(Integer fn) {
        this.fn = fn;
    }

    public Integer getSf() {
        return sf;
    }

    public void setSf(Integer sf) {
        this.sf = sf;
    }

    public Integer getRg() {
        return rg;
    }

    public void setRg(Integer rg) {
        this.rg = rg;
    }

    public Integer getAi() {
        return ai;
    }

    public void setAi(Integer ai) {
        this.ai = ai;
    }

    public String getMpp() {
        return mpp;
    }

    public void setMpp(String mpp) {
        this.mpp = mpp;
    }

    public Integer getPp() {
        return pp;
    }

    public void setPp(Integer pp) {
        this.pp = pp;
    }

    public Integer getPtda() {
        return ptda;
    }

    public void setPtda(Integer ptda) {
        this.ptda = ptda;
    }

    public Integer getTg() {
        return tg;
    }

    public void setTg(Integer tg) {
        this.tg = tg;
    }

    public Integer getFf() {
        return ff;
    }

    public void setFf(Integer ff) {
        this.ff = ff;
    }

    public Integer getEf() {
        return ef;
    }

    public void setEf(Integer ef) {
        this.ef = ef;
    }

    public Integer getPpii() {
        return ppii;
    }

    public void setPpii(Integer ppii) {
        this.ppii = ppii;
    }

    public BigDecimal getProyectoAnual() {
        return proyectoAnual;
    }

    public void setProyectoAnual(BigDecimal proyectoAnual) {
        this.proyectoAnual = proyectoAnual;
    }

    public BigDecimal getEnero() {
        return enero;
    }

    public void setEnero(BigDecimal enero) {
        this.enero = enero;
    }

    public BigDecimal getFebrero() {
        return febrero;
    }

    public void setFebrero(BigDecimal febrero) {
        this.febrero = febrero;
    }

    public BigDecimal getMarzo() {
        return marzo;
    }

    public void setMarzo(BigDecimal marzo) {
        this.marzo = marzo;
    }

    public BigDecimal getAbril() {
        return abril;
    }

    public void setAbril(BigDecimal abril) {
        this.abril = abril;
    }

    public BigDecimal getMayo() {
        return mayo;
    }

    public void setMayo(BigDecimal mayo) {
        this.mayo = mayo;
    }

    public BigDecimal getJunio() {
        return junio;
    }

    public void setJunio(BigDecimal junio) {
        this.junio = junio;
    }

    public BigDecimal getJulio() {
        return julio;
    }

    public void setJulio(BigDecimal julio) {
        this.julio = julio;
    }

    public BigDecimal getAgosto() {
        return agosto;
    }

    public void setAgosto(BigDecimal agosto) {
        this.agosto = agosto;
    }

    public BigDecimal getSeptimbre() {
        return septimbre;
    }

    public void setSeptimbre(BigDecimal septimbre) {
        this.septimbre = septimbre;
    }

    public BigDecimal getOctubre() {
        return octubre;
    }

    public void setOctubre(BigDecimal octubre) {
        this.octubre = octubre;
    }

    public BigDecimal getNoviembre() {
        return noviembre;
    }

    public void setNoviembre(BigDecimal noviembre) {
        this.noviembre = noviembre;
    }

    public BigDecimal getDiciembre() {
        return diciembre;
    }

    public void setDiciembre(BigDecimal diciembre) {
        this.diciembre = diciembre;
    }

    /**
     * @return the anio
     */
    public Integer getAnio() {
        return anio;
    }

    /**
     * @param anio
     *            the anio to set
     */
    public void setAnio(Integer anio) {
        this.anio = anio;
    }

}
