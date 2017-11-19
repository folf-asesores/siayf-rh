
package mx.gob.saludtlax.rh.presupuesto.consultarperiodo;

import java.io.IOException;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.TipoArchivo;

@ManagedBean(name = "consultarPeriodo")
@SessionScoped
public class ConsultarPeriodoController {
    private ConsultarPeriodoView view;
    @Inject
    private ConsultarPeriodoEJB ejb;

    @PostConstruct
    public void initConsultarPeriodo() {
        view = new ConsultarPeriodoView();
        view.setPeriodoList(ejb.obtenerPeriodoList());
    }

    public String consultarPeriodo() {
        System.out.println("view.getPeriodo():: " + view.getPeriodo());
        view.setConsultaPeriodoList(ejb.consultarNominaPorPeriodo(view.getPeriodo()));
        view.setButonDownload(true);
        return null;
    }

    public void validator(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        Date periodo = (Date) value;
        if (periodo == null) {
            FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Por favor ingrese un rfc.");
            context.addMessage(component.getClientId(), facesMessage1);
            throw new ValidatorException(facesMessage1);
        }

    }

    public void descargarReporte() {
        try {

            ReporteConsultarPeriodo reporte = new ReporteConsultarPeriodo();

            byte[] bytes = null;

            bytes = reporte.generarArchivoExcel(view.getConsultaPeriodo());

            if (bytes != null) {
                JSFUtils.descargarArchivo(bytes, "Consultar Periodo", TipoArchivo.getMIMEType("xlsx"));

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

    public ConsultarPeriodoView getView() {
        return view;
    }
}