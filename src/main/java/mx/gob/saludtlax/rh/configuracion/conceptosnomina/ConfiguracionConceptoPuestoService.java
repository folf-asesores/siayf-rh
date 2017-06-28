package mx.gob.saludtlax.rh.configuracion.conceptosnomina;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.persistencia.ConceptoNominaFederalesRepository;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionConceptoPuestoEntity;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionConceptoPuestoRepository;
import mx.gob.saludtlax.rh.persistencia.PuestoGeneralRepository;
import mx.gob.saludtlax.rh.persistencia.TabuladorRepository;

public class ConfiguracionConceptoPuestoService {

	@Inject
	private ConfiguracionConceptoPuestoRepository reporsitory;

	@Inject
	private ConceptoNominaFederalesRepository conceptoNominaFederalesRepository;

	@Inject
	private PuestoGeneralRepository puestoRepository;

	@Inject
	private TabuladorRepository tabuladorRepository;

	public void crear(ConfiguracionConceptoPuestoDTO dto) {
		ConfiguracionConceptoPuestoEntity entity = new ConfiguracionConceptoPuestoEntity();

		entity.setConceptoNomina(conceptoNominaFederalesRepository.obtenerPorId(dto.getIdConceptoNomina()));
		entity.setImporteConcepto(dto.getImporteConcepto());
		entity.setPuestoGeneral(puestoRepository.obtenerPorId(dto.getIdPuestoGeneral()));
		if(dto.getIdTabulador()!=null){
		entity.setTabulador(tabuladorRepository.obtenerPorId(dto.getIdTabulador()));
		}
		entity.setFormula(dto.getFormula());
		reporsitory.crear(entity);

	}

	public void editar(ConfiguracionConceptoPuestoDTO dto) {
		ConfiguracionConceptoPuestoEntity entity = reporsitory.obtenerPorId(dto.getIdConfiguracionConcepto());

		entity.setConceptoNomina(conceptoNominaFederalesRepository.obtenerPorId(dto.getIdConceptoNomina()));
		entity.setImporteConcepto(dto.getImporteConcepto());
		entity.setPuestoGeneral(puestoRepository.obtenerPorId(dto.getIdPuestoGeneral()));
		if(dto.getIdTabulador()!=null){
		entity.setTabulador(tabuladorRepository.obtenerPorId(dto.getIdTabulador()));
		}
        entity.setFormula(dto.getFormula());
		reporsitory.crear(entity);
	}

	public void borrar(Integer idConfiguracion) {
		reporsitory.eliminarPorId(idConfiguracion);
	}

	public List<ConfiguracionConceptoPuestoDTO> obtenerListaConfiguracionPorConcepto(Integer idConcepto) {
		List<ConfiguracionConceptoPuestoDTO> listDtos = new ArrayList<>();
		List<ConfiguracionConceptoPuestoEntity> listEntitys = new ArrayList<>();

		listEntitys = reporsitory.obtenerListaConfiguracionesPorConcepto(idConcepto);

		if (listEntitys != null) {
			for (ConfiguracionConceptoPuestoEntity ent : listEntitys) {
				ConfiguracionConceptoPuestoDTO dto = new ConfiguracionConceptoPuestoDTO();
				dto.setClaveConcepto(ent.getConceptoNomina().getClave());
				dto.setCodigoPuesto(ent.getPuestoGeneral().getCodigo());
				dto.setDecripcionConcepto(ent.getConceptoNomina().getDescripcion());
				dto.setDescripcionPuesto(ent.getPuestoGeneral().getPuesto());
				if(ent.getTabulador()!=null){
				dto.setEjercicioFiscalTabulador(ent.getTabulador().getEjercicioFiscal());
				dto.setIdTabulador(ent.getTabulador().getIdTabulador());
				}
				dto.setIdConceptoNomina(ent.getConceptoNomina().getIdConceptoNomina());
				dto.setIdConfiguracionConcepto(ent.getIdConfiguracionConcepto());
				dto.setIdPuestoGeneral(ent.getPuestoGeneral().getIdPuestoGeneral());
				dto.setTipoPuesto(ent.getPuestoGeneral().getIdTipoPuesto().getIdTipoPuesto());
				dto.setImporteConcepto(ent.getImporteConcepto());
				dto.setFormula(ent.getFormula());
				listDtos.add(dto);
			}
		}
		return listDtos;
	}

	public List<ConfiguracionConceptoPuestoDTO> obtenerListaConfiguracionPorPuesto(Integer idPuesto) {
		List<ConfiguracionConceptoPuestoDTO> listDtos = new ArrayList<>();
		List<ConfiguracionConceptoPuestoEntity> listEntitys = new ArrayList<>();

		listEntitys = reporsitory.obtenerListaConfiguracionesPorPuesto(idPuesto);

		if (listEntitys != null) {
			for (ConfiguracionConceptoPuestoEntity ent : listEntitys) {
				ConfiguracionConceptoPuestoDTO dto = new ConfiguracionConceptoPuestoDTO();
				dto.setClaveConcepto(ent.getConceptoNomina().getClave());
				dto.setCodigoPuesto(ent.getPuestoGeneral().getCodigo());
				dto.setDecripcionConcepto(ent.getConceptoNomina().getDescripcion());
				dto.setDescripcionPuesto(ent.getPuestoGeneral().getPuesto());
				dto.setEjercicioFiscalTabulador(ent.getTabulador().getEjercicioFiscal());
				dto.setIdConceptoNomina(ent.getConceptoNomina().getIdConceptoNomina());
				dto.setIdConfiguracionConcepto(ent.getIdConfiguracionConcepto());
				dto.setIdPuestoGeneral(ent.getPuestoGeneral().getIdPuestoGeneral());
				dto.setIdTabulador(ent.getTabulador().getIdTabulador());
				dto.setImporteConcepto(ent.getImporteConcepto());
				dto.setTipoPuesto(ent.getPuestoGeneral().getIdTipoPuesto().getIdTipoPuesto());
				dto.setFormula(ent.getFormula());
				listDtos.add(dto);
			}
		}
		return listDtos;
	}



	public ConfiguracionConceptoPuestoDTO obtenerConfiguracionPorPuestoCocepto(Integer idPuesto, Integer idConcepto){
		ConfiguracionConceptoPuestoEntity ent = new ConfiguracionConceptoPuestoEntity();
		ent = reporsitory.obtenerPorConceptoPuesto(idPuesto, idConcepto);

		ConfiguracionConceptoPuestoDTO dto = new ConfiguracionConceptoPuestoDTO();
		dto.setClaveConcepto(ent.getConceptoNomina().getClave());
		dto.setCodigoPuesto(ent.getPuestoGeneral().getCodigo());
		dto.setDecripcionConcepto(ent.getConceptoNomina().getDescripcion());
		dto.setDescripcionPuesto(ent.getPuestoGeneral().getPuesto());
		dto.setEjercicioFiscalTabulador(ent.getTabulador().getEjercicioFiscal());
		dto.setIdConceptoNomina(ent.getConceptoNomina().getIdConceptoNomina());
		dto.setIdConfiguracionConcepto(ent.getIdConfiguracionConcepto());
		dto.setIdPuestoGeneral(ent.getPuestoGeneral().getIdPuestoGeneral());
		dto.setIdTabulador(ent.getTabulador().getIdTabulador());
		dto.setImporteConcepto(ent.getImporteConcepto());
		dto.setTipoPuesto(ent.getPuestoGeneral().getIdTipoPuesto().getIdTipoPuesto());
		dto.setFormula(ent.getFormula());
		return dto;
	}
}
