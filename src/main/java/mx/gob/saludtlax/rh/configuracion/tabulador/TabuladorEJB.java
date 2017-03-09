/**

 * Copyright © 2016
 */
package mx.gob.saludtlax.rh.configuracion.tabulador;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;



/**
 * @author Eduardo Mex
 * @email lic.eduardo_mex@hotmail.com
 * @version 1.0
 * @since 28/07/2016 13:10:51
 */
@Stateless
public class TabuladorEJB implements Tabulador, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4267420572310453194L;

	@Inject
	private TabuladorService tabuladorService;

	@Override
	public void crearTabulador(TabuladorDTO tabuladorDTO) {
		tabuladorService.crearTabulador(tabuladorDTO);
	}

	@Override
	public void actualizarTabulador(TabuladorDTO tabuladorDTO) {
		tabuladorService.actualizarTabulador(tabuladorDTO);
	}

	@Override
	public void eliminarTabulador(Integer idTabulador) {
		tabuladorService.eliminarTabulador(idTabulador);
	}

	@Override
	public List<TabuladorDTO> listaTabulador(Integer idTipoTabulador) {
		return tabuladorService.listaTabulador(idTipoTabulador);
	}

	@Override
	public InfoTabuladorPuestoDTO obtenerInfoPuesto(Integer idPuestoGeneral) {
		return tabuladorService.obtenerInfoPuesto(idPuestoGeneral);
	}

	@Override
	public InfoSueldoDTO obtenerSueldoPorPuestoTipoTabulador(Integer idPuestoGeneral, Integer tipoContratacion) {

		return tabuladorService.obtenerSueldoPorPuestoTabulador(idPuestoGeneral, tipoContratacion);
	}

	@Override
	public List<TabuladorDTO> obtenerListaTabulador() {

		return tabuladorService.obtenerListaTabulador();
	}

	@Override
	public BigDecimal obtenerSueldoDiarioPorIdTabulador(Integer idTabulador) {
		return tabuladorService.obtenerSueldoDiarioPorIdTabulador(idTabulador);
	}

	@Override
	public TabuladorDTO obtenerTabuladorPorPuesto(Integer idPuesto, Integer anioFiscal) {
			return tabuladorService.obtenerTabuladorPorPuesto(idPuesto, anioFiscal);
	}

}
