/*
 *
 */

package mx.gob.saludtlax.rh.empleados.suplencia;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 22/11/2016 00:34:50
 */
public class AltaSuplenciaValidator {
    @AroundInvoke
    public Object validate(InvocationContext context) throws Exception {

        AltaSuplenciaDTO dto = (AltaSuplenciaDTO) context.getParameters()[0];
        if (dto == null) {
            throw new ValidacionException("Los datos del alta de la suplencia son requeridos.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (!ValidacionUtil.esNumeroPositivo(dto.getIdSuplenteAutorizado())) {
            throw new ValidacionException("El suplente es requerido", ValidacionCodigoError.VALOR_REQUERIDO);

        }

        if (!ValidacionUtil.esNumeroPositivo(dto.getNumeroQuincena())) {
            throw new ValidacionException("El número de quincena es requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (!ValidacionUtil.esNumeroPositivo(dto.getEjercicioFiscal())) {
            throw new ValidacionException("El ejercicio fiscal de la quincena es requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (!ValidacionUtil.esNumeroPositivo(dto.getIdCausaSuplencia())) {
            throw new ValidacionException("El motivo de la suplencia es requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (!ValidacionUtil.esNumeroPositivo(dto.getIdTabulador())) {
            throw new ValidacionException("El tabulador es requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
        }
        if (dto.getIdCausaSuplencia() != EnumTipoSuplencia.AREA_DESCUBIERTA) {
            if (!ValidacionUtil.esNumeroPositivo(dto.getIdEmpleado())) {
                throw new ValidacionException("El empleado es requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
            }
        }

        if (dto.getFechaInicio() == null) {
            throw new ValidacionException("La fecha de inicio es requerida..", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (dto.getFechaFin() == null) {
            throw new ValidacionException("La fecha final es requerida..", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        return context.proceed();
    }
}
