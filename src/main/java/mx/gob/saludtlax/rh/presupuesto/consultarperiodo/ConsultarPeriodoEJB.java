
package mx.gob.saludtlax.rh.presupuesto.consultarperiodo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import mx.gob.saludtlax.rh.util.Configuracion;
import mx.gob.saludtlax.rh.util.FechaUtil;

@Stateless
public class ConsultarPeriodoEJB {
    @PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<String> obtenerPeriodoList() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT fecha_fin_quincena FROM acumulado_partida GROUP BY fecha_fin_quincena");
        List<Date> list = query.list();
        List<String> listStr = new ArrayList<>();
        for (Date date : list) {
            listStr.add(FechaUtil.formatoFecha(date));
        }
        return listStr;
    }

    @SuppressWarnings("unchecked")
    public List<ConsultaPeriodo> consultarNominaPorPeriodo(String periodoStr) {
        Date periodo = FechaUtil.stringToDate(periodoStr);
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("CALL usp_consulta_periodo(:periodo)").setParameter("periodo", periodo);
        query.setResultTransformer(Transformers.aliasToBean(ConsultaPeriodo.class));
        List<ConsultaPeriodo> list = query.list();
        return list;
    }
}
