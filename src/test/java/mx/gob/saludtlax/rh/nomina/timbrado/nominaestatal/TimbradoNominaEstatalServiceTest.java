package mx.gob.saludtlax.rh.nomina.timbrado.nominaestatal;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.nomina.timbrado.DatosCFDINomina;
import mx.gob.saludtlax.rh.nomina.timbrado.DatosCFDITimbrado;
import mx.gob.saludtlax.rh.nomina.timbrado.DeduccionCFDI;
import mx.gob.saludtlax.rh.nomina.timbrado.HorasExtraCFDI;
import mx.gob.saludtlax.rh.nomina.timbrado.IncapacidadCFDI;
import mx.gob.saludtlax.rh.nomina.timbrado.PercepcionCFDI;
import mx.gob.saludtlax.rh.nomina.timbrado.TimbradoService;
import mx.gob.saludtlax.rh.nomina.timbrado.TimbrarNominaEJB;
import mx.gob.saludtlax.rh.util.InitTest;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
@Transactional
public class TimbradoNominaEstatalServiceTest  implements Serializable{

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
	public void timbrar(){
		ejb.procesarNominaEstatal();
		
		 
	}

}
