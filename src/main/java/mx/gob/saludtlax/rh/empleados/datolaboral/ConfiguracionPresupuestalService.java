package mx.gob.saludtlax.rh.empleados.datolaboral;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.NoResultException;

import mx.gob.saludtlax.rh.autorizaciones.AutorizacionesService;
import mx.gob.saludtlax.rh.autorizaciones.EnumTiposAccionesAutorizacion;
import mx.gob.saludtlax.rh.autorizaciones.NuevaAutorizacionDTO;
import mx.gob.saludtlax.rh.configuracion.tabulador.TabuladorService;
import mx.gob.saludtlax.rh.empleados.administracion.BitacoraEmpleadoDTO;
import mx.gob.saludtlax.rh.empleados.administracion.BitacoraModificacionService;
import mx.gob.saludtlax.rh.empleados.administracion.EnumTipoModificacionEmpleado;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.SistemaCodigoError;
import mx.gob.saludtlax.rh.excepciones.SistemaException;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionPresupuestoEntity;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionPresupuestoRepository;
import mx.gob.saludtlax.rh.persistencia.DependenciaTempEntity;
import mx.gob.saludtlax.rh.persistencia.DependenciaTempRepository;
import mx.gob.saludtlax.rh.persistencia.EstatusConfiguracionesEntity;
import mx.gob.saludtlax.rh.persistencia.EstatusConfiguracionesRepository;
import mx.gob.saludtlax.rh.persistencia.FuenteFinanciamientoEntity;
import mx.gob.saludtlax.rh.persistencia.FuenteFinanciamientoRepository;
import mx.gob.saludtlax.rh.persistencia.InventarioVacanteEntity;
import mx.gob.saludtlax.rh.persistencia.InventarioVacanteRepository;
import mx.gob.saludtlax.rh.persistencia.ProgramaEntity;
import mx.gob.saludtlax.rh.persistencia.ProgramaRepository;
import mx.gob.saludtlax.rh.persistencia.ProyectoTempEntity;
import mx.gob.saludtlax.rh.persistencia.ProyectoTempRepository;
import mx.gob.saludtlax.rh.persistencia.PuestoGeneralEntity;
import mx.gob.saludtlax.rh.persistencia.PuestoGeneralRepository;
import mx.gob.saludtlax.rh.persistencia.SubFuenteFinanciamientoTempEntity;
import mx.gob.saludtlax.rh.persistencia.SubFuenteFinanciamientoTempRepository;
import mx.gob.saludtlax.rh.persistencia.TabuladorEntity;
import mx.gob.saludtlax.rh.persistencia.TabuladorRepository;
import mx.gob.saludtlax.rh.persistencia.TempContratosEstatalesEntity;
import mx.gob.saludtlax.rh.persistencia.TempContratosEstatalesRepository;
import mx.gob.saludtlax.rh.persistencia.TempContratosFederalesEntity;
import mx.gob.saludtlax.rh.persistencia.TempContratosFederalesRepository;
import mx.gob.saludtlax.rh.persistencia.TemporalConfEmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.TemporalConfEmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.TipoContratacionEntity;
import mx.gob.saludtlax.rh.persistencia.TipoContratacionRepository;
import mx.gob.saludtlax.rh.persistencia.TipoRecursoTempEntity;
import mx.gob.saludtlax.rh.persistencia.TipoRecursoTempRepository;
import mx.gob.saludtlax.rh.persistencia.TiposNombramientosEntity;
import mx.gob.saludtlax.rh.persistencia.TiposNombramientosRepository;
import mx.gob.saludtlax.rh.persistencia.UnidadResponsableEntity;
import mx.gob.saludtlax.rh.persistencia.UnidadResponsableRepository;
import mx.gob.saludtlax.rh.puestosautorizados.EnumTipoContratacion;
import mx.gob.saludtlax.rh.puestosautorizados.EnumTipoNombramiento;
import mx.gob.saludtlax.rh.puestosautorizados.FiltroConsultaFinanciamientosDTO;
import mx.gob.saludtlax.rh.puestosautorizados.InfoConfiguracionDTO;
import mx.gob.saludtlax.rh.util.FechaUtil;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author Leila Schiaffini Ehuan
 * @since 10/08/2016 14:59:40
 * 
 */
public class ConfiguracionPresupuestalService {

