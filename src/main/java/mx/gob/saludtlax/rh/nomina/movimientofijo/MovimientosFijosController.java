/**
 * Copyright © 2016
 */
package mx.gob.saludtlax.rh.nomina.movimientofijo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;

import mx.gob.saludtlax.rh.catalogos.Catalogo;
import mx.gob.saludtlax.rh.configuracion.terceroinstitucional.TerceroInstitucional;
import mx.gob.saludtlax.rh.configuracion.terceroinstitucional.TerceroInstitucionalDTO;
import mx.gob.saludtlax.rh.empleados.administracion.Empleado;
import mx.gob.saludtlax.rh.empleados.administracion.EmpleadoDetalladoDTO;
import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;
import mx.gob.saludtlax.rh.nomina.movimientos.MovimientosController;
import mx.gob.saludtlax.rh.nomina.movimientosnomina.TipoMovimientoNominaDTO;
import mx.gob.saludtlax.rh.nomina.movimientosnomina.TipoMovimientosNominaEJB;
import mx.gob.saludtlax.rh.nomina.productosnomina.NominaEmpleadoService;
import mx.gob.saludtlax.rh.siif.reportarcontratos.BusinessException;
import mx.gob.saludtlax.rh.util.FechaUtil;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.SelectItemsUtil;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.UploadedFile;

/**
 * @author Eduardo Mex
 * @email Lic.Eduardo_Mex@hotmail.com
 * @version 1.0
 * @since 09/05/2016 16:54:38
 */
