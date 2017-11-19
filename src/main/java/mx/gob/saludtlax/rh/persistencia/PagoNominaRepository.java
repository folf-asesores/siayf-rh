
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

public class PagoNominaRepository
        extends GenericRepository<PagoNominaEntity, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = 5297286899142319610L;

    public List<PagoNominaEntity> obtenerPagosNomina(
            ProductoNominaEntity productoNomina) {
        List<PagoNominaEntity> pagoNominaList = em.createQuery(
                "FROM PagoNominaEntity AS pn WHERE pn.productoNomina = :productoNomina ",
                PagoNominaEntity.class)
                .setParameter("productoNomina", productoNomina).getResultList();
        return pagoNominaList;
    }

}
