
package mx.gob.saludtlax.rh.siif;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import mx.gob.saludtlax.rh.util.ValidacionUtil;

@ManagedBean(name = "consultaDatosPersonales")
@SessionScoped
public class DatosPersonalesController implements Serializable {

    private static final long serialVersionUID = 1545175823479673131L;
    String rfcOriginal;

    @Inject
    private DatosPersonales datoPersonal;

    @Inject
    private SiifDatosLaboralesEJB ejb;

    private ConsultaPersonalesView view;

    @PostConstruct
    public void initDatosPersonales() {
        view = new ConsultaPersonalesView();
        view.setListDependencia(ejb.obtenerDependenciaLista());
        view.setListUnidad(ejb.obtenerUnidadLista());
        view.setListNombramiento(ejb.obtenerNombramientoLista());
        view.setListPuesto(ejb.obtenerPuesto());
        // view.setListPlaza(ejb.obtenerPlaza());
        view.setListProyecto(ejb.obtenerProyecto());
        view.setListFF(ejb.obtenerFuenteFinanciamiento());
        view.setListSF(ejb.obtenerSubfuenteFinanciamiento());
        irPrincipal();
    }

    public String irPrincipal() {
        // view.setListDatosPersonales(datoPersonal.obtenerListaConsultaPorRfc());
        view.setListDatosPersonalesLista(datoPersonal
                .obtenerlistaDatosPersonalesPorCriterio(view.getRfcCriterio()));
        view.setListSiifDatosLaborales(ejb.obtenerDatosLaboralesLista());
        view.panelPrincipal();
        return "/contenido/siif/consultaDatosPersonales.xhtml?redirect-true";
    }

    public void obtenerDatoPersonal() {
        view.setTabPersonales(true);
        view.setDatoPersonal(
                datoPersonal.obtenerConsultaPorRfc(view.getRfcCriterio()));
        view.setTabPersonales(Boolean.TRUE);
    }

    public void change() {
        ejb.change();
    }

    public String filtrarPorCriterios() {
        view.setListDatosPersonalesLista(datoPersonal
                .obtenerlistaDatosPersonalesPorCriterio(view.getRfcCriterio()));
        view.setListSiifDatosLaborales(ejb
                .obtenerDatosLaboralesListaPorCriterios(view.getRfcCriterio()));
        view.setTabLaborales(Boolean.TRUE);
        view.setTabPersonales(Boolean.TRUE);
        return null;
    }

    public ConsultaPersonalesView getView() {
        return view;
    }

    public void ocultarBusqueda() {
        Boolean a = view.getPanelDatosLaborales();
        Boolean b = view.getPanelDatosPersonales();
        Boolean c = view.getBusqueda();
        c = (a && b == false) ? c == true : c == false;
        view.setBusqueda(c);
    }

    public void modificarLaborales() {
        ejb.modificarLaborales(view.getDatoPersonal().getRfc(),
                view.getDatoPersonalesSelect().getIdEmpleadoDatosPersonales());
        datoPersonal.actualizarDatosPersonales(view.getDatoPersonal());
    }

    // Opciones para Siif Datos Personales

    public String irNuevoDatosPersonales() {
        view.setDatoPersonal(datoPersonal.nuevoDatosPersonales());
        view.setOperacionNuevo(Boolean.TRUE);
        view.panelDatosPersonales();
        return null;
    }

    public void eliminarDatosPersonales() {

    }

    public String irGestionarDatosPersonales() {
        view.setDatoPersonal(datoPersonal
                .obtenerDatosPersonales(view.getDatoPersonalesSelect()));
        view.setOperacionNuevo(Boolean.FALSE);
        view.panelDatosPersonales();
        rfcOriginal = view.getDatoPersonalesSelect().getRfc();
        return null;
    }

    public String guardarDatosPersonales() {
        if (view.getOperacionNuevo()) {
            datoPersonal.crearDatosPersonales(view.getDatoPersonal());
        } else {
            System.out.println("Original..." + rfcOriginal + "Nuevo..."
                    + view.getDatoPersonal().getRfc());
            if (rfcOriginal.equals(view.getDatoPersonal().getRfc())) {
                datoPersonal.actualizarDatosPersonales(view.getDatoPersonal());
            } else {
                modificarLaborales();
            }
        }
        view.setPanelDatosPersonales(Boolean.FALSE);
        filtrarPorCriterios();
        ocultarBusqueda();
        return null;
    }

