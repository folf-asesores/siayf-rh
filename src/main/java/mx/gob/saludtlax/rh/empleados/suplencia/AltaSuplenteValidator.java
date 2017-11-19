/*
 *
 */

package mx.gob.saludtlax.rh.empleados.suplencia;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.DireccionDTO;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.util.ValidacionUtil;
import mx.gob.saludtlax.rh.vacantes.seleccion.EnumTipoCandidato;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 12/01/2017 13:30:05
 */
public class AltaSuplenteValidator {
    @AroundInvoke
    public Object validate(InvocationContext context) throws Exception {

        RegistroSuplenteDTO dto = (RegistroSuplenteDTO) context.getParameters()[0];
        if (dto == null) {
            throw new ValidacionException("Los datos del registro del suplente son requeridos.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (!ValidacionUtil.esNumeroPositivo(dto.getIdUsuario())) {
            throw new ValidacionException("El usuario es requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (!ValidacionUtil.esNumeroPositivo(dto.getIdTipoCandidato())) {
            throw new ValidacionException("Es requerido seleccionar el tipo de suplente que desea registrar.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (dto.getIdTipoCandidato().equals(EnumTipoCandidato.EMPLEADO)) {
            if (!ValidacionUtil.esNumeroPositivo(dto.getIdEmpleado())) {
                throw new ValidacionException("El empleado es requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
            }
        } else if (dto.getIdTipoCandidato().equals(EnumTipoCandidato.ASPIRANTE)) {
            AltaSuplenteDTO suplente = dto.getSuplente();
            if (suplente == null) {
                throw new ValidacionException("La información del suplente es requerida", ValidacionCodigoError.VALOR_REQUERIDO);
            }

            if (ValidacionUtil.esCadenaVacia(suplente.getRfc())) {
                throw new ValidacionException("El rfc es requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
            }

            if (ValidacionUtil.esCadenaVacia(suplente.getCurp())) {
                throw new ValidacionException("La curp del suplente es requerida.", ValidacionCodigoError.VALOR_REQUERIDO);
            }

            if (ValidacionUtil.esCadenaVacia(suplente.getNombre())) {
                throw new ValidacionException("El nombre del suplente es requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
            }

            if (ValidacionUtil.esCadenaVacia(suplente.getApellidoMaterno())) {
                throw new ValidacionException("El apellido materno es requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
            }

            if (suplente.getFechaNacimiento() == null) {
                throw new ValidacionException("La fecha de nacimiento es incorrecta.", ValidacionCodigoError.VALOR_REQUERIDO);
            }

            if (ValidacionUtil.esCadenaVacia(suplente.getSexo())) {
                throw new ValidacionException("El sexo del suplente es requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
            }

            if (!ValidacionUtil.esNumeroPositivo(suplente.getNumeroPersonal())) {
                throw new ValidacionException("El número personal del suplente es requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
            }

            DireccionDTO direccion = dto.getSuplente().getDireccion();
            if (direccion == null) {
                throw new ValidacionException("Los datos de la dirección son requeridos.", ValidacionCodigoError.VALOR_REQUERIDO);
            }

            if (ValidacionUtil.esCadenaVacia(direccion.getCalle())) {
                throw new ValidacionException("La calle es requerida.", ValidacionCodigoError.VALOR_REQUERIDO);
            }

            if (!ValidacionUtil.esNumeroPositivo(direccion.getCodigoPostal())) {
                throw new ValidacionException("El código postal es requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
            }

            if (ValidacionUtil.esCadenaVacia(direccion.getNumeroExterior())) {
                throw new ValidacionException("El número exterior es requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
            }

            if (!ValidacionUtil.esNumeroPositivo(direccion.getIdEstado())) {
                throw new ValidacionException("El estado es requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
            }

            if (!ValidacionUtil.esNumeroPositivo(direccion.getIdMunicipio())) {
                throw new ValidacionException("El municipio es requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
            }

            if (!ValidacionUtil.esNumeroPositivo(direccion.getIdAsentamiento())) {
                throw new ValidacionException("El asentamiento es requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
            }

        } else {
            throw new ValidacionException("El tipo de candidato es incorrecto.", ValidacionCodigoError.VALOR_NO_PERMITIDO);
        }

        if (!ValidacionUtil.esNumeroPositivo(dto.getNumeroLaboral())) {
            throw new ValidacionException("El número laboral es requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (!ValidacionUtil.esNumeroPositivo(dto.getIdBanco())) {
            throw new ValidacionException("El banco es requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (ValidacionUtil.esCadenaVacia(dto.getNumeroCuenta())) {
            throw new ValidacionException("El número de cuenta es requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (!ValidacionUtil.tieneCadenaFormatoNumero(dto.getNumeroCuenta())) {
            throw new ValidacionException("El número de cuenta solo admite números.", ValidacionCodigoError.FORMATO_INVALIDO);
        }

        if (!ValidacionUtil.esNumeroPositivo(dto.getIdProyecto())) {
            throw new ValidacionException("El proyecto es requerido", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (!ValidacionUtil.esNumeroPositivo(dto.getIdDependencia())) {
            throw new ValidacionException("La dependencia es requerida.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (!ValidacionUtil.esNumeroPositivo(dto.getIdUnidadResponsable())) {
            throw new ValidacionException("La unidad responsable es requerida.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (!ValidacionUtil.esNumeroPositivo(dto.getIdCentroResponsabilidad())) {
            throw new ValidacionException("El centro de responsabilidad es requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (!ValidacionUtil.esNumeroPositivo(dto.getIdPuesto())) {
            throw new ValidacionException("El puesto es requerido", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (!ValidacionUtil.esNumeroPositivo(dto.getIdMetodoPago())) {
            throw new ValidacionException("El método de pago es requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        return context.proceed();
    }
}
