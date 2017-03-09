package mx.gob.saludtlax.rh.nomina.movimientosnomina;

import java.io.IOException;
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

import mx.gob.saludtlax.rh.nomina.movimientofijo.MovimientoNominaDTO;
import mx.gob.saludtlax.rh.nomina.movimientos.MovimientosController;
import mx.gob.saludtlax.rh.configuracion.conceptosnomina.ConceptoNominaFederalesDTO;
import mx.gob.saludtlax.rh.nomina.Evaluador;
import mx.gob.saludtlax.rh.nomina.movimientofijo.MovimientoFijoService;
import mx.gob.saludtlax.rh.nomina.productosnomina.NominaEmpleadoService;
import mx.gob.saludtlax.rh.util.FechaUtil;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

@ManagedBean(name="movimientoNominaPermanente")
@ViewScoped
public class MovimientoNominaPermanentesController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6437254569200299512L;

	@Inject
	private MovimientoFijoService movimientoFijoService;
	
	@Inject
	private NominaEmpleadoService nominaEmpleadoService;
	
	@Inject
	private TipoMovimientosNominaEJB tipoMovimientosNominaEJB;
	
	
	private MovimientoNominaDTO newMovimientoFijoDTO = new MovimientoNominaDTO();
	private List<SelectItem> quincenas = new ArrayList<>();
	private Integer quincenaSeleccionada;
	private List<SelectItem> listaConceptos;
	private Integer terceroSeleccionado;
	
	private TipoMovimientoNominaDTO tipoMovimientoSeleccionado= new TipoMovimientoNominaDTO();
	
	private Integer idNominaEmpleado;
	private Integer quincenaInicial;

	private Boolean permitirAltaMovimiento = true;

	private Boolean quincenaCerrada = false;

	private Integer idUsuarioSeleccionado;
	

	  private ConfiguracionTipoMovimientoDTO configuracionMovimiento;
	
	@PostConstruct
	public void incio(){
		for (int i = 1; i <= 24; i++) {
			quincenas.add(new SelectItem(i, "Quincena-" + i));
		}
		MovimientosController beanMovimientos = (MovimientosController) JSFUtils.getManagedBean("movimientos");
		tipoMovimientoSeleccionado = tipoMovimientosNominaEJB.obtenerTipoMovimientoPorClave(beanMovimientos.getView().getClaveMovimiento());
		setIdUsuarioSeleccionado(beanMovimientos.getView().getEmpleadoSeleccionado().getIdEmpleado());
		System.out.println("clave Seleccionada mov:::!" + tipoMovimientoSeleccionado.getClave());
		
		configuracionMovimiento = new ConfiguracionTipoMovimientoDTO();
		
		configuracionMovimiento = movimientoFijoService.obtenerConfiguracionesPorTipoMovimiento(tipoMovimientoSeleccionado.getIdTimpoMovimiento());
		if(configuracionMovimiento != null){
			if(!configuracionMovimiento.getListConceptoNomina().isEmpty()){
				BigDecimal importePositivo = new BigDecimal(0);
				BigDecimal importeNegativo = new BigDecimal(0);
				for(ConceptoNominaFederalesDTO concepto : configuracionMovimiento.getListConceptoNomina()){
									
					if(concepto.getTipo().compareTo(1)==0){
						System.out.println("concepto1: "+concepto.getTipo()+concepto.getDescripcion()+" " + concepto.getFormula());
						Evaluador evaluador = new Evaluador(concepto.getFormula());
						importePositivo= importePositivo.add(new BigDecimal(String.valueOf(evaluador.getResult())));
					}
					if(concepto.getTipo().compareTo(2)==0){
						
						System.out.println("concepto2: "+concepto.getTipo()+concepto.getDescripcion()+" " + concepto.getFormula());
						Evaluador evaluador = new Evaluador(concepto.getFormula());
						importeNegativo=importeNegativo.add(new BigDecimal(String.valueOf(evaluador.getResult())));
					}
				}
				newMovimientoFijoDTO.setImporteQuincenal((importePositivo.subtract(importeNegativo)));
				newMovimientoFijoDTO.setImporteDescontado((importePositivo.subtract(importeNegativo)));
				
			}
			
		}
	}
	
	public void agregarMovimiento() {
	 MovimientosController beanMovimientos = (MovimientosController) JSFUtils.getManagedBean("movimientos");
	 System.out.println("Empleado seleccionado"+beanMovimientos.getView().getEmpleadoSeleccionado().getIdEmpleado());
	 beanMovimientos.getView().getEmpleadoSeleccionado().getIdEmpleado();
	 
		
		buscarPeriodosInicio(beanMovimientos.getView().getEmpleadoSeleccionado().getIdEmpleado());
		
		newMovimientoFijoDTO.setIdEmpleado(beanMovimientos.getView().getEmpleadoSeleccionado().getIdEmpleado());
		
		if (quincenaInicial.compareTo(25) == 0) {
			newMovimientoFijoDTO.setQuincenaInicial(1);
			newMovimientoFijoDTO.setAnioInicial(FechaUtil.ejercicioActual() + 1);
		} else {
			newMovimientoFijoDTO.setQuincenaInicial(quincenaInicial);
			newMovimientoFijoDTO.setAnioInicial(FechaUtil.ejercicioActual());
		}
		if (!quincenaCerrada) {
			newMovimientoFijoDTO.setStatusRegistro("Registro creado correctamente.");
			
			if(newMovimientoFijoDTO.getIdEmpleado()!=null){
				newMovimientoFijoDTO.setIdTipoMovimiento(tipoMovimientoSeleccionado.getIdTimpoMovimiento());
			movimientoFijoService.crear(newMovimientoFijoDTO);
			JSFUtils.infoMessage("", "El movimiento se registro correctamente.");
			}
		} else {
			newMovimientoFijoDTO.setStatusRegistro("La quincnea para este empleado se encuentra cerrada, el registro no se guardo.");
			
		}
		newMovimientoFijoDTO = new MovimientoNominaDTO();
		
	}
	
	
	
	public void buscarPeriodosInicio(Integer idEmpleado) {
		quincenaCerrada = false;
		permitirAltaMovimiento = false;
		idNominaEmpleado = nominaEmpleadoService.obntenerNominaActivaPorEmpleado(idEmpleado);
		quincenaInicial = movimientoFijoService.numeroQuincena(4, FechaUtil.ejercicioActual(),
				FechaUtil.fechaActualSinHora());
		if (idNominaEmpleado == null) {
			JSFUtils.errorMessage("Atencion:", "La nomina ya no se encuentra activa, "
					+ "por lo que no se podra dar de alta el movimiento del empleado " );
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
			if (!ValidacionUtil.esMayorCero(importeQuincenal)) {
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


	public String irInicio() throws IOException {
		 MovimientosController beanMovimientos = (MovimientosController) JSFUtils.getManagedBean("movimientos");
		 beanMovimientos.cargarMivimientosPorEmpleado();
		return "/contenido/nomina/movimientos/index.xhtml?faces-config=true";
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

	public List<SelectItem> getListaConceptos() {
		return listaConceptos;
	}

	public void setListaConceptos(List<SelectItem> listaConceptos) {
		this.listaConceptos = listaConceptos;
	}

	public Integer getTerceroSeleccionado() {
		return terceroSeleccionado;
	}

	public void setTerceroSeleccionado(Integer terceroSeleccionado) {
		this.terceroSeleccionado = terceroSeleccionado;
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

	public TipoMovimientoNominaDTO getTipoMovimientoSeleccionado() {
		return tipoMovimientoSeleccionado;
	}

	public void setTipoMovimientoSeleccionado(TipoMovimientoNominaDTO tipoMovimientoSeleccionado) {
		this.tipoMovimientoSeleccionado = tipoMovimientoSeleccionado;
	}

	public MovimientoNominaDTO getNewMovimientoFijoDTO() {
		return newMovimientoFijoDTO;
	}

	public void setNewMovimientoFijoDTO(MovimientoNominaDTO newMovimientoFijoDTO) {
		this.newMovimientoFijoDTO = newMovimientoFijoDTO;
	}

	public Integer getIdUsuarioSeleccionado() {
		return idUsuarioSeleccionado;
	}

	public void setIdUsuarioSeleccionado(Integer idUsuarioSeleccionado) {
		this.idUsuarioSeleccionado = idUsuarioSeleccionado;
	}

}
