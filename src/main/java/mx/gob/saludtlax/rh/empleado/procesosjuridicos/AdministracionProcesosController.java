package mx.gob.saludtlax.rh.empleado.procesosjuridicos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.FiltroDTO;
import mx.gob.saludtlax.rh.catalogos.Catalogo;
import mx.gob.saludtlax.rh.empleados.administracion.Empleado;
import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.SelectItemsUtil;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

@ManagedBean(name = "procesosJuridicos")
@ViewScoped
public class AdministracionProcesosController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -369562931731903026L;

	@Inject
	private Catalogo catalogo;
	@Inject
	private Empleado empleado;
	@Inject
	private ProcesoJuridico procesoJuridico;

	private AdministracionProcesosView view;

	@PostConstruct
	public void init() {
		view = new AdministracionProcesosView();
		irPrincipal();
		cargarCatalogo();
	}

	public AdministracionProcesosView getView() {
		return view;
	}

	public void cargarCatalogo() {
		this.view.setListaFiltros(SelectItemsUtil.listaFiltrosConsultaAspirantes());
		this.view.setListaProceso(SelectItemsUtil.listaCatalogos(catalogo.obtenerListaProcesoJuridico()));
	}

	public String irPrincipal() {
		view.panelPrincipal();
		return null;
	}

	public String irNuevoProceso(InfoEmpleadoDTO empleado) {
		view.setProceso(procesoJuridico.nuevoProceso(empleado));
		view.setOperacionNuevo(Boolean.TRUE);
		view.panelGestion();
		return null;
	}

	public void irGestionarProceso(ProcesoDTO p) {
		view.setProceso(procesoJuridico.gestionarProceso(p));
		view.setOperacionNuevo(Boolean.FALSE);
		view.panelGestion();
	}

	public void irPanelConsulta() {
		view.setProcesoListo(procesoJuridico.obtenerProcesoLista());
		view.panelConsulta();
	}

	public String guardarProcesos() {
		if (view.getOperacionNuevo()) {
			procesoJuridico.crearProceso(view.getProceso());
		} else {
			procesoJuridico.actualizarProceso(view.getProceso());
		}
		view.panelGestion();
		irPrincipal();
		return null;
	}

	public void eliminarProceso(ProcesoDTO proceso) {
		procesoJuridico.eliminarProceso(proceso);
		view.setDialogoEliminar(false);
		view.panelPrincipal();
		irPrincipal();
	}

	public void obtenerConsultaEmpleado() {

		List<InfoEmpleadoDTO> listaInfoEmpleado = empleado.consultaEmpleado(this.view.getFiltro());

		this.view.setMostrarMenuDetalles(false);

		if (!listaInfoEmpleado.isEmpty()) {

			this.view.setListaEmpleados(listaInfoEmpleado);
			this.view.setMostrarResultadoConsulta(true);

			this.view.setMostrarTipoBusquedaHeader(false);
			this.view.setTipoBusquedaHeader("");

		} else {
			this.view.setListaEmpleados(new ArrayList<InfoEmpleadoDTO>());
			this.view.setMostrarResultadoConsulta(false);

			this.view.setFiltro(new FiltroDTO());
			JSFUtils.errorMessage("Consulta de Empleados", "No se encontrarón resultados, vuelve a intentar");
		}
	}

	public void mostrarDialogoEliminar() {
		this.view.setDialogoEliminar(true);
	}

	public void ocultarDialogoEliminar() {
		this.view.setDialogoEliminar(false);
	}

	// < < < < < Validadores > > > > >

	public void validarConsulta(FacesContext context, UIComponent component, Object value) throws ValidatorException {

		String nombreComponente = component.getId();
		String contexto = "Campo requerido.";

		switch (nombreComponente) {

		case "tipoBusqueda":

			Integer tipoBusqueda = (Integer) value;

			if (!ValidacionUtil.esNumeroPositivo(tipoBusqueda)) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, contexto,
						"Seleccione el tipo de busqueda");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}

			break;

		case "criterio":

			String criterio = String.valueOf(value);

			if (ValidacionUtil.esCadenaVacia(criterio)) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, contexto,
						"Ingrese el criterio");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}

			if (criterio.trim().length() < 4) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, contexto,
						"El criterio de la busqueda debe contener minimo 4 letras");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}

			break;

		default:
			break;
		}

	}

	public void validatorProceso(FacesContext context, UIComponent component, Object value) {

		String nombreComponete = component.getId();
		switch (nombreComponete) {
		case "proceso":
			Integer proceso = (Integer) value;

			if (!ValidacionUtil.esNumeroPositivo(proceso)) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Selecciona un Proceso");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}
			break;
		default:
			break;
		}
	}

}
