/*
 * DispersionService.java
 * Creado el 07/Dec/2016 8:10:11 PM
 *
 */
package mx.gob.saludtlax.rh.nomina.reportes.dispersion;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import mx.gob.saludtlax.rh.util.Configuracion;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class DispersionService implements Serializable {

	private static final long serialVersionUID = 7738321309668550989L;
	private static final String USP_REPORTE_NOMINA_DISPERCION = "CALL usp_reporte_nomina_dispersion(:idPagoNomina)";
	private static final String OBTENER_ID_PAGO_NOMINA = ""
			+ " SELECT n.id_pago_nomina                                 "
			+ " FROM nomina_empleado AS n WHERE                         "
			+ " n.id_producto_nomina = :idProductoNomina                "
			+ " GROUP BY n.id_pago_nomina                               ";
	@PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
	private EntityManager em;

	@SuppressWarnings("unchecked")
	protected List<Integer> obtenerIdPagoNominaList(Integer idProductoNomina) {
		Session sesion = em.unwrap(Session.class);
		Query query = sesion.createSQLQuery(OBTENER_ID_PAGO_NOMINA).setParameter("idProductoNomina", idProductoNomina);
		List<Integer> idPagoNominaList = (List<Integer>) query.list();
		return idPagoNominaList == null ? Collections.EMPTY_LIST : idPagoNominaList;
	}

	@SuppressWarnings("unchecked")
	protected List<DispersionDTO> obtenerInformacion(Integer idPagoNomina) {
		Session sesion = em.unwrap(Session.class);
		Query query = sesion.createSQLQuery(USP_REPORTE_NOMINA_DISPERCION).setParameter("idPagoNomina", idPagoNomina)
				.setResultTransformer(Transformers.aliasToBean(DispersionDTO.class));
		List<DispersionDTO> dispersion = (List<DispersionDTO>) query.list();
		return dispersion == null ? Collections.EMPTY_LIST : dispersion;
	}
}
