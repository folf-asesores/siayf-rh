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

public class DistribucionPresupuestoService implements Serializable{
    
    /**
     * 
     */
    private static final long serialVersionUID = 5031743578563079046L;
    
    @PersistenceContext(name = "siayfrhPU")
    private EntityManager entityManager;
    @Inject private ProyeccionesPresupuestalesRepository proyeccionesPresupuestalesRepository;
    
    public List<DistribucionPresupuestoDTO>distribucionPresupuesto(Integer anio) {
        
        Session session = entityManager.unwrap(Session.class);

        Query query = session.createSQLQuery("CALL usp_obtener_proyecciones_presupuestales(:anio) ").setParameter("anio", anio);
        query.setResultTransformer(Transformers.aliasToBean(DistribucionPresupuestoDTO.class));
        @SuppressWarnings("unchecked")
        List<DistribucionPresupuestoDTO> list = query.list();
        
       /* List<ProyeccionesPresupuestalesDTO> result = new ArrayList<ProyeccionesPresupuestalesDTO>();
        for(int i = 0 ; i < 100; i++) {
            result.add(list.get(i));
        }*/
        
        return list;
    }
    
    public List<DistribucionPresupuestoDTO> consultarPartidasPorTipoNombramiento(Integer tipoNombramiento) {
		Session session = entityManager.unwrap(Session.class);
		
		Query query = session.createSQLQuery(""
                + " SELECT "
                + " COUNT(id_nombramiento) "
                + " FROM acumulado_partida "
                + " WHERE "
                + " id_nombramiento = :tipoNombramiento "
                + " GROUP BY id_nombramiento")
                  .setParameter("tipoNombramiento", tipoNombramiento);
          BigInteger numeroNombramientos = (BigInteger) query.uniqueResult();
      if (numeroNombramientos!= null && numeroNombramientos.compareTo(BigInteger.ZERO) == 1) {
		 query = session.createSQLQuery("CALL usp_obtener_partidas_por_tipo_nombramiento(:tipoNombramiento) ")
				.setParameter("tipoNombramiento", tipoNombramiento);
		query.setResultTransformer(Transformers.aliasToBean(DistribucionPresupuestoDTO.class));
		@SuppressWarnings("unchecked")
		List<DistribucionPresupuestoDTO> list = query.list();
		return list;
      }else{
          throw new ReglaNegocioException("No hay registros en el historico de acumulados",ReglaNegocioCodigoError.SIN_REGISTRO);
      }
	}

    public List<DistribucionPresupuestoDTO> obtenerProyeccionesMensuales(Integer anioPresupuesto,
            Integer idTipoNombramiento) {
        Session session = entityManager.unwrap(Session.class);
        
        Query query = session.createSQLQuery(""
                + " SELECT "
                + " COUNT(id_nombramiento) "
                + " FROM acumulado_partida "
                + " WHERE "
                + " id_nombramiento = :tipoNombramiento "
                + " GROUP BY id_nombramiento")
                  .setParameter("tipoNombramiento", idTipoNombramiento);
          BigInteger numeroNombramientos = (BigInteger) query.uniqueResult();
        if (numeroNombramientos != null && numeroNombramientos.compareTo(BigInteger.ZERO) == 1) {
            query = session
                    .createSQLQuery("CALL usp_obtener_proyecciones_mensuales(:anioPresupuesto, :tipoNombramiento) ")
                    .setParameter("anioPresupuesto", anioPresupuesto)
                    .setParameter("tipoNombramiento", idTipoNombramiento);
            query.setResultTransformer(Transformers.aliasToBean(DistribucionPresupuestoDTO.class));
            @SuppressWarnings("unchecked")
            List<DistribucionPresupuestoDTO> list = query.list();
            return list;
        } else {
          throw new ReglaNegocioException("No hay registros en el historico de acumulados",ReglaNegocioCodigoError.SIN_REGISTRO);
      }
    }

