package mx.gob.saludtlax.rh.presupuesto.presupuestoautorizado;
import java.util.List;
/**
 * 
 * @author José Pablo
 *
 */

public interface PresupuestoAutorizado {

	public void crearPresupuestoAutorizado(PresupuestoAutorizadoDTO dto);

	public void actualizarPresupuestoAutorizado(PresupuestoAutorizadoDTO dto);

	public void eliminarPresupuestoAutorizado(Integer idSalarioMinimo);

	public List<PresupuestoAutorizadoDTO> obtenerListaPresupuestoAutorizado();
}
