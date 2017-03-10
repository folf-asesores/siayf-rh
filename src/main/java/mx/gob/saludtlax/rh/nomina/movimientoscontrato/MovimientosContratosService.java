package mx.gob.saludtlax.rh.nomina.movimientoscontrato;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import mx.gob.saludtlax.rh.nomina.productosnomina.FaltaContadaDTO;
import mx.gob.saludtlax.rh.persistencia.ConceptoNominaContratosEntity;
import mx.gob.saludtlax.rh.persistencia.ConceptoNominaContratosRepository;
import mx.gob.saludtlax.rh.persistencia.DetalleMovimientosContratosRepository;
import mx.gob.saludtlax.rh.persistencia.DetalleMovimientosCotratosEntity;
import mx.gob.saludtlax.rh.persistencia.EmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.EmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.FaltaContadaEntity;
import mx.gob.saludtlax.rh.persistencia.FaltaContadaRepository;
import mx.gob.saludtlax.rh.persistencia.MovimientosNominaContratosEntity;
import mx.gob.saludtlax.rh.persistencia.MovimientosNominaContratosRepository;
import mx.gob.saludtlax.rh.persistencia.NominaEmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.NominaEmpleadoRepository;
import mx.gob.saludtlax.rh.util.FechaUtil;

/**
 * 
 * @author José Pablo
 *
 */
public class MovimientosContratosService {
	@Inject private MovimientosNominaContratosRepository movimientosNominaContratosRepository;
	@Inject private DetalleMovimientosContratosRepository detalleMovimientosContratosRepository;
	@Inject private FaltaContadaRepository faltaContadaRepository;
	@Inject private EmpleadoRepository empleadoRepository;
	@Inject private ConceptoNominaContratosRepository conceptoNominaContratosRepository;
	@Inject private NominaEmpleadoRepository nominaEmpleadoRepository;
	@PersistenceContext(name = "siayfrhPU")
	private EntityManager entityManager;

