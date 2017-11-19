
package mx.gob.saludtlax.rh.nomina.movimientos;

import java.util.List;

import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;
import mx.gob.saludtlax.rh.nomina.movimientofijo.MovimientoNominaDTO;
import mx.gob.saludtlax.rh.nomina.movimientosnomina.TipoMovimientoNominaDTO;

public interface Movimientos {
    List<TipoMovimientoDTO> getMovimientosLista();

    String getUrlFormPorClave(Integer idFormaRegistro);

    List<MovimientoNominaDTO> obtenerMovimientosPorEmpleado(
            InfoEmpleadoDTO empleadoSeleccionado,
            String movimientoSeleccionado);

    void eliminar(MovimientoNominaDTO movdto);

    void editar(MovimientoNominaDTO dto);

    TipoMovimientoNominaDTO obtenerTipoMovimiento(String clave);

}