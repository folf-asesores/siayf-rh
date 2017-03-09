package mx.gob.saludtlax.rh.persistencia;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import mx.gob.oficialiatlax.rh.demo.DependenciasDTO;
import mx.gob.oficialiatlax.rh.demo.TipoDependenciasDTO;

public class TiposDependenciasRepository  extends GenericRepository<DependenciasEntity, Integer>{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3976172963151238097L;

	public List<TipoDependenciasDTO> obtenerTipoDependencias(){
		Session session = em.unwrap(Session.class);
		Query query = session.createSQLQuery("SELECT * from tipo_dependencias");
		
		query.setResultTransformer(Transformers.aliasToBean(TipoDependenciasDTO.class));
		@SuppressWarnings("unchecked")
		List<TipoDependenciasDTO> datosDto = (List<TipoDependenciasDTO>) query.list();
		return datosDto;
	}
	
	public List<DependenciasDTO> obtenerDependencias(Integer idtipo){
		Session session = em.unwrap(Session.class);
		Query query = session.createSQLQuery("SELECT * from dependencias_demo where id_tipo_dependencia=:idtipo").setParameter("idtipo", idtipo);
		
		query.setResultTransformer(Transformers.aliasToBean(DependenciasDTO.class));
		@SuppressWarnings("unchecked")
		List<DependenciasDTO> datosDto = (List<DependenciasDTO>) query.list();
		return datosDto;
	}
	
}
