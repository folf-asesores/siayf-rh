package mx.gob.saludtlax.rh.configuracion.periodo;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import org.joda.time.DateTime;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import mx.gob.saludtlax.rh.configuracion.ejerciciofiscal.EjercicioFiscalEJB;
import mx.gob.saludtlax.rh.configuracion.ejerciciofiscal.PeriodoCalendarioDTO;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

@ManagedBean(name = "ejercicioFiscal")
@ViewScoped
public class EjercicioFiscalController {
	private EjercicioFiscalView view;
	@Inject
	private EjercicioFiscalEJB ejb;
	
	@PostConstruct
	public void initEjercicioFiscal() {
		view = new EjercicioFiscalView();
		view.setListPeriodicidad(ejb.obtenerTipoPeriodoLista());
		irPrincipal();
	}

	public String irPrincipal() {
		view.setListEjercicioFiscal(ejb.obtenerEjercicioFiscalLista());
		view.panelPrincipal();
		return null;
	}

	public String filtrarEjercicioFiscal() {
		view.setListEjercicioFiscal(ejb.obtenerEjercicioFiscalListaPorAnio(view.getEjercicioFiscalCriterio()));
		return null;
	}

	public String irNuevoEjercicioFiscal() {
		view.setEjercicioFiscal(ejb.nuevoEjercicioFiscal());
		view.setOperacionNuevo(Boolean.TRUE);
		view.setOpcionesPeriodo(Boolean.FALSE);
		view.panelGestion();
		return null;
	}
	
	public void  eliminarEjercicioFiscal() {
		ejb.eliminarEjercicioFiscal(view.getEjercicioFiscalSelect());
		view.panelPrincipal();
		irPrincipal();
	}

	public String irGestionarEjercicioFiscal() {
		view.setEjercicioFiscal(ejb.obtenerEjercicioFiscal(view.getEjercicioFiscalSelect()));
		view.setListTipoPeriodo(ejb.obtenerTipoPeriodoLista());
		view.setOperacionNuevo(Boolean.FALSE);
		view.setOpcionesPeriodo(Boolean.TRUE);
		view.panelGestion();
		return null;
	}

	public EjercicioFiscalView getView() {
		return view;
	}

	public String guardarEjercicioFiscal() {
		if (view.getOperacionNuevo()) {
			ejb.crearEjercicioFiscal(view.getEjercicioFiscal());
		} else {
			ejb.actualizarEjercicioFiscal(view.getEjercicioFiscal());
		}
		view.panelGestion();
		irPrincipal();
		return null;
	}
	
	public void onRowSelectEjercicioFiscal(SelectEvent event) {
		view.setDisabledIrGestionar(Boolean.FALSE);
	}

	public void onRowUnselectEjercicioFiscal(UnselectEvent event) {
		view.setDisabledIrGestionar(Boolean.TRUE);
	}
	
	public void onRowSelectPeriodoCalendario(SelectEvent event) {
		view.setDisabledEliminar(Boolean.FALSE);
	}

	public void onRowUnselectPeriodoCalendario(UnselectEvent event) {
		view.setDisabledEliminar(Boolean.TRUE);
	}
	
	
//	Opciones de Periodos
	
	public void nuevoPeriodoCalendario() {
		Integer ef = view.getEjercicioFiscal().getIdEjercicioFiscal();
		ejb.nuevoPeriodoCalendario(view.getPeriodoCalendario(),ef);
		view.getPanelGestion();
		irGestionarEjercicioFiscal();
	}
	
	public void  eliminarPeriodoCalendario() {
		if (view.getPeriodoCalendarioSelect() != null) {
			ejb.eliminarPeriodoCalendario(view.getPeriodoCalendarioSelect());
			view.getPanelGestion();
			irGestionarEjercicioFiscal();
		}
	}

