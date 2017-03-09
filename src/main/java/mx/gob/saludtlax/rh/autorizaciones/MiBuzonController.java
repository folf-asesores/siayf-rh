/**
 * 
 */
package mx.gob.saludtlax.rh.autorizaciones;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.notificacion.Modulo;
import mx.gob.saludtlax.rh.seguridad.ConfiguracionConst;
import mx.gob.saludtlax.rh.seguridad.usuario.UsuarioDTO;
import mx.gob.saludtlax.rh.util.JSFUtils;

/**
 * @author Leila Schiaffini Ehuan
 * @since 11/08/2016 11:57:34
 * 
 */
@ViewScoped
@ManagedBean(name = "miBuzon")
public class MiBuzonController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2710385260690576322L;

	@Inject
	private Autorizaciones autorizaciones;

	private MiBuzonView view = new MiBuzonView();

	@PostConstruct
	public void inicio() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		HttpSession httpSession = request.getSession(false);
		UsuarioDTO usuarioLogeado = (UsuarioDTO) httpSession
				.getAttribute(ConfiguracionConst.SESSION_ATRIBUTE_LOGGED_USER);

		this.view.setIdUsuarioLogeado(usuarioLogeado.getIdUsuario());
		this.view.setMisNotificaciones(
				autorizaciones.consultarAutorizacionesUsuarioEstatus(this.view.getIdUsuarioLogeado(), false));

		Object idAccionObj = httpSession.getAttribute("idAccion");
		Object idBuzonObj = httpSession.getAttribute("idBuzon");

		if (idAccionObj != null && idBuzonObj != null) {
			Integer idAccion = Integer.valueOf(idAccionObj.toString());
			Integer idBuzon = Integer.valueOf(idBuzonObj.toString());

			mostrarDetalleNotificacion(idBuzon, idAccion);
		}

	}

	public void mostrarDetalleNotificacion(Integer idBuzon, Integer idAccion) {
		view.setMostrarAutorizacion(true);
		view.setMostrarDetalleAperturaVacante(false);
		view.setMostrarDetalleAperturaVacantePrograma(false);
		view.setMostrarDetalleLaboralPrograma(false);
		view.setMostrarDetalleMovimiento(false);

		try {
			view.getAutorizacion().setIdBuzon(idBuzon);
			DetalleAutorizacionDTO detalleAutorizacion = autorizaciones.obtenerDetalleAutorizacion(idBuzon);
			view.setDetalleAutorizacion(detalleAutorizacion);

			if (idAccion == EnumTiposAccionesAutorizacion.APERTURA_VACANTE
					|| idAccion == EnumTiposAccionesAutorizacion.APERTURA_INTERINATO) {

				view.setMostrarDetalleAperturaVacante(true);
			} else if (idAccion == EnumTiposAccionesAutorizacion.APERTURA_VACANTE_PROGRAMA_FEDERAL_POR_DETALLE
					|| idAccion == EnumTiposAccionesAutorizacion.APERTURA_VACANTE_VOLUNTARIO) {

				view.setMostrarDetalleAperturaVacantePrograma(true);
				if (idAccion == EnumTiposAccionesAutorizacion.APERTURA_VACANTE_PROGRAMA_FEDERAL_POR_DETALLE) {
					view.setMostrarDetalleLaboralPrograma(true);
				}

			} else if (idAccion == EnumTiposAccionesAutorizacion.MOVIMIENTOS_PERSONAL) {
				view.setMostrarDetalleMovimiento(true);

			}
            else if (idAccion == EnumTiposAccionesAutorizacion.SUPLENCIA_POR_RECURSO) {
                view.setMostrarDetalleSuplencia(true);
            }
            else if (idAccion == EnumTiposAccionesAutorizacion.AUTORIZAR_PRODUCTO_NOMINA_ESTATAL){
                JSFUtils.redireccionarInterna( Modulo.AUTORIZAR_NOMINA.getUrl());
            }
		} catch (ReglaNegocioException exception) {
			JSFUtils.errorMessageEspecifico("messages_generales", "", exception.getMessage());
		} catch (IOException e) {
			JSFUtils.errorMessageEspecifico("messages_generales", "", e.getMessage());
		}
	}

	public void autorizarMovimiento() {

		try {

			view.getAutorizacion().setIdUsuario(view.getIdUsuarioLogeado());

			autorizaciones.autorizarProceso(view.getAutorizacion());
			MiBuzonView view = new MiBuzonView();
			this.setView(view);
			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
					.getRequest();
			HttpSession httpSession = request.getSession(false);
			UsuarioDTO usuarioLogeado = (UsuarioDTO) httpSession
					.getAttribute(ConfiguracionConst.SESSION_ATRIBUTE_LOGGED_USER);

			view.setIdUsuarioLogeado(usuarioLogeado.getIdUsuario());
			view.setMisNotificaciones(
					autorizaciones.consultarAutorizacionesUsuarioEstatus(view.getIdUsuarioLogeado(), false));

			JSFUtils.infoMessage("", "¡La autorización ha sido exitosa!");

		} catch (ReglaNegocioException exception) {
			throw new ReglaNegocioException(exception.getMessage(), exception.getCodigoError());
		}

	}

	public void ocultarDetalleNotificacion() {
		view.setMostrarAutorizacion(false);
	}

	public MiBuzonView getView() {
		return view;
	}

	public void setView(MiBuzonView view) {
		this.view = view;
	}

}
