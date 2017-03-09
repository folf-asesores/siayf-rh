package mx.gob.saludtlax.rh.reporteslaborales.termino;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author Daniela
 *
 */

@ManagedBean (name = "terminoInterinato")
@SessionScoped
public class TerminoInterinatoController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9081288022997790571L;
	
	@Inject
	private TerminoInterinatoEJB terminoInterinatoEJB;
	
	private TerminoView view;
	
	@PostConstruct
	public void inicio() {
		this.view = new TerminoView();
	}

	/************* Validar *************/
	public void validatorConsulta(FacesContext context, UIComponent component, Object value) {

		String nombreComponete = component.getId();

		switch (nombreComponete) {
		case "criterio":
			Integer criterio = (Integer) value;

			if (ValidacionUtil.esNumeroPositivo(criterio)) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor ingrese un criterio de búsqueda.");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}

			break;
		default:
			JSFUtils.errorMessage("Error: ", "Validar criterio");
			break;
		}
	}

	public void buscarEmpleados() {
		String criterio = view.getCriterio();

		List<TerminoDetalleDTO> resultado = terminoInterinatoEJB.consultarPorCriterio(criterio);
		view.setTerminoDetalleDTO(resultado);
	}

	public void descargarTermino() {

		TerminoDTO terminoDTO = view.getTerminoDTO();
		TerminoInterinatoWord terminoInterinatoWord = new TerminoInterinatoWord();
		byte[] bytesWord = terminoInterinatoWord.generar(terminoDTO);

		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			ExternalContext ec = fc.getExternalContext();

			ec.responseReset();
			ec.setResponseContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
			ec.setResponseContentLength(bytesWord.length);
			ec.setResponseHeader("Content-Disposition",
					"attachment;filename=" + "TerminoInterinato.docx");

			OutputStream outputStream = ec.getResponseOutputStream();
			outputStream.write(bytesWord, 0, bytesWord.length);
			outputStream.flush();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			fc.responseComplete();
		}

	}

	public void contenidoTermino(Integer idTipoMovimiento) {
		TerminoDTO terminoDTO = terminoInterinatoEJB.obtenerTermino(idTipoMovimiento);

		view.setTerminoDTO(terminoDTO);
		this.view.setMostrarPrincipal(false);
		this.view.setMostrarTermino(true);
	}

	public void regresar() {
		this.view.setCriterio("");
		this.view.setTerminoDetalleDTO(null);
		this.view.setMostrarPrincipal(true);
		this.view.setMostrarTermino(false);
	}

	public TerminoView getView() {
		return view;
	}

	public void setView(TerminoView view) {
		this.view = view;
	}

}
