/*
 * ProductoNominaBitacoraRepository.java
 * Creado el 04/Jan/2017 9:00:44 AM
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import javax.persistence.TypedQuery;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class ProductoNominaBitacoraRepository extends GenericRepository<ProductoNominaBitacoraAperturaEntity, Integer> {

    private static final long serialVersionUID = -2109380069261097103L;
    private static final String OBTENER_EVENTOS = "SELECT evento" + " FROM ProductoNominaBitacoraEventoEntity AS evento"
            + " WHERE evento.bitacora.idBitacora = :idBitacora";

    public List<ProductoNominaBitacoraEventoEntity> obtenerEventosPorIdBitacora(Integer idBitacora) {
        TypedQuery<ProductoNominaBitacoraEventoEntity> query = em.createQuery(OBTENER_EVENTOS, ProductoNominaBitacoraEventoEntity.class);
        query.setParameter("idBitacora", idBitacora);
        return query.getResultList();
    }

}
