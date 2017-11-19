
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

public class PensionAlimenticiaNominaRepository
        extends GenericRepository<PensionAlimenticiaNominaEntity, Integer> {
    private static final long serialVersionUID = -2169262136384038338L;

    public List<PensionAlimenticiaNominaEntity> listaPensionAlimenticiaNominaPorIdNominaEmpleado(
            NominaEmpleadoEntity nominaEmpleadoEntity) {
        List<PensionAlimenticiaNominaEntity> listaPensionAlimenticiaNomina = em
                .createQuery(
                        "select c from PensionAlimenticiaNominaEntity as c WHERE c.nominaEmpleado =:nominaEmpleado ",
                        PensionAlimenticiaNominaEntity.class)
                .setParameter("nominaEmpleado", nominaEmpleadoEntity)
                .getResultList();
        return listaPensionAlimenticiaNomina;
    }
}