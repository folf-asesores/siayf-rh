package mx.gob.saludtlax.rh.configuracion.cuentabancaria;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import mx.gob.saludtlax.rh.configuracion.cuentabancaria.CuentaBancariaEJB;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

@ManagedBean(name = "cuentaBancaria")
@ViewScoped
public class CuentaBancariaController {

	private CuentaBancariaView view;
	@Inject
	private CuentaBancariaEJB ejb;
	
	private Boolean dialogo;
	
	@PostConstruct
	public void initCuentaBancaria() {
		view = new CuentaBancariaView();
		irPrincipal();
	}

	public String irPrincipal() {
		view.setListCuentaBancaria(ejb.obtenerCuentaBancariaLista());
		view.panelPrincipal();
		return null;
	}

	public String irNuevoCuentaBancaria() {
		view.setCuentaBancaria(ejb.nuevoCuentaBancaria());
		view.setOperacionNuevo(Boolean.TRUE);
		view.setOpEliminar(Boolean.FALSE);
		view.panelGestion();
		return null;
	}
	
	public void  eliminarCuentaBancaria() {
		ejb.eliminarCuentaBancaria(view.getCuentaBancariaSelect());
		view.panelPrincipal();
		this.dialogo=Boolean.FALSE;
		irPrincipal();
	}

	public String irGestionarCuentaBancaria() {
		view.setCuentaBancaria(ejb.obtenerCuentaBancaria(view.getCuentaBancariaSelect()));
		view.setOperacionNuevo(Boolean.FALSE);
		view.setOpEliminar(Boolean.TRUE);
		view.panelGestion();
		return null;
	}

	public CuentaBancariaView getView() {
		return view;
	}

	public String guardarCuentaBancaria() {
		if (view.getOperacionNuevo()) {
			ejb.crearCuentaBancaria(view.getCuentaBancaria());
		} else {
			ejb.actualizarCuentaBancaria(view.getCuentaBancaria());
		}
		view.panelGestion();
		irPrincipal();
		return null;
	}
	
	public void mostrarDialogo(){
		this.dialogo=Boolean.TRUE;
	}
	public void ocultarDialogo(){
		this.dialogo=Boolean.FALSE;
	}
	
	public void onRowSelectCuentaBancaria(SelectEvent event) {
		view.setDisabledIrGestionar(Boolean.FALSE);
	}

	public void onRowUnselectCuentaBancaria(UnselectEvent event) {
		view.setDisabledIrGestionar(Boolean.TRUE);
	}

	public Boolean getDialogo() {
		return dialogo;
	}

	public void setDialogo(Boolean dialogo) {
		this.dialogo = dialogo;
	}
	
//	< < < < < < Validadores > > > > > > 
	
	public void validatorCuentaBancaria(FacesContext context,
			UIComponent component, Object value) {
		String nombreComponete = component.getId();
		switch (nombreComponete) {
		case "Clave":
			Integer Clave = (Integer) value;

			if (!ValidacionUtil.esNumeroPositivo(Clave)) {
				FacesMessage facesMessage1 = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "",
						"Por favor introcuzca un ID.");
				context.addMessage(component.getClientId(), facesMessage1);
				throw new ValidatorException(facesMessage1);
			}else{
				if(Clave > 999){
					FacesMessage facesMessage1 = new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "",
							"El ID debe ser de 3 digitos.");
					context.addMessage(component.getClientId(), facesMessage1);
					throw new ValidatorException(facesMessage1);
				}
			}
			break;
		case "Descripcion":
				String Descripcion = (String) value;

				if (ValidacionUtil.esCadenaVacia(Descripcion)) {
					FacesMessage facesMessage = new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "",
							"Por favor ingrese una Descripcion.");
					context.addMessage(component.getClientId(), facesMessage);
					throw new ValidatorException(facesMessage);
			}
			break;	
		case "Banco":
			String Banco = (String) value;

			if (ValidacionUtil.esCadenaVacia(Banco)) {
				FacesMessage facesMessage = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "",
						"Por favor ingrese una Descripcion.");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}
			break;
		case "noCuenta":
			String noCuenta = (String) value;

			if (ValidacionUtil.esCadenaVacia(noCuenta)) {
				FacesMessage facesMessage = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "",
						"Por favor ingrese un Número de Cuenta.");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}else{
				if(noCuenta.length() > 11){
					FacesMessage facesMessage1 = new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "",
							"El Número de Cuenta debe ser de maximo 11 digitos.");
					context.addMessage(component.getClientId(), facesMessage1);
					throw new ValidatorException(facesMessage1);
				}
			}
			break;
		case "FF":
			String FF = (String) value;

			if (ValidacionUtil.esCadenaVacia(FF)) {
				FacesMessage facesMessage = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "",
						"Por favor ingrese una Fuente de Financiamiento.");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}
			break;
		case "EjFis":
			Integer EjFis = (Integer) value;

			if (!ValidacionUtil.esNumeroPositivo(EjFis)) {
				FacesMessage facesMessage1 = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "",
						"Por favor introcuzca un ID.");
				context.addMessage(component.getClientId(), facesMessage1);
				throw new ValidatorException(facesMessage1);
			}else{
				if(EjFis < 2000 ){
					FacesMessage facesMessage1 = new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "",
							"El Ejercicio Fiscal debe estar entre 2000 y 2050");
					context.addMessage(component.getClientId(), facesMessage1);
					throw new ValidatorException(facesMessage1);
				}else{
					if(EjFis > 2050){
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
	
}