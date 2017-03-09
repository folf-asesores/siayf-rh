/**
 * 
 */
package mx.gob.saludtlax.rh.puestosautorizados.programas;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 25/08/2016 20:17:56
 */
@Stateless
public class ProgramaBean implements Programa {

	@Inject
	private ProgramaService programaService;

	@Override
	public void crearPrograma(ProgramaDTO programaDTO, Integer idUsuario) {

		programaService.crearPrograma(programaDTO, idUsuario);
	}

	@Override
	public ProgramaDTO obtenerProgramaPorId(Integer idPrograma) {
		return programaService.obtenerProgramaPorId(idPrograma);
	}

	@Override
	public List<InfoProgramaDTO> consultarProgramas() {

		return programaService.consultarProgramas();
	}

	@Override
	public List<InfoDetallePrograma> consultarDetallesProgramas(
			Integer idPrograma) {
		return programaService.consultarDetallesProgramas(idPrograma);
	}

	@Override
	public void crearDetallePrograma(DetalleProgramaDTO detalleProgramaDTO) {
		programaService.crearDetallePrograma(detalleProgramaDTO);

	}

	@Override
	public ConfiguracionDetalleProgramaDTO obtenerDetallePrograma(
			Integer idDetallePrograma) {
		return programaService.obtenerDetallePrograma(idDetallePrograma);
	}

	@Override
	public ProgramaDTO obtenerDetalleProgramaPorId(Integer idPrograma) {
		return programaService.obtenerDetalleProgramaPorId(idPrograma);
	}

	@Override
	public void editarPrograma(ProgramaDTO edicionPrograma) {
		programaService.editarPrograma(edicionPrograma);

	}

}