    public void guardarDistribucion(DistribucionPresupuestoDTO dto) {
        ProyeccionesPresupuestalesEntity entity = proyeccionesPresupuestalesRepository.obtenerPorId(dto.getIdProyeccionPartidaMensual());
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

    public DistribucionPresupuestoDTO proyectar(DistribucionPresupuestoDTO distribucionPresupuesto) {
        //Total
        BigDecimal montoTotal = BigDecimal.ZERO;
        BigDecimal aumentoTotal = BigDecimal.ZERO;
        
        BigDecimal porcentajeEntero = (distribucionPresupuesto.getPorcentajeEnero() == null ? BigDecimal.ZERO : distribucionPresupuesto.getPorcentajeEnero());
        BigDecimal porcentaje = porcentajeEntero.divide(BigDecimal.valueOf(100));
        BigDecimal mes = (distribucionPresupuesto.getEnero() == null ? BigDecimal.ZERO : distribucionPresupuesto.getEnero());
        BigDecimal proyeccion = (porcentaje.add(BigDecimal.ONE)).multiply(mes);
        distribucionPresupuesto.setProyeccionEnero(proyeccion);
        montoTotal = montoTotal.add(mes);
        aumentoTotal = aumentoTotal.add(proyeccion);
        
        porcentajeEntero = (distribucionPresupuesto.getPorcentajeFebrero() == null ? BigDecimal.ZERO : distribucionPresupuesto.getPorcentajeFebrero());
        porcentaje = porcentajeEntero.divide(BigDecimal.valueOf(100));
        mes = (distribucionPresupuesto.getFebrero() == null ? BigDecimal.ZERO : distribucionPresupuesto.getFebrero());
        proyeccion = (porcentaje.add(BigDecimal.ONE)).multiply(mes);
        distribucionPresupuesto.setProyeccionFebrero(proyeccion);
        montoTotal = montoTotal.add(mes);
        aumentoTotal = aumentoTotal.add(proyeccion);
        
        porcentajeEntero = (distribucionPresupuesto.getPorcentajeMarzo() == null ? BigDecimal.ZERO : distribucionPresupuesto.getPorcentajeMarzo());
        porcentaje = porcentajeEntero.divide(BigDecimal.valueOf(100));
        mes = (distribucionPresupuesto.getMarzo() == null ? BigDecimal.ZERO : distribucionPresupuesto.getMarzo());
        proyeccion = (porcentaje.add(BigDecimal.ONE)).multiply(mes);
        distribucionPresupuesto.setProyeccionMarzo(proyeccion);
        montoTotal = montoTotal.add(mes);
        aumentoTotal = aumentoTotal.add(proyeccion);
        
        porcentajeEntero = (distribucionPresupuesto.getPorcentajeAbril() == null ? BigDecimal.ZERO : distribucionPresupuesto.getPorcentajeAbril());
        porcentaje = porcentajeEntero.divide(BigDecimal.valueOf(100));
        mes = (distribucionPresupuesto.getAbril() == null ? BigDecimal.ZERO : distribucionPresupuesto.getAbril());
        proyeccion = (porcentaje.add(BigDecimal.ONE)).multiply(mes);
        distribucionPresupuesto.setProyeccionAbril(proyeccion);
        montoTotal = montoTotal.add(mes);
        aumentoTotal = aumentoTotal.add(proyeccion);
        
        porcentajeEntero = (distribucionPresupuesto.getPorcentajeMayo() == null ? BigDecimal.ZERO : distribucionPresupuesto.getPorcentajeMayo());
        porcentaje = porcentajeEntero.divide(BigDecimal.valueOf(100));
        mes = (distribucionPresupuesto.getMayo() == null ? BigDecimal.ZERO : distribucionPresupuesto.getMayo());
        proyeccion = (porcentaje.add(BigDecimal.ONE)).multiply(mes);
        distribucionPresupuesto.setProyeccionMayo(proyeccion);
        montoTotal = montoTotal.add(mes);
        aumentoTotal = aumentoTotal.add(proyeccion);
        
        porcentajeEntero = (distribucionPresupuesto.getPorcentajeJunio() == null ? BigDecimal.ZERO : distribucionPresupuesto.getPorcentajeJunio());
        porcentaje = porcentajeEntero.divide(BigDecimal.valueOf(100));
        mes = (distribucionPresupuesto.getJunio() == null ? BigDecimal.ZERO : distribucionPresupuesto.getJunio());
        proyeccion = (porcentaje.add(BigDecimal.ONE)).multiply(mes);
        distribucionPresupuesto.setProyeccionJunio(proyeccion);
        montoTotal = montoTotal.add(mes);
        aumentoTotal = aumentoTotal.add(proyeccion);
        
        porcentajeEntero = (distribucionPresupuesto.getPorcentajeJulio() == null ? BigDecimal.ZERO : distribucionPresupuesto.getPorcentajeJulio());
        porcentaje = porcentajeEntero.divide(BigDecimal.valueOf(100));
        mes = (distribucionPresupuesto.getJulio() == null ? BigDecimal.ZERO : distribucionPresupuesto.getJulio());
        proyeccion = (porcentaje.add(BigDecimal.ONE)).multiply(mes);
        distribucionPresupuesto.setProyeccionJulio(proyeccion);
        montoTotal = montoTotal.add(mes);
        aumentoTotal = aumentoTotal.add(proyeccion);
        
        porcentajeEntero = (distribucionPresupuesto.getPorcentajeAgosto() == null ? BigDecimal.ZERO : distribucionPresupuesto.getPorcentajeAgosto());
        porcentaje = porcentajeEntero.divide(BigDecimal.valueOf(100));
        mes = (distribucionPresupuesto.getAgosto() == null ? BigDecimal.ZERO : distribucionPresupuesto.getAgosto());
        proyeccion = (porcentaje.add(BigDecimal.ONE)).multiply(mes);
        distribucionPresupuesto.setProyeccionAgosto(proyeccion);
        montoTotal = montoTotal.add(mes);
        aumentoTotal = aumentoTotal.add(proyeccion);
        
        porcentajeEntero = (distribucionPresupuesto.getPorcentajeSeptiembre() == null ? BigDecimal.ZERO : distribucionPresupuesto.getPorcentajeSeptiembre());
        porcentaje = porcentajeEntero.divide(BigDecimal.valueOf(100));
        mes = (distribucionPresupuesto.getSeptiembre() == null ? BigDecimal.ZERO : distribucionPresupuesto.getSeptiembre());
        proyeccion = (porcentaje.add(BigDecimal.ONE)).multiply(mes);
        distribucionPresupuesto.setProyeccionSeptiembre(proyeccion);
        montoTotal = montoTotal.add(mes);
        aumentoTotal = aumentoTotal.add(proyeccion);
        
        porcentajeEntero = (distribucionPresupuesto.getPorcentajeOctubre() == null ? BigDecimal.ZERO : distribucionPresupuesto.getPorcentajeOctubre());
        porcentaje = porcentajeEntero.divide(BigDecimal.valueOf(100));
        mes = (distribucionPresupuesto.getOctubre()== null ? BigDecimal.ZERO : distribucionPresupuesto.getOctubre());
        proyeccion = (porcentaje.add(BigDecimal.ONE)).multiply(mes);
        distribucionPresupuesto.setProyeccionOctubre(proyeccion);
        montoTotal = montoTotal.add(mes);
        aumentoTotal = aumentoTotal.add(proyeccion);
        
        porcentajeEntero = (distribucionPresupuesto.getPorcentajeNoviembre() == null ? BigDecimal.ZERO : distribucionPresupuesto.getPorcentajeNoviembre());
        porcentaje = porcentajeEntero.divide(BigDecimal.valueOf(100));
        mes = (distribucionPresupuesto.getNoviembre() == null ? BigDecimal.ZERO : distribucionPresupuesto.getNoviembre());
        proyeccion = (porcentaje.add(BigDecimal.ONE)).multiply(mes);
        distribucionPresupuesto.setProyeccionNoviembre(proyeccion);
        montoTotal = montoTotal.add(mes);
        aumentoTotal = aumentoTotal.add(proyeccion);
        
        porcentajeEntero = (distribucionPresupuesto.getPorcentajeDiciembre() == null ? BigDecimal.ZERO : distribucionPresupuesto.getPorcentajeDiciembre());
        porcentaje = porcentajeEntero.divide(BigDecimal.valueOf(100));
        mes = (distribucionPresupuesto.getDiciembre() == null ? BigDecimal.ZERO : distribucionPresupuesto.getDiciembre());
        proyeccion = (porcentaje.add(BigDecimal.ONE)).multiply(mes);
        distribucionPresupuesto.setProyeccionDiciembre(proyeccion);
        montoTotal = montoTotal.add(mes);
        aumentoTotal = aumentoTotal.add(proyeccion);
        
        distribucionPresupuesto.setMontoTotal(montoTotal);
        distribucionPresupuesto.setAumentoTotal(aumentoTotal);
        return distribucionPresupuesto;
    }
}
