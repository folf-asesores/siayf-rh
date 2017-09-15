/*
 * FirmaMotorTest.java
 * Creado el 07/sep/2017 7:00:34 PM
 * 
 */

package mx.gob.saludtlax.rh.nomina.reportes.firma;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import org.jboss.logging.Logger;
import org.junit.Ignore;
import org.junit.Test;

import mx.gob.saludtlax.rh.util.ArchivoUtil;
import mx.gob.saludtlax.rh.util.FechaUtil;

import static org.junit.Assert.*;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class FirmaMotorTest {

    private static final Logger LOGGER = Logger.getLogger(FirmaMotorTest.class.getName());

    private static final String [] PROGRAMAS = {"CONTRATO", "FORTALECIMIENTO A LA ATENCION MEDICA", "CENTROS NUEVA VIDA", "AFIN ADMINISTRATIVO"};
    private static final String [][] UNIDADES_RESPONSABLES = {
        {"1250", "UNIDADES MEDICAS MOVILES"},
        {"1771", "H. I. T."},
        {"1721", "H.  DE LA MUJER"},
        {"1711", "H. G. DE TLAXCALA"},
        {"1600", "JEFATURA DE PLANEACION"}
    };
    private static final Object [][] EMPLEADOS = {
        {"BASA900305TG1", "BACA SOLIS JOSE ALBERTO", "0325190", new BigDecimal("10329")},
        {"AUBL7809098M4", "MEDINA CONTRERAS KARLA", "0325189", new BigDecimal("6632")},
        {"PEZM761125MG1", "PEREZ ZAMORA MARCOS", "0325191", new BigDecimal("2999")},
        {"SAHJ770308IL5", "SANCHEZ HERNANDEZ JUAN CARLOS", "0325192", new BigDecimal("2187")},
        {"RIDV800526T4A", "RIOS DUARTE VERONICA", "0325189", new BigDecimal("3124")},
    };

    /**
     * Test of obtenerArchivo method, of class FirmaMotor.
     * @throws java.io.IOException en caso de que haya un error de lectura.
     */
    @Ignore
    @Test
    public void testObtenerArchivo() throws IOException {
        FirmaMotor firmaMotor = new FirmaMotor();
        FirmaDTO firma = getFirma();
        byte[] reporte = firmaMotor.obtenerArchivo(firma);
        ArchivoUtil.guardarEnCarpetaUsuario(reporte, "firma-test.txt");
        assertNotNull(reporte);
    }

    private FirmaDTO getFirma() {
        
        Calendar calendar = Calendar.getInstance(FechaUtil.LUGAR_MEXICO);
        calendar.set(Calendar.DAY_OF_MONTH, 4);
        calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
        Date fechaPago =  calendar.getTime();
        return new FirmaDTO.Builder(1, fechaPago, getProgramas())
                // Elaboro
                .setNombreElaboro("LIC. VICTOR JOSE LEAL CRUZ")
                .setCargoElaboro("JEFE DE DEPTO. DE RECURSOS HUMANOS")
                
                // Reviso
                .setNombreReviso("C.P. LUZ MARIA PORTILLA GARCIA")
                .setCargoReviso("DIRECTOR  DE ADMINISTRACION")
                
                // Autorizo
                .setNombreAutorizo("DR. ALEJANDRO GUARNEROS CHUMACERO")
                .setCargoAutorizo("DIRECTOR GENERAL DE SALUD DE TLAXCALA")
                .construirFirmaDTO();
    }
    
    private Map<Integer, ProgramaDTO> getProgramas() {
        Calendar calendarInicio = Calendar.getInstance(FechaUtil.LUGAR_MEXICO);
        calendarInicio.set(Calendar.DAY_OF_MONTH, 1);
        calendarInicio.set(Calendar.MONTH, Calendar.FEBRUARY);

        Calendar calendarFin = Calendar.getInstance(FechaUtil.LUGAR_MEXICO);
        calendarFin.set(Calendar.DAY_OF_MONTH, 15);
        calendarFin.set(Calendar.MONTH, Calendar.FEBRUARY);

        Map<Integer, ProgramaDTO> programas = new TreeMap<>();
        
        for(int i = 0; i < PROGRAMAS.length; i++) {
            ProgramaDTO programa = new ProgramaDTO.Builder(i + 1, PROGRAMAS[i], calendarInicio.getTime(), calendarFin.getTime())
                    .setUnidadesResponsables(getUnidadesResponsables())
                    .construirProgramaDTO();
            programas.put(programa.getIdPrograma(), programa);
        }
        
        return programas;
    }

    private Map<String, UnidadResponsableDTO> getUnidadesResponsables() {
        Map<String,UnidadResponsableDTO> unidadesResponsables = new HashMap();
        Random rnd = new Random();
        int iTop = rnd.nextInt(UNIDADES_RESPONSABLES.length);
        
        for (int i = 0; i < iTop; i++) {
            UnidadResponsableDTO unidadResponsable = new UnidadResponsableDTO.Builder(UNIDADES_RESPONSABLES[i][0], UNIDADES_RESPONSABLES[i][1])
                    .setFirmasEmpleados(getFirmasEmpleados())
                    .construirUnidadResponsableDTO();
            unidadesResponsables.put(unidadResponsable.getNumeroUnidadResponsable(), unidadResponsable);
        }
        
        return unidadesResponsables;
    }

    private Map<String, FirmaEmpleadoDTO> getFirmasEmpleados() {
        Map<String, FirmaEmpleadoDTO> firmasEmpleados = new TreeMap<>();
        Random rnd = new Random();
        int iTop = rnd.nextInt(EMPLEADOS.length);
        
        for (int i = 0; i < iTop; i++) {
            FirmaEmpleadoDTO firmaEmpleado = new FirmaEmpleadoDTO.Builder()
                    .setFiliacion((String) EMPLEADOS[i][0])
                    .setNombre((String) EMPLEADOS[i][1])
                    .setNumeroCheque((String) EMPLEADOS[i][2])
                    .setImporte((BigDecimal) EMPLEADOS[i][3])
                    .construirFirmaEmpleadoDTO();
            
            firmasEmpleados.put(firmaEmpleado.getFiliacion(), firmaEmpleado);
//            firmasEmpleados.put("AUBL780909_" + String.format("%03d", i), firmaEmpleado);
        }
        
        return firmasEmpleados;
    }

}
