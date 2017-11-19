
package mx.gob.saludtlax.rh.nomina.movimientosnomina;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

import mx.gob.saludtlax.rh.configuracion.conceptosnomina.ConceptoNominaFederalesDTO;
import mx.gob.saludtlax.rh.persistencia.ConceptoNominaFederalesRepository;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionTipoMovimientoEntity;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionTipoMovimientoNominaReporsitory;
import mx.gob.saludtlax.rh.persistencia.TiposMovimientosNominaEntity;
import mx.gob.saludtlax.rh.persistencia.TiposMovimientosNominaRepository;

public class ConfiguracionTipoMovimientoService {

    @Inject
    TiposMovimientosNominaRepository tipomovimientosNominaRepository;

    @Inject
    ConceptoNominaFederalesRepository conceptoNominaRepository;

    @Inject
    ConfiguracionTipoMovimientoNominaReporsitory configuracionMovimientoRepository;

    public void guardarConfiguracion(ConfiguracionTipoMovimientoDTO dto) {

        List<ConfiguracionTipoMovimientoEntity> configEntitys = configuracionMovimientoRepository
                .obenerConceptosPorTipoMovimiento(dto.getTipoMovimiento().getIdTimpoMovimiento());
        if (configEntitys != null) {
            for (ConfiguracionTipoMovimientoEntity conf : configEntitys) {
                configuracionMovimientoRepository.eliminarPorId(conf.getId());
            }
        }

        for (ConceptoNominaFederalesDTO concepto : dto.getListConceptoNomina()) {
            ConfiguracionTipoMovimientoEntity entity = new ConfiguracionTipoMovimientoEntity();
            entity.setTipoMovimiento(tipomovimientosNominaRepository.obtenerPorId(dto.getTipoMovimiento().getIdTimpoMovimiento()));
            entity.setConceptoNomina(conceptoNominaRepository.obtenerPorId(concepto.getIdConceptoNomina()));
            configuracionMovimientoRepository.crear(entity);
        }

    }

    public void editarConfiguracion(ConfiguracionTipoMovimientoDTO dto) {
        try {
            List<ConfiguracionTipoMovimientoEntity> configEntitys = configuracionMovimientoRepository
                    .obenerConceptosPorTipoMovimiento(dto.getTipoMovimiento().getIdTimpoMovimiento());

            for (ConfiguracionTipoMovimientoEntity conf : configEntitys) {
                conceptoNominaRepository.eliminarPorId(conf.getId());
            }

            for (ConceptoNominaFederalesDTO concepto : dto.getListConceptoNomina()) {
                ConfiguracionTipoMovimientoEntity entity = new ConfiguracionTipoMovimientoEntity();
                entity.setTipoMovimiento(tipomovimientosNominaRepository.obtenerPorId(dto.getTipoMovimiento().getIdTimpoMovimiento()));
                entity.setConceptoNomina(conceptoNominaRepository.obtenerPorId(concepto.getIdConceptoNomina()));
                configuracionMovimientoRepository.crear(entity);
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
    }

    public ConfiguracionTipoMovimientoDTO obtenerConfiguracionesPorTipoMovimiento(Integer idTipoMovimiento) {
        List<ConceptoNominaFederalesDTO> listaConcoptos = new ArrayList<>();
        List<ConfiguracionTipoMovimientoEntity> listConfMov = new ArrayList<>();
        TiposMovimientosNominaEntity tipoMovimientoEntity = tipomovimientosNominaRepository.obtenerPorId(idTipoMovimiento);
        listConfMov = configuracionMovimientoRepository.obenerConceptosPorTipoMovimiento(idTipoMovimiento);
        if (listaConcoptos != null) {
            for (ConfiguracionTipoMovimientoEntity c : listConfMov) {
                ConceptoNominaFederalesDTO concepto = new ConceptoNominaFederalesDTO();
                concepto = ConceptoNominaFactory.crearConceptoNominaFederalesDTO(c.getConceptoNomina(), concepto);
                listaConcoptos.add(concepto);
            }
        }

        ConfiguracionTipoMovimientoDTO configuracion = new ConfiguracionTipoMovimientoDTO();
        TipoMovimientoNominaDTO tipoMovDto = new TipoMovimientoNominaDTO();
        tipoMovDto.setClave(tipoMovimientoEntity.getClave());
        tipoMovDto.setDescripcion(tipoMovimientoEntity.getDescripcion());
        tipoMovDto.setIdTimpoMovimiento(tipoMovimientoEntity.getIdMovimientoNomina());

        configuracion.setIdConfiguracion(idTipoMovimiento);
        configuracion.setTipoMovimiento(tipoMovDto);
        configuracion.setListConceptoNomina(listaConcoptos);

        return configuracion;

    }
}
