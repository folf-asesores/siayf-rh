/*
 *
 */

package mx.gob.saludtlax.rh.configuracion.calendarioglobal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eduardo Mex
 *
 */
public class CalendarioGlobalView implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6279967858097044449L;

    private List<CalendarioGlobalDTO> listaCalendarioGlobal = new ArrayList<>();

    private CalendarioGlobalDTO creaCalendarioGlobal;

    private CalendarioGlobalDTO actualizarCalendarioGlobal;

    private boolean mostrarVistaPrincipal;

    private boolean mostrarVistaCrear;

    private boolean mostrarVistaActualizar;

    private Integer idCalendarioGlobal;

    public CalendarioGlobalDTO getCreaCalendarioGlobal() {
        return creaCalendarioGlobal;
    }

    public void setCreaCalendarioGlobal(CalendarioGlobalDTO creaCalendarioGlobal) {
        this.creaCalendarioGlobal = creaCalendarioGlobal;
    }

    public CalendarioGlobalDTO getActualizarCalendarioGlobal() {
        return actualizarCalendarioGlobal;
    }

    public void setActualizarCalendarioGlobal(CalendarioGlobalDTO actualizarCalendarioGlobal) {
        this.actualizarCalendarioGlobal = actualizarCalendarioGlobal;
    }

    public boolean isMostrarVistaPrincipal() {
        return mostrarVistaPrincipal;
    }

    public void setMostrarVistaPrincipal(boolean mostrarVistaPrincipal) {
        this.mostrarVistaPrincipal = mostrarVistaPrincipal;
    }

    public boolean isMostrarVistaCrear() {
        return mostrarVistaCrear;
    }

    public void setMostrarVistaCrear(boolean mostrarVistaCrear) {
        this.mostrarVistaCrear = mostrarVistaCrear;
    }

    public boolean isMostrarVistaActualizar() {
        return mostrarVistaActualizar;
    }

    public void setMostrarVistaActualizar(boolean mostrarVistaActualizar) {
        this.mostrarVistaActualizar = mostrarVistaActualizar;
    }

    /**
     * @return the listaCalendarioGlobal
     */
    public List<CalendarioGlobalDTO> getListaCalendarioGlobal() {
        return listaCalendarioGlobal;
    }

    /**
     * @param listaCalendarioGlobal
     *            the listaCalendarioGlobal to set
     */
    public void setListaCalendarioGlobal(List<CalendarioGlobalDTO> listaCalendarioGlobal) {
        this.listaCalendarioGlobal = listaCalendarioGlobal;
    }

    /**
     * @return the idCalendarioGlobal
     */
    public Integer getIdCalendarioGlobal() {
        return idCalendarioGlobal;
    }

    /**
     * @param idCalendarioGlobal
     *            the idCalendarioGlobal to set
     */
    public void setIdCalendarioGlobal(Integer idCalendarioGlobal) {
        this.idCalendarioGlobal = idCalendarioGlobal;
    }

}
