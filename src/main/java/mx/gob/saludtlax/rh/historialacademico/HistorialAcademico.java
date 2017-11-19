/*
 *
 */

package mx.gob.saludtlax.rh.historialacademico;

import java.util.List;

/**
 * @author Leila Schiaffini Ehuan
 * @since 09/06/2016 12:53:43
 *
 */
public interface HistorialAcademico {

    /**
     * Consulta el historial academico del empleado.
     *
     * @param idEmpleado
     */
    public List<HistorialAcademicoDTO> consultarHistorialAcademicoEmpleado(Integer idEmpleado);

    /**
     * Crea un historial academico de un aspirante o empleado
     *
     * @param información
     *            del aspirante o empleado
     * @param esEmpleado
     *            indica si el historial es del empleado o del aspirante.
     */
    public void crearHistorialAcademico(NuevoHistorialDTO nuevoHistorial, boolean esEmpleado);

    public void actualizarHistorialAcademico(HistorialAcademicoDTO actualizarHistorial);

    public void actualizarAdjuntoHistorial(Integer idHistorialAcademico);

}
