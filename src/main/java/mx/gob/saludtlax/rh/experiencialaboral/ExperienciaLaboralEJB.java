/**
 * 
 */
package mx.gob.saludtlax.rh.experiencialaboral;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import mx.gob.saludtlax.rh.empleados.administracion.ExperienciaLaboralValidator;

/**
 * @author Leila Schiaffini Ehuan
 * @since 09/06/2016 13:12:19
 * 
 */
@Stateless
public class ExperienciaLaboralEJB implements ExperienciaLaboral {

	@Inject
	private ExperienciaLaboralService experienciaLaboralService;

	@Override
	public List<ExperienciaLaboralDTO> consultaExperienciaLaboralEmpleado(
			Integer idEmpleado) {
		return experienciaLaboralService
				.consultaExperienciaLaboralEmpleado(idEmpleado);
	}

	@Interceptors({ ExperienciaLaboralValidator.class })
	@Override
	public void crearExperienciaLaboralEmpleado(
			ExperienciaLaboralDTO experienciaLaboral, Integer idEmpleado) {
		experienciaLaboralService.crearExperienciaLaboralEmpleado(
				experienciaLaboral, idEmpleado);

	}

	/* (non-Javadoc)
	 * @see mx.gob.saludtlax.rh.experiencialaboral.ExperienciaLaboral#actualizarExperienciaLaboralEmpleado(mx.gob.saludtlax.rh.experiencialaboral.ExperienciaLaboralDTO, java.lang.Integer)
	 */
	@Override
	public void actualizarExperienciaLaboralEmpleado(ExperienciaLaboralDTO experienciaLaboral, Integer idEmpleado) {
		experienciaLaboralService.actualizarExperienciaLaboralEmpleado(experienciaLaboral, idEmpleado);
		
	}

}
