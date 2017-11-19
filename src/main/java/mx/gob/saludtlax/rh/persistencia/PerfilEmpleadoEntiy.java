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
 * @since 04/03/2016-15:36:38
 */
@Entity
@Table(name = "perfiles_empleados")
public class PerfilEmpleadoEntiy implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -3227500394143281817L;
    @Id
    @Column(name = "IdPerfilEmpleado")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPerfilEmpleado;

    @Column(name = "Grupo")
    private String grupo;

    @Column(name = "Area")
    private String area;

    @Column(name = "Rama")
    private String rama;

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getRama() {
        return rama;
    }

    public void setRama(String rama) {
        this.rama = rama;
    }

    public Integer getIdPerfilEmpleado() {
        return idPerfilEmpleado;
    }

}
