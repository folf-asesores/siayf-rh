/*
 *
 */

package mx.gob.saludtlax.rh.empleados.suplencia.relacionpersonal;

import java.util.List;

import javax.faces.model.SelectItem;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 *
 */
public interface RelacionPersonalSuplente {

    public List<RelacionPersonalSuplenteDTO> obtenerListaRelacionPersonalSuplente(
            Integer numeroQuincena, Integer ejercicioFiscal,
            Integer idCentroResponsabilidad);

    public List<SelectItem> listaEjercicioFiscal();

    public List<SelectItem> listaCentroResponsabilidad();

}
