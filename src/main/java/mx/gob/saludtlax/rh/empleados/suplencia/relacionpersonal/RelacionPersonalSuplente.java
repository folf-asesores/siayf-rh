/*
 *
 */

package mx.gob.saludtlax.rh.empleados.suplencia.relacionpersonal;

import java.util.List;

import javax.faces.model.SelectItem;

/**
 * @author Eduardo Mex
 *
 */
public interface RelacionPersonalSuplente {

    public List<RelacionPersonalSuplenteDTO> obtenerListaRelacionPersonalSuplente(Integer numeroQuincena, Integer ejercicioFiscal,
            Integer idCentroResponsabilidad);

    public List<SelectItem> listaEjercicioFiscal();

    public List<SelectItem> listaCentroResponsabilidad();

}
