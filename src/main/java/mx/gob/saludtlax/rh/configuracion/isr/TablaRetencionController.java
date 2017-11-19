
package mx.gob.saludtlax.rh.configuracion.isr;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

@ManagedBean(name = "tablaRetencion")
@ViewScoped
public class TablaRetencionController {
    private TablaRetencionView view;
    @Inject
    private TablaRetencionEJB ejb;

    @PostConstruct
    public void initTablaRetencion() {
        view = new TablaRetencionView();
        view.setListPeriodicidad(ejb.obtenerPeriodicidadLista());
        irPrincipal();
    }

    public String irPrincipal() {
        view.setListPeriodicidad(ejb.obtenerPeriodicidadLista());
        view.setListTablaRetencion(ejb.obtenerTablaRetencionLista());
        view.setListTablaSubsidio(ejb.obtenerTablaSubsidioLista());
        view.setListAnio(ejb.obtenerAnioLista());
        view.panelPrincipal();
        return null;
    }

    public TablaRetencionView getView() {
        return view;
    }

    public void filtrarEjercicioFiscal() {
        try {
            view.setListTablaRetencion(ejb.obtenerTablaRetencionListaPorAnio(
                    view.getEjercicioFiscal(), view.getPeriodicidad()));

            view.setListTablaSubsidio(ejb.obtenerTablaSubsidioListaPorAnio(
                    view.getEjercicioFiscal(), view.getPeriodicidad()));

        } catch (ValidatorException | ReglaNegocioException e) {
            JSFUtils.errorMessage("Error: ", e.getMessage());
            e.printStackTrace();
        }

    }

    // Opciones para la Re lIMITE iNTERORtencion

    public String irNuevoTablaRetencion() {
        view.setTablaRetencion(ejb.nuevaRetencion());
        view.setListPeriodicidad(ejb.obtenerPeriodicidadLista());
        view.setOperacionNuevo1(Boolean.TRUE);
        view.panelTablaRetencion();
        return null;
    }

    /**
     * Eliminar retencion
     */
    public void eliminarTablaRetencion() {
        try {
            ejb.eliminarTablaRetencion(view.getTablaRetencionSelect());
            view.panelPrincipal();
            irPrincipal();
            JSFUtils.infoMessage("Eliminación Retención: ",
                    "Se realizo correctamente.");
        } catch (ReglaNegocioException | ValidacionException e) {
            JSFUtils.errorMessage("Error: ", e.getMessage());
            e.printStackTrace();
        }
    }

    public String irGestionarTablaRetencion() {
        view.setTablaRetencion(
                ejb.obtenerTablaRetencion(view.getTablaRetencionSelect()));
        view.setListPeriodicidad(ejb.obtenerPeriodicidadLista());
        view.setOperacionNuevo1(Boolean.FALSE);
        view.panelTablaRetencion();
        return null;
    }

    /**
     * Crear o Actualizar Retención
     *
     * @return
     */
    public void guardarTablaRetencion() {
        try {
            if (view.getOperacionNuevo1()) {
                ejb.crearTablaRetencion(view.getTablaRetencion());
                JSFUtils.infoMessage("Registro Retención: ",
                        "Se realizo correctamente.");
            } else {
                ejb.actualizarTablaRetencion(view.getTablaRetencion());
                JSFUtils.infoMessage("Actualización Retención: ",
                        "Se realizo correctamente.");
            }
            view.panelTablaRetencion();
            irPrincipal();

        } catch (ReglaNegocioException | ValidacionException e) {
            JSFUtils.errorMessage("Error: ", e.getMessage());
            e.printStackTrace();
        }
    }
    // Opciones para Subsidio

    public String irNuevoTablaSubsidio() {
        view.setTablaSubsidio(ejb.nuevoSubsidio());
        view.setListPeriodicidad(ejb.obtenerPeriodicidadLista());
        view.setOperacionNuevo2(Boolean.TRUE);
        view.panelTablaSubsidio();
        return null;
    }

