package mx.gob.saludtlax.rh.empleado.procesosjuridicos;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;
import mx.gob.saludtlax.rh.persistencia.ProcesoEntity;
import mx.gob.saludtlax.rh.persistencia.ProcesoRepository;
import mx.gob.saludtlax.rh.util.FechaUtil;

public class ProcesoJuridicoService {

	@Inject
	ProcesoRepository procesosRepository;

	public ProcesoDTO nuevoProceso(InfoEmpleadoDTO empleado) {
		ProcesoDTO dto = new ProcesoDTO();
		dto.setIdProceso(null);
		dto.setFechaInicio(null);
		dto.setFechaFin(null);
		dto.setNumeroOficio(null);
		dto.setComentarios(null);
		dto.setIdEmpleado(empleado.getIdEmpleado());
		dto.setFechaAlta(FechaUtil.fechaActual());

		return dto;
	}

	protected void crearProceso(ProcesoDTO dto) {
		ProcesoEntity entity = new ProcesoEntity();
		entity.setIdProceso(dto.getIdProceso());
		entity.setFechaInicio(dto.getFechaInicio());
		entity.setFechaFin(dto.getFechaFin());
		entity.setNumeroOficio(dto.getNumeroOficio());
		entity.setComentarios(dto.getComentarios());
		entity.setIdEmleado(dto.getIdEmpleado());
		entity.setFechaAlta(dto.getFechaAlta());
		procesosRepository.crear(entity);
	}

	public List<ProcesoDTO> obtenerProcesoLista() {
		List<ProcesoDTO> procesoDTO = new ArrayList<ProcesoDTO>();
		for (ProcesoEntity proceso : procesosRepository.obternerListaProcesoJuridico()) {
			ProcesoDTO dto = new ProcesoDTO();
			dto.setIdProcesoEmpleado(proceso.getIdProcesoEmpleado());
			dto.setIdEmpleado(proceso.getIdEmleado());
			dto.setIdProceso(proceso.getIdProceso());
			dto.setFechaInicio(proceso.getFechaInicio());
			dto.setFechaFin(proceso.getFechaFin());
			dto.setNumeroOficio(proceso.getNumeroOficio());
			dto.setComentarios(proceso.getComentarios());
			dto.setFechaAlta(proceso.getFechaAlta());

			procesoDTO.add(dto);
		}
		return procesoDTO;
	}

	public ProcesoDTO gestionarProceso(ProcesoDTO dto) {
		ProcesoDTO DTO = new ProcesoDTO();
		ProcesoEntity entity = procesosRepository.obtenerPorId(dto.getIdProcesoEmpleado());
		DTO.setIdProcesoEmpleado(entity.getIdProcesoEmpleado());
		DTO.setIdProceso(entity.getIdProceso());
		DTO.setFechaInicio(entity.getFechaInicio());
		DTO.setFechaFin(entity.getFechaFin());
		DTO.setNumeroOficio(entity.getNumeroOficio());
		DTO.setComentarios(entity.getComentarios());
		
		return dto;
	}
	
	public void actualizarProceso(ProcesoDTO dto){
		ProcesoEntity entity = procesosRepository.obtenerPorId(dto.getIdProcesoEmpleado());
		System.out.println("Proceso::"+dto.getIdProceso());
		entity.setIdProceso(dto.getIdProceso());
		entity.setFechaInicio(dto.getFechaInicio());
		entity.setFechaFin(dto.getFechaFin());
		entity.setNumeroOficio(dto.getNumeroOficio());
		entity.setComentarios(dto.getComentarios());
		entity.setIdEmleado(dto.getIdEmpleado());

		procesosRepository.actualizar(entity);
	}

	public void eliminarProceso(ProcesoDTO proceso) {
		procesosRepository.eliminarPorId(proceso.getIdProcesoEmpleado());
	}
}
