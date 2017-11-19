/*
 *
 */

package mx.gob.saludtlax.rh.nomina.presupuestocalendario;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author Eduardo Mex
 *
 */
@Stateless
public class PresupuestoCalendarioEJB implements PresupuestoCalendario, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1749010798869208535L;

    @Inject
    private PresupuestoCalendarioService presupuestoCalendarioService;

    @Override
    public void crearPresupuestoCalendario(PresupuestoCalendarioDTO dto) {
        presupuestoCalendarioService.crearPresupuestoCalendario(dto);
    }

    @Override
    public void actualizarPresupuestoCalendario(PresupuestoCalendarioDTO dto) {
        presupuestoCalendarioService.actualizarPresupuestoCalendario(dto);
    }

    @Override
    public void eliminarPresupuestoCalendario(Integer idPresupuestoCalendario) {
        presupuestoCalendarioService.eliminarPresupuestoCalendario(idPresupuestoCalendario);
    }

    @Override
    public List<PresupuestoCalendarioDTO> obtenerListaPresupuestoCalendario() {

        return presupuestoCalendarioService.obtenerListaPresupuestoCalendario();
    }

    @Override
    public List<PresupuestoCalendarioDTO> obtenerListaPresupuestoCalendarioPorAnio(Integer anio) {
        return presupuestoCalendarioService.obtenerListaPresupuestoCalendarioPorAnio(anio);
    }

}
