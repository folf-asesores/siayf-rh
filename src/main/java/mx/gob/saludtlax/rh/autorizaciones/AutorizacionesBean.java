/**
 * 
 */
package mx.gob.saludtlax.rh.autorizaciones;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 10/08/2016 22:18:56
 */
@Stateless
public class AutorizacionesBean implements Autorizaciones {
	@Inject
	private AutorizacionesService autorizacionService;

	@Override
	public void iniciarProcesoAutorizacion(NuevaAutorizacionDTO dto) {
		autorizacionService.iniciarProcesoAprobacion(dto);

	}

	@Override
	public List<BuzonAutorizacionDTO> consultarAutorizacionesUsuarioEstatus(Integer idUsuario, boolean autorizado) {
		return autorizacionService.consultarAutorizacionesUsuarioEstatus(idUsuario, autorizado);
	}

	@Override
	public void autorizarProceso(AutorizacionDTO autorizacionDTO) {

		autorizacionService.aprobarOperacion(autorizacionDTO);
	}

	@Override
	public DetalleAutorizacionDTO obtenerDetalleAutorizacion(Integer idBuzon) {

		return autorizacionService.obtenerDetalleAutorizacion(idBuzon);
	}

	@Override
	public Integer obtenerIdEntidadContexto(Integer idBuzon) {
		return autorizacionService.obtenerIdEntidadContexto(idBuzon);
	}

	@Override
	public List<BuzonAutorizacionDTO> consultarAutorizacionesPorOperacionEstatus(Integer idUsuario, boolean autorizado,
			Integer idOperacion) {
		return autorizacionService.consultarAutorizacionesUsuarioOperacionEstatus(idUsuario, autorizado, idOperacion);
	}

}
