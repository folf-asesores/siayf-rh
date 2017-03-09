/*
 * NominaMandoMedioController.java
 * Creado el 29/Nov/2016 2:52:06 PM
 * 
 */
package mx.gob.saludtlax.rh.nomina.mandosmedios;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.EnumTipoFiltro;
import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.FiltroDTO;
import mx.gob.saludtlax.rh.catalogos.Catalogo;
import mx.gob.saludtlax.rh.empleados.administracion.Empleado;
import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;
import mx.gob.saludtlax.rh.util.SelectItemsUtil;

/**
 * Este managed bean controla el comportamiento de la vista:
 * /contenido/nomina/nominaMandoMedio.xhtml
 * 
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
@Named(value = "nominaMandoMedioController")
@ViewScoped
public class NominaMandoMedioController implements Serializable {

    private static final long serialVersionUID = -5787077686465336832L;

    @Inject private Catalogo catalogoBean;
    @Inject private Empleado empleadoBean;
    @Inject private NominaMandoMedio nominaMandoMedioBean;
    
    private NominaMandoMedioView view;

    /**
     * Creates a new instance of NominaMandoMedioController
     */
    public NominaMandoMedioController() {
        view = new NominaMandoMedioView();
    }
    
    /**
     * Termina de inicializar los componentes de la vista así como también 
     * realiza las configuraciones necesarias para que funciones la vista.
     */
    @PostConstruct
    public void init() {
        view.setNominaMandoMedioDtos(nominaMandoMedioBean.consultarTodos());
        view.setPuestos(SelectItemsUtil
                .listaCatalogos(catalogoBean.listaPuestos()));
        view.setAdscripciones(SelectItemsUtil
                .listaCatalogos(catalogoBean.consultarAdscripciones()));
    }
    /**
     * 
     * @param actionEvent 
     */
    public void actualizar(ActionEvent actionEvent) {
        nominaMandoMedioBean.actualizar(view.getNominaMandoMedioSeleccionada());
        limpiar(actionEvent);
        view.setNominaMandoMedioDtos(nominaMandoMedioBean.consultarTodos());
    }
    
    /**
     * Permite guardar una nueva firma.
     * 
     * @param actionEvent el evento de guardado.
     */
    public void guardar(ActionEvent actionEvent) {
        nominaMandoMedioBean.crear(view.getNuevaNominaMandoMedio());
        limpiar(actionEvent);
        view.setNominaMandoMedioDtos(nominaMandoMedioBean.consultarTodos());
    }
    
    /**
     * Permite eliminar la nomina de un mando medio existente.
     * 
     * @param idNominaMandoMedio 
     */
    public void eliminar(Integer idNominaMandoMedio) {
        nominaMandoMedioBean.eliminar(idNominaMandoMedio);
        view.setNominaMandoMedioDtos(nominaMandoMedioBean.consultarTodos());
    }

    public void seleccionarEmpleado(InfoEmpleadoDTO info) {
        view.setEmpleadoSeleccionado(info);
        view.getNuevaNominaMandoMedio().setIdEmpleado(info.getIdEmpleado());
        view.getNuevaNominaMandoMedio().setNombreEmpleado(info.getNombre());
        view.setMostrarDialogoEmpleado(false);
    }
    
    /**
     * 
     * @param actionEvent
     */
    public void mostrarPanelNuevo(ActionEvent actionEvent) {
        view.setNuevaNominaMandoMedio(new NominaMandoMedioDTO());
        view.setMostrarPanelNuevo(true);
    }

    /**
     * 
     * @param nominaMandoMedio 
     */
    public void mostrarDialogoEditar(NominaMandoMedioDTO nominaMandoMedio) {
        view.setNominaMandoMedioSeleccionada(nominaMandoMedio);
        view.setMostrarPanelEditar(true);
    }
    
    /**
     * Permite limpiar las instancias para los cuadros de dialogos.
     * 
     * @param actionEvent el evento que genera la acción.
     */
    public void limpiar(ActionEvent actionEvent) {
        view.setNuevaNominaMandoMedio(new NominaMandoMedioDTO());
        view.setNominaMandoMedioSeleccionada(new NominaMandoMedioDTO());
        view.setMostrarPanelNuevo(false);
        view.setMostrarPanelEditar(false);
        view.setMostrarDialogoEmpleado(false);
    }
    
    public void obtenerEmpleados() {
        view.setMostrarDialogoEmpleado(true);
        FiltroDTO filtroDTO = new FiltroDTO(EnumTipoFiltro.NOMBRE_RFC_CURP, view.getConsultaEmpleado());
        List<InfoEmpleadoDTO> empleados = empleadoBean.consultaEmpleado(filtroDTO);
        view.setEmpleados(empleados);
    }
    
    /**
     * Get the value of view
     *
     * @return the value of view
     */
    public NominaMandoMedioView getView() {
        return view;
    }

    /**
     * Set the value of view
     *
     * @param view new value of view
     */
    public void setView(NominaMandoMedioView view) {
        this.view = view;
    }

}
