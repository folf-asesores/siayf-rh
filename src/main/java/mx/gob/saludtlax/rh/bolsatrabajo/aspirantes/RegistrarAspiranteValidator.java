/*
 *
 */

package mx.gob.saludtlax.rh.bolsatrabajo.aspirantes;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import mx.gob.saludtlax.rh.excepciones.ValidationException;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 18/03/2016-13:29:23
 */
public class RegistrarAspiranteValidator {

    @AroundInvoke
    public Object validate(InvocationContext context) throws Exception {
        AspiranteDTO dto = (AspiranteDTO) context.getParameters()[0];
        String contexto = "Validacion campos: ";

        if (dto == null) {
            throw new ValidationException(contexto
                    + "Son requeridos los datos para el registro del aspirante, dto nulo notifique a sistemas.");
        }

        if (ValidacionUtil.esCadenaVacia(dto.getNombre())) {
            throw new ValidationException(
                    contexto + "El nombre es requerido, ingrese el nombre.");
        }

        if (ValidacionUtil.esCadenaVacia(dto.getApellidoMaterno())) {
            throw new ValidationException(contexto
                    + "El apellido materno es requerido, ingrese el apellido materno.");
        }

        if (ValidacionUtil.esCadenaVacia(dto.getCurp())) {
            throw new ValidationException(
                    contexto + "La curp es requerida, ingrese la curp.");
        }
        if (ValidacionUtil.esCadenaVacia(dto.getRfc())) {
            throw new ValidationException(
                    contexto + "El rfc es requerido, ingrese el rfc");
        }

        if (dto.getFechaNacimiento() == null) {
            throw new ValidationException(contexto
                    + "La fecha de nacimiento es requerida, ingrese una fecha.");
        }

        if (ValidacionUtil.esCadenaVacia(dto.getEstadoCivil())) {
            throw new ValidationException(contexto
                    + "El estado civil es requerido, seleccione una opcion.");
        }
        if (ValidacionUtil.esCadenaVacia(dto.getSexo())) {
            throw new ValidationException(contexto
                    + "El sexo del aspirante es requerido, seleccione una opcion.");
        }
        if (ValidacionUtil.esCadenaVacia(dto.getTipoSangre())) {
            throw new ValidationException(contexto
                    + "El tipo de sangre es requerido, seleccione una opcion.");
        }

        if (dto.getEstatura() <= 0) {
            throw new ValidationException(contexto
                    + "La estatura debe ser mayor a cero, ingerese una estatura valida.");
        }

        if (dto.getPeso() <= 0) {
            throw new ValidationException(contexto
                    + "El peso debe ser mayor a cero, ingrese un peso valido.");
        }

        if (ValidacionUtil.esCadenaVacia(dto.getNacionalidad())) {
            throw new ValidationException(contexto
                    + "La nacionalidad es requerida, seleccione una opcion.");
        }
        if (ValidacionUtil.esNumeroPositivo(dto.getIdPaisNacionalidad())) {
            throw new ValidationException(contexto
                    + "El pais de la nacionalidad es requerido, seleccione una opcion.");
        }
        if (ValidacionUtil.esCadenaVacia(dto.getTelefono())) {
            throw new ValidationException(contexto
                    + "Los numeros telefonicos son requeridos, ingrese uno.");
        }

        if (ValidacionUtil.esCadenaVacia(dto.getLugarNacimiento())) {
            throw new ValidationException(contexto
                    + "El lugar de nacimiento es requerido, ingrese un lugar de nacimiento.");
        }
        if (ValidacionUtil.esCadenaVacia(dto.getViveCon())) {
            throw new ValidationException(contexto
                    + "Es requerido seleccionar con quien vive el aspirante, seleccione una opcion.");
        }
        /*
         * if (ValidacionUtil.esCadenaVacia(dto.getDependientes())) {
         * throw new ValidationException(
         * contexto
         * + "Es requerido especificar las personas que dependen del aspirante, seleccione una opcion.");
         * }
         */
        if (ValidacionUtil.esNumeroPositivo(dto.getIdPuesto())) {
            throw new ValidationException(contexto
                    + "Es requerido especificar el puesto al que se aspira, seleccione una opcion.");
        }

        if (ValidacionUtil.esNumeroPositivo(dto.getIdDepartamento())) {
            throw new ValidationException(contexto
                    + "Es requerido especificar el departamento al que se aspira, seleccione una opcion.");
        }
        if (dto.isTieneLicencia()) {
            if (ValidacionUtil.esCadenaVacia(dto.getTipoLicencia())) {
                throw new ValidationException(contexto
                        + "El tipo de licencia es requerido, seleccione una opcion.");
            }
            if (ValidacionUtil.esCadenaVacia(dto.getNumeroLicencia())) {
                throw new ValidationException(contexto
                        + "El numero de licencia es requerido, ingrese un numero.");
            }
        }

        if (ValidacionUtil
                .esNumeroPositivo(dto.getDireccionDTO().getIdMunicipio())) {
            throw new ValidationException(contexto
                    + "El municipio es requerido, seleccione una opcion.");
        }

        if (ValidacionUtil
                .esNumeroPositivo(dto.getDireccionDTO().getIdAsentamiento())) {
            throw new ValidationException(contexto
                    + "La poblacion es requerida, seleccione una opcion");
        }
        //		if (ValidacionUtil.esNumeroPositivo(dto.getDireccionDTO()
        //				.getIdColonia())) {
        //			throw new ValidationException(contexto
        //					+ "La colonia es requerida, seleccione una opcion.");
        //		}
        if (ValidacionUtil.esCadenaVacia(dto.getDireccionDTO().getCalle())) {
            throw new ValidationException(contexto
                    + "La calle es requerida, ingerese el nombre de la calle.");
        }
        if (ValidacionUtil
                .esCadenaVacia(dto.getDireccionDTO().getNumeroInterior())) {
            throw new ValidationException(contexto
                    + "El numero interior es requerido, ingrese el numero interior.");
        }

        if (ValidacionUtil
                .esNumeroPositivo(dto.getDireccionDTO().getCodigoPostal())) {
            throw new ValidationException(contexto
                    + "El codigo postal es requerido, ingrese un codigo.");
        }
        return context.proceed();
    }

}
