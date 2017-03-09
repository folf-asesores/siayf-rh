package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "periodos_calendarios")
public class PeriodoCalendariosEntity implements Serializable {
	private static final long serialVersionUID = 3111008823681449423L;

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="id_periodo_calendario")
		private Integer idPeriodoCalendario;
		@Column(name = "inicio_periodo")
		private Date inicioPeriodo;
		@Column(name = "fin_periodo")
		private Date finPeriodo;
		@Column(name = "numero_periodo")
		private Integer numeroPeriodo;

		@OneToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "tipo_periodo")
		private TipoPeriodoEntity tipoPeriodo;

		@Column(name = "id_ejercicio_fiscal")
		private Integer idEjercicioFiscal;

//		<Getters & Setters>
		
		public Integer getIdPeriodoCalendario() {
			return idPeriodoCalendario;
		}
		public void setIdPeriodoCalendario(Integer idPeriodoCalendario) {
			this.idPeriodoCalendario = idPeriodoCalendario;
		}
		public Date getInicioPeriodo() {
			return inicioPeriodo;
		}
		public void setInicioPeriodo(Date inicioPeriodo) {
			this.inicioPeriodo = inicioPeriodo;
		}
		public Date getFinPeriodo() {
			return finPeriodo;
		}
		public void setFinPeriodo(Date finPeriodo) {
			this.finPeriodo = finPeriodo;
		}
		public Integer getNumeroPeriodo() {
			return numeroPeriodo;
		}
		public void setNumeroPeriodo(Integer numeroPeriodo) {
			this.numeroPeriodo = numeroPeriodo;
		}
		public TipoPeriodoEntity getTipoPeriodo() {
			return tipoPeriodo;
		}
		public void setTipoPeriodo(TipoPeriodoEntity tipoPeriodo) {
			this.tipoPeriodo = tipoPeriodo;
		}
		public Integer getIdEjercicioFiscal() {
			return idEjercicioFiscal;
		}
		public void setIdEjercicioFiscal(Integer idEjercicioFiscal) {
			this.idEjercicioFiscal = idEjercicioFiscal;
		}
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
}