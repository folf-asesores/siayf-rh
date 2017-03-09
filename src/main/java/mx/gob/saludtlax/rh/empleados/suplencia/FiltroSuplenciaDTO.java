/**
 * 
 */
package mx.gob.saludtlax.rh.empleados.suplencia;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @Since 07/12/2016 00:39:56
 */
public class FiltroSuplenciaDTO {
	private int tipoConsulta;
	private String criterio;

	public int getTipoConsulta() {
		return tipoConsulta;
	}

	public void setTipoConsulta(int tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}

	public String getCriterio() {
		return criterio;
	}

	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}

}
