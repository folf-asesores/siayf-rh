/**
 * 
 */
package mx.gob.saludtlax.rh.seguridad.administracionmodulo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.modulos.ModuloDTO;
import mx.gob.saludtlax.rh.modulos.Modulos;
import mx.gob.saludtlax.rh.util.JSFUtils;

/**
 * @author Eduardo Mex
 *
 */
@ManagedBean(name = "administracionModulo")
@ViewScoped
public class AdministracionModuloController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1772080992883687350L;

	@Inject
	private Modulos modulos;

	private AdministraciónModuloView view;

	@PostConstruct
	public void init() {
		this.view = new AdministraciónModuloView();
		cargarListaModulos();
	}

	public void cargarListaModulos() {
		List<ModuloDTO> moduloTemp = modulos.listaModulos();

		if (!moduloTemp.isEmpty()) {
			this.view.setListaModulo(moduloTemp);
		} else {
			this.view.setListaModulo(new ArrayList<ModuloDTO>());
		}
	}

	public void eliminarModulo(Integer idModulo) {

		Boolean res = modulos.eliminarModulo(idModulo);
		if (!res) {
			JSFUtils.warningMessage("",
					"EL registro de Modulo no se puede eliminar ya que se encuentra usado por configuraciones de modulos.");
		}
		cargarListaModulos();
	}

	

	/**
	 * @return the view
	 */
	public AdministraciónModuloView getView() {
		return view;
	}

	/**
	 * @param view
	 *            the view to set
	 */
	public void setView(AdministraciónModuloView view) {
		this.view = view;
	}

}
