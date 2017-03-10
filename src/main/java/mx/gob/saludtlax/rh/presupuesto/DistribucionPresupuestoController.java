package mx.gob.saludtlax.rh.presupuesto;

import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.reportes.AdministradorReportes;
import mx.gob.saludtlax.rh.seguridad.ConfiguracionConst;
import mx.gob.saludtlax.rh.seguridad.usuario.UsuarioDTO;
import mx.gob.saludtlax.rh.util.CadenaUtil;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.TipoArchivo;

@ManagedBean(name = "distribucionPresupuesto")
@SessionScoped
public class DistribucionPresupuestoController {

	private DistribucionPresupuestoView view;

	@Inject
	private DistribucionPresupuestoEJB ejb;

	@PostConstruct
	public void initConsultarPresupuesto() {
		view = new DistribucionPresupuestoView();
		view.setListaTipoNombramiento(ejb.getListaTipoNombramiento());
		view.setListaUnidadResponsable(ejb.getListaUnidadResponsable());
		view.setListaDependencia(ejb.getListaDependencia());
		view.setMostrarPrincipal(Boolean.TRUE);
		view.setMostrarDistribucion(Boolean.FALSE);
	}

	public String obtenerDistribucionesPresupuestales() {
		try {
			view.setListaDistribucion(
					ejb.distribucionPresupuesto(view.getAnioPresupuesto(), view.getIdTipoNombramiento()));
			view.setMostrarPrincipal(true);

			Integer idTipoNombramientiValido = 15;

			if (!this.view.getListaDistribucion().isEmpty()) {
				if (this.view.getIdTipoNombramiento() == idTipoNombramientiValido) {
					this.view.setMostrarOpcionDescarga(true);
				}

			}

		} catch (ReglaNegocioException e) {
			view.setListaDistribucion(new ArrayList<DistribucionPresupuestoDTO>());
			JSFUtils.infoMessage(e.getMessage(), "");
			view.setMostrarPrincipal(false);
			this.view.setMostrarOpcionDescarga(false);
		}
		view.setMostrarDistribucion(false);
		return null;
	}

	public void seleccionarEdicionProyeccion(DistribucionPresupuestoDTO distribucionPresupuestoDTO) {
		view.setMostrarDistribucion(true);
		view.setMostrarPrincipal(false);
		view.setDistribucionPresupuesto(distribucionPresupuestoDTO);
	}

	public void ocultarEdicionProyeccion() {
		view.setMostrarDistribucion(false);
		view.setMostrarPrincipal(true);
	}

	//public void guardarDistribucion() {
	//	ejb.guardarDistribucion(view.getListaDistribucion());
	//}

	public void actualizarProyeccion() {
		view.setListaDistribucion(
				ejb.distribucionPresupuesto(view.getAnioPresupuesto(), view.getIdTipoNombramiento()));
	}

	public void descargarContrato() {
		try {

			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
					.getRequest();
			HttpSession httpSession = request.getSession(false);
			UsuarioDTO usuario = (UsuarioDTO) httpSession.getAttribute(ConfiguracionConst.SESSION_ATRIBUTE_LOGGED_USER);

			String[] parametros = { "ID_USUARIO", String.valueOf(usuario.getIdUsuario()), "REPORTE_NOMBRE",
					"contrato_estatal_federal", "TIPO_REPORTE", "xlsx", "ANYO_PRESUPUESTO",
					String.valueOf(this.view.getAnioPresupuesto()), "ID_TIPO_NOMBRAMIENTO",
					String.valueOf(this.view.getIdTipoNombramiento()) };

			AdministradorReportes admintradorReportes = new AdministradorReportes();
			String referencia = admintradorReportes.obtenerReferencia(parametros);

			byte[] bytes = null;

			bytes = admintradorReportes.obtenerReporte(referencia);

			if (bytes != null) {
				JSFUtils.descargarArchivo(bytes, CadenaUtil.converterSpace("Contrato_Estatal_Federal"),
						TipoArchivo.getMIMEType("xlsx"));

			}

			JSFUtils.infoMessage("Descargar Contrato: ", "Se descargo correctamente...");

		} catch (NullPointerException | IllegalArgumentException | IOException exception) {

			exception.printStackTrace();
			JSFUtils.errorMessage("Error: ", exception.getMessage());
		} catch (ReglaNegocioException reglaNegocioException) {
			reglaNegocioException.printStackTrace();
			JSFUtils.errorMessage("Error: ", reglaNegocioException.getMessage());
		} catch (ValidacionException validacionException) {

			validacionException.printStackTrace();
			JSFUtils.errorMessage("Error: ", validacionException.getMessage());
		}
	}

	public void descargarContratoDistribucion() {
		try {

			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
					.getRequest();
			HttpSession httpSession = request.getSession(false);
			UsuarioDTO usuario = (UsuarioDTO) httpSession.getAttribute(ConfiguracionConst.SESSION_ATRIBUTE_LOGGED_USER);

			String[] parametros = { "ID_USUARIO", String.valueOf(usuario.getIdUsuario()), "REPORTE_NOMBRE",
					"contrato_estatal_federal_proyeccion", "TIPO_REPORTE", "xlsx", "ANYO_PRESUPUESTO",
					String.valueOf(this.view.getAnioPresupuesto()), "ID_TIPO_NOMBRAMIENTO",
					String.valueOf(this.view.getIdTipoNombramiento()) };

			AdministradorReportes admintradorReportes = new AdministradorReportes();
			String referencia = admintradorReportes.obtenerReferencia(parametros);

			byte[] bytes = null;

			bytes = admintradorReportes.obtenerReporte(referencia);

			if (bytes != null) {
				JSFUtils.descargarArchivo(bytes, CadenaUtil.converterSpace("Contrato_Estatal_Federal_Proyeccion"),
						TipoArchivo.getMIMEType("xlsx"));

			}

			JSFUtils.infoMessage("Descargar Contrato Proyección: ", "Se descargo correctamente...");

		} catch (NullPointerException | IllegalArgumentException | IOException exception) {

			exception.printStackTrace();
			JSFUtils.errorMessage("Error: ", exception.getMessage());
		} catch (ReglaNegocioException reglaNegocioException) {
			reglaNegocioException.printStackTrace();
			JSFUtils.errorMessage("Error: ", reglaNegocioException.getMessage());
		} catch (ValidacionException validacionException) {

			validacionException.printStackTrace();
			JSFUtils.errorMessage("Error: ", validacionException.getMessage());
		}
	}

	public DistribucionPresupuestoView getView() {
		return view;
	}

	public void setView(DistribucionPresupuestoView view) {
		this.view = view;
	}
}
