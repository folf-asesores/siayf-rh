/**
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
 * @Since 13/01/2017 11:40:04
 */
public class MovimientoSuplenteValidator {

	@AroundInvoke
	public Object validate(InvocationContext context) throws Exception {
		MovimientoSuplenteDTO dto = (MovimientoSuplenteDTO) context.getParameters()[0];

		if (dto == null) {
			throw new ValidacionException("Los datos del movimiento son requeridos.",
					ValidacionCodigoError.VALOR_REQUERIDO);
		}

		if (!ValidacionUtil.esNumeroPositivo(dto.getIdSuplente())) {
			throw new ValidacionException("El suplente es requerido", ValidacionCodigoError.VALOR_REQUERIDO);
		}

		if (!ValidacionUtil.esNumeroPositivo(dto.getIdUsuario())) {
			throw new ValidacionException("El usuario es requerido", ValidacionCodigoError.VALOR_REQUERIDO);
		}

		if (ValidacionUtil.esCadenaVacia(dto.getMovimiento())) {
			throw new ValidacionException("El movimiento es requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
		}

		if (dto.getFechaInicio() == null) {
			throw new ValidacionException("La fecha de inicio es requerida.", ValidacionCodigoError.VALOR_REQUERIDO);
		}

		if (dto.getFechaFin() == null) {
			throw new ValidacionException("La fecha fin es requerida.", ValidacionCodigoError.VALOR_REQUERIDO);
		}

		if (dto.getMovimiento().equals(EnumTipoMovimientoSuplente.VACACIONES)) {
			if (!ValidacionUtil.esNumeroPositivo(dto.getEjercicioFiscalPeriodo())) {
				throw new ValidacionException("Es requerido especificar el ejercicio al que pertenece el periodo",
						ValidacionCodigoError.VALOR_REQUERIDO);
			}
		}

		return context.proceed();
	}

}
