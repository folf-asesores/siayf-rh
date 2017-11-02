/*
 * NominaMandoMedioView.java
 * Creado el 29/Nov/2016 6:15:21 PM
 * 
 */
package mx.gob.saludtlax.rh.nomina.mandosmedios;

import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;

/**
 * Esta clase ayuda al managed bean {@link NominaMandoMedioController}, contiene
 * las instancias de los componetes que conforman la vista.
 * 
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class NominaMandoMedioView {
    
    private List<NominaMandoMedioDTO> nominaMandoMedioDtos;
    private Boolean mostrarPanelNuevo;
    private Boolean mostrarPanelEditar;
    private Boolean mostrarDialogoEmpleado;
    private NominaMandoMedioDTO nuevaNominaMandoMedio;
    private NominaMandoMedioDTO nominaMandoMedioSeleccionada;
    private InfoEmpleadoDTO empleadoSeleccionado;
    private List<SelectItem> adscripciones;
    private List<SelectItem> puestos;
    private String consultaEmpleado;
    private List<InfoEmpleadoDTO> empleados;

    /**
     * Crea una nueva instancia de la NominaMandoMedioView e inicializa sus 
     * propiedades.
     */
    public NominaMandoMedioView() {
        nominaMandoMedioDtos = new ArrayList<>();
        mostrarPanelNuevo = false;
        mostrarPanelEditar = false;
        mostrarDialogoEmpleado = false;
        nuevaNominaMandoMedio = new NominaMandoMedioDTO();
        nominaMandoMedioSeleccionada = new NominaMandoMedioDTO();
        empleadoSeleccionado = new InfoEmpleadoDTO();
        consultaEmpleado = "";
        empleados = new ArrayList<>();
        adscripciones = new ArrayList<>();
        puestos = new ArrayList<>();
    }

    /**
     * Get the value of nominaMandoMedioDtos
     *
     * @return the value of nominaMandoMedioDtos
     */
    public List<NominaMandoMedioDTO> getNominaMandoMedioDtos() {
        return nominaMandoMedioDtos;
    }

    /**
     * Set the value of nominaMandoMedioDtos
     *
     * @param nominaMandoMedioDtos new value of nominaMandoMedioDtos
     */
    public void setNominaMandoMedioDtos(List<NominaMandoMedioDTO> nominaMandoMedioDtos) {
        this.nominaMandoMedioDtos = nominaMandoMedioDtos;
    }

    /**
     * Get the value of mostrarPanelNuevo
     *
     * @return the value of mostrarPanelNuevo
     */
    public Boolean getMostrarPanelNuevo() {
        return mostrarPanelNuevo;
    }

    /**
     * Set the value of mostrarPanelNuevo
     *
     * @param mostrarPanelNuevo new value of mostrarPanelNuevo
     */
    public void setMostrarPanelNuevo(Boolean mostrarPanelNuevo) {
        this.mostrarPanelNuevo = mostrarPanelNuevo;
    }

    /**
     * Get the value of mostrarPanelEditar
     *
     * @return the value of mostrarPanelEditar
     */
    public Boolean getMostrarPanelEditar() {
        return mostrarPanelEditar;
    }

    /**
     * Set the value of mostrarPanelEditar
     *
     * @param mostrarPanelEditar new value of mostrarPanelEditar
     */
    public void setMostrarPanelEditar(Boolean mostrarPanelEditar) {
        this.mostrarPanelEditar = mostrarPanelEditar;
    }

    /**
     * Get the value of nuevaNominaMandoMedio
     *
     * @return the value of nuevaNominaMandoMedio
     */
    public NominaMandoMedioDTO getNuevaNominaMandoMedio() {
        return nuevaNominaMandoMedio;
    }

    /**
     * Set the value of nuevaNominaMandoMedio
     *
     * @param nuevaNominaMandoMedio new value of nuevaNominaMandoMedio
     */
    public void setNuevaNominaMandoMedio(NominaMandoMedioDTO nuevaNominaMandoMedio) {
        this.nuevaNominaMandoMedio = nuevaNominaMandoMedio;
    }

    /**
     * Get the value of nominaMandoMedioSeleccionada
     *
     * @return the value of nominaMandoMedioSeleccionada
     */
    public NominaMandoMedioDTO getNominaMandoMedioSeleccionada() {
        return nominaMandoMedioSeleccionada;
    }

    /**
     * Set the value of nominaMandoMedioSeleccionada
     *
     * @param nominaMandoMedioSeleccionada new value of
     * nominaMandoMedioSeleccionada
     */
    public void setNominaMandoMedioSeleccionada(NominaMandoMedioDTO nominaMandoMedioSeleccionada) {
        this.nominaMandoMedioSeleccionada = nominaMandoMedioSeleccionada;
    }

    /**
     * Get the value of empleadoSeleccionado
     *
     * @return the value of empleadoSeleccionado
     */
    public InfoEmpleadoDTO getEmpleadoSeleccionado() {
        return empleadoSeleccionado;
    }

    /**
     * Set the value of empleadoSeleccionado
     *
     * @param empleadoSeleccionado new value of empleadoSeleccionado
     */
    public void setEmpleadoSeleccionado(InfoEmpleadoDTO empleadoSeleccionado) {
        this.empleadoSeleccionado = empleadoSeleccionado;
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
     * Get the value of puestos
     *
     * @return the value of puestos
     */
    public List<SelectItem> getPuestos() {
        return puestos;
    }

    /**
     * Set the value of puestos
     *
     * @param puestos new value of puestos
     */
    public void setPuestos(List<SelectItem> puestos) {
        this.puestos = puestos;
    }

    /**
     * Get the value of mostrarDialogoEmpleado
     *
     * @return the value of mostrarDialogoEmpleado
     */
    public Boolean getMostrarDialogoEmpleado() {
        return mostrarDialogoEmpleado;
    }

    /**
     * Set the value of mostrarDialogoEmpleado
     *
     * @param mostrarDialogoEmpleado new value of mostrarDialogoEmpleado
     */
    public void setMostrarDialogoEmpleado(Boolean mostrarDialogoEmpleado) {
        this.mostrarDialogoEmpleado = mostrarDialogoEmpleado;
    }

    /**
     * Get the value of consultaEmpleado
     *
     * @return the value of consultaEmpleado
     */
    public String getConsultaEmpleado() {
        return consultaEmpleado;
    }

    /**
     * Set the value of consultaEmpleado
     *
     * @param consultaEmpleado new value of consultaEmpleado
     */
    public void setConsultaEmpleado(String consultaEmpleado) {
        this.consultaEmpleado = consultaEmpleado;
    }

    /**
     * Get the value of empleados
     *
     * @return the value of empleados
     */
    public List<InfoEmpleadoDTO> getEmpleados() {
        return empleados;
    }

    /**
     * Set the value of empleados
     *
     * @param empleados new value of empleados
     */
    public void setEmpleados(List<InfoEmpleadoDTO> empleados) {
        this.empleados = empleados;
    }


}