	@Inject
	private AutorizacionesService autorizacionesService;
	@Inject
	private BitacoraModificacionService bitacoraModificacionService;
	@Inject
	private ConfiguracionPresupuestoRepository configuracionPresupuestoRepository;
	@Inject
	private DependenciaTempRepository dependenciasRepository;
	@Inject
	private EstatusConfiguracionesRepository estatusConfiguracionesRepository;
	@Inject
	private InventarioVacanteRepository inventarioVacanteRepository;
	@Inject
	private FuenteFinanciamientoRepository fuenteFinanciamientoRepository;
	@Inject
	private SubFuenteFinanciamientoTempRepository subfuentefinanciamientoRepository;
	@Inject
	private TiposNombramientosRepository nombramientoRepository;

	@Inject
	private ProyectoTempRepository proyectoRepository;
	@Inject
	private PuestoGeneralRepository puestoGeneralRepository;
	@Inject
	private TemporalConfEmpleadoRepository temporalConfEmpleadoRepository;
	@Inject
	private TipoContratacionRepository tipoContratacionRepository;
	@Inject
	private TipoRecursoTempRepository tipoRecursoTempRepository;
	@Inject
	private TabuladorRepository tabuladorRepository;
	@Inject
	private UnidadResponsableRepository unidadResponsableRepository;

	@Inject
	private TempContratosEstatalesRepository tempContratosEstatalesRepository;

	@Inject
	private TempContratosFederalesRepository tempContratosFederalesRepository;

	@Inject
	private ProgramaRepository programaRepository;

	@Inject
	private TabuladorService tabuladorService;

