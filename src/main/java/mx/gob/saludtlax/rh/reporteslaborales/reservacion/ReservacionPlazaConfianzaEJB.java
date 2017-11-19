package mx.gob.saludtlax.rh.reporteslaborales.reservacion;

import java.util.ArrayList;
import java.util.Date;
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
public class ReservacionPlazaConfianzaEJB {
	@Inject
	private MovimientoEmpleadoRepository movimientoEmpleadoRepository;

	public ReservacionDTO obtenerReservacion(Integer idTipoMovimiento) {

		MovimientoEmpleadoEntity movimientoEmpleadoEntity = movimientoEmpleadoRepository.obtenerPorId(idTipoMovimiento);
		ReservacionDTO reservacionDTO = new ReservacionDTO();

		Integer idMovimiento = movimientoEmpleadoEntity.getIdMovimientoEmpleado();

		String asunto = "Se concede licencia sin goce de sueldo en el puesto con funciones de base, para ocupar puesto de confianza dentro de "
				+ "la Secretaría de Salud en el Estado y O.P.D. Salud de Tlaxcala.";
		String presenteNombre = movimientoEmpleadoEntity.getEmpleado().nombreCompleto();
		String presenteClaveUno = movimientoEmpleadoEntity.getEmpleado().getRfc();
		String presenteClaveDos = movimientoEmpleadoEntity.getEmpleado().getCurp();
		String comunicado = " para que se le otorgue licencia sin goce de sueldo en el puesto con funciones de base para ocupar "
				+ "puesto de confianza dentro de la Secretaría de Salud en el Estado y O.P.D. Salud de Tlaxcala, me permito comunicarle "
				+ "que con fundamento en los Artículos 43 Fracción VIII de la Ley Federal de los Trabajadores al Servicio del Estado y 148 "
				+ "Fracción V de las Condiciones Generales de Trabajo de la Secretaría de Salud";
		String fecha = "del 01 de febrero al 31 de diciembre de 2016";
		Date fechaIngreso = movimientoEmpleadoEntity.getFechaIngreso();
		String claveOriginal = "CLAVE ORIGINAL";
		String denominacionAlta = "DENOMINACION ALTA";
		String adscripcionComision = "ADSCRIPCION COMISION";
		String fechaDesignacion = "FECHA DE DESIGNACION";
		String claveDesignada = "CLAVE DESIGNADA";
		String denominacion = "DENOMINACION";
		String adscripcion = "ADSCRIPCION";
		String posicionUno = "el Artículo 151 Párrafo Segundo de las Condiciones Generales de Trabajo, al separarse del puesto de confianza,"
				+ " deberá reincorporarse a su puesto de base, en su lugar de adscripción, dentro de los seis días hábiles siguientes, por lo que "
				+ "deberá dar aviso de reincorporación a esta Dirección, 15 días antes de que se separe del puesto de confianza";
		String directoraAdministracion = "C.P. LUZ MARIA PORTILLO GARCIA";

		reservacionDTO.setIdMovimiento(idMovimiento);
		reservacionDTO.setAsunto(asunto);
		reservacionDTO.setPresenteNombre(presenteNombre);
		reservacionDTO.setPresenteClaveUno(presenteClaveUno);
		reservacionDTO.setPresenteClaveDos(presenteClaveDos);
		reservacionDTO.setComunicado(comunicado);
		reservacionDTO.setFecha(fecha);
		reservacionDTO.setFechaIngreso(fechaIngreso);
		reservacionDTO.setClaveOriginal(claveOriginal);
		reservacionDTO.setDenominacionAlta(denominacionAlta);
		reservacionDTO.setAdscripcionComision(adscripcionComision);
		reservacionDTO.setFechaDesignacion(fechaDesignacion);
		reservacionDTO.setClaveDesignada(claveDesignada);
		reservacionDTO.setDenominacion(denominacion);
		reservacionDTO.setAdscripcion(adscripcion);
		reservacionDTO.setPosicionUno(posicionUno);
		reservacionDTO.setDirectoraAdministracion(directoraAdministracion);

		return reservacionDTO;
	}

	public List<ReservacionDetalleDTO> consultarPorCriterio(String criterio) {

		List<ReservacionDetalleDTO> resultado = new ArrayList<ReservacionDetalleDTO>();
		List<MovimientoEmpleadoEntity> movimientoEmpleadoEntityList = null;

		try {
			movimientoEmpleadoEntityList = movimientoEmpleadoRepository.consultarMovimientosPorRfc(criterio);
		} catch (Exception ex) {
		}

		if (movimientoEmpleadoEntityList != null) {

			for (int i = 0; i < movimientoEmpleadoEntityList.size(); i++) {
				MovimientoEmpleadoEntity m = movimientoEmpleadoEntityList.get(i);

				ReservacionDetalleDTO dto = new ReservacionDetalleDTO();

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
	
	public Integer guardar(ReservacionDTO dto) {
		
		MovimientoEmpleadoEntity entity = convertirDtoAEntidad(dto);
		movimientoEmpleadoRepository.crear(entity);
		return entity.getIdMovimientoEmpleado();
	}

	private MovimientoEmpleadoEntity convertirDtoAEntidad(ReservacionDTO dto) {
		
		return null;
	}

}