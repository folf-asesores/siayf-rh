package mx.gob.saludtlax.rh.catalogos;

import java.util.List;

import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.EscolaridadDTO;

public interface Catalogo {

	public List<CatalogoDTO> listaPaises();

	public List<CatalogoDTO> listaEstados();

	public List<CatalogoDTO> consultarMunicipiosPorEstado(Integer idEstado);

	public List<CatalogoDTO> consultarAsantamientosPorMunicipios(
			Integer idMunicipio);

	public List<CatalogoDTO> listaPuestos();

	public List<CatalogoDTO> listaPuestosPorIdRama(Integer IdRama);

	public List<CatalogoDTO> listaDepartamentos();

	public List<CatalogoDTO> listaAreas();

	public List<CatalogoDTO> listaEscolaridades();

	public List<CatalogoDTO> listaComprobantesEstudios();

	public List<EscolaridadDTO> listaEscolaridad();

	public List<CatalogoDTO> plazasDisponibles(String idNombramiento,
			Integer idNivel);

	public List<CatalogoDTO> nombramientos();

	public List<CatalogoDTO> consultaMovimientosAutorizadosPorPadre(
			Integer idPadre);

	public List<CatalogoDTO> tercerosInstitucionales();

	public List<CatalogoDTO> obtenerListaRamaPuesto();

	/**
	 * Consulta la lista de documentos agrupados por tipo de clasificacion de
	 * expediente.
	 * 
	 */
	public List<CatalogoDTO> consultarDocumentosExpedientesClasificacion(
			String clasificacion);

	/**
	 * Agrupa la lista de documentos adjuntables dependiendo del contexto de la
	 * vista donde vayan a ser usados, ejemplo historial academico.
	 */
	public List<CatalogoDTO> consultatTiposDocumentosPorContexto(int contexto);

	public List<CatalogoDTO> listaNivelEmpleado();

	/*
	 * Obtiene la lista de tipos puestos registrados en la bd
	 */
	public List<CatalogoDTO> consultarTipoPuesto();

	/**
	 * Permite obtener la lista de estrategias que están en el almacen de datos.
	 * 
	 * @return una lista con las estrategias.
	 */
	List<CatalogoDTO> consultarEstrategias();

	/**
	 * Obtener la lista de Ramas que estan registrados en la bd
	 * 
	 * @return
	 */
	List<CatalogoDTO> consultarRamas();

	/***
	 * Obtiene la lista de tipos tabuladores registrados en la bd en formato de
	 * catalogo
	 * 
	 * @return
	 */
	List<CatalogoDTO> obtenerlistaTipoTabulador();

	/**
	 * Obtiene la lista de ejercicios fiscales registrados en la bd en formato
	 * catalogo
	 * 
	 * @return
	 */
	List<CatalogoDTO> obtenerListaEjercicioFiscal();

	public List<CatalogoDTO> listaDependencias();

	public List<CatalogoDTO> listaUnidadesResponsablesPorDependencia(
			Integer idDependencia);

	public List<CatalogoDTO> listaFuentesFinanciamientos();

	public List<CatalogoDTO> listaSubfuentesFinanciamientosPorFinanciamiento(
			Integer idFuenteFinanciamiento);

	public List<CatalogoDTO> listaSubfuentesFinanciamientos();

	public List<CatalogoDTO> listaTiposRecursos();

	public List<CatalogoDTO> consultarNombramientosPorContratacion(
			Integer tipoContratacion);

	public List<CatalogoDTO> consultarCuentasBancariasActuales();

	public List<CatalogoDTO> consultarProyectos();

	public List<CatalogoDTO> consultarProgramas();

	List<CatalogoDTO> consultarPerfiles();

	public List<CatalogoDTO> obtenerListaProfesion();

	public List<CatalogoDTO> obtenerListaEspecialidad();

	public List<CatalogoDTO> obtenerListaAutorizaciones();

	public List<CatalogoDTO> consultarListaDetallesPrograma(Integer idPrograma);

	public List<CatalogoDTO> consultarTiposJornadas();

	public List<CatalogoDTO> consultarAdscripciones();

	public List<CatalogoDTO> consultarSubadscripciones();

	public List<CatalogoDTO> consultarServicios();

	public List<CatalogoDTO> consultarFunciones();

	public List<CatalogoDTO> listaTiposJornadas();

	public List<CatalogoDTO> listaBancos();

	public List<CatalogoDTO> obtenerListaProcesoJuridico();

	public List<CatalogoDTO> obtenerTipoNombramiento();

	public List<CatalogoDTO> consultarUnidadesResponsables();

	public List<CatalogoDTO> obtenerListaSubClasificacionTabulador();

	public List<CatalogoDTO> obtenerListaCausaBajaIssste();

	public List<CatalogoDTO> obtenerListaTipoNombramiento();

	public List<CatalogoDTO> consultarOperacionesSistema();

	public List<CatalogoDTO> obtenerListaNivelSalarial();

	public List<CatalogoDTO> consultarClues();

	public List<CatalogoDTO> consultarTiposSuplencias();

	public List<CatalogoDTO> consultarTabuladorSuplencias();

	public List<CatalogoDTO> consultarTiposContratacion();

	public List<CatalogoDTO> consultarCentrosResponsabilidades();
	
	public List<CatalogoDTO> consultarTiposJornadasSuplencias(); 
	
	public List<CatalogoDTO> obtenerListaTipoMovimientoEmpleado();
	
	public List<CatalogoDTO> consultarRiesgos();
	
	public List<CatalogoDTO> consultarMetodosPago();

	public CatalogoDTO obtenerAdscripcionPorId(Integer idAdscripcion);
}