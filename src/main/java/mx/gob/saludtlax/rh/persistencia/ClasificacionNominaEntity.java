/**
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
@Table(name = "clasificaciones_nominas")
public class ClasificacionNominaEntity implements Serializable {

	private static final long serialVersionUID = -3491218769714297031L;

	@Id
	@Column(name = "id_clasificacion_nomina")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idClasificacionNomina;


	@Column(name = "clasificacion_nomina")
	private String clasificacionNomina;
	@Column(name = "descripcion")
	private String descripcion;

//	<<<<<Getters & Setters>>>>>

	public String getClasificacionNomina() {
		return clasificacionNomina;
	}
	public Integer getIdClasificacionNomina() {
		return idClasificacionNomina;
	}
	public void setIdClasificacionNomina(Integer idClasificacionNomina) {
		this.idClasificacionNomina = idClasificacionNomina;
	}
	public void setClasificacionNomina(String clasificacionNomina) {
		this.clasificacionNomina = clasificacionNomina;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}