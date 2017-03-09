/**
 * Copyright © 2016
 */
package mx.gob.saludtlax.rh.configuracion.profesion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.persistencia.AspiranteEntity;
import mx.gob.saludtlax.rh.persistencia.AspiranteRepository;
import mx.gob.saludtlax.rh.persistencia.ProfesionAspiranteEmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.ProfesionAspiranteEmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.ProfesionEntity;
import mx.gob.saludtlax.rh.persistencia.ProfesionRepository;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.vacantes.seleccion.EnumTipoCandidato;
import mx.gob.saludtlax.rh.vacantes.seleccion.InfoVacantePostularDTO;

/**
 * @author Eduardo Mex
 * @email lic.eduardo_mex@hotmail.com
 * @version 1.0
 * @since 10:40:43 09/08/2016
 */
public class ProfesionService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6419486243632009432L;

	@Inject
	private AspiranteRepository aspiranteRepository;
	@Inject
	private ProfesionRepository profesionRepository;
	@Inject
	private ProfesionAspiranteEmpleadoRepository profesionAspiranteEmpleadoRepository;

	protected List<ProfesionDTO> obtenerListaProfesionPorIdAspirante(Integer idAspirante) {

		List<ProfesionDTO> listaProfesion = profesionAspiranteEmpleadoRepository
				.obtenerListaProfesionPorIdAspirante(idAspirante);

		return listaProfesion;
	}

	protected List<ProfesionDTO> obtenerListaProfesionPorIdEmpleado(Integer idEmpleado) {

		List<ProfesionDTO> listaProfesion = profesionAspiranteEmpleadoRepository
				.obtenerListaProfesionPorIdEmpleado(idEmpleado);

		return listaProfesion;
	}

	protected List<InfoVacantePostularDTO> obtenerListaProfesionPorTipoCandidato(Integer idProfesion,
			Integer tipoCandidato) {

		List<InfoVacantePostularDTO> listaProfesion = new ArrayList<InfoVacantePostularDTO>();

		if (tipoCandidato == EnumTipoCandidato.ASPIRANTE) {
			listaProfesion = profesionAspiranteEmpleadoRepository
					.obtenerListaPorIdProfesionTipoCandidatoAspirante(idProfesion);

			// Si no hay ninguna candidato con esa profesion, entonces verifica
			// si existe la profesion en la ProfesionEntity, si no existe
			// entonces trae todas los aspirantes con sus profesiones ya que el
			// idProfesion se agrego como un id que no existe en la bd y bandera
			// para traer todo las profesiones aspirante
			if (listaProfesion.isEmpty()) {
				if (!profesionRepository.existeIdProfesion(idProfesion)) {

					listaProfesion = profesionAspiranteEmpleadoRepository.obtenerListaProfesionTipoCandidatoAspirante();
				}
			}

		}

		if (tipoCandidato == EnumTipoCandidato.EMPLEADO) {
			listaProfesion = profesionAspiranteEmpleadoRepository
					.obtenerListaPorIdProfesionTipoCandidatoEmpleado(idProfesion);

			if (listaProfesion.isEmpty()) {

				if (!profesionRepository.existeIdProfesion(idProfesion)) {

					listaProfesion = profesionAspiranteEmpleadoRepository.obtenerListaProfesionTipoCandidatoEmpleado();
				}

			}
		}
		return listaProfesion;
	}

	protected void crearProfesionAspirante(Integer idProfesion, Integer idAspirante) {

		String contexto = "Registro Profesión: ";

		if (idAspirante == null) {
			throw new ReglaNegocioException(contexto + "El necesario registrar los datos generales...",
					ReglaNegocioCodigoError.SIN_REGISTRO);
		}

		if (profesionAspiranteEmpleadoRepository.existeProfesionAspirante(idProfesion, idAspirante)) {
			throw new ReglaNegocioException(contexto + "la profesión ya se encuentra registrado...",
					ReglaNegocioCodigoError.YA_REGISTRADO);
		}

		ProfesionAspiranteEmpleadoEntity profesionAspiranteEmpleadoEntity = new ProfesionAspiranteEmpleadoEntity();

		ProfesionEntity profesionEntity = profesionRepository.obtenerPorId(idProfesion);

		profesionAspiranteEmpleadoEntity.setProfesion(profesionEntity);

		AspiranteEntity aspiranteEntity = aspiranteRepository.obtenerPorId(idAspirante);

		profesionAspiranteEmpleadoEntity.setAspirante(aspiranteEntity);

		profesionAspiranteEmpleadoRepository.crear(profesionAspiranteEmpleadoEntity);

	}

	protected void actualizarProfesionAspirante(ProfesionDTO profesionDTO, Integer idAspirante) {
		String contexto = "Actualización Profesión: ";

		if (idAspirante == null) {
			throw new ReglaNegocioException(contexto + "El necesario registrar los datos generales...",
					ReglaNegocioCodigoError.SIN_REGISTRO);
		}

		if (profesionAspiranteEmpleadoRepository.existeProfesionAspirante(profesionDTO.getIdProfesion(), idAspirante)) {
			throw new ReglaNegocioException(contexto + "la profesión ya se encuentra registrado...",
					ReglaNegocioCodigoError.YA_REGISTRADO);
		}

		ProfesionAspiranteEmpleadoEntity profesionAspiranteEmpleadoEntity = profesionAspiranteEmpleadoRepository
				.obtenerPorId(profesionDTO.getIdProfesionAspiranteEmpleado());

		ProfesionEntity profesionEntity = profesionRepository.obtenerPorId(profesionDTO.getIdProfesion());

		profesionAspiranteEmpleadoEntity.setProfesion(profesionEntity);

		AspiranteEntity aspiranteEntity = aspiranteRepository.obtenerPorId(idAspirante);

		profesionAspiranteEmpleadoEntity.setAspirante(aspiranteEntity);

		profesionAspiranteEmpleadoRepository.actualizar(profesionAspiranteEmpleadoEntity);

	}

	protected void eliminarProfesionAspirante(Integer idProfesionAspirante) {

		profesionAspiranteEmpleadoRepository.eliminarPorId(idProfesionAspirante);

	}
}
