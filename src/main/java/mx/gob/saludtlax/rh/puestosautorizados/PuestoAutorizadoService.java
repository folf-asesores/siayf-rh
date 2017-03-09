/**
 * 
 * 
 */
package mx.gob.saludtlax.rh.puestosautorizados;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.autorizaciones.AutorizacionesService;
import mx.gob.saludtlax.rh.autorizaciones.EnumTiposAccionesAutorizacion;
import mx.gob.saludtlax.rh.autorizaciones.NuevaAutorizacionDTO;
import mx.gob.saludtlax.rh.empleados.datolaboral.ConfiguracionPresupuestal;
import mx.gob.saludtlax.rh.empleados.datolaboral.SolicitudNuevoPuestoDTO;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.persistencia.AspiranteEntity;
import mx.gob.saludtlax.rh.persistencia.AspiranteRepository;
import mx.gob.saludtlax.rh.persistencia.CandidatoVacanteEntity;
import mx.gob.saludtlax.rh.persistencia.CandidatoVacanteRepository;
import mx.gob.saludtlax.rh.persistencia.EmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.EmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.InventarioVacanteEntity;
import mx.gob.saludtlax.rh.persistencia.InventarioVacanteRepository;
import mx.gob.saludtlax.rh.persistencia.PostulacionVacanteEntity;
import mx.gob.saludtlax.rh.persistencia.PostulacionVacanteRepository;
import mx.gob.saludtlax.rh.persistencia.UsuarioEntity;
import mx.gob.saludtlax.rh.persistencia.UsuarioRepository;
import mx.gob.saludtlax.rh.util.FechaUtil;
//import mx.gob.saludtlax.rh.vacantes.consulta.DatoGeneralCandidatoDTO;
import mx.gob.saludtlax.rh.vacantes.seleccion.CandidatoVacanteDTO;
import mx.gob.saludtlax.rh.vacantes.seleccion.EnumTipoCandidato;
import mx.gob.saludtlax.rh.vacantes.seleccion.PostuladoVacanteDTO;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 25/07/2016 14:22:12
 */
public class PuestoAutorizadoService {

	@Inject
	private AutorizacionesService autorizacionesService;

	@Inject
	private AspiranteRepository aspiranteRepository;
	@Inject
	private CandidatoVacanteRepository candidatoVacanteRepository;

	@Inject
	private ConfiguracionPresupuestal datosLaborales;

	@Inject
	private EmpleadoRepository empleadoRepository;

	@Inject
	private InventarioVacanteRepository inventarioVacanteRepository;
	@Inject
	private UsuarioRepository usuarioRepository;
	@Inject
	private PostulacionVacanteRepository postulacionRepository;

	/**
	 * Crea la solicitud de un nuevo puesto, generando los datos labores del
	 * puesto.
	 */
	protected void solicitarCreacionPuesto(SolicitudNuevoPuestoDTO solicitudPuestoDTO) {
		Integer idDatosLaborales = datosLaborales.crearDatosLaborales(solicitudPuestoDTO);
		NuevaAutorizacionDTO dto = new NuevaAutorizacionDTO();
		if (solicitudPuestoDTO.getDatosLaborales().getTipoContratacion() == EnumTipoContratacion.INTERINATO) {
			InventarioVacanteEntity puestoDisponible = inventarioVacanteRepository
					.obtenerPorId(solicitudPuestoDTO.getIdPuestoDisponible());
			if (puestoDisponible == null) {
				throw new ValidacionException(
						"El puesto disponible para interinato con identificador "
								+ solicitudPuestoDTO.getIdPuestoDisponible() + " no existe.",
						ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);
			}

			String mensaje = " interinato para cubrir a personal de "
					+ puestoDisponible.getTipoContratacion().getTipoContratacion() + " "
					+ puestoDisponible.getMovimientoPermiso().getEmpleado().getNombreCompleto() + " por el motivo de "
					+ puestoDisponible.getMovimientoPermiso().getMovimiento().getMovimiento();
			dto.setMensajeNotificacion(mensaje);
			dto.setIdAccion(EnumTiposAccionesAutorizacion.APERTURA_INTERINATO);

		} else {
			dto.setIdAccion(EnumTiposAccionesAutorizacion.APERTURA_VACANTE);
			
			
		}

		dto.setIdEntidadContexto(idDatosLaborales);
		dto.setIdUsuarioLogeado(solicitudPuestoDTO.getIdUsuario());
		autorizacionesService.iniciarProcesoAprobacion(dto);

	}

