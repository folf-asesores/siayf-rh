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
 * @since 10/08/2016 22:29:07
 */
@Entity
@Table(name = "operaciones_sistema")
public class OperacionSistemaEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3403662136134944588L;
    @Id
    @Column(name = "id_operacion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOperacion;

    @Column(name = "operacion")
    private String operacion;

    @Column(name = "aplica_movimiento")
    private Boolean aplicaMovimiento;

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public Integer getIdOperacion() {
        return idOperacion;
    }

    public Boolean getAplicaMovimiento() {
        return aplicaMovimiento;
    }

    public void setAplicaMovimiento(Boolean aplicaMovimiento) {
        this.aplicaMovimiento = aplicaMovimiento;
    }
}