	protected Integer crearDatoLaboral(SolicitudNuevoPuestoDTO dto) {

		int iDTipoContratacion = dto.getDatosLaborales().getTipoContratacion();
		Integer idDatoLaboral = null;

		EstatusConfiguracionesEntity estatus = estatusConfiguracionesRepository
				.obtenerPorId(EnumEstatusConfiguracion.EN_ESPERA_AUTORIZACION);

		ConfiguracionPresupuestoEntity nuevoDatoLaboral = new ConfiguracionPresupuestoEntity();
		nuevoDatoLaboral.setFechaAltaConfiguracion(FechaUtil.fechaActual());

		if (iDTipoContratacion == EnumTipoContratacion.INTERINATO) {
			InventarioVacanteEntity puestoDisponible = inventarioVacanteRepository
					.obtenerPorId(dto.getIdPuestoDisponible());
			if (puestoDisponible == null) {
				throw new ValidacionException(
						"No se encontró puesto disponible con identificador " + dto.getIdPuestoDisponible(),
						ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);
			}
			ConfiguracionPresupuestoEntity laboralesPuestoACubrir = puestoDisponible.getConfiguracion();
			TipoContratacionEntity tipoContratacion = tipoContratacionRepository
					.obtenerPorId(EnumTipoContratacion.INTERINATO);

			if (laboralesPuestoACubrir == null) {
				throw new ValidacionException(
						"No se encontró registro del dato laboral con identificador " + idDatoLaboral,
						ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);
			}

			nuevoDatoLaboral.setCuenta(laboralesPuestoACubrir.getCuenta());
			nuevoDatoLaboral.setDependencia(laboralesPuestoACubrir.getDependencia());
			// c.setEmpleado(empleado);
			nuevoDatoLaboral.setEstatus(estatus);
			nuevoDatoLaboral.setFuenteFinanciamiento(laboralesPuestoACubrir.getFuenteFinanciamiento());
			nuevoDatoLaboral.setNombramiento(laboralesPuestoACubrir.getNombramiento());
			// c.setNumeroEmpleado(numeroEmpleado);
			nuevoDatoLaboral.setProyecto(laboralesPuestoACubrir.getProyecto());
			nuevoDatoLaboral.setPuesto(laboralesPuestoACubrir.getPuesto());
			nuevoDatoLaboral.setSubfuenteFinanciamiento(laboralesPuestoACubrir.getSubfuenteFinanciamiento());
			nuevoDatoLaboral.setSueldo(laboralesPuestoACubrir.getSueldo());
			nuevoDatoLaboral.setTabulador(laboralesPuestoACubrir.getTabulador());
			nuevoDatoLaboral.setTipoContratacion(tipoContratacion);
			nuevoDatoLaboral.setTipoRecurso(laboralesPuestoACubrir.getTipoRecurso());
			nuevoDatoLaboral.setUnidadResponsable(laboralesPuestoACubrir.getUnidadResponsable());

		} else {

			DependenciaTempEntity dependencia = dependenciasRepository
					.obtenerPorId(dto.getDatosLaborales().getIdDependencia());
			FuenteFinanciamientoEntity fuenteFinanciamiento = fuenteFinanciamientoRepository
					.obtenerPorId(dto.getDatosLaborales().getIdFuenteFinanciamiento());

			Integer idNombramiento = generarNombramiento(dto.getDatosLaborales().getTipoContratacion());
			Integer idTipoContratacion = dto.getDatosLaborales().getTipoContratacion();

			if (idNombramiento == null) {
				TiposNombramientosEntity nombramiento = nombramientoRepository
						.nombramientoPorId(dto.getDatosLaborales().getIdNombramiento());
				nuevoDatoLaboral.setNombramiento(nombramiento);
			} else {
				TiposNombramientosEntity nombramiento = nombramientoRepository.nombramientoPorId(idNombramiento);
				nuevoDatoLaboral.setNombramiento(nombramiento);
			}

			ProyectoTempEntity proyecto = proyectoRepository.obtenerPorId(dto.getDatosLaborales().getIdProyecto());
			PuestoGeneralEntity puesto = puestoGeneralRepository.obtenerPorId(dto.getDatosLaborales().getIdPuesto());
			SubFuenteFinanciamientoTempEntity subfuenteFinanciamiento = subfuentefinanciamientoRepository
					.obtenerPorId(dto.getDatosLaborales().getIdSubfuenteFinanciamiento());
			TipoRecursoTempEntity tipoRecursoTempEntity = tipoRecursoTempRepository
					.obtenerPorId(dto.getDatosLaborales().getIdTipoRecurso());
			UnidadResponsableEntity unidadResponsable = unidadResponsableRepository
					.obtenerPorId(dto.getDatosLaborales().getIdUnidadResponsable());

			TabuladorEntity tabulador = tabuladorRepository.obtenerPorId(dto.getDatosLaborales().getIdTabulador());
			TipoContratacionEntity tipoContratacion = tipoContratacionRepository.obtenerPorId(idTipoContratacion);

			nuevoDatoLaboral.setDependencia(dependencia);
			// c.setEmpleado(empleado);

			nuevoDatoLaboral.setFuenteFinanciamiento(fuenteFinanciamiento);
			nuevoDatoLaboral.setEstatus(estatus);

			// c.setNumeroEmpleado(numeroEmpleado);
			nuevoDatoLaboral.setProyecto(proyecto);
			nuevoDatoLaboral.setPuesto(puesto);
			nuevoDatoLaboral.setSubfuenteFinanciamiento(subfuenteFinanciamiento);

			if (idTipoContratacion == EnumTipoContratacion.CONFIANZA
					|| idTipoContratacion == EnumTipoContratacion.BASE) {
				nuevoDatoLaboral.setSueldo(tabulador.getSueldoBrutoMensual());
			} else if (idTipoContratacion == EnumTipoContratacion.CONTRATO_ESTATAL) {
				if (dto.getDatosLaborales().getSueldo().compareTo(tabulador.getSueldoBaseMensualMinimo()) == -1) {
					throw new ReglaNegocioException(
							"El sueldo no puede ser menor a " + tabulador.getSueldoBaseMensualMinimo(),
							ReglaNegocioCodigoError.SUELDO_INCORRECTO);
				} else if (dto.getDatosLaborales().getSueldo().compareTo(tabulador.getSueldoBaseMensualMaximo()) == 1) {
					throw new ReglaNegocioException(
							"El sueldo no puede ser mayor a " + tabulador.getSueldoBaseMensualMaximo(),
							ReglaNegocioCodigoError.SUELDO_INCORRECTO);
				}

				nuevoDatoLaboral.setSueldo(dto.getDatosLaborales().getSueldo());
			} else if (idTipoContratacion == EnumTipoContratacion.CONTRATO_FEDERAL) {
				if (!ValidacionUtil.esNumeroPositivo(dto.getDatosLaborales().getSueldo())) {
					throw new ReglaNegocioException("El sueldo debe ser un número mayor a cero.",
							ReglaNegocioCodigoError.SUELDO_INCORRECTO);
				}
				nuevoDatoLaboral.setSueldo(dto.getDatosLaborales().getSueldo());

			}

			nuevoDatoLaboral.setTipoContratacion(tipoContratacion);
			nuevoDatoLaboral.setTipoRecurso(tipoRecursoTempEntity);
			nuevoDatoLaboral.setUnidadResponsable(unidadResponsable);
			nuevoDatoLaboral.setTipoContratacion(tipoContratacion);
			nuevoDatoLaboral.setTabulador(tabulador);

		}
		nuevoDatoLaboral.setAplicaPrimerPago(Boolean.TRUE);
		configuracionPresupuestoRepository.crear(nuevoDatoLaboral);
		idDatoLaboral = nuevoDatoLaboral.getId();

		return idDatoLaboral;
	}

