
package mx.gob.saludtlax.rh.areas;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.acciones.AccionDTO;
import mx.gob.saludtlax.rh.acciones.AccionService;
import mx.gob.saludtlax.rh.modulos.ModuloDTO;
import mx.gob.saludtlax.rh.modulos.ModulosService;

@Stateless
public class AreaEJB implements Areas {

    @Inject
    private AreasService areasService;

    @Inject
    ModulosService modulosService;

    @Inject
    private AccionService accionServ;

    @Override
    public void crearArea(AreaDTO areaDTO) {
        areasService.crearArea(areaDTO);
    }

    @Override
    public void editarArea(AreaDTO areaDTO) {
        areasService.editarArea(areaDTO);
    }

    @Override
    public Boolean eliminarArea(Integer idArea) {
        List<ModuloDTO> listModulos = modulosService
                .listaModulosPorArea(idArea);
        List<AccionDTO> listaAcciones = accionServ
                .obtenerAccionesPorArea(idArea);
        Boolean res = true;
        if (listaAcciones.isEmpty() && listModulos.isEmpty()) {
            areasService.eliminar(idArea);
            res = true;
        } else {
            res = false;
        }
        return res;
    }

    @Override
    public List<AreaDTO> obtenerAreas() {
        return areasService.obtenerAreas();
    }

}
