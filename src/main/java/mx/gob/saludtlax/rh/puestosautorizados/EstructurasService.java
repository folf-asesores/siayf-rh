/**
 * 
 */
package mx.gob.saludtlax.rh.puestosautorizados;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.empleados.administracion.BitacoraEmpleadoDTO;
import mx.gob.saludtlax.rh.empleados.administracion.BitacoraModificacionService;
import mx.gob.saludtlax.rh.empleados.administracion.EnumTipoModificacionEmpleado;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.SistemaCodigoError;
import mx.gob.saludtlax.rh.excepciones.SistemaException;
import mx.gob.saludtlax.rh.persistencia.InventarioVacanteEntity;
import mx.gob.saludtlax.rh.persistencia.InventarioVacanteRepository;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 22/02/2017 00:18:04
 */
public class EstructurasService {
	@Inject
	private BitacoraModificacionService bitacoraModificacionService;
	@Inject
	private InventarioVacanteRepository puestoAutorizadoRepository;

	public void actualizarEstructuraContrato(EstructuraContratoDTO estructuraContrato) {

		InventarioVacanteEntity puesto = puestoAutorizadoRepository.obtenerPorId(estructuraContrato.getIdPuesto());
		if (puesto == null) {
			throw new SistemaException("No existe el puesto con identificador " + estructuraContrato.getIdPuesto(),
					SistemaCodigoError.BUSQUEDA_SIN_RESULTADOS);
		}

		if (puesto.getTipoContratacion().getId() != EnumTipoContratacion.CONTRATO_ESTATAL) {
			throw new ReglaNegocioException("La estructura solo está permitida para contrato estatal.",
					ReglaNegocioCodigoError.TIPO_CONTRATACION_NO_PERMITIDA);
		}

		String lccActual = puesto.lccEstructuraContrato();
		puesto.setFuncionEspecifica(estructuraContrato.getFuncion());
		puesto.setSubfuncion(estructuraContrato.getSubfuncion());
		puesto.setFinanciamiento(estructuraContrato.getFinanciamiento());
		puesto.setJornada(estructuraContrato.getJornada());
		puestoAutorizadoRepository.actualizar(puesto);
		String lccNueva = puesto.lccEstructuraContrato();

		if (!lccActual.equals(lccNueva)) {
			BitacoraEmpleadoDTO bitacora = new BitacoraEmpleadoDTO();
			bitacora.setComentarios("");
			bitacora.setEmpleado(puesto.getEmpleadoActivo().getIdEmpleado());
			bitacora.setIdUsuario(estructuraContrato.getIdUsuario());
			bitacora.setLccActual(lccNueva);
			bitacora.setLccAnterior(lccActual);
			bitacora.setTipoMovimientoEmpleado(EnumTipoModificacionEmpleado.ACTUALIZACION_ESTRUCTURA_NOMINA);
			bitacoraModificacionService.registrarBitacora(bitacora);
		}
	}

}
