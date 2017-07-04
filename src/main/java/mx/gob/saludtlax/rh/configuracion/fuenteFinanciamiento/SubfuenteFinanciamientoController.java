package mx.gob.saludtlax.rh.configuracion.fuenteFinanciamiento;

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

import mx.gob.saludtlax.rh.util.ValidacionUtil;

@ManagedBean(name = "subfuenteFinanciamiento")
@ViewScoped
public class SubfuenteFinanciamientoController {

	private SubfuenteFinanciamientoView view;
	@Inject
	private FuenteFinanciamientoEJB ejb;

	private Integer fuenteNew;
	private Integer funteOpdNew;

	@PostConstruct
	public void initsubfuenteFinanciamiento() {
		view = new SubfuenteFinanciamientoView();
		irPrincipal();
	}

	public SubfuenteFinanciamientoView getView() {
		return view;
	}

	// <Los diferentes Regresos>

	public String irPrincipal() {
		view.setListSubfuenteFinanciamiento(ejb.obtenerSubfuenteFinanciamientoLista());
		view.panelPrincipal();
		return null;
	}

	public String irOPD() {
		return "contenido/configuracion/fuenteFinanciamientoODP.xhtml?faces-redirect=true";
	}

	public String irSub() {
		return "contenido/configuracion/subfuenteFinanciamiento.xhtml?faces-redirect=true";
	}

	public String irFF() {
		return "contenido/configuracion/fuenteFinanciamiento.xhtml?faces-redirect=true";
	}

	// <Inicia Procesos de Subfuente Financiamiento>

	public String irNuevoSubfuenteFinanciamiento() {
		view.setSubfuenteFinanciamiento(ejb.nuevoSubfuenteFinanciamiento());
		view.setListFuenteFinanciamiento(ejb.obtenerFuenteFinanciamientoLista());
		view.setListFuenteFinanciamientoOPD(ejb.obtenerFuenteFinanciamientoOPDLista());
		view.setOperacionNuevoSubfuente(Boolean.TRUE);
		view.panelGestion();
		return null;
	}

	public void eliminarSubfuenteFinanciamiento() {
		ejb.eliminarSubfuenteFinanciamiento(view.getSubfuenteFinanciamientoSelect());
		view.panelPrincipal();
		irPrincipal();
	}

	public String irGestionarSubfuenteFinanciamiento() {
		view.setSubfuenteFinanciamiento(ejb.obtenerSubfuenteFinanciamiento(view.getSubfuenteFinanciamientoSelect()));
		view.setListFuenteFinanciamiento(ejb.obtenerFuenteFinanciamientoLista());
		view.setListFuenteFinanciamientoOPD(ejb.obtenerFuenteFinanciamientoOPDLista());
		view.setOperacionNuevoSubfuente(Boolean.FALSE);
		view.panelGestion();
		return null;
	}

	public String guardarSubfuenteFinanciamiento() {
		if (view.getOperacionNuevoSubfuente()) {
			FuenteFinanciamientoDTO f = new FuenteFinanciamientoDTO();
			f.setIdFuenteFinanciamiento(fuenteNew);
			view.getSubfuenteFinanciamiento().setIdFuenteFinanciamiento(f);

			FuenteFinanciamientoOPDDTO fOpd = new FuenteFinanciamientoOPDDTO();
			fOpd.setIdFuenteFinanciamientoOPD(funteOpdNew);
			
			SubfuenteFinanciamientoDTO sf = view.getSubfuenteFinanciamiento();
			
			sf.setIdFuenteFinanciamientoOPD(fOpd);
			
			ejb.crearSubfuenteFinanciamiento(sf);
		} else {
			FuenteFinanciamientoDTO fuente = new FuenteFinanciamientoDTO();
			fuente.setIdFuenteFinanciamiento(fuenteNew);
			view.getSubfuenteFinanciamiento().setIdFuenteFinanciamiento(fuente);

			FuenteFinanciamientoOPDDTO fuenteOpd = new FuenteFinanciamientoOPDDTO();
			fuenteOpd.setIdFuenteFinanciamientoOPD(funteOpdNew);
			view.getSubfuenteFinanciamiento().setIdFuenteFinanciamientoOPD(fuenteOpd);
			ejb.actualizarSubfuenteFinanciamiento(view.getSubfuenteFinanciamiento());
		}
		view.panelGestion();
		irPrincipal();
		return null;
	}

	// <Inician los procesos botones dinamicos>

	public void onRowSelectSubfuenteFinanciamiento(SelectEvent event) {
		view.setDisabledIrGestionarSub(Boolean.FALSE);
	}

	public void onRowUnselectSubfuenteFinanciamiento(UnselectEvent event) {
		view.setDisabledIrGestionarSub(Boolean.TRUE);
	}

	// > > > > > > Validadores < < < < < <

	public void validatorSubfuente(FacesContext context, UIComponent component, Object value) {

		String nombreComponete = component.getId();
		switch (nombreComponete) {
		case "Descripcion":
			String Descripcion = (String) value;

			if (ValidacionUtil.esCadenaVacia(Descripcion)) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Ingrese una Descripción");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}
			break;
		case "B36":
			String B36 = (String) value;

			if (ValidacionUtil.esCadenaVacia(B36)) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Ingrese una Base 36");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			} else {
				if (B36.length() > 3) {
					FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
							"Base 36 es de un maximo de 3 caracteres");
					context.addMessage(component.getClientId(), facesMessage);
					throw new ValidatorException(facesMessage);
				}
			}
			break;
		case "FF":
			Integer FF = (Integer) value;

			if (!ValidacionUtil.esNumeroPositivo(FF)) {
				FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor seleccione una Fuente de Financiamiento");
				context.addMessage(component.getClientId(), facesMessage1);
				throw new ValidatorException(facesMessage1);
			}
			break;
		case "FFOPD":
			Integer FFOPD = (Integer) value;

			if (!ValidacionUtil.esNumeroPositivo(FFOPD)) {
				FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor seleccione una Subfuente de Financiamiento");
				context.addMessage(component.getClientId(), facesMessage1);
				throw new ValidatorException(facesMessage1);
			}
			break;
		default:
			break;
		}
	}

	public Integer getFuenteNew() {
		return fuenteNew;
	}

	public void setFuenteNew(Integer fuenteNew) {
		this.fuenteNew = fuenteNew;
	}

	public Integer getFunteOpdNew() {
		return funteOpdNew;
	}

	public void setFunteOpdNew(Integer funteOpdNew) {
		this.funteOpdNew = funteOpdNew;
	}
}