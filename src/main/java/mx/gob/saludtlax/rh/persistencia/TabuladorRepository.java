/**
 * Copyright © 2016
 */
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.NoResultException;

/**
 * @author Eduardo Mex
 * @email lic.eduardo_mex@hotmail.com
 * @version 1.0
 * @since 28/07/2016 13:09:03
 */
public class TabuladorRepository extends GenericRepository<TabuladorEntity, Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3240856679538431329L;

	public List<TabuladorEntity> consultarTabuladoresPorTipo(Integer idTipoTabulador) {
		return em.createQuery(
				"SELECT t FROM TabuladorEntity as t WHERE t.tipoTabulador.idTipoTabulador =:idTipoTabulador ORDER BY t.puestoGeneral.codigo ASC",
				TabuladorEntity.class).setParameter("idTipoTabulador", idTipoTabulador).getResultList();
	}

	public BigDecimal obtenerSueldoDiarioIdTabulador(Integer idTabulador) {
		return em.createQuery("SELECT t.sueldoDiario FROM TabuladorEntity as t WHERE t.idTabulador =:idTabulador",
				BigDecimal.class).setParameter("idTabulador", idTabulador).getSingleResult();
	}

	public List<TabuladorEntity> consultarTabuladores() {
		return em.createQuery("SELECT t FROM TabuladorEntity as t", TabuladorEntity.class).getResultList();
	}

	public TabuladorEntity obtenerTabuladorPuestoTipoTabulador(Integer idPuesto, Integer tipoTabulador) {
		try {
			return em
					.createQuery(
							"SELECT t FROM TabuladorEntity AS t WHERE t.puestoGeneral.idPuestoGeneral =:idPuesto AND t.tipoTabulador.idTipoTabulador =:tipoTabulador",
							TabuladorEntity.class)
					.setParameter("idPuesto", idPuesto).setParameter("tipoTabulador", tipoTabulador).getSingleResult();
		} catch (NoResultException exception) {
			return null;
		}
	}

	public TabuladorEntity obtenerSueldoActualPorPuestoTipoTabulador(Integer idPuestoGeneral, Integer tipoTabulador) {
		// TODO modificar el 3 por el ejercicio
		try {
			return em
					.createQuery(
							"SELECT t FROM TabuladorEntity AS t WHERE t.puestoGeneral.idPuestoGeneral =:idPuesto "
									+ "AND t.tipoTabulador.idTipoTabulador =:tipoTabulador AND t.ejercicioFiscal =:ejercicioActual",
							TabuladorEntity.class)
					.setParameter("idPuesto", idPuestoGeneral).setParameter("tipoTabulador", tipoTabulador)
					.setParameter("ejercicioActual", 2016).getSingleResult();
		} catch (NoResultException exception) {
			return null;
		}

	}

	public TabuladorEntity obtenerTabuladorPorPuesto(Integer idPuestoGeneral, Integer tipoTabulador,
			Integer anioFiscal) {
		// TODO modificar el 3 por el ejercicio
		try {
			return em
					.createQuery(
							"SELECT t FROM TabuladorEntity AS t WHERE t.puestoGeneral.idPuestoGeneral =:idPuesto "
									+ "AND t.tipoTabulador.idTipoTabulador =:tipoTabulador AND t.ejercicioFiscal =:ejercicioActual ",
							TabuladorEntity.class)
					.setParameter("idPuesto", idPuestoGeneral).setParameter("tipoTabulador", tipoTabulador)
					.setParameter("ejercicioActual", anioFiscal).setMaxResults(1).getSingleResult();
		} catch (NoResultException exception) {
			return null;
		}
	}

	public TabuladorEntity obtenerTabuladorActualPorPuesto(Integer idPuestoGeneral, Integer tipoTabulador) {

		// TODO modificar el 3 por el ejercicio
		try {
			return em
					.createQuery("SELECT t FROM TabuladorEntity AS t WHERE t.puestoGeneral.idPuestoGeneral =:idPuesto "
							+ "AND t.tipoTabulador.idTipoTabulador =:tipoTabulador", TabuladorEntity.class)
					.setParameter("idPuesto", idPuestoGeneral).setParameter("tipoTabulador", tipoTabulador)
					.setMaxResults(1).getSingleResult();
		} catch (NoResultException exception) {
			return null;
		}

	}

}
