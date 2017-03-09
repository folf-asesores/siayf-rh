/*
 * ComprobanteEmpleadoRepository.java
 * Creado el 22/Nov/2016 4:36:59 AM
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import mx.gob.saludtlax.rh.nomina.reportes.comprobante.ComprobanteEmpleadoPOJO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

/**
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
public class ComprobanteEmpleadoRepository implements Serializable {
    
    private static final long serialVersionUID = -1006356522264912212L;
    
    @PersistenceContext(name = "siayfrhPU")
    protected EntityManager em;
    
    @SuppressWarnings("unchecked")
    public List<ComprobanteEmpleadoPOJO> obtenerDatos(Integer idProductoNomina) {
        try {
            Session session = em.unwrap(Session.class);
            Query query = session.createSQLQuery("CALL usp_nomina_comprobante_empleado(:idProductoNomina)");
            query.setParameter("idProductoNomina", idProductoNomina);
            query.setResultTransformer(Transformers.aliasToBean(ComprobanteEmpleadoPOJO.class));
            List<ComprobanteEmpleadoPOJO> resultado = (List<ComprobanteEmpleadoPOJO>) query.list();

            return resultado;
        } catch (NoResultException ex) {
            return null;
        }
    }
    
}