	protected void modificarDatoLaboral(DatoLaboralDTO datoLaboral, Integer idPuesto, Integer idUsuario) {

		InventarioVacanteEntity puesto = inventarioVacanteRepository.obtenerPorId(idPuesto);
		if (puesto == null) {
			throw new SistemaException("No existe el puesto con identificador " + idPuesto,
					SistemaCodigoError.BUSQUEDA_SIN_RESULTADOS);
		}

		if (puesto.getConfiguracion() == null) {
			throw new ValidacionException("El puesto no tiene dato laboral asignado.",
					ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);
		}

		ConfiguracionPresupuestoEntity datoLaboralEmpleado = configuracionPresupuestoRepository
				.obtenerPorId(puesto.getConfiguracion().getId());
		if (datoLaboralEmpleado == null) {
			throw new ValidacionException("La configuración laboral asignada al puesto es incorrecta.",
					ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);
		}

		puesto.setSeguroPopular(datoLaboral.isSeguroPopular());

		String lccActual = datoLaboralEmpleado.lccDatosLaborales();
		String lccActualSueldo = datoLaboralEmpleado.lccSueldo();

		ProyectoTempEntity proyecto = proyectoRepository.obtenerPorId(datoLaboral.getIdProyecto());
		DependenciaTempEntity dependencia = dependenciasRepository.obtenerPorId(datoLaboral.getIdDependencia());
		UnidadResponsableEntity unidadResponsable = unidadResponsableRepository
				.obtenerPorId(datoLaboral.getIdUnidadResponsable());
		FuenteFinanciamientoEntity fuenteFinanciamiento = fuenteFinanciamientoRepository
				.obtenerPorId(datoLaboral.getIdFuenteFinanciamiento());
		SubFuenteFinanciamientoTempEntity subfuenteFinanciamiento = subfuentefinanciamientoRepository
				.obtenerPorId(datoLaboral.getIdSubfuenteFinanciamiento());
		TipoRecursoTempEntity tipoRecursoTempEntity = tipoRecursoTempRepository
				.obtenerPorId(datoLaboral.getIdTipoRecurso());

		PuestoGeneralEntity puestoGeneral = puestoGeneralRepository.obtenerPorId(datoLaboral.getIdPuesto());

		datoLaboralEmpleado.setDependencia(dependencia);
		datoLaboralEmpleado.setFuenteFinanciamiento(fuenteFinanciamiento);
		datoLaboralEmpleado.setNumeroEmpleado(datoLaboral.getNumeroEmpleado());
		datoLaboralEmpleado.setProyecto(proyecto);
		datoLaboralEmpleado.setPuesto(puestoGeneral);
		datoLaboralEmpleado.setSubfuenteFinanciamiento(subfuenteFinanciamiento);

		TabuladorEntity tabulador = null;
		if (datoLaboral.getIdTabulador() != null) {
			tabulador = tabuladorRepository.obtenerPorId(datoLaboral.getIdTabulador());
		}

		Integer idTipoContratacion = datoLaboralEmpleado.getTipoContratacion().getId();
		if (tabulador == null && datoLaboralEmpleado.getTipoContratacion().getAreaResponsable() != 2) {
			// para el caso de los puestos federales siempre es requerido un
			// tabulador configurado.
			throw new ReglaNegocioException(
					"El código seleccionado no tiene configurado un tabulador, configure el código para poder realizar la modificación.",
					ReglaNegocioCodigoError.TABULADOR_NO_CONFIGURADO);
		}
		String observacionModificacion = "";

		if (idTipoContratacion == EnumTipoContratacion.CONFIANZA || idTipoContratacion == EnumTipoContratacion.BASE) {
			datoLaboralEmpleado.setSueldo(tabulador.getSueldoBrutoMensual());

		} else if (idTipoContratacion == EnumTipoContratacion.CONTRATO_ESTATAL) {
			// Para el caso del contrato estatal se requirió que se aperturara
			// la modificación incluso si no tenía configurado un tabulador.
			if (tabulador != null) {
				// Por solicitud de las areas si el sueldo estuviera fuera de
				// rango, los dejaría realizar el movimiento lanzando solo una
				// advertencia
				if (datoLaboral.getSueldo().compareTo(tabulador.getSueldoBaseMensualMinimo()) == -1) {
					observacionModificacion = "El sueldo es menor al mínimo de "
							+ tabulador.getSueldoBaseMensualMinimo();

					// throw new ReglaNegocioException(observacionModificacion,
					// ReglaNegocioCodigoError.SUELDO_FUERA_RANGO);
				}

				if (datoLaboral.getSueldo().compareTo(tabulador.getSueldoBaseMensualMaximo()) == 1) {
					observacionModificacion = "El sueldo es mayor al maximo de "
							+ tabulador.getSueldoBaseMensualMaximo();
					// throw new ReglaNegocioException(observacionModificacion,
					// ReglaNegocioCodigoError.SUELDO_FUERA_RANGO);

				}

			}

			BigDecimal sumatoriaSueldo = datoLaboral.getSueldo01().add(datoLaboral.getSueldo14());
			if (datoLaboral.getSueldo().compareTo(sumatoriaSueldo) != 0) {
				throw new ReglaNegocioException("La sumatoria del 01 y 14 debe ser igual al sueldo.",
						ReglaNegocioCodigoError.SUELDO_INCORRECTO);
			}
			datoLaboralEmpleado.setSueldo(datoLaboral.getSueldo());
			datoLaboralEmpleado.setSueldo01(datoLaboral.getSueldo01());
			datoLaboralEmpleado.setSueldo14(datoLaboral.getSueldo14());
		} else if (idTipoContratacion == EnumTipoContratacion.CONTRATO_FEDERAL) {
			if (!ValidacionUtil.esNumeroPositivo(datoLaboral.getSueldo())) {
				throw new ReglaNegocioException("El sueldo debe ser un número mayor a cero.",
						ReglaNegocioCodigoError.SUELDO_INCORRECTO);
			}
			datoLaboralEmpleado.setSueldo(datoLaboral.getSueldo());

		}

		datoLaboralEmpleado.setTipoRecurso(tipoRecursoTempEntity);
		datoLaboralEmpleado.setUnidadResponsable(unidadResponsable);
		datoLaboralEmpleado.setTabulador(tabulador);
		datoLaboralEmpleado.setNumeroEmpleado(datoLaboral.getNumeroEmpleado());

		configuracionPresupuestoRepository.actualizar(datoLaboralEmpleado);

		String lccNueva = datoLaboralEmpleado.lccDatosLaborales();
		String lccNuevoSueldo = datoLaboralEmpleado.lccSueldo();

		if (!lccActual.equals(lccNueva)) {
			BitacoraEmpleadoDTO bitacora = new BitacoraEmpleadoDTO();
			bitacora.setComentarios(observacionModificacion);
			bitacora.setEmpleado(datoLaboralEmpleado.getEmpleado().getIdEmpleado());
			bitacora.setIdUsuario(idUsuario);
			bitacora.setLccActual(lccNueva);
			bitacora.setLccAnterior(lccActual);
			bitacora.setTipoMovimientoEmpleado(EnumTipoModificacionEmpleado.ACTUALIZACION_DATOS_LABORALES);
			Integer idBitacora = bitacoraModificacionService.registrarBitacora(bitacora);

			if (!lccActualSueldo.equals(lccNuevoSueldo)) {
				String mensajeNotificacion = datoLaboralEmpleado.getEmpleado().getNombreCompleto() + " de "
						+ lccActualSueldo + " por " + lccNuevoSueldo;
				NuevaAutorizacionDTO dto = new NuevaAutorizacionDTO();
				dto.setIdAccion(EnumTiposAccionesAutorizacion.MODIFICACION_SUELDO);
				dto.setIdEntidadContexto(idBitacora);
				dto.setIdUsuarioLogeado(idUsuario);
				dto.setMensajeNotificacion(mensajeNotificacion);
				autorizacionesService.iniciarProcesoAprobacion(dto);
			}

		}

	}

