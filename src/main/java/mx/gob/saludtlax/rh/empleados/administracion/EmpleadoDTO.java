package mx.gob.saludtlax.rh.empleados.administracion;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 13/04/2016-15:36:53
 */
public class EmpleadoDTO {
	private Integer idEmpleado;
	private Integer idAspirante;
	private int faseRegistro;
	private DatosGeneralesDTO datosGeneralesDTO;
	private DomicilioDTO domicilio;
	private DatosEmpleadoDTO datosEmpleado = new DatosEmpleadoDTO();

	public Integer getIdAspirante() {
		return idAspirante;
	}

	public void setIdAspirante(Integer idAspirante) {
		this.idAspirante = idAspirante;
	}

	public DatosEmpleadoDTO getDatosEmpleado() {
		return datosEmpleado;
	}

	public void setDatosEmpleado(DatosEmpleadoDTO datosEmpleado) {
		this.datosEmpleado = datosEmpleado;
	}

	public DomicilioDTO getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(DomicilioDTO domicilio) {
		this.domicilio = domicilio;
	}

	public int getFaseRegistro() {
		return faseRegistro;
	}

	public void setFaseRegistro(int faseRegistro) {
		this.faseRegistro = faseRegistro;
	}

	public DatosGeneralesDTO getDatosGeneralesDTO() {
		return datosGeneralesDTO;
	}

	public void setDatosGeneralesDTO(DatosGeneralesDTO datosGeneralesDTO) {
		this.datosGeneralesDTO = datosGeneralesDTO;
	}

	public Integer getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

}
