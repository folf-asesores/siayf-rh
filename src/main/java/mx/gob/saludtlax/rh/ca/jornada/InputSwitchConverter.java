
package mx.gob.saludtlax.rh.ca.jornada;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("mx.gob.saludtlax.rh.web.jornada.inputswitchconverter")
public class InputSwitchConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
            String value) {

        System.out.println(value);
        if (value == "true") {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value) {

        return value.toString();
    }

}
