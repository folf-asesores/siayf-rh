/**
 * 
 */
package mx.gob.saludtlax.rh.empleados.suplencia.relacionpersonal;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import mx.gob.saludtlax.rh.util.Configuracion;



/**
 * @author Eduardo Mex
 *
 */
public class RelacionPersonalSuplenteService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7328852634210232010L;

		@PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
	private EntityManager entityManager;

	protected List<RelacionPersonalSuplenteDTO> obtenerListaRelacionPersonalSuplente(Integer numeroQuincena,
			Integer ejercicioFiscal, Integer idCentroResponsabilidad) {

		Session session = entityManager.unwrap(Session.class);

		Query query = session
				.createSQLQuery(
						"CALL usp_relaciones_personales_suplentes(:numeroQuincena, :ejercicioFiscal, :idCentroResponsabilidad) ")
				.setParameter("numeroQuincena", numeroQuincena).setParameter("ejercicioFiscal", ejercicioFiscal)
				.setParameter("idCentroResponsabilidad", idCentroResponsabilidad);

		query.setResultTransformer(Transformers.aliasToBean(RelacionPersonalSuplenteDTO.class));

		@SuppressWarnings("unchecked")
		List<RelacionPersonalSuplenteDTO> list = query.list();

		return list;

	}

}
