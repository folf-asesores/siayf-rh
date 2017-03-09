/**
 * 
 */
package mx.gob.saludtlax.rh.puestosautorizados;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @Since 26/01/2017 21:31:45
 */
public class DetallePuestoDTO {
	private Integer idPuesto;
	private String codigoAutorizado;
	private String descripcionCodigoAutorizado;
	private String numeroPlaza;
	private String rfc;
	private String empleado;
	private String tipoContratacion;
	private String subClasificacion;
	private String estatus;

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public Integer getIdPuesto() {
		return idPuesto;
	}

	public void setIdPuesto(Integer idPuesto) {
		this.idPuesto = idPuesto;
	}

	public String getCodigoAutorizado() {
		return codigoAutorizado;
	}

	public void setCodigoAutorizado(String codigoAutorizado) {
		this.codigoAutorizado = codigoAutorizado;
	}

	public String getDescripcionCodigoAutorizado() {
		return descripcionCodigoAutorizado;
	}

	public void setDescripcionCodigoAutorizado(String descripcionCodigoAutorizado) {
		this.descripcionCodigoAutorizado = descripcionCodigoAutorizado;
	}

	public String getNumeroPlaza() {
		return numeroPlaza;
	}

	public void setNumeroPlaza(String numeroPlaza) {
		this.numeroPlaza = numeroPlaza;
	}

	public String getEmpleado() {
		return empleado;
	}

	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}

	public String getTipoContratacion() {
		return tipoContratacion;
	}

	public void setTipoContratacion(String tipoContratacion) {
		this.tipoContratacion = tipoContratacion;
	}

	public String getSubClasificacion() {
		return subClasificacion;
	}

	public void setSubClasificacion(String subClasificacion) {
		this.subClasificacion = subClasificacion;
	}

}
