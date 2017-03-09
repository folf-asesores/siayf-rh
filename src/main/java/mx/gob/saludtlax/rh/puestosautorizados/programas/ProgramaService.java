/**
 * 
 */
package mx.gob.saludtlax.rh.puestosautorizados.programas;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.autorizaciones.Autorizaciones;
import mx.gob.saludtlax.rh.autorizaciones.EnumTiposAccionesAutorizacion;
import mx.gob.saludtlax.rh.autorizaciones.NuevaAutorizacionDTO;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.persistencia.DependenciaTempEntity;
import mx.gob.saludtlax.rh.persistencia.DependenciaTempRepository;
import mx.gob.saludtlax.rh.persistencia.DetalleProgramaEntity;
import mx.gob.saludtlax.rh.persistencia.DetalleProgramaRepository;
import mx.gob.saludtlax.rh.persistencia.FuenteFinanciamientoEntity;
import mx.gob.saludtlax.rh.persistencia.FuenteFinanciamientoRepository;
import mx.gob.saludtlax.rh.persistencia.ProgramaEntity;
import mx.gob.saludtlax.rh.persistencia.ProgramaRepository;
import mx.gob.saludtlax.rh.persistencia.ProyectoTempEntity;
import mx.gob.saludtlax.rh.persistencia.ProyectoTempRepository;
import mx.gob.saludtlax.rh.persistencia.SubFuenteFinanciamientoTempEntity;
import mx.gob.saludtlax.rh.persistencia.SubFuenteFinanciamientoTempRepository;
import mx.gob.saludtlax.rh.persistencia.TipoRecursoTempEntity;
import mx.gob.saludtlax.rh.persistencia.TipoRecursoTempRepository;
import mx.gob.saludtlax.rh.persistencia.UnidadResponsableEntity;
import mx.gob.saludtlax.rh.persistencia.UnidadResponsableRepository;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 25/08/2016 20:29:10
 */
public class ProgramaService {
	@Inject
	private Autorizaciones autorizaciones;
	@Inject
	private DependenciaTempRepository dependenciasRepository;
	@Inject
	private FuenteFinanciamientoRepository fuenteFinanciamientoRepository;
	@Inject
	private SubFuenteFinanciamientoTempRepository subfuentefinanciamientoRepository;
	@Inject
	private ProyectoTempRepository proyectoRepository;
	@Inject
	private TipoRecursoTempRepository tipoRecursoTempRepository;
	@Inject
	private UnidadResponsableRepository unidadResponsableRepository;

	@Inject
	private ProgramaRepository programaRepository;

	@Inject
	private DetalleProgramaRepository detalleProgramaRepository;

	protected void crearPrograma(ProgramaDTO programaDTO, Integer idUsuario) {
		ProyectoTempEntity proyecto = proyectoRepository
				.obtenerPorId(programaDTO.getIdProyecto());
		SubFuenteFinanciamientoTempEntity subfuenteFinanciamiento = subfuentefinanciamientoRepository
				.obtenerPorId(programaDTO.getIdSubfuentefinanciamiento());
		TipoRecursoTempEntity tipoRecursoTempEntity = tipoRecursoTempRepository
				.obtenerPorId(programaDTO.getIdTipoRecurso());
		UnidadResponsableEntity unidadResponsable = unidadResponsableRepository
				.obtenerPorId(programaDTO.getIdUnidadResponsable());

		DependenciaTempEntity dependencia = dependenciasRepository
				.obtenerPorId(programaDTO.getIdDependencia());
		FuenteFinanciamientoEntity fuenteFinanciamiento = fuenteFinanciamientoRepository
				.obtenerPorId(programaDTO.getIdFuenteFinanciamiento());

		ProgramaEntity entity = new ProgramaEntity();
		entity.setDependencia(dependencia);
		entity.setFuenteFinanciamiento(fuenteFinanciamiento);
		entity.setIdTipoConfiguracion(programaDTO.getTipoConfiguracion());
		entity.setPrograma(programaDTO.getPrograma());
		entity.setProyecto(proyecto);
		entity.setSubfuenteFinanciamiento(subfuenteFinanciamiento);
		entity.setTipoRecurso(tipoRecursoTempEntity);
		entity.setUnidadResponsable(unidadResponsable);
		programaRepository.crear(entity);

	}

