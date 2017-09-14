/**
 * 
 */
package mx.gob.saludtlax.rh.autorizaciones;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.EnumEstatusAspirante;
import mx.gob.saludtlax.rh.empleados.administracion.BitacoraEmpleadoDTO;
import mx.gob.saludtlax.rh.empleados.administracion.BitacoraModificacionService;
import mx.gob.saludtlax.rh.empleados.administracion.EnumEstatusEmpleado;
import mx.gob.saludtlax.rh.empleados.administracion.EnumTipoEmpleado;
import mx.gob.saludtlax.rh.empleados.datolaboral.ConfiguracionPresupuestal;
import mx.gob.saludtlax.rh.empleados.datolaboral.DetalleConfiguracionPresupuestoDTO;
import mx.gob.saludtlax.rh.empleados.datolaboral.EnumEstatusConfiguracion;
import mx.gob.saludtlax.rh.empleados.movimientos.ConsultaMovimientoService;
import mx.gob.saludtlax.rh.empleados.movimientos.DetalleMovimientoDTO;
import mx.gob.saludtlax.rh.empleados.movimientos.EnumEstatusMovimiento;
import mx.gob.saludtlax.rh.empleados.movimientos.EnumTipoMovimiento;
import mx.gob.saludtlax.rh.empleados.suplencia.DetalleSuplenciaDTO;
import mx.gob.saludtlax.rh.empleados.suplencia.EnumEstatusSuplencia;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.SistemaCodigoError;
import mx.gob.saludtlax.rh.excepciones.SistemaException;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.notificacion.Modulo;
import mx.gob.saludtlax.rh.notificacion.Notificacion;
import mx.gob.saludtlax.rh.notificacion.NotificacionDTO;
import mx.gob.saludtlax.rh.persistencia.AspiranteEntity;
import mx.gob.saludtlax.rh.persistencia.AspiranteRepository;
import mx.gob.saludtlax.rh.persistencia.BuzonAutorizacionesEntity;
import mx.gob.saludtlax.rh.persistencia.BuzonAutorizacionesRepository;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionAprobacionEntity;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionAprobacionRepository;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionPresupuestoEntity;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionPresupuestoRepository;
import mx.gob.saludtlax.rh.persistencia.DetalleBuzonAutorizacionEntity;
import mx.gob.saludtlax.rh.persistencia.DetalleBuzonAutorizacionRepository;
import mx.gob.saludtlax.rh.persistencia.DetalleProgramaRepository;
import mx.gob.saludtlax.rh.persistencia.DetalleSuplenciaEntity;
import mx.gob.saludtlax.rh.persistencia.DetalleSuplenciaRepository;
import mx.gob.saludtlax.rh.persistencia.EmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.EmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.EstatusConfiguracionesEntity;
import mx.gob.saludtlax.rh.persistencia.EstatusConfiguracionesRepository;
import mx.gob.saludtlax.rh.persistencia.EstatusPuestosEntity;
import mx.gob.saludtlax.rh.persistencia.EstatusPuestosRepository;
import mx.gob.saludtlax.rh.persistencia.InterinatoEntity;
import mx.gob.saludtlax.rh.persistencia.InterinatoRepository;
import mx.gob.saludtlax.rh.persistencia.InventarioVacanteEntity;
import mx.gob.saludtlax.rh.persistencia.InventarioVacanteRepository;
import mx.gob.saludtlax.rh.persistencia.MovimientoEmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.MovimientoEmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.OperacionSistemaEntity;
import mx.gob.saludtlax.rh.persistencia.OperacionSistemaRepository;
import mx.gob.saludtlax.rh.persistencia.TipoContratacionEntity;
import mx.gob.saludtlax.rh.persistencia.TipoContratacionRepository;
import mx.gob.saludtlax.rh.persistencia.TipoEmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.TipoEmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.TipoMovimientoEmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.TipoMovimientoEmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.TiposNombramientosEntity;
import mx.gob.saludtlax.rh.persistencia.TiposNombramientosRepository;
import mx.gob.saludtlax.rh.persistencia.UsuarioEntity;
import mx.gob.saludtlax.rh.persistencia.UsuarioRepository;
import mx.gob.saludtlax.rh.puestosautorizados.AperturaVacanteDTO;
import mx.gob.saludtlax.rh.puestosautorizados.CrearPuestoAutorizadoService;
import mx.gob.saludtlax.rh.puestosautorizados.EnumEstatusPuesto;
import mx.gob.saludtlax.rh.puestosautorizados.EnumTipoApertura;
import mx.gob.saludtlax.rh.puestosautorizados.EnumTipoContratacion;
import mx.gob.saludtlax.rh.puestosautorizados.EnumTipoNombramiento;
import mx.gob.saludtlax.rh.puestosautorizados.programas.ConfiguracionDetalleProgramaDTO;
import mx.gob.saludtlax.rh.puestosautorizados.programas.ProgramaService;
import mx.gob.saludtlax.rh.util.FechaUtil;
import mx.gob.saludtlax.rh.util.ValidacionUtil;
import mx.gob.saludtlax.rh.vacantes.seleccion.EnumTipoCandidato;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 10/08/2016 22:21:49
 */
