
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "comprobantes")
public class ComprobanteEstatalEntity implements Serializable {
    private static final long serialVersionUID = 4396931589155152880L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comprobante")
    private Integer idComprobante;

    //	@OneToOne(fetch = FetchType.LAZY)
    //	@JoinColumn(name = "IdUbicacion")
    //	private UbicacionEstatalEntity domicilio; //
    @Column(name = "Rfc")
    private String rfc;
    @Column(name = "Nombre")
    private String nombre;

    //	@OneToMany(fetch= FetchType.LAZY)
    //	@JoinColumn(name = "IdComprobante", referencedColumnName = "IdComprobante")
    //	private List<ConceptoEstatalEntity> conceptos; //
    //	@OneToMany(fetch= FetchType.LAZY)
    //	@JoinColumn(name = "IdComprobante", referencedColumnName = "IdComprobante")
    //	private List<RetencionEstatalEntity> retenciones;
    //    @Column(name = "importe_retencion")
    //    private BigDecimal importeRetencion;

    @Column(name = "total_impuestos_retenidos")
    private BigDecimal totalImpuestosRetenidos;
    @Column(name = "total_impuestos_trasladados")
    private BigDecimal totalImpuestosTrasladados;

    //	@OneToOne(fetch = FetchType.LAZY)
    //	@JoinColumn(name = "id_comprobante")
    //	private NominaEstatalEntity nomina;

    @Column(name = "version")
    private String version;
    @Column(name = "serie")
    private String serie;
    @Column(name = "folio")
    private String folio;
    @Column(name = "fecha")
    private Date fecha;
    @Column(name = "sello")
    private String sello;
    @Column(name = "forma_pago")
    private String formaDePago;
    @Column(name = "no_certificado")
    private String noCertificado;
    @Column(name = "certificado")
    private String certificado;
    @Column(name = "subtotal")
    private BigDecimal subTotal;
    @Column(name = "descuento")
    private BigDecimal descuento;
    @Column(name = "motivo_descuento")
    private String motivoDescuento;
    @Column(name = "total")
    private BigDecimal total;
    @Column(name = "tipo_comprobante")
    private String tipoDeComprobante;
    @Column(name = "metodo_pago")
    private String metodoDePago;
    @Column(name = "lugar_expedicion")
    private String lugarExpedicion;
    @Column(name = "num_cta_pago")
    private String numCtaPago;
    @Column(name = "folio_fiscal_orig")
    private String folioFiscalOrig;
    @Column(name = "serie_folio_fiscal_orig")
    private String serieFolioFiscalOrig;
    @Column(name = "fecha_folio_fiscal_orig")
    private Date fechaFolioFiscalOrig;
    @Column(name = "monto_folio_fiscal_orig")
    private BigDecimal montoFolioFiscalOrig;

    @Column(name = "version_timbre")
    private String versionTimbre;
    @Column(name = "uuid")
    private String uuid;
    @Column(name = "fecha_timbrado")
    private Date fechaTimbrado;
    @Column(name = "sello_cfd")
    private String selloCFD;
    @Column(name = "no_certificado_sat")
    private String noCertificadoSAT;
    @Column(name = "sello_sat")
    private String selloSAT;

    @Column(name = "comprobante_xml")
    private byte[] comprobanteXML;
    @Column(name = "num_xml")
    private Integer numXML;
    @Column(name = "id_xml")
    private Integer idXML;
    @Column(name = "cadena_original")
    private String cadenaOriginal;

    //	@Id
    //	@GeneratedValue(strategy = GenerationType.IDENTITY)
    //	@Column(name="id_comprobante")
    //	private Integer idNomina;
    @Column(name = "version_nomina")
    private String versionNomina;
    @Column(name = "registro_patronal")
    private String registroPatronal;
    @Column(name = "num_empleado")
    private String numEmpleado;
    @Column(name = "curp")
    private String curp;
    @Column(name = "tipo_regimen")
    private int tipoRegimen;
    @Column(name = "num_seguridad_social")
    private String numSeguridadSocial;
    @Column(name = "fecha_pago")
    private Date fechaPago;
    @Column(name = "fecha_inicial_pago")
    private Date fechaInicialPago;
    @Column(name = "fecha_final_pago")
    private Date fechaFinalPago;
    @Column(name = "num_dias_pagados")
    private BigDecimal numDiasPagados;
    @Column(name = "departamento")
    private String departamento;
    @Column(name = "clabe")
    private BigInteger clabe;
    @Column(name = "banco")
    private String banco;
    @Column(name = "fecha_inicio_rel_laboral")
    private Date fechaInicioRelLaboral;
    @Column(name = "antiguedad")
    private Integer antiguedad;
    @Column(name = "puesto")
    private String puesto;
    @Column(name = "tipo_contrato")
    private String tipoContrato;
    @Column(name = "tipo_jornada")
    private String tipoJornada;
    @Column(name = "periodicidad_pago")
    private String periodicidadPago;
    @Column(name = "salario_base_cot_apor")
    private BigDecimal salarioBaseCotApor;
    @Column(name = "riesgo_puesto")
    private Integer riesgoPuesto;
    @Column(name = "salario_diario_integrado")
    private BigDecimal salarioDiarioIntegrado;

