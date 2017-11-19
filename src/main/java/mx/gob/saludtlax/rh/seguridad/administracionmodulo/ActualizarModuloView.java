/*
 *
 */

package mx.gob.saludtlax.rh.seguridad.administracionmodulo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.gob.saludtlax.rh.acciones.AccionDTO;
import mx.gob.saludtlax.rh.areas.AreaDTO;
import mx.gob.saludtlax.rh.modulos.ModuloDTO;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 *
 */
public class ActualizarModuloView implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4738618990853624740L;

    private Integer idModulo;

    private ModuloDTO actualizarModulo = new ModuloDTO();

    private List<AreaDTO> listaAreas = new ArrayList<>();

    private AccionDTO accion = new AccionDTO();

    private boolean mostrarTablaAccciones = true;

    private boolean mostrarFormularioAccion = false;

    /**
     * @return the idModulo
     */
    public Integer getIdModulo() {
        return idModulo;
    }

    /**
     * @param idModulo
     *            the idModulo to set
     */
    public void setIdModulo(Integer idModulo) {
        this.idModulo = idModulo;
    }

    /**
     * @return the actualizarModulo
     */
    public ModuloDTO getActualizarModulo() {
        return actualizarModulo;
    }

    /**
     * @param actualizarModulo
     *            the actualizarModulo to set
     */
    public void setActualizarModulo(ModuloDTO actualizarModulo) {
        this.actualizarModulo = actualizarModulo;
    }

    /**
     * @return the listaAreas
     */
    public List<AreaDTO> getListaAreas() {
        return listaAreas;
    }

    /**
     * @param listaAreas
     *            the listaAreas to set
     */
    public void setListaAreas(List<AreaDTO> listaAreas) {
        this.listaAreas = listaAreas;
    }

    /**
     * @return the accion
     */
    public AccionDTO getAccion() {
        return accion;
    }

    /**
     * @param accion
     *            the accion to set
     */
    public void setAccion(AccionDTO accion) {
        this.accion = accion;
    }

    /**
     * @return the mostrarTablaAccciones
     */
    public boolean isMostrarTablaAccciones() {
        return mostrarTablaAccciones;
    }

    /**
     * @param mostrarTablaAccciones
     *            the mostrarTablaAccciones to set
     */
    public void setMostrarTablaAccciones(boolean mostrarTablaAccciones) {
        this.mostrarTablaAccciones = mostrarTablaAccciones;
    }

    /**
     * @return the mostrarFormularioAccion
     */
    public boolean isMostrarFormularioAccion() {
        return mostrarFormularioAccion;
    }

    /**
     * @param mostrarFormularioAccion
     *            the mostrarFormularioAccion to set
     */
    public void setMostrarFormularioAccion(boolean mostrarFormularioAccion) {
        this.mostrarFormularioAccion = mostrarFormularioAccion;
    }

}
