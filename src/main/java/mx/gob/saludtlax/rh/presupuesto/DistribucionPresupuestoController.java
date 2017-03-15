package mx.gob.saludtlax.rh.presupuesto;

import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
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
		view.setListaDependencia(ejb.getListaDependencia());
		view.setMostrarPrincipal(Boolean.TRUE);
		view.setMostrarDistribucion(Boolean.FALSE);
	}

	public String obtenerDistribucionesPresupuestales() {
		try {
			view.setListaDistribucion(
			ejb.distribucionPresupuesto(view.getAnioPresupuesto(), view.getIdTipoNombramiento(), view.getIdDependencia()));
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
			JSFUtils.infoMessage(e.getMessage(), "");
		}
		view.setMostrarDistribucion(false);
		return null;
	}
	
	public void descargarReporte() {
		try {

			ReporteDistribucionPresupuesto reporte = new ReporteDistribucionPresupuesto();
			
			byte[] bytes = null;

			bytes = reporte.generarArchivoExcel(this.view.getListaDistribucionPresupuesto());

			if (bytes != null) {
				JSFUtils.descargarArchivo(bytes,"Distribucion Presupuestal",
						TipoArchivo.getMIMEType("xlsx"));

			}

			JSFUtils.infoMessage("Descargar Reporte: ", "Se descargo correctamente...");

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
