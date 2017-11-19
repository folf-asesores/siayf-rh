
package mx.gob.saludtlax.rh.siif;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

@ManagedBean(name = "consultaDatosEncabezado")
@SessionScoped
public class DatosEncabezadoController implements Serializable {

    private static final long serialVersionUID = 1545175823479673131L;

    @Inject
    private DatosEncabezadoEJB datoEncabezado;

    private ConsultaDatosEncabezadoView view;

    @PostConstruct
    public void initDatosEncabezado() {
        view = new ConsultaDatosEncabezadoView();
    }

    public String irPrincipal() {
        System.out.println("irPrincipal: this.view.getIdSiifEncabezado():: " + view.getIdSiifEncabezado());

        Integer CB = datoEncabezado.obtenerCuentaBancaria(view.getIdSiifEncabezado());
        System.out.println("Clave Bancaria):: " + CB);

        if (CB != null && CB == 4) {

            view.setListDatosPersonalesLista(datoEncabezado.obtenerListaDatosEncabezadoProspera(view.getIdSiifEncabezado()));
        } else {
            if (CB == 7) {
                view.setListDatosPersonalesLista(datoEncabezado.obtenerListaDatosEncabezadoProspera(view.getIdSiifEncabezado()));
            } else {

                if (CB == 2) {
                    view.setListDatosPersonalesLista(datoEncabezado.obtenerListaDatosEncabezadoContrato(view.getIdSiifEncabezado()));
                } else {
                    view.setListDatosPersonalesLista(datoEncabezado.obtenerListaDatosEncabezado(view.getIdSiifEncabezado()));
                }

            }
        }

        view.panelPrincipal();
        System.out.println("view.getListDatosPersonalesLista():: " + view.getListDatosPersonalesLista());
        return "/contenido/siif/revisarsiiffederalesdatos.xhtml?redirect-true";
    }

    // Opciones para Siif Datos Personales

    public String irGestionarDatosEncabezado() {
        view.setDatoEncabezado(datoEncabezado.obtenerDatosEncabezadoPorId(view.getDatoEncabezado().getId_empleado_datos_personales()));
        view.getPanelDatosEncabezado();

        return null;
    }

    public String cancelar() {
        view.panelPrincipal();
        return "/contenido/siif/revisarsiiffederalesdatos.xhtml?faces-redirect=true";
    }

    // Elegible o no elegible

    public void onRowSelectDatosEncabezado(SelectEvent event) {

        view.setDisabledIrGestionarDatosEncabezado(Boolean.FALSE);
        view.setIdSiifEncabezado(((ConsultaDatosEncabezadoDTO) event.getObject()).getId_empleado_datos_personales());

    }

    public void onRowUnselectDatosEncabezado(UnselectEvent event) {
        view.setDisabledIrGestionarDatosEncabezado(Boolean.TRUE);
    }

    public ConsultaDatosEncabezadoView getView() {
        return view;
    }

    public void setView(ConsultaDatosEncabezadoView view) {
        this.view = view;
    }

}