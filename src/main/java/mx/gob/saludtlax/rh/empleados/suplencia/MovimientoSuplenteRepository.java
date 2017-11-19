/*
 *
 */

package mx.gob.saludtlax.rh.empleados.suplencia;

import java.util.List;

import mx.gob.saludtlax.rh.persistencia.GenericRepository;
import mx.gob.saludtlax.rh.persistencia.MovimientoSuplenteEntity;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 13/01/2017 12:32:14
 */
public class MovimientoSuplenteRepository extends GenericRepository<MovimientoSuplenteEntity, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = 1061037579812507971L;

    public Integer obtenerVacacionesEjercicioFiscal(int ejercicioFiscal, Integer idSuplente) {
        return em
                .createQuery("SELECT SUM(m.totalDias) FROM MovimientoSuplenteEntity AS m WHERE m.suplente.idSuplenteAutorizado =:idSuplente "
                        + "AND m.ejercicioFiscalPeriodo =:ejercicio", Integer.class)
                .setParameter("ejercicio", ejercicioFiscal).setParameter("idSuplente", idSuplente).getSingleResult();

    }

    /**
     * Consulta los movimientos del suplente
     */
    public List<MovimientoSuplenteEntity> consultarMovimientosPorIdSuplenteMovimiento(Integer idSuplente, String movimiento) {
        return em.createQuery("SELECT m FROM MovimientoSuplenteEntity AS m WHERE m.suplente.idSuplenteAutorizado =:idSuplente AND m.movimiento =:movimiento",
                MovimientoSuplenteEntity.class).setParameter("idSuplente", idSuplente).setParameter("movimiento", movimiento).getResultList();
    }

}
