
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import javax.persistence.TypedQuery;

public class IpBloqueadaRepository
        extends GenericRepository<IpBloqueadaEntity, Integer> {

    private static final long serialVersionUID = -8470678492901356963L;

    private static final String OBTENER_FECHA_EXPIRA_BLOQUEO = "from IpBloqueadaEntity as ipBloqueadaEntity"
            + " where ipBloqueadaEntity.direccionIp = :direccionIp"
            + " order by ipBloqueadaEntity.fechaExpira desc, ipBloqueadaEntity.horaExpira desc";

    public IpBloqueadaEntity obtenerIpBloqueada(String direccionIp) {
        TypedQuery<IpBloqueadaEntity> query = em.createQuery(
                OBTENER_FECHA_EXPIRA_BLOQUEO, IpBloqueadaEntity.class);
        query.setParameter("direccionIp", direccionIp);
        query.setMaxResults(1);
        List<IpBloqueadaEntity> resultList = query.getResultList();

        return resultList != null && !resultList.isEmpty() ? resultList.get(0)
                : null;
    }

    @Override
    public void eliminar(IpBloqueadaEntity entity) {
        // Eliminación no permitida.
    }

    @Override
    public void eliminarPorId(Integer key) {
        // Eliminación no permitida.
    }
}
