
package mx.gob.saludtlax.rh.siif.reportarcontratos;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

@ManagedBean(name = "reportarSiifProspera")
@SessionScoped
public class ReportarSiifProsperaController {

    @Inject
    private ReportarSiifContratosEJB ejb;

    private ReportarSiifProsperaView view;

    @PostConstruct
    public void init() {
        view = new ReportarSiifProsperaView();
        view.setCuentaBancariaList(ejb.obtenerCuentaBancariaList());
        view.setTipoNominaList(ejb.obtenerTipoNominaList());
    }

    public String cancelar() {
        return "/contenido/siif/reportarSiif.xhtml?faces-redirect=true";
    }

    public ReportarSiifProsperaView getView() {
        return view;
    }

    /***
     * Import Excel
     *
     * @param event
     */
    public void uploadFile() {

        try {
            JSFUtils.infoMessage(
                    "En este momento han terminado de subir los archivos y se empieza a procesar",
                    "");
            view.setSiifBitacoraProcesada(
                    ejb.procesarContratosTheosToSIIF(view.getPaqueteEntrada()));
            view.setSiifBitacoraProcesada(
                    ejb.obtenerSiifBitacora(view.getSiifBitacoraProcesada()));// revisar proceso, no se ha movido nada

            JSFUtils.infoMessage(
                    "En este momento se han terminado de procesar los archivos",
                    "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void validatorReporteSiif(FacesContext context,
            UIComponent component, Object value) {

        String nombreComponete = component.getId();
        switch (nombreComponete) {
            case "anioCriterio":
                Integer anioCriterio = (Integer) value;
                if (!ValidacionUtil.esNumeroPositivo(anioCriterio)) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese un criterio de búsqueda.");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                } else {
                    if (anioCriterio < 999) {
                        FacesMessage facesMessage = new FacesMessage(
                                FacesMessage.SEVERITY_ERROR, "",
                                "Por favor ingrese 4 digitos");
                        context.addMessage(component.getClientId(),
                                facesMessage);
                        throw new ValidatorException(facesMessage);
                    } else {
                        if (anioCriterio > 9999) {
                            FacesMessage facesMessage = new FacesMessage(
                                    FacesMessage.SEVERITY_ERROR, "",
                                    "Por favor ingrese 4 digitos");
                            context.addMessage(component.getClientId(),
                                    facesMessage);
                            throw new ValidatorException(facesMessage);
                        }
                    }
                }
                break;
            case "periodoCriterio":
                String periodoCriterio = (String) value;
                if (ValidacionUtil.esCadenaVacia(periodoCriterio)) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese un criterio de búsqueda.");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }
                break;
            case "idCuentaBancaria":
                Integer idCuentaBancaria = (Integer) value;
                if (!ValidacionUtil.esNumeroPositivo(idCuentaBancaria)) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor seleccione una Cuenta Bancaria.");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }
                break;
            case "idTipoNomina":
                Integer idTipoNomina = (Integer) value;
                if (!ValidacionUtil.esNumeroPositivo(idTipoNomina)) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor Seleccione un Tipo de Nomina.");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }
                break;
            default:
                break;
        }
    }

}