package mx.gob.saludtlax.rh.nomina.movimientosnomina;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.persistencia.DetalleProgramacionMovimientoEntity;
import mx.gob.saludtlax.rh.persistencia.DetalleProgramacionMovimientoRepository;
import mx.gob.saludtlax.rh.persistencia.ProgramarMovimientoEntity;
import mx.gob.saludtlax.rh.persistencia.ProgramarMovimientoRepository;
import mx.gob.saludtlax.rh.persistencia.PuestoGeneralEntity;
import mx.gob.saludtlax.rh.persistencia.PuestoGeneralRepository;
import mx.gob.saludtlax.rh.persistencia.TipoContratacionEntity;
import mx.gob.saludtlax.rh.persistencia.TipoContratacionRepository;
import mx.gob.saludtlax.rh.persistencia.TiposMovimientosNominaRepository;

public class ProgramacionMovimientosService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 269941958508936769L;

	@Inject
	private DetalleProgramacionMovimientoRepository detalleProgramacionMovimientoRepository;

	@Inject
	private ProgramarMovimientoRepository programarMovimientoRepository;

	@Inject
	private PuestoGeneralRepository puestoGeneralRepository;

	@Inject
	private TipoContratacionRepository contratacionRepository;

	@Inject
	private TiposMovimientosNominaRepository tipoMovimientoRepository;

	public void crear(ProgramarMovimientoDTO dto) {
		ProgramarMovimientoEntity entity = new ProgramarMovimientoEntity();

		entity.setIdTipoMovimiento(tipoMovimientoRepository.obtenerPorId(dto.getIdTipoMovimiento()));
		entity.setImporte(dto.getImporte());
		entity.setPeriodoAplicacion(dto.getPeriodoAplicacion());
		entity.setTipoAplicacion(dto.getTipoAplicacion());
		entity.setDescripcion(dto.getDescripcion());
		programarMovimientoRepository.crear(entity);

		if (dto.getListaDetalles() != null && !dto.getListaDetalles().isEmpty()) {
			for (DetalleProgramacionMovimientoDTO dtoDetalle : dto.getListaDetalles()) {
				DetalleProgramacionMovimientoEntity newDetalle = new DetalleProgramacionMovimientoEntity();
				newDetalle.setIdProgramacionMovimiento(entity);
				if (dtoDetalle.getIdPuesto() != null) {
					newDetalle.setIdPuesto(puestoGeneralRepository.obtenerPorId(dtoDetalle.getIdPuesto()));
				}
				if (dtoDetalle.getIdTipoContratacion() != null) {
					newDetalle.setIdTipoContratacion(
							contratacionRepository.obtenerPorId(dtoDetalle.getIdTipoContratacion()));
				}

				newDetalle.setImporte(dtoDetalle.getImporte());

				detalleProgramacionMovimientoRepository.crear(newDetalle);
			}
		}

	}

	public void modificar(ProgramarMovimientoDTO dto) {
		ProgramarMovimientoEntity entity = programarMovimientoRepository
				.obtenerPorId(dto.getIdProgramacionMovimiento());

		entity.setIdTipoMovimiento(tipoMovimientoRepository.obtenerPorId(dto.getIdTipoMovimiento()));
		entity.setImporte(dto.getImporte());
		entity.setPeriodoAplicacion(dto.getPeriodoAplicacion());
		entity.setTipoAplicacion(dto.getTipoAplicacion());
		entity.setDescripcion(dto.getDescripcion());
		programarMovimientoRepository.actualizar(entity);

		if (dto.getListaDetalles() != null && !dto.getListaDetalles().isEmpty()) {
			List<DetalleProgramacionMovimientoEntity> listaold = new ArrayList<>();
			listaold = detalleProgramacionMovimientoRepository
					.obtenerRegistrosPorProgramacionMovimimento(dto.getIdProgramacionMovimiento());
			for (DetalleProgramacionMovimientoEntity entityOld : listaold) {
				detalleProgramacionMovimientoRepository.eliminar(detalleProgramacionMovimientoRepository
						.obtenerPorId(entityOld.getIdDetalleProgramacionMovimiento()));
			}

			for (DetalleProgramacionMovimientoDTO dtoDetalle : dto.getListaDetalles()) {
				DetalleProgramacionMovimientoEntity newDetalle = new DetalleProgramacionMovimientoEntity();
				newDetalle.setIdProgramacionMovimiento(entity);
				if (dtoDetalle.getIdPuesto() != null) {
					newDetalle.setIdPuesto(puestoGeneralRepository.obtenerPorId(dtoDetalle.getIdPuesto()));
				}
				if (dtoDetalle.getIdTipoContratacion() != null) {
					newDetalle.setIdTipoContratacion(
							contratacionRepository.obtenerPorId(dtoDetalle.getIdTipoContratacion()));
				}

				newDetalle.setImporte(dtoDetalle.getImporte());

				detalleProgramacionMovimientoRepository.crear(newDetalle);
			}
		}
	}

	public void eliminar(ProgramarMovimientoDTO dto) {
		List<DetalleProgramacionMovimientoEntity> listaold = new ArrayList<>();
		listaold = detalleProgramacionMovimientoRepository
				.obtenerRegistrosPorProgramacionMovimimento(dto.getIdProgramacionMovimiento());
		if (!listaold.isEmpty()) {
			for (DetalleProgramacionMovimientoEntity entityOld : listaold) {
				detalleProgramacionMovimientoRepository.eliminar(detalleProgramacionMovimientoRepository
						.obtenerPorId(entityOld.getIdDetalleProgramacionMovimiento()));
			}

		}
		programarMovimientoRepository.eliminarPorId(dto.getIdProgramacionMovimiento());
	}

	public List<ProgramarMovimientoDTO> obtenerListaRegistros(Integer idTipoMovimieto) {
		List<ProgramarMovimientoDTO> dtos = new ArrayList<>();
		List<ProgramarMovimientoEntity> entitys = new ArrayList<>();
		entitys = programarMovimientoRepository.obtenerRegistrosPorTipoMovimiento(idTipoMovimieto);

		if (entitys != null && !entitys.isEmpty()) {
			for (ProgramarMovimientoEntity en : entitys) {
				ProgramarMovimientoDTO progdto = new ProgramarMovimientoDTO();
				progdto.setIdProgramacionMovimiento(en.getIdProgramacionMovimiento());
				progdto.setIdTipoMovimiento(en.getIdTipoMovimiento().getIdMovimientoNomina());
				progdto.setImporte(en.getImporte());
				progdto.setMovimiento(
						en.getIdTipoMovimiento().getClave() + " " + en.getIdTipoMovimiento().getDescripcion());
				progdto.setPeriodoAplicacion(en.getPeriodoAplicacion());
				progdto.setTipoAplicacion(en.getTipoAplicacion());
				progdto.setDescripcion(en.getDescripcion());
				List<DetalleProgramacionMovimientoDTO> listaDetalles = new ArrayList<>();
				listaDetalles = listaDetallesPorProgramacionMovimiento(progdto.getIdProgramacionMovimiento());
				if (!listaDetalles.isEmpty()) {
					progdto.setListaDetalles(listaDetalles);
				}

				dtos.add(progdto);
			}
		}
		return dtos;
	}

	public List<DetalleProgramacionMovimientoDTO> listaDetalles() {
		List<DetalleProgramacionMovimientoEntity> lista = new ArrayList<>();
		lista = detalleProgramacionMovimientoRepository.consultarTodos();
		List<DetalleProgramacionMovimientoDTO> listadtos = new ArrayList<>();
		if (!lista.isEmpty()) {

			for (DetalleProgramacionMovimientoEntity entity : lista) {
				DetalleProgramacionMovimientoDTO dto = new DetalleProgramacionMovimientoDTO();
				dto.setIdDetalleProgramacionMovimiento(entity.getIdDetalleProgramacionMovimiento());

				if (entity.getIdPuesto() != null) {
					PuestoGeneralEntity puestoEnt = new PuestoGeneralEntity();
					puestoEnt = puestoGeneralRepository.obtenerPorId(entity.getIdPuesto().getIdPuestoGeneral());
					dto.setDescripcionPuesto(puestoEnt.getCodigo() + " " + puestoEnt.getPuesto());
					dto.setIdPuesto(puestoEnt.getIdPuestoGeneral());
				}

				if (entity.getIdTipoContratacion() != null) {
					TipoContratacionEntity tipocE = new TipoContratacionEntity();
					tipocE = contratacionRepository.obtenerPorId(entity.getIdTipoContratacion().getId());
					dto.setIdTipoContratacion(tipocE.getId());
					dto.setDescripcionTipoContratacion(tipocE.getTipoContratacion());
				}

				dto.setIdProgramacionMovimiento(entity.getIdProgramacionMovimiento().getIdProgramacionMovimiento());
				dto.setImporte(entity.getImporte());
				listadtos.add(dto);
			}

		}
		return listadtos;
	}

	public List<DetalleProgramacionMovimientoDTO> listaDetallesPorProgramacionMovimiento(Integer idProgramacionMov) {
		List<DetalleProgramacionMovimientoEntity> lista = new ArrayList<>();
		lista = detalleProgramacionMovimientoRepository.obtenerRegistrosPorProgramacionMovimimento(idProgramacionMov);
		List<DetalleProgramacionMovimientoDTO> listadtos = new ArrayList<>();
		if (!lista.isEmpty()) {

			for (DetalleProgramacionMovimientoEntity entity : lista) {
				DetalleProgramacionMovimientoDTO dto = new DetalleProgramacionMovimientoDTO();
				dto.setIdDetalleProgramacionMovimiento(entity.getIdDetalleProgramacionMovimiento());

				if (entity.getIdPuesto() != null) {
					PuestoGeneralEntity puestoEnt = new PuestoGeneralEntity();
					puestoEnt = puestoGeneralRepository.obtenerPorId(entity.getIdPuesto().getIdPuestoGeneral());
					dto.setDescripcionPuesto(puestoEnt.getCodigo() + " " + puestoEnt.getPuesto());
					dto.setIdPuesto(puestoEnt.getIdPuestoGeneral());
				}

				if (entity.getIdTipoContratacion() != null) {
					TipoContratacionEntity tipocE = new TipoContratacionEntity();
					tipocE = contratacionRepository.obtenerPorId(entity.getIdTipoContratacion().getId());
					dto.setIdTipoContratacion(tipocE.getId());
					dto.setDescripcionTipoContratacion(tipocE.getTipoContratacion());
				}

				dto.setIdProgramacionMovimiento(entity.getIdProgramacionMovimiento().getIdProgramacionMovimiento());
				dto.setImporte(entity.getImporte());
				listadtos.add(dto);
			}

		}
		return listadtos;
	}

}
