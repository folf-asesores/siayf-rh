
package mx.gob.saludtlax.rh.nomina.calculonomina;

import java.math.BigDecimal;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.nomina.calculonomina.isr.IsrService;
import mx.gob.saludtlax.rh.nomina.calculonomina.isr.ResultadoIsrDTO;

@Stateless
public class CalculoNominaEJB {

    @Inject
    private IsrService isrService;

    void calculoNomina() {
        BigDecimal ingresoGravable = BigDecimal.valueOf(3500.5);
        Integer peridiocidad = 4;
        Integer anio = 2016;
        ResultadoIsrDTO resultadoIsrDTO = isrService.calculoIsr(ingresoGravable, peridiocidad, anio);
    }
}