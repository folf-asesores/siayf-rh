
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

public class ModuloRepository extends GenericRepository<ModuloEntity, Integer> {

    private static final long serialVersionUID = -4036135115917922256L;
    private static final String OBTENER_MODULOS_POR_ID_AREA = "select modulo" + "  from ModuloEntity as modulo" + "  join modulo.area as area"
            + " where area.idArea = :idArea";

    public List<ModuloEntity> obtenerModulosPorIdArea(Integer idArea) {
        List<ModuloEntity> list = em.createQuery(OBTENER_MODULOS_POR_ID_AREA, ModuloEntity.class).setParameter("idArea", idArea).getResultList();
        return list;
    }

}
