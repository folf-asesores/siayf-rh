
package mx.gob.saludtlax.rh.siif;

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

@ManagedBean(name = "consultaNomina")
@SessionScoped
public class ConsultaNominaController {
    private ConsultaNominaView view;
    @Inject
    private ConsultaNominaEJB ejb;

    private String rfcOriginal;
    private Boolean confirma = new Boolean(false);
    private Boolean dialogo;

    public String getRfcOriginal() {
        return rfcOriginal;
    }

    public void setRfcOriginal(String rfcOriginal) {
        this.rfcOriginal = rfcOriginal;
    }

    public Boolean getConfirma() {
        return confirma;
    }

    public void setConfirma(Boolean confirma) {
        this.confirma = confirma;
    }

    public Boolean getDialogo() {
        return dialogo;
    }

    public void setDialogo(Boolean dialogo) {
        this.dialogo = dialogo;
    }

    @PostConstruct
    public void initConsultaNomina() {
        view = new ConsultaNominaView();
        irPrincipal();
    }

    public String irPrincipal() {
        System.out.println(
                "consultaNomina-irPrincipal: this.view.getRfcCriterio():: "
                        + view.getRfcCriterio());
        //this.view.setListDatosPersonalesLista(datoPersonal.obtenerlistaDatosPersonalesPorCriterio(this.view.getRfcCriterio()));
        //
        view.setListEstructuraNomina(ejb.obtenerConsultaNominaListaPorCriterios(
                view.getEstructuraNominaSeleccionada()));
        //this.view.setListEstructuraNomina(ejb.obtenerConsultaNominaContratoListaPorCriterios(this.view.getRfcCriterio()));
        view.panelPrincipal();
        return "/contenido/siif/consultaNomina.xhtml?redirect-true";
    }

    public String filtrarPorCriterios() {
        view.setListEstructuraNomina(ejb
                .obtenerConsultaNominaListaPorCriterios(view.getRfcCriterio()));
        view.setListEstructuraNominaTrailers(
                ejb.obtenerConsultaNominaTrailersListaPorCriterios(
                        view.getRfcCriterio()));
        view.setTabDat(Boolean.TRUE);
        view.setTabTra(Boolean.TRUE);
        return null;
    }

    public ConsultaNominaView getView() {
        return view;
    }

    public void ocultarBusqueda() {
        Boolean a = view.getPanelDatos();
        Boolean b = view.getPanelTrailers();
        Boolean c = view.getBusqueda();
        c = (a && b == false) ? c == true : c == false;
        view.setBusqueda(c);
    }

    public void mostrarDialogo() {
        dialogo = Boolean.TRUE;
    }

    public void ocultarDialogo() {
        dialogo = Boolean.FALSE;
    }

    public void modificarTrailers() {
        ejb.modificarTrailers(view.getEstructuraNomina().getRfc(),
                view.getEstructuraNominaSelect().getIdEstructurasNominas());
        //		System.out.println("el RFC es....."+view.getEstructuraNomina().getRfc());
        //		System.out.println("el Originales....."+rfcOriginal);
        //		System.out.println("el ID es....."+view.getEstructuraNominaSelect().getIdEstructurasNominas());
        ejb.actualizarDatos(view.getEstructuraNomina());
    }

    // Opciones para Estructura Nomina Datos

    public String irNuevoDatos() {
        view.setEstructuraNomina(ejb.nuevoDatos());
        view.setOperacionNuevo(Boolean.TRUE);
        view.panelDatos();
        return null;
    }

    public void eliminarDatos() {
        ejb.eliminarDatos(view.getEstructuraNominaSelect());
        view.panelPrincipal();
        irPrincipal();
    }

    public String irGestionarDatos() {
        view.setEstructuraNomina(
                ejb.obtenerDatos(view.getEstructuraNominaSelect()));
        view.setOperacionNuevo(Boolean.FALSE);
        view.panelDatos();
        rfcOriginal = view.getEstructuraNominaSelect().getRfc();
        return null;
    }

    public String guardarDatos() {
        if (view.getOperacionNuevo()) {
            ejb.crearDatos(view.getEstructuraNomina());
        } else {
            System.out.println("Original..." + rfcOriginal + "Nuevo..."
                    + view.getEstructuraNomina().getRfc());
            if (rfcOriginal.equals(view.getEstructuraNomina().getRfc())) {
                ejb.actualizarDatos(view.getEstructuraNomina());
            } else {
                modificarTrailers();
            }
        }
        view.setPanelDatos(Boolean.FALSE);
        ocultarBusqueda();
        return null;
    }

    public String cancelarDatos() {
        view.setPanelDatos(Boolean.FALSE);
        ocultarBusqueda();
        return null;
    }

    public String cancelar() {
        view.panelPrincipal();
        return "/contenido/siif/revisarsiiffederalesdatos.xhtml?faces-redirect=true";
    }

    // Opciones para Estructura Nomina Trailers

    public String irNuevoTrailers() {
        view.setEstructuraNominaTrailers(ejb.nuevoTrailers());
        view.setOperacionNuevo(Boolean.TRUE);
        view.panelTrailers();
        return null;
    }

    public void eliminarTrailers() {
        ejb.eliminarTrailers(view.getEstructuraNominaTrailersSelect());
        view.panelPrincipal();
        irPrincipal();
    }

    public String irGestionarTrailers() {
        view.setEstructuraNominaTrailers(
                ejb.obtenerTrailers(view.getEstructuraNominaTrailersSelect()));
        view.setOperacionNuevo(Boolean.FALSE);
        view.panelTrailers();
        ;
        return null;
    }

    public String guardarTrailers() {
        if (view.getOperacionNuevo()) {
            ejb.crearTrailers(view.getEstructuraNominaTrailers());
        } else {
            ejb.actualizarTrailers(view.getEstructuraNominaTrailers());
        }
        view.setPanelTrailers(Boolean.FALSE);
        ocultarBusqueda();
        return null;
    }

    public String cancelarTrailers() {
        view.setPanelTrailers(Boolean.FALSE);
        ocultarBusqueda();
        return null;
    }

    //Variables

    public void onRowSelectDatos(SelectEvent event) {
        view.setDisabledIrGestionarDatos(Boolean.FALSE);
    }

    public void onRowUnselectDatos(UnselectEvent event) {
        view.setDisabledIrGestionarDatos(Boolean.TRUE);
    }

    public void setView(ConsultaNominaView view) {
        this.view = view;
    }

    public void onRowSelectTrailers(SelectEvent event) {
        view.setDisabledIrGestionarTrailers(Boolean.FALSE);
    }

    public void onRowUnselectTrailers(UnselectEvent event) {
        view.setDisabledIrGestionarTrailers(Boolean.TRUE);
    }

    public void validatorConsultaNomina(FacesContext context,
            UIComponent component, Object value) {

        String nombreComponete = component.getId();
        switch (nombreComponete) {
            case "rfcCriterio":
                String rfcCriterio = (String) value;

                if (ValidacionUtil.esCadenaVacia(rfcCriterio)) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese un criterio de búsqueda.");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                } else {
                    if (rfcCriterio.length() < 5) {
                        FacesMessage facesMessage = new FacesMessage(
                                FacesMessage.SEVERITY_ERROR, "",
                                "Por favor ingrese minimo 5 caracteres");
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

}