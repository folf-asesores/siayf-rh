
package mx.gob.saludtlax.rh.configuracion.tiponomina;

import java.util.List;

import mx.gob.saludtlax.rh.configuracion.clasificacionnomina.ClasificacionNominaDTO;
import mx.gob.saludtlax.rh.configuracion.fuenteFinanciamiento.FuenteFinanciamientoDTO;
import mx.gob.saludtlax.rh.configuracion.fuenteFinanciamiento.SubfuenteFinanciamientoDTO;

public class TipoNominaView {
    private Integer tipoNominaCriterio;
    private List<TipoNominaDTO> listTipoNomina;
    private List<TipoNominaListaDTO> TipoNominaLista;
    private TipoNominaDTO tipoNominaSelect;
    private TipoNominaDTO tipoNomina;

    private Boolean disabledIrGestionar;
    private Boolean operacionNuevo;
    private Boolean opEliminar;
    private Boolean panelPrincipal;
    private Boolean panelGestion;

    private List<ClasificacionNominaDTO> listClasificacion;
    private List<FuenteFinanciamientoDTO> listFuente;
    private List<SubfuenteFinanciamientoDTO> listSubfuente;
    private List<TipoRecursoDTO> listTipoRecurso;

    public void panelPrincipal() {
        tipoNominaSelect = null;
        panelPrincipal = Boolean.TRUE;
        panelGestion = Boolean.FALSE;
        disabledIrGestionar = Boolean.TRUE;
        operacionNuevo = null;
        opEliminar = null;
    }

    public void panelGestion() {
        panelPrincipal = Boolean.FALSE;
        panelGestion = Boolean.TRUE;
    }

    public Integer getTipoNominaCriterio() {
        return tipoNominaCriterio;
    }

    public void setTipoNominaCriterio(Integer tipoNominaCriterio) {
        this.tipoNominaCriterio = tipoNominaCriterio;
    }

    public List<TipoNominaDTO> getListTipoNomina() {
        return listTipoNomina;
    }

    public void setListTipoNomina(List<TipoNominaDTO> listTipoNomina) {
        this.listTipoNomina = listTipoNomina;
    }

    public TipoNominaDTO getTipoNominaSelect() {
        return tipoNominaSelect;
    }

    public void setTipoNominaSelect(TipoNominaDTO tipoNominaSelect) {
        this.tipoNominaSelect = tipoNominaSelect;
    }

    public TipoNominaDTO getTipoNomina() {
        return tipoNomina;
    }

    public void setTipoNomina(TipoNominaDTO tipoNomina) {
        this.tipoNomina = tipoNomina;
    }

    public Boolean getDisabledIrGestionar() {
        return disabledIrGestionar;
    }

    public void setDisabledIrGestionar(Boolean disabledIrGestionar) {
        this.disabledIrGestionar = disabledIrGestionar;
    }

    public Boolean getOperacionNuevo() {
        return operacionNuevo;
    }

    public void setOperacionNuevo(Boolean operacionNuevo) {
        this.operacionNuevo = operacionNuevo;
    }

    public Boolean getOpEliminar() {
        return opEliminar;
    }

    public void setOpEliminar(Boolean opEliminar) {
        this.opEliminar = opEliminar;
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

    public List<ClasificacionNominaDTO> getListClasificacion() {
        return listClasificacion;
    }

    public void setListClasificacion(
            List<ClasificacionNominaDTO> listClasificacion) {
        this.listClasificacion = listClasificacion;
    }

    public List<FuenteFinanciamientoDTO> getListFuente() {
        return listFuente;
    }

    public void setListFuente(List<FuenteFinanciamientoDTO> listFuente) {
        this.listFuente = listFuente;
    }

    public List<SubfuenteFinanciamientoDTO> getListSubfuente() {
        return listSubfuente;
    }

    public void setListSubfuente(
            List<SubfuenteFinanciamientoDTO> listSubfuente) {
        this.listSubfuente = listSubfuente;
    }

    public List<TipoNominaListaDTO> getTipoNominaLista() {
        return TipoNominaLista;
    }

    /**
     * @return the listTipoRecurso
     */
    public List<TipoRecursoDTO> getListTipoRecurso() {
        return listTipoRecurso;
    }

    /**
     * @param listTipoRecurso
     *            the listTipoRecurso to set
     */
    public void setListTipoRecurso(List<TipoRecursoDTO> listTipoRecurso) {
        this.listTipoRecurso = listTipoRecurso;
    }

    public void setTipoNominaLista(List<TipoNominaListaDTO> tipoNominaLista) {
        TipoNominaLista = tipoNominaLista;
    }

}