	protected void crearDetallePrograma(DetalleProgramaDTO detalleProgramaDTO) {

		ProgramaEntity programa = programaRepository
				.obtenerPorId(detalleProgramaDTO.getIdPrograma());

		if (programa == null) {
			throw new ValidacionException(
					"No existe programa con el identificador especificado "
							+ detalleProgramaDTO.getIdPrograma(),
					ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);
		}

		if (programa.getProyecto() == null) {
			throw new ValidacionException(
					"El programa no tiene asignado un proyecto es necesario asignar uno para registrar un detalle."
							+ detalleProgramaDTO.getIdPrograma(),
					ValidacionCodigoError.VALOR_REQUERIDO);
		}

		if (programa.getDependencia() == null) {
			throw new ValidacionException(
					"El programa no tiene asignado una dependencia es necesario asignar una para registrar un detalle."
							+ detalleProgramaDTO.getIdPrograma(),
					ValidacionCodigoError.VALOR_REQUERIDO);
		}

		if (programa.getUnidadResponsable() == null) {
			throw new ValidacionException(
					"El programa no tiene asignado una unidad responsable es necesario asignar una para registrar un detalle."
							+ detalleProgramaDTO.getIdPrograma(),
					ValidacionCodigoError.VALOR_REQUERIDO);
		}

		if (programa.getFuenteFinanciamiento() == null) {
			throw new ValidacionException(
					"El programa no tiene asignado una fuente de financiamiento es necesario asignar una para registrar un detalle."
							+ detalleProgramaDTO.getIdPrograma(),
					ValidacionCodigoError.VALOR_REQUERIDO);
		}

		if (programa.getSubfuenteFinanciamiento() == null) {
			throw new ValidacionException(
					"El programa no tiene asignado una subfuente de financiamiento es necesario asignar una para registrar un detalle."
							+ detalleProgramaDTO.getIdPrograma(),
					ValidacionCodigoError.VALOR_REQUERIDO);
		}

		if (detalleProgramaDTO.getMesesContratacion() > 12) {
			throw new ValidacionException(
					"El periodo de contratación no debe ser mayor a 12",
					ValidacionCodigoError.VALOR_NO_PERMITIDO);
		}

		if (programa.getTipoRecurso() == null) {
			throw new ValidacionException(
					"El programa no tiene asignado un tipo de recurso es necesario asignar una para registrar un detalle."
							+ detalleProgramaDTO.getIdPrograma(),
					ValidacionCodigoError.VALOR_REQUERIDO);
		}

		DetalleProgramaEntity d = new DetalleProgramaEntity();
		d.setTipoDetallePrograma(detalleProgramaDTO.getIdTipoDetalle());

		if (detalleProgramaDTO.getIdTipoDetalle().equals(
				EnumTipoDetallePrograma.PARTIDA)) {
			d.setClave(detalleProgramaDTO.getClave());
			d.setCostoUnitario(detalleProgramaDTO.getCostoUnitario());
			d.setMesesContratacion(detalleProgramaDTO.getMesesContratacion());
			d.setNumeroPersonas(detalleProgramaDTO.getNumeroPersonas());
			d.setDescripcion(detalleProgramaDTO.getDescripcion());
			d.setTotalGlobal(detalleProgramaDTO.getTotalGlobal());
			d.setIdPrograma(detalleProgramaDTO.getIdPrograma());
			d.setEstatus(EnumEstatusDetallePrograma.ESPERA_AUTORIZACION);
			detalleProgramaRepository.crear(d);

			String mensajeNotificacion = d.getNumeroPersonas()
					+ " vacantes para el programa "
					+ programa.getPrograma().toUpperCase()
					+ " por un lapso de tiempo de " + d.getMesesContratacion()
					+ " meses con un costo por persona de "
					+ d.getCostoUnitario();

			NuevaAutorizacionDTO dto = new NuevaAutorizacionDTO();
			dto.setIdAccion(EnumTiposAccionesAutorizacion.APERTURA_VACANTE_PROGRAMA_FEDERAL_POR_DETALLE);
			dto.setIdEntidadContexto(d.getId());
			dto.setIdUsuarioLogeado(detalleProgramaDTO.getIdUsuario());
			dto.setMensajeNotificacion(mensajeNotificacion);
			autorizaciones.iniciarProcesoAutorizacion(dto);
		}

	}

