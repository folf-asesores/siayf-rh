/**
 * Copyright © 2016
 */
package mx.gob.saludtlax.rh.configuracion.puestogeneral;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.persistencia.PuestoGeneralEntity;
import mx.gob.saludtlax.rh.persistencia.PuestoGeneralRepository;
import mx.gob.saludtlax.rh.persistencia.RamaEntity;
import mx.gob.saludtlax.rh.persistencia.RamaRepository;
import mx.gob.saludtlax.rh.persistencia.TipoPuestoEntity;
import mx.gob.saludtlax.rh.persistencia.TipoPuestoRepository;

/**
 * @author Eduardo Mex
 * @email Lic.Eduardo_Mex@hotmail.com
 * @version 1.0
 * @since 21/07/2016 13:41:12
 */
public class PuestoGeneralService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8585448503480291928L;

	@Inject
	private PuestoGeneralRepository puestoGeneralRepository;
	@Inject
	private TipoPuestoRepository tipoPuestoRepository;
	@Inject
	private RamaRepository ramaRepository;

	protected List<PuestoGeneralDTO> consultarListaPuestoGeneral() {
		List<PuestoGeneralDTO> listaPuestoGeneralDTOs = new ArrayList<PuestoGeneralDTO>();
		List<PuestoGeneralEntity> listaPuestoGeneralEntities = puestoGeneralRepository.obtenerListaPuestoGeneral();

		if (listaPuestoGeneralEntities != null && !listaPuestoGeneralEntities.isEmpty()) {

			for (PuestoGeneralEntity puestoGeneralEntity : listaPuestoGeneralEntities) {
				PuestoGeneralDTO dto = new PuestoGeneralDTO();

				dto.setIdPuestoGeneral(puestoGeneralEntity.getIdPuestoGeneral());
				dto.setCodigo(puestoGeneralEntity.getCodigo());
				dto.setPuesto(puestoGeneralEntity.getPuesto());

				if (puestoGeneralEntity.getIdTipoPuesto() != null
						|| puestoGeneralEntity.getIdTipoPuesto().getIdTipoPuesto() != 0) {
					dto.setIdTipoPuesto(puestoGeneralEntity.getIdTipoPuesto().getIdTipoPuesto());
					dto.setDescripcionTipoPuesto(puestoGeneralEntity.getIdTipoPuesto().getDescripcion());
				}

				if (puestoGeneralEntity.getIdRama() != null) {
					dto.setIdRama(puestoGeneralEntity.getIdRama().getIdRamaPuesto());
					dto.setDescripcionRama(puestoGeneralEntity.getIdRama().getNombreRamaPuesto());
				}

				listaPuestoGeneralDTOs.add(dto);
			}

		}

		return listaPuestoGeneralDTOs;
	}

	protected void crearPuestoGeneral(PuestoGeneralDTO puestoGeneralDTO) {

		PuestoGeneralEntity puestoGeneralEntity = new PuestoGeneralEntity();

		puestoGeneralEntity.setCodigo(puestoGeneralDTO.getCodigo());
		puestoGeneralEntity.setPuesto(puestoGeneralDTO.getPuesto());

		if (puestoGeneralDTO.getIdTipoPuesto() != 0) {
			TipoPuestoEntity tipoTabuladorEntity = tipoPuestoRepository
					.obtenerPorId(puestoGeneralDTO.getIdTipoPuesto());

			puestoGeneralEntity.setIdTipoPuesto(tipoTabuladorEntity);
		}

		if (puestoGeneralDTO.getIdRama() != 0) {
			RamaEntity ramaEntity = ramaRepository.obtenerPorId(puestoGeneralDTO.getIdRama());

			puestoGeneralEntity.setIdRama(ramaEntity);
		}

		puestoGeneralRepository.crear(puestoGeneralEntity);
	}

	protected void actualizarPuestoGeneral(PuestoGeneralDTO puestoGeneralDTO) {

		PuestoGeneralEntity puestoGeneralEntity = puestoGeneralRepository
				.obtenerPorId(puestoGeneralDTO.getIdPuestoGeneral());

		puestoGeneralEntity.setCodigo(puestoGeneralDTO.getCodigo());
		puestoGeneralEntity.setPuesto(puestoGeneralDTO.getPuesto());

		if (puestoGeneralDTO.getIdTipoPuesto() != 0) {
			TipoPuestoEntity tipoTabuladorEntity = tipoPuestoRepository
					.obtenerPorId(puestoGeneralDTO.getIdTipoPuesto());

			puestoGeneralEntity.setIdTipoPuesto(tipoTabuladorEntity);
		}

		if (puestoGeneralDTO.getIdRama() != 0) {
			RamaEntity ramaEntity = ramaRepository.obtenerPorId(puestoGeneralDTO.getIdRama());

			puestoGeneralEntity.setIdRama(ramaEntity);
		}

		puestoGeneralRepository.actualizar(puestoGeneralEntity);

	}

	protected void eliminarPuestoGeneral(Integer idPuestoGeneral) {
		PuestoGeneralEntity puestoGeneralEntity = puestoGeneralRepository.obtenerPorId(idPuestoGeneral);
		
		puestoGeneralRepository.eliminar(puestoGeneralEntity);

	}

	protected Boolean existeCodigo(String codigo) {
		return puestoGeneralRepository.existePuestoPorCodigo(codigo);
	}

	protected Boolean existeCodigoIdPuesto(Integer idPuestoGeneral, String codigo) {

		return puestoGeneralRepository.existePuestoPorCodigoIdPuesto(idPuestoGeneral, codigo);
	}

	protected PuestoGeneralDTO puestoPorClave(String clave){
		PuestoGeneralEntity entity = puestoGeneralRepository.puestoPorClave(clave);
		PuestoGeneralDTO dto = new PuestoGeneralDTO();

		dto.setIdPuestoGeneral(entity.getIdPuestoGeneral());
		dto.setCodigo(entity.getCodigo());
		dto.setPuesto(entity.getPuesto());

		if (entity.getIdTipoPuesto() != null
				|| entity.getIdTipoPuesto().getIdTipoPuesto() != 0) {
			dto.setIdTipoPuesto(entity.getIdTipoPuesto().getIdTipoPuesto());
			dto.setDescripcionTipoPuesto(entity.getIdTipoPuesto().getDescripcion());
		}

		if (entity.getIdRama() != null) {
			dto.setIdRama(entity.getIdRama().getIdRamaPuesto());
			dto.setDescripcionRama(entity.getIdRama().getNombreRamaPuesto());
		}

		return dto;
	}
	
	protected PuestoGeneralDTO puestoPorId(Integer id){
		PuestoGeneralEntity entity = puestoGeneralRepository.obtenerPorId(id);
		PuestoGeneralDTO dto = new PuestoGeneralDTO();

		dto.setIdPuestoGeneral(entity.getIdPuestoGeneral());
		dto.setCodigo(entity.getCodigo());
		dto.setPuesto(entity.getPuesto());

		if (entity.getIdTipoPuesto() != null
				|| entity.getIdTipoPuesto().getIdTipoPuesto() != 0) {
			dto.setIdTipoPuesto(entity.getIdTipoPuesto().getIdTipoPuesto());
			dto.setDescripcionTipoPuesto(entity.getIdTipoPuesto().getDescripcion());
		}

		if (entity.getIdRama() != null) {
			dto.setIdRama(entity.getIdRama().getIdRamaPuesto());
			dto.setDescripcionRama(entity.getIdRama().getNombreRamaPuesto());
		}

		return dto;
	}
	
}
