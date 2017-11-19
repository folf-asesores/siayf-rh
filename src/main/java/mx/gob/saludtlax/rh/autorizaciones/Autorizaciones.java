/*
 *
 */

package mx.gob.saludtlax.rh.autorizaciones;

import java.util.List;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 10/08/2016 22:18:41
 */
public interface Autorizaciones {

    public void iniciarProcesoAutorizacion(NuevaAutorizacionDTO dto);

    public void autorizarProceso(AutorizacionDTO autorizacionDTO);

    public List<BuzonAutorizacionDTO> consultarAutorizacionesUsuarioEstatus(Integer idUsuario, boolean autorizado);

    public List<BuzonAutorizacionDTO> consultarAutorizacionesPorOperacionEstatus(Integer idUsuario, boolean autorizado, Integer idOperacion);

    public DetalleAutorizacionDTO obtenerDetalleAutorizacion(Integer idBuzon);

    public Integer obtenerIdEntidadContexto(Integer idBuzon);

}
