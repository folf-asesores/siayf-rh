/*
 *
 */

package mx.gob.saludtlax.rh.empleados.interinatos;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.util.ValidacionUtil;
import mx.gob.saludtlax.rh.vacantes.seleccion.EnumTipoCandidato;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 21/11/2016 19:23:34
 */
public class InterinatoValidator {

    @AroundInvoke
    public Object validate(InvocationContext context) throws Exception {

        InterinatoDTO dto = (InterinatoDTO) context.getParameters()[0];
        if (dto == null) {
            throw new ValidacionException("Los datos de la solicitud de interinato son requeridos.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (!ValidacionUtil.esNumeroPositivo(dto.getIdPuesto())) {
            throw new ValidacionException("El puesto es requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (!ValidacionUtil.esNumeroPositivo(dto.getTipoCandidato())) {
            throw new ValidacionException("El tipo de candidato es requerido", ValidacionCodigoError.VALOR_REQUERIDO);
        }
        if (!ValidacionUtil.esNumeroPositivo(dto.getIdUsuario())) {
            throw new ValidacionException("El usuario es requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (!ValidacionUtil.esNumeroPositivo(dto.getIdContexto())) {

            if (dto.getTipoCandidato() == EnumTipoCandidato.ASPIRANTE) {
                throw new ValidacionException("El aspirante es requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
            } else {
                throw new ValidacionException("El empleado es requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
            }

        }

        if (dto.getFechaIngreso() == null) {
            throw new ValidacionException("La fecha de ingreso es requerida.", ValidacionCodigoError.VALOR_REQUERIDO);
        }
        if (ValidacionUtil.esCadenaVacia(dto.getNumeroCuenta())) {
            throw new ValidacionException("El número de cuenta es requerida.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (!ValidacionUtil.tieneCadenaFormatoNumero(dto.getNumeroCuenta())) {
            throw new ValidacionException("El número de cuenta solo debe estar conformado por números.", ValidacionCodigoError.FORMATO_INVALIDO);
        }
        return context.proceed();
    }
}
