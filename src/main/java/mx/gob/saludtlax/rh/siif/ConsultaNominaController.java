
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
        return this.rfcOriginal;
    }

    public void setRfcOriginal(String rfcOriginal) {
        this.rfcOriginal = rfcOriginal;
    }

    public Boolean getConfirma() {
        return this.confirma;
    }

    public void setConfirma(Boolean confirma) {
        this.confirma = confirma;
    }

    public Boolean getDialogo() {
        return this.dialogo;
    }

    public void setDialogo(Boolean dialogo) {
        this.dialogo = dialogo;
    }

    @PostConstruct
    public void initConsultaNomina() {
        this.view = new ConsultaNominaView();
        irPrincipal();
    }

    public String irPrincipal() {
        System.out.println("consultaNomina-irPrincipal: this.view.getRfcCriterio():: " + this.view.getRfcCriterio());
        //this.view.setListDatosPersonalesLista(datoPersonal.obtenerlistaDatosPersonalesPorCriterio(this.view.getRfcCriterio()));
        //
        this.view.setListEstructuraNomina(this.ejb.obtenerConsultaNominaListaPorCriterios(this.view.getEstructuraNominaSeleccionada()));
        //this.view.setListEstructuraNomina(ejb.obtenerConsultaNominaContratoListaPorCriterios(this.view.getRfcCriterio()));
        this.view.panelPrincipal();
        return "/contenido/siif/consultaNomina.xhtml?redirect-true";
    }

    public String filtrarPorCriterios() {
        this.view.setListEstructuraNomina(this.ejb.obtenerConsultaNominaListaPorCriterios(this.view.getRfcCriterio()));
        this.view.setListEstructuraNominaTrailers(this.ejb.obtenerConsultaNominaTrailersListaPorCriterios(this.view.getRfcCriterio()));
        this.view.setTabDat(Boolean.TRUE);
        this.view.setTabTra(Boolean.TRUE);
        return null;
    }

    public ConsultaNominaView getView() {
        return this.view;
    }

    public void ocultarBusqueda() {
        Boolean a = this.view.getPanelDatos();
        Boolean b = this.view.getPanelTrailers();
        Boolean c = this.view.getBusqueda();
        c = (a && b == false) ? c == true : c == false;
        this.view.setBusqueda(c);
    }

    public void mostrarDialogo() {
        this.dialogo = Boolean.TRUE;
    }

    public void ocultarDialogo() {
        this.dialogo = Boolean.FALSE;
    }

    public void modificarTrailers() {
        this.ejb.modificarTrailers(this.view.getEstructuraNomina().getRfc(), this.view.getEstructuraNominaSelect().getIdEstructurasNominas());
        //		System.out.println("el RFC es....."+view.getEstructuraNomina().getRfc());
        //		System.out.println("el Originales....."+rfcOriginal);
        //		System.out.println("el ID es....."+view.getEstructuraNominaSelect().getIdEstructurasNominas());
        this.ejb.actualizarDatos(this.view.getEstructuraNomina());
    }

    //	> > > > > Opciones para Estructura Nomina Datos < < < < < <

    public String irNuevoDatos() {
        this.view.setEstructuraNomina(this.ejb.nuevoDatos());
        this.view.setOperacionNuevo(Boolean.TRUE);
        this.view.panelDatos();
        return null;
    }

    public void eliminarDatos() {
        this.ejb.eliminarDatos(this.view.getEstructuraNominaSelect());
        this.view.panelPrincipal();
        irPrincipal();
    }

    public String irGestionarDatos() {
        this.view.setEstructuraNomina(this.ejb.obtenerDatos(this.view.getEstructuraNominaSelect()));
        this.view.setOperacionNuevo(Boolean.FALSE);
        this.view.panelDatos();
        this.rfcOriginal = this.view.getEstructuraNominaSelect().getRfc();
        return null;
    }

    public String guardarDatos() {
        if (this.view.getOperacionNuevo()) {
            this.ejb.crearDatos(this.view.getEstructuraNomina());
        } else {
            System.out.println("Original..." + this.rfcOriginal + "Nuevo..." + this.view.getEstructuraNomina().getRfc());
            if (this.rfcOriginal.equals(this.view.getEstructuraNomina().getRfc())) {
                this.ejb.actualizarDatos(this.view.getEstructuraNomina());
            } else {
                modificarTrailers();
            }
        }
        this.view.setPanelDatos(Boolean.FALSE);
        ocultarBusqueda();
        return null;
    }

    public String cancelarDatos() {
        this.view.setPanelDatos(Boolean.FALSE);
        ocultarBusqueda();
        return null;
    }

    public String cancelar() {
        this.view.panelPrincipal();
        return "/contenido/siif/revisarsiiffederalesdatos.xhtml?faces-redirect=true";
    }

    //	> > > > > Opciones para Estructura Nomina Trailers < < < < < <

    public String irNuevoTrailers() {
        this.view.setEstructuraNominaTrailers(this.ejb.nuevoTrailers());
        this.view.setOperacionNuevo(Boolean.TRUE);
        this.view.panelTrailers();
        return null;
    }

    public void eliminarTrailers() {
        this.ejb.eliminarTrailers(this.view.getEstructuraNominaTrailersSelect());
        this.view.panelPrincipal();
        irPrincipal();
    }

    public String irGestionarTrailers() {
        this.view.setEstructuraNominaTrailers(this.ejb.obtenerTrailers(this.view.getEstructuraNominaTrailersSelect()));
        this.view.setOperacionNuevo(Boolean.FALSE);
        this.view.panelTrailers();
        ;
        return null;
    }

    public String guardarTrailers() {
        if (this.view.getOperacionNuevo()) {
            this.ejb.crearTrailers(this.view.getEstructuraNominaTrailers());
        } else {
            this.ejb.actualizarTrailers(this.view.getEstructuraNominaTrailers());
        }
        this.view.setPanelTrailers(Boolean.FALSE);
        ocultarBusqueda();
        return null;
    }

    public String cancelarTrailers() {
        this.view.setPanelTrailers(Boolean.FALSE);
        ocultarBusqueda();
        return null;
    }

    //Variables	

    public void onRowSelectDatos(SelectEvent event) {
        this.view.setDisabledIrGestionarDatos(Boolean.FALSE);
    }

    public void onRowUnselectDatos(UnselectEvent event) {
        this.view.setDisabledIrGestionarDatos(Boolean.TRUE);
    }

    public void setView(ConsultaNominaView view) {
        this.view = view;
    }

    public void onRowSelectTrailers(SelectEvent event) {
        this.view.setDisabledIrGestionarTrailers(Boolean.FALSE);
    }

    public void onRowUnselectTrailers(UnselectEvent event) {
        this.view.setDisabledIrGestionarTrailers(Boolean.TRUE);
    }

    //	> > > > > Validadores < < < < < <

    public void validatorConsultaNomina(FacesContext context, UIComponent component, Object value) {

        String nombreComponete = component.getId();
        switch (nombreComponete) {
            case "rfcCriterio":
                String rfcCriterio = (String) value;

                if (ValidacionUtil.esCadenaVacia(rfcCriterio)) {
                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese un criterio de búsqueda.");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                } else {
                    if (rfcCriterio.length() < 5) {
                        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese minimo 5 caracteres");
                        context.addMessage(component.getClientId(), facesMessage);
                        throw new ValidatorException(facesMessage);
                    }
                }
                break;
            default:
                break;
        }
    }

}