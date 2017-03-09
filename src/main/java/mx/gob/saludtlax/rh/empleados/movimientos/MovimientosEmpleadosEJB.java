package mx.gob.saludtlax.rh.empleados.movimientos;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.empleados.movimientos.reportes.ComisionadoLicenciaExcelDTO;

@Stateless
public class MovimientosEmpleadosEJB implements MovimientosEmpleados {
	
	@Inject
	private ConsultaMovimientoService consultaMovimientoService;
	@Inject
	private MovimientosEmpleadosService movimientosEmpleadosService;

	@Override
	public void crearMovimientoEmpleado(
			RegistroMovimientoDTO registroMovimientoDTO) {
		movimientosEmpleadosService.crearMovimiento(registroMovimientoDTO);

	}

	@Override
	public List<InfoMovimientoDTO> consultarMovimientos(
			FiltroConsultaDTO filtroConsultaDTO) {

		return consultaMovimientoService
				.consultarMovimientos(filtroConsultaDTO);
	}

	@Override
	public DetalleMovimientoDTO obtenerDetalleMovimiento(Integer idMovimiento) {

		return consultaMovimientoService.obtenerDetalleMovimiento(idMovimiento);
	}

	@Override
	public Integer obtenerPadreMovimiento(Integer idMovimiento) {
	
		return movimientosEmpleadosService.obtenerPadreMovimiento(idMovimiento);
	}

	@Override
	public void validarMovimiento(Integer idPuesto, Integer tipoMovimiento) {
		//movimientosEmpleadosService.validarMovimiento(idPuesto, tipoMovimiento);
		
	}

	@Override
	public List<ComisionadoLicenciaExcelDTO> listaConsultaComisionadoLicenciaPorRangoFecha(Date fechaInicial,
			Date fechaFinal) {
		
		return consultaMovimientoService.listaConsultaComisionadoLicenciaPorRangoFecha(fechaInicial, fechaFinal);
	}

}