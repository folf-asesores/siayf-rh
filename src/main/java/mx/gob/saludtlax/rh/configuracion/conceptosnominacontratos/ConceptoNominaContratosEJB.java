package mx.gob.saludtlax.rh.configuracion.conceptosnominacontratos;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.configuracion.conceptosnomina.CategoriaSatDTO;
import mx.gob.saludtlax.rh.configuracion.conceptosnomina.EstatusConceptoNominaDTO;
import mx.gob.saludtlax.rh.configuracion.conceptosnomina.TipoConceptoNominaEnum;
import mx.gob.saludtlax.rh.nomina.Evaluador;

@Stateless
public class ConceptoNominaContratosEJB implements Serializable {
	private static final long serialVersionUID = 2280757059626791094L;

	@Inject
	private ConceptoNominaContratosService conceptoNominaContratosService;

	public List<ConceptoNominaContratosDTO> obtenerConceptoNominasLista(TipoConceptoNominaEnum tipoConceptoNominaEnum) {
		List<ConceptoNominaContratosDTO> conceptoNominasLista = conceptoNominaContratosService.obtenerConceptoNominasLista(tipoConceptoNominaEnum);
		return conceptoNominasLista;
	}

	public List<EstatusConceptoNominaDTO> listaEstatusConceptoNomina() {
		return conceptoNominaContratosService.listaEstatusConceptoNomina();
	}

	public List<CategoriaSatDTO> listaCategoriaSatPorTipo(Integer tipo) {
		return conceptoNominaContratosService.listaCategoriaSatPorTipo(tipo);
	}

	public ConceptoNominaContratosDTO nuevoConceptoNomina() {
		return conceptoNominaContratosService.nuevoConceptoNomina();
	}

	public ConceptoNominaContratosDTO crearConceptoNomina(ConceptoNominaContratosDTO dto) {
		return conceptoNominaContratosService.crearConceptoNomina(dto);
	}

	public String evaluarFormula(String editarFormula) {
		Evaluador evaluador = new Evaluador(editarFormula);
		return String.valueOf(evaluador.getResult());
	}

    public ConceptoNominaContratosDTO actualizarConceptoNomina(ConceptoNominaContratosDTO conceptoNomina) {
        return conceptoNominaContratosService.actualizarConceptoNomina(conceptoNomina);
    }
}