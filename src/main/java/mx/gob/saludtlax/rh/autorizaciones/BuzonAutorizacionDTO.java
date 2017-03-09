/**
 * 
 */
package mx.gob.saludtlax.rh.autorizaciones;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 11/08/2016 00:57:03
 */
public class BuzonAutorizacionDTO {

	private Integer idBuzonAutorizacion;
	private String accion;
	private Integer idAccion;
	private String descripcion;
	private String finalizado;
	

	public Integer getIdAccion() {
		return idAccion;
	}

	public void setIdAccion(Integer idAccion) {
		this.idAccion = idAccion;
	}

	public Integer getIdBuzonAutorizacion() {
		return idBuzonAutorizacion;
	}

	public void setIdBuzonAutorizacion(Integer idBuzonAutorizacion) {
		this.idBuzonAutorizacion = idBuzonAutorizacion;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFinalizado() {
		return finalizado;
	}

	public void setFinalizado(String finalizado) {
		this.finalizado = finalizado;
	}

}
