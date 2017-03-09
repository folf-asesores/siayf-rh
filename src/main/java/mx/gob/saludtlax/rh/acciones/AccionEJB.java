package mx.gob.saludtlax.rh.acciones;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class AccionEJB implements Accion{

	 @Inject
	 private AccionService accionService;
	
	@Override
	public void crearAccion(AccionDTO accionDTO) {
accionService.crearAccion(accionDTO);
	}

	@Override
	public void editarAccion(AccionDTO accionDTO) {
		accionService.editarAccion(accionDTO);
		
	}

	@Override
	public Boolean  eliminarAccion(Integer idAccion) {
		return accionService.eliminarAccion(idAccion);
		
	}

	@Override
	public List<AccionDTO> obtenerListaAcciones() {
	return accionService.obtenerAcciones();
 }

	@Override
	public List<AccionDTO> obtenerListaAccionesPorArea(Integer idArea) {
		return accionService.obtenerAccionesPorArea(idArea);
	}
	
}
