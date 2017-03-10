/**
 * 
 */
package mx.gob.saludtlax.rh.empleados.suplencia;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 09/02/2017 22:41:08
 */
public class ConsultaMovimientoValidator {

	@AroundInvoke
	public Object validate(InvocationContext context) throws Exception {
		
		FiltroMovimientoSuplenteDTO dto = (FiltroMovimientoSuplenteDTO) context.getParameters()[0];

							

		return context.proceed();
	}
}
