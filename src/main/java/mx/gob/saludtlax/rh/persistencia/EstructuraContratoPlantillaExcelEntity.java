package mx.gob.saludtlax.rh.persistencia;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "siif_contratos_plantilla")
public class EstructuraContratoPlantillaExcelEntity {
	
	private static final long serialVersionUID = 3111008823681449423L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_estructura")
	private String idEstructura;
	@Column(name="tipo_nomina")
	private Integer tipoNomina;
	@Column(name="rfc")
	private String rfc;
	@Column(name="rfc_val")
	private String rfcVal;
	@Column(name="nombre")
	private String nombre;
	@Column(name="cheq_dap")
	private String cheqDap;
	@Column(name="f_i")
	private String fI;
	@Column(name="c_responsable")
	private String cResponsable;
	@Column(name="funcion")
	private String funcion;
	@Column(name="rama")
	private String rama;
	@Column(name="programa")
	private String programa;
			
	@Column(name="sueldo")	
	private BigDecimal sueldo;
	@Column(name="sup")
	private BigDecimal sup;
	@Column(name="honorarios")
	private BigDecimal honorarios;
	@Column(name="comp")
	private BigDecimal comp;
	@Column(name="ag")
	private BigDecimal ag;
	@Column(name="subsidio")
	private BigDecimal subsidio;
	@Column(name="vac")
	private Integer vac;
	@Column(name="r_faltas")
	private Integer rFaltas;
	@Column(name="retroa")
	private BigDecimal retroa;
	@Column(name="otros")
	private BigDecimal otros;
	@Column(name="faltas")
	private Integer faltas;
	@Column(name="istp")
	private BigDecimal istp;
	@Column(name="respons")
	private Integer respons;
	@Column(name="prestamo")
	private Integer prestamo;
	@Column(name="pension")
	private BigDecimal pension;
	@Column(name="total_bruto")	
	private BigDecimal totalBruto;
	@Column(name="percepciones")
	private BigDecimal percepciones;
	@Column(name="deducciones")	
	private BigDecimal deducciones;
	@Column(name="neto")
	private BigDecimal neto;
	
	@Column(name = "id_nombramiento")
	private Integer idNombramiento;
	@Column(name = "tipo_emision_nomina")
	private String tipoEmisionNomina;
	@Column(name = "id_siif_bitacoras")
	private Integer idSiifBitacora;
	@Column(name = "id_subfuente_financiamiento")
	private Integer idSubfuenteFinanciamiento;
	@Column(name = "id_datos_laborales")
	private Integer idDatosLaborales;
	public String getIdEstructura() {
		return idEstructura;
	}
	public void setIdEstructura(String idEstructura) {
		this.idEstructura = idEstructura;
	}
	public Integer getTipoNomina() {
		return tipoNomina;
	}
	public void setTipoNomina(Integer tipoNomina) {
		this.tipoNomina = tipoNomina;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public String getRfcVal() {
		return rfcVal;
	}
	public void setRfcVal(String rfcVal) {
		this.rfcVal = rfcVal;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCheqDap() {
		return cheqDap;
	}
	public void setCheqDap(String cheqDap) {
		this.cheqDap = cheqDap;
	}
	public String getfI() {
		return fI;
	}
	public void setfI(String fI) {
		this.fI = fI;
	}
	public String getcResponsable() {
		return cResponsable;
	}
	public void setcResponsable(String cResponsable) {
		this.cResponsable = cResponsable;
	}
	public String getFuncion() {
		return funcion;
	}
	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}
	public String getRama() {
		return rama;
	}
	public void setRama(String rama) {
		this.rama = rama;
	}
	public String getPrograma() {
		return programa;
	}
	public void setPrograma(String programa) {
		this.programa = programa;
	}
	public BigDecimal getSueldo() {
		return sueldo;
	}
	public void setSueldo(BigDecimal sueldo) {
		this.sueldo = sueldo;
	}
	public BigDecimal getSup() {
		return sup;
	}
	public void setSup(BigDecimal sup) {
		this.sup = sup;
	}
	public BigDecimal getHonorarios() {
		return honorarios;
	}
	public void setHonorarios(BigDecimal honorarios) {
		this.honorarios = honorarios;
	}
	public BigDecimal getComp() {
		return comp;
	}
	public void setComp(BigDecimal comp) {
		this.comp = comp;
	}
	public BigDecimal getAg() {
		return ag;
	}
	public void setAg(BigDecimal ag) {
		this.ag = ag;
	}
	public BigDecimal getSubsidio() {
		return subsidio;
	}
	public void setSubsidio(BigDecimal subsidio) {
		this.subsidio = subsidio;
	}
	public Integer getVac() {
		return vac;
	}
	public void setVac(Integer vac) {
		this.vac = vac;
	}
	public Integer getrFaltas() {
		return rFaltas;
	}
	public void setrFaltas(Integer rFaltas) {
		this.rFaltas = rFaltas;
	}
	public BigDecimal getRetroa() {
		return retroa;
	}
	public void setRetroa(BigDecimal retroa) {
		this.retroa = retroa;
	}
	public BigDecimal getOtros() {
		return otros;
	}
	public void setOtros(BigDecimal otros) {
		this.otros = otros;
	}
	public Integer getFaltas() {
		return faltas;
	}
	public void setFaltas(Integer faltas) {
		this.faltas = faltas;
	}
	public BigDecimal getIstp() {
		return istp;
	}
	public void setIstp(BigDecimal istp) {
		this.istp = istp;
	}
	public Integer getRespons() {
		return respons;
	}
	public void setRespons(Integer respons) {
		this.respons = respons;
	}
	public Integer getPrestamo() {
		return prestamo;
	}
	public void setPrestamo(Integer prestamo) {
		this.prestamo = prestamo;
	}
	public BigDecimal getPension() {
		return pension;
	}
	public void setPension(BigDecimal pension) {
		this.pension = pension;
	}
	public BigDecimal getTotalBruto() {
		return totalBruto;
	}
	public void setTotalBruto(BigDecimal totalBruto) {
		this.totalBruto = totalBruto;
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
	public Integer getIdNombramiento() {
		return idNombramiento;
	}
	public void setIdNombramiento(Integer idNombramiento) {
		this.idNombramiento = idNombramiento;
	}
	public String getTipoEmisionNomina() {
		return tipoEmisionNomina;
	}
	public void setTipoEmisionNomina(String tipoEmisionNomina) {
		this.tipoEmisionNomina = tipoEmisionNomina;
	}
	public Integer getIdSiifBitacora() {
		return idSiifBitacora;
	}
	public void setIdSiifBitacora(Integer idSiifBitacora) {
		this.idSiifBitacora = idSiifBitacora;
	}
	public Integer getIdSubfuenteFinanciamiento() {
		return idSubfuenteFinanciamiento;
	}
	public void setIdSubfuenteFinanciamiento(Integer idSubfuenteFinanciamiento) {
		this.idSubfuenteFinanciamiento = idSubfuenteFinanciamiento;
	}
	public Integer getIdDatosLaborales() {
		return idDatosLaborales;
	}
	public void setIdDatosLaborales(Integer idDatosLaborales) {
		this.idDatosLaborales = idDatosLaborales;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
