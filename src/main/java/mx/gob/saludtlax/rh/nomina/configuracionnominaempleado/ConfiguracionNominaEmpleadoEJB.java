
package mx.gob.saludtlax.rh.nomina.configuracionnominaempleado;

import javax.inject.Inject;

public class ConfiguracionNominaEmpleadoEJB implements ConfiguracionNominaEmpleado {
    @Inject
    private ConfiguracionNominaEmpleadoService service;

    @Override
    public EmpleadoDatosDTO obtenerEmpleadoDatos(Integer idEmpleadoSeleccionado) {
        return null; //service.obtenerEmpleadoDatos(idEmpleadoSeleccionado);
    }

}