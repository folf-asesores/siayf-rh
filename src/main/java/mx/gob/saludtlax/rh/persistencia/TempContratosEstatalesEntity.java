/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 15/08/2016 21:39:30
 */
@Entity
@Table(name = "temporal_padron_contratos_estatales")
public class TempContratosEstatalesEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -5819673926423496972L;
    @Id
    @Column(name = "id_temporal")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "rfc")
    private String rfc;

    @Column(name = "sueldo")
    private BigDecimal sueldo;

    @Column(name = "id_temporal_configuracion_empleado")
    private Integer idTemporalConfiguracionEmpleado;

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public BigDecimal getSueldo() {
        return sueldo;
    }

    public void setSueldo(BigDecimal sueldo) {
        this.sueldo = sueldo;
    }

    public Integer getIdTemporalConfiguracionEmpleado() {
        return idTemporalConfiguracionEmpleado;
    }

    public void setIdTemporalConfiguracionEmpleado(
            Integer idTemporalConfiguracionEmpleado) {
        this.idTemporalConfiguracionEmpleado = idTemporalConfiguracionEmpleado;
    }

    public Integer getId() {
        return id;
    }

}
