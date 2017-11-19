
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.util.List;

public interface Repository<T, K extends Serializable> extends Serializable {

    /**
     * Permite persistir en el almacen de datos una nueva entidad.
     *
     * @param entity
     *            la entidad a persistir.
     * @return la entidad persistida.
     */
    T crear(T entity);

    /**
     * Permite obtener una entida por medio de su ID.
     *
     * @param key
     *            el ID de la entidad a buscar.
     * @return la entidad si está en el almacen de datos.
     */
    T obtenerPorId(K key);

    /**
     * Es un contador, es decir permite conocer la cantidad de entidades hay
     * persistidas en el almacen de datos.
     *
     * @return la cantidad de registros que hay en el almacen de datos.
     */
    Long obtenerNumeroRegistros();

    /**
     * Permite obtener todos las entidades que hay persistidad en el almacen de
     * datos. Está pensado para catálogos pequeños.
     *
     * <p>
     * <strong>Importante:</strong> Usar con mucha prudencia ya que puede
     * desbordar la memoria del servidor si existen muchos datos.
     * </p>
     *
     * <p>
     * <strong>Recomendación:</strong> Se sugiere el uso de
     * {@link Repository#consultarPaginado(int, int)} para consultas
     * extentensas.
     * </p>
     *
     * @return una lista con las entidades que están en el almacen de datos.
     */
    List<T> consultarTodos();

    /**
     * Permite obtener la cantidad de entidades que se indique, iniciado por una
     * posición fija.
     *
     * @param cantidadResultados
     *            la cantidad de entidades que se consultará.
     * @param primerResultado
     *            la posición por la que se iniciará la consulta.
     * @return una lista con las entidades que están en el almacen de datos.
     */
    List<T> consultarPaginado(int cantidadResultados, int primerResultado);

    /**
     * Permite actualizar una entidad que ya está en el almacen de datos.
     *
     * @param entity
     *            la entidad a actualizar.
     * @return la entidad actualizada.
     */
    T actualizar(T entity);

    /**
     * Permite eliminar una entidad del almacen de datos por medio de u ID.
     *
     * @param key
     *            el ID de la entidad a eliminar.
     */
    void eliminarPorId(K key);

    /**
     * Permite eliminar una entidad del almacen de datos.
     *
     * @param entity
     *            la entidad a eliminar.
     */
    void eliminar(T entity);
}
