package mx.gob.saludtlax.rh.reporteslaborales.comision;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

@ManagedBean(name = "comisionOficial")
@ViewScoped
public class ComisionOficialController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2113176085297630496L;

	@Inject
	private ComisionEJB comisionEJB;
	
	private ComisionOficialView view;
	
	@PostConstruct
	public void inicio() {
		this.view = new ComisionOficialView();
	}
	
	/************* Validar *************/
	public void validatorConsulta(FacesContext context, UIComponent component, Object value){
		
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
		
		List<ComisionDetalleDTO> resultado = comisionEJB.consultarPorCriterio(criterio);
		view.setComisionDetalle(resultado);
	}
	
	public void descargarComision() {
		
		ComisionOficialDTO comisionOficialDTO = view.getComisionOficialDTO();
		
		WordComisionOficial wordComisionOficial = new WordComisionOficial();
		byte[] bytesWord = wordComisionOficial.generar(comisionOficialDTO);
		
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
            ExternalContext ec = fc.getExternalContext();

			ec.responseReset();
			ec.setResponseContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
			ec.setResponseContentLength(bytesWord.length);
			ec.setResponseHeader("Content-Disposition", "attachment;filename=" + "ComisionOficial.docx");

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
	
	public void contenidoComision(Integer idTipoMovimiento) {
		ComisionOficialDTO comisionOficialDTO = comisionEJB.obtenerComisionOficial(idTipoMovimiento);
		view.setComisionOficialDTO(comisionOficialDTO);
		this.view.setMostrarPrincipal(false);
		this.view.setMostrarComision(true);
	}
	
	public void regresar() {
		this.view.setCriterio("");
		this.view.setComisionDetalle(null);
		this.view.setMostrarPrincipal(true);
		this.view.setMostrarComision(false);
	}	
	
	/************** Getters and Setters ****************/
	
	/**
	 * @return the view
	 */
	public ComisionOficialView getView() {
		return view;
	}

	/**
	 * @param view
	 *            the view to set
	 */
	public void setView(ComisionOficialView view) {
		this.view = view;
	}
	
	
}
