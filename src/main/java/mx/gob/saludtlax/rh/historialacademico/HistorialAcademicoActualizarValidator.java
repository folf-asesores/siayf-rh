/*
 *
 */

package mx.gob.saludtlax.rh.historialacademico;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author Eduardo Mex

 * @version 1.0
 * @since 17:55:00 23/09/2016
 */
public class HistorialAcademicoActualizarValidator {

    @AroundInvoke
    public Object validate(InvocationContext context) throws Exception {
        String contexto = "Actualización historial academico: ";
        HistorialAcademicoDTO dto = (HistorialAcademicoDTO) context.getParameters()[0];

        if (dto == null) {
            throw new ValidacionException(contexto + " La informacion para registrar el historial academico es requerido.",
                    ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (dto.getIdEmpleado() == null) {
            throw new ValidacionException(contexto + "El empleado es requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (!ValidacionUtil.esNumeroPositivo(dto.getEscolaridad())) {
            throw new ValidacionException(contexto + "La escolaridad es requerida, seleccione una opción.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (ValidacionUtil.esCadenaVacia(dto.getNombreInstitucion())) {
            throw new ValidacionException(contexto + "El nombre de la institucion es requerida, seleccione una opción.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (!ValidacionUtil.esNumeroPositivo(dto.getDocumentoComprobatorio())) {
            throw new ValidacionException(contexto + "El comprobante de estudio, seleccione una opción.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (dto.getEscolaridad() > 10) {

            if (ValidacionUtil.esCadenaVacia(dto.getDuracion())) {
                throw new ValidacionException(
                        contexto + "Es requerida la duracion de la escolariad complementaria, ingrese la duracion del curso, diplomado etc..",
                        ValidacionCodigoError.VALOR_REQUERIDO);
            }
        }

        return context.proceed();
    }

}
