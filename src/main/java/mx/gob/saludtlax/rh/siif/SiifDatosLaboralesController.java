
package mx.gob.saludtlax.rh.siif;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import mx.gob.saludtlax.rh.util.ValidacionUtil;

@ManagedBean(name = "siifDatosLaborales")
@ViewScoped
public class SiifDatosLaboralesController {
    private SiifDatosLaboralesView view;

    @Inject
    private SiifDatosLaboralesEJB ejb;

    @Inject
    private DatosPersonales datoPersonal;

    @ManagedProperty(value = "#{consultaDatosPersonales.view}")
    private ConsultaPersonalesView datosPersonalesView;

    @EJB
    private SiifDatosLaboralesEJB manufacturerFacade; //el EJB

    @PostConstruct
    public void initDatosLaborales() {
        view = new SiifDatosLaboralesView();
        irPrincipal();
    }

    public void datosLaboralesChangeListener(AjaxBehaviorEvent event) {
        view.setRfcCriterio(datosPersonalesView.getRfcCriterio());
        view.setListSiifDatosLaborales(ejb.obtenerDatosLaboralesListaPorCriterios(view.getRfcCriterio()));
        view.setOculto(Boolean.TRUE);
    }

    public String irPrincipal() {
        view.setListSiifDatosLaborales(ejb.obtenerDatosLaboralesLista());
        view.panelPrincipal();
        return null;
    }

    public String filtrarPorCriterios() {
        view.setListSiifDatosLaborales(ejb.obtenerDatosLaboralesListaPorCriterios(view.getRfcCriterio()));
        view.setOculto(Boolean.TRUE);
        return null;
    }

    public String filtrarPorRFC() {
        view.setRfcCriterio(datosPersonalesView.getRfcCriterio());
        view.setListSiifDatosLaborales(ejb.obtenerDatosLaboralesListaPorCriterios(view.getRfcCriterio()));
        view.setOculto(Boolean.TRUE);

        datosPersonalesView.setListDatosPersonalesLista(datoPersonal.obtenerlistaDatosPersonalesPorCriterio(view.getRfcCriterio()));
        //		this.datosPersonalesView.setOculto(Boolean.TRUE);
        return null;
    }

    public SiifDatosLaboralesView getView() {
        return view;
    }

    //	Opciones para Siif Datos Laborales
    // TODO
    public String irNuevoDatosLaborales() {
        view.setSiifDatosLaborales(ejb.nuevoDatosLaborales(""));
        view.setOperacionNuevo(Boolean.TRUE);
        view.setListDependencia(ejb.obtenerDependenciaLista());
        view.setListUnidad(ejb.obtenerUnidadLista());
        view.setListNombramiento(ejb.obtenerNombramientoLista());
        view.setListPuesto(ejb.obtenerPuesto());
        //		view.setListPlaza(ejb.obtenerPlaza());
        view.setListFF(ejb.obtenerFuenteFinanciamiento());
        view.setListSF(ejb.obtenerSubfuenteFinanciamiento());
        view.panelDatosLaborales();
        return null;
    }

    public void eliminarDatosLaborales() {
        ejb.eliminarDatosLaborales(view.getSiifDatosLaboralesSelect());
        view.panelPrincipal();
        irPrincipal();
    }

    public String irGestionarDatosLaborales() {
        view.setSiifDatosLaborales(ejb.obtenerDatosLaborales(view.getSiifDatosLaboralesSelect()));
        view.setOperacionNuevo(Boolean.FALSE);
        view.setListDependencia(ejb.obtenerDependenciaLista());
        view.setListUnidad(ejb.obtenerUnidadLista());
        view.setListNombramiento(ejb.obtenerNombramientoLista());
        view.setListPuesto(ejb.obtenerPuesto());
        //		view.setListPlaza(ejb.obtenerPlaza());
        view.setListFF(ejb.obtenerFuenteFinanciamiento());
        view.setListSF(ejb.obtenerSubfuenteFinanciamiento());
        view.panelDatosLaborales();
        ;
        return null;
    }

    public String guardarDatosLaborales() {
        String rfc = view.getSiifDatosLaborales().getRfc();
        if (view.getOperacionNuevo()) {
            ejb.crearDatosLaborales(view.getSiifDatosLaborales(), rfc);
        } else {
            ejb.actualizarDatosLaborales(view.getSiifDatosLaborales());
        }
        view.panelDatosLaborales();
        irPrincipal();
        return null;
    }

    //Variables

    public void onRowSelectDatosLaborales(SelectEvent event) {
        view.setDisabledIrGestionarDatosLaborales(Boolean.FALSE);
    }

    public void onRowUnselectDatosLaborales(UnselectEvent event) {
        view.setDisabledIrGestionarDatosLaborales(Boolean.TRUE);
    }

