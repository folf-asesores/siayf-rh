package mx.gob.saludtlax.rh.configuracion.conceptosnomina;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.nomina.Evaluador;
import mx.gob.saludtlax.rh.configuracion.nombramiento.TipoNombramientoDTO;

@Stateless
public class ConceptoNominaEJB implements Serializable {
	private static final long serialVersionUID = 2280757059626791094L;

	@Inject
	private ConceptoNominaService conceptoNominaService;

	public List<ConceptoNominaDTO> obtenerConceptoNominasLista(TipoConceptoNominaEnum tipoConceptoNominaEnum) {
		List<ConceptoNominaDTO> conceptoNominasLista = conceptoNominaService.obtenerConceptoNominasLista(tipoConceptoNominaEnum);
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

	public ConceptoNominaDTO nuevoConceptoNomina() {
		return conceptoNominaService.nuevoConceptoNomina();
	}

	public ConceptoNominaDTO crearConceptoNomina(ConceptoNominaDTO dto) {
		return conceptoNominaService.crearConceptoNomina(dto);
	}

	public ConceptoNominaDTO obtenerConceptoNominaPorId(Integer idConceptoNomina) {
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
}