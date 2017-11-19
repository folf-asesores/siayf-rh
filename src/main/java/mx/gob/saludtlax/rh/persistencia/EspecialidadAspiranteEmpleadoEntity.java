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
import javax.validation.constraints.NotNull;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 * 
 * @version 1.0
 * @since 14:04:40 05/08/2016
 */
@Entity
@Table(name = "especialidades_aspirantes_empleados")
public class EspecialidadAspiranteEmpleadoEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6247996716398840195L;

    @Id
    @Column(name = "id_especialidad_aspirante_empleado")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEspecialidadAspiranteEmpleado;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_especialidad")
    private EspecialidadEntity especialidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_aspirante")
    private AspiranteEntity aspirante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empleado")
    private EmpleadoEntity empleado;

    public Integer getIdEspecialidadAspiranteEmpleado() {
        return idEspecialidadAspiranteEmpleado;
    }

    public void setIdEspecialidadAspiranteEmpleado(
            Integer idEspecialidadAspiranteEmpleado) {
        this.idEspecialidadAspiranteEmpleado = idEspecialidadAspiranteEmpleado;
    }

    public EspecialidadEntity getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(EspecialidadEntity especialidad) {
        this.especialidad = especialidad;
    }

    public AspiranteEntity getAspirante() {
        return aspirante;
    }

    public void setAspirante(AspiranteEntity aspirante) {
        this.aspirante = aspirante;
    }

    public EmpleadoEntity getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoEntity empleado) {
        this.empleado = empleado;
    }

}
