package mx.gob.saludtlax.rh.configuracion.fuenteFinanciamiento;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.util.JSFUtils;

@ManagedBean(name = "fuenteFinanciamiento")
@ViewScoped
public class FuenteFinanciamientoController {

	private FuenteFinanciamientoView view;
	@Inject
	private FuenteFinanciamientoEJB ejb;

	@PostConstruct
	public void initFuenteFinanciamiento() {
		view = new FuenteFinanciamientoView();
		view.setListFuenteFinanciamiento(ejb.obtenerFuenteFinanciamientoLista());

	}

	// <Inicia Procesos de Fuente Financiamiento>

	public void mostrarRegistroFuente() {
		view.setMostrarRegistroFuenteFinanciamiento(true);
	}

	public void ocultarRegistroFuente() {
		view.setMostrarRegistroFuenteFinanciamiento(false);
		FuenteFinanciamientoDTO dto = new FuenteFinanciamientoDTO();
		view.setNuevaFuenteFinanciamiento(dto);
	}

	public void eliminarFuenteFinanciamiento(Integer idFuenteFinanciamiento, String fuente) {
		try {
			ejb.eliminarFuenteFinanciamiento(idFuenteFinanciamiento);
			JSFUtils.infoMessage("",
					"La fuente de financiamiento " + fuente.toLowerCase() + "ha sido eliminada con éxito.");
			view.setListFuenteFinanciamiento(ejb.obtenerFuenteFinanciamientoLista());

		} catch (ReglaNegocioException exception) {
			JSFUtils.errorMessage("", exception.getMessage());
		}

	}

	public void guardarFuenteFinanciamiento() {
		try {
			ejb.crearFuenteFinanciamiento(view.getNuevaFuenteFinanciamiento());
			view.setMostrarRegistroFuenteFinanciamiento(false);
			JSFUtils.infoMessage("", "Se ha registrado con éxito la fuente de financiamiento "
					+ view.getNuevaFuenteFinanciamiento().getDescripcion().toLowerCase());
			FuenteFinanciamientoDTO dto = new FuenteFinanciamientoDTO();
			view.setNuevaFuenteFinanciamiento(dto);
			view.setListFuenteFinanciamiento(ejb.obtenerFuenteFinanciamientoLista());

		} catch (ReglaNegocioException exception) {
			JSFUtils.errorMessageEspecifico("error", "", exception.getMessage());
		}

	}

	public FuenteFinanciamientoView getView() {
		return view;
	}

}