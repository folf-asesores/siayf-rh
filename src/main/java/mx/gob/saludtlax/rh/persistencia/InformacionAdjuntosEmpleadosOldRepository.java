/*
 * InformacionAdjuntosEmpleadosOldRepository.java
 * Creado el Sep 5, 2016 1:13:38 PM
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;
import javax.persistence.TypedQuery;
import mx.gob.saludtlax.rh.expediente.exportacion.CantidadExpedienteDTO;

/**
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
public class InformacionAdjuntosEmpleadosOldRepository extends GenericRepository<InformacionAdjuntosEmpleadosOldEntity, Integer> {
    
    private static final String CANTIDAD_DE_ADJUNTOS = 
            "select count(ia.idInformacionAdjuntoEmpleado)"
            + " from InformacionAdjuntosEmpleadosOldEntity as ia";
    
    private static final String CANTIDAD_DE_ADJUNTOS_POR_EXPEDIENTE =
            "select new mx.gob.saludtlax.rh.expediente.exportacion.CantidadExpedienteDTO(ia.expediente.idExpedienteEmpleado, count(ia.expediente.idExpedienteEmpleado))"
            + " from InformacionAdjuntosEmpleadosOldEntity as ia"
            + " group by ia.expediente.idExpedienteEmpleado";
    
    private static final String CONSULTAR_IDS_PAGINADO = 
            "select distinct info.adjunto.idAdjuntoEmpleado"
            + " from InformacionAdjuntosEmpleadosOldEntity as info"
            + " where info.adjunto is not empty"
            + " and info.adjunto.idAdjuntoEmpleado > :idAdjunto";
    
    private static final String OBTENER_POR_ID = 
            "select info "
            + "from InformacionAdjuntosEmpleadosOldEntity as info"
            + " where info.adjunto.idAdjuntoEmpleado = :idAdjunto";
    
    public long cantidadAdjuntos() {
        TypedQuery<Long> query = em.createQuery(CANTIDAD_DE_ADJUNTOS, Long.class);

        return query.getSingleResult();
    }
    
    public List<CantidadExpedienteDTO>  cantidadExpedientes() {
        TypedQuery<CantidadExpedienteDTO> query = em.createQuery(
                CANTIDAD_DE_ADJUNTOS_POR_EXPEDIENTE, CantidadExpedienteDTO.class);
        
        return query.getResultList();
    }

    public List<Integer> consultarIdsPaginado(int cantidad, int inicio) {
        TypedQuery<Integer> query = em.createQuery(
                CONSULTAR_IDS_PAGINADO, Integer.class);
        //query.setFirstResult(inicio);
        query.setParameter("idAdjunto", inicio);
        query.setMaxResults(cantidad);
        
        return query.getResultList();
    }

    public InformacionAdjuntosEmpleadosOldEntity obtenerPorIdAdjunto(Integer idAdjunto) {
        TypedQuery<InformacionAdjuntosEmpleadosOldEntity> query = em
                .createQuery(OBTENER_POR_ID, classType);
        query.setParameter("idAdjunto", idAdjunto);
        
        return query.getSingleResult();
    }
}