public class AutorizacionesService {
	@Inject
	private AspiranteRepository aspiranteRepository;
	@Inject
	private BitacoraModificacionService bitacoraModificacionService;
	@Inject
	private ConfiguracionAprobacionRepository accionesUsuariosRepository;
	@Inject
	private OperacionSistemaRepository operacionSistemaRepository;
	@Inject
	private DetalleBuzonAutorizacionRepository detalleBuzonAutorizacionRepository;
	@Inject
	private DetalleProgramaRepository detalleProgramaRepository;
	@Inject
	private BuzonAutorizacionesRepository buzonAutorizacionesRepository;
	@Inject
	private ConfiguracionPresupuestoRepository configuracionPresupuestoRepository;
	@Inject
	private ConfiguracionPresupuestal configuracionPresupuestal;
	@Inject
	private DetalleSuplenciaRepository detalleSuplenciaRepository;
	@Inject
	private Notificacion notificacionEJB;
	@Inject
	private CrearPuestoAutorizadoService aperturaVacanteService;
	@Inject
	private UsuarioRepository usuarioRepository;
	@Inject
	private ProgramaService programaService;
	@Inject
	private MovimientoEmpleadoRepository movimientoEmpleadoRepository;
	@Inject
	private InventarioVacanteRepository inventarioVacanteRepository;
	@Inject
	private TipoMovimientoEmpleadoRepository tipoMovimientoEmpleadoRepository;
	@Inject
	private EstatusPuestosRepository estatusPuestoRepository;
	@Inject
	private EstatusConfiguracionesRepository estatusConfiguracionesRepository;
	@Inject
	private EmpleadoRepository empleadoRepository;
	@Inject
	private ConsultaMovimientoService consultaMovimientoService;
	@Inject
	private InterinatoRepository interinatoRepository;
	@Inject
	private TipoEmpleadoRepository tipoEmpleadoRepository;
	@Inject
	private TiposNombramientosRepository tiposNombramientosRepository;
	@Inject
	private TipoContratacionRepository tipoContratacionRepository;

	/**
	 * Inicia el proceso de autorizacion de una accion en especifico ejemplo
	 * apertura vacante.
	 */
	public void iniciarProcesoAprobacion(NuevaAutorizacionDTO dto) {
		List<ConfiguracionAprobacionEntity> usuariosQueDebenAutorizar = new ArrayList<>();
		if (dto.getIdAccion() == EnumTiposAccionesAutorizacion.MOVIMIENTOS_PERSONAL) {
			usuariosQueDebenAutorizar = accionesUsuariosRepository.usuariosPorAccionMovimiento(dto.getIdAccion(),
					dto.getTipoMovimiento());
		} else {
			usuariosQueDebenAutorizar = accionesUsuariosRepository.usuariosPorAccion(dto.getIdAccion());
		}

		OperacionSistemaEntity operacion = operacionSistemaRepository.obtenerPorId(dto.getIdAccion());

		BuzonAutorizacionesEntity buzonAutorizacion = new BuzonAutorizacionesEntity();
		buzonAutorizacion.setAccion(operacion);
		buzonAutorizacion.setFinalizado(false);
		buzonAutorizacion.setIdEntidadContexto(dto.getIdEntidadContexto());
		buzonAutorizacion.setIdUsuario(dto.getIdUsuarioLogeado());
		buzonAutorizacionesRepository.crear(buzonAutorizacion);

		Map<Integer, String> idsDestinatarios = new HashMap<>();

		if (!usuariosQueDebenAutorizar.isEmpty()) {
			for (ConfiguracionAprobacionEntity entity : usuariosQueDebenAutorizar) {
				DetalleBuzonAutorizacionEntity buzon = new DetalleBuzonAutorizacionEntity();
				buzon.setAutorizado(false);
				buzon.setBuzon(buzonAutorizacion);
				buzon.setUsuarioEntity(entity.getUsuario());
				detalleBuzonAutorizacionRepository.crear(buzon);
				idsDestinatarios.put(entity.getUsuario().getIdUsuario(), null);
			}
		}

		Map<String, String> parametros = new HashMap<>();
		parametros.put("idAccion", operacion.getIdOperacion().toString());
		parametros.put("idBuzon", buzonAutorizacion.getIdBuzon().toString());

		UsuarioEntity usuarioEmisor = usuarioRepository.obtenerPorId(buzonAutorizacion.getIdUsuario());
		String cuerpo = "";
		Modulo modulo = Modulo.MI_BUZON;
		if (operacion.getIdOperacion() == EnumTiposAccionesAutorizacion.APERTURA_VACANTE) {
			cuerpo = MessageFormat.format("El usuario {0} ha solicitado la apertura de un",
					usuarioEmisor.getUserName());
		} else if (operacion
				.getIdOperacion() == EnumTiposAccionesAutorizacion.APERTURA_VACANTE_PROGRAMA_FEDERAL_POR_DETALLE) {

			cuerpo = MessageFormat.format("El usuario {0} ha solicitado la apertura de " + dto.getMensajeNotificacion(),
					usuarioEmisor.getUserName());
		} else if (operacion.getIdOperacion() == EnumTiposAccionesAutorizacion.APERTURA_VACANTE_VOLUNTARIO
				|| operacion.getIdOperacion() == EnumTiposAccionesAutorizacion.APERTURA_INTERINATO) {
			cuerpo = MessageFormat.format("El usuario {0} ha solicitado la apertura de " + dto.getMensajeNotificacion(),
					usuarioEmisor.getUserName());
		} else if (operacion.getIdOperacion() == EnumTiposAccionesAutorizacion.MOVIMIENTOS_PERSONAL
				|| operacion.getIdOperacion() == EnumTiposAccionesAutorizacion.SUPLENCIA_POR_RECURSO) {
			cuerpo = MessageFormat.format(
					"El usuario {0} ha solicitado el movimiento de " + dto.getMensajeNotificacion(),
					usuarioEmisor.getUserName());
		} else if (operacion.getIdOperacion() == EnumTiposAccionesAutorizacion.AUTORIZAR_PRODUCTO_NOMINA_ESTATAL) {
			cuerpo = MessageFormat.format("El usuario {0} ha solicitado " + dto.getMensajeNotificacion(),
					usuarioEmisor.getUserName());
			modulo = Modulo.AUTORIZAR_NOMINA;
		} else if (operacion.getIdOperacion() == EnumTiposAccionesAutorizacion.MODIFICACION_SUELDO) {
			cuerpo = MessageFormat.format("El usuario {0} ha modificado el sueldo de   " + dto.getMensajeNotificacion(),
					usuarioEmisor.getUserName());
		}

		NotificacionDTO notificacionDTO = new NotificacionDTO(buzonAutorizacion.getIdUsuario(), modulo,
				operacion.getOperacion(), cuerpo, idsDestinatarios, parametros);

		notificacionEJB.enviar(notificacionDTO);
	}

