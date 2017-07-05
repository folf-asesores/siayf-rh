/*
 * PrenominaReporteTextoPlanoTest.java
 * Creado el 27/Mar/2017 10:45:10 AM
 *
 */

package mx.gob.saludtlax.rh.nomina.reportes.prenomina;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mx.gob.saludtlax.rh.util.ArchivoUtil;
import org.junit.Test;

/**
 *
 * @author Freddy Barrera (freddy.barrera@gmail.com)
 */
public class PrenominaReporteTextoPlanoTest {

    @Test
    public void generarReporte() throws IOException {
        // Percepciones y deducciones
        List<Percepcion> percepciones1 = new ArrayList<>();
        percepciones1.add(new Percepcion(5, "SUPLENCIAS", new BigDecimal("2180")));
        List<Percepcion> percepciones2 = new ArrayList<>();
        percepciones2.add(new Percepcion(5, "SUPLENCIAS", new BigDecimal("2180")));
        percepciones2.add(new Percepcion(26, "SUBSIDIO", new BigDecimal("55.5")));
        List<Percepcion> percepciones3 = new ArrayList<>();
        percepciones3.add(new Percepcion(5, "SUPLENCIAS", new BigDecimal("2180")));
        percepciones3.add(new Percepcion(26, "SUBSIDIO", new BigDecimal("55.5")));
        percepciones3.add(new Percepcion(32, "OTROS", new BigDecimal("3300.00")));
        List<Deduccion> deducciones1 = new ArrayList<>();
        deducciones1.add(new Deduccion(52, "I.S.R.", new BigDecimal("342.5")));
        List<Deduccion> deducciones2 = new ArrayList<>();
        deducciones2.add(new Deduccion(51, "FALTAS Y RETARDOS", new BigDecimal("34.415")));
        deducciones2.add(new Deduccion(52, "I.S.R.", new BigDecimal("342.5")));
        List<Deduccion> deducciones3 = new ArrayList<>();
        deducciones3.add(new Deduccion(51, "FALTAS Y RETARDOS", new BigDecimal("34.415")));
        deducciones3.add(new Deduccion(52, "I.S.R.", new BigDecimal("342.5")));
        deducciones3.add(new Deduccion(62, "PENSION ALIMENTICIA", new BigDecimal("3516")));

        // Nomina empleados
        NominaEmpleado nominaCaporal = new NominaEmpleado();
        nominaCaporal.setRfc("CAME750818R3A");
        nominaCaporal.setNombre("CAPORAL MENDIETA MA ELENA");
        nominaCaporal.setPercepciones(percepciones1);
        nominaCaporal.setDeducciones(deducciones1);

        NominaEmpleado nominaCordero = new NominaEmpleado();
        nominaCordero.setRfc("COGO770228MU5");
        nominaCordero.setNombre("CORDERO GONZALEZ OSVALDO");
        nominaCordero.setPercepciones(percepciones2);
        nominaCordero.setDeducciones(deducciones2);

        NominaEmpleado nominaContreras = new NominaEmpleado();
        nominaContreras.setRfc("COHS910628CD4");
        nominaContreras.setNombre("CONTRERAS HERNANDEZ SANDRA NELY");
        nominaContreras.setPercepciones(percepciones3);
        nominaContreras.setDeducciones(deducciones3);

        NominaEmpleado nominaCuecuecha = new NominaEmpleado();
        nominaCuecuecha.setRfc("CUMM8702236M7");
        nominaCuecuecha.setNombre("CUECUECHA MENDIETA MARTHA PAOLA");
        nominaCuecuecha.setPercepciones(percepciones2);
        nominaCuecuecha.setDeducciones(deducciones2);

        NominaEmpleado nominaCuecuecha2 = new NominaEmpleado();
        nominaCuecuecha2.setRfc("CUMY910419HI3");
        nominaCuecuecha2.setNombre("CUECUECHA MENDOZA YANET");
        nominaCuecuecha2.setPercepciones(percepciones1);
        nominaCuecuecha2.setDeducciones(deducciones1);

        NominaEmpleado nominaCuamatzi = new NominaEmpleado();
        nominaCuamatzi.setRfc("CUSE890829LS7");
        nominaCuamatzi.setNombre("CUAMATZI SANCHEZ EDUARDO");
        nominaCuamatzi.setPercepciones(percepciones3);
        nominaCuamatzi.setDeducciones(deducciones3);

        NominaEmpleado nominaCuapio = new NominaEmpleado();
        nominaCuapio.setRfc("CUSJ870703AG6");
        nominaCuapio.setNombre("CUAPIO SANCHEZ JAVIER");
        nominaCuapio.setPercepciones(percepciones2);

        NominaEmpleado nominaCuahutecatl = new NominaEmpleado();
        nominaCuahutecatl.setRfc("CUTJ8809095A0");
        nominaCuahutecatl.setNombre("CUAHUTECATL TETLACUILO JEMMY");
        nominaCuahutecatl.setPercepciones(percepciones3);
        nominaCuahutecatl.setDeducciones(deducciones3);

        NominaEmpleado nominaDelgado = new NominaEmpleado();
        nominaDelgado.setRfc("DESR741226269");
        nominaDelgado.setNombre("DELGADO SANTIAGO RUT");
        nominaDelgado.setDeducciones(deducciones1);

        NominaEmpleado nominaDominguez = new NominaEmpleado();
        nominaDominguez.setRfc("DOHE860510UT7");
        nominaDominguez.setNombre("DOMINGUEZ HERNANDEZ ELMAR ARMANDO");
        nominaDominguez.setPercepciones(percepciones3);

        Map<String, NominaEmpleado> nominasEmpleados = new HashMap<>();
        nominasEmpleados.put("CAME750818R3A", nominaCaporal);
        nominasEmpleados.put("COGO770228MU5", nominaCordero);
        nominasEmpleados.put("COHS910628CD4", nominaContreras);
        nominasEmpleados.put("CUMM8702236M7", nominaCuecuecha);
        nominasEmpleados.put("CUMY910419HI3", nominaCuecuecha2);
        nominasEmpleados.put("CUSE890829LS7", nominaCuamatzi);
        nominasEmpleados.put("CUSJ870703AG6", nominaCuapio);
        nominasEmpleados.put("CUTJ8809095A0", nominaCuahutecatl);
        nominasEmpleados.put("DESR741226269", nominaDelgado);
        nominasEmpleados.put("DOHE860510UT7", nominaDominguez);

        // Unidades responsables
        UnidadResponsable unidadResponsable = new UnidadResponsable();
        unidadResponsable.setNumeroUnidadResponsable(1300);
        unidadResponsable.setUnidadResponsable("SUBDIRECCIÓN DE PRIMER NIVEL");
        unidadResponsable.setNominasEmpleados(nominasEmpleados);

        Map<Integer, UnidadResponsable> unidadesResponsables = new HashMap<>();
        unidadesResponsables.put(1300, unidadResponsable);

        // Programas
        Programa programa = new Programa();
        programa.setIdPrograma(1);
        programa.setPrograma("ATENCIÓN A LA SALUD");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, 5);
        programa.setInicioPeriodo(cal.getTime());
        programa.setFinPeriodo(Calendar.getInstance().getTime());
        programa.setUnidadesResponsables(unidadesResponsables);

