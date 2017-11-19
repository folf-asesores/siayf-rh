
package mx.gob.saludtlax.rh.empleados.administracion;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import mx.gob.saludtlax.rh.excepciones.BusinessException;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

public class ActualizarDomicilioValidator {

    @AroundInvoke
    public Object validate(InvocationContext context) throws Exception {
        Integer idEmpleado = (Integer) context.getParameters()[0];
        //	Integer idUsuario = (Integer) context.getParameters()[1];
        DomicilioDTO domicilio = (DomicilioDTO) context.getParameters()[2];
        String contexto = "Actualizacion domicilio: ";

        if (idEmpleado == null) {
            throw new BusinessException(contexto
                    + " El empleado es requerido, comuniquese con soporte tecnico.");
        }
        /*
         * if (idUsuario == null) {
         * throw new BusinessException(
         * contexto
         * + " El usuario es requerido, comuniquese con soporte tecnico.");
         * }
         */

        if (domicilio == null) {
            throw new BusinessException(contexto
                    + "Los datos del domicilio son requeridos, comuniquese con soporte tecnico.");
        }

        if (!ValidacionUtil.esNumeroPositivo(domicilio.getIdMunicipio())) {
            throw new BusinessException(contexto
                    + "El municipio del empleado es requerido, seleccione una opcion");
        }

        if (!ValidacionUtil.esNumeroPositivo(domicilio.getIdAsentamiento())) {
            throw new BusinessException(contexto
                    + "El asentamiento es requerido, seleccione una opción.");
        }

        if (ValidacionUtil.esCadenaVacia(domicilio.getCalle())) {
            throw new BusinessException(contexto
                    + "La calle del empleado es requerida, ingrese una calle.");
        }

        if (ValidacionUtil.esCadenaVacia(domicilio.getNumeroExterior())) {
            throw new BusinessException(contexto
                    + "El numero exterior es requerido, ingrese un numero exterior.");
        }
        /*
         * if (domicilio.getCodigoPostal() == 0) {
         * throw new BusinessException(
         * contexto
         * + "El codigo postal es requerido, ingrese un codigo postal.");
         * }
         */
        return context.proceed();
    }

}
