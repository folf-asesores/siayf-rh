/**
 * 
 */
package mx.gob.saludtlax.rh.configuracion.calendarioglobal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.persistencia.CalendarioGlobalEntity;
import mx.gob.saludtlax.rh.persistencia.CalendarioGlobalRepository;

/**
 * @author Eduardo Mex
 *
 */
public class CalendarioGlobalService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7029976796940250352L;

	@Inject
	private CalendarioGlobalRepository repository;

	public void creaCalendarioGlobal(CalendarioGlobalDTO dto) {

		String contexto = "creaCalendarioGlobal: ";

		if (dto == null) {
			throw new ValidacionException(contexto + "Ingrese los valores.", ValidacionCodigoError.VALOR_REQUERIDO);
		}

		CalendarioGlobalEntity entity = new CalendarioGlobalEntity();

		entity.setPartida8000(dto.getPartida8000());
		entity.setPartida1000(dto.getPartida1000());
		entity.setConcepto(dto.getConcepto());
		entity.setImporteAnual(dto.getImporteAnual());

		repository.crear(entity);
	}

	public void actualizarCalendarioGlobal(CalendarioGlobalDTO dto) {
		String contexto = "actualizarCalendarioGlobal: ";

		if (dto == null) {
			throw new ValidacionException(contexto + "Ingrese los valores.", ValidacionCodigoError.VALOR_REQUERIDO);
		}

		CalendarioGlobalEntity entity = repository.obtenerPorId(dto.getIdCalendarioGlobal());

		entity.setPartida8000(dto.getPartida8000());
		entity.setPartida1000(dto.getPartida1000());
		entity.setConcepto(dto.getConcepto());
		entity.setImporteAnual(dto.getImporteAnual());

		repository.crear(entity);
	}

	public void eliminarCalendarioGlobal(Integer idCalendarioGlobal) {
		String contexto = "eliminarCalendarioGlobal: ";

		if (idCalendarioGlobal == null) {
			throw new ValidacionException(contexto + "Seleccione el registro.", ValidacionCodigoError.VALOR_REQUERIDO);
		}

		repository.eliminarPorId(idCalendarioGlobal);
	}

	public List<CalendarioGlobalDTO> obtenerListaCalendarioGlobal() {

		List<CalendarioGlobalDTO> lista = new ArrayList<CalendarioGlobalDTO>();

		List<CalendarioGlobalEntity> listaEntities = repository.obtenerListaCalendarioGlobal();

		if (!listaEntities.isEmpty()) {
			for (CalendarioGlobalEntity entity : listaEntities) {
				CalendarioGlobalDTO dto = new CalendarioGlobalDTO(entity.getIdCalendarioGlobal(),
						entity.getPartida8000(), entity.getPartida1000(), entity.getConcepto(),
						entity.getImporteAnual());

				lista.add(dto);

			}
		}

		return lista;
	}

}
