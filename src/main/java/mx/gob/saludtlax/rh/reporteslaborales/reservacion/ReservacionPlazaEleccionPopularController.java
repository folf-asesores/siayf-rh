package mx.gob.saludtlax.rh.reporteslaborales.reservacion;

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

@ManagedBean (name = "reservacionPlazaEleccionPopular")
@SessionScoped
public class ReservacionPlazaEleccionPopularController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5993921032049953176L;
	
	@Inject
	private ReservacionPlazaEleccionPopularEJB reservacionPlazaEleccionPopularEJB;

	private ReservacionView view;

	@PostConstruct
	public void inicio() {
		this.view = new ReservacionView();
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

		List<ReservacionDetalleDTO> resultado = reservacionPlazaEleccionPopularEJB.consultarPorCriterio(criterio);
		view.setReservacionDetalleDTO(resultado);
	}

	public void descargarReservacionPlazaEleccionPopular() {
		ReservacionDTO reservacionDTO = view.getReservacionDTO();

		ReservacionPlazaEleccionPopularWord reservacionPlazaEleccionPopularWord = new ReservacionPlazaEleccionPopularWord();
		byte[] bytesWord = reservacionPlazaEleccionPopularWord.generar(reservacionDTO);
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			ExternalContext ec = fc.getExternalContext();

			ec.responseReset();
			ec.setResponseContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
			ec.setResponseContentLength(bytesWord.length);
			ec.setResponseHeader("Content-Disposition", "attachment;filename=" + "ReservacionPlazaEleccionPopular.docx");

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

	public void contenidoReservacionOtra(Integer idTipoMovimiento) {
		ReservacionDTO reservacionDTO = reservacionPlazaEleccionPopularEJB.obtenerReservacionOtra(idTipoMovimiento);

		view.setReservacionDTO(reservacionDTO);
		this.view.setMostrarPrincipal(false);
		this.view.setMostrarReservacion(true);
	}

	public void regresar() {
		this.view.setCriterio("");
		this.view.setReservacionDetalleDTO(null);
		this.view.setMostrarPrincipal(true);
		this.view.setMostrarReservacion(false);
	}

	/************** Getters and Setters ****************/

	public ReservacionView getView() {
		return view;
	}

	public void setView(ReservacionView view) {
		this.view = view;
	}

}
