/*
 * SeguroPopularReporteService.java
 * Creado el 09/Dec/2016 7:48:59 PM
 * 
 */
package mx.gob.saludtlax.rh.siif.seguropopular;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import mx.gob.saludtlax.rh.util.Configuracion;

/**
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
public class SeguroPopularReporteService implements Serializable {

    private static final long serialVersionUID = 3761193443603057594L;

    	@PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
    private EntityManager entityManager;

    /**
     * Obtiene la información para el reporte de seguro popular.
     * 
     * @return una lista con la información del reporte de seguro popular.
     */
    protected List<SeguroPopularReporteDTO> obtenerInformacion() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("CALL usp_generar_seguro_popular_rh_dev()");
        query.setResultTransformer(Transformers.aliasToBean(SeguroPopularReporteDTO.class));
        
        List<SeguroPopularReporteDTO> resultado = (List<SeguroPopularReporteDTO>) query.list();
        return resultado == null ? Collections.EMPTY_LIST : resultado;
    }

    protected List<SeguroPopularReporteDTO> obtenerInformacion(String anyo, Integer quincena) {
        //TODO: verificar método
        throw new UnsupportedOperationException("Not supported yet."); 
    }

}
