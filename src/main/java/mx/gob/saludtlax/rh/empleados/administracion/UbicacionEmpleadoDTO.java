/**
 * 
 */
package mx.gob.saludtlax.rh.empleados.administracion;

/**
 * @author Leila Schiaffini Ehuan
 * @since 13/09/2016 14:15:35
 * 
 */
public class UbicacionEmpleadoDTO {
	private Integer idAdscripcion;
	private Integer idSubadscripcion;
	private Integer idServicio;
	private Integer idFuncion;
	private Integer idClue;
	private Integer idCentroResponsabilidad;
	private Integer idInventarioVacante;
	private Integer idUsuario;

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getIdCentroResponsabilidad() {
		return idCentroResponsabilidad;
	}

	public void setIdCentroResponsabilidad(Integer idCentroResponsabilidad) {
		this.idCentroResponsabilidad = idCentroResponsabilidad;
	}

	public Integer getIdAdscripcion() {
		return idAdscripcion;
	}

	public void setIdAdscripcion(Integer idAdscripcion) {
		this.idAdscripcion = idAdscripcion;
	}

	public Integer getIdSubadscripcion() {
		return idSubadscripcion;
	}

	public void setIdSubadscripcion(Integer idSubadscripcion) {
		this.idSubadscripcion = idSubadscripcion;
	}

	public Integer getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(Integer idServicio) {
		this.idServicio = idServicio;
	}

	public Integer getIdClue() {
		return idClue;
	}

	public void setIdClue(Integer idClue) {
		this.idClue = idClue;
	}

	public Integer getIdFuncion() {
		return idFuncion;
	}

	public void setIdFuncion(Integer idFuncion) {
		this.idFuncion = idFuncion;
	}

	public Integer getIdInventarioVacante() {
		return idInventarioVacante;
	}

	public void setIdInventarioVacante(Integer idInventarioVacante) {
		this.idInventarioVacante = idInventarioVacante;
	}

}
