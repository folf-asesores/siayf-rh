
package mx.gob.saludtlax.rh.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;

import mx.gob.saludtlax.rh.util.Configuracion;

public class AsistenciasEmpleadosRepository {

    @PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
    protected EntityManager em;

    public Integer actualizarAsistencias(String fechaInicio, String fechaFin,
            Integer tipoContratacion, Integer tipoAdscripcion) {
        System.out.println("Datos:" + fechaInicio + " " + fechaFin);

        try {
            Session session = em.unwrap(Session.class);
            Query query = session.createSQLQuery(
                    "CALL usp_actualizar_Asistencias(:fechaInicio,:fechaFin,:tipoContratacion,:Adscripcion)")
                    .setParameter("fechaInicio", fechaInicio)
                    .setParameter("fechaFin", fechaFin)
                    .setParameter("fechaInicio", fechaInicio)
                    .setParameter("tipoContratacion", tipoContratacion)
                    .setParameter("Adscripcion", tipoAdscripcion);
            //query.setResultTransformer(Transformers.aliasToBean(Integer.class));
            Integer result = 0;
            if (query.uniqueResult() != null) {
                result = (Integer) query.uniqueResult();
            }
            return result;

        } catch (NoResultException e) {
            return 0;
        }
    }
}
