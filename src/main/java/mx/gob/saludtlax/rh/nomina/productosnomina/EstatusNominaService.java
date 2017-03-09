/**
 * 
 */
package mx.gob.saludtlax.rh.nomina.productosnomina;

import java.io.Serializable;

import javax.inject.Inject;

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

	protected void retenerNominaEmpleado(Integer idEstatusNominaEmpleadoRetenido, Integer idNominaEmpleado) {

		String contexto = "retenerNominaEmpleado:";

		if (!ValidacionUtil.esNumeroPositivoInt(idEstatusNominaEmpleadoRetenido)) {
			throw new ValidacionException(contexto + "El id del estatus nomina empleado es requerido",
					ValidacionCodigoError.VALOR_REQUERIDO);
		}

		if (!ValidacionUtil.esNumeroPositivoInt(idNominaEmpleado)) {
			throw new ValidacionException(contexto + "El id nomina empleado es requerido",
					ValidacionCodigoError.VALOR_REQUERIDO);
		}

		EstatusNominasEmpleadoEntity estatusNominasEmpleadoEntity = estatusNominasEmpleadoRepository
				.obtenerPorId(idEstatusNominaEmpleadoRetenido);

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
			throw new ReglaNegocioException(contexto + "La nomina empleado ya se encuentra con el estatus retenido.",
					ReglaNegocioCodigoError.SIN_REGISTRO);
		}

		nominaEmpleadoEntity.setIdEstatusNominaEmpleado(estatusNominasEmpleadoEntity);

		nominaEmpleadoRepository.actualizar(nominaEmpleadoEntity);

	}

}
