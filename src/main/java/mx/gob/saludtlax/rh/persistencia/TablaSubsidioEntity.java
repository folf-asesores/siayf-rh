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
@Table(name = "tablas_subsidios")
public class TablaSubsidioEntity implements Serializable {

    private static final long serialVersionUID = 3111008823681449423L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tabla_subsidio")
    private Integer idTablaSubsidio;

    @Column(name = "limite_inferior")
    private BigDecimal limiteInferior;

    @Column(name = "limite_superior")
    private BigDecimal limiteSuperior;

    @Column(name = "subsidio")
    private BigDecimal subsidio;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_periodo")
    private TipoPeriodoEntity idTipoPeriodo;

    @Column(name = "ejercicio_fiscal")
    private Integer ejercicioFiscal;

//		<Getters & Setters>
    public Integer getIdTablaSubsidio() {
        return idTablaSubsidio;
    }

    public void setIdTablaSubsidio(Integer idTablaSubsidio) {
        this.idTablaSubsidio = idTablaSubsidio;
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

    public BigDecimal getSubsidio() {
        return subsidio;
    }

    public void setSubsidio(BigDecimal subsidio) {
        this.subsidio = subsidio;
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

}
