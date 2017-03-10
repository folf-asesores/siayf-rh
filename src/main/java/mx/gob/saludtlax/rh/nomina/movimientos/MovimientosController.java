package mx.gob.saludtlax.rh.nomina.movimientos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.catalogos.Catalogo;
import mx.gob.saludtlax.rh.empleados.administracion.Empleado;
import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;
import mx.gob.saludtlax.rh.siif.reportarcontratos.BusinessException;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.SelectItemsUtil;

@ManagedBean(name = "movimientos")
@SessionScoped
public class MovimientosController implements Serializable {
	private static final long serialVersionUID = 3091198275111877768L;

	@Inject
	private Movimientos ejb;

	@Inject
	private Empleado empleadoEJB;
	@Inject
	private MovimientosView view;
	@Inject
	private Catalogo catalogosEJB;


	private Boolean esTercero = false;

	@PostConstruct
	public void init() {
		view.setMovimientoLista(ejb.getMovimientosLista());
		view.showPanelElegirMovimiento();

		view.setListaConceptos(SelectItemsUtil.listaCatalogos(catalogosEJB.tercerosInstitucionales()));

		List<SelectItem> quincenas = new ArrayList<>();
		for (int i = 1; i <= 24; i++) {
			quincenas.add(new SelectItem(i, "Quincena-" + i));
		}

		view.setQuincenas(quincenas);

	}

	public String irMovimiento() {
		
		if (view.getClaveMovimiento() != null && view.getClaveMovimiento().contains("tr")) {
			esTercero = true;
		} else {
			esTercero = false;
		}
		try {
			// FacesContext contex = FacesContext.getCurrentInstance();
			// contex.getExternalContext().redirect(ejb.getUrlFormPorClave(view.getClaveMovimiento()));

			view.showPanelElegirEmpleado();
		} catch (Exception e) {
		}
		return null;
	}

	public String buscarEmpleados() {
		if (view.getCriterioEmpleado() == null) {
			JSFUtils.errorMessage("Movimientos Fijos: ", "El criterio es necesario");
		} else {

			List<InfoEmpleadoDTO> listaEmpleados = empleadoEJB.consultaPorCriterio(view.getCriterioEmpleado());

			view.setMostrarTablaEmpleados(true);

			if (!listaEmpleados.isEmpty()) {
				view.setEmpleadoLista(listaEmpleados);
			}
		}
		return null;
	}

	public String irMovimientos(InfoEmpleadoDTO empleadoSeleccionado) {
		view.setPanelBusqueda(Boolean.TRUE);
		view.setMostrarTablaEmpleados(Boolean.FALSE);
		view.setPanelMovimientosFijos(Boolean.TRUE);
		view.setEmpleadoSeleccionado(empleadoSeleccionado);
		view.setEmpleadoDatos(empleadoEJB.obtenerInformacionEmpleado(empleadoSeleccionado.getIdEmpleado()));
		view.setListaMovimientos(ejb.obtenerMovimientosPorEmpleado(view.getEmpleadoSeleccionado(), view.getClaveMovimiento()));
		view.setTipoMovimientoSeleccionado(ejb.obtenerTipoMovimiento(view.getClaveMovimiento()));
		if(view.getClaveMovimiento().contentEquals("tr")){
			view.setFormaRegistroMovSeleccionado(1);
		}else{ 
			view.setFormaRegistroMovSeleccionado(view.getTipoMovimientoSeleccionado().getFormaRegistro());
		}
		return null;
	}

	public void cargarMivimientosPorEmpleado(){
		System.out.println("actualizando mov de empleados"+ view.getEmpleadoSeleccionado()+"---"+view.getClaveMovimiento());
		view.setListaMovimientos(
				ejb.obtenerMovimientosPorEmpleado(view.getEmpleadoSeleccionado(), view.getClaveMovimiento()));
	}
	
	public String eliminarMovimiento() {
		System.out.println("Eliminar mov: "+ view.getMovimientoSeleccionado());
		ejb.eliminar(view.getMovimientoSeleccionado());
		view.setListaMovimientos(
				ejb.obtenerMovimientosPorEmpleado(view.getEmpleadoSeleccionado(), view.getClaveMovimiento()));
		return null;
	}

	public void editarRegistro() {
		try {
			ejb.editar(view.getMovimientoSeleccionado());
			FacesMessage msg = new FacesMessage("Actualizado:", "Registro Actualizado correctamentee");
			FacesContext.getCurrentInstance().addMessage(null, msg);

		} catch (BusinessException ex) {
			JSFUtils.errorMessage("", "No se pudo guardar los cambios.");
		}

	}
	
	public void regresarInicio() {
		view.setPanelElegirMovimiento(Boolean.TRUE);
		view.setPanelBusqueda(Boolean.TRUE);
		view.setMostrarTablaEmpleados(Boolean.FALSE);
		view.setPanelMovimientosFijos(Boolean.FALSE);
	}

	public String irInicio() {
		view.setPanelBusqueda(Boolean.FALSE);
		view.setMostrarTablaEmpleados(Boolean.FALSE);
		view.setPanelMovimientosFijos(Boolean.FALSE);

		return "";

	}

	public String iraFormulario() {
		String url = "";
		System.out.println("esta es la url "+view.getClaveMovimiento() +"---"+ view.getTipoMovimientoSeleccionado().getFormaRegistro());
		if(view.getClaveMovimiento().contentEquals("tr")){
		url = ejb.getUrlFormPorClave(1);	
		}else{
		url = ejb.getUrlFormPorClave(view.getTipoMovimientoSeleccionado().getFormaRegistro());
		}
		return url;
	}

	public MovimientosView getView() {
		return view;
	}

	public Boolean getEsTercero() {
		return esTercero;
	}

	public void setEsTercero(Boolean esTercero) {
		this.esTercero = esTercero;
	}
}