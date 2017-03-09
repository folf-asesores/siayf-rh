package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import mx.gob.saludtlax.rh.nomina.movimientos.TipoMovimientoDTO;
import mx.gob.saludtlax.rh.nomina.movimientosnomina.TipoMovimientoNominaDTO;

public class TiposMovimientosNominaRepository extends GenericRepository<TiposMovimientosNominaEntity, Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5520808776360200745L;
	
	
public TipoMovimientoNominaDTO obtenerMovimientoPorClave(String clave){
	  Session session = em.unwrap(Session.class);
      Query query = session.createSQLQuery("SELECT "
              + "id_tipo_movimiento_nomina AS idTimpoMovimiento, "
              + " clave, "
              + " descripcion, "
              + " forma_registro AS formaRegistro, "
              +"es_movimiento as esMovimiento,"
              + "id_padre as idPadre "
              + " FROM tipos_movimientos_nomina where clave=:clave").setParameter("clave", clave);
      
      
      query.setResultTransformer(Transformers.aliasToBean(TipoMovimientoNominaDTO.class));
      @SuppressWarnings("unchecked")
     TipoMovimientoNominaDTO result = (TipoMovimientoNominaDTO) query.uniqueResult();
      return result;
}
}
