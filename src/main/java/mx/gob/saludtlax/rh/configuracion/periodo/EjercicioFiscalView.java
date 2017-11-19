
package mx.gob.saludtlax.rh.configuracion.periodo;

import java.util.List;

import mx.gob.saludtlax.rh.configuracion.ejerciciofiscal.EjercicioFiscalDTO;
import mx.gob.saludtlax.rh.configuracion.ejerciciofiscal.EjercicioFiscalListaDTO;
import mx.gob.saludtlax.rh.configuracion.ejerciciofiscal.PeriodoCalendarioDTO;
import mx.gob.saludtlax.rh.configuracion.tipoperiodo.TipoPeriodoDTO;

public class EjercicioFiscalView {
    private Integer ejercicioFiscalCriterio;
    private List<EjercicioFiscalListaDTO> listEjercicioFiscal;
    private EjercicioFiscalListaDTO ejercicioFiscalSelect;
    private EjercicioFiscalDTO ejercicioFiscal;

    private List<TipoPeriodoDTO> listPeriodicidad;
    private List<TipoPeriodoDTO> listTipoPeriodo;

    private Boolean disabledIrGestionar;
    private Boolean disabledIrGestionarPeriodos;
    private Boolean disabledEliminar;

    private Boolean operacionNuevo;
    private Boolean opcionesPeriodo;

    private Boolean panelPrincipal;
    private Boolean panelGestion;

    private Integer peridoCalendarioCriterio;
    private PeriodoCalendarioDTO periodoCalendarioSelect;
    private PeriodoCalendarioDTO periodoCalendario;

    public void panelPrincipal() {
        ejercicioFiscalSelect = null;
        panelPrincipal = Boolean.TRUE;
        panelGestion = Boolean.FALSE;
        disabledIrGestionar = Boolean.TRUE;
        disabledEliminar = Boolean.TRUE;
        operacionNuevo = null;
        opcionesPeriodo = null;
    }

    public void panelGestion() {
        panelPrincipal = Boolean.FALSE;
        panelGestion = Boolean.TRUE;
        disabledEliminar = Boolean.TRUE;
    }

    public Integer getEjercicioFiscalCriterio() {
        return ejercicioFiscalCriterio;
    }

    public void setEjercicioFiscalCriterio(Integer ejercicioFiscalCriterio) {
        this.ejercicioFiscalCriterio = ejercicioFiscalCriterio;
    }

    public List<EjercicioFiscalListaDTO> getListEjercicioFiscal() {
        return listEjercicioFiscal;
    }

    public void setListEjercicioFiscal(
            List<EjercicioFiscalListaDTO> listEjercicioFiscal) {
        this.listEjercicioFiscal = listEjercicioFiscal;
    }

    public EjercicioFiscalListaDTO getEjercicioFiscalSelect() {
        return ejercicioFiscalSelect;
    }

    public void setEjercicioFiscalSelect(
            EjercicioFiscalListaDTO ejercicioFiscalSelect) {
        this.ejercicioFiscalSelect = ejercicioFiscalSelect;
    }

    public EjercicioFiscalDTO getEjercicioFiscal() {
        return ejercicioFiscal;
    }

    public void setEjercicioFiscal(EjercicioFiscalDTO ejercicioFiscal) {
        this.ejercicioFiscal = ejercicioFiscal;
    }

    public Boolean getDisabledIrGestionar() {
        return disabledIrGestionar;
    }

    public void setDisabledIrGestionar(Boolean disabledIrGestionar) {
        this.disabledIrGestionar = disabledIrGestionar;
    }

    public Boolean getDisabledEliminar() {
        return disabledEliminar;
    }

    public void setDisabledEliminar(Boolean disabledEliminar) {
        this.disabledEliminar = disabledEliminar;
    }

    public Boolean getDisabledIrGestionarPeriodos() {
        return disabledIrGestionarPeriodos;
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

    public List<TipoPeriodoDTO> getListPeriodicidad() {
        return listPeriodicidad;
    }

    public void setListPeriodicidad(List<TipoPeriodoDTO> listPeriodicidad) {
        this.listPeriodicidad = listPeriodicidad;
    }

    public List<TipoPeriodoDTO> getListTipoPeriodo() {
        return listTipoPeriodo;
    }

    public void setListTipoPeriodo(List<TipoPeriodoDTO> listTipoPeriodo) {
        this.listTipoPeriodo = listTipoPeriodo;
    }

    public Boolean getOperacionNuevo() {
        return operacionNuevo;
    }

    public void setOperacionNuevo(Boolean operacionNuevo) {
        this.operacionNuevo = operacionNuevo;
    }

    public Boolean getOpcionesPeriodo() {
        return opcionesPeriodo;
    }

    public void setOpcionesPeriodo(Boolean opcionesPeriodo) {
        this.opcionesPeriodo = opcionesPeriodo;
    }

    public Integer getPeridoCalendarioCriterio() {
        return peridoCalendarioCriterio;
    }

    public void setPeridoCalendarioCriterio(Integer peridoCalendarioCriterio) {
        this.peridoCalendarioCriterio = peridoCalendarioCriterio;
    }

    public PeriodoCalendarioDTO getPeriodoCalendarioSelect() {
        return periodoCalendarioSelect;
    }

    public void setPeriodoCalendarioSelect(
            PeriodoCalendarioDTO periodoCalendarioSelect) {
        this.periodoCalendarioSelect = periodoCalendarioSelect;
    }

    public PeriodoCalendarioDTO getPeriodoCalendario() {
        return periodoCalendario;
    }

    public void setPeriodoCalendario(PeriodoCalendarioDTO periodoCalendario) {
        this.periodoCalendario = periodoCalendario;
    }

}