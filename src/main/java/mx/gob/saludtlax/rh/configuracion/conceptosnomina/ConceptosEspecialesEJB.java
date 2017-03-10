package mx.gob.saludtlax.rh.configuracion.conceptosnomina;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceException;

import mx.gob.saludtlax.rh.persistencia.ConceptoNominaFederalesRepository;
import mx.gob.saludtlax.rh.persistencia.ConceptosNominaEspecialEntity;
import mx.gob.saludtlax.rh.persistencia.ConceptosNominaEspecialRepository;
import mx.gob.saludtlax.rh.siif.reportarcontratos.BusinessException;


@Stateless
public class ConceptosEspecialesEJB implements ConceptoEspecialesService {

	@Inject
	ConceptosNominaEspecialRepository conceptosEspecialesRepository;

	@Inject
	ConceptoNominaFederalesRepository conceptosFederalesRepository;

	@Override
	public List<ConceptoNominaFederalEspecialDTO> listaConceptos() {
		List<ConceptosNominaEspecialEntity> listEntitys = new ArrayList<>();
		listEntitys = conceptosEspecialesRepository.consultarTodos();

		List<ConceptoNominaFederalEspecialDTO> listDtos = new ArrayList<>();
		if (listEntitys != null) {
			for (ConceptosNominaEspecialEntity entity : listEntitys) {
				ConceptoNominaFederalEspecialDTO dto = new ConceptoNominaFederalEspecialDTO();
				dto.setClaveConceptoBase(entity.getIdConceptobase().getClave());
				dto.setClaveConceptoCompensacion(entity.getIdConceptoCompensacion()!=null? entity.getIdConceptoCompensacion().getClave():null);
				dto.setClaveConceptoIsr(entity.getIdConceptoISR().getClave());

				dto.setDescripcionConceptoBase(entity.getIdConceptobase().getDescripcion());
				dto.setDescripcionConceptoCompensacion(entity.getIdConceptoCompensacion()!=null?entity.getIdConceptoCompensacion().getDescripcion():null);
				dto.setDescripcionConceptoIsr(entity.getIdConceptoISR().getDescripcion());

				dto.setId(entity.getIdConceptoEspecial());

				dto.setIdConceptoBase(entity.getIdConceptobase().getIdConceptoNomina());
				dto.setIdConceptoCompensacion(entity.getIdConceptoCompensacion()!=null?entity.getIdConceptoCompensacion().getIdConceptoNomina():null);
				dto.setIdConceptoIsr(entity.getIdConceptoISR().getIdConceptoNomina());

				listDtos.add(dto);
			}
		}

		return listDtos;
	}

	@Override
	public void agregarConcepto(ConceptoNominaFederalEspecialDTO dto) {
		try {
			ConceptosNominaEspecialEntity entity = new ConceptosNominaEspecialEntity();
			entity.setIdConceptobase(conceptosFederalesRepository.obtenerPorId(dto.getIdConceptoBase()));
			entity.setIdConceptoCompensacion(
					conceptosFederalesRepository.obtenerPorId(dto.getIdConceptoCompensacion()));
			entity.setIdConceptoISR(conceptosFederalesRepository.obtenerPorId(dto.getIdConceptoIsr()));

			conceptosEspecialesRepository.crear(entity);
		} catch (PersistenceException e) {
			throw new BusinessException("Se presento un problema al guardar.");
		}
	}

	@Override
	public void eliminarConcepto(Integer id) {
		conceptosEspecialesRepository.eliminarPorId(id);
	}

	@Override
	public void actualizarConcepto(ConceptoNominaFederalEspecialDTO dto) {
		try {
			ConceptosNominaEspecialEntity entity = conceptosEspecialesRepository.obtenerPorId(dto.getId());
			entity.setIdConceptobase(conceptosFederalesRepository.obtenerPorId(dto.getIdConceptoBase()));
			entity.setIdConceptoCompensacion(
					conceptosFederalesRepository.obtenerPorId(dto.getIdConceptoCompensacion()));
			entity.setIdConceptoISR(conceptosFederalesRepository.obtenerPorId(dto.getIdConceptoIsr()));

			conceptosEspecialesRepository.actualizar(entity);
		} catch (PersistenceException e) {
			throw new BusinessException("Se presento un problema al guardar.");
		}
	}

}
