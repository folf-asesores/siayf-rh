
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import mx.gob.saludtlax.rh.util.FechaUtil;

public class CuentasBancariasRepository extends GenericRepository<CuentasBancariasEntity, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = -7151465372266863388L;

    public List<CuentasBancariasEntity> cuentasBancariasEjercicioActual() {

        List<CuentasBancariasEntity> cuentas = em
                .createQuery("SELECT c FROM CuentasBancariasEntity AS c WHERE c.ejercicioFiscal =:ejercicioActual", CuentasBancariasEntity.class)
                .setParameter("ejercicioActual", FechaUtil.ejercicioActual()).getResultList();
        return cuentas;

    }

}
