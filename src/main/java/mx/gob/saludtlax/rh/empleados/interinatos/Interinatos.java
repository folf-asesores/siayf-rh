/*
 *
 */

package mx.gob.saludtlax.rh.empleados.interinatos;

import java.util.List;

/**
 * @author Leila Schiaffini Ehuan
 * @since 16/09/2016 14:37:12
 *
 */
public interface Interinatos {

    /**
     * Consulta los puestos disponibles a interinato por permiso o por falta de
     * recurso.
     */
    public List<DisponiblesInterinatoDTO> consultarCandidatosInterinato(Integer tipoBusqueda);

    public void solicitarInterinato(InterinatoDTO interinato);
}
