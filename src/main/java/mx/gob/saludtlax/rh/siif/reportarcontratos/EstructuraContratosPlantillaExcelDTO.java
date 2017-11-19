package mx.gob.saludtlax.rh.siif.reportarcontratos;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;

public class EstructuraContratosPlantillaExcelDTO implements Serializable {


	private static final long serialVersionUID = -4226990086634174773L;

	private String idEstructura;
	private Integer tipoNomina;
	private String rfc;
	private String rfcVal;
	private String nombre;
	private String cheqDap;
	private String fI;
	private String cResponsable;
	private String funcion;
	private String rama;
	private String programa;	
	private BigDecimal sueldo;
	private BigDecimal sup;
	private BigDecimal honorarios;
	private BigDecimal comp;
	private BigDecimal ag;
	private BigDecimal subsidio;
	private Integer vac;
	private Integer rFaltas;
	private BigDecimal retroa;
	private BigDecimal otros;
	private Integer faltas;
	private BigDecimal istp;
	private Integer respons;
	private Integer prestamo;
	private BigDecimal pa;	
	private BigDecimal totalBruto;
	private BigDecimal percepciones;	
	private BigDecimal deducciones;
	private BigDecimal neto;	
	private Integer idNombramiento;
	private String tipoEmisionNomina;
	private Integer idSiifBitacora;
	private Integer idSubfuenteFin;
	private Integer idLaborale;
	private String cuentaBancaria;
	
	
	
	
	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

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

	public BigDecimal getPa() {
		return pa;
	}

	public void setPa(BigDecimal pa) {
		this.pa = pa;
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

	public Integer getIdSubfuenteFin() {
		return idSubfuenteFin;
	}

	public void setIdSubfuenteFin(Integer idSubfuenteFin) {
		this.idSubfuenteFin = idSubfuenteFin;
	}

	public Integer getIdLaborale() {
		return idLaborale;
	}

	public void setIdLaborale(Integer idLaborale) {
		this.idLaborale = idLaborale;
	}

	public String getCuentaBancaria() {
		return cuentaBancaria;
	}

	public void setCuentaBancaria(String cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}
	
	
			

}