/**
 * 
 */
package mx.gob.saludtlax.rh.nomina.productosnomina;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import mx.gob.saludtlax.rh.nomina.reportes.productonomina.ProductosNominaExcelDTO;

/**
 * @author Eduardo Mex
 *
 */
public class ProductoNominaService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8718541321540679089L;
	
	@PersistenceContext(name = "siayfrhPU")
	private EntityManager entityManager;

	protected List<ProductosNominaExcelDTO> obtenerListaProductoNominaPorIdProducto(Integer idProducto) {


		Session session = entityManager.unwrap(Session.class);

		Query query = session.createSQLQuery("CALL usp_productos_nominas(:idProducto) ")
				.setParameter("idProducto", idProducto);

		query.setResultTransformer(Transformers.aliasToBean(ProductosNominaExcelDTO.class));

		@SuppressWarnings("unchecked")
		List<ProductosNominaExcelDTO> list = query.list();

		return list;
	}

}
