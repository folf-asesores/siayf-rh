/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import javax.persistence.NoResultException;

import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.InfoAspiranteDTO;
import mx.gob.saludtlax.rh.excepciones.SistemaCodigoError;
import mx.gob.saludtlax.rh.excepciones.SistemaException;
import mx.gob.saludtlax.rh.vacantes.consulta.DatoGeneralCandidatoDTO;
import mx.gob.saludtlax.rh.vacantes.seleccion.InfoVacantePostularDTO;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 19/04/2016-12:43:48
 */
public class AspiranteRepository extends GenericRepository<AspiranteEntity, Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4101896049673801486L;

	/**
	 * Retorna las coincidencias de aspirantes con el nombre ingresado.
	 * 
	 * @param nombre
	 */
	public List<InfoAspiranteDTO> aspirantesDisponiblesPorCriterio(String criterio) {
		String consulta = "SELECT NEW mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.InfoAspiranteDTO(a.idAspirante, a.nombreCompleto, a.curp, a.rfc, a.direccionCompleta, a.idEstatus) FROM AspiranteEntity AS a WHERE (a.nombreCompleto LIKE :criterio OR a.rfc LIKE :criterio OR a.curp LIKE :criterio) AND a.empleado.idEmpleado IS NULL";
		List<InfoAspiranteDTO> resultado = em.createQuery(consulta, InfoAspiranteDTO.class)
				.setParameter("criterio", "%" + criterio + "%").getResultList();
		return resultado;
	}

	public List<InfoVacantePostularDTO> obtenerListaAspiranteCandidato() {
		String query = "SELECT NEW mx.gob.saludtlax.rh.vacantes.seleccion.InfoVacantePostularDTO(a.idAspirante, a.rfc, a.curp, a.nombreCompleto, a.direccionCompleta, a.idEstatus) FROM AspiranteEntity AS a WHERE a.empleado.idEmpleado IS NULL";

		List<InfoVacantePostularDTO> resultado = em.createQuery(query, InfoVacantePostularDTO.class).getResultList();

		return resultado;

	}

	public DatoGeneralCandidatoDTO obtenerCadidatoAspirante(Integer idAspirante) {

		String query = "SELECT NEW mx.gob.saludtlax.rh.vacantes.consulta.DatoGeneralCandidatoDTO(a.nombreCompleto, a.rfc, a.curp, a.direccionCompleta, a.idSexo, a.estadoCivil, a.fechaNacimiento, a.lugarNacimiento, a.telefono, a.correoElectronico, a.tipoSangre, a.tienePersonasDependientes, a.numeroHijos, a.numeroPadres, a.numeroConyuges, a.numeroOtros)FROM AspiranteEntity AS a WHERE a.idAspirante =:idAspirante";

		DatoGeneralCandidatoDTO dto = em.createQuery(query, DatoGeneralCandidatoDTO.class)
				.setParameter("idAspirante", idAspirante).getSingleResult();

		return dto;

	}

	/**
	 * Registra o Actualiza el aspirante
	 * 
	 * @param aspiranteEntity
	 * @return
	 */
	public Integer crearActualizarAspirante(AspiranteEntity aspiranteEntity) {
		em.persist(aspiranteEntity);
		return aspiranteEntity.getIdAspirante();
	}

	/**
	 * Valida si existe un empleado registrado con la curp.
	 * 
	 * @param curp
	 */
	public boolean existeAspiranteCurp(String curp) {
		try {
			em.createQuery("SELECT a.idAspirante FROM AspiranteEntity AS a WHERE a.curp =:curp", Integer.class)
					.setParameter("curp", curp).getSingleResult();
			return true;
		} catch (NoResultException exception) {
			return false;
		}
	}

	/**
	 * Valida si existe un empleado registrado con la curp.
	 * 
	 * @param curp
	 */
	public boolean consultarAspiranteRFC(String rfc) {
		try {
			em.createQuery("SELECT a.idAspirante FROM AspiranteEntity AS a WHERE a.rfc =:rfc", Integer.class)
					.setParameter("rfc", rfc).getSingleResult();
			return true;
		} catch (NoResultException exception) {
			return false;
		}
	}

	/**
	 * Valida si existe un empleado registrado con la curp.
	 * 
	 * @param curp
	 */
	public boolean consultarAspiranteRFCyId(Integer idAspirante, String rfc) {
		try {
			em.createQuery(
					"SELECT a.idAspirante FROM AspiranteEntity AS a WHERE a.idAspirante =:idAspirante AND a.rfc =:rfc",
					Integer.class).setParameter("idAspirante", idAspirante).setParameter("rfc", rfc).getSingleResult();
			return true;
		} catch (NoResultException exception) {
			return false;
		}
	}

	/**
	 * Valida si existe un empleado registrado con la curp.
	 * 
	 * @param curp
	 */
	public boolean existeAspiranteCurpyId(Integer idAspirante, String curp) {
		try {
			em.createQuery(
					"SELECT a.idAspirante FROM AspiranteEntity AS a WHERE a.idAspirante =:idAspirante AND a.curp =:curp",
					Integer.class).setParameter("idAspirante", idAspirante).setParameter("curp", curp)
					.getSingleResult();
			return true;
		} catch (NoResultException exception) {
			return false;
		}
	}

	public String consultarNombreAspirantePorId(Integer idAspirante) {
		try {
			return em.createQuery("SELECT a.nombreCompleto FROM AspiranteEntity AS a WHERE a.idAspirante =:idAspirante",
					String.class).setParameter("idAspirante", idAspirante).getSingleResult();
		} catch (NoResultException exception) {
			throw new SistemaException("No se encontró un aspirante con identificador " + idAspirante
					+ " comuniquese con soporte tecnico.", SistemaCodigoError.BUSQUEDA_SIN_RESULTADOS);
		}
	}

	/**
	 * Consulta si existe el empleado por el identificador.
	 * 
	 * @param idAspirante
	 */
	public boolean existeIdAspirante(Integer idAspirante) {
		try {
			em.createQuery("SELECT a.idAspirante FROM AspiranteEntity AS a WHERE a.idAspirante =:idAspirante",
					Integer.class).setParameter("idAspirante", idAspirante).getResultList();
			return true;
		} catch (NoResultException exception) {
			return false;
		}

	}

	public String obtenerEstatusAspirantePorId(Integer idAspirante) {
		try {
			return em.createQuery("SELECT a.idEstatus FROM AspiranteEntity AS a WHERE a.idAspirante =:idAspirante",
					String.class).setParameter("idAspirante", idAspirante).getSingleResult();

		} catch (NoResultException exception) {
			return null;
		}

	}

	public String obtenerNombreAspirante(Integer idAspirante) {
		try {
			return em.createQuery("SELECT a.nombreCompleto FROM AspiranteEntity AS a WHERE a.idAspirante =:idAspirante",
					String.class).setParameter("idAspirante", idAspirante).getSingleResult();

		} catch (NoResultException exception) {
			return null;
		}

	}

	/**
	 * Consulta los aspirantes por nombre, rfc, curp, o perfil
	 */
	public List<InfoAspiranteDTO> aspirantesDisponiblesPorCriterioPerfil(String criterio) {
		String consulta = "SELECT NEW mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.InfoAspiranteDTO(a.idAspirante, a.nombreCompleto, a.curp, a.rfc, a.direccionCompleta, a.idEstatus) FROM AspiranteEntity AS a WHERE (a.nombreCompleto LIKE :criterio OR a.rfc LIKE :criterio OR a.perfilAcademico LIKE :criterio OR a.curp LIKE :criterio) AND a.empleado.idEmpleado IS NULL";
		List<InfoAspiranteDTO> resultado = em.createQuery(consulta, InfoAspiranteDTO.class)
				.setParameter("criterio", "%" + criterio + "%").getResultList();
		return resultado;
	}

}
