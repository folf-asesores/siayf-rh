/*
 *
 */

package mx.gob.saludtlax.rh.contrato.impresion;

import java.io.IOException;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mx.gob.saludtlax.rh.contrato.Contrato;
import mx.gob.saludtlax.rh.contrato.ContratoDTO;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.reportes.AdministradorReportes;
import mx.gob.saludtlax.rh.seguridad.ConfiguracionConst;
import mx.gob.saludtlax.rh.seguridad.usuario.UsuarioDTO;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.SelectItemsUtil;
import mx.gob.saludtlax.rh.util.TipoArchivo;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 *
 * @author Eduardo Mex

 * @version 1.0
 * @since 14:26:01 09/09/2016
 */
@ManagedBean(name = "impresionContrato")
@ViewScoped
public class ImpresionContratoController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4479003057199038228L;

    @Inject
    private Contrato contrato;

    private ImpresionContratoView view;

    @PostConstruct
    public void init() {
        view = new ImpresionContratoView();

        obtenerListaContratoEmpleado();

        view.setListaTipoContrato(SelectItemsUtil.listaTiposContratosEmpleados());
    }

    public void obtenerListaContratoEmpleado() {
        try {

            List<ContratoDTO> listaContrato = contrato.listaContrato();

            if (!listaContrato.isEmpty()) {
                view.setListaContrato(listaContrato);
            }

        } catch (ValidacionException validacionException) {
            throw new ValidacionException(validacionException.getMessage(), null);
        } catch (ReglaNegocioException reglaNegocioException) {
            throw new ReglaNegocioException(reglaNegocioException.getMessage(), reglaNegocioException.getCodigoError());
        }
    }

    public void obtenerListaContratoEmpleadoPorTipo() {
        try {

            List<ContratoDTO> listaContrato = contrato.obtenerListaContratoPorTipo(view.getTipoContrato());

            if (!listaContrato.isEmpty()) {
                view.setListaContrato(listaContrato);
            } else {
                view.setListaContrato(new ArrayList<ContratoDTO>());
            }

        } catch (ValidacionException validacionException) {
            throw new ValidacionException(validacionException.getMessage(), null);
        } catch (ReglaNegocioException reglaNegocioException) {
            throw new ReglaNegocioException(reglaNegocioException.getMessage(), reglaNegocioException.getCodigoError());
        }
    }

    public void descargarContrato() {
        try {

            contrato.actualizarContratoPorImpresion(view.getIdContratoSeleccionado(), view.getNumeroContrato());

            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            HttpSession httpSession = request.getSession(false);
            UsuarioDTO usuario = (UsuarioDTO) httpSession.getAttribute(ConfiguracionConst.SESSION_ATRIBUTE_LOGGED_USER);

            String[] parametros = { "ID_USUARIO", String.valueOf(usuario.getIdUsuario()), "REPORTE_NOMBRE", "contrato-individual", "TIPO_REPORTE", "docx",
                    "ID_CONTRATO", String.valueOf(view.getIdContratoSeleccionado()) };

            AdministradorReportes admintradorReportes = new AdministradorReportes();
            String referencia = admintradorReportes.obtenerReferencia(parametros);

            view.setBytes(admintradorReportes.obtenerReporte(referencia));

            if (view.getBytes() != null) {
                JSFUtils.descargarArchivo(view.getBytes(), "CONTRATO_INDIVIDUAL_DE_TRABAJO_POR_TIEMPO_DETERMINADO", TipoArchivo.getMIMEType("docx"));

            }

        } catch (NullPointerException | IllegalArgumentException | IOException exception) {
            System.err.println(exception.getMessage());
        } catch (ReglaNegocioException reglaNegocioException) {
            throw new ReglaNegocioException(reglaNegocioException.getMessage(), reglaNegocioException.getCodigoError());
        } catch (ValidacionException validacionException) {
            System.err.println(validacionException.getMessage());
            throw new ValidacionException(validacionException.getMessage(), null);
        }
    }

    public void mostrarConfirmacionDescarga(Integer idContratoSeleccionado, boolean impreso, String numeroContrato) {
        if (impreso) {
            view.setMostrarReimpresion(true);
            view.setNumeroContrato(numeroContrato);
        } else {
            view.setMostrarConfirmacionImpresion(true);
        }
        view.setIdContratoSeleccionado(idContratoSeleccionado);

        view.setMostrarPrincipal(false);
    }

    public String regresarModulo() {
        return "/contenido/empleado/impresionContrato.xhtml?faces-redirect=true.";
    }

    public void validatorTipoContrato(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        String nombreComponete = component.getId();

        switch (nombreComponete) {

            case "tipoContrato":
                Integer tipoContrato = (Integer) value;

                obtenerListaContratoEmpleado();

                if (!ValidacionUtil.esNumeroPositivo(tipoContrato)) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor seleccione un tipo contrato.");
                    context.addMessage(component.getClientId(), facesMessage1);

                    throw new ValidatorException(facesMessage1);
                }
                break;
            default:
                JSFUtils.errorMessage("Error: ", "validaciòn incorrecta...");
                break;
        }

    }

    public void validatorNumeroContrato(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        String nombreComponete = component.getId();

        switch (nombreComponete) {

            case "numeroContrato":
                String numeroContrato = (String) value;

                if (ValidacionUtil.esCadenaVacia(numeroContrato)) {
                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese el numero de cuenta.");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }
                break;
            default:
                JSFUtils.errorMessage("Error: ", "validaciòn incorrecta...");
                break;
        }

    }

    /**
     * @return the view
     */
    public ImpresionContratoView getView() {
        return view;
    }

    /**
     * @param view
     *            the view to set
     */
    public void setView(ImpresionContratoView view) {
        this.view = view;
    }

}
