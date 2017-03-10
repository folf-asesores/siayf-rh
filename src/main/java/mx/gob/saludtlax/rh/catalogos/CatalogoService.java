package mx.gob.saludtlax.rh.catalogos;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.EscolaridadDTO;
import mx.gob.saludtlax.rh.configuracion.tabulador.EnumTipoTabulador;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.persistencia.*;
import mx.gob.saludtlax.rh.puestosautorizados.EnumTipoContratacion;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 10/03/2016-13:55:29
 */
public class CatalogoService {

	@Inject
	private ServicioRepository servicioRepository;
	@Inject
	private AdscripcionRepository adscripcionRepository;
	@Inject
	private SubadscripcionRepository subadscripcionRepository;
	@Inject
	private ClasificacionNombramientoRepository clasificacionNombramientoRepository;
	@Inject
	private ConfiguracionExpedienteRepository configuracionExpedienteRepository;
	@Inject
	private ComprobanteEstudioRepository comprobanteEstudioRepository;
	@Inject
	private CuentasBancariasRepository cuentaBancariaRepository;
	@Inject
	private DepartamentoRepository departamentoRepository;
	@Inject
	private DependenciaTempRepository dependenciasRepository;
	@Inject
	private DocumentoAdjuntableRepository documentoAdjuntableRepositoy;
	@Inject
	private EjercicioFiscalRepository ejercicioFiscalRepository;
	@Inject
	private EscolaridadRepository escolaridadRepository;
	@Inject
	private EstrategiaRepository estrategiaRepository;
	@Inject
	private EstadoRepository estadoRepository;
	@Inject
	private EspecialidadRepository especialidadRepository;
	@Inject
	private FuncionRepository funcionRepository;

	@Inject
	private TipoMovimientoEmpleadoRepository movimientosAutorizadosRepository;
	@Inject
	private MunicipioRepository municipioRepository;
	@Inject
	private TiposNombramientosRepository nombramientoRepository;
	@Inject
	private NivelEmpleadoRepository nivelEmpleadoRepository;
	@Inject
	private PuestoGeneralRepository puestoRepository;
	@Inject
	private RamaPuestoRepository ramaPuestoRepository;
	@Inject
	private PaisRepository paisRepository;
	@Inject
	private PlazaRepository plazaRepository;
	@Inject
	private AsentamientoRepository poblacionRepository;
	@Inject
	private TerceroInstitucionalRepository terceroInstitucionalRepository;
	@Inject
	private RamaRepository ramaRepository;
	@Inject
	private SectorRepository sectorRepository;
	@Inject
	private CentroPagoRepository CentroPagoRepository;
	@Inject
	private ProyectoTempRepository proyectoRepository;

	@Inject
	private UnidadResponsableRepository unidadResponsableRepository;
	@Inject
	private FuenteFinanciamientoRepository fuenteFinanciamientoRepository;
	@Inject
	private SubfuenteFinanciamientoRepository subfuentefinanciamientoRepository;
	@Inject
	private TipoRecursoTempRepository tipoRecursoTempRepository;
	@Inject
	private ProgramaRepository programaRepository;
	@Inject
	private PerfilesVoluntariosRepository perfilesVoluntariosRepository;
	@Inject
	private ProfesionRepository profesionRepository;
	@Inject
	private OperacionSistemaRepository accionAutorizacionRepository;
	@Inject
	private DetalleProgramaRepository detalleProgramaRepository;
	@Inject
	private TiposDuracionJornadaRepository tiposJornadasRepository;
	@Inject
	private TipoPuestoRepository tipoPuestoRepository;
	@Inject
	private TipoTabuladorRepository tipoTabuladorRepository;
	@Inject
	private TiposDuracionJornadaRepository tipoJornadaRepository;
	@Inject
	private BancoSatRepository bancoSatRepository;
	@Inject
	private ProcesoJuridicosRepository procesosJuridicosRepository;
	@Inject
	private SubClasificacionTabuladorRepository subClasificacionTabuladorRepository;
	@Inject
	private CausaBajaIsssteRepository causaBajaIsssteRepository;
	@Inject
	private TiposNombramientosRepository tiposNombramientosRepository;
	@Inject
	private NivelSalarialRepository nivelSalarialRepository;
	@Inject
	private CluesRepository cluesRepository;
	@Inject
	private TipoSuplenciaRepository tiposSuplenciasRepository;
	@Inject
	private TabuladorRepository tabuladorRepository;
	@Inject
	private CentroResponsabilidadRepository centroResponsabilidadRepository;
	@Inject
	private TipoJornadaSuplenciaRepository tipoJornadaSuplenciaRepository;

	@Inject
	private TipoContratacionRepository tipoContratacionRepository;

	@Inject
	private RiesgoPuestoRepository riesgoPuestoRepository;

	@Inject
	private MetodoPagoRepository metodoPagoRepository;

	/**
	 * Lista de areas
	 */
	protected List<CatalogoDTO> listaAreas() {

		return null;
	}