	/**
	 * Consulta el buzon de autorizaciones solicitadas del usuario
	 */
	protected List<BuzonAutorizacionDTO> solicitudesAutorizacionesUsuario(Integer idUsuario, boolean autorizadas) {

		List<BuzonAutorizacionesEntity> autorizaciones = buzonAutorizacionesRepository
				.solicitudesAutorizacionesUsuarioPorEstatus(idUsuario, autorizadas);
		List<BuzonAutorizacionDTO> lista = new ArrayList<BuzonAutorizacionDTO>();
		if (autorizaciones.isEmpty()) {
			for (BuzonAutorizacionesEntity entity : autorizaciones) {
				BuzonAutorizacionDTO dto = new BuzonAutorizacionDTO();
				dto.setAccion(entity.getAccion().getOperacion());
				dto.setIdBuzonAutorizacion(entity.getIdBuzon());
				if (entity.getAccion().getIdOperacion() == EnumTiposAccionesAutorizacion.APERTURA_VACANTE) {
					ConfiguracionPresupuestoEntity conf = configuracionPresupuestoRepository
							.obtenerPorId(entity.getIdEntidadContexto());
					String descripcion = conf.getTipoContratacion().getTipoContratacion() + "-"
							+ conf.getPuesto().getPuesto() + "-" + conf.getSueldo();
					dto.setDescripcion(descripcion);
				}
				if (entity.isFinalizado()) {
					dto.setFinalizado("AUTORIZADA");
				} else {
					dto.setFinalizado("PROCESO AUTORIZACION");
				}
				lista.add(dto);

			}
		}

		return lista;

	}

	/**
	 * Consulta las autorizaciones por estatus del usuario.
	 * 
	 * @param idUsuario
	 * @param autorizado
	 */
	protected List<BuzonAutorizacionDTO> consultarAutorizacionesUsuarioEstatus(Integer idUsuario, boolean autorizado) {
		List<BuzonAutorizacionesEntity> autorizaciones = detalleBuzonAutorizacionRepository
				.autorizacionesUsuarioEstatus(idUsuario, autorizado);
		List<BuzonAutorizacionDTO> lista = new ArrayList<BuzonAutorizacionDTO>();
		if (!autorizaciones.isEmpty()) {
			for (BuzonAutorizacionesEntity entity : autorizaciones) {
				BuzonAutorizacionDTO dto = new BuzonAutorizacionDTO();
				dto.setAccion(entity.getAccion().getOperacion());
				dto.setIdBuzonAutorizacion(entity.getIdBuzon());
				dto.setIdAccion(entity.getAccion().getIdOperacion());
				if (entity.getAccion().getIdOperacion() == EnumTiposAccionesAutorizacion.APERTURA_VACANTE) {
					ConfiguracionPresupuestoEntity conf = configuracionPresupuestoRepository
							.obtenerPorId(entity.getIdEntidadContexto());
					String descripcion = conf.getTipoContratacion().getTipoContratacion() + "-"
							+ conf.getPuesto().getPuesto() + "-" + conf.getSueldo();
					dto.setDescripcion(descripcion);
				} else if (entity.getAccion()
						.getIdOperacion() == EnumTiposAccionesAutorizacion.APERTURA_VACANTE_PROGRAMA_FEDERAL_POR_DETALLE) {
					String descripcionDetalle = detalleProgramaRepository
							.obtenerDescripcionDetalle(entity.getIdEntidadContexto());
					dto.setDescripcion(descripcionDetalle);
				} else if (entity.getAccion().getIdOperacion() == EnumTiposAccionesAutorizacion.MOVIMIENTOS_PERSONAL) {
					MovimientoEmpleadoEntity movimiento = movimientoEmpleadoRepository
							.obtenerPorId(entity.getIdEntidadContexto());
					String descripcionDetalle = movimiento.getMovimiento().getMovimiento().toUpperCase()
							+ " al empleado " + movimiento.getEmpleado().getNombreCompleto();
					dto.setDescripcion(descripcionDetalle);
				}
				if (entity.isFinalizado()) {
					dto.setFinalizado("AUTORIZADA");
				} else {
					dto.setFinalizado("PROCESO AUTORIZACION");
				}
				lista.add(dto);

			}
		}
		return lista;
	}

