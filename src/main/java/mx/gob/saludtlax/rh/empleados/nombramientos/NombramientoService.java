/**
 * 
 */
package mx.gob.saludtlax.rh.empleados.nombramientos;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.empleados.administracion.EnumEstadoCivil;
import mx.gob.saludtlax.rh.empleados.administracion.EnumTipoSexo;
import mx.gob.saludtlax.rh.empleados.nombramientos.impresion.ClasificacionReporteDTO;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.persistencia.ClasificacionNombramientoEntity;
import mx.gob.saludtlax.rh.persistencia.ClasificacionNombramientoRepository;
import mx.gob.saludtlax.rh.persistencia.ClasificacionReporteEntity;
import mx.gob.saludtlax.rh.persistencia.ClasificacionReporteRepository;
import mx.gob.saludtlax.rh.persistencia.EstructuraReporteEntity;
import mx.gob.saludtlax.rh.persistencia.EstructuraReporteRepository;
import mx.gob.saludtlax.rh.persistencia.NombramientoEntity;
import mx.gob.saludtlax.rh.persistencia.NombramientoRepository;
import mx.gob.saludtlax.rh.persistencia.TiposDuracionJornadaEntity;
import mx.gob.saludtlax.rh.persistencia.TiposDuracionJornadaRepository;
import mx.gob.saludtlax.rh.persistencia.TiposNombramientosEntity;
import mx.gob.saludtlax.rh.persistencia.TiposNombramientosRepository;
import mx.gob.saludtlax.rh.util.FechaUtil;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author Leila Schiaffini Ehuan
 * @since 12/09/2016 11:52:57
 * 
 */
public class NombramientoService {
	@Inject
	private ClasificacionNombramientoRepository clasificacionNombramientoRepository;
	@Inject
	private NombramientoRepository nombramientoRepository;
	@Inject
	private TiposDuracionJornadaRepository tipoJornadaRepository;
	@Inject
	private TiposNombramientosRepository tiposNombramientoRepository;
	@Inject
	private ClasificacionReporteRepository clasificacionReporteRepository;
	@Inject
	private EstructuraReporteRepository estructuraReporteRepository;

	public void crearNombramiento(NombramientoDTO dto, Integer tipoNombramiento, Integer idTipoJornada) {
		ClasificacionNombramientoEntity clasificacionNombramiento = clasificacionNombramientoRepository
				.obtenerPorId(dto.getIdClasificacionNombramiento());
		TiposDuracionJornadaEntity tipoJornada = tipoJornadaRepository.obtenerPorId(idTipoJornada);
		TiposNombramientosEntity tiposNombramiento = tiposNombramientoRepository.obtenerPorId(tipoNombramiento);

		NombramientoEntity nombramiento = new NombramientoEntity();

		nombramiento.setClasificacionNombramiento(clasificacionNombramiento);
		nombramiento.setClavePresupuestal(dto.getClavePresupuestal());
		nombramiento.setDomicilio(dto.getEmpleado().getDireccionCompleta());
		nombramiento.setEdad(FechaUtil.calcularEdad(dto.getEmpleado().getFechaNacimiento()));
		nombramiento.setEmpleado(dto.getEmpleado());
		String estadoCivil = generarEstadoCivil(dto.getEmpleado().getIdSexo(), dto.getEmpleado().getEstadoCivil());
		nombramiento.setEstadoCivil(estadoCivil);
		nombramiento.setSueldo(dto.getSueldo());
		nombramiento.setTipoJornada(tipoJornada);
		nombramiento.setTiposNombramientos(tiposNombramiento);

		nombramientoRepository.crear(nombramiento);

	}

	private String generarEstadoCivil(String sexo, String estadoCivilEmpleado) {
		String estadoCivil = "";
		if (sexo.equals(EnumTipoSexo.FEMENINO)) {
			switch (estadoCivilEmpleado) {
			case EnumEstadoCivil.CASADO:
				estadoCivil = "CASADA";
				break;
			case EnumEstadoCivil.SOLTERO:
				estadoCivil = "SOLTERA";
				break;
			case EnumEstadoCivil.DIVORCIADO:
				estadoCivil = "DIVORCIADA";
				break;
			case EnumEstadoCivil.VIUDO:
				estadoCivil = "VIUDA";
				break;
			}
		} else if (sexo.equals(EnumTipoSexo.MASCULINO)) {
			switch (estadoCivilEmpleado) {
			case EnumEstadoCivil.CASADO:
				estadoCivil = "CASADO";
				break;
			case EnumEstadoCivil.SOLTERO:
				estadoCivil = "SOLTERO";
				break;
			case EnumEstadoCivil.DIVORCIADO:
				estadoCivil = "DIVORCIADO";
				break;
			case EnumEstadoCivil.VIUDO:
				estadoCivil = "VIUDO";
				break;
			}
		}

		return estadoCivil;
	}

	protected void actualizarNombramientoImpreso(Integer idNombramiento, String tipoAdscripcion, boolean impreso) {
		String contexto = "actualizarNombramientoPorImpresion: ";

		if (idNombramiento == null) {
			throw new ValidacionException(contexto + "El identificador del nombramiento es requerido.",
					ValidacionCodigoError.VALOR_REQUERIDO);
		}

		if (ValidacionUtil.esCadenaVacia(tipoAdscripcion)) {
			throw new ValidacionException(contexto + "El tipo de adscripción es requerido.",
					ValidacionCodigoError.VALOR_REQUERIDO);
		}

		NombramientoEntity nombramientoEntity = nombramientoRepository.obtenerPorId(idNombramiento);

		if (nombramientoEntity == null) {
			throw new ReglaNegocioException(contexto + "El nombramiento no se encuentra registrado",
					ReglaNegocioCodigoError.SIN_REGISTRO);
		}

		nombramientoEntity.setAdscripcion(tipoAdscripcion);
		if (impreso) {
			nombramientoEntity.setEstatus(EnumEstatusNombramiento.IMPRESO);
			nombramientoEntity.setFechaImpreso(FechaUtil.fechaActual());
		}

		nombramientoRepository.actualizar(nombramientoEntity);

	}

	protected Integer actualizarEstructuraNombramiento(ClasificacionReporteDTO clasificacionReporteDTO) {

		ClasificacionReporteEntity clasificacionReporteEntity = new ClasificacionReporteEntity();

		clasificacionReporteEntity.setClasificacionReporte(clasificacionReporteDTO.getClasificacionReporte());

		clasificacionReporteRepository.crear(clasificacionReporteEntity);

		EstructuraReporteEntity estructuraReporteEntity = new EstructuraReporteEntity();

		estructuraReporteEntity.setClasificacionReporte(clasificacionReporteEntity);
		estructuraReporteEntity.setTextoUno(clasificacionReporteDTO.getTextoUno());
		estructuraReporteEntity.setHorarioTrabajo(clasificacionReporteDTO.getHorarioTrabajo());
		estructuraReporteEntity.setSustituye(clasificacionReporteDTO.getSustituye());
		estructuraReporteEntity.setTextoDos(clasificacionReporteDTO.getTextoDos());
		estructuraReporteEntity.setNombreSecretario(clasificacionReporteDTO.getNombreSecretario());
		estructuraReporteEntity.setFechaPosicionUno(clasificacionReporteDTO.getFechaPosicionUno());

		estructuraReporteRepository.crear(estructuraReporteEntity);

		return estructuraReporteEntity.getClasificacionReporte().getIdClasificacionReporte();
	}

}
