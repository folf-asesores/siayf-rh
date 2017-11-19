/*
 * ProyectoEJB.java
 * Creado el 23/07/2016 09:37:35 PM
 *
 */

package mx.gob.saludtlax.rh.configuracion.proyecto;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Stateless
public class ProyectoEJB implements Proyecto {

    @Inject
    private ProyectoService proyectoService;

    @Override
    public ProyectoDTO obtenerPorId(int idProyecto) {
        return proyectoService.obtenerPorId(idProyecto);
    }

    @Override
    public int crear(ProyectoDTO proyecto) {
        return proyectoService.crear(proyecto);
    }

    @Override
    public void actualizar(ProyectoDTO proyecto) {
        proyectoService.actualizar(proyecto);
    }

    @Override
    public List<ProyectoDTO> consultarProyectosPorEjercicioFiscal(
            int ejercicioFiscal) {
        return proyectoService
                .consultarProyectosPorEjercicioFiscal(ejercicioFiscal);
    }

    @Override
    public void eliminar(int idProyecto) {
        proyectoService.eliminar(idProyecto);
    }
}
