
package mx.gob.saludtlax.rh.configuracion.conceptosnomina;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.DualListModel;

import mx.gob.saludtlax.rh.configuracion.nombramiento.TipoNombramientoDTO;
import mx.gob.saludtlax.rh.configuracion.puestogeneral.PuestoGeneralDTO;

public class ConceptosNominaView implements Serializable {
    private static final long serialVersionUID = 6864499941801779132L;

    private ConceptoNominaDTO conceptoNominaSelect;
    private List<ConceptoNominaDTO> conceptoNominaLista;
    private List<EstatusConceptoNominaDTO> estatusConceptoNominaLista;
    private List<NivelEmpleadoDTO> nivelEmpleadoLista;
    private List<TipoNombramientoDTO> nombramientoLista;
    private List<CategoriaSatDTO> categoriaSatLista;

    private Integer tipoSelect;
    private Boolean panelPrincipal;
    private Boolean panelGestion;
    private Boolean disabledIrGestionar;
    private Boolean disabledVerTodos;
    private Boolean disabledVerPercepcion;
    private Boolean disabledVerDeduccion;

    private String editarFormula;
    private String result;

    private List<PuestoGeneralDTO> puestos = new ArrayList<>();
    private DualListModel<PuestoGeneralDTO> puestosSeleccion;

    private List<ConfiguracionConceptoPuestoDTO> configPuesto = new ArrayList<>();

    public void panelPrincipal() {
        conceptoNominaSelect = null;
        panelPrincipal = Boolean.TRUE;
        panelGestion = Boolean.FALSE;
        disabledIrGestionar = Boolean.TRUE;
        disabledVerPercepcion = tipoSelect == 1;
        disabledVerDeduccion = tipoSelect == 2;
        disabledVerTodos = tipoSelect == 0;
    }

    public void panelGestion() {
        panelPrincipal = Boolean.FALSE;
        panelGestion = Boolean.TRUE;
    }

    public ConceptoNominaDTO getConceptoNominaSelect() {
        return conceptoNominaSelect;
    }

    public void setConceptoNominaSelect(ConceptoNominaDTO conceptoNominaSelect) {
        this.conceptoNominaSelect = conceptoNominaSelect;
    }

    public List<ConceptoNominaDTO> getConceptoNominaLista() {
        return conceptoNominaLista;
    }

    public void setConceptoNominaLista(List<ConceptoNominaDTO> conceptoNominaLista) {
        this.conceptoNominaLista = conceptoNominaLista;
    }

    public Boolean getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(Boolean panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public Boolean getPanelGestion() {
        return panelGestion;
    }

    public void setPanelGestion(Boolean panelGestion) {
        this.panelGestion = panelGestion;
    }

    public Boolean getDisabledIrGestionar() {
        return disabledIrGestionar;
    }

    public void setDisabledIrGestionar(Boolean disabledIrGestionar) {
        this.disabledIrGestionar = disabledIrGestionar;
    }

    public Boolean getDisabledVerTodos() {
        return disabledVerTodos;
    }

    public void setDisabledVerTodos(Boolean disabledVerTodos) {
        this.disabledVerTodos = disabledVerTodos;
    }

    public Boolean getDisabledVerPercepcion() {
        return disabledVerPercepcion;
    }

    public void setDisabledVerPercepcion(Boolean disabledVerPercepcion) {
        this.disabledVerPercepcion = disabledVerPercepcion;
    }

    public Boolean getDisabledVerDeduccion() {
        return disabledVerDeduccion;
    }

    public void setDisabledVerDeduccion(Boolean disabledVerDeduccion) {
        this.disabledVerDeduccion = disabledVerDeduccion;
    }

    public List<EstatusConceptoNominaDTO> getEstatusConceptoNominaLista() {
        return estatusConceptoNominaLista;
    }

    public void setEstatusConceptoNominaLista(List<EstatusConceptoNominaDTO> estatusConceptoNominaLista) {
        this.estatusConceptoNominaLista = estatusConceptoNominaLista;
    }

    public List<NivelEmpleadoDTO> getNivelEmpleadoLista() {
        return nivelEmpleadoLista;
    }

    public void setNivelEmpleadoLista(List<NivelEmpleadoDTO> nivelEmpleadoLista) {
        this.nivelEmpleadoLista = nivelEmpleadoLista;
    }

    public List<TipoNombramientoDTO> getNombramientoLista() {
        return nombramientoLista;
    }

    public void setNombramientoLista(List<TipoNombramientoDTO> nombramientoLista) {
        this.nombramientoLista = nombramientoLista;
    }

    public List<CategoriaSatDTO> getCategoriaSatLista() {
        return categoriaSatLista;
    }

    public void setCategoriaSatLista(List<CategoriaSatDTO> categoriaSatLista) {
        this.categoriaSatLista = categoriaSatLista;
    }

    public Integer getTipoSelect() {
        return tipoSelect;
    }

    public void setTipoSelect(Integer tipoSelect) {
        this.tipoSelect = tipoSelect;
    }

    public String getEditarFormula() {
        return editarFormula;
    }

    public void setEditarFormula(String editarFormula) {
        this.editarFormula = editarFormula;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<PuestoGeneralDTO> getPuestos() {
        return puestos;
    }

    public void setPuestos(List<PuestoGeneralDTO> puestos) {
        this.puestos = puestos;
    }

    public DualListModel<PuestoGeneralDTO> getPuestosSeleccion() {
        return puestosSeleccion;
    }

    public void setPuestosSeleccion(DualListModel<PuestoGeneralDTO> puestosSeleccion) {
        this.puestosSeleccion = puestosSeleccion;
    }

    public List<ConfiguracionConceptoPuestoDTO> getConfigPuesto() {
        return configPuesto;
    }

    public void setConfigPuesto(List<ConfiguracionConceptoPuestoDTO> configPuesto) {
        this.configPuesto = configPuesto;
    }
}