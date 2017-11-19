
package mx.gob.saludtlax.rh.nomina.calculoisr;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import mx.gob.saludtlax.rh.util.Configuracion;

public class CalculoIsrService {
    @PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
    private EntityManager entityManager;

    public IsrDTO calcularIsrEmpleado(ConfiguracionIsrDTO configuracionIsr) {
        IsrDTO isr = new IsrDTO();
        if (configuracionIsr == null) {
            return null;
        }
        if (configuracionIsr.getBaseGravable() == null || configuracionIsr.getBaseGravable().compareTo(BigDecimal.ZERO) < 1) {
            isr.setImpuestoRetener(BigDecimal.ZERO);
            isr.setIngresoGravable(BigDecimal.ZERO);
            isr.setSubsidiosEntregar(BigDecimal.ZERO);
            isr.setPercepcionEfectiva(BigDecimal.ZERO);
        } else {
            TarifaIsrDTO tarifaIsr = obtenerTarifaIsr(configuracionIsr);
            BigDecimal subsidio = obtenerSubsidio(configuracionIsr);

            BigDecimal diferencia = configuracionIsr.getBaseGravable().subtract(tarifaIsr.getLimiteInferior());
            BigDecimal tasaInteres = tarifaIsr.getPorcentajeAplicable().divide(new BigDecimal(100));
            BigDecimal impuestoMarginal = diferencia.multiply(tasaInteres);
            BigDecimal impuestoPrevio = impuestoMarginal.add(tarifaIsr.getCuotaFija());
            BigDecimal impuestoRetener = impuestoPrevio.subtract(subsidio).setScale(0, RoundingMode.HALF_DOWN);
            BigDecimal subsidioEntregar = impuestoPrevio.subtract(subsidio).setScale(0, RoundingMode.HALF_DOWN);

            impuestoRetener = (impuestoRetener.compareTo(BigDecimal.ZERO) < 0) ? BigDecimal.ZERO : impuestoRetener;
            subsidioEntregar = (subsidioEntregar.compareTo(BigDecimal.ZERO) < 0) ? subsidioEntregar : BigDecimal.ZERO;

            isr.setImpuestoRetener(impuestoRetener);
            isr.setIngresoGravable(configuracionIsr.getBaseGravable());
            isr.setSubsidiosEntregar(subsidioEntregar);
            isr.setPercepcionEfectiva(configuracionIsr.getBaseGravable().subtract(impuestoRetener).subtract(subsidioEntregar));
        }
        return isr;
    }

    public ConfiguracionIsrDTO obtenerConfiguracioIsr(Integer idEmpleado) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT sueldo AS baseGravable, " + " id_tipo_periodo AS idTipoPeriodo"
                + " FROM configuraciones_presupuestales_empleados " + " WHERE id_empleado = :idEmpleado").setParameter("idEmpleado", idEmpleado);
        query.setResultTransformer(Transformers.aliasToBean(ConfiguracionIsrDTO.class));
        @SuppressWarnings("unchecked")

        List<ConfiguracionIsrDTO> result = query.list();
        ConfiguracionIsrDTO configuracionIsrDTO = result.isEmpty() ? null : result.get(0);
        System.out.println("configuracionIsrDTO.getIdTabulador():: " + configuracionIsrDTO.getIdTabulador());
        if (configuracionIsrDTO.getBaseGravable() == null) {
            query = session.createSQLQuery("SELECT " + " sueldo_bruto_mensual " + " FROM tabuladores " + " WHERE id_tabulador = :idTabulador")
                    .setParameter("idTabulador", configuracionIsrDTO.getIdTabulador());
            BigDecimal sueldo = (BigDecimal) query.list().get(0);
            configuracionIsrDTO.setBaseGravable(sueldo);
        }

        return configuracionIsrDTO;
    }

    private BigDecimal obtenerSubsidio(ConfiguracionIsrDTO configuracionIsr) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session
                .createSQLQuery("SELECT " + " subsidio " + " FROM tablas_subsidios " + " WHERE " + " id_tipo_periodo = :idTipoPeriodo " + " AND "
                        + " ( :baseGravable BETWEEN " + "	limite_inferior AND limite_superior) ")
                .setParameter("idTipoPeriodo", configuracionIsr.getIdTipoPeriodo()).setParameter("baseGravable", configuracionIsr.getBaseGravable());
        @SuppressWarnings("unchecked")
        List<BigDecimal> result = query.list();
        return result.isEmpty() ? null : result.get(0);
    }

    private TarifaIsrDTO obtenerTarifaIsr(ConfiguracionIsrDTO baseGravable) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session
                .createSQLQuery("SELECT " + " limite_inferior AS limiteInferior, " + " porcentaje_aplicable AS porcentajeAplicable, "
                        + " cuota_fija AS cuotaFija " + " FROM tarifas_retenciones " + " WHERE " + " id_tipo_periodo = :idTipoPeriodo " + " AND "
                        + " ( :baseGravable BETWEEN " + "	limite_inferior AND limite_superior ) ")
                .setParameter("idTipoPeriodo", baseGravable.getIdTipoPeriodo()).setParameter("baseGravable", baseGravable.getBaseGravable());
        query.setResultTransformer(Transformers.aliasToBean(TarifaIsrDTO.class));
        @SuppressWarnings("unchecked")
        List<TarifaIsrDTO> result = query.list();
        return result.isEmpty() ? null : result.get(0);
    }
}