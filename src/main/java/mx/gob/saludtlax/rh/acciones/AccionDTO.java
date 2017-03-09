package mx.gob.saludtlax.rh.acciones;

import java.io.Serializable;


public class AccionDTO implements Serializable{

	
/**
	 * 
	 */
	private static final long serialVersionUID = 1268725915946611463L;

private Integer id_accion;

	private String clave;
	
	private String descripcion;
	
	private Integer id_area;
	
	private Integer id_modulo;
	
	private String nombreArea;

	public Integer getId_accion() {
		return id_accion;
	}

	public void setId_accion(Integer id_accion) {
		this.id_accion = id_accion;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	

	public Integer getId_area() {
		return id_area;
	}

	public void setId_area(Integer id_area) {
		this.id_area = id_area;
	}

	public Integer getId_modulo() {
		return id_modulo;
	}

	public void setId_modulo(Integer id_modulo) {
		this.id_modulo = id_modulo;
	}

	public String getNombreArea() {
		return nombreArea;
	}

	public void setNombreArea(String nombreArea) {
		this.nombreArea = nombreArea;
	}

	
	
	
}

