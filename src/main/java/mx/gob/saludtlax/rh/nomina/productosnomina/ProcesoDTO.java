/**
 * 
 */
package mx.gob.saludtlax.rh.nomina.productosnomina;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @Since 01/12/2016 17:49:01
 */
public class ProcesoDTO {
	private Integer numeroProcesado;
	private boolean enProceso;

	public Integer getNumeroProcesado() {
		return numeroProcesado;
	}
	public void setNumeroProcesado(Integer numeroProcesado) {
		this.numeroProcesado = numeroProcesado;
	}
	public boolean isEnProceso() {
		return enProceso;
	}
	public void setEnProceso(boolean enProceso) {
		this.enProceso = enProceso;
	}
}