    public String cancelarPersonales() {
        view.setPanelDatosPersonales(Boolean.FALSE);
        ocultarBusqueda();
        return null;
    }

    public String cancelar() {
        view.panelPrincipal();
        return "/contenido/siif/reportarSiif.xhtml?faces-redirect=true";
    }

    // Opciones para Siif Datos Laborales

    public String irNuevoDatosLaborales() {
        view.setSiifDatosLaborales(
                ejb.nuevoDatosLaborales(view.getRfcCriterio()));
        view.setOperacionNuevo(Boolean.TRUE);

        view.panelDatosLaborales();
        return null;
    }

    public void eliminarDatosLaborales() {
        ejb.eliminarDatosLaborales(view.getSiifDatosLaboralesSelect());
        view.panelPrincipal();
        irPrincipal();
    }

    public String irGestionarDatosLaborales() {
        view.setSiifDatosLaborales(
                ejb.obtenerDatosLaborales(view.getSiifDatosLaboralesSelect()));
        view.setOperacionNuevo(Boolean.FALSE);

        view.setListSiifLaboralesSubfuentes(
                ejb.obtenerSiifLaboralesSubfuentesPorIDLaborles(
                        view.getSiifDatosLaboralesSelect().getIdDatoLaboral()));
        view.setListSubfuenteF(ejb.obtenerSubfuenteFinanciamiento());
        view.panelDatosLaborales();
        return null;
    }

    public String guardarDatosLaborales() {

        if (view.getOperacionNuevo()) {
            ejb.crearDatosLaborales(view.getSiifDatosLaborales(),
                    view.getSiifDatosLaborales().getRfc());
        } else {
            ejb.actualizarDatosLaborales(view.getSiifDatosLaborales());
            //ejb.actualizarDatos(view.getListSiifLaboralesSubfuentes(), view.getListSiifLaboralesSubfuentes().get(0).getIdSiifDatosLaborales());
        }

        view.setPanelDatosLaborales(Boolean.FALSE);
        ocultarBusqueda();
        return null;
    }

    public String cancelarLaborales() {
        view.setPanelDatosLaborales(Boolean.FALSE);
        ocultarBusqueda();
        return null;
    }

    // Opciones para Siif Laborales Subfuentes

    public void irNuevoSubfuente() {
        view.setListSiifLaboralesSubfuentes(ejb
                .crearDatos(view.getSiifDatosLaborales().getIdDatoLaboral()));
    }

    public void eliminarSubfuentes() {
        //view.setListSiifLaboralesSubfuentes(ejb.eliminarDatos(view.getSiifLaboralesSubfuentesSelect()));
        view.setDialogoEliminar(false);
        view.panelDatosLaborales();
        irGestionarDatosLaborales();
    }

    public void mostrarDialogoEliminar() {
        view.setDialogoEliminar(true);
    }

    public void ocultarDialogoEliminar() {
        view.setDialogoEliminar(false);
    }

    // Variables

    public void onRowSelectDatosPersonales(SelectEvent event) {
        view.setDisabledIrGestionarDatosPersonales(Boolean.FALSE);
    }

    public void onRowUnselectDatosPersonales(UnselectEvent event) {
        view.setDisabledIrGestionarDatosPersonales(Boolean.TRUE);
    }

    public DatosPersonales getDatoPersonal() {
        return datoPersonal;
    }

    public void setDatoPersonal(DatosPersonales datoPersonal) {
        this.datoPersonal = datoPersonal;
    }

    public void setView(ConsultaPersonalesView view) {
        this.view = view;
    }

    public void onRowSelectDatosLaborales(SelectEvent event) {
        view.setDisabledIrGestionarDatosLaborales(Boolean.FALSE);
    }

    public void onRowUnselectDatosLaborales(UnselectEvent event) {
        view.setDisabledIrGestionarDatosLaborales(Boolean.TRUE);
    }

    public String getRfcOriginal() {
        return rfcOriginal;
    }

    public void setRfcOriginal(String rfcOriginal) {
        this.rfcOriginal = rfcOriginal;
    }

    public void onRowSelectSubfuentes(SelectEvent event) {
        view.setDisabledEliminar(Boolean.FALSE);
    }

    public void onRowUnselectSubfuentes(UnselectEvent event) {
        view.setDisabledEliminar(Boolean.TRUE);
    }

