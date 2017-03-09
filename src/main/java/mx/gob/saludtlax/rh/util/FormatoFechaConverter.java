package mx.gob.saludtlax.rh.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("mx.gob.saludtlax.rh.util.formatoFechaConverter")
public class FormatoFechaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
		Date fecha;
		try {
			fecha = formatter.parse(value);
			String fechaString = new SimpleDateFormat("yyyy-MM-dd").format(fecha);
			return fechaString;
		} catch (ParseException e) {
		
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		return value.toString();
	}

}
