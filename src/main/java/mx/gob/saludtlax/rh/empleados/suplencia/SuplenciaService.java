/**
 * 
 */
package mx.gob.saludtlax.rh.empleados.suplencia;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.joda.time.DateTime;

import mx.gob.saludtlax.rh.autorizaciones.AutorizacionesService;
import mx.gob.saludtlax.rh.autorizaciones.EnumTiposAccionesAutorizacion;
import mx.gob.saludtlax.rh.autorizaciones.NuevaAutorizacionDTO;
import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.DireccionDTO;
import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.EnumNacionalidad;
import mx.gob.saludtlax.rh.empleados.administracion.EnumEstatusEmpleado;
import mx.gob.saludtlax.rh.empleados.administracion.EnumTipoEmpleado;
import mx.gob.saludtlax.rh.empleados.datolaboral.DatoLaboralDTO;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.SeguridadCodigoError;
import mx.gob.saludtlax.rh.excepciones.SeguridadException;
import mx.gob.saludtlax.rh.excepciones.SistemaCodigoError;
import mx.gob.saludtlax.rh.excepciones.SistemaException;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.persistencia.AsentamientoEntity;
import mx.gob.saludtlax.rh.persistencia.AsentamientoRepository;
import mx.gob.saludtlax.rh.persistencia.CentroResponsabilidadEntity;
import mx.gob.saludtlax.rh.persistencia.CentroResponsabilidadRepository;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionQuincenaRepository;
import mx.gob.saludtlax.rh.persistencia.DependenciaTempEntity;
import mx.gob.saludtlax.rh.persistencia.DependenciaTempRepository;
import mx.gob.saludtlax.rh.persistencia.DetalleSuplenciaEntity;
import mx.gob.saludtlax.rh.persistencia.DetalleSuplenciaRepository;
import mx.gob.saludtlax.rh.persistencia.DireccionEntity;
import mx.gob.saludtlax.rh.persistencia.DireccionRepository;
import mx.gob.saludtlax.rh.persistencia.EmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.EmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.EstadoEntity;
import mx.gob.saludtlax.rh.persistencia.EstadoRepository;
import mx.gob.saludtlax.rh.persistencia.InventarioVacanteRepository;
import mx.gob.saludtlax.rh.persistencia.MunicipioRepository;
import mx.gob.saludtlax.rh.persistencia.MunicipiosEntity;
import mx.gob.saludtlax.rh.persistencia.ProyectoTempEntity;
import mx.gob.saludtlax.rh.persistencia.ProyectoTempRepository;
import mx.gob.saludtlax.rh.persistencia.QuincenaActivaSuplenciaEntity;
import mx.gob.saludtlax.rh.persistencia.QuincenaActivaSuplenciaRepository;
import mx.gob.saludtlax.rh.persistencia.QuincenasSuplenciasEntity;
import mx.gob.saludtlax.rh.persistencia.QuincenasSuplenciasRepository;
import mx.gob.saludtlax.rh.persistencia.SuplenteAutorizadoEntity;
import mx.gob.saludtlax.rh.persistencia.SuplenteAutorizadoRepository;
import mx.gob.saludtlax.rh.persistencia.TipoEmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.TipoEmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.TipoJornadaSuplenciaEntity;
import mx.gob.saludtlax.rh.persistencia.TipoJornadaSuplenciaRepository;
import mx.gob.saludtlax.rh.persistencia.TipoSuplenciaEntity;
import mx.gob.saludtlax.rh.persistencia.TipoSuplenciaRepository;
import mx.gob.saludtlax.rh.persistencia.UnidadResponsableEntity;
import mx.gob.saludtlax.rh.persistencia.UnidadResponsableRepository;
import mx.gob.saludtlax.rh.persistencia.UsuarioEntity;
import mx.gob.saludtlax.rh.persistencia.UsuarioRepository;
import mx.gob.saludtlax.rh.util.FechaUtil;
import mx.gob.saludtlax.rh.util.ValidacionUtil;
import mx.gob.saludtlax.rh.vacantes.seleccion.EnumTipoCandidato;

/**
 * @author Leila Schiaffini Ehuan
 * @since 2016-10-18
 * 
 */
public class SuplenciaService {

	@Inject
	private AutorizacionesService autorizacionesService;

	@Inject
	private ConfiguracionQuincenaRepository configuracionQuincenaRepository;

	@Inject
	private DetalleSuplenciaRepository detalleSuplenciaRepository;
	@Inject
	private EmpleadoRepository empleadoRepository;
	@Inject
	private SuplenteAutorizadoRepository suplenteAutorizadoRepository;
	@Inject
	private TipoEmpleadoRepository tipoEmpleadoRepository;
	@Inject
	private TipoSuplenciaRepository tipoSuplenciaRepository;
	@Inject
	private QuincenasSuplenciasRepository quincenaSuplenciaRepository;
	@Inject
	private CentroResponsabilidadRepository centroResponsabilidadRepository;
	@Inject
	private DependenciaTempRepository dependenciasRepository;

	@Inject
	private ProyectoTempRepository proyectoRepository;
	@Inject
	private UnidadResponsableRepository unidadResponsableRepository;
	@Inject
	private UsuarioRepository usuarioRepository;
	@Inject
	private AsentamientoRepository poblacionRepository;
	@Inject
	private EstadoRepository estadoRepository;
	@Inject
	private MunicipioRepository municipioRepository;
	@Inject
	private DireccionRepository direccionRepository;
	@Inject
	private InventarioVacanteRepository inventarioVacanteRepository;
	@Inject
	private QuincenaActivaSuplenciaRepository quincenaActivaRepository;
	@Inject
	private TipoJornadaSuplenciaRepository tipoJornadaSuplenciaRepository;

