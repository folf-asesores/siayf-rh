/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import mx.gob.saludtlax.rh.util.Configuracion;

/**
 * @author Eduardo Mex
 * @email Lic.Eduardo_Mex@hotmail.com
 * @version 1.0
 * @since 07/06/2016 18:50:58
 */
public class RiesgoPuestoRepository extends GenericRepository<RiesgoPuestoEntity, Integer> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -7822948480531250198L;

    @PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
    private EntityManager entityManager;

    public List<RiesgoPuestoEntity> obtenerListaRiesgoPuesto() {
        return entityManager.createQuery("SELECT r FROM RiesgoPuestoEntity AS r ORDER BY r.idRiesgoPuesto", RiesgoPuestoEntity.class).getResultList();
    }

    public boolean validarClave(Integer clave) {
        boolean resultado = false;
        try {
            RiesgoPuestoEntity riesgoPuestoEntity = entityManager
                    .createQuery("SELECT r FROM RiesgoPuestoEntity AS r WHERE r.clave =:clave", RiesgoPuestoEntity.class).setParameter("clave", clave)
                    .getSingleResult();

            resultado = true;
        } catch (NoResultException ex) {
            resultado = false;
        }
        return resultado;
    }

}
