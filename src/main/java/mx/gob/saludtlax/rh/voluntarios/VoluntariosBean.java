package mx.gob.saludtlax.rh.voluntarios;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

@Stateless
public class VoluntariosBean implements Voluntarios {
	@Inject
	private VoluntariosService voluntariosService;

	@Interceptors({ AltaVoluntarioValidator.class })
	@Override
	public void registrarVoluntario(AltaVoluntarioDTO alta) {
		voluntariosService.registrarVoluntario(alta);

	}

	@Override
	public List<InfoVoluntarioDTO> consultarVoluntarios(ConsultaVoluntarioDTO consulta) {
		return voluntariosService.consultarVoluntarios(consulta);
	}

}
