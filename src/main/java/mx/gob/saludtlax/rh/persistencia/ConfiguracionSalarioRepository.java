
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import mx.gob.saludtlax.rh.puestosautorizados.InfoSalarioDTO;

public class ConfiguracionSalarioRepository extends GenericRepository<ConfiguracionSalarioEntity, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = -1901041922833105323L;

    public List<InfoSalarioDTO> configuracionesSalariosPorTipoContratacion(String tipoContratacion) {
        List<InfoSalarioDTO> resultado = em.createQuery(
                "SELECT NEW mx.gob.saludtlax.rh.empleados.confpresupuesto.InfoSalarioDTO(c.configuracionPresupuestal.tipoContratacion, c.configuracionPresupuestal.empleado.rfc, c.configuracionPresupuestal.empleado.nombreCompleto, c.configuracionPresupuestal.puesto.codigo, c.configuracionPresupuestal.puesto.puesto, c.sueldoMensual, c.sueldoQuincenal) FROM ConfiguracionSalarioEntity AS c WHERE c.configuracionPresupuestal.tipoContratacion =:tipoContratacion",
                InfoSalarioDTO.class).setParameter("tipoContratacion", tipoContratacion).getResultList();
        return resultado;

    }

}
