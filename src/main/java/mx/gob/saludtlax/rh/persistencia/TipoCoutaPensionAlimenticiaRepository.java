
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

public class TipoCoutaPensionAlimenticiaRepository extends GenericRepository<TipoCoutaPensionAlimenticiaEntity, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = -2108763826115397196L;

    public List<TipoCoutaPensionAlimenticiaEntity> obtenerListadoTipoCoutas() {

        List<TipoCoutaPensionAlimenticiaEntity> listado = em
                .createQuery("SELECT l FROM TipoCoutaPensionAlimenticiaEntity l", TipoCoutaPensionAlimenticiaEntity.class).getResultList();
        return listado;
    }

}