        Map<Integer, Programa> programas = new HashMap<>();
        programas.put(1, programa);

        // Producto de nómina
        ProductoNominaBuilder productoNominaBuilder = new ProductoNominaBuilder(30, Calendar.getInstance().getTime(), programas);
        productoNominaBuilder.setNombreElaboro("LIC. VICTOR JOSE LEAL CRUZ");
        productoNominaBuilder.setCargoElaboro("JEFE DE DEPTO. DE RECURSOS HUMANOS");

        productoNominaBuilder.setNombreReviso("C.P. LUZ MARIA PORTILLA GARCIA");
        productoNominaBuilder.setCargoReviso("DIRECTOR  DE ADMINISTRACION");

        productoNominaBuilder.setNombreAutorizo("DR. ALEJANDRO GUARNEROS CHUMACERO");
        productoNominaBuilder.setCargoAutorizo("DIRECTOR GENERAL DE SALUD DE TLAXCALA");

        ProductoNomina productoNomina = productoNominaBuilder.createProductoNomina();

        PrenominaReporteTextoPlano reporteTextoPlano = new PrenominaReporteTextoPlano();
        byte[] reporte = reporteTextoPlano.generar(productoNomina);
        ArchivoUtil.guardarEnCarpetaUsuario(reporte, "prenomina.txt");
    }

}
