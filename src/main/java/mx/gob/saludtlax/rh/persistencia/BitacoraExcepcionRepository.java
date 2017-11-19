/*
 * BitacoraExcepcionRepository.java
 * Creado el 25/06/2016 12:00:21 AM
 *
 */

package mx.gob.saludtlax.rh.persistencia;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class BitacoraExcepcionRepository
        extends GenericRepository<BitacoraExcepcionEntity, Integer> {

    private static final long serialVersionUID = -4010241772009372583L;

    @Override
    public BitacoraExcepcionEntity actualizar(BitacoraExcepcionEntity entity) {
        throw new UnsupportedOperationException(
                "No se permite la actualización de la bitacora");
    }

    @Override
    public void eliminar(BitacoraExcepcionEntity entity) {
        throw new UnsupportedOperationException(
                "No se permite la eliminación de la bitacora");
    }

}
