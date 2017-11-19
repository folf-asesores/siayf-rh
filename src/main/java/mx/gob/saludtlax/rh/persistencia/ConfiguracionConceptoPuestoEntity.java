
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

@Entity
@Table(name = "configuracion_concepto_puesto")
public class ConfiguracionConceptoPuestoEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5390670476557355471L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_configuracion_concepto")
    private Integer idConfiguracionConcepto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_concepto_nomina")
    private ConceptoNominaFederalesEntity conceptoNomina;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tabulador")
    private TabuladorEntity tabulador;

    @Column(name = "importe_concepto")
    private BigDecimal importeConcepto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_puesto_general")
    private PuestoGeneralEntity puestoGeneral;

    @Column(name = "formula")
    private String formula;

    public Integer getIdConfiguracionConcepto() {
        return idConfiguracionConcepto;
    }

    public void setIdConfiguracionConcepto(Integer idConfiguracionConcepto) {
        this.idConfiguracionConcepto = idConfiguracionConcepto;
    }

    public ConceptoNominaFederalesEntity getConceptoNomina() {
        return conceptoNomina;
    }

    public void setConceptoNomina(ConceptoNominaFederalesEntity conceptoNomina) {
        this.conceptoNomina = conceptoNomina;
    }

    public TabuladorEntity getTabulador() {
        return tabulador;
    }

    public void setTabulador(TabuladorEntity tabulador) {
        this.tabulador = tabulador;
    }

    public BigDecimal getImporteConcepto() {
        return importeConcepto;
    }

    public void setImporteConcepto(BigDecimal importeConcepto) {
        this.importeConcepto = importeConcepto;
    }

    public PuestoGeneralEntity getPuestoGeneral() {
        return puestoGeneral;
    }

    public void setPuestoGeneral(PuestoGeneralEntity puestoGeneral) {
        this.puestoGeneral = puestoGeneral;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

}
