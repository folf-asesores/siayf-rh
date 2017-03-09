/**
 * Copyright © 2016
 */
package mx.gob.saludtlax.rh.configuracion.tabulador;

import java.math.BigDecimal;
import java.util.List;



/**
 * @author Eduardo Mex
 * @email lic.eduardo_mex@hotmail.com
 * @version 1.0
 * @since 28/07/2016 13:10:35
 */
public interface Tabulador {

	/**
	 * Realiza un nuevo registro del tabulador
	 * 
	 * @param tabuladorDTO
	 */
	void crearTabulador(TabuladorDTO tabuladorDTO);

	/**
	 * Realiza la actualizacion del tabulador
	 * 
	 * @param tabuladorDTO
	 */
	void actualizarTabulador(TabuladorDTO tabuladorDTO);

	/***
	 * Elimina el tabulador
	 * 
	 * @param idTabulador
	 */
	void eliminarTabulador(Integer idTabulador);

	/**
	 * Obtiene todos los registros de los tabuladores de la bd
	 * 
	 * @return
	 */
	List<TabuladorDTO> listaTabulador(Integer idTipoTabulador);

	List<TabuladorDTO> obtenerListaTabulador();

	/**
	 * Obtiene Los datos de puesto por su identificador
	 * 
	 * @param idPuestoGeneral
	 * @return
	 */
	InfoTabuladorPuestoDTO obtenerInfoPuesto(Integer idPuestoGeneral);

	InfoSueldoDTO obtenerSueldoPorPuestoTipoTabulador(Integer idPuestoGeneral, Integer tipoContratacion);
	
	public BigDecimal obtenerSueldoDiarioPorIdTabulador(Integer idTabulador);
	
	public TabuladorDTO obtenerTabuladorPorPuesto(Integer idTabulador, Integer anioFiscal);

}
