/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import mx.gob.saludtlax.rh.excepciones.SistemaCodigoError;
import mx.gob.saludtlax.rh.excepciones.SistemaException;

public class FataContadaRepository extends
        GenericRepository<FataContadaEntity, Integer> implements Serializable {

    private static final long serialVersionUID = 4539001731281744855L;

    public List<FataContadaEntity> consultarFatasContadas(
            NominaEmpleadoEntity nominaEmpleadoEntity) {
        return em.createQuery(
                "SELECT e FROM FataContadaEntity AS e WHERE e.nominaEmpleado =:nominaEmpleadoEntity",
                FataContadaEntity.class)
                .setParameter("nominaEmpleadoEntity", nominaEmpleadoEntity)
                .getResultList();
    }

    public boolean faltaEstaContada(Integer idFalta) {
        try {
            em.createQuery(
                    "SELECT i.idFaltaContada FROM FataContadaEntity AS i "
                            + " WHERE " + " i.idFalta =:idFalta",
                    Integer.class).setParameter("idFalta", idFalta)
                    .getSingleResult();
            return true;
        } catch (NoResultException exception) {
            return false;
        } catch (NonUniqueResultException exception) {
            throw new SistemaException(
                    "La falta ha sido contada mas de una vez.",
                    SistemaCodigoError.ERROR_MULTIPLES_RESULTADOS);
        }
    }
}