/*
 * DividirProductoNominaView.java
 * Creado el 25/Dec/2016 6:09:22 PM
 *
 */

package mx.gob.saludtlax.rh.nomina.productosnomina;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class DividirProductoNominaView implements Serializable {

    private static final long serialVersionUID = 3147479301493255250L;
    private Integer idProductoNomina;
    private String nombreProductoNominaNuevo;
    private List<DividirNominaDTO> productoNomina;
    private List<DividirNominaDTO> productoNominaSeleccionado;
    private List<DividirNominaDTO> productoNominaFiltrados;

    public DividirProductoNominaView() {
        idProductoNomina = 0;
        productoNomina = Collections.EMPTY_LIST;
        productoNominaFiltrados = Collections.EMPTY_LIST;
        productoNominaSeleccionado = Collections.EMPTY_LIST;
    }

    /**
     * Get the value of idProductoNomina
     *
     * @return the value of idProductoNomina
     */
    public Integer getIdProductoNomina() {
        return idProductoNomina;
    }

    /**
     * Set the value of idProductoNomina
     *
     * @param idProductoNomina
     *            new value of idProductoNomina
     */
    public void setIdProductoNomina(Integer idProductoNomina) {
        this.idProductoNomina = idProductoNomina;
    }

    /**
     * Get the value of nombreProductoNominaNuevo
     *
     * @return the value of nombreProductoNominaNuevo
     */
    public String getNombreProductoNominaNuevo() {
        return nombreProductoNominaNuevo;
    }

    /**
     * Set the value of nombreProductoNominaNuevo
     *
     * @param nombreProductoNominaNuevo
     *            new value of nombreProductoNominaNuevo
     */
    public void setNombreProductoNominaNuevo(String nombreProductoNominaNuevo) {
        this.nombreProductoNominaNuevo = nombreProductoNominaNuevo;
    }

    /**
     * Get the value of productoNomina
     *
     * @return the value of productoNomina
     */
    public List<DividirNominaDTO> getProductoNomina() {
        return productoNomina;
    }

    /**
     * Set the value of productoNomina
     *
     * @param productoNomina
     *            new value of productoNomina
     */
    public void setProductoNomina(List<DividirNominaDTO> productoNomina) {
        this.productoNomina = productoNomina;
    }

    /**
     * Get the value of productoNominaSeleccionado
     *
     * @return the value of productoNominaSeleccionado
     */
    public List<DividirNominaDTO> getProductoNominaSeleccionado() {
        return productoNominaSeleccionado;
    }

    /**
     * Set the value of productoNominaSeleccionado
     *
     * @param productoNominaSeleccionado
     *            new value of productoNominaSeleccionado
     */
    public void setProductoNominaSeleccionado(List<DividirNominaDTO> productoNominaSeleccionado) {
        this.productoNominaSeleccionado = productoNominaSeleccionado;
    }

    /**
     * Get the value of productoNominaFiltrados
     *
     * @return the value of productoNominaFiltrados
     */
    public List<DividirNominaDTO> getProductoNominaFiltrados() {
        return productoNominaFiltrados;
    }

    /**
     * Set the value of productoNominaFiltrados
     *
     * @param productoNominaFiltrados
     *            new value of productoNominaFiltrados
     */
    public void setProductoNominaFiltrados(List<DividirNominaDTO> productoNominaFiltrados) {
        this.productoNominaFiltrados = productoNominaFiltrados;
    }

}
