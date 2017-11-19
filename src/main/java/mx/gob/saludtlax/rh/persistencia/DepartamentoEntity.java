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
 * @author Leila Schiaffini Ehuan
 *
 * @since 14/03/2016-15:27:09
 */
@Entity
@Table(name = "departamentos")
public class DepartamentoEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -894415813438389674L;
    @Id
    @Column(name = "id_departamento")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDepartamento;

    @Column(name = "departamento")
    private String departamentos;

    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    public String getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(String departamentos) {
        this.departamentos = departamentos;
    }

}
