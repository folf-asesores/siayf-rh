
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

public class TemporalConfEmpleadoRepository extends GenericRepository<TemporalConfEmpleadoEntity, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = -7033762686152193679L;

    public List<TemporalConfEmpleadoEntity> temporalesPorTipoContratacion(Integer tipoContratacion) {
        List<TemporalConfEmpleadoEntity> lista = em
                .createQuery("SELECT t FROM TemporalConfEmpleadoEntity AS t WHERE t.tipoContratacion =:tipoContratacion", TemporalConfEmpleadoEntity.class)
                .setParameter("tipoContratacion", tipoContratacion).getResultList();
        return lista;

    }

    public List<TemporalConfEmpleadoEntity> temporalesPorNombramientoSinClasificar(String nombramiento) {
        List<TemporalConfEmpleadoEntity> lista = em
                .createQuery("SELECT t FROM TemporalConfEmpleadoEntity AS t WHERE t.nombramiento =:nombramiento AND t.tipoContratacion IS NULL",
                        TemporalConfEmpleadoEntity.class)
                .setParameter("nombramiento", nombramiento).getResultList();
        return lista;

    }

}
