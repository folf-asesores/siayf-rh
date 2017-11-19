/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import mx.gob.saludtlax.rh.excepciones.SistemaCodigoError;
import mx.gob.saludtlax.rh.excepciones.SistemaException;

public class FaltaContadaRepository extends
        GenericRepository<FaltaContadaEntity, Integer> implements Serializable {

    private static final long serialVersionUID = 4539001731281744855L;

    public List<FaltaContadaEntity> consultarFatasContadas(
            NominaEmpleadoEntity nominaEmpleadoEntity) {
        return em.createQuery(
                "SELECT fc FROM FaltaContadaEntity AS fc WHERE fc.nominaEmpleado =:nominaEmpleadoEntity",
                FaltaContadaEntity.class)
                .setParameter("nominaEmpleadoEntity", nominaEmpleadoEntity)
                .getResultList();
    }

    public boolean faltaEstaContada(Integer idFalta) {
        try {
            em.createQuery(
                    "SELECT i.idFaltaContada FROM FaltaContadaEntity AS i "
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

    public List<FaltaContadaEntity> obtenerPorMovimientoContrato(
            MovimientosNominaContratosEntity movimientoNominaEventual) {
        return em.createQuery(
                "SELECT fc FROM FaltaContadaEntity AS fc WHERE fc.movimientoNominaEventual =:movimientoNominaEventual",
                FaltaContadaEntity.class)
                .setParameter("movimientoNominaEventual",
                        movimientoNominaEventual)
                .getResultList();
    }

    public boolean faltaEstaContada(
            MovimientosNominaContratosEntity movimientosNominaContrato,
            Date fechaFalta) {
        try {
            em.createQuery(
                    " SELECT fc                                                             "
                            + "FROM FaltaContadaEntity AS fc                                         "
                            + " WHERE                                                               "
                            + " fc.movimientoNominaEventual =:movimientosNominaContrato             "
                            + " AND                                                                 "
                            + " fc.fechaFalta = :fechaFalta                                         ",
                    FaltaContadaEntity.class)
                    .setParameter("movimientosNominaContrato",
                            movimientosNominaContrato)
                    .setParameter("fechaFalta", fechaFalta).getSingleResult();
            return true;
        } catch (NoResultException exception) {
            return false;
        }
    }
}