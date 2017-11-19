
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

@ManagedBean(name = "consultaProspera")
@SessionScoped
public class ConsultaAfaspeProsperaController {

    private ConsultaProsperaView view;
    @Inject
    private ConsultaProsperaEJB ejb;

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
    public void initConsultaProspera() {
        view = new ConsultaProsperaView();
        irPrincipal();
    }

    public String irPrincipal() {
        System.out.println(
                "consultaProspera-irPrincipal: this.view.getRfcCriterio():: "
                        + view.getRfcCriterio());
        //this.view.setListDatosPersonalesLista(datoPersonal.obtenerlistaDatosPersonalesPorCriterio(this.view.getRfcCriterio()));
        view.setListEstructuraProspera(
                ejb.obtenerConsultaProsperaListaPorCriterios(
                        view.getRfcCriterio()));
        view.panelPrincipal();
        return "/contenido/siif/consultaNomina.xhtml?redirect-true";
    }

    public String filtrarPorCriterios() {
        view.setListEstructuraProspera(
                ejb.obtenerConsultaProsperaListaPorCriterios(
                        view.getRfcCriterio()));
        view.setListEstructuraProsperaTrailers(
                ejb.obtenerConsultaProsperaTrailersListaPorCriterios(
                        view.getRfcCriterio()));
        view.setTabDat(Boolean.TRUE);
        view.setTabTra(Boolean.TRUE);
        return null;
    }

    public ConsultaProsperaView getView() {
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
        ejb.modificarTrailers(view.getEstructuraProspera().getRfc(),
                view.getEstructuraProsperaSelect().getIdEstructurasContratos());
        //		System.out.println("el RFC es....."+view.getEstructuraNomina().getRfc());
        //		System.out.println("el Originales....."+rfcOriginal);
        //		System.out.println("el ID es....."+view.getEstructuraNominaSelect().getIdEstructurasNominas());
        ejb.actualizarDatos(view.getEstructuraProspera());
    }

    // Opciones para Estructura Nomina Datos

    public String irNuevoDatos() {
        view.setEstructuraProspera(ejb.nuevoDatos());
        view.setOperacionNuevo(Boolean.TRUE);
        view.panelDatos();
        return null;
    }

    public void eliminarDatos() {
        ejb.eliminarDatos(view.getEstructuraProsperaSelect());
        view.panelPrincipal();
        irPrincipal();
    }

    public String irGestionarDatos() {
        view.setEstructuraProspera(
                ejb.obtenerDatos(view.getEstructuraProsperaSelect()));
        view.setOperacionNuevo(Boolean.FALSE);
        view.panelDatos();
        rfcOriginal = view.getEstructuraProsperaSelect().getRfc();
        return null;
    }

    public String guardarDatos() {
        if (view.getOperacionNuevo()) {
            ejb.crearDatos(view.getEstructuraProspera());
        } else {
            System.out.println("Original..." + rfcOriginal + "Nuevo..."
                    + view.getEstructuraProspera().getRfc());
            if (rfcOriginal.equals(view.getEstructuraProspera().getRfc())) {
                ejb.actualizarDatos(view.getEstructuraProspera());
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
        view.setEstructuraProsperaTrailers(ejb.nuevoTrailers());
        view.setOperacionNuevo(Boolean.TRUE);
        view.panelTrailers();
        return null;
    }

    public void eliminarTrailers() {
        ejb.eliminarTrailers(view.getEstructuraProsperaTrailersSelect());
        view.panelPrincipal();
        irPrincipal();
    }

    public String irGestionarTrailers() {
        view.setEstructuraProsperaTrailers(ejb
                .obtenerTrailers(view.getEstructuraProsperaTrailersSelect()));
        view.setOperacionNuevo(Boolean.FALSE);
        view.panelTrailers();
        ;
        return null;
    }

    public String guardarTrailers() {
        if (view.getOperacionNuevo()) {
            ejb.crearTrailers(view.getEstructuraProsperaTrailers());
        } else {
            ejb.actualizarTrailers(view.getEstructuraProsperaTrailers());
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

    //			public void setView(ConsultaNominaView view) {
    //				this.view = view;
    //			}

    public void onRowSelectTrailers(SelectEvent event) {
        view.setDisabledIrGestionarTrailers(Boolean.FALSE);
    }

    public void onRowUnselectTrailers(UnselectEvent event) {
        view.setDisabledIrGestionarTrailers(Boolean.TRUE);
    }

    public void validatorconsultaProspera(FacesContext context,
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