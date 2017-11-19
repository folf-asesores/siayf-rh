/*
 *
 */

package mx.gob.saludtlax.rh.empleados.suplencia;

import java.util.List;

import mx.gob.saludtlax.rh.empleados.datolaboral.DatoLaboralDTO;

/**
 * @author Leila Schiaffini Ehuan
 * @since 2016-10-18
 *
 */
public interface Suplencia {

    public void habilitarSuplente(RegistroSuplenteDTO registroSuplenteDTO);

    public List<SuplenteDTO> consultarSuplentesPorCriterio(
            FiltroSuplenciaDTO filtro);

    public int obtenerNumeroQuincena();

    public List<DetalleSuplenciaDTO> consultarDetallesSuplenteQuincena(
            ConsultaSuplenciaDTO consulta);

    public int obtenerUltimoDiaMes(int mes);

    public Integer crearDetalleSuplencia(AltaSuplenciaDTO altaSuplenciaDTO);

    public List<DetalleSuplenciaDTO> consultarQuincenasSuplente(
            int numeroQuincena, int ejercicio, String estatus);

    /**
     * Actualiza el estatus de la suplencia.
     *
     * @param dto
     */
    public void actualizarEstatusDetalleQuincena(Integer idDetalleQuincena,
            String estatus);

    public String obtenerEstatusQuincenaSuplencia(int quincena, int ejercicio,
            Integer idSuplente);

    public Integer obtenerIdQuincenaSuplente(int quincena, int ejercicio,
            Integer idSuplente);

    /**
     * Actualiza el estatus de la quincena de suplencia
     *
     * @param idQuincena
     * @param estatus
     */
    public void actualizarEstatusQuincena(Integer idQuincena, String estatus);

    public void cerrarQuincenaSuplencia(CierreQuincenaDTO dto);

    public DatoLaboralDTO obtenerFinanciamientos(Integer idQuincena);

    public List<InfoSuplenciaDTO> consultarSuplencias(Integer idSuplente);

    public void descuentoSuplencia(DescuentoSuplenciaDTO descuento);

    public List<SuplenciasQuincenaDTO> consultarSuplenciasQuincena(
            int numeroQuincena, int ejercicioFiscal);

    public boolean sePagaDoble(Integer idJornadaSuplencia);

    public void editarSuplencia(EdicionSuplenciaDTO edicion);

    public void eliminarSuplencia(Integer idDetalleSuplencia);

    public void activarQuincenaSuplencia(int numeroQuincena,
            int ejercicioFiscal, Integer idUsuario);

    public QuincenaActivaDTO obtenerQuincenaActiva();

    public List<DetalleSuplenciaDTO> consultarSuplenciasPendientes(
            Integer idSuplente, Integer idQuincena);

    public void agregarSuplenciaPendiente(Integer idQuincena,
            Integer idDetalleSuplencia);

    public void actualizarEstatusSuplente(Integer idSuplente, String estatus);

    /**
     * Obtener información detallada del suplente
     *
     * @param idSuplente
     */
    public SuplenteDTO obtenerSuplentePorId(Integer idSuplente);

    public List<QuincenaSuplenteDTO> consultarQuincenasSuplente(
            ConsultaSuplenciaDTO consulta);

    public void regresarARevision(Integer idQuincena);

    /**
     * Registra los movimientos del suplente
     */
    public void crearMovimientoSuplente(MovimientoSuplenteDTO movimiento);

    /**
     * Consultar las vacaciones e incapacidades de los suplentes.
     *
     * @param filtro
     *            parámetros de consulta
     */
    public List<MovimientoSuplenteDTO> consultarMovimientosSuplente(
            FiltroMovimientoSuplenteDTO filtro);
}
