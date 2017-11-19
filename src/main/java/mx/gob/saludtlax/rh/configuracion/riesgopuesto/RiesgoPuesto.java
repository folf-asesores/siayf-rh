
package mx.gob.saludtlax.rh.configuracion.riesgopuesto;

import java.util.List;

/**
 *
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 * 
 * @version 1.0
 * @since 07/06/2016 19:02:04
 */
public interface RiesgoPuesto {

    void crearRiesgoPuesto(RiesgoPuestoDTO dto);

    void actualizarRiesgoPuesto(RiesgoPuestoDTO dto);

    void eliminarRiesgoPuesto(Integer idRiesgoPuesto);

    List<RiesgoPuestoDTO> obtenerListaRiesgoPuesto();

}
