package mx.gob.saludtlax.rh.presupuesto;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.BolsaTrabajo;
import mx.gob.saludtlax.rh.excepciones.BusinessException;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.TipoArchivo;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

@ManagedBean(name = "consultarPartida")
@SessionScoped
public class ConsultarPartidaController {
    private ConsultarPartidaView view;
    @Inject
    private ConsultarPartidaEJB ejb;
    @Inject
	private BolsaTrabajo bolsaTrabajo;
    
    
    @PostConstruct
    public void initConsultarPartida() {
        view = new ConsultarPartidaView();
        view.setListaTipoNombramiento(ejb.getListaTipoNombramiento());
        view.setListaUnidadResponsable(ejb.getListaUnidadResponsable());
        view.setMostrarOpcionDescarga(false);
        this.view.setListaQuincena(ConsultarPartidaEJB.listaQuincena());
		view.setListaDependencia(ejb.getListaDependencia());
    }

    public String consultarPartidasPorRfc() {
        view.setListaConsultaPartida(ejb.consultarPartidasPorRfc(view.getRfc()));
        view.setMostrarOpcionDescarga(true);
        return null;
    }

    public String consultarPartidasPorUnidadResponsable() {
        try {
            view.setListaConsultaPartida(ejb.consultarPartidasPorUnidadResponsable(view.getIdUnidadResponsable()));
            view.setMostrarOpcionDescarga(true);
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
            view.setMostrarOpcionDescarga(true);
        } catch (ReglaNegocioException e) {
        	if(view.getListaConsultaPartida() != null) {
            view.getListaConsultaPartida().clear();
        	}
            JSFUtils.infoMessage(e.getMessage(), "");
        }

        return null;
    }
    
    public String consultarPartidasPorDependencia(){
    	try {
    		view.setListaConsultaPartida(ejb.consultarPartidasPorDependencia(view.getIdDependencia()));
            view.setMostrarOpcionDescarga(true);
        } catch (ReglaNegocioException e) {
        	if(view.getListaConsultaPartida() != null) {
            view.getListaConsultaPartida().clear();
        	}
            JSFUtils.infoMessage(e.getMessage(), "");
        }

        return null;
    }
    
    public void validatorDatosGenerales(FacesContext context, UIComponent component, Object value)
			throws ValidatorException, BusinessException {
    
    	String rfc = (String) value;
    	
    		if(this.view.getListaConsultaPartida().isEmpty()){
    			if (ValidacionUtil.esCadenaVacia(rfc)) {
        			FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
        					"Por favor ingrese un rfc.");
        			context.addMessage(component.getClientId(), facesMessage1);
        			throw new ValidatorException(facesMessage1);
        		} else {
        			try {

        				bolsaTrabajo.validarRfcAspirante(rfc);
        			} catch (BusinessException ex) {
        				FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", ex.getMessage());
        				context.addMessage(component.getClientId(), facesMessage1);
        				throw new ValidatorException(facesMessage1);
        			}

        		}
    		}
    		
    	}
		
    
    
    public void descargarReporte() {
		try {

			ReporteConsultarPartida reporte = new ReporteConsultarPartida();
			
			byte[] bytes = null;

			bytes = reporte.generarArchivoExcel(this.view.getListaConsultaPartida());

			if (bytes != null) {
				JSFUtils.descargarArchivo(bytes,"Partidas Presupuestales",
						TipoArchivo.getMIMEType("xlsx"));

			}

			JSFUtils.infoMessage("Descargar Reporte: ", "Se descargo correctamente...");

		} catch (NullPointerException | IllegalArgumentException | IOException exception) {

			exception.printStackTrace();
			JSFUtils.errorMessage("Error: ", exception.getMessage());
		} catch (ReglaNegocioException reglaNegocioException) {
			reglaNegocioException.printStackTrace();
			JSFUtils.errorMessage("Error: ", reglaNegocioException.getMessage());
		} catch (ValidacionException validacionException) {

			validacionException.printStackTrace();
			JSFUtils.errorMessage("Error: ", validacionException.getMessage());
		}
	}

    public ConsultarPartidaView getView() {
        return view;
    }

    public void setView(ConsultarPartidaView view) {
        this.view = view;
    }
}