    /**
     * Eliminar Subsidio
     */
    public void eliminarTablaSubsidio() {
        try {
            ejb.eliminarTablaSubsidio(view.getTablaSubsidioSelect());
            view.panelPrincipal();
            irPrincipal();
            JSFUtils.infoMessage("Eliminación Subsidio: ",
                    "Se realizo correctamente.");
        } catch (ReglaNegocioException | ValidacionException e) {
            JSFUtils.errorMessage("Error: ", e.getMessage());
            e.printStackTrace();
        }
    }

    public String irGestionarTablaSubsidio() {
        view.setTablaSubsidio(
                ejb.obtenerTablaSubsidio(view.getTablaSubsidioSelect()));
        view.setListPeriodicidad(ejb.obtenerPeriodicidadLista());
        view.setOperacionNuevo2(Boolean.FALSE);
        view.panelTablaSubsidio();
        return null;
    }

    /***
     * Crea y Actualiza Subsidio
     */
    public void guardarTablaSubsidio() {
        try {
            if (view.getOperacionNuevo2()) {
                ejb.crearTablaSubsidio(view.getTablaSubsidio());
                JSFUtils.infoMessage("Registro Subsidio: ",
                        "Se realizo correctamente.");
            } else {
                ejb.actualizarTablaSubsidio(view.getTablaSubsidio());
                JSFUtils.infoMessage("Actualización Subsidio: ",
                        "Se realizo correctamente.");

            }
            view.panelTablaSubsidio();
            irPrincipal();

        } catch (ReglaNegocioException | ValidacionException e) {
            JSFUtils.errorMessage("Error: ", e.getMessage());
            e.printStackTrace();
        }
    }

    // Elegible o no elegible

    public void onRowSelectTablaRetencion(SelectEvent event) {
        view.setDisabledIrGestionarTablaRetencion(Boolean.FALSE);
    }

    public void onRowUnselectTablaRetencion(UnselectEvent event) {
        view.setDisabledIrGestionarTablaRetencion(Boolean.TRUE);
    }

    public void onRowSelectTablaSubsidio(SelectEvent event) {
        view.setDisabledIrGestionarTablaSubsidio(Boolean.FALSE);
    }

    public void onRowUnselectTablaSubsidio(UnselectEvent event) {
        view.setDisabledIrGestionarTablaSubsidio(Boolean.TRUE);
    }

    // <<<Vairables Utilizadas>>>

    private BigDecimal limiteInferiorR;
    private BigDecimal limiteInferiorS;

    public BigDecimal getLimiteInferiorS() {
        return limiteInferiorS;
    }

    public void setLimiteInferiorS(BigDecimal limiteInferiorS) {
        this.limiteInferiorS = limiteInferiorS;
    }

    public BigDecimal getLimiteInferiorR() {
        return limiteInferiorR;
    }

    public void setLimiteInferiorR(BigDecimal limiteInferiorR) {
        this.limiteInferiorR = limiteInferiorR;
    }

