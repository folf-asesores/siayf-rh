/**
 * Copyright ® 2016
 */
package mx.gob.saludtlax.rh.configuracion.terceroinstitucional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.jboss.logging.Logger;

import mx.gob.saludtlax.rh.excepciones.BusinessException;
import mx.gob.saludtlax.rh.persistencia.TerceroInstitucionalEntity;
import mx.gob.saludtlax.rh.persistencia.TerceroInstitucionalRepository;

/**
 * @author Eduardo Mex
 * @email lic.eduardo_mex@hotmail.com
 * @version 1.0
 * @since 24/05/2016 10:58:42
 */
public class TerceroInstitucionalService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3937185722982814370L;
	private static final Logger LOGGER = Logger.getLogger(TerceroInstitucionalService.class);

	@Inject
	private TerceroInstitucionalRepository terceroInstitucionalRepository;

	/**
	 * 
	 * @param terceroInstitucional
	 */
	protected void crearTerceroInstitucional(TerceroInstitucionalDTO terceroInstitucional) {

		TerceroInstitucionalEntity registroTerceroInstitucional = new TerceroInstitucionalEntity();

		registroTerceroInstitucional.setNumero(terceroInstitucional.getNumero());
		registroTerceroInstitucional.setNombreEmpresa(terceroInstitucional.getNombreEmpresa());
		registroTerceroInstitucional.setConceptoDeduccion(terceroInstitucional.getConceptoDeduccion());
		registroTerceroInstitucional
				.setContrapartidaIdentificadora(terceroInstitucional.getContrapartidaIdentificadora());
		registroTerceroInstitucional.setGiro(terceroInstitucional.getGiro());

		terceroInstitucionalRepository.crear(registroTerceroInstitucional);

	}

	/**
	 * 
	 * @param terceroInstitucional
	 */
	protected void actualizarTerceroInstitucional(TerceroInstitucionalDTO terceroInstitucional) {

		String contexto = "Tercero Institucional: ";

		TerceroInstitucionalEntity actualizacionTerceroInstitucional = terceroInstitucionalRepository
				.obtenerPorId(terceroInstitucional.getIdTerceroInstitucional());

		if (actualizacionTerceroInstitucional == null) {
			LOGGER.error(contexto + "No se encontro el registro, seleccione otro registro");
			throw new BusinessException(contexto + "No se encontro el registro, seleccione otro registro");
		}

		actualizacionTerceroInstitucional.setNumero(terceroInstitucional.getNumero());
		actualizacionTerceroInstitucional.setNombreEmpresa(terceroInstitucional.getNombreEmpresa());
		actualizacionTerceroInstitucional.setConceptoDeduccion(terceroInstitucional.getConceptoDeduccion());
		actualizacionTerceroInstitucional
				.setContrapartidaIdentificadora(terceroInstitucional.getContrapartidaIdentificadora());
		actualizacionTerceroInstitucional.setGiro(terceroInstitucional.getGiro());

		terceroInstitucionalRepository.actualizar(actualizacionTerceroInstitucional);

	}

	/**
	 * 
	 * @param idTerceroInstitucional
	 */
	protected void eliminarTerceroInstitucional(Integer idTerceroInstitucional) {

		String contexto = "Tercero Institucional: ";

		TerceroInstitucionalEntity eliminarTerceroInstitucional = terceroInstitucionalRepository
				.obtenerPorId(idTerceroInstitucional);

		if (eliminarTerceroInstitucional == null) {
			LOGGER.error(contexto + "No se encontro el registro, seleccione otro registro");
			throw new BusinessException(contexto + "No se encontro el registro, seleccione otro registro");
		}

		terceroInstitucionalRepository.eliminar(eliminarTerceroInstitucional);

	}

	/**
	 * 
	 * @return
	 */
	protected List<TerceroInstitucionalDTO> obtenerListaTerceroInstitucional() {

		List<TerceroInstitucionalDTO> listaTerceroInstitucionalDTO = new ArrayList<TerceroInstitucionalDTO>();
		List<TerceroInstitucionalEntity> listaTerceroInstitucional = terceroInstitucionalRepository
				.obtenerListaTerceroInstitucional();

		if (!listaTerceroInstitucional.isEmpty()) {
			for (TerceroInstitucionalEntity terceroInstitucionalEntity : listaTerceroInstitucional) {
				TerceroInstitucionalDTO terceroInstitucionalDTO = new TerceroInstitucionalDTO();

				terceroInstitucionalDTO
						.setIdTerceroInstitucional(terceroInstitucionalEntity.getIdTerceroInstitucional());
				terceroInstitucionalDTO.setNumero(terceroInstitucionalEntity.getNumero());
				terceroInstitucionalDTO.setNombreEmpresa(terceroInstitucionalEntity.getNombreEmpresa());
				terceroInstitucionalDTO.setConceptoDeduccion(terceroInstitucionalEntity.getConceptoDeduccion());
				terceroInstitucionalDTO
						.setContrapartidaIdentificadora(terceroInstitucionalEntity.getContrapartidaIdentificadora());
				terceroInstitucionalDTO.setGiro(terceroInstitucionalEntity.getGiro());

				listaTerceroInstitucionalDTO.add(terceroInstitucionalDTO);
			}
		} else {
			listaTerceroInstitucionalDTO = new ArrayList<TerceroInstitucionalDTO>();
		}

		return listaTerceroInstitucionalDTO;
	}

	
	public TerceroInstitucionalDTO obtenerPorClave(String clave, String partida){
		return terceroInstitucionalRepository.obtenerTerceroInstitucional(clave, partida).get(0);
	}
}
