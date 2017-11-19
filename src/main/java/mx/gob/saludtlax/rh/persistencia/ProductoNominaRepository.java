
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

/**
 *
 * @author José Pablo
 *
 */

public class ProductoNominaRepository extends GenericRepository<ProductoNominaEntity, Integer> {

    private static final long serialVersionUID = -8944185401952484416L;

    private static final int STATUS_PRODUCTO_NOMINA_TIMBRAR = 7;

    public List<ProductoNominaEntity> listaConfiguracionNomina() {
        List<ProductoNominaEntity> configuracion_nomina = em.createQuery("select c from ConfiguracionNominaEntity as c", ProductoNominaEntity.class)
                .getResultList();
        return configuracion_nomina;
    }

    public List<ProductoNominaEntity> listadoProductosNominasPorTimbrar() {

        List<ProductoNominaEntity> listaProductoNominaEntity = em
                .createQuery("select n from ProductoNominaEntity as n where n.estatusProductoNomina.idEstatusConceptoNomina =:idEstatus",
                        ProductoNominaEntity.class)
                .setParameter("idEstatus", STATUS_PRODUCTO_NOMINA_TIMBRAR).getResultList();

        return listaProductoNominaEntity;
    }

}