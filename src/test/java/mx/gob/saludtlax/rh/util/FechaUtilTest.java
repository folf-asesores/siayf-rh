/*
 * FechaUtilTest.java
 * Creado el Sep 23, 2016 2:48:02 PM
 * 
 */
package mx.gob.saludtlax.rh.util;

import java.util.Calendar;
import java.util.Date;
import org.junit.Test;
import org.junit.Ignore;


/**
 *
 * @author Freddy Barrera <freddy.barrera@folfasesores.com.mx>
 */
public class FechaUtilTest {
    
    @Ignore
    @Test
    public void probarFecha() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 18);
        Date fechaOrigen = calendar.getTime();
        Date[] fechas = FechaUtil.obtenerFechasInicioFinal(fechaOrigen, FechaUtil.FECHAS_POR_SEMANA);
        
        System.out.println("Fecha inicial: " +  fechas[0]);
        System.out.println("Fecha final: " +  fechas[1]);
    }
}
