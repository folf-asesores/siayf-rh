/**
 * Copyright ® 2016
 */
package mx.gob.saludtlax.rh.nomina.movimientofijo;

import java.util.Date;
import java.util.List;

import mx.gob.saludtlax.rh.nomina.movimientosnomina.ConfiguracionTipoMovimientoDTO;

/**
 * @author  Eduardo Mex
 * @email   lic.eduardo_mex@hotmail.com
 * @version 1.0
 * @since   25/05/2016 13:49:26
 */
public interface MovimientoFijoService {

	public void crear(MovimientoNominaDTO dto);
	
	public void editar(MovimientoNominaDTO dto);
	
	public List<MovimientoNominaDTO> obtenerMovimientosPorEmpleado(Integer idEmpleado);
	
	public  List<MovimientoNominaDTO> obtenerMovimientosPorEmpleado(Integer empleadoSeleccionado,
	            String movimientoSeleccionado);
	
	public List<MovimientoNominaDTO> obtenerMovimientos();
	
	public void eliminar(MovimientoNominaDTO dto);
	
	public Integer numeroQuincena(Integer tipoPeriodo, Integer ejercicioFiscal, Date fechaActual);
	
	public MovimientoNominaDTO obtenerMovimientoPorDatosArchivo(MovimientoNominaDTO archivoDto);
	
	public ConfiguracionTipoMovimientoDTO obtenerConfiguracionesPorTipoMovimiento(Integer idTipoMovimiento);

}
