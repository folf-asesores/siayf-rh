/*
 *
 */

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
@Table(name = "conceptos_nominas_nombramientos")
public class ConceptoNominaNombramientoEntity implements Serializable {
    private static final long serialVersionUID = -8971584377593138520L;

    @Id
    @Column(name = "id_concepto_nomina_nombramiento")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idConceptoNominaNombramiento;
    @Column(name = "id_concepto_nomina")
    private Integer idConceptoNomina;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_nombramiento")
    private TiposNombramientosEntity tipoNombramiento;
    @Column(name = "aplica")
    private Boolean aplica;

    public Integer getIdConceptoNominaNombramiento() {
        return idConceptoNominaNombramiento;
    }

    public void setIdConceptoNominaNombramiento(
            Integer idConceptoNominaNombramiento) {
        this.idConceptoNominaNombramiento = idConceptoNominaNombramiento;
    }

    public Integer getIdConceptoNomina() {
        return idConceptoNomina;
    }

    public void setIdConceptoNomina(Integer idConceptoNomina) {
        this.idConceptoNomina = idConceptoNomina;
    }

    public TiposNombramientosEntity getTipoNombramiento() {
        return tipoNombramiento;
    }

    public void setTipoNombramiento(TiposNombramientosEntity tipoNombramiento) {
        this.tipoNombramiento = tipoNombramiento;
    }

    public Boolean getAplica() {
        return aplica;
    }

    public void setAplica(Boolean aplica) {
        this.aplica = aplica;
    }
}