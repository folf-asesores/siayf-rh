package mx.gob.saludtlax.rh.puestosautorizados;

import java.util.List;

import mx.gob.saludtlax.rh.empleados.administracion.UbicacionEmpleadoDTO;
import mx.gob.saludtlax.rh.empleados.datolaboral.SolicitudNuevoPuestoDTO;
import mx.gob.saludtlax.rh.vacantes.seleccion.InfoPuestoDTO;

public interface PuestosAutorizadosEmpleados {

	/**
	 * Crea los datos laborales de un nuevo puesto para ser autorizando.
	 * 
	 * @param solicitud
	 *            infomración de la solicitud
	 */
	public void solicitarAperturaVacante(SolicitudNuevoPuestoDTO solicitud);

	public List<InventarioVacanteDTO> consultarInventariosVacantes();

	public List<InfoPuestoDTO> consultaVacantesPorCriterio(FiltroVacanteDTO filtroVacanteDTO);

	public PuestoEmpleadoDTO obtenerInformacionPuesto(Integer idIventario);

	public void ubicarEmpleado(UbicacionEmpleadoDTO ubicacionEmpleadoDTO);

	public PuestoEmpleadoDTO obtenerInformacionPuestoIdEmpleado(Integer idEmpleado);

	public PuestoEmpleadoDTO obtenerInformacionIdPuesto(Integer idPuesto);

	public void modificacionPrograma(Integer idPuesto, Integer idPrograma);

	/**
	 * Consulta el resumen de codigos por contratación activos.
	 */
	public List<ResumenPuestoDTO> consultarDetallesCodigosPorContratacion(Integer tipoContratacion);

	/**
	 * Consulta el listado de empleados asignados a los puestos autorizados.
	 */
	public List<DetallePuestoDTO> consultarEmpleadosInventarioPorContratacion(Integer tipoContratacion);

}
