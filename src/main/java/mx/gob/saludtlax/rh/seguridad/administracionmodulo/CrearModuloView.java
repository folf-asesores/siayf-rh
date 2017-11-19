/**
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
 * @author Eduardo Mex
 *
 */
public class CrearModuloView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1697882452647954474L;

	private List<AreaDTO> listaAreas = new ArrayList<AreaDTO>();

	private ModuloDTO crearModulo = new ModuloDTO();

	private AccionDTO accion = new AccionDTO();
	
	private boolean mostrarTablaAccciones = true;
	
	private boolean mostrarFormularioAccion = false;

	

	/**
	 * @return the crearModulo
	 */
	public ModuloDTO getCrearModulo() {
		return crearModulo;
	}

	/**
	 * @param crearModulo
	 *            the crearModulo to set
	 */
	public void setCrearModulo(ModuloDTO crearModulo) {
		this.crearModulo = crearModulo;
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
	 * @param mostrarTablaAccciones the mostrarTablaAccciones to set
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
	 * @param mostrarFormularioAccion the mostrarFormularioAccion to set
	 */
	public void setMostrarFormularioAccion(boolean mostrarFormularioAccion) {
		this.mostrarFormularioAccion = mostrarFormularioAccion;
	}

}