	/**
	 * Consulta las autorizaciones por operacion, estatus y usuario
	 * 
	 * @param idUsuario
	 * @param autorizado
	 * @param idOperacion
	 */
	protected List<BuzonAutorizacionDTO> consultarAutorizacionesUsuarioOperacionEstatus(Integer idUsuario,
			boolean autorizado, Integer idOperacion) {
		List<BuzonAutorizacionesEntity> autorizaciones = detalleBuzonAutorizacionRepository
				.autorizacionesUsuarioOperacionEstatus(idUsuario, autorizado, idOperacion);
		List<BuzonAutorizacionDTO> lista = new ArrayList<BuzonAutorizacionDTO>();
		if (!autorizaciones.isEmpty()) {
			for (BuzonAutorizacionesEntity entity : autorizaciones) {
				BuzonAutorizacionDTO dto = new BuzonAutorizacionDTO();
				dto.setAccion(entity.getAccion().getOperacion());
				dto.setIdBuzonAutorizacion(entity.getIdBuzon());
				dto.setIdAccion(entity.getAccion().getIdOperacion());
				if (entity.getAccion().getIdOperacion() == EnumTiposAccionesAutorizacion.APERTURA_VACANTE) {
					ConfiguracionPresupuestoEntity conf = configuracionPresupuestoRepository
							.obtenerPorId(entity.getIdEntidadContexto());
					String descripcion = conf.getTipoContratacion().getTipoContratacion() + "-"
							+ conf.getPuesto().getPuesto() + "-" + conf.getSueldo();
					dto.setDescripcion(descripcion);
				} else if (entity.getAccion()
						.getIdOperacion() == EnumTiposAccionesAutorizacion.APERTURA_VACANTE_PROGRAMA_FEDERAL_POR_DETALLE) {
					String descripcionDetalle = detalleProgramaRepository
							.obtenerDescripcionDetalle(entity.getIdEntidadContexto());
					dto.setDescripcion(descripcionDetalle);
				} else if (entity.getAccion().getIdOperacion() == EnumTiposAccionesAutorizacion.MOVIMIENTOS_PERSONAL) {
					MovimientoEmpleadoEntity movimiento = movimientoEmpleadoRepository
							.obtenerPorId(entity.getIdEntidadContexto());
					String descripcionDetalle = movimiento.getMovimiento().getMovimiento().toUpperCase()
							+ " al empleado " + movimiento.getEmpleado().getNombreCompleto();
					dto.setDescripcion(descripcionDetalle);
				}
				if (entity.isFinalizado()) {
					dto.setFinalizado("AUTORIZADA");
				} else {
					dto.setFinalizado("PROCESO AUTORIZACION");
				}
				lista.add(dto);

			}
		}
		return lista;
	}

	public void aprobarOperacion(AutorizacionDTO autorizacionDTO) {

		BuzonAutorizacionesEntity solicitudAprobacion = buzonAutorizacionesRepository
				.obtenerPorId(autorizacionDTO.getIdBuzon());

		if (solicitudAprobacion != null) {

			DetalleBuzonAutorizacionEntity detalleSolicitud = detalleBuzonAutorizacionRepository
					.detalleBuzonPorBuzonUsuario(autorizacionDTO.getIdBuzon(), autorizacionDTO.getIdUsuario());

			if (detalleSolicitud == null) {
				throw new ReglaNegocioException("El usuario no tiene configurada una aprobación para la operación"
						+ autorizacionDTO.getIdBuzon(), ReglaNegocioCodigoError.SIN_REGISTRO);
			}

			if (detalleSolicitud.isAutorizado()) {
				throw new ReglaNegocioException(
						"El usuario ya ha autorizado la operación" + solicitudAprobacion.getAccion().getOperacion(),
						ReglaNegocioCodigoError.YA_AUTORIZADO);
			}

			detalleSolicitud.setAutorizado(true);
			detalleSolicitud.setFechaAutorizacion(FechaUtil.fechaActual());
			detalleSolicitud.setHoraAutorizacion(FechaUtil.fechaActual());
			detalleBuzonAutorizacionRepository.actualizar(detalleSolicitud);

			List<DetalleBuzonAutorizacionEntity> usuariosPendientesPorAutorizar = detalleBuzonAutorizacionRepository
					.detallesBuzonPorEstatus(autorizacionDTO.getIdBuzon(), false);
			String cuerpo = "";
			UsuarioEntity usuarioEmisor = usuarioRepository.obtenerPorId(autorizacionDTO.getIdUsuario());

			// Si ya no hay usuarios pendientes por autorizar entonces se
			// procede a aprobar la operación.
			if (usuariosPendientesPorAutorizar.isEmpty()) {

				if (solicitudAprobacion.getAccion()
						.getIdOperacion() == EnumTiposAccionesAutorizacion.APERTURA_VACANTE) {

					AperturaVacanteDTO aperturaVacanteDTO = new AperturaVacanteDTO();
					aperturaVacanteDTO.setIdConfiguracionPresupuesto(solicitudAprobacion.getIdEntidadContexto());
					aperturaVacanteDTO.setTipoApertura(EnumTipoApertura.APERTURA_INDIVIDUAL);
					aperturaVacanteService.crearPuestoAutorizado(aperturaVacanteDTO);

					solicitudAprobacion.setFinalizado(true);

					String tipoContratacion = configuracionPresupuestoRepository
							.obtenerTipoContratacionConfiguracion(solicitudAprobacion.getIdEntidadContexto());

					cuerpo = MessageFormat.format("El usuario {0} ha autorizado la apertura de una nueva vacante para "
							+ tipoContratacion.toLowerCase(), usuarioEmisor.getUserName());
				} else if (solicitudAprobacion.getAccion()
						.getIdOperacion() == EnumTiposAccionesAutorizacion.APERTURA_VACANTE_PROGRAMA_FEDERAL_POR_DETALLE) {

					String detallePrograma = detalleProgramaRepository
							.obtenerDescripcionDetalle(solicitudAprobacion.getIdEntidadContexto());
					aperturaVacanteService.crearPuestosProgramaFederal(solicitudAprobacion.getIdEntidadContexto());
					cuerpo = MessageFormat.format("El usuario {0} ha autorizado la apertura de las vacantes para "
							+ detallePrograma.toUpperCase(), usuarioEmisor.getUserName());
				} else if (solicitudAprobacion.getAccion()
						.getIdOperacion() == EnumTiposAccionesAutorizacion.MOVIMIENTOS_PERSONAL) {
					cuerpo = autorizarMovimiento(solicitudAprobacion.getIdEntidadContexto());

				} else if (solicitudAprobacion.getAccion()
						.getIdOperacion() == EnumTiposAccionesAutorizacion.SUPLENCIA_POR_RECURSO) {
					String mensaje = autorizarSuplencia(solicitudAprobacion.getIdEntidadContexto());
					cuerpo = MessageFormat.format("El usuario {0} ha autorizado la " + mensaje,
							usuarioEmisor.getUserName());
				} else if (solicitudAprobacion.getAccion()
						.getIdOperacion() == EnumTiposAccionesAutorizacion.APERTURA_INTERINATO) {
					aprobarInterinato(solicitudAprobacion.getIdEntidadContexto());

					cuerpo = MessageFormat.format("El usuario {0} ha autorizado la apertura de interinato ",
							usuarioEmisor.getUserName());
				} else if (solicitudAprobacion.getAccion()
						.getIdOperacion() == EnumTiposAccionesAutorizacion.MODIFICACION_SUELDO) {
					cuerpo = MessageFormat.format("El usuario {0} se ha enterado de la modificación de salario ",
							usuarioEmisor.getUserName());
				}

			}

			Map<Integer, String> idsDestinatarios = new HashMap<>();
			idsDestinatarios.put(solicitudAprobacion.getIdUsuario(), null);

			Map<String, String> parametros = new HashMap<>();

			NotificacionDTO notificacionDTO = new NotificacionDTO(autorizacionDTO.getIdUsuario(), Modulo.SIN_MODULO,
					"AUTORIZACIÓN", cuerpo, idsDestinatarios, parametros);

			notificacionEJB.enviar(notificacionDTO);
		} else {
			throw new ValidacionException("No se encontraron el buzon de notificaciones especificado",
					ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);
		}
	}