	protected void crearSuplente(RegistroSuplenteDTO registroSuplente) {

		SuplenteAutorizadoEntity suplenteAutorizado = new SuplenteAutorizadoEntity();
		suplenteAutorizado.setEstatus("ACTIVO");
		suplenteAutorizado.setFechaAlta(FechaUtil.fechaActual());
		suplenteAutorizado.setHoraAlta(FechaUtil.fechaActual());

		if (registroSuplente.getIdTipoCandidato() == EnumTipoCandidato.ASPIRANTE) {
			AltaSuplenteDTO aspiranteEntity = registroSuplente.getSuplente();
			DireccionDTO dir = registroSuplente.getSuplente().getDireccion();
			EstadoEntity estadoEntity = estadoRepository.estadoPorId(dir.getIdEstado());

			MunicipiosEntity municipiosEntity = municipioRepository.obtenerPorId(dir.getIdMunicipio());
			AsentamientoEntity poblacionEntity = poblacionRepository.obtenerPorId(dir.getIdAsentamiento());

			if (empleadoRepository.existeEmpleadoRfc(aspiranteEntity.getRfc().trim())) {
				throw new ReglaNegocioException(
						"El rfc " + aspiranteEntity.getRfc()
								+ " está asignado a un empleado, ingrese uno nuevo o seleccione el tipo empleado para la habilitación.",
						ReglaNegocioCodigoError.RFC_REGISTRADO);
			}

			if (empleadoRepository.existeEmpleadoConCurp(aspiranteEntity.getCurp().trim())) {
				throw new ReglaNegocioException(
						"El curp " + aspiranteEntity.getCurp()
								+ " está asignado a un empleado, ingrese uno nuevo o seleccione el tipo empleado para la habilitación.",
						ReglaNegocioCodigoError.CURP_REGISTRADA);
			}

			if (empleadoRepository
					.existeEmpleadoConNumeroPersonal(registroSuplente.getSuplente().getNumeroPersonal())) {
				throw new ReglaNegocioException("El número personal ya está asignado a un empleado.",
						ReglaNegocioCodigoError.NUMERO_EMPLEADO);
			}

			// Registrando Dirección
			DireccionEntity direccion = new DireccionEntity();
			direccion.setCalle(dir.getCalle());
			direccion.setNumeroExterior(dir.getNumeroExterior());
			direccion.setNumeroInterior(dir.getNumeroInterior());
			direccion.setCodigoPostal(dir.getCodigoPostal());
			direccion.setEstado(estadoEntity);
			direccion.setMunicipio(municipiosEntity);
			direccion.setAsentamiento(poblacionEntity);
			direccionRepository.crear(direccion);

			TipoEmpleadoEntity tipoEmpleado = tipoEmpleadoRepository.obtenerPorId(EnumTipoEmpleado.SUPLENTE);

			EmpleadoEntity nuevoEmpleado = new EmpleadoEntity();
			nuevoEmpleado.setApellidoMaterno(aspiranteEntity.getApellidoMaterno());
			nuevoEmpleado.setApellidoPaterno(aspiranteEntity.getApellidoPaterno());
			nuevoEmpleado.setCurp(aspiranteEntity.getCurp());
			nuevoEmpleado.setDireccionCompleta(direccion.direccionCompleta());
			nuevoEmpleado.setFechaAlta(FechaUtil.fechaActual());
			nuevoEmpleado.setFechaIngreso(FechaUtil.fechaActual());
			nuevoEmpleado.setFechaNacimiento(aspiranteEntity.getFechaNacimiento());
			nuevoEmpleado.setIdEstatus(EnumEstatusEmpleado.ACTIVO);
			nuevoEmpleado.setIdSexo(aspiranteEntity.getSexo());
			nuevoEmpleado.setNacionalidad(EnumNacionalidad.MEXICANA);
			nuevoEmpleado.setNombre(aspiranteEntity.getNombre());
			nuevoEmpleado.setNombreCompleto(nuevoEmpleado.nombreCompleto());
			nuevoEmpleado.setNumeroConyuges(0);
			nuevoEmpleado.setNumeroCuenta(registroSuplente.getNumeroCuenta());
			nuevoEmpleado.setNumeroHijos(0);
			nuevoEmpleado.setNumeroOtros(0);
			nuevoEmpleado.setNumeroPadres(0);
			nuevoEmpleado.setRfc(aspiranteEntity.getRfc());
			nuevoEmpleado.setTelefono(aspiranteEntity.getTelefono());
			nuevoEmpleado.setTienePersonasDependientes(false);
			nuevoEmpleado.setTipoEmpleado(tipoEmpleado);
			nuevoEmpleado.setIdBanco(registroSuplente.getIdBanco());
			nuevoEmpleado.setNumeroCuenta(registroSuplente.getNumeroCuenta());
			nuevoEmpleado.setIdMetodoPago(registroSuplente.getIdMetodoPago());
			nuevoEmpleado.setNumeroEmpleado(registroSuplente.getSuplente().getNumeroPersonal());

			empleadoRepository.crear(nuevoEmpleado);

			direccion.setIdEmpleado(nuevoEmpleado.getIdEmpleado());
			direccionRepository.actualizar(direccion);

			suplenteAutorizado.setEmpleado(nuevoEmpleado);

		} else if (registroSuplente.getIdTipoCandidato() == EnumTipoCandidato.EMPLEADO) {

			EmpleadoEntity empleado = empleadoRepository.obtenerPorId(registroSuplente.getIdEmpleado());
			if (empleado == null) {
				throw new ValidacionException("El empleado candidato no está registrado",
						ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);

			}

			if (suplenteAutorizadoRepository.esEmpleadoActivoComoSuplente(empleado.getIdEmpleado())) {
				throw new ReglaNegocioException("El empleado ya se encuentra habilitado como suplente",
						ReglaNegocioCodigoError.EMPLEADO_HABILITADO);
			}
			if (empleado.getIdEstatus().equals(EnumEstatusEmpleado.INACTIVO)) {
				empleado.setIdEstatus(EnumEstatusEmpleado.ACTIVO);
			}

			suplenteAutorizado.setEmpleado(empleado);

			if (inventarioVacanteRepository.tieneEmpleadoPuestoAsignado(empleado.getIdEmpleado())) {
				TipoEmpleadoEntity tipoEmpleado = tipoEmpleadoRepository.obtenerPorId(EnumTipoEmpleado.SUPLENTE);
				empleado.setTipoEmpleado(tipoEmpleado);
			}

			empleadoRepository.actualizar(empleado);

		} else {
			throw new ValidacionException("El tipo de suplente especificado no es requerido.",
					ValidacionCodigoError.VALOR_NO_PERMITIDO);
		}

		ProyectoTempEntity proyecto = proyectoRepository.obtenerPorId(registroSuplente.getIdProyecto());
		DependenciaTempEntity dependencia = dependenciasRepository.obtenerPorId(registroSuplente.getIdDependencia());
		UnidadResponsableEntity unidadResponsable = unidadResponsableRepository
				.obtenerPorId(registroSuplente.getIdUnidadResponsable());
		CentroResponsabilidadEntity centroResponsabilidadEntity = centroResponsabilidadRepository
				.obtenerPorId(registroSuplente.getIdCentroResponsabilidad());
		suplenteAutorizado.setProyecto(proyecto);
		suplenteAutorizado.setDependencia(dependencia);
		suplenteAutorizado.setUnidadResponsable(unidadResponsable);
		suplenteAutorizado.setCentroResponsabilidad(centroResponsabilidadEntity);
		suplenteAutorizado.setNumeroLaboral(registroSuplente.getNumeroLaboral());
		suplenteAutorizado.setNumeroPersonal(registroSuplente.getSuplente().getNumeroPersonal());
		suplenteAutorizado.setMetodoPago(registroSuplente.getIdMetodoPago());

		suplenteAutorizadoRepository.crear(suplenteAutorizado);

	}

