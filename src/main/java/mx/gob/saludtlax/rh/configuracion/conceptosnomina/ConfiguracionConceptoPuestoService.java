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
	
		entity.setConceptoNomina(conceptoNominaFederalesRepository.obtenerPorId(dto.getId_concepto_nomina()));
		entity.setImporte_concepto(dto.getImporte_concepto());
		entity.setPuestoGeneral(puestoRepository.obtenerPorId(dto.getId_puesto_general()));
		if(dto.getId_tabulador()!=null){
		entity.setTabulador(tabuladorRepository.obtenerPorId(dto.getId_tabulador()));
		}
		entity.setFormula(dto.getFormula());
		reporsitory.crear(entity);

	}

	public void editar(ConfiguracionConceptoPuestoDTO dto) {
		ConfiguracionConceptoPuestoEntity entity = reporsitory.obtenerPorId(dto.getId_configuracion_concepto());

		entity.setConceptoNomina(conceptoNominaFederalesRepository.obtenerPorId(dto.getId_concepto_nomina()));
		entity.setImporte_concepto(dto.getImporte_concepto());
		entity.setPuestoGeneral(puestoRepository.obtenerPorId(dto.getId_puesto_general()));
		if(dto.getId_tabulador()!=null){
		entity.setTabulador(tabuladorRepository.obtenerPorId(dto.getId_tabulador()));
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
				dto.setId_tabulador(ent.getTabulador().getIdTabulador());
				}
				dto.setId_concepto_nomina(ent.getConceptoNomina().getIdConceptoNomina());
				dto.setId_configuracion_concepto(ent.getId_configuracion_concepto());
				dto.setId_puesto_general(ent.getPuestoGeneral().getIdPuestoGeneral());
				dto.setTipoPuesto(ent.getPuestoGeneral().getIdTipoPuesto().getIdTipoPuesto());
				dto.setImporte_concepto(ent.getImporte_concepto());
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
				dto.setId_concepto_nomina(ent.getConceptoNomina().getIdConceptoNomina());
				dto.setId_configuracion_concepto(ent.getId_configuracion_concepto());
				dto.setId_puesto_general(ent.getPuestoGeneral().getIdPuestoGeneral());
				dto.setId_tabulador(ent.getTabulador().getIdTabulador());
				dto.setImporte_concepto(ent.getImporte_concepto());
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
		dto.setId_concepto_nomina(ent.getConceptoNomina().getIdConceptoNomina());
		dto.setId_configuracion_concepto(ent.getId_configuracion_concepto());
		dto.setId_puesto_general(ent.getPuestoGeneral().getIdPuestoGeneral());
		dto.setId_tabulador(ent.getTabulador().getIdTabulador());
		dto.setImporte_concepto(ent.getImporte_concepto());
		dto.setTipoPuesto(ent.getPuestoGeneral().getIdTipoPuesto().getIdTipoPuesto());
		dto.setFormula(ent.getFormula());
		return dto;
	}
}
