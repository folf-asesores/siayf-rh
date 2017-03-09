/**
 * Copyright © 2016
 */
package mx.gob.saludtlax.rh.bolsatrabajo.aspirantes;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import mx.gob.saludtlax.rh.excepciones.BusinessException;
import mx.gob.saludtlax.rh.excepciones.ValidationException;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author Eduardo Mex
 * @version 29/03/2016 12:53:35
 * @email Lic.Eduardo_Mex@hotmail.com
 */
public class RegistroDatoPersonalValidator {

	@AroundInvoke
	public Object validate(InvocationContext context) throws Exception {
		// valida
		AspiranteDTO dto = (AspiranteDTO) context.getParameters()[0];
		String contexto = "Registro Datos Generales: ";
		if (dto == null) {
			throw new BusinessException(contexto + "Los datos personales son requeridos");
		}

		if (ValidacionUtil.esCadenaVacia(dto.getNombre())) {
			
			System.out.println(dto.getNombre());
			
			throw new BusinessException(contexto + "El nombre es requerido, Ingrese el nombre");
		}

		if (ValidacionUtil.esCadenaVacia(dto.getApellidoPaterno())) {
			throw new BusinessException(contexto + "El apellido paterno es requerido, Ingrese el apellido paterno");
		}

		if (ValidacionUtil.esCadenaVacia(dto.getApellidoMaterno())) {
			throw new BusinessException(contexto + "El apellido materno es requerido, ingrese el apellido materno.");
		}

		if (ValidacionUtil.esCadenaVacia(dto.getRfc())) {
			throw new BusinessException(contexto + "El RFC es requerido, Ingrese el RFC");
		}

		if (ValidacionUtil.esCadenaVacia(dto.getCurp())) {
			throw new BusinessException(contexto + "La CURP es requerido, Ingrese la CURP");
		}

		if (dto.getFechaNacimiento() == null) {
			throw new BusinessException(
					contexto + "La fecha de nacimiento es requerido, Seleccione la fecha de nacimiento");
		}

		// if (ValidacionUtil.esCadenaVacia(dto.getTelefono())) {
		// throw new BusinessException(contexto + "El teléfono es requerido,
		// Ingrese el telefono");
		// }

//		if (ValidacionUtil.esCadenaVacia(dto.getTipoSangre())) {
//			throw new BusinessException(contexto + "El tipo de sangre es requerido, Seleccione el tipo de sangre");
//		}

		if (ValidacionUtil.esCadenaVacia(dto.getEstadoCivil())) {
			throw new BusinessException(contexto + "El estado civil es requerido, Seleccione el estado civil");
		}

		// if (dto.getIdPuesto() == null || dto.getIdPuesto() == 0) {
		// throw new BusinessException(contexto + "El puesto es requerido,
		// Seleccione el puesto");
		// }

		if (ValidacionUtil.esCadenaVacia(dto.getSexo())) {
			throw new BusinessException(contexto + "El sexo es requerido, Seleccione el sexo");
		}

		if (ValidacionUtil.esCadenaVacia(dto.getLugarNacimiento())) {
			throw new BusinessException(
					contexto + "El lugar de nacimiento es requerido, Ingrese el lugar de nacimiento");
		}

		if (ValidacionUtil.esCadenaVacia(dto.getDireccionDTO().getNumeroExterior())) {
			throw new BusinessException(contexto + "El número exterior es requerido, Ingrese el número exterior");
		}
/*
		if (dto.getEstatura() <= 0) {
			throw new BusinessException(contexto + "La estatura es requerido, Ingrese la estatura del aspirante");
		}

		if (dto.getPeso() <= 0) {
			throw new BusinessException(contexto + "El peso es requerido, Ingrese el peso del aspirante");
		}
*/
		// if (ValidacionUtil.esCadenaVacia(dto.getViveCon())) {
		// throw new BusinessException(
		// contexto + "Es requerido seleccionar con quien vive el aspirante,
		// Seleccione una opción.");
		// }
		if (ValidacionUtil.esCadenaVacia(dto.getDireccionDTO().getCalle())) {
			throw new ValidationException(contexto + "La calle es requerida, Ingerese el nombre de la calle.");
		}

		// if (ValidacionUtil.esCadenaVacia(dto.getCorreoElectronico())) {
		// throw new BusinessException(
		// contexto + "El correo del aspirante es requerido, Ingrese el correo
		// del aspirante");
		// }

		if (dto.getDireccionDTO().getIdMunicipio() == null || dto.getDireccionDTO().getIdMunicipio() == 0) {
			throw new BusinessException(contexto + "El municipio es requerido, Seleccione un municipio");
		}

		if (dto.getDireccionDTO().getIdAsentamiento() == null || dto.getDireccionDTO().getIdAsentamiento() == 0) {
			throw new BusinessException(contexto + "El asentamiento es requerido, Seleccione el asentamiento");
		}

		return context.proceed();
	}

}
