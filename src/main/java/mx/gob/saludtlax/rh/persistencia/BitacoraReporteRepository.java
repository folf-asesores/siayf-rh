/**
 *
 */
package mx.gob.saludtlax.rh.persistencia;

import java.util.UUID;

/**
 * @author eduardo
 *
 */
public class BitacoraReporteRepository extends GenericRepository<BitacoraReporteEntity, UUID> {

    /**
     *
     */
    private static final long serialVersionUID = 357048721113404463L;

    @Override
    public void eliminar(BitacoraReporteEntity entity) {
        throw new UnsupportedOperationException("No se permite la modificación de elementos de la bitacora.");
    }

    @Override
    public BitacoraReporteEntity actualizar(BitacoraReporteEntity entity) {
        throw new UnsupportedOperationException("No se permite la eliminación de elementos de la bitacora.");
    }

}
