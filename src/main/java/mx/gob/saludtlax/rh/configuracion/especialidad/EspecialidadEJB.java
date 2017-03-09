/**
 * Copyright © 2016
 */
package mx.gob.saludtlax.rh.configuracion.especialidad;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.vacantes.seleccion.InfoVacantePostularDTO;

/**
 * @author Eduardo Mex
 * @email lic.eduardo_mex@hotmail.com
 * @version 1.0
 * @since 12:25:20 09/08/2016
 */
@Stateless
public class EspecialidadEJB implements Especialidad, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5723172714749326050L;

	@Inject
	private EspecialidadService especialidadService;

	@Override
	public List<EspecialidadDTO> obtenerListaEspecialidadPorIdAspirante(Integer idAspirante) {
		return especialidadService.obtenerListaEspecialidadPorIdAspirante(idAspirante);
	}

	@Override
	public List<EspecialidadDTO> obtenerListaEspecialidadPorIdEmpleado(Integer idEmpleado) {
		return especialidadService.obtenerListaEspecialidadPorIdEmpleado(idEmpleado);
	}

	@Override
	public List<InfoVacantePostularDTO> obtenerListaEspecialidadPorTipoCandidato(Integer idEspecialidad,
			Integer tipoCandidato) {

		return especialidadService.obtenerListaEspecialidadPorTipoCandidato(idEspecialidad, tipoCandidato);
	}

	@Override
	public void crearEspecialidadAspirante(Integer idEspecialidad, Integer idAspirante) {
		especialidadService.crearEspecialidadAspirante(idEspecialidad, idAspirante);
	}

	@Override
	public void actualizarEspecialidadAspirante(EspecialidadDTO dto, Integer idAspirante) {
		especialidadService.actualizarEspecialidadAspirante(dto, idAspirante);
		
	}

	@Override
	public void eliminarEspecialidadAspirante(Integer idEspecialidadAspirante) {
		especialidadService.eliminarEspecialidadAspirante(idEspecialidadAspirante);
		
	}

}
