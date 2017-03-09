package mx.gob.saludtlax.rh.nomina.consultamovimientos;
import mx.gob.saludtlax.rh.nomina.configuracionnominaempleado.EmpleadoDatosDTO;
/**
 * 
 * @author José Pablo
 *
 */
public interface ConsultaMovimientos {
	 EmpleadoDatosDTO obtenerEmpleadoDatos(Integer idEmpleadoSeleccionado);
}