	protected ProgramaDTO obtenerProgramaPorId(Integer idPrograma) {
		ProgramaEntity p = programaRepository.obtenerPorId(idPrograma);
		ProgramaDTO dto = new ProgramaDTO();

		dto.setIdProyecto(p.getProyecto().getIdProyecto());
		dto.setIdDependencia(p.getDependencia().getIdDependencia());
		if (p.getUnidadResponsable() != null) {
			dto.setIdUnidadResponsable(p.getUnidadResponsable()
					.getIdUnidadResponsable());
		}
		dto.setIdFuenteFinanciamiento(p.getFuenteFinanciamiento()
				.getIdFuenteFinanciamiento());
		dto.setIdSubfuentefinanciamiento(p.getSubfuenteFinanciamiento()
				.getIdSubfuenteFinanciamiento());
		dto.setIdTipoRecurso(p.getTipoRecurso().getIdTipoRecurso());
		return dto;
	}

	protected List<InfoProgramaDTO> consultarProgramas() {
		List<InfoProgramaDTO> programas = new ArrayList<>();

		List<ProgramaEntity> lista = programaRepository.consultarProgramas();
		if (!lista.isEmpty()) {
			for (ProgramaEntity p : lista) {
				InfoProgramaDTO programaDTO = new InfoProgramaDTO();
				programaDTO.setIdPrograma(p.getIdPrograma());
				programaDTO.setPrograma(p.getPrograma());
				programas.add(programaDTO);
			}
		}

		return programas;
	}

	protected List<InfoDetallePrograma> consultarDetallesProgramas(
			Integer idPrograma) {
		if (!ValidacionUtil.esNumeroPositivo(idPrograma)) {
			throw new ValidacionException(
					"El identificador del programa del que desea obtener detalle es requerido.",
					ValidacionCodigoError.VALOR_REQUERIDO);
		}

		List<InfoDetallePrograma> detalles = new ArrayList<>();
		List<DetalleProgramaEntity> lista = detalleProgramaRepository
				.consultarDetallesPrograma(idPrograma);

		if (!lista.isEmpty()) {
			for (DetalleProgramaEntity e : lista) {
				InfoDetallePrograma dto = new InfoDetallePrograma();
				dto.setClave(e.getClave());
				dto.setDescripcion(e.getDescripcion());
				dto.setEstatus(e.getEstatus());
				dto.setMesesContratacion(e.getMesesContratacion());
				dto.setNumeroPersonas(e.getNumeroPersonas());
				dto.setPrecioUnitario(e.getCostoUnitario());
				dto.setTotalGlobal(e.getTotalGlobal());
				dto.setTipoDetalle(e.getTipoDetallePrograma());
				detalles.add(dto);
			}
		}
		return detalles;
	}

	public ConfiguracionDetalleProgramaDTO obtenerDetallePrograma(
			Integer idDetallePrograma) {
		if (!ValidacionUtil.esNumeroPositivo(idDetallePrograma)) {
			throw new ValidacionException(
					"El detalle del programa es requerido.",
					ValidacionCodigoError.VALOR_REQUERIDO);
		}

		DetalleProgramaEntity detallePrograma = detalleProgramaRepository
				.obtenerPorId(idDetallePrograma);
		if (detallePrograma == null) {
			throw new ValidacionException("El detalle con identificador "
					+ idDetallePrograma + " no se ha encontrado",
					ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);
		}

		InfoDetallePrograma infoDetalle = new InfoDetallePrograma();
		infoDetalle.setClave(detallePrograma.getClave());
		infoDetalle.setDescripcion(detallePrograma.getDescripcion());
		infoDetalle.setEstatus(detallePrograma.getEstatus());
		infoDetalle
				.setMesesContratacion(detallePrograma.getMesesContratacion());
		infoDetalle.setNumeroPersonas(detallePrograma.getNumeroPersonas());
		infoDetalle.setPrecioUnitario(detallePrograma.getCostoUnitario());
		infoDetalle.setTotalGlobal(detallePrograma.getTotalGlobal());
		infoDetalle.setTipoDetalle(detallePrograma.getTipoDetallePrograma());

		ProgramaEntity programa = programaRepository
				.obtenerPorId(detallePrograma.getIdPrograma());

		ConfiguracionDetalleProgramaDTO configuracionDetallePrograma = new ConfiguracionDetalleProgramaDTO();
		configuracionDetallePrograma.setNombrePrograma(programa.getPrograma()
				.toLowerCase());
		if (detallePrograma.getTipoDetallePrograma().equals(
				EnumTipoDetallePrograma.PARTIDA)) {
			configuracionDetallePrograma.setDependencia(programa
					.getDependencia().getDescripcion());
			configuracionDetallePrograma.setFuenteFinanciamiento(programa
					.getFuenteFinanciamiento().getDescripcion());
			configuracionDetallePrograma.setProyecto(programa.getProyecto()
					.getDescripcion());
			configuracionDetallePrograma.setSubFuenteFinanciamiento(programa
					.getSubfuenteFinanciamiento().getDescripcion());
			configuracionDetallePrograma.setTipoRecurso(programa
					.getTipoRecurso().getDescripcion());
			configuracionDetallePrograma.setUnidadResponsable(programa
					.getUnidadResponsable().getDescripcion());
		}
		configuracionDetallePrograma.setDetallePrograma(infoDetalle);

		return configuracionDetallePrograma;

	}

