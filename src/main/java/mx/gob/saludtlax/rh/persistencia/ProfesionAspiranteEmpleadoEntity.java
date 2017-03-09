/**
 * Copyright © 2016
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
 * @author Eduardo Mex
 * @email lic.eduardo_mex@hotmail.com
 * @version 1.0
 * @since 13:59:08 05/08/2016
 */
@Entity
@Table(name = "profesiones_aspirantes_empleados")
public class ProfesionAspiranteEmpleadoEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8740072811142425916L;

	@Id
	@Column(name = "id_profesion_aspirante_empleado")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProfesionAspiranteEmpleado;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_profesion")
	private ProfesionEntity profesion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_aspirante")
	private AspiranteEntity aspirante;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_empleado")
	private EmpleadoEntity empleado;

	public Integer getIdProfesionAspiranteEmpleado() {
		return idProfesionAspiranteEmpleado;
	}

	public void setIdProfesionAspiranteEmpleado(Integer idProfesionAspiranteEmpleado) {
		this.idProfesionAspiranteEmpleado = idProfesionAspiranteEmpleado;
	}

	public ProfesionEntity getProfesion() {
		return profesion;
	}

	public void setProfesion(ProfesionEntity profesion) {
		this.profesion = profesion;
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
