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
import mx.gob.saludtlax.rh.nomina.reportes.productonomina.ProductosNominaProgramasExcelDTO;
import mx.gob.saludtlax.rh.util.Configuracion;

/**
 * @author Eduardo Mex
 *
 */
public class ProductoNominaService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8718541321540679089L;
	
		@PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
	private EntityManager entityManager;
        
        private static final String USP_PRODUCTO_NOMINA_SUPLENCIA =
                "CALL usp_productos_nominas_suplencias(:idProducto)";

	protected List<ProductosNominaExcelDTO> obtenerListaProductoNominaPorIdProducto(Integer idProducto) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("CALL usp_productos_nominas(:idProducto)");
		query.setParameter("idProducto", idProducto);
		query.setResultTransformer(Transformers.aliasToBean(ProductosNominaExcelDTO.class));

		@SuppressWarnings("unchecked")
		List<ProductosNominaExcelDTO> list = query.list();

		return list;
	}
	
	protected List<ProductosNominaProgramasExcelDTO> obtenerListaProductoNominaProgramasPorIdProducto(Integer idProducto) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("CALL usp_productos_nominas_programas(:idProducto)");
		query.setParameter("idProducto", idProducto);
		query.setResultTransformer(Transformers.aliasToBean(ProductosNominaProgramasExcelDTO.class));

		@SuppressWarnings("unchecked")
		List<ProductosNominaProgramasExcelDTO> list = query.list();

		return list;
	}
	
	protected List<ProductosNominaExcelDTO> obtenerListaProductoNominaPorIdProductoEstatus(Integer idProducto,
			Integer estatus) {
		Session session = entityManager.unwrap(Session.class);

		Query query = session.createSQLQuery("CALL usp_productos_nominas_estatus(:idProducto, :idEstatus)")
				.setParameter("idProducto", idProducto).setParameter("idEstatus", estatus);

		query.setResultTransformer(Transformers.aliasToBean(ProductosNominaExcelDTO.class));

		@SuppressWarnings("unchecked")
		List<ProductosNominaExcelDTO> list = query.list();

		return list;
	}

    /**
     * Permite obtener la infomación para generar el reporte del producto de
     * nómina para suplencias.
     * @param idProducto el ID del producto de nómina.
     * @return una lista con la información del producto de nómina.
     */
    protected List<ProductosNominaExcelDTO> obtenerListaProductoNominaSuplenciaPorIdProducto(Integer idProducto) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery(USP_PRODUCTO_NOMINA_SUPLENCIA)
                .setParameter("idProducto", idProducto);
        query.setResultTransformer(
                Transformers.aliasToBean(ProductosNominaExcelDTO.class));
        List<ProductosNominaExcelDTO> list = query.list();
        return list;
    }

}
