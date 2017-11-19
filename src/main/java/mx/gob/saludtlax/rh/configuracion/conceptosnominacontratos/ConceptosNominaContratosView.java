
package mx.gob.saludtlax.rh.configuracion.conceptosnominacontratos;

import java.io.Serializable;
import java.util.List;

import mx.gob.saludtlax.rh.configuracion.conceptosnomina.CategoriaSatDTO;
import mx.gob.saludtlax.rh.configuracion.conceptosnomina.EstatusConceptoNominaDTO;

public class ConceptosNominaContratosView implements Serializable {
    private static final long serialVersionUID = 6864499941801779132L;

    private ConceptoNominaContratosDTO conceptoNominaSelect;
    private List<ConceptoNominaContratosDTO> conceptoNominaLista;
    private List<EstatusConceptoNominaDTO> estatusConceptoNominaLista;
    private List<CategoriaSatDTO> categoriaSatLista;

    private Integer tipoSelect;
    private Boolean panelPrincipal;
    private Boolean panelGestion;
    private Boolean disabledIrGestionar;
    private Boolean disabledVerTodos;
    private Boolean disabledVerPercepcion;
    private Boolean disabledVerDeduccion;
    private Boolean opcion;

    private String editarFormula;
    private String result;

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

    public ConceptoNominaContratosDTO getConceptoNominaSelect() {
        return conceptoNominaSelect;
    }

    public void setConceptoNominaSelect(
            ConceptoNominaContratosDTO conceptoNominaSelect) {
        this.conceptoNominaSelect = conceptoNominaSelect;
    }

    public List<ConceptoNominaContratosDTO> getConceptoNominaLista() {
        return conceptoNominaLista;
    }

    public void setConceptoNominaLista(
            List<ConceptoNominaContratosDTO> conceptoNominaLista) {
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

    public void setEstatusConceptoNominaLista(
            List<EstatusConceptoNominaDTO> estatusConceptoNominaLista) {
        this.estatusConceptoNominaLista = estatusConceptoNominaLista;
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

    public Boolean getOpcion() {
        return opcion;
    }

    public void setOpcion(Boolean opcion) {
        this.opcion = opcion;
    }
}