package mx.gob.saludtlax.rh.configuracion.conceptosnomina;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.siif.reportarcontratos.BusinessException;
import mx.gob.saludtlax.rh.util.JSFUtils;

@ManagedBean
@ViewScoped
public class ConceptosEspecialesController implements Serializable{
	
	@Inject
	ConceptoEspecialesService conceptoEspecialesService;
	
	@Inject
	ConceptoNominaFederalesEJB conceptoNominaEJB;
	
	private List<ConceptoNominaFederalEspecialDTO> listaConceptos = new ArrayList<>();
	private ConceptoNominaFederalEspecialDTO conceptoSeleccionado;
    private List<ConceptoNominaFederalesDTO> percepciones = new ArrayList<>();
    private List<ConceptoNominaFederalesDTO> deducciones = new ArrayList<>();
	
    private List<SelectItem> percepcionItems = new ArrayList<>();
    private List<SelectItem> deduccionesItems = new ArrayList<>();
    
    private Integer persepcionBaseSeleccionada;
    private Integer percepcionCompensacionSeleccionada;
    private Integer deduccionSeleccionada;
    
    
    @PostConstruct
    public void inicio(){
    	listaConceptos.clear();
    	listaConceptos = conceptoEspecialesService.listaConceptos();
    	
    	percepciones.clear();
    	percepciones = conceptoNominaEJB.obtenerConceptoNominasLista(TipoConceptoNominaEnum.PERCEPCION);
    	percepcionItems.clear();
    	for(ConceptoNominaFederalesDTO dto:percepciones){
    		percepcionItems.add(new SelectItem(dto.getIdConceptoNomina(),dto.getClave()+"--"+dto.getDescripcion()));
    	}
    	
    	
    	deducciones.clear();
    	deducciones= conceptoNominaEJB.obtenerConceptoNominasLista(TipoConceptoNominaEnum.DEDUCCION);
    	for(ConceptoNominaFederalesDTO dto:deducciones){
    		deduccionesItems.add(new SelectItem(dto.getIdConceptoNomina(),dto.getClave()+"--"+dto.getDescripcion()));
    	}
    }

    
    public void agregarConcepto(){
    	
    	
    	ConceptoNominaFederalEspecialDTO dto = new ConceptoNominaFederalEspecialDTO();
    	dto.setIdConceptoBase(persepcionBaseSeleccionada);
    	dto.setIdConceptoCompensacion(percepcionCompensacionSeleccionada);
    	dto.setIdConceptoIsr(deduccionSeleccionada);
    	
    	try{
    		conceptoEspecialesService.agregarConcepto(dto);
    		inicio();
    	}catch(BusinessException e){
    		JSFUtils.errorMessage("Error:", e.getMessage());
    	}
    }
    
    public void eliminar(){
    	conceptoEspecialesService.eliminarConcepto(conceptoSeleccionado.getId());
    	inicio();
    }
    

	public List<ConceptoNominaFederalEspecialDTO> getListaConceptos() {
		return listaConceptos;
	}


	public void setListaConceptos(List<ConceptoNominaFederalEspecialDTO> listaConceptos) {
		this.listaConceptos = listaConceptos;
	}


	public List<ConceptoNominaFederalesDTO> getPercepciones() {
		return percepciones;
	}


	public void setPercepciones(List<ConceptoNominaFederalesDTO> percepciones) {
		this.percepciones = percepciones;
	}


	public List<ConceptoNominaFederalesDTO> getDeducciones() {
		return deducciones;
	}


	public void setDeducciones(List<ConceptoNominaFederalesDTO> deducciones) {
		this.deducciones = deducciones;
	}


	public List<SelectItem> getPercepcionItems() {
		return percepcionItems;
	}


	public void setPercepcionItems(List<SelectItem> percepcionItems) {
		this.percepcionItems = percepcionItems;
	}


	public List<SelectItem> getDeduccionesItems() {
		return deduccionesItems;
	}


	public void setDeduccionesItems(List<SelectItem> deduccionesItems) {
		this.deduccionesItems = deduccionesItems;
	}


	public Integer getPersepcionBaseSeleccionada() {
		return persepcionBaseSeleccionada;
	}


	public void setPersepcionBaseSeleccionada(Integer persepcionBaseSeleccionada) {
		this.persepcionBaseSeleccionada = persepcionBaseSeleccionada;
	}


	public Integer getPercepcionCompensacionSeleccionada() {
		return percepcionCompensacionSeleccionada;
	}


	public void setPercepcionCompensacionSeleccionada(Integer percepcionCompensacionSeleccionada) {
		this.percepcionCompensacionSeleccionada = percepcionCompensacionSeleccionada;
	}


	public Integer getDeduccionSeleccionada() {
		return deduccionSeleccionada;
	}


	public void setDeduccionSeleccionada(Integer deduccionSeleccionada) {
		this.deduccionSeleccionada = deduccionSeleccionada;
	}


	public ConceptoNominaFederalEspecialDTO getConceptoSeleccionado() {
		return conceptoSeleccionado;
	}


	public void setConceptoSeleccionado(ConceptoNominaFederalEspecialDTO conceptoSeleccionado) {
		this.conceptoSeleccionado = conceptoSeleccionado;
	}
    
    
}
