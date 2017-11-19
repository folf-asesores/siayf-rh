
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

public class AccionesRepository extends GenericRepository<AccionesEntity, Integer> {

    private static final long serialVersionUID = -1926101187209405659L;

    private static final String OBTENER_ACCIONES_POR_ID_AREA = "select accion" + "  from AccionesEntity as accion" + "  join cciones.area as area"
            + " where area.idArea = :idArea";
    private static final String OBTENER_ACCIONES_POR_ID_MODULO = "select accion" + "  from AccionesEntity as accion" + "  join accion.modulo as modulo"
            + " where modulo.idModulo = :idModulo";

    public List<AccionesEntity> obtenerListaAccionesPorIdArea(Integer idArea) {
        List<AccionesEntity> list = em.createQuery(OBTENER_ACCIONES_POR_ID_AREA, AccionesEntity.class).setParameter("idArea", idArea).getResultList();
        return list;
    }

    public List<AccionesEntity> obtenerListaAccionesPorIdModulo(Integer idModulo) {
        List<AccionesEntity> list = em.createQuery(OBTENER_ACCIONES_POR_ID_MODULO, AccionesEntity.class).setParameter("idModulo", idModulo).getResultList();
        return list;
    }

}
