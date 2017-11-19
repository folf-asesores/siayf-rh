/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

/**
 * @author Leila Schiaffini Ehuan
 * @since 02/09/2016 14:48:35
 *
 */
public class TiposDuracionJornadaRepository extends GenericRepository<TiposDuracionJornadaEntity, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = -7637314766512124024L;

    public List<TiposDuracionJornadaEntity> consultarTiposJornadas() {
        return em.createQuery("SELECT t FROM TiposDuracionJornadaEntity AS t", TiposDuracionJornadaEntity.class).getResultList();
    }

}
