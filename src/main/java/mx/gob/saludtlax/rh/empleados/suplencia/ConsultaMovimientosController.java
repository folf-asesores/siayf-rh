//package mx.gob.saludtlax.rh.empleados.suplencia;
//
//import java.io.Serializable;
//
//import javax.annotation.PostConstruct;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ViewScoped;
//import javax.inject.Inject;
//
//import mx.gob.saludtlax.rh.util.SelectItemsUtil;
//
//@ManagedBean(name = "consultaMovimiento")
//@ViewScoped
//public class ConsultaMovimientosController implements Serializable {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 8659315013652832562L;
//	@Inject
//	private Suplencia suplencia;
//
//	private ConsultaMovimientoView view = new ConsultaMovimientoView();
//
//	@PostConstruct
//	public void inicio() {
//		view.setMostrarBusqueda(true);
//		view.setListaMovimientos(SelectItemsUtil.listaTiposMovimientosSuplentes());
//	}
//
//	public void consultarMovimiento() {
//		view.setMovimientos(suplencia.consultarMovimientosSuplente(view.getFiltro()));
//	}
//
//}
