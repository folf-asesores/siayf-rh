/**
 * 
 */
package mx.gob.saludtlax.rh.empleados.datolaboral;

/**
 * @author Leila Schiaffini Ehuan
 * @since 26/09/2016 14:29:20
 * 
 */
public class SolicitudNuevoPuestoDTO {
	private DatoLaboralDTO datosLaborales = new DatoLaboralDTO();
	private Integer idDatosLaborales;
	private Integer idUsuario;
	private Integer idPuestoDisponible;// para interinato.

	public Integer getIdPuestoDisponible() {
		return idPuestoDisponible;
	}

	public void setIdPuestoDisponible(Integer idPuestoDisponible) {
		this.idPuestoDisponible = idPuestoDisponible;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public DatoLaboralDTO getDatosLaborales() {
		return datosLaborales;
	}

	public void setDatosLaborales(DatoLaboralDTO datosLaborales) {
		this.datosLaborales = datosLaborales;
	}

	public Integer getIdDatosLaborales() {
		return idDatosLaborales;
	}

	public void setIdDatosLaborales(Integer idDatosLaborales) {
		this.idDatosLaborales = idDatosLaborales;
	}

}
