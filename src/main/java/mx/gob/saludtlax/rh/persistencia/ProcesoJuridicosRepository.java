
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

public class ProcesoJuridicosRepository
        extends GenericRepository<ProcesoJuridicosEntity, Integer> {

    //	<<<<<Consultas>>>>>

    /**
    *
    */
    private static final long serialVersionUID = -453512292211183832L;

    public List<ProcesoJuridicosEntity> obternerListaProceso() {
        List<ProcesoJuridicosEntity> lista = em
                .createQuery("from ProcesoJuridicosEntity AS proceso",
                        ProcesoJuridicosEntity.class)
                .getResultList();
        return lista;
    }

}