    // <<Validador Retencion>>
    public void validatorRetencion(FacesContext context, UIComponent component,
            Object value) {

        String nombreComponete = component.getId();
        switch (nombreComponete) {
            case "limiteInferiorR":
                limiteInferiorR = (BigDecimal) value;

                if (!ValidacionUtil.esNulo(limiteInferiorR)) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Ingrese un Limite Inferior");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }
                break;
            case "limiteSuperiorR":
                BigDecimal limiteSuperiorR = (BigDecimal) value;

                if (!ValidacionUtil.esNulo(limiteSuperiorR)) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Ingrese un Limite Superior");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                } else {
                    if (limiteInferiorR == null) {
                        limiteInferiorR = new BigDecimal("0");
                    } else {
                        System.out.println("limSup..." + limiteSuperiorR
                                + "...........limInf..." + limiteInferiorR);
                        if (limiteSuperiorR.compareTo(limiteInferiorR) < 0) {
                            FacesMessage facesMessage1 = new FacesMessage(
                                    FacesMessage.SEVERITY_ERROR, "",
                                    "Por favor ingrese un limite superior que no sea menor al limite inferior");
                            context.addMessage(component.getClientId(),
                                    facesMessage1);
                            throw new ValidatorException(facesMessage1);
                        }
                    }
                }
                break;
            case "cuotaFijaR":
                BigDecimal cuotaFijaR = (BigDecimal) value;

                if (!ValidacionUtil.esNulo(cuotaFijaR)) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Ingrese una Cuota Fija");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }
                break;
            case "porcentajeAplicable":
                BigDecimal porcentajeAplicable = (BigDecimal) value;

                if (!ValidacionUtil.esNulo(porcentajeAplicable)) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Ingrese una Cuota Fija");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }
                break;
            case "periodicidadR":
                Integer periodicidadR = (Integer) value;

                if (!ValidacionUtil.esNumeroPositivo(periodicidadR)) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor seleccione Periodicidad");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "ejercicioFiscalR":
                Integer ejercicioFiscalR = (Integer) value;

                if (!ValidacionUtil.esNumeroPositivo(ejercicioFiscalR)) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor introduzca un Ejercicio Fiscal");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                } else {
                    if (ejercicioFiscalR < 2000) {
                        FacesMessage facesMessage1 = new FacesMessage(
                                FacesMessage.SEVERITY_ERROR, "",
                                "El Ejercicio Fiscal debe estar entre 2000 y 2050");
                        context.addMessage(component.getClientId(),
                                facesMessage1);
                        throw new ValidatorException(facesMessage1);
                    } else {
                        if (ejercicioFiscalR > 2050) {
                            FacesMessage facesMessage1 = new FacesMessage(
                                    FacesMessage.SEVERITY_ERROR, "",
                                    "El Ejercicio Fiscal debe estar entre 2000 y 2050");
                            context.addMessage(component.getClientId(),
                                    facesMessage1);
                            throw new ValidatorException(facesMessage1);
                        }
                    }
                }
                break;
            default:
                break;
        }
    }

    // <<Validadores Subsidio>>
    public void validatorSubsidio(FacesContext context, UIComponent component,
            Object value) {

        String nombreComponete = component.getId();
        switch (nombreComponete) {
            case "limiteInferiorS":
                limiteInferiorS = (BigDecimal) value;

                if (!ValidacionUtil.esNulo(limiteInferiorS)) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Ingrese un Limite Inferior");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }
                break;
            case "limiteSuperiorS":
                BigDecimal limiteSuperiorS = (BigDecimal) value;

                if (!ValidacionUtil.esNulo(limiteSuperiorS)) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Ingrese un Limite Superior");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                } else {
                    if (limiteInferiorS == null) {
                        limiteInferiorS = new BigDecimal("0");
                    } else {
                        if (limiteSuperiorS.compareTo(limiteInferiorS) < 0) {
                            FacesMessage facesMessage1 = new FacesMessage(
                                    FacesMessage.SEVERITY_ERROR, "",
                                    "Por favor ingrese un limite superior que no sea menor al limite inferior");
                            context.addMessage(component.getClientId(),
                                    facesMessage1);
                            throw new ValidatorException(facesMessage1);
                        }
                    }
                }
                break;
            case "subsidioS":
                BigDecimal subsidioS = (BigDecimal) value;

                if (!ValidacionUtil.esNulo(subsidioS)) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Ingrese un Subsidio");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }
                break;
            case "periodicidadS":
                Integer periodicidadS = (Integer) value;

                if (!ValidacionUtil.esNumeroPositivo(periodicidadS)) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor seleccione Periodicidad");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "ejercicioFiscalS":
                Integer ejercicioFiscalS = (Integer) value;

                if (!ValidacionUtil.esNumeroPositivo(ejercicioFiscalS)) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor introduzca un Ejercicio Fiscal");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                } else {
                    if (ejercicioFiscalS < 2000) {
                        FacesMessage facesMessage1 = new FacesMessage(
                                FacesMessage.SEVERITY_ERROR, "",
                                "El Ejercicio Fiscal debe estar entre 2000 y 2050");
                        context.addMessage(component.getClientId(),
                                facesMessage1);
                        throw new ValidatorException(facesMessage1);
                    } else {
                        if (ejercicioFiscalS > 2050) {
                            FacesMessage facesMessage1 = new FacesMessage(
                                    FacesMessage.SEVERITY_ERROR, "",
                                    "El Ejercicio Fiscal debe estar entre 2000 y 2050");
                            context.addMessage(component.getClientId(),
                                    facesMessage1);
                            throw new ValidatorException(facesMessage1);
                        }
                    }
                }
                break;
            default:
                break;
        }
    }

}
