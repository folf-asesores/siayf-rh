/*
 *
 */

package mx.gob.saludtlax.rh.nomina.presupuestocalendario;

import java.util.List;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 *
 */
public interface PresupuestoCalendario {

    public void crearPresupuestoCalendario(PresupuestoCalendarioDTO dto);

    public void actualizarPresupuestoCalendario(PresupuestoCalendarioDTO dto);

    public void eliminarPresupuestoCalendario(Integer idPresupuestoCalendario);

    public List<PresupuestoCalendarioDTO> obtenerListaPresupuestoCalendario();

    public List<PresupuestoCalendarioDTO> obtenerListaPresupuestoCalendarioPorAnio(
            Integer anio);

}
