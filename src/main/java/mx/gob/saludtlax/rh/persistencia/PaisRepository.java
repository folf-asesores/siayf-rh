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
 * @since 07/03/2016-20:10:18
 */
public class PaisRepository {
    @PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
    private EntityManager entityManager;

    /**
     * Consulta el país por identificador
     */
    public PaisEntity paisPorId(Integer idPais) {
        return entityManager.find(PaisEntity.class, idPais);
    }

    /**
     * Consulta la lista de paises
     */
    public List<PaisEntity> paises() {

        List<PaisEntity> lista = entityManager
                .createQuery("SELECT p FROM PaisEntity AS p", PaisEntity.class)
                .getResultList();
        return lista;
    }

}
