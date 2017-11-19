
package mx.gob.saludtlax.rh.perfiles;

import java.util.List;

import javax.inject.Inject;

public class ConfiguracionPerfilModuloEJB implements ConfiguracionPerfilModulo {

    @Inject
    private ConfiguracionPerfilModuloService service;

    @Override
    public void crearConfiguracionPerfilModulo(
            ConfiguracionPerfilModuloDTO configuracionPerfilModuloDTO) {
        service.crearConfiguracion(configuracionPerfilModuloDTO);
    }

    @Override
    public void eliminarConfiguracionPerfilModuloPorIdPerfil(Integer idPerfil) {
        service.eliminarConfiguracion(idPerfil);
    }

    @Override
    public List<ConfiguracionPerfilModuloDTO> listaConfiguracionPerfilModulo() {
        return service.obtenerRegistros();
    }

    @Override
    public List<ConfiguracionPerfilModuloDTO> listaConfiguracionPerfilModuloPorIdPerfil(
            Integer id) {
        return service.obtenerRegistrosPorIdPerfil(id);
    }

}
