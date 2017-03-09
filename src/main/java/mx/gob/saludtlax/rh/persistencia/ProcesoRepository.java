package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

public class ProcesoRepository extends GenericRepository<ProcesoEntity, Integer> {

	// <<<<<Consultas>>>>>

	public List<ProcesoEntity> obternerListaProcesoJuridico() {
		List<ProcesoEntity> lista = em.createQuery("SELECT proceso FROM ProcesoEntity AS proceso", ProcesoEntity.class)
				.getResultList();
		return lista;
	}

}