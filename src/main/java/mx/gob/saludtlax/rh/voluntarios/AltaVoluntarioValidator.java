/*
 *
 */

package mx.gob.saludtlax.rh.voluntarios;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.util.FechaUtil;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 17/11/2016 16:27:12
 */
public class AltaVoluntarioValidator {

    @AroundInvoke
    public Object validate(InvocationContext context) throws Exception {
        AltaVoluntarioDTO dto = (AltaVoluntarioDTO) context.getParameters()[0];
        if (dto == null) {
            throw new ValidacionException("La información del voluntario es requerida.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (!ValidacionUtil.esNumeroPositivo(dto.getIdAspirante())) {
            throw new ValidacionException("El aspirante es requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (!ValidacionUtil.esNumeroPositivo(dto.getSueldoMensual())) {
            throw new ValidacionException("El sueldo del empleado debe ser mayor a 0", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (ValidacionUtil.esCadenaVacia(dto.getNumeroCuenta())) {
            throw new ValidacionException("El numero de cuenta es requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (!ValidacionUtil.tieneCadenaFormatoNumero(dto.getNumeroCuenta())) {
            throw new ValidacionException("Formato invalido de cuenta, verique que solo contenga números.", ValidacionCodigoError.FORMATO_INVALIDO);
        }

        if (dto.getFechaInicial() == null || dto.getFechaFinal() == null) {
            throw new ValidacionException("Es requerido especificar el periodo de contratación.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (FechaUtil.esFechaInicialMayorQueFinal(dto.getFechaInicial(), dto.getFechaFinal())) {
            throw new ValidacionException("La fecha de inicio no puede ser mayor a la final", ValidacionCodigoError.FECHA_INCORRECTA);
        }

        if (dto.getFechaIngreso() == null) {
            throw new ValidacionException("La fecha de ingreso es requerida.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (FechaUtil.esFechaInicialMayorQueFinal(dto.getFechaInicial(), dto.getFechaIngreso())) {
            throw new ValidacionException("La fecha de ingreso no puede ser menor que la fecha inicial de contratación.",
                    ValidacionCodigoError.FECHA_INCORRECTA);
        }
        return context.proceed();
    }

}
