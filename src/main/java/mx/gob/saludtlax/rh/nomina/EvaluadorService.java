package mx.gob.saludtlax.rh.nomina;

import java.math.BigDecimal;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.configuracion.conceptosnomina.ConceptoNominaFederalesDTO;
import mx.gob.saludtlax.rh.configuracion.tabulador.TabuladorDTO;
import mx.gob.saludtlax.rh.configuracion.tabulador.TabuladorService;
import mx.gob.saludtlax.rh.persistencia.NominaEmpleadoEntity;
import mx.gob.saludtlax.rh.util.FechaUtil;

public class EvaluadorService {
    @Inject
    private TabuladorService tabuladorService;

    public BigDecimal evaluarFormula(ConceptoNominaFederalesDTO conceptoFederal,
            NominaEmpleadoEntity nominaEmpleado) {
    	TabuladorDTO tabulador = tabuladorService
            .obtenerTabuladorPorPuesto(nominaEmpleado.getIdPuestoGeneral().getIdPuestoGeneral(), FechaUtil.ejercicioActual());
        String formulanueva = conceptoFederal.getFormula();
        if (tabulador == null ) {
        	return BigDecimal.ZERO;
        } else {
            formulanueva = formulanueva.replace("EX0700", (tabulador.getSueldoBrutoMensual() == null ? "0.0" : tabulador.getSueldoBrutoMensual() + "" ) );
            formulanueva = formulanueva.replace("EX4200", (tabulador.getAsignacionBrutaMensual() == null ? "0.0" : tabulador.getAsignacionBrutaMensual() + "" ));
            formulanueva = formulanueva.replace("EX55AG", (tabulador.getAgaBrutaMensual() == null ? "0.0" : tabulador.getAgaBrutaMensual() + "" ));
            Evaluador evaluador = new Evaluador(formulanueva);
            return new BigDecimal( evaluador.getResult());
        }
    }
}