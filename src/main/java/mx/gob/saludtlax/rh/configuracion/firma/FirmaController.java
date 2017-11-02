/*
 * FirmaController.java
 * Creado el 15/Nov/2016 7:00:58 PM
 * 
 */
package mx.gob.saludtlax.rh.configuracion.firma;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import mx.gob.saludtlax.rh.catalogos.Catalogo;
import mx.gob.saludtlax.rh.util.SelectItemsUtil;

/**
 * Este ManagedBean controla el comportamiento de la vista 
 * /contenido/configuracion/firma.xhtml.
 * 
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Named(value = "firmaController")
@ViewScoped
public class FirmaController implements Serializable {

    private static final long serialVersionUID = 7760290884357673767L;
    
    @Inject private Catalogo catalogoBean;
    @Inject private Firma firmaBean;

    private FirmaView view;

    /**
     * Creates a new instance of FirmaController
     */
    public FirmaController() {
        view = new FirmaView();
    }
    
    /**
     * Completa la inicialización del ManagedBean.
     */
    @PostConstruct
    public void init() {
        view.setAdscripciones(SelectItemsUtil.listaCatalogos(catalogoBean.consultarAdscripciones()));
        view.setFirmas(firmaBean.consultarTodas());
    }

    /**
     * Permite guardar una nueva firma.
     * 
     * @param actionEvent el evento de guardado.
     */
    public void guardar(ActionEvent actionEvent) {
        firmaBean.crear(view.getFirma());
        limpiar(actionEvent);
    }

    /**
     * Permite actualizar una firma.
     * 
     * @param actionEvent el evento de guardado.
     */
    public void actualizar(ActionEvent actionEvent) {
        firmaBean.actualizar(view.getFirmaSeleccionada());
        limpiar(actionEvent);
    }

    /**
     * Permite eliminar una firma existente.
     * @param idFirma 
     */
    public void eliminar(Integer idFirma) {
        firmaBean.eliminar(idFirma);
        limpiar(null);
    }
    
    /**
     * 
     * @param actionEvent
     */
    public void mostrarDialogoNuevo(ActionEvent actionEvent) {
        view.setFirma(new FirmaDTO());
        view.setMostrarDialogoNuevo(true);
    }

    /**
     * Permite mostrar el cuadro de dialogo para poder editar la información de 
     * una firma.
     * 
     * @param firma la firma que se mostrará en el dialogo de edición.
     */
    public void mostrarDialogoEditar(FirmaDTO firma) {
        view.setFirmaSeleccionada(firma);
        view.setMostrarDialogoEditar(true);
    }
    
    /**
     * Permite limpiar las instancias para los cuadros de dialogos.
     * 
     * @param actionEvent el evento que genera la acción.
     */
    public void limpiar(ActionEvent actionEvent) {
        view.setFirmaSeleccionada(new FirmaDTO());
        view.setFirma(new FirmaDTO());
        view.setMostrarDialogoEditar(false);
        view.setMostrarDialogoNuevo(false);
        view.setFirmas(firmaBean.consultarTodas());
    }

    /**
     * Get the value of view
     *
     * @return the value of view
     */
    public FirmaView getView() {
        return view;
    }

    /**
     * Set the value of view
     *
     * @param view new value of view
     */
    public void setView(FirmaView view) {
        this.view = view;
    }

}
