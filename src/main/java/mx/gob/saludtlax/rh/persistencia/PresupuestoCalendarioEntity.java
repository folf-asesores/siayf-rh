/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Eduardo Mex
 *
 */
@Entity
@Table(name = "presupuesto_calendario")
public class PresupuestoCalendarioEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1284674257953241160L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_presupuesto_autorizado")
	private Integer idPresupuestoCalendario;
	
	@Column(name = "ur")
	private Integer ur;
	
	@Column(name = "fin")
	private Integer fin;
	
	@Column(name = "fn")
	private Integer fn;
	
	@Column(name = "sf")
	private Integer sf;
	
	@Column(name = "rg")
	private Integer rg;
	
	@Column(name = "ai")
	private Integer ai;
	
	@Column(name = "mpp")
	private String mpp;
	
	@Column(name = "pp")
	private Integer pp;
	
	@Column(name = "ptda")
	private Integer ptda;
	
	@Column(name = "tg")
	private Integer tg;
	
	@Column(name = "ff")
	private Integer ff;
	
	@Column(name = "ef")
	private Integer ef;
	
	@Column(name = "ppii")
	private Integer ppii;
	
	@Column(name = "anio")
	private Integer anio;
	
	@Column(name = "proyecto_anual")
	private BigDecimal proyectoAnual;
	
	@Column(name = "enero")
	private BigDecimal enero;

	@Column(name = "febrero")
	private BigDecimal febrero;
	
	@Column(name = "marzo")
	private BigDecimal marzo;
	
	@Column(name = "abril")
	private BigDecimal abril;
	
	@Column(name = "mayo")
	private BigDecimal mayo;
	
	@Column(name = "junio")
	private BigDecimal junio;
	
	@Column(name = "julio")
	private BigDecimal julio;
	
	@Column(name = "agosto")
	private BigDecimal agosto;
	
	@Column(name = "septiembre")
	private BigDecimal septimbre;
	
	@Column(name = "octubre")
	private BigDecimal octubre;
	
	@Column(name = "noviembre")
	private BigDecimal noviembre;
	
	@Column(name = "diciembre")
	private BigDecimal diciembre;
	
	

	@Override
	public String toString() {
		return "PresupuestoCalendarioEntity [idPresupuestoCalendario=" + idPresupuestoCalendario + ", ur=" + ur
				+ ", fin=" + fin + ", fn=" + fn + ", sf=" + sf + ", rg=" + rg + ", ai=" + ai + ", mpp=" + mpp + ", pp="
				+ pp + ", ptda=" + ptda + ", tg=" + tg + ", ff=" + ff + ", ef=" + ef + ", ppi=" + ppii
				+ ", proyectoAnual=" + proyectoAnual + ", enero=" + enero + ", febrero=" + febrero + ", marzo=" + marzo
				+ ", abril=" + abril + ", mayo=" + mayo + ", junio=" + junio + ", julio=" + julio + ", agosto=" + agosto
				+ ", septimbre=" + septimbre + ", octubre=" + octubre + ", noviembre=" + noviembre + ", diciembre="
				+ diciembre + "]";
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

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
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
	
	
	
	
	
	
	
	
	
	

}
