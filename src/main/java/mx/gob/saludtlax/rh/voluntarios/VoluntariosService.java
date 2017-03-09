package mx.gob.saludtlax.rh.voluntarios;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.EnumEstatusAspirante;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.SistemaCodigoError;
import mx.gob.saludtlax.rh.excepciones.SistemaException;
import mx.gob.saludtlax.rh.persistencia.AdscripcionEntity;
import mx.gob.saludtlax.rh.persistencia.AdscripcionRepository;
import mx.gob.saludtlax.rh.persistencia.AspiranteEntity;
import mx.gob.saludtlax.rh.persistencia.AspiranteRepository;
import mx.gob.saludtlax.rh.persistencia.EmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.EstatusPuestosEntity;
import mx.gob.saludtlax.rh.persistencia.EstatusPuestosRepository;
import mx.gob.saludtlax.rh.persistencia.FuncionEntity;
import mx.gob.saludtlax.rh.persistencia.FuncionRepository;
import mx.gob.saludtlax.rh.persistencia.InventarioVacanteEntity;
import mx.gob.saludtlax.rh.persistencia.InventarioVacanteRepository;
import mx.gob.saludtlax.rh.persistencia.ProgramaEntity;
import mx.gob.saludtlax.rh.persistencia.ProgramaRepository;
import mx.gob.saludtlax.rh.persistencia.ServicioEntity;
import mx.gob.saludtlax.rh.persistencia.ServicioRepository;
import mx.gob.saludtlax.rh.persistencia.SubadscripcionEntity;
import mx.gob.saludtlax.rh.persistencia.SubadscripcionRepository;
import mx.gob.saludtlax.rh.persistencia.TipoContratacionEntity;
import mx.gob.saludtlax.rh.persistencia.TipoContratacionRepository;
import mx.gob.saludtlax.rh.persistencia.VoluntarioEntity;
import mx.gob.saludtlax.rh.persistencia.VoluntarioRepository;
import mx.gob.saludtlax.rh.puestosautorizados.EnumEstatusPuesto;
import mx.gob.saludtlax.rh.puestosautorizados.EnumTipoContratacion;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

public class VoluntariosService {
	@Inject
	private AdscripcionRepository adscripcionRepository;
	@Inject
	private AspiranteRepository aspiranteRepository;

	@Inject
	private EmpleadoRepository empleadoRepository;

	@Inject
	private EstatusPuestosRepository estatusPuestoRepository;
	@Inject
	private FuncionRepository funcionRepository;
	@Inject
	private InventarioVacanteRepository inventarioVacanteRepository;

	@Inject
	private ProgramaRepository programaRepository;
	@Inject
	private ServicioRepository servicioRepository;
	@Inject
	private SubadscripcionRepository subadscripcionRepository;
	@Inject
	private TipoContratacionRepository tipoContratacionRepository;
	@Inject
	private VoluntarioRepository voluntarioRepository;

