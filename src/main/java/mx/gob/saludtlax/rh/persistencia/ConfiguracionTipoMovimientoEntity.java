
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;

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
@Table(name = "configuracion_tipo_movimiento")
public class ConfiguracionTipoMovimientoEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -5447848185944272600L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_configuracion_concepto_nomina")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_movimiento")
    private TiposMovimientosNominaEntity tipoMovimiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_concepto_nomina")
    private ConceptoNominaFederalesEntity conceptoNomina;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TiposMovimientosNominaEntity getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(TiposMovimientosNominaEntity tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public ConceptoNominaFederalesEntity getConceptoNomina() {
        return conceptoNomina;
    }

    public void setConceptoNomina(
            ConceptoNominaFederalesEntity conceptoNomina) {
        this.conceptoNomina = conceptoNomina;
    }

}
