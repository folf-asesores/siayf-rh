/*
 *
 */

package mx.gob.saludtlax.rh.historialacademico;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import mx.gob.saludtlax.rh.excepciones.BusinessException;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 15/05/2016 15/05/2016
 */
public class HistorialAcademicoValidator {
    @AroundInvoke
    public Object validate(InvocationContext context) throws Exception {
        String contexto = "Registro historial academico: ";
        NuevoHistorialDTO dto = (NuevoHistorialDTO) context.getParameters()[0];

        if (dto == null) {
            throw new BusinessException(contexto
                    + " La informacion para registrar el historial academico es requerido.");
        }

        if (dto.getIdEmpleado() == null) {
            throw new BusinessException(contexto + "El empleado es requerido.");
        }

        if (!ValidacionUtil.esNumeroPositivo(dto.getIdEscolaridad())) {
            throw new BusinessException(contexto
                    + "La escolaridad es requerida, seleccione una opcion.");
        }

        if (ValidacionUtil.esCadenaVacia(dto.getNombreInstitucion())) {
            throw new BusinessException(contexto
                    + "El nombre de la institucion es requerida, seleccione una opcion. ");
        }

        if (!ValidacionUtil.esNumeroPositivo(dto.getIdComprobanteEstudio())) {
            throw new BusinessException(contexto
                    + "El comprobante de estudio, seleccione una opcion.");
        }

        if (dto.getIdEscolaridad() > 10) {

            if (ValidacionUtil.esCadenaVacia(dto.getDuracion())) {
                throw new BusinessException(contexto
                        + "Es requerida la duracion de la escolariad complementaria, ingrese la duracion del curso, diplomado etc..");
            }
        }

        return context.proceed();
    }
}
