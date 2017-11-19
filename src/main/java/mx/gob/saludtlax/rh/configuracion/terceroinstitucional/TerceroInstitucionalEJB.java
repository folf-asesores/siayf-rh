/*
 * Copyright ® 2016
 */

package mx.gob.saludtlax.rh.configuracion.terceroinstitucional;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

/**
 * @author Eduardo Mex

 * @version 1.0
 * @since 24/05/2016 10:57:26
 */
@Stateless
public class TerceroInstitucionalEJB implements TerceroInstitucional, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -7803556688801325088L;

    @Inject
    private TerceroInstitucionalService terceroInstitucionalService;

    @Override
    @Interceptors({ TerceroInstitucionalValidator.class })
    public void crearTerceroInstitucional(TerceroInstitucionalDTO terceroInstitucional) {
        terceroInstitucionalService.crearTerceroInstitucional(terceroInstitucional);
    }

    @Override
    @Interceptors({ TerceroInstitucionalValidator.class })
    public void actualizarTerceroInstitucional(TerceroInstitucionalDTO terceroInstitucional) {
        terceroInstitucionalService.actualizarTerceroInstitucional(terceroInstitucional);
    }

    @Override
    public void eliminarTerceroInstitucional(Integer idTerceroInstitucional) {
        terceroInstitucionalService.eliminarTerceroInstitucional(idTerceroInstitucional);
    }

    @Override
    public List<TerceroInstitucionalDTO> obtenerListaTerceroInstitucional() {
        return terceroInstitucionalService.obtenerListaTerceroInstitucional();
    }

    @Override
    public TerceroInstitucionalDTO obtenerporClave(String clave, String partida) {

        return terceroInstitucionalService.obtenerPorClave(clave, partida);
    }

}
