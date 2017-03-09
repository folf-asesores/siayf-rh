/**
 * 
 */
package mx.gob.saludtlax.rh.nomina.productosnomina;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @Since 29/11/2016 12:54:29
 */
public class NominaAcumuladoDTO {
	private Integer idNominaEmpleado;
	private Integer tipoNomina;

	public NominaAcumuladoDTO(Integer idNominaEmpleado, Integer tipoNomina) {

		this.idNominaEmpleado = idNominaEmpleado;
		this.tipoNomina = tipoNomina;
	}

	public Integer getIdNominaEmpleado() {
		return idNominaEmpleado;
	}

	public void setIdNominaEmpleado(Integer idNominaEmpleado) {
		this.idNominaEmpleado = idNominaEmpleado;
	}

	public Integer getTipoNomina() {
		return tipoNomina;
	}

	public void setTipoNomina(Integer tipoNomina) {
		this.tipoNomina = tipoNomina;
	}

}
