/*
 *
 */

package mx.gob.saludtlax.rh.nomina.historialpago;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.FiltroDTO;
import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;

/**
 * @author Eduardo Mex
 *
 */
public class HistorialPagoView implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3447587162915990726L;

    private FiltroDTO filtro = new FiltroDTO();

    // Listas principales
    private List<InfoEmpleadoDTO> empleados = new ArrayList<>();

    // Renderizados consulta
    private boolean mostrarResultadoConsulta = false;

    /**
     * @return the filtro
     */
    public FiltroDTO getFiltro() {
        return filtro;
    }

    /**
     * @param filtro
     *            the filtro to set
     */
    public void setFiltro(FiltroDTO filtro) {
        this.filtro = filtro;
    }

    /**
     * @return the empleados
     */
    public List<InfoEmpleadoDTO> getEmpleados() {
        return empleados;
    }

    /**
     * @param empleados
     *            the empleados to set
     */
    public void setEmpleados(List<InfoEmpleadoDTO> empleados) {
        this.empleados = empleados;
    }

    /**
     * @return the mostrarResultadoConsulta
     */
    public boolean isMostrarResultadoConsulta() {
        return mostrarResultadoConsulta;
    }

    /**
     * @param mostrarResultadoConsulta
     *            the mostrarResultadoConsulta to set
     */
    public void setMostrarResultadoConsulta(boolean mostrarResultadoConsulta) {
        this.mostrarResultadoConsulta = mostrarResultadoConsulta;
    }

}