    @Column(name = "percepciones_total_gravado")
    private BigDecimal percepcionesTotalGravado;
    @Column(name = "percepciones_total_exento")
    private BigDecimal percepcionesTotalExento;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_comprobante", referencedColumnName = "id_comprobante")
    private List<PercepcionEstatalEntity> percepciones;

    @Column(name = "deducciones_total_gravado")
    private BigDecimal deduccionesTotalGravado;
    @Column(name = "deducciones_total_exento")
    private BigDecimal deduccionesTotalExento;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_comprobante", referencedColumnName = "id_comprobante")
    private List<DeduccionEstatalEntity> deducciones;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_comprobante", referencedColumnName = "id_comprobante")
    private List<IncapacidadEstatalEntity> incapacidades;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_comprobante", referencedColumnName = "id_comprobante")
    private List<HorasExtraEstatalEntity> horasExtra;

    public Integer getIdComprobante() {
        return idComprobante;
    }

    public void setIdComprobante(Integer idComprobante) {
        this.idComprobante = idComprobante;
    }

    //	public UbicacionEstatalEntity getDomicilio() {
    //		return domicilio;
    //	}
    //	public void setDomicilio(UbicacionEstatalEntity domicilio) {
    //		this.domicilio = domicilio;
    //	}
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

    public BigDecimal getTotalImpuestosRetenidos() {
        return totalImpuestosRetenidos;
    }

    public void setTotalImpuestosRetenidos(BigDecimal totalImpuestosRetenidos) {
        this.totalImpuestosRetenidos = totalImpuestosRetenidos;
    }

    public BigDecimal getTotalImpuestosTrasladados() {
        return totalImpuestosTrasladados;
    }

    public void setTotalImpuestosTrasladados(
            BigDecimal totalImpuestosTrasladados) {
        this.totalImpuestosTrasladados = totalImpuestosTrasladados;
    }

    //	public NominaEstatalEntity getNomina() {
    //		return nomina;
    //	}
    //	public void setNomina(NominaEstatalEntity nomina) {
    //		this.nomina = nomina;
    //	}
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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

    public String getFormaDePago() {
        return formaDePago;
    }

    public void setFormaDePago(String formaDePago) {
        this.formaDePago = formaDePago;
    }

    public String getNoCertificado() {
        return noCertificado;
    }

    public void setNoCertificado(String noCertificado) {
        this.noCertificado = noCertificado;
    }

    public String getCertificado() {
        return certificado;
    }

