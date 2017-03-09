package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

public class ProcesoJuridicosRepository extends
		GenericRepository<ProcesoJuridicosEntity, Integer> {
	
//	<<<<<Consultas>>>>>
	
	 public List<ProcesoJuridicosEntity> obternerListaProceso() {
	        List<ProcesoJuridicosEntity> lista = em.createQuery(
	        		"from ProcesoJuridicosEntity AS proceso", ProcesoJuridicosEntity.class)
	                .getResultList();
	        return lista;
	    }

}