	protected void postularCandidatoVacante(PostuladoVacanteDTO dto, Integer tipoCandidato) {

		String contexto = "Postular Vacante: ";

		if (tipoCandidato == EnumTipoCandidato.ASPIRANTE) {

			for (CandidatoVacanteDTO candidatoVacanteDTO : dto.getListaCandidatoVacante()) {
				AspiranteEntity aspiranteEntity = aspiranteRepository.obtenerPorId(candidatoVacanteDTO.getIdContexto());

				InventarioVacanteEntity inventarioVacanteEntity = inventarioVacanteRepository
						.obtenerPorId(dto.getIdInventarioVacante());

				if (candidatoVacanteRepository.existeIdContextoPostuladoVacante(candidatoVacanteDTO.getIdContexto(),
						dto.getIdInventarioVacante())) {

					throw new ReglaNegocioException(contexto + "El aspirante " + aspiranteEntity.getNombreCompleto()
							+ " ya se encuentra postulado para la vacante "
							+ inventarioVacanteEntity.getConfiguracion().getPuesto().getPuesto()
							+ "seleccione otro candidato", ReglaNegocioCodigoError.CANDIDATO_REPETIDO);
				}

				if (candidatoVacanteRepository.existeIdContexto(candidatoVacanteDTO.getIdContexto())) {

					throw new ReglaNegocioException(contexto + "El aspirante " + aspiranteEntity.getNombreCompleto()
							+ " ya se encuentra postulado...", ReglaNegocioCodigoError.CANDIDATO_REPETIDO);
				}

			}

			// Si no esta postulado registra el candidato

			Integer idPostuladoVacante = crearPostuladoVacante(dto.getUsuario(), dto.getIdInventarioVacante());

			for (CandidatoVacanteDTO candidatoVacanteDTO : dto.getListaCandidatoVacante()) {

				crearCandidatoVacante(tipoCandidato, candidatoVacanteDTO.getIdContexto(), idPostuladoVacante);

			}
		}

		if (tipoCandidato == EnumTipoCandidato.EMPLEADO) {

			for (CandidatoVacanteDTO candidatoVacanteDTO : dto.getListaCandidatoVacante()) {

				EmpleadoEntity empleadoEntity = empleadoRepository.obtenerPorId(candidatoVacanteDTO.getIdContexto());

				InventarioVacanteEntity inventarioVacanteEntity = inventarioVacanteRepository
						.obtenerPorId(dto.getIdInventarioVacante());

				if (candidatoVacanteRepository.existeIdContextoPostuladoVacante(candidatoVacanteDTO.getIdContexto(),
						dto.getIdInventarioVacante())) {

					throw new ReglaNegocioException(contexto + "El empleado " + empleadoEntity.getNombreCompleto()
							+ " ya se encuentra postulado para la vacante " + inventarioVacanteEntity.getFolioVacante()
							+ " con código " + inventarioVacanteEntity.getConfiguracion().getPuesto().getCodigo(),
							ReglaNegocioCodigoError.EMPLEADO_REPETIDO);
				}

				if (candidatoVacanteRepository.existeIdContexto(candidatoVacanteDTO.getIdContexto())) {

					throw new ReglaNegocioException(contexto + "El empleado " + empleadoEntity.getNombreCompleto()
							+ " ya se encuentra postulado...", ReglaNegocioCodigoError.EMPLEADO_REPETIDO);
				}

			}

			// Si no esta postulado registra el candidato

			Integer idPostuladoVacante = crearPostuladoVacante(dto.getUsuario(), dto.getIdInventarioVacante());

			for (CandidatoVacanteDTO candidatoVacanteDTO : dto.getListaCandidatoVacante()) {

				crearCandidatoVacante(tipoCandidato, candidatoVacanteDTO.getIdContexto(), idPostuladoVacante);

			}
		}

	}

	protected Integer crearPostuladoVacante(String userName, Integer idInventarioVacante) {

		PostulacionVacanteEntity postuladoVacanteEntity = new PostulacionVacanteEntity();

		postuladoVacanteEntity.setFechaPostulacion(FechaUtil.fechaActual());
		postuladoVacanteEntity.setHoraPostulacion(FechaUtil.horaActual());

		UsuarioEntity usuario = usuarioRepository.obtenerUsuarioPorNombreUsuario(userName);
		postuladoVacanteEntity.setUsuario(usuario);

		InventarioVacanteEntity inventarioVacante = inventarioVacanteRepository.obtenerPorId(idInventarioVacante);
		postuladoVacanteEntity.setInventarioVacante(inventarioVacante);

		String siDisponible = "SI";

		postuladoVacanteEntity.setDisponible(siDisponible);

		postulacionRepository.crear(postuladoVacanteEntity);

		return postuladoVacanteEntity.getIdPostuladoVacante();

	}

	protected void crearCandidatoVacante(Integer tipoCandidato, Integer idContexto, Integer idPostuladoVacante) {

		CandidatoVacanteEntity candidatoVacanteEntity = new CandidatoVacanteEntity();

		candidatoVacanteEntity.setTipoCandidato(tipoCandidato);
		candidatoVacanteEntity.setIdContexto(idContexto);

		PostulacionVacanteEntity postuladoVacanteEntity = postulacionRepository.obtenerPorId(idPostuladoVacante);
		candidatoVacanteEntity.setPostuladoVacante(postuladoVacanteEntity);

		boolean noSeleccionado = false;

		candidatoVacanteEntity.setSeleccionado(noSeleccionado);

		candidatoVacanteRepository.crear(candidatoVacanteEntity);

	}

	protected void solicitarCrearPuestosVoluntarios(int numeroPuestos, Integer idUsuario) {

	}

}
