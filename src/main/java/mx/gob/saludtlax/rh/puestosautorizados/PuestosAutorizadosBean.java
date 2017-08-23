/**
 * 
 */
package mx.gob.saludtlax.rh.puestosautorizados;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import mx.gob.saludtlax.rh.empleados.administracion.UbicacionEmpleadoDTO;
import mx.gob.saludtlax.rh.empleados.datolaboral.SolicitudNuevoPuestoDTO;
import mx.gob.saludtlax.rh.vacantes.seleccion.InfoPuestoDTO;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 25/07/2016 14:19:33
 */
@Stateless
public class PuestosAutorizadosBean implements PuestosAutorizadosEmpleados {

	@Inject
	private ConsultaPuestoService consultaPuestoService;
	@Inject
	private EstructurasService estructurasService;
	@Inject
	private PuestoAutorizadoService puestoAutorizadoService;
	@Inject
	private PuestosEmpleadosService puestosEmpleadoService;

	@Inject
	private ConsultaPuestoService consultaVacanteService;

	@Interceptors({ SolicitudAperturaValidator.class })
	@Override
	public void solicitarAperturaVacante(SolicitudNuevoPuestoDTO solicitudPuestoDTO) {
		puestoAutorizadoService.solicitarCreacionPuesto(solicitudPuestoDTO);

	}

	@Override
	public List<InventarioVacanteDTO> consultarInventariosVacantes() {

		return consultaVacanteService.globalPuestos();
	}

	@Override
	public List<InfoPuestoDTO> consultaVacantesPorCriterio(FiltroVacanteDTO filtroVacanteDTO) {

		return consultaVacanteService.consultaVacantesPorCriterio(filtroVacanteDTO);
	}

	@Override
	public PuestoEmpleadoDTO obtenerInformacionPuesto(Integer idIventario) {
		return consultaVacanteService.obtenerInformacionPuesto(idIventario);
	}

	@Override
	public void ubicarEmpleado(UbicacionEmpleadoDTO ubicacionEmpleadoDTO) {
		puestosEmpleadoService.ubicarEmpleado(ubicacionEmpleadoDTO);

	}

	@Override
	public PuestoEmpleadoDTO obtenerInformacionPuestoIdEmpleado(Integer idEmpleado) {
		return consultaPuestoService.obtenerInformacionPuestoIdEmpleado(idEmpleado);
	}

	@Override
	public PuestoEmpleadoDTO obtenerInformacionIdPuesto(Integer idPuesto) {

		return consultaPuestoService.obtenerInformacionPuesto(idPuesto);
	}

	@Override
	public void modificacionPrograma(Integer idPuesto, Integer idPrograma) {
		puestosEmpleadoService.modificacionPrograma(idPuesto, idPrograma);

	}

	@Override
	public List<ResumenPuestoDTO> consultarDetallesCodigosPorContratacion(Integer tipoContratacion) {
		return consultaPuestoService.consultarDetallesCodigosPorContratacion(tipoContratacion);
	}

	@Override
	public List<DetallePuestoDTO> porContratacionYEstatus(Integer tipoContratacion, Integer idEstatus) {
		return consultaPuestoService.porContratacionYEstatus(tipoContratacion, idEstatus);
	}

	@Override
	public void modificarEstructuraNominaContratos(EstructuraContratoDTO estructuraContratoDTO) {
		estructurasService.actualizarEstructuraContrato(estructuraContratoDTO);
	}

	@Override
	public EstructuraContratoDTO obtenerEstructuraContratoPuesto(Integer idPuesto) {
		return consultaPuestoService.obtenerEstructuraContratoPuesto(idPuesto);
	}

	@Interceptors({ EstructuraFederalValidator.class })
	@Override
	public void actualizarEstructuraNomina(EstructuraNominaDTO estructuraNominaDTO) {
		estructurasService.actualizarEstructuraNomina(estructuraNominaDTO);
	}

	@Override
	public EstructuraNominaDTO obtenerEstructuraNominaPuesto(Integer idPuesto) {
		return estructurasService.obtenerEstructuraNominaPuesto(idPuesto);
	}

	@Override
	public DetallePuestoDTO obtenerPuesto(Integer idPuesto) {
		return consultaPuestoService.obtenerPuesto(idPuesto);
	}

}
