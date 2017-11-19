
package mx.gob.saludtlax.rh.nomina;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.configuracion.conceptosnomina.ConceptoNominaFederalesDTO;
import mx.gob.saludtlax.rh.configuracion.conceptosnomina.ConceptoNominaFederalesService;
import mx.gob.saludtlax.rh.configuracion.tabulador.TabuladorDTO;
import mx.gob.saludtlax.rh.configuracion.tabulador.TabuladorService;
import mx.gob.saludtlax.rh.nomina.movimientos.MovimientosService;
import mx.gob.saludtlax.rh.persistencia.NominaEmpleadoEntity;
import mx.gob.saludtlax.rh.util.FechaUtil;

public class EvaluadorService {
    @Inject
    private TabuladorService tabuladorService;

    @Inject
    private MovimientosService service;

    @Inject
    private ConceptoNominaFederalesService conceptoNominaService;

    public BigDecimal evaluarFormula(ConceptoNominaFederalesDTO conceptoFederal, NominaEmpleadoEntity nominaEmpleado) {
        TabuladorDTO tabulador = tabuladorService.obtenerTabuladorPorPuesto(nominaEmpleado.getIdPuestoGeneral().getIdPuestoGeneral(),
                FechaUtil.ejercicioActual());
        String formula = conceptoFederal.getFormula();
        //
        // System.out.println("Formula para nomina:: " + formula+
        // formula.contains("C30"));
        if (tabulador == null || formula == null) {
            return BigDecimal.ZERO;
        } else {

            // Se realiza la busqueda por empleado si tiene algun concepto 30
            // asignado
            List<ConceptoNominaFederalesDTO> conceptos30 = null;
            if (formula.contains("C30") || formula.contains("CA1")) {
                conceptos30 = conceptoNominaService.obtenerConceptosPorConfiguracionPresupuestal(nominaEmpleado.getIdConfiguracionPresupuestal().getId());

                if (conceptos30 != null) {
                    String formula30 = "0.0";
                    String formulaA1 = "0.0";
                    for (ConceptoNominaFederalesDTO conceptoNominaFederales : conceptos30) {
                        if (conceptoNominaFederales.getClave().contains("30BR") || conceptoNominaFederales.getClave().contains("30MR")
                                || conceptoNominaFederales.getClave().contains("30AR")) {
                            formula30 = conceptoNominaFederales.getFormula();
                        }
                        if (conceptoNominaFederales.getClave().contains("A100") || conceptoNominaFederales.getClave().contains("A200")
                                || conceptoNominaFederales.getClave().contains("A300") || conceptoNominaFederales.getClave().contains("A400")
                                || conceptoNominaFederales.getClave().contains("A500")) {
                            formulaA1 = conceptoNominaFederales.getFormula();
                        }
                    }

                    for (ConceptoNominaFederalesDTO conceptoNominaFederales : conceptos30) {

                        formula = formula.replace("C30", (formula30));
                        formula = formula.replace("CA1", (formulaA1));
                        //						System.out.println("Formula concepto a1: " + formula);

                    }
                }
            }

            formula = formula.replace("EX0700", (tabulador.getSueldoBrutoMensual() == null ? "0.0" : tabulador.getSueldoBrutoMensual() + ""));
            formula = formula.replace("EX4200", (tabulador.getAsignacionBrutaMensual() == null ? "0.0" : tabulador.getAsignacionBrutaMensual() + ""));
            formula = formula.replace("EX55AG", (tabulador.getAgaBrutaMensual() == null ? "0.0" : tabulador.getAgaBrutaMensual() + ""));

            Evaluador evaluador = new Evaluador(formula);
            return new BigDecimal(evaluador.getResult());
        }
    }

}