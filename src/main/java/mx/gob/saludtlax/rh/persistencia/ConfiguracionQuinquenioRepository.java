
package mx.gob.saludtlax.rh.persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.NoResultException;

import mx.gob.saludtlax.rh.quinquenios.ConfiguracionQuinquenioDTO;

public class ConfiguracionQuinquenioRepository extends GenericRepository<ConfiguracionQuinquenioEntity, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = 1832072893110781587L;

    @Inject
    EmpleadoRepository empleadoRepository;

    @Inject
    ConceptoNominaFederalesRepository conceptoRepository;

    public List<ConfiguracionQuinquenioDTO> obtenerConfiguracionQuinqueniosPorEmpleado(Integer idEmpleado) {

        List<ConfiguracionQuinquenioEntity> listQuin = new ArrayList<>();

        listQuin = em.createQuery("Select c from ConfiguracionQuinquenioEntity as c where c.id_empleado=:idempleado", ConfiguracionQuinquenioEntity.class)
                .setParameter("idempleado", idEmpleado).getResultList();

        List<ConfiguracionQuinquenioDTO> datos2 = new ArrayList<>();

        if (listQuin != null) {
            for (ConfiguracionQuinquenioEntity entity : listQuin) {
                String nombreCompleto = empleadoRepository.obtenerNombreEmpleadoId(entity.getId_empleado());

                datos2.add(new ConfiguracionQuinquenioDTO(entity.getId_configuracion_quinquenio(), entity.getId_empleado(), entity.getRfc(),
                        entity.getId_concepto_nomina() != null ? entity.getId_concepto_nomina().getClave() : null, entity.getFecha_alta(),
                        entity.getFecha_actualizacion(), entity.getEstatus(), entity.getIdnombramiento(), entity.getIdConfiguracionPresupestal(),
                        nombreCompleto));
                System.out.println("empleadoconfiguracionEncontrado" + datos2.size() + " " + datos2.get(0).getRfc());
            }
        }

        return datos2;
    }

    public List<ConfiguracionQuinquenioDTO> obtenerConfiguracionesQuinquenio() {

        List<ConfiguracionQuinquenioEntity> list = em.createQuery("Select c from ConfiguracionQuinquenioEntity as c", ConfiguracionQuinquenioEntity.class)
                .getResultList();

        List<ConfiguracionQuinquenioDTO> resultado = new ArrayList<>();
        if (!list.isEmpty()) {
            for (ConfiguracionQuinquenioEntity entity : list) {
                String nombreCompleto = empleadoRepository.obtenerNombreEmpleadoId(entity.getId_empleado());
                ConfiguracionQuinquenioDTO dto = new ConfiguracionQuinquenioDTO(entity.getId_configuracion_quinquenio(), entity.getId_empleado(),
                        entity.getRfc(), (entity.getId_concepto_nomina() == null ? null : entity.getId_concepto_nomina().getClave()), entity.getFecha_alta(),
                        entity.getFecha_actualizacion(), entity.getEstatus(), entity.getIdnombramiento(), entity.getIdConfiguracionPresupestal(),
                        nombreCompleto);
                resultado.add(dto);
            }
        }

        List<ConfiguracionQuinquenioDTO> datos2 = new ArrayList<>();

        if (resultado != null) {
            for (ConfiguracionQuinquenioDTO conf : resultado) {
                String nombreCompleto = empleadoRepository.obtenerNombreEmpleadoId(conf.getId_empleado());
                conf.setNombreEmpleado(nombreCompleto);

                datos2.add(conf);
            }
        }

        return datos2;
    }

    public ConfiguracionQuinquenioDTO obtenerQuinquenioPorConfiguracionPresupuestal(Integer idConfiguracion) {

        try {
            ConfiguracionQuinquenioEntity ent = em
                    .createQuery("Select c from ConfiguracionQuinquenioEntity as c where c.idConfiguracionPresupestal=:idConfiguracion",
                            ConfiguracionQuinquenioEntity.class)
                    .setParameter("idConfiguracion", idConfiguracion).getSingleResult();
            ConfiguracionQuinquenioDTO dto = null;
            if (ent != null) {
                String nombreCompleto = empleadoRepository.obtenerNombreEmpleadoId(ent.getId_empleado());
                dto = new ConfiguracionQuinquenioDTO(ent.getId_configuracion_quinquenio(), ent.getId_empleado(), ent.getRfc(),
                        (ent.getId_concepto_nomina() == null ? null : ent.getId_concepto_nomina().getClave()), ent.getFecha_alta(),
                        ent.getFecha_actualizacion(), ent.getEstatus(), ent.getIdnombramiento(), ent.getIdConfiguracionPresupestal(), nombreCompleto);

            }
            return dto;
        } catch (NoResultException e) {
            return null;
        }
    }

}
