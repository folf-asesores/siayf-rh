package mx.gob.saludtlax.rh.presupuesto.presupuestoautorizado;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.util.JSFUtils;
/**
 * 
 * @author José Pablo
 *
 */

@ManagedBean(name = "presupuestoAutorizado")
@ViewScoped
public class PresupuestoAutorizadoController implements Serializable {
	private static final long serialVersionUID = 2116402280296565216L;
	 
	@Inject
	private PresupuestoAutorizado presupuestoAutorizado;
	
	@Inject
	private PresupuestoAutorizadoView view;
	
	@PostConstruct
	private void init() {
		this.view = new PresupuestoAutorizadoView();

		obtenerListaPresupuestoAutorizado();
	}

	public void obtenerListaPresupuestoAutorizado() {
		List<PresupuestoAutorizadoDTO> lista = presupuestoAutorizado.obtenerListaPresupuestoAutorizado();

		this.view.setListaPresupuestoAutorizado(lista);
	}
	
	public void crearPresupuestoAutorizado() {
		try {
			presupuestoAutorizado.crearPresupuestoAutorizado(this.view.getCreaPresupuestoAutorizado());
			JSFUtils.infoMessage("Registro Correcto","");
			mostrarPrincipal();
		} catch (ReglaNegocioException | ValidacionException e) {
			JSFUtils.errorMessage("Error: ", e.getMessage());
		}
	}

	public void actualizarPresupuestoAutorizado() {
		try {
			presupuestoAutorizado.actualizarPresupuestoAutorizado(this.view.getActualizarPresupuestoAutorizado());
			JSFUtils.infoMessage("Actualización Correcta","");
			mostrarPrincipal();
		} catch (ReglaNegocioException | ValidacionException e) {
			JSFUtils.errorMessage("Error: ", e.getMessage());
		}
	}

	public void eliminarPresupuestoAutorizado() {
		try {
			presupuestoAutorizado.eliminarPresupuestoAutorizado(this.view.getIdPresupuestoAutorizadoSeleccionado());
			JSFUtils.infoMessage("Eliminación Correcta","");
			mostrarPrincipal();
		} catch (ReglaNegocioException | ValidacionException e) {
			JSFUtils.errorMessage("Error: ", e.getMessage());
		}
	}
	
	public void mostrarNuevoRegistro() {
		this.view.setCreaPresupuestoAutorizado(new PresupuestoAutorizadoDTO());
		this.view.setPanelPrincipal(false);
		this.view.setPanelCrear(true);
		this.view.setPanelActualizar(false);
		this.view.setDialogEliminar(false);
	}

	public void mostrarActualizacion(PresupuestoAutorizadoDTO dto) {
		this.view.setActualizarPresupuestoAutorizado(dto);
		this.view.setPanelPrincipal(false);
		this.view.setPanelCrear(false);
		this.view.setPanelActualizar(true);
		this.view.setDialogEliminar(false);
	}

	public void mostrarDialogEliminar(Integer idPresupuestoAutorizado) {
		this.view.setIdPresupuestoAutorizadoSeleccionado(idPresupuestoAutorizado);
		this.view.setPanelPrincipal(false);
		this.view.setPanelCrear(false);
		this.view.setPanelActualizar(false);
		this.view.setDialogEliminar(true);
	}

	public void mostrarPrincipal() {
		this.view.setIdPresupuestoAutorizadoSeleccionado(0);
		this.view.setActualizarPresupuestoAutorizado(new PresupuestoAutorizadoDTO());
		this.view.setCreaPresupuestoAutorizado(new PresupuestoAutorizadoDTO());
		this.view.setPanelPrincipal(true);
		this.view.setPanelCrear(false);
		this.view.setPanelActualizar(false);
		this.view.setDialogEliminar(false);
		obtenerListaPresupuestoAutorizado();
	}
	
	
	public void regresarModulo() throws IOException {
		JSFUtils.redireccionar("/siayf-rh/contenido/presupuesto/presupuestoAutorizado.xhtml?faces-redirect=true");
	}
	

	public PresupuestoAutorizadoView getView() {
		return view;
	}

	public void setView(PresupuestoAutorizadoView view) {
		this.view = view;
	}

	
}
