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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tarifas_retenciones")
public class TarifaRetencionEntity implements Serializable {
	private static final long serialVersionUID = 3111008823681449423L;

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="id_tarifa_retencion")
		private Integer idTarifaRetencion;
		
		@Column(name = "limite_inferior")
		private BigDecimal limiteInferior;
		
		@Column(name = "limite_superior")
		private BigDecimal limiteSuperior;
		
		@Column(name = "cuota_fija")
		private BigDecimal cuotaFija;
		
		@Column (name="porcentaje_aplicable")
		private BigDecimal porcentajeAplicable;
		
		@OneToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "id_tipo_periodo")
		private TipoPeriodoEntity idTipoPeriodo;
		
		@Column(name="ejercicio_fiscal")
		private Integer ejercicioFiscal;
		
//		<Getters & Setters>

		public Integer getIdTarifaRetencion() {
			return idTarifaRetencion;
		}

		public void setIdTarifaRetencion(Integer idTarifaRetencion) {
			this.idTarifaRetencion = idTarifaRetencion;
		}

		public BigDecimal getLimiteInferior() {
			return limiteInferior;
		}

		public void setLimiteInferior(BigDecimal limiteInferior) {
			this.limiteInferior = limiteInferior;
		}

		public BigDecimal getLimiteSuperior() {
			return limiteSuperior;
		}

		public void setLimiteSuperior(BigDecimal limiteSuperior) {
			this.limiteSuperior = limiteSuperior;
		}

		public BigDecimal getCuotaFija() {
			return cuotaFija;
		}

		public void setCuotaFija(BigDecimal cuotaFija) {
			this.cuotaFija = cuotaFija;
		}

		public BigDecimal getPorcentajeAplicable() {
			return porcentajeAplicable;
		}

		public void setPorcentajeAplicable(BigDecimal porcentajeAplicable) {
			this.porcentajeAplicable = porcentajeAplicable;
		}

		

		public TipoPeriodoEntity getIdTipoPeriodo() {
			return idTipoPeriodo;
		}

		public void setIdTipoPeriodo(TipoPeriodoEntity idTipoPeriodo) {
			this.idTipoPeriodo = idTipoPeriodo;
		}

		public Integer getEjercicioFiscal() {
			return ejercicioFiscal;
		}

		public void setEjercicioFiscal(Integer ejercicioFiscal) {
			this.ejercicioFiscal = ejercicioFiscal;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}
	}
