package mx.gob.saludtlax.rh.configuracion.tipoperiodo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import mx.gob.saludtlax.rh.util.Configuracion;

public class TipoPeriodoService {

		@PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
	private EntityManager entityManager;

	public List<TipoPeriodoDTO> listaTipoPeriodo() {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("SELECT id_tipo_periodo AS idTipoPeriodo, tipo_periodo AS tipoPeriodo FROM tipos_periodos");
		query.setResultTransformer(Transformers.aliasToBean(TipoPeriodoDTO.class));
		@SuppressWarnings("unchecked")
		List<TipoPeriodoDTO> result = (List<TipoPeriodoDTO>) query.list();
		return result;
	}

    public List<TipoPeriodoDTO> listaTipoPeriodoPorEjercicioFiscal(Integer ejercicioFiscal) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT"
                + " tp.id_tipo_periodo AS idTipoPeriodo, "
                + " tp.tipo_periodo AS tipoPeriodo "
                + " FROM tipos_periodos AS tp "
                + " INNER JOIN ejercicios_fiscales AS ef "
                + " ON tp.id_tipo_periodo = ef.id_tipo_periodo "
                + " WHERE "
                + " ef.ejercicio_fiscal = :ejercicioFiscal").
                setParameter("ejercicioFiscal", ejercicioFiscal);
        query.setResultTransformer(Transformers.aliasToBean(TipoPeriodoDTO.class));
        @SuppressWarnings("unchecked")
        List<TipoPeriodoDTO> result = (List<TipoPeriodoDTO>) query.list();
        return result;
    }
}