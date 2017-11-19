
package mx.gob.saludtlax.rh.nomina.primavacacional;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;

import javax.inject.Inject;

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

import mx.gob.saludtlax.rh.nomina.primavacional.PrimaVacacionalParams;
import mx.gob.saludtlax.rh.nomina.primavacional.PrimaVacacionalResult;
import mx.gob.saludtlax.rh.nomina.primavacional.PrimaVacacionalService;
import mx.gob.saludtlax.rh.nomina.primavacional.TipoCalculoPrimaVacacionalEnum;

@RunWith(Arquillian.class)
public class PrimaVacacionalServiceTest implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -7064648014323157440L;

    @Inject
    PrimaVacacionalService primaVacacionalService;

    @Deployment
    public static WebArchive crearWar() {
        WebArchive war = ShrinkWrap.create(WebArchive.class).addAsWebInfResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));

        JavaArchive jar = ShrinkWrap.create(JavaArchive.class).addClass("mx.gob.saludtlax.rh.excepciones.SistemaException")
                .addClass("mx.gob.saludtlax.rh.excepciones.CodigoError").addClass("mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError")
                .addClass("mx.gob.saludtlax.rh.excepciones.ReglaNegocioException").addClass("mx.gob.saludtlax.rh.excepciones.ValidacionException")
                .addPackage("mx.gob.saludtlax.rh.nomina.primavacional").addPackage("mx.gob.saludtlax.rh.persistencia")
                .addClass("mx.gob.saludtlax.rh.persistencia.SalarioMinimoRepository").addClass("mx.gob.saludtlax.rh.persistencia.EmpleadoRepository")
                .addClass("mx.gob.saludtlax.rh.persistencia.ProgramaEntity").addClass("mx.gob.saludtlax.rh.persistencia.ProyectoTempEntity")
                .addClass("mx.gob.saludtlax.rh.persistencia.PuestoGeneralEntity").addClass("mx.gob.saludtlax.rh.persistencia.EstatusConfiguracionesEntity")
                .addClass("mx.gob.saludtlax.rh.persistencia.TipoContratacionEntity").addClass("mx.gob.saludtlax.rh.persistencia.EmpleadoEntity")
                .addClass("mx.gob.saludtlax.rh.persistencia.TipoEmpleadoEntity").addClass("mx.gob.saludtlax.rh.persistencia.CuentasBancariasEntity")
                .addClass("mx.gob.saludtlax.rh.persistencia.ConfiguracionPresupuestoEntity").addClass("mx.gob.saludtlax.rh.persistencia.TabuladorEntity")
                .addClass("mx.gob.saludtlax.rh.persistencia.PaisEntity").addClass("mx.gob.saludtlax.rh.persistencia.TiposNombramientosEntity")
                .addClass("mx.gob.saludtlax.rh.persistencia.DependenciaTempEntity")
                .addClass("mx.gob.saludtlax.rh.persistencia.SubFuenteFinanciamientoTempEntity")
                .addClass("mx.gob.saludtlax.rh.persistencia.UnidadResponsableEntity").addClass("mx.gob.saludtlax.rh.persistencia.FuenteFinanciamientoEntity")
                .addClass("mx.gob.saludtlax.rh.persistencia.TipoRecursoTempEntity").addClass("mx.gob.saludtlax.rh.persistencia.InventarioVacanteEntity")
                .addClass("mx.gob.saludtlax.rh.persistencia.InventarioVacanteRepository").addClass("mx.gob.saludtlax.rh.persistencia.SalarioMinimoEntity")
                .addClass("mx.gob.saludtlax.rh.persistencia.GenericRepository").addClass("mx.gob.saludtlax.rh.persistencia.Repository")
                .addClass("mx.gob.saludtlax.rh.util.TipoArchivo").addClass("mx.gob.saludtlax.rh.notificacion.Modulo")
                .addClass("mx.gob.saludtlax.rh.util.ServicioWebEnum").addClass("mx.gob.saludtlax.rh.expediente.EntidadContexto")
                .addClass("mx.gob.saludtlax.rh.puestosautorizados.InventarioVacanteDTO").addClass("mx.gob.saludtlax.rh.vacantes.seleccion.InfoPuestoDTO")
                .addClass("mx.gob.saludtlax.rh.vacantes.consulta.DatoGeneralCandidatoDTO")
                .addClass("mx.gob.saludtlax.rh.vacantes.seleccion.InfoVacantePostularDTO")
                .addClass("mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO").addClass("mx.gob.saludtlax.rh.puestosautorizados.PuestoConPermisoDTO")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml").addAsManifestResource("META-INF/persistence.xml", "persistence.xml");

        war.addAsLibraries(jar);

        File[] files = Maven.resolver().loadPomFromFile("pom.xml").importRuntimeDependencies().resolve().withTransitivity().asFile();
        war.addAsLibraries(files);

        return war;
    }

    @Ignore
    @Test
    public void calcularQuincenalTest() {
        PrimaVacacionalParams parametros = new PrimaVacacionalParams();
        parametros.setBasePrima(BigDecimal.ZERO);
        parametros.setTipoCalculo(TipoCalculoPrimaVacacionalEnum.QUINCENAL);
        PrimaVacacionalResult primaVacacionalResult = primaVacacionalService.calcular(parametros);

        System.out.println(primaVacacionalResult);
        Assert.assertNotNull(primaVacacionalResult);
    }

    @Ignore
    @Test
    public void calcularMensualTest() {
        PrimaVacacionalParams parametros = new PrimaVacacionalParams();
        parametros.setBasePrima(BigDecimal.ZERO);
        parametros.setTipoCalculo(TipoCalculoPrimaVacacionalEnum.MENSUAL);
        PrimaVacacionalResult primaVacacionalResult = primaVacacionalService.calcular(parametros);

        System.out.println(primaVacacionalResult);
        Assert.assertNotNull(primaVacacionalResult);
    }

}