	private void aprobarInterinato(Integer idInterinato) {
		if (!ValidacionUtil.esNumeroPositivo(idInterinato)) {
			throw new ValidacionException("El interinato para aprobación es requerido.",
					ValidacionCodigoError.VALOR_REQUERIDO);
		}
		InterinatoEntity interinato = interinatoRepository.obtenerPorId(idInterinato);
		EstatusConfiguracionesEntity estatus = estatusConfiguracionesRepository
				.obtenerPorId(EnumEstatusConfiguracion.ACTIVO);
		TiposNombramientosEntity nombramiento = tiposNombramientosRepository
				.nombramientoPorId(EnumTipoNombramiento.INTERINOS);
		TipoContratacionEntity tipoContratacion = tipoContratacionRepository
				.obtenerPorId(EnumTipoContratacion.INTERINATO);
		EstatusPuestosEntity estatusPuesto = estatusPuestoRepository.obtenerPorId(EnumEstatusPuesto.EMPLEADO_ACTIVO);

		InventarioVacanteEntity puestoPropietario = interinato.getPuestoPropietario();

		interinato.setIdEmpleadoPropietario(puestoPropietario.getEmpleadoActivo().getIdEmpleado());

		// Crear el puesto.
		ConfiguracionPresupuestoEntity datosLaboralesPropietario = puestoPropietario.getConfiguracion();
		ConfiguracionPresupuestoEntity datosLaboralesInterino = new ConfiguracionPresupuestoEntity();
		datosLaboralesInterino.setDependencia(datosLaboralesPropietario.getDependencia());

		datosLaboralesInterino.setEstatus(estatus);
		datosLaboralesInterino.setFechaAltaConfiguracion(FechaUtil.fechaActual());
		datosLaboralesInterino.setFuenteFinanciamiento(datosLaboralesPropietario.getFuenteFinanciamiento());
		// datosLaboralesInterino.setIdPlaza(idPlaza);
		datosLaboralesInterino.setNombramiento(nombramiento);
		// datosLaboralesInterino.setNumeroEmpleado(numeroEmpleado);
		datosLaboralesInterino.setProyecto(datosLaboralesPropietario.getProyecto());
		datosLaboralesInterino.setPuesto(datosLaboralesPropietario.getPuesto());
		datosLaboralesInterino.setSubfuenteFinanciamiento(datosLaboralesPropietario.getSubfuenteFinanciamiento());
		// datosLaboralesInterino.setSueldo(sueldo);
		datosLaboralesInterino.setTabulador(datosLaboralesInterino.getTabulador());
		datosLaboralesInterino.setTipoContratacion(tipoContratacion);
		datosLaboralesInterino.setTipoRecurso(datosLaboralesInterino.getTipoRecurso());
		datosLaboralesInterino.setUnidadResponsable(datosLaboralesInterino.getUnidadResponsable());

		if (interinato.getTipoCandidato() == EnumTipoCandidato.ASPIRANTE) {
			AspiranteEntity aspiranteEntity = aspiranteRepository.obtenerPorId(interinato.getIdContexto());
			if (aspiranteEntity == null) {
				throw new SistemaException("No se encontró aspirante con identificador " + interinato.getIdContexto(),
						SistemaCodigoError.BUSQUEDA_SIN_RESULTADOS);
			}

			if (aspiranteEntity.getIdEstatus().equals(EnumEstatusAspirante.EMPLEADO)) {
				throw new ReglaNegocioException("El aspirante se encuentra activo como empleado",
						ReglaNegocioCodigoError.YA_REGISTRADO);
			}

			if (empleadoRepository.existeEmpleadoRfc(aspiranteEntity.getRfc())) {
				throw new ReglaNegocioException("El rfc del aspirante está asignado a un empleado.",
						ReglaNegocioCodigoError.RFC_REGISTRADO);
			}

			if (empleadoRepository.existeEmpleadoConCurp(aspiranteEntity.getCurp())) {
				throw new ReglaNegocioException("El curp del aspirante está asignado a un empleado",
						ReglaNegocioCodigoError.CURP_REGISTRADA);
			}

			TipoEmpleadoEntity tipoEmpleado = tipoEmpleadoRepository.obtenerPorId(EnumTipoEmpleado.EMPLEADO);
			EmpleadoEntity nuevoEmpleado = new EmpleadoEntity();
			nuevoEmpleado.setApellidoMaterno(aspiranteEntity.getApellidoMaterno());
			nuevoEmpleado.setApellidoPaterno(aspiranteEntity.getApellidoPaterno());
			nuevoEmpleado.setCorreoElectronico(aspiranteEntity.getCorreoElectronico());
			nuevoEmpleado.setCurp(aspiranteEntity.getCurp());
			nuevoEmpleado.setDireccionCompleta(aspiranteEntity.getDireccionCompleta());
			nuevoEmpleado.setEstadoCivil(aspiranteEntity.getEstadoCivil());
			nuevoEmpleado.setEstatura(aspiranteEntity.getEstatura());
			nuevoEmpleado.setFechaAlta(FechaUtil.fechaActual());
			nuevoEmpleado.setFechaIngreso(interinato.getFechaIngreso());
			nuevoEmpleado.setFechaNacimiento(aspiranteEntity.getFechaNacimiento());
			nuevoEmpleado.setIdEstatus(EnumEstatusEmpleado.ACTIVO);
			// nuevoEmpleado.setIdFoto(idFoto);
			nuevoEmpleado.setIdSexo(aspiranteEntity.getIdSexo());
			nuevoEmpleado.setLugarNacimiento(aspiranteEntity.getLugarNacimiento());
			nuevoEmpleado.setNacionalidad(aspiranteEntity.getNacionalidad());
			nuevoEmpleado.setNombre(aspiranteEntity.getNombre());
			nuevoEmpleado.setNombreCompleto(aspiranteEntity.getNombreCompleto());
			nuevoEmpleado.setNumeroConyuges(aspiranteEntity.getNumeroConyuges());
			nuevoEmpleado.setNumeroCuenta(interinato.getNumeroCuenta());
			nuevoEmpleado.setNumeroHijos(aspiranteEntity.getNumeroHijos());
			nuevoEmpleado.setNumeroOtros(aspiranteEntity.getNumeroOtros());
			nuevoEmpleado.setNumeroPadres(aspiranteEntity.getNumeroPadres());
			// nuevoEmpleado.setOtroParentesco(aspiranteEntity.get);
			nuevoEmpleado.setPaisNacionalidad(aspiranteEntity.getPaisNacionalidad());
			// nuevoEmpleado.setPersonasDependientes(aspiranteEntity.get);
			nuevoEmpleado.setPeso(aspiranteEntity.getPeso());
			nuevoEmpleado.setRfc(aspiranteEntity.getRfc());
			nuevoEmpleado.setTelefono(aspiranteEntity.getTelefono());
			// nuevoEmpleado.setTieneLicencia(aspiranteEntity.get);
			nuevoEmpleado.setTienePersonasDependientes(aspiranteEntity.getTienePersonasDependientes());
			nuevoEmpleado.setTipoSangre(aspiranteEntity.getTipoSangre());
			nuevoEmpleado.setViveCon(aspiranteEntity.getViveCon());
			nuevoEmpleado.setTipoEmpleado(tipoEmpleado);

			empleadoRepository.crear(nuevoEmpleado);

			aspiranteEntity.setEmpleado(nuevoEmpleado);
			aspiranteEntity.setIdEstatus(EnumEstatusAspirante.EMPLEADO);
			aspiranteRepository.actualizar(aspiranteEntity);
			datosLaboralesInterino.setEmpleado(nuevoEmpleado);
		} else if (interinato.getTipoCandidato() == EnumTipoCandidato.EMPLEADO) {
			EmpleadoEntity empleado = empleadoRepository.obtenerPorId(interinato.getIdContexto());
			if (empleado == null) {
				throw new ValidacionException(
						"No se encontró al empleado con identificador " + interinato.getIdContexto(),
						ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);
			}
			empleado.setFechaIngreso(interinato.getFechaIngreso());
			empleado.setIdEstatus(EnumEstatusEmpleado.ACTIVO);
			empleadoRepository.actualizar(empleado);

		}
		configuracionPresupuestoRepository.crear(datosLaboralesInterino);

		// Crear puesto en el inventario
		Integer ultimoFolio = inventarioVacanteRepository
				.ultimoFolioVacanteContratacion(EnumTipoContratacion.INTERINATO);
		Integer siguienteNumeroVacante = 1;
		if (ultimoFolio != null) {
			siguienteNumeroVacante = ultimoFolio + 1;
		}
		String folioVacante = generarFolioVacante(siguienteNumeroVacante, tipoContratacion.getCodigo());

		InventarioVacanteEntity nuevaVacante = new InventarioVacanteEntity();
		nuevaVacante.setCodigoVacante(tipoContratacion.getCodigo());
		nuevaVacante.setConfiguracion(datosLaboralesInterino);
		nuevaVacante.setDisponible("NO");
		nuevaVacante.setFolioVacante(folioVacante);
		nuevaVacante.setEstatus(estatusPuesto);
		nuevaVacante.setNumeroVacante(siguienteNumeroVacante);
		// nuevaVacante.setPrograma(programa);
		// nuevaVacante.setDetallePrograma(detallePrograma);
		nuevaVacante.setTipoContratacion(tipoContratacion);

		nuevaVacante.setProvisional(false);

		inventarioVacanteRepository.crear(nuevaVacante);

		puestoPropietario.setInterino(true);
		inventarioVacanteRepository.actualizar(puestoPropietario);

	}

