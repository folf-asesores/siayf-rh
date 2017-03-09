/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import mx.gob.saludtlax.rh.empleados.movimientos.reportes.ComisionadoLicenciaExcelDTO;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 03/05/2016 03/05/2016
 */
public class MovimientoEmpleadoRepository extends GenericRepository<MovimientoEmpleadoEntity, Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8748813339435935308L;

	public List<MovimientoEmpleadoEntity> consultarMovimientos(Integer idMovimiento) {
		return em.createQuery(
				"SELECT m FROM MovimientoEmpleadoEntity AS m WHERE m.movimiento.idTipoMovimiento =:idTipoMovimiento",
				MovimientoEmpleadoEntity.class).setParameter("idTipoMovimiento", idMovimiento).getResultList();
	}
	// para filtro de busqueda de movimiento por rango de  fechas
	public List<MovimientoEmpleadoEntity> consultarMovimientosFechas(Date fi,Date ff) {
		return em.createQuery(
				"SELECT m FROM MovimientoEmpleadoEntity AS m WHERE m.fechaIngreso BETWEEN :fi AND :ff",
				MovimientoEmpleadoEntity.class)
				.setParameter("fi", fi)
				.setParameter("ff", ff)				
				.getResultList();
	}
	// busca movimientos por coincidencias de rfc o nombre
	public List<MovimientoEmpleadoEntity> consultarMovimientosRFC(String parametro) {
		return em.createQuery(
				"SELECT m FROM MovimientoEmpleadoEntity AS m WHERE m.empleado.rfc LIKE :parametro  or m.empleado.nombreCompleto LIKE :parametro",
				MovimientoEmpleadoEntity.class)
				.setParameter("parametro", "%"+parametro+"%")
				.getResultList();
	}
	
	
	public List<MovimientoEmpleadoEntity> consultarMovimientosPorIdEmpleado(Integer idEmpleado) {
		
		return em.createQuery(
				"SELECT m FROM MovimientoEmpleadoEntity AS m WHERE m.empleado.idEmpleado =:idEmpleado",
				MovimientoEmpleadoEntity.class).setParameter("idEmpleado", idEmpleado).getResultList();
	}
	
	public List<MovimientoEmpleadoEntity> consultarMovimientosPorRfc(String rfc) {
		
		return em.createQuery(
				"SELECT m FROM MovimientoEmpleadoEntity AS m WHERE m.empleado.rfc =:rfc",
				MovimientoEmpleadoEntity.class).setParameter("rfc", rfc).getResultList();
	}

	public List<MovimientoEmpleadoEntity> consultarMovimientosHijos(Integer idMovimientoPadre) {
		return em
				.createQuery(
						"SELECT m FROM MovimientoEmpleadoEntity AS m WHERE m.movimiento.idPadre =:idMovimientoPadre",
						MovimientoEmpleadoEntity.class)
				.setParameter("idMovimientoPadre", idMovimientoPadre).getResultList();
	}

	public MovimientoEmpleadoEntity obtenerUltimoMovimientoPorPadre(Integer idPadre, Integer idEmpleado) {
		try {
			return em
					.createQuery(
							"SELECT m FROM MovimientoEmpleadoEntity AS m WHERE m.movimiento.idPadre =:idPadre AND m.empleado.idEmpleado =:idEmpleado ORDER BY m.fechaIngreso DESC",
							MovimientoEmpleadoEntity.class)
					.setParameter("idPadre", idPadre).setParameter("idEmpleado", idEmpleado).setMaxResults(1)
					.getSingleResult();
		} catch (NoResultException exception) {
			return null;
		}

	}

	public List<ComisionadoLicenciaExcelDTO> listaConsultaComisionadoLicenciaPorRangoFecha(Date fechaInicial,
			Date fechaFinal) {
		try {
			Session session = em.unwrap(Session.class);
			Query query = session.createSQLQuery("CALL usp_comisionado_licencia(:fechaInicial, :fechaFinal)")
					.setParameter("fechaInicial", fechaInicial).setParameter("fechaFinal", fechaFinal);
			query.setResultTransformer(Transformers.aliasToBean(ComisionadoLicenciaExcelDTO.class));
			@SuppressWarnings("unchecked")
			List<ComisionadoLicenciaExcelDTO> result = (List<ComisionadoLicenciaExcelDTO>) query.list();
			System.out.println("Entrando a la consulta de la lista comisionado");
			return result;
		} catch (NoResultException exception) {
			System.err.println("Error: no se encontraron resultados: " + exception.getMessage());
			exception.printStackTrace();
		}
		return null;

	}


  
}
