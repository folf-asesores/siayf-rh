package mx.gob.saludtlax.rh.quinquenios;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import org.joda.time.DateTime;

import mx.gob.saludtlax.rh.catalogos.Catalogo;
import mx.gob.saludtlax.rh.catalogos.CatalogoDTO;
import mx.gob.saludtlax.rh.util.CalculoDieferenciaFechas;
import mx.gob.saludtlax.rh.util.FechaUtil;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.vacantes.seleccion.InfoEmpleadoVacanteDTO;

@ManagedBean
@ViewScoped
public class ConfiguracionQuinquenioController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8061745178729839816L;

	@Inject
	Catalogo nombramiento;

	@Inject
	ConfiguracionQuinquenioService configuracionQuinquenioService;

	private List<CatalogoDTO> listaNombramientos = new ArrayList<>();
	private List<SelectItem> itemsNombramientos = new ArrayList<>();
	private Integer idNombramientoSeleccionado;
	private List<ConfiguracionQuinquenioDTO> listaConfiguracionesQuinquenios = new ArrayList<>();

	@PostConstruct
	public void inicio() {
		listaNombramientos.clear();
		listaNombramientos = nombramiento.obtenerListaTipoNombramiento();

		for (CatalogoDTO info : listaNombramientos) {
			itemsNombramientos.add(new SelectItem(info.getId(), info.getNombre()));
		}

		cargarConfiguraciones();
	}

	public void cargarConfiguraciones() {
		listaConfiguracionesQuinquenios.clear();
		listaConfiguracionesQuinquenios = configuracionQuinquenioService.obtenerConfiguraciones();

	}

	public void actualizarQuinquenios() {

		if (idNombramientoSeleccionado == null || idNombramientoSeleccionado <= 0) {
			JSFUtils.errorMessageEspecifico(":form:nombramiento", "Validacion:", "Debe seleccionar un nombramiento.");
		} else {

			List<InfoEmpleadoVacanteDTO> infoEmpleados = new ArrayList<>();
			infoEmpleados = configuracionQuinquenioService
					.buscarEmpleadosPorTipoNombramiento(idNombramientoSeleccionado);

			if (infoEmpleados != null) {

				for (InfoEmpleadoVacanteDTO empleado : infoEmpleados) {
					ConfiguracionQuinquenioDTO configuracionExistente = new ConfiguracionQuinquenioDTO();
					configuracionExistente = configuracionQuinquenioService
							.obtenerConfiguracionPorEmpleado(empleado.getIdEmpleado());
					if (configuracionExistente != null) {
						
						System.out.println("config existente: " + configuracionExistente.getId_configuracion_quinquenio());
						
						configuracionExistente.setFecha_actualizacion(new Date());

						String claveQuinquenio= calcularQuinquenio(empleado.getFechaInicioLabores());

						configuracionExistente.setClave_concepto(claveQuinquenio);

						configuracionQuinquenioService.actualizarConfiguracion(configuracionExistente);
					}

					System.out.println("datos::" + " empleado:" + empleado.getRfc() + " configuracion:"
							+ empleado.getIdConfiguracionPresupuestal() + " fecha:" + empleado.getFechaInicioLabores());
					String claveQuinquenio="";
					if (empleado.getFechaInicioLabores() != null) {
						 claveQuinquenio= calcularQuinquenio(empleado.getFechaInicioLabores());
					}
					ConfiguracionQuinquenioDTO configuracionNew = new ConfiguracionQuinquenioDTO();
					configuracionNew.setClave_concepto(claveQuinquenio);
					configuracionNew.setEstatus(true);
					configuracionNew.setFecha_actualizacion(new Date());
					configuracionNew.setFecha_alta(new Date());
					configuracionNew.setId_empleado(empleado.getIdEmpleado());
					configuracionNew.setIdnombramiento(empleado.getIdNombramiento());
					configuracionNew.setNombreEmpleado(empleado.getNombre());
					configuracionNew.setRfc(empleado.getRfc());
					configuracionNew.setIdConfiguracionP(empleado.getIdConfiguracionPresupuestal());

					configuracionQuinquenioService.crearConfiguracion(configuracionNew);
				}
			}

		}

		cargarConfiguraciones();

	}

	public String calcularQuinquenio(Date fechainiciolabores) {
		Date fechaActual = new Date();

		long anios;
		long meses;
		long dias;
		
		anios=CalculoDieferenciaFechas.getDiffDates(fechainiciolabores, fechaActual, 0);
		meses=CalculoDieferenciaFechas.getDiffDates(fechainiciolabores, fechaActual, 3);
		dias=CalculoDieferenciaFechas.getDiffDates(fechainiciolabores, fechaActual, 4);
		System.out.println("años:" + CalculoDieferenciaFechas.getDiffDates(fechainiciolabores, fechaActual, 0));
		System.out.println("meses año:" + CalculoDieferenciaFechas.getDiffDates(fechainiciolabores, fechaActual, 3));
		System.out.println("dias mes:" + CalculoDieferenciaFechas.getDiffDates(fechainiciolabores, fechaActual, 4));
		
		return buscarClaveQuinquenio(anios);
	}

	public String buscarClaveQuinquenio(long anios) {
		Integer anio = (int) anios;
		

		if (anio >= 5 && anio < 10) {
			return TipoQuinquenios.getClaveConcepto(5);
		} else if (anio >= 10 && anio < 15) {
			return TipoQuinquenios.getClaveConcepto(10);
		} else if (anio >= 15 && anio < 20) {
			return TipoQuinquenios.getClaveConcepto(15);
		} else if (anio >= 20 && anio < 25) {
			return TipoQuinquenios.getClaveConcepto(20);
		} else if (anio >= 25) {
			return TipoQuinquenios.getClaveConcepto(25);
		}
		return "";
	}

	public List<CatalogoDTO> getListaNombramientos() {
		return listaNombramientos;
	}

	public void setListaNombramientos(List<CatalogoDTO> listaNombramientos) {
		this.listaNombramientos = listaNombramientos;
	}

	public Integer getIdNombramientoSeleccionado() {
		return idNombramientoSeleccionado;
	}

	public void setIdNombramientoSeleccionado(Integer idNombramientoSeleccionado) {
		this.idNombramientoSeleccionado = idNombramientoSeleccionado;
	}

	public List<ConfiguracionQuinquenioDTO> getListaConfiguracionesQuinquenios() {
		return listaConfiguracionesQuinquenios;
	}

	public void setListaConfiguracionesQuinquenios(List<ConfiguracionQuinquenioDTO> listaConfiguracionesQuinquenios) {
		this.listaConfiguracionesQuinquenios = listaConfiguracionesQuinquenios;
	}

	public List<SelectItem> getItemsNombramientos() {
		return itemsNombramientos;
	}

	public void setItemsNombramientos(List<SelectItem> itemsNombramientos) {
		this.itemsNombramientos = itemsNombramientos;
	}

}
