
package mx.gob.saludtlax.rh.configuracion.dependencia;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class DependenciaEJB implements Dependencia {

    @Inject
    private DependenciaService dependenciaService;

    @Override
    public List<DependenciaDTO> obtenerDependenciaLista() {
        List<DependenciaDTO> dependenciaLista = dependenciaService.listaDependencia();
        return dependenciaLista;
    }

    @Override
    public DependenciaDTO obtenerDependencia(DependenciaDTO dependencia) {
        DependenciaDTO dto = dependenciaService.obtenerDependenciaPorId(dependencia.getIdDependencia());
        return dto;
    }

    @Override
    public List<String> consultarDescripcionDependenciasPorCriterio(String consulta) {
        return dependenciaService.consultarDescripcionDependenciasPorCriterio(consulta);
    }

    @Override
    public Integer consultarIdDependenciaPorDescripcion(String descripcion) {
        return dependenciaService.consultarIdDependenciaPorDescripcion(descripcion);
    }

    @Override
    public DependenciaDTO nuevoDependencia() {
        return dependenciaService.nuevoDependencia();
    }

    @Override
    public DependenciaDTO crearDependencia(DependenciaDTO dto) {
        return dependenciaService.crearDependencia(dto);
    }

    @Override
    public DependenciaDTO actualizarDependencia(DependenciaDTO dto) {
        return dependenciaService.actualizarDependencia(dto);
    }

    @Override
    public void eliminarDependencia(DependenciaDTO dto) {
        dependenciaService.eliminarDependencia(dto);
    }
}
