/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.util.Configuracion;

/**
 * 
 * @author Eduardo Mex
 * @version 21/03/2016 12:55:06
 * @email Lic.Eduardo_Mex@hotmail.com
 */
public class HistorialAcademicoRepository extends GenericRepository<HistorialAcademicoEntity, Integer> {

	private static final long serialVersionUID = -4518251232465263301L;

		@PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
	private EntityManager entityManager;

	/**
	 * Valida si existe el registro del historial academico del aspirante
	 * 
	 * @param idAspirante
	 * @return
	 */
	public boolean existeIdAspirante(Integer idAspirante) {
		Boolean resultado = Boolean.FALSE;
		String contexto = "Registro de historial académico: ";
		try {
			entityManager.createQuery(
					"SELECT h.idHistorialAcademico FROM HistorialAcademicoEntity AS h.empleado.idEmpleado WHERE  =:idAspirante",
					Integer.class).setParameter("idAspirante", idAspirante).getSingleResult();
			resultado = true;
		} catch (NoResultException ex) {
			resultado = false;
		} catch (NonUniqueResultException ex) {
			throw new ReglaNegocioException(
					contexto + "El historial academico del aspirante ya se encuentra registrado mas de una vez, proceda con el siguiente registro",
					ReglaNegocioCodigoError.YA_AUTORIZADO);
		}

		return resultado;
	}

	public List<HistorialAcademicoEntity> consultarHistorialAcademicoAspirante(Integer idAspirante) {
		return entityManager
				.createQuery("SELECT h FROM HistorialAcademicoEntity AS h WHERE h.idAspirante =:idAspirante",
						HistorialAcademicoEntity.class)
				.setParameter("idAspirante", idAspirante).getResultList();
	}

	public List<HistorialAcademicoEntity> consultarHistorialAcademicoEmpleado(Integer idEmpleado) {

		return entityManager.createQuery("SELECT h FROM HistorialAcademicoEntity AS h WHERE h.idEmpleado =:idEmpleado",
				HistorialAcademicoEntity.class).setParameter("idEmpleado", idEmpleado).getResultList();
	}

	public String nombreEscolaridadCursadaPorIdHistorial(Integer idHistorial) {
		try {
			return em.createQuery(
					"SELECT h.nombreCurso FROM HistorialAcademicoEntity AS h WHERE h.idHistorialAcademico =:idHistorial",
					String.class).setParameter("idHistorial", idHistorial).getSingleResult();
		} catch (NoResultException exception) {
			throw new ReglaNegocioException("Consulta nombre escolaridad: No existe el id de historial " + idHistorial,
					ReglaNegocioCodigoError.SIN_REGISTRO);
		}
	}

	public List<String> consultaEstudiosProfesionistasEmpleado(Integer idEmpleado) {
		return em.createQuery(
				"SELECT h.nombreCurso FROM HistorialAcademicoEntity AS h WHERE h.escolaridad BETWEEN 6 AND 10 AND h.idEmpleado =:idEmpleado",
				String.class).setParameter("idEmpleado", idEmpleado).getResultList();
	}

	public boolean tieneHistorialAcademicoEmpleado(Integer idEmpleado) {
		try {
			em.createQuery(
					"SELECT MAX(h.idHistorialAcademico) FROM HistorialAcademicoEntity AS h WHERE h.idEmpleado =:idEmpleado",
					Integer.class).setParameter("idEmpleado", idEmpleado).getSingleResult();
			return true;
		} catch (NoResultException exception) {
			return false;
		}
	}

	/**
	 * Consulta la lista de profesiones y especialidades del aspirante.
	 */
	public List<String> consultarProfesionesAspirante(Integer idAspirante) {
		return em.createQuery(
				"SELECT h.nombreCurso FROM HistorialAcademicoEntity AS h WHERE h.idAspirante =:idAspirante AND h.escolaridad.perfil = true",
				String.class).setParameter("idAspirante", idAspirante).getResultList();
	}

	/**
	 * Consulta la lista de profesiones y especialidades del empleado.
	 */
	public List<String> consultarProfesionesEmpleado(Integer idEmpleado) {
		return em.createQuery(
				"SELECT h.nombreCurso FROM HistorialAcademicoEntity AS h WHERE h.idEmpleado =:idEmpleado AND h.escolaridad.perfil = true",
				String.class).setParameter("idEmpleado", idEmpleado).getResultList();
	}

    public void actualizarTieneDocumentos(int idHistorialAcademico, boolean tieneDocumentacion) {
        Query query = em.createQuery("update HistorialAcademicoEntity as historial set historial.tieneDocumentacion = :tieneDocumentacion where historial.idHistorialAcademico = :idHistorialAcademico");
        query.setParameter("idHistorialAcademico", idHistorialAcademico);
        query.setParameter("tieneDocumentacion", tieneDocumentacion);
        query.executeUpdate();
    }
}
