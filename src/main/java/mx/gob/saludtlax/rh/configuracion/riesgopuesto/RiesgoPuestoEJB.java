/*
 *
 */

package mx.gob.saludtlax.rh.configuracion.riesgopuesto;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author Eduardo Mex
 * @email Lic.Eduardo_Mex@hotmail.com
 * @version 1.0
 * @since 07/06/2016 19:01:33
 */
@Stateless
public class RiesgoPuestoEJB implements RiesgoPuesto, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 648837261243804947L;

    @Inject
    private RiesgoPuestoService riesgoPuestoService;

    @Override
    public void crearRiesgoPuesto(RiesgoPuestoDTO dto) {
        riesgoPuestoService.crearRiesgoPuesto(dto);
    }

    @Override
    public void actualizarRiesgoPuesto(RiesgoPuestoDTO dto) {
        riesgoPuestoService.actualizarRiesgoPuesto(dto);
    }

    @Override
    public void eliminarRiesgoPuesto(Integer idRiesgoPuesto) {
        riesgoPuestoService.eliminarRiesgoPuesto(idRiesgoPuesto);
    }

    @Override
    public List<RiesgoPuestoDTO> obtenerListaRiesgoPuesto() {
        return riesgoPuestoService.obtenerListaRiesgoPuesto();
    }

}
