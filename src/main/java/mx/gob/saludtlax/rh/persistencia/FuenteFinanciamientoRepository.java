package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

public class FuenteFinanciamientoRepository extends
		GenericRepository<FuenteFinanciamientoEntity, Integer> {
	
	public List<FuenteFinanciamientoEntity> consultarFuenteFinanciamiento() {
		List<FuenteFinanciamientoEntity> fuentes_financiamientos = em.createQuery(
				"select f from FuenteFinanciamientoEntity AS f",
				FuenteFinanciamientoEntity.class).getResultList();
		return fuentes_financiamientos;
	}
}