	protected List<SuplenteDTO> consultarSuplentesPorCriterio(FiltroSuplenciaDTO filtro) {
		if (filtro == null) {
			throw new ValidacionException("Los datos de la consulta son requeridos.",
					ValidacionCodigoError.VALOR_REQUERIDO);
		}
		if (!ValidacionUtil.esNumeroPositivo(filtro.getTipoConsulta())) {
			throw new ValidacionException("El tipo de filtro es requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
		}

		List<SuplenteDTO> suplentes = new ArrayList<SuplenteDTO>();

		if (filtro.getTipoConsulta() == EnumTipoConsultaSuplencia.NOMBRE) {
			if (ValidacionUtil.esCadenaVacia(filtro.getCriterio())) {
				throw new ValidacionException("El criterio de búsqueda es requerido.",
						ValidacionCodigoError.VALOR_REQUERIDO);
			}
			suplentes = suplenteAutorizadoRepository.consultarSuplentesPorCriterio(filtro.getCriterio());
		} else if (filtro.getTipoConsulta() == EnumTipoConsultaSuplencia.ACTIVO) {
			suplentes = suplenteAutorizadoRepository.consultarSuplentesPorIdEstatus("ACTIVO");
		} else if (filtro.getTipoConsulta() == EnumTipoConsultaSuplencia.INACTIVO) {
			suplentes = suplenteAutorizadoRepository.consultarSuplentesPorIdEstatus("INACTIVO");
		}

		return suplentes;

	}

	protected int obtenerNumeroQuincenaActual() {
		DateTime fechaActual = FechaUtil.fechaActualSinTiempo();
		int mes = fechaActual.getMonthOfYear();
		int dia = fechaActual.getDayOfMonth();
		int numeroQuincena = configuracionQuincenaRepository.obtenerConfiguracionQuincena(mes, dia);
		return numeroQuincena;

	}

	protected List<DetalleSuplenciaDTO> consultarDetallesSuplenteQuincena(ConsultaSuplenciaDTO consulta) {

		List<DetalleSuplenciaDTO> detalles = new ArrayList<DetalleSuplenciaDTO>();
		List<DetalleSuplenciaEntity> detallesQuincena = new ArrayList<>();
		QuincenasSuplenciasEntity quincena = null;

		if (consulta.getTipoConsulta() == 1 || consulta.getTipoConsulta() == 2) {
			quincena = quincenaSuplenciaRepository.obtenerQuincenaSuplente(consulta.getQuincena(),
					FechaUtil.ejercicioActual(), consulta.getIdSuplenteAutorizado());

			if (quincena != null) {

				if (consulta.getTipoConsulta() == 1) {

					detallesQuincena = detalleSuplenciaRepository
							.consultarDetallesSuplenciaIdQuincena(quincena.getIdQuincenaSuplencia());
				} else if (consulta.getTipoConsulta() == 2) {

					detallesQuincena = detalleSuplenciaRepository.consultarDetallesQuincenaPorEstatus(
							quincena.getIdQuincenaSuplencia(), consulta.getEstatus());

				}

			}

		} else {
			if (!ValidacionUtil.esNumeroPositivo(consulta.getIdQuincena())) {
				throw new ValidacionException("Es requerida la quincena para obtener el detalle.",
						ValidacionCodigoError.VALOR_REQUERIDO);
			}
			detallesQuincena = detalleSuplenciaRepository
					.consultarDetallesSuplenciaIdQuincena(consulta.getIdQuincena());
			quincena = quincenaSuplenciaRepository.obtenerPorId(consulta.getIdQuincena());

		}

		if (!detallesQuincena.isEmpty()) {
			for (DetalleSuplenciaEntity d : detallesQuincena) {
				DetalleSuplenciaDTO dto = new DetalleSuplenciaDTO();
				dto.setCantidadDiaria(d.getCantidadDiaria());
				dto.setDias(d.getDias());
				dto.setFechaFin(d.getFechaFin());
				dto.setFechaInicio(d.getFechaInicio());
				dto.setIdTipoSuplencia(d.getTipoSuplencia().getIdCausaSuplenca());
				dto.setEstatus(d.getEstatus());
				if (d.getEmpleado() != null) {
					dto.setIdEmpleado(d.getEmpleado().getIdEmpleado());
					dto.setNombreEmpleado(d.getEmpleado().getNombreCompleto());
				}
				dto.setTipoSuplencia(d.getTipoSuplencia().getCausaSuplencia());
				dto.setNumeroQuincena(consulta.getQuincena());
				dto.setTotal(d.getTotal());
				dto.setIdDetalleSuplencia(d.getIdDetalleSuplencia());
				dto.setIdSuplenteAutorizado(consulta.getIdSuplenteAutorizado());
				dto.setIdEmpleadoSuplente(quincena.getSuplente().getEmpleado().getIdEmpleado());
				dto.setIdQuincena(quincena.getIdQuincenaSuplencia());
				if (d.isModificada()) {
					dto.setConDescuento("SI");
				} else {
					dto.setConDescuento("NO");
				}
				dto.setObservaciones(d.getObservaciones());
				dto.setIdJornada(d.getJornada().getId());
				dto.setJornada(d.getJornada().getJornada());
				dto.setIdTabulador(d.getIdTabulador());
				dto.setDias(d.getDias());

				if (d.getTipoSuplencia().getIdCausaSuplenca() == EnumTipoSuplencia.INCAPACIDADES
						|| d.getTipoSuplencia().getIdCausaSuplenca() == EnumTipoSuplencia.LICENCIA_CON_SUELDO
						|| d.getTipoSuplencia().getIdCausaSuplenca() == EnumTipoSuplencia.LICENCIA_SIN_SUELDO
						|| d.getTipoSuplencia().getIdCausaSuplenca() == EnumTipoSuplencia.COMISION) {
					// DetalleMovimientoDTO movimiento =
					// consultaMovimientoService
					// .toDetalleMovimiento(d.getMovimientoEmpleado());
					// dto.setDetalleMovimiento(movimiento);

				}
				detalles.add(dto);

			}

		}

		return detalles;
	}

	protected int obtenerUltimoDiaMes(int mes) {
		return configuracionQuincenaRepository.obtenerUltimoDiaMes(mes);
	}

	protected Integer registrarSuplencia(AltaSuplenciaDTO dto) {
		SuplenteAutorizadoEntity suplente = suplenteAutorizadoRepository.obtenerPorId(dto.getIdSuplenteAutorizado());
		TipoSuplenciaEntity tipoSuplencia = tipoSuplenciaRepository.obtenerPorId(dto.getIdCausaSuplencia());

		QuincenasSuplenciasEntity quincena = quincenaSuplenciaRepository.obtenerQuincenaSuplente(
				dto.getNumeroQuincena(), FechaUtil.ejercicioActual(), dto.getIdSuplenteAutorizado());
		UsuarioEntity usuario = usuarioRepository.obtenerPorId(dto.getIdUsuarioLogeado());
		TipoJornadaSuplenciaEntity jornada = tipoJornadaSuplenciaRepository.obtenerPorId(dto.getIdJornada());
		DetalleSuplenciaEntity d = new DetalleSuplenciaEntity();

		if (usuario == null) {
			throw new SeguridadException("No se encontró usuario con identificador " + dto.getIdUsuarioLogeado(),
					SeguridadCodigoError.USUARIO_INACTIVO);
		}

		if (!usuario.isActivo()) {
			throw new SeguridadException("El usuario está inactivo, no puede realizar la operación",
					SeguridadCodigoError.USUARIO_INACTIVO);
		}

		if (dto.getIdCausaSuplencia() != EnumTipoSuplencia.AREA_DESCUBIERTA) {
			EmpleadoEntity empleado = empleadoRepository.obtenerPorId(dto.getIdEmpleado());
			if (empleado == null) {
				throw new ValidacionException("No se encontró registro del empleado " + dto.getIdEmpleado(),
						ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);
			}
			/*
			 * if (empleado.getIdEstatus().equals(EnumEstatusEmpleado.INACTIVO))
			 * { throw new ReglaNegocioException( "El empleado " +
			 * empleado.getNombreCompleto().toLowerCase() + " está inactivo.",
			 * ReglaNegocioCodigoError.EMPLEADO_INACTIVO); }
			 */
			d.setEmpleado(empleado);
		}

		Integer idQuincena = null;

		if (quincena == null) {
			int mesQuincena = configuracionQuincenaRepository.mesPorNumQuincena(dto.getNumeroQuincena());
			QuincenasSuplenciasEntity nuevaQuincena = new QuincenasSuplenciasEntity();
			nuevaQuincena.setNumeroQuincena(dto.getNumeroQuincena());
			nuevaQuincena.setEjercicioFiscal(dto.getEjercicioFiscal());
			nuevaQuincena.setSuplente(suplente);
			nuevaQuincena.setEstatus(EnumEstatusSuplencia.CAPTURA);
			nuevaQuincena.setIdMes(mesQuincena);
			quincenaSuplenciaRepository.crear(nuevaQuincena);
			d.setQuincena(nuevaQuincena);
			idQuincena = nuevaQuincena.getIdQuincenaSuplencia();
		} else {

			int numeroDias = FechaUtil.calcularDias(dto.getFechaInicio(), dto.getFechaFin());
			DateTime fecha = new DateTime(dto.getFechaInicio());
			for (int i = 1; i <= numeroDias; i++) {
				if (detalleSuplenciaRepository.haSuplidoFecha(fecha.toDate(), suplente.getIdSuplenteAutorizado(),
						jornada.getId())) {
					throw new ReglaNegocioException("El suplente tiene registrada una suplencia en la fecha"
							+ FechaUtil.formatoFecha(fecha.toDate()), ReglaNegocioCodigoError.FECHA_INCORRECTA);
				}
				fecha = fecha.plusDays(1);
			}

			d.setQuincena(quincena);
			idQuincena = quincena.getIdQuincenaSuplencia();
		}

		d.setCantidadDiaria(dto.getCantidadDiaria());
		d.setDias(dto.getDias());
		d.setFechaFin(dto.getFechaFin());
		d.setFechaInicio(dto.getFechaInicio());
		d.setMovimientoEmpleado(null);
		d.setTipoSuplencia(tipoSuplencia);
		d.setTotal(dto.getTotal());
		d.setFechaAlta(FechaUtil.fechaActual());
		d.setHoraAlta(FechaUtil.fechaActual());
		d.setUsuarioAlta(usuario);
		d.setObservaciones(dto.getObservaciones());
		d.setIdTabulador(dto.getIdTabulador());
		d.setJornada(jornada);

		if (dto.getIdCausaSuplencia() == EnumTipoSuplencia.AREA_DESCUBIERTA) {
			d.setEstatus(EnumEstatusSuplencia.PENDIENTE_AUTORIZACION);
		} else {
			d.setEstatus(EnumEstatusSuplencia.PENDIENTE);
		}

		detalleSuplenciaRepository.crear(d);

		if (dto.getIdCausaSuplencia() == EnumTipoSuplencia.AREA_DESCUBIERTA) {
			String mensaje = " la suplencia de "
					+ d.getQuincena().getSuplente().getEmpleado().getNombreCompleto().toLowerCase().toLowerCase()
					+ " por falta de recurso";
			NuevaAutorizacionDTO autorizacion = new NuevaAutorizacionDTO();
			autorizacion.setIdAccion(EnumTiposAccionesAutorizacion.SUPLENCIA_POR_RECURSO);
			autorizacion.setIdEntidadContexto(d.getIdDetalleSuplencia());
			autorizacion.setIdUsuarioLogeado(dto.getIdUsuarioLogeado());
			autorizacion.setMensajeNotificacion(mensaje);
			autorizacionesService.iniciarProcesoAprobacion(autorizacion);
		}
		return idQuincena;
	}

	protected List<DetalleSuplenciaDTO> consultarQuincenasSuplente(int quincena, int ejercicio, String estatus) {

		if (!ValidacionUtil.esNumeroPositivo(quincena)) {
			throw new ValidacionException("La quincena es requerida", ValidacionCodigoError.VALOR_REQUERIDO);
		}
		if (!ValidacionUtil.esNumeroPositivo(ejercicio)) {
			throw new ValidacionException("El año de la quincena es requerido", ValidacionCodigoError.VALOR_REQUERIDO);
		}

		if (ValidacionUtil.esCadenaVacia(estatus)) {
			throw new ValidacionException("El estatus  es requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
		}

		List<QuincenasSuplenciasEntity> quincenas = quincenaSuplenciaRepository.consultarSuplenciasPorQuincena(quincena,
				ejercicio, estatus);

		List<DetalleSuplenciaDTO> detalles = new ArrayList<DetalleSuplenciaDTO>();
		if (!quincenas.isEmpty()) {
			for (QuincenasSuplenciasEntity e : quincenas) {
				DetalleSuplenciaDTO dto = new DetalleSuplenciaDTO();
				dto.setIdSuplenteAutorizado(e.getSuplente().getIdSuplenteAutorizado());
				dto.setNombreSuplente(e.getSuplente().getEmpleado().getNombreCompleto());
				dto.setNumeroQuincena(e.getNumeroQuincena());
				dto.setTotal(e.getTotal());
				dto.setEstatus(e.getEstatus());
				dto.setIdQuincena(e.getIdQuincenaSuplencia());
				dto.setFechaCierre(e.getFechaCierre());
				detalles.add(dto);
			}
		}

		return detalles;
	}

	protected void actualizarDetalleSuplencia(Integer idDetalleQuincena, String estatus) {
		if (!ValidacionUtil.esNumeroPositivo(idDetalleQuincena)) {
			throw new ValidacionException("Seleccione el detalle que desea actualizar.",
					ValidacionCodigoError.VALOR_REQUERIDO);
		}

		if (ValidacionUtil.esCadenaVacia(estatus)) {
			throw new ValidacionException("Seleccione el estatus que desea actualizar.",
					ValidacionCodigoError.VALOR_REQUERIDO);
		}

		DetalleSuplenciaEntity detalle = detalleSuplenciaRepository.obtenerPorId(idDetalleQuincena);

		if (detalle.getEstatus().equals(EnumEstatusSuplencia.CERRADA)) {
			throw new ReglaNegocioException(
					"El estatus actual de la suplencia es " + estatus.toLowerCase() + " seleccione otra opción",
					ReglaNegocioCodigoError.QUINCENA_CERRADA);
		}

		if (detalle.getEstatus().equals(EnumEstatusSuplencia.PENDIENTE_AUTORIZACION)) {
			throw new ReglaNegocioException(
					"El detalle no ha sido autorizado, es requerida la autorización para poder modificarla.",
					ReglaNegocioCodigoError.MOVIMIENTO_NO_AUTORIZADO);
		}
		detalle.setEstatus(estatus);
		detalleSuplenciaRepository.actualizar(detalle);

	}

	protected String obtenerEstatusQuincenaSuplencia(int quincena, int ejercicio, Integer idSuplente) {

		return quincenaSuplenciaRepository.obtenerEstatusQuincenaSuplente(quincena, ejercicio, idSuplente);
	}

	protected Integer obtenerIdQuincenaSuplente(int quincena, int ejercicio, Integer idSuplente) {

		return quincenaSuplenciaRepository.obtenerIdQuincenaSuplente(quincena, ejercicio, idSuplente);

	}

	protected void actualizarEstatusQuincenaSuplencia(Integer idQuincena, String estatus) {
		if (!ValidacionUtil.esNumeroPositivo(idQuincena)) {
			throw new ValidacionException("La quincena es requerida.", ValidacionCodigoError.VALOR_REQUERIDO);
		}
		if (ValidacionUtil.esCadenaVacia(estatus)) {
			throw new ValidacionException("El estatus es requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
		}

		QuincenasSuplenciasEntity quincena = quincenaSuplenciaRepository.obtenerPorId(idQuincena);

		if (quincena == null) {
			throw new SistemaException("No se encontró quincena con identificador " + idQuincena,
					SistemaCodigoError.BUSQUEDA_SIN_RESULTADOS);
		}

		if (quincena.getEstatus().equals(EnumEstatusSuplencia.CERRADA)) {
			throw new ReglaNegocioException("La quincena se encuentra cerrada no puede ser modificada.",
					ReglaNegocioCodigoError.QUINCENA_CERRADA);
		}

		if (estatus.equals(EnumEstatusSuplencia.REVISION)) {
			if (quincena.getEstatus().equals(EnumEstatusSuplencia.REVISION)) {
				throw new ReglaNegocioException("La quincena ya ha sido enviada a revisión.",
						ReglaNegocioCodigoError.QUINCENA_CERRADA);
			}
		} else if (estatus.equals(EnumEstatusSuplencia.APROBADO)) {
			if (quincena.getEstatus().equals(EnumEstatusSuplencia.APROBADO)) {
				throw new ReglaNegocioException("La quincena ya ha sido enviada a cierre.",
						ReglaNegocioCodigoError.QUINCENA_CERRADA);
			}

			List<Integer> detalles = detalleSuplenciaRepository.tieneDetallesAprobados(idQuincena,
					EnumEstatusSuplencia.APROBADO);
			if (detalles.isEmpty()) {
				throw new ReglaNegocioException("Para enviar a cierre la suplencia debe aprobar al menos un concepto.",
						ReglaNegocioCodigoError.QUINCENA_CERRADA);
			}

		}
		quincena.setEstatus(estatus);
		quincenaSuplenciaRepository.actualizar(quincena);
	}

	protected void regresarARevision(Integer idQuincena) {
		if (!ValidacionUtil.esNumeroPositivo(idQuincena)) {
			throw new ValidacionException("La quincena es requerida.", ValidacionCodigoError.VALOR_REQUERIDO);
		}

		QuincenasSuplenciasEntity quincena = quincenaSuplenciaRepository.obtenerPorId(idQuincena);
		if (quincena == null) {
			throw new SistemaException("No se encontró quincena con identificador " + idQuincena,
					SistemaCodigoError.BUSQUEDA_SIN_RESULTADOS);
		}

		if (quincena.getIdNomina() != null) {
			throw new ReglaNegocioException(
					"Se ha procesado la nómina del suplente por lo cual no puede ser modificada.",
					ReglaNegocioCodigoError.QUINCENA_CERRADA);
		}

		quincena.setEstatus(EnumEstatusSuplencia.REVISION);
		quincenaSuplenciaRepository.actualizar(quincena);

	}

	protected void cerrarQuincenaSuplencia(CierreQuincenaDTO dto) {
		if (!ValidacionUtil.esNumeroPositivo(dto.getIdQuincena())) {
			throw new ValidacionException("La quincena es requerida.", ValidacionCodigoError.VALOR_REQUERIDO);
		}
		if (!ValidacionUtil.esNumeroPositivo(dto.getSueldo())) {
			throw new ReglaNegocioException("El total de la suplencia debe ser mayor a cero",
					ReglaNegocioCodigoError.SUELDO_INCORRECTO);
		}

		QuincenasSuplenciasEntity quincena = quincenaSuplenciaRepository.obtenerPorId(dto.getIdQuincena());
		if (quincena.getEstatus().equals(EnumEstatusSuplencia.CERRADA)) {
			throw new ReglaNegocioException("La quincena se encuentra cerrada no puede ser modificada.",
					ReglaNegocioCodigoError.QUINCENA_CERRADA);
		}

		quincena.setFechaCierre(FechaUtil.fechaActual());
		quincena.setTotal(dto.getSueldo());
		quincena.setTotalDias(dto.getTotalDias());
		quincena.setEstatus(EnumEstatusSuplencia.CERRADA);
		quincenaSuplenciaRepository.actualizar(quincena);

	}

	protected DatoLaboralDTO obtenerFinanciamientosQuincena(Integer idQuincena) {
		if (!ValidacionUtil.esNumeroPositivo(idQuincena)) {
			throw new ValidacionException("El suplente es requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
		}

		QuincenasSuplenciasEntity quincena = quincenaSuplenciaRepository.obtenerPorId(idQuincena);
		DatoLaboralDTO dto = new DatoLaboralDTO();
		// dto.setIdCuenta(quincena.getCuenta().getIdCuentaBancaria());
		dto.setIdDependencia(quincena.getDependencia().getIdDependencia());
		// dto.setIdFuenteFinanciamiento(quincena.getFuenteFinanciamiento().getIdFuenteFinanciamiento());
		dto.setIdProyecto(quincena.getProyecto().getIdProyecto());
		// dto.setIdSubfuenteFinanciamiento(quincena.getSubfuenteFinanciamiento().getIdFuenteFinanciamiento());
		// dto.setIdTipoRecurso(quincena.getTipoRecurso().getIdTipoRecurso());
		dto.setIdUnidadResponsable(quincena.getUnidadResponsable().getIdUnidadResponsable());
		dto.setSueldo(quincena.getTotal());

		return dto;
	}

	protected List<InfoSuplenciaDTO> consultarDiasSuplidos(Integer idSuplente) {
		List<InfoSuplenciaDTO> lista = quincenaSuplenciaRepository.consultarDiasSuplidos(idSuplente);
		return lista;

	}

	protected void descuentoSuplencia(DescuentoSuplenciaDTO edicion) {
		DetalleSuplenciaEntity detalle = detalleSuplenciaRepository.obtenerPorId(edicion.getIdDetalleSuplencia());

		if (detalle == null) {
			throw new ValidacionException("No se encontró la suplencia requerida.",
					ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);
		}
		UsuarioEntity usuario = usuarioRepository.obtenerPorId(edicion.getIdUsuarioLogeado());
		if (usuario == null) {
			throw new ValidacionException("No se encontró el usuario requerido.",
					ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);

		}

		if (detalle.getQuincena().getEstatus().equals(EnumEstatusSuplencia.CERRADA)) {
			throw new ReglaNegocioException("El detalle no puede ser modificado, quincena cerrada.",
					ReglaNegocioCodigoError.QUINCENA_CERRADA);
		}

		if (detalle.getEstatus().equals(EnumEstatusSuplencia.APROBADO)) {
			throw new ReglaNegocioException("El detalle ha sido aprobado, no puede ser modificado.",
					ReglaNegocioCodigoError.MOVIMIENTO_NO_AUTORIZADO);

		}

		if (edicion.isRegresarImporteAnterior()) {
			if (detalle.getTotalAnterior() == null) {
				throw new ReglaNegocioException(
						"No se ha realizado descuento al detalle, no existe un importe anterior.",
						ReglaNegocioCodigoError.MOVIMIENTO_NO_AUTORIZADO);
			}
			detalle.setTotal(detalle.getTotalAnterior());
			detalle.setTotalAnterior(null);
			detalle.setObservaciones(
					detalle.getObservaciones() + " se ha regresado al importe anterior " + detalle.getTotal());
		} else {

			if (edicion.getImporteADescontar().compareTo(detalle.getTotal()) == 1) {
				throw new ReglaNegocioException("El importe con descuento no puede ser mayor al importe inicial.",
						ReglaNegocioCodigoError.SUELDO_INCORRECTO);

			}
			BigDecimal nuevoTotal = detalle.getTotal().subtract(edicion.getImporteADescontar());

			detalle.setTotalAnterior(detalle.getTotal());
			detalle.setTotal(nuevoTotal);
			detalle.setModificada(true);
			detalle.setFechaModifacion(FechaUtil.fechaActual());
			detalle.setHoraModificacion(FechaUtil.fechaActual());
			detalle.setUsuarioModificacion(usuario);
			if (detalle.getObservaciones() != null) {
				detalle.setObservaciones(detalle.getObservaciones() + "Importe total: " + detalle.getTotalAnterior()
						+ " se descontó  $" + edicion.getImporteADescontar() + " por: " + edicion.getMotivoDescuento());
			} else {
				detalle.setObservaciones(" Descuento por: " + edicion.getMotivoDescuento());
			}
		}

		detalleSuplenciaRepository.actualizar(detalle);

	}

	protected List<SuplenciasQuincenaDTO> consultarSuplenciasQuincena(int numeroQuincena, int ejercicioFiscal) {
		if (!ValidacionUtil.esNumeroPositivo(numeroQuincena)) {
			throw new ValidacionException("El número de quincena es requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
		}
		if (!ValidacionUtil.esNumeroPositivo(ejercicioFiscal)) {
			throw new ValidacionException("El año es requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
		}
		if (!configuracionQuincenaRepository.esValidaQuincena(numeroQuincena)) {
			throw new ValidacionException("El número de quincena es incorrecto",
					ValidacionCodigoError.VALOR_NO_PERMITIDO);
		}

		return quincenaSuplenciaRepository.consultarQuincenaSuplencias(numeroQuincena, ejercicioFiscal);
	}

	protected boolean sePagaDoble(Integer idJornadaSuplencia) {

		TipoJornadaSuplenciaEntity jornada = tipoJornadaSuplenciaRepository.obtenerPorId(idJornadaSuplencia);
		return jornada.isDoble();

	}

	protected void editarSuplencia(EdicionSuplenciaDTO edicion) {

		DetalleSuplenciaEntity detalle = detalleSuplenciaRepository.obtenerPorId(edicion.getIdDetalleSuplencia());

		if (detalle == null) {
			throw new ValidacionException("No se encontró la suplencia requerida.",
					ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);
		}

		if (!detalle.getQuincena().getEstatus().equals(EnumEstatusSuplencia.CAPTURA)) {
			throw new ReglaNegocioException(
					"La quincena tiene un estatus de " + detalle.getQuincena().getEstatus().toLowerCase()
							+ " para poder realizar una edición es necesario que la quincena cuente con un estatus de CAPTURA.",
					ReglaNegocioCodigoError.ESTATUS_INCORRECTO);

		}

		if (detalle.getEstatus().equals(EnumEstatusSuplencia.APROBADO)
				|| detalle.getEstatus().equals(EnumEstatusSuplencia.RECHAZADO)) {
			throw new ReglaNegocioException(
					"No está permitido editar detalles con estatus " + detalle.getEstatus().toLowerCase(),
					ReglaNegocioCodigoError.ESTATUS_INCORRECTO);

		}

		DateTime fechaEdicionI = FechaUtil.fechaSinTiempo(edicion.getFechaInicio());
		DateTime fechaEdicionF = FechaUtil.fechaSinTiempo(edicion.getFechaFin());
		DateTime fechaActualI = FechaUtil.fechaSinTiempo(detalle.getFechaInicio());
		DateTime fechaActualF = FechaUtil.fechaSinTiempo(detalle.getFechaFin());

		if ((fechaEdicionI.compareTo(fechaActualI) != 0) && (fechaEdicionF.compareTo(fechaActualF) != 0)) {
			int numeroDias = FechaUtil.calcularDias(detalle.getFechaInicio(), detalle.getFechaFin());
			DateTime fecha = new DateTime(detalle.getFechaInicio());
			for (int i = 1; i <= numeroDias; i++) {
				if (detalleSuplenciaRepository.haSuplidoFecha(fecha.toDate(),
						detalle.getQuincena().getSuplente().getIdSuplenteAutorizado(), edicion.getIdJornada())) {
					throw new ReglaNegocioException("El suplente tiene registrada una suplencia en la fecha"
							+ FechaUtil.formatoFecha(fecha.toDate()), ReglaNegocioCodigoError.FECHA_INCORRECTA);
				}
				fecha = fecha.plusDays(1);
			}
		}

		/*
		 * if (!detalle.getEstatus().equals(EnumEstatusSuplencia.PENDIENTE) &&
		 * !detalle.getEstatus().equals(
		 * EnumEstatusSuplencia.PENDIENTE_AUTORIZACION)) { throw new
		 * ReglaNegocioException(
		 * "El detalle que desea editar tiene un estatus de " +
		 * detalle.getEstatus().toLowerCase() +
		 * " para poder realizar una edición es necesario que cuente con un estatus de PENDIENTE."
		 * , ReglaNegocioCodigoError.ESTATUS_INCORRECTO); }
		 */
		TipoJornadaSuplenciaEntity jornada = tipoJornadaSuplenciaRepository.obtenerPorId(edicion.getIdJornada());
		detalle.setFechaInicio(edicion.getFechaInicio());
		detalle.setFechaFin(edicion.getFechaFin());
		detalle.setCantidadDiaria(edicion.getCantidadDiaria());
		detalle.setDias(edicion.getDias());
		detalle.setTotal(edicion.getTotal());
		detalle.setJornada(jornada);
		detalle.setObservaciones(edicion.getObservaciones());
		detalleSuplenciaRepository.actualizar(detalle);

	}

	protected void eliminarSuplencia(Integer idDetalleSuplencia) {
		DetalleSuplenciaEntity detalle = detalleSuplenciaRepository.obtenerPorId(idDetalleSuplencia);

		if (!ValidacionUtil.esNumeroPositivo(idDetalleSuplencia)) {
			throw new ValidacionException("Es requerido seleccionar un detalle.",
					ValidacionCodigoError.VALOR_REQUERIDO);
		}

		if (detalle == null) {
			throw new ValidacionException("No se encontró la suplencia requerida.",
					ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);
		}

		if (!detalle.getQuincena().getEstatus().equals(EnumEstatusSuplencia.CAPTURA)) {
			throw new ReglaNegocioException(
					"La quincena tiene un estatus de " + detalle.getQuincena().getEstatus().toLowerCase()
							+ " para poder eliminar un registro es necesario que la quincena cuente con un estatus de CAPTURA.",
					ReglaNegocioCodigoError.ESTATUS_INCORRECTO);

		}

		if (detalle.getEstatus().equals(EnumEstatusSuplencia.APROBADO)
				|| detalle.getEstatus().equals(EnumEstatusSuplencia.RECHAZADO)) {

			throw new ReglaNegocioException(
					"No está permitida la eliminación de detalles con estatus " + detalle.getEstatus().toLowerCase(),
					ReglaNegocioCodigoError.ESTATUS_INCORRECTO);
		}
		/*
		 * if (!detalle.getEstatus().equals(EnumEstatusSuplencia.PENDIENTE)) {
		 * throw new ReglaNegocioException(
		 * "El detalle que desea eliminar tiene un estatus de " +
		 * detalle.getEstatus().toLowerCase() +
		 * " para poder realizar la operación es necesario que cuente con un estatus de PENDIENTE."
		 * , ReglaNegocioCodigoError.ESTATUS_INCORRECTO); }
		 */
		detalleSuplenciaRepository.eliminar(detalle);
	}

	protected void activarQuincenaSuplencia(int numeroQuincena, int ejercicioFiscal, Integer idUsuario) {
		if (!ValidacionUtil.esNumeroPositivo(numeroQuincena)) {
			throw new ValidacionException("El número de quincena es requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
		}
		if (numeroQuincena > 24 || numeroQuincena < 1) {
			throw new ValidacionException("El número de quincena solo puede ser un número entre el 1 y el 24.",
					ValidacionCodigoError.VALOR_NO_PERMITIDO);
		}
		if (!ValidacionUtil.esNumeroPositivo(ejercicioFiscal)) {
			throw new ValidacionException("El ejercicio fiscal es requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
		}
		if (!ValidacionUtil.esNumeroPositivo(idUsuario)) {
			throw new ValidacionException("El usuario es requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
		}
		UsuarioEntity usuario = usuarioRepository.obtenerPorId(idUsuario);
		if (usuario == null) {
			throw new SeguridadException("No se encontró usuario con identificador " + idUsuario,
					SeguridadCodigoError.USUARIO_INACTIVO);
		}

		if (!usuario.isActivo()) {
			throw new SeguridadException("El usuario está inactivo, no puede realizar la operación",
					SeguridadCodigoError.USUARIO_INACTIVO);
		}

		QuincenaActivaSuplenciaEntity quincenaActiva = quincenaActivaRepository.obtenerQuincenaActiva();

		if (quincenaActiva == null) {
			QuincenaActivaSuplenciaEntity primerRegistro = new QuincenaActivaSuplenciaEntity();
			primerRegistro.setFechaActivacion(FechaUtil.fechaActual());
			primerRegistro.setQuincenaActiva(numeroQuincena);
			primerRegistro.setEjercicioFiscal(ejercicioFiscal);
			primerRegistro.setUsuarioEntity(usuario);
			primerRegistro.setActivo(true);
			quincenaActivaRepository.crear(primerRegistro);
		} else {

			quincenaActiva.setActivo(false);
			quincenaActivaRepository.actualizar(quincenaActiva);

			QuincenaActivaSuplenciaEntity nuevaQuincenaActiva = new QuincenaActivaSuplenciaEntity();
			nuevaQuincenaActiva.setFechaActivacion(FechaUtil.fechaActual());
			nuevaQuincenaActiva.setQuincenaActiva(numeroQuincena);
			nuevaQuincenaActiva.setUsuarioEntity(usuario);
			nuevaQuincenaActiva.setEjercicioFiscal(ejercicioFiscal);
			nuevaQuincenaActiva.setActivo(true);
			quincenaActivaRepository.crear(nuevaQuincenaActiva);

		}

	}

	protected QuincenaActivaDTO obtenerQuincenaActiva() {
		QuincenaActivaDTO quincena = new QuincenaActivaDTO();
		QuincenaActivaSuplenciaEntity quincenaActiva = quincenaActivaRepository.obtenerQuincenaActiva();

		if (quincenaActiva != null) {

			quincena.setFecha(quincenaActiva.getFechaActivacion());
			quincena.setNumeroQuincenaActiva(quincenaActiva.getQuincenaActiva());
			quincena.setUsuario(quincenaActiva.getUsuarioEntity().nombreCompleto());
			quincena.setEjercicioFiscal(quincenaActiva.getEjercicioFiscal());

		}

		return quincena;
	}

	protected List<DetalleSuplenciaDTO> consultarSuplenciasPendientes(Integer idSuplente, Integer idQuincena) {
		if (!ValidacionUtil.esNumeroPositivo(idSuplente)) {
			throw new ValidacionException("El suplente es requerido", ValidacionCodigoError.VALOR_REQUERIDO);
		}

		List<DetalleSuplenciaDTO> detalles = new ArrayList<DetalleSuplenciaDTO>();
		List<DetalleSuplenciaEntity> consulta = detalleSuplenciaRepository
				.consultarDetalleSuplentePorEstatus(idSuplente, EnumEstatusSuplencia.PENDIENTE, idQuincena);

		if (!consulta.isEmpty()) {
			for (DetalleSuplenciaEntity d : consulta) {
				DetalleSuplenciaDTO dto = new DetalleSuplenciaDTO();
				dto.setDias(d.getDias());
				dto.setFechaFin(d.getFechaFin());
				dto.setFechaInicio(d.getFechaInicio());
				dto.setIdTipoSuplencia(d.getTipoSuplencia().getIdCausaSuplenca());
				dto.setEstatus(d.getEstatus());
				if (d.getEmpleado() != null) {
					dto.setIdEmpleado(d.getEmpleado().getIdEmpleado());
					dto.setNombreEmpleado(d.getEmpleado().getNombreCompleto());
				}
				dto.setTipoSuplencia(d.getTipoSuplencia().getCausaSuplencia());
				dto.setTotal(d.getTotal());
				dto.setIdDetalleSuplencia(d.getIdDetalleSuplencia());
				dto.setIdTabulador(d.getIdTabulador());
				dto.setDias(d.getDias());
				dto.setNumeroQuincena(d.getQuincena().getNumeroQuincena());
				dto.setIdSuplenteAutorizado(d.getQuincena().getSuplente().getIdSuplenteAutorizado());

				if (d.getTipoSuplencia().getIdCausaSuplenca() == EnumTipoSuplencia.INCAPACIDADES
						|| d.getTipoSuplencia().getIdCausaSuplenca() == EnumTipoSuplencia.LICENCIA_CON_SUELDO
						|| d.getTipoSuplencia().getIdCausaSuplenca() == EnumTipoSuplencia.LICENCIA_SIN_SUELDO
						|| d.getTipoSuplencia().getIdCausaSuplenca() == EnumTipoSuplencia.COMISION) {
					// DetalleMovimientoDTO movimiento =
					// consultaMovimientoService
					// .toDetalleMovimiento(d.getMovimientoEmpleado());
					// dto.setDetalleMovimiento(movimiento);

				}
				detalles.add(dto);
			}
		}

		return detalles;
	}

	protected void agregarSuplenciaPendiente(Integer idQuincena, Integer idDetalleSuplencia) {
		if (!ValidacionUtil.esNumeroPositivo(idQuincena)) {
			throw new ValidacionException("La quincena es requerida", ValidacionCodigoError.VALOR_REQUERIDO);
		}

		if (!ValidacionUtil.esNumeroPositivo(idDetalleSuplencia)) {
			throw new ValidacionException("El detalle de la suplencia es requerido.",
					ValidacionCodigoError.VALOR_REQUERIDO);
		}

		QuincenasSuplenciasEntity quincenaSuplencia = quincenaSuplenciaRepository.obtenerPorId(idQuincena);
		if (quincenaSuplencia == null) {
			throw new ValidacionException("La quincena con identificador " + idQuincena + " no existe.",
					ValidacionCodigoError.VALOR_REQUERIDO);
		}

		if (quincenaSuplencia.getEstatus().equals(EnumEstatusSuplencia.CERRADA)) {
			throw new ReglaNegocioException(
					"La quincena " + quincenaSuplencia.getNumeroQuincena() + " ha sido cerrada.",
					ReglaNegocioCodigoError.QUINCENA_CERRADA);
		}

		DetalleSuplenciaEntity detalleSuplencia = detalleSuplenciaRepository.obtenerPorId(idDetalleSuplencia);
		if (detalleSuplencia == null) {
			throw new ValidacionException(
					"El detalle de la suplencia con identificador " + idDetalleSuplencia + " no existe.",
					ValidacionCodigoError.VALOR_REQUERIDO);
		}

		if (detalleSuplencia.getEstatus().equals(EnumEstatusSuplencia.APROBADO)
				|| detalleSuplencia.getEstatus().equals(EnumEstatusSuplencia.PENDIENTE_AUTORIZACION)
				|| detalleSuplencia.getEstatus().equals(EnumEstatusSuplencia.RECHAZADO)) {
			throw new ReglaNegocioException("El detalle no puede ser agregado por que cuenta con un estatus de "
					+ detalleSuplencia.getEstatus().toLowerCase(), ReglaNegocioCodigoError.ESTATUS_INCORRECTO);
		}
		if (detalleSuplencia.getQuincena().getNumeroQuincena() > quincenaSuplencia.getNumeroQuincena()) {
			throw new ReglaNegocioException(
					"Usted intenta asignar un detalle de la quincena " + quincenaSuplencia.getNumeroQuincena()
							+ " en la quincena, la quincena origen no puede ser mayor a la quincena destinataria. "
							+ detalleSuplencia.getQuincena().getNumeroQuincena(),
					ReglaNegocioCodigoError.MOVIMIENTO_NO_AUTORIZADO);
		}

		detalleSuplencia.setQuincena(quincenaSuplencia);
		detalleSuplenciaRepository.actualizar(detalleSuplencia);

	}

	protected void actualizarEstatusSuplente(Integer idSuplente, String estatus) {
		if (!ValidacionUtil.esNumeroPositivo(idSuplente)) {
			throw new ValidacionException("El suplente es requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
		}

		if (ValidacionUtil.esCadenaVacia(estatus)) {
			throw new ValidacionException("El estatus es requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
		}

		SuplenteAutorizadoEntity suplente = suplenteAutorizadoRepository.obtenerPorId(idSuplente);
		if (suplente == null) {
			throw new ValidacionException("El suplente con identificador " + idSuplente + " es requerido.",
					ValidacionCodigoError.VALOR_REQUERIDO);
		}

		suplente.setEstatus(estatus);
		suplenteAutorizadoRepository.actualizar(suplente);
	}

	protected void registrarMovimientoSuplencia(MovimientoSuplenteDTO movimientoSuplente) {
		SuplenteAutorizadoEntity suplente = suplenteAutorizadoRepository
				.obtenerPorId(movimientoSuplente.getIdSuplente());

		if (suplente == null) {
			throw new SistemaException(
					"No se encontró registro del suplente con identificador " + movimientoSuplente.getIdSuplente(),
					SistemaCodigoError.BUSQUEDA_SIN_RESULTADOS);
		}

		// MovimientoSuplenteEntity nuevoMovimiento = new
		// MovimientoSuplenteEntity();

	}

	protected SuplenteDTO obtenerSuplentePorId(Integer idSuplente) {
		if (!ValidacionUtil.esNumeroPositivo(idSuplente)) {
			throw new ValidacionException("El identificador del suplente es requerido.",
					ValidacionCodigoError.VALOR_REQUERIDO);
		}
		SuplenteAutorizadoEntity suplente = suplenteAutorizadoRepository.obtenerPorId(idSuplente);
		if (suplente == null) {
			throw new SistemaException("No se encontró registro de suplente con identificador " + idSuplente,
					SistemaCodigoError.BUSQUEDA_SIN_RESULTADOS);
		}
		String mensaje = "SIN ASIGNAR";
		SuplenteDTO dto = new SuplenteDTO();
		if (suplente.getCentroResponsabilidad() != null) {
			dto.setCentroResponsabilidad(suplente.getCentroResponsabilidad().getDescripcion());
		} else {
			dto.setCentroResponsabilidad(mensaje);
		}

		if (suplente.getDependencia() != null) {
			dto.setDependencia(suplente.getDependencia().getDescripcion());
		} else {
			dto.setDependencia(mensaje);
		}
		dto.setEstatus(suplente.getEstatus());
		dto.setFechaIngreso(suplente.getFechaIngreso());
		dto.setFechaPrimeraQuincena(suplente.getPrimeraQuincena());
		if (suplente.getFuncion() != null) {
			dto.setFuncion(suplente.getFuncion().getFuncion());
		} else {
			dto.setFuncion(mensaje);
		}
		dto.setIdEmpleado(suplente.getEmpleado().getIdEmpleado());
		dto.setIdSuplente(suplente.getIdSuplenteAutorizado());
		if (suplente.getMetodoPago() == 1) {
			dto.setMetodoPago("CHEQUE");
		} else if (suplente.getMetodoPago() == 2) {
			dto.setMetodoPago("TRANSFERENCIA");
		} else {
			dto.setMetodoPago("METODO NO IDENTIFICADO");
		}

		dto.setNombre(suplente.getEmpleado().getNombreCompleto());
		dto.setNumeroCuenta(suplente.getNumero());
		dto.setNumeroLaboral(suplente.getNumeroLaboral());
		dto.setNumeroPersonal(suplente.getEmpleado().getNumeroEmpleado());
		if (suplente.getProyecto() != null) {
			dto.setProyecto(suplente.getProyecto().getDescripcion());
		} else {
			dto.setProyecto(mensaje);
		}

		dto.setRfc(suplente.getEmpleado().getRfc());
		if (suplente.getEmpleado().getTipoEmpleado() != null) {
			dto.setTipoSuplente(suplente.getEmpleado().getTipoEmpleado().getTipoEmpleado());
		} else {
			dto.setTipoSuplente(mensaje);
		}
		if (suplente.getUnidadResponsable() != null) {
			dto.setUnidadResponsable(suplente.getUnidadResponsable().getDescripcion());
		} else {
			dto.setUnidadResponsable(mensaje);
		}

		return dto;
	}

	protected List<QuincenaSuplenteDTO> consultarQuincenasSuplente(ConsultaSuplenciaDTO consulta) {
		List<QuincenaSuplenteDTO> quincenas = new ArrayList<>();
		if (!ValidacionUtil.esNumeroPositivo(consulta.getTipoConsulta())) {
			throw new ValidacionException("El tipo de consulta es requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
		}
		if (consulta.getTipoConsulta() == EnumTipoConsultaSuplencia.QUINCENA_POR_CRITERIO) {
			if (!ValidacionUtil.esNumeroPositivo(consulta.getTipoConsulta())) {
				throw new ValidacionException("El criterio es requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
			}
			if (consulta.getCriterio().trim().length() < 3) {
				throw new ValidacionException("El criterio debe contener más de 3 letras.",
						ValidacionCodigoError.VALOR_MUY_CORTO);
			}
			quincenas = quincenaSuplenciaRepository.consultarQuincenasPorNombre(consulta.getCriterio());

		} else if (consulta.getTipoConsulta() == EnumTipoConsultaSuplencia.QUINCENAS_CENTROS_ESTATUS) {
			if (!ValidacionUtil.esNumeroPositivo(consulta.getQuincena())) {
				throw new ValidacionException("El número de quincena es requerido.",
						ValidacionCodigoError.VALOR_REQUERIDO);
			}

			if (!ValidacionUtil.esNumeroPositivo(consulta.getEjercicio())) {
				throw new ValidacionException("El ejercicio fiscal es requerido.",
						ValidacionCodigoError.VALOR_REQUERIDO);
			}

			if (!ValidacionUtil.esNumeroPositivo(consulta.getIdCentroResponsabilidad())) {
				throw new ValidacionException("El centro de responsabilidad es requerido.",
						ValidacionCodigoError.VALOR_REQUERIDO);
			}

			if (ValidacionUtil.esCadenaVacia(consulta.getEstatus())) {
				throw new ValidacionException("El estatus requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
			}

			quincenas = quincenaSuplenciaRepository.consultarQuincenasPorCentroEstatus(consulta.getQuincena(),
					consulta.getEjercicio(), consulta.getIdCentroResponsabilidad(), consulta.getEstatus());
		}

		return quincenas;
	}
}
