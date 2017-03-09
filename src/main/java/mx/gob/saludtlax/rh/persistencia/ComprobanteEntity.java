package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "comprobantes")
public class ComprobanteEntity implements Serializable {
	private static final long serialVersionUID = 4396931589155152880L;
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_comprobante")
	private Integer idComprobante;
	@Column(name="version")
	private String version;
	@Column(name="serie")
	private String serie;	
	@Column(name="folio")
	private String folio;
	@Column(name="fecha")
	private Date fecha;
	@Column(name="sello")
	private String sello;
	@Column(name="forma_pago")
	private String formaPago;
	@Column(name="no_certificado")
	private String noCertificado;
	@Column(name="subtotal")
	private BigDecimal subTotal;
	@Column(name="descuento")
	private BigDecimal descuento; 
	@Column(name="motivo_descuento")
	private String motivoDescuento;
	@Column(name="tipo_cambio")
	private String tipoCambio;
	@Column(name="moneda")
	private String moneda;
	@Column(name="total")
	private BigDecimal  total;
	@Column(name="tipo_comprobante")
	private String tipoComprobante;
	@Column(name="metodo_pago")
	private String metodoPago;
	@Column(name="lugar_expedicion")
	private String lugarExpedicion;
	@Column(name="numero_cuenta_pago")
	private String numeroCuentaPago;
	@Column(name="folio_fiscal_original")
	private String folioFiscalOriginal;
	@Column(name="serie_folio_fiscal_original")
	private String serieFolioFiscalOriginal;
	@Column(name="fecha_folio_fiscal_original")
	private Date fechaFolioFiscalOriginal;
	@Column(name="version_timbre")
	private String versionTimbre;
	@Column(name="uuid")
	private String uUID;
	@Column(name="fecha_timbrado")
	private Date fechaTimbrado;
	@Column(name="sello_cfd")
	private String selloCFDI;
	@Column(name="no_certificado_sat")
	private String numeroCertificadoSAT;
	@Column(name="sello_sat")
	private String selloSAT;
	@Column(name="rfc")
	private String rFC;
	@Column(name="nombre")
	private String nombre;
	@Column(name="total_impuestos_retenidos")
	private BigDecimal totalImpuestoRetenidos;
	@Column(name="total_impuestos_trasladados")
	private BigDecimal totalImpuestoTrasladados;
	@Column(name="comprobante_xml")
	private byte[] comprobanteXML;
	@Column(name="num_xml")
	private String numXML;
	@Column(name="id_xml")
	private String idXML;
	@Column(name="cadena_original")
	private String cadenaOriginal;
	@Column(name="version_nomina")
	private String versionNomina;
	@Column(name="registro_patronal")
	private String registroPatronal;
	@Column(name="num_empleado")
	private String numeroEmpleado;
	@Column(name="curp")
	private String cURP;	
	@Column(name="num_seguridad_social")
	private String numeroSeguridadSocial;
	@Column(name="fecha_pago")
	private Date fechaPago;
	@Column(name="fecha_inicial_pago")
	private Date fechaInicialPago;
	@Column(name="fecha_final_pago")
	private Date fechaFinalPago;
	@Column(name="num_dias_pagados")
	private BigDecimal numeroDiasPagados;
	@Column(name="departamento")
	private String departamento;
	@Column(name="clabe")
	private String cLABE;
	@Column(name="banco")
	private String banco;
	@Column(name="fecha_inicio_rel_laboral")
	private Date fechaInicioRelacionLaboral;
	@Column(name="antiguedad")
	private Integer antiguedad;
	@Column(name="puesto")
	private String puesto;
	@Column(name="tipo_contrato")
	private String tipoContrato;
	@Column(name="tipo_jornada")
	private String tipoJornada;
	@Column(name="periodicidad_pago")
	private String periocidadPago;
	@Column(name="salario_base_cot_apor")
	private BigDecimal salarioBaseCotizacionAport;
	@Column(name="riesgo_puesto")
	private Integer riesgoPuesto;
	@Column(name="salario_diario_integrado")
	private BigDecimal salarioDiarioIntegrado;
	@Column(name="percepcion_total_gravado")
	private BigDecimal percepcionTotalGravado;
	@Column(name="percepcion_total_exento")
	private BigDecimal percepcionTotalExcento;
	@Column(name="deduccion_total_gravado")
	private BigDecimal deduccionTotalGravado;
	@Column(name="deduccion_total_exento")
	private BigDecimal deduccionTotalExcento;
	@Column(name="calle")
	private String calle;
	@Column(name="no_exterior")
	private String noExterior;
	@Column(name="no_interior")
	private String noInterior;
	@Column(name="colonia")
	private String colonia;
	@Column(name="localidad")
	private String localidad;
	@Column(name="referencia")
	private String referencia;
	@Column(name="municipio")
	private String municipio;
	@Column(name="estado")
	private String estado;
	@Column(name="pais")
	private String pais;
	@Column(name="codigo_postal")
	private String codigoPostal;
	@Column(name="cancelado")
	private boolean cancelado;
	@Column(name="origen_recurso")
	private String origenRecurso;
	@Column(name="tipoRegimenEmpleado")
	private String TipoRegimenEmpleado;
	@Column(name="monto_recurso_propio")
	private BigDecimal montoRecursoPropio;
	@Column(name="tipo_nomina")
	private String tipoNomina ;
		
