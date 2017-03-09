package mx.gob.saludtlax.rh.configuracion.nombramiento;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class TipoNombramientoEJB {

	@Inject
	private TipoNombramientoService nombramientoService;

	public List<TipoNombramientoDTO> obtenerNombramientoLista() {
		List<TipoNombramientoDTO> nombramientoLista = nombramientoService.listaNombramiento();
		return nombramientoLista;
	}

	public TipoNombramientoDTO obtenerNombramiento(Integer idTipoNombramiento) {
		TipoNombramientoDTO dto = nombramientoService.obtenerNombramientoPorId(idTipoNombramiento);
		return dto;
	}

}