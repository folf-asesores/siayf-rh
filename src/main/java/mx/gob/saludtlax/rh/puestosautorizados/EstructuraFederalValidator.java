/**
 * 
 */
package mx.gob.saludtlax.rh.puestosautorizados;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 15/03/2017
 */
public class EstructuraFederalValidator {

	@AroundInvoke
	public Object validate(InvocationContext context) throws Exception {
		EstructuraNominaDTO dto = (EstructuraNominaDTO) context.getParameters()[0];

		if (dto == null) {
			throw new ValidacionException("Los datos de la estructura nómina son requeridos.",
					ValidacionCodigoError.VALOR_REQUERIDO);
		}

		if (!ValidacionUtil.esNumeroPositivo(dto.getIdPuesto())) {
			throw new ValidacionException("El puesto es requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
		}

		if (!ValidacionUtil.esNumeroPositivo(dto.getIdUsuario())) {
			throw new ValidacionException("El usuario es requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
		}

		if (ValidacionUtil.esCadenaVacia(dto.getSubfuncion())) {
			throw new ValidacionException("Seleccione una subfuncion.", ValidacionCodigoError.VALOR_REQUERIDO);
		}

		if (ValidacionUtil.esCadenaVacia(dto.getTabuladorPuesto())) {
			throw new ValidacionException("Seleccione un tabulador de puesto.", ValidacionCodigoError.VALOR_REQUERIDO);
		}

		if (ValidacionUtil.esCadenaVacia(dto.getPagaduria())) {
			throw new ValidacionException("Seleccione una pagaduría.", ValidacionCodigoError.VALOR_REQUERIDO);
		}

		if (ValidacionUtil.esCadenaVacia(dto.getIndicadorMando())) {
			throw new ValidacionException("Seleccione un indicador de mando.", ValidacionCodigoError.VALOR_REQUERIDO);
		}

		if (ValidacionUtil.esCadenaVacia(dto.getTipoUnidad())) {
			throw new ValidacionException("Seleccione un tipo de unidad.", ValidacionCodigoError.VALOR_REQUERIDO);
		}

		if (ValidacionUtil.esCadenaVacia(dto.getTipoPago())) {
			throw new ValidacionException("Seleccione un tipo de pago.", ValidacionCodigoError.VALOR_REQUERIDO);
		}

		if (ValidacionUtil.esCadenaVacia(dto.getFinanciamiento())) {
			throw new ValidacionException("Seleccione un financiamiento.", ValidacionCodigoError.VALOR_REQUERIDO);
		}

		if (ValidacionUtil.esCadenaVacia(dto.getJornadaTrabajo())) {
			throw new ValidacionException("Seleccione una jornada de trabajo", ValidacionCodigoError.VALOR_REQUERIDO);
		}
		return context.proceed();
	}
}
