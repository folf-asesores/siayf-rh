
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "estatus_nominas_empleado")
public class EstatusNominasEmpleadoEntity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -8848678771725434434L;

    @Id
    @Column(name = "id_estatus_nomina_empleado")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEstatusNominaEmpleado;

    @Column(name = "estatus_nomina_empleado")
    private String estatusNominaEmpleado;

    public Integer getIdEstatusNominaEmpleado() {
        return idEstatusNominaEmpleado;
    }

    public void setIdEstatusNominaEmpleado(Integer idEstatusNominaEmpleado) {
        this.idEstatusNominaEmpleado = idEstatusNominaEmpleado;
    }

    public String getEstatusNominaEmpleado() {
        return estatusNominaEmpleado;
    }

    public void setEstatusNominaEmpleado(String estatusNominaEmpleado) {
        this.estatusNominaEmpleado = estatusNominaEmpleado;
    }

}
