/**
 * Copyright © 2016
 */
package mx.gob.saludtlax.rh.empleados.nombramientos;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.empleados.nombramientos.impresion.ClasificacionReporteDTO;
import mx.gob.saludtlax.rh.empleados.nombramientos.impresion.InfoLugarAdscripcionNombramientoDTO;
import mx.gob.saludtlax.rh.empleados.nombramientos.impresion.InfoNombramientoDTO;
import mx.gob.saludtlax.rh.empleados.nombramientos.impresion.NombramientoDetalleDTO;

/**
 * @author Eduardo Mex
 * @email lic.eduardo_mex@hotmail.com
 * @version 1.0
 * @since 18:08:57 12/09/2016
 */
@Stateless
public class NombramientoEJB implements Nombramiento, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2392179056696043613L;

	@Inject
	private ConsultaNombramientoService consultaNombramientoService;
	@Inject
	private NombramientoService nombramientoService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.gob.saludtlax.rh.empleados.nombramientos.Nombramiento#
	 * obtenerNombramientoReportePorId(java.lang.Integer)
	 */
	@Override
	public NombramientoDetalleDTO obtenerNombramientoReportePorId(Integer idNombramiento, Integer idEstructura) {

		return consultaNombramientoService.obtenerNombramientoReportePorId(idNombramiento, idEstructura);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.gob.saludtlax.rh.empleados.nombramientos.Nombramiento#
	 * obtenerListaInfoNombramiento()
	 */
	@Override
	public List<InfoNombramientoDTO> obtenerListaInfoNombramiento() {

		return consultaNombramientoService.obtenerListaInfoNombramiento();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.gob.saludtlax.rh.empleados.nombramientos.Nombramiento#
	 * obtenerListaInfoNombramientoPorTipo(java.lang.Integer)
	 */
	@Override
	public List<InfoNombramientoDTO> obtenerListaInfoNombramientoPorTipo(Integer tipoNombramiento) {

		return consultaNombramientoService.obtenerListaInfoNombramientoPorTipo(tipoNombramiento);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.gob.saludtlax.rh.empleados.nombramientos.Nombramiento#
	 * obtenerInventarioVacantePorIdNombramiento(java.lang.Integer)
	 */
	@Override
	public Integer obtenerInventarioVacantePorIdNombramiento(Integer idNombramiento) {

		return consultaNombramientoService.obtenerInventarioVacantePorIdNombramiento(idNombramiento);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.gob.saludtlax.rh.empleados.nombramientos.Nombramiento#
	 * obtenerInfoLugarAdscripcion(java.lang.Integer, java.lang.Integer,
	 * java.lang.Integer)
	 */
	@Override
	public InfoLugarAdscripcionNombramientoDTO obtenerInfoLugarAdscripcion(Integer adscripcion, Integer areaAdscripcion,
			Integer lugarAdscripcion) {

		return consultaNombramientoService.obtenerInfoLugarAdscripcion(adscripcion, areaAdscripcion, lugarAdscripcion);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.gob.saludtlax.rh.empleados.nombramientos.Nombramiento#
	 * actualizarNombramientoPorImpresion(java.lang.String, boolean)
	 */
	@Override
	public void actualizarNombramientoImpreso(Integer idNombramiento, String tipoAdscripcion, boolean impreso) {
		nombramientoService.actualizarNombramientoImpreso(idNombramiento, tipoAdscripcion, impreso);

	}

	@Override
	public Integer actualizarEstructuraNombramiento(ClasificacionReporteDTO clasificacionReporteDTO) {
		
		return nombramientoService.actualizarEstructuraNombramiento(clasificacionReporteDTO);
	}

	@Override
	public NombramientoDetalleDTO obtenerNombramientoReporteFormalizaFaseIIPorId(Integer idNombramiento,
			Integer idClasificacion) {
		
		return consultaNombramientoService.obtenerNombramientoReporteFormalizaFaseIIPorId(idNombramiento, idClasificacion);
	}

	@Override
	public NombramientoInterinatoDTO obtenerNombramientoReporteInterinato(Integer idNombramiento,
			Integer idClasificacion) {
		
		return consultaNombramientoService.obtenerNombramientoReporteInterinato(idNombramiento, idClasificacion);
	}

}
