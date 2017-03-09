package mx.gob.saludtlax.rh.siif.revisarcheques;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.persistencia.RevisarChequesEntity;
import mx.gob.saludtlax.rh.persistencia.RevisarChequesRepository;

public class RevisarChequesServices implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -591511725948944727L;
	/**
	 * 
	 */
	@Inject
	private RevisarChequesRepository revisarChequesRepository;
	
	protected List<RevisarChequesDTO> obtenerListaRevisarCheques() {
		List<RevisarChequesDTO> listaRevisarChequesDTOs = new ArrayList<>();

		listaRevisarChequesDTOs = revisarChequesRepository.obtenerListaRevisarCheques();

//		if (!obtenerRevisarChequesEntities.isEmpty()) {
//			for (RevisarChequesEntity revisarchequesEntity : obtenerRevisarChequesEntities) {
//				RevisarChequesDTO dto = new RevisarChequesDTO();
//
//				dto.setNumCuenta(revisarchequesEntity.getNumCuenta());
//				dto.setRfc(revisarchequesEntity.getRfc());
//				dto.setCheque(revisarchequesEntity.getNumCheq());
//				dto.setNombramiento(revisarchequesEntity.getNombramiento());
//
//				listaRevisarChequesDTOs.add(dto);
//			}
//
//		} else {
//			listaRevisarChequesDTOs = new ArrayList<>();
//		}

		return listaRevisarChequesDTOs;
	}
	
	protected List<RevisarChequesDTO> obtenerListaRevisarCheques(String periodo, Integer anio) {
		List<RevisarChequesDTO> listaRevisarChequesDTOs = new ArrayList<>();

		listaRevisarChequesDTOs = revisarChequesRepository.obtenerListaRevisarCheques(periodo, anio);

		return listaRevisarChequesDTOs;
	}
	
	protected List<RevisarChequesDTO> obtenerListaRevisarChequesAvanzada() {
		List<RevisarChequesDTO> listaRevisarChequesDTOs = new ArrayList<>();
		listaRevisarChequesDTOs = revisarChequesRepository.obtenerListaRevisarChequesAvanzada();
		return listaRevisarChequesDTOs;
	}

}
