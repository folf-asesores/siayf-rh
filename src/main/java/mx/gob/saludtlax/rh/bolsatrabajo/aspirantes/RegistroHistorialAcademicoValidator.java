/**
 * 
 */
package mx.gob.saludtlax.rh.bolsatrabajo.aspirantes;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import mx.gob.saludtlax.rh.excepciones.BusinessException;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author eduardo
 *
 */
public class RegistroHistorialAcademicoValidator {

	@AroundInvoke
	public Object validate(InvocationContext context) throws Exception {

		HistorialAcademicoDTO dto = (HistorialAcademicoDTO) context.getParameters()[0];
		String contexto = "Registro Historial Académico: ";

		if (dto == null) {
			throw new BusinessException(contexto + "Los datos del historial academico son requeridos");
		}

		if (dto.getAspirante() == null || dto.getAspirante() == 0) {
			throw new BusinessException(contexto + "El registro de datos generales es requerida");
		}

		if (dto.getEscolaridad() == null || dto.getEscolaridad() == 0) {
			throw new BusinessException(contexto + "La escolaridad es requerida");
		}

		if (dto.getComprobanteEstudio() == null || dto.getComprobanteEstudio() == 0) {
			throw new BusinessException(contexto + "El comprobante de estudio es requerido");
		}

		if (ValidacionUtil.esCadenaVacia(dto.getNombreInstitucion())) {
			throw new BusinessException(contexto + "El nombre de la institución es requerida");
		}

		if (dto.getFechaInicial() == null) {
			throw new BusinessException(contexto + "La fecha inicial es requerida");
		}

		// if (dto.getFechaFinal() == null) {
		// throw new BusinessException(contexto + "La fecha final es
		// requerida");
		// }

		if (dto.getFechaFinal() != null) {
			if (dto.getFechaInicial().after(dto.getFechaFinal())) {
				throw new BusinessException(contexto + "La fecha inicial no puede ser mayor a la fecha final");
			}

		}

		return context.proceed();
	}

}
