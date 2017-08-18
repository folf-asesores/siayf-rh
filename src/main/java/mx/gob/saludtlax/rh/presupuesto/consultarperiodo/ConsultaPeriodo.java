package mx.gob.saludtlax.rh.presupuesto.consultarperiodo;

import java.math.BigDecimal;
import java.util.Date;

public class ConsultaPeriodo {
	private Integer idNomina;
	private Integer idDependencia;
	private Integer idNombramiento;
	private String nombramiento;
	private Date fecha;
	private Integer idTipoNomina;
	private String tipoNomina;
	private BigDecimal percepciones;
	private BigDecimal deducciones;
	private BigDecimal neto;
	private String subfuente;
	private String tipoEmision;
	private String claveBanco;

	public Integer getIdNomina() {
		return idNomina;
	}
	public void setIdNomina(Integer idNomina) {
		this.idNomina = idNomina;
	}
	public Integer getIdDependencia() {
		return idDependencia;
	}
	public void setIdDependencia(Integer idDependencia) {
		this.idDependencia = idDependencia;
	}
	public Integer getIdNombramiento() {
		return idNombramiento;
	}
	public void setIdNombramiento(Integer idNombramiento) {
		this.idNombramiento = idNombramiento;
	}
	public String getNombramiento() {
		return nombramiento;
	}
	public void setNombramiento(String nombramiento) {
		this.nombramiento = nombramiento;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Integer getIdTipoNomina() {
		return idTipoNomina;
	}
	public void setIdTipoNomina(Integer idTipoNomina) {
		this.idTipoNomina = idTipoNomina;
	}
	public String getTipoNomina() {
		return tipoNomina;
	}
	public void setTipoNomina(String tipoNomina) {
		this.tipoNomina = tipoNomina;
	}
	public BigDecimal getPercepciones() {
		return percepciones;
	}
	public void setPercepciones(BigDecimal percepciones) {
		this.percepciones = percepciones;
	}
	public BigDecimal getDeducciones() {
		return deducciones;
	}
	public void setDeducciones(BigDecimal deducciones) {
		this.deducciones = deducciones;
	}
	public BigDecimal getNeto() {
		return neto;
	}
	public void setNeto(BigDecimal neto) {
		this.neto = neto;
	}
	public String getSubfuente() {
		return subfuente;
	}
	public void setSubfuente(String subfuente) {
		this.subfuente = subfuente;
	}
	public String getTipoEmision() {
		return tipoEmision;
	}
	public void setTipoEmision(String tipoEmision) {
		this.tipoEmision = tipoEmision;
	}
	public String getClaveBanco() {
		return claveBanco;
	}
	public void setClaveBanco(String claveBanco) {
		this.claveBanco = claveBanco;
	}
}