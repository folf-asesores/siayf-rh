
package mx.gob.saludtlax.rh.configuracion.conceptosnomina;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ConfiguracionConceptoPuestoEJB {

    @Inject
    private ConfiguracionConceptoPuestoService configuracionConceptoPuestoService;

    public void crear(ConfiguracionConceptoPuestoDTO dto) {
        configuracionConceptoPuestoService.crear(dto);
    }

    public void editar(ConfiguracionConceptoPuestoDTO dto) {
        configuracionConceptoPuestoService.editar(dto);
    }

    public void borrar(ConfiguracionConceptoPuestoDTO dto) {
        System.out.println(
                "borrarConfiguracion: " + dto.getIdConfiguracionConcepto());
        configuracionConceptoPuestoService
                .borrar(dto.getIdConfiguracionConcepto());
    }

    public List<ConfiguracionConceptoPuestoDTO> obtenerListaPorConcepto(
            Integer idConcepto) {
        return configuracionConceptoPuestoService
                .obtenerListaConfiguracionPorConcepto(idConcepto);
    }

    public List<ConfiguracionConceptoPuestoDTO> obtenerListaPorPuesto(
            Integer idPuesto) {
        return configuracionConceptoPuestoService
                .obtenerListaConfiguracionPorPuesto(idPuesto);
    }

    public ConfiguracionConceptoPuestoDTO obtenerConfiguracionPorPuestoConcepto(
            Integer idPuesto, Integer idConcepto) {
        return configuracionConceptoPuestoService
                .obtenerConfiguracionPorPuestoCocepto(idPuesto, idConcepto);
    }

}