    public ConsultaPersonalesView getDatosPersonalesView() {
        return datosPersonalesView;
    }

    public void setDatosPersonalesView(ConsultaPersonalesView datosPersonalesView) {
        this.datosPersonalesView = datosPersonalesView;
    }

    public void validatorBusqueda(FacesContext context, UIComponent component, Object value) {

        String nombreComponete = component.getId();
        switch (nombreComponete) {
            case "rfcCr":
                String rfcCr = (String) value;
                if (ValidacionUtil.esCadenaVacia(rfcCr)) {
                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese un criterio de búsqueda.");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                } else {
                    if (rfcCr.length() < 5) {
                        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese más de 4 caracteres");
                        context.addMessage(component.getClientId(), facesMessage);
                        throw new ValidatorException(facesMessage);
                    }
                }
                break;
            default:
                break;
        }
    }

    public void validatorDatosLaborales(FacesContext context, UIComponent component, Object value) {
        String nombreComponete = component.getId();
        switch (nombreComponete) {
            case "rfc2":
                String rfc2 = (String) value;
                if (ValidacionUtil.esCadenaVacia(rfc2)) {
                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese un RFC.");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                } else {
                    if (!ValidacionUtil.validarRfc(rfc2)) {
                        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "El formato de RFC que introdujo es incorrecto");
                        context.addMessage(component.getClientId(), facesMessage);
                        throw new ValidatorException(facesMessage);
                    }
                }
                break;
            case "plaza":
                String plaza = (String) value;
                if (ValidacionUtil.esCadenaVacia(plaza)) {
                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese una plaza");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }
                break;
            case "proyecto":
                Integer proyecto = (Integer) value;
                if (!ValidacionUtil.esNumeroPositivo(proyecto)) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese un proyecto");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "dependencia":
                Integer dependencia = (Integer) value;
                if (!ValidacionUtil.esNumeroPositivo(dependencia)) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor selecciona una dependencia");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "unidad":
                Integer unidad = (Integer) value;
                if (!ValidacionUtil.esNumeroPositivo(unidad)) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor selecciona una unidad responsable");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "nombramiento":
                String nombramiento = (String) value;
                if (ValidacionUtil.esCadenaVacia(nombramiento)) {
                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor selecciona un nombramiento");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }
                break;
            case "puesto":
                String puesto = (String) value;
                if (ValidacionUtil.esCadenaVacia(puesto)) {
                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor selecciona un puesto");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }
                break;
            case "sindicato":
                Integer sindicato = (Integer) value;
                if (sindicato == null) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor selecciona si es de sindicato");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "habilitado":
                Integer habilitado = (Integer) value;

                if (habilitado == null) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor selecciona si esta habilitado");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "fingreso":
                Date fingreso = (Date) value;
                if (fingreso == null) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese una fecha de ingreso");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "noQ":
                Integer noQ = (Integer) value;
                if (noQ == null) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese un Numero de Quinquenio");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "sdoM":
                BigDecimal sdoM = (BigDecimal) value;

                if (sdoM == null) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese un Sueldo Mensual");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "percepcion":
                BigDecimal percepcion = (BigDecimal) value;
                if (percepcion == null) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese una Percepcion Complementaria");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "despensa":
                BigDecimal despensa = (BigDecimal) value;
                if (despensa == null) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese una Despensa");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "incA":
                BigDecimal incA = (BigDecimal) value;
                if (incA == null) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese un Incentivo de Ahorro");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "compensacion":
                BigDecimal compensacion = (BigDecimal) value;
                if (compensacion == null) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese una Compensacion");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "quin":
                BigDecimal quin = (BigDecimal) value;
                if (quin == null) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese un Quinquenio");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "cuenta":
                String cuenta = (String) value;
                if (ValidacionUtil.esCadenaVacia(cuenta)) {
                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingresa un No. de Cuenta");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }
                break;
            case "trecurso":
                Integer trecurso = (Integer) value;

                if (!ValidacionUtil.esNumeroPositivo(trecurso)) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingresa un tipo de recurso");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "policia":
                Integer policia = (Integer) value;
                if (policia == null) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor seleccione si es Policia o no");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "idFF":
                Integer idFF = (Integer) value;
                if (!ValidacionUtil.esNumeroPositivo(idFF)) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor seleccione una Fuente de Financiamiento");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "idSF":
                Integer idSF = (Integer) value;
                if (!ValidacionUtil.esNumeroPositivo(idSF)) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor seleccione una Subfuente de Financiamiento");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "idEE":
                String idEE = (String) value;
                if (ValidacionUtil.esCadenaVacia(idEE)) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese un Estado de Empleado");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            default:
                break;
        }

    }

}