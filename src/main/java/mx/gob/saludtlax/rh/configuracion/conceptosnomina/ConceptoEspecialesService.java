package mx.gob.saludtlax.rh.configuracion.conceptosnomina;

import java.util.List;

public interface ConceptoEspecialesService {

	public List<ConceptoNominaFederalEspecialDTO> listaConceptos();
	public void agregarConcepto(ConceptoNominaFederalEspecialDTO dto);
	public void eliminarConcepto(Integer id);
	public void actualizarConcepto(ConceptoNominaFederalEspecialDTO dto);
	
}
