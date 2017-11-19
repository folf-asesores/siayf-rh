
package mx.gob.saludtlax.rh.reporteslaborales.reincorporacion;

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

@ManagedBean(name = "reincorporacionBase")
@SessionScoped
public class ReincorporacionBaseController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -7359263173765732160L;

    @Inject
    private ReincorporacionBaseEJB reincorporacionBaseEJB;

    private ReincorporacionBaseView view;

    private String nombre = "[nombre]";

    @PostConstruct
    public void inicio() {
        view = new ReincorporacionBaseView();
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

        String criterio = view.getCriterio();

        List<ReincorporacionBaseDetalleDTO> resultado = reincorporacionBaseEJB.consultarPorCriterio(criterio);
        view.setReincorporacionBaseDetalleDTO(resultado);
    }

    public void descargarReservacionPlazaConfianza() {
        ReincorporacionBaseDTO reincorporacionBaseDTO = view.getReincorporacionBaseDTO();

        ReincorporacionBaseWord reincorporacionBaseWord = new ReincorporacionBaseWord();
        byte[] bytesWord = reincorporacionBaseWord.generar(reincorporacionBaseDTO);
        FacesContext fc = FacesContext.getCurrentInstance();

        try {
            ExternalContext ec = fc.getExternalContext();

            ec.responseReset();
            ec.setResponseContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            ec.setResponseContentLength(bytesWord.length);
            ec.setResponseHeader("Content-Disposition", "attachment;filename=" + "ReincorporacionBase.docx");

            OutputStream outputStream = ec.getResponseOutputStream();
            outputStream.write(bytesWord, 0, bytesWord.length);
            outputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fc.responseComplete();
        }
    }

    public void contenidoReservacion(Integer idTipoMovimiento) {
        ReincorporacionBaseDTO reincorporacionBaseDTO = reincorporacionBaseEJB.obtenerReincorporacion(idTipoMovimiento);

        view.setReincorporacionBaseDTO(reincorporacionBaseDTO);
        view.setMostrarPrincipal(false);
        view.setMostrarReincorporacion(true);
    }

    public void regresar() {
        view.setCriterio("");
        view.setReincorporacionBaseDetalleDTO(null);
        view.setMostrarPrincipal(true);
        view.setMostrarReincorporacion(false);
    }

    public void edicion() {
        view.setMostrarPrincipal(false);
        view.setMostrarReincorporacion(false);
        view.setMostrarEdicion(true);
    }

    public void guardar() {
        view.setMostrarPrincipal(false);
        view.setMostrarReincorporacion(true);
        view.setMostrarEdicion(false);
    }

    public ReincorporacionBaseView getView() {
        return view;
    }

    public void setView(ReincorporacionBaseView view) {
        this.view = view;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}