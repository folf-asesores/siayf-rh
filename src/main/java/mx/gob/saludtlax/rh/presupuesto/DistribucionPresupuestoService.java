package mx.gob.saludtlax.rh.presupuesto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;

public class DistribucionPresupuestoService implements Serializable{
    
    /**
     * 
     */
    private static final long serialVersionUID = 5031743578563079046L;
    
    @PersistenceContext(name = "siayfrhPU")
    private EntityManager entityManager;
    
//    public List<DistribucionPresupuestoDTO>distribucionPresupuesto(Integer anio) {
//        
//        Session session = entityManager.unwrap(Session.class);
//
//        Query query = session.createSQLQuery("CALL usp_obtener_proyecciones_presupuestales(:anio) ").setParameter("anio", anio);
//        query.setResultTransformer(Transformers.aliasToBean(DistribucionPresupuestoDTO.class));
//        @SuppressWarnings("unchecked")
//        List<DistribucionPresupuestoDTO> list = query.list();
//        
//       /* List<ProyeccionesPresupuestalesDTO> result = new ArrayList<ProyeccionesPresupuestalesDTO>();
//        for(int i = 0 ; i < 100; i++) {
//            result.add(list.get(i));
//        }*/
//        
//        return list;
//    }
//    
//    public List<DistribucionPresupuestoDTO> consultarPartidasPorTipoNombramiento(Integer tipoNombramiento) {
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
//		query.setResultTransformer(Transformers.aliasToBean(DistribucionPresupuestoDTO.class));
//		@SuppressWarnings("unchecked")
//		List<DistribucionPresupuestoDTO> list = query.list();
//		return list;
//      }else{
//          throw new ReglaNegocioException("No hay registros en el historico de acumulados",ReglaNegocioCodigoError.SIN_REGISTRO);
//      }
//	}

    public List<DistribucionPresupuestoDTO> obtenerProyeccionesMensuales(Integer anioPresupuesto,
            Integer idTipoNombramiento, Integer idDependencia) {
        Session session = entityManager.unwrap(Session.class);
        
        Query query = session.createSQLQuery(""
                + " SELECT "
                + " COUNT(id_nombramiento) "
                + " FROM distribucion_presupuestal"
                + " WHERE "
                + " id_nombramiento = :tipoNombramiento "
                + " GROUP BY id_nombramiento")
                  .setParameter("tipoNombramiento", idTipoNombramiento);
          BigInteger numeroNombramientos = (BigInteger) query.uniqueResult();
        if (numeroNombramientos != null && numeroNombramientos.compareTo(BigInteger.ZERO) == 1) {
            query = session
                    .createSQLQuery("CALL usp_distribucion_presupuestal(:tipoNombramiento, :anioPresupuesto, :dependencia) ")
                    .setParameter("anioPresupuesto", anioPresupuesto)
                    .setParameter("tipoNombramiento", idTipoNombramiento)
                    .setParameter("dependencia", idDependencia);
            query.setResultTransformer(Transformers.aliasToBean(DistribucionPresupuestoDTO.class));
            @SuppressWarnings("unchecked")
            List<DistribucionPresupuestoDTO> list = query.list();
            return list;
        } else {
          throw new ReglaNegocioException("No hay registros en el historico de acumulados",ReglaNegocioCodigoError.SIN_REGISTRO);
      }
    }

    

    public DistribucionPresupuestoDTO proyectar(DistribucionPresupuestoDTO distribucionPresupuesto) {
        //Total
        BigDecimal montoTotal = BigDecimal.ZERO;
        
        BigDecimal mes = (distribucionPresupuesto.getEnero() == null ? BigDecimal.ZERO : distribucionPresupuesto.getEnero());
        montoTotal = montoTotal.add(mes);
        
        mes = (distribucionPresupuesto.getFebrero() == null ? BigDecimal.ZERO : distribucionPresupuesto.getFebrero());
        montoTotal = montoTotal.add(mes);
        
        mes = (distribucionPresupuesto.getMarzo() == null ? BigDecimal.ZERO : distribucionPresupuesto.getMarzo());
        montoTotal = montoTotal.add(mes);
        
        mes = (distribucionPresupuesto.getAbril() == null ? BigDecimal.ZERO : distribucionPresupuesto.getAbril());
        montoTotal = montoTotal.add(mes);
        
        mes = (distribucionPresupuesto.getMayo() == null ? BigDecimal.ZERO : distribucionPresupuesto.getMayo());
        montoTotal = montoTotal.add(mes);
        
        mes = (distribucionPresupuesto.getJunio() == null ? BigDecimal.ZERO : distribucionPresupuesto.getJunio());
        montoTotal = montoTotal.add(mes);
        
        mes = (distribucionPresupuesto.getJulio() == null ? BigDecimal.ZERO : distribucionPresupuesto.getJulio());
        montoTotal = montoTotal.add(mes);
        
        mes = (distribucionPresupuesto.getAgosto() == null ? BigDecimal.ZERO : distribucionPresupuesto.getAgosto());
        montoTotal = montoTotal.add(mes);
        
        mes = (distribucionPresupuesto.getSeptiembre() == null ? BigDecimal.ZERO : distribucionPresupuesto.getSeptiembre());
        montoTotal = montoTotal.add(mes);
        
        mes = (distribucionPresupuesto.getOctubre()== null ? BigDecimal.ZERO : distribucionPresupuesto.getOctubre());
        montoTotal = montoTotal.add(mes);
        
        mes = (distribucionPresupuesto.getNoviembre() == null ? BigDecimal.ZERO : distribucionPresupuesto.getNoviembre());
        montoTotal = montoTotal.add(mes);
        
        mes = (distribucionPresupuesto.getDiciembre() == null ? BigDecimal.ZERO : distribucionPresupuesto.getDiciembre());
        montoTotal = montoTotal.add(mes);
        
        return distribucionPresupuesto;
    }
}
