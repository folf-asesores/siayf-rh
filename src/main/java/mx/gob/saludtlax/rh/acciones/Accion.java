
package mx.gob.saludtlax.rh.acciones;

import java.util.List;

public interface Accion {

    void crearAccion(AccionDTO accionDTO);

    void editarAccion(AccionDTO accionDTO);

    Boolean eliminarAccion(Integer idAccion);

    List<AccionDTO> obtenerListaAcciones();

    List<AccionDTO> obtenerListaAccionesPorArea(Integer idArea);

    List<AccionDTO> obtenerListaAccionesPorModulo(Integer idModulo);

    List<AccionDTO> obtenerAccionesFiltradas(Integer idmodulo,
            List<Integer> idAccionFiltro);

}
