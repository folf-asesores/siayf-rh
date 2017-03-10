/**
 * 
 */
package mx.gob.saludtlax.rh.seguridad.configuracionmoduloaccion;

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

import org.jboss.logging.Logger;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

import mx.gob.saludtlax.rh.acciones.Accion;
import mx.gob.saludtlax.rh.acciones.AccionDTO;
import mx.gob.saludtlax.rh.modulos.ConfiguracionModuloAccion;
import mx.gob.saludtlax.rh.modulos.ModuloDTO;
import mx.gob.saludtlax.rh.modulos.Modulos;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author Eduardo Mex
 *
 */
@ManagedBean(name = "crearConfigModuloAccion")
@ViewScoped
public class CrearConfigModuloAccionController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6478581873009415107L;

	private static final Logger LOGGER = Logger.getLogger(CrearConfigModuloAccionController.class);

	@Inject
	private Modulos moduloEJB;
	@Inject
	private Accion accionEJB;
	@Inject
	private ConfiguracionModuloAccion configuracionModuloAccion;

	private CrearConfigModuloAccionView view;

	@PostConstruct
	public void init() {

		this.view = new CrearConfigModuloAccionView();

		List<ModuloDTO> listaModulos = moduloEJB.listaModulos();
		this.view.setListaModulos(listaModulos);

	}

	public void accionesPorModuloSeleccionado() {

		this.view.setListaAcciones(new ArrayList<AccionDTO>());

		ModuloDTO modulo = new ModuloDTO();

		for (ModuloDTO mod : this.view.getListaModulos()) {
			if (mod.getId_modulo()
					.compareTo(this.view.getConfiguracionModuloAccionDTONew().getModulo().getId_modulo()) == 0)
				modulo = mod;
		}
		this.view.setListaAcciones(accionEJB.obtenerListaAccionesPorModulo((modulo.getId_modulo())));

		List<AccionDTO> accionSource = accionEJB.obtenerListaAccionesPorModulo((modulo.getId_modulo()));
		List<AccionDTO> accionTarget = new ArrayList<AccionDTO>();

		this.view.setPikListAcciones(new DualListModel<AccionDTO>(accionSource, accionTarget));

	}

	public void agregarConfiguracionModuloAccion() {

		List<AccionDTO> acciones = (List<AccionDTO>) this.view.getPikListAcciones().getTarget();

		this.view.getConfiguracionModuloAccionDTONew().setAcciones(acciones);

		configuracionModuloAccion.crear(this.view.getConfiguracionModuloAccionDTONew());

		JSFUtils.infoMessage("Configuración: ", "¡Se registro correctamente!");
		init();

	}

	public void onTransfer(TransferEvent event) {
		StringBuilder builder = new StringBuilder();
		for (Object item : event.getItems()) {
			builder.append(((AccionDTO) item).getDescripcion()).append("<br />");
		}

		LOGGER.debug("Acciones agregadas: " + builder.toString());

		// FacesMessage msg = new FacesMessage();
		// msg.setSeverity(FacesMessage.SEVERITY_INFO);
		// msg.setSummary("Provando transferencia");
		// msg.setDetail(builder.toString());
		//
		// FacesContext.getCurrentInstance().addMessage(null, msg);

		List<AccionDTO> acciones = (List<AccionDTO>) this.view.getPikListAcciones().getTarget();

		for (AccionDTO accion : acciones) {
			LOGGER.debug(accion.toString());
		}

	}

	public void validatorConfiguracionModuloAccion(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {

		String nombreComponete = component.getId();
		switch (nombreComponete) {

		case "modulo":
			Integer modulo = (Integer) value;

			if (!ValidacionUtil.esNumeroPositivo(modulo)) {
				FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Seleccione un modulo.");
				context.addMessage(component.getClientId(), facesMessage1);
				throw new ValidatorException(facesMessage1);
			}
			break;

		case "accion":
			Integer accion = (Integer) value;

			if (!ValidacionUtil.esNumeroPositivo(accion)) {
				FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Seleccione una acción.");
				context.addMessage(component.getClientId(), facesMessage1);
				throw new ValidatorException(facesMessage1);
			}
			break;
		}
	}

	/**************** Getters and Setters *********/
	/**
	 * @return the view
	 */
	public CrearConfigModuloAccionView getView() {
		return view;
	}

	/**
	 * @param view
	 *            the view to set
	 */
	public void setView(CrearConfigModuloAccionView view) {
		this.view = view;
	}

}