	public String getTipoRegimenEmpleado() {
		return TipoRegimenEmpleado;
	}
	public void setTipoRegimenEmpleado(String tipoRegimenEmpleado) {
		TipoRegimenEmpleado = tipoRegimenEmpleado;
	}
	public boolean isCancelado() {
		return cancelado;
	}
	public void setCancelado(boolean cancelado) {
		this.cancelado = cancelado;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public BigDecimal getDescuento() {
		return descuento;
	}
	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
	}
	public Integer getIdComprobante() {
		return idComprobante;
	}
	public void setIdComprobante(Integer idComprobante) {
		this.idComprobante = idComprobante;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getSello() {
		return sello;
	}
	public void setSello(String sello) {
		this.sello = sello;
	}
	public String getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	public String getNoCertificado() {
		return noCertificado;
	}
	public void setNoCertificado(String noCertificado) {
		this.noCertificado = noCertificado;
	}
	public BigDecimal getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}
	public String getMotivoDescuento() {
		return motivoDescuento;
	}
	public void setMotivoDescuento(String motivoDescuento) {
		this.motivoDescuento = motivoDescuento;
	}
	public String getTipoCambio() {
		return tipoCambio;
	}
	public void setTipoCambio(String tipoCambio) {
		this.tipoCambio = tipoCambio;
	}
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public String getTipoComprobante() {
		return tipoComprobante;
	}
	public void setTipoComprobante(String tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}
	public String getMetodoPago() {
		return metodoPago;
	}
	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}
	public String getLugarExpedicion() {
		return lugarExpedicion;
	}
	public void setLugarExpedicion(String lugarExpedicion) {
		this.lugarExpedicion = lugarExpedicion;
	}
	public String getNumeroCuentaPago() {
		return numeroCuentaPago;
	}
	public void setNumeroCuentaPago(String numeroCuentaPago) {
		this.numeroCuentaPago = numeroCuentaPago;
	}
	public String getFolioFiscalOriginal() {
		return folioFiscalOriginal;
	}
	public void setFolioFiscalOriginal(String folioFiscalOriginal) {
		this.folioFiscalOriginal = folioFiscalOriginal;
	}
	public String getSerieFolioFiscalOriginal() {
		return serieFolioFiscalOriginal;
	}
	public void setSerieFolioFiscalOriginal(String serieFolioFiscalOriginal) {
		this.serieFolioFiscalOriginal = serieFolioFiscalOriginal;
	}
	public Date getFechaFolioFiscalOriginal() {
		return fechaFolioFiscalOriginal;
	}
	public void setFechaFolioFiscalOriginal(Date fechaFolioFiscalOriginal) {
		this.fechaFolioFiscalOriginal = fechaFolioFiscalOriginal;
	}
	public String getVersionTimbre() {
		return versionTimbre;
	}
	public void setVersionTimbre(String versionTimbre) {
		this.versionTimbre = versionTimbre;
	}
	public String getuUID() {
		return uUID;
	}
	public void setuUID(String uUID) {
		this.uUID = uUID;
	}
	public Date getFechaTimbrado() {
		return fechaTimbrado;
	}
	public void setFechaTimbrado(Date fechaTimbrado) {
		this.fechaTimbrado = fechaTimbrado;
	}
	public String getSelloCFDI() {
		return selloCFDI;
	}
	public void setSelloCFDI(String selloCFDI) {
		this.selloCFDI = selloCFDI;
	}
	public String getNumeroCertificadoSAT() {
		return numeroCertificadoSAT;
	}
	public void setNumeroCertificadoSAT(String numeroCertificadoSAT) {
		this.numeroCertificadoSAT = numeroCertificadoSAT;
	}
	public String getSelloSAT() {
		return selloSAT;
	}
	public void setSelloSAT(String selloSAT) {
		this.selloSAT = selloSAT;
	}
	public String getrFC() {
		return rFC;
	}
	public void setrFC(String rFC) {
		this.rFC = rFC;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public BigDecimal getTotalImpuestoRetenidos() {
		return totalImpuestoRetenidos;
	}
	public void setTotalImpuestoRetenidos(BigDecimal totalImpuestoRetenidos) {
		this.totalImpuestoRetenidos = totalImpuestoRetenidos;
	}
	public BigDecimal getTotalImpuestoTrasladados() {
		return totalImpuestoTrasladados;
	}
	public void setTotalImpuestoTrasladados(BigDecimal totalImpuestoTrasladados) {
		this.totalImpuestoTrasladados = totalImpuestoTrasladados;
	}
	public byte[] getComprobanteXML() {
		return comprobanteXML;
	}
	public void setComprobanteXML(byte[] comprobanteXML) {
		this.comprobanteXML = comprobanteXML;
	}
	public String getNumXML() {
		return numXML;
	}
	public void setNumXML(String numXML) {
		this.numXML = numXML;
	}
	public String getIdXML() {
		return idXML;
	}
	public void setIdXML(String idXML) {
		this.idXML = idXML;
	}
	public String getCadenaOriginal() {
		return cadenaOriginal;
	}
	public void setCadenaOriginal(String cadenaOriginal) {
		this.cadenaOriginal = cadenaOriginal;
	}
	public String getVersionNomina() {
		return versionNomina;
	}
	public void setVersionNomina(String versionNomina) {
		this.versionNomina = versionNomina;
	}
	public String getRegistroPatronal() {
		return registroPatronal;
	}
	public void setRegistroPatronal(String registroPatronal) {
		this.registroPatronal = registroPatronal;
	}
	public String getNumeroEmpleado() {
		return numeroEmpleado;
	}
	public void setNumeroEmpleado(String numeroEmpleado) {
		this.numeroEmpleado = numeroEmpleado;
	}
	public String getcURP() {
		return cURP;
	}
	public void setcURP(String cURP) {
		this.cURP = cURP;
	}
	
	public String getNumeroSeguridadSocial() {
		return numeroSeguridadSocial;
	}
	public void setNumeroSeguridadSocial(String numeroSeguridadSocial) {
		this.numeroSeguridadSocial = numeroSeguridadSocial;
	}
	public Date getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}
	public Date getFechaInicialPago() {
		return fechaInicialPago;
	}
	public void setFechaInicialPago(Date fechaInicialPago) {
		this.fechaInicialPago = fechaInicialPago;
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
	public String getcLABE() {
		return cLABE;
	}
	public void setcLABE(String cLABE) {
		this.cLABE = cLABE;
	}
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public Date getFechaInicioRelacionLaboral() {
		return fechaInicioRelacionLaboral;
	}
	public void setFechaInicioRelacionLaboral(Date fechaInicioRelacionLaboral) {
		this.fechaInicioRelacionLaboral = fechaInicioRelacionLaboral;
	}
	public Integer getAntiguedad() {
		return antiguedad;
	}
	public void setAntiguedad(Integer antiguedad) {
		this.antiguedad = antiguedad;
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
	public BigDecimal getSalarioBaseCotizacionAport() {
		return salarioBaseCotizacionAport;
	}
	public void setSalarioBaseCotizacionAport(BigDecimal salarioBaseCotizacionAport) {
		this.salarioBaseCotizacionAport = salarioBaseCotizacionAport;
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
	public BigDecimal getPercepcionTotalGravado() {
		return percepcionTotalGravado;
	}
	public void setPercepcionTotalGravado(BigDecimal percepcionTotalGravado) {
		this.percepcionTotalGravado = percepcionTotalGravado;
	}
	public BigDecimal getPercepcionTotalExcento() {
		return percepcionTotalExcento;
	}
	public void setPercepcionTotalExcento(BigDecimal percepcionTotalExcento) {
		this.percepcionTotalExcento = percepcionTotalExcento;
	}
	public BigDecimal getDeduccionTotalGravado() {
		return deduccionTotalGravado;
	}
	public void setDeduccionTotalGravado(BigDecimal deduccionTotalGravado) {
		this.deduccionTotalGravado = deduccionTotalGravado;
	}
	public BigDecimal getDeduccionTotalExcento() {
		return deduccionTotalExcento;
	}
	public void setDeduccionTotalExcento(BigDecimal deduccionTotalExcento) {
		this.deduccionTotalExcento = deduccionTotalExcento;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getNoExterior() {
		return noExterior;
	}
	public void setNoExterior(String noExterior) {
		this.noExterior = noExterior;
	}
	public String getNoInterior() {
		return noInterior;
	}
	public void setNoInterior(String noInterior) {
		this.noInterior = noInterior;
	}
	public String getColonia() {
		return colonia;
	}
	public void setColonia(String colonia) {
		this.colonia = colonia;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
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
	public String getOrigenRecurso() {
		return origenRecurso;
	}
	public void setOrigenRecurso(String origenRecurso) {
		this.origenRecurso = origenRecurso;
	}
	public BigDecimal getMontoRecursoPropio() {
		return montoRecursoPropio;
	}
	public void setMontoRecursoPropio(BigDecimal montoRecursoPropio) {
		this.montoRecursoPropio = montoRecursoPropio;
	}
	public String getTipoNomina() {
		return tipoNomina;
	}
	public void setTipoNomina(String tipoNomina) {
		this.tipoNomina = tipoNomina;
	}
	
	
	
	

}