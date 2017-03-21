/**
 * Copyright ® 2016
 */
package mx.gob.saludtlax.rh.nomina.movimientos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;
import mx.gob.saludtlax.rh.nomina.movimientofijo.MovimientoNominaDTO;
import mx.gob.saludtlax.rh.nomina.movimientosnomina.TipoMovimientoNominaDTO;
import mx.gob.saludtlax.rh.persistencia.ConceptoNominaFederalesEntity;
import mx.gob.saludtlax.rh.persistencia.ConceptoNominaFederalesRepository;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionTipoMovimientoEntity;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionTipoMovimientoNominaReporsitory;
import mx.gob.saludtlax.rh.persistencia.MovimientoFijoEntity;
import mx.gob.saludtlax.rh.persistencia.MovimientoFijoRepository;
import mx.gob.saludtlax.rh.siif.reportarcontratos.BusinessException;
import mx.gob.saludtlax.rh.util.Configuracion;
import mx.gob.saludtlax.rh.util.FechaUtil;

public class MovimientosService implements Serializable {

	private static final long serialVersionUID = -2736791045459854674L;

		@PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
	private EntityManager entityManager;
	@Inject
	MovimientoFijoRepository movimientoFijoRepository;

	@Inject
	ConfiguracionTipoMovimientoNominaReporsitory configuracionMovimiento;

	@Inject
	ConceptoNominaFederalesRepository conceptoNominaFederalesRepository;

	public TipoMovimientoDTO obtenerTipoMovimientoPorClave(String clave) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("SELECT " + "id_tipo_movimiento_nomina AS idMovimientoNomina, "
				+ " clave, " + " descripcion, " + " forma_registro AS formaRegistro, "
				+ "es_movimiento as esMovimiento," + "id_padre as idPadre " + " FROM tipos_movimientos_nomina"
				+ " WHERE " + " tipos_movimientos_nomina.clave=:clave ").setParameter("clave", clave);
		query.setResultTransformer(Transformers.aliasToBean(TipoMovimientoDTO.class));
		@SuppressWarnings("unchecked")
		List<TipoMovimientoDTO> result = (List<TipoMovimientoDTO>) query.list();
		if (!result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}

	public List<TipoMovimientoDTO> getMovimientosLista() {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("SELECT " + "id_tipo_movimiento_nomina AS idMovimientoNomina, "
				+ " clave, " + " descripcion, " + " forma_registro AS formaRegistro, "
				+ "es_movimiento as esMovimiento," + "id_padre as idPadre " + " FROM tipos_movimientos_nomina"
				+ " WHERE " + " id_padre IS NULL AND tipos_movimientos_nomina.forma_registro <> 0 ");
		query.setResultTransformer(Transformers.aliasToBean(TipoMovimientoDTO.class));
		@SuppressWarnings("unchecked")
		List<TipoMovimientoDTO> result = (List<TipoMovimientoDTO>) query.list();
		for (TipoMovimientoDTO movimientoDTO : result) {
			query = session
					.createSQLQuery(" SELECT " + " id_tipo_movimiento_nomina AS idMovimientoNomina, " + " clave, "
							+ " descripcion, " + " forma_registro AS formaRegistro, "
							+ " es_movimiento as esMovimiento," + " id_padre as idPadre "
							+ " FROM tipos_movimientos_nomina" + " WHERE " + " id_padre = :idPadre " + "  ")
					.setParameter("idPadre", movimientoDTO.getIdMovimientoNomina());
			// System.out.println("movimientoDTO.getIdMovimientoNomina():: " +
			// movimientoDTO.getIdMovimientoNomina());
			query.setResultTransformer(Transformers.aliasToBean(TipoMovimientoDTO.class));
			List<TipoMovimientoDTO> resultTem = new ArrayList<>();
			@SuppressWarnings("unchecked")
			List<TipoMovimientoDTO> resultSub = (List<TipoMovimientoDTO>) query.list();
			resultTem.addAll(resultSub);
			// System.out.println("resultTem:: " + resultTem);
			movimientoDTO.setMovimientosLista(resultTem);
		}
		// System.out.println("result:: " + result);
		return result;
	}

