/*
 * Copyright ® 2016
 */

package mx.gob.saludtlax.rh.nomina.movimientos;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;
import mx.gob.saludtlax.rh.nomina.movimientofijo.MovimientoNominaDTO;
import mx.gob.saludtlax.rh.nomina.movimientosnomina.TipoMovimientoNominaDTO;
import mx.gob.saludtlax.rh.persistencia.TiposMovimientosNominaRepository;

@Stateless
public class MovimientosEJB implements Movimientos {

    @Inject
    private MovimientosService service;

    @Inject
    private TiposMovimientosNominaRepository tipoMovimientoRepository;

    @Override
    public List<TipoMovimientoDTO> getMovimientosLista() {
        List<TipoMovimientoDTO> movimientosLista = new ArrayList<>();
        TipoMovimientoDTO movimiento = new TipoMovimientoDTO();
        //        movimiento.setIdMovimientoNomina(-1);
        //        movimiento.setDescripcion("Consulta de Movimientos de Empleado");
        //        movimiento.setFormaRegistro(FormasRegistroMovimientos.CONSULTA_MOVIMIENTOS.getId());
        //        movimiento.setEsMovimiento(false);
        //        movimientosLista.add(movimiento);

        movimiento = new TipoMovimientoDTO();
        movimiento.setIdMovimientoNomina(0);
        movimiento.setClave("tr");
        movimiento.setDescripcion("Terceros");
        movimiento.setFormaRegistro(FormasRegistroMovimientos.MOVIMIENTOS_FIJOS.getId());
        movimiento.setEsMovimiento(true);
        movimientosLista.add(movimiento);
        movimientosLista.addAll(service.getMovimientosLista());

        return movimientosLista;
    }

    @Override
    public String getUrlFormPorClave(Integer idFormaRegistro) {
        //		List<TipoMovimientoDTO> movimientosLista = getMovimientosLista();
        //        Map<String,TipoMovimientoDTO> map = new HashMap<String,TipoMovimientoDTO>();
        //        for (TipoMovimientoDTO i : movimientosLista) map.put(i.getClave(),i);
        //        TipoMovimientoDTO movimiento = map.get(claveMovimiento);

        return FormasRegistroMovimientos.getPorId(idFormaRegistro);
    }

    @Override
    public List<MovimientoNominaDTO> obtenerMovimientosPorEmpleado(InfoEmpleadoDTO empleadoSeleccionado, String movimientoSeleccionado) {

        if (movimientoSeleccionado.contentEquals("tr")) {
            return service.obtenerMovimientosTercerosPorEmpleado(empleadoSeleccionado);
        }
        TipoMovimientoNominaDTO movDto = tipoMovimientoRepository.obtenerMovimientoPorClave(movimientoSeleccionado);
        return service.obtenerMovimientosPorEmpleado(empleadoSeleccionado, movDto);
    }

    @Override
    public void eliminar(MovimientoNominaDTO movDto) {
        service.eliminar(movDto);
    }

    @Override
    public void editar(MovimientoNominaDTO dto) {
        service.editar(dto);
    }

    @Override
    public TipoMovimientoNominaDTO obtenerTipoMovimiento(String clave) {
        TipoMovimientoNominaDTO dto = new TipoMovimientoNominaDTO();
        dto = tipoMovimientoRepository.obtenerMovimientoPorClave(clave);
        return dto;
    }

}