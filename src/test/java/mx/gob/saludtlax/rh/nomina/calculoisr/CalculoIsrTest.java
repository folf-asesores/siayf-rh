package mx.gob.saludtlax.rh.nomina.calculoisr;

import java.math.BigDecimal;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class CalculoIsrTest {

    @Inject
    CalculoIsrService calculoIsrService;

    @Deployment
    public static WebArchive createDeployment() {
        return InitCalculoIsrTest.crearWar();
    }

    @Test
    public void calculoIsr() {
    	System.out.println("Hola calculoIsr");
		ConfiguracionIsrDTO configuracionIsr = new ConfiguracionIsrDTO();
		BigDecimal sueldoQuincenal = new BigDecimal(9468);
		configuracionIsr.setBaseGravable(sueldoQuincenal);
		configuracionIsr.setIdTipoPeriodo(4);
		configuracionIsr.setIdEmpleado(38503);
		IsrDTO isr = calculoIsrService.calcularIsrEmpleado(configuracionIsr);
        System.out.println(isr);
    }
}
