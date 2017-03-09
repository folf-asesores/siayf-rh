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

@ManagedBean(name = "reservacionPlazaConfianza")
@SessionScoped
public class ReservacionPlazaConfianzaController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7359263173765732160L;

	@Inject
	private ReservacionPlazaConfianzaEJB reservacionPlazaConfianzaEJB;

	private ReservacionView view;
	
	private String nombre = "[nombre]";

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

		List<ReservacionDetalleDTO> resultado = reservacionPlazaConfianzaEJB.consultarPorCriterio(criterio);
		view.setReservacionDetalleDTO(resultado);
	}

	public void descargarReservacionPlazaConfianza() {
		ReservacionDTO reservacionDTO = view.getReservacionDTO();
		
		Integer idMovimientoEmpledo  = reservacionPlazaConfianzaEJB.guardar(reservacionDTO);
		ReservacionDTO reservacionDTOPersistida = reservacionPlazaConfianzaEJB.obtenerReservacion(idMovimientoEmpledo); 

		ReservacionPlazaConfianzaWord wordReservacionPlazaConfianza = new ReservacionPlazaConfianzaWord();
		byte[] bytesWord = wordReservacionPlazaConfianza.generar(reservacionDTOPersistida);
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			ExternalContext ec = fc.getExternalContext();

			ec.responseReset();
			ec.setResponseContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
			ec.setResponseContentLength(bytesWord.length);
			ec.setResponseHeader("Content-Disposition", "attachment;filename=" + "ReservacionPlazaConfianza.docx");

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

	public void contenidoReservacion(Integer idTipoMovimiento) {
		ReservacionDTO reservacionDTO = reservacionPlazaConfianzaEJB.obtenerReservacion(idTipoMovimiento);

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
	
	public void edicion(){
		this.view.setMostrarPrincipal(false);
		this.view.setMostrarReservacion(false);
		this.view.setMostrarEdicion(true);
	}
	
	public void guardar() {
		this.view.setMostrarPrincipal(false);
		this.view.setMostrarReservacion(true);
		this.view.setMostrarEdicion(false);
	}

	/************** Getters and Setters ****************/

	public ReservacionView getView() {
		return view;
	}

	public void setView(ReservacionView view) {
		this.view = view;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
}
