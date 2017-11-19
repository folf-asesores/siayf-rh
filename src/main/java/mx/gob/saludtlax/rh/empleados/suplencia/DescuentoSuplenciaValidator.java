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
 * @since 17/11/2016 21:32:59
 */
public class DescuentoSuplenciaValidator {
    @AroundInvoke
    public Object validate(InvocationContext context) throws Exception {
        DescuentoSuplenciaDTO dto = (DescuentoSuplenciaDTO) context
                .getParameters()[0];

        if (dto == null) {
            throw new ValidacionException(
                    "La información de la edición es requerida.",
                    ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (!ValidacionUtil.esNumeroPositivo(dto.getIdDetalleSuplencia())) {
            throw new ValidacionException(
                    "Seleccione el detalle de suplencia a editar",
                    ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (!ValidacionUtil.esNumeroPositivo(dto.getImporteADescontar())) {
            throw new ValidacionException(
                    "El importe a descontar es requerido.",
                    ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (!ValidacionUtil.esNumeroPositivo(dto.getIdUsuarioLogeado())) {
            throw new ValidacionException("El usuario es requerido.",
                    ValidacionCodigoError.VALOR_REQUERIDO);
        }

        return context.proceed();
    }
}
