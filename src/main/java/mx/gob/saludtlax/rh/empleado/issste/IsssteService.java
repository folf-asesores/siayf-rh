/**
 * 
 */
package mx.gob.saludtlax.rh.empleado.issste;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.persistencia.CausaBajaIsssteEntity;
import mx.gob.saludtlax.rh.persistencia.CausaBajaIsssteRepository;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionPresupuestoRepository;
import mx.gob.saludtlax.rh.persistencia.EmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.EmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.MovimientoIsssteEmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.MovimientoIsssteEmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.NivelSalarialEntity;
import mx.gob.saludtlax.rh.persistencia.NivelSalarialRepository;
import mx.gob.saludtlax.rh.persistencia.TipoMovimientoIsssteEntity;
import mx.gob.saludtlax.rh.persistencia.TipoMovimientoIsssteRepository;
import mx.gob.saludtlax.rh.persistencia.TiposNombramientosEntity;
import mx.gob.saludtlax.rh.persistencia.TiposNombramientosRepository;
import mx.gob.saludtlax.rh.persistencia.UsuarioEntity;
import mx.gob.saludtlax.rh.persistencia.UsuarioRepository;
import mx.gob.saludtlax.rh.util.FechaUtil;

/**
 * @author Eduardo Mex
 *
 */
public class IsssteService {

	@Inject
	private MovimientoIsssteEmpleadoRepository movimientoIsssteEmpleadoRepository;
	@Inject
	private ConfiguracionPresupuestoRepository configuracionPresupuestoRepository;
	@Inject
	private EmpleadoRepository empleadoRepository;
	@Inject
	private UsuarioRepository usuarioRepository;
	@Inject
	private CausaBajaIsssteRepository causaBajaIsssteRepository;
	@Inject
	private TiposNombramientosRepository nombramientosRepository;
	@Inject
	private TipoMovimientoIsssteRepository tipoMovimientoIsssteRepository;
	@Inject
	private NivelSalarialRepository nivelSalarialRepository;

	protected Integer altaIsssteMovimiento(MovimientoIsssteEmpleadoDTO movimientoIsssteEmpleadoDTO) {

		String contexto = "altaIsssteMovimiento: ";

		if (movimientoIsssteEmpleadoDTO == null) {
			throw new ValidacionException(contexto + "Ingrese los datos obligatorios.",
					ValidacionCodigoError.VALOR_REQUERIDO);
		}

		MovimientoIsssteEmpleadoEntity entity = new MovimientoIsssteEmpleadoEntity();

		EmpleadoEntity empleadoEntity = empleadoRepository.obtenerPorId(movimientoIsssteEmpleadoDTO.getIdEmpleado());

		if (empleadoEntity == null) {
			throw new ReglaNegocioException(contexto + "El empleado no se encuentra registrado.",
					ReglaNegocioCodigoError.SIN_REGISTRO);
		}

		UsuarioEntity usuarioEntity = usuarioRepository.obtenerPorId(movimientoIsssteEmpleadoDTO.getIdUsuario());

		if (usuarioEntity == null) {
			throw new ReglaNegocioException(contexto + "El usuario no se encuentra registrado.",
					ReglaNegocioCodigoError.SIN_REGISTRO);
		}

		TiposNombramientosEntity tiposNombramientosEntity = nombramientosRepository
				.obtenerPorId(movimientoIsssteEmpleadoDTO.getIdTipoNombramiento());

		if (tiposNombramientosEntity == null) {
			throw new ReglaNegocioException(contexto + "El nombramiento no se encuentra registrado.",
					ReglaNegocioCodigoError.SIN_REGISTRO);
		}

		TipoMovimientoIsssteEntity tipoMovimientoIsssteEntity = tipoMovimientoIsssteRepository
				.obtenerPorId(EnumTipoMovimientoIssste.ALTA_TRABAJADOR);

		if (tipoMovimientoIsssteEntity == null) {
			throw new ReglaNegocioException(contexto + "El tipo movimiento issste no se encuentra registrado.",
					ReglaNegocioCodigoError.SIN_REGISTRO);
		}

		NivelSalarialEntity nivelSalarialEntity = nivelSalarialRepository
				.obtenerPorId(movimientoIsssteEmpleadoDTO.getIdNivelSalarial());

		if (nivelSalarialEntity == null) {
			throw new ReglaNegocioException(contexto + "El nivel salarial no se encuentra registrado.",
					ReglaNegocioCodigoError.SIN_REGISTRO);
		}

		entity.setEmpleado(empleadoEntity);
		entity.setTipoMovimientoIssste(tipoMovimientoIsssteEntity);
		entity.setSueldoIssste(movimientoIsssteEmpleadoDTO.getSueldoIssste());
		entity.setSueldoSar(movimientoIsssteEmpleadoDTO.getSueldoSar());
		entity.setTotalRemuneracion(movimientoIsssteEmpleadoDTO.getTotalRemuneracion());
		entity.setNivelSalario(nivelSalarialEntity.getNivelSalarial());
		entity.setNombramiento(tiposNombramientosEntity.getDescripcion());
		entity.setFechaMovimiento(FechaUtil.fechaActual());
		entity.setHoraMovimiento(FechaUtil.horaActual());
		entity.setUsuario(usuarioEntity);

		movimientoIsssteEmpleadoRepository.crear(entity);

		return entity.getIdMovimientoIsssteEmpleado();
	}

