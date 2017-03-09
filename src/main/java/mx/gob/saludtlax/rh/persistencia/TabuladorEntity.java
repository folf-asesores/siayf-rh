/**
 * Copyright © 2016
 */
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author Eduardo Mex
 * @email lic.eduardo_mex@hotmail.com
 * @version 1.0
 * @since 28/07/2016 12:19:23
 */
@Entity
@Table(name = "tabuladores")
public class TabuladorEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1386667764533913609L;

	@Id
	@Column(name = "id_tabulador")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTabulador;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_puesto_general")
	private PuestoGeneralEntity puestoGeneral;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo_tabulador")
	private TipoTabuladorEntity tipoTabulador;

	@Column(name = "ejercicio_fiscal")
	private Integer ejercicioFiscal;

	@Column(name = "sueldo_bruto_mensual", precision = 18, scale = 2)
	private BigDecimal sueldoBrutoMensual;

	@Column(name = "asignacion_bruta_mensual", precision = 18, scale = 2)
	private BigDecimal asignacionBrutaMensual;

	@Column(name = "aga_bruta_mensual", precision = 18, scale = 2)
	private BigDecimal agaBrutaMensual;

	@Column(name = "total_bruto_mensual", precision = 18, scale = 2)
	private BigDecimal totalBrutoMensual;

	@Column(name = "sueldo_base_mensual_minimo", precision = 18, scale = 2)
	private BigDecimal sueldoBaseMensualMinimo;

	@Column(name = "sueldo_base_mensual_medio", precision = 18, scale = 2)
	private BigDecimal sueldoBaseMensualMedio;

	@Column(name = "sueldo_base_mensual_maximo", precision = 18, scale = 2)
	private BigDecimal sueldoBaseMensualMaximo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_subclasificacion_tabulador")
	private SubclasificacionTabuladorEntity subclasificacion;
	
	@Column(name ="sueldo_diario")
	private BigDecimal sueldoDiario;

	/************* Getters and Setter ********/

	
	
	public Integer getIdTabulador() {
		return idTabulador;
	}

	public BigDecimal getSueldoDiario() {
		return sueldoDiario;
	}

	public void setSueldoDiario(BigDecimal sueldoDiario) {
		this.sueldoDiario = sueldoDiario;
	}

	public SubclasificacionTabuladorEntity getSubclasificacion() {
		return subclasificacion;
	}

	public void setSubclasificacion(SubclasificacionTabuladorEntity subclasificacion) {
		this.subclasificacion = subclasificacion;
	}

	public BigDecimal getSueldoBaseMensualMinimo() {
		return sueldoBaseMensualMinimo;
	}

	public void setSueldoBaseMensualMinimo(BigDecimal sueldoBaseMensualMinimo) {
		this.sueldoBaseMensualMinimo = sueldoBaseMensualMinimo;
	}

	public BigDecimal getSueldoBaseMensualMedio() {
		return sueldoBaseMensualMedio;
	}

	public void setSueldoBaseMensualMedio(BigDecimal sueldoBaseMensualMedio) {
		this.sueldoBaseMensualMedio = sueldoBaseMensualMedio;
	}

	public BigDecimal getSueldoBaseMensualMaximo() {
		return sueldoBaseMensualMaximo;
	}

	public void setSueldoBaseMensualMaximo(BigDecimal sueldoBaseMensualMaximo) {
		this.sueldoBaseMensualMaximo = sueldoBaseMensualMaximo;
	}

	public void setIdTabulador(Integer idTabulador) {
		this.idTabulador = idTabulador;
	}

	public PuestoGeneralEntity getPuestoGeneral() {
		return puestoGeneral;
	}

	public void setPuestoGeneral(PuestoGeneralEntity puestoGeneral) {
		this.puestoGeneral = puestoGeneral;
	}

	public BigDecimal getSueldoBrutoMensual() {
		return sueldoBrutoMensual;
	}

	public void setSueldoBrutoMensual(BigDecimal sueldoBrutoMensual) {
		this.sueldoBrutoMensual = sueldoBrutoMensual;
	}

	public BigDecimal getAsignacionBrutaMensual() {
		return asignacionBrutaMensual;
	}

	public void setAsignacionBrutaMensual(BigDecimal asignacionBrutaMensual) {
		this.asignacionBrutaMensual = asignacionBrutaMensual;
	}

	public BigDecimal getAgaBrutaMensual() {
		return agaBrutaMensual;
	}

	public void setAgaBrutaMensual(BigDecimal agaBrutaMensual) {
		this.agaBrutaMensual = agaBrutaMensual;
	}

	public BigDecimal getTotalBrutoMensual() {
		return totalBrutoMensual;
	}

	public void setTotalBrutoMensual(BigDecimal totalBrutoMensual) {
		this.totalBrutoMensual = totalBrutoMensual;
	}

	/**
	 * @return the tipoTabulador
	 */
	public TipoTabuladorEntity getTipoTabulador() {
		return tipoTabulador;
	}

	/**
	 * @param tipoTabulador
	 *            the tipoTabulador to set
	 */
	public void setTipoTabulador(TipoTabuladorEntity tipoTabulador) {
		this.tipoTabulador = tipoTabulador;
	}

	/**
	 * @return the ejercicioFiscal
	 */
	public Integer getEjercicioFiscal() {
		return ejercicioFiscal;
	}

	/**
	 * @param ejercicioFiscal
	 *            the ejercicioFiscal to set
	 */
	public void setEjercicioFiscal(Integer ejercicioFiscal) {
		this.ejercicioFiscal = ejercicioFiscal;
	}

}
