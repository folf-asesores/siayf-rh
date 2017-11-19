/*
 * Copyright ® 2016
 */

package mx.gob.saludtlax.rh.configuracion.terceroinstitucional;

import java.util.List;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 * 
 * @version 1.0
 * @since 24/05/2016 10:56:36
 */
public interface TerceroInstitucional {

    /**
     * Crea un tercero institucional de operaciones y pagos
     *
     * @param terceroInstitucional
     */
    void crearTerceroInstitucional(
            TerceroInstitucionalDTO terceroInstitucional);

    /**
     * Actualiza el tercero institucional de operaciones y pagos
     *
     * @param terceroInstitucional
     */
    void actualizarTerceroInstitucional(
            TerceroInstitucionalDTO terceroInstitucional);

    /**
     * Elimina el tercero institucional de operaciones y pagos
     *
     * @param idTerceroInstitucional
     */
    void eliminarTerceroInstitucional(Integer idTerceroInstitucional);

    /**
     * Obtiene la lista de todos los terceros institucionales registrados en la
     * BD
     *
     * @return
     */
    List<TerceroInstitucionalDTO> obtenerListaTerceroInstitucional();

    TerceroInstitucionalDTO obtenerporClave(String clave, String partida);

}