	protected DatoLaboralDTO obtenerConfiguracionPorId(Integer idConfiguracion) {
		if (!ValidacionUtil.esNumeroPositivo(idConfiguracion)) {
			throw new ValidacionException("Es requerido el identificador del puesto",
					ValidacionCodigoError.VALOR_REQUERIDO);
		}
		ConfiguracionPresupuestoEntity datoLaboral = configuracionPresupuestoRepository.obtenerPorId(idConfiguracion);
		if (datoLaboral == null) {
			throw new ValidacionException("No se encontró dato laboral con el identificador solicitado",
					ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);
		}

		DatoLaboralDTO dto = new DatoLaboralDTO();

		dto.setIdDependencia(datoLaboral.getDependencia().getIdDependencia());
		dto.setIdFuenteFinanciamiento(datoLaboral.getFuenteFinanciamiento().getIdFuenteFinanciamiento());
		dto.setIdNombramiento(datoLaboral.getNombramiento().getIdTipoNombramiento());
		dto.setIdProyecto(datoLaboral.getProyecto().getIdProyecto());
		dto.setIdPuesto(datoLaboral.getPuesto().getIdPuestoGeneral());
		dto.setIdSubfuenteFinanciamiento(datoLaboral.getSubfuenteFinanciamiento().getIdFuenteFinanciamiento());
		dto.setIdTabulador(datoLaboral.getTabulador().getIdTabulador());
		dto.setIdTipoRecurso(datoLaboral.getTipoRecurso().getIdTipoRecurso());
		dto.setIdUnidadResponsable(datoLaboral.getUnidadResponsable().getIdUnidadResponsable());
		dto.setSueldo(datoLaboral.getSueldo());
		dto.setTipoContratacion(datoLaboral.getTipoContratacion().getId());
		return dto;
	}

