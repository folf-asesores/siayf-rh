package mx.gob.saludtlax.rh.configuracion.conceptosnomina;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.nomina.Evaluador;
import mx.gob.saludtlax.rh.configuracion.nombramiento.TipoNombramientoDTO;

@Stateless
public class ConceptoNominaFederalesEJB implements Serializable {
	private static final long serialVersionUID = 2280757059626791094L;

	@Inject
	private ConceptoNominaFederalesService conceptoNominaService;

	public List<ConceptoNominaFederalesDTO> obtenerConceptoNominasLista(TipoConceptoNominaEnum tipoConceptoNominaEnum) {
		List<ConceptoNominaFederalesDTO> conceptoNominasLista = conceptoNominaService.obtenerConceptoNominasLista(tipoConceptoNominaEnum);
		return conceptoNominasLista;
	}

	public List<EstatusConceptoNominaDTO> listaEstatusConceptoNomina() {
		return conceptoNominaService.listaEstatusConceptoNomina();
	}

	public List<NivelEmpleadoDTO> listaNivelEmpleado() {
		return conceptoNominaService.listaNivelEmpleado();
	}

	public List<TipoNombramientoDTO> listaNombramiento() {
		return conceptoNominaService.listaNombramiento();
	}

	public List<CategoriaSatDTO> listaCategoriaSatPorTipo(Integer tipo) {
		return conceptoNominaService.listaCategoriaSatPorTipo(tipo);
	}

	public ConceptoNominaFederalesDTO nuevoConceptoNomina() {
		return conceptoNominaService.nuevoConceptoNomina();
	}

	public ConceptoNominaFederalesDTO crearConceptoNomina(ConceptoNominaFederalesDTO dto) {
		return conceptoNominaService.crearConceptoNomina(dto);
	}

	public ConceptoNominaFederalesDTO actualizarConceptoNomina(ConceptoNominaFederalesDTO dto) {
		return conceptoNominaService.actualizarConceptoNomina(dto);
	}

	
	public ConceptoNominaFederalesDTO obtenerConceptoNominaPorId(Integer idConceptoNomina) {
		return conceptoNominaService.obtenerConceptoNominaPorId(idConceptoNomina);
	}

	public List<FormulaDTO> listaFormula() {
		List<FormulaDTO> formulaList = new ArrayList<FormulaDTO>();
//		formulaList.addAll(conceptoNominaService.listaFormulaConstante());
		formulaList.add(conceptoNominaService.listaFormula());
		return formulaList;
	}

	public String evaluarFormula(String editarFormula) {
		Evaluador evaluador = new Evaluador(editarFormula);
		return String.valueOf(evaluador.getResult());
	}
	
	public List<ConceptoNominaFederalesDTO> obtenerConceptosPorConfiguracionPresupuestal(Integer idConfiguracion){
		return conceptoNominaService.obtenerConceptosPorConfiguracionPresupuestal(idConfiguracion);
	}
}