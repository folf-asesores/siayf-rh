
package mx.gob.saludtlax.rh.nomina.timbrado.nominaestatal;

import java.io.Serializable;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import mx.gob.saludtlax.rh.nomina.timbrado.TimbrarNominaEJB;
import mx.gob.saludtlax.rh.util.InitTest;

@RunWith(Arquillian.class)
@Transactional
public class TimbradoNominaEstatalServiceTest implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1056012740716514919L;

    @Inject
    private TimbrarNominaEJB ejb;

    @Deployment
    public static WebArchive createDeployment() {

        return InitTest.crearWar();
    }

    @Test
    public void timbrar() {
        ejb.procesarNominaEstatal();

    }

}
