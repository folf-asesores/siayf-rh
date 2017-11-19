/*
 * PagoGeneralView.java
 * Creado el 25/Dic/2016 6:09:22 PM
 *
 */

package mx.gob.saludtlax.rh.nomina.productosnomina;

import java.io.Serializable;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class PagoGeneralView implements Serializable {
    private static final long serialVersionUID = 3147479301493255250L;

    private Integer idProductoNomina;
    private String rfcTexto;

    //    private String nombreProductoNominaNuevo;
    //    private List<DividirNominaDTO> productoNomina;
    //    private List<DividirNominaDTO> productoNominaSeleccionado;
    //    private List<DividirNominaDTO> productoNominaFiltrados;

    public PagoGeneralView() {
        idProductoNomina = 0;
        rfcTexto = "";
        //        productoNomina = Collections.EMPTY_LIST;
        //        productoNominaFiltrados = Collections.EMPTY_LIST;
        //        productoNominaSeleccionado = Collections.EMPTY_LIST;
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
     * Get the value of rfcTexto
     *
     * @return the value of rfcTexto
     */
    public String getRfcTexto() {
        return rfcTexto;
    }

    /**
     * Set the value of rfcTexto
     *
     * @param rfcTexto
     *            new value of rfcTexto
     */
    public void setRfcTexto(String rfcTexto) {
        this.rfcTexto = rfcTexto;
    }

    //    public String getNombreProductoNominaNuevo() {
    //        return nombreProductoNominaNuevo;
    //    }

    //    public void setNombreProductoNominaNuevo(String nombreProductoNominaNuevo) {
    //        this.nombreProductoNominaNuevo = nombreProductoNominaNuevo;
    //    }

    //    public List<DividirNominaDTO> getProductoNomina() {
    //        return productoNomina;
    //    }

    //    public void setProductoNomina(List<DividirNominaDTO> productoNomina) {
    //        this.productoNomina = productoNomina;
    //    }

    //    public List<DividirNominaDTO> getProductoNominaSeleccionado() {
    //        return productoNominaSeleccionado;
    //    }

    //    public void setProductoNominaSeleccionado(List<DividirNominaDTO> productoNominaSeleccionado) {
    //        this.productoNominaSeleccionado = productoNominaSeleccionado;
    //    }

    //    public List<DividirNominaDTO> getProductoNominaFiltrados() {
    //        return productoNominaFiltrados;
    //    }

    //    public void setProductoNominaFiltrados(List<DividirNominaDTO> productoNominaFiltrados) {
    //        this.productoNominaFiltrados = productoNominaFiltrados;
    //    }

}
