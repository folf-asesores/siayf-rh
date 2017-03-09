package mx.gob.saludtlax.rh.nomina.timbrado.exportarxml;

import java.io.Serializable;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import mx.gob.saludtlax.rh.nomina.exportarxml.ExportarXmlService;

@RunWith(Arquillian.class)
public class ExportXMLTest implements Serializable {
	private static final long serialVersionUID = 3945364712566019882L;
	@Inject
	ExportarXmlService service;

	@Deployment
	public static WebArchive createDeployment() {
		return InitExportXMLTest.crearWar();
	}

	@Test
	public void testExportXml() {
		service.exportarXml();
	}
}