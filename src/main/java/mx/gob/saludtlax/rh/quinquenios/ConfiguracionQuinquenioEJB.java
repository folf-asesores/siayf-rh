
package mx.gob.saludtlax.rh.quinquenios;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

import mx.gob.saludtlax.rh.persistencia.ConceptoNominaFederalesEntity;
import mx.gob.saludtlax.rh.persistencia.ConceptoNominaFederalesRepository;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionQuinquenioEntity;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionQuinquenioRepository;
import mx.gob.saludtlax.rh.persistencia.InventarioVacanteEntity;
import mx.gob.saludtlax.rh.persistencia.InventarioVacanteRepository;
import mx.gob.saludtlax.rh.vacantes.seleccion.InfoEmpleadoVacanteDTO;

@Stateless
public class ConfiguracionQuinquenioEJB implements ConfiguracionQuinquenioService {

    @Inject
    ConfiguracionQuinquenioRepository configuracionQuinquenioRepository;

    @Inject
    InventarioVacanteRepository inventarioVacante;

    @Inject
    ConceptoNominaFederalesRepository conceptosFederalesRepository;

    @Override
    public List<ConfiguracionQuinquenioDTO> obtenerConfiguraciones() {
        try {
            return configuracionQuinquenioRepository.obtenerConfiguracionesQuinquenio();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public ConfiguracionQuinquenioDTO obtenerConfiguracionPorEmpleado(Integer id) {
        try {

            List<ConfiguracionQuinquenioDTO> list = configuracionQuinquenioRepository.obtenerConfiguracionQuinqueniosPorEmpleado(id);
            if (!list.isEmpty()) {
                return list.get(0);
            }
            return null;
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<InfoEmpleadoVacanteDTO> buscarEmpleadosPorTipoNombramiento(Integer idTipoNombramiento) {

        try {
            List<InventarioVacanteEntity> listaInventariosEntity = new ArrayList<>();
            listaInventariosEntity = inventarioVacante.consultaEmpleadosPorTipoNombramiento(idTipoNombramiento);

            List<InfoEmpleadoVacanteDTO> listaInfo = new ArrayList<>();
            if (listaInventariosEntity != null) {
                for (InventarioVacanteEntity entity : listaInventariosEntity) {
                    InfoEmpleadoVacanteDTO dto = new InfoEmpleadoVacanteDTO();
                    dto.setFechaInicioLabores(entity.getEmpleadoActivo().getFechaIngreso());
                    dto.setIdConfiguracionPresupuestal(entity.getConfiguracion().getId());
                    dto.setIdInventarioVacante(entity.getIdVacante());
                    dto.setIdEmpleado(entity.getEmpleadoActivo().getIdEmpleado());
                    dto.setNombre(entity.getEmpleadoActivo().getNombreCompleto());
                    dto.setNumeroEmpleado(entity.getConfiguracion().getNumeroEmpleado());
                    dto.setRfc(entity.getEmpleadoActivo().getRfc());
                    dto.setIdNombramiento(entity.getNombramiento().getIdTipoNombramiento());
                    listaInfo.add(dto);
                }
            }

            return listaInfo;
        } catch (NoResultException e) {
            return null;
        }

    }

    @Override
    public void crearConfiguracion(ConfiguracionQuinquenioDTO dto) {
        ConfiguracionQuinquenioEntity entity = new ConfiguracionQuinquenioEntity();

        entity.setEstatus(dto.getEstatus());
        entity.setFecha_actualizacion(dto.getFecha_actualizacion());
        entity.setFecha_alta(dto.getFecha_alta());
        ConceptoNominaFederalesEntity concepto = conceptosFederalesRepository.obtenerConceptoPorClave(dto.getClave_concepto());
        entity.setId_concepto_nomina(concepto);
        entity.setId_empleado(dto.getId_empleado());
        entity.setIdnombramiento(dto.getIdnombramiento());
        entity.setRfc(dto.getRfc());
        entity.setIdConfiguracionPresupestal(dto.getIdConfiguracionP());
        configuracionQuinquenioRepository.crear(entity);
    }

    @Override
    public void actualizarConfiguracion(ConfiguracionQuinquenioDTO dto) {
        ConfiguracionQuinquenioEntity entity = configuracionQuinquenioRepository.obtenerPorId(dto.getId_configuracion_quinquenio());
        System.out.println("?? " + dto.getEstatus() + entity.getId_configuracion_quinquenio());
        entity.setEstatus(dto.getEstatus());
        entity.setFecha_actualizacion(dto.getFecha_actualizacion());
        entity.setFecha_alta(dto.getFecha_alta());

        ConceptoNominaFederalesEntity concepto = conceptosFederalesRepository.obtenerConceptoPorClave(dto.getClave_concepto());
        entity.setId_concepto_nomina(concepto);
        entity.setId_empleado(dto.getId_empleado());
        entity.setIdnombramiento(dto.getIdnombramiento());
        entity.setRfc(dto.getRfc());
        entity.setIdConfiguracionPresupestal(dto.getIdConfiguracionP());
        configuracionQuinquenioRepository.actualizar(entity);
    }

    @Override
    public ConfiguracionQuinquenioDTO obtenerConfiguracionPorConfiguracionPresup(Integer idConfiguracion) {

        return configuracionQuinquenioRepository.obtenerQuinquenioPorConfiguracionPresupuestal(idConfiguracion);
    }

}
