package mx.gob.saludtlax.rh.nomina.consultamovimientos;
/**
 * 
 * @author José Pablo
 *
 */
import javax.inject.Inject;
import mx.gob.saludtlax.rh.nomina.configuracionnominaempleado.EmpleadoDatosDTO;
public class ConsultaMovimientosEJB implements ConsultaMovimientos{
	@Inject
    private ConsultaMovimientosService service;

    @Override
    public EmpleadoDatosDTO obtenerEmpleadoDatos(Integer idEmpleadoSeleccionado) {
        return null; //service.obtenerEmpleadoDatos(idEmpleadoSeleccionado);
    }
    
    public MovimientosNominaEmpleadoDTO obtenerMovimientos(MovimientosNominaEmpleadoDTO movimientosNomina){
    	return service.obtenerMovimientos (movimientosNomina);
    }
}
