/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.util.List;

import mx.gob.saludtlax.rh.empleados.administracion.EnumTipoContratoEmpleado;

/**
 * @author Leila Schiaffini Ehuan
 * @since 05/09/2016 12:58:04
 *
 */
public class ContratoEmpleadoRepository extends GenericRepository<ContratoEmpleadoEntity, Integer> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6109861998964867250L;

    public List<ContratoEmpleadoEntity> obtenerlistaContrato() {

        return em.createQuery("SELECT c FROM ContratoEmpleadoEntity AS c WHERE c.tipoContrato =:tipoContrato", ContratoEmpleadoEntity.class)
                .setParameter("tipoContrato", EnumTipoContratoEmpleado.ESTATAL).getResultList();

    }

    public List<ContratoEmpleadoEntity> obtenerlistaContratoPorTipo(Integer tipoContrato) {
        try {
            String query = "SELECT c FROM ContratoEmpleadoEntity AS c WHERE c.tipoContrato =:tipoContrato";

            return em.createQuery(query, ContratoEmpleadoEntity.class).setParameter("tipoContrato", tipoContrato).getResultList();
        } catch (NullPointerException e) {
            return null;
        }
    }

}
