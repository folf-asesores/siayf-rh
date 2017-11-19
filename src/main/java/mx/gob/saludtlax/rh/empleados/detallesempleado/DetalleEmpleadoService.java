/*
 *
 */

package mx.gob.saludtlax.rh.empleados.detallesempleado;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import mx.gob.saludtlax.rh.util.Configuracion;

/**
 * @author Eduardo Mex
 *
 */
public class DetalleEmpleadoService implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4968811924541393668L;

    @PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
    private EntityManager entityManager;

    protected List<DetalleEmpleadoDTO> detalleEmpleadoPorIdTipoContratacion(Integer idTipoContratacion) {

        Session session = entityManager.unwrap(Session.class);

        Query query = session.createSQLQuery("CALL usp_detalles_empleados(:idTipoContratacion) ").setParameter("idTipoContratacion", idTipoContratacion);

        query.setResultTransformer(Transformers.aliasToBean(DetalleEmpleadoDTO.class));

        @SuppressWarnings("unchecked")
        List<DetalleEmpleadoDTO> list = query.list();

        return list;

    }

}
