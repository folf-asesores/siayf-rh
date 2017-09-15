/*
 * FirmaReporteQuery.java
 * Creado el 11/Sep/2017 12:53:29 PM
 * 
 */

package mx.gob.saludtlax.rh.persistencia.espejo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import mx.gob.saludtlax.rh.nomina.reportes.firma.FirmaPOJO;
import mx.gob.saludtlax.rh.util.Configuracion;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class FirmaReporteQuery implements Serializable {

    private static final long serialVersionUID = 8646160355015622370L;

    @PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA_ESPEJO)
    protected EntityManager em;
    
    private static final String OBTENER_DATOS_FIRMAS = "CALL usp_listado_firma(:idProductoNomina)";
    
    public List<FirmaPOJO> obtenerDatos(Integer idProductoNomina) {
        try {
            Session session = em.unwrap(Session.class);
            Query query = session.createSQLQuery(OBTENER_DATOS_FIRMAS);
            query.setParameter("idProductoNomina", idProductoNomina);
            query.setResultTransformer(Transformers.aliasToBean(FirmaPOJO.class));
            List<FirmaPOJO> resultado = (List<FirmaPOJO>) query.list();

            return resultado;
        } catch (NoResultException ex) {
            return null;
        }
    }
}
