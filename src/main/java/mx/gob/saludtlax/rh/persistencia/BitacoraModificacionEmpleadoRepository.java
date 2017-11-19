/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 07/03/2016-22:27:32
 */
public class BitacoraModificacionEmpleadoRepository extends GenericRepository<BitacoraModificacionEmpleadoEntity, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = -3561210789435750739L;

    public List<BitacoraModificacionEmpleadoEntity> consultarBitacorasModificacionesIdEmpleado(Integer idEmpleado) {

        return em.createQuery("SELECT b FROM BitacoraModificacionEmpleadoEntity  AS b " + "WHERE b.idEmpleado =:idEmpleado",
                BitacoraModificacionEmpleadoEntity.class).setParameter("idEmpleado", idEmpleado).getResultList();

    }

}
