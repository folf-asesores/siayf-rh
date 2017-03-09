package mx.gob.saludtlax.rh.configuracion.unidadresponsable;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UnidadResponsableEJB implements UnidadResponsable {

    @Inject private UnidadResponsableService unidadResponsableService;

    @Override
    public List<UnidadResponsableDTO> obtenerUnidadResponsableLista() {
        List<UnidadResponsableDTO> unidadResponsableLista = unidadResponsableService.listaUnidadResponsable();
        return unidadResponsableLista;
    }

    @Override
    public UnidadResponsableDTO obtenerUnidadResponsable(UnidadResponsableDTO unidadResponsable) {
        UnidadResponsableDTO dto = unidadResponsableService.obtenerUnidadResponsablePorId(unidadResponsable.getIdUnidadResponsable());
        return dto;
    }

    @Override
    public List<String> consultarDescripcionUnidadesResponsablesPorCriterio(String consulta) {
        return unidadResponsableService.consultarDescripcionUnidadesResponsablesPorCriterio(consulta);
    }

    @Override
    public Integer consultarIdUnidadResponsablePorDescripcion(String descripcion) {
        return unidadResponsableService.consultarIdUnidadResponsablePorDescripcion(descripcion);
    }

    @Override
    public UnidadResponsableDTO nuevoUnidadResponsable() {
        return unidadResponsableService.nuevoUnidadResponsable();
    }

    @Override
    public UnidadResponsableDTO crearUnidadResponsable(UnidadResponsableDTO dto) {
        return unidadResponsableService.crearUnidadResponsable(dto);
    }

    @Override
    public UnidadResponsableDTO actualizarUnidadResponsable(UnidadResponsableDTO dto) {
        return unidadResponsableService.actualizarUnidadResponsable(dto);
    }

    @Override
    public void  eliminarUnidadResponsable(UnidadResponsableDTO dto) {
        unidadResponsableService.eliminarUnidadResponsable(dto);
    }
}
