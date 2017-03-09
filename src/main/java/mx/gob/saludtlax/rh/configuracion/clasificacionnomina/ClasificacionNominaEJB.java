package mx.gob.saludtlax.rh.configuracion.clasificacionnomina;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ClasificacionNominaEJB {

	@Inject
	private ClasificacionNominaService clasificacionNominaService;

	public List<ClasificacionNominaDTO> obtenerClasificacionNominaLista() {
		List<ClasificacionNominaDTO> clasificacionNominaLista = clasificacionNominaService.listaClasificacionNomina();
		return clasificacionNominaLista;
	}

	public ClasificacionNominaDTO obtenerClasificacionNomina(ClasificacionNominaDTO clasificacionNomina) {
		ClasificacionNominaDTO dto = clasificacionNominaService.obtenerClasificacionNominaPorId(clasificacionNomina.getIdClasificacionNomina());
		return dto;
	}
	public ClasificacionNominaDTO nuevoClasificacionNomina() {
		return clasificacionNominaService.nuevoClasificacionNomina();
	}

	public ClasificacionNominaDTO crearClasificacionNomina(ClasificacionNominaDTO dto) {
		return clasificacionNominaService.crearClasificacionNomina(dto);
	}

	public ClasificacionNominaDTO actualizarClasificacionNomina(ClasificacionNominaDTO dto) {
		return clasificacionNominaService.actualizarClasificacionNomina(dto);
	}

	public void  eliminarClasificacionNomina(ClasificacionNominaDTO dto) {
		clasificacionNominaService.eliminarClasificacionNomina(dto);
	}
}