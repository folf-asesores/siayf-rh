
package mx.gob.saludtlax.rh.presupuesto.presupuestoautorizado;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author José Pablo
 *
 */
public class PresupuestoAutorizadoDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4810099860462714224L;

    private Integer idPresupuestoAutorizado;

    private Integer idUnidadResponsable;

    private Integer fin;

    private Integer fn;

    private Integer idSubfuenteFinanciamiento;

    private Integer rg;

    private Integer ai;

    private String mpp;

    private Integer pp;

    private Integer partida;

    private Integer tg;

    private Integer ff;

    private Integer ef;

    private Integer ppii;

    private String concepto;

    private BigDecimal importeAnual;

    private Integer anio;

    public Integer getIdPresupuestoAutorizado() {
        return idPresupuestoAutorizado;
    }

    public void setIdPresupuestoAutorizado(Integer idPresupuestoAutorizado) {
        this.idPresupuestoAutorizado = idPresupuestoAutorizado;
    }

    public Integer getIdUnidadResponsable() {
        return idUnidadResponsable;
    }

    public void setIdUnidadResponsable(Integer idUnidadResponsable) {
        this.idUnidadResponsable = idUnidadResponsable;
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

    public Integer getIdSubfuenteFinanciamiento() {
        return idSubfuenteFinanciamiento;
    }

    public void setIdSubfuenteFinanciamiento(Integer idSubfuenteFinanciamiento) {
        this.idSubfuenteFinanciamiento = idSubfuenteFinanciamiento;
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

    public Integer getPartida() {
        return partida;
    }

    public void setPartida(Integer partida) {
        this.partida = partida;
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

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public BigDecimal getImporteAnual() {
        return importeAnual;
    }

    public void setImporteAnual(BigDecimal importeAnual) {
        this.importeAnual = importeAnual;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

}
