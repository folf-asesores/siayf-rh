/**
 * 
 */
package mx.gob.saludtlax.rh.puestosautorizados;

/**
 * @author Leila Schiaffini Ehuan
 * @since 15/08/2016 10:20:26
 * 
 */
public class FiltroVacanteDTO {
	private int tipoBusqueda;
	private int identificador;
	private String criterio;
	
	

	public String getCriterio() {
		return criterio;
	}

	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}

	public int getTipoBusqueda() {
		return tipoBusqueda;
	}

	public void setTipoBusqueda(int tipoBusqueda) {
		this.tipoBusqueda = tipoBusqueda;
	}

	public int getIdentificador() {
		return identificador;
	}

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

}
