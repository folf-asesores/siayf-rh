/*
 *
 */

package mx.gob.saludtlax.rh.bolsatrabajo.aspirantes;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import mx.gob.saludtlax.rh.excepciones.BusinessException;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 * @version 21/03/2016 16:31:49
 * 
 */
public class RegistroExperienciaLaboralValidator {

    @AroundInvoke
    public Object validate(InvocationContext context) throws Exception {

        ExperienciaLaboralAspiranteDTO dto = (ExperienciaLaboralAspiranteDTO) context
                .getParameters()[0];
        String contexto = "Registro Experiencia Laboral: ";

        if (dto == null) {
            throw new BusinessException(contexto
                    + "Los datos de la experiencia laboral del aspirante son requeridos");
        }

        if (dto.getIdAspirante() == null || dto.getIdAspirante() == 0) {
            throw new BusinessException(contexto
                    + "Es necesario registrar los datos personales primero");
        }

        if (ValidacionUtil.esCadenaVacia(dto.getNombreEmpresa())) {
            throw new BusinessException(
                    contexto + "El nombre de la empresa es requerida");
        }

        //		if (ValidacionUtil.esCadenaVacia(dto.getTelefono())) {
        //			throw new BusinessException(contexto + "El telefono de la empresa es requerido");
        //		}

        if (ValidacionUtil.esCadenaVacia(dto.getPuesto())) {
            throw new BusinessException(
                    contexto + "El puesto que desempeño es requerido");
        }

        //		if (dto.getSueldoMensual() == null || dto.getSueldoMensual().compareTo(BigDecimal.ZERO) == 0) {
        //			throw new BusinessException(contexto + "El sueldo mensual es requerido");
        //		}

        if (dto.getFechaInicial() == null) {
            throw new BusinessException(
                    contexto + "La fecha inicial de labor es requerido");
        }

        if (dto.getFechaFinal() == null) {
            throw new BusinessException(
                    contexto + "La fecha final de labor es requerido");
        }

        if (dto.getFechaInicial().after(dto.getFechaFinal())) {
            throw new BusinessException(contexto
                    + "La fecha inicial no puede ser mayor a la fecha Final");
        }

        //		if (ValidacionUtil.esCadenaVacia(dto.getCorreoContacto())) {
        //			throw new BusinessException(contexto + "El correo de la empresa es requerido");
        //		}

        //		if (ValidacionUtil.esCadenaVacia(dto.getDireccionEmpresa())) {
        //			throw new BusinessException(contexto + "La dirección de la empresa es requerido");
        //		}

        //		if (ValidacionUtil.esCadenaVacia(dto.getMotivoSeparacion())) {
        //			throw new BusinessException(contexto + "El motivo de separación es requerido");
        //		}

        //		if (ValidacionUtil.esCadenaVacia(dto.getNombreJefe())) {
        //			throw new BusinessException(contexto + "El nombre del jefe directo es requerido");
        //		}

        //		if (ValidacionUtil.esCadenaVacia(dto.getPuestoJefe())) {
        //			throw new BusinessException(contexto + "El puesto del jefe directo es requerido");
        //		}

        return context.proceed();

    }

}