	protected void modificacionIsssteMovimiento(MovimientoIsssteEmpleadoDTO movimientoIsssteEmpleadoDTO) {

		String contexto = "modificacionIsssteMovimiento: ";

		MovimientoIsssteEmpleadoEntity entity = movimientoIsssteEmpleadoRepository
				.obtenerPorId(movimientoIsssteEmpleadoDTO.getIdMovimientoIsssteEmpleado());

		if (entity == null) {
			throw new ReglaNegocioException(contexto + "El movimiento ISSSTE no se encuentra registrado.",
					ReglaNegocioCodigoError.SIN_REGISTRO);
		}

		TipoMovimientoIsssteEntity tipoMovimientoIsssteEntity = tipoMovimientoIsssteRepository
				.obtenerPorId(EnumTipoMovimientoIssste.MODIFICACIÓN_SUELDO);

		if (tipoMovimientoIsssteEntity == null) {
			throw new ReglaNegocioException(contexto + "El tipo movimiento issste no se encuentra registrado.",
					ReglaNegocioCodigoError.SIN_REGISTRO);
		}

		entity.setTipoMovimientoIssste(tipoMovimientoIsssteEntity);
		entity.setSueldoIssste(movimientoIsssteEmpleadoDTO.getSueldoIssste());
		entity.setSueldoSar(movimientoIsssteEmpleadoDTO.getSueldoSar());
		entity.setTotalRemuneracion(movimientoIsssteEmpleadoDTO.getTotalRemuneracion());
		entity.setFechaMovimiento(FechaUtil.fechaActual());
		entity.setHoraMovimiento(FechaUtil.horaActual());

		movimientoIsssteEmpleadoRepository.actualizar(entity);

	}

	protected void bajaIsssteMovimiento(MovimientoIsssteEmpleadoDTO movimientoIsssteEmpleadoDTO) {

		String contexto = "bajaIsssteMovimiento: ";

		MovimientoIsssteEmpleadoEntity entity = movimientoIsssteEmpleadoRepository
				.obtenerPorId(movimientoIsssteEmpleadoDTO.getIdMovimientoIsssteEmpleado());

		if (entity == null) {
			throw new ReglaNegocioException(contexto + "El movimiento ISSSTE no se encuentra registrado.",
					ReglaNegocioCodigoError.SIN_REGISTRO);
		}

		CausaBajaIsssteEntity causaBajaIsssteEntity = causaBajaIsssteRepository
				.obtenerPorId(movimientoIsssteEmpleadoDTO.getIdCausaBaja());

		if (causaBajaIsssteEntity == null) {
			throw new ReglaNegocioException(contexto + "La causa baja ISSSTE no se encuentra registrado.",
					ReglaNegocioCodigoError.SIN_REGISTRO);
		}

		TipoMovimientoIsssteEntity tipoMovimientoIsssteEntity = tipoMovimientoIsssteRepository
				.obtenerPorId(EnumTipoMovimientoIssste.BAJA_ISSSTE);

		if (tipoMovimientoIsssteEntity == null) {
			throw new ReglaNegocioException(contexto + "El tipo movimiento issste no se encuentra registrado.",
					ReglaNegocioCodigoError.SIN_REGISTRO);
		}

		entity.setTipoMovimientoIssste(tipoMovimientoIsssteEntity);

		entity.setSueldoIssste(movimientoIsssteEmpleadoDTO.getSueldoIssste());
		entity.setSueldoSar(movimientoIsssteEmpleadoDTO.getSueldoSar());
		entity.setTotalRemuneracion(movimientoIsssteEmpleadoDTO.getTotalRemuneracion());
		entity.setCausaBaja(causaBajaIsssteEntity);
		entity.setFechaMovimiento(FechaUtil.fechaActual());
		entity.setHoraMovimiento(FechaUtil.horaActual());

		movimientoIsssteEmpleadoRepository.actualizar(entity);

	}

}
