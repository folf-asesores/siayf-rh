/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 10/08/2016 21:17:50
 */
public class SubFuenteFinanciamientoTempRepository
        extends GenericRepository<SubFuenteFinanciamientoTempEntity, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = -3671780028840888105L;

    public List<SubFuenteFinanciamientoTempEntity> consultarSubfuentesFinanciamientos(
            Integer idFuenteFinanciamiento) {
        List<SubFuenteFinanciamientoTempEntity> subfuentes_financiamientos_temp = em
                .createQuery(
                        "SELECT s FROM SubFuenteFinanciamientoTempEntity AS s WHERE s.idFuenteFinanciamiento =:idFuenteFinanciamiento",
                        SubFuenteFinanciamientoTempEntity.class)
                .setParameter("idFuenteFinanciamiento", idFuenteFinanciamiento)
                .getResultList();
        return subfuentes_financiamientos_temp;

    }

    public List<SubFuenteFinanciamientoTempEntity> consultarSubfuentes() {
        List<SubFuenteFinanciamientoTempEntity> subfuentes_financiamientos_temp = em
                .createQuery(
                        "SELECT s FROM SubFuenteFinanciamientoTempEntity AS s ",
                        SubFuenteFinanciamientoTempEntity.class)
                .getResultList();
        return subfuentes_financiamientos_temp;

    }
}
