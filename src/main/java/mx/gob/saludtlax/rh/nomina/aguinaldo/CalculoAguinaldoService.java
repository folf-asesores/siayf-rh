
package mx.gob.saludtlax.rh.nomina.aguinaldo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.persistencia.SalarioMinimoEntity;
import mx.gob.saludtlax.rh.persistencia.SalarioMinimoRepository;
import mx.gob.saludtlax.rh.util.Configuracion;
import mx.gob.saludtlax.rh.util.FechaUtil;

@Stateless
public class CalculoAguinaldoService implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -429740667173799660L;

    @PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
    private EntityManager entityManager;

    @Inject
    private SalarioMinimoRepository salarioMinimoRepository;

    private final int TOTAL_DIAS_PAGAR_AGUINALDO = 365;

    public AguinaldoResult calcular(AguinaldoParams aguinaldoParams) {

        //		InventarioVacanteEntity inventarioVacanteEntity = inventarioVacanteRepository
        //				.obtenerPuestoPorIdEmpleado(aguinaldoParams.getIdEmpleado());
        //
        //		if (inventarioVacanteEntity == null) {
        //			throw new ReglaNegocioException("El empleado no tiene una vacante asignada",
        //					ReglaNegocioCodigoError.CONFIGURACION_NO_EXISTE);
        //		}
        //
        //		if (inventarioVacanteEntity.getConfiguracion().getSueldo() == null) {
        //			throw new ReglaNegocioException("La configuracion del empleado no cuenta con un salario asignado",
        //					ReglaNegocioCodigoError.CONFIGURACION_NO_EXISTE);
        //		}

        // Tenemos el salario mensual del empleado
        BigDecimal salarioMensual = aguinaldoParams.getBaseAguinaldo();
        // Ahora el salario diario del empleado
        BigDecimal salarioDario = salarioMensual.divide(new BigDecimal("30"), 4);
        // Ahora sabemos cualto le corresponde de aguinaldo completo
        BigDecimal aguinaldoCompleto = salarioDario.multiply(aguinaldoParams.getDiasPagar());
        // POr ultimo tenemos cuando le corresponde por dia trabajado de
        // aguinaldo.
        BigDecimal aguinaldoPorDia = aguinaldoCompleto.divide(new BigDecimal(TOTAL_DIAS_PAGAR_AGUINALDO), 4);

        BigDecimal aguinaldo = null;
        BigDecimal diasPagarAguinaldo = null;
        BigDecimal importeExcentoAguinaldo = calcularImporteExcento(aguinaldoParams.getDiasExentoPagar());

        // Cuando se trata de un finiquito solo se le paga la parte proprocional
        // a los dias trabajados en el momento que se realiza el finiquito
        if (aguinaldoParams.isCalculoFiniquito()) {
            if (aguinaldoParams.getFechaCalculo() == null) {
                throw new ReglaNegocioException("Debe enviar la fecha con la que realzara el calculo", ReglaNegocioCodigoError.CONFIGURACION_NO_EXISTE);
            }
            diasPagarAguinaldo = new BigDecimal(calcularDiasPagarFiniquito(aguinaldoParams.getIdEmpleado(), aguinaldoParams.getFechaCalculo()));
        } else {
            diasPagarAguinaldo = new BigDecimal(
                    calcularDiasPagarAnual(aguinaldoParams.getIdEmpleado(), FechaUtil.anioFecha(aguinaldoParams.getFechaCalculo())));
        }
        aguinaldo = aguinaldoPorDia.multiply(diasPagarAguinaldo);

        AguinaldoResult aguinaldoResult = new AguinaldoResult();
        aguinaldoResult.setDiasPagados(diasPagarAguinaldo.intValue());
        aguinaldoResult.setImporteAguinaldoCompleto(aguinaldoCompleto);
        aguinaldoResult.setImporteAguinaldoPorDia(aguinaldoPorDia);
        aguinaldoResult.setTotal(aguinaldo);
        aguinaldoResult.setGravado(aguinaldo);
        aguinaldoResult.setExcento(BigDecimal.ZERO);

        if (aguinaldo.compareTo(importeExcentoAguinaldo) > 0) {
            aguinaldoResult.setExcento(importeExcentoAguinaldo);
            aguinaldoResult.setGravado(aguinaldo.subtract(importeExcentoAguinaldo));
        } else {
            aguinaldoResult.setExcento(aguinaldo);
            aguinaldoResult.setGravado(BigDecimal.ZERO);
        }

        return aguinaldoResult;
    }

    // Calcula los dias que ha trabajado un empleado en el anyo actual
    private Integer calcularDiasPagarAnual(Integer idEmpleado, Integer anio) {
        BigInteger diasDescontar = (BigInteger) entityManager.createNativeQuery("CALL usp_dias_descontar_anyo (:idEmpleado, :anio);")
                .setParameter("idEmpleado", idEmpleado).setParameter("anio", anio).getSingleResult();
        Integer diasAguinaldo = TOTAL_DIAS_PAGAR_AGUINALDO - diasDescontar.intValue();
        return diasAguinaldo;
    }

    private Integer calcularDiasPagarFiniquito(Integer idEmpleado, Date fecha) {

        BigInteger diasTrabajados = (BigInteger) entityManager.createNativeQuery("CALL ups_total_dias_trabajados_aguinaldo (:fecha,:idEmpleado);")
                .setParameter("fecha", fecha).setParameter("idEmpleado", idEmpleado).getSingleResult();
        ;
        return diasTrabajados.intValue();

    }

    /**
     * Realiza el calculo importe excento del aguinaldo con respecto al ultimo
     * salario minimo registrado en el sistema
     *
     * @param
     *
     * @return
     */
    private BigDecimal calcularImporteExcento(BigDecimal diasExentoPagar) {
        SalarioMinimoEntity salarioMinimo = salarioMinimoRepository.obtenerSalarioMinimoActual();

        if (salarioMinimo != null) {
            return diasExentoPagar.multiply(salarioMinimo.getSalarioMinimo());
        } else {
            return BigDecimal.ZERO;
        }
    }
}