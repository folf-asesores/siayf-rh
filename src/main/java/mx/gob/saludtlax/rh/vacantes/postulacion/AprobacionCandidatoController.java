/**
 * 
 */
package mx.gob.saludtlax.rh.vacantes.postulacion;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.vacantes.seleccion.EnumTipoCandidato;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @Since 24/10/2016 17:02:23
 */
@ManagedBean(name = "aprobacionCandidato")
@ViewScoped
public class AprobacionCandidatoController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1661379576503063774L;

	@Inject
	private Postulacion postulacion;

	private AprobacionCandidatoView view = new AprobacionCandidatoView();

	@PostConstruct
	public void inicio() {
		view.setMostrarPostulacionesDisponibles(true);
		view.setPostulacionesDisponibles(postulacion
				.consultarPostulacionesDisponibles());
	}

	public void mostrarCandidatosPostulados(InfoPostulacionDTO postulacionInfo) {

		view.setCandidatosPostulados(postulacion
				.consultarCandidatosPostulacion(postulacionInfo
						.getIdPostulacion()));
		view.setPostulacionSeleccionada(postulacionInfo);
		view.setMostrarCandidatosPostulados(true);
		view.setMostrarPostulacionesDisponibles(false);
	}

	public void ocultarCandidatosPostulados() {
		view.setMostrarCandidatosPostulados(false);
		view.setMostrarPostulacionesDisponibles(true);
	}

	public void visualizarDetalleSeleccionado(InfoCandidatoDTO candidato) {

		view.setCandidatoSeleccionado(candidato);
		view.setMostrarDetalleSeleccionado(true);

		if (candidato.getIdTipoCandidato() == EnumTipoCandidato.ASPIRANTE) {
			HttpServletRequest request = (HttpServletRequest) FacesContext
					.getCurrentInstance().getExternalContext().getRequest();
			HttpSession httpSession = request.getSession(false);
			httpSession.setAttribute("idAspirante", candidato.getIdContexto());
			view.setConsulta("consultaAspirante.xhtml");
		} else if (candidato.getIdTipoCandidato() == EnumTipoCandidato.EMPLEADO) {
			HttpServletRequest request = (HttpServletRequest) FacesContext
					.getCurrentInstance().getExternalContext().getRequest();
			HttpSession httpSession = request.getSession(false);
			httpSession.setAttribute("idEmpleado", candidato.getIdContexto());
			view.setConsulta("consultaEmpleado.xhtml");
		}

	}

	public void aprobarCandidato() {
		try {
			postulacion.aprobarCandidatoPostulacion(view
					.getPostulacionSeleccionada().getIdPostulacion(), view
					.getCandidatoSeleccionado().getIdCandidatoPostulado());
			view.setMostrarDetalleSeleccionado(false);
			view.setMostrarPostulacionesDisponibles(true);
			view.setMostrarCandidatosPostulados(false);
			view.setPostulacionesDisponibles(postulacion
					.consultarPostulacionesDisponibles());
			
			JSFUtils.infoMessage("", "El candidato ha sido aprobado con éxito");
		} catch (ReglaNegocioException exception) {
			JSFUtils.errorMessageEspecifico("errorAprobacion", "",
					exception.getMessage());
		}
	}

	public void ocultarDetalleSeleccionado() {
		view.setMostrarDetalleSeleccionado(false);
	}

	public AprobacionCandidatoView getView() {
		return view;
	}

	public void setView(AprobacionCandidatoView view) {
		this.view = view;
	}

}
