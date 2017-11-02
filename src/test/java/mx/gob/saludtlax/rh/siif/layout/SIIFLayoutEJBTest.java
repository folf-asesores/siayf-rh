/*
 * SIIFLayoutEJBTest.java
 * Creado el 26/06/2016 01:56:23 AM
 * 
 */
package mx.gob.saludtlax.rh.siif.layout;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import javax.inject.Inject;
import mx.gob.saludtlax.rh.excepciones.SistemaException;
import mx.gob.saludtlax.rh.persistencia.CuentasBancariasEntity;
import mx.gob.saludtlax.rh.persistencia.GenericRepository;
import mx.gob.saludtlax.rh.persistencia.Repository;
import mx.gob.saludtlax.rh.persistencia.SIIFEncabezadoEntity;
import mx.gob.saludtlax.rh.persistencia.SIIFEncabezadoRepository;
import mx.gob.saludtlax.rh.persistencia.SIIFLayoutStoredProcedure;
import mx.gob.saludtlax.rh.persistencia.SiifBitacoraEntity;
import mx.gob.saludtlax.rh.persistencia.TipoNominaEntity;
import mx.gob.saludtlax.rh.siif.EstructuraSeguroPopularDTO;
import mx.gob.saludtlax.rh.siif.reportarcontratos.EstructuraContratosDatDTO;
import mx.gob.saludtlax.rh.siif.reportarcontratos.EstructuraContratosTrailersDTO;
import mx.gob.saludtlax.rh.util.ArchivoUtil;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;


import org.junit.runner.RunWith;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@RunWith(Arquillian.class)
public class SIIFLayoutEJBTest {

    private static final Logger LOGGER = Logger.getLogger(SIIFLayoutEJBTest.class.getName());
    @Inject
    private SIIFLayout instance;

    @Deployment
    public static WebArchive createDeployment() {
        WebArchive war = ShrinkWrap.create(WebArchive.class)
                .addAsWebInfResource(EmptyAsset.INSTANCE,
                        ArchivePaths.create("beans.xml"))
                .addAsManifestResource("log4j.properties");

        JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
                .addAsManifestResource("META-INF/beans.xml", "beans.xml")
                .addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml")
                .addClass(ArchivoUtil.class)
                .addClass(CSVService.class)
                .addClass(CuentasBancariasEntity.class)
                .addClass(DatosPersonalesDTO.class)
                .addClass(DatosLaboralesDTO.class)
                .addClass(DetalleNominaDTO.class)
                .addClass(DetallePagoNominaDTO.class)
                .addClass(EstructuraContratosTrailersDTO.class)
                .addClass(EstructuraContratosDatDTO.class)
                .addClass(EstructuraSeguroPopularDTO.class)
                .addClass(GenericRepository.class)
                .addClass(Repository.class)
                .addClass(SiifBitacoraEntity.class)
                .addClass(SIIFDatService.class)
                .addClass(SIIFEncabezadoDTO.class)
                .addClass(SIIFEncabezadoEntity.class)
                .addClass(SIIFEncabezadoRepository.class)
                .addClass(SIIFEncabezadoService.class)
                .addClass(SIIFLayout.class)
                .addClass(SIIFLayoutEJB.class)
                .addClass(SIIFLayoutService.class)
                .addClass(SIIFLayoutStoredProcedure.class)
                .addClass(SIIFTraService.class)
                .addClass(SistemaException.class)
                .addClass(TipoNominaEntity.class)
                ;

        war.addAsLibraries(jar);

        File[] files = Maven.resolver().loadPomFromFile("pom.xml")
                .importRuntimeDependencies()
                .resolve()
                .withTransitivity().asFile();

        war.addAsLibraries(files);

        return war;
    }

    /**
     * Test of getDatTra method, of class SIIFLayoutEJB.
     * @throws java.io.IOException en caso de que no se pueda guardar el archivo
     * en la carpeta del usuario.
     */
    @Ignore
    @Test
    public void getDatTra() throws IOException {
        LOGGER.info("Iniciando test: getDatTra");
        byte[] result = instance.getDatTra(4);
        ArchivoUtil.guardarEnCarpetaUsuario(result, "dat-tra.zip");
        Assert.assertNotNull(result);
    }
}
