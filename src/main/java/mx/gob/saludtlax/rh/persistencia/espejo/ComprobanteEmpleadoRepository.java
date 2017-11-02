/*
 * ComprobanteEmpleadoRepository.java
 * Creado el 22/nov/2016 4:36:59 AM
 * 
 */

package mx.gob.saludtlax.rh.persistencia.espejo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import mx.gob.saludtlax.rh.nomina.reportes.comprobante.ComprobanteEmpleadoPojo;
import mx.gob.saludtlax.rh.util.Configuracion;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class ComprobanteEmpleadoRepository implements Serializable {
    
    private static final long serialVersionUID = -1006356522264912212L;
    
    @PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA_ESPEJO)
    protected EntityManager em;
    
    @SuppressWarnings("unchecked")
    public List<ComprobanteEmpleadoPojo> obtenerDatos(Integer idProductoNomina) {
        try {
            Session session = em.unwrap(Session.class);
            Query query = session.createSQLQuery("CALL usp_nomina_comprobante_empleado(:idProductoNomina)");
            query.setParameter("idProductoNomina", idProductoNomina);
            query.setResultTransformer(Transformers.aliasToBean(ComprobanteEmpleadoPojo.class));
            List<ComprobanteEmpleadoPojo> resultado = (List<ComprobanteEmpleadoPojo>) query.list();

            return resultado;
        } catch (NoResultException ex) {
            return null;
        }
    }
    
}
