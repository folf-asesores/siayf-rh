/*
 *
 */

package mx.gob.saludtlax.rh.empleados.datolaboral;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.puestosautorizados.EnumTipoContratacion;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 07/12/2016 03:08:36
 */
public class ModificacionLaboralValidator {

    @AroundInvoke
    public Object validate(InvocationContext context) throws Exception {
        DatoLaboralDTO dto = (DatoLaboralDTO) context.getParameters()[0];

        if (dto == null) {
            throw new ValidacionException("La información de la edición es requerida.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (!ValidacionUtil.esNumeroPositivo(dto.getIdProyecto())) {
            throw new ValidacionException("El proyecto es requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (!ValidacionUtil.esNumeroPositivo(dto.getIdDependencia())) {
            throw new ValidacionException("La dependencia es requerida.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (!ValidacionUtil.esNumeroPositivo(dto.getIdUnidadResponsable())) {
            throw new ValidacionException("La unidad responsable es requerida.", ValidacionCodigoError.VALOR_REQUERIDO);
        }
        if (!ValidacionUtil.esNumeroPositivo(dto.getIdFuenteFinanciamiento())) {
            throw new ValidacionException("La fuente de financiamiento es requerida.", ValidacionCodigoError.VALOR_REQUERIDO);
        }
        if (!ValidacionUtil.esNumeroPositivo(dto.getIdSubfuenteFinanciamiento())) {
            throw new ValidacionException("La subfuente de financiamiento es requerida.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (!ValidacionUtil.esNumeroPositivo(dto.getIdTipoRecurso())) {
            throw new ValidacionException("El tipo de recurso es requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (!ValidacionUtil.esNumeroPositivo(dto.getIdPuesto())) {
            throw new ValidacionException("El puesto es requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (dto.getTipoContratacion() != EnumTipoContratacion.CONTRATO_FEDERAL && dto.getTipoContratacion() != EnumTipoContratacion.CONTRATO_ESTATAL) {
            if (!ValidacionUtil.esNumeroPositivo(dto.getIdTabulador())) {
                throw new ValidacionException("El tabulador es requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
            }
        }

        if (dto.getTipoContratacion() == EnumTipoContratacion.CONTRATO_ESTATAL) {
            if (!ValidacionUtil.esNumeroPositivo(dto.getSueldo())) {
                throw new ValidacionException("El sueldo debe ser un número mayor a cero.", ValidacionCodigoError.VALOR_REQUERIDO);
            }
            if (!ValidacionUtil.esNumeroPositivo(dto.getSueldo01())) {
                throw new ValidacionException("El sueldo 01 debe ser un número mayor a cero.", ValidacionCodigoError.VALOR_REQUERIDO);
            }

        }

        if (dto.getFechaInicioLabores() == null) {
            throw new ValidacionException("La fecha de inicio de labores es requerida.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        return context.proceed();
    }

}