@ManagedBean(name = "movimientosFijos")
@ViewScoped
public class MovimientosFijosController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -826109450983768276L;

	@Inject
	private Empleado empleadoEJB;
	@Inject
	private Catalogo catalogosEJB;

	@Inject
	private MovimientoFijoService movimientoFijoService;

	@Inject
	private NominaEmpleadoService nominaEmpleadoService;

	@Inject
	private TerceroInstitucional terceroInstitucional;

	@Inject
	private TipoMovimientosNominaEJB tipoMovimientosNominaEJB;

	private List<InfoEmpleadoDTO> empleadoLista = new ArrayList<InfoEmpleadoDTO>();
	private EmpleadoDetalladoDTO empleadoDatos;

	private List<MovimientoNominaDTO> listaMovimientos = new ArrayList<>();
	private List<MovimientoNominaDTO> listaMovimientoArchivo = new ArrayList<>();

	private MovimientoNominaDTO newMovimientoFijoDTO = new MovimientoNominaDTO();
	private MovimientoNominaDTO movimientoSeleccionado = new MovimientoNominaDTO();

	private List<SelectItem> listaNombramientos;
	private List<SelectItem> listaConceptos;
	private Integer terceroSeleccionado;

	private List<SelectItem> quincenas = new ArrayList<>();
	private Integer quincenaSeleccionada;

	private InfoEmpleadoDTO empleadoSeleccionado;
	private UploadedFile file = null;
	private String criterio;

	private Boolean panelBusqueda = Boolean.TRUE;
	private Boolean panelMovimientosFijos = Boolean.FALSE;
	private Boolean mostrarTablaEmpleados = Boolean.FALSE;

	private Integer idNominaEmpleado;
	private Integer quincenaInicial;

	private Boolean permitirAltaMovimiento = true;

	private Boolean quincenaCerrada = false;

	private List<TipoMovimientoNominaDTO> listaMovimientosNomina = new ArrayList<>();
	private TipoMovimientoNominaDTO tipoMovimientoDTOSeleccionado = new TipoMovimientoNominaDTO();
	private List<SelectItem> itemsTiposMov = new ArrayList<>();
	private Integer tipoMovNominaSeleccionado;

	private Boolean habilitarCampoUnidades = false;
	private Boolean habilitarCamposTerceros = false;
	private Boolean habilitarCampoImporte = true;

	private Boolean mostrarPanelDatos = false;

	private List<MovimientoNominaDTO> movimientosProcesados = new ArrayList<>();

	private Boolean mostrarPanelImportacionMasiva = false;

	private Boolean mostrarTablaCargaInfo = true;
	private Boolean mostrarTablaResultCargaInfo = false;

	private Boolean activarBotonGuardar = true;

	@PostConstruct
	public void init() {
//		listaMovimientosNomina = new ArrayList<>();
//		listaMovimientosNomina = tipoMovimientosNominaEJB.obtenerListaMovimientos();
		this.listaConceptos = SelectItemsUtil.listaCatalogos(catalogosEJB.tercerosInstitucionales());
		//itemsTiposMov.clear();
//		for (TipoMovimientoNominaDTO dto : listaMovimientosNomina) {
//			itemsTiposMov
//					.add(new SelectItem(dto.getIdTimpoMovimiento(), dto.getClave() + " - " + dto.getDescripcion()));
//		}

		for (int i = 1; i <= 24; i++) {
			quincenas.add(new SelectItem(i, "Quincena-" + i));
		}
		 MovimientosController beanMovimientos = (MovimientosController) JSFUtils.getManagedBean("movimientos");
			tipoMovimientoDTOSeleccionado = tipoMovimientosNominaEJB.obtenerTipoMovimientoPorClave(beanMovimientos.getView().getClaveMovimiento());
			empleadoSeleccionado = beanMovimientos.getView().getEmpleadoSeleccionado();
			if(empleadoSeleccionado!=null){
			newMovimientoFijoDTO.setIdEmpleado(empleadoSeleccionado.getIdEmpleado());
			}
	}

	public void mostrarPanelImportacion() {
		mostrarPanelImportacionMasiva = true;
		panelBusqueda = false;
		mostrarTablaEmpleados = false;
	}

	public void ocultarPanelImportacion() {
		mostrarPanelImportacionMasiva = true;
		panelBusqueda = true;
	}

	
	public String iraFormulario() {

		Integer valTipoMovimientoSeleccionado = 2;
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

		TipoMovimientoNominaDTO tipoMovimientoNominaDTO = tipoMovimientosNominaEJB
				.obtenerTipoMovimientoPorId(valTipoMovimientoSeleccionado);
		session.setAttribute("idTipoMovimientoNomina", tipoMovimientoNominaDTO);
		String url = "";
		if (tipoMovimientoNominaDTO.getFormaRegistro().compareTo(1) == 0) {
			// redirecciona a movimientos fijos
			url = "/contenido/nomina/movimientos/movimientosfijos.xhtml";
		} else if (tipoMovimientoNominaDTO.getFormaRegistro().compareTo(2) == 0) {
			// redireccion a movimientos importe
			url = "/contenido/nomina/movimientos/movimientoImporte.xhtml";
		} else if (tipoMovimientoNominaDTO.getFormaRegistro().compareTo(3) == 0) {
			// redireccion a movimientos dias
			url = "/contenido/nomina/movimientos/movimientosDias.xhtml";
		} else if (tipoMovimientoNominaDTO.getFormaRegistro().compareTo(4) == 0) {
			// redireccion a movimientos permanentes
			url = "/contenido/nomina/movimientos/movimientoPermanente.xhtml";
		}

		return url;

	}
	
	public void cargarCatalogo() {
		//this.listaNombramientos = SelectItemsUtil.listaCatalogos(catalogosEJB.nombramientos());
		this.listaConceptos = SelectItemsUtil.listaCatalogos(catalogosEJB.tercerosInstitucionales());
		List<MovimientoNominaDTO> movimientosList = new ArrayList<>();
		movimientosList = movimientoFijoService.obtenerMovimientosPorEmpleado(empleadoSeleccionado.getIdEmpleado());
		listaMovimientos.clear();
		listaMovimientos.addAll(movimientosList);
	}

	public void procesarArchivo() {

		if (file.getSize() == 0) {
			JSFUtils.errorMessage("", "Debe seleccionar el archivo antes de intentar cargarlo.");
		} else {
			try {

				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputstream()));

				String lineaTemporal;
				lineaTemporal = bufferedReader.readLine();
				int numeroLineaActual = 0;

				List<ArchivoDTO> listArchivos = new ArrayList<>();
				while (lineaTemporal != null) {
					ArchivoDTO datosArchivo = new ArchivoDTO();

					datosArchivo.setAnio(new Integer(lineaTemporal.substring(32, 36)));
					datosArchivo.setCapitulo(lineaTemporal.substring(21, 24));
					datosArchivo.setClave_partida(lineaTemporal.substring(38, 40));
					datosArchivo.setMonto(new BigDecimal(lineaTemporal.substring(24, 32)));
					datosArchivo.setNumero_quincena(new Integer(lineaTemporal.substring(36, 38)));
					datosArchivo.setRfc(lineaTemporal.substring(0, 13));
					datosArchivo.setTipo_movimiento(lineaTemporal.substring(42, 43));

					numeroLineaActual++;
					lineaTemporal = bufferedReader.readLine();
					listArchivos.add(datosArchivo);
				}
				listaMovimientoArchivo.clear();
				for (ArchivoDTO dto : listArchivos) {
					// System.out.println("archivo::" + dto.getRfc());
					newMovimientoFijoDTO = new MovimientoNominaDTO();
					empleadoSeleccionado = null;
					try {
						List<InfoEmpleadoDTO> listaEmpleados = empleadoEJB.consultaPorCriterio(dto.getRfc());

						if (!listaEmpleados.isEmpty()) {
							this.setEmpleadoLista(listaEmpleados);
							empleadoSeleccionado = listaEmpleados.get(0);
						} else {
							newMovimientoFijoDTO.setStatusRegistro(
									"No se encontro a ningun empleado relasionado al RFC, este registro no se guardará");
						}

					} catch (NoResultException e) {

						System.out.println("No se encontro el empleado: " + dto.getRfc());
					}

					TerceroInstitucionalDTO teroInsDto = new TerceroInstitucionalDTO();
					System.out.println("terceros: " + dto.getCapitulo() +" - "+ dto.getClave_partida());
					teroInsDto = terceroInstitucional.obtenerporClave(dto.getCapitulo(), dto.getClave_partida());
					newMovimientoFijoDTO.setAnioFinal(dto.getAnio());
					newMovimientoFijoDTO.setAnyoOperacion(FechaUtil.ejercicioActual());
					newMovimientoFijoDTO.setIdTerceroInstitucional(teroInsDto.getIdTerceroInstitucional());
					newMovimientoFijoDTO.setTerceroInstitucional(teroInsDto.getConceptoDeduccion());
					newMovimientoFijoDTO.setImporteDescontado(dto.getMonto());
					newMovimientoFijoDTO.setQuincenaInicial(0);
					newMovimientoFijoDTO.setAnioInicial(FechaUtil.ejercicioActual());
					newMovimientoFijoDTO.setRfc(dto.getRfc());
					newMovimientoFijoDTO.setQuincenaFinal(dto.getNumero_quincena());
					newMovimientoFijoDTO
							.setIdEmpleado(empleadoSeleccionado != null ? empleadoSeleccionado.getIdEmpleado() : null);
					newMovimientoFijoDTO.setIdTipoMovimiento(tipoMovNominaSeleccionado);
					newMovimientoFijoDTO.setEstatus(true);
					if (dto.getTipo_movimiento().contains("A")) {
						newMovimientoFijoDTO.setTipoMovimiento("alta");
					}

					if (dto.getTipo_movimiento().contains("M")) {
						newMovimientoFijoDTO.setTipoMovimiento("edicion");
					}

					listaMovimientoArchivo.add(newMovimientoFijoDTO);

				}
				mostrarTablaResultCargaInfo = false;
				mostrarTablaCargaInfo = true;
				activarBotonGuardar = false;
			} catch (IOException e) {
				throw new BusinessException();
			}
		}
	}

	public void guardarMovimientosArchivos() {
		movimientosProcesados.clear();
		for (MovimientoNominaDTO dto : listaMovimientoArchivo) {
			if (dto.getTipoMovimiento().contains("alta")) {

				newMovimientoFijoDTO = new MovimientoNominaDTO();
				newMovimientoFijoDTO = dto;

				agregarMovimiento();
			}
			if (dto.getTipoMovimiento().contains("edicion")) {
				MovimientoNominaDTO movEdicion = movimientoFijoService.obtenerMovimientoPorDatosArchivo(dto);
				if (movEdicion == null) {
					JSFUtils.errorMessage("El movimiento no se encuentra en bd para ser editado" + dto.getRfc(), null);
					continue;
				}
			}
		}
		mostrarTablaResultCargaInfo = true;
		mostrarTablaCargaInfo = false;
		activarBotonGuardar = true;
	}

	public void handleFileUpload(FileUploadEvent event) {
		FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void agregarMovimiento() {
		buscarPeriodosInicio(newMovimientoFijoDTO.getIdEmpleado());

		if (quincenaInicial.compareTo(25) == 0) {
			newMovimientoFijoDTO.setQuincenaInicial(1);
			newMovimientoFijoDTO.setAnioInicial(FechaUtil.ejercicioActual() + 1);
		} else {
			newMovimientoFijoDTO.setQuincenaInicial(quincenaInicial);
			newMovimientoFijoDTO.setAnioInicial(FechaUtil.ejercicioActual());
		}
		if (!quincenaCerrada) {
			newMovimientoFijoDTO.setStatusRegistro("Registro creado correctamente.");

			if (newMovimientoFijoDTO.getIdEmpleado() != null) {
				newMovimientoFijoDTO.setEstatus(true);
				movimientoFijoService.crear(newMovimientoFijoDTO);
				JSFUtils.infoMessage("Exito:","El movimiento se registro correctamente.");
				movimientosProcesados.add(newMovimientoFijoDTO);
			}
		} else {
			newMovimientoFijoDTO.setStatusRegistro(
					"La quincnea para este empleado se encuentra cerrada, el registro no se guardo.");
			movimientosProcesados.add(newMovimientoFijoDTO);
		}
		newMovimientoFijoDTO = new MovimientoNominaDTO();
		//cargarCatalogo();
	}

	public void validator(FacesContext context, UIComponent component, Object value) throws ValidatorException {

		String nombreComponete = component.getId();
		switch (nombreComponete) {

		case "numDias":
			Integer dias = (Integer) value;

			if (!ValidacionUtil.esNumeroPositivo(dias)) {
				FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor ingrese el numero de dias, el campo no puede quedar vacio.");
				context.addMessage(component.getClientId(), facesMessage1);
				throw new ValidatorException(facesMessage1);
			}

			break;

		case "folio":
			String folio = (String) value;

			if (ValidacionUtil.esCadenaVacia(folio)) {
				FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor ingrese el folio del documento, el campo no puede quedar vacio.");
				context.addMessage(component.getClientId(), facesMessage1);
				throw new ValidatorException(facesMessage1);
			}

			break;
		case "anioFinal":
			Integer anioFinal = (Integer) value;

			if (!ValidacionUtil.esNumeroPositivo(anioFinal) || anioFinal.compareTo(2015) < 0) {
				FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor ingrese el año final, debe ser mayor a" + (FechaUtil.ejercicioActual() - 1) + ".");
				context.addMessage(component.getClientId(), facesMessage1);
				throw new ValidatorException(facesMessage1);
			}
			break;
		case "importeQuincenal":
			BigDecimal importeQuincenal = (BigDecimal) value;
			if (!ValidacionUtil.esNumeroPositivo(importeQuincenal)) {
				FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"El importe debe ser mayor a 0.");
				context.addMessage(component.getClientId(), facesMessage1);
				throw new ValidatorException(facesMessage1);
			}
			break;
		case "concepto":
			Integer concepto = (Integer) value;

			if (!ValidacionUtil.esNumeroPositivo(concepto)) {
				FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Eliga un concepto para el movimiento.");
				System.out.println("concepto::" + concepto);
				context.addMessage(component.getClientId(), facesMessage1);
				throw new ValidatorException(facesMessage1);
			}
			break;
		}
	}

	public void eliminarMovimiento() {
		movimientoFijoService.eliminar(movimientoSeleccionado);
		//cargarCatalogo();
	}

	public void editarRegistro() {
		try {
			movimientoFijoService.editar(movimientoSeleccionado);
			newMovimientoFijoDTO.setStatusRegistro("El registro se edito correctamente.");
			movimientosProcesados.add(newMovimientoFijoDTO);
			movimientoSeleccionado = newMovimientoFijoDTO;
		//	cargarCatalogo();
			FacesMessage msg = new FacesMessage("Actualizado:", "Registro Actualizado correctamentee");
			FacesContext.getCurrentInstance().addMessage(null, msg);

		} catch (BusinessException ex) {
			JSFUtils.errorMessage("", "No se pudo guardar los cambios.");
		}

	}

	public void onRowEdit(RowEditEvent event) {

		try {

			MovimientoNominaDTO mov = ((MovimientoNominaDTO) event.getObject());
			movimientoFijoService.editar(mov);

			FacesMessage msg = new FacesMessage("Actualizado:",
					((MovimientoNominaDTO) event.getObject()).getTerceroInstitucional());
			FacesContext.getCurrentInstance().addMessage(null, msg);

		} catch (BusinessException ex) {
			JSFUtils.errorMessage("", "No se pudo guardar los cambios.");
		}

	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edicion Cancelada:",
				((MovimientoNominaDTO) event.getObject()).getTerceroInstitucional());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void buscarEmpleados() {

		if (this.criterio == null) {
			JSFUtils.errorMessage("Movimientos Fijos: ", "El criterio es necesario");
		} else {

			List<InfoEmpleadoDTO> listaEmpleados = empleadoEJB.consultaPorCriterio(this.criterio);

			this.mostrarTablaEmpleados = true;

			if (!listaEmpleados.isEmpty()) {
				this.setEmpleadoLista(listaEmpleados);
			}
		}

	}

	public void verMovimientosFijos(InfoEmpleadoDTO empleadoSeleccionado) {

		this.panelBusqueda = Boolean.FALSE;
		this.mostrarTablaEmpleados = Boolean.FALSE;
		this.panelMovimientosFijos = Boolean.TRUE;

		this.empleadoSeleccionado = empleadoSeleccionado;
		empleadoDatos = empleadoEJB.obtenerInformacionEmpleado(empleadoSeleccionado.getIdEmpleado());
		//cargarCatalogo();
		buscarPeriodosInicio(empleadoSeleccionado.getIdEmpleado());

	}

	public void buscarPeriodosInicio(Integer idEmpleado) {
		quincenaCerrada = false;
		permitirAltaMovimiento = false;
		idNominaEmpleado = nominaEmpleadoService.obntenerNominaActivaPorEmpleado(idEmpleado);
		quincenaInicial = movimientoFijoService.numeroQuincena(4, FechaUtil.ejercicioActual(),
				FechaUtil.fechaActualSinHora());
		if (idNominaEmpleado == null) {
			JSFUtils.errorMessage("Atencion:", "La nomina ya no se encuentra activa, "
					+ "por lo que no se podra dar de alta el movimiento del empleado " + empleadoSeleccionado.getRfc());
			quincenaInicial = quincenaInicial + 1;
			// habilita el boton
			permitirAltaMovimiento = true;
			// bandera para permitir o no guardar el registro del movimiento
			quincenaCerrada = true;
		} else {
			if (quincenaInicial.compareTo(0) == 0) {
				quincenaCerrada = true;
				JSFUtils.warningMessage("", "No se encuentra registrado el periodo para los datos enviados.");
			}
		}
	}

	public String irInicio() {
		MovimientosController beanMovimientos = (MovimientosController) JSFUtils.getManagedBean("movimientos");
		 beanMovimientos.cargarMivimientosPorEmpleado();
		return "/contenido/nomina/movimientos/index.xhtml?faces-config=true";
	}

	/****************** Getters and Setters **********************/

	/**
	 * @return the criterio
	 */
	public String getCriterio() {
		return criterio;
	}

	/**
	 * @param criterio
	 *            the criterio to set
	 */
	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}

	/**
	 * @return the empleadoEJB
	 */
	public Empleado getEmpleadoEJB() {
		return empleadoEJB;
	}

	/**
	 * @param empleadoEJB
	 *            the empleadoEJB to set
	 */
	public void setEmpleadoEJB(Empleado empleadoEJB) {
		this.empleadoEJB = empleadoEJB;
	}

	/**
	 * @return the panelBusqueda
	 */
	public Boolean getPanelBusqueda() {
		return panelBusqueda;
	}

	/**
	 * @param panelBusqueda
	 *            the panelBusqueda to set
	 */
	public void setPanelBusqueda(Boolean panelBusqueda) {
		this.panelBusqueda = panelBusqueda;
	}

	/**
	 * @return the mostrarTablaEmpleados
	 */
	public Boolean getMostrarTablaEmpleados() {
		return mostrarTablaEmpleados;
	}

	/**
	 * @param mostrarTablaEmpleados
	 *            the mostrarTablaEmpleados to set
	 */
	public void setMostrarTablaEmpleados(Boolean mostrarTablaEmpleados) {
		this.mostrarTablaEmpleados = mostrarTablaEmpleados;
	}

	/**
	 * @return the panelMovimientosFijos
	 */
	public Boolean getPanelMovimientosFijos() {
		return panelMovimientosFijos;
	}

	/**
	 * @param panelMovimientosFijos
	 *            the panelMovimientosFijos to set
	 */
	public void setPanelMovimientosFijos(Boolean panelMovimientosFijos) {
		this.panelMovimientosFijos = panelMovimientosFijos;
	}

	public List<InfoEmpleadoDTO> getEmpleadoLista() {
		return empleadoLista;
	}

	public void setEmpleadoLista(List<InfoEmpleadoDTO> empleadoLista) {
		this.empleadoLista = empleadoLista;
	}

	public InfoEmpleadoDTO getEmpleadoSeleccionado() {
		return empleadoSeleccionado;
	}

	public void setEmpleadoSeleccionado(InfoEmpleadoDTO empleadoSeleccionado) {
		this.empleadoSeleccionado = empleadoSeleccionado;
	}

	/**
	 * @return the listaConceptos
	 */
	public List<SelectItem> getListaConceptos() {
		return listaConceptos;
	}

	/**
	 * @param listaConceptos
	 *            the listaConceptos to set
	 */
	public void setListaConceptos(List<SelectItem> listaConceptos) {
		this.listaConceptos = listaConceptos;
	}

	/**
	 * @return the listaNombramientos
	 */
	public List<SelectItem> getListaNombramientos() {
		return listaNombramientos;
	}

	/**
	 * @param listaNombramientos
	 *            the listaNombramientos to set
	 */
	public void setListaNombramientos(List<SelectItem> listaNombramientos) {
		this.listaNombramientos = listaNombramientos;
	}

	public List<SelectItem> getQuincenas() {
		return quincenas;
	}

	public void setQuincenas(List<SelectItem> quincenas) {
		this.quincenas = quincenas;
	}

	public Integer getQuincenaSeleccionada() {
		return quincenaSeleccionada;
	}

	public void setQuincenaSeleccionada(Integer quincenaSeleccionada) {
		this.quincenaSeleccionada = quincenaSeleccionada;
	}

	public List<MovimientoNominaDTO> getListaMovimientos() {
		return listaMovimientos;
	}

	public void setListaMovimientos(List<MovimientoNominaDTO> listaMovimientos) {
		this.listaMovimientos = listaMovimientos;
	}

	public MovimientoNominaDTO getNewMovimientoFijoDTO() {
		return newMovimientoFijoDTO;
	}

	public void setNewMovimientoFijoDTO(MovimientoNominaDTO newMovimientoFijoDTO) {
		this.newMovimientoFijoDTO = newMovimientoFijoDTO;
	}

	public MovimientoNominaDTO getMovimientoSeleccionado() {
		return movimientoSeleccionado;
	}

	public void setMovimientoSeleccionado(MovimientoNominaDTO movimientoSeleccionado) {
		this.movimientoSeleccionado = movimientoSeleccionado;
	}

	public Integer getTerceroSeleccionado() {
		return terceroSeleccionado;
	}

	public void setTerceroSeleccionado(Integer terceroSeleccionado) {
		this.terceroSeleccionado = terceroSeleccionado;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public Integer getIdNominaEmpleado() {
		return idNominaEmpleado;
	}

	public void setIdNominaEmpleado(Integer idNominaEmpleado) {
		this.idNominaEmpleado = idNominaEmpleado;
	}

	public Integer getQuincenaInicial() {
		return quincenaInicial;
	}

	public void setQuincenaInicial(Integer quincenaInicial) {
		this.quincenaInicial = quincenaInicial;
	}

	public Boolean getPermitirAltaMovimiento() {
		return permitirAltaMovimiento;
	}

	public void setPermitirAltaMovimiento(Boolean permitirAltaMovimiento) {
		this.permitirAltaMovimiento = permitirAltaMovimiento;
	}

	public Boolean getQuincenaCerrada() {
		return quincenaCerrada;
	}

	public void setQuincenaCerrada(Boolean quincenaCerrada) {
		this.quincenaCerrada = quincenaCerrada;
	}

	public List<TipoMovimientoNominaDTO> getListaMovimientosNomina() {
		return listaMovimientosNomina;
	}

	public void setListaMovimientosNomina(List<TipoMovimientoNominaDTO> listaMovimientosNomina) {
		this.listaMovimientosNomina = listaMovimientosNomina;
	}

	public List<SelectItem> getItemsTiposMov() {
		return itemsTiposMov;
	}

	public void setItemsTiposMov(List<SelectItem> itemsTiposMov) {
		this.itemsTiposMov = itemsTiposMov;
	}

	public Integer getTipoMovNominaSeleccionado() {
		return tipoMovNominaSeleccionado;
	}

	public void setTipoMovNominaSeleccionado(Integer tipoMovNominaSeleccionado) {
		this.tipoMovNominaSeleccionado = tipoMovNominaSeleccionado;
	}

	public Boolean getHabilitarCampoUnidades() {
		return habilitarCampoUnidades;
	}

	public void setHabilitarCampoUnidades(Boolean habilitarCampoUnidades) {
		this.habilitarCampoUnidades = habilitarCampoUnidades;
	}

	public Boolean getHabilitarCamposTerceros() {
		return habilitarCamposTerceros;
	}

	public void setHabilitarCamposTerceros(Boolean habilitarCamposTerceros) {
		this.habilitarCamposTerceros = habilitarCamposTerceros;
	}

	public Boolean getHabilitarCampoImporte() {
		return habilitarCampoImporte;
	}

	public void setHabilitarCampoImporte(Boolean habilitarCampoImporte) {
		this.habilitarCampoImporte = habilitarCampoImporte;
	}

	public Boolean getMostrarPanelDatos() {
		return mostrarPanelDatos;
	}

	public void setMostrarPanelDatos(Boolean mostrarPanelDatos) {
		this.mostrarPanelDatos = mostrarPanelDatos;
	}

	public TipoMovimientoNominaDTO getTipoMovimientoDTOSeleccionado() {
		return tipoMovimientoDTOSeleccionado;
	}

	public void setTipoMovimientoDTOSeleccionado(TipoMovimientoNominaDTO tipoMovimientoDTOSeleccionado) {
		this.tipoMovimientoDTOSeleccionado = tipoMovimientoDTOSeleccionado;
	}

	public Boolean getMostrarPanelImportacionMasiva() {
		return mostrarPanelImportacionMasiva;
	}

	public void setMostrarPanelImportacionMasiva(Boolean mostrarPanelImportacionMasiva) {
		this.mostrarPanelImportacionMasiva = mostrarPanelImportacionMasiva;
	}

	public List<MovimientoNominaDTO> getListaMovimientoArchivo() {
		return listaMovimientoArchivo;
	}

	public void setListaMovimientoArchivo(List<MovimientoNominaDTO> listaMovimientoArchivo) {
		this.listaMovimientoArchivo = listaMovimientoArchivo;
	}

	public List<MovimientoNominaDTO> getMovimientosProcesados() {
		return movimientosProcesados;
	}

	public void setMovimientosProcesados(List<MovimientoNominaDTO> movimientosProcesados) {
		this.movimientosProcesados = movimientosProcesados;
	}

	public Boolean getMostrarTablaCargaInfo() {
		return mostrarTablaCargaInfo;
	}

	public void setMostrarTablaCargaInfo(Boolean mostrarTablaCargaInfo) {
		this.mostrarTablaCargaInfo = mostrarTablaCargaInfo;
	}

	public Boolean getMostrarTablaResultCargaInfo() {
		return mostrarTablaResultCargaInfo;
	}

	public void setMostrarTablaResultCargaInfo(Boolean mostrarTablaResultCargaInfo) {
		this.mostrarTablaResultCargaInfo = mostrarTablaResultCargaInfo;
	}

	public Boolean getActivarBotonGuardar() {
		return activarBotonGuardar;
	}

	public void setActivarBotonGuardar(Boolean activarBotonGuardar) {
		this.activarBotonGuardar = activarBotonGuardar;
	}

	public EmpleadoDetalladoDTO getEmpleadoDatos() {
		return empleadoDatos;
	}

	public void setEmpleadoDatos(EmpleadoDetalladoDTO empleadoDatos) {
		this.empleadoDatos = empleadoDatos;
	}

}
