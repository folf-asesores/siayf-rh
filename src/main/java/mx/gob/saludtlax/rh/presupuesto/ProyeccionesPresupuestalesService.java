
package mx.gob.saludtlax.rh.presupuesto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.persistencia.ProyeccionesPresupuestalesEntity;
import mx.gob.saludtlax.rh.persistencia.ProyeccionesPresupuestalesRepository;
import mx.gob.saludtlax.rh.util.Configuracion;

public class ProyeccionesPresupuestalesService implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5031743578563079046L;

    @PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
    private EntityManager entityManager;
    @Inject
    private ProyeccionesPresupuestalesRepository proyeccionesPresupuestalesRepository;

    public List<ProyeccionesPresupuestalesDTO> proyeccionesPresupuestales(
            Integer anio) {

        Session session = entityManager.unwrap(Session.class);

        Query query = session
                .createSQLQuery(
                        "CALL usp_obtener_proyecciones_presupuestales(:anio) ")
                .setParameter("anio", anio);
        query.setResultTransformer(
                Transformers.aliasToBean(ProyeccionesPresupuestalesDTO.class));
        @SuppressWarnings("unchecked")
        List<ProyeccionesPresupuestalesDTO> list = query.list();

        /*
         * List<ProyeccionesPresupuestalesDTO> result = new ArrayList<ProyeccionesPresupuestalesDTO>();
         * for(int i = 0 ; i < 100; i++) {
         * result.add(list.get(i));
         * }
         */

        return list;
    }

    //    public List<ProyeccionesPresupuestalesDTO> consultarPartidasPorTipoNombramiento(Integer tipoNombramiento) {
    //		Session session = entityManager.unwrap(Session.class);
    //
    //		Query query = session.createSQLQuery(""
    //                + " SELECT "
    //                + " COUNT(id_nombramiento) "
    //                + " FROM acumulado_partida "
    //                + " WHERE "
    //                + " id_nombramiento = :tipoNombramiento "
    //                + " GROUP BY id_nombramiento")
    //                  .setParameter("tipoNombramiento", tipoNombramiento);
    //          BigInteger numeroNombramientos = (BigInteger) query.uniqueResult();
    //      if (numeroNombramientos!= null && numeroNombramientos.compareTo(BigInteger.ZERO) == 1) {
    //		 query = session.createSQLQuery("CALL usp_obtener_partidas_por_tipo_nombramiento(:tipoNombramiento) ")
    //				.setParameter("tipoNombramiento", tipoNombramiento);
    //		query.setResultTransformer(Transformers.aliasToBean(ProyeccionesPresupuestalesDTO.class));
    //		@SuppressWarnings("unchecked")
    //		List<ProyeccionesPresupuestalesDTO> list = query.list();
    //		return list;
    //      }else{
    //          throw new ReglaNegocioException("No hay registros en el historico de acumulados",ReglaNegocioCodigoError.SIN_REGISTRO);
    //      }
    //	}

    public List<ProyeccionesPresupuestalesDTO> obtenerProyeccionesMensuales(
            Integer anioPresupuesto, Integer idTipoNombramiento) {
        Session session = entityManager.unwrap(Session.class);

        Query query = session
                .createSQLQuery("" + " SELECT " + " COUNT(id_nombramiento) "
                        + " FROM acumulado_partida " + " WHERE "
                        + " id_nombramiento = :tipoNombramiento "
                        + " GROUP BY id_nombramiento")
                .setParameter("tipoNombramiento", idTipoNombramiento);
        BigInteger numeroNombramientos = (BigInteger) query.uniqueResult();
        if (numeroNombramientos != null
                && numeroNombramientos.compareTo(BigInteger.ZERO) == 1) {
            query = session.createSQLQuery(
                    "CALL usp_obtener_proyecciones_mensuales(:anioPresupuesto, :tipoNombramiento) ")
                    .setParameter("anioPresupuesto", anioPresupuesto)
                    .setParameter("tipoNombramiento", idTipoNombramiento);
            query.setResultTransformer(Transformers
                    .aliasToBean(ProyeccionesPresupuestalesDTO.class));
            @SuppressWarnings("unchecked")
            List<ProyeccionesPresupuestalesDTO> list = query.list();
            return list;
        } else {
            throw new ReglaNegocioException(
                    "No hay registros en el historico de acumulados",
                    ReglaNegocioCodigoError.SIN_REGISTRO);
        }
    }

    public void guardarProyeccion(ProyeccionesPresupuestalesDTO dto) {
        ProyeccionesPresupuestalesEntity entity = proyeccionesPresupuestalesRepository
                .obtenerPorId(dto.getIdProyeccionPartidaMensual());
        entity.setPorcentajeEnero(dto.getPorcentajeEnero());
        entity.setPorcentajeFebrero(dto.getPorcentajeFebrero());
        entity.setPorcentajeMarzo(dto.getPorcentajeMarzo());
        entity.setPorcentajeAbril(dto.getPorcentajeAbril());
        entity.setPorcentajeMayo(dto.getPorcentajeMayo());
        entity.setPorcentajeJunio(dto.getPorcentajeJunio());
        entity.setPorcentajeJulio(dto.getPorcentajeJulio());
        entity.setPorcentajeAgosto(dto.getPorcentajeAgosto());
        entity.setPorcentajeSeptiembre(dto.getPorcentajeSeptiembre());
        entity.setPorcentajeOctubre(dto.getPorcentajeOctubre());
        entity.setPorcentajeNoviembre(dto.getPorcentajeNoviembre());
        entity.setPorcentajeDiciembre(dto.getPorcentajeDiciembre());

        entity.setProyeccionEnero(dto.getProyeccionEnero());
        entity.setProyeccionFebrero(dto.getProyeccionFebrero());
        entity.setProyeccionMarzo(dto.getProyeccionMarzo());
        entity.setProyeccionAbril(dto.getProyeccionAbril());
        entity.setProyeccionMayo(dto.getProyeccionMayo());
        entity.setProyeccionJunio(dto.getProyeccionJunio());
        entity.setProyeccionJulio(dto.getProyeccionJulio());
        entity.setProyeccionAgosto(dto.getProyeccionAgosto());
        entity.setProyeccionSeptiembre(dto.getProyeccionSeptiembre());
        entity.setProyeccionOctubre(dto.getProyeccionOctubre());
        entity.setProyeccionNoviembre(dto.getProyeccionNoviembre());
        entity.setProyeccionDiciembre(dto.getProyeccionDiciembre());

        entity.setTotalActual(dto.getMontoTotal());
        entity.setTotalProyeccion(dto.getAumentoTotal());
        proyeccionesPresupuestalesRepository.actualizar(entity);
    }

    public ProyeccionesPresupuestalesDTO proyectar(
            ProyeccionesPresupuestalesDTO proyeccionPresupuestal) {
        //Total
        BigDecimal montoTotal = BigDecimal.ZERO;
        BigDecimal aumentoTotal = BigDecimal.ZERO;

        BigDecimal porcentajeEntero = (proyeccionPresupuestal
                .getPorcentajeEnero() == null ? BigDecimal.ZERO
                        : proyeccionPresupuestal.getPorcentajeEnero());
        BigDecimal porcentaje = porcentajeEntero
                .divide(BigDecimal.valueOf(100));
        BigDecimal mes = (proyeccionPresupuestal.getEnero() == null
                ? BigDecimal.ZERO : proyeccionPresupuestal.getEnero());
        BigDecimal proyeccion = (porcentaje.add(BigDecimal.ONE)).multiply(mes);
        proyeccionPresupuestal.setProyeccionEnero(proyeccion);
        montoTotal = montoTotal.add(mes);
        aumentoTotal = aumentoTotal.add(proyeccion);

        porcentajeEntero = (proyeccionPresupuestal
                .getPorcentajeFebrero() == null ? BigDecimal.ZERO
                        : proyeccionPresupuestal.getPorcentajeFebrero());
        porcentaje = porcentajeEntero.divide(BigDecimal.valueOf(100));
        mes = (proyeccionPresupuestal.getFebrero() == null ? BigDecimal.ZERO
                : proyeccionPresupuestal.getFebrero());
        proyeccion = (porcentaje.add(BigDecimal.ONE)).multiply(mes);
        proyeccionPresupuestal.setProyeccionFebrero(proyeccion);
        montoTotal = montoTotal.add(mes);
        aumentoTotal = aumentoTotal.add(proyeccion);

        porcentajeEntero = (proyeccionPresupuestal.getPorcentajeMarzo() == null
                ? BigDecimal.ZERO
                : proyeccionPresupuestal.getPorcentajeMarzo());
        porcentaje = porcentajeEntero.divide(BigDecimal.valueOf(100));
        mes = (proyeccionPresupuestal.getMarzo() == null ? BigDecimal.ZERO
                : proyeccionPresupuestal.getMarzo());
        proyeccion = (porcentaje.add(BigDecimal.ONE)).multiply(mes);
        proyeccionPresupuestal.setProyeccionMarzo(proyeccion);
        montoTotal = montoTotal.add(mes);
        aumentoTotal = aumentoTotal.add(proyeccion);

        porcentajeEntero = (proyeccionPresupuestal.getPorcentajeAbril() == null
                ? BigDecimal.ZERO
                : proyeccionPresupuestal.getPorcentajeAbril());
        porcentaje = porcentajeEntero.divide(BigDecimal.valueOf(100));
        mes = (proyeccionPresupuestal.getAbril() == null ? BigDecimal.ZERO
                : proyeccionPresupuestal.getAbril());
        proyeccion = (porcentaje.add(BigDecimal.ONE)).multiply(mes);
        proyeccionPresupuestal.setProyeccionAbril(proyeccion);
        montoTotal = montoTotal.add(mes);
        aumentoTotal = aumentoTotal.add(proyeccion);

        porcentajeEntero = (proyeccionPresupuestal.getPorcentajeMayo() == null
                ? BigDecimal.ZERO : proyeccionPresupuestal.getPorcentajeMayo());
        porcentaje = porcentajeEntero.divide(BigDecimal.valueOf(100));
        mes = (proyeccionPresupuestal.getMayo() == null ? BigDecimal.ZERO
                : proyeccionPresupuestal.getMayo());
        proyeccion = (porcentaje.add(BigDecimal.ONE)).multiply(mes);
        proyeccionPresupuestal.setProyeccionMayo(proyeccion);
        montoTotal = montoTotal.add(mes);
        aumentoTotal = aumentoTotal.add(proyeccion);

        porcentajeEntero = (proyeccionPresupuestal.getPorcentajeJunio() == null
                ? BigDecimal.ZERO
                : proyeccionPresupuestal.getPorcentajeJunio());
        porcentaje = porcentajeEntero.divide(BigDecimal.valueOf(100));
        mes = (proyeccionPresupuestal.getJunio() == null ? BigDecimal.ZERO
                : proyeccionPresupuestal.getJunio());
        proyeccion = (porcentaje.add(BigDecimal.ONE)).multiply(mes);
        proyeccionPresupuestal.setProyeccionJunio(proyeccion);
        montoTotal = montoTotal.add(mes);
        aumentoTotal = aumentoTotal.add(proyeccion);

        porcentajeEntero = (proyeccionPresupuestal.getPorcentajeJulio() == null
                ? BigDecimal.ZERO
                : proyeccionPresupuestal.getPorcentajeJulio());
        porcentaje = porcentajeEntero.divide(BigDecimal.valueOf(100));
        mes = (proyeccionPresupuestal.getJulio() == null ? BigDecimal.ZERO
                : proyeccionPresupuestal.getJulio());
        proyeccion = (porcentaje.add(BigDecimal.ONE)).multiply(mes);
        proyeccionPresupuestal.setProyeccionJulio(proyeccion);
        montoTotal = montoTotal.add(mes);
        aumentoTotal = aumentoTotal.add(proyeccion);

        porcentajeEntero = (proyeccionPresupuestal.getPorcentajeAgosto() == null
                ? BigDecimal.ZERO
                : proyeccionPresupuestal.getPorcentajeAgosto());
        porcentaje = porcentajeEntero.divide(BigDecimal.valueOf(100));
        mes = (proyeccionPresupuestal.getAgosto() == null ? BigDecimal.ZERO
                : proyeccionPresupuestal.getAgosto());
        proyeccion = (porcentaje.add(BigDecimal.ONE)).multiply(mes);
        proyeccionPresupuestal.setProyeccionAgosto(proyeccion);
        montoTotal = montoTotal.add(mes);
        aumentoTotal = aumentoTotal.add(proyeccion);

        porcentajeEntero = (proyeccionPresupuestal
                .getPorcentajeSeptiembre() == null ? BigDecimal.ZERO
                        : proyeccionPresupuestal.getPorcentajeSeptiembre());
        porcentaje = porcentajeEntero.divide(BigDecimal.valueOf(100));
        mes = (proyeccionPresupuestal.getSeptiembre() == null ? BigDecimal.ZERO
                : proyeccionPresupuestal.getSeptiembre());
        proyeccion = (porcentaje.add(BigDecimal.ONE)).multiply(mes);
        proyeccionPresupuestal.setProyeccionSeptiembre(proyeccion);
        montoTotal = montoTotal.add(mes);
        aumentoTotal = aumentoTotal.add(proyeccion);

        porcentajeEntero = (proyeccionPresupuestal
                .getPorcentajeOctubre() == null ? BigDecimal.ZERO
                        : proyeccionPresupuestal.getPorcentajeOctubre());
        porcentaje = porcentajeEntero.divide(BigDecimal.valueOf(100));
        mes = (proyeccionPresupuestal.getOctubre() == null ? BigDecimal.ZERO
                : proyeccionPresupuestal.getOctubre());
        proyeccion = (porcentaje.add(BigDecimal.ONE)).multiply(mes);
        proyeccionPresupuestal.setProyeccionOctubre(proyeccion);
        montoTotal = montoTotal.add(mes);
        aumentoTotal = aumentoTotal.add(proyeccion);

        porcentajeEntero = (proyeccionPresupuestal
                .getPorcentajeNoviembre() == null ? BigDecimal.ZERO
                        : proyeccionPresupuestal.getPorcentajeNoviembre());
        porcentaje = porcentajeEntero.divide(BigDecimal.valueOf(100));
        mes = (proyeccionPresupuestal.getNoviembre() == null ? BigDecimal.ZERO
                : proyeccionPresupuestal.getNoviembre());
        proyeccion = (porcentaje.add(BigDecimal.ONE)).multiply(mes);
        proyeccionPresupuestal.setProyeccionNoviembre(proyeccion);
        montoTotal = montoTotal.add(mes);
        aumentoTotal = aumentoTotal.add(proyeccion);

        porcentajeEntero = (proyeccionPresupuestal
                .getPorcentajeDiciembre() == null ? BigDecimal.ZERO
                        : proyeccionPresupuestal.getPorcentajeDiciembre());
        porcentaje = porcentajeEntero.divide(BigDecimal.valueOf(100));
        mes = (proyeccionPresupuestal.getDiciembre() == null ? BigDecimal.ZERO
                : proyeccionPresupuestal.getDiciembre());
        proyeccion = (porcentaje.add(BigDecimal.ONE)).multiply(mes);
        proyeccionPresupuestal.setProyeccionDiciembre(proyeccion);
        montoTotal = montoTotal.add(mes);
        aumentoTotal = aumentoTotal.add(proyeccion);

        proyeccionPresupuestal.setMontoTotal(montoTotal);
        proyeccionPresupuestal.setAumentoTotal(aumentoTotal);
        return proyeccionPresupuestal;
    }
}
