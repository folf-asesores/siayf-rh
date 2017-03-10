package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import mx.gob.saludtlax.rh.empleados.movimientos.reportes.ComisionadoLicenciaExcelDTO;
import mx.gob.saludtlax.rh.nomina.movimientoscontrato.DetalleMovimientoContratoDTO;

public class DetalleMovimientosContratosRepository
		extends GenericRepository<DetalleMovimientosCotratosEntity, Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5379100167525864224L;

	public List<DetalleMovimientoContratoDTO> obtenerDetallesPorMovimiento(Integer idMovimiento) {
		Session session = em.unwrap(Session.class);
		Query query = session
				.createSQLQuery(
						"Select * From DetalleMovimientosCotratosEntity where id_movimiento_contrato=:idMovimiento")
				.setParameter("idMovimiento", idMovimiento);
		query.setResultTransformer(Transformers.aliasToBean(ComisionadoLicenciaExcelDTO.class));
		@SuppressWarnings("unchecked")
		List<DetalleMovimientoContratoDTO> result = (List<DetalleMovimientoContratoDTO>) query.list();
		return result;
	}

	public List<DetalleMovimientosCotratosEntity> obtenerDetalleMovimientosPorMovimiento(Integer idMovimientoContrato) {
		TypedQuery<DetalleMovimientosCotratosEntity> query = em
				.createQuery(
						" FROM DetalleMovimientosCotratosEntity AS d                         "
								+ " WHERE                                                    "
								+ " d.idMovimientoContrato = :idMovimientoContrato           ",
						DetalleMovimientosCotratosEntity.class)
				.setParameter("idMovimientoContrato", idMovimientoContrato);
		List<DetalleMovimientosCotratosEntity> result = query.getResultList();
		return result;
	}

}
