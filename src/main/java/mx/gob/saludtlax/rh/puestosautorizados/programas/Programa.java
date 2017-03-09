/**
 * 
 */
package mx.gob.saludtlax.rh.puestosautorizados.programas;

import java.util.List;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 25/08/2016 20:17:41
 */
public interface Programa {

	public void crearPrograma(ProgramaDTO programaDTO, Integer idUsuario);

	public ProgramaDTO obtenerProgramaPorId(Integer idPrograma);

	public List<InfoProgramaDTO> consultarProgramas();

	public List<InfoDetallePrograma> consultarDetallesProgramas(Integer idPrograma);

	public void crearDetallePrograma(DetalleProgramaDTO detalleProgramaDTO);
	
	public ConfiguracionDetalleProgramaDTO obtenerDetallePrograma(Integer idDetallePrograma);
	
	public ProgramaDTO obtenerDetalleProgramaPorId(Integer idPrograma);
	
	public void editarPrograma(ProgramaDTO edicionPrograma);
}