	private String generarFolioVacante(Integer siguienteNumeroVacante, String codigoContratacion) {
		String folioVacante = "";

		if (siguienteNumeroVacante < 10) {
			folioVacante = codigoContratacion + "-00" + siguienteNumeroVacante;
		} else if (siguienteNumeroVacante < 100) {
			folioVacante = codigoContratacion + "-0" + siguienteNumeroVacante;
		} else {
			folioVacante = codigoContratacion + "-" + siguienteNumeroVacante;
		}

		return folioVacante;
	}

	private String autorizarSuplencia(Integer idDetalleSuplencia) {
		DetalleSuplenciaEntity suplencia = detalleSuplenciaRepository.obtenerPorId(idDetalleSuplencia);
		if (suplencia == null) {
			throw new ValidacionException("No se encontró registro de la suplencia que se requiere aprobar.",
					ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);
		}

		suplencia.setEstatus(EnumEstatusSuplencia.PENDIENTE);
		detalleSuplenciaRepository.actualizar(suplencia);

		String mensaje = " la suplencia por falta de recurso del suplente "
				+ suplencia.getQuincena().getSuplente().getEmpleado().getNombreCompleto();
		return mensaje;
	}

	private String autorizarMovimiento(Integer idMovimiento) {
		MovimientoEmpleadoEntity movimientoEmpleado = movimientoEmpleadoRepository.obtenerPorId(idMovimiento);
		if (movimientoEmpleado == null) {
			throw new ValidacionException("El movimiento con identificador " + idMovimiento + " no se ha encontrado",
					ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);
		}
		TipoMovimientoEmpleadoEntity movimiento = tipoMovimientoEmpleadoRepository
				.obtenerPorId(movimientoEmpleado.getMovimiento().getIdTipoMovimiento());
		InventarioVacanteEntity puesto = movimientoEmpleado.getInventarioVacante();
		EmpleadoEntity empleadoMovimiento = movimientoEmpleado.getEmpleado();
		ConfiguracionPresupuestoEntity configuracionPresupuestal = puesto.getConfiguracion();
		if (movimiento.getIdPadre() == EnumTipoMovimiento.BAJAS_DEFINITIVAS) {
			EstatusPuestosEntity estatusPuesto = estatusPuestoRepository.obtenerPorId(EnumEstatusPuesto.LIBERADA);
			puesto.setDisponible("SI");
			puesto.setUltimoEmpleado(empleadoMovimiento);
			puesto.setEstatus(estatusPuesto);

			inventarioVacanteRepository.actualizar(puesto);

			empleadoMovimiento.setIdEstatus(EnumEstatusEmpleado.INACTIVO);
			empleadoMovimiento.setTipoBaja(movimiento.getIdTipoMovimiento());
			empleadoMovimiento.setFechaBaja(FechaUtil.fechaActual());
			empleadoRepository.actualizar(empleadoMovimiento);

			EstatusConfiguracionesEntity estatus = estatusConfiguracionesRepository
					.obtenerPorId(EnumEstatusConfiguracion.INACTIVO);
			configuracionPresupuestal.setEstatus(estatus);
			configuracionPresupuestoRepository.actualizar(configuracionPresupuestal);

		}
		if (movimiento.getIdPadre() == EnumTipoMovimiento.LICENCIA_POR_INCAPACIDAD_MEDICA
				|| movimiento.getIdPadre() == EnumTipoMovimiento.LICENCIAS_C_C_S
				|| movimiento.getIdPadre() == EnumTipoMovimiento.LICENCIAS_C_S_S
				|| movimiento.getIdPadre() == EnumTipoMovimiento.LICENCIAS_D_C_S
				|| movimiento.getIdPadre() == EnumTipoMovimiento.LICENCIAS_D_S_S) {
			EstatusPuestosEntity estatus = estatusPuestoRepository.obtenerPorId(EnumEstatusPuesto.EMPLEADO_EN_PERMISO);
			puesto.setFechaInicioPermiso(movimientoEmpleado.getFechaInicioPermiso());
			puesto.setFechaFinPermiso(movimientoEmpleado.getFechaFinPermiso());
			puesto.setMovimientoPermiso(movimientoEmpleado);
			puesto.setEstatus(estatus);
			inventarioVacanteRepository.actualizar(puesto);

		} else if (movimiento.getIdPadre() == EnumTipoMovimiento.BAJAS_TEMPORALES) {
			EstatusPuestosEntity estatusPuesto = estatusPuestoRepository
					.obtenerPorId(EnumEstatusPuesto.EMPLEADO_EN_PERMISO);
			puesto.setEstatus(estatusPuesto);
			puesto.setMovimientoPermiso(movimientoEmpleado);
			inventarioVacanteRepository.actualizar(puesto);

		}

		movimientoEmpleado.setFechaAutorizacion(FechaUtil.fechaActual());
		movimientoEmpleado.setEstatusMovimiento(EnumEstatusMovimiento.AUTORIZADO);
		movimientoEmpleadoRepository.actualizar(movimientoEmpleado);

		String mensaje = movimiento.getMovimiento() + " al empleado "
				+ movimientoEmpleado.getEmpleado().getNombreCompleto();
		return mensaje;
	}