	protected void editarPrograma(ProgramaDTO edicionPrograma) {
		if (!ValidacionUtil.esNumeroPositivo(edicionPrograma.getIdPrograma())) {
			throw new ValidacionException("Seleccione un programa",
					ValidacionCodigoError.VALOR_REQUERIDO);
		}
		ProgramaEntity entity = programaRepository.obtenerPorId(edicionPrograma
				.getIdPrograma());
		if (entity == null) {
			throw new ValidacionException("El programa no existe",
					ValidacionCodigoError.VALOR_REQUERIDO);
		}
		ProyectoTempEntity proyecto = proyectoRepository
				.obtenerPorId(edicionPrograma.getIdProyecto());
		SubFuenteFinanciamientoTempEntity subfuenteFinanciamiento = subfuentefinanciamientoRepository
				.obtenerPorId(edicionPrograma.getIdSubfuentefinanciamiento());
		TipoRecursoTempEntity tipoRecursoTempEntity = tipoRecursoTempRepository
				.obtenerPorId(edicionPrograma.getIdTipoRecurso());
		UnidadResponsableEntity unidadResponsable = unidadResponsableRepository
				.obtenerPorId(edicionPrograma.getIdUnidadResponsable());

		DependenciaTempEntity dependencia = dependenciasRepository
				.obtenerPorId(edicionPrograma.getIdDependencia());
		FuenteFinanciamientoEntity fuenteFinanciamiento = fuenteFinanciamientoRepository
				.obtenerPorId(edicionPrograma.getIdFuenteFinanciamiento());

		entity.setDependencia(dependencia);
		entity.setFuenteFinanciamiento(fuenteFinanciamiento);
		entity.setPrograma(edicionPrograma.getPrograma());
		entity.setProyecto(proyecto);
		entity.setSubfuenteFinanciamiento(subfuenteFinanciamiento);
		entity.setTipoRecurso(tipoRecursoTempEntity);
		entity.setUnidadResponsable(unidadResponsable);
		programaRepository.actualizar(entity);

	}

	protected ProgramaDTO obtenerDetalleProgramaPorId(Integer idPrograma) {
		if (!ValidacionUtil.esNumeroPositivo(idPrograma)) {
			throw new ValidacionException("Seleccione un programa",
					ValidacionCodigoError.VALOR_REQUERIDO);
		}
		ProgramaEntity p = programaRepository.obtenerPorId(idPrograma);
		if (p == null) {
			throw new ValidacionException("El programa no existe",
					ValidacionCodigoError.VALOR_REQUERIDO);
		}
		ProgramaDTO programa = new ProgramaDTO();
		programa.setIdPrograma(p.getIdPrograma());
		programa.setPrograma(p.getPrograma());

		if (p.getProyecto() != null) {
			programa.setIdProyecto(p.getProyecto().getIdProyecto());
		}

		if (p.getDependencia() != null) {
			programa.setIdDependencia(p.getDependencia().getIdDependencia());
		}

		if (p.getUnidadResponsable() != null) {
			programa.setIdUnidadResponsable(p.getUnidadResponsable()
					.getIdUnidadResponsable());
		}

		if (p.getFuenteFinanciamiento() != null) {
			programa.setIdFuenteFinanciamiento(p.getFuenteFinanciamiento()
					.getIdFuenteFinanciamiento());
		}

		if (p.getSubfuenteFinanciamiento() != null) {
			programa.setIdSubfuentefinanciamiento(p
					.getSubfuenteFinanciamiento()
					.getIdSubfuenteFinanciamiento());
		}

		if (p.getTipoRecurso() != null) {
			programa.setIdTipoRecurso(p.getTipoRecurso().getIdTipoRecurso());
		}

		return programa;

	}
}
