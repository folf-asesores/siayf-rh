/**
 * 
 */
package mx.gob.saludtlax.rh.seguridad.configuracionmoduloaccion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.DualListModel;

import mx.gob.saludtlax.rh.acciones.AccionDTO;
import mx.gob.saludtlax.rh.modulos.ConfiguracionModuloAccionDTO;
import mx.gob.saludtlax.rh.modulos.ModuloDTO;

/**
 * @author Eduardo Mex
 *
 */

public class CrearConfigModuloAccionView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1365363619019730301L;

	private DualListModel<AccionDTO> pikListAcciones = new DualListModel<AccionDTO>();

	private List<ModuloDTO> listaModulos = new ArrayList<ModuloDTO>();

	private List<AccionDTO> listaAcciones = new ArrayList<AccionDTO>();

	private ConfiguracionModuloAccionDTO configuracionModuloAccionDTONew = new ConfiguracionModuloAccionDTO();

	

	/**
	 * @return the configuracionModuloAccionDTONew
	 */
	public ConfiguracionModuloAccionDTO getConfiguracionModuloAccionDTONew() {
		return configuracionModuloAccionDTONew;
	}

	/**
	 * @param configuracionModuloAccionDTONew
	 *            the configuracionModuloAccionDTONew to set
	 */
	public void setConfiguracionModuloAccionDTONew(ConfiguracionModuloAccionDTO configuracionModuloAccionDTONew) {
		this.configuracionModuloAccionDTONew = configuracionModuloAccionDTONew;
	}

	/**
	 * @return the listaModulos
	 */
	public List<ModuloDTO> getListaModulos() {
		return listaModulos;
	}

	/**
	 * @param listaModulos
	 *            the listaModulos to set
	 */
	public void setListaModulos(List<ModuloDTO> listaModulos) {
		this.listaModulos = listaModulos;
	}

	/**
	 * @return the listaAcciones
	 */
	public List<AccionDTO> getListaAcciones() {
		return listaAcciones;
	}

	/**
	 * @param listaAcciones
	 *            the listaAcciones to set
	 */
	public void setListaAcciones(List<AccionDTO> listaAcciones) {
		this.listaAcciones = listaAcciones;
	}

	/**
	 * @return the pikListAcciones
	 */
	public DualListModel<AccionDTO> getPikListAcciones() {
		return pikListAcciones;
	}

	/**
	 * @param pikListAcciones
	 *            the pikListAcciones to set
	 */
	public void setPikListAcciones(DualListModel<AccionDTO> pikListAcciones) {
		this.pikListAcciones = pikListAcciones;
	}

}
