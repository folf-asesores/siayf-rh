package mx.gob.saludtlax.rh.persistencia;
import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
/**
 * 
 * @author José Pablo
 *
 */
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "presupuesto_autorizado")
public class PresupuestoAutorizadoEntity implements Serializable{
	
	private static final long serialVersionUID = 7212628000214644356L;
	
	@Id
	@Column(name = "id_presupuesto_autorizado")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPresupuestoAutorizado;
	
	@Column(name = "id_unidad_responsable")
	private Integer idUnidadResponsable;
	
	@Column(name = "fin")
	private Integer fin;
	
	@Column(name = "fn")
	private Integer fn;
	
	@Column(name = "id_subfuente_financiamiento")
	private Integer idSubfuenteFinanciamiento;
	
	@Column(name = "rg")
	private Integer rg;
	
	@Column(name = "ai")
	private Integer ai;
	
	@Column(name = "mpp")
	private String mpp;
	
	@Column(name = "pp")
	private Integer pp;
	
	@Column(name = "partida")
	private Integer partida;
	
	@Column(name = "tg")
	private Integer tg;
	
	@Column(name = "ff")
	private Integer ff;
	
	@Column(name = "ef")
	private Integer ef;
	
	@Column(name = "ppii")
	private Integer ppii;
	
	@Column(name = "concepto")
	private String concepto;
	
	@Column(name = "importe_anual")
	private BigDecimal importeAnual;
	
	@Column(name = "anio")
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

	public void setIdSunfuenteFinanciamiento(Integer idSubfuenteFinanciamiento) {
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
