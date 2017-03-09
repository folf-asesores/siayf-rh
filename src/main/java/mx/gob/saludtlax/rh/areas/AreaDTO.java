package mx.gob.saludtlax.rh.areas;

import java.util.List;

public class AreaDTO {
	
	private Integer idArea;
	private String nombreArea;
	private Integer areaPadre;
	private String descripcion;
	private String titular;

	private List<AreaDTO> cuentas;
	
	
	public Integer getIdArea() {
		return idArea;
	}
	public void setIdArea(Integer idArea) {
		this.idArea = idArea;
	}
	
	public String getNombreArea() {
		return nombreArea;
	}
	public void setNombreArea(String nombreArea) {
		this.nombreArea = nombreArea;
	}
	public Integer getAreaPadre() {
		return areaPadre;
	}
	public void setAreaPadre(Integer areaPadre) {
		this.areaPadre = areaPadre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getTitular() {
		return titular;
	}
	public void setTitular(String titular) {
		this.titular = titular;
	}
	public List<AreaDTO> getCuentas() {
		return cuentas;
	}
	public void setCuentas(List<AreaDTO> cuentas) {
		this.cuentas = cuentas;
	}
	
}
