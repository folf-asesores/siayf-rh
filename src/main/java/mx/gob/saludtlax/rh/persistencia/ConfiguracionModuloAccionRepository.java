package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

public class ConfiguracionModuloAccionRepository extends GenericRepository<ConfiguracionModuloAccionEntity, Integer>{

	private static final long serialVersionUID = 4736774820048524697L;
        private static final String OBTENER_REGISTROS_POR_MODULO
                = "select c"
                + "  from ConfiguracionModuloAccionEntity as c"
                + "  join c.modulo as modulo"
                + " where modulo.idModulo = :idModulo";

	public List<ConfiguracionModuloAccionEntity> obtenerRegistrosPorModulo(Integer idModulo){
		List<ConfiguracionModuloAccionEntity> list =
                        em.createQuery(OBTENER_REGISTROS_POR_MODULO, ConfiguracionModuloAccionEntity.class)
                                .setParameter("idModulo",idModulo)
                                .getResultList();
		return list;
	}

}