	public void onRowSelectPeriodoCalendario(CellEditEvent event) {
		for(PeriodoCalendarioDTO df: view.getEjercicioFiscal().getListPeriodoCalendario()){
			System.out.println("InicioPeriodo..."+df.getInicioPeriodo()+"FinPeriodo..."+df.getFinPeriodo()+"TipoPEriodo..."+df.getIdTipoPeriodo());
		}
	}
	
//	<<<<<<Variables Utilizadas>>>>>>
	
	private Date inicio;
	private Date fin;
	private Integer EjercicioFiscal;
	
	public Date getInicio() {return inicio;}
	public void setInicio(Date inicio) {this.inicio = inicio;}

	public Integer getEjercicioFiscal() {return EjercicioFiscal;}
	public void setEjercicioFiscal(Integer ejercicioFiscal) {EjercicioFiscal = ejercicioFiscal;}

	public Date getFin() {return fin;}
	public void setFin(Date fin) {this.fin = fin;}
	
//	Date inicio = view.getEjercicioFiscalSelect().getInicio();
//	Date fin = view.getEjercicioFiscalSelect().getFin();
//	Integer EjercicioFiscal = view.getEjercicioFiscalSelect().getEjercicioFiscal();
	
//	< < < < < < Validadores > > > > > >
	
//	<<Validador de Busqueda>>
	

	public void validatorBusqueda(FacesContext context, UIComponent component,
			Object value) {
		String nombreComponete = component.getId();

		switch (nombreComponete) {
		case "ejercicioFiscal":
			Integer ejercicioFiscal = (Integer) value;
			if(ejercicioFiscal == null){
				break;
			}else{
			if(ejercicioFiscal < 2000 ){
				FacesMessage facesMessage1 = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "",
						"El Ejercicio Fiscal debe estar entre 2000 y 2050");
				context.addMessage(component.getClientId(), facesMessage1);
				throw new ValidatorException(facesMessage1);
			}else{
				if(ejercicioFiscal > 2050){
					FacesMessage facesMessage1 = new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "",
							"El Ejercicio Fiscal debe estar entre 2000 y 2050");
					context.addMessage(component.getClientId(), facesMessage1);
					throw new ValidatorException(facesMessage1);
					}
				}
			}
			break;
		default:
			break;
		}
	}
	
//	<<Validador de Ejercicio Fiscal>>
	
	public void validatorEjercicio(FacesContext context, UIComponent component,
			Object value) {

		String nombreComponete = component.getId();
		switch (nombreComponete) {
		case "EjercicioFiscal":
			EjercicioFiscal = (Integer) value;

			if (!ValidacionUtil.esNumeroPositivo(EjercicioFiscal)) {
				FacesMessage facesMessage1 = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "",
						"Por favor introduzca un Ejercicio Fiscal.");
				context.addMessage(component.getClientId(), facesMessage1);
				throw new ValidatorException(facesMessage1);
			}else{
				if(EjercicioFiscal < 2000 ){
					FacesMessage facesMessage1 = new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "",
							"El Ejercicio Fiscal debe estar entre 2000 y 2050");
					context.addMessage(component.getClientId(), facesMessage1);
					throw new ValidatorException(facesMessage1);
				}else{
					if(EjercicioFiscal > 2050){
						FacesMessage facesMessage1 = new FacesMessage(
								FacesMessage.SEVERITY_ERROR, "",
								"El Ejercicio Fiscal debe estar entre 2000 y 2050");
						context.addMessage(component.getClientId(), facesMessage1);
						throw new ValidatorException(facesMessage1);
						}
					}
				}
			break;
			case "inicio":
				inicio = (Date) value;
				
				if (inicio == null) {
					FacesMessage facesMessage1 = new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "",
							"Por favor ingrese una fecha de inicio.");
					context.addMessage(component.getClientId(), facesMessage1);
					throw new ValidatorException(facesMessage1);
				}
				else {
					if(EjercicioFiscal==null){
						EjercicioFiscal = view.getEjercicioFiscalSelect().getIdEjercicioFiscal();
					}else{
					if ((obtenerAnio(inicio)-EjercicioFiscal)!=0 ) {
						FacesMessage facesMessage1 = new FacesMessage(
								FacesMessage.SEVERITY_ERROR, "",
								"Por favor ingrese una fecha que sea del mismo Ejercicio Fiscal");
						context.addMessage(component.getClientId(), facesMessage1);
						throw new ValidatorException(facesMessage1);
					}
				}
			}
			break;	
			case "fin":
				fin = (Date) value;
				
				if (fin == null) {
					FacesMessage facesMessage1 = new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "",
							"Por favor ingrese una fecha de fin.");
					context.addMessage(component.getClientId(), facesMessage1);
					throw new ValidatorException(facesMessage1);
				}else {
						if(inicio==null){
							inicio = new Date(2000-01-01);						
						}else{
					if (fin.compareTo(inicio) < 0) {
						FacesMessage facesMessage1 = new FacesMessage(
								FacesMessage.SEVERITY_ERROR, "",
								"Por favor ingrese una fecha que no sea menor a la fecha de inicio");
						context.addMessage(component.getClientId(), facesMessage1);
						throw new ValidatorException(facesMessage1);
					}
				}
			}		
			break;
			case "periodicidad1":
				Integer periodicidad1 = (Integer) value;

				if (!ValidacionUtil.esNumeroPositivo(periodicidad1)) {
					FacesMessage facesMessage1 = new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "",
							"Por favor seleccione un Periodo");
					context.addMessage(component.getClientId(), facesMessage1);
					throw new ValidatorException(facesMessage1);
				}
