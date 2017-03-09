/*
 *  NotificadorErroresView.java
 *  Creado el Jun 16, 2016 4:14:42 PM
 * 
 */
package mx.gob.saludtlax.rh.configuracion.notificadorerror;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
public class NotificadorErroresView implements Serializable {

    private static final long serialVersionUID = 7761723696104331831L;

    private List<CorreoNotificacionDTO> correosNotificacion;
    private CorreoNotificacionDTO nuevoCorreoNotificacion;
    private CorreoNotificacionDTO editarCorreoNotificacion;
    private boolean mostrarDialogoNuevo;
    private boolean mostrarDialogoEditar;

    public NotificadorErroresView() {
        correosNotificacion = new ArrayList<>();
        mostrarDialogoNuevo = false;
        mostrarDialogoEditar = false;
        nuevoCorreoNotificacion = new CorreoNotificacionDTO();
        editarCorreoNotificacion = new CorreoNotificacionDTO();
    }

    /**
     * Get the value of correosNotificacion
     *
     * @return the value of correosNotificacion
     */
    public List<CorreoNotificacionDTO> getCorreosNotificacion() {
        return correosNotificacion;
    }

    /**
     * Set the value of correosNotificacion
     *
     * @param correosNotificacion new value of correosNotificacion
     */
    public void setCorreosNotificacion(List<CorreoNotificacionDTO> correosNotificacion) {
        this.correosNotificacion = correosNotificacion;
    }

    /**
     * Get the value of nuevoCorreoNotificacion
     *
     * @return the value of nuevoCorreoNotificacion
     */
    public CorreoNotificacionDTO getNuevoCorreoNotificacion() {
        return nuevoCorreoNotificacion;
    }

    /**
     * Set the value of nuevoCorreoNotificacion
     *
     * @param nuevoCorreoNotificacion new value of nuevoCorreoNotificacion
     */
    public void setNuevoCorreoNotificacion(CorreoNotificacionDTO nuevoCorreoNotificacion) {
        this.nuevoCorreoNotificacion = nuevoCorreoNotificacion;
    }

    /**
     * Get the value of editarCorreoNotificacion
     *
     * @return the value of editarCorreoNotificacion
     */
    public CorreoNotificacionDTO getEditarCorreoNotificacion() {
        return editarCorreoNotificacion;
    }

    /**
     * Set the value of editarCorreoNotificacion
     *
     * @param editarCorreoNotificacion new value of editarCorreoNotificacion
     */
    public void setEditarCorreoNotificacion(CorreoNotificacionDTO editarCorreoNotificacion) {
        this.editarCorreoNotificacion = editarCorreoNotificacion;
    }

    /**
     * Get the value of mostrarDialogoNuevo
     *
     * @return the value of mostrarDialogoNuevo
     */
    public boolean getMostrarDialogoNuevo() {
        return mostrarDialogoNuevo;
    }

    /**
     * Set the value of mostrarDialogoNuevo
     *
     * @param mostrarDialogoNuevo new value of mostrarDialogoNuevo
     */
    public void setMostrarDialogoNuevo(boolean mostrarDialogoNuevo) {
        this.mostrarDialogoNuevo = mostrarDialogoNuevo;
    }

    /**
     * Get the value of mostrarDialogoEditar
     *
     * @return the value of mostrarDialogoEditar
     */
    public boolean getMostrarDialogoEditar() {
        return mostrarDialogoEditar;
    }

    /**
     * Set the value of mostrarDialogoEditar
     *
     * @param mostrarDialogoEditar new value of mostrarDialogoEditar
     */
    public void setMostrarDialogoEditar(boolean mostrarDialogoEditar) {
        this.mostrarDialogoEditar = mostrarDialogoEditar;
    }

}