    public void setCertificado(String certificado) {
        this.certificado = certificado;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public String getMotivoDescuento() {
        return motivoDescuento;
    }

    public void setMotivoDescuento(String motivoDescuento) {
        this.motivoDescuento = motivoDescuento;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getTipoDeComprobante() {
        return tipoDeComprobante;
    }

    public void setTipoDeComprobante(String tipoDeComprobante) {
        this.tipoDeComprobante = tipoDeComprobante;
    }

    public String getMetodoDePago() {
        return metodoDePago;
    }

    public void setMetodoDePago(String metodoDePago) {
        this.metodoDePago = metodoDePago;
    }

    public String getLugarExpedicion() {
        return lugarExpedicion;
    }

    public void setLugarExpedicion(String lugarExpedicion) {
        this.lugarExpedicion = lugarExpedicion;
    }

    public String getNumCtaPago() {
        return numCtaPago;
    }

    public void setNumCtaPago(String numCtaPago) {
        this.numCtaPago = numCtaPago;
    }

    public String getFolioFiscalOrig() {
        return folioFiscalOrig;
    }

    public void setFolioFiscalOrig(String folioFiscalOrig) {
        this.folioFiscalOrig = folioFiscalOrig;
    }

    public String getSerieFolioFiscalOrig() {
        return serieFolioFiscalOrig;
    }

    public void setSerieFolioFiscalOrig(String serieFolioFiscalOrig) {
        this.serieFolioFiscalOrig = serieFolioFiscalOrig;
    }

    public Date getFechaFolioFiscalOrig() {
        return fechaFolioFiscalOrig;
    }

    public void setFechaFolioFiscalOrig(Date fechaFolioFiscalOrig) {
        this.fechaFolioFiscalOrig = fechaFolioFiscalOrig;
    }

    public BigDecimal getMontoFolioFiscalOrig() {
        return montoFolioFiscalOrig;
    }

    public void setMontoFolioFiscalOrig(BigDecimal montoFolioFiscalOrig) {
        this.montoFolioFiscalOrig = montoFolioFiscalOrig;
    }

    public String getVersionTimbre() {
        return versionTimbre;
    }

    public void setVersionTimbre(String versionTimbre) {
        this.versionTimbre = versionTimbre;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getFechaTimbrado() {
        return fechaTimbrado;
    }

    public void setFechaTimbrado(Date fechaTimbrado) {
        this.fechaTimbrado = fechaTimbrado;
    }

    public String getSelloCFD() {
        return selloCFD;
    }

    public void setSelloCFD(String selloCFD) {
        this.selloCFD = selloCFD;
    }

    public String getNoCertificadoSAT() {
        return noCertificadoSAT;
    }

    public void setNoCertificadoSAT(String noCertificadoSAT) {
        this.noCertificadoSAT = noCertificadoSAT;
    }

    public String getSelloSAT() {
        return selloSAT;
    }

    public void setSelloSAT(String selloSAT) {
        this.selloSAT = selloSAT;
    }

    public byte[] getComprobanteXML() {
        return comprobanteXML;
    }

    public void setComprobanteXML(byte[] comprobanteXML) {
        this.comprobanteXML = comprobanteXML;
    }

    public Integer getNumXML() {
        return numXML;
    }

    public void setNumXML(Integer numXML) {
        this.numXML = numXML;
    }

    public Integer getIdXML() {
        return idXML;
    }

    public void setIdXML(Integer idXML) {
        this.idXML = idXML;
    }

    public String getCadenaOriginal() {
        return cadenaOriginal;
    }

    public void setCadenaOriginal(String cadenaOriginal) {
        this.cadenaOriginal = cadenaOriginal;
    }

    @Column(name = "calle")
    private String calle;
    @Column(name = "no_exterior")
    private String noExterior;
    @Column(name = "no_interior")
    private String noInterior;
    @Column(name = "colonia")
    private String colonia;
    @Column(name = "localidad")
    private String localidad;
    @Column(name = "referencia")
    private String referencia;
    @Column(name = "municipio")
    private String municipio;
    @Column(name = "estado")
    private String estado;
    @Column(name = "pais")
    private String pais;
    @Column(name = "codigo_postal")
    private String codigoPostal;

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

    public String getNumEmpleado() {
        return numEmpleado;
    }

    public void setNumEmpleado(String numEmpleado) {
        this.numEmpleado = numEmpleado;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public int getTipoRegimen() {
        return tipoRegimen;
    }

    public void setTipoRegimen(int tipoRegimen) {
        this.tipoRegimen = tipoRegimen;
    }

    public String getNumSeguridadSocial() {
        return numSeguridadSocial;
    }

    public void setNumSeguridadSocial(String numSeguridadSocial) {
        this.numSeguridadSocial = numSeguridadSocial;
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

    public BigDecimal getNumDiasPagados() {
        return numDiasPagados;
    }

    public void setNumDiasPagados(BigDecimal numDiasPagados) {
        this.numDiasPagados = numDiasPagados;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public BigInteger getClabe() {
        return clabe;
    }

    public void setClabe(BigInteger clabe) {
        this.clabe = clabe;
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

    public String getPeriodicidadPago() {
        return periodicidadPago;
    }

    public void setPeriodicidadPago(String periodicidadPago) {
        this.periodicidadPago = periodicidadPago;
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

    public BigDecimal getPercepcionesTotalGravado() {
        return percepcionesTotalGravado;
    }

    public void setPercepcionesTotalGravado(
            BigDecimal percepcionesTotalGravado) {
        this.percepcionesTotalGravado = percepcionesTotalGravado;
    }

    public BigDecimal getPercepcionesTotalExento() {
        return percepcionesTotalExento;
    }

    public void setPercepcionesTotalExento(BigDecimal percepcionesTotalExento) {
        this.percepcionesTotalExento = percepcionesTotalExento;
    }

    public List<PercepcionEstatalEntity> getPercepciones() {
        return percepciones;
    }

    public void setPercepciones(List<PercepcionEstatalEntity> percepciones) {
        this.percepciones = percepciones;
    }

    public BigDecimal getDeduccionesTotalGravado() {
        return deduccionesTotalGravado;
    }

    public void setDeduccionesTotalGravado(BigDecimal deduccionesTotalGravado) {
        this.deduccionesTotalGravado = deduccionesTotalGravado;
    }

    public BigDecimal getDeduccionesTotalExento() {
        return deduccionesTotalExento;
    }

    public void setDeduccionesTotalExento(BigDecimal deduccionesTotalExento) {
        this.deduccionesTotalExento = deduccionesTotalExento;
    }

    public List<DeduccionEstatalEntity> getDeducciones() {
        return deducciones;
    }

    public void setDeducciones(List<DeduccionEstatalEntity> deducciones) {
        this.deducciones = deducciones;
    }

    public List<IncapacidadEstatalEntity> getIncapacidades() {
        return incapacidades;
    }

    public void setIncapacidades(List<IncapacidadEstatalEntity> incapacidades) {
        this.incapacidades = incapacidades;
    }

    public List<HorasExtraEstatalEntity> getHorasExtra() {
        return horasExtra;
    }

    public void setHorasExtra(List<HorasExtraEstatalEntity> horasExtra) {
        this.horasExtra = horasExtra;
    }
}