/**
 * 
 */
package mx.gob.saludtlax.rh.empleados.administracion;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import mx.gob.saludtlax.rh.excepciones.BusinessException;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 *
 *
 */
public class ActualizarDatosValidator {

	@AroundInvoke
	public Object validate(InvocationContext context) throws Exception {
		String contexto = "Envio información actualizacion: ";
		/*
		 * Integer idUsuario = (Integer) context.getParameters()[0];
		 * 
		 * if (idUsuario == null) { throw new BusinessException( contexto +
		 * "No se ha detectado un usuario en linea, veficar que se encuente logeado al sistema."
		 * ); }
		 */
		DatosGeneralesDTO datos = (DatosGeneralesDTO) context.getParameters()[0];

		if (datos == null) {
			throw new BusinessException(
					contexto
							+ " No se detecta información en el envío, verifique que los campos obligatorios esten completos.");
		}

		if (ValidacionUtil.esCadenaVacia(datos.getNombre())) {
			throw new BusinessException(
					contexto
							+ " El nombre del empleado es requerido, ingrese un nombre.");
		}
		if (ValidacionUtil.esCadenaVacia(datos.getApellidoMaterno())) {
			throw new BusinessException(
					contexto
							+ "El apellido materno del empleado es requerido, ingrese un apellido materno. ");
		}

		if (ValidacionUtil.esCadenaVacia(datos.getIdSexo())) {
			throw new BusinessException(
					contexto
							+ "El sexo del empleado es requerido, seleccione una opcion. ");
		}
		if (ValidacionUtil.esCadenaVacia(datos.getIdEstadoCivil())) {
			throw new BusinessException(
					contexto
							+ "El estado civil del empleado es requerido, seleccione una opcion. ");
		}
		if (datos.getFechaNacimiento() == null) {
			throw new BusinessException(
					contexto
							+ " La fecha de nacimiento del empleado es requerida, seleccione una fecha del calendario.");
		}

		if (ValidacionUtil.esFechaFutura(datos.getFechaNacimiento())) {
			throw new BusinessException(
					contexto
							+ "La fecha de nacimiento no puede ser mayor a la fecha actual.");
		}

	/*	if (ValidacionUtil.esCadenaVacia(datos.getTelefonos())) {
			throw new BusinessException(
					contexto
							+ "El telefono del empleado es requerido, ingrese un numero telefonico.");
		}
		if (ValidacionUtil.esCadenaVacia(datos.getCorreo())) {
			throw new BusinessException(
					contexto
							+ "El correo electronico es requerido para el envio de sus comprobantes de nomina, ingrese un correo valido. ");
		}

		if (ValidacionUtil.esCadenaVacia(datos.getIdTipoSangre())) {
			throw new BusinessException(
					contexto
							+ "El tipo de sangre del empleado es requerido, seleccione una opcion.");
		}
*/
		if (datos.getTienePersonasDependientes()) {
			if (datos.getParentescos().isEmpty()) {
				throw new BusinessException(
						contexto
								+ "Es requerido seleccionar el parentesco de las personas dependientes, seleccione minimo una opcion. ");
			}
			for (String parentesco : datos.getParentescos()) {
				if (parentesco.equals("CONYUGE")) {
					if (datos.getNumeroConyuges() == 0) {
						throw new BusinessException(contexto
								+ "El número de conyugues es requerido.");
					}
				}
				if (parentesco.equals("PADRES")) {
					if (datos.getNumeroConyuges() == 0) {
						throw new BusinessException(
								contexto
										+ "El número de padres es requerido, ingrese el numero de padres que dependen de usted.");
					}
				}
				if (parentesco.equals("HIJOS")) {
					if(datos.getNumeroHijos() == 0){
						throw new BusinessException(
								contexto
										+ "El número de hijos es requerido, ingrese el numero de hijos que dependen de usted.");
					}
					
				}

				if (parentesco.equals("OTROS")) {
					if (ValidacionUtil.esCadenaVacia(datos.getOtroParentesco())) {
						throw new BusinessException(
								contexto
										+ "Selecciono el parentesco otros es requerido indicar cual es el parentesco, ingrese un nombre.");
					}

					throw new BusinessException(contexto + "El número de "
							+ datos.getOtroParentesco()
							+ " es requerido, ingrese el numero de "
							+ datos.getOtroParentesco()
							+ " que dependen de usted.");
				}
			}
		}

		return context.proceed();
	}

}
