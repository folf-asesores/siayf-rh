/**
 * 
 */
package mx.gob.saludtlax.rh.nomina.historialpago;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

/**
 * @author Eduardo Mex
 *
 */
public class HistorialPagoService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2524219054309362373L;
	
	@PersistenceContext(name = "siayfrhPU")
	private EntityManager entityManager;
	
	protected List<HistorialPagoDetalleDTO> obtenerListaHistorialPagoPorIdEmpleado(Integer idEmpleado) {

		Session session = entityManager.unwrap(Session.class);

		Query query = session.createSQLQuery("CALL usp_historiales_pagos(:idEmpleado) ")
				.setParameter("idEmpleado", idEmpleado);

		query.setResultTransformer(Transformers.aliasToBean(HistorialPagoDetalleDTO.class));

		@SuppressWarnings("unchecked")
		List<HistorialPagoDetalleDTO> list = query.list();

		return list;
	}

}
