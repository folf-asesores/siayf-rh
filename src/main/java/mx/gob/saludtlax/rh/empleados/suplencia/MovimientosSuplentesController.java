/**
 * 
 */
package mx.gob.saludtlax.rh.empleados.suplencia;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.seguridad.ConfiguracionConst;
import mx.gob.saludtlax.rh.seguridad.usuario.UsuarioDTO;
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
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		HttpSession httpSession = request.getSession(false);
		UsuarioDTO usuario = (UsuarioDTO) httpSession.getAttribute(ConfiguracionConst.SESSION_ATRIBUTE_LOGGED_USER);
		view.getMovimiento().setIdUsuario(usuario.getIdUsuario());

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
		view.getMovimiento().setIdSuplente(view.getSuplenteSeleccionado().getIdSuplente());

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

	public void registrarMovimiento() {
		try {
			suplencia.crearMovimientoSuplente(view.getMovimiento());
			view.setMostrarRegistroMovimiento(false);
			JSFUtils.infoMessage("", "¡El movimiento se ha registrado con éxito!");
		} catch (ReglaNegocioException | ValidacionException exception) {
			JSFUtils.errorMessageEspecifico("error", "", exception.getMessage());
		}
	}

	public void ocultarMovimiento() {
		view.setMostrarRegistroMovimiento(false);
	}

	public MovimientosSuplentesView getView() {
		return view;
	}

	public void setView(MovimientosSuplentesView view) {
		this.view = view;
	}

}
