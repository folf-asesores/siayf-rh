
package mx.gob.saludtlax.rh.persistencia;

import java.math.BigDecimal;

public class TablaSubsidioRepositroy extends GenericRepository<TablaSubsidioEntity, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = -7832596139600264225L;

    public TablaSubsidioEntity getTablaSubsidioByDatos(BigDecimal ingresoGravable, Integer peridiocidad, Integer anio) {
        return (TablaSubsidioEntity) super.em
                .createQuery("FROM TablaSubsidioEntity as ts WHERE " + "( :ingresoGravable >= ts.limiteInferior AND "
                        + ":ingresoGravable <= ts.limiteSuperior) AND" + "ts.periodicidad = :periodicidad AND" + "ts.ejercicoFiscal = :anio")
                .setParameter("ingresoGravable", ingresoGravable).setParameter("peridiocidad", peridiocidad).setParameter("anio", anio).getResultList().get(0);

    }

}