	protected void registrarVoluntario(AltaVoluntarioDTO alta) {

		AspiranteEntity aspiranteEntity = aspiranteRepository.obtenerPorId(alta.getIdAspirante());

		if (aspiranteEntity == null) {
			throw new SistemaException("No se encontró aspirante con identificador " + alta.getIdAspirante(),
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

		VoluntarioEntity nuevoVoluntario = new VoluntarioEntity();
		nuevoVoluntario.setApellidoMaterno(aspiranteEntity.getApellidoMaterno());
		nuevoVoluntario.setApellidoPaterno(aspiranteEntity.getApellidoPaterno());
		nuevoVoluntario.setCurp(aspiranteEntity.getCurp());
		nuevoVoluntario.setFechaFinContratacion(alta.getFechaFinal());
		nuevoVoluntario.setFechaIngreso(alta.getFechaIngreso());
		nuevoVoluntario.setFechaInicioContratacion(alta.getFechaInicial());
		nuevoVoluntario.setNombre(aspiranteEntity.getNombre());
		nuevoVoluntario.setNombreCompleto(aspiranteEntity.getNombreCompleto());
		nuevoVoluntario.setNumeroCuenta(alta.getNumeroCuenta());
		nuevoVoluntario.setSueldo(alta.getSueldoMensual());
		nuevoVoluntario.setRfc(aspiranteEntity.getRfc());
		voluntarioRepository.crear(nuevoVoluntario);

		EstatusPuestosEntity estatusPuesto = estatusPuestoRepository
				.obtenerPorId(EnumEstatusPuesto.APERTURA_DESIGNACION);

		TipoContratacionEntity tipoContratacion = tipoContratacionRepository
				.obtenerPorId(EnumTipoContratacion.VOLUNTARIOS);

		aspiranteEntity.setIdEstatus(EnumEstatusAspirante.EMPLEADO);
		aspiranteRepository.actualizar(aspiranteEntity);

		// Generar folios
		Integer ultimoFolio = inventarioVacanteRepository.ultimoFolioVacanteContratacion(tipoContratacion.getId());
		Integer siguienteNumeroVacante = 1;
		if (ultimoFolio != null) {
			siguienteNumeroVacante = ultimoFolio + 1;
		}
		String folioVacante = generarFolioVacante(siguienteNumeroVacante, tipoContratacion.getCodigo());

		// Crear puesto
		InventarioVacanteEntity puestoEmpleado = new InventarioVacanteEntity();
		puestoEmpleado.setTipoContratacion(tipoContratacion);
		puestoEmpleado.setNumeroVacante(siguienteNumeroVacante);
		puestoEmpleado.setCodigoVacante(tipoContratacion.getCodigo());
		puestoEmpleado.setFolioVacante(folioVacante);
		puestoEmpleado.setEstatus(estatusPuesto);
		puestoEmpleado.setDisponible("NO");
		puestoEmpleado.setVoluntario(nuevoVoluntario);
		puestoEmpleado.setFechaInicio(alta.getFechaInicial());
		puestoEmpleado.setFechaFin(alta.getFechaFinal());
		if (ValidacionUtil.esNumeroPositivo(alta.getIdPrograma())) {
			ProgramaEntity programa = programaRepository.obtenerPorId(alta.getIdPrograma());
			puestoEmpleado.setPrograma(programa);
		}
		if (!ValidacionUtil.esNumeroPositivo(alta.getIdAdscripcion())) {
			AdscripcionEntity adscripcion = adscripcionRepository.obtenerPorId(alta.getIdAdscripcion());
			puestoEmpleado.setAdscripcion(adscripcion);
		}

		if (!ValidacionUtil.esNumeroPositivo(alta.getIdSubadscripcion())) {
			SubadscripcionEntity subadscripcion = subadscripcionRepository.obtenerPorId(alta.getIdSubadscripcion());
			puestoEmpleado.setSubadscripcion(subadscripcion);
		}
		if (!ValidacionUtil.esNumeroPositivo(alta.getIdServicio())) {
			ServicioEntity servicio = servicioRepository.obtenerPorId(alta.getIdServicio());
			puestoEmpleado.setServicio(servicio);
		}

		if (!ValidacionUtil.esNumeroPositivo(alta.getIdFuncion())) {
			FuncionEntity funcion = funcionRepository.obtenerPorId(alta.getIdFuncion());
			puestoEmpleado.setFuncion(funcion);
		}

		inventarioVacanteRepository.crear(puestoEmpleado);

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

	protected List<InfoVoluntarioDTO> consultarVoluntarios(ConsultaVoluntarioDTO dto) {
		List<InfoVoluntarioDTO> voluntarios = new ArrayList<>();
		List<InventarioVacanteEntity> puestos = new ArrayList<>();
		if (dto.getTipoConsulta() == EnumTipoConsultaVoluntario.ACTIVOS) {
			puestos = inventarioVacanteRepository.consultarVoluntariosActivos();
		} else if (dto.getTipoConsulta() == EnumTipoConsultaVoluntario.NOMBRE) {
			puestos = inventarioVacanteRepository.consultarVoluntariosPorCriterio(dto.getCriterio());
		}
		System.out.println("consulta voluntarios" + puestos.size());
		if (!puestos.isEmpty()) {
			for (InventarioVacanteEntity entity : puestos) {
				InfoVoluntarioDTO info = new InfoVoluntarioDTO();
				if (entity.getAdscripcion() != null) {
					info.setAdscripcion(entity.getAdscripcion().getAdscripcion());
					info.setIdAdscripcion(entity.getAdscripcion().getIdAdscripcion());
				}
				info.setCurp(entity.getVoluntario().getCurp());
				info.setFechaFin(entity.getFechaFin());
				info.setFechaIngreso(entity.getVoluntario().getFechaIngreso());
				info.setFechaInicio(entity.getFechaInicio());
				if (entity.getFuncion() != null) {
					info.setFuncion(entity.getFuncion().getFuncion());
					info.setIdFuncion(entity.getFuncion().getIdFuncion());
				}
				if (entity.getPrograma() != null) {
					info.setIdPrograma(entity.getPrograma().getIdPrograma());
					info.setPrograma(entity.getPrograma().getPrograma());
				}
				info.setIdPuesto(entity.getIdVacante());
				if (entity.getServicio() != null) {
					info.setIdServicio(entity.getServicio().getIdServicio());
					info.setServicio(entity.getServicio().getServicio());
				}
				if (entity.getSubadscripcion() != null) {
					info.setIdSubadscripcion(entity.getSubadscripcion().getIdSubadscripcion());
					info.setSubadscripcion(entity.getSubadscripcion().getSubadscripcion());
				}

				info.setSueldo(entity.getVoluntario().getSueldo());
				info.setVoluntario(entity.getVoluntario().getNombreCompleto());
				info.setNumeroCuenta(entity.getVoluntario().getNumeroCuenta());
				voluntarios.add(info);

			}
		}
		return voluntarios;
	}

}
