/**
 * Copyright © 2016
 */
package mx.gob.saludtlax.rh.configuracion.puestogeneral;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.catalogos.Catalogo;
import mx.gob.saludtlax.rh.catalogos.CatalogoDTO;
import mx.gob.saludtlax.rh.excepciones.BusinessException;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.SelectItemsUtil;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author Eduardo Mex
 * @email Lic.Eduardo_Mex@hotmail.com
 * @version 1.0
 * @since 21/07/2016 13:42:48
 */
@ManagedBean(name = "puestoGeneral")
@ViewScoped
public class PuestoGeneralController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7800298033568387061L;

	@Inject
	private PuestoGeneral puestoGeneral;
	@Inject
	private Catalogo catalogo;

	private PuestoGeneralView view;

	@PostConstruct
	private void init() {
		this.view = new PuestoGeneralView();
		this.obtenerListaPuestoGeneral();

		List<CatalogoDTO> listaTipoPuesto = catalogo.consultarTipoPuesto();
		List<CatalogoDTO> listaRamas = catalogo.consultarRamas();

		this.view.setListaTipoPuesto(SelectItemsUtil.listaCatalogos(listaTipoPuesto));
		this.view.setListaRama(SelectItemsUtil.listaCatalogos(listaRamas));

	}

	public void obtenerListaPuestoGeneral() {
		List<PuestoGeneralDTO> listaPuestoGeneralDTOs = puestoGeneral.consultarListaPuestoGeneral();
		this.view.setListaPuestoGeneral(listaPuestoGeneralDTOs);
	}

	public void accionRegistra() {
		try {

			puestoGeneral.crearPuestoGeneral(this.view.getPuestoGeneralDTO());
			cerrarPanelesSecundarios();
			obtenerListaPuestoGeneral();
			JSFUtils.infoMessage("Registro Puesto General: ", "Se realizo correctamente...");

		} catch (BusinessException ex) {
			JSFUtils.errorMessage("Error: ", ex.getMessage());
		}
	}

	public void accionActualizar() {
		try {

			puestoGeneral.actualizarPuestoGeneral(this.view.getPuestoGeneralDTOSeleccionado());
			cerrarPanelesSecundarios();
			obtenerListaPuestoGeneral();
			JSFUtils.infoMessage("Actualización Puesto General: ", "Se realizo correctamente...");

		} catch (BusinessException ex) {
			JSFUtils.errorMessage("Error: ", ex.getMessage());
		}
	}

	public void eliminarPuestoGeneral() {
		try {
			puestoGeneral.eliminarPuestoGeneral(this.view.getIdPuestoGeneral());
			cerrarDialogoAccionEliminar();
			obtenerListaPuestoGeneral();
			JSFUtils.infoMessage("Eliminación Puesto General: ", "Se realizo correctamente...");
		} catch (BusinessException ex) {
			JSFUtils.errorMessage("Error: ", ex.getMessage());
		}
	}

	public void mostrarDialogoNuevoRegistro() {
		this.view.setPuestoGeneralDTO(new PuestoGeneralDTO());
		this.view.setPanelRegistro(true);
		this.view.setPanelActualizar(false);
	}

	public void mostrarDialogoActualizarRegistro(PuestoGeneralDTO puestoGeneralDTO) {
		this.view.setPuestoGeneralDTOSeleccionado(puestoGeneralDTO);
		this.view.setPanelRegistro(false);
		this.view.setPanelActualizar(true);
	}

	public void mostrarDialogoEliminar(Integer idPuestoGeneral) {
		this.view.setIdPuestoGeneral(idPuestoGeneral);
		this.view.setDialogoEliminar(true);
		this.view.setPanelRegistro(false);
		this.view.setPanelActualizar(false);
	}

	public void cerrarPanelesSecundarios() {
		this.view.setPuestoGeneralDTO(new PuestoGeneralDTO());
		this.view.setPuestoGeneralDTOSeleccionado(new PuestoGeneralDTO());
		this.view.setPanelRegistro(false);
		this.view.setPanelActualizar(false);
		this.view.setDialogoEliminar(false);
	}

	public void cerrarDialogoAccionEliminar() {
		this.view.setPuestoGeneralDTO(new PuestoGeneralDTO());
		this.view.setDialogoEliminar(false);
		this.view.setPanelRegistro(false);
		this.view.setPanelActualizar(false);
	}

	public void validarCampoNuevo(FacesContext context, UIComponent component, Object value) throws ValidatorException {

		String nombreComponente = component.getId();
		String contexto = "Campo requerido.";

		switch (nombreComponente) {

		case "codigo":

			String codigo = String.valueOf(value);

			if (ValidacionUtil.esCadenaVacia(codigo)) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, contexto,
						"Ingrese la codigo");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}
			if (!ValidacionUtil.esCadenaVacia(codigo)) {
				Boolean claveValido = false;

				claveValido = puestoGeneral.existeCodigo(codigo.trim());

				if (claveValido) {
					FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, contexto,
							"el codigo ya existe");
					context.addMessage(component.getClientId(), facesMessage);
					throw new ValidatorException(facesMessage);
				}
			}

			break;

		case "puesto":

			String puesto = String.valueOf(value);

			if (ValidacionUtil.esCadenaVacia(puesto)) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, contexto,
						"Ingrese el nombre del puesto");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}

			break;

		case "tipoTabulador":

			Integer tipoTabulador = (Integer) value;

			if (!ValidacionUtil.esNumeroPositivo(tipoTabulador)) {
				this.view.getPuestoGeneralDTO().setIdTipoPuesto(0);
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, contexto,
						"Seleccione el tipo de puesto");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);

			}

			break;

		case "rama":

			Integer rama = (Integer) value;

			if (!ValidacionUtil.esNumeroPositivo(rama)) {
				this.view.getPuestoGeneralDTO().setIdRama(0);
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, contexto,
						"Seleccione la rama");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}

			break;

		default:
			break;
		}

	}

	public void validarCampoActualizar(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {

		String nombreComponente = component.getId();
		String contexto = "Campo requerido.";

		switch (nombreComponente) {

		case "codigoA":

			String codigo = String.valueOf(value);

			if (ValidacionUtil.esCadenaVacia(codigo)) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, contexto,
						"Ingrese la codigo");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}
			if (!ValidacionUtil.esCadenaVacia(codigo)) {
				Boolean claveValido = false;

				claveValido = puestoGeneral.existeCodigoIdPuesto(
						this.view.getPuestoGeneralDTOSeleccionado().getIdPuestoGeneral(), codigo.trim());

				if (!claveValido) {
					claveValido = puestoGeneral.existeCodigo(codigo.trim());

					if (claveValido) {
						FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, contexto,
								"el codigo ya existe");
						context.addMessage(component.getClientId(), facesMessage);
						throw new ValidatorException(facesMessage);
					}
				}
			}

			break;

		case "puestoA":

			String puesto = String.valueOf(value);

			if (ValidacionUtil.esCadenaVacia(puesto)) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, contexto,
						"Ingrese el nombre del puesto");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}

			break;

		case "tipoTabuladorA":

			Integer tipoTabulador = (Integer) value;

			if (!ValidacionUtil.esNumeroPositivo(tipoTabulador)) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, contexto,
						"Seleccione el tipo de puesto");
				context.addMessage(component.getClientId(), facesMessage);

				throw new ValidatorException(facesMessage);
			}

			break;

		case "ramaA":

			Integer rama = (Integer) value;

			if (!ValidacionUtil.esNumeroPositivo(rama)) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, contexto,
						"Seleccione la rama");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}

			break;

		default:
			break;
		}

	}

	
	/**
	 * @return the view
	 */
	public PuestoGeneralView getView() {
		return view;
	}

	/**
	 * @param view
	 *            the view to set
	 */
	public void setView(PuestoGeneralView view) {
		this.view = view;
	}

}
