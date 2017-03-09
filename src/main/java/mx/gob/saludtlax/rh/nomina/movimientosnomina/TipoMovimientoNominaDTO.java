package mx.gob.saludtlax.rh.nomina.movimientosnomina;

public class TipoMovimientoNominaDTO {

	private Integer idTimpoMovimiento;
	
	private String clave;
	
	private String descripcion;
	
	private Integer formaRegistro;
	
	private Boolean esMovimiento;
	
	private Integer idPadre;

	public Integer getIdTimpoMovimiento() {
		return idTimpoMovimiento;
	}

	public void setIdTimpoMovimiento(Integer idTimpoMovimiento) {
		this.idTimpoMovimiento = idTimpoMovimiento;
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

	public Integer getFormaRegistro() {
		return formaRegistro;
	}

	public void setFormaRegistro(Integer formaRegistro) {
		this.formaRegistro = formaRegistro;
	}

	public Boolean getEsMovimiento() {
		return esMovimiento;
	}

	public void setEsMovimiento(Boolean esMovimiento) {
		this.esMovimiento = esMovimiento;
	}

	public Integer getIdPadre() {
		return idPadre;
	}

	public void setIdPadre(Integer idPadre) {
		this.idPadre = idPadre;
	}
	
	
	
}
