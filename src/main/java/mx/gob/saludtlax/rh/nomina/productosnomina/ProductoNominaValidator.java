
package mx.gob.saludtlax.rh.nomina.productosnomina;

/**
 * @author José Pablo
 */
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import mx.gob.saludtlax.rh.util.ValidacionUtil;

public class ProductoNominaValidator {

    public void validatorProductoNomina(FacesContext context,
            UIComponent component, Object value) {

        String nombreComponete = component.getId();
        switch (nombreComponete) {

            case "idEjercicioFiscal":
                Integer idEjercicioFiscal = (Integer) value;
                if (!ValidacionUtil.esNumeroPositivo(idEjercicioFiscal)) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Seleccione un ejercicio fiscal.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "idPeriodoCalendario":
                Integer idPeriodoCalendario = (Integer) value;
                if (!ValidacionUtil.esNumeroPositivo(idPeriodoCalendario)) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Seleccione un periodo.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "idTipoNombramiento":
                Integer idTipoNombramiento = (Integer) value;
                if (!ValidacionUtil.esNumeroPositivo(idTipoNombramiento)) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Seleccione un tipo de nombramiento.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "idFuenteFinanciamiento":
                Integer idFuenteFinanciamiento = (Integer) value;
                if (!ValidacionUtil.esNumeroPositivo(idFuenteFinanciamiento)) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Seleccione una fuente de financiamiento.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "idSubfuenteFinanciamiento":
                Integer idSubfuenteFinanciamiento = (Integer) value;
                if (!ValidacionUtil
                        .esNumeroPositivo(idSubfuenteFinanciamiento)) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Seleccione una subfuente de financiamiento.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "idTipoNomina":
                Integer idTipoNomina = (Integer) value;
                if (!ValidacionUtil.esNumeroPositivo(idTipoNomina)) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Seleccione un tipo de nómina.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "fechaPago":
                Date fechaPago = (Date) value;
                if (fechaPago == null) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Ingrese una fecha de pago.");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }
                break;
            //		case "fechaPagoDia":
            //			Date periodo = (Date) value;
            //			if (fechaPago ==null) {
            //				FacesMessage facesMessage = new FacesMessage(
            //						FacesMessage.SEVERITY_ERROR, "",
            //						"Ingrese una fecha de pago mayor al primer dia del periodo seleccionado.");
            //				context.addMessage(component.getClientId(), facesMessage);
            //				throw new ValidatorException(facesMessage);
            //			}
            //		break;
            default:
                break;
        }

    }
}
