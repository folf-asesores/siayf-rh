/*
 *
 */

package mx.gob.saludtlax.rh.historialacademico;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

/**
 * @author Leila Schiaffini Ehuan
 * @since 09/06/2016 12:54:18
 *
 */
@Stateless
public class HistorialAcademicoEJB implements HistorialAcademico {

    @Inject
    private HistorialAcademicoService historialAcaedmicoService;

    @Override
    public List<HistorialAcademicoDTO> consultarHistorialAcademicoEmpleado(Integer idEmpleado) {
        return historialAcaedmicoService.listaHistorialAcademicoPorIdEmpleado(idEmpleado);
    }

    @Interceptors({ HistorialAcademicoValidator.class })
    @Override
    public void crearHistorialAcademico(NuevoHistorialDTO nuevoHistorial, boolean esEmpleado) {
        historialAcaedmicoService.crearHistorial(nuevoHistorial, esEmpleado);

    }

    @Override
    public void actualizarAdjuntoHistorial(Integer idHistorialAcademico) {
        historialAcaedmicoService.actualizarAdjuntoHistorial(idHistorialAcademico);

    }

    /*
     * (non-Javadoc)
     *
     * @see mx.gob.saludtlax.rh.historialacademico.HistorialAcademico#
     * actualizarHistorialAcademico(mx.gob.saludtlax.rh.historialacademico.
     * HistorialAcademicoDTO)
     */
    @Interceptors({ HistorialAcademicoActualizarValidator.class })
    @Override
    public void actualizarHistorialAcademico(HistorialAcademicoDTO actualizarHistorial) {
        historialAcaedmicoService.actualizarHistorialAcademico(actualizarHistorial);
    }

}