	protected DetalleAutorizacionDTO obtenerDetalleAutorizacion(Integer idBuzon) {
		BuzonAutorizacionesEntity buzon = buzonAutorizacionesRepository.obtenerPorId(idBuzon);
		if (buzon == null) {
			throw new ReglaNegocioException("No se ha encontrado solicitud con identificador " + idBuzon,
					ReglaNegocioCodigoError.SIN_REGISTRO);
		}

		DetalleAutorizacionDTO dto = new DetalleAutorizacionDTO();
		dto.setNotificacion(buzon.getAccion().getOperacion());
		if (buzon.getAccion().getIdOperacion() == EnumTiposAccionesAutorizacion.APERTURA_VACANTE
				|| buzon.getAccion().getIdOperacion() == EnumTiposAccionesAutorizacion.APERTURA_INTERINATO) {

			DetalleConfiguracionPresupuestoDTO detalleConfiguracion = configuracionPresupuestal
					.obtenerDetalleConfiguracionId(buzon.getIdEntidadContexto());

			dto.setConfiguracionPresupuesto(detalleConfiguracion);
		} else if (buzon.getAccion()
				.getIdOperacion() == EnumTiposAccionesAutorizacion.APERTURA_VACANTE_PROGRAMA_FEDERAL_POR_DETALLE
				|| buzon.getAccion().getIdOperacion() == EnumTiposAccionesAutorizacion.APERTURA_VACANTE_VOLUNTARIO) {

			ConfiguracionDetalleProgramaDTO configuracionDetallePrograma = programaService
					.obtenerDetallePrograma(buzon.getIdEntidadContexto());
			dto.setConfiguracionDetallePrograma(configuracionDetallePrograma);
		} else if (buzon.getAccion().getIdOperacion() == EnumTiposAccionesAutorizacion.MOVIMIENTOS_PERSONAL) {
			DetalleMovimientoDTO detalleMovimiento = consultaMovimientoService
					.obtenerDetalleMovimiento(buzon.getIdEntidadContexto());
			dto.setDetalleMovimiento(detalleMovimiento);

		} else if (buzon.getAccion().getIdOperacion() == EnumTiposAccionesAutorizacion.SUPLENCIA_POR_RECURSO) {
			DetalleSuplenciaDTO detalle = obtenerDetalleSuplenciaPorId(buzon.getIdEntidadContexto());
			dto.setDetalleSuplencia(detalle);
		} else if (buzon.getAccion().getIdOperacion() == EnumTiposAccionesAutorizacion.MODIFICACION_SUELDO) {
			BitacoraEmpleadoDTO bitacora = bitacoraModificacionService
					.obtenerBitacoraPorId(buzon.getIdEntidadContexto());
			dto.setBitacora(bitacora);
		}

		return dto;

	}

