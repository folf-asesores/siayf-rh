/*
 * Firma.java
 * Creado el 15/Nov/2016 4:56:40 PM
 *
 */

package mx.gob.saludtlax.rh.configuracion.firma;

import java.util.List;

import javax.ejb.Local;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Local
public interface Firma {

    /**
     * Permite la creación de una nuevo tipo de firma.
     * Con lo que se identifica una adscripción y quienes son los responsables
     * de firmar en dicha adscripción.
     *
     * @param firma
     *            los datos sobre la firma.
     * @return el identificador con el que se guardo.
     */
    Integer crear(FirmaDTO firma);

    /**
     * Permite obtener la información de una firma especifica por medio de su ID.
     *
     * @param idFirma
     *            el identificador de la firma.
     * @return en caso de existir devuelve un DTO con la información de la firma
     *         en caso contrario retornara <code>null</code>.
     */
    FirmaDTO obtenerPorId(Integer idFirma);

    /**
     * Permite obtener todas las firmas disponibles.
     *
     * @return una lista con todas las firmas disponibles.
     */
    List<FirmaDTO> consultarTodas();

    /**
     * Permite actualizar la información de una firma ya existente.
     * La firma debe existir previamente para poder ser modificada al igual que
     * la adscripción.
     *
     * @param firma
     *            la firma a modificar.
     */
    void actualizar(FirmaDTO firma);

    /**
     * Permite eliminar una firma por medio de su ID.
     *
     * @param idFirma
     *            el ID de la firma que será eliminada.
     */
    void eliminar(Integer idFirma);

}
