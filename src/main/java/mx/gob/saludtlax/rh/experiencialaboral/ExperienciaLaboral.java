/**
 * 
 */
package mx.gob.saludtlax.rh.experiencialaboral;

import java.util.List;

/**
 * @author Leila Schiaffini Ehuan
 * @since 09/06/2016 13:11:52
 * 
 */
public interface ExperienciaLaboral {

	public List<ExperienciaLaboralDTO> consultaExperienciaLaboralEmpleado(Integer idEmpleado);

	public void crearExperienciaLaboralEmpleado(ExperienciaLaboralDTO experienciaLaboral, Integer idEmpleado);

	public void actualizarExperienciaLaboralEmpleado(ExperienciaLaboralDTO experienciaLaboral, Integer idEmpleado);

}