	protected DetalleSuplenciaDTO obtenerDetalleSuplenciaPorId(Integer idDetalleSuplencia) {
		if (idDetalleSuplencia == null) {
			throw new ValidacionException("", ValidacionCodigoError.VALOR_REQUERIDO);
		}
		DetalleSuplenciaEntity detalle = detalleSuplenciaRepository.obtenerPorId(idDetalleSuplencia);
		if (detalle == null) {
			throw new ValidacionException("No se encontró registro del detalle de la suplencia",
					ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);
		}

		DetalleSuplenciaDTO dto = new DetalleSuplenciaDTO();
		dto.setCantidadDiaria(detalle.getCantidadDiaria());
		dto.setDias(detalle.getDias());
		dto.setEstatus(detalle.getEstatus());
		dto.setFechaFin(detalle.getFechaFin());
		dto.setFechaInicio(detalle.getFechaInicio());
		dto.setNombreSuplente(detalle.getQuincena().getSuplente().getEmpleado().getNombreCompleto());
		dto.setNumeroQuincena(detalle.getQuincena().getNumeroQuincena());
		dto.setTipoSuplencia(detalle.getTipoSuplencia().getCausaSuplencia());
		dto.setTotal(detalle.getTotal());

		return dto;
	}

	protected List<Integer> consultarReceptores(Integer idBuzon) {
		return detalleBuzonAutorizacionRepository.consultarReceptores(idBuzon);
	}

	public Integer obtenerIdEntidadContexto(Integer idBuzon) {
		BuzonAutorizacionesEntity buzon = buzonAutorizacionesRepository.obtenerPorId(idBuzon);
		if (buzon == null) {
			throw new ReglaNegocioException("No se ha encontrado solicitud con identificador " + idBuzon,
					ReglaNegocioCodigoError.SIN_REGISTRO);
		}
		return buzon.getIdEntidadContexto();
	}

	public Boolean esUsuarioAutoriza(Integer idOperacion, Integer idEntidadContexto, Integer idUsuario) {
		OperacionSistemaEntity operacion = operacionSistemaRepository.obtenerPorId(idOperacion);
		return buzonAutorizacionesRepository.esUsuarioAutorizaNomina(operacion, idEntidadContexto, idUsuario);
	}
}