    public void validatorConsulta(FacesContext context, UIComponent component,
            Object value) {

        String nombreComponete = component.getId();
        switch (nombreComponete) {
            case "rfc":
                String rfc = (String) value;
                if (ValidacionUtil.esCadenaVacia(rfc)) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese un criterio de búsqueda.");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                } else {
                    if (rfc.length() < 5) {
                        FacesMessage facesMessage = new FacesMessage(
                                FacesMessage.SEVERITY_ERROR, "",
                                "Por favor ingrese más de 4 caracteres");
                        context.addMessage(component.getClientId(),
                                facesMessage);
                        throw new ValidatorException(facesMessage);
                    }
                }
                break;
            default:
                break;
        }
    }

    public void validatorDatosPersonales(FacesContext context,
            UIComponent component, Object value) {

        String nombreComponete = component.getId();
        switch (nombreComponete) {
            case "DP":
                Integer DP = (Integer) value;
                if (!ValidacionUtil.esNumeroPositivo(DP)) {
                    System.out.println("Si entro");
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese un ID de Empleado correcto");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                } else if (datoPersonal.verificaIdDatoPersonal(DP)
                        && view.getOperacionNuevo()) {
                    System.out.println("si valida ID");
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "El ID ya esta ocupado, ingrese otro.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "rfcP":
                String rfcP = (String) value;
                if (ValidacionUtil.esCadenaVacia(rfcP)) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese un RFC");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }
                break;
            case "apPat":
                String apPat = (String) value;
                if (ValidacionUtil.esCadenaVacia(apPat)) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese un Apellido Paterno");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }
                break;
            case "apMat":
                String apMat = (String) value;
                if (ValidacionUtil.esCadenaVacia(apMat)) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese un Apellido Materno");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }
                break;
            case "nom":
                String nom = (String) value;
                if (ValidacionUtil.esCadenaVacia(nom)) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese un Nombre(s)");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }
                break;
            case "fenac":
                Date fenac = (Date) value;
                if (fenac == null) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese una Fecha de Nacimiento");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }
                break;
            case "sexo":
                String sexo = (String) value;
                if (ValidacionUtil.esCadenaVacia(sexo)) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese un tipo de Sexo");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }
                break;
            // case "calle":
            // String calle = (String) value;
            // if (ValidacionUtil.esCadenaVacia(calle)) {
            // FacesMessage facesMessage = new FacesMessage(
            // FacesMessage.SEVERITY_ERROR, "",
            // "Por favor ingrese una Calle");
            // context.addMessage(component.getClientId(), facesMessage);
            // throw new ValidatorException(facesMessage);
            // }
            // break;
            // case "numExt":
            // String numExt = (String) value;
            // if (ValidacionUtil.esCadenaVacia(numExt)) {
            // FacesMessage facesMessage = new FacesMessage(
            // FacesMessage.SEVERITY_ERROR, "",
            // "Por favor ingrese un numero Externo");
            // context.addMessage(component.getClientId(), facesMessage);
            // throw new ValidatorException(facesMessage);
            // }
            // break;
            // case "cp":
            // String cp = (String) value;
            // if (ValidacionUtil.esCadenaVacia(cp)) {
            // FacesMessage facesMessage = new FacesMessage(
            // FacesMessage.SEVERITY_ERROR, "",
            // "Por favor ingrese un Codigo Postal");
            // context.addMessage(component.getClientId(), facesMessage);
            // throw new ValidatorException(facesMessage);
            // }
            // break;
            // case "tel":
            // String tel = (String) value;
            // if (ValidacionUtil.esCadenaVacia(tel)) {
            // FacesMessage facesMessage = new FacesMessage(
            // FacesMessage.SEVERITY_ERROR, "",
            // "Por favor ingrese un Teléfono");
            // context.addMessage(component.getClientId(), facesMessage);
            // throw new ValidatorException(facesMessage);
            // }
            // break;
            case "idEdoEmp":
                String idEdoEmp = (String) value;
                if (ValidacionUtil.esCadenaVacia(idEdoEmp)) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese un Estado del Empleado");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }
                break;
            default:
                break;
        }
    }

    public void validatorBusqueda(FacesContext context, UIComponent component,
            Object value) {

        String nombreComponete = component.getId();
        switch (nombreComponete) {
            case "rfcCr":
                String rfcCr = (String) value;
                if (ValidacionUtil.esCadenaVacia(rfcCr)) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese un criterio de búsqueda.");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                } else {
                    if (rfcCr.length() < 5) {
                        FacesMessage facesMessage = new FacesMessage(
                                FacesMessage.SEVERITY_ERROR, "",
                                "Por favor ingrese más de 4 caracteres");
                        context.addMessage(component.getClientId(),
                                facesMessage);
                        throw new ValidatorException(facesMessage);
                    }
                }
                break;
            default:
                break;
        }
    }

    public void validatorDatosLaborales(FacesContext context,
            UIComponent component, Object value) {
        String nombreComponete = component.getId();
        switch (nombreComponete) {
            case "idLaboral":
                Integer DL = (Integer) value;
                if (!ValidacionUtil.esNumeroPositivo(DL)) {
                    System.out.println("Si entro");
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese un ID de Empleado correcto");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                } else if (ejb.verificaIdDatoLaboral(DL)
                        && view.getOperacionNuevo()) {
                    System.out.println("si valida ID");
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "El ID ya esta ocupado, ingrese otro.");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }
                break;
            case "rfc2":
                String rfc2 = (String) value;
                if (ValidacionUtil.esCadenaVacia(rfc2)) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese un RFC.");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                } else {
                    if (!ValidacionUtil.validarRfc(rfc2)) {
                        FacesMessage facesMessage = new FacesMessage(
                                FacesMessage.SEVERITY_ERROR, "",
                                "El formato de RFC que introdujo es incorrecto");
                        context.addMessage(component.getClientId(),
                                facesMessage);
                        throw new ValidatorException(facesMessage);
                    }
                }
                break;
            case "plaza":
                String plaza = (String) value;
                if (ValidacionUtil.esCadenaVacia(plaza)) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese una plaza");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }
                break;
            case "proyecto":
                Integer proyecto = (Integer) value;
                if (!ValidacionUtil.esNumeroPositivo(proyecto)) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese un proyecto");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "dependencia":
                Integer dependencia = (Integer) value;
                if (!ValidacionUtil.esNumeroPositivo(dependencia)) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor selecciona una dependencia");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "unidad":
                Integer unidad = (Integer) value;
                if (!ValidacionUtil.esNumeroPositivo(unidad)) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor selecciona una unidad responsable");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "nombramiento":
                String nombramiento = (String) value;
                if (ValidacionUtil.esCadenaVacia(nombramiento)) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor selecciona un nombramiento");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }
                break;
            case "puesto":
                String puesto = (String) value;
                if (ValidacionUtil.esCadenaVacia(puesto)) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor selecciona un puesto");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }
                break;
            case "sindicato":
                Integer sindicato = (Integer) value;
                if (sindicato == null) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor selecciona si es de sindicato");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "habilitado":
                Integer habilitado = (Integer) value;

                if (habilitado == null) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor selecciona si esta habilitado");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "fingreso":
                Date fingreso = (Date) value;
                if (fingreso == null) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese una fecha de ingreso");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "noQ":
                Integer noQ = (Integer) value;
                if (noQ == null) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese un Numero de Quinquenio");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "sdoM":
                BigDecimal sdoM = (BigDecimal) value;

                if (sdoM == null) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese un Sueldo Mensual");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "percepcion":
                BigDecimal percepcion = (BigDecimal) value;
                if (percepcion == null) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese una Percepcion Complementaria");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "despensa":
                BigDecimal despensa = (BigDecimal) value;
                if (despensa == null) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese una Despensa");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "incA":
                BigDecimal incA = (BigDecimal) value;
                if (incA == null) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese un Incentivo de Ahorro");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "compensacion":
                BigDecimal compensacion = (BigDecimal) value;
                if (compensacion == null) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese una Compensacion");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "quin":
                BigDecimal quin = (BigDecimal) value;
                if (quin == null) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese un Quinquenio");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "cuenta":
                String cuenta = (String) value;
                if (ValidacionUtil.esCadenaVacia(cuenta)) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingresa un No. de Cuenta");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }
                break;
            case "trecurso":
                Integer trecurso = (Integer) value;

                if (!ValidacionUtil.esNumeroPositivo(trecurso)) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingresa un tipo de recurso");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "policia":
                Integer policia = (Integer) value;
                if (policia == null) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor seleccione si es Policia o no");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "idFF":
                Integer idFF = (Integer) value;
                if (!ValidacionUtil.esNumeroPositivo(idFF)) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor seleccione una Fuente de Financiamiento");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "idSF":
                Integer idSF = (Integer) value;
                if (!ValidacionUtil.esNumeroPositivo(idSF)) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor seleccione una Subfuente de Financiamiento");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "idEE":
                String idEE = (String) value;
                if (ValidacionUtil.esCadenaVacia(idEE)) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese un Estado de Empleado");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            default:
                break;
        }

    }
}