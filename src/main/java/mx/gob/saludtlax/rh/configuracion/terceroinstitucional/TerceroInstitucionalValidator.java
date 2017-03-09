/**
 * Copyright ® 2016
 */
package mx.gob.saludtlax.rh.configuracion.terceroinstitucional;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import mx.gob.saludtlax.rh.excepciones.ValidationException;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author Eduardo Mex
 * @email lic.eduardo_mex@hotmail.com
 * @version 1.0
 * @since 24/05/2016 14:19:30
 */
public class TerceroInstitucionalValidator {

	@AroundInvoke
	public Object validate(InvocationContext context) throws Exception {

		TerceroInstitucionalDTO dto = (TerceroInstitucionalDTO) context.getParameters()[0];
		String contexto = "Validación campos: ";

		if (dto == null) {
			throw new ValidationException(
					contexto + "Son requeridos los datos para el registro, dto nulo notifique a sistemas");
		}

		if (dto.getNumero().isEmpty()) {
			throw new ValidationException(contexto + "El número es requerido, ingrese el número");
		}

//		if (ValidacionUtil.esCadenaVacia(dto.getNombreEmpresa())) {
//			throw new ValidationException(
//					contexto + "El nombre de la empresa es requerido, ingrese el nombre de la empresa");
//		}

		if (ValidacionUtil.esCadenaVacia(dto.getConceptoDeduccion())) {
			throw new ValidationException(
					contexto + "El concepto de deducción es requerido, ingrese el concepto de deducción");
		}

		if (ValidacionUtil.esCadenaVacia(dto.getContrapartidaIdentificadora())) {
			throw new ValidationException(contexto + "La contrapartida identificadora es requerida, ingrese la contrapartida identificadora");
		}

//		if (ValidacionUtil.esCadenaVacia(dto.getGiro())) {
//			throw new ValidationException(contexto + "El giro es requerido, ingrese el giro");
//		}
		return context.proceed();
	}

}
