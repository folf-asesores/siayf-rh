/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import mx.gob.saludtlax.rh.empleados.suplencia.EnumEstatusSuplencia;
import mx.gob.saludtlax.rh.empleados.suplencia.InfoSuplenciaDTO;
import mx.gob.saludtlax.rh.empleados.suplencia.QuincenaSuplenteDTO;
import mx.gob.saludtlax.rh.empleados.suplencia.SuplenciasQuincenaDTO;
import mx.gob.saludtlax.rh.excepciones.SistemaCodigoError;
import mx.gob.saludtlax.rh.excepciones.SistemaException;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @Since 07/11/2016 23:04:46
 */
public class QuincenasSuplenciasRepository extends GenericRepository<QuincenasSuplenciasEntity, Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7081108340236238495L;

	public QuincenasSuplenciasEntity obtenerQuincenaSuplente(int quincena, int ejercicio, int idSuplente) {
		try {

			return em
					.createQuery(
							"SELECT q FROM QuincenasSuplenciasEntity AS q WHERE q.numeroQuincena =:quincena "
									+ "AND q.ejercicioFiscal =:ejercicio AND q.suplente.idSuplenteAutorizado =:idSuplente",
							QuincenasSuplenciasEntity.class)
					.setParameter("quincena", quincena).setParameter("ejercicio", ejercicio)
					.setParameter("idSuplente", idSuplente).getSingleResult();

		} catch (NoResultException exception) {
			return null;
		} catch (NonUniqueResultException exception) {
			throw new SistemaException("El suplente tiene asignada mas detalle en la misma quincena.",
					SistemaCodigoError.ERROR_MULTIPLES_RESULTADOS);
		}
	}

	public Integer obtenerIdQuincenaSuplente(int quincena, int ejercicio, int idSuplente) {
		try {

			return em
					.createQuery(
							"SELECT q.idQuincenaSuplencia FROM QuincenasSuplenciasEntity AS q WHERE q.numeroQuincena =:quincena "
									+ "AND q.ejercicioFiscal =:ejercicio AND q.suplente.idSuplenteAutorizado =:idSuplente",
							Integer.class)
					.setParameter("quincena", quincena).setParameter("ejercicio", ejercicio)
					.setParameter("idSuplente", idSuplente).getSingleResult();

		} catch (NoResultException exception) {
			return null;
		} catch (NonUniqueResultException exception) {
			throw new SistemaException("El suplente tiene asignada la quincena " + quincena + " mas de una vez",
					SistemaCodigoError.ERROR_MULTIPLES_RESULTADOS);
		}
	}

	public List<QuincenasSuplenciasEntity> consultarSuplenciasPorQuincena(int quincena, int ejercicio, String estatus) {
		return em
				.createQuery(
						"SELECT q FROM QuincenasSuplenciasEntity AS q WHERE "
								+ "q.numeroQuincena =:quincena AND q.ejercicioFiscal =:ejercicio AND q.estatus =:estatus",
						QuincenasSuplenciasEntity.class)
				.setParameter("quincena", quincena).setParameter("ejercicio", ejercicio)
				.setParameter("estatus", estatus).getResultList();
	}

	public List<QuincenaSuplenteDTO> consultarQuincenasPorNombre(String criterio) {
		return em
				.createQuery(
						"SELECT NEW mx.gob.saludtlax.rh.empleados.suplencia.QuincenaSuplenteDTO(q.idQuincenaSuplencia,"
								+ " q.suplente.empleado.nombreCompleto, q.numeroQuincena, q.ejercicioFiscal, q.suplente.idSuplenteAutorizado, q.estatus) "
								+ "FROM QuincenasSuplenciasEntity AS q "
								+ "WHERE q.estatus =:estatus AND (q.suplente.empleado.rfc LIKE :criterio "
								+ "OR q.suplente.empleado.nombreCompleto LIKE :criterio) ORDER BY q.suplente.empleado.rfc ASC",
						QuincenaSuplenteDTO.class)
				.setParameter("estatus", EnumEstatusSuplencia.REVISION).setParameter("criterio", "%" + criterio + "%")
				.getResultList();
	}

	public String obtenerEstatusQuincenaSuplente(int quincena, int ejercicio, int idSuplente) {
		try {

			return em
					.createQuery(
							"SELECT q.estatus FROM QuincenasSuplenciasEntity AS q WHERE q.numeroQuincena =:quincena "
									+ "AND q.ejercicioFiscal =:ejercicio AND q.suplente.idSuplenteAutorizado =:idSuplente",
							String.class)
					.setParameter("quincena", quincena).setParameter("ejercicio", ejercicio)
					.setParameter("idSuplente", idSuplente).getSingleResult();

		} catch (NoResultException exception) {
			return null;
		} catch (NonUniqueResultException exception) {
			throw new SistemaException("El suplente tiene asignada mas detalle en la misma quincena.",
					SistemaCodigoError.ERROR_MULTIPLES_RESULTADOS);
		}
	}

	public List<InfoSuplenciaDTO> consultarDiasSuplidos(Integer idSuplente) {
		return em.createQuery(
				"SELECT NEW mx.gob.saludtlax.rh.empleados.suplencia.InfoSuplenciaDTO(q.idMes, q.ejercicioFiscal, SUM(q.totalDias)) "
						+ "FROM QuincenasSuplenciasEntity AS q " + "WHERE q.suplente.idSuplenteAutorizado =:idSuplente "
						+ "AND q.estatus =:estatus GROUP BY q.ejercicioFiscal, q.idMes",
				InfoSuplenciaDTO.class).setParameter("estatus", EnumEstatusSuplencia.CERRADA)
				.setParameter("idSuplente", idSuplente).getResultList();
	}

	public List<SuplenciasQuincenaDTO> consultarQuincenaSuplencias(int numeroQuincena, int ejercicioFiscal) {
		return em
				.createQuery(
						"SELECT NEW mx.gob.saludtlax.rh.empleados.suplencia.QuincenaSuplenciasDTO(q.suplente.empleado.idEmpleado, q.suplente.empleado.nombreCompleto,q.total)"
								+ " FROM QuincenasSuplenciasEntity AS q WHERE q.numeroQuincena =:numeroQuincena AND q.ejercicioFiscal =:ejercicioFiscal"
								+ " AND q.estatus =:estatus ORDER BY q.suplente.empleado.nombreCompleto ASC",
						SuplenciasQuincenaDTO.class)
				.setParameter("estatus", EnumEstatusSuplencia.CERRADA).setParameter("numeroQuincena", numeroQuincena)
				.setParameter("ejercicioFiscal", ejercicioFiscal).getResultList();
	}

	public List<QuincenaSuplenteDTO> consultarQuincenasPorCentroEstatus(int numeroQuincena, int ejercicioFiscal,
			Integer centroResponsabilidad, String estatus) {
		return em
				.createQuery(
						"SELECT NEW mx.gob.saludtlax.rh.empleados.suplencia.QuincenaSuplenteDTO(q.idQuincenaSuplencia,"
								+ " q.suplente.empleado.nombreCompleto, q.numeroQuincena, q.ejercicioFiscal, q.suplente.idSuplenteAutorizado, q.estatus) "
								+ " FROM QuincenasSuplenciasEntity AS q WHERE q.numeroQuincena =:numeroQuincena AND q.ejercicioFiscal =:ejercicioFiscal"
								+ " AND q.estatus =:estatus AND q.suplente.centroResponsabilidad.idCentroResponsabilidad =:idCentro AND q.estatus =:estatus ORDER BY q.suplente.empleado.nombreCompleto ASC",
						QuincenaSuplenteDTO.class)
				.setParameter("estatus", estatus).setParameter("numeroQuincena", numeroQuincena)
				.setParameter("ejercicioFiscal", ejercicioFiscal).setParameter("idCentro", centroResponsabilidad)
				.getResultList();
	}

	public List<QuincenasSuplenciasEntity> consultarQuincenasIdSuplente(Integer idSuplenteAutorizado) {
		return em
				.createQuery(
						"SELECT q FROM QuincenasSuplenciasEntity AS q WHERE q.suplente.idSuplenteAutorizado =:idSuplenteAutorizado",
						QuincenasSuplenciasEntity.class)
				.setParameter("idSuplenteAutorizado", idSuplenteAutorizado).getResultList();
	}

	public QuincenasSuplenciasEntity obtenerPorNominaEmpleado(NominaEmpleadoEntity nominaEmpleadoEntity) {
		try {
			return em
					.createQuery("SELECT q FROM QuincenasSuplenciasEntity AS q WHERE q.idNomina =:nominaEmpleadoEntity",
							QuincenasSuplenciasEntity.class)
					.setParameter("nominaEmpleadoEntity", nominaEmpleadoEntity.getIdNominaEmpleado()).getSingleResult();
		} catch (NoResultException exception) {
			return null;
		}
	}

}
