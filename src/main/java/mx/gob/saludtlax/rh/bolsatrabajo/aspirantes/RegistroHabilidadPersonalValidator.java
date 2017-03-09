/**
 * Copyright © 2016
 */
package mx.gob.saludtlax.rh.bolsatrabajo.aspirantes;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import mx.gob.saludtlax.rh.excepciones.BusinessException;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author Eduardo Mex
 * @version 23/03/2016 12:11:45
 * @email Lic.Eduardo_Mex@hotmail.com
 */
public class RegistroHabilidadPersonalValidator {

	@AroundInvoke
	public Object validate(InvocationContext context) throws Exception {

		HabilidadesPersonalesAspiranteDTO dto = (HabilidadesPersonalesAspiranteDTO) context.getParameters()[0];
		String contexto = "Registro Habilidades Personales: ";

		if (dto == null) {
			throw new BusinessException(contexto + "Los datos de la encuesta personal del aspirante son requeridos");
		}

		if (ValidacionUtil.esCadenaVacia(dto.getIdioma())) {
			throw new BusinessException(contexto + "El idioma o los idiomas que habla son requeridos");
		}

//		if (dto.getNivelIdioma() == null || dto.getNivelIdioma() == 0) {
//			throw new BusinessException(contexto + "El nivel de idioma es requerido");
//		}

		if (ValidacionUtil.esCadenaVacia(dto.getMaquinaTallerDomina())) {
			throw new BusinessException(contexto + "El equipo de oficina o el taller que domine es requerido");
		}

		if (ValidacionUtil.esCadenaVacia(dto.getFuncionesOficinaDomina())) {
			throw new BusinessException(contexto + "La funcion o funciones de oficna es requerido");
		}

		if (ValidacionUtil.esCadenaVacia(dto.getMaquinaTallerDomina())) {
			throw new BusinessException(contexto + "El equipo de oficina o el taller que domine es requerido");
		}

		if (ValidacionUtil.esCadenaVacia(dto.getSoftwareDomina())) {
			throw new BusinessException(contexto + "El nombre del software o software's que domine es requerido");
		}
		if (dto.getIdAspirante() == null || dto.getIdAspirante() == 0) {
			throw new BusinessException(contexto + "Es necesario registrar los datos personales primero");
		}
		return context.proceed();
	}
}
