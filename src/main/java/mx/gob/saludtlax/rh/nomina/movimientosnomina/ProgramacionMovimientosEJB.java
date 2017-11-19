
package mx.gob.saludtlax.rh.nomina.movimientosnomina;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ProgramacionMovimientosEJB {

    @Inject
    private ProgramacionMovimientosService programacionMovimientosService;

    public void crear(ProgramarMovimientoDTO dto) {
        programacionMovimientosService.crear(dto);
    }

    public void modificar(ProgramarMovimientoDTO dto) {
        programacionMovimientosService.modificar(dto);
    }

    public void eliminar(ProgramarMovimientoDTO dto) {
        programacionMovimientosService.eliminar(dto);
    }

    public List<ProgramarMovimientoDTO> obtenerMovimientosProgramados(
            Integer idTipoMovimiento) {
        return programacionMovimientosService
                .obtenerListaRegistros(idTipoMovimiento);
    }

    public List<DetalleProgramacionMovimientoDTO> obtenerDetallesPorMovimiento(
            Integer idProgramacionMovimiento) {
        return programacionMovimientosService
                .listaDetallesPorProgramacionMovimiento(
                        idProgramacionMovimiento);
    }
}
