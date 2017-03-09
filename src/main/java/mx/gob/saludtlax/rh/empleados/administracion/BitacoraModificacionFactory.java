/**
 * 
 */
package mx.gob.saludtlax.rh.empleados.administracion;

import mx.gob.saludtlax.rh.persistencia.EmpleadoEntity;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 08/03/2016-11:09:56
 */
public class BitacoraModificacionFactory {

	/**
	 * Retorna la información requerida para registrar una bitacora de
	 * movimiento del empleado
	 * 
	 * @param comentarios
	 *            observaciones o comentarios del movimiento
	 * @param empleado
	 *            empleado al que se le realiza el movimiento
	 * @param lccActual
	 *            linea de captura del objeto EmpleadoEntity con las
	 *            modificaciones realizadas.
	 * @param lccAnterior
	 *            linea de captura del objeto EmpleadoEntity antes de realizar
	 *            el movimiento.
	 * @param tipoMovimientoEmpleado
	 *            movimiento realizado al empleado.
	 * */
	public static BitacoraEmpleadoDTO toBitacoraEmpleadoDTO(String comentarios,
			EmpleadoEntity empleado, String lccActual, String lccAnterior,
			Integer tipoMovimientoEmpleado) {
		BitacoraEmpleadoDTO dto = new BitacoraEmpleadoDTO();
		dto.setComentarios(comentarios);
		//dto.setEmpleado(empleado);
		dto.setLccActual(lccActual);
		dto.setLccAnterior(lccAnterior);
		dto.setTipoMovimientoEmpleado(tipoMovimientoEmpleado);
		return dto;
	}
}
