/*
 * NominaMandoMedioEJB.java
 * Creado el 29/Nov/2016 1:33:04 PM
 *
 */

package mx.gob.saludtlax.rh.nomina.mandosmedios;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * Este EJB implementa las operaciones de negocio de las nominas nominas de
 * mandos medios.
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Stateless
public class NominaMandoMedioEJB implements NominaMandoMedio {

    private static final long serialVersionUID = 256857878566819149L;

    @Inject
    private NominaMandoMedioService mandoMedioService;

    @Override
    public void crear(NominaMandoMedioDTO nominaMandoMedio) {
        if (nominaMandoMedio == null) {
            throw new ValidacionException(
                    "La información de la nomina de mando medio no debe estar vacia.",
                    ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (ValidacionUtil.esMenorQueUno(nominaMandoMedio.getIdEmpleado())) {
            throw new ValidacionException(
                    "El ID del empleado debe ser mayor que cero o no nulo.",
                    ValidacionCodigoError.NUMERO_NEGATIVO);
        }

        if (ValidacionUtil.esMenorQueUno(nominaMandoMedio.getIdAdscripcion())) {
            throw new ValidacionException(
                    "El ID de la adscripcion debe ser mayor que cero o no nulo.",
                    ValidacionCodigoError.NUMERO_NEGATIVO);
        }

        if (ValidacionUtil
                .esMenorQueUno(nominaMandoMedio.getIdPuestoGeneral())) {
            throw new ValidacionException(
                    "El ID del puesto debe ser mayor que cero o no nulo.",
                    ValidacionCodigoError.NUMERO_NEGATIVO);
        }

        mandoMedioService.crear(nominaMandoMedio);
    }

    @Override
    public NominaMandoMedioDTO obtenerPorId(Integer id) {
        if (ValidacionUtil.esMenorQueUno(id)) {
            throw new ValidacionException(
                    "El ID de la nomina del mando medio debe ser mayor que cero o no nulo.",
                    ValidacionCodigoError.NUMERO_NEGATIVO);
        }

        return mandoMedioService.obtenerPorId(id);
    }

    @Override
    public List<NominaMandoMedioDTO> consultarTodos() {
        return mandoMedioService.consultarTodos();
    }

    @Override
    public void actualizar(NominaMandoMedioDTO nominaMandoMedio) {
        if (nominaMandoMedio == null) {
            throw new ValidacionException(
                    "La información de la nomina de mando medio no debe estar vacia.",
                    ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (ValidacionUtil
                .esMenorQueUno(nominaMandoMedio.getIdNominaMandoMedio())) {
            throw new ValidacionException(
                    "El ID de la nomina de mando menio debe ser mayor que cero o no nulo.",
                    ValidacionCodigoError.NUMERO_NEGATIVO);
        }

        if (ValidacionUtil.esMenorQueUno(nominaMandoMedio.getIdEmpleado())) {
            throw new ValidacionException(
                    "El ID del empleado debe ser mayor que cero o no nulo.",
                    ValidacionCodigoError.NUMERO_NEGATIVO);
        }

        if (ValidacionUtil.esMenorQueUno(nominaMandoMedio.getIdAdscripcion())) {
            throw new ValidacionException(
                    "El ID de la adscripcion debe ser mayor que cero o no nulo.",
                    ValidacionCodigoError.NUMERO_NEGATIVO);
        }

        if (ValidacionUtil
                .esMenorQueUno(nominaMandoMedio.getIdPuestoGeneral())) {
            throw new ValidacionException(
                    "El ID del puesto debe ser mayor que cero o no nulo.",
                    ValidacionCodigoError.NUMERO_NEGATIVO);
        }

        mandoMedioService.actualizar(nominaMandoMedio);
    }

    @Override
    public void eliminar(Integer id) {
        if (ValidacionUtil.esMenorQueUno(id)) {
            throw new ValidacionException(
                    "El ID de la nomina del mando medio debe ser mayor que cero o no nulo.",
                    ValidacionCodigoError.NUMERO_NEGATIVO);
        }

        mandoMedioService.eliminar(id);
    }

}
