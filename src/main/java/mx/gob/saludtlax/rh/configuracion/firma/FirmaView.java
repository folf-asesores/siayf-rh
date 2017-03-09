/*
 * FirmaView.java
 * Creado el 15/Nov/2016 7:24:05 PM
 * 
 */
package mx.gob.saludtlax.rh.configuracion.firma;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.faces.model.SelectItem;

/**
 * Esta clase ayuda a la clase {@link FirmaController} manteniendo el estado de 
 * los componentes de la vista.
 * 
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
public class FirmaView {

    private FirmaDTO firma = new FirmaDTO();
    private FirmaDTO firmaSeleccionada = new FirmaDTO();
    private List<SelectItem> adscripciones = new ArrayList<>();
    private boolean mostrarDialogoEditar = false;
    private boolean mostrarDialogoNuevo = false;
    private List<FirmaDTO> firmas;
    private List<FirmaDTO> firmasFiltradas;

    public FirmaView() {
        firmas = Collections.EMPTY_LIST;
        firmasFiltradas = Collections.EMPTY_LIST;
    }

    /**
     * Get the value of adscripciones
     *
     * @return the value of adscripciones
     */
    public List<SelectItem> getAdscripciones() {
        return adscripciones;
    }

    /**
     * Set the value of adscripciones
     *
     * @param adscripciones new value of adscripciones
     */
    public void setAdscripciones(List<SelectItem> adscripciones) {
        this.adscripciones = adscripciones;
    }

    /**
     * Get the value of firma
     *
     * @return the value of firma
     */
    public FirmaDTO getFirma() {
        return firma;
    }

    /**
     * Set the value of firma
     *
     * @param firma new value of firma
     */
    public void setFirma(FirmaDTO firma) {
        this.firma = firma;
    }

    /**
     * Get the value of firmaSeleccionada
     *
     * @return the value of firmaSeleccionada
     */
    public FirmaDTO getFirmaSeleccionada() {
        return firmaSeleccionada;
    }

    /**
     * Set the value of firmaSeleccionada
     *
     * @param firmaSeleccionada new value of firmaSeleccionada
     */
    public void setFirmaSeleccionada(FirmaDTO firmaSeleccionada) {
        this.firmaSeleccionada = firmaSeleccionada;
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
     * Get the value of firmas
     *
     * @return the value of firmas
     */
    public List<FirmaDTO> getFirmas() {
        return firmas;
    }

    /**
     * Set the value of firmas
     *
     * @param firmas new value of firmas
     */
    public void setFirmas(List<FirmaDTO> firmas) {
        this.firmas = firmas;
    }


    /**
     * Get the value of firmasFiltradas
     *
     * @return the value of firmasFiltradas
     */
    public List<FirmaDTO> getFirmasFiltradas() {
        return firmasFiltradas;
    }

    /**
     * Set the value of firmasFiltradas
     *
     * @param firmasFiltradas new value of firmasFiltradas
     */
    public void setFirmasFiltradas(List<FirmaDTO> firmasFiltradas) {
        this.firmasFiltradas = firmasFiltradas;
    }
}