	public List<MovimientoNominaDTO> obtenerMovimientosPorEmpleado(InfoEmpleadoDTO empleadoSeleccionado,
			TipoMovimientoNominaDTO movimientoSeleccionado) {
		try {
			return movimientoFijoRepository.obtenerMovimientosFijosPorEmpleadoytipoMov(
					empleadoSeleccionado.getIdEmpleado(), movimientoSeleccionado);
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<MovimientoNominaDTO> obtenerMovimientosPorEmpleado(Integer empleado, Integer movimiento) {
		try {
			return movimientoFijoRepository.obtenerMovimiento(empleado, movimiento);
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<ConceptoNominaFederalesEntity> obtenerMovimiento30PorEmpleado(Integer empleado) {

		TipoMovimientoDTO mov = obtenerTipoMovimientoPorClave("7101");
		
		List<MovimientoNominaDTO> movs30AR = movimientoFijoRepository.obtenerMovimiento(empleado,
				mov.getIdMovimientoNomina());

		List<ConfiguracionTipoMovimientoEntity> configuracionMov = null;

		if (!movs30AR.isEmpty()) {

			configuracionMov = configuracionMovimiento
					.obenerConceptosPorTipoMovimiento(movs30AR.get(0).getIdTipoMovimiento());
			
			List<ConceptoNominaFederalesEntity> conceptosMov = new ArrayList<>();

			if (!configuracionMov.isEmpty()) {
				for (ConfiguracionTipoMovimientoEntity configMov : configuracionMov) {
					conceptosMov.add(configMov.getConceptoNomina());
				}
			}
			return conceptosMov;
		}
		mov = obtenerTipoMovimientoPorClave("7103");
		List<MovimientoNominaDTO> movs30MR = movimientoFijoRepository.obtenerMovimiento(empleado,
				mov.getIdMovimientoNomina());

		if (!movs30MR.isEmpty()) {
			configuracionMov = configuracionMovimiento
					.obenerConceptosPorTipoMovimiento(movs30MR.get(0).getIdTipoMovimiento());

			List<ConceptoNominaFederalesEntity> conceptosMov = new ArrayList<>();

			if (!configuracionMov.isEmpty()) {
				for (ConfiguracionTipoMovimientoEntity configMov : configuracionMov) {
					conceptosMov.add(configMov.getConceptoNomina());
				}
			}
			return conceptosMov;

		}
		mov = obtenerTipoMovimientoPorClave("7105");
		List<MovimientoNominaDTO> movs30BR = movimientoFijoRepository.obtenerMovimiento(empleado,
				mov.getIdMovimientoNomina());

		if (!movs30BR.isEmpty()) {
			configuracionMov = configuracionMovimiento
					.obenerConceptosPorTipoMovimiento(movs30BR.get(0).getIdTipoMovimiento());

			List<ConceptoNominaFederalesEntity> conceptosMov = new ArrayList<>();

			if (!configuracionMov.isEmpty()) {
				for (ConfiguracionTipoMovimientoEntity configMov : configuracionMov) {
					conceptosMov.add(configMov.getConceptoNomina());
				}
			}
			return conceptosMov;
		}

		return null;

	}
	
	public List<ConceptoNominaFederalesEntity> obtenerconceptosQuinquenios(Integer idEmpleado){
		return null;
	}

	public List<MovimientoNominaDTO> obtenerMovimientosTercerosPorEmpleado(InfoEmpleadoDTO empleadoSeleccionado) {
		try {
			return movimientoFijoRepository.obtenerMovimientosTercerosPorEmpleado(empleadoSeleccionado.getIdEmpleado());
		} catch (NoResultException e) {
			return null;
		}
	}

	public void eliminar(MovimientoNominaDTO dto) {
		movimientoFijoRepository.eliminar(movimientoFijoRepository.obtenerPorId(dto.getIdMovimientoFijo()));
	}

	public void editar(MovimientoNominaDTO dto) {

		try {
			MovimientoFijoEntity newMovimiento = movimientoFijoRepository.obtenerPorId(dto.getIdMovimientoFijo());
			newMovimiento.setImporteDescontado(dto.getImporteDescontado());
			newMovimiento.setQuincenaFinal(dto.getQuincenaFinal());
			newMovimiento.setAnioFinal(dto.getAnioFinal());
			newMovimiento.setFolioDocumento(dto.getFolio());
			newMovimiento.setFechaDocumento(dto.getFechaDocumento());
			newMovimiento.setIdTipoMovimiento(dto.getIdTipoMovimiento());
			newMovimiento.setDias(dto.getDias());
			newMovimiento.setFechaModificacion(FechaUtil.fechaActual());
			newMovimiento.setEstatus(dto.getEstatus());
			newMovimiento.setClave(dto.getClave());
			// newMovimiento.setTerceroInstitucional(dto.getIdTerceroInstitucional()!=null?terceroInstitucionalRepository.obtenerPorId(dto.getIdTerceroInstitucional()):null);
			// newMovimiento.setIdTipoMovimiento(dto.getIdTipoMovimiento());
			movimientoFijoRepository.actualizar(newMovimiento);
		} catch (PersistenceException e) {
			throw new BusinessException();
		}
	}
}