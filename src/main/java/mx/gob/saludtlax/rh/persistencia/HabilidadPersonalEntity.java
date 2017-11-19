/**
 * Copyright © 2016
 */
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Eduardo Mex
 * @version 22/03/2016 16:52:55
 * @email Lic.Eduardo_Mex@hotmail.com
 */
@Entity
@Table(name = "habilidades_personales")
public class HabilidadPersonalEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5509407771404549088L;

	@Id
	@Column(name = "id_habilidad_personal")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idEncuestaPersonalAspirante;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_aspirante")
	private AspiranteEntity aspirante;

	@Column(name = "idioma")
	private String idioma;

	@Column(name = "nivel_idioma")
	private Integer nivelIdioma;

	@Column(name = "maquina_taller_domina")
	private String maquinaTallerDomina;

	@Column(name = "funciones_oficina_domina")
	private String funcionesOficinaDomina;

	@Column(name = "software_domina")
	private String softwareDomina;

	@Column(name = "otro_trabajo_funcion")
	private String otrosTrabajosFunciones;

	@Column(name = "anuncio")
	private boolean anuncio;

	@Column(name = "otro_medio")
	private String otroMedio;

	@Column(name = "pariente")
	private boolean parientes;

	@Column(name = "nombre_pariente")
	private String nombreParientes;

	@Column(name = "afianzado")
	private boolean afianzado;

	@Column(name = "nombre_afianza")
	private String nombreAfianza;

	@Column(name = "sindicato")
	private boolean sindicato;

	@Column(name = "nombre_sindicato")
	private String nombreSindicato;

	@Column(name = "seguro_vida")
	private boolean seguroVida;

	@Column(name = "nombre_seguro_vida")
	private String nombreSeguroVida;

	@Column(name = "disposicion_viajar")
	private boolean disposicionViajar;

	@Column(name = "razon_no_viajar")
	private String razonNoViajar;

	@Column(name = "cambio_residencia")
	private boolean cambioResidencia;

	@Column(name = "razon_no_cambio_residencia")
	private String razonNoCambioResidencia;

	@Column(name = "fecha_empezar_trabajar")
	private Date fechaEmpezarTrabajar;

	@Column(name = "otro_ingreso")
	private boolean otroIngreso;

	@Column(name = "nombre_otro_ingreso")
	private String nombreOtroIngreso;

	@Column(name = "importe_otro_ingreso")
	private BigDecimal importeOtroIngreso;

	@Column(name = "conyuge_trabajando")
	private boolean conyugeTrabajando;

	@Column(name = "nombre_trabajo_conyuge")
	private String nombreTrabajoConyuge;

	@Column(name = "percepcion_mensual_conyuge")
	private BigDecimal percepcionMensualConyuge;

	@Column(name = "casa_propia")
	private boolean casaPropia;

	@Column(name = "valor_aproximado_casa")
	private BigDecimal valorAproximadoCasa;

	@Column(name = "renta_casa")
	private boolean rentaCasa;

	@Column(name = "renta_mensual")
	private BigDecimal rentaMensual;

	@Column(name = "automovil_propio")
	private boolean automovilPropio;

	@Column(name = "marca_automovil")
	private String marcaAutomovil;

	@Column(name = "modelo_automovil")
	private String modeloAutomovil;

	@Column(name = "deudas")
	private boolean deudas;

	@Column(name = "nombre_deuda")
	private String nombreDeuda;

	@Column(name = "importe_deuda")
	private BigDecimal importeDeuda;

	@Column(name = "abono_mensual_deuda")
	private BigDecimal abonoMensualDeuda;

	@Column(name = "gasto_mensual")
	private BigDecimal gastoMensual;

	
	/**
	 * @return the idEncuestaPersonalAspirante
	 */
	public Integer getIdEncuestaPersonalAspirante() {
		return idEncuestaPersonalAspirante;
	}

	/**
	 * @param idEncuestaPersonalAspirante
	 *            the idEncuestaPersonalAspirante to set
	 */
	public void setIdEncuestaPersonalAspirante(Integer idEncuestaPersonalAspirante) {
		this.idEncuestaPersonalAspirante = idEncuestaPersonalAspirante;
	}

	/**
	 * @return the aspirante
	 */
	public AspiranteEntity getAspirante() {
		return aspirante;
	}

	/**
	 * @param aspirante
	 *            the aspirante to set
	 */
	public void setAspirante(AspiranteEntity aspirante) {
		this.aspirante = aspirante;
	}

	/**
	 * @return the idioma
	 */
	public String getIdioma() {
		return idioma;
	}

	/**
	 * @param idioma
	 *            the idioma to set
	 */
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	/**
	 * @return the nivelIdioma
	 */
	public Integer getNivelIdioma() {
		return nivelIdioma;
	}

	/**
	 * @param nivelIdioma
	 *            the nivelIdioma to set
	 */
	public void setNivelIdioma(Integer nivelIdioma) {
		this.nivelIdioma = nivelIdioma;
	}

	/**
	 * @return the maquinaTallerDomina
	 */
	public String getMaquinaTallerDomina() {
		return maquinaTallerDomina;
	}

	/**
	 * @param maquinaTallerDomina
	 *            the maquinaTallerDomina to set
	 */
	public void setMaquinaTallerDomina(String maquinaTallerDomina) {
		this.maquinaTallerDomina = maquinaTallerDomina;
	}

	/**
	 * @return the funcionesOficinaDomina
	 */
	public String getFuncionesOficinaDomina() {
		return funcionesOficinaDomina;
	}

	/**
	 * @param funcionesOficinaDomina
	 *            the funcionesOficinaDomina to set
	 */
	public void setFuncionesOficinaDomina(String funcionesOficinaDomina) {
		this.funcionesOficinaDomina = funcionesOficinaDomina;
	}

	/**
	 * @return the softwareDomina
	 */
	public String getSoftwareDomina() {
		return softwareDomina;
	}

	/**
	 * @param softwareDomina
	 *            the softwareDomina to set
	 */
	public void setSoftwareDomina(String softwareDomina) {
		this.softwareDomina = softwareDomina;
	}

	/**
	 * @return the otrosTrabajosFunciones
	 */
	public String getOtrosTrabajosFunciones() {
		return otrosTrabajosFunciones;
	}

	/**
	 * @param otrosTrabajosFunciones
	 *            the otrosTrabajosFunciones to set
	 */
	public void setOtrosTrabajosFunciones(String otrosTrabajosFunciones) {
		this.otrosTrabajosFunciones = otrosTrabajosFunciones;
	}

	/**
	 * @return the anuncio
	 */
	public boolean getAnuncio() {
		return anuncio;
	}

	/**
	 * @param anuncio
	 *            the anuncio to set
	 */
	public void setAnuncio(boolean anuncio) {
		this.anuncio = anuncio;
	}

	/**
	 * @return the otroMedio
	 */
	public String getOtroMedio() {
		return otroMedio;
	}

	/**
	 * @param otroMedio
	 *            the otroMedio to set
	 */
	public void setOtroMedio(String otroMedio) {
		this.otroMedio = otroMedio;
	}

	/**
	 * @return the parientes
	 */
	public boolean isParientes() {
		return parientes;
	}

	/**
	 * @param parientes
	 *            the parientes to set
	 */
	public void setParientes(boolean parientes) {
		this.parientes = parientes;
	}

	/**
	 * @return the nombreParientes
	 */
	public String getNombreParientes() {
		return nombreParientes;
	}

	/**
	 * @param nombreParientes
	 *            the nombreParientes to set
	 */
	public void setNombreParientes(String nombreParientes) {
		this.nombreParientes = nombreParientes;
	}

	/**
	 * @return the afianzado
	 */
	public boolean isAfianzado() {
		return afianzado;
	}

	/**
	 * @param afianzado
	 *            the afianzado to set
	 */
	public void setAfianzado(boolean afianzado) {
		this.afianzado = afianzado;
	}

	/**
	 * @return the nombreAfianza
	 */
	public String getNombreAfianza() {
		return nombreAfianza;
	}

	/**
	 * @param nombreAfianza
	 *            the nombreAfianza to set
	 */
	public void setNombreAfianza(String nombreAfianza) {
		this.nombreAfianza = nombreAfianza;
	}

	/**
	 * @return the sindicato
	 */
	public boolean isSindicato() {
		return sindicato;
	}

	/**
	 * @param sindicato
	 *            the sindicato to set
	 */
	public void setSindicato(boolean sindicato) {
		this.sindicato = sindicato;
	}

	/**
	 * @return the nombreSindicato
	 */
	public String getNombreSindicato() {
		return nombreSindicato;
	}

	/**
	 * @param nombreSindicato
	 *            the nombreSindicato to set
	 */
	public void setNombreSindicato(String nombreSindicato) {
		this.nombreSindicato = nombreSindicato;
	}

	/**
	 * @return the seguroVida
	 */
	public boolean isSeguroVida() {
		return seguroVida;
	}

	/**
	 * @param seguroVida
	 *            the seguroVida to set
	 */
	public void setSeguroVida(boolean seguroVida) {
		this.seguroVida = seguroVida;
	}

	/**
	 * @return the nombreSeguroVida
	 */
	public String getNombreSeguroVida() {
		return nombreSeguroVida;
	}

	/**
	 * @param nombreSeguroVida
	 *            the nombreSeguroVida to set
	 */
	public void setNombreSeguroVida(String nombreSeguroVida) {
		this.nombreSeguroVida = nombreSeguroVida;
	}

	/**
	 * @return the disposicionViajar
	 */
	public boolean isDisposicionViajar() {
		return disposicionViajar;
	}

	/**
	 * @param disposicionViajar
	 *            the disposicionViajar to set
	 */
	public void setDisposicionViajar(boolean disposicionViajar) {
		this.disposicionViajar = disposicionViajar;
	}

	/**
	 * @return the razonNoViajar
	 */
	public String getRazonNoViajar() {
		return razonNoViajar;
	}

	/**
	 * @param razonNoViajar
	 *            the razonNoViajar to set
	 */
	public void setRazonNoViajar(String razonNoViajar) {
		this.razonNoViajar = razonNoViajar;
	}

	/**
	 * @return the cambioResidencia
	 */
	public boolean isCambioResidencia() {
		return cambioResidencia;
	}

	/**
	 * @param cambioResidencia
	 *            the cambioResidencia to set
	 */
	public void setCambioResidencia(boolean cambioResidencia) {
		this.cambioResidencia = cambioResidencia;
	}

	/**
	 * @return the razonNoCambioResidencia
	 */
	public String getRazonNoCambioResidencia() {
		return razonNoCambioResidencia;
	}

	/**
	 * @param razonNoCambioResidencia
	 *            the razonNoCambioResidencia to set
	 */
	public void setRazonNoCambioResidencia(String razonNoCambioResidencia) {
		this.razonNoCambioResidencia = razonNoCambioResidencia;
	}

	/**
	 * @return the fechaEmpezarTrabajar
	 */
	public Date getFechaEmpezarTrabajar() {
		return fechaEmpezarTrabajar;
	}

	/**
	 * @param fechaEmpezarTrabajar
	 *            the fechaEmpezarTrabajar to set
	 */
	public void setFechaEmpezarTrabajar(Date fechaEmpezarTrabajar) {
		this.fechaEmpezarTrabajar = fechaEmpezarTrabajar;
	}

	/**
	 * @return the otroIngreso
	 */
	public boolean isOtroIngreso() {
		return otroIngreso;
	}

	/**
	 * @param otroIngreso
	 *            the otroIngreso to set
	 */
	public void setOtroIngreso(boolean otroIngreso) {
		this.otroIngreso = otroIngreso;
	}

	/**
	 * @return the nombreOtroIngreso
	 */
	public String getNombreOtroIngreso() {
		return nombreOtroIngreso;
	}

	/**
	 * @param nombreOtroIngreso
	 *            the nombreOtroIngreso to set
	 */
	public void setNombreOtroIngreso(String nombreOtroIngreso) {
		this.nombreOtroIngreso = nombreOtroIngreso;
	}

	/**
	 * @return the importeOtroIngreso
	 */
	public BigDecimal getImporteOtroIngreso() {
		return importeOtroIngreso;
	}

	/**
	 * @param importeOtroIngreso
	 *            the importeOtroIngreso to set
	 */
	public void setImporteOtroIngreso(BigDecimal importeOtroIngreso) {
		this.importeOtroIngreso = importeOtroIngreso;
	}

	/**
	 * @return the conyugeTrabajando
	 */
	public boolean isConyugeTrabajando() {
		return conyugeTrabajando;
	}

	/**
	 * @param conyugeTrabajando
	 *            the conyugeTrabajando to set
	 */
	public void setConyugeTrabajando(boolean conyugeTrabajando) {
		this.conyugeTrabajando = conyugeTrabajando;
	}

	/**
	 * @return the nombreTrabajoConyuge
	 */
	public String getNombreTrabajoConyuge() {
		return nombreTrabajoConyuge;
	}

	/**
	 * @param nombreTrabajoConyuge
	 *            the nombreTrabajoConyuge to set
	 */
	public void setNombreTrabajoConyuge(String nombreTrabajoConyuge) {
		this.nombreTrabajoConyuge = nombreTrabajoConyuge;
	}

	/**
	 * @return the percepcionMensualConyuge
	 */
	public BigDecimal getPercepcionMensualConyuge() {
		return percepcionMensualConyuge;
	}

	/**
	 * @param percepcionMensualConyuge
	 *            the percepcionMensualConyuge to set
	 */
	public void setPercepcionMensualConyuge(BigDecimal percepcionMensualConyuge) {
		this.percepcionMensualConyuge = percepcionMensualConyuge;
	}

	/**
	 * @return the casaPropia
	 */
	public boolean isCasaPropia() {
		return casaPropia;
	}

	/**
	 * @param casaPropia
	 *            the casaPropia to set
	 */
	public void setCasaPropia(boolean casaPropia) {
		this.casaPropia = casaPropia;
	}

	/**
	 * @return the valorAproximadoCasa
	 */
	public BigDecimal getValorAproximadoCasa() {
		return valorAproximadoCasa;
	}

	/**
	 * @param valorAproximadoCasa
	 *            the valorAproximadoCasa to set
	 */
	public void setValorAproximadoCasa(BigDecimal valorAproximadoCasa) {
		this.valorAproximadoCasa = valorAproximadoCasa;
	}

	/**
	 * @return the rentaCasa
	 */
	public boolean isRentaCasa() {
		return rentaCasa;
	}

	/**
	 * @param rentaCasa
	 *            the rentaCasa to set
	 */
	public void setRentaCasa(boolean rentaCasa) {
		this.rentaCasa = rentaCasa;
	}

	/**
	 * @return the rentaMensual
	 */
	public BigDecimal getRentaMensual() {
		return rentaMensual;
	}

	/**
	 * @param rentaMensual
	 *            the rentaMensual to set
	 */
	public void setRentaMensual(BigDecimal rentaMensual) {
		this.rentaMensual = rentaMensual;
	}

	/**
	 * @return the automovilPropio
	 */
	public boolean isAutomovilPropio() {
		return automovilPropio;
	}

	/**
	 * @param automovilPropio
	 *            the automovilPropio to set
	 */
	public void setAutomovilPropio(boolean automovilPropio) {
		this.automovilPropio = automovilPropio;
	}

	/**
	 * @return the marcaAutomovil
	 */
	public String getMarcaAutomovil() {
		return marcaAutomovil;
	}

	/**
	 * @param marcaAutomovil
	 *            the marcaAutomovil to set
	 */
	public void setMarcaAutomovil(String marcaAutomovil) {
		this.marcaAutomovil = marcaAutomovil;
	}

	/**
	 * @return the modeloAutomovil
	 */
	public String getModeloAutomovil() {
		return modeloAutomovil;
	}

	/**
	 * @param modeloAutomovil
	 *            the modeloAutomovil to set
	 */
	public void setModeloAutomovil(String modeloAutomovil) {
		this.modeloAutomovil = modeloAutomovil;
	}

	/**
	 * @return the deudas
	 */
	public boolean isDeudas() {
		return deudas;
	}

	/**
	 * @param deudas
	 *            the deudas to set
	 */
	public void setDeudas(boolean deudas) {
		this.deudas = deudas;
	}

	/**
	 * @return the nombreDeuda
	 */
	public String getNombreDeuda() {
		return nombreDeuda;
	}

	/**
	 * @param nombreDeuda
	 *            the nombreDeuda to set
	 */
	public void setNombreDeuda(String nombreDeuda) {
		this.nombreDeuda = nombreDeuda;
	}

	/**
	 * @return the importeDeuda
	 */
	public BigDecimal getImporteDeuda() {
		return importeDeuda;
	}

	/**
	 * @param importeDeuda
	 *            the importeDeuda to set
	 */
	public void setImporteDeuda(BigDecimal importeDeuda) {
		this.importeDeuda = importeDeuda;
	}

	/**
	 * @return the abonoMensualDeuda
	 */
	public BigDecimal getAbonoMensualDeuda() {
		return abonoMensualDeuda;
	}

	/**
	 * @param abonoMensualDeuda
	 *            the abonoMensualDeuda to set
	 */
	public void setAbonoMensualDeuda(BigDecimal abonoMensualDeuda) {
		this.abonoMensualDeuda = abonoMensualDeuda;
	}

	/**
	 * @return the gastoMensual
	 */
	public BigDecimal getGastoMensual() {
		return gastoMensual;
	}

	/**
	 * @param gastoMensual
	 *            the gastoMensual to set
	 */
	public void setGastoMensual(BigDecimal gastoMensual) {
		this.gastoMensual = gastoMensual;
	}

}
