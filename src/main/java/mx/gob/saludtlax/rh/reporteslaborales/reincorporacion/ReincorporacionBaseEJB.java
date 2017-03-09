package mx.gob.saludtlax.rh.reporteslaborales.reincorporacion;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.persistencia.MovimientoEmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.MovimientoEmpleadoRepository;

@Stateless
public class ReincorporacionBaseEJB {

	@Inject
	private MovimientoEmpleadoRepository movimientoEmpleadoRepository;

	public ReincorporacionBaseDTO obtenerReincorporacion(Integer idTipoMovimiento) {

		MovimientoEmpleadoEntity movimientoEmpleadoEntity = movimientoEmpleadoRepository.obtenerPorId(idTipoMovimiento);
		ReincorporacionBaseDTO reincorporacionBaseDTO = new ReincorporacionBaseDTO();
		
		
		
		Integer idMovimiento = movimientoEmpleadoEntity.getIdMovimientoEmpleado();
		
		String presenteNombre = movimientoEmpleadoEntity.getEmpleado().getNombreCompleto();
		String presenteClaveUno = movimientoEmpleadoEntity.getEmpleado().getRfc();
		String presenteClaveDos = movimientoEmpleadoEntity.getEmpleado().getCurp();
		String fecha = movimientoEmpleadoEntity.getFechaInicioPermiso()	+ " al " + movimientoEmpleadoEntity.getFechaFinPermiso();
		String fechaNombramiento = "";

		reincorporacionBaseDTO.setIdMovimiento(idMovimiento);
		reincorporacionBaseDTO.setPresenteNombre(presenteNombre);
		reincorporacionBaseDTO.setPresenteClaveUno(presenteClaveUno);
		reincorporacionBaseDTO.setPresenteClaveDos(presenteClaveDos);
		reincorporacionBaseDTO.setFecha(fecha);
		reincorporacionBaseDTO.setFechaNombramiento(fechaNombramiento);

		return reincorporacionBaseDTO;
	}

	public List<ReincorporacionBaseDetalleDTO> consultarPorCriterio(String criterio) {

		List<ReincorporacionBaseDetalleDTO> resultado = new ArrayList<ReincorporacionBaseDetalleDTO>();
		List<MovimientoEmpleadoEntity> movimientoEmpleadoEntityList = null;

		try {
			movimientoEmpleadoEntityList = movimientoEmpleadoRepository.consultarMovimientosPorRfc(criterio);
		} catch (Exception ex) {
		}

		if (movimientoEmpleadoEntityList != null) {

			for (int i = 0; i < movimientoEmpleadoEntityList.size(); i++) {
				MovimientoEmpleadoEntity m = movimientoEmpleadoEntityList.get(i);

				ReincorporacionBaseDetalleDTO dto = new ReincorporacionBaseDetalleDTO();

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