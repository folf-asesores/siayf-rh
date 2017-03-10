/**
 * 
 */
package mx.gob.saludtlax.rh.seguridad.configuracionmoduloaccion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.modulos.ConfiguracionModuloAccion;
import mx.gob.saludtlax.rh.modulos.ConfiguracionModuloAccionDTO;
import mx.gob.saludtlax.rh.util.JSFUtils;

/**
 * @author Eduardo Mex
 *
 */
@ManagedBean(name = "configModuloAccion")
@ViewScoped
public class ConfigModuloAccionController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5533846908901889903L;

	@Inject
	ConfiguracionModuloAccion configuracionModuloAccion;

	private ConfigModuloAccionView view;

	@PostConstruct
	public void init() {
		this.view = new ConfigModuloAccionView();

		List<ConfiguracionModuloAccionDTO> configuracionModuloAccionTemp = new ArrayList<>();
		configuracionModuloAccionTemp = configuracionModuloAccion.obtenerListaConfiguracionModuloAccionDTO();

		this.view.getListaConfiguracionModuloAccion().clear();
		this.view.getListaConfiguracionModuloAccion().addAll(configuracionModuloAccionTemp);

	}

	public void eliminarConfiguracion(Integer idConfiguracionModuloAccion) {
		configuracionModuloAccion.eliminar(idConfiguracionModuloAccion);
		init();
		JSFUtils.infoMessage("Configuración Modulo Acción: ", "¡Se elimino Correctamente!");
	}

	/************* Getters and Setters *******/

	public ConfigModuloAccionView getView() {
		return view;
	}

	public void setView(ConfigModuloAccionView view) {
		this.view = view;
	}

}
