/*
 *
 */

package mx.gob.saludtlax.rh.expediente.empleado;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.excepciones.BusinessException;
import mx.gob.saludtlax.rh.persistencia.EmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.ExpedienteEmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.ExpedienteEmpleadoRepository;

/**
 * @author Leila Schiaffini Ehuan
 * @since 13/06/2016 11:12:44
 *
 */
public class ExpedienteEmpleadoService {

    @Inject
    private EmpleadoRepository empleadoRepository;
    @Inject
    private ExpedienteEmpleadoRepository expedienteEmpleadoRepository;

    protected void crearExpediente(ExpedienteDTO dto) {
        String contexto = "Apertura expediente: ";

        if (!empleadoRepository.existeIdEmpleado(dto.getIdEmpleado())) {
            throw new BusinessException(contexto + "El identificador enviado no corresponde a un empleado, seleccione otra opcion.");
        }

        if (expedienteEmpleadoRepository.existeExpedienteAsignadoEmpleado(dto.getIdEmpleado())) {
            throw new BusinessException(contexto + "El empleado ya tiene aperturado un expediente.");
        }

        if (expedienteEmpleadoRepository.existeNumeroExpediente(dto.getNumeroExpediente())) {
            throw new BusinessException(contexto + "El numero de expediente ya ha sido asignado, ingrese un nuevo número.");
        }

        ExpedienteEmpleadoEntity expediente = new ExpedienteEmpleadoEntity();
        expediente.setIdEmpleado(dto.getIdEmpleado());
        expediente.setNumeroExpediente(dto.getNumeroExpediente().trim().toUpperCase());
        expedienteEmpleadoRepository.crear(expediente);

    }

    protected boolean tieneExpedienteAperturado(Integer idEmpleado) {
        return expedienteEmpleadoRepository.existeExpedienteAsignadoEmpleado(idEmpleado);
    }

    protected String numeroExpedienteEmpleado(Integer idEmpleado) {

        return expedienteEmpleadoRepository.numeroExpedienteEmpleado(idEmpleado);
    }

    protected Integer obtenerIdExpedienteEmpleado(Integer idEmpleado) {
        return expedienteEmpleadoRepository.obtenerIdExpedienteEmpleado(idEmpleado);
    }

}
