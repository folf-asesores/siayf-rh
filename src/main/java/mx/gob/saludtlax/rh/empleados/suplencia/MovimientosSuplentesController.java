/**
 * 
 */
package mx.gob.saludtlax.rh.empleados.suplencia;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.util.JSFUtils;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @Since 11/01/2017 11:04:35
 */
@ManagedBean(name = "movimientoSuplente")
@ViewScoped
public class MovimientosSuplentesController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5196335636968958641L;

	@Inject
	private Suplencia suplencia;

	private MovimientosSuplentesView view;

	@PostConstruct
	public void inicio() {
		view = new MovimientosSuplentesView();
		FiltroSuplenciaDTO filtro = new FiltroSuplenciaDTO();
		view.setFiltro(filtro);

	}

	public void buscarSuplente() {
		view.getFiltro().setTipoConsulta(EnumTipoConsultaSuplencia.NOMBRE);
		view.setSuplentes(suplencia.consultarSuplentesPorCriterio(view.getFiltro()));
		if (view.getSuplentes().isEmpty()) {
			JSFUtils.errorMessage("", "No se encontraron resultado con el criterio " + view.getFiltro().getCriterio());
		}

	}

	public void mostrarRegistroMovimiento(Integer idSuplente) {
		view.setMostrarRegistroMovimiento(true);
		view.setSuplenteSeleccionado(suplencia.obtenerSuplentePorId(idSuplente));

	}

	public void seleccionarSuplente(SuplenteDTO suplente) {

	}

	public void seleccionarMovimiento() {
		view.setMostrarVacaciones(false);
		view.setMostrarIncapacidad(false);
		if (view.getMovimiento().getMovimiento().equals("VACACIONES")) {
			view.setMostrarVacaciones(true);
		} else if (view.getMovimiento().getMovimiento().equals("INCAPACIDAD")) {
			view.setMostrarIncapacidad(true);
		}
	}

	public MovimientosSuplentesView getView() {
		return view;
	}

	public void setView(MovimientosSuplentesView view) {
		this.view = view;
	}

}