	/**
	 * Lista del catalogo de paises.
	 */
	protected List<CatalogoDTO> listaPaises() {
		List<PaisEntity> paises = paisRepository.paises();
		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();
		if (!paises.isEmpty()) {
			for (PaisEntity entity : paises) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getIdPais());
				dto.setNombre(entity.getNombre());
				lista.add(dto);
			}
		}
		return lista;
	}

	/**
	 * Lista de municipios del catalogo
	 */
	protected List<CatalogoDTO> consultarMunicipiosPorEstado(Integer idEstado) {
		if (!ValidacionUtil.esNumeroPositivo(idEstado)) {
			throw new ValidacionException("consultarMunicipiosPorEstado: El identificador del estado es requerido.",
					ValidacionCodigoError.NUMERO_NEGATIVO);
		}
		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();
		List<MunicipiosEntity> municipios = municipioRepository.consultarMunicipiosPorEstado(idEstado);
		if (!municipios.isEmpty()) {
			for (MunicipiosEntity entity : municipios) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getIdMunicipio());
				dto.setNombre(entity.getNombre());
				lista.add(dto);
			}
		}
		return lista;
	}

	/**
	 * Lista de poblaciones del catalogo
	 */
	protected List<CatalogoDTO> consultarAsentamientos(Integer idMunicipio) {
		if (!ValidacionUtil.esNumeroPositivo(idMunicipio)) {
			throw new ValidacionException(
					"consultarAsentamientoPorMunicipio: El identificador del municipio es requerido.",
					ValidacionCodigoError.NUMERO_NEGATIVO);
		}

		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();
		List<AsentamientoEntity> asentamientos = poblacionRepository.consultarAsentamientos();
		if (!asentamientos.isEmpty()) {
			for (AsentamientoEntity entity : asentamientos) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getIdMunicipio());
				dto.setNombre(entity.getNombre());
				lista.add(dto);
			}
		}
		return lista;
	}

	/**
	 * Consulta las poblaciones que hay en un municipio
	 * 
	 * @param idMunicipio
	 */
	protected List<CatalogoDTO> consultarAsentamientosPorMunicipio(Integer idMunicipio) {

		if (!ValidacionUtil.esNumeroPositivo(idMunicipio)) {
			throw new ValidacionException(
					"consultarAsentamientoPorMunicipio: El identificador del municipio es requerido.",
					ValidacionCodigoError.NUMERO_NEGATIVO);
		}

		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();
		List<AsentamientoEntity> asentamientos = poblacionRepository.consultarAsentamientosPorIdMunicipio(idMunicipio);
		if (!asentamientos.isEmpty()) {
			for (AsentamientoEntity entity : asentamientos) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getIdAsentamiento());
				dto.setNombre(entity.getNombre() + " C.P.-" + entity.getCodigoPostal());
				lista.add(dto);
			}
		}
		return lista;
	}

	protected List<CatalogoDTO> listaEstados() {
		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();
		List<EstadoEntity> estados = estadoRepository.estados();
		if (!estados.isEmpty()) {
			for (EstadoEntity entity : estados) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getIdEstado());
				dto.setNombre(entity.getNombre());
				lista.add(dto);
			}
		}
		return lista;
	}

	/**
	 * Ontiene la lista de puestos registrados en la bd
	 * 
	 * @return
	 */
	protected List<CatalogoDTO> listaPuestos() {
		List<PuestoGeneralEntity> puestos = puestoRepository.obtenerListaPuestoGeneral();
		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();
		if (!puestos.isEmpty()) {
			for (PuestoGeneralEntity entity : puestos) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getIdPuestoGeneral());
				dto.setNombre(entity.getCodigo() + " - " + entity.getPuesto());
				lista.add(dto);
			}
		}
		return lista;
	}

	/**
	 * Ontiene la lista de puestos registrados en la bd
	 * 
	 * @return
	 */
	protected List<CatalogoDTO> listaPuestosPorIdRama(Integer idRamaPuesto) {
		List<PuestoGeneralEntity> puestos = puestoRepository.consultarPuestosPorRama(idRamaPuesto);

		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();
		if (!puestos.isEmpty()) {
			for (PuestoGeneralEntity entity : puestos) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getIdPuestoGeneral());
				dto.setNombre(entity.getPuesto());
				lista.add(dto);
			}
		}
		return lista;
	}

	protected List<CatalogoDTO> listaDepartamentos() {
		List<DepartamentoEntity> departamentos = departamentoRepository.departamentos();
		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();
		if (!departamentos.isEmpty()) {
			for (DepartamentoEntity entity : departamentos) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getIdDepartamento());
				dto.setNombre(entity.getDepartamentos());
				lista.add(dto);
			}
		}
		return lista;
	}

	/**
	 * Lista de Escolaridades
	 * 
	 * @return
	 */
	protected List<EscolaridadDTO> listaEscolaridad() {
		List<EscolaridadDTO> listaEscolaridadDTO = new ArrayList<>();

		List<EscolaridadEntity> listaEscolaridadEntity = escolaridadRepository.listaEscolaridades();

		if (!listaEscolaridadEntity.isEmpty()) {
			for (EscolaridadEntity escolaridadEntity : listaEscolaridadEntity) {
				EscolaridadDTO escolaridadDTO = new EscolaridadDTO();
				escolaridadDTO.setIdEscolaridad(escolaridadEntity.getIdEscolaridad());
				escolaridadDTO.setEscolaridad(escolaridadEntity.getEscolaridad());
				escolaridadDTO.setGrupoAcademico(escolaridadEntity.getGrupoAcademico());

				listaEscolaridadDTO.add(escolaridadDTO);
			}
		} else {
			listaEscolaridadDTO = new ArrayList<>();
		}
		return listaEscolaridadDTO;
	}

	protected List<CatalogoDTO> plazasDisponibles(String idNombramiento, Integer idNivel) {
		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();
		List<PlazaEntity> plazas = plazaRepository.plazasDisponiblesPorNombramientoNivel(idNombramiento, idNivel);
		if (!plazas.isEmpty()) {
			for (PlazaEntity entity : plazas) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getIdPlaza());
				String nombre = "CLAVE: " + entity.getClave() + " PLAZA: " + entity.getNombrePlaza()
						+ " SUELDO MENSUAL: " + entity.getImporteMensual();
				dto.setNombre(nombre);
				lista.add(dto);
			}
		}
		return lista;
	}

	protected List<CatalogoDTO> nombramientos() {
		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();
		List<TiposNombramientosEntity> nombramientos = nombramientoRepository.nombramientos();
		if (!nombramientos.isEmpty()) {
			for (TiposNombramientosEntity entity : nombramientos) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getIdTipoNombramiento());
				dto.setNombre(entity.getNombramiento());
				lista.add(dto);
			}
		}
		return lista;
	}

	protected List<CatalogoDTO> consultaMovimientosAutorizadosPorPadre(Integer idPadre) {
		List<TipoMovimientoEmpleadoEntity> movimientos = movimientosAutorizadosRepository
				.consultaMovimientosPorPadre(idPadre);
		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();
		if (!movimientos.isEmpty()) {
			for (TipoMovimientoEmpleadoEntity entity : movimientos) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getIdTipoMovimiento());
				dto.setNombre(entity.getMovimiento());
				lista.add(dto);
			}
		}
		return lista;
	}

	protected List<CatalogoDTO> terceroInstitucional() {
		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();
		List<TerceroInstitucionalEntity> terceros = terceroInstitucionalRepository.obtenerListaTerceroInstitucional();
		if (!terceros.isEmpty()) {
			for (TerceroInstitucionalEntity terceroInstitucionalEntity : terceros) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(terceroInstitucionalEntity.getIdTerceroInstitucional());
				dto.setNombre(terceroInstitucionalEntity.getContrapartidaIdentificadora() + " - "
						+ terceroInstitucionalEntity.getConceptoDeduccion() + " - "
						+ terceroInstitucionalEntity.getNumero());

				lista.add(dto);
			}
		}
		return lista;
	}

	/**
	 * Obtiene la lista rama puesto y lo convierte a lista catalogo
	 * 
	 * @return
	 */
	protected List<CatalogoDTO> obtenerListaRamaPuesto() {
		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();
		List<RamaPuestoEntity> listaRama = ramaPuestoRepository.obtenerListaRamaPuesto();
		if (!listaRama.isEmpty()) {
			for (RamaPuestoEntity ramaPuestoEntity : listaRama) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(ramaPuestoEntity.getIdRamaPuesto());
				dto.setNombre(ramaPuestoEntity.getNombreRamaPuesto());

				lista.add(dto);
			}
		}
		return lista;
	}

	protected List<CatalogoDTO> consultarTiposDocumentosExpedienteCapturistas(String clasificacion) {
		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();
		List<ConfiguracionExpedienteEntity> documentos = configuracionExpedienteRepository
				.consultaDocumentosPorClasificacionExpediente(clasificacion);

		if (!documentos.isEmpty()) {
			for (ConfiguracionExpedienteEntity entity : documentos) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getDocumentoAdjuntable().getIdDocumentoAdjuntable());
				dto.setNombre(entity.getDocumentoAdjuntable().getDescripcion());

				lista.add(dto);
			}
		}

		return lista;
	}

	protected List<CatalogoDTO> listaEscolaridades() {
		List<EscolaridadEntity> escolaridades = escolaridadRepository.listaEscolaridades();
		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();
		if (!escolaridades.isEmpty()) {
			for (EscolaridadEntity entity : escolaridades) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getIdEscolaridad());
				dto.setNombre(entity.getGrupoAcademico());
				lista.add(dto);
			}
		}

		return lista;
	}

	protected List<CatalogoDTO> listaComprobantesEstudios() {
		List<ComprobanteEstudioEntity> documentos = comprobanteEstudioRepository.consultarComprobantesEstudios();
		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();
		if (!documentos.isEmpty()) {
			for (ComprobanteEstudioEntity entity : documentos) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getIdComprobanteEstudio());
				dto.setNombre(entity.getEstatus());
				lista.add(dto);
			}
		}
		return lista;

	}

	protected List<CatalogoDTO> consultatTiposDocumentosPorContexto(int contexto) {
		List<DocumentoAdjuntableEntity> documentos = documentoAdjuntableRepositoy
				.consultarDocumentosPorContexto(contexto);
		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();
		if (!documentos.isEmpty()) {
			for (DocumentoAdjuntableEntity entity : documentos) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getIdDocumentoAdjuntable());
				dto.setNombre(entity.getDescripcion());
				lista.add(dto);
			}
		}
		return lista;

	}

	protected List<CatalogoDTO> listaNivelEmpleado() {
		List<NivelEmpleadoEntity> obtenerListaNivelEmpleado = nivelEmpleadoRepository.obtenerListaNivelEmpleado();
		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();
		if (!obtenerListaNivelEmpleado.isEmpty()) {
			for (NivelEmpleadoEntity entity : obtenerListaNivelEmpleado) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getIdNivelEmpleado());
				dto.setNombre(entity.getNivelEmpleado());
				lista.add(dto);
			}
		}
		return lista;
	}

	protected List<CatalogoDTO> listaTipoPuesto() {
		List<TipoPuestoEntity> obtenerListaTipoPuesto = tipoPuestoRepository.obtenerListaTipoTabulador();
		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();
		if (!obtenerListaTipoPuesto.isEmpty()) {
			for (TipoPuestoEntity entity : obtenerListaTipoPuesto) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getIdTipoPuesto());
				dto.setNombre(entity.getDescripcion());
				lista.add(dto);
			}
		}
		return lista;
	}

	/**
	 * Consultar sectores
	 * 
	 * @return
	 */
	protected List<CatalogoDTO> consultarSectores() {
		List<SectorEntity> sectores = sectorRepository.consultarSectores();
		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();
		if (!sectores.isEmpty()) {
			for (SectorEntity entity : sectores) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getIdSector());
				dto.setNombre(entity.getSector());
				lista.add(dto);
			}
		}
		return lista;
	}

	/**
	 * Consultar centros_pago
	 * 
	 * @return
	 */
	protected List<CatalogoDTO> consultarCentrosPago() {
		List<CentroPagoEntity> centros_pago = CentroPagoRepository.consultarCentrosPago();
		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();
		if (!centros_pago.isEmpty()) {
			for (CentroPagoEntity entity : centros_pago) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getIdCentroPago());
				dto.setNombre(entity.getCentro_pago());
				lista.add(dto);
			}
		}
		return lista;
	}

	protected List<CatalogoDTO> consultarEstrategias() {
		List<EstrategiaEntity> estrategias = estrategiaRepository.consultarEstrategias();
		List<CatalogoDTO> catalogoDTOs = new ArrayList<>();

		if (estrategias != null && !estrategias.isEmpty()) {
			for (EstrategiaEntity estrategia : estrategias) {
				CatalogoDTO dto = new CatalogoDTO();

				dto.setId(estrategia.getIdEstrategia());
				dto.setNombre(estrategia.getEstrategia());
				catalogoDTOs.add(dto);
			}
		}

		return catalogoDTOs;
	}

	/**
	 * Catalogo de la lista de ramas que existen en la bd
	 * 
	 * @return List<CatalogoDTO>
	 */
	protected List<CatalogoDTO> consultarRama() {
		List<RamaEntity> ramas = ramaRepository.obtenerListaRama();
		List<CatalogoDTO> catalogoDTOs = new ArrayList<>();

		if (ramas != null && !ramas.isEmpty()) {
			for (RamaEntity ramaEntity : ramas) {
				CatalogoDTO dto = new CatalogoDTO();

				dto.setId(ramaEntity.getIdRamaPuesto());
				dto.setNombre(ramaEntity.getNombreRamaPuesto());
				catalogoDTOs.add(dto);
			}
		}
		return catalogoDTOs;
	}

	/**
	 * Catalogo de la lista de tipos de tabuladores registrados en la bd
	 * 
	 * @return
	 */
	protected List<CatalogoDTO> obtenerlistaTipoTabulador() {

		List<TipoTabuladorEntity> tabuladores = tipoTabuladorRepository.obtenerListaTipoTabulador();

		List<CatalogoDTO> catalogoDTOs = new ArrayList<>();

		if (tabuladores != null && !tabuladores.isEmpty()) {
			for (TipoTabuladorEntity tipoTabuladorEntity : tabuladores) {
				CatalogoDTO dto = new CatalogoDTO();

				dto.setId(tipoTabuladorEntity.getIdTipoTabulador());
				dto.setNombre(tipoTabuladorEntity.getDescripcion());
				catalogoDTOs.add(dto);
			}
		}
		return catalogoDTOs;
	}

	/***
	 * Catalogo de la lista de ejercicios fiscales registrados de la bd
	 * 
	 * @return
	 */
	protected List<CatalogoDTO> obtenerListaEjercicioFiscal() {

		List<EjercicioFiscalEntity> ejercicios = ejercicioFiscalRepository.obtenerListaEjercicioFiscal();

		List<CatalogoDTO> catalogoDTOs = new ArrayList<>();

		if (ejercicios != null && !ejercicios.isEmpty()) {
			for (EjercicioFiscalEntity ejercicioFiscalEntity : ejercicios) {
				CatalogoDTO dto = new CatalogoDTO();

				dto.setId(ejercicioFiscalEntity.getIdEjercicioFiscal());
				dto.setNombre(ejercicioFiscalEntity.getEjercicioFiscal().toString());
				catalogoDTOs.add(dto);
			}
		}
		return catalogoDTOs;
	}

	/**
	 * Consultar proyectos
	 * 
	 * @param idUnidadResponsable
	 * @param dependencia
	 * @return
	 */
	protected List<CatalogoDTO> consultarProyectos() {
		List<ProyectoTempEntity> proyectos = proyectoRepository.consultarProyectos();
		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();
		if (!proyectos.isEmpty()) {
			for (ProyectoTempEntity entity : proyectos) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getIdProyecto());
				dto.setNombre(entity.getIdBase36() + "-" + entity.getDescripcion());
				lista.add(dto);
			}
		}
		return lista;
	}

	/**
	 * Consultar dependecias
	 * 
	 * @return
	 */
	protected List<CatalogoDTO> consultarDependencias() {
		List<DependenciaTempEntity> dependencias = dependenciasRepository.consultarDependencias();
		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();
		if (!dependencias.isEmpty()) {
			for (DependenciaTempEntity entity : dependencias) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getIdDependencia());
				dto.setNombre(entity.getIdBase36() + "-" + entity.getDescripcion());
				lista.add(dto);
			}
		}
		return lista;
	}

	/**
	 * Consultar unidades responsables
	 * 
	 * @return
	 */
	protected List<CatalogoDTO> consultarUnidadesRepondablesPorDependencia(Integer idDependencia) {
		List<UnidadResponsableEntity> unidades_responsables = unidadResponsableRepository
				.consultarUnidadResponsablePorDependencia(idDependencia);
		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();
		if (!unidades_responsables.isEmpty()) {
			for (UnidadResponsableEntity entity : unidades_responsables) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getIdUnidadResponsable());
				dto.setNombre(entity.getIdBase36() + "-" + entity.getDescripcion());
				lista.add(dto);
			}
		}
		return lista;
	}

	/**
	 * Consultar fuentes financiamiento
	 * 
	 * @return
	 */
	protected List<CatalogoDTO> consultarFuentesFinanciamiento() {
		List<FuenteFinanciamientoEntity> fuentes_financiamientos = fuenteFinanciamientoRepository
				.consultarFuenteFinanciamiento();
		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();
		if (!fuentes_financiamientos.isEmpty()) {
			for (FuenteFinanciamientoEntity entity : fuentes_financiamientos) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getIdFuenteFinanciamiento());
				dto.setNombre(entity.getDescripcion());
				lista.add(dto);
			}
		}
		return lista;
	}

	/**
	 * Consultar las Subfuentes financiamiento
	 * 
	 * @param idFuenteFinanciamiento
	 */
	protected List<CatalogoDTO> consultarSubfuentesFinanciamientosPorFinanciamiento(Integer idFuenteFinanciamiento) {
		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();
		List<SubFuenteFinanciamientoTempEntity> subfuentes_financiamientos_temp = subfuentefinanciamientoRepository
				.consultarSubfuentesFinanciamientos(idFuenteFinanciamiento);
		if (!subfuentes_financiamientos_temp.isEmpty()) {
			for (SubFuenteFinanciamientoTempEntity entity : subfuentes_financiamientos_temp) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getIdSubfuenteFinanciamiento());
				dto.setNombre(entity.getDescripcion());
				lista.add(dto);
			}
		}
		return lista;
	}

	/**
	 * Consultar las Subfuentes financiamiento
	 * 
	 * @param idFuenteFinanciamiento
	 */
	protected List<CatalogoDTO> consultarSubfuentesFinanciamientos(Integer idFuenteFinanciamiento) {
		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();
		List<SubFuenteFinanciamientoTempEntity> subfuentes_financiamientos_temp = subfuentefinanciamientoRepository
				.consultarSubfuentesFinanciamientos(idFuenteFinanciamiento);
		if (!subfuentes_financiamientos_temp.isEmpty()) {
			for (SubFuenteFinanciamientoTempEntity entity : subfuentes_financiamientos_temp) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getIdSubfuenteFinanciamiento());
				dto.setNombre(entity.getDescripcion());
				lista.add(dto);
			}
		}
		return lista;
	}

	/**
	 * Consultar las Subfuentes financiamiento
	 * 
	 * @param idFuenteFinanciamiento
	 */
	protected List<CatalogoDTO> consultarSubfuentes() {
		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();
		List<SubFuenteFinanciamientoTempEntity> subfuentes_financiamientos_temp = subfuentefinanciamientoRepository
				.consultarSubfuentes();
		if (!subfuentes_financiamientos_temp.isEmpty()) {
			for (SubFuenteFinanciamientoTempEntity entity : subfuentes_financiamientos_temp) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getIdSubfuenteFinanciamiento());
				dto.setNombre(entity.getDescripcion());
				lista.add(dto);
			}
		}
		return lista;
	}

	/**
	 * Consultar tipos recursos
	 * 
	 * @return
	 */
	protected List<CatalogoDTO> consultarTiposRecursos() {
		List<TipoRecursoTempEntity> tipos_recursos_temp = tipoRecursoTempRepository.consultarTipoRecurso();
		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();
		if (!tipos_recursos_temp.isEmpty()) {
			for (TipoRecursoTempEntity entity : tipos_recursos_temp) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getIdTipoRecurso());
				dto.setNombre(entity.getDescripcion());
				lista.add(dto);
			}
		}
		return lista;
	}

	protected List<CatalogoDTO> consultarTiposContratacion() {
		List<TipoContratacionEntity> listaTiposContratacion = new ArrayList<>();
		listaTiposContratacion = tipoContratacionRepository.consultarTodos();
		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();
		if (!listaTiposContratacion.isEmpty()) {
			for (TipoContratacionEntity entity : listaTiposContratacion) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getId());
				dto.setNombre(entity.getTipoContratacion());
				lista.add(dto);
			}
		}
		return lista;
	}

	protected List<CatalogoDTO> consultarNombramientosPorContratacion(Integer tipoContratacion) {

		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();
		if (tipoContratacion == EnumTipoContratacion.FORMALIZADOS) {
			CatalogoDTO f = new CatalogoDTO();
			f.setId(5);
			f.setNombre("FORMALIZADOS");
			lista.add(f);

			CatalogoDTO f2 = new CatalogoDTO();
			f2.setId(13);
			f2.setNombre("FORMALIZADOS FASE II");
			lista.add(f2);

			CatalogoDTO f3 = new CatalogoDTO();
			f3.setId(14);
			f3.setNombre("FORMALIZADOS FASE III");
			lista.add(f2);

		} else if (tipoContratacion == EnumTipoContratacion.REGULARIZADOS) {
			CatalogoDTO r = new CatalogoDTO();
			r.setId(4);
			r.setNombre("REGULARIZADO FEDERAL");
			lista.add(r);

			CatalogoDTO rs = new CatalogoDTO();
			rs.setId(4);
			rs.setNombre("REGULARIZADO SEGURO POPULAR");
			lista.add(rs);
		} else if (tipoContratacion == EnumTipoContratacion.BASE
				|| tipoContratacion == EnumTipoContratacion.HOMOLOGADOS) {

			CatalogoDTO bh = new CatalogoDTO();
			bh.setId(2);
			bh.setNombre("CONFIANZA");

			CatalogoDTO b = new CatalogoDTO();
			b.setId(1);
			b.setNombre("BASE");
			lista.add(b);
			lista.add(bh);
		}

		return lista;

	}

	protected List<CatalogoDTO> consultarCuentasBancariasActuales() {
		List<CuentasBancariasEntity> cuentas = cuentaBancariaRepository.cuentasBancariasEjercicioActual();
		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();
		if (!cuentas.isEmpty()) {
			for (CuentasBancariasEntity entity : cuentas) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getIdCuentaBancaria());
				dto.setNombre(entity.getDescripcion() + "-" + entity.getNumeroCuenta());
				lista.add(dto);
			}
		}
		return lista;

	}

	protected List<CatalogoDTO> consultarProgramas() {
		List<ProgramaEntity> programas = programaRepository.consultarProgramas();
		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();
		if (!programas.isEmpty()) {
			for (ProgramaEntity entity : programas) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getIdPrograma());
				dto.setNombre(entity.getPrograma());
				lista.add(dto);
			}
		}
		System.out.println("lista" + lista.size());
		return lista;

	}

	protected List<CatalogoDTO> consultarPerfilesVoluntarios() {
		List<PerfilesVoluntariosEntity> perfiles = perfilesVoluntariosRepository.consultarVoluntarios();
		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();
		if (!perfiles.isEmpty()) {
			for (PerfilesVoluntariosEntity entity : perfiles) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getIdVoluntario());
				dto.setNombre(entity.getPerfil());
				lista.add(dto);
			}
		}
		return lista;

	}

	protected List<CatalogoDTO> obtenerListaProfesion() {

		List<ProfesionEntity> listaProfesion = profesionRepository.obtenerListaProfesion();
		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();
		if (!listaProfesion.isEmpty()) {
			for (ProfesionEntity entity : listaProfesion) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getIdProfesion());
				dto.setNombre(entity.getProfesion().toUpperCase());
				lista.add(dto);
			}

			// Agregando un catalogo
			CatalogoDTO catalogoDTO = new CatalogoDTO();
			catalogoDTO.setId(listaProfesion.get(listaProfesion.size() - 1).getIdProfesion() + 1);
			catalogoDTO.setNombre("TODAS LAS PROFESIONES");
			lista.add(catalogoDTO);
		}
		return lista;
	}

	protected List<CatalogoDTO> obtenerListaEspecialidad() {

		List<EspecialidadEntity> listaEspecialidad = especialidadRepository.obtenerListaEspecialidad();
		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();
		if (!listaEspecialidad.isEmpty()) {
			for (EspecialidadEntity entity : listaEspecialidad) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getIdEspecialidad());
				dto.setNombre(entity.getEspecialidad().toUpperCase());
				lista.add(dto);
			}
			// Agregando un catalogo mas
			CatalogoDTO catalogoDTO = new CatalogoDTO();
			catalogoDTO.setId(listaEspecialidad.get(listaEspecialidad.size() - 1).getIdEspecialidad() + 1);
			catalogoDTO.setNombre("TODAS LAS ESPECIALIDADES");
			lista.add(catalogoDTO);
		}
		return lista;
	}

	protected List<CatalogoDTO> consultarOperacionesSistema() {
		List<OperacionSistemaEntity> autorizacion = accionAutorizacionRepository.consultarOperaciones();
		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();
		if (!autorizacion.isEmpty()) {
			for (OperacionSistemaEntity entity : autorizacion) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getIdOperacion());
				dto.setNombre(entity.getOperacion());
				lista.add(dto);
			}
		}
		return lista;
	}

	protected List<CatalogoDTO> consultarDetallesPrograma(Integer idPrograma) {
		List<DetalleProgramaEntity> autorizacion = detalleProgramaRepository.consultarDetallesPrograma(idPrograma);
		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();
		if (!autorizacion.isEmpty()) {
			for (DetalleProgramaEntity entity : autorizacion) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getId());
				dto.setNombre(entity.getClave() + "-" + entity.getDescripcion());
				lista.add(dto);
			}
		}
		return lista;
	}

	public List<CatalogoDTO> obtenerListaUsuarios() {

		return null;
	}

	protected List<CatalogoDTO> consultarTiposJornadas() {
		List<TiposDuracionJornadaEntity> listaJornadas = tiposJornadasRepository.consultarTiposJornadas();
		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();
		if (!listaJornadas.isEmpty()) {
			for (TiposDuracionJornadaEntity entity : listaJornadas) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getIdTipoJornada());
				dto.setNombre(entity.getJornada());
				lista.add(dto);
			}
		}
		return lista;

	}

	protected List<CatalogoDTO> consultarAdscripciones() {
		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();
		List<AdscripcionEntity> areas = adscripcionRepository.consultarAdscripciones();
		if (!areas.isEmpty()) {
			for (AdscripcionEntity entity : areas) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getIdAdscripcion());
				dto.setNombre(entity.getAdscripcion());
				lista.add(dto);
			}
		}
		return lista;
	}

	protected List<CatalogoDTO> consultarSubadscripciones() {
		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();
		List<SubadscripcionEntity> areas = subadscripcionRepository.consultarAreasAdscripcion();
		if (!areas.isEmpty()) {
			for (SubadscripcionEntity entity : areas) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getIdSubadscripcion());
				dto.setNombre(entity.getSubadscripcion());
				lista.add(dto);
			}
		}
		return lista;
	}

	protected List<CatalogoDTO> listaTiposJornadas() {
		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();
		List<TiposDuracionJornadaEntity> areas = tipoJornadaRepository.consultarTiposJornadas();
		if (!areas.isEmpty()) {
			for (TiposDuracionJornadaEntity entity : areas) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getIdTipoJornada());
				dto.setNombre(entity.getJornada());
				lista.add(dto);
			}
		}
		return lista;
	}

	protected List<CatalogoDTO> listaBancos() {
		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();
		List<BancoSatEntity> areas = bancoSatRepository.obtenerListaBanco();
		if (!areas.isEmpty()) {
			for (BancoSatEntity entity : areas) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getIdBanco());
				dto.setNombre(entity.getNombreCorto());
				lista.add(dto);
			}
		}
		return lista;
	}

	protected List<CatalogoDTO> obtenerListaProcesoJuridico() {
		List<ProcesoJuridicosEntity> proceso = procesosJuridicosRepository.obternerListaProceso();
		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();
		if (!proceso.isEmpty()) {
			for (ProcesoJuridicosEntity entity : proceso) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getIdProcesoJuridico());
				dto.setNombre(entity.getProceso());
				lista.add(dto);
			}
		}
		return lista;
	}

	protected List<CatalogoDTO> consultarServicios() {
		List<ServicioEntity> servicios = servicioRepository.consultarActividades();
		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();
		if (!servicios.isEmpty()) {
			for (ServicioEntity entity : servicios) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getIdServicio());
				dto.setNombre(entity.getServicio());
				lista.add(dto);
			}
		}
		return lista;
	}

	protected List<CatalogoDTO> consultarFunciones() {
		List<FuncionEntity> funciones = funcionRepository.consultarFunciones();
		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();
		if (!funciones.isEmpty()) {
			for (FuncionEntity entity : funciones) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getIdFuncion());
				dto.setNombre(entity.getFuncion());
				lista.add(dto);
			}
		}
		return lista;
	}

	protected List<CatalogoDTO> obtenerTipoNombramiento() {
		List<ClasificacionNombramientoEntity> clasificaciones = clasificacionNombramientoRepository
				.listaClasificacionNombramiento();
		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();
		if (!clasificaciones.isEmpty()) {
			for (ClasificacionNombramientoEntity entity : clasificaciones) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getIdClasificacionNombramiento());
				dto.setNombre(entity.getClasificacionNombramiento());
				lista.add(dto);
			}
		}
		return lista;
	}

	/**
	 * Consultar unidades responsables
	 * 
	 * @return
	 */
	protected List<CatalogoDTO> consultarUnidadesResponsables() {
		List<UnidadResponsableEntity> unidades_responsables = unidadResponsableRepository
				.consultarUnidadesResponsables();
		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();
		if (!unidades_responsables.isEmpty()) {
			for (UnidadResponsableEntity entity : unidades_responsables) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getIdUnidadResponsable());
				dto.setNombre(entity.getDescripcion());
				lista.add(dto);
			}
		}
		return lista;
	}

	protected List<CatalogoDTO> obtenerListaSubClasificacionTabulador() {
		List<SubclasificacionTabuladorEntity> listaSub = subClasificacionTabuladorRepository
				.obtenerListaSubClasificaionTabulador();

		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();
		if (!listaSub.isEmpty()) {
			for (SubclasificacionTabuladorEntity entity : listaSub) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getIdSubclasificacion());
				dto.setNombre(entity.getSubclasificacion());
				lista.add(dto);
			}
		}
		return lista;
	}

	protected List<CatalogoDTO> obtenerListaCausaBajaIssste() {

		List<CausaBajaIsssteEntity> listaCausa = causaBajaIsssteRepository.listaCausaBajaIssste();

		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();
		if (!listaCausa.isEmpty()) {
			for (CausaBajaIsssteEntity entity : listaCausa) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getIdCausaBaja());
				dto.setNombre(entity.getCausaBaja());
				lista.add(dto);
			}
		}
		return lista;

	}

	protected List<CatalogoDTO> obtenerListaTipoNombramiento() {

		List<TiposNombramientosEntity> listaTipo = tiposNombramientosRepository.nombramientos();

		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();
		if (!listaTipo.isEmpty()) {
			for (TiposNombramientosEntity entity : listaTipo) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getIdTipoNombramiento());
				dto.setNombre(entity.getDescripcion());
				lista.add(dto);
			}
		}
		return lista;

	}

	protected List<CatalogoDTO> obtenerListaNivelSalarial() {

		List<NivelSalarialEntity> listaNivelSalarial = nivelSalarialRepository.listaNivelSalarial();

		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();
		if (!listaNivelSalarial.isEmpty()) {
			for (NivelSalarialEntity entity : listaNivelSalarial) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getIdNivelSalarial());
				dto.setNombre(entity.getNivelSalarial());
				lista.add(dto);
			}
		}
		return lista;
	}

	protected List<CatalogoDTO> consultarClues() {
		List<CatalogoDTO> lista = new ArrayList<>();
		List<CluesEntity> consulta = cluesRepository.consultarTodos();
		if (!consulta.isEmpty()) {
			for (CluesEntity entity : consulta) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getIdClues());
				dto.setNombre("CLUE: " + entity.getClues() + " TIPOLOGIA: " + entity.getNombreTipologia()
						+ "-NOMBRRE UNIDAD: " + entity.getNombreUnidad());
				lista.add(dto);
			}
		}

		return lista;

	}

	protected List<CatalogoDTO> consultarTiposSuplencias() {
		List<CatalogoDTO> lista = new ArrayList<>();
		List<TipoSuplenciaEntity> consulta = tiposSuplenciasRepository.consultarTiposSuplencias();
		if (!consulta.isEmpty()) {
			for (TipoSuplenciaEntity entity : consulta) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getIdCausaSuplenca());
				dto.setNombre(entity.getCausaSuplencia());
				lista.add(dto);
			}
		}
		return lista;
	}

	protected List<CatalogoDTO> consultarTabuladorSuplencias() {
		List<CatalogoDTO> lista = new ArrayList<>();
		List<TabuladorEntity> consulta = tabuladorRepository
				.consultarTabuladoresPorTipo(EnumTipoTabulador.UNICO_PERSONAL_SUPLENTE);
		if (!consulta.isEmpty()) {
			for (TabuladorEntity entity : consulta) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getIdTabulador());
				dto.setNombre("$" + entity.getSueldoDiario());
				lista.add(dto);
			}
		}
		return lista;
	}

	protected List<CatalogoDTO> consultarCentrosResponsabilidad() {
		List<CatalogoDTO> lista = new ArrayList<>();
		List<CentroResponsabilidadEntity> consulta = centroResponsabilidadRepository.consultarTodos();
		if (!consulta.isEmpty()) {
			for (CentroResponsabilidadEntity entity : consulta) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getIdCentroResponsabilidad());
				dto.setNombre(entity.getClave() + "-" + entity.getDescripcion());
				lista.add(dto);
			}
		}
		return lista;
	}

	protected List<CatalogoDTO> consultarTiposJornadasSuplencias() {
		List<CatalogoDTO> lista = new ArrayList<>();
		List<TipoJornadaSuplenciaEntity> consulta = tipoJornadaSuplenciaRepository.consultarTodos();
		if (!consulta.isEmpty()) {
			for (TipoJornadaSuplenciaEntity entity : consulta) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getId());
				dto.setNombre(entity.getJornada());
				lista.add(dto);
			}
		}
		return lista;
	}

	protected List<CatalogoDTO> obtenerListaTipoMovimientoEmpleado() {
		List<CatalogoDTO> lista = new ArrayList<>();
		List<TipoMovimientoEmpleadoEntity> consulta = movimientosAutorizadosRepository.consultaMovimientos();
		if (!consulta.isEmpty()) {
			for (TipoMovimientoEmpleadoEntity entity : consulta) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(entity.getIdTipoMovimiento());
				dto.setNombre(entity.getMovimiento());
				lista.add(dto);
			}
		}
		return lista;
	}

	protected List<CatalogoDTO> consultarRiesgos() {
		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();
		List<RiesgoPuestoEntity> consulta = riesgoPuestoRepository.consultarTodos();
		if (!consulta.isEmpty()) {
			for (RiesgoPuestoEntity r : consulta) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(r.getIdRiesgoPuesto());
				dto.setNombre(r.getDescripcionRiesgoPuesto());
				lista.add(dto);
			}
		}

		return lista;
	}

	protected List<CatalogoDTO> consultarMetodosPago() {
		List<CatalogoDTO> lista = new ArrayList<CatalogoDTO>();

		List<MetodoPagoEntity> consulta = metodoPagoRepository.consultarTodos();
		if (!consulta.isEmpty()) {
			for (MetodoPagoEntity m : consulta) {
				CatalogoDTO dto = new CatalogoDTO();
				dto.setId(m.getIdMetodoPago());
				dto.setNombre(m.getDescripcion().toUpperCase());
				lista.add(dto);
			}
		}
		return lista;
	}
	
	protected CatalogoDTO obtenerAdscripcionPorId(Integer idAdscripcion) {
		AdscripcionEntity adscripcion = adscripcionRepository.obtenerPorId(idAdscripcion);

		CatalogoDTO dto = new CatalogoDTO();
		if (adscripcion != null) {
			dto.setId(adscripcion.getIdAdscripcion());
			dto.setNombre(adscripcion.getAdscripcion());
		}
		return dto;
	}
	
	
}