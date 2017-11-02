/*
 *  NotificadorErroresController.java
 *  Creado el Jun 16, 2016 4:14:27 PM
 * 
 */
package mx.gob.saludtlax.rh.configuracion.notificadorerror;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Este ManagedBean controla la vista
 * /contenido/configuracion/notificacion-de-errores.xhtml.
 * 
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Named(value = "notificadorErroresController")
@ViewScoped
public class NotificadorErroresController implements Serializable {

    private static final long serialVersionUID = -6655950760281785758L;

    @Inject private NotificadorErrores notificadorErroresEJB;

    private NotificadorErroresView view;

    /**
     * Creates a new instance of NotificadorErroresController
     */
    public NotificadorErroresController() {
        view = new NotificadorErroresView();
    }
    
    @PostConstruct
    public void init() {
        view.setCorreosNotificacion(notificadorErroresEJB.consutarCorreosNotificacion());
    }

    public void guardar() {
        notificadorErroresEJB.crear(view.getNuevoCorreoNotificacion());
        view.setMostrarDialogoNuevo(false);
        view.setCorreosNotificacion(notificadorErroresEJB.consutarCorreosNotificacion());
    }

    public void actualizar() {
        notificadorErroresEJB.actualizar(view.getEditarCorreoNotificacion());
        view.setMostrarDialogoEditar(false);
        view.setCorreosNotificacion(notificadorErroresEJB.consutarCorreosNotificacion());
    }

    public void eliminar(int idCorreoNotificacion) {
        notificadorErroresEJB.eliminar(idCorreoNotificacion);
        view.setCorreosNotificacion(notificadorErroresEJB.consutarCorreosNotificacion());
    }

    public void mostrarDialogoNuevo() {
        view.setMostrarDialogoNuevo(true);
    }

    public void ocultarDialogoNuevo() {
        view.setMostrarDialogoNuevo(false);
        view.setNuevoCorreoNotificacion(new CorreoNotificacionDTO());
    }

    public void mostrarDialogoEditar(CorreoNotificacionDTO correoNotificacion) {
        view.setMostrarDialogoEditar(true);
        view.setEditarCorreoNotificacion(correoNotificacion);
    }

    public void ocultarDialogoEditar() {
        view.setMostrarDialogoEditar(false);
        view.setNuevoCorreoNotificacion(new CorreoNotificacionDTO());
    }

    /**
     * Get the value of view
     *
     * @return the value of view
     */
    public NotificadorErroresView getView() {
        return view;
    }

    /**
     * Set the value of view
     *
     * @param view new value of view
     */
    public void setView(NotificadorErroresView view) {
        this.view = view;
    }

}
