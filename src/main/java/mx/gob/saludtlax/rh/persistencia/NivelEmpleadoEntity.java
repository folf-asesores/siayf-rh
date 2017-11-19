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

@Entity
@Table(name = "niveles_empleados")
public class NivelEmpleadoEntity implements Serializable {

    private static final long serialVersionUID = 8931640499924636932L;

    @Id
    @Column(name = "id_nivel_empleado")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idNivelEmpleado;

    @Column(name = "nivel_empleado")
    private String nivelEmpleado;

    public Integer getIdNivelEmpleado() {
        return idNivelEmpleado;
    }

    public void setIdNivelEmpleado(Integer idNivelEmpleado) {
        this.idNivelEmpleado = idNivelEmpleado;
    }

    public String getNivelEmpleado() {
        return nivelEmpleado;
    }

    public void setNivelEmpleado(String nivelEmpleado) {
        this.nivelEmpleado = nivelEmpleado;
    }
}