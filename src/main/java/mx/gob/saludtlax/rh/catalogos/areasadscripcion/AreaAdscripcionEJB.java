
package mx.gob.saludtlax.rh.catalogos.areasadscripcion;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import mx.gob.saludtlax.rh.persistencia.AreaAdscripcionEntity;

@Stateless
public class AreaAdscripcionEJB implements AreaAdscripcion {

    @Inject
    private AreaAdscripcionService areaAdscripcionService;

    @Interceptors({ AreaAdscripcionValidator.class })
    @Override
    public void crearAreaAdscripcion(AreaAdscripcionDTO areaAdscripcionDTO) {
        areaAdscripcionService.crearAreaAdscripcion(areaAdscripcionDTO);
    }

    @Override
    public AreaAdscripcionDTO obtenerPorId(int idAreaAdscripcion) {
        return areaAdscripcionService.obtenerPorId(idAreaAdscripcion);
    }

    @Override
    public List<InfoAreaAdscripcionDTO> consultarAreasAdscripcion(
            TipoFiltro filtro, Integer idFiltro) {

        return areaAdscripcionService.consultarAreasAdscripcion(filtro,
                idFiltro);
    }

    public List<AreaAdscripcionEntity> verAreas() {
        return areaAdscripcionService.verAreas();
    }

    @Override
    public List<String> consultarAreaAdscripcionPorCriterio(String consulta) {
        return areaAdscripcionService
                .consultarAreaAdscripcionPorCriterio(consulta);
    }

    @Override
    public Integer consultarIdAreaAdscripcionPorDescripcion(
            String descripcion) {
        return areaAdscripcionService
                .consultarIdAreaAdscripcionPorDescripcion(descripcion);
    }

}
