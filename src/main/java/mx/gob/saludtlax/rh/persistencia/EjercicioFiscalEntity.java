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

@Table(name = "ejercicios_fiscales")
public class EjercicioFiscalEntity implements Serializable {
	private static final long serialVersionUID = 3111008823681449423L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ejercicio_fiscal")
	private Integer idEjercicioFiscal;
	@Column(name = "ejercicio_fiscal")
	private Integer ejercicioFiscal;
	@Column(name = "inicio")
	private Date inicio;
	@Column(name = "fin")
	private Date fin;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo_periodo")
	private TipoPeriodoEntity tipoPeriodo;

	public Integer getIdEjercicioFiscal() {
		return idEjercicioFiscal;
	}
	public void setIdEjercicioFiscal(Integer idEjercicioFiscal) {
		this.idEjercicioFiscal = idEjercicioFiscal;
	}
	public Integer getEjercicioFiscal() {
		return ejercicioFiscal;
	}
	public void setEjercicioFiscal(Integer ejercicioFiscal) {
		this.ejercicioFiscal = ejercicioFiscal;
	}
	public Date getInicio() {
		return inicio;
	}
	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}
	public Date getFin() {
		return fin;
	} 
	public void setFin(Date fin) {
		this.fin = fin;
	}
	public TipoPeriodoEntity getTipoPeriodo() {
		return tipoPeriodo;
	}
	public void setTipoPeriodo(TipoPeriodoEntity tipoPeriodo) {
		this.tipoPeriodo = tipoPeriodo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}