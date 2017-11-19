/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.gob.saludtlax.rh.util.Configuracion;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 07/03/2016-21:15:44
 */
public class EstadoRepository {
    @PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
    private EntityManager entityManager;

    /**
     * Consulta los estados por identificador
     *
     * @param idEstado
     */
    public EstadoEntity estadoPorId(Integer idEstado) {
        return entityManager.find(EstadoEntity.class, idEstado);

    }

    /**
     * Consulta los estados del catalogo.
     *
     */
    public List<EstadoEntity> estados() {
        List<EstadoEntity> estados = entityManager
                .createQuery("SELECT e FROM EstadoEntity AS e",
                        EstadoEntity.class)
                .getResultList();
        return estados;
    }

}