	public boolean esTipoDeContratos(Integer idEmpleado) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery(" SELECT " 
				+ " tc.area_responsable " 
				+ " FROM tipos_contratacion AS tc "
				+ " INNER JOIN configuraciones_presupuestales_empleados AS cpe "
				+ " ON cpe.id_tipo_contratacion = tc.id_tipo_contratacion " 
				+ " WHERE "
				+ " cpe.id_empleado = :idEmpleado ").setParameter("idEmpleado", idEmpleado);
		Integer areaResponsable = (Integer) query.uniqueResult();
		return areaResponsable == 2;
	}

	public List<MovimientoContratosDTO> obtenerMovimientosNominaEmpleadoLista(Integer idEmpleadoSeleccionado) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("CALL usp_movimientos_contratos_empleado(:idEmpleado)")
				.setParameter("idEmpleado", idEmpleadoSeleccionado);
		query.setResultTransformer(Transformers.aliasToBean(MovimientoContratosDTO.class));
		@SuppressWarnings("unchecked")
		List<MovimientoContratosDTO> result = (List<MovimientoContratosDTO>) query.list();
		List<DetalleMovimientoContratoDTO> listaDetalles = new ArrayList<>();
		List<MovimientoContratosDTO> resultado = new ArrayList<>();
		if (!result.isEmpty()) {
			for (MovimientoContratosDTO mov : result) {
				List<DetalleMovimientosCotratosEntity> listEntitys = new ArrayList<>();
				listEntitys = entityManager
						.createQuery(
								"Select d From DetalleMovimientosCotratosEntity AS d WHERE d.idMovimientoContrato=:idMovimiento",
								DetalleMovimientosCotratosEntity.class)
						.setParameter("idMovimiento", mov.getIdMovimientoContratos()).getResultList();
				if (!listEntitys.isEmpty()) {
					listaDetalles = new ArrayList<>();
					for (DetalleMovimientosCotratosEntity detalleEnty : listEntitys) {
						DetalleMovimientoContratoDTO dto = new DetalleMovimientoContratoDTO();
						dto.setAbono(detalleEnty.getAbono());
						dto.setDescuento(detalleEnty.getDescuento());
						dto.setFechaRegistro(detalleEnty.getFechaRegistro());
						dto.setIdMovimientoContrato(detalleEnty.getIdMovimientoContrato());
						dto.setIdDetalleMovimiento(detalleEnty.getIdDetalleMovimiento());
						dto.setMonto(detalleEnty.getMonto());
						dto.setNumeroPago(detalleEnty.getNumeroPago());
						dto.setSaldo(detalleEnty.getSaldo());
						listaDetalles.add(dto);
					}
					mov.setListaDetalles(listaDetalles);
				}
				resultado.add(mov);
			}
		}
		return resultado;
	}

	public MovimientosNominaContratosEntity crear(MovimientoContratosDTO dto) {
		try {
			MovimientosNominaContratosEntity entity = new MovimientosNominaContratosEntity();
			ConceptoNominaContratosEntity conceptoNominaContrato = conceptoNominaContratosRepository.obtenerPorId(dto.getIdConceptoContratos());
			entity.setConceptoNominaContrato(conceptoNominaContrato);
			entity.setIdEmpleado(dto.getIdEmpleado());
			entity.setFechaRegistro(new Date());
			entity.setFechaModificacion(dto.getFechaModificacion());
			entity.setRfc(dto.getRfc());
			entity.setFechaDocumento(dto.getFechaDocumento());
			entity.setFolioDocumento(dto.getFolioDocumento());
			entity.setMonto(dto.getMonto());
			entity.setAbonado(dto.getAbonado());
			entity.setSaldo(dto.getSaldo());
			entity.setDescuento(dto.getDescuento());
			entity.setNumeroPagos(dto.getNumeroAbonos());
			entity.setAnioInicial(dto.getAnioInicial());
			entity.setPeriodoInicial(dto.getPeriodoInicial());
			entity.setAnioFinal(dto.getAnioFinal());
			entity.setPeriodoFinal(dto.getPeriodoFinal());
			entity.setAnyoOperacion(FechaUtil.ejercicioActual());
			entity.setDescuento(dto.getDescuento());
			entity.setDias(dto.getDias());
			entity.setIdEstatus(dto.getIdEstatus());
			entity.setIdTipoMovimiento(dto.getIdTipoMovimiento());
			entity.setIdTipoPeriodo(dto.getIdTipoPeriodo());
			entity.setQuincenaOperacion(dto.getQuincenaOperacion());
            entity.setIdNominaEmpleado(dto.getIdNominaEmpleado());
			entity.setDecripcionConcepto(dto.getDescripcion_concepto());
			movimientosNominaContratosRepository.crear(entity);
			EmpleadoEntity empleadoEntity = empleadoRepository.obtenerPorId(dto.getIdEmpleado());
			NominaEmpleadoEntity nominaEmpleadoEntity = nominaEmpleadoRepository.obtenerPorId(dto.getIdNominaEmpleado());
			if (dto.getFaltaContadaList() != null ) {
				for (FaltaContadaDTO faltaContadaDTO: dto.getFaltaContadaList()) {
					FaltaContadaEntity faltaContadaEntity = new FaltaContadaEntity();
					faltaContadaEntity.setEmpleado(empleadoEntity);
					faltaContadaEntity.setFechaFalta(faltaContadaDTO.getFechaFalta());
					faltaContadaEntity.setIdFalta(null);
					faltaContadaEntity.setMovimientoNominaEventual(entity);
					faltaContadaEntity.setNominaEmpleado(nominaEmpleadoEntity);
					faltaContadaRepository.crear(faltaContadaEntity);
				}
			}
			return entity;
		} catch (PersistenceException ex) {
			System.out.println(ex);
			return null;
		}
	}

	public void guardarDetalle(DetalleMovimientoContratoDTO dto) {
		DetalleMovimientosCotratosEntity entity = new DetalleMovimientosCotratosEntity();
		entity.setAbono(dto.getAbono());
		entity.setDescuento(dto.getDescuento());
		entity.setFechaRegistro(dto.getFechaRegistro());
		entity.setIdMovimientoContrato(dto.getIdMovimientoContrato());
		entity.setMonto(dto.getMonto());
		entity.setNumeroPago(dto.getNumeroPago());
		entity.setSaldo(dto.getSaldo());
		detalleMovimientosContratosRepository.crear(entity);
	}

	public List<DetalleMovimientoContratoDTO> listaDetallesMovimientosPorMovimiento(Integer idMovimiento) {
		try {
			return detalleMovimientosContratosRepository.obtenerDetallesPorMovimiento(idMovimiento);
		} catch (NoResultException ex) {
			return null;
		}
	}

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<InfoMovimientoDTO> obtenerInfoMovimientos(Integer idNominaEmpleado){
		List<MovimientosNominaContratosEntity> movimientos = movimientosNominaContratosRepository.
				obtenerMovimientosNominaContratosPorIdNominaEmpleado(idNominaEmpleado);
		
		List<InfoMovimientoDTO> listaInfoDto = new ArrayList<>();
		
		if(!movimientos.isEmpty()){
			for(MovimientosNominaContratosEntity ent : movimientos){
				System.out.println("ent.getIdMovimiento():: " + ent.getIdMovimiento());
				InfoMovimientoDTO infoDto = new InfoMovimientoDTO();

				Session session = entityManager.unwrap(Session.class);
		        Query query = session.createSQLQuery(
		        		  " SELECT "
		        		+ " dmc.id_detalle_movimiento AS idDetalleMovimiento, "
		        		+ " MIN(dmc.numero_pago) AS numeroPago "
		        		+ " FROM detalle_movimientos_contratos AS dmc "
		        		+ " WHERE "
		        		+ " dmc.id_movimiento_contrato = :idMovimiento "
		        		+ " AND "
		        		+ " dmc.id_estatus_detalle = 1 ")
						.setParameter("idMovimiento", ent.getIdMovimiento());
		        query.setResultTransformer(Transformers.aliasToBean(DetalleMovimientoContratoDTO.class));
		        DetalleMovimientoContratoDTO detalleMovimiento = (DetalleMovimientoContratoDTO) query.uniqueResult();
		        System.out.println("detalleMovimiento:: " + detalleMovimiento.getIdDetalleMovimiento());
				DetalleMovimientosCotratosEntity detalle = null;
				if (detalleMovimiento != null && detalleMovimiento.getIdDetalleMovimiento() != null) {
					detalle = entityManager.find(DetalleMovimientosCotratosEntity.class, detalleMovimiento.getIdDetalleMovimiento());
				}

				infoDto.setIdConcepto(ent.getConceptoNominaContrato().getIdConceptoNomina());
				infoDto.setIdEmpleado(ent.getIdEmpleado());
				infoDto.setIdMovimiento(ent.getIdMovimiento());
				infoDto.setIdNominaEmpleado(ent.getIdNominaEmpleado());
				infoDto.setTipoConcepto(ent.getConceptoNominaContrato().getTipo());
				if (detalle != null) {
					infoDto.setMontoPeriodo(detalle.getDescuento());
					infoDto.setNumeroPago(detalle.getNumeroPago());
				} else {
					// cuando el movimiento no son de tipo 2
					infoDto.setMontoPeriodo(ent.getMonto());
					infoDto.setNumeroPago(1);
				}
				listaInfoDto.add(infoDto);
			}
		}
		return listaInfoDto;
	}

	public void eliminarMovimiento(MovimientoContratosDTO dto) {
		MovimientosNominaContratosEntity entity = movimientosNominaContratosRepository.obtenerPorId(dto.getIdMovimientoContratos());
		List<DetalleMovimientosCotratosEntity> detalles = detalleMovimientosContratosRepository.obtenerDetalleMovimientosPorMovimiento(dto.getIdMovimientoContratos());
		if (!detalles.isEmpty()) {
			for (DetalleMovimientosCotratosEntity detalle : detalles) {
				detalleMovimientosContratosRepository.eliminar(detalle);
			}
		}
		List<FaltaContadaEntity> faltaContadaList = faltaContadaRepository.obtenerPorMovimientoContrato(entity);
		if (!faltaContadaList.isEmpty()) {
			for (FaltaContadaEntity faltaContadaEntity : faltaContadaList) {
				faltaContadaRepository.eliminar(faltaContadaEntity);
			}
		}
		movimientosNominaContratosRepository.eliminar(entity);
	}

	public void actualizarMovimientoContratos(MovimientoContratosDTO dto) {
		MovimientosNominaContratosEntity entity = movimientosNominaContratosRepository.obtenerPorId(dto.getIdMovimientoContratos());
		ConceptoNominaContratosEntity conceptoNominaContrato = conceptoNominaContratosRepository.obtenerPorId(dto.getIdConceptoContratos());
		entity.setConceptoNominaContrato(conceptoNominaContrato);
		entity.setIdEmpleado(dto.getIdEmpleado());
		entity.setFechaRegistro(new Date());
		entity.setFechaModificacion(dto.getFechaModificacion());
		entity.setRfc(dto.getRfc());
		entity.setFechaDocumento(dto.getFechaDocumento());
		entity.setFolioDocumento(dto.getFolioDocumento());
		entity.setMonto(dto.getMonto());
		entity.setAbonado(dto.getAbonado());
		entity.setSaldo(dto.getSaldo());
		entity.setDescuento(dto.getDescuento());
		entity.setNumeroPagos(dto.getNumeroAbonos());
		entity.setAnioInicial(dto.getAnioInicial());
		entity.setPeriodoInicial(dto.getPeriodoInicial());
		entity.setAnioFinal(dto.getAnioFinal());
		entity.setPeriodoFinal(dto.getPeriodoFinal());
		entity.setAnyoOperacion(FechaUtil.ejercicioActual());
		entity.setDescuento(dto.getDescuento());
		entity.setDias(dto.getDias());
		entity.setIdEstatus(dto.getIdEstatus());
		entity.setIdTipoMovimiento(dto.getIdTipoMovimiento());
		entity.setIdTipoPeriodo(dto.getIdTipoPeriodo());
		entity.setQuincenaOperacion(dto.getQuincenaOperacion());
        entity.setIdNominaEmpleado(dto.getIdNominaEmpleado());
		entity.setDecripcionConcepto(dto.getDescripcion_concepto());
		movimientosNominaContratosRepository.actualizar(entity);
		EmpleadoEntity empleadoEntity = empleadoRepository.obtenerPorId(dto.getIdEmpleado());
		NominaEmpleadoEntity nominaEmpleadoEntity = nominaEmpleadoRepository.obtenerPorId(dto.getIdNominaEmpleado());
		for (FaltaContadaDTO faltaContadaDTO: dto.getFaltaContadaList()) {
			if (!faltaContadaRepository.faltaEstaContada(entity, faltaContadaDTO.getFechaFalta())) {
				FaltaContadaEntity faltaContadaEntity = new FaltaContadaEntity();
				faltaContadaEntity.setEmpleado(empleadoEntity);
				faltaContadaEntity.setFechaFalta(faltaContadaDTO.getFechaFalta());
				faltaContadaEntity.setIdFalta(null);
				faltaContadaEntity.setMovimientoNominaEventual(entity);
				faltaContadaEntity.setNominaEmpleado(nominaEmpleadoEntity);
				faltaContadaRepository.crear(faltaContadaEntity);
			}
		}
		List<FaltaContadaEntity> faltaContadaList = faltaContadaRepository.obtenerPorMovimientoContrato(entity);
		for (FaltaContadaEntity faltaContadaEntity: faltaContadaList) {
			Boolean sigue = false;
			for (FaltaContadaDTO faltaContadaDTO: dto.getFaltaContadaList()) {
				if (faltaContadaEntity.getFechaFalta().equals(faltaContadaDTO.getFechaFalta())) {
					sigue = true;
					break;
				}
			}
			if (!sigue) {
				faltaContadaRepository.eliminar(faltaContadaEntity);
			}
		}
	}

	public MovimientoContratosDTO obtenerMovimientoContrato(MovimientoContratosDTO movimientoContratos) {
		MovimientosNominaContratosEntity entity = movimientosNominaContratosRepository.obtenerPorId(movimientoContratos.getIdMovimientoContratos());
		List<FaltaContadaEntity> faltaContadaList = faltaContadaRepository.obtenerPorMovimientoContrato(entity);
		List<FaltaContadaDTO> faltaContadaDTOList = new ArrayList<>();
		if (!faltaContadaList.isEmpty()) {
			for (FaltaContadaEntity faltaContadaEntity : faltaContadaList) {
				FaltaContadaDTO faltaContadaDTO = new FaltaContadaDTO();
				faltaContadaDTO.setIdFalta(faltaContadaEntity.getIdFalta());
				faltaContadaDTO.setFechaFalta(faltaContadaEntity.getFechaFalta());
				faltaContadaDTO.setIdFaltaContada(faltaContadaEntity.getIdFaltaContada());
				if (faltaContadaEntity.getNominaEmpleado() != null) {
					faltaContadaDTO.setIdNominaEmpleado(faltaContadaEntity.getNominaEmpleado().getIdNominaEmpleado());
				}
				faltaContadaDTO.setIdMovimientoEventual(faltaContadaEntity.getMovimientoNominaEventual().getIdMovimiento());
				faltaContadaDTOList.add(faltaContadaDTO);
			}
		}
		movimientoContratos.setFaltaContadaList(faltaContadaDTOList);
		return movimientoContratos;
	}

}