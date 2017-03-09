package mx.gob.saludtlax.rh.persistencia;

import java.math.BigDecimal;

public class TarifaRetencionRepository extends GenericRepository<TarifaRetencionEntity, Integer>{

	public TarifaRetencionEntity getTarifaRetencionByDatos(BigDecimal ingresoGravable, Integer peridiocidad, Integer anio) {
		return	(TarifaRetencionEntity) super.em.createQuery("FROM TarifaRetencionEntity as tr WHERE "
				+ "( :ingresoGravable >= tr.limiteInferior AND "
				+ ":ingresoGravable <= tr.limiteSuperior) AND"
				+ "tr.periodicidad = :periodicidad AND"
				+ "tr.anio = :anio")
		.setParameter("ingresoGravable", ingresoGravable)
		.setParameter("peridiocidad", peridiocidad)
		.setParameter("anio", anio).getResultList().get(0);
		
	}

}