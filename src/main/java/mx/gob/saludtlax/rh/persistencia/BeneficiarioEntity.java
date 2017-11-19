/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 *
 */
@Entity
@Table(name = "beneficiarios")
public class BeneficiarioEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2999660266430354462L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_beneficiario")
    private Integer idBeneficiario;

    @Column(name = "id_seguro_vida")
    private Integer idSeguroVida;

    @Column(name = "id_dependiente_economico")
    private Integer idDependienteEconomico;

    @Column(name = "porcentaje")
    private Integer porcetaje;

    public Integer getIdBeneficiario() {
        return idBeneficiario;
    }

    public void setIdBeneficiario(Integer idBeneficiario) {
        this.idBeneficiario = idBeneficiario;
    }

    public Integer getIdSeguroVida() {
        return idSeguroVida;
    }

    public void setIdSeguroVida(Integer idSeguroVida) {
        this.idSeguroVida = idSeguroVida;
    }

    public Integer getIdDependienteEconomico() {
        return idDependienteEconomico;
    }

    public void setIdDependienteEconomico(Integer idDependienteEconomico) {
        this.idDependienteEconomico = idDependienteEconomico;
    }

    public Integer getPorcetaje() {
        return porcetaje;
    }

    public void setPorcetaje(Integer porcetaje) {
        this.porcetaje = porcetaje;
    }

}
