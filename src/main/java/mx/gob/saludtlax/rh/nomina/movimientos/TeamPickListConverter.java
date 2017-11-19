
package mx.gob.saludtlax.rh.nomina.movimientos;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

import mx.gob.saludtlax.rh.configuracion.puestogeneral.PuestoGeneralDTO;

@FacesConverter("teamPickListConverter")
public class TeamPickListConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
            String value) {
        //System.out.println("String value: {}" + value);
        return getObjectFromUIPickListComponent(component, value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object object) {
        String string;
        //System.out.println("String valu2e: {}" + object);
        if (object == null) {
            string = "";
        } else {
            try {
                string = String.valueOf(
                        ((PuestoGeneralDTO) object).getIdPuestoGeneral());
            } catch (ClassCastException cce) {
                throw new ConverterException();
            }
        }
        return string;
    }

    @SuppressWarnings("unchecked")
    private PuestoGeneralDTO getObjectFromUIPickListComponent(
            UIComponent component, String value) {
        final DualListModel<PuestoGeneralDTO> dualList;
        try {
            dualList = (DualListModel<PuestoGeneralDTO>) ((PickList) component)
                    .getValue();
            PuestoGeneralDTO team = getObjectFromList(dualList.getSource(),
                    Integer.valueOf(value));
            if (team == null) {
                team = getObjectFromList(dualList.getTarget(),
                        Integer.valueOf(value));
            }

            return team;
        } catch (ClassCastException cce) {
            throw new ConverterException();
        } catch (NumberFormatException nfe) {
            throw new ConverterException();
        }
    }

    private PuestoGeneralDTO getObjectFromList(final List<?> list,
            final Integer identifier) {
        for (final Object object : list) {
            final PuestoGeneralDTO team = (PuestoGeneralDTO) object;
            if (team.getIdPuestoGeneral().equals(identifier)) {
                return team;
            }
        }
        return null;
    }
}
