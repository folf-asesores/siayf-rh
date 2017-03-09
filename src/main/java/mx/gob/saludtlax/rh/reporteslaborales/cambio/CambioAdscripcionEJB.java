package mx.gob.saludtlax.rh.reporteslaborales.cambio;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.persistencia.MovimientoEmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.MovimientoEmpleadoRepository;

/**
 * @author Daniela
 *
 */

@Stateless
public class CambioAdscripcionEJB {

	@Inject
	private MovimientoEmpleadoRepository movimientoEmpleadoRepository;

	public CambioAdscripcionDTO obtenerComisionOficial(Integer idTipoMovimiento) {

		MovimientoEmpleadoEntity movimientoEmpleadoEntity = movimientoEmpleadoRepository.obtenerPorId(idTipoMovimiento);
		CambioAdscripcionDTO cambioAdscripcionDTO = new CambioAdscripcionDTO();

		Integer idMovimiento = movimientoEmpleadoEntity.getIdMovimientoEmpleado();

		String asunto = "";
		String presenteNombre = movimientoEmpleadoEntity.getEmpleado().nombreCompleto();
		String presenteClaveUno = movimientoEmpleadoEntity.getEmpleado().getRfc();
		String presenteClaveDos = movimientoEmpleadoEntity.getEmpleado().getCurp();
		String fecha = movimientoEmpleadoEntity.getFechaInicioPermiso() + " al " + movimientoEmpleadoEntity.getFechaFinPermiso();
		String fechaCambio = "";
		String cambioAdscripcion = "";
		String funcion = "";
		String clavePresupuestal = "";
		String turno = "";
		String encargadoLabores = "";
		String secretarioSalud = "";

		cambioAdscripcionDTO.setIdMovimiento(idMovimiento);
		cambioAdscripcionDTO.setAsunto(asunto);
		cambioAdscripcionDTO.setPresenteNombre(presenteNombre);
		cambioAdscripcionDTO.setPresenteClaveUno(presenteClaveUno);
		cambioAdscripcionDTO.setPresenteClaveDos(presenteClaveDos);
		cambioAdscripcionDTO.setFecha(fecha);
		cambioAdscripcionDTO.setFechaCambio(fechaCambio);
		cambioAdscripcionDTO.setCambioAdscripcion(cambioAdscripcion);
		cambioAdscripcionDTO.setFuncion(funcion);
		cambioAdscripcionDTO.setClavePresupuestal(clavePresupuestal);
		cambioAdscripcionDTO.setTurno(turno);
		cambioAdscripcionDTO.setEncargadoLabores(encargadoLabores);
		cambioAdscripcionDTO.setSecretarioSalud(secretarioSalud);

		return cambioAdscripcionDTO;
	}

	public List<CambioAdscripcionDetalleDTO> consultarPorCriterio(String criterio) {

		List<CambioAdscripcionDetalleDTO> resultado = new ArrayList<CambioAdscripcionDetalleDTO>();
		List<MovimientoEmpleadoEntity> movimientoEmpleadoEntityList = null;

		try {
			movimientoEmpleadoEntityList = movimientoEmpleadoRepository.consultarMovimientosPorRfc(criterio);
		} catch (Exception ex) {
		}

		if (movimientoEmpleadoEntityList != null) {

			for (int i = 0; i < movimientoEmpleadoEntityList.size(); i++) {
				MovimientoEmpleadoEntity m = movimientoEmpleadoEntityList.get(i);

				CambioAdscripcionDetalleDTO dto = new CambioAdscripcionDetalleDTO();

				String curp = m.getEmpleado().getCurp();
				String empleado = m.getEmpleado().getNombreCompleto();
				Integer idMovimiento = m.getIdMovimientoEmpleado();
				String rfc = m.getEmpleado().getRfc();
				String motivo = m.getMotivoPermiso();
				String url = "";

				dto.setCurp(curp);
				dto.setEmpleado(empleado);
				dto.setIdMovimiento(idMovimiento);
				dto.setRfc(rfc);
				dto.setMotivo(motivo);
				dto.setUrl(url);

				resultado.add(dto);
			}
		}

		return resultado;
	}
}
