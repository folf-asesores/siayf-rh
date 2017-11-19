
package mx.gob.saludtlax.rh.presupuesto;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.util.Configuracion;

public class ConsultarPartidaService implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -8587474716742021039L;

    @PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
    private EntityManager entityManager;

    public List<ConsultarPartidaDTO> consultarPartidasPorRfc(String rfc) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session
                .createSQLQuery("CALL usp_obtener_partidas_por_rfc(:rfc) ")
                .setParameter("rfc", rfc);
        query.setResultTransformer(
                Transformers.aliasToBean(ConsultarPartidaDTO.class));
        @SuppressWarnings("unchecked")
        List<ConsultarPartidaDTO> list = query.list();
        return list;
    }

    public List<ConsultarPartidaDTO> consultarPartidasPorUnidadResponsable(
            Integer unidadResponsable) {
        Session session = entityManager.unwrap(Session.class);

        Query query = session
                .createSQLQuery(
                        "" + " SELECT " + " COUNT(id_unidad_responsable) "
                                + " FROM acumulado_partida " + " WHERE "
                                + " id_unidad_responsable = :unidadResponsable "
                                + " GROUP BY id_unidad_responsable")
                .setParameter("unidadResponsable", unidadResponsable);
        BigInteger numeroUnidades = (BigInteger) query.uniqueResult();
        if (numeroUnidades != null
                && numeroUnidades.compareTo(BigInteger.ZERO) == 1) {
            query = session.createSQLQuery(
                    "CALL usp_obtener_partidas_por_unidad_responsable(:unidadResponsable) ")
                    .setParameter("unidadResponsable", unidadResponsable);
            query.setResultTransformer(
                    Transformers.aliasToBean(ConsultarPartidaDTO.class));
            @SuppressWarnings("unchecked")
            List<ConsultarPartidaDTO> list = query.list();
            return list;
        } else {
            throw new ReglaNegocioException(
                    "No hay registros en el historico de acumulados",
                    ReglaNegocioCodigoError.SIN_REGISTRO);
        }

    }

    public List<ConsultarPartidaDTO> consultarPartidasPorTipoNombramiento(
            Integer tipoNombramiento) {
        Session session = entityManager.unwrap(Session.class);

        Query query = session
                .createSQLQuery("" + " SELECT " + " COUNT(id_nombramiento) "
                        + " FROM acumulado_partida " + " WHERE "
                        + " id_nombramiento = :tipoNombramiento "
                        + " GROUP BY id_nombramiento")
                .setParameter("tipoNombramiento", tipoNombramiento);
        BigInteger numeroNombramientos = (BigInteger) query.uniqueResult();
        if (numeroNombramientos != null
                && numeroNombramientos.compareTo(BigInteger.ZERO) == 1) {
            query = session.createSQLQuery(
                    "CALL usp_obtener_partidas_por_tipo_nombramiento(:tipoNombramiento) ")
                    .setParameter("tipoNombramiento", tipoNombramiento);
            query.setResultTransformer(
                    Transformers.aliasToBean(ConsultarPartidaDTO.class));
            @SuppressWarnings("unchecked")
            List<ConsultarPartidaDTO> list = query.list();
            return list;
        } else {
            throw new ReglaNegocioException(
                    "No hay registros en el historico de acumulados",
                    ReglaNegocioCodigoError.SIN_REGISTRO);
        }
    }

    public List<ConsultarPartidaDTO> consultarPartidasPorDependencia(
            Integer dependencia) {
        Session session = entityManager.unwrap(Session.class);

        Query query = session
                .createSQLQuery("" + " SELECT " + " COUNT(id_dependencia) "
                        + " FROM acumulado_partida " + " WHERE "
                        + " id_dependencia = :dependencia "
                        + " GROUP BY id_dependencia")
                .setParameter("dependencia", dependencia);
        BigInteger numeroNombramientos = (BigInteger) query.uniqueResult();
        if (numeroNombramientos != null
                && numeroNombramientos.compareTo(BigInteger.ZERO) == 1) {
            query = session.createSQLQuery(
                    "CALL usp_obtener_partidas_por_dependencia(:dependencia) ")
                    .setParameter("dependencia", dependencia);
            query.setResultTransformer(
                    Transformers.aliasToBean(ConsultarPartidaDTO.class));
            @SuppressWarnings("unchecked")
            List<ConsultarPartidaDTO> list = query.list();
            return list;
        } else {
            throw new ReglaNegocioException(
                    "No hay registros en el historico de acumulados",
                    ReglaNegocioCodigoError.SIN_REGISTRO);
        }
    }
}