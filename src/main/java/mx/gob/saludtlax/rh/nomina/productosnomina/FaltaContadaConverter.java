package mx.gob.saludtlax.rh.nomina.productosnomina;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;


@FacesConverter("faltaContadaConverter")
public class FaltaContadaConverter implements Converter {

	@SuppressWarnings("unchecked")
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		final DualListModel<FaltaContadaDTO> dualList;
		try {
			dualList = (DualListModel<FaltaContadaDTO>) ((PickList) component).getValue();
			FaltaContadaDTO team = getObjectFromList(dualList.getSource(), Integer.valueOf(value));
			if (team == null) {
				team = getObjectFromList(dualList.getTarget(), Integer.valueOf(value));
			}

			return team;
		} catch (ClassCastException cce) {
			throw new ConverterException();
		} catch (NumberFormatException nfe) {
			throw new ConverterException();
		}
	}

	public String getAsString(FacesContext context, UIComponent component, Object object) {
		String string;
		if (object == null) {
			string = "";
		} else {
			try {
				string = String.valueOf(((FaltaContadaDTO) object).getIdFalta());
			} catch (ClassCastException cce) {
				System.out.println(cce.getMessage());
				cce.printStackTrace();
				throw new ConverterException();
			}
		}
		return string;
	}


	private FaltaContadaDTO getObjectFromList(final List<?> list, final Integer identifier) {
		for (final Object object : list) {
			final FaltaContadaDTO team = (FaltaContadaDTO) object;
			if (team.getIdFalta().equals(identifier)) {
				return team;
			}
		}
		return null;
	}
}
