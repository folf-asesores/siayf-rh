
package mx.gob.saludtlax.rh.puestosautorizados;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import mx.gob.saludtlax.rh.empleados.datolaboral.DatoLaboralDTO;
import mx.gob.saludtlax.rh.empleados.datolaboral.SolicitudNuevoPuestoDTO;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

public class SolicitudAperturaValidator {
    @AroundInvoke
    public Object validate(InvocationContext context) throws Exception {

        SolicitudNuevoPuestoDTO solicitud = (SolicitudNuevoPuestoDTO) context.getParameters()[0];

        DatoLaboralDTO dto = solicitud.getDatosLaborales();

        if (dto == null) {
            throw new ValidacionException("Los datos laborales son requeridos.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (!ValidacionUtil.esNumeroPositivo(dto.getTipoContratacion())) {
            throw new ValidacionException("Seleccione un tipo de contratación.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (!ValidacionUtil.esNumeroPositivo(dto.getIdProyecto())) {
            throw new ValidacionException("Seleccione un proyecto.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (!ValidacionUtil.esNumeroPositivo(dto.getIdDependencia())) {
            throw new ValidacionException("Seleccione una dependencia.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (!ValidacionUtil.esNumeroPositivo(dto.getIdUnidadResponsable())) {
            throw new ValidacionException("Seleccione una unidad responsable.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (!ValidacionUtil.esNumeroPositivo(dto.getIdFuenteFinanciamiento())) {
            throw new ValidacionException("Seleccione una fuente de financiamiento.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (!ValidacionUtil.esNumeroPositivo(dto.getIdSubfuenteFinanciamiento())) {
            throw new ValidacionException("Seleccione una subfuente de financiamiento.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (!ValidacionUtil.esNumeroPositivo(dto.getIdPuesto())) {
            throw new ValidacionException("Seleccione un puesto para obtener el tabulador correspondiente.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (!ValidacionUtil.esNumeroPositivo(dto.getIdTabulador())) {
            throw new ValidacionException("El puesto no tiene asignado un tabulador, es requerido configurar uno.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        return context.proceed();
    }
}
