/*
 *
 */

package mx.gob.saludtlax.rh.empleados.administracion;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import mx.gob.saludtlax.rh.excepciones.BusinessException;
import mx.gob.saludtlax.rh.experiencialaboral.ExperienciaLaboralDTO;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author Leila Schiaffini Ehuan
 * @since 16/05/2016 11:15:31
 *
 */
public class ExperienciaLaboralValidator {

    @AroundInvoke
    public Object validate(InvocationContext context) throws Exception {
        ExperienciaLaboralDTO dto = (ExperienciaLaboralDTO) context.getParameters()[0];
        Integer idEmpleado = (Integer) context.getParameters()[1];
        String contexto = "Registro experiencia laboral: ";
        if (dto == null) {
            throw new BusinessException(contexto + "La información de la experiencia laboral es requerida.");
        }

        if (idEmpleado == null) {
            throw new BusinessException(contexto + "El empleado es requerido, comuniquese con soporte.");
        }

        if (ValidacionUtil.esCadenaVacia(dto.getNombreEmpresa())) {
            throw new BusinessException(contexto + "El nombre de la empresa es requerido, ingrese un nombre.");
        }

        if (ValidacionUtil.esCadenaVacia(dto.getPuestoAspirante())) {
            throw new BusinessException(contexto + "El puesto que desempeñaba es requerido, ingrese un puesto.");
        }

        /*
         * if (dto.getFechaInicial() == null) {
         * throw new BusinessException(
         * contexto
         * + "La fecha que inicio labores es requerida, ingrese una fecha.");
         * }
         */

        return context.proceed();
    }
}
