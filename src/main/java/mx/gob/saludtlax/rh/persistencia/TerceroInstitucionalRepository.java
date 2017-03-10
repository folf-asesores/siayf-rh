/**
 * Copyright ® 2016
 */
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import mx.gob.saludtlax.rh.configuracion.terceroinstitucional.TerceroInstitucionalDTO;
import mx.gob.saludtlax.rh.nomina.movimientofijo.MovimientoNominaDTO;

/**
 * @author Eduardo Mex
 * @email lic.eduardo_mex@hotmail.com
 * @version 1.0
 * @since 24/05/2016 11:34:28
 */
public class TerceroInstitucionalRepository extends GenericRepository<TerceroInstitucionalEntity, Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2800672738749852142L;
	private static final String OBTENER_LISTA_TERCERO_INSTITUCIONAL = "FROM TerceroInstitucionalEntity AS t ORDER BY t.numero";

	/**
	 * Retorna la lista de terceros institucionales registradoe en la bd
	 * 
	 * @return lista TerceroInstitucionalEntity
	 */
	public List<TerceroInstitucionalEntity> obtenerListaTerceroInstitucional() {
		return em.createQuery(OBTENER_LISTA_TERCERO_INSTITUCIONAL, TerceroInstitucionalEntity.class).getResultList();
	}
	
	public List<TerceroInstitucionalDTO> obtenerTerceroInstitucional(String clave,String partida){
		Session session = em.unwrap(Session.class);
		System.out.println("¡¡¡ " + clave + " - " +partida);
		Query query = session.createSQLQuery("SELECT id_tercero_institucional as idTerceroInstitucional, "
				+ " numero as numero, "
				+ " nombre_empresa as nombreEmpresa,"
				+ " concepto_deduccion as conceptoDeduccion,"
				+ " contra_partida_identificadora as contrapartidaIdentificadora,"
				+ " giro as giro  from terceros_institucionales where contra_partida_identificadora=:partida and numero=:clave")
				.setParameter("clave", clave)
				.setParameter("partida", partida);
		
		query.setResultTransformer(Transformers.aliasToBean(TerceroInstitucionalDTO.class));
		@SuppressWarnings("unchecked")
		List<TerceroInstitucionalDTO> datosDto = (List<TerceroInstitucionalDTO>) query.list();
		
		System.out.println("datosss: "+ datosDto.size());
		return datosDto;
	}

}
 