/**
 * 
 */
package mx.gob.saludtlax.rh.nomina.productosnomina;

import java.io.Serializable;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.empleados.administracion.BitacoraModificacionService;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.persistencia.EstatusNominasEmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.EstatusNominasEmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.NominaEmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.NominaEmpleadoRepository;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author Eduardo Mex
 *
 */
public class EstatusNominaService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8712983595660996919L;

	@Inject
	private EstatusNominasEmpleadoRepository estatusNominasEmpleadoRepository;

	@Inject
	private NominaEmpleadoRepository nominaEmpleadoRepository;

	@Inject
	private BitacoraModificacionService bitacoraModificacionService;

	public Integer obtenerEstatusPorIdProductoNomina(Integer idProductoNominaEmpleado) {

		String contexto = "obtenerEstatusPorIdProductoNomina: ";

		if (!ValidacionUtil.esNumeroPositivoInt(idProductoNominaEmpleado)) {
			throw new ValidacionException(contexto + "El id del producto nomina empleado es requerido",
					ValidacionCodigoError.VALOR_REQUERIDO);
		}

		Integer estatus = nominaEmpleadoRepository.obtenerEstatusPorIdProductoNomina(idProductoNominaEmpleado);

		if (estatus == null) {
			throw new ReglaNegocioException(contexto + "El estatus nomina empleado no se encuentra registrado.",
					ReglaNegocioCodigoError.SIN_REGISTRO);
		}

		return estatus;

	}

	protected void actualizarEstatusNominaEmpleado(Integer idEstatusNominaEmpleado, Integer idNominaEmpleado,
			Integer idUsuario) {

		String contexto = "actualizarEstatusNominaEmpleado: ";

		if (!ValidacionUtil.esNumeroPositivoInt(idEstatusNominaEmpleado)) {
			throw new ValidacionException(contexto + "El id del estatus nomina empleado es requerido",
					ValidacionCodigoError.VALOR_REQUERIDO);
		}

		if (!ValidacionUtil.esNumeroPositivoInt(idNominaEmpleado)) {
			throw new ValidacionException(contexto + "El id nomina empleado es requerido",
					ValidacionCodigoError.VALOR_REQUERIDO);
		}

		EstatusNominasEmpleadoEntity estatusNominasEmpleadoEntity = estatusNominasEmpleadoRepository
				.obtenerPorId(idEstatusNominaEmpleado);

		if (estatusNominasEmpleadoEntity == null) {
			throw new ReglaNegocioException(contexto + "El estatus nomina empleado no se encuentra registrado.",
					ReglaNegocioCodigoError.SIN_REGISTRO);
		}

		NominaEmpleadoEntity nominaEmpleadoEntity = nominaEmpleadoRepository.obtenerPorId(idNominaEmpleado);

		if (nominaEmpleadoEntity == null) {
			throw new ReglaNegocioException(contexto + "La nomina empleado no se encuentra registrado.",
					ReglaNegocioCodigoError.SIN_REGISTRO);
		}

		if (nominaEmpleadoEntity.getIdEstatusNominaEmpleado() == estatusNominasEmpleadoEntity) {
			throw new ReglaNegocioException(
					contexto + "La nomina empleado ya se encuentra con el estatus "
							+ EnumEstatusProductoNomina.obtenerEstatus(idEstatusNominaEmpleado),
					ReglaNegocioCodigoError.SIN_REGISTRO);
		}

		Integer movimientoEstatus = 1;// ESTA PARTE SI SE REQUIERE PULIR, SE
										// PUEDE REALIZAR CON UN CATALOGO EXTRA,
										// POR EL MOMENTO SOLO UTILIZO EL NUMERO
										// 1 COMO EL MOVIMIENTO PARA EL ESTATUS

		// ADMINISTRA LOS MOVIMIENTOS DE LOS ESTATUS DEL PRODUCTO NOMINA
		// EMPLEADO
		bitacoraModificacionService.registrarBitacoraProductoNominaEmpleado(nominaEmpleadoEntity,
				EnumEstatusProductoNomina.obtenerEstatus(idEstatusNominaEmpleado), idUsuario, movimientoEstatus);

		nominaEmpleadoEntity.setIdEstatusNominaEmpleado(estatusNominasEmpleadoEntity);

		nominaEmpleadoRepository.actualizar(nominaEmpleadoEntity);

	}

}
