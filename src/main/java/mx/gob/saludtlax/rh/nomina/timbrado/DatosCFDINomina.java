package mx.gob.saludtlax.rh.nomina.timbrado;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Juan Carlos Ivan Ganzo Dominguez
 *
 */
public class DatosCFDINomina implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -46854117815310871L;

	private String serie;
	private String folio;
	private String rfc;
	private String nombre;
	private String calle;
	private String noExterio;
	private String noInterior;
	private String colonia;
	private String municipio;
	private String estado;
	private String pais;
	private String codigoPostal;
	private String numeroEmpleado;
	private String curp;
	private Integer tipoRegimen;
	private String numeroSeguroSocial;
	private Date fechaPago;
	private Date fechaInicalPago;
	private Date fechaFinalPago;
	private BigDecimal numeroDiasPagados;
	private String departamento;
	private String banco;
	private Date fechaInicioRelLaboral;
	private int antiguedad;
	private String puesto;
	private String tipoContrato;
	private String tipoJornada;
	private String periocidadPago;
	private BigDecimal salarioBaseCotApor;
	private Integer riesgoPuesto;
	private BigDecimal salarioDiarioIntegrado;
	private List<PercepcionCFDI> percepciones;
	private List<DeduccionCFDI> deducciones;
	private List<HorasExtraCFDI> horasExtra;
	private List<IncapacidadCFDI> incapacidadCFDI;
	private Integer tipoFuente;
	private Integer porcentajeFederal;
	private String metodoPago;
	private String numeroCuentaPago;
	private String tipoRegimenEmpleado;
	private String origenRecursos;
	private BigDecimal montoRecursosPropios;
	private String tipoNomina;
	private List<OtroPagoCFDI> otrosPagosCFDI;
	

	public String getOrigenRecursos() {
		return origenRecursos;
	}

	public void setOrigenRecursos(String origenRecursos) {
		this.origenRecursos = origenRecursos;
	}

	public BigDecimal getMontoRecursosPropios() {
		return montoRecursosPropios;
	}

	public void setMontoRecursosPropios(BigDecimal montoRecursosPropios) {
		this.montoRecursosPropios = montoRecursosPropios;
	}

	public String getTipoNomina() {
		return tipoNomina;
	}

	public void setTipoNomina(String tipoNomina) {
		this.tipoNomina = tipoNomina;
	}

	public List<OtroPagoCFDI> getOtrosPagosCFDI() {
		return otrosPagosCFDI;
	}

	public void setOtrosPagosCFDI(List<OtroPagoCFDI> otrosPagosCFDI) {
		this.otrosPagosCFDI = otrosPagosCFDI;
	}

	public String getTipoRegimenEmpleado() {
		return tipoRegimenEmpleado;
	}

	public void setTipoRegimenEmpleado(String tipoRegimenEmpleado) {
		this.tipoRegimenEmpleado = tipoRegimenEmpleado;
	}

	public Integer getTipoFuente() {
		return tipoFuente;
	}

	public void setTipoFuente(Integer tipoFuente) {
		this.tipoFuente = tipoFuente;
	}

	public Integer getPorcentajeFederal() {
		return porcentajeFederal;
	}

	public void setPorcentajeFederal(Integer porcentajeFederal) {
		this.porcentajeFederal = porcentajeFederal;
	}

	public List<HorasExtraCFDI> getHorasExtra() {
		return horasExtra;
	}

	public void setHorasExtra(List<HorasExtraCFDI> horasExtra) {
		this.horasExtra = horasExtra;
	}

	public List<IncapacidadCFDI> getIncapacidadCFDI() {
		return incapacidadCFDI;
	}

	public void setIncapacidadCFDI(List<IncapacidadCFDI> incapacidadCFDI) {
		this.incapacidadCFDI = incapacidadCFDI;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public void setAntiguedad(int antiguedad) {
		this.antiguedad = antiguedad;
	}

	public int getAntiguedad() {
		return antiguedad;
	}

	public String getNoInterior() {
		return noInterior;
	}

	public void setNoInterior(String noInterior) {
		this.noInterior = noInterior;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNoExterio() {
		return noExterio;
	}

	public void setNoExterio(String noExterio) {
		this.noExterio = noExterio;
	}

	public String getColonia() {
		return colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getNumeroEmpleado() {
		return numeroEmpleado;
	}

	public void setNumeroEmpleado(String numeroEmpleado) {
		this.numeroEmpleado = numeroEmpleado;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public Integer getTipoRegimen() {
		return tipoRegimen;
	}

	public void setTipoRegimen(Integer tipoRegimen) {
		this.tipoRegimen = tipoRegimen;
	}

	public String getNumeroSeguroSocial() {
		return numeroSeguroSocial;
	}

	public void setNumeroSeguroSocial(String numeroSeguroSocial) {
		this.numeroSeguroSocial = numeroSeguroSocial;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public Date getFechaInicalPago() {
		return fechaInicalPago;
	}

	public void setFechaInicalPago(Date fechaInicalPago) {
		this.fechaInicalPago = fechaInicalPago;
	}

	public Date getFechaFinalPago() {
		return fechaFinalPago;
	}

	public void setFechaFinalPago(Date fechaFinalPago) {
		this.fechaFinalPago = fechaFinalPago;
	}

	public BigDecimal getNumeroDiasPagados() {
		return numeroDiasPagados;
	}

	public void setNumeroDiasPagados(BigDecimal numeroDiasPagados) {
		this.numeroDiasPagados = numeroDiasPagados;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public Date getFechaInicioRelLaboral() {
		return fechaInicioRelLaboral;
	}

	public void setFechaInicioRelLaboral(Date fechaInicioRelLaboral) {
		this.fechaInicioRelLaboral = fechaInicioRelLaboral;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public String getTipoContrato() {
		return tipoContrato;
	}

	public void setTipoContrato(String tipoContrato) {
		this.tipoContrato = tipoContrato;
	}

	public String getTipoJornada() {
		return tipoJornada;
	}

	public void setTipoJornada(String tipoJornada) {
		this.tipoJornada = tipoJornada;
	}

	public String getPeriocidadPago() {
		return periocidadPago;
	}

	public void setPeriocidadPago(String periocidadPago) {
		this.periocidadPago = periocidadPago;
	}

	public BigDecimal getSalarioBaseCotApor() {
		return salarioBaseCotApor;
	}

	public void setSalarioBaseCotApor(BigDecimal salarioBaseCotApor) {
		this.salarioBaseCotApor = salarioBaseCotApor;
	}

	public Integer getRiesgoPuesto() {
		return riesgoPuesto;
	}

	public void setRiesgoPuesto(Integer riesgoPuesto) {
		this.riesgoPuesto = riesgoPuesto;
	}

	public BigDecimal getSalarioDiarioIntegrado() {
		return salarioDiarioIntegrado;
	}

	public void setSalarioDiarioIntegrado(BigDecimal salarioDiarioIntegrado) {
		this.salarioDiarioIntegrado = salarioDiarioIntegrado;
	}

	public List<PercepcionCFDI> getPercepciones() {
		return percepciones;
	}

	public void setPercepciones(List<PercepcionCFDI> percepciones) {
		this.percepciones = percepciones;
	}

	public List<DeduccionCFDI> getDeducciones() {
		return deducciones;
	}

	public void setDeducciones(List<DeduccionCFDI> deducciones) {
		this.deducciones = deducciones;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	public String getNumeroCuentaPago() {
		return numeroCuentaPago;
	}

	public void setNumeroCuentaPago(String numeroCuentaPago) {
		this.numeroCuentaPago = numeroCuentaPago;
	}
	

}