	protected DatoLaboralDTO obtenerDatosLaboralesPuesto(Integer idPuesto) {
		if (!ValidacionUtil.esNumeroPositivo(idPuesto)) {
			throw new ValidacionException("Es requerido el identificador del puesto",
					ValidacionCodigoError.VALOR_REQUERIDO);
		}

		Integer idConfiguracion = inventarioVacanteRepository.obtenerIdDatoLaboralPorPuesto(idPuesto);
		if (!ValidacionUtil.esNumeroPositivo(idConfiguracion)) {
			throw new ValidacionException("El puesto no tiene dato laboral asignado.",
					ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);
		}

		ConfiguracionPresupuestoEntity datoLaboral = configuracionPresupuestoRepository.obtenerPorId(idConfiguracion);
		if (datoLaboral == null) {
			throw new ReglaNegocioException("El puesto no tiene asignada su configuración laboral.",
					ReglaNegocioCodigoError.CONFIGURACION_NO_EXISTE);
		}

		DatoLaboralDTO dto = new DatoLaboralDTO();

		dto.setIdDependencia(datoLaboral.getDependencia().getIdDependencia());
		dto.setIdFuenteFinanciamiento(datoLaboral.getFuenteFinanciamiento().getIdFuenteFinanciamiento());
		dto.setIdNombramiento(datoLaboral.getNombramiento().getIdTipoNombramiento());
		dto.setIdProyecto(datoLaboral.getProyecto().getIdProyecto());
		dto.setIdPuesto(datoLaboral.getPuesto().getIdPuestoGeneral());
		dto.setIdSubfuenteFinanciamiento(datoLaboral.getSubfuenteFinanciamiento().getIdSubfuenteFinanciamiento());
		if (datoLaboral.getTabulador() != null) {
			dto.setIdTabulador(datoLaboral.getTabulador().getIdTabulador());
		}

		dto.setIdTipoRecurso(datoLaboral.getTipoRecurso().getIdTipoRecurso());
		dto.setIdUnidadResponsable(datoLaboral.getUnidadResponsable().getIdUnidadResponsable());
		dto.setSueldo(datoLaboral.getSueldo());
		dto.setTipoContratacion(datoLaboral.getTipoContratacion().getId());
		dto.setFechaInicioLabores(datoLaboral.getFechaInicioLabores());
		dto.setNumeroEmpleado(datoLaboral.getNumeroEmpleado());
		dto.setSueldo01(datoLaboral.getSueldo01());
		dto.setSueldo14(datoLaboral.getSueldo14());
		return dto;
	}

	private Integer generarNombramiento(Integer idTipoContratacion) {
		Integer idNombramiento = null;
		switch (idTipoContratacion) {
		case EnumTipoContratacion.BASE:
			idNombramiento = EnumTipoNombramiento.BASE;
			break;
		case EnumTipoContratacion.CONFIANZA:
			idNombramiento = EnumTipoNombramiento.CONFIANZA;
			break;
		case EnumTipoContratacion.CONTRATO_ESTATAL:
			idNombramiento = EnumTipoNombramiento.EVENTUALES;
			break;
		case EnumTipoContratacion.CONTRATO_FEDERAL:
			idNombramiento = EnumTipoNombramiento.EVENTUALES;
			break;
		case EnumTipoContratacion.HOMOLOGADOS:
			idNombramiento = EnumTipoNombramiento.EVENTUALES;
			break;
		}
		return idNombramiento;
	}

