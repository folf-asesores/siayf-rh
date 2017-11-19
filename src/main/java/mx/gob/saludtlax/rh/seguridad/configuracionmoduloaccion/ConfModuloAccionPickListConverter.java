/*
 *
 */

package mx.gob.saludtlax.rh.seguridad.configuracionmoduloaccion;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.jboss.logging.Logger;
import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

import mx.gob.saludtlax.rh.acciones.AccionDTO;

/**
 * @author Eduardo Mex
 *
 */
@FacesConverter("configModuloAccionPickListConverter")
public class ConfModuloAccionPickListConverter implements Converter {

    private static final Logger LOGGER = Logger.getLogger(ConfModuloAccionPickListConverter.class);

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        LOGGER.debugv("String value: {0}", value);
        return getObjectFromUIPickListComponent(component, value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object object) {
        String string;
        LOGGER.debugv("Object value: {0}", object);
        if (object == null) {
            string = "";
        } else {
            try {
                string = String.valueOf(((AccionDTO) object).getIdAccion());
            } catch (ClassCastException cce) {
                throw new ConverterException();
            }
        }
        return string;
    }

    @SuppressWarnings("unchecked")
    private AccionDTO getObjectFromUIPickListComponent(UIComponent component, String value) {
        final DualListModel<AccionDTO> dualList;
        try {
            dualList = (DualListModel<AccionDTO>) ((PickList) component).getValue();
            AccionDTO team = getObjectFromList(dualList.getSource(), Integer.valueOf(value));
            if (team == null) {
                team = getObjectFromList(dualList.getTarget(), Integer.valueOf(value));
            }

            return team;
        } catch (ClassCastException | NumberFormatException cce) {
            throw new ConverterException();
        }
    }

    private AccionDTO getObjectFromList(final List<?> list, final Integer identifier) {
        for (final Object object : list) {
            final AccionDTO team = (AccionDTO) object;
            if (team.getIdAccion().equals(identifier)) {
                return team;
            }
        }
        return null;
    }

}
