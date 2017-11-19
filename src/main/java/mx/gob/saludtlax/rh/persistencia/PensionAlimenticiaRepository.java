
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

public class PensionAlimenticiaRepository extends GenericRepository<PensionAlimenticiaEntity, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = -927615717418070116L;

    public List<PensionAlimenticiaEntity> obtenerListadoPensionesPorIdEmpleado(int idEmpleado) {

        List<PensionAlimenticiaEntity> listado = em
                .createQuery("SELECT p FROM PensionAlimenticiaEntity p WHERE p.empleado.idEmpleado =:idEmpleado ", PensionAlimenticiaEntity.class)
                .setParameter("idEmpleado", idEmpleado).getResultList();
        return listado;
    }

    //Charritos ¬¬
    public List<PensionAlimenticiaEntity> obtenerPensionesActivasPorIdEmpleado(int idEmpleado) {
        try {
            EmpleadoEntity empleado = em.find(EmpleadoEntity.class, idEmpleado);
            List<PensionAlimenticiaEntity> listado = em
                    .createQuery("" + " SELECT p " + " FROM PensionAlimenticiaEntity p " + " WHERE " + " p.empleado =:empleado " + " AND " + " p.estatus = 1 ",
                            PensionAlimenticiaEntity.class)
                    .setParameter("empleado", empleado).getResultList();
            return listado;
        } catch (Exception e) {
            return null;
        }
    }

}
