/**
 * 
 */
package mx.gob.saludtlax.rh.empleados.movimientos;

import java.util.Date;
import java.util.List;

import mx.gob.saludtlax.rh.empleados.movimientos.reportes.ComisionadoLicenciaExcelDTO;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 24/05/2016 00:03:44
 */
public interface MovimientosEmpleados {

	/**
	 * Crea un nuevo movimiento.
	 * 
	 * @param registroMovimientoDTO
	 */
	public void crearMovimientoEmpleado(RegistroMovimientoDTO registroMovimientoDTO);

	/**
	 * Consulta los movimientos de empleados.
	 * 
	 * @param filtroConsultaDTO
	 */
	public List<InfoMovimientoDTO> consultarMovimientos(FiltroConsultaDTO filtroConsultaDTO);

	/**
	 * Obtiene el detalle del movimiento del empleado.
	 * 
	 * @param idMovimiento
	 */
	public DetalleMovimientoDTO obtenerDetalleMovimiento(Integer idMovimiento);

	/**
	 * Obtiene el movimiento padre del movimiento.
	 * 
	 * @param idMovimiento
	 */
	public Integer obtenerPadreMovimiento(Integer idMovimiento);

	/**
	 * Valida que el empleado pueda realizar el movimiento seleccionado
	 * 
	 * @param idPuesto
	 *            id puesto que ocupa el empleado.
	 * @param tipoMovimiento
	 *            tipo de movimiento al empleado.
	 */
	public void validarMovimiento(Integer idPuesto, Integer tipoMovimiento);

	/**
	 * Consulta la lista de movimeintos por rango de fechas
	 * 
	 * @param fechaInicial
	 * @param fechaFinal
	 * @return
	 */
	public List<ComisionadoLicenciaExcelDTO> listaConsultaComisionadoLicenciaPorRangoFecha(Date fechaInicial,
			Date fechaFinal);

}
