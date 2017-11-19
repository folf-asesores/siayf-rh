/*
 *
 * Estrategia.java
 * Creado el Jul 12, 2016 10:21:13 AM
 *
 */

package mx.gob.saludtlax.rh.configuracion.estrategia;

import java.util.List;

import javax.ejb.Local;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Local
public interface Estrategia {

    /**
     * Permite guardar o crear una un DTO en el almacen de datos. Devolviendo el
     * ID con el que se guardo en el almacen de datos.
     *
     * @param dto
     *            un DTO con la información de la estrategia a guardar.
     * @return el ID con el que se guardo en el almacen de datos.
     */
    int crear(EstrategiaDTO dto);

    /**
     * Permite actualizar la información de una estrategia que ya existe en el
     * almacen de datos.
     *
     * @param dto
     *            un DTO con la información de la estrategia a guardar.
     */
    void actualizar(EstrategiaDTO dto);

    /**
     * Permite obtener una estrategia especifica por medio de us ID.
     *
     * @param idEstrategia
     *            el ID que identifica la estrategia.
     * @return un DTO con la información asociada al ID de la estrategia.
     */
    EstrategiaDTO obtenerPorId(int idEstrategia);

    /**
     * Consulata todas las estrategias disponible en el almacen de datos.
     *
     * @return una lista con todas las estrategias disponibles en el almacen de
     *         datos.
     */
    List<EstrategiaDTO> consultarEstrategias();

    /**
     * Permite obtener una lista con las descripción de las estrategias que
     * existen en el almacende datos y que coninciden con el criterio de
     * consulta.
     *
     * @param criterio
     *            una cadena con uno o más caráctateres que puedan
     *            encontrarse en la descripción de alguna estrategia.
     * @return una lista con las descripciones que coninciden con el criterio.
     */
    List<String> consultarDescripcionEstrategiaPorCriterio(String criterio);

    /**
     * Permite obtener el ID de la estrategia según la descripción,
     *
     * @param descripcion
     *            la descripción de una estrategia.
     * @return el ID de la estrategia encontrada.
     */
    Integer consultarIdEstrategiaPorDescripcion(String descripcion);

    /**
     * Permite eliminar una estrategia existente por medio de su ID.
     *
     * @param idEstrategia
     */
    void eliminar(int idEstrategia);

    /**
     * Permite saber si un código de estrategia ya existe.
     *
     * @param codigoEstrategia
     *            el código de la estrategia.
     * @return verdad si y sólo existe un código de estrategia registado.
     */
    boolean existeCodigoEstrategia(int codigoEstrategia);

}
