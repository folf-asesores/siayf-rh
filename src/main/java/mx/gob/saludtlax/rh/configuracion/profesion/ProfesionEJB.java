/**
 * Copyright © 2016
 */
package mx.gob.saludtlax.rh.configuracion.profesion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import mx.gob.saludtlax.rh.excepciones.BusinessException;
import mx.gob.saludtlax.rh.persistencia.ProfesionEntity;
import mx.gob.saludtlax.rh.persistencia.ProfesionRepository;
import mx.gob.saludtlax.rh.vacantes.seleccion.InfoVacantePostularDTO;

/**
 * @author Eduardo Mex
 * @email Lic.Eduardo_Mex@hotmail.com
 * @version 10/03/2016 11:34:08
 */
@Stateless
public class ProfesionEJB implements Serializable, Profesion {

	/**
	 * 
	 */
	private static final long serialVersionUID = 155932748387612020L;

	@Inject
	private ProfesionService profesionService;

	@Inject
	private ProfesionRepository profesionRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.saludtlax.rh.configuracion.profesion.Profesion#crearProfesion(mx.
	 * gob.saludtlax.rh.configuracion.profesion.ProfesionDTO)
	 */
	@Override
	public void crearProfesion(ProfesionDTO profesion) {
		try {
			ProfesionEntity profesionRegistrar = new ProfesionEntity();

			profesionRegistrar.setProfesion(profesion.getProfesion());

			profesionRepository.crear(profesionRegistrar);

		} catch (PersistenceException e) {
			throw new BusinessException("No se registro la Profesión, intentelo de nuevo");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.saludtlax.rh.configuracion.profesion.Profesion#actualizarProfesion
	 * (mx.gob.saludtlax.rh.configuracion.profesion.ProfesionDTO)
	 */
	@Override
	public void actualizarProfesion(ProfesionDTO profesion) {

		try {
			ProfesionEntity profesionActualizar = profesionRepository.obtenerPorId(profesion.getIdProfesion());

			profesionActualizar.setProfesion(profesion.getProfesion());

			profesionRepository.actualizar(profesionActualizar);
		} catch (PersistenceException e) {
			throw new BusinessException("No se actualizarón los datos, intentelo de nuevo");
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.saludtlax.rh.configuracion.profesion.Profesion#eliminarProfesion(
	 * java.lang.Integer)
	 */
	@Override
	public void eliminarProfesion(Integer idProfesion) {
		try {
			profesionRepository.eliminarPorId(idProfesion);
		} catch (PersistenceException ex) {
			throw new BusinessException("No se elimino la Profesión, Intentelo de nuevo");
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.saludtlax.rh.configuracion.profesion.Profesion#listaProfesion()
	 */
	@Override
	public List<ProfesionDTO> listaProfesion() {
		try {
			List<ProfesionDTO> listaProfesion = new ArrayList<>();
			List<ProfesionEntity> profesionEntities = profesionRepository.obtenerListaProfesion();

			for (ProfesionEntity profesionEntity : profesionEntities) {
				ProfesionDTO profesion = new ProfesionDTO(profesionEntity.getIdProfesion(),
						profesionEntity.getProfesion());
				listaProfesion.add(profesion);
			}
			return listaProfesion;
		} catch (NoResultException ex) {
			throw new BusinessException("No se encontrarón resultados");
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.gob.saludtlax.rh.configuracion.profesion.Profesion#
	 * obtenerListaProfesionPorIdAspirante(java.lang.Integer)
	 */
	@Override
	public List<ProfesionDTO> obtenerListaProfesionPorIdAspirante(Integer idAspirante) {
		return profesionService.obtenerListaProfesionPorIdAspirante(idAspirante);
	}

	@Override
	public List<ProfesionDTO> obtenerListaProfesionPorIdEmpleado(Integer idEmpleado) {

		return profesionService.obtenerListaProfesionPorIdEmpleado(idEmpleado);
	}

	@Override
	public List<InfoVacantePostularDTO> obtenerListaProfesionPorTipoCandidato(Integer idProfesion,
			Integer tipoCandidato) {

		return profesionService.obtenerListaProfesionPorTipoCandidato(idProfesion, tipoCandidato);
	}

	@Override
	public void crearProfesionAspirante(Integer idProfesion, Integer idAspirante) {
		profesionService.crearProfesionAspirante(idProfesion, idAspirante);
	}

	@Override
	public void actualizarProfesionAspirante(ProfesionDTO profesionDTO, Integer idAspirante) {
		profesionService.actualizarProfesionAspirante(profesionDTO, idAspirante);
		
	}

	@Override
	public void eliminarProfesionAspirante(Integer idProfesionAspirante) {

		profesionService.eliminarProfesionAspirante(idProfesionAspirante);
	}
}