//			case "periodicidad1":
//				String periodicidad1 = (String) value;
//
//				if (ValidacionUtil.esCadenaVacia(periodicidad1)) {
//					FacesMessage facesMessage = new FacesMessage(
//							FacesMessage.SEVERITY_ERROR, "",
//							"Por favor seleccione un Periodo");
//					context.addMessage(component.getClientId(), facesMessage);
//					throw new ValidatorException(facesMessage);
//				}
			break;	
		default:
			break;
		}
	}	
	
	public static Integer obtenerAnio(Date dt) {DateTime fecha = new DateTime(dt);Integer anio = fecha.getYear();return anio;}
	
//	<<Validador de Periodo Calendario>>
	
	public void validatorPeriodo(FacesContext context, UIComponent component,
			Object value) {

		String nombreComponete = component.getId();
		switch (nombreComponete) {
		case "inicioPeriodo":
			Date inicioPeriodo = (Date) value;
			
			if (inicioPeriodo == null) {
				FacesMessage facesMessage1 = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "",
						"Por favor ingrese una fecha de inicio periodo.");
				context.addMessage(component.getClientId(), facesMessage1);
				throw new ValidatorException(facesMessage1);
			}
		break;	
		case "finPeriodo":
			Date finPeriodo = (Date) value;
			
			if (finPeriodo == null) {
				FacesMessage facesMessage1 = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "",
						"Por favor ingrese una fecha de fin periodo.");
				context.addMessage(component.getClientId(), facesMessage1);
				throw new ValidatorException(facesMessage1);
			}
//			else {
//				if (finPeriodo.compareTo(inicioPeriodo) < 0) {
//					FacesMessage facesMessage1 = new FacesMessage(
//							FacesMessage.SEVERITY_ERROR, "",
//							"Por favor ingrese una fecha que no sea menor a la fecha de inicio periodo");
//					context.addMessage(component.getClientId(), facesMessage1);
//					throw new ValidatorException(facesMessage1);
//			}
//		}
		break;
		case "tp":
			Integer tp = (Integer) value;

			if (!ValidacionUtil.esNumeroPositivo(tp)) {
				FacesMessage facesMessage1 = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "",
						"Por favor seleccione un Tipo de Periodo");
				context.addMessage(component.getClientId(), facesMessage1);
				throw new ValidatorException(facesMessage1);
			}
		
		break;	
	default:
		break;
	}
}	
		
}