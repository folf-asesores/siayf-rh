/*
 *
 */

package mx.gob.saludtlax.rh.catalogos;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.EscolaridadDTO;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 10/03/2016-13:54:43
 */
@Stateless
public class CatalogoEJB implements Catalogo {

    @Inject
    private CatalogoService catalogosService;

    /*
     * (non-Javadoc)
     *
     * @see mx.gob.saludtlax.rh.api.catalogos.Catalogo#listaPaises()
     */
    @Override
    public List<CatalogoDTO> listaPaises() {
        return catalogosService.listaPaises();
    }

    /*
     * (non-Javadoc)
     *
     * @see mx.gob.saludtlax.rh.api.catalogos.Catalogo#listaEstados()
     */
    @Override
    public List<CatalogoDTO> listaEstados() {
        return catalogosService.listaEstados();
    }

    /*
     * (non-Javadoc)
     *
     * @see mx.gob.saludtlax.rh.api.catalogos.Catalogo#listaMunicipios()
     */
    @Override
    public List<CatalogoDTO> consultarMunicipiosPorEstado(Integer idEstado) {
        return catalogosService.consultarMunicipiosPorEstado(idEstado);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * mx.gob.saludtlax.rh.api.catalogos.Catalogo#listaPoblacionesPorMunicipio
     * (java.lang.Integer)
     */
    @Override
    public List<CatalogoDTO> consultarAsantamientosPorMunicipios(
            Integer idMunicipio) {
        return catalogosService.consultarAsentamientosPorMunicipio(idMunicipio);
    }

    /*
     * (non-Javadoc)
     *
     * @see mx.gob.saludtlax.rh.api.catalogos.Catalogo#listaPuestos()
     */
    @Override
    public List<CatalogoDTO> listaPuestos() {
        return catalogosService.listaPuestos();
    }

    /*
     * (non-Javadoc)
     *
     * @see mx.gob.saludtlax.rh.api.catalogos.Catalogo#listaDepartamentos()
     */
    @Override
    public List<CatalogoDTO> listaDepartamentos() {
        return catalogosService.listaDepartamentos();
    }

    /*
     * (non-Javadoc)
     *
     * @see mx.gob.saludtlax.rh.api.catalogos.Catalogo#listaAreas()
     */
    @Override
    public List<CatalogoDTO> listaAreas() {
        return catalogosService.listaAreas();
    }

    /*
     * (non-Javadoc)
     *
     * @see mx.gob.saludtlax.rh.api.catalogos.Catalogo#listaEscolaridad()
     */
    @Override
    public List<EscolaridadDTO> listaEscolaridad() {
        return catalogosService.listaEscolaridad();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * mx.gob.saludtlax.rh.api.catalogos.Catalogo#plazasDisponibles(java.lang
     * .String, java.lang.Integer)
     */
    @Override
    public List<CatalogoDTO> plazasDisponibles(String idNombramiento,
            Integer idNivel) {
        return catalogosService.plazasDisponibles(idNombramiento, idNivel);
    }

    /*
     * (non-Javadoc)
     *
     * @see mx.gob.saludtlax.rh.api.catalogos.Catalogo#nombramientos()
     */
    @Override
    public List<CatalogoDTO> nombramientos() {
        return catalogosService.nombramientos();
    }

    /*
     * (non-Javadoc)
     *
     * @see mx.gob.saludtlax.rh.api.catalogos.Catalogo#
     * consultaMovimientosAutorizadosPorPadre(java.lang.Integer)
     */
    @Override
    public List<CatalogoDTO> consultaMovimientosAutorizadosPorPadre(
            Integer idPadre) {
        return catalogosService.consultaMovimientosAutorizadosPorPadre(idPadre);
    }

    /*
     * (non-Javadoc)
     *
     * @see mx.gob.saludtlax.rh.api.catalogos.Catalogo#tercerosInstitucionales()
     */
    @Override
    public List<CatalogoDTO> tercerosInstitucionales() {
        return catalogosService.terceroInstitucional();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * mx.gob.saludtlax.rh.api.catalogos.Catalogo#ramaPuesto(java.lang.Integer)
     */
    @Override
    public List<CatalogoDTO> obtenerListaRamaPuesto() {
        return catalogosService.obtenerListaRamaPuesto();
    }

    @Override
    public List<CatalogoDTO> listaEscolaridades() {
        return catalogosService.listaEscolaridades();
    }

    @Override
    public List<CatalogoDTO> listaComprobantesEstudios() {

        return catalogosService.listaComprobantesEstudios();
    }

    @Override
    public List<CatalogoDTO> consultatTiposDocumentosPorContexto(int contexto) {
        return catalogosService.consultatTiposDocumentosPorContexto(contexto);
    }

    @Override
    public List<CatalogoDTO> listaPuestosPorIdRama(Integer IdRama) {

        return catalogosService.listaPuestosPorIdRama(IdRama);
    }

    @Override
    public List<CatalogoDTO> listaNivelEmpleado() {
        return catalogosService.listaNivelEmpleado();
    }

    @Override
    public List<CatalogoDTO> consultarDocumentosExpedientesClasificacion(
            String clasificacion) {

        return catalogosService
                .consultarTiposDocumentosExpedienteCapturistas(clasificacion);
    }

    @Override
    public List<CatalogoDTO> consultarTipoPuesto() {
        return catalogosService.listaTipoPuesto();
    }

    @Override
    public List<CatalogoDTO> consultarEstrategias() {
        return catalogosService.consultarEstrategias();
    }

    @Override
    public List<CatalogoDTO> consultarRamas() {
        return catalogosService.consultarRama();
    }

    @Override
    public List<CatalogoDTO> obtenerlistaTipoTabulador() {
        return catalogosService.obtenerlistaTipoTabulador();
    }

    @Override
    public List<CatalogoDTO> obtenerListaEjercicioFiscal() {
        return catalogosService.obtenerListaEjercicioFiscal();
    }

    @Override
    public List<CatalogoDTO> listaDependencias() {
        return catalogosService.consultarDependencias();
    }

    @Override
    public List<CatalogoDTO> listaUnidadesResponsablesPorDependencia(
            Integer idDependencia) {
        return catalogosService
                .consultarUnidadesRepondablesPorDependencia(idDependencia);
    }

    @Override
    public List<CatalogoDTO> listaFuentesFinanciamientos() {

        return catalogosService.consultarFuentesFinanciamiento();
    }

    @Override
    public List<CatalogoDTO> listaSubfuentesFinanciamientosPorFinanciamiento(
            Integer idFuenteFinanciamiento) {

        return catalogosService
                .consultarSubfuentesFinanciamientosPorFinanciamiento(
                        idFuenteFinanciamiento);
    }

    @Override
    public List<CatalogoDTO> listaTiposRecursos() {

        return catalogosService.consultarTiposRecursos();
    }

    @Override
    public List<CatalogoDTO> consultarNombramientosPorContratacion(
            Integer tipoContratacion) {

        return catalogosService
                .consultarNombramientosPorContratacion(tipoContratacion);
    }

    @Override
    public List<CatalogoDTO> consultarCuentasBancariasActuales() {

        return catalogosService.consultarCuentasBancariasActuales();
    }

    @Override
    public List<CatalogoDTO> consultarProyectos() {

        return catalogosService.consultarProyectos();
    }

    @Override
    public List<CatalogoDTO> consultarProgramas() {
        return catalogosService.consultarProgramas();
    }

    @Override
    public List<CatalogoDTO> consultarPerfiles() {
        return catalogosService.consultarPerfilesVoluntarios();
    }

    @Override
    public List<CatalogoDTO> obtenerListaProfesion() {

        return catalogosService.obtenerListaProfesion();
    }

    @Override
    public List<CatalogoDTO> obtenerListaEspecialidad() {

        return catalogosService.obtenerListaEspecialidad();
    }

    @Override
    public List<CatalogoDTO> obtenerListaAutorizaciones() {
        return catalogosService.consultarOperacionesSistema();
    }

    @Override
    public List<CatalogoDTO> consultarListaDetallesPrograma(
            Integer idPrograma) {
        return catalogosService.consultarDetallesPrograma(idPrograma);
    }

    @Override
    public List<CatalogoDTO> consultarTiposJornadas() {

        return catalogosService.consultarTiposJornadas();
    }

    @Override
    public List<CatalogoDTO> consultarAdscripciones() {
        return catalogosService.consultarAdscripciones();
    }

    @Override
    public List<CatalogoDTO> listaTiposJornadas() {
        return catalogosService.listaTiposJornadas();
    }

    @Override
    public List<CatalogoDTO> listaBancos() {
        return catalogosService.listaBancos();
    }

    @Override
    public List<CatalogoDTO> obtenerListaProcesoJuridico() {
        return catalogosService.obtenerListaProcesoJuridico();
    }

    /*
     * (non-Javadoc)
     *
     * @see mx.gob.saludtlax.rh.catalogos.Catalogo#obtenerTipoNombramiento()
     */
    @Override
    public List<CatalogoDTO> obtenerTipoNombramiento() {

        return catalogosService.obtenerTipoNombramiento();
    }

    @Override
    public List<CatalogoDTO> consultarUnidadesResponsables() {

        return catalogosService.consultarUnidadesResponsables();
    }

    @Override
    public List<CatalogoDTO> obtenerListaSubClasificacionTabulador() {

        return catalogosService.obtenerListaSubClasificacionTabulador();
    }

    @Override
    public List<CatalogoDTO> obtenerListaCausaBajaIssste() {

        return catalogosService.obtenerListaCausaBajaIssste();
    }

    @Override
    public List<CatalogoDTO> obtenerListaTipoNombramiento() {

        return catalogosService.obtenerListaTipoNombramiento();
    }

    @Override
    public List<CatalogoDTO> consultarOperacionesSistema() {
        return catalogosService.consultarOperacionesSistema();
    }

    @Override
    public List<CatalogoDTO> obtenerListaNivelSalarial() {

        return catalogosService.obtenerListaNivelSalarial();
    }

    @Override
    public List<CatalogoDTO> listaSubfuentesFinanciamientos() {
        return catalogosService.consultarSubfuentes();
    }

    @Override
    public List<CatalogoDTO> consultarSubadscripciones() {

        return catalogosService.consultarSubadscripciones();
    }

    @Override
    public List<CatalogoDTO> consultarServicios() {

        return catalogosService.consultarServicios();
    }

    @Override
    public List<CatalogoDTO> consultarFunciones() {

        return catalogosService.consultarFunciones();
    }

    @Override
    public List<CatalogoDTO> consultarClues() {
        return catalogosService.consultarClues();
    }

    @Override
    public List<CatalogoDTO> consultarTiposSuplencias() {
        return catalogosService.consultarTiposSuplencias();
    }

    @Override
    public List<CatalogoDTO> consultarTabuladorSuplencias() {
        return catalogosService.consultarTabuladorSuplencias();
    }

    @Override
    public List<CatalogoDTO> consultarTiposContratacion() {

        return catalogosService.consultarTiposContratacion();
    }

    @Override
    public List<CatalogoDTO> consultarCentrosResponsabilidades() {
        return catalogosService.consultarCentrosResponsabilidad();
    }

    @Override
    public List<CatalogoDTO> consultarTiposJornadasSuplencias() {
        return catalogosService.consultarTiposJornadasSuplencias();
    }

    @Override
    public List<CatalogoDTO> obtenerListaTipoMovimientoEmpleado() {

        return catalogosService.obtenerListaTipoMovimientoEmpleado();
    }

    @Override
    public List<CatalogoDTO> consultarRiesgos() {
        return catalogosService.consultarRiesgos();
    }

    @Override
    public List<CatalogoDTO> consultarMetodosPago() {
        return catalogosService.consultarMetodosPago();
    }

    @Override
    public CatalogoDTO obtenerAdscripcionPorId(Integer idAdscripcion) {
        return catalogosService.obtenerAdscripcionPorId(idAdscripcion);
    }

}
