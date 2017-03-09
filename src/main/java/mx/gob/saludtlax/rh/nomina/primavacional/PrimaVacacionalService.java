package mx.gob.saludtlax.rh.nomina.primavacional;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.persistencia.SalarioMinimoEntity;
import mx.gob.saludtlax.rh.persistencia.SalarioMinimoRepository;

@Stateless
public class PrimaVacacionalService implements Serializable {
	private static final long serialVersionUID = 542713257347272054L;

    @Inject private SalarioMinimoRepository salarioMinimoRepository;

    private final Integer MESES_MINIMOS_ANTIGUEDAD = 6;

	/**
	 * Realiza el calculo de la prima vacacional.
	 * 
	 * @param idEmpleado
	 *            del empleado a calcular
	 * @param tipoCalculo
	 *            tipo calculo que se realiza de la prima 0 QUINCENAL 1 MENSUAL
	 * @return PrimaVacacionaResult objeto con la informacion del importe pagar
	 *         y los meses de antiguedad que tiene el empleado
	 */
    public PrimaVacacionalResult calcular(PrimaVacacionalParams params) {
//		InventarioVacanteEntity inventarioVacanteEntity = inventarioVacanteRepository
//				.obtenerPuestoPorIdEmpleado(params.getConfiguracionPresupuesto().getEmpleado().getIdEmpleado());
//
//		if (inventarioVacanteEntity == null) {
//			throw new ReglaNegocioException("El empleado no tiene una vacante asignada",
//					ReglaNegocioCodigoError.CONFIGURACION_NO_EXISTE);
//		}
//
//		if (inventarioVacanteEntity.getConfiguracion().getSueldo() == null) {
//            throw new ReglaNegocioException("La configuracion del empleado no cuenta con un salario asignado",
//					ReglaNegocioCodigoError.CONFIGURACION_NO_EXISTE);
//		}

		// Tenemos el salario mensual del empleado
		BigDecimal salarioMensual = params.getBasePrima();

		Integer antiguedadMeses = calcularMesesAntiguedad(params.getConfiguracionPresupuesto().getFechaInicioLabores());

		PrimaVacacionalResult primaVacacionalResult = new PrimaVacacionalResult();

		primaVacacionalResult.setAntiguedadEmpleado(antiguedadMeses);
		primaVacacionalResult.setFechaIngreso(params.getConfiguracionPresupuesto().getFechaInicioLabores());
		BigDecimal primaVacacional = BigDecimal.ZERO;
		// Validamos los meses de antiguedad del empleado
		if (antiguedadMeses > MESES_MINIMOS_ANTIGUEDAD) {
			// Dependiendo el tipo calculo se realiza el calculo de la prima
			// vacacional
	        BigDecimal salarioPorDia = salarioMensual.divide(new BigDecimal(30),4);
		    primaVacacional = salarioPorDia.multiply(params.getDiasPagar());
		}

		BigDecimal importeExcento = calcularImporteExcento(params.getDiasExentoPagar());

        primaVacacionalResult.setTotal(primaVacacional);

        if (primaVacacional.compareTo(importeExcento) > 0) {
            primaVacacionalResult.setExcento(importeExcento);
            primaVacacionalResult.setGravado(primaVacacional.subtract(importeExcento));
        } else {
            primaVacacionalResult.setExcento(primaVacacional);
            primaVacacionalResult.setGravado(BigDecimal.ZERO);
        }

		return primaVacacionalResult;
	}

	private BigDecimal calcularImporteExcento(BigDecimal diasExentoPagar) {
        SalarioMinimoEntity salarioMinimo = salarioMinimoRepository.obtenerSalarioMinimoActual();

        if (salarioMinimo != null) {
            return diasExentoPagar.multiply(salarioMinimo.getSalarioMinimo());
        } else {
            return BigDecimal.ZERO;
        }
    }

    private Integer calcularMesesAntiguedad(Date ingreso) {

		Calendar fechaActual = GregorianCalendar.getInstance();
		Calendar fechaIngreso = GregorianCalendar.getInstance();
		fechaIngreso.setTime(ingreso);

		Integer meses = 0;

		while (fechaIngreso.before(fechaActual)) {

			fechaIngreso.add(Calendar.MONTH, 1);

			meses++;

		}
		return meses;
	}

}
