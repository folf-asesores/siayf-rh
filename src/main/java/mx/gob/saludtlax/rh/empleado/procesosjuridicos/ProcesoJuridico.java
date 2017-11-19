
package mx.gob.saludtlax.rh.empleado.procesosjuridicos;

import java.util.List;

import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;

public interface ProcesoJuridico {

    public void crearProceso(ProcesoDTO procesoDTO);

    public ProcesoDTO nuevoProceso(InfoEmpleadoDTO empleado);

    public void actualizarProceso(ProcesoDTO proceso);

    public List<ProcesoDTO> obtenerProcesoLista();

    public ProcesoDTO gestionarProceso(ProcesoDTO proceso);

    public void eliminarProceso(ProcesoDTO proceso);

}
