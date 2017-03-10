package mx.gob.saludtlax.rh.presupuesto.presupuestoautorizado;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
/**
 * 
 * @author José Pablo
 *
 */

@Stateless
public class PresupuestoAutorizadoEJB implements PresupuestoAutorizado, Serializable {
	private static final long serialVersionUID = 7829750091048865871L;
	
	@Inject
	private PresupuestoAutorizadoService presupuestoAutorizadoService;
	
	@Override
	public void crearPresupuestoAutorizado(PresupuestoAutorizadoDTO dto) {
		presupuestoAutorizadoService.crearPresupuestoAutorizado(dto);
	}

	@Override
	public void actualizarPresupuestoAutorizado(PresupuestoAutorizadoDTO dto) {
		presupuestoAutorizadoService.actualizarPresupuestoAutorizado(dto);
	}

	@Override
	public void eliminarPresupuestoAutorizado(Integer idPresupuestoAutorizado) {
		presupuestoAutorizadoService.eliminarPresupuestoAutorizado(idPresupuestoAutorizado);
	}
	
	@Override
	public List<PresupuestoAutorizadoDTO> obtenerListaPresupuestoAutorizado(){
		return presupuestoAutorizadoService.obtenerListaPresupuestoAutorizado();
	}

}
