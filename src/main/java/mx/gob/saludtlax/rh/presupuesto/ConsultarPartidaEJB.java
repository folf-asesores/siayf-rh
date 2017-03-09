package mx.gob.saludtlax.rh.presupuesto;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.configuracion.nombramiento.TipoNombramientoDTO;
import mx.gob.saludtlax.rh.configuracion.nombramiento.TipoNombramientoService;
import mx.gob.saludtlax.rh.configuracion.unidadresponsable.UnidadResponsableDTO;
import mx.gob.saludtlax.rh.configuracion.unidadresponsable.UnidadResponsableService;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;

@Stateless
public class ConsultarPartidaEJB {
    @Inject
    private TipoNombramientoService tipoNombramientoService;
    @Inject
    private UnidadResponsableService unidadResponsableService;
    @Inject
    private ConsultarPartidaService consultarPartidaService;

    public List<TipoNombramientoDTO> getListaTipoNombramiento() {
        return tipoNombramientoService.listaNombramiento();
    }

    public List<UnidadResponsableDTO> getListaUnidadResponsable() {
        return unidadResponsableService.listaUnidadResponsable();
    }

    public List<ConsultarPartidaDTO> consultarPartidasPorRfc(String rfc) {
        // TODO Auto-generated method stub
        return consultarPartidaService.consultarPartidasPorRfc(rfc);
    }

    public List<ConsultarPartidaDTO> consultarPartidasPorUnidadResponsable(Integer unidadResponsable)
            throws ReglaNegocioException {
        // TODO Auto-generated method stub
        return consultarPartidaService.consultarPartidasPorUnidadResponsable(unidadResponsable);
    }

    public List<ConsultarPartidaDTO> consultarPartidasPorTipoNombramiento(Integer tipoNombramiento)
            throws ReglaNegocioException {
        // TODO Auto-generated method stub
        return consultarPartidaService.consultarPartidasPorTipoNombramiento(tipoNombramiento);
    }

}