	protected DetalleConfiguracionPresupuestoDTO obtenerDetallePorIdConfiguracion(Integer idConfiguracionPresupuesto) {

		ConfiguracionPresupuestoEntity configuracion = configuracionPresupuestoRepository
				.obtenerPorId(idConfiguracionPresupuesto);
		DetalleConfiguracionPresupuestoDTO detalle = new DetalleConfiguracionPresupuestoDTO();
		detalle.setCodigoPuesto(configuracion.getPuesto().getCodigo());
		if (configuracion.getCuenta() != null) {
			detalle.setCuentaFinanciamiento(configuracion.getCuenta().getDescripcion());
		} else {
			detalle.setCuentaFinanciamiento("SIN CUENTA");
		}
		
		System.out.println("id configuracion :" + configuracion.getId());
		detalle.setDependencia(configuracion.getDependencia().getDescripcion());
		detalle.setEstatus(configuracion.getEstatus().getEstatus());
		detalle.setFuenteFinanciamiento(configuracion.getFuenteFinanciamiento().getDescripcion());
		detalle.setNombramiento(configuracion.getNombramiento().getDescripcion());
		if (configuracion.getEmpleado() != null) {
			detalle.setNombreEmpleado(configuracion.getEmpleado().getNombre());
		}

		detalle.setNumeroEmpleado(configuracion.getNumeroEmpleado());
		detalle.setProyecto(configuracion.getProyecto().getDescripcion());
		detalle.setPuesto(configuracion.getPuesto().getPuesto());
		detalle.setSubfuenteFinanciamiento(configuracion.getSubfuenteFinanciamiento().getDescripcion());
		detalle.setSueldoAutorizado(configuracion.getSueldo());
		if (configuracion.getTabulador() != null) {
			detalle.setSueldoTabulador(configuracion.getTabulador().getSueldoBrutoMensual());
			detalle.setTabulador(configuracion.getTabulador().getTipoTabulador().getDescripcion());
		}
		if (configuracion.getTipoRecurso() != null) {
			detalle.setTipoRecurso(configuracion.getTipoRecurso().getDescripcion());
		} else {
			detalle.setTipoRecurso("SIN TIPO RECURSO ASIGNADO");
		}

		detalle.setUnidadResponsable(configuracion.getUnidadResponsable().getDescripcion());
		detalle.setIdDetalle(configuracion.getId());
		detalle.setTipoContratacion(configuracion.getTipoContratacion().getTipoContratacion());
		detalle.setIdTipoContratacion(configuracion.getTipoContratacion().getId());
		detalle.setIdTipoNombramiento(configuracion.getNombramiento().getIdTipoNombramiento());

		return detalle;

	}

	protected List<InfoConfiguracionDTO> consultarConfiguracionesPorCriterio(FiltroConsultaFinanciamientosDTO filtro) {
		List<InfoConfiguracionDTO> lista = new ArrayList<InfoConfiguracionDTO>();

		lista = configuracionPresupuestoRepository.configuracionesPorTipoContratacion(filtro.getTipoContratacion());

		return lista;
	}

	protected List<InfoConfiguracionDTO> consultarConfiguracionesPorEmpleado(Integer idEmpleado) {
		List<InfoConfiguracionDTO> lista = new ArrayList<InfoConfiguracionDTO>();
		try {
			lista = configuracionPresupuestoRepository.configuracionesPorIdEmpleado(idEmpleado);
			return lista;
		} catch (NoResultException e) {
			return null;
		}

	}

