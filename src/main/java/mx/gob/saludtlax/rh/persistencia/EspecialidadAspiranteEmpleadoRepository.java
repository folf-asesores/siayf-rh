/**
 * Copyright © 2016
 */
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.InfoAspiranteDTO;
import mx.gob.saludtlax.rh.configuracion.especialidad.EspecialidadDTO;
import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;
import mx.gob.saludtlax.rh.vacantes.seleccion.InfoVacantePostularDTO;

/**
 * @author Eduardo Mex
 * @email lic.eduardo_mex@hotmail.com
 * @version 1.0
 * @since 14:22:04 05/08/2016
 */
public class EspecialidadAspiranteEmpleadoRepository
		extends GenericRepository<EspecialidadAspiranteEmpleadoEntity, Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3713679248050122528L;

	/**
	 * Retorna las coincidencias de aspirantes con la especialidad.
	 * 
	 * @param nombre
	 */
	public List<InfoAspiranteDTO> aspirantesDisponiblesPorEspecialidad(String criterio) {
		String consulta = "SELECT NEW mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.InfoAspiranteDTO(e.aspirante.idAspirante, e.aspirante.nombreCompleto, e.aspirante.curp, e.aspirante.rfc, e.aspirante.direccionCompleta, e.aspirante.idEstatus, e.especialidad.especialidad) FROM EspecialidadAspiranteEmpleadoEntity AS e WHERE (e.especialidad.especialidad LIKE :criterio) AND e.empleado.idEmpleado IS NULL";
		List<InfoAspiranteDTO> resultado = em.createQuery(consulta, InfoAspiranteDTO.class)
				.setParameter("criterio", "%" + criterio + "%").getResultList();
		return resultado;
	}

	/**
	 * Retorna las especialidades por identificador del aspirante
	 * 
	 * @param idAspirante
	 * @return
	 */
	public List<EspecialidadDTO> obtenerListaEspecialidadPorIdAspirante(Integer idAspirante) {

		String consulta = "SELECT NEW mx.gob.saludtlax.rh.configuracion.especialidad.EspecialidadDTO(e.idEspecialidadAspiranteEmpleado, e.especialidad.idEspecialidad, e.especialidad.especialidad) FROM EspecialidadAspiranteEmpleadoEntity AS e WHERE e.aspirante.idAspirante =:idAspirante AND e.empleado.idEmpleado IS NULL";

		List<EspecialidadDTO> resultado = em.createQuery(consulta, EspecialidadDTO.class)
				.setParameter("idAspirante", idAspirante).getResultList();

		return resultado;
	}

	public List<InfoEmpleadoDTO> empleadoDisponiblePorEspecialidad(String criterio) {
		String consulta = "SELECT NEW mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO(e.empleado.idEmpleado, e.empleado.nombreCompleto, e.empleado.curp, e.empleado.rfc, e.empleado.direccionCompleta, e.empleado.numeroEmpleado, e.empleado.idEstatus, e.especialidad.especialidad) FROM EspecialidadAspiranteEmpleadoEntity AS e WHERE (e.especialidad.especialidad LIKE :criterio)";
		List<InfoEmpleadoDTO> resultado = em.createQuery(consulta, InfoEmpleadoDTO.class)
				.setParameter("criterio", "%" + criterio + "%").getResultList();

		return resultado;
	}

	public List<EspecialidadDTO> obtenerListaEspecialidadPorIdEmpleado(Integer idEmpleado) {

		String consulta = "SELECT NEW mx.gob.saludtlax.rh.configuracion.especialidad.EspecialidadDTO(e.idEspecialidadAspiranteEmpleado, e.especialidad.idEspecialidad, e.especialidad.especialidad) FROM EspecialidadAspiranteEmpleadoEntity AS e WHERE e.empleado.idEmpleado =:idEmpleado";

		List<EspecialidadDTO> resultado = em.createQuery(consulta, EspecialidadDTO.class)
				.setParameter("idEmpleado", idEmpleado).getResultList();

		return resultado;
	}

	/**
	 * Obtiene la lista de aspirantes con identificador de especialidad
	 * 
	 * @param idEspecialidad
	 * @return
	 */
	public List<InfoVacantePostularDTO> obtenerListaPorIdEspecialidadTipoCandidatoAspirante(Integer idEspecialidad) {

		String query = "SELECT NEW mx.gob.saludtlax.rh.vacantes.seleccion.InfoVacantePostularDTO(e.aspirante.idAspirante, e.aspirante.rfc, e.aspirante.curp, e.aspirante.nombreCompleto, e.aspirante.direccionCompleta, e.aspirante.idEstatus, e.especialidad.especialidad) FROM EspecialidadAspiranteEmpleadoEntity As e WHERE e.especialidad.idEspecialidad =:idEspecialidad AND e.empleado.idEmpleado IS NULL";

		List<InfoVacantePostularDTO> resultado = em.createQuery(query, InfoVacantePostularDTO.class)
				.setParameter("idEspecialidad", idEspecialidad).getResultList();

		return resultado;
	}

	/**
	 * Obtiene las lista de aspirantes con especialidad
	 * 
	 * @return
	 */
	public List<InfoVacantePostularDTO> obtenerListaEspecialidadTipoCandidatoAspirante() {

		String query = "SELECT NEW mx.gob.saludtlax.rh.vacantes.seleccion.InfoVacantePostularDTO(e.aspirante.idAspirante, e.aspirante.rfc, e.aspirante.curp, e.aspirante.nombreCompleto, e.aspirante.direccionCompleta, e.aspirante.idEstatus, e.especialidad.especialidad) FROM EspecialidadAspiranteEmpleadoEntity As e WHERE e.empleado.idEmpleado IS NULL";

		List<InfoVacantePostularDTO> resultado = em.createQuery(query, InfoVacantePostularDTO.class).getResultList();

		return resultado;
	}

	/**
	 * Obtiene la lista de empleados con identificador de especialidad
	 * 
	 * @param idEspecialidad
	 * @return
	 */
	public List<InfoVacantePostularDTO> obtenerListaPorIdEspecialidadTipoCandidatoEmpleado(Integer idEspecialidad) {

		String query = "SELECT NEW mx.gob.saludtlax.rh.vacantes.seleccion.InfoVacantePostularDTO(e.empleado.idEmpleado, e.empleado.rfc, e.empleado.curp, e.empleado.nombreCompleto, e.empleado.direccionCompleta, e.empleado.idEstatus, e.especialidad.especialidad) FROM EspecialidadAspiranteEmpleadoEntity As e WHERE e.especialidad.idEspecialidad =:idEspecialidad AND e.aspirante.idAspirante IS NULL";

		List<InfoVacantePostularDTO> resultado = em.createQuery(query, InfoVacantePostularDTO.class)
				.setParameter("idEspecialidad", idEspecialidad).getResultList();

		return resultado;

	}

	/**
	 * Obtiene la lista de empleados con especialidad
	 * 
	 * @return
	 */
	public List<InfoVacantePostularDTO> obtenerListaEspecialidadTipoCandidatoEmpleado() {

		String query = "SELECT NEW mx.gob.saludtlax.rh.vacantes.seleccion.InfoVacantePostularDTO(e.empleado.idEmpleado, e.empleado.rfc, e.empleado.curp, e.empleado.nombreCompleto, e.empleado.direccionCompleta, e.empleado.idEstatus, e.especialidad.especialidad) FROM EspecialidadAspiranteEmpleadoEntity As e WHERE e.aspirante.idAspirante IS NULL";

		List<InfoVacantePostularDTO> resultado = em.createQuery(query, InfoVacantePostularDTO.class).getResultList();

		return resultado;

	}

	public boolean existeEspecialidadAspirante(Integer idEspecialidad, Integer idAspirante) {
		boolean resultado = false;
		try {

			EspecialidadAspiranteEmpleadoEntity especialidadAspiranteEmpleadoEntity = em
					.createQuery(
							"SELECT e FROM EspecialidadAspiranteEmpleadoEntity AS e WHERE e.especialidad.idEspecialidad =:idEspecialidad AND e.aspirante.idAspirante =:idAspirante",
							EspecialidadAspiranteEmpleadoEntity.class)
					.setParameter("idEspecialidad", idEspecialidad).setParameter("idAspirante", idAspirante)
					.getSingleResult();

			resultado = true;
		} catch (NoResultException e) {
			resultado = false;
		} catch (NonUniqueResultException e) {
			resultado = true;
		}
		return resultado;
	}

}
