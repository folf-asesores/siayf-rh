package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

public class SubfuenteFinanciamientoRepository extends GenericRepository<SubfuenteFinanciamientoEntity, Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8239258023854901908L;

	public List<SubFuenteFinanciamientoTempEntity> consultarSubfuentesFinanciamientos(Integer idFuenteFinanciamiento) {
		List<SubFuenteFinanciamientoTempEntity> subfuentes_financiamientos_temp = em
				.createQuery(
						"SELECT s FROM SubFuenteFinanciamientoTempEntity AS s WHERE s.idFuenteFinanciamiento =:idFuenteFinanciamiento",
						SubFuenteFinanciamientoTempEntity.class)
				.setParameter("idFuenteFinanciamiento", idFuenteFinanciamiento).getResultList();
		return subfuentes_financiamientos_temp;

	}

	public List<SubFuenteFinanciamientoTempEntity> consultarSubfuentes() {
		List<SubFuenteFinanciamientoTempEntity> subfuentes_financiamientos_temp = em
				.createQuery("SELECT s FROM SubFuenteFinanciamientoTempEntity AS s ",
						SubFuenteFinanciamientoTempEntity.class)
				.getResultList();
		return subfuentes_financiamientos_temp;

	}
}
