
package mx.gob.saludtlax.rh.reporteslaborales.cambio;

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

/**
 * @author Daniela
 *
 */

@ManagedBean(name = "cambioAdscripcion")
@ViewScoped
public class CambioAdscripcionController implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2113176085297630496L;

    @Inject
    private CambioAdscripcionEJB cambioAdscripcionEJB;

    private CambioAdscripcionView view;

    @PostConstruct
    public void inicio() {
        this.view = new CambioAdscripcionView();
    }

    
    public void validatorConsulta(FacesContext context, UIComponent component, Object value) {

        String nombreComponete = component.getId();

        switch (nombreComponete) {
            case "criterio":
                Integer criterio = (Integer) value;

                if (ValidacionUtil.esNumeroPositivo(criterio)) {
                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese un criterio de búsqueda.");
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

        String criterio = this.view.getCriterio();

        List<CambioAdscripcionDetalleDTO> resultado = this.cambioAdscripcionEJB.consultarPorCriterio(criterio);
        this.view.setCambioDetalle(resultado);

    }

    public void descargarComision() {

        CambioAdscripcionDTO cambioAdscripcionDTO = this.view.getCambioAdscripcionDTO();

        CambioAdscripcionWord cambioAdscripcionWord = new CambioAdscripcionWord();
        byte[] bytesWord = cambioAdscripcionWord.generar(cambioAdscripcionDTO);

        FacesContext fc = FacesContext.getCurrentInstance();

        try {
            ExternalContext ec = fc.getExternalContext();

            ec.responseReset();
            ec.setResponseContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            ec.setResponseContentLength(bytesWord.length);
            ec.setResponseHeader("Content-Disposition", "attachment;filename=" + "CambioAdscripcion.docx");

            OutputStream outputStream = ec.getResponseOutputStream();
            outputStream.write(bytesWord, 0, bytesWord.length);
            outputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fc.responseComplete();
        }
    }

    public void contenidoComision(Integer idTipoMovimiento) {
        CambioAdscripcionDTO cambioAdscripcionDTO = this.cambioAdscripcionEJB.obtenerComisionOficial(idTipoMovimiento);
        this.view.setCambioAdscripcionDTO(cambioAdscripcionDTO);
        this.view.setMostrarPrincipal(false);
        this.view.setMostrarCambio(true);
    }

    public void regresar() {
        this.view.setCriterio("");
        this.view.setCambioDetalle(null);
        this.view.setMostrarPrincipal(true);
        this.view.setMostrarCambio(false);
    }

    public void edicion() {
        this.view.setMostrarPrincipal(false);
        this.view.setMostrarCambio(false);
        this.view.setMostrarEdicion(true);
    }

    public void guardar() {
        this.view.setMostrarPrincipal(false);
        this.view.setMostrarCambio(true);
        this.view.setMostrarEdicion(false);
    }

    /**
     * @return the view
     */
    public CambioAdscripcionView getView() {
        return this.view;
    }

    /**
     * @param view
     *            the view to set
     */
    public void setView(CambioAdscripcionView view) {
        this.view = view;
    }

}