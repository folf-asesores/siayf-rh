package mx.gob.saludtlax.rh.configuracion.dependencia;

public class DependenciaDTO {
	private Integer idDependencia;
	private Integer idRamo;
	private Integer idSector;
	private Integer idClasificacionOrganismo;
	private Integer idEntePublico;
	private String idBase;
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
	}
