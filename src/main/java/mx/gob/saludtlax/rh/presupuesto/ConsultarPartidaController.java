package mx.gob.saludtlax.rh.presupuesto;

import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.util.JSFUtils;

@ManagedBean(name = "consultarPartida")
@SessionScoped
public class ConsultarPartidaController {
    private ConsultarPartidaView view;
    @Inject
    private ConsultarPartidaEJB ejb;

    @PostConstruct
    public void initConsultarPartida() {
        view = new ConsultarPartidaView();
        view.setListaTipoNombramiento(ejb.getListaTipoNombramiento());
        view.setListaUnidadResponsable(ejb.getListaUnidadResponsable());
    }

    public String consultarPartidasPorRfc() {
        view.setListaConsultaPartida(ejb.consultarPartidasPorRfc(view.getRfc()));
        return null;
    }

    public String consultarPartidasPorUnidadResponsable() {
        try {
            view.setListaConsultaPartida(ejb.consultarPartidasPorUnidadResponsable(view.getIdUnidadResponsable()));
        } catch (ReglaNegocioException e) {
        	
        	if(view.getListaConsultaPartida() != null) {
        		view.getListaConsultaPartida().clear();
        	}
            JSFUtils.infoMessage(e.getMessage(), "");
        }
        return null;
    }

    public String consultarPartidasPorTipoNombramiento() {
        try {
            view.setListaConsultaPartida(ejb.consultarPartidasPorTipoNombramiento(view.getIdTipoNombramiento()));
        } catch (ReglaNegocioException e) {
        	if(view.getListaConsultaPartida() != null) {
            view.getListaConsultaPartida().clear();
        	}
            JSFUtils.infoMessage(e.getMessage(), "");
        }

        return null;
    }

    public ConsultarPartidaView getView() {
        return view;
    }

    public void setView(ConsultarPartidaView view) {
        this.view = view;
    }
}