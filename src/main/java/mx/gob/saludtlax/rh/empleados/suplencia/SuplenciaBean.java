/*
 *
 */

package mx.gob.saludtlax.rh.empleados.suplencia;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import mx.gob.saludtlax.rh.empleados.datolaboral.DatoLaboralDTO;

/**
 * @author Leila Schiaffini Ehuan
 * @since 2016-10-18
 *
 */
@Stateless
public class SuplenciaBean implements Suplencia {

    @Inject
    private SuplenciaService suplenciaService;

    @Interceptors({ AltaSuplenteValidator.class })
    @Override
    public void habilitarSuplente(RegistroSuplenteDTO registroSuplenteDTO) {
        suplenciaService.crearSuplente(registroSuplenteDTO);

    }

    @Override
    public List<SuplenteDTO> consultarSuplentesPorCriterio(FiltroSuplenciaDTO filtro) {
        return suplenciaService.consultarSuplentesPorCriterio(filtro);
    }

    @Override
    public int obtenerNumeroQuincena() {
        return suplenciaService.obtenerNumeroQuincenaActual();
    }

    @Override
    public List<DetalleSuplenciaDTO> consultarDetallesSuplenteQuincena(ConsultaSuplenciaDTO consulta) {
        return suplenciaService.consultarDetallesSuplenteQuincena(consulta);
    }

    @Override
    public int obtenerUltimoDiaMes(int mes) {
        return suplenciaService.obtenerUltimoDiaMes(mes);
    }

    @Interceptors({ AltaSuplenciaValidator.class })
    @Override
    public Integer crearDetalleSuplencia(AltaSuplenciaDTO altaSuplenciaDTO) {
        return suplenciaService.registrarSuplencia(altaSuplenciaDTO);

    }

    @Override
    public List<DetalleSuplenciaDTO> consultarQuincenasSuplente(int numeroQuincena, int ejercicio, String estatus) {
        return suplenciaService.consultarQuincenasSuplente(numeroQuincena, ejercicio, estatus);
    }

    @Override
    public void actualizarEstatusDetalleQuincena(Integer idDetalleQuincena, String estatus) {
        suplenciaService.actualizarDetalleSuplencia(idDetalleQuincena, estatus);

    }

    @Override
    public String obtenerEstatusQuincenaSuplencia(int quincena, int ejercicio, Integer idSuplente) {
        return suplenciaService.obtenerEstatusQuincenaSuplencia(quincena, ejercicio, idSuplente);
    }

    @Override
    public void actualizarEstatusQuincena(Integer idQuincena, String estatus) {
        suplenciaService.actualizarEstatusQuincenaSuplencia(idQuincena, estatus);

    }

    @Override
    public void cerrarQuincenaSuplencia(CierreQuincenaDTO dto) {
        suplenciaService.cerrarQuincenaSuplencia(dto);

    }

    @Override
    public DatoLaboralDTO obtenerFinanciamientos(Integer idQuincena) {
        return suplenciaService.obtenerFinanciamientosQuincena(idQuincena);
    }

    @Override
    public Integer obtenerIdQuincenaSuplente(int quincena, int ejercicio, Integer idSuplente) {
        return suplenciaService.obtenerIdQuincenaSuplente(quincena, ejercicio, idSuplente);
    }

    @Override
    public List<InfoSuplenciaDTO> consultarSuplencias(Integer idSuplente) {
        return suplenciaService.consultarDiasSuplidos(idSuplente);
    }

    @Override
    public void descuentoSuplencia(DescuentoSuplenciaDTO descuento) {
        suplenciaService.descuentoSuplencia(descuento);

    }

    @Override
    public List<SuplenciasQuincenaDTO> consultarSuplenciasQuincena(int numeroQuincena, int ejercicioFiscal) {

        return null;
    }

    @Override
    public boolean sePagaDoble(Integer idJornadaSuplencia) {
        return suplenciaService.sePagaDoble(idJornadaSuplencia);
    }

    @Override
    public void editarSuplencia(EdicionSuplenciaDTO edicion) {
        suplenciaService.editarSuplencia(edicion);

    }

    @Override
    public void eliminarSuplencia(Integer idDetalleSuplencia) {
        suplenciaService.eliminarSuplencia(idDetalleSuplencia);

    }

    @Override
    public void activarQuincenaSuplencia(int numeroQuincena, int ejercicioFiscal, Integer idUsuario) {
        suplenciaService.activarQuincenaSuplencia(numeroQuincena, ejercicioFiscal, idUsuario);

    }

    @Override
    public QuincenaActivaDTO obtenerQuincenaActiva() {
        return suplenciaService.obtenerQuincenaActiva();
    }

    @Override
    public List<DetalleSuplenciaDTO> consultarSuplenciasPendientes(Integer idSuplente, Integer idQuincena) {
        return suplenciaService.consultarSuplenciasPendientes(idSuplente, idQuincena);
    }

    @Override
    public void agregarSuplenciaPendiente(Integer idQuincena, Integer idDetalleSuplencia) {
        suplenciaService.agregarSuplenciaPendiente(idQuincena, idDetalleSuplencia);

    }

    @Override
    public void actualizarEstatusSuplente(Integer idSuplente, String estatus) {
        suplenciaService.actualizarEstatusSuplente(idSuplente, estatus);

    }

    @Override
    public SuplenteDTO obtenerSuplentePorId(Integer idSuplente) {
        return suplenciaService.obtenerSuplentePorId(idSuplente);
    }

    @Override
    public List<QuincenaSuplenteDTO> consultarQuincenasSuplente(ConsultaSuplenciaDTO consulta) {
        return suplenciaService.consultarQuincenasSuplente(consulta);
    }

    @Override
    public void regresarARevision(Integer idQuincena) {
        suplenciaService.regresarARevision(idQuincena);

    }

    @Interceptors({ MovimientoSuplenteValidator.class })
    @Override
    public void crearMovimientoSuplente(MovimientoSuplenteDTO movimiento) {
        suplenciaService.crearMovimiento(movimiento);

    }

    @Override
    public List<MovimientoSuplenteDTO> consultarMovimientosSuplente(FiltroMovimientoSuplenteDTO filtro) {
        return suplenciaService.consultarMovimientosSuplente(filtro);
    }

}
