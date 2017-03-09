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
@Table(name = "dependencias")
public class DependenciaEntity implements Serializable {

	private static final long serialVersionUID = -3491218769714297031L;

	@Id
	@Column(name = "id_dependencia")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idDependencia;

	@Column(name = "id_ramo")
	private Integer idRamo;
	@Column(name = "id_sector")
	private Integer idSector;
	@Column(name = "id_clasificacion_organismo")
	private Integer idClasificacionOrganismo;
	@Column(name = "id_ente_publico")
	private Integer idEntePublico;
	@Column(name = "id_base_36")
	private String idBase;
	@Column(name = "descripcion")
	private String descripcion;
	
//	<Getters & Setters>
	
	public Integer getIdDependencia() {
		return idDependencia;
	}
	public void setIdDependencia(Integer idDependencia) {
		this.idDependencia = idDependencia;
	}
	public Integer getIdRamo() {
		return idRamo;
	}
	public void setIdRamo(Integer idRamo) {
		this.idRamo = idRamo;
	}
	public Integer getIdSector() {
		return idSector;
	}
	public void setIdSector(Integer idSector) {
		this.idSector = idSector;
	}
	public Integer getIdClasificacionOrganismo() {
		return idClasificacionOrganismo;
	}
	public void setIdClasificacionOrganismo(Integer idClasificacionOrganismo) {
		this.idClasificacionOrganismo = idClasificacionOrganismo;
	}
	public Integer getIdEntePublico() {
		return idEntePublico;
	}
	public void setIdEntePublico(Integer idEntePublico) {
		this.idEntePublico = idEntePublico;
	}
	public String getIdBase() {
		return idBase;
	}
	public void setIdBase(String idBase) {
		this.idBase = idBase;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}