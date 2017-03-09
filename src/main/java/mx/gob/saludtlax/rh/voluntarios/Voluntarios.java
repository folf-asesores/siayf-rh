package mx.gob.saludtlax.rh.voluntarios;

import java.util.List;

public interface Voluntarios {

	public void registrarVoluntario(AltaVoluntarioDTO alta);

	public List<InfoVoluntarioDTO> consultarVoluntarios(ConsultaVoluntarioDTO consulta);

}