	protected void procesarPadron(int padron) {
		if (padron == EnumTipoContratacion.CONTRATO_ESTATAL) {
			List<TemporalConfEmpleadoEntity> temporales = temporalConfEmpleadoRepository
					.temporalesPorNombramientoSinClasificar("V");
			if (!temporales.isEmpty()) {
				int contador = 1;
				for (TemporalConfEmpleadoEntity t : temporales) {
					System.out.println("procesados" + contador);
					TempContratosEstatalesEntity contratoEstatal = tempContratosEstatalesRepository
							.contratoEstatalPorRfc(t.getRfc());
					if (contratoEstatal != null) {
						t.setTipoContratacion(EnumTipoContratacion.CONTRATO_ESTATAL);
						BigDecimal sueldoMensual = contratoEstatal.getSueldo().multiply(new BigDecimal(2)).setScale(2,
								RoundingMode.DOWN);
						t.setSueldoMensual(sueldoMensual);
						temporalConfEmpleadoRepository.actualizar(t);
						contratoEstatal.setIdTemporalConfiguracionEmpleado(t.getId());
						tempContratosEstatalesRepository.actualizar(contratoEstatal);
					}

					contador++;
				}
			}

		} else if (padron == EnumTipoContratacion.CONTRATO_FEDERAL) {
			List<TemporalConfEmpleadoEntity> temporales = temporalConfEmpleadoRepository
					.temporalesPorNombramientoSinClasificar("V");
			if (!temporales.isEmpty()) {
				int contador = 1;
				for (TemporalConfEmpleadoEntity t : temporales) {
					System.out.println("procesados" + contador);
					TempContratosFederalesEntity contratosFederales = tempContratosFederalesRepository
							.obtenerContratoFederalPorRfc(t.getRfc());

					if (contratosFederales != null) {
						ProgramaEntity programa = programaRepository
								.programaPorDescripcion(contratosFederales.getPrograma());
						if (programa != null) {
							if (programa.getIdPrograma() == 5) {
								t.setTipoContratacion(EnumTipoContratacion.CONTRATO_ESTATAL);
							} else {
								t.setTipoContratacion(EnumTipoContratacion.CONTRATO_FEDERAL);

							}

							t.setSueldoMensual(contratosFederales.getSueldoMensual());
							t.setIdPrograma(programa.getIdPrograma());
							temporalConfEmpleadoRepository.actualizar(t);

							contratosFederales.setIdTemporalConfiguracionEmpleado(t.getId());
							tempContratosFederalesRepository.actualizar(contratosFederales);
							contador++;

						}

					}

				}
			}

		}

	}

	protected InfoDatosLaboralesDTO obtenerDatosLaboralesIdPuesto(Integer idPuesto) {
		if (!ValidacionUtil.esNumeroPositivo(idPuesto)) {
			throw new ValidacionException("El puesto es requerido, comuniquese con sistemas.",
					ValidacionCodigoError.VALOR_REQUERIDO);
		}
		Integer idConfiguracion = inventarioVacanteRepository.obtenerIdDatoLaboralPorPuesto(idPuesto);
		if (idConfiguracion == null) {
			throw new ReglaNegocioException(
					"El puesto no tiene configurado datos laborales, es requerido asignarle los datos correspondientes.",
					ReglaNegocioCodigoError.CONFIGURACION_NO_EXISTE);
		}

		InfoDatosLaboralesDTO dto = new InfoDatosLaboralesDTO();
		ConfiguracionPresupuestoEntity datosLaborales = configuracionPresupuestoRepository
				.obtenerPorId(idConfiguracion);
		if (datosLaborales == null) {
			throw new ReglaNegocioException(
					"La configuración con identificador " + idConfiguracion
							+ " no es valida, comuniquese con sistemas.",
					ReglaNegocioCodigoError.CONFIGURACION_NO_EXISTE);
		}

		dto.setNumeroEmpleado(datosLaborales.getNumeroEmpleado());
		dto.setProyecto(datosLaborales.getProyecto().getDescripcion());
		dto.setDependencia(datosLaborales.getDependencia().getDescripcion());
		dto.setUnidadResponsable(datosLaborales.getUnidadResponsable().getDescripcion());
		dto.setNombramiento(datosLaborales.getNombramiento().getDescripcion());
		if (datosLaborales.getPuesto() != null) {
			dto.setPuesto(datosLaborales.getPuesto().getCodigo() + "-" + datosLaborales.getPuesto().getPuesto());
		} else {
			dto.setPuesto("SIN PUESTO ASIGNADO.");
		}
		dto.setFuenteFinanciamiento(datosLaborales.getFuenteFinanciamiento().getDescripcion());
		dto.setSubfuenteFinanciamiento(datosLaborales.getSubfuenteFinanciamiento().getDescripcion());
		dto.setTipoRecurso(datosLaborales.getTipoRecurso().getDescripcion());

		if (datosLaborales.getTabulador() == null) {
			throw new ReglaNegocioException("El empleado no tiene asignado un tabulador, es requerido asignarle uno.",
					ReglaNegocioCodigoError.TABULADOR_NO_CONFIGURADO);
		}

		dto.setSueldo(tabuladorService.obtenerInfoSueldoIdTabulador(datosLaborales.getTabulador()));

		return dto;
	}
}
